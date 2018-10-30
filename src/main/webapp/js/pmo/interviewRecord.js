var result=null;
var length = null;
$(function(){
	getInterviewRecord();
});

//window.onload = function(){
//	
//}

function getInterviewRecord(){
	var candidateId = $('#candidateId').val();
	$.ajax({
		url:path+'/service/interview/getInterviewRecordByCandId',
		dataType:"json",
		data:{"candidateId":candidateId},
		async:true,
		cache:false,
		type:"post",
		success:function(data){
			if(data==null){
				return;
			}
//			alert(JSON.stringify(data));
//			var obj=eval("("+data+")");
//			console.log("%o",data);
			if(Object.keys(data).length==0){
				$('#dataRecord').show()
				return;
			}
			
			result = data;
			length = Object.keys(data).length;
			var sourceListNode = document.getElementById("interviewInfoList");// 获得被克隆的节点对象 
			var sourceNode = document.getElementById("interviewInfo");
			var tabNode = document.getElementById("tab");
			var num=0;
			for ( var i in data) {
				var obj = data[i];
				num++;
				for(var j=0;j<obj.length;j++){
					var candidate = obj[j];
					console.log(JSON.stringify(candidate));
					if(j==0){
						var clonetabNode = tabNode.cloneNode(true); 
						clonetabNode.setAttribute("id", "tab" + num);
						tabNode.parentNode.appendChild(clonetabNode);
//						clonetabNode.children[0].setAttribute("href","interviewInfoList" + num);
						//clonetabNode.children[0].setAttribute("onclick","tabData("+num+")");
						clonetabNode.children[0].children[0].children[0].setAttribute("id", 'sp' + num);
						clonetabNode.removeAttribute('style');

						var clonedNode = sourceListNode.cloneNode(true); 
						clonedNode.setAttribute("id", "interviewInfoList" + num);
						sourceListNode.parentNode.appendChild(clonedNode);
						clonedNode.children[0].children[0].children[0].setAttribute("id", 'csSubDeptName' + num);
						clonedNode.children[0].children[1].children[0].setAttribute("id", 'projectName' + num);
						clonedNode.children[1].setAttribute("id", 'interviewInfo' + num  + j);
						clonedNode.children[1].children[1].children[0].children[0].setAttribute("id", 'th' + num + j);
						clonedNode.children[1].children[2].children[0].children[0].setAttribute("id", 'candidateName' + num + j);
						clonedNode.children[1].children[3].children[0].children[0].setAttribute("id", 'interviewType' + num + j);
						clonedNode.children[1].children[4].children[0].children[0].setAttribute("id", 'interviewDate' + num + j);
						clonedNode.children[1].children[5].children[0].children[0].setAttribute("id", 'interviewStatus' + num + j);
						clonedNode.children[1].children[6].children[0].children[0].setAttribute("id", 'interviewFeedBack' + num + j);
						$('#'+('sp' + num)).text(num);
						$('#'+('csSubDeptName' + num)).text(candidate.csSubdeptName);
						$('#'+('projectName' + num)).text(candidate.projectName);
						$('#'+('th' + num + j)).text(candidate.interviewSerrial);
						$('#'+('candidateName'+num + j)).text(candidate.nickName);
						$('#'+('interviewType'+num + j)).text(candidate.interviewType);
						$('#'+('interviewDate'+num + j)).text(candidate.interviewDate);
						$('#'+('interviewStatus'+num + j)).text(candidate.interviewStatus);
						$('#'+('interviewFeedBack'+num + j)).text(candidate.interviewFeedBack);
					}else{
						var clonedNode = sourceNode.cloneNode(true); 
						clonedNode.setAttribute("id", "interviewInfo" + num + j); 
						document.getElementById("interviewInfoList" + num).appendChild(clonedNode); 
						clonedNode.children[1].children[0].children[0].setAttribute("id", 'th' + num + j);
						clonedNode.children[2].children[0].children[0].setAttribute("id", 'candidateName' + num + j);
						clonedNode.children[3].children[0].children[0].setAttribute("id", 'interviewType' + num + j);
						clonedNode.children[4].children[0].children[0].setAttribute("id", 'interviewDate' + num + j);
						clonedNode.children[5].children[0].children[0].setAttribute("id", 'interviewStatus' + num + j);
						clonedNode.children[6].children[0].children[0].setAttribute("id", 'interviewFeedBack' + num + j);

						$('#'+('th' + num + j)).text(candidate.interviewSerrial);
						$('#'+('candidateName'+num + j)).text(candidate.nickName);
						$('#'+('interviewType'+num + j)).text(candidate.interviewType);
						$('#'+('interviewDate'+num + j)).text(candidate.interviewDate);
						$('#'+('interviewStatus'+num + j)).text(candidate.interviewStatus);
						$('#'+('interviewFeedBack'+num + j)).text(candidate.interviewFeedBack);
					}
					
					
					$('#interviewInfoList1').show();
					$("#tab1").addClass("active");
				}
			}
		}
	});
}

$(".nav-pills").delegate("li","click",function(e){
	if(!$(this).hasClass("active")){
		$(".nav-pills li").removeClass("active");
		$(this).addClass("active");
		$(".box-content").hide();
		$('#interviewInfoList'+$(this).attr("id").replace("tab","")).show();
	}
});


