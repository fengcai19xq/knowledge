var formatDate = function(value) {
	return value ? Ext.Date.dateFormat(value, 'Y-m-d ') : '';//G:i:s
};

var demoFieldSet = Ext.create('Ext.form.FormPanel', {
			margin : '5,5,5,5',//定义整个form与父元素的间距
			defaultType : 'textfield',
			layout : {
				type : 'column'
			},
			defaults : {
				msgTarget : 'qtip',//表示会进行表单验证
				margin : '5 10 5 5',//定义form内每个field之间的间距
				labelWidth : 75,//定义textfield的label的默认宽度，
				columnWidth : 0.335,//定义textfield的field的宽度
				maxLength : 100,
				enforceMaxLength : true
			},
			items : [ {
						name : 'demoEntity.billnum',
						id :'billNum',
						fieldLabel : '编号',
						maxLength : 75,
						enforceMaxLength : true,
						selectOnFocus : true
					}, {
						name : 'demoEntity.name',
						id : 'xm',
						fieldLabel :'姓名',
						maxLength : 75,
						//columnWidth :0.2,
						enforceMaxLength : true,
						selectOnFocus : true
					},{
						xtype : 'combo',
						name : 'demoEntity.remitbank',
						fieldLabel : '汇款银行',
						store : bankStore,
						displayField : 'name',
						valueField : 'code',
						//queryMode : 'local',
						triggerAction : 'all',//用all表示把下拉框列表框的列表值全部显示出来，如果用query，如果你做了一次选择，第二次再去用，按第一次输入的值，它只显示匹配出来的。
						forceSelection : true//多条相同的 显示 值 ，combo在失去 焦点的时候 会 自动给你转向第一条
					},{
						xtype : 'fieldcontainer',
						height : 22,
						layout : {
							type : 'hbox'
						},
						defaults : {
							labelWidth : 100
						},
						fieldDefaults : {
							labelAlign : 'left'
						},
						defaultType : 'datefield',
						items : [{
									minWidth : 75,
									xtype : 'tbtext',
									html : "汇款时间："
								}, {
									flex : 1,
									fieldLabel : '从',
									id : 'dcStartDate',
									name : 'demoEntity.dcStartDate',
									labelWidth : 10,
									labelSeparator : '',
									editable : false,
									margins : '0 0 0 2',
									format : 'Y-m-d'
								}, {
									flex : 1,
									labelWidth : 10,
									id : 'dcEndDate',
									fieldLabel : '到',
									name : 'demoEntity.dcEndDate',
									labelSeparator : '',
									editable : false,
									margins : '0 0 0 3',
									format : 'Y-m-d'
								}]
					},{
						name : 'demoEntity.handoverstatus',
						xtype : 'combobox',
						fieldLabel : '交账状态',
						
						store : Ext.create('Ext.data.ArrayStore', {
									fields : ['abbr'],
									data : [[''],['未交账'], ['已标记'], ['已交账']]
								}),
						valueField : 'abbr',
						displayField : 'abbr'
//						typeAhead : true,
//						queryMode : 'local'

					},{
						xtype : 'hidden',
						id : 'queryRemitRemitDeptCode',
						columnWidth : 0
					},{
						name : 'emport_totalCount',
						id : 'emport_totalCount',
						xtype : 'numberfield',
						hidden : true,
						value : 0
					}]
		});
		
		
		
 Ext.define("Fins.Demo.SingleField",{
    extend:'Ext.form.FormPanel',
	margin : '5,2,2,2',//定义整个form与父元素的间距
	defaultType : 'textfield',
	layout : {
		type : 'column'
	},
	defaults : {
		msgTarget : 'qtip',//表示会进行表单验证
		margin : '10 15 10 15',//定义form内每个field之间的间距
		labelWidth : 75,//定义textfield的label的默认宽度，
		columnWidth : 0.43,//定义textfield的field的宽度
		maxLength : 100,
		enforceMaxLength : true
	},
	items:[
	{
	  id:'billnum',	
	  name:'demoEntity.billnum',
	  fieldLabel:'编号'
	},{
		id:'name',
		name:'demoEntity.name',
	  fieldLabel:'姓名'
	},{
		xtype : 'combo',
		id:'remitbank',
		name : 'demoEntity.remitbank',
		fieldLabel : '汇款银行',
		store : bankStore,
		displayField : 'name',
		valueField : 'code',
		//queryMode : 'local',
		triggerAction : 'all',//用all表示把下拉框列表框的列表值全部显示出来，如果用query，如果你做了一次选择，第二次再去用，按第一次输入的值，它只显示匹配出来的。
		forceSelection : true
	},{
				fieldLabel:'收款日期',
				renderer : formatDate,
				xtype : 'datefield',
				id:'remitdate',
				name:'demoEntity.remitdate',
				format : 'Y-m-d ',//G:i:s
				editable :false,
                allowBlank :false						
			},{
				fieldLabel:'交付状态',
				id:'handoverstatus',
				name:'demoEntity.handoverstatus',
					xtype : 'combobox',
					store : Ext.create('Ext.data.ArrayStore', {
						fields : ['abbr'],
						data : [[''],['未交账'], ['已标记'], ['已交账']]
					}),
					valueField : 'abbr',
					displayField : 'abbr',
					typeAhead : true,
					queryMode : 'local'
			},{
				xtype:'numberfield',
				id:'money',
				name:'demoEntity.money',
	            fieldLabel:'收款金额'
	           },{
	               id:'btnType',
	               name:'btnType',
	               label:'隐藏，用于判断btn操作类型',
	               value:'add',
	               hidden:true
	           }
	]
});		
 
 var importField = Ext.create('Ext.form.FieldSet', {
		title : '附件下载',
		margin : '5 5 5 5',
		collapsible : false,
		region : 'center',
		layout : 'column',
		defaults : {
			margin : '5 5 5 5',
			labelWidth : 80,
			columnWidth : 0.4
		},
		fieldDefaults : {
			labelAlign : 'right'
		},
		items : [{
			xtype : 'label',
			html : "<span>请按模板格式导入：&nbsp;&nbsp;&nbsp;" +
					"<a href='../scripts/example/resource/importExcel.xlsx'>" +
					"<font color='red'>模板下载</font></a></span>"
		}, {
			xtype : 'filefield',
			fieldLabel : '文件',
			labelWidth : 80,
			columnWidth : 1,
			allowBlank : false,
			name : 'excelFile',
			id : 'fkgoto_importFile',
			buttonText : '请选择文件......'
		}]
	});
 
