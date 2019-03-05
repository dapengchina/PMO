/** 查看详情的列表 */
var RmEvaFlag = false;// 由引入页面传入，RM初评页面传入true以便决定detail显示情况
var stateSubmitFlag = true;// 返回给RM初评页面以便决定是否可以提交

// 分页参数
var tableId = "";// 由引入页面传入以便计算序号值
var pageNumber = 1;
var pageSize = 10;
var columns = [ {
	title : 'No',
	formatter: function (value, row, index) {
        return index+1;
    }
}, {
	field : 'ehr',
	title : 'E-HR'
}, {
	field : 'lobNo',
	title : 'LOB'
}, {
	field : 'name',
	title : 'Name'
}, {
	field : 'hireDate',
	title : 'Date-onboard'
}, {
	field : 'role',
	title : 'MSA Role'
}, {
	field : 'serviceLine',
	title : 'Line'
}, {
	field : 'bu',
	title : 'BU',
	showSelectTitle : true
}, {
	field : 'du',
	title : 'DU'
}, {
	field : 'location',
	title : 'Location'
}, {
	field : 'keymember',
	title : 'Backbone',
	visible: showBankbone
}, {
	field : 'participate',
	title : 'Assessed'
}, {
	field : 'manager',
	title : 'Direct Supervisor'
}, {
	field : 'customerFeedback',
	title : 'Client Feedback',
	formatter : function(value, row, index) {
		var substr = "";
		if (value == null) {
			return "";
		}
		if (value.length > 5) {
			substr = value.substring(0, 5);
			return "<a href='#' class='link'>" + substr + "<div class='tips'>" + value + "</div></a>";
		} else {
			return value;
		}
	}
}, {
	field : 'initialEvalution',
	title : 'Pre-Assessment(Refer Client Feedback)',
	formatter : function(value, row, index) {
		if (row.initialEvalution == "" || row.initialEvalution == undefined) {
			stateSubmitFlag = false;// RM初评页面，所有员工评级完成才可提交
		}
		return value;
	}
}, {
	field : 'pmEvalution',
	title : 'Pre-Assessment'
}, {
	field : 'duEvalution',
	title : 'GroupAssessment'
}, {
	field : 'duEvaManager',
	title : 'GroupAssessment Result'
}, {
	field : 'achievement',
	title : 'PerformanceFacts(A/C/D)'
}, {
	field : 'jump',
	title : 'PerformanceSkip'
}, {
	field : 'comments',
	title : 'Remark',
}, {
	field : 'previous1Quarter',
	title : 'Last Q'
}, {
	field : 'previous2Quarter',
	title : '2Qs ago'
}, {
	field : 'previous3Quarter',
	title : '3Qs ago'
}/*, {
	title : 'Detail',
	formatter : function(value, row, index) {
		if (!RmEvaFlag) {
			return "<a href='javascript:void(0);' onClick='detail(\"" + row.resultId + "\");' class='btn btn-info btn-small'><i class='glyphicon glyphicon-edit'></i></a>";
		} else if (row.state == 0 || row.state == null) {// 待RM审批状态显示编辑按钮
			return "<a href='javascript:void(0);' onClick='detail(\"" + row.resultId + "\")' " + "' class='btn btn-info btn-small'><i class='glyphicon glyphicon-edit'></i></a>";
		}
	}
}*/ ];