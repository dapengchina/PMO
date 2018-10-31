$(function() {
	loadManageTargetApprovalList();

});

function loadManageTargetApprovalList() {
	var queryUrl = path + '/service/performanceManageEva/assessment/goal/list';
	var columns = [ {
		checkbox : true,
		visible : true
	// 是否显示复选框
	}, {
		title : 'SL',
		formatter : function(value, row, index) {
			return "<span>" + (index + 1) + "</span>";
		}
	}, {
		field : 'E_HR',
		title : 'E-HR',
	}, {
		field : 'STAFF_NAME',
		title : 'Employee Name',
	}, {
		field : 'ROLE',
		title : 'MSA Role',
	}, {
		field : 'SKILL',
		title : 'Skill/Technology',
	}, {
		field : 'submit',
		title : '是否提交',
		formatter : function(value, row, index) {
			return row.submit == 1 ? "是" : "否";
		}
	}, {
		field : 'pioneer',
		title : '业务先锋'
	}, {
		field : 'state',
		title : '审批状态',
		formatter : function(value, row, index) {
			return row.state == 1 ? "审批通过" : row.state == 0 ? "待审批" : "审批不通过";
		}
	}, {
		title : 'Detail',
		formatter : function(value, row, index) {
			if (row.submit == 1 && row.state == 0) {
				return "<a href='javascript:void(0);' onClick='detail(\"" + row.EMPLOYEE_ID + "\");' class='btn btn-info btn-small'><i class='glyphicon glyphicon-edit'></i></a>";
			}
		}
	} ];

	var table = $('#manageTargetApprovalList').bootstrapTable({
		url : queryUrl, // 请求后台的URL（*）
		method : 'GET', // 请求方式（*）
		// toolbar: '#toolbar', //工具按钮用哪个容器
		striped : true, // 是否显示行间隔色
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, // 是否显示分页（*）
		sortable : false, // 是否启用排序
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
			return {
				pageSize : params.limit,
				pageNumber : params.offset / params.limit + 1,
			};
		},
		columns : columns,
		onLoadSuccess : function(sta) {
			console.log("in onLoadSuccess");
			console.log(JSON.stringify(sta));
		},
		onLoadError : function(status, res) { // 加载失败时执行
			console.log(res);
			console.log("error.status:" + status);
		},
		onDblClickRow : function(row, $element) {

		}
	});
}

function detail(employeeId) {
	// 页面跳转post提交
	$("#detailForm").remove();
	var url = path + '/service/performanceManageEva/goal/detail.html';
	var $eleForm = $("<form method='post' class='hidden' id='detailForm'></form>");
	$eleForm.attr("action", url);
	$(document.body).append($eleForm);

	var idInput = $("<input type='text' name='employeeId' class='hidden'></input>");
	var titleInput = $("<input type='text' name='title' class='hidden'></input>");
	var typeInput = $("<input type='text' name='type' class='hidden'></input>");
	idInput.attr("value", employeeId);
	titleInput.attr("value", "Management->绩效目标->审批");
	typeInput.attr("value", "1");
	$("#detailForm").append(idInput);
	$("#detailForm").append(titleInput);
	$("#detailForm").append(typeInput);
	$eleForm.submit();
}

function download() {
	window.location.href = path + "/service/performanceManageEva/goal/export";
}

function search() {
	var queryParams = {
		query : {
			pageSize : 10,
			pageNumber : 1,
			submit : "1",
			state : "1",
			bockbone : "1",
		}
	}
	// 刷新表格
	$('#manageTargetApprovalList').bootstrapTable('refresh', queryParams);
}
