
$(function(){
	loadSkillEdit();
	loadPositionEdit();
	/*loadDemandStatusEdit();*/
	loadDepartmentEdit();
	//loadSubDepartmentEdit();
	dateType();
	//loadDemandListEdit();
	$("#searchBtn").click(function(){
		loadDemandListEdit();
	})
	//loadCsBuName();
	loadScSubDeptNameEdit();
	loadLocationEdit();
	loadHrPriorityEdit();
	loadGbGf();
	loadHsbcDept();
	loadHsbcSubDept();
})

function loadHsbcDept(){
	//$("#hsbcSubDept").empty();
	//$("#hsbcSubDept").append("<option value=''>-- Option --</option>");
	$.ajax({
		url:path+'/service/hsbcDept/queryChild',
		dataType:"json",
		data:{"id":$("#editgbgfid").val()},
		async:true,
		cache:false,
		type:"post",
		success:function(result){
			$.each(result, function(i, item){
				$("#hsbcDeptEdit").append("<option value='"+item.id+"'>"+item.name+"</option>");
			})
			$('#hsbcDeptEdit').val($("#edithsbcdeptid").val());
		}
	})
}

function loadHsbcSubDept(){
	//$("#hsbcSubDept").empty();
	//$("#hsbcSubDept").append("<option value=''>-- Option --</option>");
	$.ajax({
		url:path+'/service/hsbcDept/queryChild',
		dataType:"json",
		data:{"id":$("#edithsbcdeptid").val()},
		async:true,
		cache:false,
		type:"post",
		success:function(result){
			$.each(result, function(i, item){
				$("#hsbcSubDeptEdit").append("<option value='"+item.id+"'>"+item.name+"</option>");
			})
			$('#hsbcSubDeptEdit').val($("#edithsbcsubdeptid").val());
		}
	})
}

function loadGbGf(){
	var url = path + '/service/hsbcDept/queryTopParent';
	$.getJSON(url, function(data){
		$.each(data, function(i, item){
			$("#gbGf").append("<option value='"+item.id+"'>"+item.name+"</option>");
		})
		$("#gbGf").val($("#editgbgfid").val());
	});
}

function changeGBGF(){
	var id =$("#gbGf").val();
	$("#hsbcDeptEdit").empty();
	$("#hsbcSubDeptEdit").empty();
	$("#hsbcDeptEdit").append("<option value=''>-- Option --</option>");
	$("#hsbcSubDeptEdit").append("<option value=''>-- Option --</option>");
	$.ajax({
		url:path+'/service/hsbcDept/queryChild',
		dataType:"json",
		data:{"id":id},
		async:true,
		cache:false,
		type:"post",
		success:function(result){
			$.each(result, function(i, item){
				$("#hsbcDeptEdit").append("<option value='"+item.id+"'>"+item.name+"</option>");
			})
		}
	})
}

function changeHsbcDept(){
	var id =$("#hsbcDeptEdit").val();
	$("#hsbcSubDeptEdit").empty();
	$("#hsbcSubDeptEdit").append("<option value=''>-- Option --</option>");
	$.ajax({
		url:path+'/service/hsbcDept/queryChild',
		dataType:"json",
		data:{"id":id},
		async:true,
		cache:false,
		type:"post",
		success:function(result){
			$.each(result, function(i, item){
				$("#hsbcSubDeptEdit").append("<option value='"+item.id+"'>"+item.name+"</option>");
			})
		}
	})
}

$("#skill").change(function(){
	$("#exportExcel").attr("disabled", true);
})
$("#position").change(function(){
	$("#exportExcel").attr("disabled", true);
})
$("#department").change(function(){
	$("#exportExcel").attr("disabled", true);
})
$("#sub_department").change(function(){
	$("#exportExcel").attr("disabled", true);
})
$("#status").change(function(){
	$("#exportExcel").attr("disabled", true);
})
$("#csBuName").change(function(){
	$("#exportExcel").attr("disabled", true);
})
$("#scSubDeptName").change(function(){
	$("#exportExcel").attr("disabled", true);
})

