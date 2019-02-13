
// 将弹出框的按钮文字替换为中文
var MsgCN = new top.Ext.window.MessageBox({
	buttonText : {
		ok : '确定',
		yes : '是',
		no : '否',
		cancel : '取消'
	}
});


// 两个日期类型进行比较，结束日期不能小于开始日期
function compareEndDateStartDate(startDate, endDate, msg) {
	if (startDate == null && endDate == null) {
		return true;
	} else {
		// 如果开始日期不为空，结束日期为空
		if (startDate != null && endDate == null) {
			MsgCN.alert('提示', msg + '：结束日期不能为空!');
			return false;
		} else if (endDate != null && startDate == null) {
			MsgCN.alert('提示', msg + '：开始日期不能为空!');
			return false;
		} else {
			if (endDate >= startDate) {
				return true;
			} else {
				MsgCN.alert('提示', msg + '：结束日期不能小于开始日期!');
				return false;
			}
		}

	}
}


//设置grid的分页参数
function setGridPageSize(id, store) {
	// 参数合法性判断
	if (id != null && store != null) {
		var comp = Ext.getCmp(id);
		if (comp != null) {
			// 获取分页参数值
			var value = comp.getValue();

			// 若设置的值不空
			if (value != null) {
				// 分页参数只能在1-100之间
				if (value < 1 || value > 100) {
					MsgCN.alert('提示', '分页参数必须是介于1-100的正整数!');
				} else {
					// 获取当前的分页参数
					var pageSize = store.pageSize;
					// 若要设置的分页参数与当前分页参数不相同，
					// 则将分页参数设置为新的分页参数
					if (pageSize != value) {
						store.pageSize = value;
						store.loadPage(1);
						Ext.getCmp('selectRecTance').setValue("");
					}
				}
			}
		}
	}
}

//手动设置跳转到第几页
function goGridPageNum(id, store) {

	// 参数合法性判断
	if (id != null && store != null) {
		var comp = Ext.getCmp(id);

		if (comp != null) {
			// 获取要跳转的页数
			var value = comp.getValue();

			// 若设置的值不空
			if (value != null) {
				if (value > 0) {
					// 获取当前的分页参数
					var pageSize = store.pageSize;
					// 获取当前记录数
					var totalCount = store.getTotalCount();
					if (totalCount == null) {
						MsgCN.alert('提示', '您还未进行查询操作!');
					} else {
						if (pageSize != null && pageSize != 0
								&& totalCount != null) {
							// 共有页数
							var page = totalCount / pageSize;
							// 余数
							var leftNum = totalCount % pageSize;

							if (leftNum > 0) {
								page = page + 1;
							}
							if (value > page) {
								MsgCN.alert('提示', '不存在该页');
							} else {
								store.loadPage(value);
								Ext.getCmp('selectRecTance').setValue("");
							}
						}
					}
				} else if (value == 0) {
					MsgCN.alert('提示', '不存在该页');
				}
			}
		}
	}
}