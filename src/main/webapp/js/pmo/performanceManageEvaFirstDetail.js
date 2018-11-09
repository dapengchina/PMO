$(function() {
	queryPercentage();
	loadManageEvaFirstDetailList();

});
/** 获取当年当季绩效审核比例 * */
function queryPercentage() {
	$.ajax({
		url : path + '/service/performanceManageEva/curRole/percentage',
		dataType : "json",
		cache : false,
		type : "GET",
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
	});
}
RmEvaFlag = true;// 标识该页面为RM评级页面，反馈给performancTable.js
tableId = "manageEvaFirstDetailList";
function loadManageEvaFirstDetailList() {
	// var queryUrl = path +
	// '/service/performanceManageEva/queryManageEvaFirstDetailList';
	var queryUrl = path + '/service/performanceManageEva/processing/result/list/rm';

	var table = $('#manageEvaFirstDetailList').bootstrapTable({
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
		// search : true, // 是否显示表格搜索
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
			var showAchievement = $("#showAchievement").val();
			return {
				pageSize : params.limit,
				pageNumber : params.offset / params.limit + 1,
				showAchievement : showAchievement
			};
		},
		columns : columns,
		onLoadSuccess : function(data) {
			var ary = data.rows;
			// 保存要审批数据ID
			for ( var item in ary) {
				stateSubmitIds.push(ary[item].resultId);
			}
			console.log("===" + stateSubmitIds);
		},
		onLoadError : function(status, res) { // 加载失败时执行
			console.log(res);
			console.log("error.status:" + status);
		},
		onDblClickRow : function(row, $element) {

		}

	});
}
var stateSubmitIds = new Array();
function stateSubmit() {
	// 所有员工评价后才可提交
	if (!stateSubmitFlag) {
		alert("还有未评级员工，请先评级");
		return;
	}
	if (stateSubmitIds.length == 0) {
		alert("暂无数据审批！");
		return;
	}
	// 审批
	$.ajax({
		url : path + "/service/performanceManageEva/assessment/approval/rm/submit",
		type : "POST",
		data : {
			"ids" : stateSubmitIds.join(",")
		},
		success : function(data) {
			alert("审批成功");
			history.go(0);
		},
		error : function() {
			alert("审批失败");
		}
	});
}

function detail(resultId) {
	// 页面跳转post提交
	$("#detailForm").remove();
	var url = path + '/service/performance/goalDetail.html';
	var $eleForm = $("<form method='post' class='hidden' id='detailForm'></form>");
	$eleForm.attr("action", url);
	$(document.body).append($eleForm);

	var idInput = $("<input type='text' name='resultId' class='hidden'></input>");
	var titleInput = $("<input type='text' name='title' class='hidden'></input>");
	var typeInput = $("<input type='text' name='type' class='hidden'></input>");
	idInput.attr("value", resultId);
	titleInput.attr("value", "Management->绩效考评->初评");
	typeInput.attr("value", "2");
	$("#detailForm").append(idInput);
	$("#detailForm").append(titleInput);
	$("#detailForm").append(typeInput);
	$eleForm.submit();
}
