var csDeptName0 = "";

var csSubDeptName0 = "";

var csBuName0 = "";

var lobArray = [];
var staffNameArray = [];
$(function() {
	loadCusDeptInfo();
	loadEmployeeList();
	dateType1();
	loadRole();
	loadSkill();
	loadStaffRegion();
	//loadDept();
	batchMaintenanceValidate();
	loadGBGF();
})

$("#pageRecordsNum").change(function() {
	var csDeptName = $("#csDept").find("option:selected").text();

	if (csDeptName.indexOf('Option') != -1) {
		csDeptName = "";
	}

	var csBuName = $("#csBu").find("option:selected").text();

	if (csBuName.indexOf('Option') != -1) {
		csBuName = "";
	}

	var csSubDeptName = $("#csSubDept").find("option:selected").text();

	if (csSubDeptName.indexOf('Option') != -1) {
		csSubDeptName = "";
	}

	var engagementType = $("#engagementType").find("option:selected").text();

	if (engagementType.indexOf('Option') != -1) {
		engagementType = "";
	}

	loadEmployeeList("", csDeptName, csSubDeptName, csBuName, engagementType);

})
$("#csSubDept").change(function() {
	var bu = $("#csBu").val();
	var du = changeCSDeptToId($("#csSubDept").val());
	loadUserForRM(bu, du, "");
})

$("#csBu").change(function() {
	var bu = $("#csBu").val();
	var du = changeCSDeptToId($("#csSubDept").val());
	loadUserForRM(bu, du, "");
})

