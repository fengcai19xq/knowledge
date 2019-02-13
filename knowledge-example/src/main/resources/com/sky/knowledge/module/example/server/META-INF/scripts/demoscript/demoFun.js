//渲染grid中收款银行下拉列表
function typeRender(value){
	if (value == '1') {
		return '建设银行';
	}
	if (value == '2') {
		return '招商银行';
	}
	if (value == '3') {
		return '农业银行';
	}
	if (value == '4') {
		return '邮政储蓄';
	}
}
//渲染已交账 颜色
function renderCol(value, metaData, record, rowIndex, columnIndex, store, view ){
	if(record.get("handoverstatus")=='已交账'){
		  metaData.style = "color:#888888";
	} 
	
    return value;
}

function queryFun(){
	
	var doStartDate = demoForm.getForm().findField("demoEntity.dcStartDate").getValue();//页面日期字段
	var doEndDate = demoForm.getForm().findField("demoEntity.dcEndDate").getValue();//页面日期字段
   if(compareEndDateStartDate(doStartDate,doEndDate,'')){
	 //获取所有的查询条件值
		var data=demoForm.getForm().getValues();
		//将所有的查询条件传给store
		demoStore.getProxy().extraParams=data;
		
		var remitbank = demoForm.getForm().findField("demoEntity.remitbank").getRawValue();//页面日期字段
		var handoverstatus = demoForm.getForm().findField("demoEntity.handoverstatus").getRawValue();//页面日期字段

		//进行查询操作
		demoStore.loadPage(1,{
			//加载对公账户信息的回调函数
			callback: function(records, operation, success) {
				if (success) {
					var response = Ext.decode(operation.response.responseText);
					var totAmount=response.totalMoney;
					if(totAmount==null )
					{
						totAmount=0;
					}
					var totCount=response.totalCount;
					if(totCount==null)
					{
						totCount=0;
					}
					Ext.getCmp('demoGrid').setTitle(
							"查询结果  : 总金额：<font color='red'>" + totAmount + "</font>，总笔数：<font color='red'>"
									+ demoStore.getTotalCount()+"</font>");
				} else {
					Ext.Msg.alert('提示', '服务器出现异常');
				}
			}
		});
   }
}
/**
 * 修改单条信息
 * @param grid
 * @param rowIndex
 * @param colIndex
 */
function editorSingleFun(grid, rowIndex, colIndex){
	 var record = grid.getStore().getAt(rowIndex);
	 var billnum = record.get('billnum');
	 var demoEntity = queryDemoEntity(billnum);
	 if(demoEntity==null){
		  MsgCN.alert('提示','根据编号查询信息失败!');
		  return false;
     } 
	 singleForm.form.setValues(demoEntity);
	 singleForm.getForm().findField('demoEntity.remitdate').setValue(new Date(demoEntity.remitdate));
	 singleForm.getForm().findField('btnType').setValue("modify");
	 Ext.create("Fins.Demo.SingleWin").show();

}
/**
 * 根据编号获取实体信息
 * @return
 */
function queryDemoEntity(billnum){
  
   var demoEntity = null;//返回值
   Ext.Ajax.request({
  	 url:'demoaction_querySingleEntity.action',
  	 method:'post',
  	 async:false,//设置为同步方式
  	 params:{
  	 	'demoEntity.billnum':billnum
  	 },
  	 success:function(r,o){
  	    var obj = Ext.decode(r.responseText);
  	    demoEntity = obj.demoEntity ;
  	 },
  	 failure:function(r,o){
  	 }
  });
  return demoEntity ;
}

//批量修改
function editorGridFun(){
	var selection = demoGrid.getSelectionModel().getSelection();
 	if (selection.length == 0) {
 		MsgCN.alert("提示", "请选择你所要修改的记录！");
 		return;
 	}
 	var r ;
 	var i = 0;
 	Ext.each(selection, function(record) {
 				    r = Ext.ModelManager.create({
 					billnum : record.get('billnum'),
 					name : record.get('name'),
 					remitbank : record.get('remitbank'),
 					remitdate : record.get('remitdate'),
 					handoverstatus : record.get('handoverstatus'),
 					money : record.get('money')
 						}, 'demoModel');
 					editorNullStore.insert(i, r);
 					
 					i++;

 			});
	 editDemoWin.show();
}

