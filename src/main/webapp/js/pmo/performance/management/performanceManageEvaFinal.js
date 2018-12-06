$(function() {
	queryPercentage();
	loadManageEvaFinalList();

});

/** 获取当年-当季-定稿-绩效审核比例 * */
function queryPercentage() {
	$.ajax({
		url : path + '/service/performanceManageEva/finalize/percentage',
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
tableId = "manageEvaFirstDetailList";
function loadManageEvaFinalList() {
	var queryUrl = path + '/service/performanceManageEva/finalize/result/list';
	var table = $('#manageEvaFirstDetailList').bootstrapTable({
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
			var showAchievement = $("#showAchievement").val();
			return {
				pageSize : params.limit,
				pageNumber : params.offset / params.limit + 1,
				showAchievement : showAchievement
			};
		},
		columns : 
		[ 
			       {
					  title : 'No',
					  width: "70px",
					  formatter: function (value, row, index) {
				         return index+1;
				      }
				    },
				    {
			            field: 'operation',
			            title: 'Operation',
			            width: "120px",
			            sortable: true,
			            formatter : function(value,row, index){
			                return "<a onclick='detail(\"" + row.employee_id + "\")' href='#' class='btn btn-info btn-sm'>"+
			                "<span></span> Detail"+
			              "</a>";
			            }
			        },
				    {
					  field : 'ehr',
					  title : 'E-HR',
					  width: "100px"
				    }, 
				    {
					  field : 'lobNo',
					  title : 'LOB',
					  width: "100px"
				    },
				    {
					  field : 'name',
					  title : 'Name',
					  width: "100px"
				    }, 
				    {
					  field : 'hireDate',
					  title : 'Date-onboard',
					  width: "110px"
				    }, 
				    {
					  field : 'role',
					  title : 'MSA Role',
					  width: "130px"
				    }, 
				    {
					  field : 'serviceLine',
					  title : 'Line',
					  width: "100px"
				    }, 
				    {
					  field : 'bu',
					  title : 'BU',
					  width: "170px"
				    }, 
				    {
					  field : 'du',
					  title : 'DU',
					  width: "150px"
				    }, 
				    {
					  field : 'location',
					  title : 'Location',
					  width: "100px"
				    }, 
				    {
					  field : 'keymember',
					  title : 'Backbone',
					  width: "100px"
				    }, 
				    {
					  field : 'participate',
					  title : 'Assessed',
					  width: "100px"
				    }, 
				    {
					  field : 'manager',
					  title : 'Direct Supervisor',
					  width: "140px"
				    }, 
				    {
					  field : 'customerFeedback',
					  title : 'Client Feedback',
					  width: "140px",
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
				    }, 
				    {
					  field : 'initialEvalution',
					  title : 'Pre-Assessment',
					  width: "140px"
				    }, 
				    {
					  field : 'pmEvalution',
					  title : 'Pre-Assessment',
					  width: "140px",
					  formatter : function(value, row, index) {
						if (row.pmEvalution == "" || row.pmEvalution == undefined) {
							stateSubmitFlag = false;// RM初评页面，所有员工评级完成才可提交
						}
						return value;
				      }
				    }, 
				    {
					  field : 'duEvalution',
					  title : 'GroupAssessment',
					  width: "150px"
				    }, 
				    {
					  field : 'duEvaManager',
					  title : 'GroupAssessment Result',
					  width: "200px"
				    }, 
				    {
					  field : 'achievement',
					  title : 'PerformanceFacts(A/C/D)',
					  width: "200px"
				    }, 
				    {
					  field : 'jump',
					  title : 'PerformanceSkip',
					  width: "150px"
				    }, 
				    {
					  field : 'comments',
					  title : 'Remark',
					  width: "100px"
				    }, 
				    {
					  field : 'previous1Quarter',
					  title : 'Last Q',
					  width: "100px"
				    }, 
				    {
					  field : 'previous2Quarter',
					  title : '2Qs ago',
					  width: "100px"
				    }, 
				    {
					  field : 'previous3Quarter',
					  title : '3Qs ago',
					  width: "100px"
		}],
		onLoadSuccess : function(sta) {
//			console.log("in onLoadSuccess");
//			console.log(JSON.stringify(sta));
		},
		onLoadError : function(status, res) { // 加载失败时执行
//			console.log(res);
//			console.log("error.status:" + status);
		},
		onDblClickRow : function(row, $element) {

		}
	});
}

function detail(employeeId) {
	window.location.href = path+"/service/performanceManageEva/latestPerforDetailPage/"+employeeId;
}

/** 导出绩效定稿数据 */
function finalizeResultExport() {
	var tableData = $('#manageEvaFirstDetailList').bootstrapTable("getData");
	if (tableData.length == 0) {
		alert("暂无数据导出");
		return;
	}
	var url = path + '/service/performanceManageEva/finalize/result/export';
	window.location.href = url;
}