/*add by jama 加载HrPriority*/
function loadHrPriorityEdit(){
	var url = path+'/json/hrPriority.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#hrPriorityEdit").append("<option>"+item.name+"</option>");
	       });
	       //add by jama 回显HrPriority的值
			var responseValue = $("#hrPriorityInput").val();
			var all_options = document.getElementById("hrPriorityEdit").options;
		    for (i=1; i<all_options.length; i++){
			   if (all_options[i].value == responseValue){
				   document.getElementById("hrPriorityEdit").options[i].selected = true;
				   var userType=$("#UserType").val();
				   if(userType!=0){
					   $("#hrPriorityEdit").attr("disabled","disabled");
				   }
				   break;
			   }  
		    }
	});
	
	
}

/*add by jama 加载location*/
function loadLocationEdit(){
	var url = path+'/json/staffRegion.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#locationEdit").append("<option>"+item.name+"</option>");
	       });
	       //add by jama 回显location的值
			var responseValue = $("#locationInput").val();
			var all_options = document.getElementById("locationEdit").options;
		    for (i=1; i<all_options.length; i++){
			   if (all_options[i].value == responseValue){
				   document.getElementById("locationEdit").options[i].selected = true;
				   break;
			   }  
		    }
	});
}

/*加载skill本地json信息*/
function loadSkillEdit(){
	var url = path+'/json/skill.json';
	$.getJSON(url, function(data){
		$.each(data, function(i, item){
			 $("#skillEdit").append("<option value='"+item.name+"'>"+item.name+"</option>");
		});
		//add by jama 回显skill的值
		var responseValue = $("#skillInput").val();
		var all_options = document.getElementById("skillEdit").options;
	    for (i=1; i<all_options.length; i++){
		   if (all_options[i].value == responseValue){
			   document.getElementById("skillEdit").options[i].selected = true;
			   break;
		   }  
	    }
	});
}
/*加载position本地json信息*/
function loadPositionEdit(){
	var url = path+'/json/position.json';
	$.getJSON(url, function(data){
		$.each(data, function(i, item){
			$("#positionEdit").append("<option value='"+item.name+"'>"+item.name+"</option>");
		});
		//add by jama 回显position的值
		var responseValue = $("#positionInput").val();
		var all_options = document.getElementById("positionEdit").options;
	    for (i=1; i<all_options.length; i++){
		   if (all_options[i].value == responseValue){
			   document.getElementById("positionEdit").options[i].selected = true;
			   break;
		   }  
	    }
	})
}
/*加载status本地json信息*/
/*function loadDemandStatusEdit(){
	var url = path+'/json/demandStatus.json';
	$.getJSON(url, function(data){
		$.each(data, function(i, item){
			$("#status").append("<option value='"+item.name+"'>"+item.name+"</option>");
		});
		
		var responseValue = $("#statusInput").val();
		var all_options = document.getElementById("status").options;
	    for (i=1; i<all_options.length; i++){
		   if (all_options[i].value == responseValue){
			   document.getElementById("status").options[i].selected = true;
			   break;
		   }  
	    }
	});
	//add by jama 设置状态回显值
}*/


/*加载业务部*/
function loadCsBuName(){
	var url = path+'/json/csBuName.json';
	$.getJSON(url, function(data){
		$.each(data,function(i,item){
			$("#csBuName").append("<option value='"+item.name+"'>"+item.name+"</option>");
		})
	})
}
/*加载交付部*/
/*function loadScSubDeptName(){
	$("#csBuName").change(function(){
		var csBuName = $("#csBuName").val();
		//$("#scSubDeptName").empty();
		//$("#scSubDeptName").append("<option value=''>-- select --</option>");
		$.ajax({
			url:path+'/service/demand/loadScSubDeptName',
			dataType:"json",
			async:true,
			cache:false,
			type:"post",
			data:{"csBuName":csBuName},
			success:function(data){
				$.each(data, function(i,item){
					$("#csSubDeptEdit").append("<option value='"+item.csSubDeptName+"'>"+item.csSubDeptName+"</option>");
				})
			}
		})
	})
}*/

