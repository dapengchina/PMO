$(function() {
	$("#udemandid").val("");
	loadBillRateType();
	loadEmployeeType();
	loadEmployeeInfo();
	dateType();
});

function changeData() {
	var staffRegion = $('#staffRegion').val();
	var role = $('#role').val();
	var skill = $('#skill').val();
	if ('' == staffRegion || staffRegion == null) {
		return;
	}
	if ('' == role || role == null) {
		return;
	}
	if ('' == skill || skill == null) {
		return;
	}

	$.ajax({
		url : path + '/service/interview/getBillRate',
		dataType : "json",
		async : true,
		data : {
			"staffRegion" : staffRegion,
			"role" : role,
			"skill" : skill
		},
		cache : false,
		type : "post",
		success : function(data) {
			if (data != null) {
				$('#billRate').val(data);
			}
		}
	})
}

function updateEmployee() {
	var emptype = $("#employeeType").val();
	var validflag = false;
	if(emptype=='HSBC'){
		var bootstrapValidator = $("#updateEmployeeForm").data('bootstrapValidator');
		bootstrapValidator.validate();
		var validflag = bootstrapValidator.isValid();
	}else{
		validflag = true;
	}
	if (validflag) {
		var employeetype = $("#employeeType").val();
		var udemandid = $("#udemandid").val();
		var employeeId = $('#employeeId').val();
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
		
        var hsbcDept = $('#hsbcDept').val();
		var zuhe = hsbcDept+","+hsbcSubDept;
		
		var projectName = $('#hsbcProjectName').val();
		var projectManager = $('#hsbcProjectManager').val();
		var sow = $('#sow').val();
		var sowExpiredDate = $('#sowExpiredDate1').val();
		var staffCategory = $('#staffCategory').val();
		var engagementType = $('#engagementType').val();
		var hsbcDOJ = $('#hsbcDOJ1').val();
		var graduationDate = $('#graduationDate1').val();
		var role = $('#role').val();
		var skill = $('#skill').val();
		var billingCurrency = $('#billingCurrency').val();
		var billRateType = $('#billRateType').val();
		var billRate = $('#billRate').val();
		var resourceStatus = $('#resourceStatus').val();
		var terminatedDate = $('#terminatedDate1').val();
		var terminationReason = $('#terminationReason').val();
		var email =  $('#email').val();
		var entryDate = $('#entryDate1').val();
		var gbGf = $('#gbGf').val();
		
		var chsoftiProNumber =  $('#chsoftiProNumber').val();
		var chsoftiProStartDate1 = $('#chsoftiProStartDate1').val();
		var chsoftiProName = $('#chsoftiProName').val();
		
		//拿到IT行业工作年限
		var itWorkYear = $("#itworkyear").val();
		
		//alert("毕业日期"+graduationDate);
		var currentDate = getCurrentDate();
		//alert("当前日期"+currentDate);
		var date1_temp = graduationDate.split("-");  
		var date2_temp = currentDate.split("-"); 
		var date1 = new Date(date1_temp[0], date1_temp[1]-1, date1_temp[2]);
		var date2 = new Date(date2_temp[0], date2_temp[1]-1, date2_temp[2]);  
		var days = date2.getTime() - date1.getTime(); 
		var year = parseInt(days / (1000 * 60 * 60 * 24 * 365)); 
		//alert("毕业的年限"+year);
		//判断IT工作年限不能大于实际工作年限
		//if(year!=null && year!=""){
			//alert(parseInt(itWorkYear));
			//alert(parseInt(year));
			if(parseInt(itWorkYear)>parseInt(year)){
				$("#modal-container-489917").modal('show');
				return;
			}
		//}
		
		$('#updatejindu').modal('show');
		$.ajax({
			url:path+'/service/employee/updateEmployee',
			dataType:"json",
			data:{"employeetype":employeetype,"billRateType":billRateType,"udemandid":udemandid,"employeeId":employeeId,"eHr":eHr,"lob":lob,"hsbcStaffId":hsbcStaffId,"staffName":staffName,"LN":LN,"staffRegion":staffRegion,"staffLocation":staffLocation,"locationType":locationType,"onshoreOrOffshore":onshoreOrOffshore,"csSubDept":csSubDept,"hsbcSubDept":zuhe,"projectName":projectName,"projectManager":projectManager,"sow":sow,"sowExpiredDate":sowExpiredDate,"staffCategory":staffCategory,"engagementType":engagementType,"hsbcDOJ":hsbcDOJ,"graduationDate":graduationDate,"role":role,"skill":skill,"billingCurrency":billingCurrency,"billRate":billRate,"resourceStatus":resourceStatus,"terminatedDate":terminatedDate,"terminationReason":terminationReason,
				"email":email,"entryDate":entryDate,"gbGf":gbGf,"itindustryWorkYear":itWorkYear,
				"chsoftiProNumber":chsoftiProNumber,"chsoftiProStartDate":chsoftiProStartDate1,"chsoftiProName":chsoftiProName	
			},
			async:true,
			cache:false,
			type:"post",
			success:function(resultFlag){
				if(resultFlag){
					$('#updatejindu').modal('hide');
					$("html,body").animate({scrollTop:0}, 500);
					$('#successAlert').html('Staff:'+staffName+' information edited succesffully').show();
					setTimeout(function () {
						$('#successAlert').hide();
					}, 2000);
					
					var urlTo = path+'/service/employee/employeeInfo.html';
					window.location.href = urlTo;
				}
				if(!resultFlag){
					alert('1');
				}
			}
		})
	}else{
		return;
	}
}

