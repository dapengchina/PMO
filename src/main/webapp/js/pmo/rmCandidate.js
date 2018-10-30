$(function() {
	loadMyCandidate();
	dateType();
})

function loadMyCandidate(currPage) {
	$("#rmCandidateList  tr:not(:first)").html("");
	var pageRecordsNum = $("#pageRecordsNum").find("option:selected").text();
	var candidateNames = $("#candidateNames").val();
	var tel = $("#tel").val();
	$.ajax({
				url : path + '/service/rmCandidate/getMyCandidate',
				dateType : 'json',
				type : 'post',
				data : {
					"currPage" : currPage,
					"pageRecNum":pageRecordsNum,
					"name":candidateNames,
					"tel":tel
				},
				success : function(result) {
					if (result.candidatelist.length <= 0) {
						$("#rmCandidateList")
								.append(
										"<tr><td colspan='13' style='text-align:center'>No record!</td></tr>");
						return;
					}
					// $.each(result,function(candidatePush){
					for (var i = 0; i < result.candidatelist.length; i++) {
						// alert(result.candidatelist[i].candidateInfo.candidateId);
						var tr = $("<tr id='" + result.candidatelist[i].pushId
								+ "'></tr>");
						var td1 = $("<td><a href='javascript:void(0);' "
								+ "onclick=displayPDF('"
								+ result.candidatelist[i].candidateInfo.candidateId
								+ "')>"
								+ result.candidatelist[i].candidateInfo.candidateName
								+ "</a></td>");
						var tmp = result.candidatelist[i].interviewList[0].interviewId;
						// alert("tmp:"+tmp+",status:"+result.candidatelist[i].candidateInfo.interviewStatus);
						var interviewStatus = result.candidatelist[i].candidateInfo.interviewStatus;
						if (interviewStatus == '1') {
							var td2 = $("<td>未安排面试</td>");
						} else if (interviewStatus == '3') {
							var td2 = $("<td>面试通过</td>");
						} else if (interviewStatus == '4') {
							var td2 = $("<td>面试失败</td>");
						} else if (interviewStatus == '2') {
							var td2 = $("<td>面试中</td>");
						} else if (interviewStatus == '6') {
							var td2 = $("<td>面试确认</td>");
						} else if (interviewStatus == '7') {
							var td2 = $("<td>重安排面试</td>");
						} else {
							var td2 = $("<td></td>");
						}

						if (result.candidatelist[i].candidateInfo.candidateSex == '0') {
							var td3 = $("<td>男</td>");
						} else {
							var td3 = $("<td>女</td>");
						}
						var td4 = $("<td>"
								+ result.candidatelist[i].candidateInfo.candidateAge
								+ "</td>");
						var td5 = $("<td>"
								+ result.candidatelist[i].candidateInfo.skill
								+ "</td>");
						/*if (result.candidatelist[i].candidateInfo.education == '0') {
							var td6 = $("<td>博士</td>");
						} else if (result.candidatelist[i].candidateInfo.education == '1') {
							var td6 = $("<td>硕士</td>");
						} else if (result.candidatelist[i].candidateInfo.education == '2') {
							var td6 = $("<td>学士</td>");
						} else if (result.candidatelist[i].candidateInfo.education == '3') {
							var td6 = $("<td>专科</td>");
						} else if (result.candidatelist[i].candidateInfo.education == '4') {
							var td6 = $("<td>高中</td>");
						}*/
						// var td6 =
						// $("<td>"+result.candidatelist[i].candidateInfo.education+"</td>");
						var td6 = $("<td>"
								+ result.candidatelist[i].candidateInfo.experienceYears
								+ "</td>");
						var td7 = $("<td>"
								+ result.candidatelist[i].candidateInfo.candidateTel
								+ "</td>");
						var td8 = $("<td>"
								+ result.candidatelist[i].candidateInfo.email
								+ "</td>");
						var td9 = $("<td>"
								+ result.candidatelist[i].csDept.csSubDeptName
								+ "</td>");
						if (interviewStatus == 7) {
							var td10 = $("<td> <a href='javascript:void(0);' "
									+ "onclick=confirmContent('"
									+ result.candidatelist[i].candidateInfo.candidateId
									+ "','"+result.candidatelist[i].candidateInfo.candidateName+"')>"+result.candidatelist[i].user.nickname+ "</a></td>");
						}else{
						var td10 = $("<td>"
								+ result.candidatelist[i].user.nickname
								+ "</td>");
						}
						var status = result.candidatelist[i].interviewList[0].interviewId;
						if (interviewStatus == 1) {
							//var cid1 = result.candidatelist[i].candidateInfo.candidateId;
							//alert(cid1);
							var td11 = $("<td><a href='javascript:void(0);' id='"
									+ result.candidatelist[i].pushId
									+ "' name='"
									+ result.candidatelist[i].interviewList[0].projectName
									+ "' class='btn btn-info btn-small' onclick='scheduleInterview(this.id)'>New Turn</a>&nbsp;"
									+ "<a href='javascript:void(0);' disabled='disabled' id='"
									+ result.candidatelist[i].pushId
									+ "' name='"
									+ result.candidatelist[i].interviewList[0].projectName
									+ "' class='btn btn-info btn-small ' onclick='nextInterview(this.id,this.name)'>Next Interview</a>&nbsp;"
									+ "<a href='javascript:void(0);' id='"
									+ result.candidatelist[i].pushId
									+ "' name='"
									+ result.candidatelist[i].candidateInfo.candidateId
									+ "' class='btn btn-info btn-small' onclick='interviewBack(this.id,this.name)'>Back</a>&nbsp;"
									+ "<a href='javascript:void(0);' disabled='disabled' id='"
									+ result.candidatelist[i].pushId
									+ "' name='"
									+ result.candidatelist[i].candidateInfo.candidateId
									+ "' class='btn btn-info btn-small '  onclick='offerInterview(this.id)'>Offer</a>&nbsp;" 
									+ "<a href='javascript:void(0);' disabled='disabled' id='"
									+ result.candidatelist[i].pushId
									+ "' name='"
									+ result.candidatelist[i].candidateInfo.candidateId
									+ "' class='btn btn-info btn-small ' onclick='rescheduleInterview(this.id,this.name)'>Reschedualing</a>&nbsp;"
									+		"</td>");
						} else if (interviewStatus == 2) {
							var td11 = $("<td><a href='javascript:void(0);' disabled='disabled' id='"
									+ result.candidatelist[i].pushId
									+ "' name='"
									+ result.candidatelist[i].interviewList[0].projectName
									+ "' class='btn btn-info btn-small ' onclick='scheduleInterview(this.id)'>New Turn</a>&nbsp;"
									+ "<a href='javascript:void(0);' disabled='disabled' id='"
									+ result.candidatelist[i].pushId
									+ "' name='"
									+ result.candidatelist[i].interviewList[0].projectName
									+ "' class='btn btn-info btn-small' onclick='nextInterview(this.id,this.name)'>Next Interview</a>&nbsp;"
									+ "<a href='javascript:void(0);' disabled='disabled' id='"
									+ result.candidatelist[i].pushId
									+ "' name='"
									+ result.candidatelist[i].candidateInfo.candidateId
									+ "' class='btn btn-info btn-small' onclick='interviewBack(this.id,this.name)'>Back</a>&nbsp;"
									+ "<a href='javascript:void(0);' disabled='disabled' id='"
									+ result.candidatelist[i].pushId
									+ "' name='"
									+ result.candidatelist[i].candidateInfo.candidateId
									+ "' class='btn btn-info btn-small' onclick='offerInterview(this.id)'>Offer</a>"
									+ "<a href='javascript:void(0);' id='"
									+ result.candidatelist[i].pushId
									+ "' name='"
									+ result.candidatelist[i].candidateInfo.candidateId
									+ "' class='btn btn-info btn-small ' onclick='rescheduleInterview(this.id,this.name)'>Reschedualing</a>&nbsp;"
									+		"</td>");
						} else if (interviewStatus == 3) {
							var td11 = $("<td><a href='javascript:void(0);' id='"
									+ result.candidatelist[i].pushId
									+ "' name='"
									+ result.candidatelist[i].interviewList[0].projectName
									+ "' class='btn btn-info btn-small ' onclick='scheduleInterview(this.id)'>New Turn</a>&nbsp;"
									+ "<a href='javascript:void(0);' id='"
									+ result.candidatelist[i].pushId
									+ "' name='"
									+ result.candidatelist[i].interviewList[0].projectName
									+ "' class='btn btn-info btn-small' onclick='nextInterview(this.id,this.name)'>Next Interview</a>&nbsp;"
									+ "<a href='javascript:void(0);' id='"
									+ result.candidatelist[i].pushId
									+ "' name='"
									+ result.candidatelist[i].candidateInfo.candidateId
									+ "' class='btn btn-info btn-small' onclick='interviewBack(this.id,this.name)'>Back</a>&nbsp;"
									+ "<a href='javascript:void(0);' id='"
									+ result.candidatelist[i].pushId
									+ "' name='"
									+ result.candidatelist[i].candidateInfo.candidateId
									+ "' class='btn btn-info btn-small' onclick='offerInterview(this.id)'>Offer</a>"
									+ "<a href='javascript:void(0);' disabled='disabled' id='"
									+ result.candidatelist[i].pushId
									+ "' name='"
									+ result.candidatelist[i].candidateInfo.candidateId
									+ "' class='btn btn-info btn-small ' onclick='rescheduleInterview(this.id,this.name)'>Reschedualing</a>&nbsp;"
									+		"</td>");
						} else if (interviewStatus == 4) {
							var td11 = $("<td><a href='javascript:void(0);' id='"
									+ result.candidatelist[i].pushId
									+ "' name='"
									+ result.candidatelist[i].interviewList[0].projectName
									+ "' class='btn btn-info btn-small ' onclick='scheduleInterview(this.id)'>New Turn</a>&nbsp;"
									+ "<a href='javascript:void(0);' disabled='disabled' id='"
									+ result.candidatelist[i].pushId
									+ "' name='"
									+ result.candidatelist[i].interviewList[0].projectName
									+ "' class='btn btn-info btn-small' onclick='nextInterview(this.id,this.name)'>Next Interview</a>&nbsp;"
									+ "<a href='javascript:void(0);' id='"
									+ result.candidatelist[i].pushId
									+ "' name='"
									+ result.candidatelist[i].candidateInfo.candidateId
									+ "' class='btn btn-info btn-small' onclick='interviewBack(this.id,this.name)'>Back</a>&nbsp;"
									+ "<a href='javascript:void(0);' disabled='disabled' id='"
									+ result.candidatelist[i].pushId
									+ "' name='"
									+ result.candidatelist[i].candidateInfo.candidateId
									+ "' class='btn btn-info btn-small' onclick='offerInterview(this.id)'>Offer</a>"
									+ "<a href='javascript:void(0);' disabled='disabled' id='"
									+ result.candidatelist[i].pushId
									+ "' name='"
									+ result.candidatelist[i].candidateInfo.candidateId
									+ "' class='btn btn-info btn-small ' onclick='rescheduleInterview(this.id,this.name)'>Reschedualing</a>&nbsp;"
									+		"</td>");
						}else if(interviewStatus==6){
							var td11 = $("<td><a href='javascript:void(0);'disabled='disabled' id='"
									+ result.candidatelist[i].pushId
									+ "' name='"
									+ result.candidatelist[i].interviewList[0].projectName
									+ "' class='btn btn-info btn-small ' onclick='scheduleInterview(this.id)'>New Turn</a>&nbsp;"
									+ "<a href='javascript:void(0);' disabled='disabled' id='"
									+ result.candidatelist[i].pushId
									+ "' name='"
									+ result.candidatelist[i].interviewList[0].projectName
									+ "' class='btn btn-info btn-small' onclick='nextInterview(this.id,this.name)'>Next Interview</a>&nbsp;"
									+ "<a href='javascript:void(0);'disabled='disabled' id='"
									+ result.candidatelist[i].pushId
									+ "' name='"
									+ result.candidatelist[i].candidateInfo.candidateId
									+ "' class='btn btn-info btn-small' onclick='interviewBack(this.id,this.name)'>Back</a>&nbsp;"
									+ "<a href='javascript:void(0);' disabled='disabled' id='"
									+ result.candidatelist[i].pushId
									+ "' name='"
									+ result.candidatelist[i].candidateInfo.candidateId
									+ "' class='btn btn-info btn-small' onclick='offerInterview(this.id)'>Offer</a>"
									+ "<a href='javascript:void(0);' disabled='disabled' id='"
									+ result.candidatelist[i].pushId
									+ "' name='"
									+ result.candidatelist[i].candidateInfo.candidateId
									+ "' class='btn btn-info btn-small ' onclick='rescheduleInterview(this.id,this.name)'>Reschedualing</a>&nbsp;"
									+		"</td>");
							
						}else if(interviewStatus==7){
							var td11 = $("<td><a href='javascript:void(0);'disabled='disabled' id='"
									+ result.candidatelist[i].pushId
									+ "' name='"
									+ result.candidatelist[i].interviewList[0].projectName
									+ "' class='btn btn-info btn-small ' onclick='scheduleInterview(this.id)'>New Turn</a>&nbsp;"
									+ "<a href='javascript:void(0);' disabled='disabled' id='"
									+ result.candidatelist[i].pushId
									+ "' name='"
									+ result.candidatelist[i].interviewList[0].projectName
									+ "' class='btn btn-info btn-small' onclick='nextInterview(this.id,this.name)'>Next Interview</a>&nbsp;"
									+ "<a href='javascript:void(0);' id='"
									+ result.candidatelist[i].pushId
									+ "' name='"
									+ result.candidatelist[i].candidateInfo.candidateId
									+ "' class='btn btn-info btn-small' onclick='interviewBack(this.id,this.name)'>Back</a>&nbsp;"
									+ "<a href='javascript:void(0);' disabled='disabled' id='"
									+ result.candidatelist[i].pushId
									+ "' name='"
									+ result.candidatelist[i].candidateInfo.candidateId
									+ "' class='btn btn-info btn-small' onclick='offerInterview(this.id)'>Offer</a>"
									+ "<a href='javascript:void(0);' id='"
									+ result.candidatelist[i].pushId
									+ "' name='"
									+ result.candidatelist[i].candidateInfo.candidateId
									+ "' class='btn btn-info btn-small ' onclick='rescheduleInterview(this.id,this.name)'>Reschedualing</a>&nbsp;"
									+		"</td>");
						}

						td1.appendTo(tr);
						td2.appendTo(tr);
						td3.appendTo(tr);
						td4.appendTo(tr);
						td5.appendTo(tr);
						//td6.appendTo(tr);
						td6.appendTo(tr);
						td7.appendTo(tr);
						td8.appendTo(tr);
						td9.appendTo(tr);
						td10.appendTo(tr);
						td11.appendTo(tr);
						$("#rmCandidateList").append(tr);
					}
					$("#pageCount").html(result.pageCondition.totalPage);
					$("#currentPage").html(result.pageCondition.currPage);
					$("#fristPage").attr("onclick", "loadMyCandidate(1)");
					if (result.pageCondition.currPage <= result.pageCondition.totalPage) {
						$("#previousPage").attr(
								"onclick",
								"loadMyCandidate("
										+ (result.pageCondition.currPage - 1)
										+ ")");
						$("#nextPage").attr(
								"onclick",
								"loadMyCandidate("
										+ (result.pageCondition.currPage + 1)
										+ ")");
						$("#lastPage").attr(
								"onclick",
								"loadMyCandidate("
										+ (result.pageCondition.totalPage)
										+ ")");
					}
					if (result.pageCondition.currPage == result.pageCondition.totalPage) {
						$("#nextPage").parent("li").addClass("disabled");
						$("#nextPage").removeAttr('onclick');
						$("#lastPage").parent("li").addClass("disabled");
						$("#lastPage").removeAttr('onclick');
						$("#fristPage").parent("li").removeClass("disabled");
						$("#previousPage").parent("li").removeClass("disabled");
					}
					if (result.pageCondition.currPage == 1) {
						$("#fristPage").parent("li").addClass("disabled");
						$("#fristPage").removeAttr('onclick');
						$("#previousPage").parent("li").addClass("disabled");
						$("#previousPage").removeAttr('onclick');
						$("#nextPage").parent("li").removeClass("disabled");
						$("#lastPage").parent("li").removeClass("disabled");
					}
					$("ul.pagination-centered li a")
							.each(
									function() {
										if (1 < result.pageCondition.currPage
												&& result.pageCondition.currPage < result.pageCondition.totalPage) {
											$(this).parent("li").siblings("li")
													.removeClass("disabled");
										}
									});
				}
			})
}

