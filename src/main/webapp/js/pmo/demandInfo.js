
$(function(){
	loadSkill();
	loadPosition();
	loadDemandStatus();
	loadEngagementType();
	loadDemandList();
	$("#searchBtn").click(function(){
		loadDemandList();
	})
	/*loadCsBuName();
	loadScSubDeptName();*/
	
	
})

$("#skill").change(function(){
	$("#exportExcel").attr("disabled", true);
})
$("#position").change(function(){
	$("#exportExcel").attr("disabled", true);
})

$("#pageRecordsNum").change(function(){
	loadDemandList();
})
/*$("#department").change(function(){
	$("#exportExcel").attr("disabled", true);
})
$("#sub_department").change(function(){
	$("#exportExcel").attr("disabled", true);
})*/
$("#status").change(function(){
	$("#exportExcel").attr("disabled", true);
})
$("#csBuName").change(function(){
	$("#exportExcel").attr("disabled", true);
})
$("#scSubDeptName").change(function(){
	$("#exportExcel").attr("disabled", true);
})

/*加载skill本地json信息*/
function loadSkill(){
	var url = path+'/json/skill.json';
	$.getJSON(url, function(data){
		$.each(data, function(i, item){
			 $("#skill").append("<option value='"+item.name+"'>"+item.name+"</option>");
		})
	})
}
/*加载position本地json信息*/
function loadPosition(){
	var url = path+'/json/position.json';
	$.getJSON(url, function(data){
		$.each(data, function(i, item){
			$("#position").append("<option value='"+item.name+"'>"+item.name+"</option>");
		})
	})
}

/*导出Excel全选*/
function selectAll(){

	$("input[type='checkbox']").attr("checked",'true');
}


/*加载status本地json信息*/
function loadDemandStatus(){
	var url = path+'/json/demandStatus.json';
	$.getJSON(url, function(data){
		$.each(data, function(i, item){
			$("#status").append("<option value='"+item.name+"'>"+item.name+"</option>");
		})
	})
}
/*加载业务部*/
function loadCsBuName(){
	var url = path+'/json/csBuName.json';
	$.getJSON(url, function(data){
		$.each(data,function(i,item){
			$("#csBuName").append("<option value='"+item.name+"'>"+item.name+"</option>");
		})
	})
}

/*
function loadCSBu(result){
	var userType = result.user.userType;
	var url = path+'/json/csBuName.json'
	$.getJSON(url,  function(data) {
		$("#csBuName").empty();
		$("#csBuName").append("<option value=''>--Option--</option>");
	       $.each(data, function(i, item) {
	    	   $("#csBuName").append("<option value='"+item.name+"'>"+item.name+"</option>");
	       })
	       if(userType=='1' || userType=='2' || userType=='3' || userType=='4'){
				$('#csBuName').val(result.user.bu);
				$("#csBuName").attr("disabled","disabled");
			}
	});
}*/

function loadCSBu(result){
	var csBuNames = result.csBuNames;
	var userType = result.user.userType;
	var url = path+'/json/csBuName.json'
	$.getJSON(url,  function(data) {
		   $("#csBuName").empty();
		   $("#csBuName").append("<option value=''>--Option--</option>");
	       $.each(data, function(i, item) {
	    	   $("#csBuName").append("<option>"+item.name+"</option>");
	       })
	       if(userType=='1' || userType=='2' || userType=='3' || userType=='4'||userType=='5'|| userType=='11'||userType=='12'){
	    	   if(csBuNames.length==1){   		   
	    		   $('#csBuName').val(result.user.bu);
	    		   $("#csBuName").attr("disabled","disabled");
	    	   }else if(csBuNames.length>1){
	    		   $("#csBuName").empty();
	    		   for(var i = 0;i<csBuNames.length;i++){
						$("#csBuName").append("<option>"+csBuNames[i]+"</option>");
						$('#csBuName').val(result.csBuName);
					}
	    	   }
			}else{
				$('#csBuName').val(result.csBuName);
			}
	});
}

