var currentPage = "";//当前页码
var pageCount = "";//共几页 
var exportdata;
$(function(){
	loadCandidateList(); 
	loadCandidateSkillInfo();
})

$('#searchCandidateBtn').bind("click", function(){
	loadCandidateList();
});

$('#exportCandidateExcel').bind("click", function(){	
	exportdata = new FormData(document.getElementById("candidateForm"));
	$('#myCandidateListModal').modal('show');	
});

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
	$('#myCandidateListModal').modal('hide');
	var url = path+'/service/candidate/exportExcel';
	$("#exceltHrefCandidate").attr("href",url);
	document.getElementById("exceltHrefCandidate").click();
}

function updateResumeInfo(candidateId){
	$("#editForm").attr("action",path+"/service/resume/toUpdateResume");
	$("#candidateId").val(candidateId);
	$("#editForm").submit();
}

function downLoadCandidateResume(candidateId,resumePath){
	if(resumePath == null || resumePath == ''){
		alert("Not uploading the resume");
		return;
	}
	var url = path+'/service/candidate/downLoadCandidateResume?candidateId='+candidateId;
	$("#exceltHrefCandidate").attr("href",url);
	document.getElementById("exceltHrefCandidate").click();
}

//gkf
function displayPDF(candidateId,resumePath){
	if(resumePath == null || resumePath == ''){
		alert("Not uploading the resume");
		return;
	}
	var url = path+'/service/display/getPdf?candidateId='+candidateId;
	$("#editForm").attr("action",url);
	$("#candidateId").val(candidateId);
	$("#editForm").submit();
}

function loadCandidateSkillInfo(){
	var url = path+'/json/skill.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#skill").append("<option>"+item.name+"</option>");
	       })
	});
}

function loadCandidateList(pageState)
{
	var candidate = new FormData(document.getElementById("candidateForm"));
	var pageRecordsNum = $("#pageRecordsNum").find("option:selected").text();
	if(null != pageState)
	{
		candidate.append("pageState",pageState);
	}
	candidate.append("currentPage",currentPage);
	candidate.append("pageCount",pageCount);
	candidate.append("pageRecNum",pageRecordsNum);
	$.ajax({
		url:path+"/service/candidate/queryCandidateList",
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
				$("<td colspan='14' style='color: red;text-align: center;'>No Record!</td>").appendTo(tr);
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
				"<td>"+ result.data[i].skill+ "</td>" +
				"<td>"+ result.data[i].nickName+ "</td>").appendTo(tr);
				
				if(result.data[i].candidateStatus == '闲置中'){
					$("<td><a href='javascript:void(0);' class='btn btn-info btn-small' " +
						"onclick=updateResumeInfo('"+result.data[i].candidateId+"')>Edit</a>" +
						"<a href='javascript:void(0);' class='btn btn-info btn-small' " +
							"onclick=downLoadCandidateResume('"+result.data[i].candidateId+"','"+result.data[i].resumePath.replace(/\s+/g, "")+"')>Resume</a>" +
							"<a href='javascript:void(0);' class='btn btn-info btn-small' " +
							"onclick=lock('"+result.data[i].candidateId+"','"+result.data[i].resumePath.replace(/\s+/g, "")+"')>Lock</a>" +
					"</td>").appendTo(tr);
				}else{
					$("<td><a href='javascript:void(0);' class='btn btn-info btn-small' " +
							"onclick=downLoadCandidateResume('"+result.data[i].candidateId+"','"+result.data[i].resumePath.replace(/\s+/g, "")+"')>Resume</a>" +
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
			$("#pageDataCount").html(pageDataCount);
			$("#dataCount").html(dataCount);
			$("#nextPage").attr("onclick","loadCandidateList('next')");
			$("#previousPage").attr("onclick","loadCandidateList('previous')");
			$("#lastPage").attr("onclick","loadCandidateList('last')");
			$("#fristPage").attr("onclick","loadCandidateList('frist')");
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

function lock(candidateId,resumePath){
	$.ajax({
		url:path+'/service/interview/lockCandidate',
		dataType:"json",
		data:{"candidateId":candidateId},
		async:true,
		cache:false,
		type:"post",
		success:function(result){
			if(result)
			{
				$("#"+candidateId).children().eq(13).html("<a href='javascript:void(0);' class='btn btn-info btn-small' " +
							"onclick=downLoadCandidateResume('"+candidateId+"','"+resumePath+"')>Resume</a>");
			}else{
				alert('Lock unsuccessful');
			}
		}
	})
}

$("#pageRecordsNum").change(function(){
	loadCandidateList();
})
