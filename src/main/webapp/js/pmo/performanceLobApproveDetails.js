$(function() {
    queryLobApproveDetailsProportions();
    loadLobApproveDetailsList();
    duSelect();
});

function reject() {
	$.ajax({
		url: path + '/service/performanceLobApprove/performanceLobStatusToReject',
		dataType: "json",
		data:JSON.stringify({'bu':$("#bu").val()}),
		async: true,
		cache: false,
		type: "POST",
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json; charset=utf-8'
		},
		success: function (sta) {
			console.log("reject result is " + sta);
			BootstrapDialog.show({
				title: '提示',
				message: 'reject success!',
				buttons: [{
					id: 'btn-ok',
					icon: 'glyphicon glyphicon-check',
					label: 'OK',
					cssClass: 'btn-primary',
					autospin: false,
					action: function(dialogRef){
						dialogRef.close();
					}
				}]
			});
		}
	})
}

function approve() {
	$.ajax({
		url: path + '/service/performanceLobApprove/performanceLobStatusToApprove',
		dataType: "json",
        data:JSON.stringify({'bu':$("#bu").val()}),
		async: true,
		cache: false,
		type: "POST",
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json; charset=utf-8'
		},
		success: function (sta) {
			console.log("approve result is " + sta);
			BootstrapDialog.show({
				title: '提示',
				message: 'approve success!',
				buttons: [{
					id: 'btn-ok',
					icon: 'glyphicon glyphicon-check',
					label: 'OK',
					cssClass: 'btn-primary',
					autospin: false,
					action: function(dialogRef){
						dialogRef.close();
					}
				}]
			});
		}
	})
}

function duSelect() {
    $.ajax({
        url : path + "/service/performanceLobApprove/getDuListByBu",
        dataType:"json",
        async:true,
        cache:false,
        type:"post",
        headers : {
            'Accept' : 'application/json',
            'Content-Type' : 'application/json; charset=utf-8'
        },
        data:JSON.stringify({'bu':$("#bu").val()}),
        success:function(list){
        	var duHid = $("#duHid").val();
            $("#du").empty();
            $("#du").append("<option value=''>--Option--</option>");
            for(var i = 0;i<list.length;i++){
            	if (duHid == list[i]){
                    $("#du").append("<option value='"+list[i]+"' selected=\"selected\">"+list[i]+"</option>");
                } else {
                    $("#du").append("<option value='"+list[i]+"'>"+list[i]+"</option>");
				}
            }
        }
    })
}

function queryLobApproveDetailsProportions(){
    $.ajax({
        url:path+'/service/performanceLobApprove/getPerformanceLobApproveProportions?dt='+new Date().getTime(),
        dataType : "json",
        data:JSON.stringify({'bu':$("#bu").val()}),
        async:true,
        cache:false,
        type : "POST",
        headers : {
            'Accept' : 'application/json',
            'Content-Type' : 'application/json; charset=utf-8'
        },
        success:function(sta){
            $("#rounda").html(sta["rounda"]);
            $("#roundbplus").html(sta["roundbplus"]);
            $("#roundb").html(sta["roundb"]);
            $("#roundc").html(sta["roundc"]);
            $("#roundd").html(sta["roundd"]);
            $("#roundTotal").html(sta["roundTotal"]);
            $("#cola").html(sta["cola"]);
            $("#colbplus").html(sta["colbplus"]);
            $("#colb").html(sta["colb"]);
            $("#colc").html(sta["colc"]);
            $("#cold").html(sta["cold"]);
            $("#total").html(sta["total"]);
            $("#lobApprovalFeedback").val(sta["approvalFeedback"]);
        }
    })
}


