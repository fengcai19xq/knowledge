// 新增窗口
var addDemoWin = Ext.create("Ext.window.Window", {
	closable : true,
	modal : true,
	closeAction : 'hide',
	title : '新增',
	layout : 'absolute',
	width : document.documentElement.clientWidth * 0.695,
	height : document.documentElement.clientHeight * 0.6,
	autoScroll : true,
	items : [addDemoGrid],
	listeners:{
		"hide":function(){
			addDemoGrid.getStore().removeAll();
		}
	}
});

  Ext.define("Fins.Demo.SingleWin",{
	extend :'Ext.window.Window',
	closable : true,
	modal : true,
	closeAction : 'hide',
	title : '新增',
	layout : 'absolute',
	width : document.documentElement.clientWidth * 0.695,
	height : document.documentElement.clientHeight * 0.6,
	autoScroll : true,
	items : [singleForm],
	listeners:{
		"hide":function(){
			singleForm.form.reset();
		}
	}
});

//修改窗口
var editDemoWin = new Ext.Window({
	closable : true,
	modal : true,
	closeAction : 'hide',
	title : '修改窗口',
	layout : 'absolute',
	width : document.documentElement.clientWidth * 0.694,
	height : document.documentElement.clientHeight * 0.5,
	autoScroll : true,
	items : [editorDemoGrid],
	listeners : {
		"hide" : function() {
			editorDemoGrid.getStore().removeAll();
		}
	}
});

//打印界面
var printViewWin = new Ext.Window({
	modal : true,
	autoScroll : true,
	width : 780,
	resizable : false,
	height : 550,
	closeAction : 'hide',
	layout : 'absolute',
	buttonAlign : 'center',
	buttons : [{
		text : '打印',
		handler : printView
	},{
		text : '返回',
		handler : function() {
			printViewWin.close();
		}
	}],
	items : [{
		xtype : "component",
		name : 'printViewIFrame',
		id : 'printViewIFrame',
		autoEl : {
			tag : "iframe",
			frameBorder : 0,
			width : "100%",
			height : "100%",
			src : "printView.action"
		}
	}]
});


/**
 * 上传附件弹出页面
 * 
 */
var importWin = Ext.create('Ext.window.Window', {
			title : '导入界面',
			modal : true,
			width : document.documentElement.clientWidth * 0.6,
			height : document.documentElement.clientWidth * 0.2,
			layout : 'fit',
			items : [importForm],
			closeAction : 'hide',
//			resizable : false,
			listeners : {
				"hide" : function() {
					importForm.getForm().reset();
				}
			}
		});