/*add by jama 加载交付部，不依赖于事业部*/
function loadScSubDeptNameEdit(){
	var responseValue = $("#csSubDeptEdit").val();
	$("#csSubDeptEdit").empty();
	$("#csSubDeptEdit").append("<option value=''>-- Option --</option>");
	$.ajax({
		url:path+'/service/demand/loadAllScSubDeptName',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(data){
			$.each(data, function(i,item){
				$("#csSubDeptEdit").append("<option value='"+item.csSubDeptId+"'>"+item.csSubDeptName+"</option>");
			});
			//add by jama 设置业务部回显值
			var all_options = document.getElementById("csSubDeptEdit").options;
		    for (i=1; i<all_options.length; i++){
			   if (all_options[i].value == responseValue){
				   document.getElementById("csSubDeptEdit").options[i].selected = true;
				   break;
			   }  
		    }
		}
	})
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
		 //$('#recruitdemandFormEdit').bootstrapValidator('revalidateField','plannedOnboardDate1'); 
		 //$('#recruitdemandFormEdit').bootstrapValidator('revalidateField', 'reqPublishedDate1Edit');
		 //$('#recruitdemandFormEdit').bootstrapValidator('revalidateField', 'proposedJoiningDateEdit');
	});
}

/*异步加载Department信息*/
function loadDepartmentEdit(){
	$.ajax({
		url:path+'/service/demand/loadDepartment',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(data){
			$.each(data, function(i,item){
				$("#hsbcDeptEdit").append("<option value='"+item.hsbcDeptName+"'>"+item.hsbcDeptName+"</option>")
			});
			//add by jama 设置部门回显值
			var responseValue = $("#hsbcDeptInput").val();
			var resSubDeptValue = $("#hsbcSubDeptEdit").val();//子部门的返回值
			if(document.getElementById("hsbcDeptEdit")!=null){
				var all_options = document.getElementById("hsbcDeptEdit").options;
			    for (i=1; i<all_options.length; i++){
				   if (all_options[i].value == responseValue){
					   document.getElementById("hsbcDeptEdit").options[i].selected = true;
					   //上一行代码改变部门后，子部门的值跟着变化了，所以将子部门的返回值继续设置在节点上供后面使用
					   //document.getElementById("hsbcSubDeptEdit").value = resSubDeptValue;
					   break;
				   }  
			    }
			}
		   genSubDept4Dept(resSubDeptValue);
		}
	})
}

$("#hsbcDeptEdit").change(function(){
	var hsbcDeptName = $('#hsbcDeptEdit').val();
	$.ajax({
		url:path+'/service/demand/loadSubDepartment',
		dataType:"json",
		async:true,
		data:{"hsbcDeptName":hsbcDeptName},
		cache:false,
		type:"post",
		success:function(list){
			
			// ---gkf modify---
			$("#hsbcSubDeptEdit").find("option").remove(); 
			$("#hsbcSubDeptEdit").append("<option value=''>-- Option --</option>");
			if(list.length == 1 && list[0].hsbcSubDeptName == null){
				$("#hsbcSubDeptEdit").append("<option value='"+$('#hsbcDeptEdit').find("option:selected").val()+"'>"+$('#hsbcDeptEdit').find("option:selected").text()+"</option>");
			}else{
				for(var i = 0;i<list.length;i++){
					$("#hsbcSubDeptEdit").append("<option value='"+list[i].hsbcSubDeptName+"'>"+list[i].hsbcSubDeptName+"</option>");
				}
			}
		}
	})
});
	
/*根据部门加载对应的子部门*/
function genSubDept4Dept(resSubDeptValue){
	var department = $("#hsbcDeptEdit").val();
	$("#hsbcSubDeptEdit").empty();
	$("#hsbcSubDeptEdit").append("<option value=''>-- Option --</option>");
	$.ajax({
		url:path+'/service/demand/loadSubDepartment',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		data:{"hsbcDeptName":department},
		success:function(data){
			$.each(data, function(i,item){
				if(item.hsbcSubDeptName!=null){
					$("#hsbcSubDeptEdit").append("<option value='"+item.hsbcSubDeptName+"'>"+item.hsbcSubDeptName+"</option>");
				}else{
					$("#hsbcSubDeptEdit").append("<option value='"+item.hsbcDeptName+"'>"+item.hsbcDeptName+"</option>");
				}
			});
			//add by jama 设置子部门回显
			if("" != resSubDeptValue && undefined != resSubDeptValue){
				document.getElementById("hsbcSubDeptEdit").value = resSubDeptValue;
			}
			var responseValue = $("#hsbcSubDeptEdit").val();
			if(document.getElementById("hsbcSubDeptEdit")!=null){
				var all_options = document.getElementById("hsbcSubDeptEdit").options;
			    for (i=1; i<all_options.length; i++){
				   if (all_options[i].value == responseValue){
					   document.getElementById("hsbcSubDeptEdit").options[i].selected = true;
					   break;
				   }  
			    }
			}
			
		}
	})
}

