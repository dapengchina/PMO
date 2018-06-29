$(document).ready(function() {
	$('#registerEmployeeForm').bootstrapValidator({
		message: 'This value is not valid',

        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        excluded:[":disabled"],
        fields: {
        	eHr: {
				validators: {
                    notEmpty: {
                        message: 'Please enter ehr'
                    },
                    regexp: {
                        regexp: /^E\d{9}$/,
                        message: 'Please enter the E-HR（E and 9 digits）'
                    },
                    remote:{
                    	type:"post",
                    	url: path+'/service/employee/checkEhr',
                        message:"E-HR already exists"
                    }

                 }
            },
            billRateType: {
                validators: {
                    notEmpty: {
                        message: 'Please select billratetype'
                    }
                }
            },
            csSubDept: {
                validators: {
                    notEmpty: {
                        message: 'Please select csdept'
                    }
                }
            },
            demandrr: {
                validators: {
                    notEmpty: {
                        message: 'Please click then select demand'
                    }
                }
            },
            gbGf: {
                validators: {
                    notEmpty: {
                        message: 'Please select gbgf'
                    }
                }
            },
            role: {
                validators: {
                    notEmpty: {
                        message: 'Please select msarole'
                    }
                }
            },
            skill: {
                validators: {
                    notEmpty: {
                        message: 'Please select skills'
                    }
                }
            },
            nickName: {
                validators: {
                    notEmpty: {
                        message: 'Please select rm'
                    }
                }
            },
            hsbcDept: {
                validators: {
                    notEmpty: {
                        message: 'Please select dept'
                    }
                }
            },
            hsbcSubDept: {
                validators: {
                    notEmpty: {
                        message: 'Please select subdept'
                    }
                }
            },

            lob: {
                validators: {
                    notEmpty: {
                        message: 'Please enter lob'
                    },
                    numeric: {
                    	message:'Please enter number'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxlength'
                    },
                    remote:{
                    	type:"post",
                    	url: path+'/service/employee/checkLob',
                        message:"LOB already exists"
                    }
                }
            },
            hsbcStaffId: {
                validators: {
                	notEmpty: {
                        message: 'Please enter staffid'
                    },
                    /*numeric: {
                	message:'Please enter number'
                    },*/
				    regexp:{
        			   regexp:/^[0-9a-zA-Z\s?]+$/,
        			   message:'please enter right format.'
        		    },
                    remote:{
                    	type:"post",
                    	url: path+'/service/employee/checkHSBCStaffID',
                        message:"hsbcStaffId already exists"
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxlength'
                    },
                }
            },
            staffName: {
                validators: {
                    notEmpty: {
                        message: 'Please enter staffname'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxlength'
                    },

                }
            },
            LN: {
                validators: {
                	notEmpty: {
                        message: 'Please enter ln'
                    },
                	regexp:{
            			regexp:/^[0-9a-zA-Z\s?]+$/,
            			message:'please enter english.'
            		},
            		stringLength: {
                        max: 32,
                        message: 'Exceeded the maxlength'
                    },
                }
            },

            hsbcProjectName: {
                validators: {
                    notEmpty: {
                        message: 'Please enter projectname'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxlength'
                    },

                }
            },
        
            hsbcProjectManager: {
                validators: {
                    notEmpty: {
                        message: 'Please select projectmanager'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxlength'
                    },

                }
            },
            sow: {
                validators: {
                    notEmpty: {
                        message: 'Please select sow'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxlength'
                    },

                }
            },
            graduationDate1: {
                validators: {
                    notEmpty: {
                        message: 'Please select graduation date'
                    },
                    date : {  
                        format : 'YYYY-MM-DD',  
                        message : 'Time format is incorrect'  
                    }

                }
            },
            hsbcDOJ1: {
                validators: {
                    notEmpty: {
                        message: 'Please select doj'
                    },
                    date : {  
                        format : 'YYYY-MM-DD',  
                        message : 'Time format is incorrect'  
                    }

                }
            },
            chsoftiProStartDate1: {
                validators: {
                    notEmpty: {
                        message: 'Please select chsoftiprostartdate'
                    },
                    date : {
                        format : 'YYYY-MM-DD',
                        message : 'Time format is incorrect'
                    }
                }
            },
            sowExpiredDate1: {
                validators: {
                    notEmpty: {
                        message: 'Please select sowexpireddate'
                    },
                    date : {  
                        format : 'YYYY-MM-DD',  
                        message : 'Time format is incorrect'  
                    }

                }
            },
            billRate: {
                validators: {
                    notEmpty: {
                        message: 'Please enter billrate'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxlength'
                    },
                    regexp:{
            			regexp:/^([1-9]\d*(\.\d*[0-9])?(\/(m|M))?)$|^(0\.\d*[0-9](\/(m|M))?)$/,
            			message:'Please enter the correct format.'
            		},
                }
            },
            itworkyear: {
                validators: {
                    notEmpty: {
                        message: 'Please enter itworkyear'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxlength'
                    },
                    regexp:{
            			regexp:/^([1-9]\d*(\.\d*[0-9])?(\/(m|M))?)$|^(0\.\d*[0-9](\/(m|M))?)$/,
            			message:'Please enter the correct format.'
            		},
                }
            },
            email: {
                validators: {
                	 regexp: {
                          regexp:/^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/,
                          message: 'Please enter correct email'
                      },
                      stringLength: {
                          max: 100,
                          message: 'Exceeded the maxLength'
                      },
                      
                }
            },
            entryDate1: {
            	validators: {
                notEmpty: {
                    message: 'Please select entrydate'
                },
                date : {  
                    format : 'YYYY-MM-DD',  
                    message : 'Time format is incorrect'  
                }

            }
        },
         
        }
    });
    
    $('#updateEmployeeForm').bootstrapValidator({
		message: 'This value is not valid',

        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	eHr: {
				validators: {
                    notEmpty: {
                        message: 'Please enter ehr'
                    },
                    regexp: {
                        regexp: /^E\d{9}$/,
                        message: 'Please enter the E-HR（E and 9 digits）'
                    },
                    

                 }
            },

            lob: {
                validators: {
                    notEmpty: {
                        message: 'Please enter lob'
                    },
                    numeric: {
                    	message:'Please enter number'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxlength'
                    },
                }
            },
            hsbcStaffId: {
                validators: {
                	notEmpty: {
                        message: 'Please enter staffid'
                    },
                    /*numeric: {
                	message:'Please enter number'
                    },*/
				    regexp:{
        			   regexp:/^[0-9a-zA-Z\s?]+$/,
        			   message:'please enter right format.'
        		    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxLength'
                    },
                   
                }
            },
            staffName: {
                validators: {
                    notEmpty: {
                        message: 'Please enter staffname'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxlength'
                    },

                }
            },
            staffLocation: {
                validators: {
                    notEmpty: {
                        message: 'Please select stafflocation'
                    }
                }
            },
            locationType: {
                validators: {
                    notEmpty: {
                        message: 'Please select locationtype'
                    }
                }
            },
            csSubDept: {
                validators: {
                    notEmpty: {
                        message: 'Please select csdept'
                    }
                }
            },
            gbGf: {
                validators: {
                    notEmpty: {
                        message: 'Please select gbgf'
                    }
                }
            },
            hsbcDept: {
                validators: {
                    notEmpty: {
                        message: 'Please select dept'
                    }
                }
            },
            hsbcSubDept: {
                validators: {
                    notEmpty: {
                        message: 'Please select subdept'
                    }
                }
            },
            staffCategory: {
                validators: {
                    notEmpty: {
                        message: 'Please select staffcatefory'
                    }
                }
            },
            engagementType: {
                validators: {
                    notEmpty: {
                        message: 'Please select engagement'
                    }
                }
            },
            staffRegion: {
                validators: {
                    notEmpty: {
                        message: 'Please select staffregion'
                    }
                }
            },
            onshoreOrOffshore: {
                validators: {
                    notEmpty: {
                        message: 'Please select value'
                    }
                }
            },
            role: {
                validators: {
                    notEmpty: {
                        message: 'Please select msarole'
                    }
                }
            },
            skill: {
                validators: {
                    notEmpty: {
                        message: 'Please select skills'
                    }
                }
            },
            billingCurrency: {
                validators: {
                    notEmpty: {
                        message: 'Please select billingcurrency'
                    }
                }
            },
            billRateType: {
                validators: {
                    notEmpty: {
                        message: 'Please select billratetype'
                    }
                }
            },
            resourceStatus: {
                validators: {
                    notEmpty: {
                        message: 'Please select resourcestatus'
                    }
                }
            },
            LN: {
                validators: {
                	notEmpty: {
                        message: 'Please enter ln'
                    },
            		regexp:{
            			regexp:/^[0-9a-zA-Z\s?]+$/,
            			message:'please enter english.'
            		},
            		stringLength: {
                        max: 32,
                        message: 'Exceeded the maxlength'
                    },
                }
            },
            
            hsbcProjectName: {
                validators: {
                    notEmpty: {
                        message: 'Please enter projectname'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxlength'
                    },
            		
                }
            },
        
            hsbcProjectManager: {
                validators: {
                    notEmpty: {
                        message: 'Please select projectmanager'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxlength'
                    },

                }
            },
            sow: {
                validators: {
                    notEmpty: {
                        message: 'Please select sow'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxlength'
                    },

                }
            },
            billRate: {
                validators: {
                    notEmpty: {
                        message: 'Please Choose billrate'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxlength'
                    },
                    regexp:{
            			regexp:/^([1-9]\d*(\.\d*[0-9])?(\/(m|M))?)$|^(0\.\d*[0-9](\/(m|M))?)$/,
            			message:'Please enter the correct format.'
            		},

                }
            },
            itworkyear: {
                validators: {
                    notEmpty: {
                        message: 'Please enter itworkyear'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxlength'
                    },
                    regexp:{
            			regexp:/^([1-9]\d*(\.\d*[0-9])?(\/(m|M))?)$|^(0\.\d*[0-9](\/(m|M))?)$/,
            			message:'Please enter the correct format.'
            		},
                }
            },
            email: {
                validators: {
                	notEmpty: {
                		message: 'Please enter email'
                	},
                    emailAddress:{
                    	message:'Email address is incorrect'
                    },
                    stringLength: {
                        max: 100,
                        message: 'Exceeded the maxlength'
                    },
                }
            },
            sowExpiredDate1: {
                validators: {
                    notEmpty: {
                        message: 'Please select sowexpireddate'
                    },
                    date : {  
                        format : 'YYYY-MM-DD',  
                        message : 'Time format is incorrect'  
                    }

                }
            },
            graduationDate1: {
                validators: {
                    notEmpty: {
                        message: 'Please select graduation date'
                    },
                    date : {  
                        format : 'YYYY-MM-DD',  
                        message : 'Time format is incorrect'  
                    }

                }
            },
            entryDate1: {
            	validators: {
                notEmpty: {
                    message: 'Please select entrydate'
                },
                date : {  
                    format : 'YYYY-MM-DD',  
                    message : 'Time format is incorrect'  
                }

            }
        },
        hsbcDOJ1: {
            validators: {
                notEmpty: {
                    message: 'Please select doj'
                },
                date : {  
                    format : 'YYYY-MM-DD',  
                    message : 'Time format is incorrect'  
                }

            }
        },
        chsoftiProStartDate1: {
            validators: {
                notEmpty: {
                    message: 'Please select chsoftiprostartdate'
                },
                date : {
                    format : 'YYYY-MM-DD',
                    message : 'Time format is incorrect'
                }
            }
        },
        terminatedDate1: {
            validators: {
                notEmpty: {
                    message: 'Please select terminatedDate'
                },
                date : {  
                    format : 'YYYY-MM-DD',  
                    message : 'Time format is incorrect'  
                }

            }
        },
        }
    }) 
});



