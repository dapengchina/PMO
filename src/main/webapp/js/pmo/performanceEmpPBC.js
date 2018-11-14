$(function(){
	//加载员工当年当季度绩效目标设定数据
	loadEmployeePerforgoal();
})

function loadEmployeePerforgoal(){
	$.ajax({
		url:path+"/service/empPerforGoal/getEmployeePerformance",
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(result){
			//console.log("data==" + JSON.stringify(result));
			$("#du").val(result.department);
			$("#position").val(result.role);
			$("#assessmentSupervisor").val(result.assessmentSupervisor);
			if(result.state=="1" || result.state=="2"){
				document.getElementById("Save").setAttribute("disabled", true);
				document.getElementById("Edit").setAttribute("disabled", true);
				document.getElementById("Submit").setAttribute("disabled", true);
			}
			for(var i = 0; i < result.data.length; i++){
				if(result.data[i].type == "0"){
					loadPriorityWork(result);
	            }
				if(result.data[i].type == "1"){
					loadKeyEvents(result);
				}
			}
			loadEmployeePlan(result);
			if(result.state=="1" || result.state=="2"){
				document.getElementById("button1").setAttribute("disabled", 'disabled');
				document.getElementById("button2").setAttribute("disabled", 'disabled');
				document.getElementById("button3").setAttribute("disabled", 'disabled');
			}
		}
	})
}

//加载重点工作数据
function loadPriorityWork(result){
	$("#table1 tbody").remove();
	var tbody = $("<tbody>");
	tbody.appendTo($("#table1"));
	for (var i = 0; i < result.data.length; i++) {
		if(result.data[i].type == "0"){
			var tr1 = $("<tr id='tr1'></tr>");
			tr1.appendTo(tbody);
		}
	if(result.data[i].type == "0"){
		var td1 = $("<td id='tx1'>重点工作</td>");
		
		var td2 = $("<td name='index'>"
				+ result.data[i].index
				+ "</td>");
		var td3 = $("<td name='description'>"
				+ result.data[i].description
				+ "</td>");
		var td4 = $("<td name='weightrate'>"
				+ result.data[i].weightrate
				+ "</td>");
		var td5 = $("<td name='phasegoal'>"
				+ result.data[i].phasegoal
				+ "</td>");
		var td6 = $("<td name='keyaction'> "
				+ result.data[i].keyaction
				+ "</td>");
		var td7 = $("<td>"
				+ result.data[i].department
				+ "</td>");
		
		td1.appendTo(tr1);
		td2.appendTo(tr1);
		td3.appendTo(tr1);
		td4.appendTo(tr1);
		td5.appendTo(tr1);
		td6.appendTo(tr1);
		td7.appendTo(tr1);

	}}
	var tr2 = $("<tr id='tr2' ></tr>");
	tr2.appendTo(tbody);
//	var td = $("<td></td>");
//	td.appendTo(tr2);
	var td1 = $("<td colspan='8'></td>");
	td1.appendTo(tr2);
	var href =$("<button id='button1' href=\"javascript:void(0);\" class=\"btn btn-info btn-sm\" onclick=\"addTr1(\'table1\', -1, 7);\")>"+"<span class='glyphicon glyphicon-plus'></span> Plus"+"</button>"); 
	href.appendTo(td1);
	
	$("#table1").append("</tbdoy>");
}

//加载关键事件数据
function loadKeyEvents(result){
	$("#table2 tbody").remove();
	var tbody = $("<tbody>");
	tbody.appendTo($("#table2"));
	for (var i = 0; i < result.data.length; i++) {
		if(result.data[i].type == "1"){
			var tr1 = $("<tr id='tr1'></tr>");
			tr1.appendTo(tbody);
		}
	if(result.data[i].type == "1"){
		var td1 = $("<td style='width:153px;' id='tx1'>关键事件</td>");
		
		var td2 = $("<td style='width:97px;' name='index'>"
				+ result.data[i].index
				+ "</td>");
		var td3 = $("<td style='width:208px;' name='description'>"
				+ result.data[i].description
				+ "</td>");
		var td4 = $("<td style='width:97px;' name='weightrate'>"
				+ result.data[i].weightrate
				+ "</td>");
		var td5 = $("<td style='width:153px;' name='phasegoal'>"
				+ result.data[i].phasegoal
				+ "</td>");
		var td6 = $("<td style='width:153px;' name='keyaction'> "
				+ result.data[i].keyaction
				+ "</td>");
		var td7 = $("<td>"
				+ result.data[i].department
				+ "</td>");
		
		td1.appendTo(tr1);
		td2.appendTo(tr1);
		td3.appendTo(tr1);
		td4.appendTo(tr1);
		td5.appendTo(tr1);
		td6.appendTo(tr1);
		td7.appendTo(tr1);

	}}
	var tr2 = $("<tr id='tr2' ></tr>");
	tr2.appendTo(tbody);
//	var td = $("<td></td>");
//	td.appendTo(tr2);
	var td1 = $("<td colspan='7'></td>");
	td1.appendTo(tr2);
	var href =$("<button id='button2' href=\"javascript:void(0);\" class=\"btn btn-info btn-sm\" onclick=\"addTr3(\'table2\', -1, 7);\")>"+"<span class='glyphicon glyphicon-plus'></span> Plus"+"</button>"); 
	href.appendTo(td1);
	
	$("#table2").append("</tbdoy>");
}