/*根据条件和当前页加载查询到的信息*/
function loadDemandListEdit(currPage){
	var skill= $("#skill").val();
	var position= $("#position").val();
	var department= $("#department").val();
	var sub_department= $("#sub_department").val();
	var status= $("#status").val();
	var rr= $("#rr").val();
	var csBuName = $("#csBuName").val();
	var csSubDept = $("#scSubDeptName").val();
	//$("#demandList").empty();
	$("#demandList  tr:not(:first)").html("");
	$.ajax({
		url:path+'/service/demand/queryDemandList',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		data:{"csBuName":csBuName,"skill":skill,"position":position,"hsbcDept.hsbcDeptName":department,"hsbcDept.hsbcSubDeptName":sub_department,
			"status":status,"rr":rr,"currPage":currPage,"csSubDept":csSubDept},
		success:function(result){
			//alert(result.list.length);
			if(result.list.length > 0){
				$("#exportExcel").removeAttr("disabled");
			}else{
				$("#demandList").append("<tr><td colspan='7' style='text-align:center'>暂无数据！</td></tr>");
			}
			//$.each(reslut, function(i,data){
			for (var i = 0; i < result.list.length; i++) {
				var tr = $("<tr id='"+result.list[i].rr+"'></tr>");
				var td1 = $("<td>"+result.list[i].rr+"</td>");
				var td2 = $("<td>"+result.list[i].skill+"</td>");
				var td3 = $("<td>"+result.list[i].position+"</td>");
				if(result.list[i].hsbcDept == null){
					var td4 = $("<td></td>");
					var td5 = $("<td></td>");
				}else{
					var td4 = $("<td>"+result.list[i].hsbcDept.hsbcDeptName+"</td>");
					var td5 = $("<td>"+result.list[i].hsbcDept.hsbcSubDeptName+"</td>");
				}
				var td6 = $("<td>"+result.list[i].status+"</td>");
				var td7 = $("<td>"+result.list[i].csSubDept+"</td>");
				var td8 = $("<td><div class='btn-group btn-group-sm'><a href='javascript:void(0);' class='btn btn-primary' onclick='demandDetail("+result.list[i].demandId+")'>详情</a><a href='javascript:void(0);' class='btn btn-primary' onclick='demandDetailUpdate("+result.list[i].demandId+")'>编辑</a></div></td>");
				
				td1.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);
				td4.appendTo(tr);
				td5.appendTo(tr);
				td6.appendTo(tr);
				td7.appendTo(tr);
				td8.appendTo(tr);
				$("#demandList").append(tr);
			}
			//alert(result.pageCondition.totalPage);
			$("#pageCount").html(result.pageCondition.totalPage);
			$("#currentPage").html(result.pageCondition.currPage);
			//request.setAttribute("totalPage",result.pageCondition.totalPage);
			var html='<li><a href="javascript:void(0);" id="fristPage">首页</a></li><li><a href="javascript:void(0);" id="previousPage" >上一页</a></li>';
			/*if(result.pageCondition.currPage==1){
				html='<li><a href="#" id="fristPage" onclick="loadDemandList(1)">首页</a></li><li><a href="#" id="previousPage" >&laquo;</a></li><li><a href="#" id="nextPage" >&raquo;</a></li><li><a href="#" id="lastPage" >末页</a></li>';
			}else if(result.pageCondition.currPage==result.pageCondition.totalPage){
				html='<li><a href="#" id="fristPage" onclick="loadDemandList(1)">首页</a></li><li><a href="#" id="previousPage" >&laquo;</a></li><li><a href="#">'+(result.pageCondition.totalPage-2)+'</a></li><li><a href="#">'+(result.pageCondition.totalPage-1)+'</a></li><li><a href="#">'+(result.pageCondition.totalPage)+'</a></li><li><a href="#" id="nextPage" >&raquo;</a></li><li><a href="#" id="lastPage" >末页</a></li>';
			}else {
				html='<li><a href="#" id="fristPage" onclick="loadDemandList(1)">首页</a></li><li><a href="#" id="previousPage" >&laquo;</a></li><li><a href="#">'+(result.pageCondition.currPage-1)+'</a></li><li><a href="#">'+result.pageCondition.currPage+'</a></li><li><a href="#">'+(result.pageCondition.currPage+1)+'</a></li><li><a href="#" id="nextPage" >&raquo;</a></li><li><a href="#" id="lastPage" >末页</a></li>';
			}*/
			/*var page = 4;
			if(result.pageCondition.totalPage <= page){
				for (var i = 1; i <= result.pageCondition.totalPage; i++) {
					html +='<li><a href="javascript:void(0);" onclick="loadDemandList('+i+')">'+i+'</a></li>';
				}
			}else{
				if(result.pageCondition.currPage >= result.pageCondition.totalPage -page){
					for (var i = result.pageCondition.totalPage - page; i <= result.pageCondition.totalPage; i++) {
						html +='<li><a href="javascript:void(0);" onclick="loadDemandList('+i+')">'+i+'</a></li>';
					}
				}else{
					for (var i = result.pageCondition.currPage; i <= result.pageCondition.totalPage; i++) {
						if(i <=result.pageCondition.currPage + page){
							html +='<li><a href="javascript:void(0);" onclick="loadDemandList('+i+')">'+i+'</a></li>';
						}else{
							html +='<li style="display:none;"><a href="javascript:void(0);" onclick="loadDemandList('+i+')">'+i+'</a></li>';
						}
					}
				}
			}
			if(result.pageCondition.totalPage>page && result.pageCondition.currPage < result.pageCondition.totalPage - page){
				html += '<li class="disabled"><a href="javascript:void(0);">...</a></li>';
			}*/
			html += '<li><a href="javascript:void(0);" id="nextPage" >下一页</a></li><li><a href="javascript:void(0);" id="lastPage" >末页</a></li>';
			/*if(result.pageCondition.totalPage>page){
				html += '跳至<input style="height:39px;width:39px" type="text" id="toPage">页</input><li style="float:right"><a href="javascript:void(0);" id="sure" >确定</a></li>';
			}*/
			$("ul.pagination-centered").html(html);
			//console.log($("ul.pagination-centered"));
			/*$("#sure").click(function(){
				var pageNum = $("#toPage").val();
				loadDemandList(pageNum);
			})*/
			$("#fristPage").attr("onclick","loadDemandList(1)");
			if(result.pageCondition.currPage <= result.pageCondition.totalPage){
				$("#previousPage").attr("onclick","loadDemandList("+(result.pageCondition.currPage - 1)+")");
				$("#nextPage").attr("onclick","loadDemandList("+(result.pageCondition.currPage + 1)+")");
				$("#lastPage").attr("onclick","loadDemandList("+(result.pageCondition.totalPage)+")");
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
					//$(this).parent("li").removeClass("active");
					//$(this).parent("li").siblings("li").addClass("active");
					//$(this).parent("li").siblings("li").removeClass("disabled");
				}
			});
		}
	})
	
}

