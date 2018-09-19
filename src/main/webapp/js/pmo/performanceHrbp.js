$(function(){
	
})

/*home*/
$("#getEm").click(function(){
	 window.location.href = path+"/service/performanceManagement/homeType.html?status=1";
} );
$("#getFp").click(function(){
	window.location.href = path+"/service/performanceManagement/homeType.html?status=2";
});
$("#getHR").click(function(){
	window.location.href = path+"/service/performanceManagement/homeType.html?status=3";
});
$("#getLOB").click(function(){
	window.location.href = path+"/service/performanceManagement/homeType.html?status=4";
});


$("#getAP").click(function(){
	window.location.href = path+"/service/performanceManagement/approval";
});
$("#getTem").click(function(){
	window.location.href = path+"/service/performanceManagement/template";
});
$("#getDU").click(function(){
	window.location.href = path+"/service/performanceManagement/dataUpload";
});
$("#getLP").click(function(){
	window.location.href = path+"/service/performanceManagement/lastPerformance";
});
$("#getPR").click(function(){
	window.location.href = path+"/service/performanceManagement/perecords";
});



$(document).ready(function () {
	
	var operateFormatter1 = function (value, row, index) {//赋予的参数
		 return [
		  '<button type="button" class="btn btn-default" style="color:#FF0000;border:none";><span class="glyphicon glyphicon-edit"></span></button>'		
		 ].join('');
		 }
	
	var queryUrl = path+'/json/hrbpCatalog.json';
    $('#table').bootstrapTable({
    	url: queryUrl,                      //请求后台的URL（*）
        method: 'GET',                      //请求方式（*）
        //toolbar: '#toolbar',              //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        /*fixedColumns: true,
        fixedNumber: 6,*/
        singleSelect : true,                // 单选checkbox 
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        pageSize: 10,                     //每页的记录行数（*）
        pageList: [10,15,30],        //可供选择的每页的行数（*）
        search: false,                      //是否显示表格搜索
        strictSearch: true,
        showColumns: false,                  //是否显示所有的列（选择显示的列）
        showRefresh: false,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        height: 600,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        clickToSelect:true,  //是否启动点击选中行
        //得到查询的参数
      queryParams : function (params) {
        	return {
        		pageSize: params.limit,
        		pageNumber: params.offset/params.limit+1,
            };
        },       
        columns: [
            { title: 'No.', field: 'no.'},
            { title: 'EHR ID', field: 'ehrID' },
            { title: 'LOB ID', field: 'lobID' },
            { title: 'Name', field: 'name' },
            { title: 'Date on-board', field: 'dateOnBoard' },
            { title: 'MSA ROLE', field: 'msaROLE' },
            { title: 'LOB', field: 'lob' },
            { title: 'BU', field: 'bu' },
            { title: 'DU', field: 'du'},           
            { title: 'Loacation', field: 'location' },
            { title: 'BackBone', field: 'backBone' },
            { title: 'Assessed', field: 'assessed' },
            { title: 'Direct Supervisor', field: 'direcSupervisort' },
            { title: 'Client Feedback', field: 'clientFeedback' },
            { title: 'Pre-Assessment(Refer Client Feedback)', field: 'refer' },
            { title: 'Pre-Assessment', field: 'preAssessment' },
            { title: 'Group Assessment Result', field: 'groupAssessment' },
            { title: 'Performance Facts(A/C/D)', field: 'facts' },
            { title: 'Performance Skip', field: 'skip' },
            { title: 'Remarks', field: 'remarks' },
            { title: 'LastQ', field: 'lastQ' },
            { title: '2 Qs ago', field: '2Qsago' },
            { title: '3 Qs ago', field: '3Qsago' },
            { title: 'Perfoemance Goal', field: 'goal',formatter: operateFormatter1,align:'center'  },
        ]
       
    });    
});


$(document).ready(function () {	
	var operateFormatter2 = function (value, row, index) {//赋予的参数
		 return [
		  '<button type="button"  class="btn btn-default" style="color:#FF0000;border:none";><span class="glyphicon glyphicon-edit"></span></button>'		
			 
			 ].join('');
		 }
	window.operateEvents2 = {
	          'click .btn': function (e, value, row, index) {
	                /*alert(row.qxxh);*/
	              window.location.href=path+"/service/performanceManagement/approvalPage";
	            }
	        };

    $('#table1').bootstrapTable({
    	url: path+'/json/approval.json',                      //请求后台的URL（*）
        method: 'GET',                      //请求方式（*）
        striped: false,                      //是否显示行间隔色
        singleSelect : false,                // 单选checkbox 
        pagination: true,                   //是否显示分页（*）
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）               
        columns: [
        	{ checkbox: true },
        	{ title: 'No.', field: 'no'},
        	{ title: 'DU', field: 'ehrID' },
        	{ title: 'year', field: 'year' },
        	{ title: 'Quarter', field: 'quarter' },
        	{ title: 'Status', field: 'status' },
        	{ title: 'Detail', 
        		field: 'detail',
        		events: operateEvents2,
        		formatter: operateFormatter2,
        		align:'center'  
        	}
        	
        ]
       
    });    
});




