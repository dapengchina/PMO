$(function(){
	loadDept();
	loadStaffCategory();
	loadRole();
	loadSkill();
	loadGbGf();
	loadBillingEntity();
	loadBillingCurrency();
    //loadResourceStatus();
	loadCSDept();
	loadUserType();
	loadStaffRegion();
	loadStaffLocation();
	loadLocationType();
	loadOnshoreOrOffshore();
	dateType();
	dateType0();
	dateType1();
	dateType2();
	dateType3();
	loadUserForRM();
})

	$("#staffRegion").change(function(){
		var staffRegion = $("#staffRegion").val();
		$("#staffLocation").val(regionMap.get(staffRegion));
	});

var lastConditionStr = "";
function addEmployee(){
	var bootstrapValidator = $("#registerEmployeeForm").data('bootstrapValidator');
	bootstrapValidator.validate();
	if(bootstrapValidator.isValid()){
		$('#sub_search').addClass('disabled'); 
		
		var udemandid = $("#udemandid").val();
		var eHr = $('#eHr').val();
		var lob = $('#lob').val();
		var hsbcStaffId = $('#hsbcStaffId').val();
		var staffName = $('#staffName').val();
		var LN = $('#LN').val();
		var staffRegion = $('#staffRegion').val();
		var staffLocation = $('#staffLocation').val();
		var locationType = $('#locationType').val();
		var onshoreOrOffshore = $('#onshoreOrOffshore').val();
		var csSubDept = $('#csSubDept').val();
		var hsbcSubDept = $('#hsbcSubDept').val();
		var projectName = $('#hsbcProjectName').val();
		var projectManager = $('#hsbcProjectManager').val();
		var sow = $('#sow').val();
		var sowExpiredDate = $('#sowExpiredDate1').val();
		var staffCategory = $('#staffCategory').val();
		var engagementType = $('#engagementType').val();
		var hsbcDOJ = $('#hsbcDOJ2').val();
		var graduationDate = $('#graduationDate1').val();
		var role = $('#role').val();
		var skill = $('#skill').val();
		var billingCurrency = $('#billingCurrency').val();
		var billRate = $('#billRate').val();
		var terminationReason = $('#terminationReason').val();
		
		var chsoftiProNumber =  $('#chsoftiProNumber').val();
		var chsoftiProStartDate1 = $('#chsoftiProStartDate1').val();
		var chsoftiProName = $('#chsoftiProName').val();
		
		var email = $('#email').val();
		var gbGf = $('#gbGf').val();
		var entryDate = $('#entryDate1').val();
		var rmName= $("#RM").val();
		//拿到IT行业工作年限
		var itWorkYear = $("#itworkyear").val();
		//alert("毕业日期"+graduationDate);
		var currentDate = getCurrentDate();
		//alert("当前日期"+currentDate);
		var date1_temp = graduationDate.split("-");  
		var date2_temp = currentDate.split("-"); 
		var date1 = new Date(date1_temp[0], date1_temp[1]-1, date1_temp[2]);
		var date2 = new Date(date2_temp[0], date2_temp[1]-1, date2_temp[2]);  
		var days = date2.getTime() - date1.getTime(); 
		var year = parseInt(days / (1000 * 60 * 60 * 24 * 365)); 
		//alert("毕业的年限"+year);
		//判断IT工作年限不能大于实际工作年限
		//if(year!=null && year!=""){
			//alert(parseInt(itWorkYear));
			//alert(parseInt(year));
			if(parseInt(itWorkYear)>parseInt(year)){
				$("#modal-container-489917").modal('show');
				return;
			}
		//}
		
		var addEmpConditionStr = eHr + lob + hsbcStaffId + staffName + LN
						+ staffRegion + staffLocation + locationType
						+ onshoreOrOffshore + csSubDept + hsbcSubDept + projectName
						+ projectManager + sow + sowExpiredDate + staffCategory
						+ engagementType + hsbcDOJ + graduationDate + role + skill
						+ billingCurrency + billRate + terminationReason + email + gbGf
						+ entryDate + rmName + itWorkYear + chsoftiProNumber + chsoftiProStartDate1 + chsoftiProName;
		if (lastConditionStr != addEmpConditionStr) {
			lastConditionStr = addEmpConditionStr;
		} else {
			$('#sub_search').removeClass('disabled'); 
			$("html,body").animate({
				scrollTop : 0
			}, 500);
			$('#successAlert').html('Duplicate data,do not resubmit!').show();
			setTimeout(function() {
				$('#successAlert').hide();
			}, 2000);
			return;
		}
		
		$.ajax({
			url:path+'/service/employee/addEmployee',
			dataType:"json",
			data:{"udemandid":udemandid,"eHr":eHr,"lob":lob,"hsbcStaffId":hsbcStaffId,"staffName":staffName,"LN":LN,"staffRegion":staffRegion,
				"staffLocation":staffLocation,"locationType":locationType,"onshoreOrOffshore":onshoreOrOffshore,"csSubDept":csSubDept,
				"hsbcSubDept":hsbcSubDept,"projectName":projectName,"projectManager":projectManager,"sow":sow,"sowExpiredDate":sowExpiredDate,
				"staffCategory":staffCategory,"engagementType":engagementType,"hsbcDOJ":hsbcDOJ,"graduationDate":graduationDate,
				"role":role,"skill":skill,"billingCurrency":billingCurrency,"billRate":billRate,"resourceStatus":'Active',"terminatedDate":'',
				"email":email,"gbGf":gbGf,"entryDate":entryDate,"rmUserId":rmName,"terminationReason":terminationReason,"itindustryWorkYear":itWorkYear,
				"chsoftiProNumber":chsoftiProNumber,"chsoftiProStartDate":chsoftiProStartDate1,"chsoftiProName":chsoftiProName	
			},
			async:true,
			cache:false,
			type:"post",
			success:function(resultFlag){
				
				$.ajax({
					url:path+'/service/candidate/updateOnboardCandidate',
					dataType:"json",
					async:true,
					cache:false,
					type:"post",
					success:function(result){
						if(result){
							$("html,body").animate({scrollTop:0}, 500);
							$('#successAlert').html('Employee:'+staffName+' information added succesffully').show();
							setTimeout(function () {
								$('#successAlert').hide();
							}, 2000);
						}
					}
				})
				
				if(resultFlag){
					$("html,body").animate({scrollTop:0}, 500);
					$('#successAlert').html('Employee:'+staffName+' information added succesffully').show();
					setTimeout(function () {
						$('#successAlert').hide();
					}, 2000);
					$('#sub_search').removeClass('disabled'); 
					var urlTo = path+'/service/employee/employeeInfo.html';
					window.location.href = urlTo;
				}
			}
		})
		
	}else{
		return;
	}
	
	
}


