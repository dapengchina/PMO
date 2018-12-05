$(function(){
	loadEmpCurrentPeriodList();
});

function loadEmpCurrentPeriodList(){
    var queryUrl = path+'/service/performance/result/getCurrentYearQuarter';
    var table = $('#empEvaCurrentPeriodList').bootstrapTable({
        url: queryUrl,                      //请求后台的URL（*）
        method: 'post',                      //请求方式（*）
//        toolbar: '#toolbar',              //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        pageSize: 10,                     //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                      //是否显示表格搜索
//        strictSearch: true,
        showColumns: false,                  //是否显示所有的列（选择显示的列）
//        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect : true, // 是否启用点击选中行
        //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
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
        columns: [
	        {
	            field: 'year',
	            title: 'Year',
	            sortable: true
	        }, 
	        {
	            field: 'quarter',
	            title: 'Quarter',
	            sortable: true,
                formatter: function (value, row, index) {
                    return "Q"+value;
                }
	        }, 
	        {
	            field: 'du',
	            title: 'DU',
	            sortable: true
	        }, 
	        {
	            field: 'rm',
	            title: 'RM',
	            sortable: true
	        }, 
	        {
	            field: 'result',
	            title: 'Rating',
	            sortable: true
	        }, 
	        {
	            field: 'result_Comments',
	            title: 'ResultComments'
	        },
	        {
	            field: 'operation',
	            title: 'Operation',
	            sortable: true,
	            formatter : function(value,row, index){
	                return "<a onclick='detail()' href='#' class='btn btn-info btn-sm'>"+
	                "<span class='glyphicon glyphicon-pencil'></span> Detail"+
	              "</a>";
	            }
	        }
	    ],
        onLoadSuccess: function (sta) {
			
        },
        onLoadError: function (status, res) {
          
        }
    });
}

function detail(){
	window.location.href=path+"/service/performance/result/getDetail.html";
}