/**
 * 取消编辑
 */
var editorCancelFun = function() {
	editorDemoGrid.getStore().removeAll();
	editDemoWin.close();
};


/**
 * 修改grid里的数据
 */
var editorNonBGridFun = function() {
	editorNonBcellEditing.completeEdit();
	var flag = false;
//	var rowCount = editorPrivateRemitGridXq.store.getCount();
//
//	for (var i = 0; i < rowCount; i++) {
//
//		var record = editorNonBEditorGrid.getStore().getAt(i);
//
//		// 获取数据中的项
//		var busitype = record.data.busitype;
//
//		var cashamount = record.data.cashamount;
//
//		var deptmon = record.data.deptmon;
//
//		var customer = record.data.customer;
//
//		if (busitype == null || busitype == '') {
//			MsgCN.alert('提示', '您类型项未填，请填写后保存');
//			return;
//		} else if (cashamount == null || cashamount == '') {
//			MsgCN.alert('提示', '您金额项未填，请填写后保存');
//			return;
//		} else if (deptmon == null || deptmon == '') {
//			MsgCN.alert('提示', '您汇款所属部门项未填，请填写后保存');
//			return;
//		} else {
//			if (busitype == custAmount || busitype == myAmount) {
//				if (customer == null || customer == '') {
//					MsgCN.alert('提示', '您客户项未填，请填写后保存');
//					return;
//				} else {
//					flag = true;
//				}
//			} else {
//				flag = true;
//			}
//		}
//
//	}
//	if (flag) {
		editorGridData();
//	}
};


/**
 * 修改Grid数据到数据库
 */
var editorGridData = function() {
	 var rowCount = editorDemoGrid.store.getCount();
		var easyuiTableInfo = new Array();// easyui表内容
		for (var i = 0; i < rowCount; i++) {
			var record = editorDemoGrid.getStore().getAt(i);

			// 获取数据中的项
			var billnum = record.data.billnum;

			var name = record.data.name;

			var remitbank = record.data.remitbank;

			var remitdate = record.data.remitdate;

			var handoverstatus = record.data.handoverstatus;
			
			var money = record.data.money;


			var dt = Ext.Date.format(remitdate, 'Y-m-d');
			
//			billnum = escape(encodeURIComponent(billnum));
			name = escape(encodeURIComponent(name));
			remitbank = escape(encodeURIComponent(remitbank));
			handoverstatus = escape(encodeURIComponent(handoverstatus));

			easyuiTableInfo
					.push("{billnum:'" + billnum + "',name:'" + name
							+ "',remitbank:'" + remitbank
							+ "',handoverstatus:'" + handoverstatus 
							+ "',remitdate:" + dt + ",money:"+money+"}");
		}
		var specialRules = "[" + easyuiTableInfo.join(",") + "]";

		Ext.Ajax.request({
					url : 'updateGridData.action',
					method : 'post',
					params : {
						'nonBLS' : specialRules
					},
					success : function(r, o) {
//						var obj = Ext.decode(r.responseText);
						//alert(this.window);
                       MsgCN.alert('提示','数据修改成功!');
                       editDemoWin.close();
						
						// 每页条数
	 					var pgs = demoStore.pageSize;
	 					// 总条数
	 					var ptc = demoStore.getTotalCount();
	 					// 当前所在的页数
	 					var cp = demoStore.currentPage;
	 					
	 					demoStore.loadPage(cp*1,{
	 						//加载对公账户信息的回调函数
	 						callback: function(records, operation, success) {
	 							if (success) {
	 								var response = Ext.decode(operation.response.responseText);
	 								var totAmount=response.totalMoney;
	 								if(totAmount==null )
	 								{
	 									totAmount=0;
	 								}
	 								var totCount=response.totalCount;
	 								if(totCount==null)
	 								{
	 									totCount=0;
	 								}
	 								Ext.getCmp('demoGrid').setTitle(
	 										"查询结果  : 总金额：<font color='red'>" + totAmount + "</font>，总笔数：<font color='red'>"
	 												+ demoStore.getTotalCount()+"</font>");
	 							} else {
	 								Ext.Msg.alert('提示', '服务器出现异常');
	 							}
	 						}
	 					});// *1表示将字符型转换成数字类型
						
					}
				});
		  editorDemoGrid.getStore().removeAll();
};


