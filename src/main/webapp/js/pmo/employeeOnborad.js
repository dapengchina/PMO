var url;
$(function(){
	loadStaffCategory();
	loadRole();
	loadSkill();
	loadGbGf();
	loadBillingEntity();
	loadBillingCurrency();
    loadResourceStatus();
	loadCSDept();
	loadUserType();
	loadStaffRegion();
	loadStaffLocation();
	loadLocationType();
	loadOnshoreOrOffshore();
	dateTypeForEmp();
	loadUserForRM();
	$('#staffName').val(empObj.staffName);
	$('#candidateNameEdit').val(empObj.staffName);
	$('#graduationDate1').val(empObj.graduationDate);
	$('#sow').val(empObj.sow);
//	$('#entryDate1').val(empObj.entryDate);
	$('#email').val(empObj.email);
	loadHsbcDept();
})

	$("#staffRegion").change(function(){
		var staffRegion = $("#staffRegion").val();
		$("#staffLocation").val(regionMap.get(staffRegion));
	});


function addEmployee(){
	var bootstrapValidator = $("#registerEmployeeForm").data('bootstrapValidator');
	bootstrapValidator.validate();
	if(bootstrapValidator.isValid()){
		var eHr = $('#eHr').val();
		var lob = $('#lob').val();
		var hsbcStaffId = $('#hsbcStaffId').val();
		var staffName = $('#staffName').val();
		var LN = $('#LN').val();
		var staffRegion = $('#staffRegion').val();
		var staffLocation = $('#staffLocation').val();
		var locationType = $('#locationType').val();
		var onshoreOrOffshore = $('#onshoreOrOffshore').val();
		var csSubDept = $('#csSubDept').val();
		var hsbcSubDept = $('#hsbcSubDept').val();
		var projectName = $('#hsbcProjectName').val();
		var projectManager = $('#hsbcProjectManager').val();
		var sow = $('#sow').val();
		var sowExpiredDate = $('#sowExpiredDate1').val();
		var staffCategory = $('#staffCategory').val();
		var engagementType = $('#engagementType2').val();
		var hsbcDOJ = $('#hsbcDOJ2').val();
		var graduationDate = $('#graduationDate1').val();
		var role = $('#role').val();
		var skill = $('#skill').val();
		var billingCurrency = $('#billingCurrency').val();
		var billRate = $('#billRate').val();
		var terminationReason = $('#terminationReason').val();
		
		var email = $('#email').val();
		var gbGf = $('#gbGf').val();
		var entryDate = $('#entryDate1').val();
		var rmName= $("#RM").val();
		var itworkyear= $("#itworkyear").val();
		
		$.ajax({
			url:path+'/service/employee/addEmployee',
			dataType:"json",
			data:{"eHr":eHr,"lob":lob,"hsbcStaffId":hsbcStaffId,"staffName":staffName,"LN":LN,"staffRegion":staffRegion,
				"staffLocation":staffLocation,"locationType":locationType,"onshoreOrOffshore":onshoreOrOffshore,"csSubDept":csSubDept,
				"hsbcSubDept":hsbcSubDept,"projectName":projectName,"projectManager":projectManager,"sow":sow,"sowExpiredDate":sowExpiredDate,
				"staffCategory":staffCategory,"engagementType":engagementType,"hsbcDOJ":hsbcDOJ,"graduationDate":graduationDate,
				"role":role,"skill":skill,"billingCurrency":billingCurrency,"billRate":billRate,"resourceStatus":'Active',"terminatedDate":'',
				"email":email,"gbGf":gbGf,"entryDate":entryDate,"rmUserId":rmName,"terminationReason":terminationReason,"itindustryWorkYear":itworkyear},
			async:true,
			cache:false,
			type:"post",
			success:function(resultFlag){
				if(resultFlag){
					return ture;		
				}
			}
		})
		return true;
	}
	
}


function dateTypeForEmp(){
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
		 $('#registerEmployeeForm').bootstrapValidator('revalidateField', 'hsbcDOJ1'); 
		 $('#registerEmployeeForm').bootstrapValidator('revalidateField', 'sowExpiredDate1'); 
		 $('#registerEmployeeForm').bootstrapValidator('revalidateField', 'graduationDate1');
		 $('#registerEmployeeForm').bootstrapValidator('revalidateField', 'entryDate1'); 
	});
}
function loadOnshoreOrOffshore(){
	var url = path+'/json/onshoreOrOffshore.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#onshoreOrOffshore").append("<option>"+item.name+"</option>");
	       })
	});
}


