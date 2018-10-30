$(function() {
    queryProportions("");
    loadLobApproveBUList();
});

var bu;

function updateApprovalFeedback() {
    var resultComments = $("#lobApprovalFeedback").val().trim();
    console.log("updateApprovalFeedback() resultComments is " + resultComments)
    if (resultComments != "") {
        $.ajax({
            url: path + '/service/performanceLobApprove/updateApprovalFeedback',
            dataType: "json",
            data: JSON.stringify({'bu': bu, 'resultComments': resultComments}),
            async: true,
            cache: false,
            type: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json; charset=utf-8'
            },
        success: function (sta) {
            console.log("updateApprovalFeedback result is " + sta);
            BootstrapDialog.show({
                title: '提示',
                message: '提交成功!',
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
    } else {

        BootstrapDialog.show({
            title: '提示',
            message: 'Comments 不能为空!',
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
}

function queryProportions(name){
    console.log("in queryProportions()");
	$.ajax({
		url:path+'/service/performanceLobApprove/getPerformanceLobApproveProportions?dt='+new Date().getTime(),
        dataType : "json",
		data:JSON.stringify({'bu':name}),
		async:true,
        cache:false,
        type : "POST",
        headers : {
            'Accept' : 'application/json',
            'Content-Type' : 'application/json; charset=utf-8'
        },
		success:function(sta){
            console.log("in queryProportions success()");
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
            $("#pageBU").html(sta["bu"]);
		}
	})
}

function loadLobApproveBUList(){
    var queryUrl = path+'/service/performanceLobApprove/getPerformanceLobApprovePerformances';
    var columns = [{
			        	radio: true,
			            visible: true                 //是否显示复选框
			        }, {
			            title: 'No.',
			            formatter:function(value,row,index){
				           	return "<span>" + (index+1) + "</span>";
			           }
			        }, {
			            field: 'bu',
			            title: 'BU'
			        }, {
			            field: 'year',
			            title: 'Year'
			        }, {
			            title: 'Quarter',
                        formatter: function (value,row,index) {
                            var display = "";
                            if (row.quarter == "1"){
                                display = "Q1";
                            } else if (row.quarter == "2"){
                                display = "Q2";
                            } else if (row.quarter == "3"){
                                display = "Q3";
                            } else if (row.quarter == "4"){
                                display = "Q4";
                            }
                            return display;
                        }
			        }, {
			            title: 'Status',
                        formatter: function (value,row,index) {
			                var display = "";
			                if (row.state == "1"){
			                    display = "Approved";
                            } else if (row.state == "2"){
                                display = "Rejected";
                            } else {
                                display = "Pending";
                            }
                            return display;
                        }
			        },{
			            title: 'Detail',
			            formatter:function(value,row,index) {
			            	return "<a href='performanceLobApproveDetails.html?bu="+row.bu+"' class='btn btn-info btn-small'><i class='glyphicon glyphicon-edit'></i></a>" ;
			            }
			        }];

    var table = $('#lobApproveBUList').bootstrapTable({
        url: queryUrl,                      //请求后台的URL（*）
        method: 'post',                      //请求方式（*）
//        toolbar: '#toolbar',              //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: false,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        pageSize: 10,                     //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                      //是否显示表格搜索
        strictSearch: false,
        showColumns: false,                  //是否显示所有的列（选择显示的列）
//        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect : true, // 是否启用点击选中行
//        height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        singleSelect:false, 				//禁止多选_____
        //得到查询的参数
        queryParams : function (params) {
        	return {
        		pageSize: params.limit,
        		pageNumber: params.offset/params.limit+1
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
           
        },
        onCheck: function (row, $element) {
            console.log("loadLobApproveBUList click radio")
            bu = row.bu;
            queryProportions(row.bu)
        }
        
    });
}

