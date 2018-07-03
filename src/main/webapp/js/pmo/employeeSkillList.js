$(function () {
//	$("fileUpload").blur( function() {
//		alert('upload2');
//	});
	
//	$('#fileUpload').uploadify({
//    	'buttonText' : '上传'
//    	,'auto'     : false
//    	,  'swf'      : path+'/misc/uploadify.swf'
//    	, 'uploader' : path+'/service/skill/skillUpload2' 
//    }); 
	
	
	
//     $("#fileUpload").uploadify({  
////            'height'        : 27,   
////            'width'         : 80,    
//            'buttonText'    : '添加附件',  
//            'swf'           : path+'/misc/uploadify.swf',  
//            'uploader'      : path+'/service/skill/skillUpload2',
//            'auto'          : false,  
//            'fileTypeExts'  : '*.*',  
////            'formData'      : {'userName':'','content':''},  
//            'onUploadStart' : function(file) {  
//                $("#file_upload").uploadify("settings");  
//            },  
//            'onUploadSuccess':function(){  
//                $.messager.show({  
//                    msg : '导入成功！',  
//                    title : '提示'  
//                });  
//            },  
//            'onUploadComplete':function(){  
//                $('#importLispDialog').window('close');  
//            }  
//    });  
  
	
	$('#myModal').modal('hide');
    loadSkillList();
    loadCSSubDept();
    loadLevel();
    loadRole();
    loadSkill();
    
	$("#myfiles").blur( function() {
		skillUpload();
	});
    
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
				$("#csSubDept").append("<option value='"+list[i].csSubDeptId+"'>"+list[i].csSubDeptName+"</option>");
			}
//			$('#csSubDept').val(result.pageInfo.du);
		}
	})
}

function loadSkill(result){
	$.ajax({
		url:path+'/service/skill/skills',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(list){
			$("#paramName").empty();
			$("#paramName").append("<option value=''>--Option--</option>");
			for(var i = 0;i<list.length;i++){
				$("#paramName").append("<option>"+list[i]+"</option>");
			}
//			$('#csSubDept').val(result.pageInfo.du);
		}
	})
}

function loadRole(result){
	var url = path+'/json/role.json';
	$.getJSON(url,  function(data) {
		   $("#role").empty();
		   $("#role").append("<option value=''>--Option--</option>");
	       $.each(data, function(i, item) {
	    	   $("#role").append("<option>"+item.name+"</option>");
	       })
	});
}

function loadLevel(result){
	var url = path+'/json/capabilityLevel.json';
	$.getJSON(url,  function(data) {
		   $("#capabilityLevel").empty();
		   $("#capabilityLevel").append("<option value=''>--Option--</option>");
	       $.each(data, function(i, item) {
	    	   $("#capabilityLevel").append("<option value='"+item.key+"'>"+item.name+"</option>");
	       })
	});
}
function loadLevelToTd(sele, abilityLevel){
	if(abilityLevel==null) abilityLevel='';
	var url = path+'/json/capabilityLevel.json';
	$.getJSON(url,  function(data) {
		$(sele).empty();
		$(sele).append("<option value=''>--Option--</option>");
	    $.each(data, function(i, item) {
	    	 if(item.key==abilityLevel){
	    		 $(sele).append("<option selected value='"+item.key+"'>"+item.name+"</option>");
	    	 }else{
	    		 $(sele).append("<option value='"+item.key+"'>"+item.name+"</option>");
	    	 }
	    })
	});
}

//function loadCSBu(result){
//	var url = path+'/json/csBuName.json'
//	$.getJSON(url,  function(data) {
//		   $("#csBu").empty();
//		   $("#csBu").append("<option value=''>--Option--</option>");
//	       $.each(data, function(i, item) {
//	    	   $("#csBu").append("<option value=''>"+item.name+"</option>");
//	       })
////		   $('#csBu').val(result.pageInfo.bu);
//	});
//}

//function majorcateIds(result){
//	$.ajax({
//		url:path+'/service/skill/majorcateIds',
//		dataType:"json",
//		async:true,
//		cache:false,
//		type:"post",
//		success:function(list){
//			$("#majorcateId").empty();
//			$("#majorcateId").append("<option value=''>--Option--</option>");
//			for(var i = 0;i<list.length;i++){
//				$("#majorcateId").append("<option value='"+list[i].majorcateId+"'>"+list[i].name+"</option>");
//			}
//		}
//	})
//}

