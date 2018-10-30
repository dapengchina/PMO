$(document).ready(function() {
	// 对编辑页面加校验
    $('#recruitdemandFormEdit').bootstrapValidator({
		message: 'This value is not valid',

        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	rrEdit: {
				validators: {
                    /*notEmpty: {
                        message: 'Please enter your rr'
                    },*/
                    remote:{
                    	type:"post",
                    	url: path+'/service/resume/checkTel',
                    	date:{
                    		rr:function(){return $("#rr").val();}
                    	},
                        message:"rr already exists"
                    }

                   /*
					 * regexp: { regexp: /^([\u4E00-\u9FA5]|\w)*$/, message:
					 * '请勿包含特殊字符' }, stringLength: { min: 1, max: 20, message:
					 * '请输入长度在1到20位之间的用户名' }, remote: { url:
					 * paths+'/service/employee/checkErExists', message:
					 * '用戶名不存在', delay : 2000,//per 2s send a request type:
					 * 'POST' }
					 */
                 }
            },
            /*jobCodeEdit: {
                validators: {
                    notEmpty: {
                        message: 'Please enter your jobCode'
                    }
                }
            },*/
            skillEdit: {
                validators: {
                    notEmpty: {
                        message: 'Please enter your skill'
                    }
                }
            },
            positionEdit: {
                validators: {
                    notEmpty: {
                        message: 'Please select your position'
                    }

                }
            },
            locationEdit: {
                validators: {
                    notEmpty: {
                        message: 'Please select your location'
                    }

                }
            },
            /*plannedOnboardDate1: {
                validators: {
                    notEmpty: {
                        message: 'Please entry date'
                    },
                    date : {  
                        format : 'YYYY-MM-DD',  
                        message : 'Time format is incorrect'  
                    }

                }
            },*/
            hrPriorityEdit: {
                validators: {
                    notEmpty: {
                        message: 'Please select your hrPriority'
                    }

                }
            },
            /*profilesNoEdit: {
                validators: {
                    notEmpty: {
                        message: 'Please select your No. of Profiles Sent to HSBC'
                    }

                }
            },*/
            
            /*interviewedNoEdit: {
                validators: {
                    notEmpty: {
                        message: 'Please select your No of Profiles Interviewed'
                    }

                }
            },*/
            reqPublishedDate1Edit: {
                validators: {
                    notEmpty: {
                        message: 'Please entry date'
                    },
                    date : {  
                        format : 'YYYY-MM-DD',  
                        message : 'Time format is incorrect'  
                    }

                }
            },
            /*ageingEdit: {
                validators: {
                    notEmpty: {
                        message: 'Please select your ageing'
                    }

                }
            },*/
            remarkEdit: { 
            	validators: { 
            		notEmpty: {
                        message: 'Please select your Remark'
                    }

            	 } 
            	}, 
            hsbcDeptEdit: {
                validators: {
                    notEmpty: {
                        message: 'Please select your HSBC Department'
                    }

                }
            },
            hsbcSubDeptEdit: {
                validators: {
                    notEmpty: {
                        message: 'Please select your HSBC Sub-Department'
                    }

                }
            },
            requestorEdit: {
                validators: {
                    notEmpty: {
                        message: 'Please enter your requestor'
                    }

                }
            },
            csSubDeptEdit: {
                validators: {
                    notEmpty: {
                        message: 'Please enter your CS Department'
                    }

                }
            },
            candidateNameEdit: {
                validators: {
                    notEmpty: {
                        message: 'Please enter your Staff Name'
                    }

                }
            },
          
           
            proposedJoiningDateEdit: {
            	 validators: {
                     notEmpty: {
                         message: 'Please entry date'
                     },
                     date : {  
                         format : 'YYYY-MM-DD',  
                         message : 'Time format is incorrect'  
                     }

                 }
            },
            bgvClearedEdit: {
                validators: {
                    notEmpty: {
                        message: 'Please select your BGV Cleared'
                    }

                }
            },
            doNumberEdit: {
                validators: {
                    notEmpty: {
                        message: 'Please select your DO number'
                    }

                }
            },
        }
    }) /*
		 * .on('success.form.bv', function(e) { // Prevent submit form
		 * e.preventDefault();
		 * 
		 * var $form = $(e.target); validator =
		 * $form.data('bootstrapValidator'); if(validator){ login(e.target); } }) ;
		 */

    });

// 验证jobCode
function jobCode(field,alerttxt){
	with(field){
		if(value==null || value=="" ||!value==jobCode){
			alert(alerttxt);
			return false;
		}else{
			return true;
		}
	}
	
}



/*
 * function login(e) { var userName = $("#userName").val(); var password =
 * $("#password").val();
 * 
 * $.ajax({ url : path+"/service/manage/login", type : "post", async : true,
 * cache : false, dataType : "json", data : {'userName' : userName, 'password' :
 * password}, timeout : 20000, success : function(result) { if (result) {
 * window.location.href = path+"/service/employee/index.html"; } else {
 * //$("#loginAlert").width(500); $("#loginAlert").html('用户名密码错误');
 * $("#loginAlert").css({color:"red"}); } } }); }
 */
/*
 * $('#submitBtn').bind("click", function(){
 * 
 * window.location.href = path+"/service/employee/index.html";
 * 
 * });
 */