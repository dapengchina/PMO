$(function() {
	queryPercentage();
	loadManageEvaSecondQueryList();

});
/** 百分比计算方法 * */
function queryPercentage() {
	var rm = $("#curRm").val();
	$.ajax({
		url : path + '/service/performanceManageEva/rm/percentage?rm=' + rm,
		dataType : "json",
		cache : false,
		type : "get",
		success : function(data) {
			$("#percentA").html(data["percentA"] == undefined ? 0 : data["percentA"]);
			$("#percentBplus").html(data["percentB+"] == undefined ? 0 : data["percentB+"]);
			$("#percentB").html(data["percentB"] == undefined ? 0 : data["percentB"]);
			$("#percentC").html(data["percentC"] == undefined ? 0 : data["percentC"]);
			$("#percentD").html(data["percentD"] == undefined ? 0 : data["percentD"]);
			$("#empA").html(data["A"] == undefined ? 0 : data["A"]);
			$("#empBplus").html(data["B+"] == undefined ? 0 : data["B+"]);
			$("#empB").html(data["B"] == undefined ? 0 : data["B"]);
			$("#empC").html(data["C"] == undefined ? 0 : data["C"]);
			$("#empD").html(data["D"] == undefined ? 0 : data["D"]);
			$("#empSum").html(data["sum"] == undefined ? 0 : data["sum"]);
			$("#percentSum").html("100%");
		}
	})
}

