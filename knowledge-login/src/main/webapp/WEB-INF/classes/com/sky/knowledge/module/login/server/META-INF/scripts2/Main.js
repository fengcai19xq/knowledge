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
 * 定义工作区Panel
 */
var tabPanel = Ext.create('Ext.tab.Panel', {
    style : {
    	'background-color' : 'rgb(223, 223, 246)'
    },
    border : false,
	region : 'center',
	items : [ {
		iconCls : 'home',
		cls : "main",
		title : '首页',
		html : "<div style='background-color: #FFFFFF' width='100%' height='100%' align='center'><img src='../images/login/welcome_image.jpg'></div>"
	} ]
});

// 定义顶部Panel
var topPanel = Ext.create('Ext.panel.Panel',{
	margin : '0,5,0,5',
	region : 'north',
	height : 58,
	border:false,
	html : '<iframe src ="topRegion.action" width="100%" frameborder="no" border="0" framespacing="0" align="left"></iframe>'

});

// 定义底部Panel
var bottomPanel = Ext.create('Ext.panel.Panel',{
	region : "south", // 南方布局
	height : 28,// 高度
	border:false,
	html : '<iframe src ="bottomRegion.action" width="100%" frameborder="no" border="0" framespacing="0" align="left"></iframe>'
});

// 菜单区
var accordion = new Ext.Panel({
    autoScroll:true,
	region : 'west',
	title : '系统功能',
	iconCls : "sysmanagemenu",
	collapsible : true,
	split : true,
	width : 200,// 宽度
	layout : 'accordion',
	items : []
});



// 加载子系统树的节点
function loadTree(subSystemNodes) {
	var index = 1;
	Ext.each(subSystemNodes,function(subSystem){
		if(index>5){
			index=1;
		}
		var tree = new Ext.tree.Panel({
			rootVisible : false,
			title : subSystem.functionName,
			iconCls : "root"+index++,
			useArrows: true,
			root : {
				id : subSystem.functionCode,
				expanded : true
			},
			store : Ext.create('Ext.data.TreeStore', {
				proxy : {
					type : 'ajax',
					actionMethods : 'POST',
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
	});
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
			loadTree(subSystemNodes);
			Ext.create('Ext.container.Viewport', {
				layout : 'border',
				border:false,
				items : [ accordion, tabPanel, topPanel, bottomPanel ]
			});
		}
	});
});

// 打开tab页
function addTab(functionModel) {
	var tab = Ext.getCmp(functionModel.data.id);
	if (tab) {
		tabPanel.setActiveTab(tab);
	} else {
		tabPanel.add({
			id : functionModel.data.id,
			title : functionModel.data.text,
			//border: false,
			iconCls : "treeNodeLeafIcon",
			html : '<iframe id="iframe_'+functionModel.data.id+'" src="'
					+ '/'
					+ getAppName()
					+ functionModel.raw.entity.uri
					/*+ '" width='
					+window.screen.availWidth*0.77344
					+' height='
					+window.screen.availHeight*0.717*/
					+' " width=100% height=100% marginwidth="0" framespacing="0" marginheight="0" frameborder="0" ></iframe>',
			closable : true
		}).show();
		tab = Ext.getCmp(functionModel.data.id);
		tabPanel.setActiveTab(tab);
	}
}