// gkf
function displayPDF(candidateId) {
	var url = path + '/service/display/getPdf?candidateId=' + candidateId;
	$("#resumeForm").attr("action", url);
	$("#candidateId").val(candidateId);
	$("#resumeForm").submit();
}

function confirmContent(candidateId,candidateName){
	$("#confirmBox").modal('show');
	$('#confirmForm').bootstrapValidator({
		message: 'This value is not valid',

        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	confirmInfo: {
                validators: {
                    notEmpty: {
                        message: 'Please enter Confirm Information'
                    }
                }
            }
        }
    });
	var bootstrapValidator = $("#confirmForm").data('bootstrapValidator');
	bootstrapValidator.validate();
	if(!bootstrapValidator.isValid()){
		return;
	}
		$.ajax({
			url : path + '/service/rmCandidate/getConfirminfo',
			dataType : "json",
			async : true,
			cache : false,
			type : "post",
			data : {
				"candidateId" : candidateId
			},
			success : function(data) {
			  $("#candidateName").val(candidateName);
			  $("#confirmInfo").val(data.mark);
			}
		})
}

function dateType() {
	$('.form_datetime').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		language : 'zh-CN',
		format : 'yyyy-mm-dd hh:ii:00',
		pickerPosition : 'bottom-left',
		showMeridian : 1
	});
}
/*重新安排面试*/

