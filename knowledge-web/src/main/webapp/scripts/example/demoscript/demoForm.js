var demoForm = Ext.create('Ext.form.Panel', {
	title : '查询',
	collapsible : true,
	frame : true,
	id : 'demoForm',
	layout : 'fit',
	region : 'north',
	height : 200,
	items : [demoFieldSet],
	buttonAlign : 'center',
	margin : '0 0 2 0',
	buttons : [{
		xtype :'button',
		text : '查询',
		handler:queryFun // 根据用户输入的条件执行查询操作
		},{
	    xtype :'button',
		text : '清空',
		handler : function() {
			demoForm.form.reset();
		}
	}]
});



 var singleForm = Ext.create("Ext.form.Panel",{
//	collapsible : true,
	frame : true,
	layout : 'fit',
	height : document.documentElement.clientHeight * 0.42,
	items : [Ext.create('Fins.Demo.SingleField')],
	buttonAlign : 'center',
	margin : '0 0 2 0',
	buttons : [{
		xtype :'button',
		text : '保存',
		handler:saveSingleFun 
		},{
	    xtype :'button',
		text : '清空',
		handler : function() {
			singleForm.form.reset();
		}
	}]
});
 
 
 /**
  * 导入form
  */
 var importForm = Ext.create('Ext.form.Panel', {
 			fileUpload : true,
 			border : 0,
 			frame : false,
 			cls:'border_color',
 			buttonAlign : 'center',
 			items : [importField],
 			buttons : [{
 						text : '导入',
 						frame : false,
 						cls : 'first_btn',
 						handler : importFun
 					}, {
 						text : '重置',
 						handler : function() {
 							importForm.getForm().reset();
 						}
 					}, {
 						text : '返回',
 						handler : function() {
 							importWin.hide();
 						}
 					}]
 		});
 