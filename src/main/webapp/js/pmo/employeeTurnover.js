$(function(){	
	loadEmployeeList();	
})

$("#pageRecordsNum").change(function(){
	var csDeptName = $("#csDept").find("option:selected").text();

	if(csDeptName.indexOf('Option')!=-1){
		csDeptName = "";
	}
	var csBuName = $("#csBu").find("option:selected").text();

	if(csBuName.indexOf('Option')!=-1){
		csBuName = "";
	}
	var csSubDeptName = $("#csSubDept").find("option:selected").text();
	if(csSubDeptName.indexOf('Option')!=-1){
		csSubDeptName = "";
	}
	loadEmployeeList("",csDeptName,csSubDeptName,csBuName);
})

var hasLoadBu = false; //是否已经加载bu
var hasLoadDu = false; //是否已经加载du
var hasLoadRM = false; //是否已经加载 rm
$("#csSubDept").change(function(){
	var bu = $("#csBu").val();
	var du =$("#csSubDept").val();
	if($("#RM").attr("disabled") != "disabled"){
		loadUserForRM(bu,du);
	}
})

$("#csBu").change(function(){
	var bu = $("#csBu").val();
	var du =$("#csSubDept").val();
	if($("#RM").attr("disabled") != "disabled"){
		loadUserForRM(bu,du);
	}
	loadUserForRM(bu,du);
})

$('#searchBtn').bind("click", function(){
	var csDeptName = $("#csDept").find("option:selected").text();
	if(csDeptName.indexOf('Option')!=-1){
		csDeptName = "";
	}
	var csBuName = $("#csBu").find("option:selected").text();

	if(csBuName.indexOf('Option')!=-1){
		csBuName = "";
	}
	var csSubDeptName = $("#csSubDept").find("option:selected").text();
	if(csSubDeptName.indexOf('Option')!=-1){
		csSubDeptName = "";
	}
	loadEmployeeList("",csDeptName,csSubDeptName,csBuName);
});

function loadCSSubDept(result){
	var userType = result.user.userType;
	var csSubDeptNames = result.csSubDeptNames;
	var csdeptIds =  result.user.csdeptId.split(",");
	var html = "";
	if(userType == '5'){
		if(csSubDeptNames.length == 1){
			html += "<option value='"+ result.csSubDeptId +"'>"+ result.csSubDeptNames[0]+ "</option>";
			$("#csSubDept").html(html);
			$("#csSubDept").attr("disabled","disabled");
		}else if(csSubDeptNames.length>1){
			for(var i = 0;i<csSubDeptNames.length;i++){
				html += "<option value='"+csdeptIds[i]+"'>"+csSubDeptNames[i]+"</option>";
			}
			$("#csSubDept").html(html);
		}
		hasLoadDu = true;
	}else if(userType == "0"){
		$.ajax({
			url:path+'/service/csDept/queryAllCSSubDeptName',
			dataType:"json",
			async:true,
			cache:false,
			type:"post",
			success:function(list){
				hasLoadDu = true;
				html = "<option value>--Option--</option>";
				for(var i = 0;i<list.length;i++){
					html +="<option value='"+list[i].csSubDeptId+"'>"+list[i].csSubDeptName+"</option>";
				}
				$("#csSubDept").html(html);
				$('#csSubDept').val(result.csSubDeptId);
			}
		});
	}
}

function turnoverEmployee(employeeId){
	$("#editForm").attr("action",path+"/service/employee/editTurnover.html");
	$("#employeeId").val(employeeId);
	$("#editForm").submit();
}

function loadCSBu(result){
	var csBuNames = result.csBuNames;
	var userType = result.user.userType;
	var html = "";
	if(userType=='5'){
		if(csBuNames.length==1){
			html = "<option>" + csBuNames[0] + "</option>";
			$("#csBu").html(html);
 		    $("#csBu").attr("disabled","disabled");
 	   }else if(csBuNames.length>1){
 		   html = "<option value=''>--Option--</option>";
 		   for(var i = 0;i<csBuNames.length;i++){
				html += "<option>"+csBuNames[i]+"</option>";
			}
 		  $('#csBu').html(html);
 		  $('#csBu').val(result.pageInfo.csbuName);  
 	   }
		hasLoadBu = true;
	}else if(userType == "0"){
		var url = path+'/json/csBuName.json'
		$.getJSON(url,  function(data) { 
		   hasLoadBu = true;
		   html = "<option value=''>--Option--</option>";
	       $.each(data, function(i, item) {
	    	  html += "<option>"+item.name+"</option>";
	       })
	       $("#csBu").html(html);
	       $('#csBu').val(result.pageInfo.csbuName);
		});
	}
}

