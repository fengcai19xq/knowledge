// 将后台日期数据转换为可在前台显示的日期
function dateConvert(value, record) {
	if (value != null) {
		var date = new Date(value);
		return date;
	} else {
		return null;
	}
}

Ext.define("demoModel", {
	extend : 'Ext.data.Model',
	fields : [{
		name : 'billnum',
		type : 'string'
	}, {
		name : 'name',
		type : 'string'
	}, {
		name : 'remitbank',
		type : 'string'
	},{
		name : 'remitdate',
		type : 'date',
		convert : dateConvert
	},  {
		name : 'handoverstatus',
		type : 'string'
	}, {
		name : 'money',
		type : 'string'
	}, {
		name : 'surnname',
		type : 'string'
	}, {
		name : 'lastname',
		type : 'string'
	}]
});
