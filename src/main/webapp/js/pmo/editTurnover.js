
window.onload = function(){
	loadEmployeeInfo();
	loadBUlist();
	initEmployeeValidate();
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
			$('#csSubDept').html("<option value ='"+ employee.csSubDept +"'>"+ employee.csSubDeptName +"</option>");
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
	var html = "<option value=''>--Option--</option>";
	$("#newRM").html(html);
	if(!csBuName){
		$("#newSubDept").html(html);
	}else{
		$.ajax({
			url : path+'/service/employee/loadScSubDeptName',
			dataType : "json",
			async : true,
			cache : false,
			type : "post",
			data : {'csBuName' : csBuName},
			success : function(result){
				$.each(result, function(i, item) {
			    	   html += "<option value='" + item.csSubDeptId + "'>" + item.csSubDeptName +"</option>";
			    });
				$("#newSubDept").html(html);
			}
		});
	}
}

var RMList=[];
function loadRMList(du){
	var bu = $("#newBU").val();
	var html = "<option value>--Option--</option>";
	if(!bu || !du){
		$("#newRM").html(html);
		$('#editTurnoverForm').data("bootstrapValidator").revalidateField("newRM"); 
	}else{
		if(RMList.length == 0){
			$.ajax({
				url:path+'/service/user/getUserForRM',
				dataType:"json",
				async:true,
				cache:false,
				type:"post",
				success:function(list){
					RMList = list;
					getRM(bu,du,RMList);
				}
			});
		}else{
			getRM(bu,du,RMList);
		}
	}
}

function getRM(bu,du,list){
	var html = "<option value>--Option--</option>";
	var RM = [];
	for(var i=0; i< list.length; i++){
		var csDeptIds = list[i].csdeptId.split(",");
		if(bu == list[i].bu){
			for(var j=0; j < csDeptIds.length; j++){
				if(csDeptIds[j] == du){
					RM.push(list[i]);
				}
			}
		}
	}
	for(var i = 0;i < RM.length;i++){
		html += "<option value='"+RM[i].userId+"'>"+RM[i].nickname+"</option>";
	}
	$("#newRM").html(html);
	if($("#newRM option").length == 1){
		$("#editTurnoverForm").bootstrapValidator("removeField", "newRM");
	}else{
		$('#editTurnoverForm').data("bootstrapValidator").addField("newRM",{validators: {
            notEmpty: {
                message: 'Please select new RM'
            }}});
		$('#editTurnoverForm').data("bootstrapValidator").revalidateField("newRM");
	}
	
}

function confirmTurnover(employee){
	initEmployeeValidate();
	var data = {};
	var newSubDeptID = $("#newSubDept").val();
	var newSubDept = $("#newSubDept").find("option:selected").text();
	var csSubDept = $("#csSubDept").val();
	var newRM = $("#newRM").val();
	var length = $("#newRM option").length;
	var bootstrapValidator = $("#editTurnoverForm").data('bootstrapValidator');
	if(csSubDept && length == 1){
		$("#editTurnoverForm").bootstrapValidator("removeField", "newRM");
	}
	bootstrapValidator.validate();
	var validflag = bootstrapValidator.isValid();
	data.newdepartment = newSubDeptID;
	data.employeeId = $('#employeeId').val();
	data.newRM = newRM;
	if(validflag){
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
	
}
function back(){
	var urlTo = path+'/service/employee/employeeTurnover.html';
	window.location.href = urlTo;
}

function initEmployeeValidate(){
	$("#editTurnoverForm").bootstrapValidator({
		message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        live: 'enabled',
        fields: {
        	newBU: {
				validators: {
                    notEmpty: {
                        message: 'Please select bu'
                    }
                 }
            },

            newSubDept: {
                validators: {
                    notEmpty: {
                        message: 'Please select du'
                    },
                    different: {
                    	field: "csSubDept",
                    	message: "please select different du"
                    }
                }
            },
            newRM: {
                validators: {
                    notEmpty: {
                        message: 'Please select new RM'
                    }
                }
            },
            csSubDept: {
            	validators: {
            		 notEmpty: {
                         message: 'Please select old du'
                     }
                 }
            }
        }
    }) 
}