function loadEmployeeInfo() {
	var employeeId = $('#employeeId').val();

	$.ajax({
		url : path + '/service/employee/queryEmployeeById',
		dataType : "json",
		data : {
			"employeeId" : employeeId
		},
		async : true,
		cache : false,
		type : "post",
		success : function(employee) {

			loadStaffCategory(employee);
			loadEngagementType(employee);
			loadRole(employee);
			loadSkill(employee);
			loadBillingCurrency(employee);
			loadResourceStatus(employee);
			loadCSDept(employee);
			loadStaffRegion(employee);
			loadStaffLocation(employee);
			loadLocationType(employee);
			loadOnshoreOrOffshore(employee);
			loadTerminationReason(employee);
			loadPersonHsbcDept(employee);
			loadGbGf(employee);
			$('#hsbcStaffId').val(employee.hsbcStaffId);
			$('#lob').val(employee.lob);
			$('#hsbcProjectName').val(employee.projectName);
			$('#hsbcProjectManager').val(employee.projectManager);
			$('#staffName').val(employee.staffName);
			$('#LN').val(employee.ln);
			$('#sow').val(employee.sow);
			$('#sowExpiredDate1').val(employee.sowExpiredDate);
			$('#hsbcDOJ1').val(employee.hsbcDOJ);
			$('#graduationDate1').val(employee.graduationDate);
			$('#billRate').val(employee.billRate);
			$('#billRateType').val(employee.billRateType);
			$('#terminatedDate1').val(employee.terminatedDate);
			$('#eHr').val(employee.eHr);
			$('#email').val(employee.email);
			$('#entryDate1').val(employee.entryDate);
			$('#itworkyear').val(employee.itindustryWorkYear);
			$('#chsoftiProNumber').val(employee.chsoftiProNumber);
			$('#chsoftiProStartDate1').val(employee.chsoftiProStartdate);
			$('#chsoftiProName').val(employee.chsoftiProName);
			
			var type = $("#senddemandtype").val();
			if(type=="1"){
				$("#demandrr").val("");
				$("#demandrr").val(employee.demandrr);
			}
			if(type=="2"){
				$("#demandskill").val("");
				$("#demandskill").val(employee.demandskill);
			}
			if(type=="3"){
				$("#demandskill").val("");
				$("#demandskill").val(employee.demandskill);
			}
			
		}
	})
}

function dateType() {
	$('.form_datetime').datetimepicker({
		weekStart : 1,
		minView : 'month',
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		language : 'zh-CN',
		format : 'yyyy-mm-dd',
		pickerPosition : 'bottom-left',
		showMeridian : 1,
		endDate : new Date()
	}).on('changeDate', function(ev){
		 $('#updateEmployeeForm').bootstrapValidator('revalidateField', 'sowExpiredDate1'); 
		 $('#updateEmployeeForm').bootstrapValidator('revalidateField', 'graduationDate1');
		 $('#updateEmployeeForm').bootstrapValidator('revalidateField', 'entryDate1');
		 $('#updateEmployeeForm').bootstrapValidator('revalidateField', 'hsbcDOJ1');
		 $('#updateEmployeeForm').bootstrapValidator('revalidateField', 'terminatedDate1');
		 $('#updateEmployeeForm').bootstrapValidator('revalidateField', 'chsoftiProStartDate1');
	});;
}