/*加载交付部*/
function loadScSubDeptName(){
	$("#csBuName").change(function(){
		var csBuName = $("#csBuName").val();
		$("#scSubDeptName").empty();
		$("#scSubDeptName").append("<option value=''>--Option--</option>");
		$.ajax({
			url:path+'/service/demand/loadScSubDeptName',
			dataType:"json",
			async:true,
			cache:false,
			type:"post",
			data:{"csBuName":csBuName},
			success:function(data){
				$.each(data, function(i,item){
					$("#scSubDeptName").append("<option value='"+item.csSubDeptName+"'>"+item.csSubDeptName+"</option>");
				})
			}
		})
	})
}
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
			$("#csSubDept").empty();
			$("#csSubDept").append("<option value=''>--Option--</option>");
			for(var i = 0;i<list.length;i++){
				$("#csSubDept").append("<option>"+list[i].csSubDeptName+"</option>");
			}
			
			if(userType=='3' || userType=='4' || userType=='5'|| userType=='11'||userType=='12'){
				if(csSubDeptNames.length==1){
					$('#csSubDept').val(result.csSubDeptNames[0]);
					$("#csSubDept").attr("disabled","disabled");
				}else if(csSubDeptNames.length>1){
					$("#csSubDept").empty();
					for(var i = 0;i<csSubDeptNames.length;i++){
						$("#csSubDept").append("<option>"+csSubDeptNames[i]+"</option>");
						$('#csSubDept').val(result.csSubDeptName);
					}
				}
			}else{
				$('#csSubDept').val(result.csSubDeptName);
				
			}
		}
	})
}

/*function loadCSSubDept(){
	$.ajax({
		url:path+'/service/csDept/queryAllCSSubDept',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(result){
			var userType = result.user.userType;
			
			var csSubs = result.csSubDepts;
			if(userType=='0'){
				for(var i = 0;i<result.data.length;i++){
					$("#csSubDept").append("<option value='"+result.data[i].csSubDeptId+"'>"+result.data[i].csSubDeptName+"</option>");
				}
			}else{
				if(csSubs.length==1){
					$("#csSubDept").append("<option value='"+csSubs[0].csSubDeptId+"'>"+csSubs[0].csSubDeptName+"</option>");
					$('#csSubDept').val(csSubs[0].csSubDeptId);
					$("#csSubDept").attr("disabled","disabled");
				}else if(csSubs.length>1){
					$("#csSubDept").empty();
					for(var i = 0;i<csSubs.length;i++){
						$("#csSubDept").append("<option value='"+csSubs[i].csSubDeptId+"'>"+csSubs[i].csSubDeptName+"</option>");
					}
				}
			}
		}
	})
}*/
/*异步加载Department信息*/
/*function loadDepartment(){
	$.ajax({
		url:path+'/service/demand/loadDepartment',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(data){
			$.each(data, function(i,item){
				$("#department").append("<option value='"+item.hsbcDeptName+"'>"+item.hsbcDeptName+"</option>")
			})
		}
	})
}*/
/*根据department加载subdepartment的ajax*/
/*function loadSubDepartment(){
	$("#department").change(function(){
		//var department = $("#department option:selected").text();
		var department = $("#department").val();
		//$("#sub_department").find("option").remove();
		$("#sub_department").empty();
		$("#sub_department").append("<option value=''>--Option--</option>");
		$.ajax({
			url:path+'/service/demand/loadSubDepartment',
			dataType:"json",
			async:true,
			cache:false,
			type:"post",
			data:{"hsbcDeptName":department},
			success:function(data){
				$.each(data, function(i,item){
					if(item.hsbcSubDeptName){
						$("#sub_department").append("<option value='"+item.hsbcSubDeptName+"'>"+item.hsbcSubDeptName+"</option>");
					}
				})
			}
		});
	});
}*/
	
