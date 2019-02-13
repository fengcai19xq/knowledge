//

//var selModel = new Ext.selection.CheckboxModel();
//var topGridTabPanel = Ext.create('Ext.tab.Panel',{
//	width : document.documentElement.clientWidth,
//	height : 280,
//    activeTab: 0,
//    region : 'north',
//	items : [detailedListForm,detailedListForm]
//});

//选择模型
var selModel = Ext.create('Ext.selection.CheckboxModel', {
	mode : 'MULTI',//允许多选
//	checkOnly : true,//设置只有点击复选框中才选中
	allowDeselect : true,//允许用户取消选择
	checkOnly : true,
	listeners : {
		selectionchange : function(rowModel, record, rowIndex, eOpts) {
			var hd_checker = demoGrid.getEl()
					.select('div.x-column-header-checkbox');
			var hd = hd_checker.first();
			// 判断是否是全选/取消全选
			if (hd != null) {
				if (hd.hasCls(Ext.baseCSSPrefix + 'grid-hd-checker-on')) {
					if (demoGrid.getSelectionModel().hasSelection()) {
						var records = demoGrid.getSelectionModel()
								.getSelection();
						var total = new Array();
						var money = 0;
						for (var i = 0; i < records.length; i++) {
							money += records[i].get('money');
							total[i] = records[i];
						}
						if (money == null) {
							money = 0;
						}
						money = parseFloat(money).toFixed(2);
						
						var alertMsg = "<font color=red><b>选中记录"+total.length+"条,总金额"+Ext.util.Format.round(money,2)+"元</b></font>";
						Ext.getCmp('selectRecTance').setValue(alertMsg);
						
					}
				} else if (!hd.hasCls(Ext.baseCSSPrefix + 'grid-hd-checker-on')) {
					Ext.getCmp("selectRecTance").setValue('');
					countTanceOfSelectRec();
				}
			}
			
			

		}
	}
//	renderer : function(value,metaData,rec,rowIndex,colIndex, store,view){return "";//将复选框隐藏掉
//    metaData.tdCls = Ext.baseCSSPrefix + 'grid-cell-special';
//   return '<div class="'+Ext.baseCSSPrefix + 'grid-row-checker">&#160;</div>';},
});

/**
 * 可编辑单元格
 */
var addNonBcellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
			clicksToEdit : 1
		});

var editorNonBcellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
	clicksToEdit : 1
});

 var data=[[1,'EasyJWeb','EasyJF','www.easyjf.com'], 

                 [2,'jfox','huihoo','www.huihoo.org'], 
                 [3,'jdon','jdon','www.jdon.com'], 

                 [4,'springside','springside','www.springside.org.cn']]; 
 
var store=new Ext.data.SimpleStore({data:data,fields:["id","name","organization","homepage"]}); 

 var grid2=Ext.create('Ext.grid.Panel',{ 

                       title:"中国Java开源产品及团队", 
                       region:'center',
                       frame:true,
                       height:150, 
                       width:300, 
                       defaultType:'textfield',
                       columns:[{text:"项目名称",dataIndex:"name"}, 

                       {text:"开发团队",dataIndex:"organization"}, 

                       {text:"网址",dataIndex:"homepage"}], 
                       
                       store:store

                 }); 
 
//分页一定要放到tablePanel之前
 var demoGridBar = Ext.create('Ext.PagingToolbar', {
 	store :demoStore,
 	displayInfo : true,
 	displayMsg : '显示记录 {0} - {1} / {2}',
 	emptyMsg : "没有记录",
 	items : [{
 		id : 'privateRemitPageSizeXq',
 		xtype : 'numberfield', //手动设置分页参数
 		labelWidth : 40,
 		fieldLabel : '每页',
// 		emptyText : '分页参数',
 		width: 65,
 		hideTrigger : true,
 		minValue : 0,
 		maxValue : 100,
// 		html : '条',
 		listeners : {
 			blur : function (){
 					setGridPageSize('privateRemitPageSizeXq',privateRemitStoreXq);
 			}
 		}
 	},{
     	xtype : 'displayfield',
     	width : 10,
     	html : '条'
     },{
 		id : 'privateRemitPageNumXq',
 		xtype : 'numberfield', //跳转到第XX页
 		fieldLabel : '跳转到',
 		labelWidth : 50,
 		width: 100,
 		hideTrigger : true,
 		minValue : 1,
 		allowDecimals : false
 	},{
 		xtype : 'label',
 		text:'页',
 		width : 20
 	},{
 		xtype : 'button',
 		width :25,
 		text : 'go',
 		handler:function(){
 			goGridPageNum('privateRemitPageNumXq',privateRemitStoreXq);
 		}
 	},{
		id : 'selectRecTance',
		xtype : 'displayfield',
		width : 300,
		fieldLabel : ' ',
		labelWidth : 5
	}]
 });