function deleteGridData(){
	
	var selection = demoGrid.getSelectionModel().getSelection();
 	if (selection.length == 0) {
 		MsgCN.alert("提示", "请选择你所要删除的记录！");
 		return;
 	}
 	var recordArray = [];
 	var flag = false;
 	Ext.each(selection, function(record) {
 				recordArray.push(record.get("billnum"));
// 				if (record.get('handaccount') > 0) {
// 					flag = true;
// 				}
 			});

 	if (recordArray.length == 0) {
 		MsgCN.alert('提示', '删除记录里不存在要删除的数据！');
 		return;
 	}

 	MsgCN.confirm('提示！', "确定要删除所选择的数据吗？", function(e) {
 		if (e == 'yes') {
 			
 			Ext.Ajax.request({
 				url : 'deleteData.action',
 				params : {
 					'demoEntity.arrayParams' : recordArray
 				},
 				success : function(response) {
 					var json = Ext.decode(response.responseText);
 					// 每页条数
 					var pgs = demoStore.pageSize;
 					// 总条数
 					var ptc = demoStore.getTotalCount();
 					// 当前所在的页数
 					var cp = demoStore.currentPage;
 					if (ptc != 0) {
 						var cpt = ptc / pgs;
 						if (cpt <= cp) {
 							if (selection.length == demoStore.getCount()) {
 								cp -= 1;
 							}
 						}
 					}
 					demoStore.loadPage(cp,{
 						//加载对公账户信息的回调函数
 						callback: function(records, operation, success) {
 							if (success) {
 								var response = Ext.decode(operation.response.responseText);
 								var totAmount=response.totalMoney;
 								if(totAmount==null )
 								{
 									totAmount=0;
 								}
 								var totCount=response.totalCount;
 								if(totCount==null)
 								{
 									totCount=0;
 								}
 								Ext.getCmp('demoGrid').setTitle(
 										"查询结果  : 总金额：<font color='red'>" + totAmount + "</font>，总笔数：<font color='red'>"
 												+ demoStore.getTotalCount()+"</font>");
 							} else {
 								Ext.Msg.alert('提示', '服务器出现异常');
 							}
 						}
 					});
 					
 				},
 				failure : function(response) {
 					MsgCN.alert("提示", "服务器端操作异常");
 				}
 			});
 		}
 	});
}

function exportAllDataFun(){
	
	if (demoStore.getCount() == null
			|| demoStore.getCount() == 0) {
		Ext.Msg.alert('提示', '导出数据不能为空');
		return;
	}
	if (demoStore.getCount() >=6000) {
		// Ext.Msg.alert('提示','导出的记录不能超过六千条');
		//exportWin.show();
		//exportForm.form.reset();
	} else {
		Ext.MessageBox
				.confirm(
						'提示',
						'你确定要导出excel表格吗？',
						function(id) {
							if (id == 'yes') {
								document.location.href = "exportAllData.action?method=exportAllData";
							}
						});
	}
}

