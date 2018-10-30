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
		alert("Please select item");
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
	var url = path+'/service/candidate/exportExcel';
	$("#exceltHrefCandidate").attr("href",url);
	document.getElementById("exceltHrefCandidate").click();
	
	$('#myCandidateListModal').modal('hide');
	$("[type='checkbox']").attr("checked","checked");
}
var statusMap = {"招聘中":"0","offer中":"1","已入职":"2","闲置中":"3","暂不关注":"4","黑名单":"5","入职他司":"6"};
function updateCandidateStatus(candidateId,candidateName,candidateStatus){	
		 
	$('#updateCandidateStatusId').val(candidateId);
	$('#updateCandidateStatusName').val(candidateName);
	$("#myCandidateStatus").val(statusMap[candidateStatus]);
	$('#myCandidateStatusModal').modal('show');
};
$(document).ready(function() {  
	$('#myCandidateStatusModal').on('hide.bs.modal', function(e) {  
        $('#statusCandidateForm').bootstrapValidator('resetForm', true);   
    });
	$('#myCandidatePushModal').on('hide.bs.modal', function(e) {  
        $('#pushCandidateForm').bootstrapValidator('resetForm', true);   
    });
	$("#statusSubmit").on("click", function(){
		   var bootstrapValidator = $("#statusCandidateForm").data('bootstrapValidator');
		   bootstrapValidator.validate();
		   if(bootstrapValidator.isValid())
			   updateStatus();
		   else return;

		});
	$("#pushSubmit").on("click", function(){
		   var bootstrapValidator = $("#pushCandidateForm").data('bootstrapValidator');
		   bootstrapValidator.validate();
		   if(bootstrapValidator.isValid())
			   pushCandidateOk();
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
	        	myCandidateStatus: {
	          		validators: {
	          			 notEmpty: {
	                          message: 'Please select status'
	                      }
	                  }
	              }
	     
	        }
	    });	   
	//Verify the input information when the Candidate  is pushed 
	$('#pushCandidateForm').bootstrapValidator({
		 feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {       	
	        	csSubdeptName: {
	          		validators: {
	          			 notEmpty: {
	                          message: 'Please select department'
	                      }
	                  }
	              }
	     
	        }
	    });	   
});

function updateStatus(){
	var updateMycandidateStatus = new FormData();
	var candidateStatus = $("#myCandidateStatus").find("option:selected").val();
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
				alert("Update successfully!");				    
			}else{
				alert("Update unsuccessfully,please refresh the page and try again.");
			}
			$('#myCandidateStatusModal').modal('hide');	
			loadCandidateList();
		}
	})
}
function pushCandidateToDept(candidateId,candidateName){	
	$('#myCandidatePushModal').on('show.bs.modal', function (e) {
		loadCusDeptInfo();
		$('#pushCandidateId').val(candidateId);
		$('#pushCandidateName').val(candidateName);
	});
	$('#myCandidatePushModal').modal('show');	
};
function loadCusDeptInfo(){
	$.ajax({
		url:path+'/service/candidate/loadCusDeptInfo',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(list){
			for(var i = 0;i<list.length;i++){
				$("#csSubdeptName").append("<option value='"+list[i].csSubDeptId+"'>"+list[i].csSubDeptName+"</option>");
			}
		}
	})
}



function pushCandidateOk(){
	var csSubDeptId = $("#csSubdeptName").find("option:selected").val();	
	/*if(csSubDeptId == ''){
		alert("请选择推送部门！");
		return;
	}*/
	$("#csdeptid").val("");
	$("#csdeptid").val(csSubDeptId);
	var candidatePush = new FormData();
	candidatePush.append("csSubDeptId",csSubDeptId);
	candidatePush.append("candidateId",$("#pushCandidateId").val());
	
	$.ajax({
		url:path+'/service/candidate/pushCandidateOk',
		dataType:"json",
		data:candidatePush,
		async:true,
		cache:false,
		processData:false,
        contentType:false,
		type:"post",
		success:function(flag){
			if(flag == '5'){
			    $('#myCandidatePushModal').modal('hide');	
			    $("#issendemail2").modal('show');
				//alert("Push successfully!");
			}else if(flag == '0'){
				alert("Push unsuccessfully,please refresh the page and try again.");
			}else if(flag == '1'){
				alert("Push unsuccessfully,please refresh the page and try again.");
			}else if(flag == '2'){
				alert("Push unsuccessfully,please refresh the page and try again.");
			}else if(flag == '3'){
				alert("Push unsuccessfully,please refresh the page and try again.");
			}else if(flag == '4'){
				alert("Push unsuccessfully,please refresh the page and try again.");
			}
			loadCandidateList();
		}
	})
}