function loadLocationType(){
	var url = path+'/json/locationType.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#locationType").append("<option>"+item.name+"</option>");
	       })
	});
}


function loadStaffLocation(){
	var url = path+'/json/staffLocation.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#staffLocation").append("<option>"+item.name+"</option>");
	       })
	       $("#staffLocation").val(empObj.staffLocation);
	});
	
}


var regionMap = new Map();
function loadStaffRegion(){
	var url = path+'/json/staffRegion.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#staffRegion").append("<option>"+item.name+"</option>");
	    	   regionMap.set(item.name,item.key);
	       })
	       $("#staffRegion").val(empObj.staffRegion);
	});
	
}



var userTypeMap = new Map();
function loadUserType(){
	var url = path+'/json/userType.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#type").append("<option value='"+item.key+"'>"+item.name+"</option>");
	    	   userTypeMap.set(item.name,item.key);
	       })
	});
}


function loadResourceStatus(){
	var url = path+'/json/resourceStatus.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#resourceStatus").append("<option>"+item.name+"</option>");
	       })
	       $("#resourceStatus").val(empObj.resourceStatus);
	});
}


function loadBillingCurrency(){
	var url = path+'/json/billingCurrency.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#billingCurrency").append("<option>"+item.name+"</option>");
	       })
	});
}

function loadBillingEntity(){
	var url = path+'/json/billingEntity.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#billingEntity").append("<option>"+item.name+"</option>");
	       })
	});
}

function loadSkill(){
	var url = path+'/json/skill.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#skill").append("<option>"+item.name+"</option>");
	    	   $("#skill1").append("<option>"+item.name+"</option>");
	       })
	       $("#skill").val(empObj.skill);
	});
}

function loadGbGf(){
	var url = path + '/json/gbGf.json';
	$.getJSON(url, function(data){
		$.each(data, function(i, item){
			$("#gbGf").append("<option>"+item.name+"</option>");
		})
	});
}

function loadRole(){
	var url = path+'/json/role.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#role").append("<option>"+item.name+"</option>");
	       })
	      $('#role').val(empObj.role);
	});
}


function loadStaffCategory(){
	var url = path+'/json/staffCategory.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#staffCategory").append("<option>"+item.name+"</option>");
	       })
	});
}

function loadCSDept(){
	var responseValue = $("#csSubDept").val();
	$("#csSubDept").empty();
	$("#csSubDept").append("<option value=''>-- Option --</option>");
	$.ajax({
		url:path+'/service/demand/loadAllScSubDeptName',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(data){
			$.each(data, function(i,item){
				$("#csSubDept").append("<option value='"+item.csSubDeptId+"'>"+item.csSubDeptName+"</option>");
			});
			//add by jama 设置业务部回显值
			var all_options = document.getElementById("csSubDept").options;
		    for (i=1; i<all_options.length; i++){
			   if (all_options[i].value == responseValue){
				   document.getElementById("csSubDept").options[i].selected = true;
				   break;
			   }  
		    }
//		    $("#csSubDept").attr("disabled","disabled");
		    loadUserForRM($('#csSubDept').val());
		}
	})
}
$("#csSubDept").change(function(){
	var du =$("#csSubDept").val();
	loadUserForRM(du);
})

function loadUserForRM(du){	
	$.ajax({
		url:path+'/service/user/getUserForRM',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(list){
			$("#RM").empty();
			$("#RM").append("<option value=''>--Option--</option>");	
			var RMList = new Array();
			if (du != null && du != "") {
				for (var i = 0; i < list.length; i++) {
					var csDeptIds = list[i].csdeptId.split(",");
					for (var j = 0; j < csDeptIds.length; j++) {
						if (du == csDeptIds[j]) {
							RMList.push(list[i]);
						}
					}
				}
			}else{
				for(var i = 0;i<list.length;i++){
					RMList.push(list[i]);
				}

			}
			//remove duplicates
			var newRMList = new Array();
			for(var i = 0;i < RMList.length;i++){				
				if(newRMList.indexOf(RMList[i])==-1){
					newRMList.push(RMList[i]);
				}
			}
			
			for(var i = 0;i < newRMList.length;i++){
				$("#RM").append("<option value='"+newRMList[i].userId+"'>"+newRMList[i].nickname+"</option>")
			}
			
		}
	})
}
/*$("#csDept").change(function(){
	var csSubDeptId = $('#csDept').val();
	$("#csSubDept").find("option").remove(); 
	$("#csSubDept").append("<option value=''>-- 请选择项目 --</option>");
	$.ajax({
		url:path+'/service/csDept/queryCSSubDeptName',
		dataType:"json",
		async:true,
		data:{"csSubDeptId":csSubDeptId},
		cache:false,
		type:"post",
		success:function(list){
			$("#csSubDept").find("option").remove(); 
			$("#csSubDept").append("<option value=''>-- 请选择子交付部 --</option>");
			for(var i = 0;i<list.length;i++){
				$("#csSubDept").append("<option value='"+list[i].csSubDeptId+"'>"+list[i].csSubDeptName+"</option>");
			}
		}
	})
})
*/