function loadGBGF(){
	var url = path + '/service/hsbcDept/queryTopParent';
	$.getJSON(url, function(data){
		$.each(data, function(i, item){
			$("#gbGf").append("<option value='"+item.id+"'>"+item.name+"</option>");
		})
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
function changeHSBCDept(){
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
/*function loadDept() {
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
		}
	})
}*/

function loadSkill() {
	var url = path + '/json/skill.json'
	$.getJSON(url, function(data) {
		$.each(data, function(i, item) {
			$("#skill").append("<option>" + item.name + "</option>");
		})
	});
}

var regionMap = new Map();
function loadStaffRegion() {
	var url = path + '/json/staffRegion.json'
	$.getJSON(url, function(data) {
		$.each(data, function(i, item) {
			$("#staffRegion").append("<option>" + item.name + "</option>");
			regionMap.set(item.name, item.key);
		})
	});
}
function loadResourceStatus(result) {
	var url = path + '/json/resourceStatus.json'
	$.getJSON(url, function(data) {
		$("#resourceStatus").empty();
		$("#resourceStatus").append("<option value=''>--Option--</option>");
		$.each(data, function(i, item) {
			$("#resourceStatus").append("<option>" + item.name + "</option>");
		})
		$('#resourceStatus').val(result.pageInfo.resourceStatus);
	});
}

function selectAll() {

	$("input[type='checkbox']").attr("checked", 'true');
}

function exportCondition() {
	var lb = $("label input");
	var condition = "";
	for (var i = 0; i < lb.length; i++) {
		if (lb.eq(i).is(':checked')) {
			condition += lb.eq(i).attr("name") + ",";
		}
	}

	$.ajax({
		url : path + '/service/employeeInfo/setEmpConditon',
		dataType : "json",
		data : {
			"condition" : condition
		},
		async : true,
		cache : false,
		type : "post",
		success : function(resultFlag) {
			var url = path + '/service/employee/exportExcel';
			$("#exceltHref").attr("href", url);
			document.getElementById("exceltHref").click();
			$('#myModal').modal('hide');
			$("[type='checkbox']").removeAttr("checked");

		}
	})

}

var allCSSubDept;
var userType;
function loadCSSubDept(result) {
	userType = result.user.userType;
	var csSubDeptNames = result.csSubDeptNames;
	$
			.ajax({
				url : path + '/service/csDept/queryAllCSSubDeptName',
				dataType : "json",
				async : true,
				cache : false,
				type : "post",
				success : function(list) {
					allCSSubDept = list;
					$("#csSubDept").empty();
					$("#csSubDept").append(
							"<option value=''>--Option--</option>");
					for (var i = 0; i < list.length; i++) {
						$("#csSubDept").append(
								"<option>" + list[i].csSubDeptName
										+ "</option>");
					}

					if (userType == '3' || userType == '5' || userType == '11'
							|| userType == '12' || userType == '13'
							|| userType == '14') {
						if (csSubDeptNames.length == 1) {
							$('#csSubDept').val(result.csSubDeptNames[0]);
							$("#csSubDept").attr("disabled", "disabled");
						} else if (csSubDeptNames.length > 1) {
							$("#csSubDept").empty();
							for (var i = 0; i < csSubDeptNames.length; i++) {
								$("#csSubDept").append(
										"<option>" + csSubDeptNames[i]
												+ "</option>");
								$('#csSubDept').val(
										result.pageInfo.csSubDeptName);
							}
						}
					} else {
						$('#csSubDept').val(result.pageInfo.csSubDeptName);
					}
				}
			})
}

function editEmployeeInfo(employeeId) {
	$("#editForm").attr("action",
			path + "/service/employee/updateEmployeeInfo.html");
	$("#employeeId").val(employeeId);
	$("#editForm").submit();
}

function loadCSBu(result) {
	var csBuNames = result.csBuNames;
	var userType = result.user.userType;
	var url = path + '/json/csBuName.json'
	$.getJSON(url, function(data) {
		$("#csBu").empty();
		$("#csBu").append("<option value=''>--Option--</option>");
		$.each(data, function(i, item) {
			$("#csBu").append("<option>" + item.name + "</option>");
		})
		if (userType == '3' || userType == '5' || userType == '11'
				|| userType == '12' || userType == '13' || userType == '14') {
			if (csBuNames.length == 1) {
				$('#csBu').val(result.user.bu);
				$("#csBu").attr("disabled", "disabled");
			} else if (csBuNames.length > 1) {
				$("#csBu").empty();
				for (var i = 0; i < csBuNames.length; i++) {
					$("#csBu").append("<option>" + csBuNames[i] + "</option>");
					$('#csBu').val(result.pageInfo.csbuName);
				}
			}
		} else {
			$('#csBu').val(result.pageInfo.csbuName);
		}
	});
}

function loadEmployeeList(pageState, csDeptName, csSubDeptName, csBuName,
		engagementType) {
	var csDeptName = csDeptName;

	csDeptName0 = csDeptName;

	var csSubDeptName = csSubDeptName;

	csSubDeptName0 = csSubDeptName;

	var csBuName = csBuName;

	csBuName0 = csBuName;

	var engagementType = engagementType;

	var hsbcStaffId = $("#hsbcStaffId").val();

	var eHr = $("#eHr").val();

	var lob = $("#lob").val();

	var resourceStatus = $("#resourceStatus").val();

	var staffName = $("#staffName").val();

	var rmName = $("#RM").val();

	var pageState = pageState;

	var pageRecordsNum = $("#pageRecordsNum").find("option:selected").text();

	$.ajax({
				url : path + "/service/employeeInfo/queryBatchEmployeeList",
				dataType : "json",
				async : true,
				data : {
					"staffName" : staffName,
					"resourceStatus" : resourceStatus,
					"pageState" : pageState,
					"csBuName" : csBuName,
					"csSubDeptName" : csSubDeptName,
					"hsbcStaffId" : hsbcStaffId,
					"eHr" : eHr,
					"lob" : lob,
					"rmUserId" : rmName,
					"engagementType" : engagementType,
					"pageRecordsNum" : pageRecordsNum
				},
				cache : false,
				type : "post",
				success : function(result) {
					$("#employeeList tbody").remove();

					var tbody = $("<tbody>");
					tbody.appendTo($("#employeeList"));

					if (result.data.length > 0) {
						$("#exportExcel").removeAttr("disabled");
					}
					var userType = result.user.userType;
					for (var i = 0; i < result.data.length; i++) {
						var staffName = result.data[i].staffName;
						var lob = result.data[i].lob;
						var employeeId = result.data[i].employeeId;
						var tr = $("<tr></tr>");
						tr.appendTo(tbody);
						
						var td0 = $("<td><input id='ls"
								+ employeeId
								+ "' type='checkbox' name='checkedEmployee[]' value='"
								+ lob + "," + staffName + ","
								+ employeeId + "' onclick='checkedEmployee(\""
								+ lob + "\",\"" + staffName + "\",\""
								+ employeeId + "\")' ></td>");
						var td1 = $("<td>"
								+ (i+1)
								+ "</td>");
						var td2 = $("<td>" + result.data[i].staffName + "</td>");
						var td3 = $("<td>" + result.data[i].eHr + "</td>");
						var td4 = $("<td>" + result.data[i].lob + "</td>");
						var td5 = $("<td>" + result.data[i].hsbcStaffId
								+ "</td>");
						var td6 = $("<td>" + result.data[i].csSubDeptName
								+ "</td>");
						var td7 = $("<td><center>"
								+ result.data[i].engagementType
								+ "</center></td>");
						var td8 = $("<td>" + result.data[i].resourceStatus
								+ "</td>");
						var td81 = $("<td>" + result.data[i].nickname + "</td>");
						// var td7 = $("<td><a class='btn btn-info'
						// href='javascript:void(0);'> <i class='glyphicon
						// glyphicon-edit icon-white'></i> 编辑</a></td>");
						var td9 = null;
						var engagementType = result.data[i].engagementType
								.replace(/\s+/g, "");
						td9 = $("<td><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=employeeDetail('"
								+ result.data[i].employeeId
								+ "','"
								+ engagementType + "')>Detail</a></td>");

						if ((result.data[i].hsbcStaffId) == null) {
							var td5 = $("<td></td>");
						}
						if ((result.data[i].eHr) == null) {
							var td3 = $("<td></td>");
						}
						if ((result.data[i].lob) == null) {
							var td4 = $("<td></td>");
						}
						if ((result.data[i].csSubDeptName) == null) {
							var td6 = $("<td></td>");
						}
						if ((result.data[i].resourceStatus) == null) {
							var td8 = $("<td></td>");
						}
						if ((result.data[i].nickname) == null) {
							var td81 = $("<td></td>");
						}

						td0.appendTo(tr);
						td1.appendTo(tr);
						td2.appendTo(tr);
						td3.appendTo(tr);
						td4.appendTo(tr);
						td5.appendTo(tr);
						td6.appendTo(tr);
						td7.appendTo(tr);
						td8.appendTo(tr);
						td81.appendTo(tr);
						td9.appendTo(tr);

						// gkf add 防止页面list刷新所选的checkbox失去焦点
						for ( var index in lobArray) {
							if (result.data[i].employeeId == lobArray[index]) {
								$('#ls' + employeeId + '').attr("checked",
										'true');
							}
						}
						//全选效果
						if(contains(lobArray,result.data[i].employeeId)){
							document.getElementById('employeeIds').checked=true;
						}else{
							if(document.getElementById('employeeIds').checked){
								document.getElementById('employeeIds').checked=false;
							}
						}
						
					}
					$("#employeeList").append("</tbdoy>");
					// alert(window.location.href);
					var pageNum = parseInt(result.pageInfo.currentPage);
					var pageRecordsNum = parseInt(result.pageInfo.pageRecordsNum);
					pageNum = pageNum / pageRecordsNum + 1;
					var totalPage = parseInt(result.pageInfo.pageCount);
					$("#pageCount").html(totalPage);
					$("#currentPage").html(pageNum);
					$("#nextPage").attr("onclick", "loadEmployeeList('next')");
					$("#previousPage").attr("onclick",
							"loadEmployeeList('previous')");
					if (pageNum == totalPage) {
						$("#nextPage").removeAttr("onclick");
					}
					if (pageNum == 1) {
						$("#previousPage").removeAttr("onclick");
					}

					loadResourceStatus(result);

					loadCSSubDept(result);

					loadCSBu(result);

					loadEngagementType(result)

					loadUserForRM(result.pageInfo.csbuName, result.csSubDeptId,
							result.pageInfo.rmUserId);

					rmAuthority(result);
					
				}

			})
			
}

function contains(arr, obj) {  
    var i = arr.length;  
    while (i--) {  
        if (arr[i] === obj) {  
            return true;  
        }  
    }  
    return false;  
} 

// RM修改权限
function rmAuthority(result) {

	var userType = result.user.userType;
	if (userType == '5') {
		$("#modifyName option[value='4']").remove();
	}

}

function loadUserForRM(bu, du, pageRMUserId) {
	$.ajax({
		url : path + '/service/user/getUserForRM',
		dataType : "json",
		async : true,
		cache : false,
		type : "post",
		success : function(list) {
			$("#RM").empty();
			$("#RM").append("<option value=''>--Option--</option>");
			var RMList = new Array();
			if (bu != null && bu != "" && du != null && du != "") {
				for (var i = 0; i < list.length; i++) {
					var csDeptIds = list[i].csdeptId.split(",");
					for (var j = 0; j < csDeptIds.length; j++) {
						if (bu == list[i].bu && du == csDeptIds[j]) {
							RMList.push(list[i]);
						}
					}
				}
			} else if ((bu == null || bu == "") && (du != null && du != "")) {
				for (var i = 0; i < list.length; i++) {
					var csDeptIds = list[i].csdeptId.split(",");
					for (var j = 0; j < csDeptIds.length; j++) {
						if (du == csDeptIds[j]) {
							RMList.push(list[i]);
						}
					}
				}
			} else if ((du == null || du == "") && (bu != null && bu != "")) {
				for (var i = 0; i < list.length; i++) {
					var csDeptIds = list[i].csdeptId.split(",");
					for (var j = 0; j < csDeptIds.length; j++) {
						if (bu == list[i].bu) {
							RMList.push(list[i]);
						}
					}
				}

			} else {
				for (var i = 0; i < list.length; i++) {
					RMList.push(list[i]);
				}

			}
			// remove duplicates
			var newRMList = new Array();
			for (var i = 0; i < RMList.length; i++) {
				if (newRMList.indexOf(RMList[i]) == -1) {
					newRMList.push(RMList[i]);
				}
			}

			for (var i = 0; i < newRMList.length; i++) {
				$("#RM").append(
						"<option value='" + newRMList[i].userId + "'>"
								+ newRMList[i].nickname + "</option>")
			}
			$('#RM').val(pageRMUserId);

			var currentUserType = userType;

			// RM 只能显示和修改自己管理的员工
			if (currentUserType == '5') {
				$("#RM").attr("disabled", "disabled");
			}

			// load RM list for rmName
			$("#rmName").empty();
			$("#rmName").append("<option value=''>--Option--</option>");
			for (var i = 0; i < newRMList.length; i++) {
				$("#rmName").append(
						"<option value='" + newRMList[i].userId + "'>"
								+ newRMList[i].nickname + "</option>")
			}

			$('#rmName').val(pageRMUserId);
		}
	})
}

function changeCSDeptToId(du) {
	var list = allCSSubDept;
	var csDeptId;
	for (var i = 0; i < list.length; i++) {
		if (list[i].csSubDeptName == du) {
			csDeptId = list[i].csSubDeptId;
			break;
		}
	}
	return csDeptId;
}

function dateType1() {
	$('.form_datetime1').datetimepicker({
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
		showMeridian : 1
	}).on(
			'changeDate',
			function(ev) {
				$('#modifyForm').bootstrapValidator(
						'revalidateField', 'sowExpiredDate1');

			});
}
function loadEngagementType(result) {
	var url = path + '/json/engagementType.json'
	$.getJSON(url, function(data) {
		$("#engagementType").empty();
		$("#engagementType").append("<option value=''>--Option--</option>");
		$.each(data, function(i, item) {
			$("#engagementType").append("<option>" + item.name + "</option>");
		})
		$('#engagementType').val(result.pageInfo.engagementType);

		$("#engagementType2").empty();
		$("#engagementType2").append("<option value=''>--Option--</option>");
		$.each(data, function(i, item) {
			$("#engagementType2").append("<option>" + item.name + "</option>");
		})
		$('#engagementType').val(result.pageInfo.engagementType);
	});
}
function loadRole() {
	var url = path + '/json/role.json'
	$.getJSON(url, function(data) {
		$.each(data, function(i, item) {
			$("#role").append("<option>" + item.name + "</option>");
		})
	});
}

function employeeDetail(employeeId, engagementType) {
	if (engagementType == "Time&Material" || engagementType == "TeamDelivery") {
		$("#editForm").attr("action",
				path + "/service/interview/employeeDetailInfo.html?type=1");
	} else if (engagementType == "FixedPrice") {
		$("#editForm").attr("action",
				path + "/service/interview/employeeDetailInfo.html?type=2");
	} else if (engagementType == "Support") {
		$("#editForm").attr("action",
				path + "/service/interview/employeeDetailInfo.html?type=3");
	} else {
		$("#editForm").attr("action",
				path + "/service/interview/employeeDetailInfo.html?type=1");
	}
	$("#employeeId").val(employeeId);
	$("#editForm").submit();
}

$("#hsbcDept").change(
		function() {
			var hsbcSubDeptId = $('#hsbcDept').val();
			$("#projectName").find("option").remove();
			$("#projectName").append("<option value=''>-- 请选择项目 --</option>");
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
					// if(list.length == 1 && list[0].hsbcSubDeptName == null){
					// $("#hsbcSubDept").append("<option
					// value='"+$('#hsbcDept').find("option:selected").val()+"'>"+$('#hsbcDept').find("option:selected").text()+"</option>");
					// }else{
					$("#hsbcSubDept").append(
							"<option value=''>-- Option --</option>");
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
						for (var i = 0; i < list.length; i++) {
							$("#hsbcSubDept").append(
									"<option value='" + list[i].hsbcSubDeptId
											+ "'>" + list[i].hsbcSubDeptName
											+ "</option>");
						}
					}

					// }
				}
			})
		})