function loadOnshoreOrOffshore(employee) {
	var url = path + '/json/onshoreOrOffshore.json'
	$.getJSON(url, function(data) {
		$.each(data, function(i, item) {
			$("#onshoreOrOffshore")
					.append("<option>" + item.name + "</option>");
		})
		$('#onshoreOrOffshore').val(employee.onshoreOrOffshore);
	});
}

function loadLocationType(employee) {
	var url = path + '/json/locationType.json'
	$.getJSON(url, function(data) {
		$.each(data, function(i, item) {
			$("#locationType").append("<option>" + item.name + "</option>");
		})
		$('#locationType').val(employee.locationType);
	});
}

function loadStaffLocation(employee) {
	var url = path + '/json/staffLocation.json'
	$.getJSON(url, function(data) {
		$.each(data, function(i, item) {
			$("#staffLocation").append("<option>" + item.name + "</option>");
		})
		$('#staffLocation').val(employee.staffLocation);
	});
}

function loadStaffRegion(employee) {
	var url = path + '/json/staffRegion.json'
	$.getJSON(url, function(data) {
		$.each(data, function(i, item) {
			$("#staffRegion").append("<option>" + item.name + "</option>");
		})
		$('#staffRegion').val(employee.staffRegion);
	});
}

function loadResourceStatus(employee) {
	var url = path + '/json/resourceStatus.json'
	$.getJSON(url, function(data) {
		$.each(data, function(i, item) {
			$("#resourceStatus").append("<option>" + item.name + "</option>");
		})
		$('#resourceStatus').val(employee.resourceStatus);
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
	});

}

$("#resourceStatus").bind("click", function() {
	if ($('#resourceStatus').val() == "Active") {
		$("#terminatedDateDiv").hide();
		$("#terminationReasonDiv").hide();
	} else if ($('#resourceStatus').val() == "Released") {
		$("#terminatedDateDiv").show();
		$("#terminationReasonDiv").hide();
	} else if ($('#resourceStatus').val() == "Transfer") {
		$("#terminatedDateDiv").show();
		$("#terminationReasonDiv").hide();
	}  else {
		$("#terminatedDateDiv").show();
		$("#terminationReasonDiv").show();
	}
});

function loadBillingCurrency(employee) {
	var url = path + '/json/billingCurrency.json'
	$.getJSON(url, function(data) {
		$.each(data, function(i, item) {
			$("#billingCurrency").append("<option>" + item.name + "</option>");
		})
		$('#billingCurrency').val(employee.billingCurrency);
	});
}

function loadBillingEntity() {
	var url = path + '/json/billingEntity.json'
	$.getJSON(url, function(data) {
		$.each(data, function(i, item) {
			$("#billingEntity").append("<option>" + item.name + "</option>");
		})
	});
}

function loadSkill(employee) {
	var url = path + '/json/skill.json'
	$.getJSON(url, function(data) {
		$.each(data, function(i, item) {
			$("#skill").append("<option>" + item.name + "</option>");
		})
		$('#skill').val(employee.skill);
	});
}

function loadSource() {
	var url = path + '/json/source.json'
	$.getJSON(url, function(data) {
		$.each(data, function(i, item) {
			$("#source").append("<option>" + item.name + "</option>");
		})
	});
}

function loadRole(employee) {
	var url = path + '/json/role.json'
	$.getJSON(url, function(data) {
		$.each(data, function(i, item) {
			$("#role").append("<option>" + item.name + "</option>");
		})
		$('#role').val(employee.role);
	});
}
function loadGbGf(employee){
	var url = path + '/service/hsbcDept/queryTopParent';
	$.getJSON(url, function(data){
		$.each(data, function(i, item){
			$("#gbGf").append("<option value='"+item.id+"'>"+item.name+"</option>");
		})
		$('#gbGf').val(employee.gbGf);
	});
}