function rescheduleInterview(pushId,candidateId) {
	
	$("#graduationDate1").val("");
	$("#interviewer").val("");
	$('#projectName').val("");
	$('#interviewType').val("");
	if($("#graduationDate1").val()==''||$("#interviewer").val()==''||$('#projectName').val()==''||$('#interviewType').val()==''){
		 $('#addInterviewer').attr("disabled", "disabled");
	}
	
	/*上次面试信息回显*/
	$.ajax({
		url:path + '/service/rmCandidate/getIntervieInfo',
	    async : true,
	    cache : false,
	    type  : "get",
	    data  : {
			"candidateId" : candidateId
		},
		success : function(result) {
			if(result!=null||result!=null){
				$('#myModal').modal('show');
				var interviewerId=result.interviewerId;
		    	$('#graduationDate1').val(result.interviewDate);
				$('#projectName').val(result.projectName);
				$("#interviewType").val(result.interviewType);
				$('#interviewer').val(result.interviewerId);
				loadInterviewer(pushId,interviewerId);
				
				$("#puid").val();
				$("#puid").val(pushId);

				$("#addInterviewer").click(function() {
					var interviewDate = $("#graduationDate1").val();
					var interviewerId = $("#interviewer").val();
					var projectName = $('#projectName').val();
					var interviewType = $('#interviewType').val();
					var interviewId=result.interviewId;
					var candidateId=result.candidateId;
					$.ajax({
						url : path + '/service/rmCandidate/updateInterview',
						dataType : "json",
						async : true,
						cache : false,
						type : "post",
						data : {
							"candidateId" : candidateId,
							"interviewId" : interviewId,
							"interviewDate" : interviewDate,
							"interviewerId" : interviewerId,
							"interviewType" : interviewType,
							"projectName" : projectName
						},
						success : function(data) {
							if (data) {
								$('#myModal').modal('hide');
								$("#issendemail").modal('show');
								// BootstrapDialog.alert('安排面试成功!');
								BootstrapDialog.show({
									title : 'Interview arrangement',
									message : 'Arrange successfully!',
									size : BootstrapDialog.SIZE_NORMAL,
									buttons : [ {
										label : 'Confirm',
										action : function(dialog) {
											loadMyCandidate();
											dialog.close();
										}
									} ]
								});
							} else {
								BootstrapDialog.alert('Arrange unsuccessfully!');
							}
						}
					})
					$('#graduationDate1').val("");
					$('#projectName').val("");
					$("#interviewer").val("");
					$('#interviewType').val("");
				})
			}
			/*	
				$.ajax({
					url : path + '/service/rmCandidate/updateInterview',
					dataType : "json",
					async : true,
					cache : false,
					type : "post",
					data : {
						"pushId" : pushId,
						"interviewDate" : interviewDate,
						"interviewerId" : interviewerId,
						"interviewType" : interviewType,
						"projectName" : projectName
					},
					success : function(data) {
						if (data == "1") {
							$('#myModal').modal('hide');
							// BootstrapDialog.alert('安排面试成功!');
							BootstrapDialog.show({
								title : '面试安排',
								message : '新一轮面试安排成功!',
								size : BootstrapDialog.SIZE_NORMAL,
								buttons : [ {
									label : '确认',
									action : function(dialog) {
										loadMyCandidate();
										dialog.close();
									}
								} ]
							});
						} else {
							BootstrapDialog.alert('安排面试失败!');
						}
					}
				})*/
			}
		
		})
	
	
	
	
}
/* 新一轮面试 */
function scheduleInterview(pushId) {
	//alert("dd"+cid);
	$('#myModal').modal('show');

	$("#graduationDate1").val("");
	$("#interviewer").val("");
	$('#projectName').val("");
	$('#interviewType').val("");
	 	
	if($("#graduationDate1").val()==''||$("#interviewer").val()==''||$('#projectName').val()==''||$('#interviewType').val()==''){
	 	$('#addInterviewer').attr("disabled", "disabled");
	 }
	

	loadInterviewer(pushId,"");
	$("#puid").val();
	$("#puid").val(pushId);

	$("#addInterviewer").click(function() {
		var interviewDate = $("#interviewDate").val();
		var interviewerId = $("#interviewer").val();
		var projectName = $('#projectName').val();
		var interviewType = $('#interviewType').val();
		$.ajax({
			url : path + '/service/rmCandidate/addInterview',
			dataType : "json",
			async : true,
			cache : false,
			type : "post",
			data : {
				"pushId" : pushId,
				"interviewDate" : interviewDate,
				"interviewerId" : interviewerId,
				"interviewType" : interviewType,
				"projectName" : projectName
			},
			success : function(data) {
				if (data == "1") {
					$('#myModal').modal('hide');
					$("#issendemail").modal('show');
					// BootstrapDialog.alert('安排面试成功!');
					BootstrapDialog.show({
						title : 'Interview arrangement',
						message : 'Arrange successfully!',
						size : BootstrapDialog.SIZE_NORMAL,
						buttons : [ {
							label : 'Confirm',
							action : function(dialog) {
								loadMyCandidate();
								dialog.close();
							}
						} ]
					});
				} else {
					BootstrapDialog.alert('Arrange unsuccessfully!');
				}
			}
		})
		$('#graduationDate1').val("");
		$('#projectName').val("");
		$("#interviewer").val("");
		$('#interviewType').val("");
	})
}


