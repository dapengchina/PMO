var currentPage = "";//当前页码
var pageCount = "";//共几页 
var exportdata;
$(function(){
	loadBlackList(); 
	loadBlackListSkillInfo();
})

$('#searchCandidateBtn').bind("click", function(){
	loadBlackList();
});

$('#exportCandidateExcel').bind("click", function(){	
	exportdata = new FormData(document.getElementById("blackListForm"));
	$('#blackListModal').modal('show');	
});

function exportCondition(){
	var lb = $("label input");
	var exportDataColumn = "";
	var exportPageColumn = "";
	if(lb.length <= 0){
		alert("未勾选导出列！");
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
		url:path+'/service/candidate/transformCandidateData',
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
	$('#blackListModal').modal('hide');
	var url = path+'/service/candidate/exportExcel';
	$("#exceltHrefCandidate").attr("href",url);
	document.getElementById("exceltHrefCandidate").click();
}

function downLoadCandidateResume(candidateId,resumePath){
	if(resumePath == null || resumePath == ''){
		alert("未上传此候选人简历");
		return;
	}
	var url = path+'/service/candidate/downLoadCandidateResume?candidateId='+candidateId;
	$("#exceltHrefCandidate").attr("href",url);
	document.getElementById("exceltHrefCandidate").click();
}

function loadBlackListSkillInfo(){
	var url = path+'/json/skill.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#skill").append("<option>"+item.name+"</option>");
	       })
	});
}
var statusMap = {"招聘中":"0","offer中":"1","已入职":"2","闲置中":"3","暂不关注":"4","黑名单":"5","入职他司":"6"};
function updateCandidateStatus(candidateId,candidateName,candidateStatus){	
		 
	$('#updateCandidateStatusId').val(candidateId);
	$('#updateCandidateStatusName').val(candidateName);
	$("#blackListCandidateStatus").val(statusMap[candidateStatus]);
	$('#blackListStatusModal').modal('show');
};
$(document).ready(function() {  
	$('#blackListStatusModal').on('hide.bs.modal', function(e) {  
        $('#statusCandidateForm').bootstrapValidator('resetForm', true);   
    });
	$("#statusSubmit").on("click", function(){
		   var bootstrapValidator = $("#statusCandidateForm").data('bootstrapValidator');
		   bootstrapValidator.validate();
		   if(bootstrapValidator.isValid())
			   updateStatus();
		   else return;

		});
	//Verify the input information when the Candidate'status is updated 
	$('#statusCandidateForm').bootstrapValidator({
		 feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {       	
	        	blackListCandidateStatus: {
	          		validators: {
	          			 notEmpty: {
	                          message: 'Please select status'
	                      }
	                  }
	              }
	        }
	    });	   
});
function updateStatus(){
	var updateMycandidateStatus = new FormData();
	var candidateStatus = $("#blackListCandidateStatus").find("option:selected").val();
	updateMycandidateStatus.append("candidateId",$("#updateCandidateStatusId").val());
	updateMycandidateStatus.append("candidateStatus",candidateStatus);
	
	$.ajax({
		url:path+'/service/candidate/updateCandidateStatusOk',
		dataType:"json",
		data:updateMycandidateStatus,
		async:true,
		cache:false,
		processData:false,
        contentType:false,
		type:"post",
		success:function(flag){
			if(flag){
				alert("更新成功！");				    
			}else{
				alert("更新失败！请刷新页面重试！");
			}
			$('#blackListStatusModal').modal('hide');	
			loadBlackList();
		}
	})
}
function loadBlackList(pageState)
{
	var candidate = new FormData(document.getElementById("blackListForm"));
	var pageRecordsNum = $("#pageRecordsNum").find("option:selected").text();
	if(null != pageState)
	{
		candidate.append("pageState",pageState);
	}
	candidate.append("currentPage",currentPage);
	candidate.append("pageCount",pageCount);
	candidate.append("pageRecNum",pageRecordsNum);
	$.ajax({
		url:path+"/service/candidate/queryBlackList",
		dataType:"json",
		async:true,
		data:candidate,
		cache:false,
		processData:false,
        contentType:false,
		type:"post",
		success:function(result){
			$("#blackList tbody").remove();
			var tbody = $("<tbody>");
			tbody.appendTo($("#blackList"));
			if(result.data.length <= 0 ){
				$("#exportCandidateExcel").attr("disabled",true);
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);
				$("<td colspan='14' style='color: red;text-align: center;'>未查询到数据！</td>").appendTo(tr);
			}else{
				$("#exportCandidateExcel").removeAttr("disabled");
			}
			for (var i = 0; i < result.data.length; i++) {
				var tr = $("<tr id='"+result.data[i].candidateId+"' ></tr>");
				tr.appendTo(tbody);
				$("<td><a href='javascript:void(0);' " +
				"onclick=displayPDF('"+result.data[i].candidateId+"','"+result.data[i].resumePath.replace(/\s+/g, "")+"')>" +result.data[i].candidateName+"</a></td>" +
				"<td>"+ result.data[i].candidateSex+ "</td>" +
				"<td>"+ result.data[i].candidateAge+ "</td>" +
				"<td>"+ result.data[i].candidateTel+ "</td>" +
				"<td>"+ result.data[i].email+ "</td>" +
				"<td>"+ result.data[i].source+ "</td>" +
				"<td>"+ result.data[i].candidateStatus+ "</td>" +
				"<td>"+ result.data[i].education+ "</td>" +
				"<td>"+ result.data[i].experienceYears+ "</td>" +
				"<td>"+ result.data[i].majorStatus+ "</td>" +
				"<td>"+ result.data[i].englishLevel+ "</td>" +
				"<td>"+ result.data[i].skill+ "</td>").appendTo(tr);
				
					$("<td><a href='javascript:void(0);' class='btn btn-info btn-small' " +
						"onclick=updateCandidateStatus('"+result.data[i].candidateId+"','"+result.data[i].candidateName+"')>Status</a>" +
					"</td>").appendTo(tr);
			}
			$("#blackList").append("</tbdoy>");
			currentPage = parseInt(result.pageInfo.currentPage);
			pageCount = parseInt(result.pageInfo.pageCount);
			var pageDataCount = parseInt(result.pageInfo.pageDataCount);
			var dataCount = parseInt(result.pageInfo.dataCount);
			$("#pageCount").html(pageCount);
			$("#currentPage").html(currentPage);
			$("#pageDataCount").html(pageDataCount);
			$("#dataCount").html(dataCount);
			$("#nextPage").attr("onclick","loadBlackList('next')");
			$("#previousPage").attr("onclick","loadBlackList('previous')");
			$("#lastPage").attr("onclick","loadBlackList('last')");
			$("#fristPage").attr("onclick","loadBlackList('frist')");
			if(currentPage == pageCount){
				$("#nextPage").removeAttr("onclick");
				$("#lastPage").removeAttr("onclick");
			}
			if(currentPage == 1){
				$("#previousPage").removeAttr("onclick");
				$("#fristPage").removeAttr("onclick");
			}
		}
	})
}

//gkf
function displayPDF(candidateId,resumePath){
	if(resumePath == null || resumePath == ''){
		alert("未上传此候选人简历");
		return;
	}
	var url = path+'/service/display/getPdf?candidateId='+candidateId;
	$("#editForm").attr("action",url);
	$("#candidateId").val(candidateId);
	$("#editForm").submit();
}

$("#pageRecordsNum").change(function(){
	loadBlackList();
})
