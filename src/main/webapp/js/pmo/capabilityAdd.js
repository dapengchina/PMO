function majorcateIds(result){
	var url = path+'/json/capabilityLabelType.json'
	$.getJSON(url,  function(data) {
		   $("#majorcateId").empty();
		   $("#majorcateId").append("<option value=''>--Option--</option>");
	       $.each(data, function(i, item) {
	    	   $("#majorcateId").append("<option value='"+ item.key + "'>"+item.name+"</option>");
	       })
//		   $('#csBu').val(result.pageInfo.bu);
	});
}

//function majorcateIds(result){
//	$.ajax({
//		url:path+'/service/capability/majorcateIds',
//		dataType:"json",
//		async:true,
//		cache:false,
//		type:"post",
//		success:function(list){
//			$("#majorcateId").empty();
//			$("#majorcateId").append("<option value=''>--Option--</option>");
//			for(var i = 0;i<list.length;i++){
//				$("#majorcateId").append("<option value='"+list[i].majorcateId+"'>"+list[i].paramName+"</option>");
//			}
////			$('#csSubDept').val(result.pageInfo.du);
//		}
//	})
//}

$(document).ready(function() {
//	loadDu();
//	loadBu();
	majorcateIds();
	$('#addCapabilityForm').bootstrapValidator({
		message: 'This value is not valid',

        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	paramName: {
            	group: '.group',
                validators: {
                    notEmpty: {
                        message: 'Please enter paramName'
                    }
                }
            },
            majorcateId: {
            	group: '.group',
                validators: {
                    notEmpty: {
                        message: 'Please select majorcate'
                    }
                }
            }
//            ,subcateId: {
//            	group: '.group',
//                validators: {
//                    notEmpty: {
//                        message: 'Please enter subcate'
//                    }
//
//                }
//            }
        }
    }).on('success.form.bv', function(e) {
        // Prevent submit form
        e.preventDefault();

        var $form     = $(e.target);
        validator = $form.data('bootstrapValidator');
        if(validator){
        	addCapability(e.target);
        }
    });
	
	$("#majorcateId").change( function() {
		var data = $(this).children('option:selected').val();  
		if(data =='') return;
		var url = path+'/service/capability/maxSubCate?'+ "majorcateId="+data;
		
		$.get(url, function(rtn){
			  $('#subcateId').val(rtn);
		});
	});
});

//function save(){
//	$.ajax({
//        type: "POST",
//        url: path+"/service/capability/save",
//        contentType: "application/json;charset=utf-8",
//        data: JSON.stringify(row),
//        dataType: 'JSON',
//        success: function (data, status) {
//            if (data == "0") {
//            	var data = $('#OfflineOperList').bootstrapTable('getData');
//            	loadOfflineSummary(data);
//            }
//        },
//        error: function () {
//            alert('编辑失败');
//        },
//        complete: function () {
//
//        }
//
//    });
//}

function addCapability(){
//	var bootstrapValidator = $("#addCapabilityForm").data('bootstrapValidator');
//	bootstrapValidator.validate();
//	if(bootstrapValidator.isValid()){
		var majorcateId = $("#majorcateId").val();
		var subcateId = $("#subcateId").val();
		var paramName = $("#paramName").val();
		$.ajax({
			url:path+'/service/capability/add',
			dataType:"json",
			data:{"majorcateId":majorcateId,"paramName":paramName,"subcateId":subcateId},
			async:true,
			cache:false,
			type:"post",
			success:function(flag){
				if(flag){
					$("html,body").animate({scrollTop:0}, 500);
					$('#successAlert').html('Capability information added succesffully').show();
					setTimeout(function () {
						$('#successAlert').hide();
					}, 2000);
//					$("#addCapabilityForm")[0].reset();
					location=path+"/service/capability/listPage";
				}
				
//				if(flag=='0'){
//					$("html,body").animate({scrollTop:0}, 500);
//					$('#successAlert').html('Candidate:'+candidateName+' information added succesffully').show();
//					setTimeout(function () {
//						$('#successAlert').hide();
//					}, 4000);
//					var urlTo = path+"/service/candidate/getMyCandidate.html";
//					window.location.href = urlTo;
//					/*window.location.href=path+"/service/resume/input.html";*/
//				}else{
//					alert("Information added unsuccesffully!")
//				}
			}
		})
		
//	}else{
//		return;
//	}
	
}