function dateType(){
	$('.form_datetime').datetimepicker({
		weekStart: 1,
		minView:'month',
		todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
		language:'zh-CN',
		format: 'yyyy-mm-dd',
		pickerPosition: 'bottom-left',
		showMeridian: 1
	}).on('changeDate', function(ev){
		 $('#registerEmployeeForm').bootstrapValidator('revalidateField', 'hsbcDOJ1'); 
	
	});
}

function dateType0(){
	$('.form_datetime0').datetimepicker({
		weekStart: 1,
		minView:'month',
		todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
		language:'zh-CN',
		format: 'yyyy-mm-dd',
		pickerPosition: 'bottom-left',
		showMeridian: 1
	}).on('changeDate', function(ev){
		 $('#registerEmployeeForm').bootstrapValidator('revalidateField', 'graduationDate1'); 
	
	});
}

function dateType1(){
	$('.form_datetime1').datetimepicker({
		weekStart: 1,
		minView:'month',
		todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
		language:'zh-CN',
		format: 'yyyy-mm-dd',
		pickerPosition: 'bottom-left',
		showMeridian: 1
	}).on('changeDate', function(ev){
	
		 $('#registerEmployeeForm').bootstrapValidator('revalidateField', 'sowExpiredDate1'); 
		
	});
}