function backCandidateToDept(candidateId){
	if(confirm("Are you sure to withdraw the candidate?"))
	{
		$.ajax({
			url:path+'/service/candidate/backCandidateToDept',
			dataType:"json",
			data:{"candidateId":candidateId},
			async:true,
			cache:false,
			type:"post",
			success:function(flag){
				if(flag == '3'){
					alert("Withdraw successfully");
				}else if(flag == '0'){
					alert("Withdraw unsuccessfully,please refresh the page and try again.");
				}else if(flag == '1'){
					alert("Withdraw unsuccessfully,please refresh the page and try again.");
				}else if(flag == '2'){
					alert("Withdraw unsuccessfully,please refresh the page and try again.");
				}	
				loadCandidateList();
			}
		})
	}
}



function updateResumeInfo(candidateId){
	$("#editForm").attr("action",path+"/service/resume/toUpdateResume");
//	alert(candidateId);
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

function loadCandidateSkillInfo(){
	var url = path+'/json/skill.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#skill").append("<option>"+item.name+"</option>");
	       })
	});
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
		url:path+"/service/candidate/queryMyCandidateList",
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
				$("<td colspan='15' style='color: red;text-align: center;'>No record!</td>").appendTo(tr);
			}else{
				$("#exportCandidateExcel").removeAttr("disabled");
			}
			for (var i = 0; i < result.data.length; i++) {
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);
				$("<td><a href='javascript:void(0);' " +
				"onclick=displayPDF('"+result.data[i].candidateId+"','"+result.data[i].resumePath.replace(/\s+/g, "")+"')>"+ result.data[i].candidateName+"</a></td>"+
				"<td>"+ result.data[i].candidateSex+ "</td>"+
				"<td>"+ result.data[i].candidateAge+ "</td>"+
				"<td>"+ result.data[i].candidateTel+ "</td>"+
				"<td>"+ result.data[i].email+ "</td>"+
				"<td>"+ result.data[i].source+ "</td>"+
				"<td>"+ result.data[i].candidateStatus+ "</td>"+
				"<td>"+ result.data[i].education+ "</td>"+
				"<td>"+ result.data[i].experienceYears+ "</td>"+
				"<td>"+ result.data[i].majorStatus+ "</td>"+
				"<td>"+ result.data[i].englishLevel+ "</td>"+
				"<td>"+ result.data[i].skill+ "</td>"+
				"<td>"+ result.data[i].interviewStatus+ "</td>").appendTo(tr);
				if(result.data[i].candidateStatus == '招聘中'){

					if(result.data[i].interviewStatus == '未推送' || result.data[i].interviewStatus == '已退回' || result.data[i].interviewStatus == '5'){

						$("<td></td>"+
							"<td><a href='javascript:void(0);' class='btn btn-info btn-small' " +
								"onclick=updateResumeInfo('"+result.data[i].candidateId+"')>Edit</a>"+
							"<a href='javascript:void(0);' class='btn btn-info btn-small' " +
								"onclick=pushCandidateToDept('"+result.data[i].candidateId+"','"+result.data[i].candidateName+"')>Push</a>" +
							"<a href='javascript:void(0);' class='btn btn-info btn-small'  " +
								"onclick=updateCandidateStatus('"+result.data[i].candidateId+"','"+result.data[i].candidateName+"','"+result.data[i].candidateStatus+"')>Status</a>" +
								"<a href='javascript:void(0); ' class='btn btn-info btn-small' " +
								"onclick=feedbackCandidateInfo('"+result.data[i].candidateId+"','"+result.data[i].candidateName+"')>FeedBack</a>" +
								"<a href='javascript:void(0);' class='btn btn-info btn-small' " +
								"onclick=downLoadCandidateResume('"+result.data[i].candidateId+"','"+result.data[i].resumePath.replace(/\s+/g, "")+"')>Resume</a>" +
						"</td>").appendTo(tr);
					}else if(result.data[i].interviewStatus == '已推送'){
						$("<td>"+ result.data[i].csSubdeptName+ "</td>"+
							"<td><a href='javascript:void(0);' class='btn btn-info btn-small' " +
								"onclick=updateResumeInfo('"+result.data[i].candidateId+"')>Edit</a>"+
							"<a href='javascript:void(0);' class='btn btn-info btn-small' " +
								"onclick=backCandidateToDept('"+result.data[i].candidateId+"')>Back</a>" +
							"<a href='javascript:void(0); ' class='btn btn-info btn-small' " +
								"onclick=feedbackCandidateInfo('"+result.data[i].candidateId+"','"+result.data[i].candidateName+"')>FeedBack</a>" +
							"<a href='javascript:void(0);' class='btn btn-info btn-small' " +
								"onclick=downLoadCandidateResume('"+result.data[i].candidateId+"','"+result.data[i].resumePath.replace(/\s+/g, "")+"')>Resume</a>" +
						"</td>").appendTo(tr);
					}else if(result.data[i].interviewStatus == '面试确认'){
						$("<td>"+ result.data[i].csSubdeptName+ "</td>"+
								"<td><a href='javascript:void(0);' class='btn btn-info btn-small' " +
									"onclick=updateResumeInfo('"+result.data[i].candidateId+"')>Edit</a>"+
								 "<a href='javascript:void(0); ' class='btn btn-info btn-small' id = 'reconfirm'" +
									"onclick=interviewConfirm('"+result.data[i].candidateId+"','"+result.data[i].candidateName+"','"+result.data[i].csSubdeptName+"')>Reconfirm</a>" +
								"<a href='javascript:void(0);' class='btn btn-info btn-small' " +
									"onclick=backCandidateToDept('"+result.data[i].candidateId+"')>Back</a>" +
								"<a href='javascript:void(0); ' class='btn btn-info btn-small' " +
									"onclick=feedbackCandidateInfo('"+result.data[i].candidateId+"','"+result.data[i].candidateName+"')>FeedBack</a>" +
								"<a href='javascript:void(0);' class='btn btn-info btn-small' " +
									"onclick=downLoadCandidateResume('"+result.data[i].candidateId+"','"+result.data[i].resumePath.replace(/\s+/g, "")+"')>Resume</a>" +
							"</td>").appendTo(tr);
						}else{
						$("<td>"+ result.data[i].csSubdeptName+ "</td>"+
								"<td><a href='javascript:void(0);' class='btn btn-info btn-small' " +
									"onclick=updateResumeInfo('"+result.data[i].candidateId+"')>Edit</a>"+
								"<a href='javascript:void(0);' class='btn btn-info btn-small' " +
									"onclick=backCandidateToDept('"+result.data[i].candidateId+"')>Back</a>" +
								"<a href='javascript:void(0); ' class='btn btn-info btn-small' " +
									"onclick=feedbackCandidateInfo('"+result.data[i].candidateId+"','"+result.data[i].candidateName+"')>FeedBack</a>" +
								"<a href='javascript:void(0);' class='btn btn-info btn-small' " +
									"onclick=downLoadCandidateResume('"+result.data[i].candidateId+"','"+result.data[i].resumePath.replace(/\s+/g, "")+"')>Resume</a>" +
							"</td>").appendTo(tr);
					}
				}else{
					if(result.data[i].interviewStatus == '未推送' || result.data[i].interviewStatus == '已退回' || result.data[i].interviewStatus == '5'){
						$("<td>"+ result.data[i].csSubdeptName+ "</td>"+
								"<td><a href='javascript:void(0);' class='btn btn-info btn-small' " +
										"onclick=updateResumeInfo('"+result.data[i].candidateId+"')>Edit</a>"+
									"<a href='javascript:void(0);' class='btn btn-info btn-small' " +					
										"onclick=updateCandidateStatus('"+result.data[i].candidateId+"','"+result.data[i].candidateName+"','"+result.data[i].candidateStatus+"')>Status</a>" +
									"<a href='javascript:void(0); ' class='btn btn-info btn-small' " +
										"onclick=feedbackCandidateInfo('"+result.data[i].candidateId+"','"+result.data[i].candidateName+"')>FeedBack</a>" +
								"<a href='javascript:void(0);' class='btn btn-info btn-small' " +
									"onclick=downLoadCandidateResume('"+result.data[i].candidateId+"','"+result.data[i].resumePath.replace(/\s+/g, "")+"')>Resume</a>" +
							"</td>").appendTo(tr);
					}else if(result.data[i].interviewStatus == '面试确认'){
						$("<td>"+ result.data[i].csSubdeptName+ "</td>"+
								"<td><a href='javascript:void(0);' class='btn btn-info btn-small' " +
										"onclick=updateResumeInfo('"+result.data[i].candidateId+"')>Edit</a>"+
								    "<a href='javascript:void(0); ' class='btn btn-info btn-small' id = 'reconfirm' " +
										"onclick=interviewConfirm('"+result.data[i].candidateId+"','"+result.data[i].candidateName+"','"+result.data[i].csSubdeptName+"')>Reconfirm</a>" +
									//"<a href='javascript:void(0);' class='btn btn-info btn-small' " +					
										//"onclick=updateCandidateStatus('"+result.data[i].candidateId+"','"+result.data[i].candidateName+"','"+result.data[i].candidateStatus+"')>Status</a>" +
									"<a href='javascript:void(0); ' class='btn btn-info btn-small' " +
										"onclick=feedbackCandidateInfo('"+result.data[i].candidateId+"','"+result.data[i].candidateName+"')>FeedBack</a>" +
								"<a href='javascript:void(0);' class='btn btn-info btn-small' " +
									"onclick=downLoadCandidateResume('"+result.data[i].candidateId+"','"+result.data[i].resumePath.replace(/\s+/g, "")+"')>Resume</a>" +
							"</td>").appendTo(tr);
					}else{
						$("<td>"+ result.data[i].csSubdeptName+ "</td>"+
								"<td><a href='javascript:void(0);' class='btn btn-info btn-small' " +
										"onclick=updateResumeInfo('"+result.data[i].candidateId+"')>Edit</a>"+
									"<a href='javascript:void(0);' class='btn btn-info btn-small' " +					
										"onclick=feedbackCandidateInfo('"+result.data[i].candidateId+"','"+result.data[i].candidateName+"')>FeedBack</a>" +
								"<a href='javascript:void(0);' class='btn btn-info btn-small' " +
									"onclick=downLoadCandidateResume('"+result.data[i].candidateId+"','"+result.data[i].resumePath.replace(/\s+/g, "")+"')>Resume</a>" +
							"</td>").appendTo(tr);
					}
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

$("#pageRecordsNum").change(function(){
	loadCandidateList();
})

//获取RM
function getRm2(){
	var csdeptid = $("#csdeptid").val();
	$.ajax({
		url:path+'/service/user/getRM',
		dataType:"json",
		data:{"csdeptid":csdeptid},
		async:true,
		cache:false,
		type:"get",
		success:function(result){
			if(result){
				if(result != null && result.length>0){
					$("#hrdatatable2 tbody").remove();
					var tbody = $("<tbody>");
					tbody.appendTo($("#hrdatatable2"));
					for(var i=0;i<result.length;i++){
						$("<tr>" +
								"<td><input value='"+result[i].userName+"' type='checkbox' name='hremail2'/></td>"+
								"<td>"+result[i].userName+"</td>" +
								"<td>"+result[i].nickname+"</td>" +
								"<td>"+result[i].userType+"</td>" +
								"<td>"+result[i].email+"</td>" +
								"</tr>").appendTo(tbody);
					}
					$("#rmlist2").modal('show');
					//隐藏掉是否发送邮件的提示
					$("#issendemail2").modal('hide');
				}
				
			}
		}
	})
	
}

//发送邮件
function sendemail2(){
	$("#rmlist2").modal('hide');
	$("#jindu").modal('show');
	obj = document.getElementsByName("hremail2");
    ehs = [];
    for(k in obj){
        if(obj[k].checked)
        	ehs.push(obj[k].value);
    }
	
	$.ajax({
		url:path+'/service/sendemail/send5',
		dataType:"json",
		data:{ehr:JSON.stringify(ehs)},
		async:true,
		cache:false,
		type:"post",
		success:function(result){
			if(result){
				$("#jindu").modal('hide');
				alert("发送成功");
				$("#rmlist2").modal('hide');
			}
		}
	})
	
}

function selAll2(){
	var all=document.getElementById('checkAll2');//获取到点击全选的那个复选框的id  
	var one=document.getElementsByName('hremail2');//获取到复选框的名称  
	if(all.checked==true){
		for(var i=0;i<one.length;i++){ 
			if(one[i].checked==false){
				one[i].checked=true; 
			}
		} 
	}else{
		for(var i=0;i<one.length;i++){  
			one[i].checked=false;
		} 
	}
}