function changeGBGF(){
	var id =$("#gbGf").val();
	$("#hsbcDept").empty();
	$("#hsbcSubDept").empty();
	$("#hsbcDept").append("<option value=''>-- Option --</option>");
	$("#hsbcSubDept").append("<option value=''>-- Option --</option>");
	$.ajax({
		url:path+'/service/hsbcDept/queryChild',
		dataType:"json",
		data:{"id":id},
		async:true,
		cache:false,
		type:"post",
		success:function(result){
			$.each(result, function(i, item){
				$("#hsbcDept").append("<option value='"+item.id+"'>"+item.name+"</option>");
			})
		}
	})
}

function changeHsbcDept(){
	var id =$("#hsbcDept").val();
	$("#hsbcSubDept").empty();
	$("#hsbcSubDept").append("<option value=''>-- Option --</option>");
	$.ajax({
		url:path+'/service/hsbcDept/queryChild',
		dataType:"json",
		data:{"id":id},
		async:true,
		cache:false,
		type:"post",
		success:function(result){
			$.each(result, function(i, item){
				$("#hsbcSubDept").append("<option value='"+item.id+"'>"+item.name+"</option>");
			})
		}
	})
}



function loadStaffCategory(employee) {
	var url = path + '/json/staffCategory.json'
	$.getJSON(url, function(data) {
		$.each(data, function(i, item) {
			$("#staffCategory").append("<option>" + item.name + "</option>");
		})
		$('#staffCategory').val(employee.staffCategory);
	});
}

function loadEngagementType(employee) {
	var url = path + '/json/engagementType.json'
	$.getJSON(url, function(data) {
		$.each(data, function(i, item) {
			$("#engagementType").append("<option>" + item.name + "</option>");
		})
		$('#engagementType').val(employee.engagementType);
	});
}

function loadCSDept(employee) {

	$.ajax({
		url : path + '/service/csDept/queryAllCSSubDept',
		dataType : "json",
		async : true,
		cache : false,
		type : "post",
		success : function(result) {
			var userType = result.user.userType;

			var csSubs = result.csSubDepts;

			for (var i = 0; i < result.data.length; i++) {
				$("#csSubDept").append(
						"<option value='" + result.data[i].csSubDeptId + "'>"
								+ result.data[i].csSubDeptName + "</option>");
			}
			$('#csSubDept').val(employee.csSubDept);
			if(userType!=0){
				$("#csSubDept").attr("disabled","disabled");
			}
		}
	})

}

function loadPersonHsbcDept(employee) {
	var combination = employee.hsbcSubDept;
	var cc = null;
	if(combination!=null && combination!="" && combination!=undefined){
		cc = combination.split(",");
	}
	
	var gbgf = employee.gbGf;
	//$("#hsbcDept").empty();
	//$("#hsbcSubDept").empty();
	//$("#hsbcDept").append("<option value=''>-- Option --</option>");
	//$("#hsbcSubDept").append("<option value=''>-- Option --</option>");
	$.ajax({
		url:path+'/service/hsbcDept/queryChild',
		dataType:"json",
		data:{"id":gbgf},
		async:true,
		cache:false,
		type:"post",
		success:function(result){
			$.each(result, function(i, item){
				$("#hsbcDept").append("<option value='"+item.id+"'>"+item.name+"</option>");
			})
			if(cc!=null){
				if(cc.length>0){
					$('#hsbcDept').val(cc[0]);
				}
			}
			
			loadHsbcSubDept(cc);
		}
	})
	/**var hsbcSubDeptId = employee.hsbcSubDept;
	if(hsbcSubDeptId==""||hsbcSubDeptId==null){
		loadFirstDept(employee);
	}else{
		$.ajax({
			url : path + '/service/hsbcDept/queryById',
			dataType : "json",
			async : true,
			cache : false,
			data : {
				"id" : hsbcSubDeptId
			},
			type : "post",
			success : function(hsbcDept) {

				loadDept(employee, hsbcDept);
			}
		})
	}*/
	
	
}

function loadHsbcSubDept(cc){
	//$("#hsbcSubDept").empty();
	//$("#hsbcSubDept").append("<option value=''>-- Option --</option>");
	$.ajax({
		url:path+'/service/hsbcDept/queryChild',
		dataType:"json",
		data:{"id":cc[0]},
		async:true,
		cache:false,
		type:"post",
		success:function(result){
			$.each(result, function(i, item){
				$("#hsbcSubDept").append("<option value='"+item.id+"'>"+item.name+"</option>");
			})
			if(cc!=null){
				if(cc.length>1){
					$('#hsbcSubDept').val(cc[1]);
				}
			}
		}
	})
}

