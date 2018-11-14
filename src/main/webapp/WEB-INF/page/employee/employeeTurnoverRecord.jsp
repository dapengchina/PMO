<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>PMO</title>
<link rel="shortcut icon" href="<%=path %>/img/favicon.ico" />
<!-- The styles -->
<link href="<%=path %>/css/bootstrap-cerulean.min.css" rel="stylesheet" />
<link href="<%=path %>/css/charisma-app.css" rel="stylesheet" />
<link
	href='<%=path %>/bower_components/fullcalendar/dist/fullcalendar.css'
	rel='stylesheet' />
<link
	href='<%=path %>/bower_components/fullcalendar/dist/fullcalendar.print.css'
	rel='stylesheet' media='print' />
<link href='<%=path %>/bower_components/chosen/chosen.min.css'
	rel='stylesheet' />
<link href='<%=path %>/bower_components/colorbox/example3/colorbox.css'
	rel='stylesheet' />
<link
	href='<%=path %>/bower_components/responsive-tables/responsive-tables.css'
	rel='stylesheet' />
<link
	href='<%=path %>/bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css'
	rel='stylesheet' />
<link
	href='<%=path %>/bower_components/bootstrap-val/bootstrapValidator.css'
	rel='stylesheet' />
<link href='<%=path %>/css/jquery.noty.css' rel='stylesheet' />
<link href='<%=path %>/css/noty_theme_default.css' rel='stylesheet' />
<link href='<%=path %>/css/elfinder.min.css' rel='stylesheet' />
<link href='<%=path %>/css/elfinder.theme.css' rel='stylesheet' />
<link href='<%=path %>/css/jquery.iphone.toggle.css' rel='stylesheet' />
<link href='<%=path %>/css/uploadify.css' rel='stylesheet' />
<link href='<%=path %>/css/animate.min.css' rel='stylesheet' />
<link href='<%=path%>/css/bootstrap-datetimepicker.css' rel='stylesheet' />
<link href='<%=path%>/css/bootstrap-datetimepicker.min.css' rel='stylesheet' />
<link href='<%=path%>/css/bootstrap-select.css' rel='stylesheet'>
<link href='<%=path%>/css/bootstrap-select.min.css' rel='stylesheet'>
<link href='<%=path%>/css/bootstarp-fileinput/fileinput.min.css' rel='stylesheet'>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" media="all" rel="stylesheet" type="text/css"/>
<link href="<%=path %>/js/bootstarp-fileinput/themes/explorer-fa/theme.css" media="all" rel="stylesheet" type="text/css"/> 
</head>
<script>
var path='<%=path%>';
</script>
<body>
	<c:import url="/service/manage/top" />
	<div class="ch-container">
		<div class="row">
			<c:import url="/service/manage/left" />
			<div id="content" class="col-lg-10 col-sm-10">
				<!-- content starts -->
				<div class="row">
					<div class="box col-md-12">
						<div class="box-inner">
							<div class="box-header well" data-original-title="">
								<h2>
									<i class="glyphicon glyphicon-user"></i> Turnover Record
								</h2>
							</div>
							<div id="turnoverConfirm" class="box-content">
								<div class="panel panel-default">
									<div class="panel-body">
										<div class="group">
											<label class="col-sm-2 control-label">E-HR</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="eHr"
													id="eHr" />
											</div>
										</div>
										<div class="group">
											<label class="col-sm-2 control-label">Staff ID</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="staffId"
													id="staffId" />
											</div>
										</div>
										</br></br></br>
										<div class="group">
											<label class="col-sm-2 control-label">Staff Name</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="staffName"
													id="staffName" />
											</div>
										</div>
										<div class="group">
											<label class="col-sm-2 control-label">LOB</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="lob"
													id="lob" />
											</div>
										</div>
										</br></br></br>
										<div class="group">
										<label class="col-lg-2 control-label">old DU</label>
										<div class="col-lg-4">
											<select class="form-control" name="oldSubDept" data-bv-notempty
												data-bv-notempty-message="请选择角色" id="oldSubDept" data-bv-group=".group">
												<option value="">-- Option--</option>
											</select>
										</div>
										</div>
										<div class="group">
										<label class="col-lg-2 control-label">new DU</label>
										<div class="col-lg-4">
											<select class="form-control" name="newSubDept" data-bv-notempty
												data-bv-notempty-message="请选择角色" id="newSubDept" data-bv-group=".group">
												<option value="">-- Option--</option>
											</select>
										</div>
										</div>
										</br></br></br>
										<div class="group">
										<label class="col-lg-2 control-label">State</label>
										<div class="col-lg-4">
											<select class="form-control" name="state" data-bv-notempty
												data-bv-notempty-message="请选择角色" id="state" data-bv-group=".group">
												<option value="">-- Option--</option>
												<option value="0">Wait Approve</option>
												<option value="1">Approved</option>
												<option value="2">Refused</option>
											</select>
										</div>
										</div>
										</br></br></br>
										<div class="group">
											<div class="col-sm-4">
												<button onClick="search()" type="button"
													style="margin-left: 50px" id="btn_query"
													class="btn btn-primary">Search</button>
											</div>
										</div>
										
								</div>
							<!-- 数据列表  -->
								<table id="turnoverList"></table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<c:import url="/service/manage/footer" />

	<!-- CSS引用 -->
	<link rel="stylesheet"
		href="<%=path %>/extensioncss/bootstarp-table/bootstrap-table.css"
		type="text/css" />
	<link rel="stylesheet"
		href="<%=path %>/extensioncss/bootstarp-table/bootstrap-table.min.css"
		type="text/css" />
	<link rel="stylesheet"
		href="<%=path %>/extensionjs/bootstrap3-editable/css/bootstrap-editable.css"
		type="text/css" />
	<link rel="stylesheet"
		href="<%=path %>/extensioncss/bootstarp-table/bootstrap-table-fixed-columns.css"
		type="text/css" />

	<!-- JS引用 -->
	<script
		src="<%=path %>/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script
		src="<%=path %>/extensionjs/bootstrap-table/dist/bootstrap-table.js"
		type="text/javascript"></script>
	<script
		src="<%=path %>/extensionjs/bootstrap-table/dist/bootstrap-table-locale-all.js"
		type="text/javascript"></script>
	<script
		src="<%=path %>/extensionjs/bootstrap-table/dist/bootstrap-table-fixed-columns.js"
		type="text/javascript"></script>
	<script
		src="<%=path %>/extensionjs/bootstrap-table/dist/locale/bootstrap-table-zh-CN.js"></script>
	<script
		src="<%=path %>/extensionjs/bootstrap3-editable/js/bootstrap-editable.js"
		type="text/javascript"></script>
	<script
		src="<%=path %>/extensionjs/bootstrap-table/dist/extensions/editable/bootstrap-table-editable.js"
		type="text/javascript"></script>


	<script
		src="<%=path %>/bower_components/bootstrap-val/bootstrapValidator.min.js"></script>
	<!-- library for cookie management -->
	<script src="<%=path %>/js/jquery.cookie.js"></script>
	<!-- calender plugin -->
	<script src='<%=path %>/bower_components/moment/min/moment.min.js'></script>
	<script
		src='<%=path %>/bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
	<!-- data table plugin -->
	<script src='<%=path %>/js/jquery.dataTables.min.js'></script>
	<!-- select or dropdown enhancer -->
	<script src="<%=path %>/bower_components/chosen/chosen.jquery.min.js"></script>
	<!-- plugin for gallery image view -->
	<script
		src="<%=path %>/bower_components/colorbox/jquery.colorbox-min.js"></script>
	<!-- notification plugin -->
	<script src="<%=path %>/js/jquery.noty.js"></script>
	<!-- library for making tables responsive -->
	<script
		src="<%=path %>/bower_components/responsive-tables/responsive-tables.js"></script>
	<!-- tour plugin -->
	<script
		src="<%=path %>/bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
	<!-- star rating plugin -->
	<script src="<%=path %>/js/jquery.raty.min.js"></script>
	<!-- for iOS style toggle switch -->
	<script src="<%=path %>/js/jquery.iphone.toggle.js"></script>
	<!-- autogrowing textarea plugin -->
	<script src="<%=path %>/js/jquery.autogrow-textarea.js"></script>
	<!-- history.js for cross-browser state change on ajax -->
	<script src="<%=path %>/js/jquery.history.js"></script>
	
	<!-- multiple file upload plugin -->
	<script src="<%=path %>/js/jquery.uploadify-3.1.min.js"></script>
	<!-- application script for Charisma demo -->
	<script src="<%=path %>/js/charisma.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" type="text/javascript"></script>
	
	<script src="<%=path %>/js/bootstarp-fileinput/fileinput.min.js"></script>
	<script src="<%=path %>/js/bootstarp-fileinput/themes/explorer-fa/theme.js" type="text/javascript"></script>
    <script src="<%=path %>/js/bootstarp-fileinput/themes/fa/theme.js" type="text/javascript"></script>
    
	
	<script type="text/javascript" src="<%=path %>/js/bootstrap-select.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-select.min.js"></script>

	<script type="text/javascript" src="<%=path %>/js/pmo/employeeTurnoverRecord.js"></script>

</body>
</html>