function loadHsbcDept(){
	$.ajax({
		url:path+'/service/demand/loadDepartment',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(data){
			$.each(data, function(i,item){
				$("#hsbcDept").append("<option value='"+item.hsbcDeptName+"'>"+item.hsbcDeptName+"</option>")
			});
			//add by jama 设置部门回显值
			var responseValue = $("#hsbcDeptInput2").val();
			var resSubDeptValue = $("#hsbcSubDept").val();//子部门的返回值
			if(document.getElementById("hsbcDept")!=null){
				var all_options = document.getElementById("hsbcDept").options;
			    for (i=1; i<all_options.length; i++){
				   if (all_options[i].value == responseValue){
					   document.getElementById("hsbcDept").options[i].selected = true;
					   //上一行代码改变部门后，子部门的值跟着变化了，所以将子部门的返回值继续设置在节点上供后面使用
					   break;
				   }  
			    }
			}
			
		   genSubDeptDept(resSubDeptValue);
		}
	})
}
/*根据部门加载对应的子部门*/
function genSubDeptDept(resSubDeptValue){
	var department = $("#hsbcDept").val();
	$("#hsbcSubDept").empty();
	$("#hsbcSubDept").append("<option value=''>-- Option --</option>");
	$.ajax({
		url:path+'/service/demand/loadSubDepartment',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		data:{"hsbcDeptName":department},
		success:function(data){
			$.each(data, function(i,item){
				if(item.hsbcSubDeptName!=null){
					$("#hsbcSubDept").append("<option value='"+item.hsbcSubDeptName+"'>"+item.hsbcSubDeptName+"</option>");
				}else{
					$("#hsbcSubDept").append("<option value='"+item.hsbcDeptName+"'>"+item.hsbcDeptName+"</option>");
				}
			});
			//add by jama 设置子部门回显
			if("" != resSubDeptValue && undefined != resSubDeptValue){
				document.getElementById("hsbcSubDept").value = resSubDeptValue;
			}
			var responseValue = $("#hsbcSubDept").val();
			if(document.getElementById("hsbcSubDept")!=null){
				var all_options = document.getElementById("hsbcSubDept").options;
			    for (i=1; i<all_options.length; i++){
				   if (all_options[i].value == responseValue){
					   document.getElementById("hsbcSubDept").options[i].selected = true;
					   break;
				   }  
			    }
			}
			
		}
	})
}

$("#hsbcDept").change(function(){
	var hsbcDeptName = $('#hsbcDept').val();
	$.ajax({
		url:path+'/service/demand/loadSubDepartment',
		dataType:"json",
		async:true,
		data:{"hsbcDeptName":hsbcDeptName},
		cache:false,
		type:"post",
		success:function(list){
			// ---gkf modify---
			$("#hsbcSubDept").find("option").remove(); 
			$("#hsbcSubDept").append("<option value=''>-- Option --</option>");
			if(list.length == 1 && list[0].hsbcSubDeptName == null){
				$("#hsbcSubDept").append("<option value='"+$('#hsbcDept').find("option:selected").val()+"'>"+$('#hsbcDept').find("option:selected").text()+"</option>");
			}else{
				for(var i = 0;i<list.length;i++){
					$("#hsbcSubDept").append("<option value='"+list[i].hsbcSubDeptName+"'>"+list[i].hsbcSubDeptName+"</option>");
				}
			}
		}
	})
})