$("#hsbcSubDept").change(
		function() {
			var hsbcSubDeptId = $('#hsbcSubDept').val();
			$.ajax({
				url : path + '/service/hsbcProject/queryprojcetName',
				dataType : "json",
				async : true,
				data : {
					"hsbcSubDeptId" : hsbcSubDeptId
				},
				cache : false,
				type : "post",
				success : function(list) {
					$("#projectName").find("option").remove();
					$("#projectName").append(
							"<option value=''>-- 请选择项目 --</option>");
					for (var i = 0; i < list.length; i++) {
						$("#projectName").append(
								"<option value='" + list[i].hsbcProjectId
										+ "'>" + list[i].hsbcProjectName
										+ "</option>");
					}
				}
			})
		})

$('#searchBtn').bind("click", function() {
	var csDeptName = $("#csDept").find("option:selected").text();

	if (csDeptName.indexOf('Option') != -1) {
		csDeptName = "";
	}

	var csBuName = $("#csBu").find("option:selected").text();

	if (csBuName.indexOf('Option') != -1) {
		csBuName = "";
	}

	var csSubDeptName = $("#csSubDept").find("option:selected").text();

	if (csSubDeptName.indexOf('Option') != -1) {
		csSubDeptName = "";
	}

	var engagementType = $("#engagementType").find("option:selected").text();

	if (engagementType.indexOf('Option') != -1) {
		engagementType = "";
	}

	loadEmployeeList("", csDeptName, csSubDeptName, csBuName, engagementType);
});
var num = 0;
function checkedEmployee(lob, staffName, employeeId) {
	if ($('#ls' + employeeId + '').is(':checked')) {
		lobArray.push(employeeId);
		staffNameArray.push(staffName + ":" + lob);
		$('#transferBox').show();
		$('#transBox').val(staffNameArray + ";");
		++num;
	} else {
		for (var i = 0; i < lobArray.length; i++) {
			if (employeeId == lobArray[i]) {
				lobArray.splice(i, 1);
				if (lobArray.length == 0) {
					$('#transferBox').hide();
				}
				--num;
			}
		}
		for (var i = 0; i < staffNameArray.length; i++) {
			if ((staffName + ":" + lob) == staffNameArray[i]) {
				staffNameArray.splice(i, 1);
				$('#transBox').val(staffNameArray);
			}
		}
	}
	//document.getElementById("num").innerHTML="Employee("+ num +")";
	$("#num").html("Employee("+ num +")");
}
var checkEmployeeValue = [];
function checkedEmployeeAll(){
	
	var all=document.getElementById('employeeIds');//获取到点击全选的那个复选框的id  
	var one=document.getElementsByName('checkedEmployee[]');//获取到复选框的名称  
	if(all.checked==true){
		for(var i=0;i<one.length;i++){ 
			if(one[i].checked==false){
				one[i].checked=true; 
				checkEmployeeValue = one[i].value.split(",");
				checkedEmployee(checkEmployeeValue[0],checkEmployeeValue[1],checkEmployeeValue[2])
			}
		} 
	}else{
		for(var i=0;i<one.length;i++){  
			one[i].checked=false;
			checkEmployeeValue = one[i].value.split(",");
			checkedEmployee(checkEmployeeValue[0],checkEmployeeValue[1],checkEmployeeValue[2])
		} 
	}
	
};
$("#modifyName").bind("click", function() {
	var result = $("#modifyName").find("option:selected").val();
	if (result == 0) {
		$("#projectProperties").hide();
		$("#humanRole").hide();
		$("#departmentModify").hide();
		$("#nickName").hide();
	} else if (result == 1) {
		$("#projectProperties").show();
		$("#humanRole").hide();
		$("#departmentModify").hide();
		$("#nickName").hide();
	} else if (result == 2) {
		$("#projectProperties").hide();
		$("#humanRole").show();
		$("#departmentModify").hide();
		$("#nickName").hide();
	} else if (result == 3) {
		$("#projectProperties").hide();
		$("#humanRole").hide();
		$("#nickName").hide();
		$("#departmentModify").show();
	} else if (result = 4) {
		$("#projectProperties").hide();
		$("#humanRole").hide();
		$("#departmentModify").hide();
		$("#nickName").show();
	}

})

