/** 查看详情的列表 */
var stateSubmitFlag = true;
var RmEvaFlag = false;
var columns = [ {
	title : '序<br/>号',
	formatter : function(value, row, index) {
		return "<span>" + (index + 1) + "</span>";
	},
	width : "2%",
}, {
	field : 'ehr',
	title : 'E-HR<br/>编号'
}, {
	field : 'lobNo',
	title : 'LOB<br/>工号'
}, {
	field : 'name',
	title : '姓名'
}, {
	field : 'hireDate',
	title : '入<br/>职<br/>时<br/>间'
}, {
	field : 'role',
	title : '职务'
}, {
	field : 'serviceLine',
	title : '业务线'
}, {
	field : 'bu',
	title : '事<br/>业<br/>部'
}, {
	field : 'du',
	title : '交<br/>付<br/>部'
}, {
	field : 'location',
	title : '归<br/>属<br/>地'
}, {
	field : 'keymember',
	title : '是<br/>否<br/>骨<br/>干',
	width : "2.5%",
}, {
	field : 'participate',
	title : '是<br/>否<br/>参<br/>评',
	width : "2.5%"
}, {
	field : 'manager',
	title : '直<br/>接<br/>主<br/>管'
}, {
	field : 'customerFeedback',
	title : '客户<br/>反馈',
	formatter : function(value, row, index) {
		var substr = "";
		if (value.length > 5) {
			substr = value.substring(0, 5);
			return "<a href='#' class='link'>" + substr + "<div class='tips'>" + value + "</div></a>";
		} else {
			return value;
		}
	}
}, {
	field : 'initialEvalution',
	title : '初评<br/>(依据<br/>客户<br/>反馈)',
	width : "4%",
	formatter : function(value, row, index) {
		if (row.initialEvalution == "" || row.initialEvalution == undefined) {
			stateSubmitFlag = false;
		}
		return value;
	}
}, {
	field : 'pmEvalution',
	title : '直接<br/>主管<br/>初评<br/>结果',
	width : "4%"
}, {
	field : 'duEvalution',
	title : '部门<br/>集体<br/>评议<br/>结果',
	width : "4%"
}, {
	field : 'duEvaManager',
	title : '集体<br/>评议<br/>主管'
}, {
	field : 'achievement',
	title : 'A/C/D<br/>人员<br/>绩效<br/>事实'
}, {
	field : 'jump',
	title : '是否<br/>绩效<br/>跳变',
	width : "4%"
}, {
	field : 'comments',
	title : '备注',
}, {
	field : 'previous1Quarter',
	title : '上<br/>季度<br/>绩效',
	width : "4%"
}, {
	field : 'previous2Quarter',
	title : '上上<br/>季度<br/>绩效',
	width : "4%"
}, {
	field : 'previous3Quarter',
	title : '上上<br/>上季<br/>度绩<br/>效',
	width : "4%"
}, {
	title : '详<br/>情',
	width : "3%",
	formatter : function(value, row, index) {
		if (!RmEvaFlag) {
			return "<a href='javascript:void(0);' onClick='detail(\"" + row.resultId + "\");' class='btn btn-info btn-small'><i class='glyphicon glyphicon-edit'></i></a>";
		} else if (row.state == 0) {// 待RM审批状态显示编辑按钮
			return "<a href='javascript:void(0);' onClick='detail(\"" + row.resultId + "\")' " + "' class='btn btn-info btn-small'><i class='glyphicon glyphicon-edit'></i></a>";
		}
	}
} ];