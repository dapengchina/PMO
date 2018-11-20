$(function() {
	loadManageResultHistoryQueryList();

});

function loadManageResultHistoryQueryList() {
	var queryUrl = path + '/service/performanceManageResultHistory/queryManageResultHistoryQueryList';
	var table = $('#manageResultHistoryQueryList').bootstrapTable({
		url : queryUrl, // 请求后台的URL（*）
		method : 'GET', // 请求方式（*）
		fixedColumns: true,
        fixedNumber: 5,
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
			console.log(params);
			// 获取查询条件
			var eHr = $("#eHr").val();
			var staffName = $("#staffName").val();
			var bu = $("#bu").val();
			var du = $("#du").val();
			var startYear = $("#startYear").val();
			var startQuarter = $("#startQuarter").val();
			var endYear = $("#endYear").val();
			var endQuarter = $("#endQuarter").val();
			return {
				pageSize : params.limit,
				pageNumber : params.offset / params.limit + 1,
				eHr : eHr,
				staffName : staffName,
				bu : bu,
				du : du,
				startYear : startYear,
				startQuarter : startQuarter,
				endYear : endYear,
				endQuarter : endQuarter
			};
		},
		columns : 
		[ 
			{
			  title : 'No',
			  sortable : true,
			  width:'70px',
			  formatter : function(value, row, index) {
				 return index+1;
			  }
		    },
		    {
		      field: 'operation',
		      title : 'Detail',
			  width:'100px',
			  formatter : function(value, row, index) {
				return "<a href='javascript:void(0);' onClick='detail(\"" + row.resultId + "\")' " + "' class='btn btn-info btn-small'><i class='glyphicon glyphicon-edit'></i></a>";
			  }
		    }, 
		    {
			  field : 'ehr',
			  title : 'E-HR',
			  sortable : true,
			  width:'100px',
		    },
		    {
			  field : 'name',
			  title : 'Employee Name',
			  sortable : true,
			  width:'130px',
		    }, 
		    {
			  field : 'bu',
			  title : 'BU',
			  sortable : true,
			  width:'380px',
		    }, 
		    {
			  field : 'du',
			  title : 'DU',
			  sortable : true,
			  width:'280px',
		    }, 
		    {
			  field : 'beginDate',
			  title : 'Begin Date',
			  width:'230px',
			  formatter : function(value, row, index) {
				 if (row.quarter == "1") {
					return "01 / 01 / " + row.year;
				 } else if (row.quarter == "2") {
					return "01 / 04 / " + row.year;
				 } else if (row.quarter == "3") {
					return "01 / 07 / " + row.year;
				 } else if (row.quarter == "4") {
					return "01 / 10 / " + row.year;
				 }
			  }
		    }, 
		    {
			  field : 'endDate',
			  title : 'End Date',
			  width:'230px',
			  formatter : function(value, row, index) {
				 if (row.quarter == "1") {
					return "31 / 03 / " + row.year;
				 } else if (row.quarter == "2") {
					return "30 / 06 / " + row.year;
				 } else if (row.quarter == "3") {
					return "30 / 09 / " + row.year;
				 } else if (row.quarter == "4") {
					return "31 / 12 / " + row.year;
				 }
			   }
		    }, 
		    {
			  field : 'rm',
			  title : 'RM',
			  width:'150px',
		    }, 
		    {
			  field : 'result',
			  title : 'Assessment Result',
			  width:'130px',
		    }, 
		    {
			  field : 'resultComments',
			  title : 'Remark',
			  width:'130px',	
		} ],
		onLoadSuccess : function(sta) {
			console.log("in onLoadSuccess");
			console.log(JSON.stringify(sta));
		},
		onLoadError : function(status, res) { // 加载失败时执行
//			console.log(res);
//			console.log("error.status:" + status);
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
	var startYear = $("#startYear").val();
	var startQuarter = $("#startQuarter").val();
	var endYear = $("#endYear").val();
	var endQuarter = $("#endQuarter").val();
	var queryParams = {
		query : {
			eHr : eHr,
			staffName : staffName,
			bu : bu,
			du : du,
			startYear : startYear,
			startQuarter : startQuarter,
			endYear : endYear,
			endQuarter : endQuarter
		}
	}
	// 刷新表格
	$('#manageResultHistoryQueryList').bootstrapTable('refreshOptions', {
		pageSize : 10,
		pageNumber : 1
	});
	// $('#manageResultHistoryQueryList').bootstrapTable('refresh',
	// queryParams);
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
	titleInput.attr("value", "Management->绩效结果->历史绩效");
	typeInput.attr("value", "6");
	$("#detailForm").append(idInput);
	$("#detailForm").append(titleInput);
	$("#detailForm").append(typeInput);
	$eleForm.submit();
}

/** 历史绩效导出 * */
function historyResultExport() {
	var tableData = $('#manageResultHistoryQueryList').bootstrapTable("getData");
	if (tableData.length == 0) {
		alert("暂无数据导出");
		return;
	}
	$("#downloadForm").remove();
	var url = path + '/service/performanceManageResultHistory/result/export';
	var $eleForm = $("<form method='post'></form>");
	$eleForm.attr("id", "downloadForm");
	$eleForm.attr("style", "display:none");
	$eleForm.attr("action", url);
	$eleForm.append($("#searchPanel").clone());
	$(document.body).append($eleForm);
	$eleForm.submit();// 提交表单，实现下载
}

/** clear * */
function clearParams() {
	$("#eHr").val("");
	$("#staffName").val("");
	$("#bu").val("");
	$("#du").val("");
	$("#startYear").val("2010");
	$("#startQuarter").val("Q1");
	$("#endYear").val("2018");
	$("#endQuarter").val("Q4");
}