/* 下一次面试 */
function nextInterview(pushId, projectName) {
	$('#projectName').val(projectName);
	$('#projectName').attr("disabled", "disabled");
	$('#myModal').modal('show');

	$("#graduationDate1").val("");
	$("#interviewer").val("");
	$('#interviewType').val("");
	
	
	if($("#graduationDate1").val()==''||$("#interviewer").val()==''||$('#projectName').val()==''||$('#interviewType').val()==''){
		$('#addInterviewer').attr("disabled", "disabled");
	}
	

	loadInterviewer(pushId,"");
	
	$("#puid").val();
	$("#puid").val(pushId);

	$("#addInterviewer").click(function() {
		var interviewDate = $("#interviewDate").val();
		var interviewerId = $("#interviewer").val();
		var projectName = $("#projectName").val();
		var interviewType = $("#interviewType").val();

		$.ajax({
			url : path + '/service/rmCandidate/addNextInterview',
			dataType : "json",
			async : true,
			cache : false,
			type : "post",
			data : {
				"pushId" : pushId,
				"interviewDate" : interviewDate,
				"interviewerId" : interviewerId,
				"projectName" : projectName,
				"interviewType" : interviewType
			},
			success : function(data) {
				if (data == "1") {
					$('#myModal').modal('hide');
					$("#issendemail").modal('show');
					BootstrapDialog.show({
						title : 'Interview arrangement',
						message : 'Arrange successfully!',
						size : BootstrapDialog.SIZE_NORMAL,
						buttons : [ {
							label : 'Confirm',
							action : function(dialog) {
								loadMyCandidate();
								dialog.close();
							}
						} ]
					});
				} else {
					BootstrapDialog.alert('Arrange unsuccessfully!');
				}
			}
		})
		$('#graduationDate1').val("");
		$("#interviewer").val("");
		$("#interviewType").val("");
		$("#projectName").val("");
	})
}
/* 退回 */
function interviewBack(pushId, candidateId) {
	/*
	 * BootstrapDialog.show({ title: 'Default Title', message: '确认退回？', size:
	 * BootstrapDialog.SIZE_NORMAL });
	 */

	// alert(pushId+","+candidateId);
	BootstrapDialog.show({
		title : 'Interview arrangement',
		message : 'Are you sure to withdraw the candidate?',
		size : BootstrapDialog.SIZE_NORMAL,
		buttons : [ {
			label : 'Confirm',
			action : function(dialog) {
				$.ajax({
					url : path + '/service/rmCandidate/interviewBack',
					dataType : "json",
					async : true,
					cache : false,
					type : "post",
					data : {
						"pushId" : pushId,
						"candidateId" : candidateId
					},
					success : function(data) {
						loadMyCandidate();
					}
				});
				dialog.close();
			}
		}, {
			label : 'Cancel',
			action : function(dialog) {
				dialog.close();
			}
		} ]
	});

}