//明细列表
var demoGrid=Ext.create('Ext.grid.Panel',{
		id : 'demoGrid',
    	title:'明细',
    	region:'center',
    	selModel : selModel,
    	flex : 1,
    	collapsible: true,
		store:demoStore,
		defaultType:'textfield',
		frame:true,
		layout : 'fit',
		columnLines : true,
		forceFit : true, // 列表宽度自适应
		autoScroll:true,
		viewConfig: {
	         stripeRows: true,
	        	getRowClass:function(record,index,p,ds) {
	        	var cls = 'white-row';
	        	switch (record.data.handoverstatus) {
	        	case '已标记' : cls = 'x-grid-record-green'; break;
	        	case '未交账' : cls = 'x-grid-record-yellow'; break;
	        	case '已交账' : cls = 'x-grid-record-orange'; break;
	        	}
	        	return cls;
	        	 }
	     },
		columns:[{
			header : '修改区',
			xtype : 'actioncolumn',
			width : 48,
			items : [{
						icon : '../images/example/edit.png',
						tooltip : '修改',
						handler : editorSingleFun
					}]
		},{
			text:'编号',
			width:100,
			align:'center',
			dataIndex:'billnum',
			sortable:true
		},{
			text:'姓名',
			columnWidth:100,
			 columns: [{
		            text     : '姓',
		            width    : 120,
		            sortable : true,
		            align:'center',
		            dataIndex: 'surnname'
		        }, {
		        	text     : '氏',
		            width    : 120,
		            sortable : true,
		            align:'center',
		            dataIndex: 'lastname'
		        }]
		},{
			text:'收款银行',
			width:150,
			align:'center',
			dataIndex:'remitbank',
			sortable:true,
			renderer:function(value){
				if(value=='01'){
					return "建设银行";
				}else if(value=='02'){
					return "招商银行";
				}else if(value=='03'){
					return "农业银行";
				}else if(value=='04'){
					return "邮政储蓄";
				}
			}
		},{
			text:'收款日期',
			width:150,
			align:'center',
			sortable:true,
			xtype :'datecolumn',
			format:'Y-m-d',//G:i:s
			dataIndex:'remitdate',
			sortable:true
		},{
			text:'交付状态2',
			width:150,
			align:'center', 
			dataIndex:'handoverstatus',
//			renderer:renderCol,
			sortable:true
			
		},{
			text:'收款金额',
			align:'center',
			columnWidth:100,
			dataIndex:'money',
			sortable:true

		}],
		bbar:demoGridBar,
		dockedItems:[{
			xtype:'toolbar',
			buttonAlign:'center',
			items:[{
				icon:'../images/example/add.png',
				text:'新增',
				scope:this,
				handler:function(){
//					singleForm.getForm().findField('btnType').setValue("add");
					Ext.create("Fins.Demo.SingleWin").show();
				}
			},'-',{
				icon:'../images/example/add.png',
				text:'批量新增',
				scope:this,
				handler:function(){
					addDemoWin.show();
				}
			},'-',{
				icon:'../images/example/edit.png',
				text:'批量修改',
				scope:this,
				handler:editorGridFun
			},'-',{
				icon:'../images/example/delete.png',
				text:'批量删除',
				scope:this,
				handler:deleteGridData
			},'-',{
				icon:'../images/example/excel.gif',
				text:'导入',
				scope:this,
				handler:function(){
					importWin.show();
				}
			},'-',{
				icon:'../images/example/excel.gif',
				text:'导出',
				scope:this,
				handler:exportAllDataFun
			},'-',{
				icon:'../images/example/edit.png',
				text:'标记',
				scope:this,
				handler:markFun
			},'-',{
				icon:'../images/example/edit.png',
				text:'打印',
				scope:this,
				handler:printWin
			}]
		}],
        listeners : {
        	select : itemClickOfRec,
        	 scrollershow: function(scroller) {
                 if (scroller && scroller.scrollEl) {
                             scroller.clearManagedListeners(); 
                             scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
                 }
            },
         itemdblclick : function(view, record, item, index, e, eOpts){
            	
            }
        }
			
	});




/**
 * 添加新窗体
 */