function changeData(){
	var staffRegion = $('#staffRegion').val();
	var role = $('#role').val();
	var skill = $('#skill').val();
	if('' == staffRegion || staffRegion== null){
		return;
	}
	if('' == role || role== null){
		return;
	}
	if('' == skill || skill== null){
		return;
	}
	
	$.ajax({
		url:path+'/service/interview/getBillRate',
		dataType:"json",
		async:true,
		data:{"staffRegion":staffRegion,"role":role,"skill":skill},
		cache:false,
		type:"post",
		success:function(data){
			if(data != null){
				$('#billRate').val(data);
			}
		}
	})
}

function updateDemandOnboard(){
	var bootstrapValidator = $("#registerEmployeeForm").data('bootstrapValidator');
	bootstrapValidator.validate();
	var bootstrapValidator2 = $("#recruitdemandFormEdit").data('bootstrapValidator');
	bootstrapValidator2.validate();
	if(bootstrapValidator.isValid()&&bootstrapValidator2.isValid()){
		var onboradDemandFlag = updateDemandForOnborad("onborad");
		var onboradEmpFlag = addEmployee();
		self.opener.location.reload();
	}
	
	if(onboradDemandFlag&&onboradEmpFlag){
		$("html,body").animate({
			scrollTop : 0
		}, 500);
		$('#successAlert').html('Onboard successfully!').show();
		setTimeout(function() {
			$('#successAlert').hide();
			CloseWebPage();
		}, 2000);
		
	}
	
}

//function show_confirm() {
//	var r = confirm("Onboard successfully!");
//	if (r == true) {
//		CloseWebPage();
//	} 
//}

function CloseWebPage() {
	if (navigator.userAgent.indexOf("MSIE") > 0) {
		if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
			window.opener = null;
			window.close();
		} else {
			window.open('', '_top');
			window.top.close();
		}
	} else if (navigator.userAgent.indexOf("Firefox") > 0) {
		window.location.href = 'about:blank ';
	} else {
		window.opener = null;
		window.open('', '_self', '');
		window.close();
	}
}
 
function updateDemandForOnborad(index){
	var bootstrapValidator = $("#recruitdemandFormEdit").data('bootstrapValidator');
	   bootstrapValidator.validate();
	if(bootstrapValidator.isValid()){
		var demandId=$('#demandIdEdit').val();
		var engagementType=$('#engagementType').val();
		var rr=$('#rrEdit').val();
		var jobCode=$('#jobCodeEdit').val();
		var skill=$('#skillEdit').val();
		var requestor=$('#requestorEdit').val();
		var position=$('#positionEdit').val();
		//部门信息
		var hsbcDept=$('#hsbcDeptEdit').val();
		var hsbcSubDept=$('#hsbcSubDeptEdit').val();
		var location=$('#locationEdit').val();
		var reqPublishedDate=$('#reqPublishedDate1Edit').val();
		var ageing=$('#ageingEdit').val();
		var profilesNo=$('#profilesNoEdit').val();
		var interviewedNo=$('#interviewedNoEdit').val();
		var status=$('#status').val();
		var proposedJoiningDate=$('#proposedJoiningDateEdit1').val();
		var bgvCleared=$('#bgvClearedEdit').val();
		var sowSigned=$('#sowSignedEdit').val();
		var reason=$('#reasonEdit').val();
		
		var remark=$('#remarkEdit').val();
		var csSubDept=$('#csSubDeptEdit').val();

		var plannedOnboardDate=$('#plannedOnboardDate1').val();
		var doNumber=$('#doNumberEdit').val();
		var hrPriority=$('#hrPriorityEdit').val();
		var staffName=$('#candidateNameEdit').val();
		
		$.ajax({
			url:path+'/service/demand/updateDemand',
			dataType:"json",
			data:{"demandId":demandId,"rr":rr,"engagementType":engagementType,"jobCode":jobCode,"skill":skill,"requestor":requestor,
				"position":position,"location":location,
				"reqPublishedDate":reqPublishedDate,"ageing":ageing,"profilesNo":profilesNo,
				"interviewedNo":interviewedNo,"status":status,"staffName":staffName,
				"proposedJoiningDate":proposedJoiningDate,"sowSigned":sowSigned,
				"reason":reason,"bgvCleared":bgvCleared,
				"remark":remark,"csSubDept":csSubDept,"plannedOnboardDate":plannedOnboardDate,
				"doNumber":doNumber,"hrPriority":hrPriority,
				"hsbcDept":hsbcDept,"hsbcSubDept":hsbcSubDept,"onborad":index},
			async:true,
			cache:false,
			type:"post",
			success:function(resultFlag){
				if(resultFlag){
					return true;
				}
			}
		})
		return true;
	}
}