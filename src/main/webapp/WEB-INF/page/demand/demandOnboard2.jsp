<%@ page language="java"
	import="java.util.*,com.pmo.dashboard.entity.*,net.sf.json.JSONObject"
	pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	Employee emp = (Employee) request.getAttribute("employee");
	String empInfo = JSONObject.fromObject(emp).toString();
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

<link rel="shortcut icon" href="<%=path%>/img/favicon.ico">

</head>
<script>
var path='<%=path%>';
	var empObj =<%=empInfo%>;
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
				<div class="box col-md-12">
					<div class="row">
						<div class="box-inner">
							<div class="box-header well">
								<h2>
									<i class="glyphicon glyphicon-user"></i>EmployeeOnborard
								</h2>

								<div class="box-icon">

									<a href="#" style="text-align: center; width: 100%;"> <input
										type="button" value="Onborard" class="button btn btn-primary"
										data-dismiss="modal" onclick="updateDemandOnboard()"
										style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 29px; line-height: 30px; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin: auto;">
									</a>
								</div>
							</div>
							<div class="box-content">
								<ul class="nav nav-tabs" id="myTab">
									<li class="active"><a href="#demand">demandInfo</a></li>
									<li><a href="#employee">EmployeeInfo</a></li>
								</ul>
								<div id="successAlert" class="alert alert-success" style="display: none;"></div>
								<div id="myTabContent" class="tab-content">
									<div class="tab-pane active" id="demand">
										<div id="register" class="box-content" style="overflow: auto;">
											<form id="recruitdemandFormEdit" method="post">
												<div class="form-group hidden">
													<input type="hidden" name="demandIdEdit" id="demandIdEdit"
														value="${demand.demandId}" />
													<input type="hidden" name="candidateId" id="candidateId" value="${demand.candidateId}"/>
												</div>


												<div class="form-group">
													<div class="group">
														<label class="col-sm-2 control-label">Engagement
															Type</label>
														<div class="col-sm-4">
															
															<select class="form-control" name="engagementType"
																data-bv-notempty 
																data-bv-notempty-message="Please Select Your Engagement Type"
																id="engagementType" data-bv-group=".group">
																<option value="${demand.engagementType}">${demand.engagementType}</option>
															</select>
														</div>
													</div>
													<div class="group">
														<label class="col-sm-2 control-label">Status</label>
														<div class="col-lg-4">
															<input type="text" id="status" readonly name="status" class="form-control"  value="Onboard"/>
														</div>
													</div>
												</div>
												<br /> <br /> <br />

												<div class="form-group">
													<div class="group">
														<label class="col-lg-2 control-label">Skill</label>
														<div class="col-lg-4">
															<input type="text" style="display: none"
																value="${demand.skill}" id="skillInput" /> <select
																class="form-control" name="skillEdit" data-bv-notempty
																data-bv-notempty-message="Pleaser Select Your Skill "
																id="skillEdit" data-bv-group=".group">
																<option value="">--Option--</option>
															</select>
														</div>
													</div>
													<div class="group">
														<label class="col-lg-2 control-label">Position</label>
														<div class="col-lg-4">
															<input type="text" style="display: none"
																value="${demand.position}" id="positionInput" /> <select
																class="form-control" name="positionEdit"
																data-bv-notempty
																data-bv-notempty-message="Please Select Your Position"
																id="positionEdit" data-bv-group=".group">
																<option value="">--Option--</option>
															</select>
														</div>
													</div>
												</div>
												<br /> <br /> <br />
												<div class="form-group">
													<div class="group">
														<label class="col-lg-2 control-label">Location</label>
														<div class="col-lg-4">
															<input type="text" style="display: none"
																value="${demand.location}" id="locationInput" /> <select
																class="form-control" name="locationEdit"
																data-bv-notempty
																data-bv-notempty-message="Please Select Your Location"
																id="locationEdit" data-bv-group=".group">
																<option value="">--Option--</option>
															</select>
														</div>
													</div>
													<div class="group">
														<label class="col-sm-2 control-label">Planned
															Onboard Date </label>
														<div class="col-md-4">
															<div class="input-group date form_datetime col-sm-12"
																data-link-field="dt_set_order_time_input">
																<input class="form-control" type="text" readonly
																	data-bv-group=".group" id="plannedOnboardDate1"
																	name="plannedOnboardDate1"
																	value="${demand.plannedOnboardDate}"> <span
																	class="input-group-addon"><span
																	class="glyphicon glyphicon-th"></span></span> <input
																	type="hidden" id="plannedOnboardDate2"
																	name="plannedOnboardDate2" />
															</div>
														</div>
													</div>
												</div>
												<br /> <br /> <br />
												<div class="form-group">
													<div class="group">
														<label class="col-lg-2 control-label">HR Priority</label>
														<div class="col-lg-4">
															<input type="text" style="display: none"
																value="${demand.hrPriority}" id="hrPriorityInput" /> <select
																class="form-control" name="hrPriorityEdit"
																data-bv-notempty
																data-bv-notempty-message="Please Select Your HrPriority"
																id="hrPriorityEdit" data-bv-group=".group">
																<option value="">--Option--</option>
															</select>
														</div>
													</div>
													<div class="group">
														<label class="col-sm-2 control-label">Remark</label>
														<div class="col-md-4">
															<input type="text" class="form-control" name="remarkEdit"
																value="${demand.remark }" id="remarkEdit" />
														</div>
													</div>
												</div>
												<br /> <br /> <br />
												
												<div class="form-group">
													<div class="group">
														<label class="col-sm-2 control-label">Req published Date</label>
														<div class="col-md-4">
															<div class="input-group date form_datetime col-sm-12"
																data-link-field="dt_set_order_time_input">
																<input class="form-control" type="text" readonly
																	data-bv-group=".group" id="reqPublishedDate1Edit"
																	name="reqPublishedDate1Edit"
																	value="${demand.reqPublishedDate }"> <span
																	class="input-group-addon"> <span
																	class="glyphicon glyphicon-calendar"> </span>
																</span> <input type="hidden" id="reqPublishedDate2Edit"
																	name="reqPublishedDate2Edit" />
															</div>
														</div>
													</div>
													<div class="group">
														<label class="col-sm-2 control-label">Ageing</label>
														<div class="col-sm-4">
															<input type="text" class="form-control" name="ageingEdit"
																value="${demand.ageing }" id="ageingEdit" />
														</div>
													</div>

												</div>
												<br /> <br /> <br />
												
												<div class="form-group">
													<div class="group">
														<label class="col-lg-2 control-label">HSBC
															Requestor</label>
														<div class="col-lg-4">
															<input type="text" class="form-control"
																name="requestorEdit" value="${demand.requestor }"
																id="requestorEdit" />
														</div>
													</div>
													<div class="group">
														<label class="col-sm-2 control-label">CS
															Department</label>
														<div class="col-sm-4">
															<select class="form-control" name="csSubDeptEdit"
																data-bv-notempty
																data-bv-notempty-message="Please Select Your CsSubDept"
																id="csSubDeptEdit" data-bv-group=".group">
																<option value="${demand.csSubDept }"></option>
															</select>
														</div>
													</div>

												</div>
												<br /> <br /> <br />
												<div class="form-group" id="atype" style='display: none'>
													<div class="group" id="staffnameEdit" style='display: none'>
														<label class="col-sm-2 control-label">Staff Name</label>
														<div class="col-sm-4">
															<input type="text" class="form-control"
																name="candidateNameEdit"
																 id="candidateNameEdit" />
														</div>
													</div>
													<div class="group" id="joiningEdit" style='display: none'>
														<label class="col-sm-2 control-label">Proposed
															Date of Joining</label>
														<div class="col-md-4">
															<div class="input-group date form_datetime col-sm-12"
																data-link-field="dt_set_order_time_input">
																<input class="form-control" type="text" readonly
																	data-bv-group=".group" id="proposedJoiningDateEdit"
																	name="proposedJoiningDateEdit"
																	value="${demand.proposedJoiningDate}"> <span
																	class="input-group-addon"><span
																	class="glyphicon glyphicon-th"></span></span> <input
																	type="hidden" id="proposedJoiningDateEdit2"
																	name="proposedJoiningDateEdit2" />
															</div>
														</div>
													</div>
												</div>
												<br /> <br /> <br />
												
												<div class="form-group" id="ctype" style='display: none'>
													<div class="group" id="contract" style='display: none'>
														<label class="col-sm-2 control-label">Contract
															Reference</label>
														<div class="col-sm-4">
															<input type="text" class="form-control"
																name="doNumberEdit" value="${demand.doNumber }"
																id="doNumberEdit" />
														</div>
													</div>
													<div class="group" id="reasonAbort" style='display: none'>
														<label class="col-sm-2 control-label">Reason for
															Abort</label>
														<div class="col-sm-4">
															<input type="text" class="form-control" name="reasonEdit"
																value="${demand.reason }" id="reasonEdit" />
														</div>
													</div>
													<div class="group" id="reasonDelayed" style='display: none'>
														<label class="col-sm-2 control-label">Reason for
															Delay</label>
														<div class="col-sm-4">
															<input type="text" class="form-control" name="reasonEdit"
																value="${demand.reason }" id="reasonEdit" />
														</div>
													</div>
												</div>
												<br /> <br /> <br />
	

												<!-- <div class="form-group">
													<div style="text-align: center; width: 50%; float: left">
														<input type="button" value="修&nbsp;&nbsp;改"
															name="subscribe" id="sub_search" 
															class="button btn btn-primary" data-dismiss="modal"
															onclick="updateDemand()"
															style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin: auto;">
													</div>
													<div style="text-align: center; width: 50%; float: right">
														<input type="reset" value="重&nbsp;&nbsp;置"
															name="subscribe" id="sub_add" 
															class="button btn btn-primary" data-dismiss="modal"
															style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin: auto;">
													</div>
												</div> -->

											</form>
										</div>
									</div>
									<div class="tab-pane" id="employee">
										<div id="register2" class="box-content"
											style="overflow: auto;">
											<form id="registerEmployeeForm" method="post">

												<!-- <div id="successAlert" class="alert alert-success"
													style="display: none;"></div> -->

												<div class="form-group">
													<div class="group">
														<label class="col-sm-2 control-label">E-HR</label>
														<div class="col-sm-4">
															<input type="text" class="form-control" name="eHr"
																id="eHr" data-bv-group=".group" />
														</div>
													</div>
													<div class="group">
														<label class="col-sm-2 control-label">LOB</label>
														<div class="col-sm-4">
															<input type="text" class="form-control" name="lob"
																id="lob" data-bv-group=".group" />
														</div>
													</div>

												</div>
												<br /> <br /> <br />
												<div class="form-group">

													<div class="group">
														<label class="col-sm-2 control-label">HSBC Staff
															ID</label>
														<div class="col-sm-4">
															<input type="text" class="form-control"
																name="hsbcStaffId" id="hsbcStaffId"
																data-bv-group=".group" />
														</div>
													</div>
													<div class="group">
														<label class="col-sm-2 control-label">Staff Name</label>
														<div class="col-sm-4">
															<input type="text" class="form-control" name="staffName"
																id="staffName" data-bv-group=".group" />
														</div>
													</div>
												</div>
												<br /> <br /> <br />
												<div class="form-group">
													<div class="group">
														<label class="col-sm-2 control-label">LN</label>
														<div class="col-sm-4">
															<input type="text" class="form-control" name="LN" id="LN"
																data-bv-group=".group" />
														</div>
													</div>
													<div class="group">
														<label class="col-sm-2 control-label">Email</label>
														<div class="col-sm-4">
															<input type="text" class="form-control" name="email"
																id="email" data-bv-group=".group" />
														</div>
													</div>

												</div>
												<br /> <br /> <br />
												<div class="form-group">
													<div class="group">
														<label class="col-lg-2 control-label">Staff
															Location</label>
														<div class="col-lg-4">
															<select class="form-control" name="staffLocation"
																data-bv-notempty
																data-bv-notempty-message="Please Select  StaffLocation"
																id="staffLocation" data-bv-group=".group">
																<option value="">--Option--</option>
															</select>
														</div>
													</div>
													<div class="group">
														<label class="col-lg-2 control-label">Location
															Type</label>
														<div class="col-lg-4">
															<select class="form-control" name="locationType"
																data-bv-notempty
																data-bv-notempty-message="Please Select  LocationType"
																id="locationType" data-bv-group=".group">
																<option value="">--Option--</option>
															</select>
														</div>
													</div>
												</div>
												<br /> <br /> <br />
												<div class="form-group">
													<div class="group">
														<label class="col-sm-2 control-label">Project Name</label>
														<div class="col-sm-4">
															<input type="text" class="form-control"
																name="hsbcProjectName" id="hsbcProjectName"
																data-bv-group=".group" />
														</div>
													</div>
													<div class="group">
														<label class="col-sm-2 control-label">Project
															Manager</label>
														<div class="col-sm-4">
															<input type="text" class="form-control"
																name="hsbcProjectManager" id="hsbcProjectManager"
																data-bv-group=".group" />
														</div>
													</div>
												</div>
												<br /> <br /> <br />
												<div class="form-group">
													<div class="group">
														<label class="col-lg-2 control-label">CS Dept</label>
														<div class="col-lg-4">
															<select class="form-control" name="csSubDept"
																data-bv-notempty
																data-bv-notempty-message="Please Select  csDept"
																id="csSubDept" data-bv-group=".group">
																<option value="${demand.csSubDept }"></option>
															</select>
														</div>
													</div>

													<div class="group">
														<label class="col-lg-2 control-label">RM</label>
														<div class="col-lg-4">
															<select class="form-control" name="Rm" data-bv-notempty
																data-bv-notempty-message="Please Select  RM" id="RM"
																data-bv-group=".group">
																<option value="">--Option--</option>
															</select>
														</div>
													</div>
												</div>
												<br /> <br /> <br />
												<div class="form-group">
													<div class="group">
														<label class="col-sm-2 control-label">Graduation
															Date</label>
														<div class="col-md-4">
															<div class="input-group date form_datetime col-sm-12"
																data-link-field="dt_set_order_time_input">
																<input class="form-control" type="text" readonly
																	data-bv-group=".group" id="graduationDate1"
																	name="graduationDate1"> <span
																	class="input-group-addon"><span
																	class="glyphicon glyphicon-calendar"></span></span> <input
																	type="hidden" id="graduationDate2"
																	name="graduationDate2" />
															</div>
														</div>
													</div>
													<div class="group">
														<label class="col-sm-2 control-label">Date of
															Joining</label>
														<div class="col-md-4">
															<div class="input-group date form_datetime col-sm-12"
																data-link-field="dt_set_order_time_input">
																<input class="form-control" type="text" readonly
																	data-bv-group=".group" id="entryDate1"
																	name="entryDate1"> <span
																	class="input-group-addon"><span
																	class="glyphicon glyphicon-calendar"></span></span> <input
																	type="hidden" id="entryDate2" name="entryDate2" />
															</div>
														</div>
													</div>
												</div>
												<br /> <br /> <br />
												<div class="form-group">
													<div class="group">
														<label class="col-lg-2 control-label">Staff
															Category</label>
														<div class="col-lg-4">
															<select class="form-control" name="staffCategory"
																data-bv-notempty
																data-bv-notempty-message="please select  staffCategory"
																id="staffCategory" data-bv-group=".group">
																<option value="">--Option--</option>
															</select>
														</div>
													</div>
													<div class="group">
														<label class="col-lg-2 control-label">Engagement
															Type</label>
														<div class="col-lg-4">
															<select class="form-control" name="engagementType"
																data-bv-notempty
																data-bv-notempty-message="please select  engagementType"
																id="engagementType2" data-bv-group=".group">
																<option value="Fixed Price" selected="selected">Fixed Price</option>
															</select>
														</div>
													</div>
												</div>
												<br /> <br /> <br />
												<div class="form-group">
													<div class="group">
														<label class="col-lg-2 control-label">Contract
															Reference</label>
														<div class="col-lg-4">
															<input type="text" class="form-control" name="sow"
																id="sow" data-bv-group=".group" /> 
														</div>
													</div>
													<div class="group">
														<label class="col-sm-2 control-label">Contract
															Expired Date</label>
														<div class="col-md-4">
															<div class="input-group date form_datetime col-sm-12"
																data-link-field="dt_set_order_time_input">
																<input class="form-control" type="text" readonly
																	data-bv-group=".group" id="sowExpiredDate1"
																	name="sowExpiredDate1"> <span
																	class="input-group-addon"><span
																	class="glyphicon glyphicon-calendar"></span></span> <input
																	type="hidden" id="sowExpiredDate2"
																	name="sowExpiredDate2" />
															</div>
														</div>
													</div>
												</div>
												<br /> <br /> <br />
												<div class="form-group">
													<div class="group">
														<label class="col-lg-2 control-label">MSA Role</label>
														<div class="col-lg-4">
															<select class="form-control" name="role" data-bv-notempty
																data-bv-notempty-message="please select  msaRole"
																id="role" data-bv-group=".group" onchange="changeData()">
																<option value="">--Option--</option>
															</select>
														</div>
													</div>
													<div class="group">
														<label class="col-lg-2 control-label">Skills/Technology</label>
														<div class="col-lg-4">
															<select class="form-control" name="skill"
																data-bv-notempty
																data-bv-notempty-message="please select  skills"
																id="skill" data-bv-group=".group"
																onchange="changeData()">
																<option value="">--Option--</option>
															</select>
														</div>
													</div>
												</div>
												<br /> <br /> <br />
												<div class="form-group">
													<div class="group">
														<label class="col-lg-2 control-label">Staff Region</label>
														<div class="col-lg-4">
															<select class="form-control" name="staffRegion"
																data-bv-notempty
																data-bv-notempty-message="Please Select  staffRegion"
																id="staffRegion" data-bv-group=".group"
																onchange="changeData()">
																<option value="">--Option--</option>
															</select>
														</div>
													</div>

													<div class="group">
														<label class="col-lg-2 control-label">Resource
															Status</label>
														<div class="col-lg-4">
															<select class="form-control" name="resourceStatus"
																data-bv-notempty
																data-bv-notempty-message="please select  resourceStatus"
																id="resourceStatus" data-bv-group=".group">
																<option value="">--Option--</option>
															</select>
														</div>
													</div>

												</div>
												<br /> <br /> <br />
												<div class="form-group">
									              <div class="group">
										           <label class="col-sm-2 control-label">IT Work Year</label>
										           <div class="col-md-4">
											         <input type="text" class="form-control" name="itworkyear"
													  id="itworkyear" data-bv-group=".group"/>
										           </div>
									              </div>
									              <div class="group">
											
									              </div>
								                </div> 
                                                <br /> <br /> <br />

												<!-- <div class="form-group">
										<div class="group">
										<label class="col-sm-2 control-label">If terminated,mention LWD</label>
										<div class="col-md-4">
											<div class="input-group date form_datetime2 col-sm-12"
												data-link-field="dt_set_order_time_input">
												<input class="form-control" type="text" readonly data-bv-group=".group"
													id="terminatedDate1" name="terminatedDate1"> <span
													class="input-group-addon"><span
													class="glyphicon glyphicon-calendar"></span></span> <input type="hidden"
													id="terminatedDate2" name="terminatedDate2" />
											</div>
										</div>
									    </div>
									   <div class="form-group">
								         <div class="group">
										<label class="col-lg-2 control-label">Reason for Termination</label>
										<div class="col-lg-4">
											<input type="text" class="form-control" name="terminationReason"
													id="terminationReason" data-bv-group=".group"/>
										</div>
										</div>
								</div>  -->
											</form>
										</div>
									</div>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<hr>
		<c:import url="/service/manage/footer" />

	</div>

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
	<script type="text/javascript" src="<%=path %>/js/pmo/recruitdemandafixedprice.js"></script>
	<script type="text/javascript" src="<%=path%>/js/pmo/employeeOnborad.js"></script>
	
	<script type="text/javascript" src="<%=path%>/js/pmo/demandInfoEdit.js"></script>
	
	<script type="text/javascript" src="<%=path%>/js/pmo/demandvalid.js"></script>
	
	<script type="text/javascript" src="<%=path%>/js/pmo/employeeValid.js"></script>
	
	<script type="text/javascript" src="<%=path%>/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="<%=path%>/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/bootstrap-datetimepicker.zh-CN.js"></script>

</body>
</html>