$('#exportExcel').bind("click", function(){
	
	$('#myModal').modal('show');
	
});

function exportCondition(){
	var condition="";
	$("label").find(":checkbox:checked").each(function(){
		condition += $(this).attr("name") +",";
	});
	var url = path+'/service/demand/exportExcel';
	$("#condition").val(condition);
	$("#conditionForm").attr("action",url);
	$("#conditionForm").submit();
	
	$('#myModal').modal('hide');
	$("[type='checkbox']").removeAttr("checked");
}

function demandDetail(demandId){
	
	//alert("dddd");
	$("#demandId").val(demandId);
	var url = path+'/service/demand/demandDetail';
	$("#detailForm").attr("action",url);
	$("#detailForm").submit();
	/*$.ajax({
		url:path+'/service/demand/demandDetail',
		dataType:"json",
		type:"post",
		data:{"demandId":demandId},
		success:function(result){
			
		}
	})*/
}

//add by jama
function demandDetailUpdate(demandId){
	
	//alert("aa");
	$("#demandId").val(demandId);
	var url = path+'/service/demand/demandDetailUpdate';
	$("#detailForm").attr("action",url);
	$("#detailForm").submit();
	/*$.ajax({
		url:path+'/service/demand/demandDetail',
		dataType:"json",
		type:"post",
		data:{"demandId":demandId},
		success:function(result){
			
		}
	})*/
}

