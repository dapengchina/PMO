$(function(){
	loadSkill();
	loadPosition();
	loadLocation();
	loadCsSubDept();
	loadworkYears();
	loadInterviewerList();
	loadDu();
})

$("#skill").change(function(){
	$("#exportExcel").attr("disabled", true);
})
$("#location").change(function(){
	$("#exportExcel").attr("disabled", true);
})
$("#interviewerStatus").change(function(){
	$("#exportExcel").attr("disabled", true);
})

function loadDu(){
	$.ajax({
		url:path+'/service/csDept/queryAllCSSubDept',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(result){
			var userType = result.user.userType;
			
			var csSubs = result.csSubDepts;
			if(userType=='0'){
				for(var i = 0;i<result.data.length;i++){
					$("#du").append("<option value='"+result.data[i].csSubDeptId+"'>"+result.data[i].csSubDeptName+"</option>");
				}
			}else{
				if(csSubs.length==1){
					$("#du").append("<option value='"+csSubs[0].csSubDeptId+"'>"+csSubs[0].csSubDeptName+"</option>");
					$('#du').val(csSubs[0].csSubDeptId);
					$("#du").attr("disabled","disabled");
				}else if(csSubs.length>1){
					$("#du").empty();
					for(var i = 0;i<csSubs.length;i++){
						$("#du").append("<option value='"+csSubs[i].csSubDeptId+"'>"+csSubs[i].csSubDeptName+"</option>");
					}
				}
			}
		}
	})
}

function loadCsSubDept(){
	var url = path+'/json/cssubdept.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#csSubDept").append("<option value = "+item.name+">"+item.name+"</option>");
	       })
	});
}

function loadSkill(){
	var url = path+'/json/skill.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#skill").append("<option value = "+item.name+">"+item.name+"</option>");
	       })
	});
}
function loadworkYears(){
	var url = path+'/json/workYears.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#experience_years").append("<option value = "+item.name+">"+item.name+"</option>");
	       })
	});
}
function loadPosition(){
	var url = path+'/json/msaRole.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#role").append("<option value = "+item.name+">"+item.name+"</option>");
	       })
	});
}
function loadLocation(){
	var url = path+'/json/staffRegion.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#location").append("<option value = "+item.name+">"+item.name+"</option>");
	       })
	});
}
$('#searchBtn').bind("click", function(){
	loadInterviewerList();
});

/* 点击认定时执行的方法*/
function renDing(employeeId,status)
{
	if(confirm("Are you sure to authorize?"))
	{
		$.ajax({
			url:path+'/service/interviewer/interviewerRenDing',
			dataType:"json",
			async:true,
			cache:false,
			type:"post",
			data:{"employeeId":employeeId,"status":status},
			success:function(flag){
				if(flag){
					$("html,body").animate({scrollTop:0}, 500);
					$('#successAlert').html('Authorize successfully').show();
					setTimeout(function () {
						$('#successAlert').hide();
					}, 2000);
				}
				
				loadInterviewerList();
			}
		})
	}
}
/* 点击取消时执行的方法*/
function quXiao(employeeId,status)
{
	if(confirm("Are you sure to cancel?"))
	{
		$.ajax({
			url:path+'/service/interviewer/interviewerRenDing',
			dataType:"json",
			async:true,
			cache:false,
			type:"post",
			data:{"employeeId":employeeId,"status":status},
			success:function(resultFlag){
				
				if(resultFlag){
					
					$("html,body").animate({scrollTop:0}, 500);
					$('#successAlert').html('Cancel successfully').show();
					setTimeout(function () {
						$('#successAlert').hide();
					}, 2000);
				}
				
				loadInterviewerList();
			}
		})
	}
}

