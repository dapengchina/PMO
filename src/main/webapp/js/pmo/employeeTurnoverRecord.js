$(function () {
	loadTurnoverList();
	loadCSSubDept();
});

function loadCSSubDept(){
	$.ajax({
		url:path+'/service/csDept/queryAllCSSubDeptName',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(list){
			var html = "<option value=''>--Option--</option>";
			for(var i = 0;i<list.length;i++){
				html += "<option value='"+list[i].csSubDeptId+"'>"+list[i].csSubDeptName+"</option>";
			}
			$("#oldSubDept").html(html);
			$("#newSubDept").html(html);
		}
	})
}

function loadTurnoverList(){
	var queryUrl = path+'/service/employee/queryTurnoverList';
	var table = $("#turnoverList").bootstrapTable({
        url: queryUrl,
        contentType:'application/x-www-form-urlencoded; charset=UTF-8',
		method: "POST",
		striped: true,
		cache: false,
		pagination: true,
		sortbale: true,
		sortOrder: "asc",
		sidePagination: "server",
		pageNumber: 1,
		pageSize: 10,
		pageList: [10, 25, 50, 100],
		search: false,
		strictSearch: true,
		showColumns: false,
		showRefresh: true,
		minimumCountColumns: 2,
		clickToSelect : false,
		uniqueId: "id",
		showToggle: false,
		cardView: false,
		detailView: false,
		singleSelect: true,
		queryParams:function(params){
			//获取查询条件
        	var eHr = $("#eHr").val();
        	var staffId = $("#staffId").val();
        	var staffName = $("#staffName").val();
        	var lob = $("#lob").val();
        	var oldDepartment = $("#oldSubDept").val();
        	var newDepartment = $("#newSubDept").val();
        	var state = $("#state").val();
        	return {
        		pageSize : params.limit,
        		pageNumber : params.offset/params.limit+1,
        		eHr : eHr,
    			staffId : staffId,
    			staffName : staffName,
    			lob : lob,
    			oldDepartment : oldDepartment,
    			newDepartment : newDepartment,
    			state : state
            };
		},
		columns: [ 
	        {
	            field: 'ehr',
	            title: 'E-HR',
	            sortable: true
	        },
	        {
	            field: 'staffid',
	            title: 'Staff ID',
	            sortable: true
	        },
	        {
	            field: 'lob',
	            title: 'LOB',
	            sortable: true
	        }, 
	        {
	            field: 'staffname',
	            title: 'Staff Name',
	            sortable: true
	            , formatter : function(value, row, index){
	          	  	var div = "<div style='width:200px;'>"+value+"</div>";
	          	  	return div;
	            }
	        },
	        {
	            field: 'olddepartmentName',
	            title: 'Old DU',
	            sortable: true
	            , formatter : function(value, row, index){
	            	  if(value == null){
	            		  return "";
	            	  }else{
	            		  var div = "<div style='width:150px;'>"+value+"</div>";
	                	  return div;
	            	  }
	            }
	        },
	        {
	        	field: 'newdepartmentName',
	            title: 'New DU',
	            sortable: true
	            , formatter : function(value, row, index){
	            	  if(value == null){
	            		  return "";
	            	  }else{
	            		  var div = "<div style='width:150px;'>"+value+"</div>";
	                	  return div;
	            	  }
	            }
	        },
	        {
	        	field: 'applicantName',
	            title: 'Applicant',
	            sortable: true
	            , formatter : function(value, row, index){
	            	  if(value == null){
	            		  return "";
	            	  }else{
	            		  var div = "<div style='width:150px;'>"+value+"</div>";
	                	  return div;
	            	  }
	            }
	        },
	        {
	        	field: 'newRMName',
	            title: 'New RM',
	            sortable: true
	            , formatter : function(value, row, index){
	            	  if(value == null){
	            		  return "";
	            	  }else{
	            		  var div = "<div style='width:150px;'>"+value+"</div>";
	                	  return div;
	            	  }
	            }
	        },
	        {
	        	field: 'applicationdate',
	            title: 'Applicate Date',
	            sortable: true
	            , formatter : function(value, row, index){
	            	  if(value == null){
	            		  return "";
	            	  }else{
	            		  var div = "<div style='width:100px;'>"+ new Date(value).format("yyyy-MM-dd") +"</div>";
	                	  return div;
	            	  }
	            }
	        },
	        {
	        	field: 'approvalName',
	            title: 'Approval Name',
	            sortable: true
	            , formatter : function(value, row, index){
	            	  if(value == null){
	            		  return "";
	            	  }else{
	            		  var div = "<div style='width:150px;'>"+value+"</div>";
	                	  return div;
	            	  }
	            }
	        },
	        {
	        	field: 'approvaldate',
	            title: 'Approval Date',
	            sortable: true
	            , formatter : function(value, row, index){
	            	  if(value == null){
	            		  return "";
	            	  }else{
	            		  var div = "<div style='width:100px;'>"+ new Date(value).format("yyyy-MM-dd") +"</div>";
	                	  return div;
	            	  }
	            }
	        },
	        {
	            field: 'stateNmae',
	            title: 'State',
	            sortable: true,
	            formatter : function(value, row, index){
	            	var div = "<div style='width:100px;'>"+ value +"</div>";
	                return div;
	            }
	        }
	        ]
	});
}

function search(){
//	//获取查询条件
	var eHr = $("#eHr").val();
	var staffId = $("#staffId").val();
	var staffName = $("#staffName").val();
	var lob = $("#lob").val();
	var oldDepartment = $("#oldSubDept").val();
	var newDepartment = $("#newSubDept").val();
	var state = $("#state").val();
	var queryParams = { 
		query: {  
			eHr:eHr,
			staffId:staffId,
			staffName:staffName,
			lob:lob,
			oldDepartment : oldDepartment,
			newDepartment : newDepartment,
			state : state
        }
    }  
	//刷新表格  
    $('#turnoverList').bootstrapTable('refresh',queryParams);  
}

//Date Format
Date.prototype.format = function(fmt) { 
    var o = { 
       "M+" : this.getMonth()+1,
       "d+" : this.getDate(),
       "h+" : this.getHours(),
       "m+" : this.getMinutes(),
       "s+" : this.getSeconds(),
       "q+" : Math.floor((this.getMonth()+3)/3),
       "S"  : this.getMilliseconds()
   }; 
   if(/(y+)/.test(fmt)) {
           fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
   }
    for(var k in o) {
       if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
   return fmt; 
}