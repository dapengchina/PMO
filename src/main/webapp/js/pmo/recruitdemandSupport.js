var t3;
$(function(){
	$('#demandDraftTip').hide();
	$("#haveDraftTip").hide();
	loadDemandDraft();
	//定义一个反复执行的调用 
	t3 = setInterval(addDemandDraft,3000);
	loadDept();
	loadSkill();
	loadPosition();
	loadLocation();
	//loadStatus();
	//loadHrPriority();
	dateType();
	dateType1();
	loadCsSubDept();
})
var draftFlag = false;
/*function addDemand(){
	var url = path+'/service/demand/addDemand';
	alert(url);
	$("#recruitdemandForm").attr("action",url);
	$("#recruitdemandForm").submit();
}*/

/*$('#rr').blur(function(){
	hrPriority();
});

$('#jobCode').blur(function(){
	hrPriority();
});

function hrPriority(){
	$('#hrPriority').empty();
	if($('#rr').val() == '' || $('#rr').val() == null){
		$('#hrPriority').append("<option>4</option>");
		return; 
	}
	
	if($('#jobCode').val() == '' || $('#jobCode').val() == null){
		$('#hrPriority').append("<option>4</option>");
		return; 
	}
	
	$('#hrPriority').append("<option>3</option>");
}
*/

function loadDept(){
	$.ajax({
		url:path+'/service/hsbcDept/queryDeptName',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(list){
			for(var i = 0;i<list.length;i++){
				$("#hsbcDept").append("<option value='"+list[i].hsbcSubDeptId+"'>"+list[i].hsbcDeptName+"</option>");
			}
		}
	})
}
function loadEngagementTypeSupport(){
	var url = path+'/json/engagementType2.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#engagementType").append("<option>"+item.name+"</option>");
	       })
	});
}

$("#hsbcDept").change(function(){
	var hsbcSubDeptId = $('#hsbcDept').val();
	$("#projectName").find("option").remove(); 
	$("#projectName").append("<option value=''>-- 请选择项目 --</option>");
	$.ajax({
		url:path+'/service/hsbcDept/querySubDeptName',
		dataType:"json",
		async:true,
		data:{"hsbcSubDeptId":hsbcSubDeptId},
		cache:false,
		type:"post",
		success:function(list){
			$("#hsbcSubDept").find("option").remove(); 
//			if(list.length == 1 && list[0].hsbcSubDeptName == null){
//				$("#hsbcSubDept").append("<option value='"+$('#hsbcDept').find("option:selected").val()+"'>"+$('#hsbcDept').find("option:selected").text()+"</option>");
//			}else{
			$("#hsbcSubDept").append("<option value=''>-- Option --</option>");
			if(list.length == 1 && list[0].hsbcSubDeptName == null){
				$("#hsbcSubDept").append("<option value='"+$('#hsbcDept').find("option:selected").val()+"'>"+$('#hsbcDept').find("option:selected").text()+"</option>");
			}else{
				for(var i = 0;i<list.length;i++){
					$("#hsbcSubDept").append("<option value='"+list[i].hsbcSubDeptId+"'>"+list[i].hsbcSubDeptName+"</option>");
				}
			}
			
			//}
		}
	})
})
function loadCsSubDept(){
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
}