function markFun(){
	
	var select = demoGrid.getSelectionModel().getSelection();
	if(select.length==0||select.length>1){
		MsgCN.alert("提示","请选择一条数据并只能选择一个记录!");
		return ;
	}else{
		var handOverStatus = select[0].get("handoverstatus") ;
		var billnum = select[0].get('billnum');
		if(handOverStatus!='未交账'){
			MsgCN.alert("提示","请选择一条未交账的数据!");
			return ;
		}else{
			handOverStatus = '已标记';
		}
	Ext.Ajax.request({
		url : 'markHandOverStatus.action',
		method : 'post',
		timeout : 120000,
		params :{
			'm_billNum' : billnum,
			'm_handOverStatus' : handOverStatus
		},
		success : function(response){
			var r = Ext.JSON.decode(response.responseText);
			if(r.mark_flag)
			{   Ext.Msg.alert('提示', '标记成功!');
			demoStore.findRecord('billnum',billnum).set('handoverstatus',handOverStatus);	
			demoStore.getView().refresh();
			}else
				{
				//MsgCN.alert('提示','<span style="color:red;">标记失败</span>该收款记录交账状态已被更改为：'+m_handOverStatus+'！');
				//PrivateRemitStore.findRecord('billNum',billNum).set('handOverStatus',r.m_handOverStatus);
				}
		},
		failure :function(response){
			MsgCN.alert('提示，标记失败请稍后再试');
		}
	})	;
		
	}
}

function printWin(){
	
	//window.open("printAc.action","打印");  只支持IE浏览器

	printViewWin.show();
	var grid= demoGrid.getSelectionModel().getSelection();

	var billnum = grid[0].get("billnum");
	
	Ext.getDom('printViewIFrame').src = "printView.action?demoEntity.billnum="+billnum;
}

var printView = function(){
	
	if (Ext.isIE) {
		frames[0].focus();
		frames[0].document.execCommand('print', false, null);
	} else {
		frames[0].focus();
		frames[0].print();
	}
};
/**
 * 往grid里添加一条数据
 */
function addGridRowFun () {
	var r = Ext.ModelManager.create({
				billnum : null,
				name : null,
				remitbank : null,
				remitdate : null,
				handoverstatus : null,
				money : ''
			}, 'demoModel');
	addNullStore.insert(0, r);
	addNonBcellEditing.startEditByPosition({
				row : 0,
				column : 0
			});
}
/**
 * 删除grid里的数据
 */
function deleteAddGridRowFun () {
	var selection = addDemoGrid.getSelectionModel().getSelection();
	//alert(selection);
	if (selection.length == 0) {
		MsgCN.alert("提示", "请选择你所要删除的记录！");
		return;
	}
	addNullStore.remove(selection);
}


/**
 * 保存grid里的数据
 */
function saveGridFun () {
	addNonBcellEditing.completeEdit();
	var flag = false;
	var rowCount = addDemoGrid.store.getCount();
//	for (var i = 0; i < rowCount; i++) {
//
//		var record = addPrivateRemitGridXq.getStore().getAt(i);

		// 获取数据中的项
		//var busitype = record.data.busitype;

//		var cashamount = record.data.cashamount;
//
//		var deptmon = record.data.deptmon;
//
//		var customer = record.data.customer;

//		if (busitype == null || busitype == '') {
//			MsgCN.alert('提示', '您类型项未填，请填写后保存');
//			return;
//		} else if (cashamount == null || cashamount == '') {
//			MsgCN.alert('提示', '您金额项未填，请填写后保存');
//			return;
//		} else if (deptmon == null || deptmon == '') {
//			MsgCN.alert('提示', '您汇款所属部门项未填，请填写后保存');
//			return;
//		} else {
//			if (busitype == custAmount || busitype == myAmount) {
//				if (customer == null || customer == '') {
//					MsgCN.alert('提示', '您客户项未填，请填写后保存');
//					return;
//				} else {
//					flag = true;
//				}
//			} else {
//				flag = true;
//			}
//		}
//
//	}
//	if (flag) {
		addGridData();
//	}
}


/**
 * 添加Grid数据到数据库
 */