function loadLobApproveDetailsList(){
    var queryUrl = path+'/service/performanceLobApprove/getPerformanceLobDetails';
    var columns = [{
			            title: '序号',
			            formatter:function(value,row,index){
				           	return "<span>" + (index+1) + "</span>";
			           }
			        }, {
			            field: 'ehr',
			            title: 'E-HR编号'
			        }, {
			            field: 'lobId',
			            title: 'LOB工号'
			        }, {
			            field: 'staffName',
			            title: '姓名'
			        }, {
			            field: 'onBoardDate',
			            title: '入职时间'
			        }, {
			            field: 'role',
			            title: '职务'
			        }, {
						field: 'lob',
						title: 'LOB'
					}, {
			            field: 'bu',
			            title: 'BU'
			        }, {
			            field: 'du',
			            title: '交付部'
			        }, {
			            field: 'location',
			            title: '归属地'
			        }, {
			            field: 'backbone',
			            title: '是否<br/>骨干',
						formatter: function (value,row,index) {
							var display = "";
							if (row.backbone == "1"){
								display = "是";
							} else {
								display = "否";
							}
							return display;
						}
			        }, {
			            field: 'assessed',
			            title: '是否<br/>参评',
						formatter: function (value,row,index) {
							var display = "";
							if (row.assessed == "1"){
								display = "是";
							} else {
								display = "否";
							}
							return display;
						}
					}, {
			            field: 'directSupervisor',
			            title: '直接主管'
			        }, {
			            field: 'clientFeedback',
			            title: '客户反馈',
			            formatter:function(value,row,index){   
			                var substr = "";
			                if (value.length >5) {
				                substr = value.substring(0,5);	
				                return "<a href='#' class='link'>" +substr+ "<div class='tips'>" +value+ "</div></a>";
			                } else {
			                	return value;
			                }
			            }
			        }, {
			            field: 'preAssessmentResult',
			            title: '初评<br/>(依据<br/>客户<br/>反馈)',
			            sortable: true
			        }, {
			            field: 'directSupervisorAssessmentResult',
			            title: '直接<br/>主管<br/>初评<br/>结果',
			            sortable: true
			        }, {
			            field: 'groupAssessmentResult',
			            title: '部门<br/>集体<br/>评议<br/>结果',
			            sortable: true
			        }, {
			            field: 'groupAssessmentManager',
			            title: '集体<br/>评议<br/>主管'
			        }, {
			            field: 'performanceFacts',
			            title: 'A/C/D<br/>人员<br/>绩效<br/>事实'
			        }, {
			            field: 'performanceSkip',
			            title: '是否<br/>绩效<br/>跳变',
						formatter: function (value,row,index) {
							var display = "";
							if (row.performanceSkip == "1"){
								display = "是";
							} else {
								display = "否";
							}
							return display;
						}
			        }, {
			            field: 'remark',
			            title: '备注'
			        }, {
			            field: 'previous1Quarter',
			            title: '上<br/>季度<br/>绩效'
			        }, {
			            field: 'previous2Quarter',
			            title: '上上<br/>季度<br/>绩效'
			        }, {
			            field: 'previous3Quarter',
			            title: '上上上<br/>季<br/>度绩效'
			        },{
						title: 'Detail',
						formatter:function(value,row,index){
							return "<a href='#' class='btn btn-info btn-small'><i class='glyphicon glyphicon-edit'></i></a>" ;
						}
					}];
    
    var table = $('#queryLobApproveDetailsList').bootstrapTable({
        url: queryUrl,                      //请求后台的URL（*）
        method: 'post',                      //请求方式（*）
//	        toolbar: '#toolbar',              //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        pageSize: 10,                     //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                      //是否显示表格搜索
        strictSearch: false,
        showColumns: false,                  //是否显示所有的列（选择显示的列）
//	        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect : true, // 是否启用点击选中行
        height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        singleSelect:false, 				//禁止多选_____
        //得到查询的参数
        queryParams : function (params) {
        	//获取查询条件
        	var eHr = $("#eHr").val();
        	var staffName = $("#staffName").val();
        	var bu = $("#bu").val();
        	var du = $("#du").val();        	
        	return {
        		pageSize: params.limit,
        		pageNumber: params.offset/params.limit+1,
        		ehr: eHr,
        		staffName: staffName,
        		bu: bu,
        		du: du
            };
        },
        columns: columns
        ,onLoadSuccess: function (sta) {
			console.log("in onLoadSuccess");
			console.log(JSON.stringify(sta));
        }
        ,onLoadError: function (status, res) { //加载失败时执行
          console.log( res);
          console.log("error.status:" + status);
        },
        onDblClickRow: function (row, $element) {           
        }
        
    });
}

function clean(){
    $("#eHr").val("");
    $("#staffName").val("");
    duSelect();
}

function search(){	
	//获取查询条件
	var eHr = $("#eHr").val();
	var staffName = $("#staffName").val();
	var bu = $("#bu").val();
	var du = $("#du").val();      
	var queryParams = { 
		query: {  
    		ehr: eHr,
    		staffName: staffName,
    		bu: bu,
    		du: du
        }
    };
	//刷新表格
    $('#queryLobApproveDetailsList').bootstrapTable('refresh',queryParams);
}