/**function loadFirstDept(employee) {
	$.ajax({
		url : path + '/service/hsbcDept/queryDeptName',
		dataType : "json",
		async : true,
		cache : false,
		type : "post",
		success : function(list) {
			
			for (var i = 0; i < list.length; i++) {

				$("#hsbcDept").append(
						"<option value='" + list[i].hsbcSubDeptId + "'>"
								+ list[i].hsbcDeptName + "</option>");
			}

			loadHSBCSubDept(employee);
		}
	})
}*/

function loadDept(employee, hsbcDept) {
	$.each(hsbcDept, function(i, item){
		$("#hsbcDept").append("<option value='"+item.id+"'>"+item.name+"</option>");
	})
	/**$.ajax({
		url : path + '/service/hsbcDept/queryDeptName',
		dataType : "json",
		async : true,
		cache : false,
		type : "post",
		success : function(list) {
			
			var hsbcDeptName = hsbcDept.hsbcDeptName;

			for (var i = 0; i < list.length; i++) {

				$("#hsbcDept").append(
						"<option value='" + list[i].hsbcSubDeptId + "'>"
								+ list[i].hsbcDeptName + "</option>");
				if (list[i].hsbcDeptName == hsbcDeptName) {
					$("#hsbcDept").val(list[i].hsbcSubDeptId);
				}
			}

			loadHSBCSubDept(employee);
		}
	})*/
}

/**function loadHSBCSubDept(employee) {
	var hsbcSubDeptId = employee.hsbcSubDept;
	$.ajax({
		url : path + '/service/hsbcDept/querySubDeptName',
		dataType : "json",
		async : true,
		data : {
			"hsbcSubDeptId" : hsbcSubDeptId
		},
		cache : false,
		type : "post",
		success : function(list) {
			if (list.length == 1 && list[0].hsbcSubDeptName == null) {
				$("#hsbcSubDept").append(
						"<option value='"
								+ $('#hsbcDept').find("option:selected").val()
								+ "'>"
								+ $('#hsbcDept').find("option:selected").text()
								+ "</option>");
			} else {
				$("#hsbcSubDept").find("option").remove();
				$("#hsbcSubDept").append(
						"<option value=''>-- Option --</option>");
				for (var i = 0; i < list.length; i++) {
					$("#hsbcSubDept").append(
							"<option value='" + list[i].hsbcSubDeptId + "'>"
									+ list[i].hsbcSubDeptName + "</option>");
				}
			}
			if (hsbcSubDeptId != null && hsbcSubDeptId != '') {
				$("#hsbcSubDept").val(employee.hsbcSubDept)
			}
		}
	})
}*/

function loadTerminationReason(employee) {
	var url = path + '/json/terminatedReason.json'
	$.getJSON(url, function(data) {
		$.each(data, function(i, item) {
			$("#terminationReason").append(
					"<option value='" + item.name + "'>" + item.name
							+ "</option>");
			if (item.name == employee.terminationReason) {
				$('#terminationReason').val(item.name);
			}
		})
	});
}

$("#hsbcDept").change(
		function() {
			var hsbcSubDeptId = $("#hsbcDept").val();
			$.ajax({
				url : path + '/service/hsbcDept/querySubDeptName',
				dataType : "json",
				async : true,
				data : {
					"hsbcSubDeptId" : hsbcSubDeptId
				},
				cache : false,
				type : "post",
				success : function(list) {
					$("#hsbcSubDept").find("option").remove();
					if (list.length == 1 && list[0].hsbcSubDeptName == null) {
						$("#hsbcSubDept").append(
								"<option value='"
										+ $('#hsbcDept')
												.find("option:selected").val()
										+ "'>"
										+ $('#hsbcDept')
												.find("option:selected").text()
										+ "</option>");
					} else {
						$("#hsbcSubDept").find("option").remove();
						$("#hsbcSubDept").append(
								"<option value=''>-- Option --</option>");
						for (var i = 0; i < list.length; i++) {
							$("#hsbcSubDept").append(
									"<option value='" + list[i].hsbcSubDeptId
											+ "'>" + list[i].hsbcSubDeptName
											+ "</option>");
						}
					}
				}
			})
		})
		
		
