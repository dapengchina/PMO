var employeeId = "";
var type = "";
$().ready(function() {
	employeeId = $("#employeeId").val();
	type = $("#type").val();
	// 显示标题
	$("#title_" + type).removeClass("hidden");
	$("#button_" + type).removeClass("hidden");
	if (type != "2" && type != "1") {
		$("#button_3").removeClass("hidden");
	}
	// feefback显示状态
	if (type == "1") {
		$("#feedback")[0].readOnly = false;// 只有绩效目标审批可编辑
		$("#ratingDiv").attr("style", "display:none");// 只有绩效目标审批不显示
	}
	// rating显示状态
	if (type == "2") {// 只有rm初评可编辑
		$("#rating").removeAttr("disabled");
	}
	if ($("#result").val() != "") {
		$("#rating").val($("#result").val());
	}
	// 获取员工绩效目标设定总表数据
	getGoal();
	getKPO();
	getKeyEvent();
	getPlan();
});

function getGoal() {
	$.ajax({
		url : path + "/service/performanceManageEva/goal/" + employeeId,
		type : "GET",
		async : false,
		success : function(data) {
			// 赋值
			$("#userName").val(data.staffname);
			$("#lob").val(data.ehr);
			$("#bu").val(data.department);
			$("#position").val(data.position);
			$("#supervisor").val(data.assessmensupervisor);
			supervisor = data.assessmensupervisor;
			$("#selfAssessment").val(data.SelfAssessment);
			$("#feedback").val(data.remark);
		},
		error : function() {

		}
	});
}
function getKPO() {
	var url = path + "/service/performanceManageEva/kpo/" + employeeId;
	var table = $('#kpoTable').bootstrapTable({
		url : url,
		method : 'GET',
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : false, // 是否显示分页（*）
		columns : columns_KPO,
		onLoadSuccess : function(data) {
			$('#kpoTable').bootstrapTable("mergeCells", {
				index : 0,
				field : "type",
				rowspan : data.length
			});
		},
		onLoadError : function(status, res) { // 加载失败时执行
			console.log(res);
			console.log("error.status:" + status);
		},
	});
}
function getKeyEvent() {
	var url = path + "/service/performanceManageEva/keyevent/" + employeeId;
	var table = $('#keyEventTable').bootstrapTable({
		url : url,
		method : 'GET',
		showHeader : false,
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		columns : columns_keyEvent,
		onLoadSuccess : function(data) {
			$('#keyEventTable').bootstrapTable("mergeCells", {
				index : 0,
				field : "type",
				rowspan : data.length
			});
		},
		onLoadError : function(status, res) { // 加载失败时执行
			console.log(res);
			console.log("error.status:" + status);
		},
	});
}
function getPlan() {
	var url = path + "/service/performanceManageEva/plan/" + employeeId;
	var table = $('#planTable').bootstrapTable({
		url : url,
		method : 'GET',
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		columns : columns_plan,
		onLoadSuccess : function(data) {

		},
		onLoadError : function(status, res) { // 加载失败时执行
			console.log(res);
			console.log("error.status:" + status);
		},
	});
}

function submit(type) {
	var feedback = $("#feedback").val();
	if (feedback == "") {
		alert("请填写反馈");
		return;
	}
	$.ajax({
		url : path + "/service/performanceManageEva/assessment/goal/submit.html",
		type : "POST",
		data : {
			state : type,
			comments : feedback,
			id : employeeId
		},
		success : function() {
			alert("审批成功");
			history.go(-1);// TODO 刷新页面存在问题
		},
		error : function() {
			alert("审批失败");
		}
	});
}

// RM评级提交
function gradeSubmit() {
	$.ajax({
		url : path + "/service/performanceManageEva/assessment/grade/rm/submit",
		type : "POST",
		data : {
			"id" : $("#resultId").val(),
			"grade" : $("#rating").val()
		},
		success : function(data) {
			alert("评级成功");
			history.go(-1);
			// window.location.href = path +
			// "/service/performance/performanceManageEvaFirstDetailComments.html";
		},
		error : function() {
			alert("评级失败");
		}
	});
}

function gradeCancel() {
	history.go(-1);
	// window.location.href = path +
	// "/service/performance/performanceManageEvaFirstDetailComments.html";
}

function back() {
	history.go(-1);
}

var columns_KPO = [ {
	title : '分类',
	field : "type",
	formatter : function(value, row, index) {
		return "重点工作";
	},
	width : '10%',
	valign : "middle"
}, {
	title : '序号',
	formatter : function(value, row, index) {
		return "<span>" + (index + 1) + "</span>";
	},
	width : '5%'
}, {
	field : 'description',
	title : '重点工作内容',
	width : '10%'
}, {
	field : 'weightrate',
	title : '权重',
	width : '5%'
}, {
	field : 'phasegoal',
	title : '阶段目标',
	width : '30%'
}, {
	field : 'keyaction',
	title : '关键措施',
	width : '30%'
}, {
	title : '测评部门考核',
	formatter : function(value, row, index) {
		return supervisor;
	},
	width : '10%'
} ];
var columns_keyEvent = [ {
	title : '分类',
	field : "type",
	formatter : function(value, row, index) {
		return "关键事件";
	},
	width : '10%',
	valign : "middle"
}, {
	title : '序号',
	formatter : function(value, row, index) {
		return "<span>" + (index + 1) + "</span>";
	},
	width : '5%'
}, {
	field : 'description',
	title : '重点工作内容',
	width : '10%'
}, {
	field : 'weightrate',
	title : '权重',
	width : '5%'
}, {
	field : 'phasegoal',
	title : '阶段目标',
	width : '30%'
}, {
	field : 'keyaction',
	title : '关键措施',
	width : '30%'
}, {
	title : '测评部门考核',
	formatter : function(value, row, index) {
		return supervisor;
	},
	width : '10%'
} ];
var columns_plan = [ {
	title : '待提高或发展能力/经验<br/>(识别关键项)',
	field : "keyability"
}, {
	title : '实施活动及衡量标准<br/>(活动要具体、个性化，可衡量)',
	field : "action"
}, {
	field : 'supportor',
	title : '所需支持人<br/>(导师、主管等相关人)'
}, {
	field : 'dealine',
	title : '计划完成时间<br/>(计划要可执行)'
} ];