/*根据条件和当前页面加载查询到的信息*/
function loadInterviewerList(currPage){
	var pageRecordsNum = $("#pageRecordsNum").find("option:selected").text();
	var hsbcStaffId= $("#hsbcStaffId").val();
	var ehr= $("#ehr").val();
	var staffName= $("#staffName").val();
	var lob= $("#lob").val();
	var location= $("#location").val();
	var skill= $("#skill").val();
	var role = $("#role").val();
	var experience_years = $("#experience_years").val();
    var interviewerStatus = $("#interviewerStatus").val();
    var du= $("#du").val();
	
	//$("#demandList").empty();
	$("#interviewerList  tr:not(:first)").html("");
	$.ajax({
		url:path+'/service/interviewer/queryInterviewerList',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		data:{"hsbcStaffId":hsbcStaffId,"ehr":ehr,"staffName":staffName,"lob":lob,"staffRegion":location,
			"skill":skill,"role":role,"experienceYearas":experience_years,"status":interviewerStatus,"csSubDeptId":du,"currPage":currPage,"pageRecNum":pageRecordsNum},
		success:function(result){
			if(result.list.length > 0){
				$("#exportExcel").removeAttr("disabled");
			}else{
				// Felix, 20171212, colspan='9' to colspan='10'
				$("#interviewerList").append("<tr><td colspan='10' style='text-align:center'>暂无数据！</td></tr>");
			}
			//$.each(reslut, function(i,data){
			for (var i = 0; i < result.list.length; i++) {
				var tr = $("<tr></tr>");
				var status=result.list[i].status;
				if(status=='1'){
					status='是';
				}else{
					status='否';
				}
				var staff_Id = result.list[i].hsbcStaffId == null? '' : result.list[i].hsbcStaffId;	
				$("<td>"+result.list[i].ehr+"</td>"+
				"<td>"+result.list[i].lob+"</td>"+
				"<td>"+staff_Id+"</td>"+
				"<td>"+result.list[i].staffName+"</td>"+
				"<td>"+result.list[i].skill+"</td>"+
				"<td>"+result.list[i].staffRegion+"</td>"+
				// Felix, 20171212, MSA Role Begin
				"<td>"+result.list[i].role+"</td>"+
				// Felix, 20171212, MSA Role End
				"<td>"+result.list[i].experienceYearas+"</td>"+
				"<td>"+status+"</td>").appendTo(tr);
				if(status == '否' || status == null){
					
					$("<td><a href='javascript:void(0);' class='btn btn-info btn-small' " +
							"onclick=renDing('"+result.list[i].employeeId+"','"+result.list[i].status+"')>Authorize</a>" +
						"</td>").appendTo(tr);
				}else{
					$("<td><a href='javascript:void(0);' class='btn btn-info btn-small' " +
							"onclick=quXiao('"+result.list[i].employeeId+"','"+result.list[i].status+"')>Cancel</a>" +
						"</td>").appendTo(tr);
				}
				$("#interviewerList").append(tr);
			}
			//alert(result.pageCondition.totalPage);
			$("#pageCount").html(result.pageCondition.totalPage);
			$("#currentPage").html(result.pageCondition.currPage);
			//request.setAttribute("totalPage",result.pageCondition.totalPage);
			var html='<li><a href="javascript:void(0);" id="fristPage">First</a></li><li><a href="javascript:void(0);" id="previousPage" >Last</a></li>';
	
			html += '<li><a href="javascript:void(0);" id="nextPage" >Next</a></li><li><a href="javascript:void(0);" id="lastPage" >End</a></li>';
			/*if(result.pageCondition.totalPage>page){
				html += '跳至<input style="height:39px;width:39px" type="text" id="toPage">页</input><li style="float:right"><a href="javascript:void(0);" id="sure" >确定</a></li>';
			}*/
			$("ul.pagination-centered").html(html);
			console.log($("ul.pagination-centered"));
			/*$("#sure").click(function(){
				var pageNum = $("#toPage").val();
				loadDemandList(pageNum);
			})*/
			$("#fristPage").attr("onclick","loadInterviewerList(1)");
			if(result.pageCondition.currPage <= result.pageCondition.totalPage){
				$("#previousPage").attr("onclick","loadInterviewerList("+(result.pageCondition.currPage - 1)+")");
				$("#nextPage").attr("onclick","loadInterviewerList("+(result.pageCondition.currPage + 1)+")");
				$("#lastPage").attr("onclick","loadInterviewerList("+(result.pageCondition.totalPage)+")");
			}
			if(result.pageCondition.currPage==result.pageCondition.totalPage){
				$("#nextPage").parent("li").addClass("disabled");
				$("#nextPage").removeAttr('onclick');
				$("#lastPage").parent("li").addClass("disabled");
				$("#lastPage").removeAttr('onclick');
			}
			if(result.pageCondition.currPage==1){
				$("#fristPage").parent("li").addClass("disabled");
				$("#fristPage").removeAttr('onclick');
				$("#previousPage").parent("li").addClass("disabled");
				$("#previousPage").removeAttr('onclick');
			}
			$("ul.pagination-centered li a").each(function(){
				if($(this).text()==result.pageCondition.currPage){
					//$(this).parent("li").addClass("disabled");
					$(this).parent("li").addClass("active");
					$(this).removeAttr('onclick');
					
				}
			});
		}
	})
	
}

$("#pageRecordsNum").change(function(){
	loadInterviewerList();
})



