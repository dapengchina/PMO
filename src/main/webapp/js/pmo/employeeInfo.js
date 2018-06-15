var csDeptName0 = "";

var csSubDeptName0 = "";
	
var csBuName0 = "";


$(function(){
	
	loadEmployeeList();
	initSearchDate();
	
})

$("#pageRecordsNum").change(function(){
	var csDeptName = $("#csDept").find("option:selected").text();

	if(csDeptName.indexOf('Option')!=-1){
		csDeptName = "";
	}
	
	var csBuName = $("#csBu").find("option:selected").text();

	if(csBuName.indexOf('Option')!=-1){
		csBuName = "";
	}
	
	var csSubDeptName = $("#csSubDept").find("option:selected").text();
	
	if(csSubDeptName.indexOf('Option')!=-1){
		csSubDeptName = "";
	}
	
	var engagementType = $("#engagementType").find("option:selected").text();
	
	if(engagementType.indexOf('Option')!=-1){
		engagementType = "";
	}
	
	loadEmployeeList("",csDeptName,csSubDeptName,csBuName,engagementType);
	
})

function loadEngagementType(result){
	var url = path+'/json/engagementType.json'
	$.getJSON(url,  function(data) {
		   $("#engagementType").empty();
		   $("#engagementType").append("<option value=''>--Option--</option>");
	       $.each(data, function(i, item) {
	    	   $("#engagementType").append("<option>"+item.name+"</option>");
	       })
	       $('#engagementType').val(result.pageInfo.engagementType);
	});
}

function loadResourceStatus(result){
	var url = path+'/json/resourceStatus.json'
	$.getJSON(url,  function(data) {
		   $("#resourceStatus").empty();
		   $("#resourceStatus").append("<option value=''>--Option--</option>");
	       $.each(data, function(i, item) {
	    	   $("#resourceStatus").append("<option>"+item.name+"</option>");
	       })
	       $('#resourceStatus').val(result.pageInfo.resourceStatus);
	});
}


$("#csDept").change(function(){
	$("#exportExcel").attr("disabled", true);
	
})


$("#csSubDept").change(function(){
	$("#exportExcel").attr("disabled", true);
	var bu = $("#csBu").val();
	var du =changeCSDeptToId($("#csSubDept").val());
	loadUserForRM(bu,du,"");
})


$("#csBu").change(function(){
	$("#exportExcel").attr("disabled", true);
	var bu = $("#csBu").val();
	var du =changeCSDeptToId($("#csSubDept").val());
	loadUserForRM(bu,du,"");
})


/*function loadCSDept(){
	$.ajax({
		url:path+'/service/csDept/queryCSDeptName',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(list){
			for(var i = 0;i<list.length;i++){
				$("#csDept").append("<option value='"+list[i].csSubDeptId+"'>"+list[i].csDeptName+"</option>");
			}
		}
	})
}*/


$('#searchBtn').bind("click", function(){
	var csDeptName = $("#csDept").find("option:selected").text();

	if(csDeptName.indexOf('Option')!=-1){
		csDeptName = "";
	}
	
	var csBuName = $("#csBu").find("option:selected").text();

	if(csBuName.indexOf('Option')!=-1){
		csBuName = "";
	}
	
	var csSubDeptName = $("#csSubDept").find("option:selected").text();
	
	if(csSubDeptName.indexOf('Option')!=-1){
		csSubDeptName = "";
	}
	
	var engagementType = $("#engagementType").find("option:selected").text();
	
	if(engagementType.indexOf('Option')!=-1){
		engagementType = "";
	}
	
	loadEmployeeList("",csDeptName,csSubDeptName,csBuName,engagementType);
});

$('#exportExcel').bind("click", function(){
	
	$('#myModal').modal('show');
	
});

function selectAll(){
	$("input[type='checkbox']").prop("checked",'true');
}

function exportCondition(){
	var lb = $("label input");
	var condition = "";
	for (var i=0;i<lb.length;i++)
	{
		if (lb.eq(i).is(':checked'))
		{
			condition += lb.eq(i).attr("name")+",";
		}
	}
	if(condition==""||condition==null){
		alert("Please select checkbox.");
		return;
	}
	$.ajax({
		url:path+'/service/employeeInfo/setEmpConditon',
		dataType:"json",
		data:{"condition":condition},
		async:true,
		cache:false,
		type:"post",
		success:function(resultFlag){
			var url = path+'/service/employee/exportExcel';
			$("#exceltHref").attr("href",url);
			document.getElementById("exceltHref").click();
			$('#myModal').modal('hide');
			$("[type='checkbox']").removeAttr("checked");

		}
	})
}