$(document).ready(function () {
	var operateFormatter3 = function (value, row, index) {//赋予的参数
		 return [
		  '<button type="button" class="btn btn-default" style="color:#FF0000;border:none";><span class="glyphicon glyphicon-edit"></span></button>'		
		 ].join('');
		 }
    $('#tablePerformance').bootstrapTable({
    	url: path+'/json/hrbpCatalog.json',                      //请求后台的URL（*）
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
        pageList: [10,15,30],        //可供选择的每页的行数（*）
        search: false,                      //是否显示表格搜索
        strictSearch: true,
        showColumns: false,                  //是否显示所有的列（选择显示的列）
        showRefresh: false,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        height: 600,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        clickToSelect:true,  //是否启动点击选中行
        //得到查询的参数
      queryParams : function (params) {
        	return {
        		pageSize: params.limit,
        		pageNumber: params.offset/params.limit+1,
            };
        },       
        columns: [
        	{ checkbox: true },
            { title: 'No.', field: 'no.'},
            { title: 'EHR ID', field: 'ehrID' },
            { title: 'LOB ID', field: 'lobID' },
            { title: 'Name', field: 'name' },
            { title: 'Date on-board', field: 'dateOnBoard' },
            { title: 'MSA ROLE', field: 'msaROLE' },
            { title: 'LOB', field: 'lob' },
            { title: 'BU', field: 'bu' },
            { title: 'DU', field: 'du'},           
            { title: 'Loacation', field: 'location' },
            { title: 'BackBone', field: 'backBone' },
            { title: 'Assessed', field: 'assessed' },
            { title: 'Direct Supervisor', field: 'direcSupervisort' },
            { title: 'Client Feedback', field: 'clientFeedback' },
            { title: 'Pre-Assessment(Refer Client Feedback)', field: 'refer' },
            { title: 'Pre-Assessment', field: 'preAssessment' },
            { title: 'Group Assessment Result', field: 'groupAssessment' },
            { title: 'Performance Facts(A/C/D)', field: 'facts' },
            { title: 'Performance Skip', field: 'skip' },
            { title: 'Remarks', field: 'remarks' },
            { title: 'LastQ', field: 'lastQ' },
            { title: '2 Qs ago', field: '2Qsago' },
            { title: '3 Qs ago', field: '3Qsago' },
            { title: 'Perfoemance Goal', 
              field: 'goal',
              formatter: operateFormatter3,
      		  align:'center'
            },
            
        ]
       
    });    
});



$(document).ready(function () {
	var operateFormatter = function (value, row, index) {//赋予的参数
		 return [
		  '<input type="submit" value="Upload" class="RoleOfedit btn btn-primary  btn-sm"     data-toggle="modal"  style="display:inline">'		
		 ].join('');
		 }
	 window.operateEvents = {
	          'click .RoleOfedit': function (e, value, row, index) {
	                /*alert(row.qxxh);*/
	                $("#upload").modal('show');
	            }
	        };
    $('#dataUpload').bootstrapTable({
    	url: path+'/json/dataupload.json',                      //请求后台的URL（*）
        method: 'GET',                      //请求方式（*）
        toolbar: '#toolbar',              //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        fixedColumns: true,
        fixedNumber: 6,
        singleSelect : true,                // 单选checkbox 
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        pageSize: 5,                     //每页的记录行数（*）
        pageList: [10,15,30],        //可供选择的每页的行数（*）
        search: false,                      //是否显示表格搜索
        strictSearch: true,
        showColumns: false,                  //是否显示所有的列（选择显示的列）
        showRefresh: false,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        clickToSelect:true,  //是否启动点击选中行
        //得到查询的参数
      queryParams : function (params) {
        	return {
        		pageSize: params.limit,
        		pageNumber: params.offset/params.limit+1,
            };
        },       
        columns: [
            { title: 'Name', field: 'name'},
            { title: 'Operate', 
              field: 'operate',
              events: operateEvents,
              formatter: operateFormatter, //自定义方法，添加操作按钮
              align:'center'
             
            }           
        ]       
    });    
});