function loadInterviewer(pushId,interviewerId) {
	$("#interviewer").empty();
	$("#interviewer").append("<option value=''>--Option--</option>");
	$.ajax({
		url : path + '/service/rmCandidate/loadInterviewer',
		dataType : "json",
		async : true,
		data  :{"pushId":pushId
		},
		cache : false,
		type : "post",
		success : function(data) {
			$.each(data, function(i, item) {
				$("#interviewer").append(
						"<option value='" + item.employeeId + "'>"
								+ item.staffName + "</option>")
			})
			if(interviewerId!=null||interviewerId!=""){
				$("#interviewer").val(interviewerId);
			}
		}
	})
}
/* offer */
function offerInterview(pushId) {
	var str = '<table id="demandList" class="table table-bordered table-hover">'
			+ '<thead><tr><th></th><th>RR</th><th>Job Code</th><th>Skill</th>'
			+ '<th>Position</th><th>Location</th>'
			+ '<th>Status</th><th>CS Dept</th><th>Detail</th></tr></thead></table>';
	$("#table_area").append(str);

	$("#demandList").delegate("tr", "click", function(e) {
		$(this).find("input[type=radio]").prop("checked", true);
	});

	$.ajax({
				url : path + '/service/rmCandidate/offerDemandList',
				dataType : 'json',
				async : false,
				cache : false,
				type : 'post',
				data : {
					"pushId" : pushId
				},
				success : function(result) {
					if (result.list.length < 0) {
						$("#demandList")
								.append(
										"<tr><td colspan='9' style='text-align:center'>暂无数据！</td></tr>");
						return;
					}
					for (var i = 0; i < result.list.length; i++) {
						var tr = $("<tr id='" + result.list[i].demandId
								+ "'></tr>");
						var td1 = $("<td><input type='radio' name='checkAll' value='"
								+ result.list[i].demandId + "'/></td>");
						var td2 = $("<td>" + result.list[i].rr + "</td>");
						var td3 = $("<td>" + result.list[i].jobCode + "</td>");
						var td4 = $("<td>" + result.list[i].skill + "</td>");
						var td5 = $("<td>" + result.list[i].position + "</td>");
						/*
						 * if(result.list[i].hsbcDept == null){ var td6 = $("<td></td>");
						 * var td7 = $("<td></td>"); }else{ var td6 = $("<td>"+result.list[i].hsbcDept.hsbcDeptName+"</td>");
						 * var td7 = $("<td>"+result.list[i].hsbcDept.hsbcSubDeptName+"</td>"); }
						 */
						var td6 = $("<td>" + result.list[i].location + "</td>");
						var td8 = $("<td>" + result.list[i].status + "</td>");
						var td9 = $("<td>" + result.list[i].csSubDept + "</td>");
						// Felix, 20171213, Demand Detail, Begin.
						var td10 = $("<td><div class='btn-group btn-group-sm'><a href='javascript:void(0);' class='btn btn-info btn-small' onclick='demandDetail(\""
								+ result.list[i].demandId
								+ "\")'>Detail</a></div>" 
								+ "<div class='btn-group btn-group-sm'><a href='javascript:void(0);' class='btn btn-info btn-small' onclick = offerConfirm('"+pushId+"','"+result.list[i].demandId+"')>Confirm</div></td>");
						// Felix, 20171213, Demand Detail, End.
						td1.appendTo(tr);
						td2.appendTo(tr);
						td3.appendTo(tr);
						td4.appendTo(tr);
						td5.appendTo(tr);
						td6.appendTo(tr);
						// td7.appendTo(tr);
						td8.appendTo(tr);
						td9.appendTo(tr);
						td10.appendTo(tr);
						$("#demandList").append(tr);
					}
				}

			});

	var demandTable = $("#demandList");
	BootstrapDialog.show({
		title : 'DemandList',
		message : demandTable,
		size : BootstrapDialog.SIZE_WIDE,
		buttons : [ 
			 {
			label : 'Cancel',
			action : function(dialog) {
				dialog.close();
			}
		} ],
		onshown : function(e) {
			$(".bootstrap-dialog-message").css("maxHeight",
					window.innerHeight - 240 + "px")
		}
	});
}

