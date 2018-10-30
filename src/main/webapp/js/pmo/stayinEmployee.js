var currentPage = "";//当前页码
var pageCount = "";//共几页 
var exportdata;
//页面加载执行的方法
$(function(){
	loadCandidateSkillInfo();
	loadRole();
	loadStayinList();
})
//查询按钮绑定
$('#searchStayinBtn').bind("click", function(){
	loadStayinList();
});
//到处按钮绑定
$('#exportCandidateExcel').bind("click", function(){	
	exportdata = new FormData(document.getElementById("stayinForm"));
	$('#myStayinListModal').modal('show');	
});

$("#pageRecordsNum").change(function(){
	loadStayinList();
})
//导出条件
function exportCondition(){
	var lb = $("label input");
	var exportDataColumn = "";
	var exportPageColumn = "";
	if(lb.length <= 0){
		alert("Please select item!");
		return;
	}
	for (var i=0;i<lb.length;i++)
	{
		if (lb.eq(i).is(':checked'))
		{
			exportDataColumn += lb.eq(i).attr("name")+",";
			exportPageColumn += lb.eq(i).val()+",";
		}
	}
	
	exportdata.append("exportDataColumn",exportDataColumn);
	exportdata.append("exportPageColumn",exportPageColumn);
	$.ajax({
		url:path+'/service/stayin/transformCandidateData',
		dataType:"json",
		data:exportdata,
		async:true,
		cache:false,
		processData:false,
        contentType:false,
		type:"post",
		success:function(result){
			if(result == '1')
			{
				exportData();
			}
		}
	})
	
}

function exportData(){
	var url = path+'/service/stayin/exportExcel';
	$("#exceltHrefCandidate").attr("href",url);
	document.getElementById("exceltHrefCandidate").click();
	
	$('#myStayinListModal').modal('hide');
	$("[type='checkbox']").attr("checked","checked");
}

/*function editEmployeeInfo(employeeId){
	$("#editForm").attr("action",path+"/service/employee/updateEmployeeInfo.html");
	$("#employeeId").val(employeeId);
	$("#editForm").submit();
}
*/



function loadCandidateSkillInfo(){
	var url = path+'/json/skill.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#skill").append("<option>"+item.name+"</option>");
	       })
	});
}
function loadRole(){
	var url = path+'/json/position.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#role").append("<option>"+item.name+"</option>");
	       })
	});
}