$(function () {
	  $("#sub_search").click(function () {	   
		  $("#uploadsuccess").modal('show');
	  });
	 });


$(document).ready(function () {
	
	var operateFormatter4 = function (value, row, index) {//赋予的参数
		 return [
		  '<button type="button" class="btn btn-default" style="border:none";><span class="glyphicon glyphicon-edit"></span></button>'		
		 ].join('');
	}
	window.operateEvents4 = {
	          'click .btn': function (e, value, row, index) {
	        	  /*alert(row.qxxh);*/
	              window.location.href=path+"/service/performanceManagement/historyDetail";
	            }
	};
    $('#records').bootstrapTable({
    	url: path+'/json/records.json',                      //请求后台的URL（*）
        method: 'GET',                      //请求方式（*）
        striped: true,                      //是否显示行间隔色
        singleSelect : false,                // 单选checkbox 
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        pageSize: 10,                     //每页的记录行数（*）
        pageList: [10,15,30],        //可供选择的每页的行数（*）
        search: false,                      //是否显示表格搜索
        strictSearch: true,
        showColumns: false,                  //是否显示所有的列（选择显示的列）
        showRefresh: false,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        clickToSelect:true,  //是否启动点击选中行
        //得到查询的参数
      queryParams : function (params) {
        	return {
        		pageSize: params.limit,
        		pageNumber: params.offset/params.limit+1,
            };
        },     
        columns: [
        	{checkbox:true},
            { title: 'No.', field: 'no.'},
            { title: 'E-HR', field: 'ehr' },
            { title: 'Employee Name', field: 'emname' },
            { title: 'DU', field: 'du' },
            { title: 'BU', field: 'bu' },
            { title: 'Begin Date', field: 'beginDate' },
            { title: 'End Date', field: 'endDate' },
            { title: 'RM', field: 'rm' },
            { title: 'Assessment Results', field: 'assResult'},           
            { title: 'Remarks', field: 'remarks' },
            { title: 'Detail', 
              field: 'detail',
              events: operateEvents4,
              formatter: operateFormatter4, //自定义方法，添加操作按钮
              align:'center'
            },
            
        ]
       
    });    
});


$(document).ready(function () {
    $('#kbo').bootstrapTable({
    	url: path+'/json/kbo.json',                      //请求后台的URL（*）
        method: 'GET',                      //请求方式（*）
        striped: false,                      //是否显示行间隔色
        singleSelect : true,                // 单选checkbox 
        pagination: true,                   //是否显示分页（*）
        sidePagination: "server", 
        onLoadSuccess: function (data) {
            $('#kbo').bootstrapTable('mergeCells', {index: 0, field: 'types', rowspan: 5});
            $('#kbo').bootstrapTable('mergeCells', {index: 4, field: 'types',colspan: 7});
        },
        columns: [
        	{ title: 'Types', field: 'types'},
            { title: 'NO.', field: 'no.'},
            { title: 'KBO Description', field: 'kboDes' },
            { title: 'Weightage', field: 'weightage' },
            { title: 'Phase Goal', field: 'phaseGoal' },
            { title: 'Key Actions', field: 'keyActions' },
            { title: 'Assessment Dept', field: 'assessmentDept' }
                        
        ]
       
    });    
});



$(function () {
	  $("#searchHistroyBtn").click(function () {	   
		  /*$("#uploadsuccess").modal('show');*/
		  window.location.href = path+"/service/performanceManagement/searchHistroyBtn";
	  });
	 });


$(document).ready(function () {
    $('#individual').bootstrapTable({
    	url: path+'/json/kbo.json',                      //请求后台的URL（*）
        method: 'GET',                      //请求方式（*）
        striped: false,                      //是否显示行间隔色
        singleSelect : true,                // 单选checkbox 
        pagination: true,                   //是否显示分页（*）
        sidePagination: "server", 
        onLoadSuccess: function (data) {
            $('#individual').bootstrapTable('mergeCells', {index: 1, field: 'key', colspan: 4});
        },
        columns: [
        	{ title: '<center>Key Capability bo be Improved<br>(Recognize key items)</center>', field: 'key'},
            { title: '<center>Actions and Measuement<br>(Specific、Personalized、Measurable)</center>', field: 'actions'},
            { title: '<center>Support<br>(Supervisor)</center>', field: 'support' },
            { title: '<center>Plan Deadline<br>(Executable)</center>', field: 'plan' },
                                   
        ]
       
    });    
});








