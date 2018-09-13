$("#getEmp").click(function(){
	window.open(path+"/service/performance/performanceEmpPBC.html");
} );

$("#getManage").click(function(){
	window.open(path+"/service/performance/performanceEmpPBC.html");
});
$("#getHR").click(function(){
	window.open(path+"/service/performance/performanceEmpPBC.html");
});
$("#getLOB").click(function(){
	window.open(path+"/service/performance/performanceEmpPBC.html");
});
if($("#id").length>0){}else{} 

//year drop down list for performanceEmpEvaHistoryQuery.html
if($("#datetimepicker1").length>0){
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
	var tr0 = $("<tr></tr>");
	for (var i = 0; i < cols; i++) {
		var td0 = $("<td>"	+ "&nbsp;"	+ "</td>");
		td0.appendTo(tr0);
	}
	addTr(tableId, row, tr0);
	
	//更新“重点工作”列的rowspan参数，使其加1
	var currentRowspan = $("#leftrowspan").attr("rowspan");
	newRowspan =(parseInt(currentRowspan)+1);
	$("#leftrowspan").attr("rowspan", newRowspan);
}
function addTr2(tableId, row, cols) {
	var tr0 = $("<tr></tr>");
	for (var i = 0; i < cols; i++) {
		var td0 = $("<td>"	+ "&nbsp;"	+ "</td>");
		td0.appendTo(tr0);
	}
	addTr(tableId, row, tr0);
}