function loadStayinList(pageState)
{
	var candidate = new FormData(document.getElementById("stayinForm"));
	var  pageSize =parseInt($("#pageRecordsNum").find("option:selected").text());
	if(null != pageState)
	{
		candidate.append("pageState",pageState);
	}
	candidate.append("currentPage",currentPage);
	candidate.append("pageCount",pageCount);
	candidate.append("pageSize",pageSize);
	$.ajax({
		url:path+"/service/stayin/queryStayinList",
		dataType:"json",
		async:true,
		data:candidate,
		cache:false,
		processData:false,
        contentType:false,
		type:"post",
		success:function(result){
			$("#candidateList tbody").remove();
			var tbody = $("<tbody>");
			tbody.appendTo($("#candidateList"));
			if(result.data.length <= 0 ){
				$("#exportCandidateExcel").attr("disabled",true);
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);
				$("<td colspan='14' style='color: red;text-align: center;'>未查询到数据！</td>").appendTo(tr);
			}else{
				$("#exportCandidateExcel").removeAttr("disabled");
			}
			for (var i = 0; i < result.data.length; i++) {
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);
				if(result.data[i].demandStatus=='Delayed'){
					$("<td><a href='javascript:void(0);' onclick='displayReason(\""+result.data[i].candidateId+"\",\""+result.data[i].candidateName+"\",\""+result.data[i].arrivalDate+"\",\""+result.data[i].reason+"\")'>"+ result.data[i].candidateName+"</a></td>").appendTo(tr);
				}else{
					$("<td>"+ result.data[i].candidateName+"</td>").appendTo(tr);
				}
				$("<td>"+ result.data[i].candidateSex+ "</td>").appendTo(tr);
				$("<td>"+ result.data[i].candidateAge+ "</td>").appendTo(tr);
				$("<td>"+ result.data[i].candidateTel+ "</td>").appendTo(tr);
				$("<td>"+ result.data[i].email+ "</td>").appendTo(tr);
				$("<td>"+ result.data[i].skill+ "</td>").appendTo(tr);
				$("<td>"+ result.data[i].role+ "</td>").appendTo(tr);
				$("<td>"+ result.data[i].collage+ "</td>").appendTo(tr);
				/*$("<td>"+ result.data[i].education+ "</td>").appendTo(tr);
				$("<td>"+ result.data[i].experienceYears+ "</td>").appendTo(tr);*/
				$("<td>"+ result.data[i].demandStatus+ "</td>").appendTo(tr);
				if(result.data[i].arrivalDate==null||result.data[i].arrivalDate==""){
					$("<td>--</td>").appendTo(tr);
				}else{
					$("<td>"+ result.data[i].arrivalDate+ "</td>").appendTo(tr);
				}
				if(result.data[i].demandStatus=='Offering'){
					$("<td><a href='javascript:void(0);' class='btn btn-info btn-small'"+
						     "onclick=queryDemandList('"+result.data[i].candidateId+"','"+result.data[i].demandId+"')>Update</a>" +
					"</td>").appendTo(tr);
				}else if(result.data[i].demandStatus=='Frozen'||result.data[i].demandStatus=='Cancel'){
					$("<td><a href='javascript:void(0);' class='btn btn-info btn-small'" +
							"onclick='entryEmployee(\""+result.data[i].candidateId+"\",\""+result.data[i].engagementType+"\")'>Entry</a>" +
						     "&nbsp;&nbsp;" +
						     "<a href='javascript:void(0);' class='btn btn-info btn-small'"+
						     "onclick=cancelEmployee('"+result.data[i].candidateId+"','"+result.data[i].demandId+"')>Cancel</a>" +
					"</td>").appendTo(tr);
				}else{
					$("<td><a href='javascript:void(0);' class='btn btn-info btn-small'" +
						"onclick='onboard(\""+result.data[i].candidateId+"\",\""+result.data[i].engagementType+"\")'>Onboard</a>" +
					     "&nbsp;&nbsp;" +
					     "<a href='javascript:void(0);' class='btn btn-info btn-small'"+
					     "onclick=queryDemandList('"+result.data[i].candidateId+"','"+result.data[i].demandId+"')>Update</a>" +
				"</td>").appendTo(tr);
					
				}
			}
			$("#candidateList").append("</tbdoy>");
			currentPage = parseInt(result.pageInfo.currentPage);
			pageCount = parseInt(result.pageInfo.pageCount);
			var pageDataCount = parseInt(result.pageInfo.pageDataCount);
			var dataCount = parseInt(result.pageInfo.dataCount);
			$("#pageCount").html(pageCount);
			$("#currentPage").html(currentPage);
			$("#nextPage").attr("onclick","loadStayinList('next')");
			$("#previousPage").attr("onclick","loadStayinList('previous')");
			$("#lastPage").attr("onclick","loadStayinList('last')");
			$("#fristPage").attr("onclick","loadStayinList('frist')");
			if(currentPage == pageCount){
				$("#nextPage").removeAttr("onclick");
				$("#nextPage").parent("li").addClass("disabled");
				$("#lastPage").removeAttr("onclick");
				$("#lastPage").parent("li").addClass("disabled");
				$("#previousPage").parent("li").removeClass("disabled");
				$("#fristPage").parent("li").removeClass("disabled");
			}
			if(currentPage == 1){
				$("#previousPage").removeAttr("onclick");
				$("#previousPage").parent("li").addClass("disabled");
				$("#fristPage").removeAttr("onclick");
				$("#fristPage").parent("li").addClass("disabled");
				$("#nextPage").parent("li").removeClass("disabled");
				$("#lastPage").parent("li").removeClass("disabled");
			}
			
			$("ul.pagination-centered li a").each(function(){
				if(1 < currentPage && currentPage < pageCount){
					$(this).parent("li").siblings("li").removeClass("disabled");
				}
			});
			
			 loadCSSubDept(result);	
		}
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
//查询模态窗口需求列表
function queryDemandList(candidateId,demandId){
	var str = '<table id="demandList" class="table table-bordered table-hover">'+
	'<thead><tr><th></th><th>RR</<th><th>Job Code</th><th>Tech/Skill</th>'+
	'<th>Position</th><th>Location</th>'+
	'<th>Status</th><th>Request</th><th>Option</th></tr></thead></table>';
	$("#table_area").append(str);
	/*delegate() 方法为指定的元素（属于被选元素的子元素）添加一个或多个事件处理程序，并规定当这些事件发生时运行的函数。
	使用 delegate() 方法的事件处理程序适用于当前或未来的元素（比如由脚本创建的新元素）。*/
	$("#demandList").delegate("tr","click",function(e){
		$(this).find("input[type=radio]").prop("checked",true);
		
	});
	$.ajax({
		url:path+'/service/stayin/queryDemandList',
		dataType:"json",
		async:true,
		cache:false,
		data:{"candidateId":candidateId,"demandId":demandId},
		type:'post',
		success:function(result){
			if(result.length<0)
			{
				$("#demandList").append("<tr><td colspan='10' style='text-align:center'>No record!</td></tr>");
				return;
			}
			for(var i =0;i<result.length;i++){
				var tr = $("<tr id='"+result[i].demandId+"'></tr>");
				var td1 = $("<td><input type='radio' name='checkAll' value='"+result[i].demandId+"'/></td>");
				var td2 = $("<td>"+result[i].rr+"</td>");
				var td3 = $("<td>"+result[i].jobCode+"</td>");
				var td4 = $("<td>"+result[i].skill+"</td>");
				var td5 = $("<td>"+result[i].position+"</td>");
				/*var td6 = $("<td>"+result[i].hsbcSubDeptId+"</td>");*/
				var td7 = $("<td>"+result[i].location+"</td>");
				var td8 = $("<td>"+result[i].status+"</td>");
				var td9 = $("<td>"+result[i].requestor+"</td>");
				/*var td10 = $("<td>"+result[i].candidateId+"</td>");*/
				var td11 = $("<td><div class='btn-group btn-group-sm'><a href='javascript:void(0);' class='btn btn-primary' onclick=updateDemand('"+result[i].demandId+"','"+result[i].candidateId+"')>Confirm</div></td>");
		
				td1.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);
				td4.appendTo(tr);
				td5.appendTo(tr);
				/*td6.appendTo(tr);*/
				td7.appendTo(tr);
				td8.appendTo(tr);
				td9.appendTo(tr);
				/*td10.appendTo(tr);*/
				td11.appendTo(tr);
				$("#demandList").append(tr);
			}	

			var demandTable = $("#demandList");
			var demandId1 = $("input[name=checked]:checked").val();
			BootstrapDialog.show({
				title:"demandList",
				message:demandTable,
				size:BootstrapDialog.SIZE_WIDE,
				buttons:[{
					label:'Cancel',
					action:function(dialog){
						
						
						dialog.close();
					}
				}]
			})
		}
	});
	
}

