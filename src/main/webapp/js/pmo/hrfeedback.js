
function feedbackCandidateInfo(candidateId,candidateName){
	$("#hrcandidateId").val(candidateId);
	$("#candidateNameId").val(candidateName);
	$('#hrfeedBackbox').modal('show');
	$('#feedbackList tbody').html("")
	$('#hrFeedBack').html("");
	$.ajax({
		url:path+"/service/hrfeedback/hrfeedbackQuery ",
		dataType:"json",
		async:true,
		data:{'candidateId':candidateId},
		cache:false,
		type:"post",
		success:function(result){
			var tbody = $("<tbody>");
			tbody.appendTo($("#feedbackList"));
			if(result.length<= 0 ){
				$('#recordTable').display = "none";
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);
				$("<td colspan='4' style='color: red;text-align: center;'>No record!</td>").appendTo(tr);
			}
			for (var i = 0; i < result.length; i++) {
				var tr = $("<tr ></tr>");
				tr.appendTo(tbody);
				$("<td>HR Name:" +result[i].nickname+"</td>" +
				"<td>FeedbackDATE:"+ result[i].feedbacktime+ "</td>").appendTo(tr);
				var trr = $("<tr ></tr>");
				trr.appendTo(tbody);
				$("<td colspan='2'>Feedback:"+ result[i].hrFeedBack+ "</td>").appendTo(trr);
				
			$("#feedbackList").append("</tbdoy>");
			
			
		}}
	})
	
	
}

function updateHRFeedBack(){
	var candidateId=$("#hrcandidateId").val();
	var hrFeedBack=$("#hrFeedBack").val();
	$('#hrFeedBackForm').bootstrapValidator({
		message: 'This value is not valid',

        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	hrFeedBack: {
                validators: {
                    notEmpty: {
                        message: 'Please enter Feedback'
                    }
                }
            }
        }
    });
	var bootstrapValidator = $("#hrFeedBackForm").data('bootstrapValidator');
	bootstrapValidator.validate();
	if(!bootstrapValidator.isValid()){
		return;
	}
	$.ajax({
		url:path+"/service/hrfeedback/hrFinalFeedBack",
		dataType:"json",
		async:true,
		data:{'candidateId':candidateId,'hrFeedBack':hrFeedBack},
		cache:false,
		type:"post",
		success:function(result){
			$("#hrFeedBack").val(" ");
			$('#hrFeedBackForm').data('bootstrapValidator').resetForm(); 
			if(result)
			{
			     $('#hrfeedBackbox').modal('hide');
			     loadCandidateList();	
			}else{
				 $('#hrfeedBackbox').modal('hide');
				 alert("Feedback unsuccessfully")
			}
		}
	})
	
}

function interviewConfirm(candidateId,candidateName,csSubdeptName){
	$('#hrInterviewConfirmbox').modal('show');
	$("#hrConfirmCandidateId").val(candidateId);
	$("#hrConfirmCandidateNameId").val(candidateName);
	$("#hrConfirmCandidateNameId").attr("disabled", true);
	$('#confirmDate').val("confirm");
	$("#newInterviewDate").hide();
	$("#newDate").val("");
	
	var candidateId = candidateId;
	var csSubdeptName = csSubdeptName;
	$.ajax({
		url:path+"/service/interview/getNewInterviewRecord",
		dataType:"json",
		async:true,
		data:{'candidateId':candidateId,'csSubdeptName':csSubdeptName},
		cache:false,
		type:"post",
		success:function(result){
			$("#hrConfirmInterviewId").val(result.interviewId);
			$("#interviewDate").val(result.interviewDate);
			$("#interviewDate").attr("disabled", true);
			//$("#newDate").val(" ");
			
		}
	})
	
}
$("#confirmDate").change(function(){
	var confirmDate =  $("#confirmDate").find("option:selected").text()
	if (confirmDate =="取消"){
		$("#newInterviewDate").show();
	}else if (confirmDate =="确认"){
		$("#newInterviewDate").hide();
		$("#newDate").val("");
	}
})

function confirmInterviewDate(){
	var candidateId = $("#hrConfirmCandidateId").val();
	var confirmDateType =  $("#confirmDate").find("option:selected").text()
	var interviewId = $("#hrConfirmInterviewId").val();
	var newDate = $("#newDate").val();
	var bootstrapValidator;
	if($('#newInterviewDate').is(':hidden')){
	      //如果隐藏时的处理方法
	}else{
		//alert("显示");
	      //如果显示时的处理方法
		$('#conformForm').bootstrapValidator({
			message: 'This value is not valid',

	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	        	newDate: {
	                validators: {
	                    notEmpty: {
	                        message: 'Please enter New Interview Date'
	                    }
	                }
	            }
	        }
	    });
		bootstrapValidator = $("#conformForm").data('bootstrapValidator');
		bootstrapValidator.validate();
		if(!bootstrapValidator.isValid()){
			return;
		}
	}
	$.ajax({
		url:path+"/service/hrfeedback/confirmInterviewDate",
		dataType:"json",
		async:true,
		data:{'confirmDateType':confirmDateType,'newDate':newDate,'candidateId':candidateId,'interviewId':interviewId},
		cache:false,
		type:"post",
		success:function(result){
		    $("#issendemail").modal('show');
			if ($('#conformForm').data('bootstrapValidator') != undefined) {
				$('#conformForm').data('bootstrapValidator').resetForm();
			}
			if (result == false) {
				alert("Reconfirm unsuccessfully");
			} else if (result == true) {
				$("#reconfirm").hide();
			}
			$('#hrInterviewConfirmbox').modal('hide');
			loadCandidateList();
		}
	})
}

function cancel(){
	if($('#hrFeedBackForm').data('bootstrapValidator')!=undefined){
		$('#hrFeedBackForm').data('bootstrapValidator').resetForm();  
	}
	$('#hrfeedBackbox').modal('hide');
	$("#hrFeedBack").val("");
}
function cancelConform(){
	if($('#conformForm').data('bootstrapValidator')!=undefined){
		$('#conformForm').data('bootstrapValidator').resetForm(); 
	}
	$('#hrInterviewConfirmbox').modal('hide');
	$("#newDate").val("");
}	

//获取RM
function getRm(){
	var candidateId = $("#hrConfirmCandidateId").val();
	//alert(candidateId);
	$.ajax({
		url:path+'/service/user/getRM',
		dataType:"json",
		data:{"canid":candidateId},
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
					$("#rmlist1").modal('show');
					//隐藏掉是否发送邮件的提示
					$("#issendemail").modal('hide');
				}
				
			}
		}
	})
	
}

//发送邮件
function sendemail(){
	$("#rmlist1").modal('hide');
	$("#jindu").modal('show');
	obj = document.getElementsByName("hremail");
    ehs = [];
    for(k in obj){
        if(obj[k].checked)
        	ehs.push(obj[k].value);
    }
    //alert(ehs);
    //获取候选人ID
    var candidateId = $("#hrConfirmCandidateId").val();
    //alert(candidateId);
    //获取是否发送到面试官标志
    var issendtointerview = $("#issendtointerview").val();
    //alert(issendtointerview);
	
	$.ajax({
		url:path+'/service/sendemail/send3',
		dataType:"json",
		data:{ehr:JSON.stringify(ehs),candidateId:candidateId,issendtointerview:issendtointerview},
		async:true,
		cache:false,
		type:"post",
		success:function(result){
			if(result){
				$("#jindu").modal('hide');
				alert("发送成功");
				$("#rmlist1").modal('hide');
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
