// require 类似java import
Ext.require([ '*' ]);
/**
 * 5分钟发一个空请求给服务器，让session不超时
 */ 
setInterval(function() {
	Ext.Ajax.request({
		url : 'keepSessionOnLine.action'
	});
}, 5 * 1000 * 60);

/**
 * EXTJS出现的窗口用上top那么EXT对象就不是iframe里面的对象了，
 * 所以要重写里面的getNode方法，在去查找iframe里面的EXT对象
 */
Ext.override(Ext.view.AbstractView, {
    getNode : function(nodeInfo) {
        if (!this.rendered) {
            return null;
        }
        if (Ext.isString(nodeInfo)) {
            return window.document.getElementById(nodeInfo);
        }
        if (Ext.isNumber(nodeInfo)) {
            return this.all.elements[nodeInfo];
        }
        if (nodeInfo instanceof Ext.data.Model) {
            return this.getNodeByRecord(nodeInfo);
        }
        if(window.tabPanel!=null){
        	var activeTabId = window.tabPanel.activeTab.id;
        	var index = Ext.Array.indexOf( window.tabPanel.items.keys, activeTabId, 1);
        	if(Ext.isIE){
        		var frame = window.frames[index];
        	}else{
        		var frame = window.frames[1+index];
        	}
        	
        	if(frame!=null && frame.Ext!=undefined){
        		if (nodeInfo instanceof frame.Ext.data.Model) {
        			return this.getNodeByRecord(nodeInfo);
        		}        		
        	}        	
        }
        return nodeInfo; 
    }
});

/**
 * 得到应用名
 * @returns
 */
function getAppName() {
	var str = (window.location.pathname).split("/");
	return str[1];
}

/**
 * return 当前登录人信息
 */
function getCurrentLoginUser(){
	var currentLoginUser;
	Ext.Ajax.request({
				url : 'queryCurrentLoginUser.action',
				method : 'post',
				async : false,
				success : function(r, o) {
					var obj = Ext.decode(r.responseText);
					currentLoginUser = obj.currentLoginUser;
				},
				exception : function(){
					window.location.href = Ext.get("basePath").dom.value+'500ErrorPage.jsp';
				},
				failure : function(){
					window.location.href = Ext.get("basePath").dom.value+'500ErrorPage.jsp';
				}
			});
	return currentLoginUser;
}

/** 上层的员工基本信息 * */
Ext.define('Fssc.welcome.BaseInfoPanel', {
    extend : 'Ext.panel.Panel',
    cls : 'welcome_user',
    constructor : function(config) {
        var me = this, cfg = Ext.apply({}, config);
        var htmlView = '', app_currentusername, app_currentusercode, app_currentuserdept;
        htmlView += '<h2>您好，欢迎使用财务自助系统！</h2>';
        
        //得到当前登录用户信息
        var currentLoginUser = getCurrentLoginUser();
        if(currentLoginUser == null){
        	
        	app_currentusername = 'undefined';
        	app_currentusercode = 'undefined';
        	app_currentuserdept = 'undefined';
        	
        }else{
        	//员工名称
        	app_currentusername = currentLoginUser.empCode.empName;
        	//工号
        	app_currentusercode = currentLoginUser.empCode.empCode;
        	//所属部门
        	app_currentuserdept = currentLoginUser.empCode.deptId.deptName;
        }
        
        htmlView += '<span>当前用户：' + app_currentusername + '</span>';
    	htmlView += '<span>工号 ：' + app_currentusercode + '</span>';
    	htmlView += '<span>部门：' + app_currentuserdept + '</span>';
    	
    	me.update(htmlView);
        me.callParent([cfg]);
    }
});

/**
 * 得到首页上的指定类型数据
 * @param {} dataType
 * @return {}
 */
function getHomeData(dataType){
	if(dataType == null || dataType == 'undefind' || dataType == ''){
		return null;
	}
	var homeData;
	Ext.Ajax.request({
				url : 'homePage.action',
				method : 'post',
				async : false,
				params : {
					'homePageEntity.dataType' : dataType
				},
				success : function(r, o) {
					var obj = Ext.decode(r.responseText);
					homeData = obj.list;
				},
				exception : function(){
					window.location.href = Ext.get("basePath").dom.value+'500ErrorPage.jsp';
				},
				failure : function(){
					window.location.href = Ext.get("basePath").dom.value+'500ErrorPage.jsp';
				}
	});
	return homeData;
}
/** 快捷功能* */
Ext.define('Fssc.welcome.fastFuntionPanel', {
    extend : 'Ext.panel.Panel',
    title : '快捷功能',
    cls : 'welcome_button',
    constructor : function(config) {
       //向后台请求得到所有LINK数据
       var linkData = getHomeData('TYPE_LINK');
       if(linkData != null){
	       var me = this, cfg = Ext.apply({}, config);
	        var htmlView = '<div class="img_list"><ul>';
	        
       		for(var i=0; i<linkData.length; i++){
				  htmlView+= '<li><a href="#" title = "' + (linkData[i].fun != null ? '' : '没有权限') + '" onclick=addTab(null,"'+linkData[i].functionCode+'","'+linkData[i].title+'","' + (linkData[i].fun != null ? linkData[i].fun.uri : null) + '")><img src="../images/login/'+linkData[i].imgSrc+'" />'+linkData[i].title+'</a></li>'; 
       		}
       		
        	htmlView+= '</ul></div>';
	        me.update(htmlView);
	        me.callParent([cfg]);
       }
    }
});

