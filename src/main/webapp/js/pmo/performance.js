var userType=null;
$(function(){
	loadUserType();
})

function loadUserType(){
	$.ajax({
		url:path+"/service/performance/getUserType",
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(result){
			userType=result;
			//普通员工角色判断
			if(result=='16'){
				document.getElementById('getManage').style.opacity="0.5";
				document.getElementById('getHR').style.opacity="0.5";
				document.getElementById('getLOB').style.opacity="0.5";
			}
			//RM角色判断 
			if(result=='5'){
				document.getElementById('getEmp').style.opacity="0.5";
				document.getElementById('getHR').style.opacity="0.5";
				document.getElementById('getLOB').style.opacity="0.5";
			}
			//交付部经理角色判断
			if(result=='3'){
				document.getElementById('getEmp').style.opacity="0.5";
				document.getElementById('getHR').style.opacity="0.5";
				document.getElementById('getLOB').style.opacity="0.5";
			}
			//事业部经理角色判断
			if(result=='1'){
				document.getElementById('getEmp').style.opacity="0.5";
				document.getElementById('getHR').style.opacity="0.5";
				document.getElementById('getLOB').style.opacity="0.5";
			}
			//HRBP角色判断
			if(result=='9'){
				document.getElementById('getEmp').style.opacity="0.5";
				document.getElementById('getManage').style.opacity="0.5";
				document.getElementById('getLOB').style.opacity="0.5";
			}
			//业务线/LOB总
			if(result=='15'){
				document.getElementById('getEmp').style.opacity="0.5";
				document.getElementById('getManage').style.opacity="0.5";
				document.getElementById('getHR').style.opacity="0.5";
			}
			//管理员
			if(result=='0'){
				document.getElementById('getEmp').style.opacity="0.5";
				document.getElementById('getManage').style.opacity="0.5";
				document.getElementById('getHR').style.opacity="0.5";
			}
			
		}
	})
}

$("#getEmp").click(function(){
	if(userType=='16'){
		window.open(path+"/service/performance/performanceEmpPBC.html");
	}
} );

$("#getManage").click(function(){
	//RM
	if(userType=='5'){
		window.open(path+"/service/performance/performanceManageTargetApproval.html");
	}
	//交付部经理
	if(userType=='3'){
		window.open(path+"/service/performance/performanceManageEvaSecondDU.html");
	}
	//事业部经理
	if(userType=='1'){
		window.open(path+"/service/performance/performanceManageEvaSecondBU.html");
	}
});
$("#getHR").click(function(){
	if(userType=='9'){
		window.open(path+"/service/performance/performanceHRBPGroupEva.html");
	}
});
$("#getLOB").click(function(){
	if(userType=='15' || userType=='0'){
		window.open(path+"/service/performance/performanceLobApprove.html");
	}
});
if($("#id").length>0){}else{} 

//year drop down list for performanceEmpEvaHistoryQuery.html
if($("#datetimepicker1").length>0){
	var newDate = new Date();
    var t = newDate.toJSON(); 
	$('#datetimepicker1').datetimepicker({
	     startView: 'decade',  
	     minView: 'decade',  
	     format: 'yyyy',
	     maxViewMode: 2,  
	     minViewMode:2,  
	     autoclose: true
	});
	$('#datetimepicker2').datetimepicker({
	    startView: 'decade',  
	     minView: 'decade',  
	     format: 'yyyy',  
	     maxViewMode: 2,  
	     minViewMode:2,  
	      autoclose: true  
	});
}



/**
 * 在table指定行前添加一行
 * tab 表id
 * row 行数，如：0->第一行 1->第二行 -2->倒数第二行 -1->最后一行
 * trHtml 添加行的html代码
 */
function addTr(tableId, row, trHtml) {
	//获取row所在的行
	var $tr=$("#"+tableId+" tr").eq(row);
  if($tr.length==0){
      alert("指定的table id或行数不存在！");
      return;
  }
	//当前行之前插入一行 
	$tr.before(trHtml);
}
function addTr1(tableId, row, cols) {
	var tr0 = $("<tr id='tr1'></tr>");
	
	var td0 = $("<td >"	+ "重点工作"	+ "</td>");
	var td1 = $("<td name='index'>"	+ "</td>");
	var td2 = $("<td name='description'>"+ "</td>");
	var td3 = $("<td name='weightrate'>"+ "</td>");
	var td4 = $("<td name='phasegoal'>"	+ "</td>");
	var td5 = $("<td name='keyaction'>"	+ "</td>");
	var td6 = $("<td colspan='8'>"	+ "&nbsp;"	+ "</td>");
	td0.appendTo(tr0);
	td1.appendTo(tr0);
	td2.appendTo(tr0);
	td3.appendTo(tr0);
	td4.appendTo(tr0);
	td5.appendTo(tr0);
	td6.appendTo(tr0);
	addTr(tableId, row, tr0);
	
	//更新“重点工作”列的rowspan参数，使其加1
	var currentRowspan = $("#leftrowspan").attr("rowspan");
	newRowspan =(parseInt(currentRowspan)+1);
	$("#leftrowspan").attr("rowspan", newRowspan);
}
function addTr2(tableId, row, cols) {
	var tr0 = $("<tr id='tr1' height=39px></tr>");
	var td0 = $("<td name='keyability'>" + "</td>");
	var td1 = $("<td name='action'>" + "</td>");
	var td2 = $("<td name='supportor'>" + "</td>");
	var td3 = $("<td name='dealine'>" + "</td>");
	td0.appendTo(tr0);
	td1.appendTo(tr0);
	td2.appendTo(tr0);
	td3.appendTo(tr0);
	
	addTr(tableId, row, tr0);
}
function addTr3(tableId, row, cols) {
	var tr0 = $("<tr id='tr1'></tr>");
	
	var td0 = $("<td style='width:153px;'>"	+ "关键事件"	+ "</td>");
	var td1 = $("<td style='width:97px;' name='index'>"	+ "</td>");
	var td2 = $("<td style='width:208px;' name='description'>"+ "</td>");
	var td3 = $("<td style='width:97px;' name='weightrate'>"+ "</td>");
	var td4 = $("<td style='width:153px;' name='phasegoal'>"	+ "</td>");
	var td5 = $("<td style='width:153px;' name='keyaction'>"	+ "</td>");
	var td6 = $("<td colspan='8'>"	+ "&nbsp;"	+ "</td>");
	td0.appendTo(tr0);
	td1.appendTo(tr0);
	td2.appendTo(tr0);
	td3.appendTo(tr0);
	td4.appendTo(tr0);
	td5.appendTo(tr0);
	td6.appendTo(tr0);
	addTr(tableId, row, tr0);
	
	//更新“重点工作”列的rowspan参数，使其加1
	var currentRowspan = $("#leftrowspan").attr("rowspan");
	newRowspan =(parseInt(currentRowspan)+1);
	$("#leftrowspan").attr("rowspan", newRowspan);
}
function edit(){
	$("tbody #tr1 td").attr("contenteditable","true");
}