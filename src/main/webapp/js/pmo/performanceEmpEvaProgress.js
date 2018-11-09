$(function () {
    loadEmpProgressList();
});


function loadEmpProgressList(){
	
	$.ajax({
		url:path+'/service/performanceEmpProgress/queryPerformanceEmpProgressList',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(result){
			console.log("data==" + JSON.stringify(result.rows));
			$("#performancePregressList tbody").remove();
			var tbody = $("<tbody>");
			tbody.appendTo($("#performancePregressList"));
			for(var i=0;i<result.rows.length;i++){
				var tr1 = $("<tr></tr>");
				tr1.appendTo(tbody);
				
				var td1 = $("<td>"+result.rows[i].processname+"</td>");
				var td2 = $("<td>"+result.rows[i].owner+"</td>");
				var td3 = $("<td>"+result.rows[i].state+"</td>");
				var td4 = $("<td></td>");
				
				td1.appendTo(tr1);
				td2.appendTo(tr1);
				td3.appendTo(tr1);
				td4.appendTo(tr1);
			}
			$("#performancePregressList").append("</tbdoy>");
		}
	})
}