function loadSkillList(){
    var queryUrl = path+'/service/skill/query';
	//var queryUrl = path+'/json/test.json';
    var table = $('#skillList').bootstrapTable({
        url: queryUrl,                      //请求后台的URL（*）
        method: 'GET',                      //请求方式（*）
        toolbar: '#toolbar',              //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        fixedColumns: true,
        fixedNumber: 6,
        singleSelect : false,                // 单选checkbox 
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        pageSize: 10,                     //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                      //是否显示表格搜索
        strictSearch: true,
        showColumns: false,                  //是否显示所有的列（选择显示的列）
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        //得到查询的参数
        queryParams : function (params) {
        	return {
        		pageSize: params.limit,
        		pageNumber: params.offset/params.limit+1,
            };
        },
        columns: [
        {
        	checkbox: true,  
            visible: true                 //是否显示复选框  
        }, 
        {
            field: 'eHr',
            title: 'E-HR',
            sortable: true
        },
        {
            field: 'staffId',
            title: 'Staff ID',
            sortable: true
        },
        {
            field: 'lob',
            title: 'LOB',
            sortable: true
        }, 
        {
            field: 'staffName',
            title: 'Staff Name',
            sortable: true
            , formatter : function(value, row, index){
          	  	var div = "<div style='width:200px;'>"+value+"</div>";
          	  	return div;
            }
        },
        {
//            field: 'csSubDept',
            field: 'du',
            title: 'DU',
            sortable: true
            , formatter : function(value, row, index){
            	  var div = "<div style='width:150px;'>"+value+"</div>";
            	  return div;
            }
        },
        {
            field: 'role',
            title: 'Role',
            sortable: true
        },
        {
            field: 'paramName',
            title: 'Major Skill',
            sortable: true
        }
        ,{
            field: 'officialAccreditation',
            title: 'Official Certification',
            sortable: true
            ,formatter:function(value,row,index){
            	if('1'==value){
            		return 'Yes';
            	}
            	return 'No';
            }
        }
        ,{
            field: 'workExperience',
//            title: 'work Experience(Years)',
            title: 'work(Years)',
            sortable: true
        }
        ,{
//            field: 'Operate',
            title: 'Operate'
            ,formatter:function(value,row,index){
//            	 class='btn btn-info btn-small'
            	var r = row.id==null?'':"<a href='javascript:void(0);' class='btn-info btn-small' onclick=detail('"+row.eHr+"')>Detail</a>" ;
            	return r+"<a href='javascript:void(0);' class='btn-info btn-small' onclick=toEdit('"+row.eHr+"')>Edit</a>" ;
            }
        }
        /*{
            field:'id',
            title: 'Operation',
            width: 120,
            align: 'center',
            valign: 'middle'
//        }*/
        
        ]
        ,onLoadSuccess: function () {
//        	var data = $('#skillList').bootstrapTable('getData');
        },
        onLoadError: function () {
        	
        },
        onDblClickRow: function (row, $element) {
           
        }
        
    });
}

function search(){
//	//获取查询条件
	var eHr = $("#eHr").val();
	var staffId = $("#staffId").val();
	var staffName = $("#staffName").val();
	var lob = $("#lob").val();
	var role = $("#role").val();
	var csSubDept = $("#csSubDept").val();
	var paramName = $("#paramName").val();
	var officialAccreditation = $('#officialAccreditation').is(':checked')?'1':'';
	var workExperience = $("#workExperience").val();
	var capabilityLevel = $("#capabilityLevel").val();
	var mainAbility  ;
	if($('#mainAbility').is(':checked') && paramName=='') {
//		officialAccreditation='1';
		alert('Please choose Skill list for Main Skill');
		return ;
	}
	mainAbility = $('#mainAbility').is(':checked')?'1':'';
	var queryParams = { 
		query: {  
			eHr:eHr
			,staffId:staffId
			,staffName:staffName
			,lob:lob
			,role:role
			,csSubDept:csSubDept
			,paramName:paramName
			,officialAccreditation:officialAccreditation
			,workExperience:workExperience
			,abilityLevel:capabilityLevel
			,mainAbility:mainAbility
        }
    }  
	//刷新表格  
    $('#skillList').bootstrapTable('refresh',queryParams);  
}

