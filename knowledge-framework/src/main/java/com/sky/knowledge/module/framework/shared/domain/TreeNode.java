package com.sky.knowledge.module.framework.shared.domain;

import java.util.ArrayList;
import java.util.List;

import com.sky.knowledge.module.framework.shared.entity.BaseEntity;

public class TreeNode<T extends BaseEntity> {

	//树节点ID
	private String id;
	
	//树节点文本显示
	private String text;
	
	//是否叶子节点
	private Boolean leaf;
	
	//父节点ID
	private String parentId;
	
	//显示是否选择
	private Boolean checked;
	
	//显示图标
	private String iconCls;
	
	//节点对象数据
	private T entity;
	@SuppressWarnings("rawtypes")
	
	//孩子节点
	private List<TreeNode> childNodes = new ArrayList<TreeNode>();


	@SuppressWarnings("rawtypes")
	public List<TreeNode> getChildNodes() {
		return childNodes;
	}

	public String getId() {
		return id;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public String getParentId() {
		return parentId;
	}

	public String getText() {
		return text;
	}

	@SuppressWarnings("rawtypes")
	public void setChildNodes(List<TreeNode> childNodes) {
		this.childNodes = childNodes;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}
	
}
