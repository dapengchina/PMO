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
									<i class="glyphicon glyphicon-user"></i> Employee Information
								</h2>
							</div>
							<div id="register" class="box-content" style="overflow: auto;">
							<form id="registerEmployeeForm" method="post">
							    <input name="bt" id="bt" type="hidden" value="${typepage}"></input>
							    <div class="form-group">
									    <input type="hidden" name="employeeId" id="employeeId" value="${employeeId}"/>
								</div>
								<div id="successAlert" class="alert alert-success" style="display: none;"></div>	
							    <br/>
								<div class="form-group">
										<div class="group">
											<label class="col-sm-2 control-label">E-HR</label>
											<div class="col-sm-4">
												<input type="text" readonly class="form-control" name="eHr"
													id="eHr" />
											</div>
										</div>
										<div class="group">
											<label class="col-sm-2 control-label">LOB</label>
											<div class="col-sm-4">
												<input type="text" readonly class="form-control" name="lob"
													id="lob" />
											</div>
										</div>
										
								</div>
								<br/><br/>
							    
								<div class="form-group">
								
										<div class="group">
											<label class="col-sm-2 control-label">HSBC Staff ID</label>
											<div class="col-sm-4">
												<input type="text" readonly class="form-control" name="hsbcStaffId"
													id="hsbcStaffId" />
											</div>
										</div>
										<div class="group">
											<label class="col-sm-2 control-label">Staff Name</label>
											<div class="col-sm-4">
												<input type="text" readonly class="form-control" name="staffName"
													id="staffName" />
											</div>
										</div>
								</div>
								<br/><br/>
								<div class="form-group">
										<div class="group">
											<label class="col-sm-2 control-label">LN</label>
											<div class="col-sm-4">
												<input type="text" readonly class="form-control" name="LN"
													id="LN" />
											</div>
										</div>
										<div class="group">
											<label class="col-sm-2 control-label">Email</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="email" readonly
													id="email" data-bv-group=".group"/>
											</div>
										</div>
								</div>
								<br/><br/>
								<div class="form-group">
										<div class="group">
										<label class="col-lg-2 control-label">Staff Location</label>
										<div class="col-lg-4">
											<input type="text" readonly class="form-control" name="staffLocation"
													id="staffLocation" />
										</div>
										</div>
										<div class="group">
										<label class="col-lg-2 control-label">Location Type</label>
										<div class="col-lg-4">
											<input type="text" readonly class="form-control" name="locationType"
													id="locationType" />
										</div>
										</div>
								</div>
								<br/><br/>
								<div class="form-group">
										<div class="group">
										<label class="col-lg-2 control-label">CS Dept</label>
											<div class="col-lg-4">
												<input type="text" readonly class="form-control" name="csSubDept"
														id="csSubDept" />
											</div>
										</div>
										<div class="group">
											<label class="col-lg-2 control-label">GB/GF</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="gbGf" readonly
													 id="gbGf" data-bv-group=".group"/>
											</div>
										</div>
								</div>
								
								<br/><br/>
								<div class="form-group">
								        <div class="group">
										<label class="col-lg-2 control-label">HSBC Dept</label>
										<div class="col-lg-4">
											<input type="text" readonly class="form-control" name="hsbcDept"
													id="hsbcDept" />
										</div>
										</div>
										<div class="group">
										<label class="col-lg-2 control-label">HSBC Sub Dept</label>
										<div class="col-lg-4">
											<input type="text" readonly class="form-control" name="hsbcSubDept"
													id="hsbcSubDept" />
										</div>
										</div>
								</div>
								<br/><br/>
								<div class="form-group">
									<div class="group">
										<label class="col-lg-2 control-label">HSBC Project Name</label>
										<div class="col-lg-4">
											<input type="text" readonly class="form-control" name="hsbcProjectName"
												id="hsbcProjectName" />
										</div>
									</div>
									<div class="group">
										<label class="col-lg-2 control-label">HSBC Project Manager</label>
										<div class="col-lg-4">
											<input type="text" readonly class="form-control" name="hsbcProjectManager"
												id="hsbcProjectManager" />
										</div>
									</div>
								</div>
								<br/><br/>
								<div class="form-group">
										<div class="group">
											<label class="col-lg-2 control-label">SOW#</label>
											<div class="col-lg-4">
												<input type="text" readonly class="form-control" name="sow"
													id="sow" />
											</div>
										</div>
										<div class="group">
											<label class="col-lg-2 control-label">SOW# Expired Date</label>
											<div class="col-lg-4">
												<input type="text" readonly class="form-control" name="sowExpiredDate1"
													id="sowExpiredDate1" />
											</div>
										</div>
								</div>
								<br/><br/>
								<div class="form-group">
										<div class="group">
										<label class="col-lg-2 control-label">Staff Category</label>
										<div class="col-lg-4">
											<input type="text" readonly class="form-control" name="staffCategory"
													id="staffCategory" />
										</div>
										</div>
										<div class="group">
										<label class="col-lg-2 control-label">Engagement Type</label>
										<div class="col-lg-4">
											<input type="text" readonly class="form-control" name="engagementType"
													id="engagementType" />
										</div>
										</div>
								</div>
								<br/><br/>
								<div class="form-group">
									
									<div class="group">
										<label class="col-lg-2 control-label">Graduation Date</label>
										<div class="col-lg-4">
											<input type="text" readonly class="form-control" name="graduationDate1"
											id="graduationDate1" />
										</div>
									</div>
									<div class="group">
											<label class="col-sm-2 control-label">EntryDate</label>
											<div class="col-sm-4">
													<input class="form-control" type="text" readonly data-bv-group=".group"
														id="entryDate1" name="entryDate1"/> 
											</div>
									</div>
								</div>
								<br/><br/>
								<div class="form-group">
										<div class="group">
											<label class="col-lg-2 control-label">Onshore or Offshore</label>
											<div class="col-lg-4">
												<input type="text" readonly class="form-control" name="onshoreOrOffshore"
														id="onshoreOrOffshore" />
											</div>
										</div>
										<div class="group">
										<label class="col-lg-2 control-label">Staff Region</label>
										<div class="col-lg-4">
											<input type="text" readonly class="form-control" name="staffRegion"
												id="staffRegion" />
											</div>
										</div>
								</div>
								<br/><br/>
								<div class="form-group">
										<div class="group">
										<label class="col-lg-2 control-label">MSA Role</label>
										<div class="col-lg-4">
											<input type="text" readonly class="form-control" name="role"
													id="role" />
										</div>
										</div>
										<div class="group">
										<label class="col-lg-2 control-label">Skills/Technology</label>
										<div class="col-lg-4">
											 <input type="text" readonly class="form-control" name="skill"
													id="skill" />
										</div>
										</div>
								</div>
								<br/><br/>
								<div class="form-group">
										<div class="group">
										<label class="col-lg-2 control-label">Billing Currency</label>
										<div class="col-lg-4">
											 <input type="text" readonly class="form-control" name="billingCurrency"
													id="billingCurrency" />
										</div>
										</div>
										<div class="group">
											<label class="col-sm-2 control-label">Bill Rate</label>
											<div class="col-sm-4">
												<input type="text" readonly class="form-control" name="billRate"
													id="billRate" />
											</div>
										</div>
								</div>
								<br/><br/>
								<div class="form-group">
										<div class="group">
											<label class="col-lg-2 control-label">HSBC DOJ</label>
											<div class="col-lg-4">
											<input type="text" readonly class="form-control" name="hsbcDOJ1"
												id="hsbcDOJ1" />
											</div>
										</div>
										<div class="group">
										<label class="col-lg-2 control-label">Resource Status</label>
										<div class="col-lg-4">
											 <input type="text" readonly class="form-control" name="resourceStatus"
													id="resourceStatus" />
										</div>
										</div>
								</div>
								<br/><br/>
								<div class="form-group">
									<div class="group">
										<label class="col-sm-2 control-label">IT Work Year</label>
										<div class="col-md-4">
											<input type="text" readonly class="form-control" name="itworkyear"
													id="itworkyear" data-bv-group=".group"/>
										</div>
									</div>
									<div class="group">
										<label class="col-sm-2 control-label">Chsofti Pro Number</label>
										<div class="col-md-4">
											<input type="text" readonly class="form-control" name="chsoftiProNumber"
													id="chsoftiProNumber" data-bv-group=".group"/>
										</div>	
									</div>
								</div> 
								<br/><br/>
								<div class="form-group">
									<div class="group">
										<label class="col-sm-2 control-label">Chsofti Pro StartDate</label>
										<div class="col-md-4">
											<input type="text" readonly class="form-control" name="chsoftiProStartDate"
													id="chsoftiProStartDate" data-bv-group=".group"/>
										</div>
									</div>
									<div class="group">
										<label class="col-sm-2 control-label">Chsofti Pro Name</label>
										<div class="col-md-4">
											<input type="text" readonly class="form-control" name="chsoftiProName"
													id="chsoftiProName" data-bv-group=".group"/>
										</div>	
									</div>
								</div> 
								<br/><br/>
								<div class="form-group">
									<div class="group">
										<label class="col-sm-2 control-label">HSBC POD</label>
										<div class="col-lg-4">
											 <input type="text" readonly class="form-control" name="hsbcPOD"
													id="hsbcPOD" />
										</div>
									</div>
									<div class="group">
										<label class="col-sm-2 control-label">Team Leader</label>
										<div class="col-sm-4">
											<input type="text" readonly class="form-control" name="podtl"
												id="podtl" />
										</div>
									</div>
								</div>
								<br/><br/>
								<div class="form-group">
										<div class="group">
											<label class="col-sm-2 control-label">Team Leader Type</label>
											<div class="col-lg-4">
												 <input type="text" readonly class="form-control" name="tlType"
														id="tlType" />
										</div>
										<div class="group" style="display:none" id="terminatedDateDiv">
											<label class="col-sm-2 control-label">LWD</label>
											<div class="col-lg-4">
												 <input type="text" readonly class="form-control" name="terminatedDate1"
														id="terminatedDate1" />
											</div>
									    </div>
									</div>
								</div>
								<br/><br/>
								<div class="form-group">
										<div class="group" style="display:none" id="terminationReasonDiv">
											<label class="col-sm-2 control-label">Reason for Termination</label>
											<div class="col-sm-4">
												<input type="text" readonly class="form-control" name="terminationReason"
													id="terminationReason" />
											</div>
										</div>
										
										
								</div>
								
								<div class="form-group">
										<div style="text-align: center; width: 50%; float: left">
											<input type="button" value="Back" name="subscribe"
												id="sub_search" href="#" class="button btn btn-primary"
												data-dismiss="modal" onclick="back(1)"
												style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin: auto;">
										</div>
								</div>
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

	<script type="text/javascript" src="<%=path %>/js/pmo/employeeDetailInfo.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.zh-CN.js"></script>

</body>
</html>


