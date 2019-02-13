package com.sky.knowledge.module.login.server.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;

import com.sky.knowledge.module.framework.cache.CacheManager;
import com.sky.knowledge.module.framework.cache.ICache;
import com.sky.knowledge.module.framework.server.context.UserContext;
import com.sky.knowledge.module.framework.server.web.action.AbstractAction;
import com.sky.knowledge.module.framework.shared.define.FunctionType;
import com.sky.knowledge.module.framework.shared.domain.Function;
import com.sky.knowledge.module.framework.shared.domain.TreeNode;
import com.sky.knowledge.module.framework.shared.domain.User;
import com.sky.knowledge.module.framework.shared.entity.IFunction;
import com.sky.knowledge.module.framework.shared.entity.IUser;
import com.sky.knowledge.module.framework.shared.exception.security.UserNotLoginException;
@Controller
public class MenuAction extends AbstractAction{

	private static final long serialVersionUID = 1851855354424501485L;

	//子功能信息集合
	private List<Function> functions;
	
	//父功能编码
	private String node;
	
	//子系统信息集合
	private List<Function> subSystemNodes;
	
	//功能树节点集合
	private List<TreeNode> nodes;

	/**
	 * 得到用户拥有的子系统功能信息
	 * @return 
	 */
	public String loadSubSystem() {
		subSystemNodes=findFunction(FunctionType.SUBSYSTEM,null);
		return SUCCESS;
	}

	//查询功能方法
	@SuppressWarnings("unchecked")
	private List<Function> findFunction(String type,String parentCode) {
		IUser user = UserContext.getCurrentUser();
		if (user == null) {
			throw new UserNotLoginException();
		}
		ICache<String, IFunction> functionCache = CacheManager.getInstance().getCache(IFunction.class.getName());
		Set<String> functionCodes = ((User) user).getFunctionCodes();
		List<Function> functionNodes = new ArrayList<Function>();
		for (IFunction func : functionCache.get().values()) {
			Byte functionType = ((Function)func).getFunctionType();
			if(functionType==null||functionType.toString().equals(FunctionType.BUTTON)){
				continue;
			}
			//如果当前用户有该功能，则加入用户功能集合中
			if(type!=null){
				if(functionType.toString().equals(type)){
					if(functionCodes.contains(func.getFunctionCode())){
						functionNodes.add((Function) func);					
					}
				}				
			}else{
				if(functionCodes.contains(func.getFunctionCode())){
					Function parentFunction = ((Function) func).getParentCode();
					if(parentFunction==null){
						continue;
					}
					String functionCode = parentFunction.getFunctionCode();					
					if(functionCode.equals(parentCode)){
						functionNodes.add((Function) func);									
					}
				}
			}
		}
		//对子系统功能进行排序
		Collections.sort(functionNodes,new Comparator<Function>() {

			public int compare(Function o1, Function o2) {
				return o1.getDisplayOrder().compareTo(o2.getDisplayOrder());
			}
			
		});
		return functionNodes;
	}

	/**
	 * 
	 * @description 查询树的节点
	 * @version 1.0
	 * @author patern
	 * @update 2011-10-12 下午01:16:50
	 */
	public String loadTree() {
		List<Function> functions=findFunction(null,node);
		nodes = new ArrayList<TreeNode>();
		for(Function fun : functions){
			TreeNode<Function> treeNode = new TreeNode<Function>();
			treeNode.setId(fun.getFunctionCode());
			treeNode.setText(fun.getFunctionName());
			if(fun.getFunctionType()==3){
				treeNode.setLeaf(true);
				treeNode.setIconCls("treeNodeLeafIcon");
			}else{
				treeNode.setIconCls("treeNodePackageIcon");
				treeNode.setLeaf(false);
			}
			if (fun.getParentCode()!=null) {
				treeNode.setParentId(fun.getParentCode().getFunctionCode());
			} else {
				treeNode.setParentId(null);
			}
			treeNode.setEntity(fun);
			nodes.add(treeNode);
		}
		return SUCCESS;
	}
	
	/**
	 * 保持会话
	 */
	public String keepSessionOnLine(){
		return SUCCESS;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public List<TreeNode> getNodes() {
		return nodes;
	}

	public List<Function> getFunctions() {
		return functions;
	}

	public List<Function> getSubSystemNodes() {
		return subSystemNodes;
	}
}