var lastConditionStr = "";
function addDemand(){
	clearInterval(t3);
	 var bootstrapValidator = $("#recruitdemandForm").data('bootstrapValidator');
	   bootstrapValidator.validate();
	if(bootstrapValidator.isValid()){
		var demandId=$('#demandId').val();
		var engagementType=$('#engagementType').val();
		var rr=$('#rr').val();
		var jobCode=$('#jobCode').val();
		var skill=$('#skill').val();
		var requestor=$('#requestor').val();
		var position=$('#position').val();
		//部门信息
		var hsbcDept=$('#hsbcDept').val();
		var hsbcSubDept=$('#hsbcSubDept').val();
		var location=$('#location').val();
		var reqPublishedDate=$('#reqPublishedDate2').val();
		var ageing=$('#ageing').val();
		var profilesNo=$('#profilesNo').val();
		var interviewedNo=$('#interviewedNo').val();
		var status=$('#status').val();
		var proposedJoiningDate=$('#proposedJoiningDate').val();
		var dcCleared=$('#dcCleared').val();
		var sowSigned=$('#sowSigned').val();
		var onboarded=$('#onboarded').val();
		var abort=$('#abort').val();
		var delayed=$('#delayed').val();
		var reason=$('#reason').val();
		var nextAction=$('#nextAction').val();
		var status2=$('#status2').val();
		var remark=$('#remark').val();
		var csSubDept=$('#csSubDept').val();
		var plannedOnboardDate=$('#plannedOnboardDate2').val();
		var doNumber=$('#doNumber').val();
		var hrPriority=$('#hrPriority').val();
		var reqReceivedDate=$('#reqReceivedDate').val();
		var ageingReceived=$('#ageingReceived').val();
		var demandPriority=$('#demandPriority').val();
		var creatDate=$('#creatDate').val();
		var updateDate=$('#updateDate').val();
		var recruitmentCycle=$('#recruitmentCycle').val();
		var completionDay=$('#completionDay').val();
		var completionDate=$('#completionDate').val();
		var onboardDate=$('#onboardDate').val();
		var staffName=$('#staffName').val();
		
		//需求数量
		var requirementNumber=$('#requirementNumber').val();
		var addDemandConditionStr = demandId + engagementType + rr + jobCode
						+ skill + requestor + position + hsbcDept + hsbcSubDept
						+ location + reqPublishedDate + ageing + profilesNo
						+ interviewedNo + status + proposedJoiningDate + dcCleared
						+ sowSigned + onboarded + abort + delayed + reason + nextAction
						+ status2 + remark + csSubDept + plannedOnboardDate + doNumber
						+ hrPriority + reqReceivedDate + ageingReceived
						+ demandPriority + creatDate + updateDate + recruitmentCycle
						+ completionDay + completionDate + onboardDate + staffName + requirementNumber;
		if (lastConditionStr != addDemandConditionStr) {
			lastConditionStr = addDemandConditionStr;
		} else {
			$("html,body").animate({
				scrollTop : 0
			}, 500);
			$('#successAlert').html('Duplicate data,do not resubmit!').show();
			setTimeout(function() {
				$('#successAlert').hide();
			}, 2000);
			return;
		}
		$.ajax({
			url:path+'/service/demand/addDemand',
			dataType:"json",
	//		data:{"rr":rr,"jobCode":jobCode,"skill":skill,"requestor":requestor},
			data:{"rr":rr,"jobCode":jobCode,"engagementType":engagementType,"skill":skill,"requestor":requestor,
				"position":position,"location":location,
				"reqPublishedDate":reqPublishedDate,"ageing":ageing,"profilesNo":profilesNo,
				"interviewedNo":interviewedNo,"status":status,"staffName":staffName,
				"proposedJoiningDate":proposedJoiningDate,"dcCleared":dcCleared,"sowSigned":sowSigned,
				"onboarded":onboarded,"abort":abort,"delayed":delayed,"reason":reason,"nextAction":nextAction,
				"status2":status2,"remark":remark,"csSubDept":csSubDept,"plannedOnboardDate":plannedOnboardDate,
				"doNumber":doNumber,"hrPriority":hrPriority,"reqReceivedDate":reqReceivedDate,"ageingReceived":ageingReceived,
				"demandPriority":demandPriority,"creatDate":creatDate,"updateDate":updateDate,"recruitmentCycle":recruitmentCycle,
				"completionDay":completionDay,"completionDate":completionDate,"onboardDate":onboardDate,"hsbcDept":hsbcDept,"hsbcSubDept":hsbcSubDept,"requirementNumber":requirementNumber,"type":3},
			async:true,
			cache:false,
			type:"post",
			success:function(resultFlag){
				if(resultFlag){
					$("html,body").animate({scrollTop:0}, 500);
					$('#successAlert').html('The information was added successfully').show();
					setTimeout(function () {
						$('#successAlert').hide();
					}, 2000);
					$("#issendemail").modal('show');
					//var urlTo = path+'/service/demand/demandInfo.html';
					//window.location.href = urlTo;
				}
			}
		})
	}else{
		//Do something for invalid
		//alert('请填写正确的信息');
		return ;
	}
}

/*$("#skill").change(function(){
	var skill = $('#skill').val();
	$.ajax({
		url:path+'/service/rate/queryRateBySkill',
		dataType:"json",
		data:{"skill":skill},
		async:true,
		cache:false,
		type:"post",
		success:function(list){
			$("#position").empty();
			$("#position").append("<option value=''>--Option--</option>");
			for(var i = 0;i<list.length;i++){
				$("#position").append("<option>"+list[i].position+"</option>");
			}
		}
	})
	
})*/