//function editSkill(id){
////	$("#editForm").attr("action",path+"/service/capability/editPage.html");
////	$("#id").val(id);
////	$("#editForm").submit();
//	var url= path+"/service/skill/editPage.html"+"?id="+id
//	location=url;
//}

function detail(eHr){
	$("tr:empty").remove();
	$("#updateSkills").css("display","none");
	$("#batchUpdate").css("display","none");
	var queryUrl = path+'/service/skill/detail/'+eHr;
	$.ajax({
		url:queryUrl,
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(data){
			$("#detailBtn").click();
//			$("#accordion tbody").remove();
			$("#accordion > div:gt(0)").remove();
			
//			var _firstDiv = $("#accordion > div");
			
			var tbody ;
			var last_majorcate = '' ;
			$("#myModalLabel").text("View Detail Information of "+eHr);
				
			for (var i = 0; i <data.length; i++) {
//				if(i>1) return;
				if(last_majorcate!=data[i].majorcateId){
					var div_id='div_'+data[i].majorcateId;
					var a_id='a_'+data[i].majorcateId;
//					$("#accordion > div:first-child").clone().insertAfter($("#accordion > div:last-child")); 
					$("#accordion > div:first-child").clone().appendTo($("#accordion")); 
					$("#accordion > div:last-child").attr('id',div_id);
					
					$("#"+div_id +" a").attr('href','#'+a_id);
					$("#"+div_id +" a").text(data[i].fatherName);
					$("#"+div_id +" > div").eq(1).attr('id', a_id);
					
					$("#accordion > div:last-child").css("display","block");
					tbody = $("#accordion tbody:last-child");
					div_id = '';
				}
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);
				
				var td1 = $("<td width='15%'>"	+data[i].paramName + "</td>");
				var td2 = $("<td width='12%'>"	+(data[i].abilityLevel==null?'':showLeve(data[i].abilityLevel))	+ "</td>");
				var td3 = $("<td>"	+( data[i].mainAbility==null?'': ('1'==data[i].mainAbility?'<input CHECKED type="radio"/>':'' )	)+ "</td>");
				var td4 = $("<td>"	+( data[i].officialAccreditation==null?'':('1'==data[i].officialAccreditation?'Yes':'No' ))		+ "</td>");
				var td5 = $("<td>"	+( data[i].authenticationName==null?'':data[i].authenticationName)	+ "</td>");
				var td6 = $("<td>"	+( data[i].workExperience==null?'':data[i].workExperience)	+ "</td>");
				
				td1.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);
				td4.appendTo(tr);
				td5.appendTo(tr);
				td6.appendTo(tr);
				last_majorcate = data[i].majorcateId ;
			}
		}
	});
	$(".panel-collapse").css("in");
}
	