//获取当前时间，格式YYYY-MM-DD
function getCurrentDate() {
     var date = new Date();
     var seperator1 = "-";
     var year = date.getFullYear();
     var month = date.getMonth() + 1;
     var strDate = date.getDate();
     if (month >= 1 && month <= 9) {
         month = "0" + month;
     }
     if (strDate >= 0 && strDate <= 9) {
         strDate = "0" + strDate;
     }
     var currentdate = year + seperator1 + month + seperator1 + strDate;
     return currentdate;
}


function cdemand(){
    //alert("加载需求");
	var type = $("#senddemandtype").val();
    $("#demandlist").modal('show');
    var queryUrl = path+'/service/demand/getDemand/'+type
    var table = $('#demandlist2').bootstrapTable({
        url: queryUrl,                      //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        //toolbar: '#toolbar',                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 8,                       //每页的记录行数（*）
        pageList: [8,10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,
        showColumns: false,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        //height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
        showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        singleSelect:true, 				//禁止多选_____
        //得到查询的参数
        queryParams : function (params) {
        	return {
        		pageSize: params.limit,
        		pageNumber: params.offset/params.limit+1,
            };
        },
        columns: [{
            checkbox: true,  
            visible: true                  //是否显示复选框  
        },{
            field: 'rr',
            title: 'RR',
            sortable: true
        },{
            field: 'jobCode',
            title: 'JobCode',
            sortable: true
        },{
            field: 'skill',
            title: 'Skill',
            sortable: true
        },{
            field: 'position',
            title: 'Position',
            sortable: true
        },{
            field: 'location',
            title: 'Location',
            sortable: true
        },
        {
            field: 'status',
            title: 'Status',
            sortable: true
        },
        {
            field: 'csDeptName',
            title: 'DU',
            sortable: true
        }/*,
        {
            field:'ID',
            title: 'Operation',
            width: 120,
            align: 'center',
            valign: 'middle'
        },*/ ],
        onLoadSuccess: function () {
        },
        onLoadError: function () {
           
        },
        onDblClickRow: function (row, $element) {
           
        },
    });
}

function sureDemand(type){
var demand = $('#demandlist2').bootstrapTable('getSelections');
//console.log(demand[0].demandId);

$("#demandlist").modal('hide');

if(type=="1"){
	$("#demandrr").val("");
	$("#demandrr").val(demand[0].rr);
}
if(type=="2"){
	$("#demandskill").val("");
	$("#demandskill").val(demand[0].skill);
}
if(type=="3"){
	$("#demandskill").val("");
	$("#demandskill").val(demand[0].skill);
}


$("#udemandid").val("");
$("#udemandid").val(demand[0].demandId);
//console.log($("#udemandid").val());
}

//查找需求
function searchDemand(type){
	if(type=="1"){
		var rr = $("#searchdemandrr").val();
		var jobcode = $("#searchjobcode").val(); 
		//alert(rr);
		var queryParams = { 
			query: {  
				rr:rr,
				jobcode:jobcode
		    }
		}  
		//刷新表格  
		$('#demandlist2').bootstrapTable('refresh',queryParams);  
	}
    if(type=="2"){
    	var searchdemandskill = $("#searchdemandskill").val();
    	var queryParams = { 
    		query: {  
    			skill:searchdemandskill
    	    }
    	}  
    	//刷新表格  
    	$('#demandlist2').bootstrapTable('refresh',queryParams);  
	}
    if(type=="3"){
    	var searchdemandskill = $("#searchdemandskill").val();
    	var queryParams = { 
    		query: {  
    			skill:searchdemandskill
    	    }
    	}  
    	//刷新表格  
    	$('#demandlist2').bootstrapTable('refresh',queryParams); 
	}

}

function loadBillRateType(){
	var url = path+'/json/billRateType.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#billRateType").append("<option value='"+item.name+"'>"+item.name+"</option>");
	       })
	});
}
function loadEmployeeType(){
	var url = path+'/json/employeeType.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#employeeType").append("<option value='"+item.name+"'>"+item.name+"</option>");
	       })
	});
}

