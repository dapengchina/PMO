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
<meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
<meta charset="utf-8">
<title>CandidateList</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
<meta name="author" content="Muhammad Usman">

<!-- The styles -->
<link href="<%=path %>/css/bootstrap-cerulean.min.css" rel="stylesheet">
<link href="<%=path %>/css/charisma-app.css" rel="stylesheet">
<link href='<%=path %>/bower_components/fullcalendar/dist/fullcalendar.css' rel='stylesheet'>
<link href='<%=path %>/bower_components/fullcalendar/dist/fullcalendar.print.css' rel='stylesheet' media='print'>
<link href='<%=path %>/bower_components/chosen/chosen.min.css'	rel='stylesheet'>
<link href='<%=path %>/bower_components/colorbox/example3/colorbox.css'	rel='stylesheet'>
<link href='<%=path %>/bower_components/responsive-tables/responsive-tables.css' rel='stylesheet'>
<link href='<%=path %>/bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css' rel='stylesheet'>
<link href='<%=path %>/bower_components/bootstrap-val/bootstrapValidator.css' rel='stylesheet'>
<link href='<%=path %>/css/jquery.noty.css' rel='stylesheet'>
<link href='<%=path %>/css/noty_theme_default.css' rel='stylesheet'>
<link href='<%=path %>/css/elfinder.min.css' rel='stylesheet'>
<link href='<%=path %>/css/elfinder.theme.css' rel='stylesheet'>
<link href='<%=path %>/css/jquery.iphone.toggle.css' rel='stylesheet'>
<link href='<%=path %>/css/uploadify.css' rel='stylesheet'>
<link href='<%=path %>/css/animate.min.css' rel='stylesheet'>
<link href='<%=path%>/css/bootstrap-datetimepicker.css' rel='stylesheet'>
<link href='<%=path%>/css/bootstrap-datetimepicker.min.css' rel='stylesheet'>
<!-- jQuery -->
<script src="<%=path %>/bower_components/jquery/jquery.min.js"></script>
<!-- The fav icon -->
<link rel="shortcut icon" href="<%=path %>/img/favicon.ico">
<style>
.nav-pills>li.active>a h5, 
.nav-pills>li.active>a:hover h5, 
.nav-pills>li.active>a:focus h5 {
    color: #ffffff;
    background-color: #2fa4e7;
}
.nav-pills>li>a h5, 
.nav-pills>li>a:hover h5, 
.nav-pills>li>a:focus h5 {
    margin:0px;
    cursor: pointer;
}
a, button, input[type=submit], input[type=button], .product-img{
    -webkit-transition: 0s;
    -moz-transition: 0s;
    -ms-transition: 0s;
    -o-transition: 0s;
    transition: 0s;
}
.box-content{
	padding: 15px;
}
.control-label {
    height: 30px;
}
</style>
</head>
<script>
var path='<%=path%>';
</script>
<body>
	<!-- topbar starts -->
	<c:import url="/service/manage/top" />
	<!-- topbar ends -->
	<div class="ch-container">
		<div class="row">
			<!-- left menu starts -->
			<c:import url="/service/manage/left" />
			<!-- left menu ends -->
			<div id="content" class="col-lg-10 col-sm-10">
				<!-- content starts -->
				<div class="row" >					
					<div class="box col-md-12">
						<div class="box-inner" style="min-height:500px;">
							<div class="box-header well" data-original-title="">
								<h2>
									<i class="glyphicon glyphicon-user"></i> Interview Records
								</h2>
							</div>
							<div id="dataRecord" class="box-content" style="display:none">
								<h5>
									No interview record
								</h5>
							</div>
							<div>
							    <input type="hidden" id="candidateId" value="${candidateId}"/>
							</div>
							<ul class="nav nav-pills">
							  <li id='tab' role="presentation" class=""  style="display:none">
								  <a>
								  	<h5>&nbsp;Round <span id="sp"></span> </h5>
								  </a>
							  </li>
							</ul>
							<div id="interviewInfoList" class="box-content" style="display:none">
						    	<div class="control-label" style="">
									<h5>&nbsp;Department&nbsp;:&nbsp;<span id="csSubDeptName"></span></h5>
									<h5>&nbsp;Project&nbsp;:&nbsp;<span id="projectName"></span></h5>
								</div>
								<div id="interviewInfo" style="margin-left: 2%;">
									<hr>
									<div class="control-label">
										<h5>Interview <span id="th"></span></h5>
									</div>
									<div class="control-label">
										<label>Interviewer:  <span id="candidateName"></span></label>
									</div>
									<div class="control-label">
										<label>Interview Type:  <span id="interviewType"></span></label>
									</div>
									<div class="control-label">
										<label>Interview Date:  <span id="interviewDate"></span></label>
									</div>
									<div class="control-label">
										<label>Interview Result: <span id="interviewStatus"></span></label>
									</div>
									<div class="control-label">
										<label>Feedback: <span id="interviewFeedBack"></span></label>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--/row-->
				<!-- content ends -->
			</div>
			<!--/#content.col-md-0-->
		</div>
		<hr>
		<c:import url="/service/manage/footer" />

	</div>
	<!--/.fluid-container-->

	<!-- external javascript -->

	<script src="<%=path %>/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="<%=path %>/bower_components/bootstrap-val/bootstrapValidator.min.js"></script>

	<!-- library for cookie management -->
	<script src="<%=path %>/js/jquery.cookie.js"></script>
	<!-- calender plugin -->
	<script src='<%=path %>/bower_components/moment/min/moment.min.js'></script>
	<script src='<%=path %>/bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
	<!-- data table plugin -->
	<script src='<%=path %>/js/jquery.dataTables.min.js'></script>
	<!-- select or dropdown enhancer -->
	<script src="<%=path %>/bower_components/chosen/chosen.jquery.min.js"></script>
	<!-- plugin for gallery image view -->
	<script src="<%=path %>/bower_components/colorbox/jquery.colorbox-min.js"></script>
	<!-- notification plugin -->
	<script src="<%=path %>/js/jquery.noty.js"></script>
	<!-- library for making tables responsive -->
	<script src="<%=path %>/bower_components/responsive-tables/responsive-tables.js"></script>
	<!-- tour plugin -->
	<script src="<%=path %>/bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
	<!-- star rating plugin -->
	<script src="<%=path %>/js/jquery.raty.min.js"></script>
	<!-- for iOS style toggle switch -->
	<script src="<%=path %>/js/jquery.iphone.toggle.js"></script>
	<!-- autogrowing textarea plugin -->
	<script src="<%=path %>/js/jquery.autogrow-textarea.js"></script>
	<!-- multiple file upload plugin -->
	<script src="<%=path %>/js/jquery.uploadify-3.1.min.js"></script>
	<!-- history.js for cross-browser state change on ajax -->
	<script src="<%=path %>/js/jquery.history.js"></script>
	<!-- application script for Charisma demo -->
	<script src="<%=path %>/js/charisma.js"></script>

	<script type="text/javascript" src="<%=path %>/js/pmo/interviewRecord.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.zh-CN.js"></script>

</body>
</html>


