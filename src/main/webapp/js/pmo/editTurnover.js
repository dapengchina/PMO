
window.onload = function(){
	loadEmployeeInfo();
	loadBUlist();
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
			$('#staffName').val(employee.staffName);
			$('#eHr').val(employee.eHr);
			$('#csSubDept').val(employee.csSubDeptName);
		}
	})
}

function loadBUlist(){
	var url = path+'/json/csBuName.json';
	$.getJSON(url, function(data){
		var html = "";
		$.each(data, function(i, item) {
	    	   html += "<option>"+item.name+"</option>";
	    });
		$("#newBU").append(html);
	});
}

function loadNewSubDept(csBuName){
	$.ajax({
		url : path+'/service/employee/loadScSubDeptName',
		dataType : "json",
		async : true,
		cache : false,
		type : "post",
		data : {'csBuName' : csBuName},
		success : function(result){
			var html = "<option value=''>--Option--</option>";
			$.each(result, function(i, item) {
		    	   html += "<option value='" + item.csSubDeptId + "'>" + item.csSubDeptName +"</option>";
		    });
			$("#newSubDept").html(html);
		}
	});
}

function confirmTurnover(employee){
	var data = {};
	var newSubDept = $("#newSubDept").find("option:selected").text();
	var csSubDept = $("#csSubDept").val();
	if(!newSubDept || newSubDept == csSubDept){ return;}
	data.newdepartment = $("#newSubDept").val();
	data.employeeId = $('#employeeId').val();
	$.ajax({
		url : path + "/service/employee/addTurnover",
		dataType : "json",
		async :　true,
		cache　: false,
		type : "post",
		data : data,
		success : function(result){
			if(result){
				back();
			}
		}
	});
}
function back(){
	var urlTo = path+'/service/employee/employeeTurnover.html';
	window.location.href = urlTo;
}