function exportCancel(){
	$('#myModal').modal('hide');
	$("[type='checkbox']").removeAttr("checked");
}

var allCSSubDept;
function loadCSSubDept(result){
	var userType = result.user.userType;
	var csSubDeptNames = result.csSubDeptNames;
	$.ajax({
		url:path+'/service/csDept/queryAllCSSubDeptName',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(list){
			allCSSubDept= list;
			$("#csSubDept").empty();
			$("#csSubDept").append("<option value=''>--Option--</option>");
			for(var i = 0;i<list.length;i++){
				$("#csSubDept").append("<option>"+list[i].csSubDeptName+"</option>");
			}
			
			if(userType=='3' || userType=='4' || userType=='5'){
				if(csSubDeptNames.length==1){
					$('#csSubDept').val(result.csSubDeptNames[0]);
					$("#csSubDept").attr("disabled","disabled");
				}else if(csSubDeptNames.length>1){
					$("#csSubDept").empty();
					for(var i = 0;i<csSubDeptNames.length;i++){
						$("#csSubDept").append("<option>"+csSubDeptNames[i]+"</option>");
						$('#csSubDept').val(result.pageInfo.csSubDeptName);
					}
				}
			}else{
				$('#csSubDept').val(result.pageInfo.csSubDeptName);
				
			}
		}
	})
}


function editEmployeeInfo(employeeId,engagementType){
	
	if(engagementType=="Time&Material"||engagementType=="TeamDelivery"){
		$("#editForm").attr("action",path+"/service/employee/updateEmployeeInfo.html?type=1");
	}else if(engagementType=="FixedPrice"){
		$("#editForm").attr("action",path+"/service/employee/updateEmployeeInfo.html?type=2");
	}else if(engagementType=="Support"){
		$("#editForm").attr("action",path+"/service/employee/updateEmployeeInfo.html?type=3");
	}else{
		$("#editForm").attr("action",path+"/service/employee/updateEmployeeInfo.html?type=1");
	}
	$("#employeeId").val(employeeId);
	$("#editForm").submit();
}

function loadCSBu(result){
	var csBuNames = result.csBuNames;
	var userType = result.user.userType;
	var url = path+'/json/csBuName.json'
	$.getJSON(url,  function(data) {
		   $("#csBu").empty();
		   $("#csBu").append("<option value=''>--Option--</option>");
	       $.each(data, function(i, item) {
	    	   $("#csBu").append("<option>"+item.name+"</option>");
	       })
	       if(userType=='1' || userType=='2' || userType=='3' || userType=='4'||userType=='5'){
	    	   if(csBuNames.length==1){   		   
	    		   $('#csBu').val(result.user.bu);
	    		   $("#csBu").attr("disabled","disabled");
	    	   }else if(csBuNames.length>1){
	    		   $("#csBu").empty();
	    		   for(var i = 0;i<csBuNames.length;i++){
						$("#csBu").append("<option>"+csBuNames[i]+"</option>");
						$('#csBu').val(result.pageInfo.csbuName);
					}
	    	   }
			}else{
				$('#csBu').val(result.pageInfo.csbuName);
			}
	});
}

//gkf add
function loadUserForRM(bu,du,rmUserId){	
	$.ajax({
		url:path+'/service/user/getUserForRM',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(list){
			$("#RM").empty();
			$("#RM").append("<option value=''>--Option--</option>");	
			var RMList = new Array();
			if (bu != null && bu != "" && du != null && du != "") {
				for (var i = 0; i < list.length; i++) {
					var csDeptIds = list[i].csdeptId.split(",");
					for (var j = 0; j < csDeptIds.length; j++) {
						if (bu == list[i].bu && du == csDeptIds[j]) {
							RMList.push(list[i]);
						}
					}
				}
			}else if((bu==null || bu=="")&&(du!=null && du != "")){
				for(var i = 0;i<list.length;i++){
					var csDeptIds= list[i].csdeptId.split(",");
					for(var j = 0;j < csDeptIds.length;j++){
						if(du==csDeptIds[j]){
							RMList.push(list[i]);
						}
					}
				}
			}else if((du==null || du=="")&&(bu!=null && bu != "")){
				for(var i = 0;i<list.length;i++){
					var csDeptIds= list[i].csdeptId.split(",");
					for(var j = 0;j < csDeptIds.length;j++){
						if(bu==list[i].bu){
							RMList.push(list[i]);
						}						
					}
				}
				
			}else{
				for(var i = 0;i<list.length;i++){
					RMList.push(list[i]);
				}

			}
			//remove duplicates
			var newRMList = new Array();
			for(var i = 0;i < RMList.length;i++){				
				if(newRMList.indexOf(RMList[i])==-1){
					newRMList.push(RMList[i]);
				}
			}
			
			for(var i = 0;i < newRMList.length;i++){
				$("#RM").append("<option value='"+newRMList[i].userId+"'>"+newRMList[i].nickname+"</option>")
			}
				
			$('#RM').val(rmUserId);				

		}
	})
}

