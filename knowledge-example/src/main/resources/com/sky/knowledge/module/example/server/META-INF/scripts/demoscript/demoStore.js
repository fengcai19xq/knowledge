
var demoStore = Ext.create('Ext.data.Store',{
	model : 'demoModel',
	pageSize : 6,
	proxy:{
		type:'ajax',
		actionMethods : 'POST',
		url:'queryFun.action',
		reader:{
			type:'json',
			root:'demoEntityList', //结果集
			totalProperty  : 'totalCount'
		}
	},
	listeners : {
		beforeload : function(store, operation, eOpts) {
			Ext.apply(operation, {
				params : {
					//showAll : false,
					//showOther : true
					//'queryCondition' : 'queryPrivateRemitConditionXq'

				}
			});
		}
	}
});



/**
 * 可编辑窗体数据源
 */
var addNullStore = Ext.create('Ext.data.ArrayStore', {
			fields : [{
						name : 'billnum'
					}, {
						name : 'name'
					}, {
						name : 'remitbank'
							
					}, {
						name : 'remitdate',
						defaultValue : new Date(),
						convert : dateConvert,
						type : 'date',
						format : 'Y-m-d '//G:i:s
					}, {
						name : 'handoverstatus'
					}, {
						name : 'money'
					} ],
			data : []
		});



/**
 * 可编辑窗体数据源
 */
var editorNullStore = Ext.create('Ext.data.ArrayStore', {
			fields : [{
						name : 'billnum'
					}, {
						name : 'name'
					}, {
						name : 'remitbank'
							
					}, {
						name : 'remitdate',
						defaultValue : new Date(),
						convert : dateConvert,
						type : 'date',
						format : 'Y-m-d '//G:i:s
					}, {
						name : 'handoverstatus'
					}, {
						name : 'money'
					} ],
			data : []
		});



var data=[{"code":01,"name":'建设银行'}, 

          {"code":02,"name":'招商银行'}, 
          {"code":03,"name":'农业银行'}, 

          {"code":04,"name":'邮政储蓄'}]; 

var bankStore=new Ext.data.Store({
	data:data,fields:["code","name"],
	autoLoad:true
}); 

