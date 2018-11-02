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
			if (data["sum"] == 0) {
				$("#percentSum").html("0%");
			}
		}
	})
}
tableId = "queryManageEvaSecondQueryList";
function loadManageEvaSecondQueryList() {
	// var queryUrl =
	// path+'/service/performanceManageEva/queryManageEvaSecondQueryList';
	var queryUrl = path + '/service/performanceHRBPEva/processing/result/list';

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

function detail(resultId) {
	// 页面跳转post提交
	$("#detailForm").remove();
	var url = path + '/service/performanceManageEva/goal/detail.html';
	var $eleForm = $("<form method='post' class='hidden' id='detailForm'></form>");
	$eleForm.attr("action", url);
	$(document.body).append($eleForm);

	var idInput = $("<input type='text' name='resultId' class='hidden'></input>");
	var titleInput = $("<input type='text' name='title' class='hidden'></input>");
	var typeInput = $("<input type='text' name='type' class='hidden'></input>");
	idInput.attr("value", resultId);
	titleInput.attr("value", "Management->绩效考评->审批");
	typeInput.attr("value", "3");
	$("#detailForm").append(idInput);
	$("#detailForm").append(titleInput);
	$("#detailForm").append(typeInput);
	$eleForm.submit();
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