function offerConfirm(pushId,demandId) {
	var pushId = pushId;
	var demandId = demandId;
	$.ajax({
		url : path + '/service/rmCandidate/offerInterview',
		dataType : "json",
		async : false,
		cache : false,
		type : "post",
		data : {
			"pushId" : pushId,
			"demandId" : demandId
		},
		success : function(data) {
			if (data == "1") {
				alert("Offering successfully!");
				location.reload();
			}
			loadMyCandidate();
		}
	});
}

function demandDetail(demandId) {
	$("#demandId").val(demandId);
	var url = path + '/service/demand/demandDetail';
	$("#detailForm").attr("action", url);
	$("#detailForm").submit();
}

$("#pageRecordsNum").change(function(){
	loadMyCandidate();
});

$("#searchBtn").click(function(){
	loadMyCandidate();
});

$("#graduationDate1").bind("input propertychange change",function(){
	if ($("#graduationDate1").val() == '') {
		$('#addInterviewer').attr("disabled", "disabled");
	}
	
	if ($("#graduationDate1").val() != ''
			&& $("#interviewer").val() != ''
			&& $("#projectName").val() != ''
			&& $("#interviewType").val() != '') {
		$("#addInterviewer").removeAttr("disabled");
	}
});

$("#interviewer").change(function(){
	if ($("#interviewer").val() == '') {
		$('#addInterviewer').attr("disabled", "disabled");
	}
	if ($("#graduationDate1").val() != ''
			&& $("#interviewer").val() != ''
			&& $("#projectName").val() != ''
			&& $("#interviewType").val() != '') {
		$("#addInterviewer").removeAttr("disabled");
	}
});