var allRMList = [];
function loadUserForRM(bu,du){
	if(allRMList.length == 0){
		$.ajax({
			url:path+'/service/user/getUserForRM',
			dataType:"json",
			async:true,
			cache:false,
			type:"post",
			success:function(list){
				allRMList = list;
				loadRMList(bu,du,allRMList)
				hasLoadRM = true;
			}
		});
	}else{
		loadRMList(bu,du,allRMList);
	}
}

function loadRMList(bu,du,allRMList){
	var html = "<option value=''>--Option--</option>";	
	var RMList = new Array();
	for(var i = 0; i<allRMList.length; i++){
		var csDeptIds = allRMList[i].csdeptId.split(",");
		var testbu = bu?bu:allRMList[i].bu;
		var testdu = du? du.split():csDeptIds;
		if(allRMList[i].bu == testbu){
			if(testdu.length>1){
				RMList.push(allRMList[i]);
			}else{
				for (var j = 0; j < csDeptIds.length; j++) {
					if (testdu == csDeptIds[j]) {
						RMList.push(allRMList[i]);
					}
				}
			}
		}
	}	
	for(var i = 0;i < RMList.length;i++){
		html += "<option value='"+RMList[i].userId+"'>"+RMList[i].nickname+"</option>";
	}	
	$("#RM").html(html);
}

function loadEmployeeList(pageState,csDeptName,csSubDeptName,csBuName){
	var hsbcStaffId = $("#hsbcStaffId").val();	
	var eHr = $("#eHr").val();	
	var lob = $("#lob").val();	
	var staffName = $("#staffName").val();
	var rmName= $("#RM").val();
	var pageState = pageState;
	var pageRecordsNum = $("#pageRecordsNum").find("option:selected").text();
	var data = {
			"staffName" : staffName,
			"pageState" : pageState,
			"csBuName" : csBuName,
			"csDeptName" : csDeptName,
			"csSubDeptName" : csSubDeptName,
			"hsbcStaffId" : hsbcStaffId,
			"eHr" : eHr,
			"lob": lob,
			"rmUserId" : rmName,
			"pageRecordsNum" : pageRecordsNum
	};
	$.ajax({
		url:path+"/service/employeeInfo/queryBatchEmployeeList",
		dataType:"json",
		async:true,
		data: data,
		cache:false,
		type:"post",
		success:function(result){
			var userType = result.user.userType;
			var html = "";
			for (var i = 0; i < result.data.length; i++) {
				html +="<tr><td>"+ (i+1) +"</td>" +
						"<td>"+ result.data[i].staffName +"</td>" +
						"<td>"+ (result.data[i].eHr?result.data[i].eHr:"") +"</td>" +
						"<td>"+ (result.data[i].lob?result.data[i].lob:"") +"</td>" +
						"<td>"+ (result.data[i].hsbcStaffId?result.data[i].hsbcStaffId:"") +"</td>" +
						"<td>"+ (result.data[i].csSubDeptName?result.data[i].csSubDeptName:"") +"</td>" +
						"<td>"+ result.data[i].engagementType +"</td>" +
						"<td>"+ (result.data[i].resourceStatus?result.data[i].resourceStatus:"") +"</td>" +
						"<td>"+ (result.data[i].nickname?result.data[i].nickname:"") +"</td>" +
						"<td><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=turnoverEmployee('"+result.data[i].employeeId+"')>Turnover</a></td></tr>";
			}
			$("#employeeList tbody").html(html);
			var pageNum = parseInt(result.pageInfo.currentPage);
			var pageRecordsNum = parseInt(result.pageInfo.pageRecordsNum);
			pageNum = pageNum / pageRecordsNum + 1;
			var totalPage = parseInt(result.pageInfo.pageCount);
			$("#pageCount").html(totalPage);
			$("#currentPage").html(pageNum);
			$("#nextPage").attr("onclick","loadEmployeeList('next')");
			$("#previousPage").attr("onclick","loadEmployeeList('previous')");
			if(pageNum == totalPage){
				$("#nextPage").removeAttr("onclick");
			}
			if(pageNum == 1){
				$("#previousPage").removeAttr("onclick");
			}
			if(!hasLoadBu){
				loadCSBu(result);
			}
			if(!hasLoadDu){
				loadCSSubDept(result);
			}
			if(!hasLoadRM){
				if(userType == "5"){
					$("#RM").html("<option value = '"+result.user.userId +"'>"+ result.user.nickname +"</option>");
					$("#RM").attr("disabled","disabled");
					hasLoadRM = true;
				}else if(userType == "0"){
					loadUserForRM(result.pageInfo.csbuName,result.csSubDeptId);
				}
			}
		}
	});
}