function updateDemand(index){
	var bootstrapValidator = $("#recruitdemandFormEdit").data('bootstrapValidator');
	   bootstrapValidator.validate();
	   
	   setTimeout(function(){
		   var flag2 = $("#recruitdemandFormEdit").data('bootstrapValidator').validate();
		   if(flag2){
			   if(bootstrapValidator.isValid()){
					var demandId=$('#demandIdEdit').val();
					var engagementType=$('#engagementType').val();
					var rr=$('#rrEdit').val();
					var jobCode=$('#jobCodeEdit').val();
					var skill=$('#skillEdit').val();
					var requestor=$('#requestorEdit').val();
					var position=$('#positionEdit').val();
					//部门信息
					var gbgf=$('#gbGf').val();
					var hsbcDept=$('#hsbcDeptEdit').val();
					var hsbcSubDept=$('#hsbcSubDeptEdit').val();
					
					var zuhe = gbgf+","+hsbcDept+","+hsbcSubDept;
					
					
					var location=$('#locationEdit').val();
					var reqPublishedDate=$('#reqPublishedDate1Edit').val();
					var ageing=$('#ageingEdit').val();
					var profilesNo=$('#profilesNoEdit').val();
					var interviewedNo=$('#interviewedNoEdit').val();
					var status=$('#status').val();
					var proposedJoiningDate=$('#proposedJoiningDateEdit1').val();
					var bgvCleared=$('#bgvClearedEdit').val();
					var sowSigned=$('#sowSignedEdit').val();
					var reason=$('#reasonEdit').val();
					
					var remark=$('#remarkEdit').val();
					var csSubDept=$('#csSubDeptEdit').val();

					var plannedOnboardDate=$('#plannedOnboardDate1').val();
					var doNumber=$('#doNumberEdit').val();
					var hrPriority=$('#hrPriorityEdit').val();
					var staffName=$('#candidateNameEdit').val();
					
					$.ajax({
						url:path+'/service/demand/updateDemand',
						dataType:"json",
						data:{"demandId":demandId,"rr":rr,"engagementType":engagementType,"jobCode":jobCode,"skill":skill,"requestor":requestor,
							"position":position,"location":location,
							"reqPublishedDate":reqPublishedDate,"ageing":ageing,"profilesNo":profilesNo,
							"interviewedNo":interviewedNo,"status":status,"staffName":staffName,
							"proposedJoiningDate":proposedJoiningDate,"sowSigned":sowSigned,
							"reason":reason,"bgvCleared":bgvCleared,
							"remark":remark,"csSubDept":csSubDept,"plannedOnboardDate":plannedOnboardDate,
							"doNumber":doNumber,"hrPriority":hrPriority,
							"hsbcDept":hsbcDept,"hsbcSubDept":hsbcSubDept,"hsbcSubDeptId":zuhe,"onborad":index},
						async:true,
						cache:false,
						type:"post",
						success:function(resultFlag){
							if(resultFlag){
								$("html,body").animate({scrollTop:0}, 500);
								$('#successAlert').html('demand update sucessful!').show();
								setTimeout(function () {
									$('#successAlert').hide();
								}, 2000);
								self.opener.location.reload();
								
								$("#issendemail").modal('show');
								//var urlTo = path+'/service/demand/demandInfo.html';
								//window.location.href = urlTo;
								//$("#modal-container-489917").modal('show');
							}
						}
					})
				}else{
					return;
				}
		   }
	   },500);
	   
	/*if(bootstrapValidator.isValid()){
		var demandId=$('#demandIdEdit').val();
		var engagementType=$('#engagementType').val();
		var rr=$('#rrEdit').val();
		var jobCode=$('#jobCodeEdit').val();
		var skill=$('#skillEdit').val();
		var requestor=$('#requestorEdit').val();
		var position=$('#positionEdit').val();
		//部门信息
		var hsbcDept=$('#hsbcDeptEdit').val();
		var hsbcSubDept=$('#hsbcSubDeptEdit').val();
		var location=$('#locationEdit').val();
		var reqPublishedDate=$('#reqPublishedDate1Edit').val();
		var ageing=$('#ageingEdit').val();
		var profilesNo=$('#profilesNoEdit').val();
		var interviewedNo=$('#interviewedNoEdit').val();
		var status=$('#status').val();
		var proposedJoiningDate=$('#proposedJoiningDateEdit1').val();
		var bgvCleared=$('#bgvClearedEdit').val();
		var sowSigned=$('#sowSignedEdit').val();
		var reason=$('#reasonEdit').val();
		
		var remark=$('#remarkEdit').val();
		var csSubDept=$('#csSubDeptEdit').val();

		var plannedOnboardDate=$('#plannedOnboardDate1').val();
		var doNumber=$('#doNumberEdit').val();
		var hrPriority=$('#hrPriorityEdit').val();
		var staffName=$('#candidateNameEdit').val();
		
		$.ajax({
			url:path+'/service/demand/updateDemand',
			dataType:"json",
			data:{"demandId":demandId,"rr":rr,"engagementType":engagementType,"jobCode":jobCode,"skill":skill,"requestor":requestor,
				"position":position,"location":location,
				"reqPublishedDate":reqPublishedDate,"ageing":ageing,"profilesNo":profilesNo,
				"interviewedNo":interviewedNo,"status":status,"staffName":staffName,
				"proposedJoiningDate":proposedJoiningDate,"sowSigned":sowSigned,
				"reason":reason,"bgvCleared":bgvCleared,
				"remark":remark,"csSubDept":csSubDept,"plannedOnboardDate":plannedOnboardDate,
				"doNumber":doNumber,"hrPriority":hrPriority,
				"hsbcDept":hsbcDept,"hsbcSubDept":hsbcSubDept,"onborad":index},
			async:true,
			cache:false,
			type:"post",
			success:function(resultFlag){
				if(resultFlag){
					$("html,body").animate({scrollTop:0}, 500);
					$('#successAlert').html('demand update sucessful!').show();
					setTimeout(function () {
						$('#successAlert').hide();
					}, 2000);
					self.opener.location.reload();
				}
			}
		})
	}else{
		return;
	}*/
}