/** 通知公告* */
Ext.define('Fssc.welcome.noticePanel', {
    extend : 'Ext.panel.Panel',
    title : '帮助文档',
    cls : 'welcome_button',
    constructor : function(config) {
        var helpData = getHomeData('TYPE_HELP');
        if(helpData != null){
	        var me = this, cfg = Ext.apply({}, config);
	        var htmlView = '<ul>';
        	for(var i=0; i<helpData.length; i++){
        		//最多显示多个
        		if(i == 6){
        			break;
        		}
     			htmlView += '<li><a href="javascript:downLoadFile(\''+helpData[i].homePageID+'\')"><FONT style="FONT-SIZE: 14px;">'+helpData[i].title+'</FONT></a></li>';
        	}
        	htmlView += '</ul>';
	        me.update(htmlView);
	        me.callParent([cfg]);
        }
    }
});

/**
 * 文件下载
 * @param {} fileName
 */
function downLoadFile(fileName) {
    var url = '../login/download.action?homePageEntity.homePageID=' + fileName;
    window.location.href = url;
}
var winWidth = 0;
var winHeight = 0;
//获取尺寸
function findDimensions()
{
    window.onresize = null;
    // 获取窗口宽度
    if (window.innerWidth)
        winWidth = window.innerWidth;
    else if ((document.body) && (document.body.clientWidth))
        winWidth = document.body.clientWidth;
    // 获取窗口高度
    if (window.innerHeight)
        winHeight = window.innerHeight;
    else if ((document.body) && (document.body.clientHeight))
        winHeight = document.body.clientHeight;

    // 通过深入Document内部对body进行检测，获取窗口大小
    if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth) {
        winHeight = document.documentElement.clientHeight;
        winWidth = document.documentElement.clientWidth;
    }
};

findDimensions();
var topHeight = 71;
var footerHeight = 25;
var centerHeight = winHeight - (topHeight + footerHeight);

//main:首页
Ext.define('Fssc.main.WelcomePanel',{
    extend : 'Ext.panel.Panel',
    id : 'T_home',
    title : '欢迎页',
    height : centerHeight,
    cls : 'welcomepanel',
    border : false,
    closable : false,
    autoScroll : true,
    layout : 'column',
    defaults : {
        columnWidth : 1
    },
    baseInfo : null,
    getBaseInfo : function() {//登录信息
        if (this.baseInfo == null) {
            this.baseInfo = Ext.create('Fssc.welcome.BaseInfoPanel', {
                height : 90
            });
        }
        return this.baseInfo;
    },
    fastFunction : null,
    getFastFunction : function() {
    },
    setFastFunction : function(fastFunction) {
        this.fastFunction = fastFunction
    },
    welNotice : null,
    getWelNotice : function() {//快捷链接
        if (this.welNotice == null) {
            this.welNotice = Ext.create('Fssc.welcome.fastFuntionPanel');
        }
        return this.welNotice;
    },
    noticePanel : null,
    getNoticePanel : function() {//帮助信息
        if (this.noticePanel == null) {
            this.noticePanel = Ext.create('Fssc.welcome.noticePanel');
        }
        return this.noticePanel;
    },
    constructor : function(config) {
        var me = this, cfg = Ext.apply({}, config);
        me.items = [me.getBaseInfo(),  me.getWelNotice(), me.getNoticePanel()];
        me.callParent([cfg]);
    }
})

/**
 * 定义工作区Panel
 */
var tabPanel = Ext.create('Ext.tab.Panel', {
    cls:'nav_tab',
    border : false,
    id : 'tabPanelId',
	region : 'center',
	items : [ Ext.create('Fssc.main.WelcomePanel')],
	listeners:{
			tabchange : function(tabPanel, newCard, oldCard, eOpts) {
                var panel = Ext.getCmp(newCard.id);
                if (panel) {
                    panel.doLayout();
                }
            },
            //左边菜单折叠时，使右边模块自动适应/自动填充
            resize : function(panel, width, height, oldWidth, oldHeight, eOpts) {
                var tabContent = Ext.getCmp("tabPanelId");
                if (tabContent) {
                    tabContent.doLayout();
                }
            }
	}
});

// 定义顶部Panel
var topPanel = Ext.create('Ext.panel.Panel',{
	region : 'north',
	id:'topPanel',
	cls:'topPanel',
	
	height : 58,
	border:false,
	html : '<iframe src ="topRegion.action" width="100%" frameborder="no" border="0" framespacing="0" align="left" allowtransparency="true"></iframe>'

});

