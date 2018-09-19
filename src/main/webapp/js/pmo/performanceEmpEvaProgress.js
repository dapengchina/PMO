$(function(){
	loadPerformanceProgressList();
})


function loadPerformanceProgressList(pageState){
	var pageState = pageState;
	
	$.ajax({
		url:path+"/service/performanceEmpProgress/queryPerformanceEmpProgressList",
		dataType:"json",
		async:true,
		data:{"pageState":pageState},
		cache:false,
		type:"post",
		success:function(result){
			console.log("data==" + JSON.stringify(result));
			$("#performancePregressList tbody").remove();
			
			var tbody = $("<tbody>");
			tbody.appendTo($("#performancePregressList"));
			for (var i = 0; i < result.data.length; i++) {
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);

				var td1 = $("<td id='tx1'>"
						+ result.data[i].progressName
						+ "</td>");
				
				var td2 = $("<td>"
						+ result.data[i].auditor
						+ "</td>");
				var td3 = $("<td>"
						+ result.data[i].status
						+ "</td>");
				var td4 = $("<td>"
						+ result.data[i].comments
						+ "</td>");
				
				td1.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);
				td4.appendTo(tr);

			}
			$("#performancePregressList").append("</tbdoy>");		

		}
		
	})
}