function loadSkill(){
	var url = path+'/json/skill.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#skill").append("<option>"+item.name+"</option>");
	       })
	});
}
function loadPosition(){
	var url = path+'/json/role.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#position").append("<option>"+item.name+"</option>");
	       })
	});
}
function loadLocation(){
	var url = path+'/json/staffRegion.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#location").append("<option>"+item.name+"</option>");
	       })
	});
}

/*function loadStatus(){
	var url = path+'/json/status.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#status").append("<option>"+item.name+"</option>");
	       })
	});
}*/

function loadHrPriority(){
	var url = path+'/json/hrPriority.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#hrPriority").append("<option>"+item.name+"</option>");
	       })
	});
}


function dateType(){
	$('.form_datetime').datetimepicker({
		weekStart: 1,
		minView:'month',
		todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
		language:'zh-CN',
		format: 'yyyy-mm-dd',
		pickerPosition: 'bottom-left',
		showMeridian: 1
	}).on('changeDate', function(ev){
		 $('#recruitdemandFormEdit').bootstrapValidator('revalidateField', 'reqPublishedDate1Edit'); 
	});
}

function dateType1(){
	$('.form_datetime1').datetimepicker({
		weekStart: 1,
		minView:'month',
		todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
		language:'zh-CN',
		format: 'yyyy-mm-dd',
		pickerPosition: 'bottom-left',
		showMeridian: 1
	}).on('changeDate', function(ev){
		 $('#recruitdemandFormEdit').bootstrapValidator('revalidateField', 'plannedOnboardDate1'); 
	});
}

$("#sub_add").on("click",function(e){
	$(".has-feedback").removeClass("has-feedback");
	$(".has-success").removeClass("has-success");
	$(".has-error").removeClass("has-error");
	$(".form-control-feedback").css("display","none");
	$("small.help-block").css("display","none");
});

$("#getTm").click(function(){
	 window.location.href = path+"/service/demand/recruitType.html?status=1";
} );

$("#getFp").click(function(){
	window.location.href = path+"/service/demand/recruitType.html?status=2";
});

$("#getSupport").click(function(){
	window.location.href = path+"/service/demand/recruitType.html?status=3";
});