function toEdit(eHr){
	$("tr:empty").remove();
	$("#updateSkills").css("display","block");
	$("#batchUpdate").css("display","none");
	var queryUrl = path+'/service/skill/toEdit/'+eHr;
	$.ajax({
		url:queryUrl,
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(data){
			$("#detailBtn").click();
//			$("#accordion tbody").remove();
			$("#accordion > div:gt(0)").remove();
			
//			var _firstDiv = $("#accordion > div");
			
			var tbody ;
			var last_majorcate = '' ;
			$("#myModalLabel").text("Edit Competence Tag for "+eHr);
			for (var i = 0; i <data.length; i++) {
				if(last_majorcate!=data[i].majorcateId){
					var div_id='div_'+data[i].majorcateId;
					var a_id='a_'+data[i].majorcateId;
					$("#accordion > div:first-child").clone().appendTo($("#accordion")); 
					$("#accordion > div:last-child").attr('id',div_id);
					
					$("#"+div_id +" a").attr('href','#'+a_id);
					$("#"+div_id +" a").text(data[i].fatherName);
					$("#"+div_id +" > div").eq(1).attr('id', a_id);
					
					$("#accordion > div:last-child").css("display","block");
					tbody = $("#accordion tbody:last-child");
					div_id = '';
				}
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);
				
				var td1 = $("<td width='15%'>" +"<input id='capparamId' type='hidden' value='"+(data[i].capparamId==null?'':data[i].capparamId)+"'/>"
						+"<input id='status' type='hidden' value='"+data[i].status+"'/>"
						+"<input id='id' type='hidden' value='"+(data[i].id==null?'':data[i].id)+"'/>"
						+"<input id='ischeck' type='checkbox'"+ (data[i].status=='1'?'checked':'')
						+" />"  	+data[i].paramName + "</td>");
				var td2 = $("<td  width='12%'></td>");  
				var sel_level = $("<SELECT ID='capabilityLevel' ></SELECT>");
				var td3 = $("<td>"	+( data[i].mainAbility==null?'<input id="mainAbility" name="mainAbility" type="radio" value="0"/>': 
						('1'==data[i].mainAbility?'<input id="mainAbility" name="mainAbility" CHECKED type="radio" value="1"/>':
							'<input id="mainAbility" name="mainAbility" type="radio" value="0"/>' )	
						)
						+ "</td>"); 
				var td4 = $("<td>"	+( data[i].officialAccreditation==null?
						'<SELECT ID="officialAccreditation" ><OPTION VALUE="1">Yes<OPTION VALUE="0" selected="selected">No</SELECT>'
						:('1'==data[i].officialAccreditation?
							'<SELECT ID="officialAccreditation" ><OPTION VALUE="1" selected="selected">Yes<OPTION VALUE="0">No</SELECT>':
							'<SELECT ID="officialAccreditation" ><OPTION VALUE="1">Yes<OPTION VALUE="0" selected="selected">No</SELECT>' ))		+ "</td>");
				var td5 = $("<td>"+(data[i].authenticationName==null?"<input id='authenticationName' type='text' maxLength=100 />": 
					"<input id='authenticationName' type='text' value='"+data[i].authenticationName + "' maxLength=100 />")+"</td>") ;
				var td6 = $("<td>"+(data[i].workExperience==null?"<input id='workExperience' type='text' onkeypress='return event.keyCode>=48&&event.keyCode<=57' ng-pattern=''/[^a-zA-Z]/' size=2 maxLength=2 />": 
					"<input id='workExperience' type='text' value='"+data[i].workExperience + "' onkeypress='return event.keyCode>=48&&event.keyCode<=57' ng-pattern=''/[^a-zA-Z]/' size=2 maxLength=2 />")+"</td>") ;
				
				td1.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);
				td4.appendTo(tr);
				td5.appendTo(tr);
				td6.appendTo(tr);
				
				sel_level.appendTo(td2);
				loadLevelToTd(sel_level, data[i].abilityLevel);
				last_majorcate = data[i].majorcateId ;
			}
		}
	});
	$(".panel-collapse").css("in");
}

function update(eHr){
	$("tr:empty").remove();
	
	var _ischeck = $("#accordion tbody tr").find("#ischeck:checked").length==0?true:false; 
	if(_ischeck){
		alert("Please choose  at least one  skills.");
		return ;
	}
	
	var _mainAbility = $("#accordion tbody tr").find("input[name='mainAbility']").is(':checked')?false:true; 
	if(_mainAbility){
		alert("Please choose Main Ability.");
		return ;
	}
	
	
	var eHr= $("#myModalLabel").text().replace('Edit Competence Tag for ','');
	var url = path+'/service/skill/save';
	var rtn=1;
	$("#accordion tbody tr").each(function(index,domEle){
//		alert( $(domEle).find("td").length);
//		alert( $(domEle).find("td").eq(0).text());
//		return ;
		
		var ischeck=$(domEle).find('#ischeck').prop('checked'); 
		var status=$(domEle).find('#status').val();
		var id=$(domEle).find('#id').val();
		if(ischeck){
			var capabilityLevel= $(domEle).find("#capabilityLevel").val();
			var paramName= $(domEle).find("td").eq(0).text();
			var capparamId=$(domEle).find('#capparamId').val();
			var mainAbility=$(domEle).find('#mainAbility').prop('checked')?'1':''; 
			var officialAccreditation=$(domEle).find("#officialAccreditation").val();
			var authenticationName=$(domEle).find("#authenticationName").val(); 
			authenticationName=$.trim(authenticationName);
			var workExperience=$(domEle).find("#workExperience").val(); 
//			alert(capparamId+','+id+','+mainAbility+','+officialAccreditation+','+authenticationName+','+workExperience);  
			$.ajax({
				url:url,
				dataType:"json",
				data:{"abilityLevel":capabilityLevel, "employeeId":eHr, "status":status, "capparamId":capparamId, "id":id,  "mainAbility":mainAbility
					, "officialAccreditation":officialAccreditation, "authenticationName":authenticationName, "workExperience":workExperience },
				async:true,
				cache:false,
				type:"post",
				success:function(flag){
					if(flag=='0'){
						alert(eHr +" update "+paramName+" fail1");
					}
				}
			})
		}else{
			//del
			if(status=='1'){
				url = path+'/service/skill/delete/'+id;
				$.post(url);
			}
		}
		
		
	});
	alert(eHr+" update Success.");
	$(".modal-footer .group > button").click();
}

