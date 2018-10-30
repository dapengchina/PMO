$(document).ready(function() {
	majorcateIds();
	queryById();
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
            ,subcateId: {
            	group: '.group'
            }
        }
    }).on('success.form.bv', function(e) {
        // Prevent submit form
        e.preventDefault();

        var $form     = $(e.target);
        validator = $form.data('bootstrapValidator');
        if(validator){
        	updateCapability(e.target);
        }
    });
	
	$("#majorcateId").change( function() {
		var data = $(this).children('option:selected').val();  
		if(data =='') return;
		var _majorcateId = $("#_majorcateId").val();  
		if(_majorcateId==data) return;
		var url = path+'/service/capability/maxSubCate?'+ "majorcateId="+data;
		
		$.get(url, function(rtn){
			  $('#subcateId').val(rtn);
		});
	});
});

function majorcateIds(result){
	$.ajax({
		url:path+'/service/capability/majorcateIds',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(list){
			$("#majorcateId").empty();
			$("#majorcateId").append("<option value=''>--Option--</option>");
			for(var i = 0;i<list.length;i++){
				$("#majorcateId").append("<option value='"+list[i].majorcateId+"'>"+list[i].paramName+"</option>");
			}
		}
	})
}

function updateCapability(){
		var id = $("#id").val();
		var majorcateId = $("#majorcateId").val();
		var subcateId = $("#subcateId").val();
		var paramName = $("#paramName").val();
		$.ajax({
			url:path+'/service/capability/update',
			dataType:"json",
			data:{"id":id,"majorcateId":majorcateId,"paramName":paramName,"subcateId":subcateId},
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
//					location=path+"/service/capability/listPage";
				}
			}
		})
}

function queryById(){
	var id = $('#id').val();
	$.ajax({
		url:path+'/service/capability/queryById',
		dataType:"json",
		data:{"id":id},
		async:true,
		cache:false,
		type:"post",
		success:function(data){
			$('#majorcateId').val(data.majorcateId);
			$('#subcateId').val(data.subcateId);
			$('#paramName').val(data.paramName);
			$('#_majorcateId').val(data.majorcateId);
		}
	})
}


