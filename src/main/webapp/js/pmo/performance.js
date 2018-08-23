$("#getEmp").click(function(){
	window.open(path+"/service/performance/performanceEmpPBC.html?status=1");
} );

$("#getManage").click(function(){
	window.open(path+"/service/employee/getTMemployee.html?status=3");
});
$("#getHR").click(function(){
	window.open(path+"/service/employee/getTMemployee.html?status=4");
});
$("#getLOB").click(function(){
	window.open(path+"/service/employee/getTMemployee.html?status=4");
});

//year drop down list for performanceEmpEvaHistoryQuery.html
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