var addDemoGrid = Ext.create('Ext.grid.Panel', {
			store : addNullStore,
			height : 340,
			frame : true,
			columns:[{
				text:'编号',
				width:100,
				dataIndex:'billnum',
				sortable:true,
				editor:{
					allowBlank : false
				}
			},{
				text:'姓名',
				columnWidth:100,
				dataIndex:'name',
				sortable:true,
				editor:{
					allowBlank : false
				}
			},{
				text:'收款银行',
				width:150,
				dataIndex:'remitbank',
				renderer:typeRender,
				sortable:true,
				editor : {
					xtype : 'combo',
					store : bankStore,
					displayField : 'name',
					valueField : 'code',
					queryMode : 'local',
					triggerAction : 'all',
					forceSelection : true
				}
			},{
				text:'收款日期',
				width:100,
				sortable:true,
				dataIndex:'remitdate',
				//format : 'Y-m-d ',
				renderer : formatDate,
				editor : {
					xtype : 'datefield',
					format : 'Y-m-d ',//G:i:s
					editable :false,
                    allowBlank :false						
				}
			},{
				text:'交付状态',
				width:100,
				dataIndex:'handoverstatus',
				sortable:true,
				editor:{
					xtype : 'combobox',
					store : Ext.create('Ext.data.ArrayStore', {
						fields : ['abbr'],
						data : [[''],['未交账'], ['已标记'], ['已交账']]
					}),
					valueField : 'abbr',
					displayField : 'abbr',
					typeAhead : true,
					queryMode : 'local'
						}
			},{
				text:'收款金额',
				columnWidth:100,
				dataIndex:'money',
				sortable:true,
				decimalPrecision : 2,
				mouseWheelEnabled : false,
				maxValue: 99999999999999.99,
				editor:{
					allowBlank : false
				}

			}],
			selModel : {
				selType : 'rowmodel'
			},
			dockedItems : [{
						xtype : 'toolbar',
						buttonAlign : 'bottom',
						items : [{
									icon : '../images/example/add.png',
									text : '新增',
									scope : this,
									handler : addGridRowFun
								}, '-', {
									xtype : 'button',
									icon : '../images/example/delete.png',
									text : '删除', // 删除
									scope : this,
									handler : deleteAddGridRowFun
								}, '-', {
									xtype : 'button',
									icon : '../images/example/save.gif',
									text : '保存',
									margin : '0 10 0 0',
									handler : saveGridFun
								}]
					}],
			plugins : [addNonBcellEditing]
		});



/**
 * 添加新窗体
 */
var editorDemoGrid = Ext.create('Ext.grid.Panel', {
			store : editorNullStore,
			height : 270,
			frame : true,
			buttonAlign : 'center',
			columns:[{
				text:'编号',
				width:100,
				dataIndex:'billnum',
				sortable:true,
				editor:{
					allowBlank : false
				}
			},{
				text:'姓名',
				columnWidth:100,
				dataIndex:'name',
				sortable:true,
				editor:{
					allowBlank : false
				}
			},{
				text:'收款银行',
				width:150,
				dataIndex:'remitbank',
				sortable:true,
				editor : {
					xtype : 'combobox',
					forceSelection : true,
					store : bankStore,
					displayField : 'name',
					valueField : 'value',
					queryMode : 'local',
					disabledDaysText : '请选择类型！'
				}
			},{
				text:'收款日期',
				width:100,
				sortable:true,
				dataIndex:'remitdate',
				//format : 'Y-m-d ',
				renderer : formatDate,
				editor : {
					xtype : 'datefield',
					format : 'Y-m-d ',//G:i:s
					editable :false,
                    allowBlank :false						
				}
			},{
				text:'交付状态',
				width:100,
				dataIndex:'handoverstatus',
				sortable:true,
				editor:{
					xtype : 'combobox',
					store : Ext.create('Ext.data.ArrayStore', {
						fields : ['abbr'],
						data : [[''],['未交账'], ['已标记'], ['已交账']]
					}),
					valueField : 'abbr',
					displayField : 'abbr',
					typeAhead : true,
					queryMode : 'local'
						}
			},{
				text:'收款金额',
				columnWidth:100,
				dataIndex:'money',
				sortable:true,
				editor:{
					allowBlank : true
				}

			}],
			selModel : {
				selType : 'rowmodel'
			},
			buttons : [{
				text : '保存',
				handler : editorNonBGridFun
			}, {
				text : '取消',
				handler : editorCancelFun
			}],
	plugins : [editorNonBcellEditing]
		});

