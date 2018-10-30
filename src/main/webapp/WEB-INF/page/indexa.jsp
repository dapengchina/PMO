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

<meta charset="utf-8">
<title>PMO</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
<meta name="author" content="Muhammad Usman">

<!-- The styles -->
<link href="<%=path%>/css/bootstrap-cerulean.min.css" rel="stylesheet">

<link href="<%=path%>/css/charisma-app.css" rel="stylesheet">
<link
	href='<%=path%>/bower_components/fullcalendar/dist/fullcalendar.css'
	rel='stylesheet'>
<link
	href='<%=path%>/bower_components/fullcalendar/dist/fullcalendar.print.css'
	rel='stylesheet' media='print'>
<link href='<%=path%>/bower_components/chosen/chosen.min.css'
	rel='stylesheet'>
<link href='<%=path%>/bower_components/colorbox/example3/colorbox.css'
	rel='stylesheet'>
<link
	href='<%=path%>/bower_components/responsive-tables/responsive-tables.css'
	rel='stylesheet'>
<link
	href='<%=path%>/bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css'
	rel='stylesheet'>
<link
	href='<%=path%>/bower_components/bootstrap-val/bootstrapValidator.css'
	rel='stylesheet'>
<link href='<%=path%>/css/jquery.noty.css' rel='stylesheet'>
<link href='<%=path%>/css/noty_theme_default.css' rel='stylesheet'>
<link href='<%=path%>/css/elfinder.min.css' rel='stylesheet'>
<link href='<%=path%>/css/elfinder.theme.css' rel='stylesheet'>
<link href='<%=path%>/css/jquery.iphone.toggle.css' rel='stylesheet'>
<link href='<%=path%>/css/uploadify.css' rel='stylesheet'>
<link href='<%=path%>/css/animate.min.css' rel='stylesheet'>
<link href='<%=path%>/css/bootstrap-datetimepicker.css' rel='stylesheet'>
<link href='<%=path%>/css/bootstrap-datetimepicker.min.css'
	rel='stylesheet'>
<link href='<%=path%>/css/style.css' rel='stylesheet'>

<!-- jQuery -->
<script src="<%=path%>/bower_components/jquery/jquery.min.js"></script>

<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- The fav icon -->
<link rel="shortcut icon" href="<%=path%>/img/favicon.ico">

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
				<div class="row">
					<div class="box col-md-12">
						<div class="box-inner">
							<div class="box-header well" data-original-title="">
								<h2>
									<i class="glyphicon glyphicon-user"></i> Add Employee
								</h2>
							</div>
							 <div id="register" class="box-content" style="overflow: auto;"></div>
							  </br>
							  </br>
								<div class=" row">
									<div class="col-md-4 col-sm-3 col-xs-6">
										<div id="getTm">
											<a data-toggle="tooltip"
												title="Time&Material  & Team Delivery."
												class="well top-block" href="#"><i
												class="glyphicon glyphicon-briefcase"></i>
												<div>
													Time&Material </br> Team Delivery
												</div> </a>
										</div>
									</div>

									<div class="col-md-4 col-sm-3 col-xs-6">
										<div id="getFp">
											<a data-toggle="tooltip" title="Fixed Price."
												class="well top-block" href="#"> <i
												class="glyphicon glyphicon-lock"></i>
												<div>
													Fixed Price </br> </br>
												</div>
											</a>
										</div>
									</div>
									<div class="col-md-4 col-sm-3 col-xs-6">
										<div id="getSupport">
											<a data-toggle="tooltip" title="Support."
												class="well top-block" href="#"><i
												class="glyphicon glyphicon-user"></i>
												<div>
													Support</br> </br>
												</div> </a>
										</div>
									</div>
									</br> </br>  </br> </br> </br> </br> </br> </br> </br> </br> </br>
									</br> </br> </br> </br> </br> </br> </br> </br> </br>
								</div>
							</div>
						</div>
					</div>
				</div>
              </div>
              <hr>
              
				<c:import url="/service/manage/footer" />

			</div>
			<!--/.fluid-container-->

			<!-- external javascript -->

			<script
				src="<%=path%>/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
			<script
				src="<%=path%>/bower_components/bootstrap-val/bootstrapValidator.min.js"></script>

			<!-- library for cookie management -->
			<script src="<%=path%>/js/jquery.cookie.js"></script>
			<!-- calender plugin -->
			<script src='<%=path%>/bower_components/moment/min/moment.min.js'></script>
			<script
				src='<%=path%>/bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
			<!-- data table plugin -->
			<script src='<%=path%>/js/jquery.dataTables.min.js'></script>

			<!-- select or dropdown enhancer -->
			<script src="<%=path%>/bower_components/chosen/chosen.jquery.min.js"></script>
			<!-- plugin for gallery image view -->
			<script
				src="<%=path%>/bower_components/colorbox/jquery.colorbox-min.js"></script>
			<!-- notification plugin -->
			<script src="<%=path%>/js/jquery.noty.js"></script>
			<!-- library for making tables responsive -->
			<script
				src="<%=path%>/bower_components/responsive-tables/responsive-tables.js"></script>
			<!-- tour plugin -->
			<script
				src="<%=path%>/bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
			<!-- star rating plugin -->
			<script src="<%=path%>/js/jquery.raty.min.js"></script>
			<!-- for iOS style toggle switch -->
			<script src="<%=path%>/js/jquery.iphone.toggle.js"></script>
			<!-- autogrowing textarea plugin -->
			<script src="<%=path%>/js/jquery.autogrow-textarea.js"></script>
			<!-- multiple file upload plugin -->
			<script src="<%=path%>/js/jquery.uploadify-3.1.min.js"></script>
			<!-- history.js for cross-browser state change on ajax -->
			<script src="<%=path%>/js/jquery.history.js"></script>
			<!-- application script for Charisma demo -->
			<script src="<%=path%>/js/charisma.js"></script>

			<script type="text/javascript" src="<%=path%>/js/pmo/loadHSBCDept.js"></script>
			<script type="text/javascript"
				src="<%=path%>/js/pmo/employeeValid.js"></script>
			<script type="text/javascript"
				src="<%=path%>/js/bootstrap-datetimepicker.js"></script>
			<script type="text/javascript"
				src="<%=path%>/js/bootstrap-datetimepicker.min.js"></script>
			<script type="text/javascript"
				src="<%=path%>/js/bootstrap-datetimepicker.zh-CN.js"></script>
</body>
</html>