var addGridData = function() {
	var rowCount = addDemoGrid.store.getCount();
	if(rowCount==0){
		MsgCN.alert('提示','请新增数据!');
		return;
	}
	var easyuiTableInfo = new Array();// easyui表内容
	for (var i = 0; i < rowCount; i++) {
		var record = addDemoGrid.getStore().getAt(i);

		// 获取数据中的项
		var billnum = record.data.billnum;

		var name = record.data.name;

		var remitbank = record.data.remitbank;

		var remitdate = record.data.remitdate;

		var handoverstatus = record.data.handoverstatus;
		
		var money = record.data.money;


		var dt = Ext.Date.format(remitdate, 'Y-m-d');
		
//		billnum = escape(encodeURIComponent(billnum));
		name = escape(encodeURIComponent(name));
		remitbank = escape(encodeURIComponent(remitbank));
		handoverstatus = escape(encodeURIComponent(handoverstatus));

		easyuiTableInfo
				.push("{billnum:'" + billnum + "',name:'" + name
						+ "',remitbank:'" + remitbank
						+ "',handoverstatus:'" + handoverstatus 
						+ "',remitdate:" + dt + ",money:"+money+"}");
	}
	var specialRules = "[" + easyuiTableInfo.join(",") + "]";

	Ext.Ajax.request({
				url : 'save.action',
				method : 'post',
				params : {
					'nonBLS' : specialRules
				},
				success : function(r, o) {
//					var obj = Ext.decode(r.responseText);
					//alert(this.window);
					//this.window.close();
//					MsgCN.alert('提示', '您汇款所属部门项未填，请填写后保存');
                    MsgCN.alert("提示",'保存成功!');
                    addDemoGrid.getStore().removeAll();
                    addDemoWin.close();
                    queryFun();
				}
			});
};



function itemClickOfRec(n, record, item, index, e){
	countTanceOfSelectRec();
}
//统计用户选中记录的总金额及总条数
function countTanceOfSelectRec(){
	// 获取选择的记录数组
	var r = demoGrid.getSelectionModel().getSelection();
	//用户选中记录的总金额
	var sum = 0;
	//遍历用户选中的记录，判断是否满足交账条件。
	//若不满足交账条件,则提示用户，并返回
	for(var i=0;i<r.length;i++){
		var tance = r[i].get('money');
		if(tance != null && tance != ''){
			sum += parseFloat(r[i].get('money'));
		}
		
	}
	
	//用户选中记录的总条数
	var count = r.length;
	
	var alertMsg = "<font color=red><b>选中记录"+count+"条,总金额"+Ext.util.Format.round(sum,2)+"元</b></font>";
	Ext.getCmp('selectRecTance').setValue(alertMsg);
}


var saveSingleFun = function(){
	singleForm.getForm().submit({
		url : 'demoaction_saveSingleData.action?operateType='+singleForm.getForm().findField("btnType").getValue(),
		waitTitle : '提示',
		method : 'post',
		waitMsg : '正在处理数据,请稍候...',
		success : function(form, o) {
			var obj = o.result;
			 if(obj.msg!=''){
             	 MsgCN.alert('提示',obj.msg); 
              }else{
            	  MsgCN.alert('提示', '保存成功！');
            	  singleForm.form.reset();
            	  singleForm.form.owner.ownerCt.close();
          		//将所有的查询条件传给store
          		  demoStore.getProxy().extraParams=demoForm.getForm().getValues();
            	  demoStore.load();
              }
		},
		failure : function(form, action) {
			MsgCN.alert('提示', '修改失败！');
		}
	});
};


/**
 * 导入方法
 * @returns
 */
var importFun = function() {
	var form = importForm.getForm();

	if (!form.isValid()) {
		return;
	}
	// 获取表单对象

	// 定义正则表达式校验文件后缀名
	var reg = new RegExp(".+\\.xls(x)?", "i");

	// 获取文件路径
	var fileName = form.findField('fkgoto_importFile').getValue();

	// 正则校验导入的文件名后缀
	if (!reg.test(fileName)) {
		MsgCN.alert('提示', '你选择的文件格式不正确，请选择Excel类型的文件。');
		return;
	}

	form.submit({
				waitTitle : '导入操作...',
				waitMsg : '数据正在努力导入中，请耐心等待一会儿！',
				method : 'POST',
				url : 'demoaction_importExcel.action',
				success : function(form, action) {
					demoStore.load();
					importWin.hide();
				},
				failure : function(form, action) {
					var jsonobject = Ext.decode(action.response.responseText);
					MsgCN.alert('提示', jsonobject.msg);
				}
			});
};



