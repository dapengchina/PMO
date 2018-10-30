$(function(){
    queryLobApproveReportProportions();
    loadLobApproveReportList();
});

function queryLobApproveReportProportions(){
    $.ajax({
        url:path+'/service/performanceLobApprove/getPerformanceLobReportProportions',
        dataType : "json",
        data:JSON.stringify({'bu':""}),
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
        }
    })
}

function loadLobApproveReportList(){
    var queryUrl = path + '/service/performanceLobApprove/getPerformanceLobApproveReport';
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
			            field: 'preAssessmentResult',
			            title: '初评<br/>(依据<br/>客户<br/>反馈)',
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
			            title: '备注',
			            sortable: true
			        },{
			            title: 'Detail',
			            formatter:function(value,row,index){            	
			            	return "<a href='#' class='btn btn-info btn-small'><i class='glyphicon glyphicon-edit'></i></a>" ;
			            }
			        }];

    var table = $('#lobApproveReportDetailList').bootstrapTable({
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
        	return {
        		pageSize: params.limit,
        		pageNumber: params.offset/params.limit+1,
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