function removeEmployeeValid(){
	
	var type = $("#employeeType").val();
	if(type=='HSBC'){
		alert("恢复验证");
		initEmployeeValidate();
	}else{
		$('#updateEmployeeForm').data('bootstrapValidator').destroy();
		$('#updateEmployeeForm').data('bootstrapValidator', null);
		
		/*$("#registerEmployeeForm").bootstrapValidator('resetForm');
		$("#registerEmployeeForm").bootstrapValidator('removeField','eHr');
		$("#registerEmployeeForm").bootstrapValidator('removeField','lob');
		$("#registerEmployeeForm").bootstrapValidator('removeField','hsbcStaffId');
		$("#registerEmployeeForm").bootstrapValidator('removeField','staffName');
		$("#registerEmployeeForm").bootstrapValidator('removeField','LN');
		$("#registerEmployeeForm").bootstrapValidator('removeField','hsbcProjectName');
		$("#registerEmployeeForm").bootstrapValidator('removeField','hsbcProjectManager');
		$("#registerEmployeeForm").bootstrapValidator('removeField','sow');
		$("#registerEmployeeForm").bootstrapValidator('removeField','graduationDate1');
		$("#registerEmployeeForm").bootstrapValidator('removeField','hsbcDOJ1');
		$("#registerEmployeeForm").bootstrapValidator('removeField','chsoftiProStartDate1');
		$("#registerEmployeeForm").bootstrapValidator('removeField','sowExpiredDate1');
		$("#registerEmployeeForm").bootstrapValidator('removeField','billRate');
		$("#registerEmployeeForm").bootstrapValidator('removeField','itworkyear');
		$("#registerEmployeeForm").bootstrapValidator('removeField','email');
		$("#registerEmployeeForm").bootstrapValidator('removeField','entryDate1');
		$("#registerEmployeeForm").bootstrapValidator('removeField','billRateType');
		$("#registerEmployeeForm").bootstrapValidator('removeField','csSubDept');
		$("#registerEmployeeForm").bootstrapValidator('removeField','gbGf');
		$("#registerEmployeeForm").bootstrapValidator('removeField','hsbcDept');
		$("#registerEmployeeForm").bootstrapValidator('removeField','hsbcSubDept');
		$("#registerEmployeeForm").bootstrapValidator('removeField','role');
		$("#registerEmployeeForm").bootstrapValidator('removeField','skill');
		$("#registerEmployeeForm").bootstrapValidator('removeField','nickName');*/
	}
}
function initEmployeeValidate(){
	$('#updateEmployeeForm').bootstrapValidator({
		message: 'This value is not valid',

        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	eHr: {
				validators: {
                    notEmpty: {
                        message: 'Please enter ehr'
                    },
                    regexp: {
                        regexp: /^E\d{9}$/,
                        message: 'Please enter the E-HR（E and 9 digits）'
                    },
                    

                 }
            },

            lob: {
                validators: {
                    notEmpty: {
                        message: 'Please enter lob'
                    },
                    numeric: {
                    	message:'Please enter number'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxlength'
                    },
                }
            },
            hsbcStaffId: {
                validators: {
                	notEmpty: {
                        message: 'Please enter staffid'
                    },
                    /*numeric: {
                	message:'Please enter number'
                    },*/
				    regexp:{
        			   regexp:/^[0-9a-zA-Z\s?]+$/,
        			   message:'please enter right format.'
        		    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxLength'
                    },
                   
                }
            },
            staffName: {
                validators: {
                    notEmpty: {
                        message: 'Please enter staffname'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxlength'
                    },

                }
            },
            staffLocation: {
                validators: {
                    notEmpty: {
                        message: 'Please select stafflocation'
                    }
                }
            },
            locationType: {
                validators: {
                    notEmpty: {
                        message: 'Please select locationtype'
                    }
                }
            },
            csSubDept: {
                validators: {
                    notEmpty: {
                        message: 'Please select csdept'
                    }
                }
            },
            gbGf: {
                validators: {
                    notEmpty: {
                        message: 'Please select gbgf'
                    }
                }
            },
            hsbcDept: {
                validators: {
                    notEmpty: {
                        message: 'Please select dept'
                    }
                }
            },
            hsbcSubDept: {
                validators: {
                    notEmpty: {
                        message: 'Please select subdept'
                    }
                }
            },
            staffCategory: {
                validators: {
                    notEmpty: {
                        message: 'Please select staffcatefory'
                    }
                }
            },
            engagementType: {
                validators: {
                    notEmpty: {
                        message: 'Please select engagement'
                    }
                }
            },
            staffRegion: {
                validators: {
                    notEmpty: {
                        message: 'Please select staffregion'
                    }
                }
            },
            onshoreOrOffshore: {
                validators: {
                    notEmpty: {
                        message: 'Please select value'
                    }
                }
            },
            role: {
                validators: {
                    notEmpty: {
                        message: 'Please select msarole'
                    }
                }
            },
            skill: {
                validators: {
                    notEmpty: {
                        message: 'Please select skills'
                    }
                }
            },
            billingCurrency: {
                validators: {
                    notEmpty: {
                        message: 'Please select billingcurrency'
                    }
                }
            },
            billRateType: {
                validators: {
                    notEmpty: {
                        message: 'Please select billratetype'
                    }
                }
            },
            resourceStatus: {
                validators: {
                    notEmpty: {
                        message: 'Please select resourcestatus'
                    }
                }
            },
            LN: {
                validators: {
                	notEmpty: {
                        message: 'Please enter ln'
                    },
            		regexp:{
            			regexp:/^[0-9a-zA-Z\s?]+$/,
            			message:'please enter english.'
            		},
            		stringLength: {
                        max: 32,
                        message: 'Exceeded the maxlength'
                    },
                }
            },
            
            hsbcProjectName: {
                validators: {
                    notEmpty: {
                        message: 'Please enter projectname'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxlength'
                    },
            		
                }
            },
        
            hsbcProjectManager: {
                validators: {
                    notEmpty: {
                        message: 'Please select projectmanager'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxlength'
                    },

                }
            },
            sow: {
                validators: {
                    notEmpty: {
                        message: 'Please select sow'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxlength'
                    },

                }
            },
            billRate: {
                validators: {
                    notEmpty: {
                        message: 'Please Choose billrate'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxlength'
                    },
                    regexp:{
            			regexp:/^([1-9]\d*(\.\d*[0-9])?(\/(m|M))?)$|^(0\.\d*[0-9](\/(m|M))?)$/,
            			message:'Please enter the correct format.'
            		},

                }
            },
            itworkyear: {
                validators: {
                    notEmpty: {
                        message: 'Please enter itworkyear'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxlength'
                    },
                    regexp:{
            			regexp:/^([1-9]\d*(\.\d*[0-9])?(\/(m|M))?)$|^(0\.\d*[0-9](\/(m|M))?)$/,
            			message:'Please enter the correct format.'
            		},
                }
            },
            email: {
                validators: {
                	notEmpty: {
                		message: 'Please enter email'
                	},
                    emailAddress:{
                    	message:'Email address is incorrect'
                    },
                    stringLength: {
                        max: 100,
                        message: 'Exceeded the maxlength'
                    },
                }
            },
            sowExpiredDate1: {
                validators: {
                    notEmpty: {
                        message: 'Please select sowexpireddate'
                    },
                    date : {  
                        format : 'YYYY-MM-DD',  
                        message : 'Time format is incorrect'  
                    }

                }
            },
            graduationDate1: {
                validators: {
                    notEmpty: {
                        message: 'Please select graduation date'
                    },
                    date : {  
                        format : 'YYYY-MM-DD',  
                        message : 'Time format is incorrect'  
                    }

                }
            },
            entryDate1: {
            	validators: {
                notEmpty: {
                    message: 'Please select entrydate'
                },
                date : {  
                    format : 'YYYY-MM-DD',  
                    message : 'Time format is incorrect'  
                }

            }
        },
        hsbcDOJ1: {
            validators: {
                notEmpty: {
                    message: 'Please select doj'
                },
                date : {  
                    format : 'YYYY-MM-DD',  
                    message : 'Time format is incorrect'  
                }

            }
        },
        chsoftiProStartDate1: {
            validators: {
                notEmpty: {
                    message: 'Please select chsoftiprostartdate'
                },
                date : {
                    format : 'YYYY-MM-DD',
                    message : 'Time format is incorrect'
                }
            }
        },
        terminatedDate1: {
            validators: {
                notEmpty: {
                    message: 'Please select terminatedDate'
                },
                date : {  
                    format : 'YYYY-MM-DD',  
                    message : 'Time format is incorrect'  
                }

            }
        },
        }
    }) 
}
