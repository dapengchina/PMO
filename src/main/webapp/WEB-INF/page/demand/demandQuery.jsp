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
									<i class="glyphicon glyphicon-user"></i> Demand Maintenance
								</h2>
							</div>
							<div id="demandInfo" class="box-content">

								<form id="demandForm" method="post" class="form-horizontal">
									<div class="group">
										<label class="col-lg-2 control-label">BU</label>
										<div class="col-lg-4">
											<select class="form-control" name="csBuName" data-bv-notempty
												data-bv-notempty-message="请选择角色" id="csBuName"
												data-bv-group=".group">
												<option value="">--Option--</option>
											</select>
										</div>
									</div>
									<div class="group">
										<label class="col-sm-2 control-label">DU</label>
										<div class="col-sm-4">
											<select class="form-control" name="csSubDept"
												data-bv-notempty data-bv-notempty-message="请选择角色"
												id="csSubDept" data-bv-group=".group">
												<option value="">--Option--</option>
											</select>
										</div>
									</div>
									<br> <br> <br>
									<div class="group">
										<label class="col-lg-2 control-label">Tech/Skill</label>
										<div class="col-lg-4">
											<select class="form-control" name="skill" data-bv-notempty
												data-bv-notempty-message="请选择角色" id="skill"
												data-bv-group=".group">
												<option value="">--Option--</option>
											</select>
										</div>
									</div>
									<div class="group">
										<label class="col-lg-2 control-label">Position</label>
										<div class="col-lg-4">
											<select class="form-control" name="position" data-bv-notempty
												data-bv-notempty-message="请选择角色" id="position"
												data-bv-group=".group">
												<option value="">--Option--</option>
											</select>
										</div>
									</div>
									</br> </br> </br>
									<div id="successAlert" class="alert alert-success"
										style="display: none;"></div>

									<div class="group">
										<label class="col-sm-2 control-label">Status</label>
										<div class="col-sm-4">
											<select class="form-control" name="status" data-bv-notempty
												data-bv-notempty-message="请选择角色" id="status"
												data-bv-group=".group">
												<option value="">--Option--</option>
											</select>
										</div>
									</div>
									<div class="group">
										<label class="col-sm-2 control-label">EngagementType</label>
										<div class="col-sm-4">
											<select class="form-control" name="engagementType"
												data-bv-notempty data-bv-notempty-message="请选择角色"
												id="engagementType" data-bv-group=".group">
												<option value="">--Option--</option>
											</select>
										</div>
									</div>
									</br> </br> </br>
									<div class="group">
										<label class="col-sm-2 control-label">RR #</label>
										<div class="col-sm-4">
											<input type="text" class="form-control" name="rr" id="rr" />
										</div>
									</div>
									<div class="group">
										<label class="col-sm-2 control-label">Job Code</label>
										<div class="col-sm-4">
											<input type="text" class="form-control" name="jobcode"
												id="jobcode" />
										</div>
									</div>
									</br> </br> </br>
									<div class="group">
										<label class="col-sm-2 control-label">DemandNumber</label>
										<div class="col-sm-4">
											<input type="text" class="form-control" name="demandNumber"
												id="demandNumber" />
										</div>
									</div>
									<input type="hidden" name="cssubName" id="cssubName" /> <input
										type="hidden" name="hsbcsubName" id="hsbcsubName" /> </br> </br> </br>


									<div class="form-group">
										<div style="text-align: center; width: 50%; float: left">
											<input type="button" value="Search" name="searchBtn"
												id="searchBtn" class="button btn btn-primary"
												data-dismiss="modal"
												style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin: auto;">
										</div>
										<div style="text-align: center; width: 50%; float: right">
											<input type="button" value="Export" disabled="disabled"
												name="exportExcel" id="exportExcel"
												class="button btn btn-primary" data-dismiss="modal"
												style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin: auto;">
										</div>
									</div>

									<div>
										<a href="" id="exceltHref" style="display: none;">导出</a>
									</div>

									<div style="overflow: auto;">
										<table id="demandList"
											class="table table-striped table-bordered">
											<thead>
												<tr>
													<th>RR</th>
													<th>Job Code</th>
													<th>Skill</th>
													<th>Position</th>
													<th>Location</th>
													<th>Status</th>
													<th>CsDpet</th>
													<th>Operate</th>
												</tr>
											</thead>

										</table>
									</div>
									<div>
										<ul class="pagination pagination-centered">
											<li><a href="#" id="fristPage">First</a></li>
											<li><a href="#" id="previousPage">Last</a></li>
											<li><a href="#" id="nextPage">Next</a></li>
											<li><a href="#" id="lastPage">End</a></li>
										</ul>
										<div style="max-width: 400px; float: right; margin-top: 30px;">
											<b><span id="currentPage"></span> / <span id="pageCount"></span>
												<select class="pagination pagination-centered"
												name="pageRecordsNum" data-bv-notempty
												data-bv-notempty-message="please select  pageRecordsNum"
												id="pageRecordsNum" data-bv-group=".group">
													<option value="10">10</option>
													<option value="20">20</option>
													<option value="50">50</option>
													<option value="100">100</option>
											</select> </b>
										</div>
									</div>

								</form>
								<form action="" id="detailForm" method="post" target="_blank">
									<input id="demandId" name="demandId" type="hidden" /> <input
										id="statusa" name="statusa" type="hidden" /> <input
										id="userType" name="userType" type="hidden" />
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

		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">


			<div class="modal-dialog">
				<div class="modal-content">
					<div class="box-header well" data-original-title="">
						<h2>
							<i class="glyphicon glyphicon-user"></i> Item List
						</h2>

						<div class="box-icon">
							<a href="#" class="btn btn-round btn-default  btn-minimize "><i
								class="glyphicon glyphicon-chevron-up"></i></a> <a
								class="btn btn-round btn-default" href="#" data-dismiss="modal">
								<i class="glyphicon glyphicon-remove"></i>
							</a>
						</div>
					</div>
					<div id="excelCheckBox" class="box-content">
						<table id="excelBox" class="table table-striped table-bordered">
							<thead>
								<tr>
									<label><input type="checkbox" name="RR #" />RR
										#&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Job Code" />Job
										Code&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Tech/Skill" />Tech/Skill&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Requestor" />Requestor&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Position" />Position&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Department" />Department&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Sub - Department" />Sub
										- Department&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Location" />Location&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Req published Date" />Req
										published Date&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Ageing" />Ageing&nbsp;&nbsp;</label>
									<label><input type="checkbox"
										name="No. of Profiles Sent to HSBC" />No. of Profiles Sent to
										HSBC&nbsp;&nbsp;</label>
									<label><input type="checkbox"
										name="No of Profiles Interviewed" />No of Profiles
										Interviewed&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Status" />Status&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Candidate Name" />Candidate
										Name&nbsp;&nbsp;</label>
									<label><input type="checkbox"
										name="Proposed Date of Joining" />Proposed Date of
										Joining&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="SOW signed" />SOW
										signed&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="BGV Cleared" />BGV
										Cleared&nbsp;&nbsp;</label>
									<label><input type="checkbox"
										name="Reason for Abort / Delay" />Reason for Abort /
										Delay&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Remark" />Remark&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="CsDept" />CsDept&nbsp;&nbsp;</label>
									<label><input type="checkbox"
										name="Planned Onboard date" />Planned Onboard
										date&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="DO number" />DO
										number&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="HR Priority" />HR
										Priority&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Completion Day" />Completion
										Day&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Onboard Date" />Onboard
										Date&nbsp;&nbsp;</label>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
						<div class="center">
							<a class="btn btn-success" href="#" onClick="selectAll()">
								Select All </a> <a class="btn btn-success" href="#"
								onClick="exportCondition()"> <i
								class="glyphicon glyphicon-ok icon-white"></i> Confirm
							</a> <a class="btn btn-info" href="#" data-dismiss="modal"> <i
								class="glyphicon glyphicon-remove icon-white"></i> Cancel
							</a>
						</div>
					</div>
				</div>
			</div>
			<!--/span-->
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

	<script type="text/javascript" src="<%=path%>/js/pmo/demandInfo.js"></script>
	<script type="text/javascript"
		src="<%=path%>/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript"
		src="<%=path%>/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>/js/bootstrap-datetimepicker.zh-CN.js"></script>

</body>
</html>