function loadManageEvaSecondQueryList() {
	// var queryUrl =
	// path+'/service/performanceManageEva/queryManageEvaSecondQueryList';
	var queryUrl = path + '/service/performanceHRBPEva/processing/result/list';
	var columns = [ {
		title : '绩效<br/>目标',
		formatter : function(value, row, index) {
			return "<a href='#' class='btn btn-info btn-small'><i class='glyphicon glyphicon-edit'></i></a>";
		}
	}, {
		title : '序号',
		formatter : function(value, row, index) {
			return "<span>" + (index + 1) + "</span>";
		}
	}, {
		field : 'ehr',
		title : 'E-HR编号'
	}, {
		field : 'lobNo',
		title : 'LOB工号'
	}, {
		field : 'name',
		title : '姓名'
	}, {
		field : 'hireDate',
		title : '入职时间'
	}, {
		field : 'role',
		title : '职务'
	}, {
		field : 'serviceLine',
		title : '业务线'
	}, {
		field : 'bu',
		title : 'BU'
	}, {
		field : 'du',
		title : '交付部'
	}, {
		field : 'location',
		title : '归属地'
	}, {
		field : 'keymember',
		title : '是否<br/>骨干'
	}, {
		field : 'participate',
		title : '是否<br/>参评'
	}, {
		field : 'manager',
		title : '直接主管'
	}, {
		field : 'customerFeedback',
		title : '客户反馈',
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
	// sortable : true
	}, {
		field : 'pmEvalution',
		title : '直接<br/>主管<br/>初评<br/>结果',
	// sortable : true
	}, {
		field : 'duEvalution',
		title : '部门<br/>集体<br/>评议<br/>结果',
	// sortable : true
	}, {
		field : 'duEvaManager',
		title : '集体<br/>评议<br/>主管'
	}, {
		field : 'achievement',
		title : 'A/C/D<br/>人员<br/>绩效<br/>事实'
	}, {
		field : 'jump',
		title : '是否<br/>绩效<br/>跳变'
	}, {
		field : 'comments',
		title : '备注',
	// sortable : true
	}, {
		field : 'previous1Quarter',
		title : '上<br/>季度<br/>绩效'
	}, {
		field : 'previous2Quarter',
		title : '上上<br/>季度<br/>绩效'
	}, {
		field : 'previous3Quarter',
		title : '上上上<br/>季<br/>度绩效'
	} ];

	var table = $('#queryManageEvaSecondQueryList').bootstrapTable({
		url : queryUrl, // 请求后台的URL（*）
		method : 'GET', // 请求方式（*）
		// toolbar: '#toolbar', //工具按钮用哪个容器
		striped : true, // 是否显示行间隔色
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, // 是否显示分页（*）
		sortable : true, // 是否启用排序
		sortOrder : "asc", // 排序方式
		sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
		pageNumber : 1, // 初始化加载第一页，默认第一页,并记录
		pageSize : 10, // 每页的记录行数（*）
		pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
		search : false, // 是否显示表格搜索
		strictSearch : false,
		showColumns : false, // 是否显示所有的列（选择显示的列）
		// showRefresh: true, //是否显示刷新按钮
		minimumCountColumns : 2, // 最少允许的列数
		clickToSelect : true, // 是否启用点击选中行
		// height : 500, // 行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		uniqueId : "id", // 每一行的唯一标识，一般为主键列
		showToggle : false, // 是否显示详细视图和列表视图的切换按钮
		cardView : false, // 是否显示详细视图
		detailView : false, // 是否显示父子表
		singleSelect : false, // 禁止多选_____
		// 得到查询的参数
		queryParams : function(params) {
			// 获取查询条件
			var eHr = $("#eHr").val();
			var staffName = $("#staffName").val();
			var bu = $("#bu").val();
			var du = $("#du").val();
			var rm = $("#curRm").val();
			return {
				pageSize : params.limit,
				pageNumber : params.offset / params.limit + 1,
				eHr : eHr,
				staffName : staffName,
				bu : bu,
				du : du,
				rm : rm
			};
		},
		columns : columns,
		onLoadSuccess : function(sta) {
			console.log("in onLoadSuccess");
			console.log(JSON.stringify(sta));

			/*
			 * $("#percentA").html(sta["percentA"]);
			 * $("#percentBplus").html(sta["percentBplus"]);
			 * $("#percentB").html(sta["percentB"]);
			 * $("#percentC").html(sta["percentC"]);
			 * $("#percentD").html(sta["percentD"]);
			 * $("#percentSum").html(sta["percentSum"]);
			 * $("#empA").html(sta["empA"]);
			 * $("#empBplus").html(sta["empBplus"]);
			 * $("#empB").html(sta["empB"]); $("#empC").html(sta["empC"]);
			 * $("#empD").html(sta["empD"]); $("#empSum").html(sta["empSum"]);
			 */
		},
		onLoadError : function(status, res) { // 加载失败时执行
			console.log(res);
			console.log("error.status:" + status);
		},
		onDblClickRow : function(row, $element) {
		}

	});
}

function search() {
	// 获取查询条件
	var eHr = $("#eHr").val();
	var staffName = $("#staffName").val();
	var bu = $("#bu").val();
	var du = $("#du").val();
	var rm = $("#curRm").val();
	var queryParams = {
		query : {
			eHr : eHr,
			staffName : staffName,
			bu : bu,
			du : du,
			rm : rm
		}
	};
	// 刷新表格
	$('#queryManageEvaSecondQueryList').bootstrapTable('refresh', queryParams);
}
/** 清除条件 * */
function duClear() {
	$("#eHr").val("");
	$("#staffName").val("");
}
/** 返回 * */
function goBack() {
	window.location.href = path + "/service/performance/performanceManageEvaSecondDU.html";
}

/** 审批-指定rm下所有员工 * */
function approval(type) {
	var state = type == 1 ? 4 : 3;// TODO xuexuan
	var rm = $("#curRm").val();
	$.ajax({
		url : path + '/service/performanceManageEva/assessment/approval/du/detail/submit',
		data : {
			"rm" : rm,
			"state" : state
		},
		cache : false,
		type : "POST",
		success : function(data) {
			alert("审批成功");
			// 返回审批页面
			window.location.href = path + "/service/performance/performanceManageEvaSecondDU.html";
		},
		error : function(error) {
			alert("审批失败");
		}
	});
}
