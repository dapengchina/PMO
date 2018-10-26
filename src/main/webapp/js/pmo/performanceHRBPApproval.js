$().ready(function() {
	queryPercentage();
	loadHRBPApprovalList();
});
/** 获取交付部绩效审核比例 * */
function queryPercentage() {
	$.ajax({
		url : path + '/service/performanceHRBPEva/percentage',
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
			$("#empSum").html(data["sum"]);
		}
	});
}
/** 获取列表 * */
function loadHRBPApprovalList() {
	var queryUrl = path + '/service/performanceHRBPEva/approval/list';
	var columns = [ {
		checkbox : true,
		visible : true
	// 是否显示复选框
	}, {
		title : 'NO.',
		formatter : function(value, row, index) {
			return "<span>" + (index + 1) + "</span>";
		}
	}, {
		field : 'BU',
		title : 'Bu'
	}, {
		field : 'Year',
		title : 'Year'
	}, {
		field : 'Quarter',
		title : 'Quarter'
	}, {
		field : 'State',
		title : 'Status'
	}, {
		title : 'Detail',
		formatter : function(value, row, index) {
			return "<a href='performanceHRBPApprovalDetail.html?bu=" + row.BU + "' class='btn btn-info btn-small'><i class='glyphicon glyphicon-edit'></i></a>";
		}
	} ];

	var table = $('#HRBPApprovalList').bootstrapTable({
		url : queryUrl, // 请求后台的URL（*）
		method : 'GET', // 请求方式（*）
		// toolbar: '#toolbar', //工具按钮用哪个容器
		striped : true, // 是否显示行间隔色
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : false, // 是否显示分页（*）
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
		// height: 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		uniqueId : "id", // 每一行的唯一标识，一般为主键列
		showToggle : false, // 是否显示详细视图和列表视图的切换按钮
		cardView : false, // 是否显示详细视图
		detailView : false, // 是否显示父子表
		singleSelect : false, // 禁止多选_____
		// 得到查询的参数
		queryParams : function(params) {
			return {
				pageSize : params.limit,
				pageNumber : params.offset / params.limit + 1
			};
		},
		columns : columns,
		onLoadSuccess : function(data) {
			console.log("in onLoadSuccess");
			console.log(JSON.stringify(data));
			// 判断是否都已经审批
			approvalAllFlag = true;
			for ( var item in data) {
				if (data[item].State.indexOf("待") > -1) {// TODO xuexuan
															// 如何判断审批状态
					approvalAllFlag = false;
					break;
				}
			}
		},
		onLoadError : function(status, res) { // 加载失败时执行
			console.log(res);
			console.log("error.status:" + status);
		},
		onDblClickRow : function(row, $element) {

		}

	});
}
/** 导出审批列表和集体评议 * */
function approvalExport() {
	var url = path + '/service/performanceHRBPEva/approval/export';
	window.location.href = url;
}
/** 提交审批 * */
var approvalAllFlag = false;
function submit() {
	// 所有事业部均处理后才可提交
	var comments = $("#approval_feedback").val();
	if (approvalAllFlag) {
		$.ajax({
			url : path + '/service/performanceHRBPEva/approval/submit',
			data : {
				"comments" : comments
			},
			cache : false,
			type : "POST",
			success : function(data) {
				alert("审批成功");
			},
			error : function(error) {
				alert("审批失败");
			}
		});
	} else {
		alert("请先审批");
	}
}