function showLeve(n){
	var v='';
	switch(n)
	{
		case '0':
	        v='junior';
	        break;
	    case '1':
	    	v='intermediate';
	        break;
	    case '2':
	    	v='senior';
	        break;
	}
	return v;
}

function toBatch(){
	if($(".bs-checkbox :checkbox:checked").length==0){
		alert("please select employees.");
		return ;
	}
	$("tr:empty").remove();
	$("#updateSkills").css("display","none");
	$("#batchUpdate").css("display","block");
	
	var queryUrl = path+'/service/skill/toBatch';
	$.ajax({
		url:queryUrl,
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(data){
			$("#detailBtn").click();
			$("#accordion > div:gt(0)").remove();
			var tbody ;
			var last_majorcate = '' ;
			$("#myModalLabel").text("Batch Edit");
			for (var i = 0; i <data.length; i++) {
				if(last_majorcate!=data[i].majorcateId){
					var div_id='div_'+data[i].majorcateId;
					var a_id='a_'+data[i].majorcateId;
					$("#accordion > div:first-child").clone().appendTo($("#accordion")); 
					$("#accordion > div:last-child").attr('id',div_id);
					
					$("#"+div_id +" a").attr('href','#'+a_id);
					$("#"+div_id +" a").text(data[i].fatherName);
					$("#"+div_id +" > div").eq(1).attr('id', a_id);
					
					$("#accordion > div:last-child").css("display","block");
					tbody = $("#accordion tbody:last-child");
					div_id = '';
				}
				var tr = $("<tr></tr>");
				tr.appendTo(tbody);
				
				var td1 = $("<td width='15%'>" +"<input id='capparamId' type='hidden' value='"+(data[i].capparamId==null?'':data[i].capparamId)+"'/>"
//						+"<input id='status' type='hidden' value='"+data[i].status+"'/>"
//						+"<input id='id' type='hidden' value='"+(data[i].id==null?'':data[i].id)+"'/>"
						+"<input id='ischeck' name='ischeck' type='radio' onclick='checkWithMainAbility(this)'	 />"  	+data[i].paramName 
						+ "</td>");
				var td2 = $("<td  width='12%'></td>");  
				var sel_level = $("<SELECT ID='capabilityLevel' ></SELECT>");
				var td3 = $("<td>" + '<input id="mainAbility" name="mainAbility" type="radio" value="0" onclick="checkWithMainAbility(this)" />'
//						+( data[i].mainAbility==null?'<input id="mainAbility" name="mainAbility" type="radio" value="0"/>': 
//						('1'==data[i].mainAbility?'<input id="mainAbility" name="mainAbility" CHECKED type="radio" value="1"/>':
//							'<input id="mainAbility" name="mainAbility" type="radio" value="0"/>' )	
//						)
						+ "</td>"); 
				var td4 = $("<td>"	 + '<SELECT ID="officialAccreditation" ><OPTION VALUE="1">Yes<OPTION VALUE="0" selected="selected">No</SELECT>'
//						+( data[i].officialAccreditation==null?
//						'<SELECT ID="officialAccreditation" ><OPTION VALUE="1">Yes<OPTION VALUE="0" selected="selected">No</SELECT>'
//						:('1'==data[i].officialAccreditation?
//							'<SELECT ID="officialAccreditation" ><OPTION VALUE="1" selected="selected">Yes<OPTION VALUE="0">No</SELECT>':
//							'<SELECT ID="officialAccreditation" ><OPTION VALUE="1">Yes<OPTION VALUE="0" selected="selected">No</SELECT>' ))	
							+ "</td>");
				var td5 = $("<td>" + "<input id='authenticationName' type='text' maxLength=100 />"
//						+(data[i].authenticationName==null?"<input id='authenticationName' type='text' maxLength=100 />": 
//					"<input id='authenticationName' type='text' value='"+data[i].authenticationName + "' maxLength=100 />")
					+"</td>") ;
				var td6 = $("<td>" + "<input id='workExperience' type='text' onkeypress='return event.keyCode>=48&&event.keyCode<=57' ng-pattern=''/[^a-zA-Z]/' size=2 maxLength=2 />"
//						+(data[i].workExperience==null?"<input id='workExperience' type='text' onkeypress='return event.keyCode>=48&&event.keyCode<=57' ng-pattern=''/[^a-zA-Z]/' size=2 maxLength=2 />": 
//					"<input id='workExperience' type='text' value='"+data[i].workExperience + "' onkeypress='return event.keyCode>=48&&event.keyCode<=57' ng-pattern=''/[^a-zA-Z]/' size=2 maxLength=2 />")
					+"</td>") ;
				
				td1.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);
				td4.appendTo(tr);
				td5.appendTo(tr);
				td6.appendTo(tr);
				
				sel_level.appendTo(td2);
				loadLevelToTd(sel_level, data[i].abilityLevel);
				last_majorcate = data[i].majorcateId ;
			}
		}
	});
	$(".panel-collapse").css("in");
}