function updateDemand(demandId,candidateId){
	$.ajax({
		url:path+'/service/stayin/UpdateDemand',
		dataType:'json',
		async:false,
		cache:false,
		type:'post',
		data:{"demandId":demandId,"candidateId":candidateId},
	    success:function(flag){
		     if(flag){
		    	 alert("Update successfully!");
		         location.reload();
		    	 
		     }
		     
	    }
	});
}

function onboard(index,engagementType){
	if(confirm("确定要Onborad吗?")){
		if(engagementType=="Time&Material"||engagementType=="Team Delivery"){
			url = path+'/service/demand/demandOnboard.html?type=1';
			$("#editForm").attr("action",url);
		}else if(engagementType=="Fixed Price"){
			url = path+'/service/demand/demandOnboard.html?type=2';
			$("#editForm").attr("action",url);
		}else if(engagementType=="Support"){
			url = path+'/service/demand/demandOnboard.html?type=3';
			$("#editForm").attr("action",url);
		}else{
			url = path+'/service/demand/demandOnboard.html?type=1';
			$("#editForm").attr("action",url);
		}
		$("#candidateId").val(index);
		$("#editForm").submit();
	}
}

function displayReason(candidateId,candidateName,arrivalDate,reason){
	$('#delayMyWaitCandidateId').val(candidateId);
	$('#delayMyWaitCandidateName').val(candidateName);
	$('#delayMyWaitCandidateArrivalDate').val(arrivalDate);
	$('#delayMyWaitCandidateReason').val(reason);
	$('#delayMyWaitCandidateModal').modal('show');
}

function entryEmployee(candidateId,engagementType){
	if(confirm("确定要Entry吗?")){
		url = path+'/service/employee/entryEmployee.html?'+candidateId;
		$("#editForm").attr("action",url);
	}
	$("#candidateId").val(candidateId);
	$("#editForm").submit();
}

function cancelEmployee(candidateId,engagementType){
	alert("cancelEmployee");
}