$('#projectName').bind("input propertychange change",function(){
	if ($("#projectName").val() == '') {
		$('#addInterviewer').attr("disabled", "disabled");
	}
	if ($("#graduationDate1").val() != ''
			&& $("#interviewer").val() != ''
			&& $("#projectName").val() != ''
			&& $("#interviewType").val() != '') {
		$("#addInterviewer").removeAttr("disabled");
	}
});

$('#interviewType').change(function(){
	if ($("#interviewType").val() == '') {
		$('#addInterviewer').attr("disabled", "disabled");
	}
	if ($("#graduationDate1").val() != ''
			&& $("#interviewer").val() != ''
			&& $("#projectName").val() != ''
			&& $("#interviewType").val() != '') {
		$("#addInterviewer").removeAttr("disabled");
	}
		
});


//获取HR
function getHr(){
	var puid = $("#puid").val();
	//alert(puid);
	$.ajax({
		url:path+'/service/user/getHR',
		dataType:"json",
		data:{"puid":puid},
		async:true,
		cache:false,
		type:"get",
		success:function(result){
			if(result){
				if(result != null && result.length>0){
					$("#hrdatatable tbody").remove();
					var tbody = $("<tbody>");
					tbody.appendTo($("#hrdatatable"));
					for(var i=0;i<result.length;i++){
						$("<tr>" +
								"<td><input value='"+result[i].userName+"' type='checkbox' name='hremail'/></td>"+
								"<td>"+result[i].userName+"</td>" +
								"<td>"+result[i].nickname+"</td>" +
								"<td>"+result[i].userType+"</td>" +
								"<td>"+result[i].email+"</td>" +
								"</tr>").appendTo(tbody);
					}
					$("#hrlist").modal('show');
					//隐藏掉是否发送邮件的提示
					$("#issendemail").modal('hide');
				}
				
			}
		}
	})
	
}

//发送邮件
function sendemail(){
	$("#hrlist").modal('hide');
	$("#jindu").modal('show');
	obj = document.getElementsByName("hremail");
    ehs = [];
    for(k in obj){
        if(obj[k].checked)
        	ehs.push(obj[k].value);
    }
    //alert(ehs);
	
	$.ajax({
		url:path+'/service/sendemail/send4',
		dataType:"json",
		data:{ehr:JSON.stringify(ehs)},
		async:true,
		cache:false,
		type:"post",
		success:function(result){
			if(result){
				$("#jindu").modal('hide');
				alert("发送成功");
				$("#hrlist").modal('hide');
			}
		}
	})
	
}

function selAll(){
		var all=document.getElementById('checkAll');//获取到点击全选的那个复选框的id  
		var one=document.getElementsByName('hremail');//获取到复选框的名称  
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