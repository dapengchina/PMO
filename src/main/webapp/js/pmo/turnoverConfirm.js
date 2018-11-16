$(function () {
	loadTurnoverList();
});

var hasLoadCSSubDept = false; //du 接口是否已请求，若已请求，则不再发送请求
function loadCSSubDept(data){
	var userType = data.user.userType;
	var csSubDeptNames = data.csSubDeptNames;
	var csdeptIds = data.user.csdeptId.split(",");
	$.ajax({
		url:path+'/service/csDept/queryAllCSSubDeptName',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(list){
			hasLoadCSSubDept = true;
			var html = "<option value=''>--Option--</option>";
			for(var i = 0;i<list.length;i++){
				html += "<option value='"+list[i].csSubDeptId+"'>"+list[i].csSubDeptName+"</option>";
			}
			$("#oldSubDept").html(html);
			if(userType == "3"){
				html = "";
				if(csSubDeptNames.length == 1){
					html = "<option value='" + data.user.csdeptId + "'>" + csSubDeptNames[0] + "</option>"
					$("#newSubDept").attr("disabled","disabled");
				}else{
					for(var i=0;i<csSubDeptNames.length;i++){
						html += "<option value='" + csdeptIds[i] + "'>" + csSubDeptNames[i] + "</option>"
					}
				}
			}
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
        	return {
        		pageSize : params.limit,
        		pageNumber : params.offset/params.limit+1,
        		eHr : eHr,
    			staffId : staffId,
    			staffName : staffName,
    			lob : lob,
    			oldDepartment : oldDepartment,
    			newDepartment : newDepartment,
    			state : "0"
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
	            title: 'old DU',
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
	            title: 'new DU',
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
	            title: 'new RM',
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
	        }
	        ,{
	            title: 'Operate'
	            ,formatter:function(value,row,index){
	            	var r = "<a href='javascript:void(0);' class='btn-info btn-small' onclick=update('"+row.id+"','1')>Approve</a>" ;
	            	return r+"<a href='javascript:void(0);' class='btn-info btn-small' onclick=update('"+row.id+"','2')>Refuse</a>" ;
	            }
	        }
	        ],
	        onLoadSuccess: function (data) {
	        	if(!hasLoadCSSubDept){
	        		loadCSSubDept(data);
	        	}
	        }
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
	var queryParams = { 
		query: {  
			eHr:eHr,
			staffId:staffId,
			staffName:staffName,
			lob:lob,
			oldDepartment : oldDepartment,
			newDepartment : newDepartment,
			state : "0"
        }
    }  
	//刷新表格  
    $('#turnoverList').bootstrapTable('refresh',queryParams);  
}

function update(id, state){
	var eHr = $("#eHr").val();
	var staffId = $("#staffId").val();
	var staffName = $("#staffName").val();
	var lob = $("#lob").val();
	var oldDepartment = $("#oldSubDept").val();
	var newDepartment = $("#newSubDept").val();
	var queryParams = { 
		query: {  
			eHr:eHr,
			staffId:staffId,
			staffName:staffName,
			lob:lob,
			oldDepartment : oldDepartment,
			newDepartment : newDepartment,
			state : "0"
        }
    }
	$.ajax({
		url: path+"/service/employee/updateList",
		dataType: "json",
		cache: false,
		async: true,
		method:"post",
		data:{id:id, state: state},
		success: function(){
			$('#turnoverList').bootstrapTable('refresh',queryParams); 
		}
	});
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