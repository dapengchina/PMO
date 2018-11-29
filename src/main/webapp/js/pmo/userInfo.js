var userTypeMap = new Map();

function loadUserTypeMap(){
	var url = path+'/json/userType.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   userTypeMap.set(item.key,item.name);
	       })
	});
}


$(function(){
	loadUserTypeMap();
	loadUserList();
	
})

$('#searchBtn').bind("click", function(){
	
	var csBuName = $("#csBu").find("option:selected").text();

	if(csBuName.indexOf('Option')!=-1){
		csBuName = "";
	}
	
	var csSubDeptName = $("#csSubDept").find("option:selected").text();
	
	if(csSubDeptName.indexOf('Option')!=-1){
		csSubDeptName = "";
	}
	
	loadUserList("",csSubDeptName,csBuName);
});


function loadCSSubDept(result){
	$.ajax({
		url:path+'/service/csDept/queryAllCSSubDeptName',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(list){
			$("#csSubDept").empty();
			$("#csSubDept").append("<option value=''>--Option--</option>");
			for(var i = 0;i<list.length;i++){
				$("#csSubDept").append("<option>"+list[i].csSubDeptName+"</option>");
			}
			$('#csSubDept').val(result.pageInfo.du);
		}
	})
}


function loadCSBu(result){
	var url = path+'/json/csBuName.json'
	$.getJSON(url,  function(data) {
		   $("#csBu").empty();
		   $("#csBu").append("<option value=''>--Option--</option>");
	       $.each(data, function(i, item) {
	    	   $("#csBu").append("<option>"+item.name+"</option>");
	       })
		   $('#csBu').val(result.pageInfo.bu);
	});
}

function loadUserType(result){
	var url = path+'/json/userType.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#userType").append("<option value='"+item.key+"'>"+item.name+"</option>");
	       })
	       $('#userType').val(result.pageInfo.userType);
	});
}


function loadUserList(pageState,csSubDeptName,csBuName){
	var eHr = $("#eHr").val();
	
	var userType = $("#userType").val();
	
	var userName = $("#userName").val();

	var pageState = pageState;
	
	$.ajax({
		url:path+"/service/userInfo/queryUserList",
		dataType:"json",
		async:true,
		data:{"userName":userName,"userType":userType,"pageState":pageState,"csBuName":csBuName,"csSubDeptName":csSubDeptName,"eHr":eHr},
		cache:false,
		type:"post",
		success:function(result){
			loadUserType(result);
			$("#userList tbody").remove();
			
			var tbody = $("<tbody>");
			tbody.appendTo($("#userList"));
			for (var i = 0; i < result.data.length; i++) {
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);

				var td1 = $("<td id='tx1'>"
						+ result.data[i].userName
						+ "</td>");
				
				var td2 = $("<td>"
						+ result.data[i].nickname
						+ "</td>");
				var td3 = $("<td>"
						+ userTypeMap.get(result.data[i].userType)
						+ "</td>");
				var td4 = $("<td>"
						+ (result.data[i].bu == null? '' : result.data[i].bu)
						+ "</td>");
				var td5 = $("<td>"
						+ (result.data[i].du == null? '' : result.data[i].du)
						+ "</td>");
				var td6 = null;
				if(result.data[i].loginStatus=="0"){
					td6 = $("<td><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=editUserInfo('"+result.data[i].userId+"')>Edit</a><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=userRights('"+result.data[i].userId+"','"+1+"')>Forbidden</a></td>");
				}
				if(result.data[i].loginStatus=="1"){
					td6 = $("<td><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=editUserInfo('"+result.data[i].userId+"')>Edit</a><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=userRights('"+result.data[i].userId+"','"+0+"')>Startusing</a></td>");
				}
				
				td1.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);
				td4.appendTo(tr);
				td5.appendTo(tr);
				td6.appendTo(tr);
			}
			$("#userList").append("</tbdoy>");
			var pageNum = parseInt(result.pageInfo.currentPage);
			pageNum = pageNum / 10 + 1;
			var totalPage = parseInt(result.pageInfo.pageCount);
			$("#pageCount").html(totalPage);
			$("#currentPage").html(pageNum);
			$("#nextPage").attr("onclick","loadUserList('next')");
			$("#previousPage").attr("onclick","loadUserList('previous')");
			if(pageNum == totalPage){
				$("#nextPage").removeAttr("onclick");
			}
			if(pageNum == 1){
				$("#previousPage").removeAttr("onclick");
			}
			
			loadCSSubDept(result);
			
			loadCSBu(result);
		}
		
	})
}

function editUserInfo(userId){
	$("#editForm").attr("action",path+"/service/user/updateUserInfo.html");
	$("#userId").val(userId);
	$("#editForm").submit();
}

//禁用/启用
function userRights(userid,state){
	//alert(state);
	$.ajax({
		url:path+"/service/user/userRights/"+userid+"/"+state,
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(result){
			if(result.code=='1'){
				alert(result.msg);
				window.location.href = path + "/service/user/userInfo.html";
			}else{
				alert(result.msg);
			}
		}
	})
}

