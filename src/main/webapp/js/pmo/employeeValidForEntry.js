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
                        message: 'Please enter E-HR'
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
                        message: 'Exceeded the maxLength'
                    },
                    remote:{
                    	type:"post",
                    	url: path+'/service/employee/checkLob',
                        message:"LOB already exists"
                    }
                }
            },
            staffName: {
                validators: {
                    notEmpty: {
                        message: 'Please enter staffName'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxLength'
                    },

                }
            },
           
            graduationDate1: {
                validators: {
                    notEmpty: {
                        message: 'Please select Graduation Date'
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
                        message: 'Please enter billRate'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxLength'
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
                          message: 'Please enter correct Email'
                      },
                      stringLength: {
                          max: 32,
                          message: 'Exceeded the maxLength'
                      },
                      
                }
            },
            entryDate1: {
            	validators: {
                notEmpty: {
                    message: 'Please select EntryDate'
                },
                date : {  
                    format : 'YYYY-MM-DD',  
                    message : 'Time format is incorrect'  
                }

            }
        },
         
        }
    });
    
});



