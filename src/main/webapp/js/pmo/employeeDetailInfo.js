
window.onload = function(){
	loadEmployeeInfo();
}

function loadEmployeeInfo(){
	var employeeId = $('#employeeId').val();
	
	$.ajax({
		url:path+'/service/interview/queryEmployeeById',
		dataType:"json",
		data:{"employeeId":employeeId},
		async:true,
		cache:false,
		type:"post",
		success:function(employee){
			$('#hsbcStaffId').val(employee.hsbcStaffId);
			$('#lob').val(employee.lob);
			$('#hsbcProjectName').val(employee.projectName);
			$('#hsbcProjectManager').val(employee.projectManager);
			$('#staffName').val(employee.staffName);
			$('#LN').val(employee.ln);
			$('#staffRegion').val(employee.staffRegion);
			$('#staffLocation').val(employee.staffLocation);
			$('#locationType').val(employee.locationType);
			$('#onshoreOrOffshore').val(employee.onshoreOrOffshore);
			$('#sow').val(employee.sow);
			$('#sowExpiredDate1').val(employee.sowExpiredDate);
			$('#staffCategory').val(employee.staffCategory);
			$('#engagementType').val(employee.engagementType);
			$('#hsbcDOJ1').val(employee.hsbcDOJ);
			$('#graduationDate1').val(employee.graduationDate);
			$('#role').val(employee.role);
			$('#skill').val(employee.skill);
			$('#billingCurrency').val(employee.billingCurrency);
			$('#billRate').val(employee.billRate);
			$('#resourceStatus').val(employee.resourceStatus);
			$('#terminatedDate1').val(employee.terminatedDate);
			$('#eHr').val(employee.eHr);
			$('#csSubDept').val(employee.csSubDeptName);
			//loadHSBCSubDept(employee.hsbcSubDept);
			//loadTerminationReason(employee);
			$('#terminationReason').val(employee.terminationReason);
			loadCSDept(employee);
			$('#email').val(employee.email);
			$('#entryDate1').val(employee.entryDate);
			$('#gbGf').val(employee.gbGf);
			$("#hsbcDept").val(employee.hsbcDeptName);
			$("#hsbcSubDept").val(employee.hsbcDeptSubName);
			$('#itworkyear').val(employee.itindustryWorkYear);
			$('#chsoftiProNumber').val(employee.chsoftiProNumber);
			$('#chsoftiProStartDate').val(employee.chsoftiProStartdate);
			$('#chsoftiProName').val(employee.chsoftiProName);
			
			if ($('#resourceStatus').val() == "Active") {
				$("#terminatedDateDiv").hide();
				$("#terminationReasonDiv").hide();
			} else if ($('#resourceStatus').val() == "Released") {
				$("#terminatedDateDiv").show();
				$("#terminationReasonDiv").hide();
			}else if ($('#resourceStatus').val() == "Transfer") {
				$("#terminatedDateDiv").show();
				$("#terminationReasonDiv").hide();
			} else {
				$("#terminatedDateDiv").show();
				$("#terminationReasonDiv").show();
			}

		}
	})
}


/**function loadHSBCSubDept(hsbcSubDeptId){
	$.ajax({
		url:path+'/service/interview/queryHSBCSubDeptById',
		dataType:"json",
		async:true,
		data:{"hsbcSubDeptId":hsbcSubDeptId},
		cache:false,
		type:"post",
		success:function(data){
			$("#hsbcSubDept").val(data.hsbcSubDeptName);
			$("#hsbcDept").val(data.hsbcDeptName);
		}
	})
}*/

function loadTerminationReason(employee){
	var url = path+'/json/terminatedReason.json'
	$.getJSON(url,  function(data) {
       $.each(data, function(i, item) {
    	   if(item.key == employee.terminationReason){
    		   $('#terminationReason').val(item.name);
    	   }
       })
	});
}

function loadCSDept(employee){
	$.ajax({
		url:path+'/service/csDept/queryAllCSSubDept',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(list){
			for(var i = 0;i<list.length;i++){
				 if(list[i].csSubDeptId == employee.csSubDept){
		    		   $('#csSubDept').val(list[i].csSubDeptName);
		    	   }
			}
		}
	})
}

function back(type){
	var temp = $("#bt").val();
	//alert(temp);
	var urlTo = path+'/service/employee/employeeInfo.html';
	var urlTo3 = path+'/service/employee/employeeInfoBatch.html';
	if(temp == 'batch'){
		window.location.href = urlTo3;
	}else{
		window.location.href = urlTo;
	}
	
}