function changeCSDeptToId(du){
	var list = allCSSubDept;
	var csDeptId;
	for(var i=0;i<list.length;i++){
		if(list[i].csSubDeptName==du){
			csDeptId = list[i].csSubDeptId;
			break;
		}
	}
	return csDeptId;
}



function loadEmployeeList(pageState,csDeptName,csSubDeptName,csBuName,engagementType){
	
	var csDeptName = csDeptName;

	csDeptName0 = csDeptName;
	
	var csSubDeptName = csSubDeptName;
	
	csSubDeptName0 = csSubDeptName;
	
	var csBuName = csBuName;
	
	csBuName0 = csBuName;
	
	var engagementType = engagementType;
	
	var hsbcStaffId = $("#hsbcStaffId").val();
	
	var eHr = $("#eHr").val();
	
	var lob = $("#lob").val();
	
	var resourceStatus = $("#resourceStatus").val();
	
	var staffName = $("#staffName").val();
	
	var rmName= $("#RM").val();

	var pageState = pageState;
	
	var  pageRecordsNum = $("#pageRecordsNum").find("option:selected").text();
	
	$.ajax({
		url:path+"/service/employeeInfo/queryEmployeeList",
		dataType:"json",
		async:true,
		data:{"staffName":staffName,"resourceStatus":resourceStatus,"pageState":pageState,"csBuName":csBuName,"csSubDeptName":csSubDeptName,"hsbcStaffId":hsbcStaffId,"eHr":eHr,"lob":lob,"rmUserId":rmName,"engagementType":engagementType,"pageRecordsNum":pageRecordsNum},
		cache:false,
		type:"post",
		success:function(result){
			$("#employeeList tbody").remove();
			
			var tbody = $("<tbody>");
			tbody.appendTo($("#employeeList"));
			
			if(result.data.length > 0 ){
				$("#exportExcel").removeAttr("disabled");
			}
			var userType = result.user.userType;
			for (var i = 0; i < result.data.length; i++) {
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);
				
				var td0 = $("<td>"
						+ (i+1)
						+ "</td>");
				var td1 = $("<td>"
						+ result.data[i].staffName
						+ "</td>");
				var td2 = $("<td>"
						+ result.data[i].eHr
						+ "</td>");
				var td3 = $("<td>"
						+ result.data[i].lob
						+ "</td>");
				var td4 = $("<td id='tx1'>"
						+ result.data[i].hsbcStaffId
						+ "</td>");
				var td5 = $("<td>"
						+ result.data[i].csSubDeptName
						+ "</td>");
				var td6 = $("<td><center>"
						+ result.data[i].engagementType
						+ "</center></td>");
				var td7 = $("<td>"
						+ result.data[i].resourceStatus
						+ "</td>");
				var td71 = $("<td>"
						+ result.data[i].nickname
						+ "</td>");
				//var td7 = $("<td><a class='btn btn-info' href='javascript:void(0);'> <i class='glyphicon glyphicon-edit icon-white'></i> 编辑</a></td>");
				var td8 = null;
				var engagementType = result.data[i].engagementType.replace(/\s+/g,"");
				if(userType=='2' || userType=='4'|| userType=='6'|| userType=='7' || userType=='9'|| userType=='10'|| userType=='15'){
					td8 = $("<td><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=employeeDetail('"+result.data[i].employeeId+"','"+engagementType+"')>Detail</a></td>");
				}else if(userType=='5'){
					if($("#userId").val()==result.data[i].rmUserId || result.data[i].rmUserId=='' || result.data[i].rmUserId==null){
						td8 = $("<td><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=employeeDetail('"+result.data[i].employeeId+"','"+engagementType+"')>Detail</a>" +
								"<a href='javascript:void(0);' class='btn btn-info btn-small' onclick=editEmployeeInfo('"+result.data[i].employeeId+"','"+engagementType+"')>Edit</a>" +
								"<a href='javascript:void(0);' class='btn btn-info btn-small' onclick=employeeUpgrade('"+result.data[i].staffName+"','"+result.data[i].eHr+"','"+result.data[i].hsbcStaffId+"','"+result.data[i].lob+"')>Promote</a>"+
										"</td>"
										
						);
					}else{
						td8 = $("<td><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=employeeDetail('"+result.data[i].employeeId+"','"+engagementType+"')>Detail</a></td>");
					}
				}else if(userType=='11'||userType=='12'||userType=='13'||userType=='14'){
					if(result.csdeptNameForEdit == result.data[i].csSubDeptName || result.user.bu== result.data[i].csSubDeptName){
						td8 = $("<td><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=employeeDetail('"+result.data[i].employeeId+"','"+engagementType+"')>Detail</a>" +
								"<a href='javascript:void(0);' class='btn btn-info btn-small' onclick=editEmployeeInfo('"+result.data[i].employeeId+"','"+engagementType+"')>Edit</a>" +
								"<a href='javascript:void(0);' class='btn btn-info btn-small' onclick=employeeUpgrade('"+result.data[i].staffName+"','"+result.data[i].eHr+"','"+result.data[i].hsbcStaffId+"','"+result.data[i].lob+"')>Promote</a>"+
										"</td>");
					}else{
						td8 = $("<td><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=employeeDetail('"+result.data[i].employeeId+"','"+engagementType+"')>Detail</a></td>");
					}
				}else if(userType=='8'){
					td8 = $("<td><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=employeeDetail('"+result.data[i].employeeId+"','"+engagementType+"')>Detail</a>" +
							"<a href='javascript:void(0);' class='btn btn-info btn-small' onclick=editEmployeeInfo('"+result.data[i].employeeId+"','"+engagementType+"')>Edit</a>" +
							"<a href='javascript:void(0);' class='btn btn-info btn-small' onclick=employeeUpgrade('"+result.data[i].staffName+"','"+result.data[i].eHr+"','"+result.data[i].hsbcStaffId+"','"+result.data[i].lob+"')>Promote</a>"+
									"</td>");
				}else{
					td8 = $("<td><a href='javascript:void(0);' class='btn btn-info btn-small' onclick=employeeDetail('"+result.data[i].employeeId+"','"+engagementType+"')>Detail</a>" +
							"<a href='javascript:void(0);' class='btn btn-info btn-small' onclick=editEmployeeInfo('"+result.data[i].employeeId+"','"+engagementType+"')>Edit</a>" +
							"<a href='javascript:void(0);' class='btn btn-info btn-small' onclick=employeeUpgrade('"+result.data[i].staffName+"','"+result.data[i].eHr+"','"+result.data[i].hsbcStaffId+"','"+result.data[i].lob+"')>Promote</a>"+
									"</td>");
				}
				if((result.data[i].hsbcStaffId)==null){
					var td4 = $("<td></td>");
				}
				if((result.data[i].eHr)==null){
					var td2 = $("<td></td>");
				}
				if((result.data[i].lob)==null){
					var td3 = $("<td></td>");
				}
				if((result.data[i].csSubDeptName)==null){
					var td5 = $("<td></td>");
				}
				if((result.data[i].resourceStatus)==null){
					var td7 = $("<td></td>");
				}
				if((result.data[i].nickname)==null){
					var td71 = $("<td></td>");
				}
				td0.appendTo(tr);
				td1.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);
				td4.appendTo(tr);
				td5.appendTo(tr);
				td6.appendTo(tr);
				td7.appendTo(tr);
				td71.appendTo(tr);
				td8.appendTo(tr);
			}
			$("#employeeList").append("</tbdoy>");
			//alert(window.location.href);
			var pageNum = parseInt(result.pageInfo.currentPage);
			var pageRecordsNum = parseInt(result.pageInfo.pageRecordsNum);
			pageNum = pageNum / pageRecordsNum + 1;
			var totalPage = parseInt(result.pageInfo.pageCount);
			$("#pageCount").html(totalPage);
			$("#currentPage").html(pageNum);
			$("#nextPage").attr("onclick","loadEmployeeList('next')");
			$("#previousPage").attr("onclick","loadEmployeeList('previous')");
			if(pageNum == totalPage){
				$("#nextPage").removeAttr("onclick");
			}
			if(pageNum == 1){
				$("#previousPage").removeAttr("onclick");
			}
			
			loadResourceStatus(result);
			
			loadCSSubDept(result);
			
			loadCSBu(result);
			
			loadEngagementType(result);
			
			loadUserForRM(result.pageInfo.csbuName,result.csSubDeptId,result.pageInfo.rmUserId);
			
			
		}
		
	})
}