function batchUpdate(){
	var eHrs ='';
	$("#skillList .bs-checkbox :checkbox:checked").each(function(i){
		eHrs +=$(this).parent().parent().find("td").eq(1).text() + ",";
	 });
	eHrs = eHrs.substring(0, eHrs.length-1);
	
	$("tr:empty").remove();
	
	var _ischeck = $("#accordion tbody tr").find("#ischeck:checked").length==0?true:false; 
	if(_ischeck){
		alert("Please choose  at least one  skills.");
		return ;
	}
	
	var _mainAbility = $("#accordion tbody tr").find("input[name='mainAbility']").is(':checked')?false:true; 
	if(_mainAbility){
		alert("Please choose Main Ability.");
		return ;
	}
	
	
//	var eHr= $("#myModalLabel").text().replace('Edit Competence Tag for ','');
	var url = path+'/service/skill/batch';
	var rtn=1;
	$("#accordion tbody tr").each(function(index,domEle){
		var ischeck=$(domEle).find('#ischeck').prop('checked'); 
		var status=$(domEle).find('#status').val();
		var id=$(domEle).find('#id').val();
		if(ischeck){
			var capabilityLevel= $(domEle).find("#capabilityLevel").val();
			var paramName= $(domEle).find("td").eq(0).text();
			var capparamId=$(domEle).find('#capparamId').val();
			var mainAbility=$(domEle).find('#mainAbility').prop('checked')?'1':''; 
			var officialAccreditation=$(domEle).find("#officialAccreditation").val();
			var authenticationName=$(domEle).find("#authenticationName").val(); 
			authenticationName=$.trim(authenticationName);
			var workExperience=$(domEle).find("#workExperience").val(); 
			$.ajax({
				url:url,
				dataType:"json",
				data:{"abilityLevel":capabilityLevel, "employeeId":eHrs, "status":status, "capparamId":capparamId, "id":id,  "mainAbility":mainAbility
					, "officialAccreditation":officialAccreditation, "authenticationName":authenticationName, "workExperience":workExperience 
					, 
					},
				async:true,
				cache:false,
				type:"post",
				success:function(flag){
//					if(flag=='1'){
//						alert("Batch update fail1.");
//					}else{
//						alert("Batch update success.");
//					}
				}
			})
		}
		
	});
	alert("Batch update Success.");
	$(".modal-footer .group > button").click();
}

function checkWithMainAbility(obj){
	if($(obj).is(':checked')){
		$(obj).parent().parent().find("#mainAbility").attr('checked','true');
	}
}

function skillUpload(){
	if(''!=$("#myfiles")[0].value){
		$('#uploadForm').submit();
	}
	
}