function dateType2(){
	$('.form_datetime2').datetimepicker({
		weekStart: 1,
		minView:'month',
		todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
		language:'zh-CN',
		format: 'yyyy-mm-dd',
		pickerPosition: 'bottom-left',
		showMeridian: 1
	}).on('changeDate', function(ev){		 
		 $('#registerEmployeeForm').bootstrapValidator('revalidateField', 'entryDate1'); 
	});
}
function dateType3(){
	$('.form_datetime3').datetimepicker({
		weekStart: 1,
		minView:'month',
		todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
		language:'zh-CN',
		format: 'yyyy-mm-dd',
		pickerPosition: 'bottom-left',
		showMeridian: 1
	}).on('changeDate', function(ev){		 
		 $('#registerEmployeeForm').bootstrapValidator('revalidateField', 'chsoftiProStartDate1'); 
	});
}

function loadOnshoreOrOffshore(){
	var url = path+'/json/onshoreOrOffshore.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#onshoreOrOffshore").append("<option>"+item.name+"</option>");
	       })
	});
}


function loadLocationType(){
	var url = path+'/json/locationType.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#locationType").append("<option value='"+item.name+"'>"+item.name+"</option>");
	    	   if(item.name=='ODC'){
	    		   $("#locationType").val(item.name);
	    	   }
	       })
	});
}


function loadStaffLocation(){
	var url = path+'/json/staffLocation.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#staffLocation").append("<option value='"+item.name+"'>"+item.name+"</option>");
	    	   if(item.name=='China'){
	    		   $("#staffLocation").val(item.name);
	    	   }
	       })
	});
}


var regionMap = new Map();
function loadStaffRegion(){
	var url = path+'/json/staffRegion.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#staffRegion").append("<option value='"+item.name+"'>"+item.name+"</option>");
	    	   regionMap.set(item.name,item.key);
	    	   if(item.name=='Xian'){
	    		   $("#staffRegion").val(item.name);
	    	   }
	       })
	});
}



var userTypeMap = new Map();
function loadUserType(){
	var url = path+'/json/userType.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#type").append("<option value='"+item.key+"'>"+item.name+"</option>");
	    	   userTypeMap.set(item.name,item.key);
	       })
	});
}


function loadResourceStatus(){
	var url = path+'/json/resourceStatus.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#resourceStatus").append("<option>"+item.name+"</option>");
	       })
	});
}


function loadBillingCurrency(){
	var url = path+'/json/billingCurrency.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#billingCurrency").append("<option>"+item.name+"</option>");
	       })
	});
}

function loadBillingEntity(){
	var url = path+'/json/billingEntity.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#billingEntity").append("<option>"+item.name+"</option>");
	       })
	});
}

function loadSkill(){
	var url = path+'/json/skill.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#skill").append("<option>"+item.name+"</option>");
	       })
	});
}

function loadGbGf(){
	var url = path + '/json/gbGf.json';
	$.getJSON(url, function(data){
		$.each(data, function(i, item){
			$("#gbGf").append("<option>"+item.name+"</option>");
		})
	});
}

function loadRole(){
	var url = path+'/json/role.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#role").append("<option>"+item.name+"</option>");
	       })
	});
}


function loadStaffCategory(){
	var url = path+'/json/staffCategory.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#staffCategory").append("<option value='"+item.name+"'>"+item.name+"</option>");
	    	   if(item.name=='CAT G'){
	    		   $("#staffCategory").val(item.name);
	    	   }
	       })
	});
}



function loadFixedPrice(){
	var url = path+'/json/engagementType1.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#engagementType").append("<option>"+item.name+"</option>");
	       })
	});
}