//保存需求草稿
function addDemandDraft(){

		var demandId=$('#demandId').val();
		var engagementType=$('#engagementType').val();
		var rr=$('#rr').val();
		var jobCode=$('#jobCode').val();
		var skill=$('#skill').val();
		var requestor=$('#requestor').val();
		var position=$('#position').val();
		//部门信息
		var hsbcDept=$('#hsbcDept').val();
		var hsbcSubDept=$('#hsbcSubDept').val();
		var location=$('#location').val();
		var reqPublishedDate=$('#reqPublishedDate2').val();
		var ageing=$('#ageing').val();
		var profilesNo=$('#profilesNo').val();
		var interviewedNo=$('#interviewedNo').val();
		var status=$('#status').val();
		var proposedJoiningDate=$('#proposedJoiningDate').val();
		var dcCleared=$('#dcCleared').val();
		var sowSigned=$('#sowSigned').val();
		var onboarded=$('#onboarded').val();
		var abort=$('#abort').val();
		var delayed=$('#delayed').val();
		var reason=$('#reason').val();
		var nextAction=$('#nextAction').val();
		var status2=$('#status2').val();
		var remark=$('#remark').val();
		var csSubDept=$('#csSubDept').val();
		var plannedOnboardDate=$('#plannedOnboardDate2').val();
		var doNumber=$('#doNumber').val();
		var hrPriority=$('#hrPriority').val();
		var reqReceivedDate=$('#reqReceivedDate').val();
		var ageingReceived=$('#ageingReceived').val();
		var demandPriority=$('#demandPriority').val();
		var creatDate=$('#creatDate').val();
		var updateDate=$('#updateDate').val();
		var recruitmentCycle=$('#recruitmentCycle').val();
		var completionDay=$('#completionDay').val();
		var completionDate=$('#completionDate').val();
		var onboardDate=$('#onboardDate').val();
		var staffName=$('#staffName').val();
		
		//需求数量
		var requirementNumber=$('#requirementNumber').val();
		var addDemandConditionStr = demandId + engagementType + rr + jobCode
						+ skill + requestor + position + hsbcDept + hsbcSubDept
						+ location + reqPublishedDate + ageing + profilesNo
						+ interviewedNo + status + proposedJoiningDate + dcCleared
						+ sowSigned + onboarded + abort + delayed + reason + nextAction
						+ status2 + remark + csSubDept + plannedOnboardDate + doNumber
						+ hrPriority + reqReceivedDate + ageingReceived
						+ demandPriority + creatDate + updateDate + recruitmentCycle
						+ completionDay + completionDate + onboardDate + staffName + requirementNumber;
		
		$.ajax({
			url:path+'/service/demandDraft/add',
			dataType:"json",
			data:{"rr":rr,"jobCode":jobCode,"engagementType":engagementType,"skill":skill,"requestor":requestor,
				"position":position,"location":location,
				"reqPublishedDate":reqPublishedDate,"ageing":ageing,"profilesNo":profilesNo,
				"interviewedNo":interviewedNo,"status":status,"staffName":staffName,
				"proposedJoiningDate":proposedJoiningDate,"dcCleared":dcCleared,"sowSigned":sowSigned,
				"onboarded":onboarded,"abort":abort,"delayed":delayed,"reason":reason,"nextAction":nextAction,
				"status2":status2,"remark":remark,"csSubDept":csSubDept,"plannedOnboardDate":plannedOnboardDate,
				"doNumber":doNumber,"hrPriority":hrPriority,"reqReceivedDate":reqReceivedDate,"ageingReceived":ageingReceived,
				"demandPriority":demandPriority,"creatDate":creatDate,"updateDate":updateDate,"recruitmentCycle":recruitmentCycle,
				"completionDay":completionDay,"completionDate":completionDate,"onboardDate":onboardDate,"hsbcDept":hsbcDept,"hsbcSubDept":hsbcSubDept,"requirementNumber":requirementNumber,"type":3},
			async:true,
			cache:false,
			type:"post",
			success:function(resultFlag){
				if(resultFlag){
					/*$("html,body").animate({scrollTop:0}, 500);
					$('#successAlert').html('The information was added successfully').show();
					setTimeout(function () {
						$('#successAlert').hide();
					}, 2000);*/
					if(draftFlag==false){
						$("#demandDraftTip").show();
						draftFlag = true;
					}
					setTimeout(function () {
						$('#demandDraftTip').hide();
					}, 2000);
				}
			}
		})
}


function loadDemandDraft(){
	$.ajax({
		url:path+'/service/demandDraft/getByID',
		dataType:"json",
		data:{"type":3},
		async:true,
		cache:false,
		type:"post",
		success:function(result){
			if(result!=null){
				$("#haveDraftTip").show();
			}
		}
	})
}

function delDemandDraft(){
	$.ajax({
		url:path+'/service/demandDraft/delete',
		dataType:"json",
		data:{"type":3},
		async:true,
		cache:false,
		type:"post",
		success:function(result){
			if(result){
				$("#haveDraftTip").hide();
			}
		}
	})
}

function recoveryDemandDraft(){
	$("#haveDraftTip").hide();
	$.ajax({
		url:path+'/service/demandDraft/getByID',
		dataType:"json",
		data:{"type":3},
		async:true,
		cache:false,
		type:"post",
		success:function(result){
			if(result!=null){
                if(result.skill!=null && result.skill!=''){
                	$("#skill").val(result.skill);
                }
                if(result.location!=null && result.location!=''){
                	$("#location").val(result.location);
                }
                if(result.position!=null && result.position!=''){
                	$("#position").val(result.position);
                }
                if(result.remark!=null && result.remark!=''){
                	$("#remark").val(result.remark);
                }
                if(result.csSubdept!=null && result.csSubdept!=''){
                	$("#csSubDept").find("option[value='"+result.csSubdept+"']").attr("selected",true);
                }
                if(result.plannedOnboardDate!=null && result.plannedOnboardDate!=''){
                	$("#plannedOnboardDate1").val(result.plannedOnboardDate);
                	$("#plannedOnboardDate2").val(result.plannedOnboardDate);
                }
			}
		}
	})
}

//获取HR
function getHr(){
	$.ajax({
		url:path+'/service/user/getHR',
		dataType:"json",
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
					//隐藏掉是否发送邮件的提示框
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
		url:path+'/service/sendemail/send1',
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
				var urlTo = path+'/service/demand/demandInfo.html';
				window.location.href = urlTo;
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

