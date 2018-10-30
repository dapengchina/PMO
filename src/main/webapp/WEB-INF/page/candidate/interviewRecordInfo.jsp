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
<title>CandidateList</title>
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
<!-- jQuery -->
<script src="<%=path%>/bower_components/jquery/jquery.min.js"></script>
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
									<i class="glyphicon glyphicon-user"></i> Interview Record
								</h2>
							</div>
							
							<div id="CandidateInfo" class="box-content" >
								<form id="candidateForm" method="post" class="form-horizontal">
							<div class="group">
								<label class="col-sm-2 control-label">Candidate Name</label>
									<div class="col-sm-4">
										<input type="text" class="form-control" name="candidateNames" id="candidateNames" />
									</div>
							</div>
							<div class="group">
								<label class="col-sm-2 control-label">Tel</label>
									<div class="col-sm-4">
										<input type="text" class="form-control" name="tel" id="tel" />
									</div>
							</div>
							</br></br></br>
							
							<div class="form-group">
								<div style="text-align:center;width:100%;">
								<input type="button" value="Search"
								name="searchBtn" id="searchBtn" href="#"
								class="button btn btn-primary" data-dismiss="modal"
								style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin:auto ;">
								 </div>
							</div>
							
									<div style='overflow:auto'>
										<table id="candidateList"
											class="table table-striped table-bordered">
											<thead>
												<tr>
													<th>Name</th>
													<th>Sex</th>
													<th>Age</th>
													<th>Tel</th>
													<th>Email</th>
													<th>Role</th>
													<th>Work Years</th>
													<th>English Level</th>
													<th>Skill</th>
													<th>Operation</th>
												</tr>
											</thead>
											<tbody></tbody>
										</table>
										
										<div>
										<div style="max-width: 400px; float: left">
											<ul class="pagination pagination-centered">
												<li><a href="#" id="fristPage" onclick="loadCandidateList('frist')">First</a></li>
												<li><a href="#" id="previousPage" onclick="loadCandidateList('previous')">Last</a></li>
												<li><a href="#" id="nextPage" onclick="loadCandidateList('next')">Next</a></li>
												<li><a href="#" id="lastPage" onclick="loadCandidateList('last')">End</a></li>
											</ul>
										</div>
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
									</div>
									
								</form>
						 		<form action="" id="editForm" method="post" target="_blank">
									<input id="interviewId" name="interviewId" type="hidden" />
									<input id="candidateId" name="candidateId" type="hidden" />
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
		<div class="modal fade" id="feedBackDialog" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="box-header well" data-original-title="">
						<h2>
							<i class="glyphicon glyphicon-user"></i> Interview Record
						</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-round btn-default  btn-minimize ">
								<i class="glyphicon glyphicon-chevron-up"></i>
							</a> 
							<a class="btn btn-round btn-default" href="#" data-dismiss="modal">
								<i class="glyphicon glyphicon-remove"></i>
							</a>
						</div>
					</div>
					<div class="box-content">
						<form id="interviewForm">
							<!-- <input type="hidden" name="interviewId" id="interviewId" /> -->
							<div class="group">
								<label class="col-sm-2 control-label">Candidate Name:</label>
								<div class="col-sm-4">
									<input type="text" readonly="true" class="form-control" name="candidateName" id="candidateName" />
								</div>
							</div>
							
							<div class="group">
								<label class="col-sm-2 control-label">Interview Name:</label>
								<div class="col-sm-4">
									<input type="text" readonly="true" class="form-control" name="interviewName" id="interviewName" />
								</div>
							</div>
							
							<br/><br/><br/>
							<div class="group">
								<label class="col-sm-2 control-label">Interview Status:</label>
								<div class="col-lg-4">
									<select id="interviewStatus" name="interviewStatus" class="form-control" data-bv-notempty>
										<option value="">-- Option--</option>
										<option value="0">通过</option>
										<option value="1">未通过</option>
									</select>
								</div>
							</div>
							
							<div class="group">
								<label class="col-sm-2 control-label">csSubDept:</label>
								<div class="col-sm-4">
									<input type="text" readonly="true" class="form-control" name="csSubDept" id="csSubDept" />
								</div>
							</div>
							
							<br/><br/><br/>
							<div class="group">
								<label class="col-sm-2 control-label">Feedback:</label>
								<div class="col-sm-10">
									<textarea id="interviewFeedBack" name="interviewFeedBack" class="form-control" style="margin-bottom: 15px;" rows="5" data-bv-notempty></textarea>
								</div>
							</div>
							<div class="center">
								<button type=submit class="btn btn-success"> 
									<i class="glyphicon glyphicon-ok icon-white"></i> Confirm
								</button> 
								<button class="btn btn-info" data-dismiss="modal"> 
									<i class="glyphicon glyphicon-remove icon-white"></i> Cancel
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

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

	<script type="text/javascript"
		src="<%=path%>/js/pmo/interviewRecordInfo.js"></script>
	<script type="text/javascript"
		src="<%=path%>/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript"
		src="<%=path%>/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript"
		src="<%=path %>/js/bootstrap-datetimepicker.zh-CN.js"></script>

</body>
</html>