/*根据条件和当前页加载查询到的信息*/
function loadDemandList(currPage){
	var skill= $("#skill").val();
	var position= $("#position").val();
	var  pageRecordsNum = $("#pageRecordsNum").find("option:selected").text();
	/*var department= $("#department").val();
	var sub_department= $("#sub_department").val();*/
	var status= $("#status").val();
	var rr= $("#rr").val();
	var csBuName = $("#csBuName").val();
	var csSubDept = $("#csSubDept").val();
	var engagementType=$("#engagementType").val();
	var jobCode=$("#jobcode").val();
	var demandNumber = $("#demandNumber").val();
	//$("#demandList").empty();
	$("#demandList  tr:not(:first)").html("");
	$.ajax({
		url:path+'/service/demand/queryDemandList',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		data:{"csBuName":csBuName,"skill":skill,"position":position,

			"status":status,"demandId":demandNumber,"rr":rr,"currPage":currPage,"csSubDept":csSubDept,"engagementType":engagementType,"jobCode":jobCode,"pageRecordsNum":pageRecordsNum
         
       },

		success:function(result){
			//alert(result.list.length);
			var userType = result.user.userType;
			if(result.list.length > 0){
				$("#exportExcel").removeAttr("disabled");
			}else{
				$("#demandList").append("<tr><td colspan='8' style='text-align:center'>No record!</td></tr>");
			}
			//$.each(reslut, function(i,data){
			for (var i = 0; i < result.list.length; i++) {
				var tr = $("<tr id='"+result.list[i].rr+"'></tr>");
				var rrp=result.list[i].rr;
				var jobCodep=result.list[i].jobCode;
				if(rrp==null||rrp==""){
					var td1 = $("<td></td>");
				}else{
					var td1 = $("<td>"+result.list[i].rr+"</td>");
				}
				if(jobCodep==null||jobCodep==""){
					var td2 = $("<td></td>");
				}else{
					var td2 = $("<td>"+result.list[i].jobCode+"</td>");
				}
				var td3 = $("<td>"+result.list[i].skill+"</td>");
				var td4 = $("<td>"+result.list[i].position+"</td>");
				var td5 = $("<td>"+result.list[i].location+"</td>");
				var td6 = $("<td>"+result.list[i].status+"</td>");
				var status=result.list[i].status;
			/*	var hsbcSubDeptName=result.list[i].hsbcSubDeptName;
				var csDeptName=result.list[i].csSubDept;*/
				var td7 = $("<td>"+result.list[i].csDeptName+"</td>");
				var demandId = result.list[i].demandId;
				var statusa  = result.list[i].status;
				
				var engagementType ="";
				if(result.list[i].engagementType!=null){
					engagementType = result.list[i].engagementType;
				}
				
				/*$("#hsbcsubName").val(hsbcSubDeptName);
				$("#cssubName").val(cssubName);*/
				if(userType=='2' || userType=='4' || userType=='6' || userType=='7'  || userType=='9' || userType=='15'){

					var td8 = $("<td><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=demandDetail(\""+demandId+"\",\""+statusa+"\",\""+engagementType.replace(/\s/g, "")+"\")>Detail</a></td>");
				}else if(userType=='13' || userType=='14'){
					if(result.list[i].csSubDept == result.user.bu){
						var td8 = $("<td><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=demandDetail(\""+demandId+"\",\""+statusa+"\",\""+engagementType.replace(/\s/g, "")+"\")>Detail</a><a href='javascript:void(0); ' class='btn btn-info btn-small' onclick=demandDetailUpdate(\""+demandId+"\",\""+statusa+"\",\""+engagementType.replace(/\s/g, "")+"\",\""+userType+"\")>Edit</a></td>");
					}else{
						var td8 = $("<td><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=demandDetail(\""+demandId+"\",\""+statusa+"\",\""+engagementType.replace(/\s/g, "")+"\")>Detail</a></td>");
					}
					
				}else if(status=='Cancel'||status=='Abort'){
					
					var td8 = $("<td><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=demandDetail(\""+demandId+"\",\""+statusa+"\",\""+engagementType.replace(/\s/g, "")+"\")>Detail</a></td>");

				}else{
					var td8 = $("<td><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=demandDetail(\""+demandId+"\",\""+statusa+"\",\""+engagementType.replace(/\s/g, "")+"\")>Detail</a><a href='javascript:void(0); ' class='btn btn-info btn-small' onclick=demandDetailUpdate(\""+demandId+"\",\""+statusa+"\",\""+engagementType.replace(/\s/g, "")+"\",\""+userType+"\")>Edit</a></td>");

				}
				td1.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);
				td4.appendTo(tr);
				td5.appendTo(tr);
				td6.appendTo(tr);
				td7.appendTo(tr);
				td8.appendTo(tr);
				$("#demandList").append(tr);
			}
			//alert(result.pageCondition.totalPage);
			$("#pageCount").html(result.pageCondition.totalPage);
			$("#currentPage").html(result.pageCondition.currPage);
			$("#fristPage").attr("onclick","loadDemandList(1)");
			if(result.pageCondition.currPage <= result.pageCondition.totalPage){
				$("#previousPage").attr("onclick","loadDemandList("+(result.pageCondition.currPage - 1)+")");
				$("#nextPage").attr("onclick","loadDemandList("+(result.pageCondition.currPage + 1)+")");
				$("#lastPage").attr("onclick","loadDemandList("+(result.pageCondition.totalPage)+")");
			}
			if(result.pageCondition.currPage==result.pageCondition.totalPage){
				$("#nextPage").parent("li").addClass("disabled");
				$("#nextPage").removeAttr('onclick');
				$("#lastPage").parent("li").addClass("disabled");
				$("#lastPage").removeAttr('onclick');
				$("#fristPage").parent("li").removeClass("disabled");
				$("#previousPage").parent("li").removeClass("disabled");
			}
			if(result.pageCondition.currPage==1){
				$("#fristPage").parent("li").addClass("disabled");
				$("#fristPage").removeAttr('onclick');
				$("#previousPage").parent("li").addClass("disabled");
				$("#previousPage").removeAttr('onclick');
				$("#nextPage").parent("li").removeClass("disabled");
				$("#lastPage").parent("li").removeClass("disabled");
			}
			$("ul.pagination-centered li a").each(function(){
				if( 1 < result.pageCondition.currPage && result.pageCondition.currPage < result.pageCondition.totalPage){
					$(this).parent("li").siblings("li").removeClass("disabled");
				}
			});
			
			loadCSBu(result);
            loadCSSubDept(result);			
		}
	})

}

