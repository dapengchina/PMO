$(document).ready(function() {
	// 对编辑页面加校验
	$('#recruitdemandFormEdit').bootstrapValidator({
		message : 'This value is not valid',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		excluded:[":disabled"],
		fields : {
			/*rrEdit : {
				validators : {
					notEmpty : {
						message : 'Please enter your rr'
					},
				}
			},*/
			jobCodeEdit : {
				validators : {
					notEmpty : {
						message : 'Please enter your jobCode'
					}
				}
			},
			requestorEdit : {
				validators : {

				}
			},
			ageingEdit : {
				validators : {

				}
			},
			profilesNoEdit : {
				validators : {

				}
			},
			interviewedNoEdit : {
				validators : {

				}
			},
			proposedJoiningDateEdit : {
				validators : {
					notEmpty : {
						message : 'Please entry date'
					},
					date : {
						format : 'YYYY-MM-DD',
						message : 'Time format is incorrect'
					}

				}
			},
			sowSignedEdit : {
				validators : {

				}
			},
			bgvClearedEdit : {
				validators : {

				}
			},
			reasonEdit : {
				validators : {

				}
			},
			remarkEdit : {
				validators : {

				}
			},
			doNumberEdit : {
				validators : {

				}
			},
			plannedOnboardDate1 : {
				validators : {
					notEmpty : {
						message : 'Please entry date'
					},
					date : {
						format : 'YYYY-MM-DD',
						message : 'Time format is incorrect'
					}

				}
			},
			reqPublishedDate1Edit : {
				validators : {
					notEmpty : {
						message : 'Please entry date'
					},
					date : {
						format : 'YYYY-MM-DD',
						message : 'Time format is incorrect'
					}

				}
			},
			
		}
	})

	$('#recruitdemandForm').bootstrapValidator({
		message : 'This value is not valid',

		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			rr : {
				validators : {
					/*notEmpty : {
						message : 'Please enter your rr'
					},*/
					remote : {
						type : "post",
						url : path + '/service/resume/checkTel',
						date : {
							rr : function() {
								return $("#rr").val();
							}
						},
						message : "rr already exists"
					}

				/*
				 * regexp: { regexp: /^([\u4E00-\u9FA5]|\w)*$/, message:
				 * '请勿包含特殊字符' }, stringLength: { min: 1, max: 20, message:
				 * '请输入长度在1到20位之间的用户名' }, remote: { url:
				 * paths+'/service/employee/checkErExists', message: '用戶名不存在',
				 * delay : 2000,//per 2s send a request type: 'POST' }
				 */
				}
			},

			reqPublishedDate1 : {
				validators : {
					notEmpty : {
						message : 'Please entry date'
					},
					date : {
						format : 'YYYY-MM-DD',
						message : 'Time format is incorrect'
					}

				}
			},

			jobCode : {
				validators : {
					notEmpty : {
						message : 'Please enter your jobCode'
					}
				}
			},
			
			requirementNumber : {
				validators : {
					notEmpty : {
						message : 'Please enter your requirement Number'
					},
					regexp: {
                        regexp: /^[0-2]{1,2}$/,
                        message: 'Please enter one or two digit number'
                    },
				}
			},

			skill : {
				validators : {
					notEmpty : {
						message : 'Please enter your jobCode'
					}
				}
			},
			requestor : {
				validators : {
					notEmpty : {
						message : 'Please enter your requestor'
					}

				}
			},
			position : {
				validators : {
					notEmpty : {
						message : 'Please select your position'
					}

				}
			},

			hsbcSubDeptId : {
				validators : {
					notEmpty : {
						message : 'Please enter your hsbcSubDeptId'
					}

				}
			},
			/*
			 * staffName: { validators: { notEmpty: { message: '请输入中文名字' },
			 * regexp: { regexp: /^([\u4E00-\u9FA5])*$/,
			 * 
			 * message: '请输入中文字符' } } },
			 */
			status : {
				validators : {
					notEmpty : {
						message : 'Please select your status'
					}

				}
			},
			hrPriority : {
				validators : {
					notEmpty : {
						message : 'Please select your hrPriority'
					}

				}
			},
			reqPublishedDate : {
				validators : {
					notEmpty : {
						message : 'Please Choose your Date'
					}

				}
			}
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
function jobCode(field, alerttxt) {
	with (field) {
		if (value == null || value == "" || !value == jobCode) {
			alert(alerttxt);
			return false;
		} else {
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