//加载个人能力提升计划数据
function loadEmployeePlan(result){
	$("#table3 tbody").remove();
	
	var tbody = $("<tbody>");
	tbody.appendTo($("#table3"));
	for (var i = 0; i < result.plan.length; i++) {
		var tr = $("<tr id='tr1'></tr>");
		tr.appendTo(tbody);

		var td1 = $("<td name='keyability'>"
				+ result.plan[i].keyability
				+ "</td>");
		var td2 = $("<td name='action'>"
				+ result.plan[i].action
				+ "</td>");
		var td3 = $("<td name='supportor'>"
				+ result.plan[i].supportor
				+ "</td>");
		var td4 = $("<td name='dealine'>"
				+ result.plan[i].dealineString
				+ "</td>");
		
		td1.appendTo(tr);
		td2.appendTo(tr);
		td3.appendTo(tr);
		td4.appendTo(tr);

	}
	var tr2 = $("<tr id='tr2' ></tr>");
	tr2.appendTo(tbody);
	var td1 = $("<td colspan='4'></td>");
	td1.appendTo(tr2);
	var href =$("<button id='button3' href=\"javascript:void(0);\" class=\"btn btn-info btn-sm\" onclick=\"addTr2(\'table3\', -1, 4);\")>"+"<span class='glyphicon glyphicon-plus'></span> Plus"+"</button>"); 
	href.appendTo(td1);
	$("#table3").append("</tbdoy>");	
}

//将员工设定的绩效目标保存为草稿状态
function save(){
	/**
	 * 获取重点工作数据
	 */
	var trindex = $("#table1 tbody #tr1").length;
	var kpodata = [];
	for(var i=0;i<trindex;i++){
	    var data1 ={};
	    data1.index = $("#table1 tbody #tr1 td[name='index']").eq(i).text();
	    data1.description = $("#table1 tbody #tr1 td[name='description']").eq(i).text();
	    data1.weightrate = $("#table1 tbody #tr1 td[name='weightrate']").eq(i).text();
	    data1.phasegoal = $("#table1 tbody #tr1 td[name='phasegoal']").eq(i).text();
	    data1.keyaction = $("#table1 tbody #tr1 td[name='keyaction']").eq(i).text();
	    kpodata.push(data1);
	}
	
	
	/**
	 * 获取关键事件数据
	 */
	var trindex2 = $("#table2 tbody #tr1").length;
	var keydata = [];
	for(var j=0;j<trindex2;j++){
	    var data2 ={};
	    data2.index = $("#table2 tbody #tr1 td[name='index']").eq(j).text();
	    data2.description = $("#table2 tbody #tr1 td[name='description']").eq(j).text();
	    data2.weightrate = $("#table2 tbody #tr1 td[name='weightrate']").eq(j).text();
	    data2.phasegoal = $("#table2 tbody #tr1 td[name='phasegoal']").eq(j).text();
	    data2.keyaction = $("#table2 tbody #tr1 td[name='keyaction']").eq(j).text();
	    keydata.push(data2);
	}
	
	
	/**
	 * 获取个人能力提升计划数据
	 * @returns
	 */
	var trindex3 = $("#table3 tbody #tr1").length;
	var plandata = [];
	for(var k=0;k<trindex3;k++){
	    var data3 ={};
	    data3.keyability = $(" td[name='keyability']").eq(k).text();
	    data3.action = $(" td[name='action']").eq(k).text();
	    data3.supportor = $(" td[name='supportor']").eq(k).text();
	    data3.dealine = $("td[name='dealine']").eq(k).text();
	    plandata.push(data3);
	}
	//console.log("数据===="+JSON.stringify(kpodata));
	//console.log("数据===="+JSON.stringify(keydata));
	//console.log("数据===="+JSON.stringify(plandata));
	if(JSON.stringify(kpodata)=="[]" && JSON.stringify(keydata)=="[]" && JSON.stringify(plandata)=="[]"){
		alert("请设定绩效目标");
		return;
	}
	
	/**
	 * 开始保存
	 */
	$.ajax({
		url:path+'/service/empPerforGoal/saveEmployeePerformance',
		dataType:"json", 
		traditional:true,
		data:{
			data1:JSON.stringify(kpodata),
			data2:JSON.stringify(keydata),
			data3:JSON.stringify(plandata),
			data4:0
		},
		async:true,
		cache:false,
		type:"post",
		success:function(result){
			if(result.code=="1"){
           	 alert("保存成功");
           	 window.open(path+"/service/performance/performanceEmpPBC.html");
            }else{
           	 alert("保存失败");
            }
		},
	})
}