$('#exportExcel').bind("click", function(){
	
	$('#myModal').modal('show');
	
});

function exportCondition(){
	var condition="";
	$("label").find(":checkbox:checked").each(function(){
		condition += $(this).attr("name") +",";
	});
	console.log(condition);
	var url = path+'/service/demand/exportExcel';
	$("#condition").val(condition);
	$("#conditionForm").attr("action",url);
	$("#conditionForm").submit();
	$('#myModal').modal('hide');
	$("[type='checkbox']").removeAttr("checked");
}

function demandDetail(demandId,statusa,engagementType){
	$("#demandId").val(demandId);
	$('#statusa').val(statusa);
	if(engagementType=="FixedPrice"){
		var url = path+'/service/demand/demandDetail?engagementType=1';
	}else if(engagementType=="Support"){
		var url = path+'/service/demand/demandDetail?engagementType=2';
	}else{
		var url = path+'/service/demand/demandDetail?engagementType=3';
	}
	$("#detailForm").attr("action",url);
	$("#detailForm").submit();
}

//add by jama
function demandDetailUpdate(demandId,statusa,engagementType,userType){
	
	$("#demandId").val(demandId);
	$('#statusa').val(statusa);
	$('#userType').val(userType);
	if(engagementType=="FixedPrice"){
		var url = path+'/service/demand/demandDetailUpdate?engagementType=1';
	}else if(engagementType=="Support"){
		var url = path+'/service/demand/demandDetailUpdate?engagementType=2';
	}else{
		var url = path+'/service/demand/demandDetailUpdate?engagementType=3';
	}
	
	
	$("#detailForm").attr("action",url);
	$("#detailForm").submit();
}

function loadEngagementType(){
	var url = path+'/json/engagementType.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#engagementType").append("<option>"+item.name+"</option>");
	       })
	});
}
/*function demandDetailUpdate(){
	var demandId = "1";
	$.ajax({
		url:path+'/service/demand/demandDetailUpdate ',
		dataType:"json",
		async:false,
		data:{"demandId":demandId},
		cache:false,
		type:"post",
		success:function(data){
			alert("success");
		}
	})
}*/