// 批量异动部门
$('#transfer').bind("click", function() {
	if (lobArray.length == 0) {
		alert("Please choose employees.");
	} else {
		$('#modifyMadal').modal('show');
		$('#staffNames').val(staffNameArray);
		$('#staffIds').val(lobArray);
	}
});
function loadCusDeptInfo() {
	$.ajax({
		url : path + '/service/candidate/loadCusDeptInfo',
		dataType : "json",
		async : true,
		cache : false,
		type : "post",
		success : function(list) {
			for (var i = 0; i < list.length; i++) {
				$("#csSubdeptName").append(
						"<option value='" + list[i].csSubDeptId + "'>"
								+ list[i].csSubDeptName + "</option>");
			}
		}
	})
}
// 校验
function batchMaintenanceValidate() {
	$("#modifyForm").bootstrapValidator({
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			modifyName : {
				group : '.group',
				validators : {
					notEmpty : {
						message : 'please choose modify type.'
					}
				}
			},
			projectName : {
				group : '.group',
				validators : {
					notEmpty : {
						message : 'please input projectName.'
					}
				}
			},
			sowName : {
				group : '.group',
				validators : {
					notEmpty : {
						message : 'please input sowName.'
					}
				}
			},
			sowExpiredDate1 : {
				group : '.group',
				validators : {
					notEmpty : {
						message : 'please input sowExpiredDate.'
					}
				}
			},
			engagementType : {
				group : '.group',
				validators : {
					notEmpty : {
						message : 'please choose engagementType.'
					}
				}
			},

			role : {
				group : '.group',
				validators : {
					notEmpty : {
						message : 'please choose MSA Role.'
					}
				}
			},
			skill : {
				group : '.group',
				validators : {
					notEmpty : {
						message : 'please choose skill.'
					}
				}
			},
			staffRegion : {
				group : '.group',
				validators : {
					notEmpty : {
						message : 'please choose staffRegion.'
					}
				}
			},
			billRate: {
                validators: {
                    notEmpty: {
                        message: 'Please enter billRate'
                    },
                    
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxLength'
                    },
                    regexp:{
            			regexp:/^([1-9]\d*(\.\d*[0-9])?(\/(m|M))?)$|^(0\.\d*[0-9](\/(m|M))?)$/,
            			message:'Please enter the correct format.'
            		},
                }
            },

			hsbcDept : {
				group : '.group',
				validators : {
					notEmpty : {
						message : 'please choose hsbcDept.'
					}
				}
			},
			hsbcSubDept : {
				group : '.group',
				validators : {
					notEmpty : {
						message : 'please choose hsbcSubDept.'
					}
				}
			},
			hsbcManager : {
				group : '.group',
				validators : {
					notEmpty : {
						message : 'please input hsbcManager.'
					}
				}
			},

			rmName : {
				group : '.group',
				validators : {
					notEmpty : {
						message : 'please choose rmName.'
					}
				}
			},
		}
	});
}