//将员工设定的绩效目标保存为提交待审批状态
function submit(){
	/**
	 * 获取重点工作数据
	 */
	var trindex = $("#table1 tbody #tr1").length;
	var kpodata = [];
	for(var i=0;i<trindex;i++){
	    var data1 ={};
	    data1.index = $("#table1 tbody #tr1 td[name='index']").eq(i).text();
	    data1.description = $("#table1 tbody #tr1 td[name='description']").eq(i).text();
	    data1.weightrate = $("#table1 tbody #tr1 td[name='weightrate']").eq(i).text();
	    data1.phasegoal = $("#table1 tbody #tr1 td[name='phasegoal']").eq(i).text();
	    data1.keyaction = $("#table1 tbody #tr1 td[name='keyaction']").eq(i).text();
	    kpodata.push(data1);
	}
	
	
	/**
	 * 获取关键事件数据
	 */
	var trindex2 = $("#table2 tbody #tr1").length;
	var keydata = [];
	for(var j=0;j<trindex2;j++){
	    var data2 ={};
	    data2.index = $("#table2 tbody #tr1 td[name='index']").eq(j).text();
	    data2.description = $("#table2 tbody #tr1 td[name='description']").eq(j).text();
	    data2.weightrate = $("#table2 tbody #tr1 td[name='weightrate']").eq(j).text();
	    data2.phasegoal = $("#table2 tbody #tr1 td[name='phasegoal']").eq(j).text();
	    data2.keyaction = $("#table2 tbody #tr1 td[name='keyaction']").eq(j).text();
	    keydata.push(data2);
	}
	
	
	/**
	 * 获取个人能力提升计划数据
	 * @returns
	 */
	var trindex3 = $("#table3 tbody #tr1").length;
	var plandata = [];
	for(var k=0;k<trindex3;k++){
	    var data3 ={};
	    data3.keyability = $(" td[name='keyability']").eq(k).text();
	    data3.action = $(" td[name='action']").eq(k).text();
	    data3.supportor = $(" td[name='supportor']").eq(k).text();
	    data3.dealine = $("td[name='dealine']").eq(k).text();
	    plandata.push(data3);
	}
	//console.log("数据===="+JSON.stringify(kpodata));
	//console.log("数据===="+JSON.stringify(keydata));
	//console.log("数据===="+JSON.stringify(plandata));
	if(JSON.stringify(kpodata)=="[]" && JSON.stringify(keydata)=="[]" && JSON.stringify(plandata)=="[]"){
		alert("请设定绩效目标");
		return;
	}
	
	/**
	 * 开始保存
	 */
	$.ajax({
		url:path+'/service/empPerforGoal/saveEmployeePerformance',
		dataType:"json", 
		traditional:true,
		data:{
			data1:JSON.stringify(kpodata),
			data2:JSON.stringify(keydata),
			data3:JSON.stringify(plandata),
			data4:1
		},
		async:true,
		cache:false,
		type:"post",
		success:function(result){
             if(result.code=="1"){
            	 alert("提交成功");
            	 window.open(path+"/service/performance/performanceEmpPBC.html");
             }else{
            	 alert("提交失败");
             }
		},
	})
	
	document.getElementById("Save").setAttribute("disabled", true);
	document.getElementById("Edit").setAttribute("disabled", true);
	document.getElementById("Submit").setAttribute("disabled", true);
	
	document.getElementById("button1").setAttribute("disabled", true);
	document.getElementById("button2").setAttribute("disabled", true);
	document.getElementById("button3").setAttribute("disabled", true);
}


	

	