function loadCSDept(){
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
					$("#csSubDept").append("<option value='"+result.data[i].csSubDeptId+"'>"+result.data[i].csSubDeptName+"</option>");
				}
			}else{
				if(csSubs.length==1){
					$("#csSubDept").append("<option value='"+csSubs[0].csSubDeptId+"'>"+csSubs[0].csSubDeptName+"</option>");
					$('#csSubDept').val(csSubs[0].csSubDeptId);
					$("#csSubDept").attr("disabled","disabled");
					loadUserForRM($('#csSubDept').val());
				}else if(csSubs.length>1){
					$("#csSubDept").empty();
					for(var i = 0;i<csSubs.length;i++){
						$("#csSubDept").append("<option value='"+csSubs[i].csSubDeptId+"'>"+csSubs[i].csSubDeptName+"</option>");
					}
					loadUserForRM($('#csSubDept').val());
				}else{
					for(var i = 0;i<result.data.length;i++){
						$("#csSubDept").append("<option value='"+result.data[i].csSubDeptId+"'>"+result.data[i].csSubDeptName+"</option>");
					}
				}
			}
		}
	})
}
$("#csSubDept").change(function(){
	var du =$("#csSubDept").val();
	loadUserForRM(du);
})

function loadUserForRM(du){	
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
			if (du != null && du != "") {
				for (var i = 0; i < list.length; i++) {
					var csDeptIds = list[i].csdeptId.split(",");
					for (var j = 0; j < csDeptIds.length; j++) {
						if (du == csDeptIds[j]) {
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
			$("#RM").val($("#rmuserid").val());
		}
	})
}
$("#csDept").change(function(){
	var csSubDeptId = $('#csDept').val();
	$("#csSubDept").find("option").remove(); 
	$("#csSubDept").append("<option value=''>-- 请选择项目 --</option>");
	$.ajax({
		url:path+'/service/csDept/queryCSSubDeptName',
		dataType:"json",
		async:true,
		data:{"csSubDeptId":csSubDeptId},
		cache:false,
		type:"post",
		success:function(list){
			$("#csSubDept").find("option").remove(); 
			$("#csSubDept").append("<option value=''>-- 请选择子交付部 --</option>");
			for(var i = 0;i<list.length;i++){
				$("#csSubDept").append("<option value='"+list[i].csSubDeptId+"'>"+list[i].csSubDeptName+"</option>");
			}
		}
	})
})


function loadDept(){
	$.ajax({
		url:path+'/service/hsbcDept/queryDeptName',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(list){
			for(var i = 0;i<list.length;i++){
				$("#hsbcDept").append("<option value='"+list[i].hsbcSubDeptId+"'>"+list[i].hsbcDeptName+"</option>");
			}
		}
	})
}


$("#hsbcDept").change(function(){
	var hsbcSubDeptId = $('#hsbcDept').val();
	$("#projectName").find("option").remove(); 
	$("#projectName").append("<option value=''>-- 请选择项目 --</option>");
	$.ajax({
		url:path+'/service/hsbcDept/querySubDeptName',
		dataType:"json",
		async:true,
		data:{"hsbcSubDeptId":hsbcSubDeptId},
		cache:false,
		type:"post",
		success:function(list){
			$("#hsbcSubDept").find("option").remove(); 
//			if(list.length == 1 && list[0].hsbcSubDeptName == null){
//				$("#hsbcSubDept").append("<option value='"+$('#hsbcDept').find("option:selected").val()+"'>"+$('#hsbcDept').find("option:selected").text()+"</option>");
//			}else{
			$("#hsbcSubDept").append("<option value=''>-- Option --</option>");
			if(list.length == 1 && list[0].hsbcSubDeptName == null){
				$("#hsbcSubDept").append("<option value='"+$('#hsbcDept').find("option:selected").val()+"'>"+$('#hsbcDept').find("option:selected").text()+"</option>");
			}else{
				for(var i = 0;i<list.length;i++){
					$("#hsbcSubDept").append("<option value='"+list[i].hsbcSubDeptId+"'>"+list[i].hsbcSubDeptName+"</option>");
				}
			}
			
			//}
		}
	})
})

$("#hsbcSubDept").change(function(){
	var hsbcSubDeptId = $('#hsbcSubDept').val();
	$.ajax({
		url:path+'/service/hsbcProject/queryprojcetName',
		dataType:"json",
		async:true,
		data:{"hsbcSubDeptId":hsbcSubDeptId},
		cache:false,
		type:"post",
		success:function(list){
			$("#projectName").find("option").remove(); 
			$("#projectName").append("<option value=''>-- 请选择项目 --</option>");
			for(var i = 0;i<list.length;i++){
				$("#projectName").append("<option value='"+list[i].hsbcProjectId+"'>"+list[i].hsbcProjectName+"</option>");
			}
		}
	})
})

function changeData(){
	var staffRegion = $('#staffRegion').val();
	var role = $('#role').val();
	var skill = $('#skill').val();
	if('' == staffRegion || staffRegion== null){
		return;
	}
	if('' == role || role== null){
		return;
	}
	if('' == skill || skill== null){
		return;
	}
	
	$.ajax({
		url:path+'/service/interview/getBillRate',
		dataType:"json",
		async:true,
		data:{"staffRegion":staffRegion,"role":role,"skill":skill},
		cache:false,
		type:"post",
		success:function(data){
			if(data != null){
				$('#billRate').val(data);
			}
		}
	})
}
$("#getTm").click(function(){
	 window.location.href = path+"/service/employee/getTMemployee.html?status=1";
} );
/*function displayTM(){
    window.location.href = path+"/service/employee/getTMemployee.html?status=1";
}*/
/*function displayTD(){
	window.location.href = path+"/service/employee/getTMemployee.html?status=2";
}*/
$("#getFp").click(function(){
	window.location.href = path+"/service/employee/getTMemployee.html?status=3";
});
/*function displayFP(){
	window.location.href = path+"/service/employee/getTMemployee.html?status=3";
}*/
$("#getSupport").click(function(){
	window.location.href = path+"/service/employee/getTMemployee.html?status=4";
});

//获取当前时间，格式YYYY-MM-DD
function getCurrentDate() {
     var date = new Date();
     var seperator1 = "-";
     var year = date.getFullYear();
     var month = date.getMonth() + 1;
     var strDate = date.getDate();
     if (month >= 1 && month <= 9) {
         month = "0" + month;
     }
     if (strDate >= 0 && strDate <= 9) {
         strDate = "0" + strDate;
     }
     var currentdate = year + seperator1 + month + seperator1 + strDate;
     return currentdate;
}




function cdemand(){
    //alert("加载需求");
    $("#demandlist").modal('show');
    
    /*var url = path+'/json/skill.json';
    $.getJSON(url, function(data){
        $.each(data, function(i, item){
    	     $("#searchdemandskill").append("<option value='"+item.name+"'>"+item.name+"</option>");
        })
    })*/
    
    
    
    var queryUrl = path+'/service/demand/getDemand/2'
    var table = $('#demandlist2').bootstrapTable({
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
        pageSize: 8,                       //每页的记录行数（*）
        pageList: [8,10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,
        showColumns: false,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
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
        columns: [{
            checkbox: true,  
            visible: true                  //是否显示复选框  
        },{
            field: 'rr',
            title: 'RR',
            sortable: true
        },{
            field: 'jobCode',
            title: 'JobCode',
            sortable: true
        },{
            field: 'skill',
            title: 'Skill',
            sortable: true
        },{
            field: 'position',
            title: 'Position',
            sortable: true
        },{
            field: 'location',
            title: 'Location',
            sortable: true
        },
        {
            field: 'status',
            title: 'Status',
            sortable: true
        },
        {
            field: 'csDeptName',
            title: 'DU',
            sortable: true
        }/*,
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
}

function sureDemand(){
var demand = $('#demandlist2').bootstrapTable('getSelections');
//console.log(demand[0].demandId);

$("#demandlist").modal('hide');

$("#demandskill").val("");
$("#demandskill").val(demand[0].skill);

$("#udemandid").val("");
$("#udemandid").val(demand[0].demandId);
//console.log($("#udemandid").val());
}

//查找需求
function searchDemand(){
var skill = $("#searchdemandskill").val();
//alert(rr);
var queryParams = { 
	query: {  
		skill:skill
    }
}  
//刷新表格  
$('#demandlist2').bootstrapTable('refresh',queryParams);  
} 
/*function displaySupport(){
	window.location.href = path+"/service/employee/getTMemployee.html?status=4";
	
}*/