//Modal验证销毁重构
$('#modifyMadal').on('hidden.bs.modal', function() {
    $("#modifyForm").data('bootstrapValidator').destroy();
    $('#modifyForm').data('bootstrapValidator', null);
    batchMaintenanceValidate();
});

$("#transSubmit").on("click", function() {
	 var bootstrapValidator = $("#modifyForm").data('bootstrapValidator');
	   bootstrapValidator.validate();
	   if(bootstrapValidator.isValid()){
		   var result = $("#modifyName").find("option:selected").val();
			if (result == 1) {
				updateProperties();
			} else if (result == 2) {
				updateRoles();
			} else if (result == 3) {
				updateDept();
			} else {
				updateRM();
			}
	   }
	
});
function updateProperties() {
	var staffIds = $("#staffIds").val();
	var projectName = $("#projectName").val();
	var sowNum = $("#sowName").val();
	var sowExpiredDate = $("#sowExpiredDate1").val();
	var engagementType = $("#engagementType2").find("option:selected").val();
	$.ajax({
		url : path + '/service/employee/propertiesModify',
		dataType : "json",
		async : true,
		data : {
			"staffIds" : staffIds,
			"projectName" : projectName,
			"sowNum" : sowNum,
			"sowExpiredDate" : sowExpiredDate,
			"engagementType" : engagementType
		},
		cache : false,
		type : "post",
		success : function(result) {
			if (result) {
				alert("Information modified succesffully.");
				$("#modifyName").val("");
				$("#projectProperties").hide();
				$("#projectName").val("");
				$("#sowName").val("");
				$("#sowExpiredDate1").val("");
				$("#engagementType").val("");
				$('#modifyMadal').modal('hide');
				loadEmployeeList();

			} else {
				alert("Information modified unsuccesffully.")
			}

		}
	})
}

