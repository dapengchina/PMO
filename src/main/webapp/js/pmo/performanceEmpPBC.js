$(function(){
	loadPerformanceState();
	loadPerformanceIDPList();
})
function save(){
	var data = "save";
	updateState(data);
	saveKeyevent();
	saveKPO();
	savePlan();
	alert("保存成功");
	
}
function submit(){
	var data = "submit";
	updateState(data);
	saveProcess();
	saveKeyevent();
	saveKPO();
	savePlan();
	alert("提交成功");
	
}
//读取状态
function loadPerformanceState(){
	$.ajax({
		url:path+"/service/performanceEmpPBC/queryPerformanceEmpState",
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(result){
			console.log("data==" + JSON.stringify(result));
			if( result.data[0] == null) {
				loadPerformanceEmpList();
				loadPerformancePBCList();
				return ;
			}else if(result.data[0].state == null ||result.data[0].state == ""){
				loadPerformanceEmpList();
				loadPerformancePBCList();
			}else {
				loadPerformanceEmpKPOList();
				loadPerformanceEmpEventList();
				if (result.data[0].state == "submit"){
					document.getElementById("Save").setAttribute("disabled", true);
					document.getElementById("Edit").setAttribute("disabled", true);
					document.getElementById("Submit").setAttribute("disabled", true);
				}
			}		

		}
		
	})
	
}
//state为空 读取KPO模板
function loadPerformanceEmpList(pageState){
	var pageState = pageState;
	
	$.ajax({
		url:path+"/service/performanceEmpPBC/queryPerformanceEmpFirstList",
		dataType:"json",
		async:true,
		data:{"pageState":pageState},
		cache:false,
		type:"post",
		success:function(result){
			console.log("data==" + JSON.stringify(result));
			$("#table1 tbody").remove();
			
			var tbody = $("<tbody>");
			tbody.appendTo($("#table1"));
			for (var i = 0; i < result.data.length; i++) {
				var tr1 = $("<tr id='tr1'></tr>");
				tr1.appendTo(tbody);
			if(result.data[i].type == "KPO"){
				var td1 = $("<td id='tx1' >"
						+ "重点工作"+
						+ "</td>");
				
				var td2 = $("<td name='index'>"
						+ result.data[i].index
						+ "</td>");
				var td3 = $("<td name='description'>"
						+ result.data[i].pbc1
						+ "</td>");
				var td4 = $("<td name='weightrate'>"
						+ result.data[i].pbc2
						+ "</td>");
				var td5 = $("<td name='phasegoal'>"
						+ result.data[i].pbc3
						+ "</td>");
				var td6 = $("<td name='keyaction'> "
						+ result.data[i].pbc4
						+ "</td>");
				var td7 = $("<td>"
						+ result.data[i].pbc5
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
			var td = $("<td></td>");
			td.appendTo(tr2);
			var td1 = $("<td colspan='6'></td>");
			td1.appendTo(tr2);
			var href =$("<a href=\"javascript:void(0);\" class=\"btn btn-info btn-small\" onclick=\"addTr1(\'table1\', -1, 7);\")>"+"+"+"</a>"); 
			href.appendTo(td1);
			
			$("#table1").append("</tbdoy>");		

		}
		
	})
}
//state为空 读取重点工作模板
function loadPerformancePBCList(pageState){
	var pageState = pageState;
	
	$.ajax({
		url:path+"/service/performanceEmpPBC/queryPerformanceEmpFirstList",
		dataType:"json",
		async:true,
		data:{"pageState":pageState},
		cache:false,
		type:"post",
		success:function(result){
			console.log("data==" + JSON.stringify(result));
			$("#table2 tbody").remove();
			
			var tbody = $("<tbody>");
			tbody.appendTo($("#table2"));
			for (var i = 0; i < result.data.length; i++) {
				var tr = $("<tr id='tr1'></tr>");
				tr.appendTo(tbody);
				if(result.data[i].type == "keyEvent"){
				var td1 = $("<td id='tx1'>"
						+ "关键事件"+
						+ "</td>");
				
				var td2 = $("<td name='index'>"
						+ result.data[i].index
						+ "</td>");
				var td3 = $("<td name='description'>"
						+ result.data[i].pbc1
						+ "</td>");
				var td4 = $("<td name='weightrate'>"
						+ result.data[i].pbc2
						+ "</td>");
				var td5 = $("<td name='phasegoal'>"
						+ result.data[i].pbc3
						+ "</td>");
				var td6 = $("<td name='keyaction' >"
						+ result.data[i].pbc4
						+ "</td>");
				var td7 = $("<td>"
						+ result.data[i].pbc5
						+ "</td>");
				
				td1.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);
				td4.appendTo(tr);
				td5.appendTo(tr);
				td6.appendTo(tr);
				td7.appendTo(tr);

			}}
			var tr2 = $("<tr id='tr2' ></tr>");
			tr2.appendTo(tbody);
			var td = $("<td></td>");
			td.appendTo(tr2);
			var td1 = $("<td colspan='6'></td>");
			td1.appendTo(tr2);
			var href =$("<a href=\"javascript:void(0);\" class=\"btn btn-info btn-small\" onclick=\"addTr1(\'table2\', -1, 7);\")>"+"+"+"</a>"); 
			href.appendTo(td1);
			$("#table2").append("</tbdoy>");		

		}
		
	})
}
//读取IDP记录
function loadPerformanceIDPList(pageState){
	var pageState = pageState;
	
	$.ajax({
		url:path+"/service/performanceEmpPBC/queryPerformanceEmpList3",
		dataType:"json",
		async:true,
		data:{"pageState":pageState},
		cache:false,
		type:"post",
		success:function(result){
			console.log("data==" + JSON.stringify(result));
			$("#table3 tbody").remove();
			
			var tbody = $("<tbody>");
			tbody.appendTo($("#table3"));
			for (var i = 0; i < result.data.length; i++) {
				var tr = $("<tr id='tr1'></tr>");
				tr.appendTo(tbody);

				var td1 = $("<td name='keyability'>"
						+ result.data[i].keyability
						+ "</td>");
				var td2 = $("<td name='action'>"
						+ result.data[i].action
						+ "</td>");
				var td3 = $("<td name='supportor'>"
						+ result.data[i].supportor
						+ "</td>");
				var td4 = $("<td name='dealine'>"
						+ result.data[i].dealine
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
			var href =$("<a href=\"javascript:void(0);\" class=\"btn btn-info btn-small\" onclick=\"addTr2(\'table3\', -1, 4);\")>"+"+"+"</a>"); 
			href.appendTo(td1);
			$("#table3").append("</tbdoy>");		

		}
		
	})
}
function loadPerformanceEmpKPOList(pageState){
	var pageState = pageState;
	
	$.ajax({
		url:path+"/service/performanceEmpPBC/queryPerformanceEmpKPOList",
		dataType:"json",
		async:true,
		data:{"pageState":pageState},
		cache:false,
		type:"post",
		success:function(result){
			console.log("data==" + JSON.stringify(result));
			$("#table1 tbody").remove();
			
			var tbody = $("<tbody>");
			tbody.appendTo($("#table1"));
			for (var i = 0; i < result.data.length; i++) {
				var tr1 = $("<tr id='tr1'></tr>");
				tr1.appendTo(tbody);
				var td1 = $("<td id='tx1' >"
						+ "重点工作"+
						+ "</td>");
				
				var td2 = $("<td name='index' >"
						+ result.data[i].index
						+ "</td>");
				var td3 = $("<td name='description' >"
						+ result.data[i].description
						+ "</td>");
				var td4 = $("<td name='weightrate'>"
						+ result.data[i].weightrate
						+ "</td>");
				var td5 = $("<td name='phasegoal'>"
						+ result.data[i].phasegoal
						+ "</td>");
				var td6 = $("<td name='keyaction' >"
						+ result.data[i].keyaction
						+ "</td>");
				var td7 = $("<td>"
						+ "XX交付部经理"
						+ "</td>");
				
				td1.appendTo(tr1);
				td2.appendTo(tr1);
				td3.appendTo(tr1);
				td4.appendTo(tr1);
				td5.appendTo(tr1);
				td6.appendTo(tr1);
				td7.appendTo(tr1);

			}
			var tr2 = $("<tr id='tr2' ></tr>");
			tr2.appendTo(tbody);
			var td = $("<td></td>");
			td.appendTo(tr2);
			var td1 = $("<td colspan='6'></td>");
			td1.appendTo(tr2);
			var href =$("<a href=\"javascript:void(0);\" class=\"btn btn-info btn-small\" onclick=\"addTr1(\'table1\', -1, 7);\")>"+"+"+"</a>"); 
			href.appendTo(td1);
			
			$("#table1").append("</tbdoy>");		

		}
		
	})
}
function loadPerformanceEmpEventList(pageState){
	var pageState = pageState;
	
	$.ajax({
		url:path+"/service/performanceEmpPBC/queryPerformanceEmpEventList",
		dataType:"json",
		async:true,
		data:{"pageState":pageState},
		cache:false,
		type:"post",
		success:function(result){
			console.log("data==" + JSON.stringify(result));
			$("#table2 tbody").remove();
			
			var tbody = $("<tbody>");
			tbody.appendTo($("#table2"));
			for (var i = 0; i < result.data.length; i++) {
				var tr = $("<tr id='tr1'></tr>");
				tr.appendTo(tbody);
				var td1 = $("<td id='tx1'>"
						+ "关键事件"+
						+ "</td>");
				
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
				var td6 = $("<td name='keyaction'>"
						+ result.data[i].keyaction
						+ "</td>");
				var td7 = $("<td>"
						+ "XX交付部经理"
						+ "</td>");
				
				td1.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);
				td4.appendTo(tr);
				td5.appendTo(tr);
				td6.appendTo(tr);
				td7.appendTo(tr);

			}
			var tr2 = $("<tr id='tr2' ></tr>");
			tr2.appendTo(tbody);
			var td = $("<td></td>");
			td.appendTo(tr2);
			var td1 = $("<td colspan='6'></td>");
			td1.appendTo(tr2);
			var href =$("<a href=\"javascript:void(0);\" class=\"btn btn-info btn-small\" onclick=\"addTr1(\'table2\', -1, 7);\")>"+"+"+"</a>"); 
			href.appendTo(td1);
			$("#table2").append("</tbdoy>");		

		}
		
	})
}
function saveKPO(){
	var pageState = pageState;
	var trindex = $("#table1 tbody #tr1").length;
	var datas = [];
	for(var i=0;i<trindex;i++){
	var data ={};
	 data.index = $("#table1 tbody #tr1 td[name='index']").eq(i).text();
	 data.description = $("#table1 tbody #tr1 td[name='description']").eq(i).text();
	 data.weightrate = $("#table1 tbody #tr1 td[name='weightrate']").eq(i).text();
	 data.phasegoal = $("#table1 tbody #tr1 td[name='phasegoal']").eq(i).text();
	 data.keyaction = $("#table1 tbody #tr1 td[name='keyaction']").eq(i).text();
	 datas.push(data);
	}
	$.ajax({
		url:path+'/service/performanceEmpPBC/deletePerformanceEmpKPO',
		dataType:"json",
		data:{"pageState":pageState},
		async:true,
		cache:false,
		type:"post",
		success:function(){
			$.ajax({
			url:path+'/service/performanceEmpPBC/savePerformanceEmpKPO',
			dataType:"json", traditional:true,
			data:{table1:JSON.stringify(datas)},
			async:true,
			cache:false,
			type:"post",
			success:1,
		})},
	})
}
function saveKeyevent(){
		var pageState = pageState;
		var trindex = $("#table2 tbody #tr1").length;
		var datas = [];
		for(var i=0;i<trindex;i++){
		var data ={};
		 data.index = $("#table2 tbody #tr1 td[name='index']").eq(i).text();
		 data.description = $("#table2 tbody #tr1 td[name='description']").eq(i).text();
		 data.weightrate = $("#table2 tbody #tr1 td[name='weightrate']").eq(i).text();
		 data.phasegoal = $("#table2 tbody #tr1 td[name='phasegoal']").eq(i).text();
		 data.keyaction = $("#table2 tbody #tr1 td[name='keyaction']").eq(i).text();
		 datas.push(data);
		}
		$.ajax({
			url:path+'/service/performanceEmpPBC/deletePerformanceEmpKeyEvent',
			dataType:"json",
			data:{"pageState":pageState},
			async:true,
			cache:false,
			type:"post",
			success:function(){
				$.ajax({
				url:path+'/service/performanceEmpPBC/savePerformanceEmpKeyEvent',
				dataType:"json", traditional:true,
				data:{table2:JSON.stringify(datas)},
				async:true,
				cache:false,
				type:"post",
				success:2,
			})},
		})
}
function savePlan(){
	var pageState = pageState;
	var trindex = $("#table3 tbody #tr1").length;
	var datas = [];
	for(var i=0;i<trindex;i++){
	var data ={};
	 data.keyability = $(" td[name='keyability']").eq(i).text();
	 data.action = $(" td[name='action']").eq(i).text();
	 data.supportor = $(" td[name='supportor']").eq(i).text();
	 data.dealine = $("td[name='dealine']").eq(i).text();
	 datas.push(data);
	}
	$.ajax({
		url:path+'/service/performanceEmpPBC/deletePerformanceEmpPlan',
		dataType:"json",
		data:{"pageState":pageState},
		async:true,
		cache:false,
		type:"post",
		success:function(){
			$.ajax({
			url:path+'/service/performanceEmpPBC/savePerformanceEmpPlan',
			dataType:"json", traditional:true,
			data:{table3:JSON.stringify(datas)},
			async:true,
			cache:false,
			type:"post",
			success:3,
		})},
	})
}
function updateState(data){
	$.ajax({
		url:path+"/service/performanceEmpPBC/deletePerformanceEmpState",
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success: function(){
			$.ajax({
				url:path+"/service/performanceEmpPBC/savePerformanceEmpState",
				dataType:"json",
				data:{"data":data},
				async:true,
				cache:false,
				type:"post",
				success: "updatesuccess",
				
			})
		}
		
	})
	
}
//submit是插入t_employeeperforprocess表
function saveProcess(){
	$.ajax({
		url:path+"/service/performanceEmpPBC/savePerformanceEmpProcess",
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(){
				alert("保存进度成功");
				document.getElementById("Save").setAttribute("disabled", true);
				document.getElementById("Edit").setAttribute("disabled", true);
				document.getElementById("Submit").setAttribute("disabled", true);
}
		
	})
}
	

	