function employeeDetail(employeeId,engagementType){
	if(engagementType=="Time&Material"||engagementType=="TeamDelivery"){
		$("#editForm").attr("action",path+"/service/interview/employeeDetailInfo.html?type=1");
	}else if(engagementType=="FixedPrice"){
		$("#editForm").attr("action",path+"/service/interview/employeeDetailInfo.html?type=2");
	}else if(engagementType=="Support"){
		$("#editForm").attr("action",path+"/service/interview/employeeDetailInfo.html?type=3");
	}else{
		$("#editForm").attr("action",path+"/service/interview/employeeDetailInfo.html?type=1");
	}
	
	$("#employeeId").val(employeeId);
	$("#editForm").submit();
}

function initSearchDate(){
	var picker1 = $('#datetimepicker1').datetimepicker({  
		language : 'zh-CN',
		format : "yyyy-mm-dd",
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 2,
		forceParse : 0
    });  
    var picker2 = $('#datetimepicker2').datetimepicker({  
    	language : 'zh-CN',
    	format : "yyyy-mm-dd",
    	weekStart : 1,
    	todayBtn : 1,
    	autoclose : 1,
    	todayHighlight : 1,
    	startView : 2,
    	minView : 2,
    	forceParse : 0
    }); 
    var picker3 = $('#datetimepicker3').datetimepicker({  
    	language : 'zh-CN',
    	format : "yyyy-mm-dd",
    	weekStart : 1,
    	todayBtn : 1,
    	autoclose : 1,
    	todayHighlight : 1,
    	startView : 2,
    	minView : 2,
    	forceParse : 0
    }); 
    //动态设置最小值  
    picker1.on('dp.change', function (e) {  
        picker2.data('DateTimePicker').minDate(e.date);  
    });  
    //动态设置最大值  
    picker2.on('dp.change', function (e) {  
        picker1.data('DateTimePicker').maxDate(e.date);  
    });  
}