function updateRoles() {
	var staffIds = $("#staffIds").val();
	var msaRole = $("#role").find("option:selected").val();
	var skill = $("#skill").find("option:selected").val();
	var staffRegion = $("#staffRegion").find("option:selected").val();
	var billRate = $("#billRate").val();
	$.ajax({
		url : path + '/service/employee/rolesModify',
		dataType : "json",
		async : true,
		data : {
			"staffIds" : staffIds,
			"msaRole" : msaRole,
			"skill" : skill,
			"staffRegion" : staffRegion,
			"billRate" : billRate
		},
		cache : false,
		type : "post",
		success : function(result) {
			if (result) {
				alert("Information modified succesffully.");
				$('#modifyMadal').modal('hide');
				$("#modifyName").val("");
				$("#humanRole").hide();
				$("#role").val("");
				$("#skill").val("");
				$("#staffRegion").val("");
				$("#billRate").val("");
				loadEmployeeList();
			} else {
				alert("Information modified unsuccesffully.")
			}

		}
	})
}

function updateDept() {
	var staffIds = $("#staffIds").val();
	var gbgf = $("#gbGf").find("option:selected").val();
	var hsbcDept = $("#hsbcDept").find("option:selected").val();
	var hsbcSubDept = $("#hsbcSubDept").find("option:selected").val();
	var hsbcManager = $("#hsbcManager").val();
	var zuhe=hsbcDept+","+hsbcSubDept;

	$.ajax({
		url : path + '/service/employee/deptModify',
		dataType : "json",
		async : true,
		data : {
			"staffIds" : staffIds,
			"hsbcDept" : hsbcDept,
			"hsbcSubDept" : zuhe,
			"gbgf" : gbgf,
			"hsbcManager" : hsbcManager
		},
		cache : false,
		type : "post",
		success : function(result) {
			if (result) {
				alert("Information modified succesffully.");
				$('#modifyMadal').modal('hide');
				$("#modifyName").val("");
				$("#departmentModify").hide();
				$("#hsbcDept").val("");
				$("#hsbcSubDept").val("");
				$("#hsbcManager").val("");

				loadEmployeeList();
			} else {
				alert("Information modified unsuccesffully.")
			}

		}
	})
}

// gkf add
function updateRM() {
	var rmUserId = $("#rmName").find("option:selected").val();
	var nickName = $("#rmName").find("option:selected").text();
	var staffIds = $("#staffIds").val();
	$.ajax({
		url : path + '/service/employee/updateRM',
		dataType : 'json',
		async : true,
		data : {
			"staffIds" : staffIds,
			"nickName" : nickName,
			"rmUserId":  rmUserId
		},
		cache : false,
		type : "post",
		success : function(result) {
			if (result) {
				alert("Information modified succesffully.");
				$('#modifyMadal').modal('hide');
				$("#nickName").hide();
				$("#modifyName").val("");
				$("#rmName").val("");
				loadEmployeeList();
			} else {
				alert("Information modified unsuccesffully.");
			}
		}
	});
};
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
