$(function(){
	loadEmpHistoryList();
	loadSkill();
	loadRole();
	
});

function loadEmpHistoryList(){
    var queryUrl = path+'/service/performance/result/getPerforEmployeeHistory';
    var table = $('#empHistoryList').bootstrapTable({
        url: queryUrl,                      //请求后台的URL（*）
        method: 'GET',                      //请求方式（*）
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
                title : 'No.',
                formatter: function (value, row, index) {
                    return index+1;
                }
            },
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
	            title: 'Comments'
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
        onLoadSuccess: function (result) {
        	loadCSSubDept(result);
        	loadCSBu(result);
        },
        onLoadError: function (status, res) {
          
        }
    });
}

function detail(){
	window.location.href=path+"/service/performance/result/getDetail.html";
}

function loadSkill(){
	var url = path+'/json/skill.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#skill").append("<option>"+item.name+"</option>");
	       })
	});
}

function loadRole(){
	var url = path+'/json/role.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#role").append("<option>"+item.name+"</option>");
	       })
	});
}

var allCSSubDept;
function loadCSSubDept(result){
	var userType = result.user.userType;
	var csSubDeptNames = result.csSubDeptNames;
	$.ajax({
		url:path+'/service/csDept/queryAllCSSubDeptName',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(list){
			allCSSubDept= list;
			$("#csSubDept").empty();
			$("#csSubDept").append("<option value=''>--Option--</option>");
			for(var i = 0;i<list.length;i++){
				$("#csSubDept").append("<option>"+list[i].csSubDeptName+"</option>");
			}
			
			if(userType=='3' || userType=='4' || userType=='5'){
				if(csSubDeptNames.length==1){
					$('#csSubDept').val(result.csSubDeptNames[0]);
					$("#csSubDept").attr("disabled","disabled");
				}else if(csSubDeptNames.length>1){
					$("#csSubDept").empty();
					for(var i = 0;i<csSubDeptNames.length;i++){
						$("#csSubDept").append("<option>"+csSubDeptNames[i]+"</option>");
						//$('#csSubDept').val(result.pageInfo.csSubDeptName);
					}
				}
			}else{
				//$('#csSubDept').val(result.pageInfo.csSubDeptName);
				
			}
		}
	})
}

function loadCSBu(result){
	var csBuNames = result.csBuNames;
	var userType = result.user.userType;
	var url = path+'/json/csBuName.json'
	$.getJSON(url,  function(data) {
		   $("#csBu").empty();
		   $("#csBu").append("<option value=''>--Option--</option>");
	       $.each(data, function(i, item) {
	    	   $("#csBu").append("<option>"+item.name+"</option>");
	       })
	       if(userType=='1' || userType=='2' || userType=='3' || userType=='4'||userType=='5'){
	    	   if(csBuNames.length==1){   		   
	    		   $('#csBu').val(result.user.bu);
	    		   $("#csBu").attr("disabled","disabled");
	    	   }else if(csBuNames.length>1){
	    		   $("#csBu").empty();
	    		   for(var i = 0;i<csBuNames.length;i++){
						$("#csBu").append("<option>"+csBuNames[i]+"</option>");
						//$('#csBu').val(result.pageInfo.csbuName);
					}
	    	   }
			}else{
				//$('#csBu').val(result.pageInfo.csbuName);
			}
	});
}
function search(){
	//MSA Role
	var msaRole = $("#role").val();
	//Skills/Technology
	var skill = $("#skill").val();
	//BU
	var bu = $("#csBu").val();
	//DU
	var du = $("#csSubDept").val();
	//Start Year
	var startYear = $("#startYear").val();
	//Start Quarter
	var sq = $("#startQuarter").val();
	//End Year
	var endYear = $("#endYear").val();
	//End Quarter
	var eq = $("#endQuarter").val();
	console.log(startYear+sq+endYear+eq);
	var queryParams = { 
		query: {  
		   msaRole:msaRole,
		   skill:skill,
		   bu:bu,
		   du:du,
		   startYear:startYear,
		   endYear:endYear,
		   sq:sq,
		   eq:eq
        }
    }  
	//刷新表格  
    $('#empHistoryList').bootstrapTable('refresh',queryParams);  
}