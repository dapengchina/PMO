
	$("#editPwd").bind("click",function(){
	$("#editPassword").modal('show');
	});

function updatePassword(){
	var newPwd = $("#newPassword").val();
	var confPwd = $("#confPassword").val();
	if(newPwd==confPwd){
		$.ajax({
			url : path+"/service/user/updatePassword",
			type : "post",
			async : true,
			cache : false,
			dataType : "json",
			data : {'newPwd':newPwd},
			//timeout : 20000,
			success : function(data) {
				if(data=="1"){
					alert("修改成功");
					$("#newPassword").val("");
					$("#confPassword").val("");
				}
			}
		});
	}else{
		$("#updateAlert").html('确认密码与新密码不一致').show();
		$("#updateAlert").css({color:"red"});
	}
}
	
	  //退出登陆
	$("#loginOut1").bind("click",function(){
		
		
		   alert("你确定要退出登陆吗？");
		   
//		   location.replace("<%=path%>/UserController/welcome"); 
		  $("#loginOut2");
		  alert("1221");
		
		})
	function loginOut(){
		$.ajax({
			url : path+"/service/user/loginOut",
			
			type : "post",
			async : true,
			cache : false,
			dataType : "json",
			data : {'newPwd':newPwd},
			//timeout : 20000,
			success : function(data) {
				 alert("1wwwwwww21");
				} 
			});
		
	}
	
 	
	