function employeeUpgrade(staffname,ehr,staffid,lob){
	console.log(staffid);
	$("#employeeUpgrade").modal('show');
	$("#upgradeemployeename").html(staffname);
	
	$("#eHrupgrade").val(ehr!=null?ehr:" ");
	$("#staffidupgrade").val(staffid!=null?staffid:" ");
	$("#staffnameupgrade").val(staffname!=null?staffname:" ");
	$("#lobupgrade").val(lob!=null?lob:" ");
	
	
	var queryUrl = path+'/json/test.json';
    var table = $('#employeeUpgradeRecordList').bootstrapTable({
        url: queryUrl,                      //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        //toolbar: '#toolbar',                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 7,                       //每页的记录行数（*）
        pageList: [7,10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,
        showColumns: false,                  //是否显示所有的列
        showRefresh: false,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        //height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
        showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        singleSelect:true, 				//禁止多选_____
        //得到查询的参数
        queryParams : function (params) {
        	return {
        		pageSize: params.limit,
        		pageNumber: params.offset/params.limit+1,
            };
        },
        columns: [
        	{  
                //field: 'Number',//可不加  
                title: 'LN',//标题  可不加  
                formatter: function (value, row, index) {  
                    return index+1;  
                }  
            },
        	{
            field: 'ehr',
            title: 'E-HR',
            sortable: true
        },{
            field: 'staffid',
            title: 'StaffId',
            sortable: true
        },{
            field: 'lob',
            title: 'LOB',
            sortable: true
        },{
            field: 'msa role',
            title: 'MSA Role',
            sortable: true
        },{
            field: 'promote Role',
            title: 'Promote Role',
            sortable: true
        },
        {
            field: 'previous rate',
            title: 'Previous rate',
            sortable: true
        },
        {
            field: 'now rate',
            title: 'Now Rate',
            sortable: true
        },{
            field: 'effective date',
            title: 'Effective Date',
            sortable: true
        },{
            field: 'operation date',
            title: 'Operation Date',
            sortable: true
        },{
            field: 'owner',
            title: 'Owner',
            sortable: true
        }
        /*,
        {
            field:'ID',
            title: 'Operation',
            width: 120,
            align: 'center',
            valign: 'middle'
        },*/ ],
        onLoadSuccess: function () {
        },
        onLoadError: function () {
           
        },
        onDblClickRow: function (row, $element) {
           
        },
    });
    loadRole();
}

function loadRole(){
	var url = path+'/json/role.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#promoteroleupgrade").append("<option>"+item.name+"</option>");
	       })
	});
}