function back(){
	var urlTo = path+'/service/demand/demandInfo.html';
	window.location.href = urlTo;
}

//获取HR
function getHr(){
	$.ajax({
		url:path+'/service/user/getHR',
		dataType:"json",
		async:true,
		cache:false,
		type:"get",
		success:function(result){
			if(result){
				if(result != null && result.length>0){
					$("#hrdatatable tbody").remove();
					var tbody = $("<tbody>");
					tbody.appendTo($("#hrdatatable"));
					for(var i=0;i<result.length;i++){
						$("<tr>" +
								"<td><input value='"+result[i].userName+"' type='checkbox' name='hremail'/></td>"+
								"<td>"+result[i].userName+"</td>" +
								"<td>"+result[i].nickname+"</td>" +
								"<td>"+result[i].userType+"</td>" +
								"<td>"+result[i].email+"</td>" +
								"</tr>").appendTo(tbody);
					}
					$("#hrlist").modal('show');
					//隐藏掉是否发送邮件的提示
					$("#issendemail").modal('hide');
				}
				
			}
		}
	})
	
}

//发送邮件
function sendemail(){
	$("#hrlist").modal('hide');
	$("#jindu").modal('show');
	obj = document.getElementsByName("hremail");
    ehs = [];
    for(k in obj){
        if(obj[k].checked)
        	ehs.push(obj[k].value);
    }
    //alert(ehs);
    //获取需求编号
    var demandid = $("#demandIdEdit").val();
	
	$.ajax({
		url:path+'/service/sendemail/send2',
		dataType:"json",
		data:{ehr:JSON.stringify(ehs),demandid:demandid},
		async:true,
		cache:false,
		type:"post",
		success:function(result){
			if(result){
				$("#jindu").modal('hide');
				alert("发送成功");
				$("#hrlist").modal('hide');
				var urlTo = path+'/service/demand/demandInfo.html';
				window.location.href = urlTo;
			}
		}
	})
	
}

function selAll(){
		var all=document.getElementById('checkAll');//获取到点击全选的那个复选框的id  
		var one=document.getElementsByName('hremail');//获取到复选框的名称  
		if(all.checked==true){
			for(var i=0;i<one.length;i++){ 
				if(one[i].checked==false){
					one[i].checked=true; 
				}
			} 
		}else{
			for(var i=0;i<one.length;i++){  
				one[i].checked=false;
			} 
		}
}