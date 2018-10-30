$(function () {
	majorcateIds();
    loadCapabilityList();
});

function majorcateIds(result){
	$.ajax({
		url:path+'/service/capability/majorcateIds',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(list){
			$("#majorcateId").empty();
			$("#majorcateId").append("<option value=''>--Option--</option>");
			for(var i = 0;i<list.length;i++){
				$("#majorcateId").append("<option value='"+list[i].majorcateId+"'>"+list[i].paramName+"</option>");
			}
		}
	})
}

function loadCapabilityList(){
    var queryUrl = path+'/service/capability/query';
	//var queryUrl = path+'/json/test.json';
    var table = $('#capabilityList').bootstrapTable({
        url: queryUrl,                      //请求后台的URL（*）
        method: 'GET',                      //请求方式（*）
        toolbar: '#toolbar',              //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        fixedColumns: true,
        fixedNumber: 0,
        singleSelect : true,                // 单选checkbox 
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        pageSize: 10,                     //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                      //是否显示表格搜索
        strictSearch: true,
        showColumns: false,                  //是否显示所有的列（选择显示的列）
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        //得到查询的参数
        queryParams : function (params) {
        	return {
        		pageSize: params.limit,
        		pageNumber: params.offset/params.limit+1,
            };
        },
        columns: [
        {
        	checkbox: true,  
            visible: true                 //是否显示复选框  
        }, 
        {
            field: 'createDate',
            title: 'Create Date',
            sortable: true
            ,formatter:function(value,row,index){
            	if(value){
            		var d = new Date();
                	d.setTime(value);
                    return d.toLocaleDateString();
            	}
            }
        },
        {
            field: 'updateDate',
            title: 'Update Date',
            sortable: true
            ,formatter:function(value,row,index){
            	if(value){
            		var d = new Date();
                	d.setTime(value);
                    return d.toLocaleDateString();
            	}
            }
        },
//        {
//            field: 'operateId',
//            title: 'operateId',
//            sortable: true
//        }, 
        {
            field: 'majorcateId',
            title: 'Major Cate',
            sortable: true
        },
        {
            field: 'subcateId',
            title: 'Subcate',
            sortable: true
        },
        {
            field: 'paramName',
            title: 'Param Name',
            sortable: true
        }
        ,{
//            field: 'Operate',
            title: 'Operate',
            sortable: true
            
            ,formatter:function(value,row,index){
            	return "<a href='javascript:void(0);' class='btn btn-info btn-small' onclick=editCapability('"+row.id+"')>Edit</a>";
            }
        }
        /*{
            field:'id',
            title: 'Operation',
            width: 120,
            align: 'center',
            valign: 'middle'
//        }*/
        
        ]
        ,onLoadSuccess: function () {
        	var data = $('#capabilityList').bootstrapTable('getData');
//        	loadOfflineSummary(data);
        },
        onLoadError: function () {
        	
        },
        onDblClickRow: function (row, $element) {
           
        }
        
    });
}

function search(){
//	//获取查询条件
	var majorcateId = $("#majorcateId").val();
	var paramName = $("#paramName").val();
//	//中软项目名称
//	var projectName = $("#projectName").val();
//	//中软项目编号
//	var proNumber = $("#projectNumber").val();
//	//中软部门
//	var csdeptid = $("#csSubDept").val();
	var queryParams = { 
		query: {  
			majorcateId:majorcateId,
			paramName:paramName
//		   ,projectName:projectName,
//		   proNumber:proNumber,
//		   csdeptid:csdeptid
        }
    }  
	//刷新表格  
    $('#capabilityList').bootstrapTable('refresh',queryParams);  
}

function editCapability(id){
//	$("#editForm").attr("action",path+"/service/capability/editPage.html");
//	$("#id").val(id);
//	$("#editForm").submit();
	var url= path+"/service/capability/editPage.html"+"?id="+id
	location=url;
}