// 定义底部Panel
var bottomPanel = Ext.create('Ext.panel.Panel',{
	region : "south", // 南方布局
	height : 28,// 高度
	border:false,
	cls:'footerPanel',
	html : '<iframe src ="bottomRegion.action" width="100%" frameborder="no" border="0" framespacing="0" align="left"></iframe>'
});

// 菜单区
var accordion = new Ext.Panel({
    autoScroll:true,
	region : 'west',
	cls:'menu',
	useArrows: true,
	iconCls : "sysmanagemenu",
	collapsible : true,
	split : false,  //收缩的箭头
	width : 230,// 宽度
	layout: 'fit',
	items : []
});



// 加载子系统树的节点
function loadTree() {
	var index = 1;
	var tree = new Ext.tree.Panel({
		rootVisible : false,
		iconCls : "root"+index++,
		useArrows: true,
		id : 'mainTreeId',
		bodyCls : 'menu_container',
		root : {
			id : '0',
			expanded : true
		},
		store : Ext.create('Ext.data.TreeStore', {
			proxy : {
				type : 'ajax',
				actionMethods : 'POST',
				async : false,
				url : 'loadTree.action',
				reader : {
					type : 'json',
					root : 'functions'
				}
			}
		})
	});
	// 给tree添加鼠标点击事件
	tree.on("itemclick", treeClickAction, this);
	// 将树挂到左边的菜单上
	accordion.add(tree).doLayout();
}

// //////////////////////树的事件////////////////////////////////

function treeClickAction(node, record, item, index, e) {
	e.preventDefault();// 阻止浏览器默认行为处理事件
	var child = node.getSelectionModel().getSelection()[0];
	if (child.isLeaf()) {
		addTab(child);
	}
}

/**
 * 手动展开通用管理菜单
 */
function openTree(){
	var tree = Ext.getCmp('mainTreeId');//主菜单
	var level1 = '01';
//	var level1 = '10';
	var level2 = '01001';
//	var level2 = '10001';
	tree.getStore().load({
		node : tree.getStore().getNodeById(level1),//先加载1级，也就是综合管理子系统
		callback : function(){
			if(tree.getStore().getNodeById(level1) == null ||
				tree.getStore().getNodeById(level1) == 'undefinde'){
					Ext.Msg.alert('提示','你没有此功能的权限!');
				return;
			}
			tree.getStore().getNodeById(level1).expand();//然后展开
			tree.getStore().load({
				node : tree.getStore().getNodeById(level2),//再加载2级，也就是通用管理
				callback : function(){
					if(tree.getStore().getNodeById(level2) != null &&
						tree.getStore().getNodeById(level2) != 'undefinde'){
							
						tree.getStore().getNodeById(level2).expand();//然后展开
					}
				}
			});
		}
	});
}
/**
 * 退出系统方法
 */
function loginOut(){
	Ext.Msg.confirm('提示','是否确定退出!', function(optional){
		if(optional == 'yes'){
			window.location.href = 'logout.action';
		}
	});
}

/**
 * 初始化
 */
Ext.onReady(function() {
	setTimeout(function() {
		Ext.get('loading').remove();
		Ext.get('loading-mask').fadeOut({
			remove : true
		});
	}, 1000);
	Ext.Ajax.request({
		url : 'loadSubSystem.action',
		success : function(response, opts) {
			var subSystemNodes = Ext.decode(response.responseText).subSystemNodes;
			loadTree();
			Ext.create('Ext.container.Viewport', {
				layout : 'border',
				border:false,
				items : [ accordion, tabPanel, topPanel, bottomPanel ]
			});
		},
		exception : function(){
			window.location.href = Ext.get("basePath").dom.value+'500ErrorPage.jsp';
		},
		failure : function(){
			window.location.href = Ext.get("basePath").dom.value+'500ErrorPage.jsp';
		}
	});
});

// 打开tab页
function addTab(functionModel, id, title, uri) {
	var tabId, tabTitle, tabUri;
	if(functionModel != null){
		tabId = functionModel.data.id;
		tabTitle = functionModel.data.text;
		tabUri = functionModel.raw.entity.uri;
	}else{
		if(uri != null && uri != 'undefind' && uri != 'null' && uri != ''){
			tabId = id;
			tabTitle = title;
			tabUri = uri;
		}else{
			return;
		}
	}
	var tab = Ext.getCmp(tabId);
	if (tab) {
		tabPanel.setActiveTab(tab);
	} else {
		tabPanel.add({
			id : tabId,
			title : tabTitle,
			html : '<iframe id="iframe_'+tabId+'" src="'
					+ '/'
					+ getAppName()
					+ tabUri
					+' " width=100% height=100% marginwidth="0" framespacing="0" marginheight="0" frameborder="0" ></iframe>',
			closable : true
		}).show();
		tab = Ext.getCmp(tabId);
		tabPanel.setActiveTab(tab);
	}
}
