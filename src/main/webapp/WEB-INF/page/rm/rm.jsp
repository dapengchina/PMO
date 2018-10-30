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
<title>Pmo</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
<meta name="author" content="Muhammad Usman">

<!-- The styles -->
<link href="<%=path %>/css/bootstrap-cerulean.min.css" rel="stylesheet">

<link href="<%=path %>/css/charisma-app.css" rel="stylesheet">
<link
	href='<%=path %>/bower_components/fullcalendar/dist/fullcalendar.css'
	rel='stylesheet'>
<link
	href='<%=path %>/bower_components/fullcalendar/dist/fullcalendar.print.css'
	rel='stylesheet' media='print'>
<link href='<%=path %>/bower_components/chosen/chosen.min.css'
	rel='stylesheet'>
<link href='<%=path %>/bower_components/colorbox/example3/colorbox.css'
	rel='stylesheet'>
<link
	href='<%=path %>/bower_components/responsive-tables/responsive-tables.css'
	rel='stylesheet'>
<link
	href='<%=path %>/bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css'
	rel='stylesheet'>
<link
	href='<%=path %>/bower_components/bootstrap-val/bootstrapValidator.css'
	rel='stylesheet'>
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

<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- The fav icon -->
<link rel="shortcut icon" href="<%=path %>/img/favicon.ico">

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
						<div class="box-inner">
							<div class="box-header well" data-original-title="">
								<h2>
									<i class="glyphicon glyphicon-user"></i> Interviewer
								</h2>
							</div>
							<div id="register" class="box-content" style="overflow: auto;">
							<form id="candidateForm" method="post" enctype="multipart/form-data">
							    
								<div class="form-group">
								<div id="successAlert" class="alert alert-success" style="display: none;"></div>
										
										<div class="group">
											<label class="col-sm-2 control-label">E-HR</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="ehr"
													id="ehr" />
											</div>
										</div>
										<div class="group">
											<label class="col-sm-2 control-label">Staff ID</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="hsbcStaffId"
													id="hsbcStaffId" />
											</div>
										</div>
								</div>
								<br/><br/><br/>
								<div class="form-group">
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
								</div>
								<br/><br/>
								<div class="form-group">
								        
								        <div class="group">
										<label class="col-lg-2 control-label">Location</label>
										<div class="col-lg-4">
											<select class="form-control" id="location" name="location" data-bv-notempty>
												<option value="">-- Option--</option>
												
											</select>
											
										</div>
										</div>
								        <div class="group">
											<label class="col-lg-2 control-label">Skill</label>
											<div class="col-sm-4">
												<select class="form-control" id="skill" name="skill" data-bv-notempty>
													<option value="">-- Option--</option>
													
											    </select>
											</div>
										</div>
								
								</div>
								<br/><br/>
								<div class="form-group">
									<div class="group">
										<label class="col-lg-2 control-label"> MSA Role</label>
										<div class="col-lg-4">
											<select class="form-control" id="role" name="role" data-bv-notempty>
												<option value="">-- Option--</option>
											</select>
										</div>
										</div>
										
										<div class="group">
											<label class="col-lg-2 control-label">Experience Years</label>
											<div class="col-lg-4">
											<select class="form-control" id="experience_years" name="experience_years" data-bv-notempty>
												<option value="">-- Option--</option>
											</select>
										</div>
										</div>
								</div>
								<br/><br/>
								<div class="form-group">
										
										<div class="group">
										<label class="col-lg-2 control-label">Interviewer Status</label>
										<div class="col-lg-4">
											<select class="form-control" id="interviewerStatus" name="interviewerStatus" data-bv-notempty>
												<option value="">-- Option--</option>
												<option value="0">可作为面试官</option>
												<option value="1">已是面试官</option>
											</select>
										</div>
										</div>
										<div class="group">
										<label class="col-lg-2 control-label">DU</label>
										<div class="col-lg-4">
											<select class="form-control" name="du" id="du" data-bv-group=".group">
												<option value="">--Option--</option>
											</select>
										</div>
										</div>
								</div>
								<br/><br/>
								<div class="form-group">
									    <div style="text-align:center;width:90%;float:left">
									    <input type="button" value="Search"
										name="searchBtn" id="searchBtn" href="#"
										class="button btn btn-primary" data-dismiss="modal"
										style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin:auto ;">
									    </div>
									   <!--  <div style="text-align:center;width:50%;float:right">
									    <input type="reset" value="导出Excel" disabled="disabled"
										name="exportExcel" id="exportExcel" href="#"
										class="button btn btn-primary" data-dismiss="modal"
										style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin:auto ;">
									    </div> -->
								</div>
								        <div >
									    <a href="" id="exceltHref" style="display:none;">导出</a>
									    </div>
							    
							       <br/><br/>
									<div style="overflow: auto;">
									<table id="interviewerList"
										class="table table-striped table-bordered">
										<thead>
											<tr>
												<th>E-HR</th>
												<th>LOB</th>
												<th>Staff Id</th>
												<th>Staff Name</th>
												<th>Skill</th>
												<th>Location</th>
												<%-- Felix, 20171212, MSA Role Begin --%>
												<th>MSA Role</th>
												<%-- Felix, 20171212, MSA Role End --%>
												<th>Experience Years</th>
												<th>Interviewer Status</th>
												<th>Operate</th>
											</tr>
										</thead>
										
										<tbody>
									    </tbody>
									</table>
									</div>
									<div>
										<ul class="pagination pagination-centered">
											<%-- <li><a href="#" id="fristPage" onclick="loadDemandList(1)">首页</a></li>
											<li><a href="#" id="previousPage" >&laquo;</a></li>
										<c:forEach begin="1" end="<% %>" step="1" varStatus="status" >
											<li><a href="#">${status.count }</a></li>
										</c:forEach>
											<li><a href="#" id="nextPage" >&raquo;</a></li>
											<li><a href="#" id="lastPage" >末页</a></li> --%>
										</ul>
										<div style="max-width:400px;float:right;margin-top:30px;">
										<b><span id="currentPage"></span> / <span id="pageCount"></span>
										<select class="pagination pagination-centered"	name="pageRecNum" data-bv-notempty
													data-bv-notempty-message="please select  pageRecordsNum"
													id="pageRecordsNum" data-bv-group=".group">
													<option value="10">10</option>
													<option value="20">20</option>
													<option value="50">50</option>
													<option value="100">100</option>
												</select>
										</b>
										</div>
									</div>
							</form>
							<form action="" id="detailForm" method="post" target="_blank">
									<input id="demandId" name="demandId" type="hidden" />
							</form>
							<form action="" id="conditionForm" method="post">
							    	<input type="hidden" id="condition" name="condition">
							</form>
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

	<script
		src="<%=path %>/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
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
	<!-- multiple file upload plugin -->
	<script src="<%=path %>/js/jquery.uploadify-3.1.min.js"></script>
	<!-- history.js for cross-browser state change on ajax -->
	<script src="<%=path %>/js/jquery.history.js"></script>
	<!-- application script for Charisma demo -->
	<script src="<%=path %>/js/charisma.js"></script>

	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="<%=path %>/js/pmo/interviewer.js"></script>
</body>
</html>


