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
<title>修改候选人信息</title>
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
									<i class="glyphicon glyphicon-user"></i> Candidate Input
								</h2>
							</div>
							<div id="register" class="box-content" style="overflow: auto;">
							<form id="updateResumeForm" method="post" action ="/Pmo/service/resume/updateResume.html" enctype="multipart/form-data">
							    
								<div class="form-group">
								<div id="successAlert" class="alert alert-success" style="display: none;"></div>
										<div class="group">
											<label class="col-sm-2 control-label">Name</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="candidateName" value="${resume.candidateName}"
													id="candidateName"/>
											</div>
										</div>
										<div class="group">
											<label class="col-sm-2 control-label">Tel</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="tel" value="${ resume.tel }"
													id="candidateTel" />
											</div>
										</div>
								</div>
								<br/><br/><br/>
								<div class="form-group">
										<div class="group">
											<label class="col-sm-2 control-label">Age</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="age" value="${ resume.age }"
													id="candidateAge" />
											</div>
										</div>
										<div class="group">
										<label class="col-lg-2 control-label">Gender</label>
										<div class="col-lg-4">
											<select class="form-control" id="gender" name="gender"  data-bv-notempty>
												<option value="0" selected="selected">男</option>
												<option value="1">女</option>
											</select>
											
										</div>
										</div>
								</div>
								<br/><br/>
								<div class="form-group">
										<div class="group">
										<label class="col-lg-2 control-label">Education</label>
										<div class="col-lg-4">
											<select class="form-control" id="education" name="education" data-bv-notempty>
												<option value="">-- option--</option>
												<option value="0">博士</option>
												<option value="1">硕士</option>
												<option value="2">学士</option>
												<option value="3">专科</option>
												<option value="4">高中</option>
											</select>
											
										</div>
										</div>
										<div class="group">
											<label class="col-sm-2 control-label">College</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="college" value="${ resume.college }"
													id="college" />
											</div>
										</div>
								</div>
								<br/><br/>
								<div class="form-group">
										<div class="group">
										<label class="col-lg-2 control-label">Is Computer major</label>
										<div class="col-lg-4">
											<select class="form-control" id="major" name="major" data-bv-notempty
												>
												<option value="">-- option--</option>
												<option value="0"> Yes</option>
												<option value="1"> No</option>
											</select>
										</div>
										</div>
										<div class="group">
											<label class="col-sm-2 control-label">Experience Years</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="experience_years"
													id="experience_years" value="${ resume.experience_years }"/>
											</div>
										</div>
								</div>
								<br/><br/>
								<div class="form-group">
										<div class="group">
										<label class="col-lg-2 control-label">Skill</label>
										<div class="col-lg-4">
											<select class="form-control" name="skill" data-bv-notempty
												data-bv-notempty-message="请选择角色" id="skill" data-bv-group=".group">
												<option value="">-- option--</option>
											</select>
										</div>
										</div>
										<div class="group">
										<label class="col-sm-2 control-label">Graduate Date</label>
										<div class="col-md-4">
											<div class="input-group date form_datetime col-sm-12"
												data-link-field="dt_set_order_time_input">
												<input class="form-control" type="text" disabled="disabled"
													id="GRADUATE_DATE1" name="GRADUATE_DATE1"/> <span
													class="input-group-addon"><span
													class="glyphicon glyphicon-th"></span></span> <input type="hidden"
													id="graduate_date" name="graduate_date" value="${ resume.graduate_date }"/>
											</div>
										</div>
									    </div>
								</div>
								<br/><br/>
								<div class="form-group">
										<div class="group">
										<label class="col-lg-2 control-label">English Level</label>
										<div class="col-lg-4">
											<select class="form-control" name="English_level" id="English_level" data-bv-notempty>
												<option value="">-- option --</option>
												<option value="0">非工作语言</option>
												<option value="1">工作语言</option>
											</select>
										</div>
										</div>
										<div class="group">
										<label class="col-lg-2 control-label">Candidate Status</label>
										<div class="col-lg-4">
											<select class="form-control" name="candidate_status" id="candidate_status" data-bv-notempty>
												<option value="">-- option --</option>
												<option value="0">招聘中</option>
												<option value="1">offer中</option>
												<option value="2">已入职</option>
												<option value="3">闲置</option>
												<option value="4">暂不关注</option>
											</select>
										</div>
										</div>
								</div>
								<br/><br/>
								<div class="form-group">
										
										<div class="group">
										<div class="group">
											<label class="col-sm-2 control-label">Email</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="email" value="${ resume.email }"
													id="email" />
											</div>
										</div>
									</div>
									<div class="group">
										<div class="group">
											<label class="col-sm-2 control-label">Entry_date</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="entry_date"
													id="entry_date" value="${ resume.entry_date }"/>
											</div>
										</div>
									</div>
									
								</div>
								<br/><br/>
								<div class="form-group">
									<div class="group">
										<label class="col-lg-2 control-label">Source</label>
										<div class="col-lg-4">
											<select class="form-control" name="source" data-bv-notempty
											data-bv-notempty-message="请选择简历来源" id="source" data-bv-group=".group">
											<option value="">-- option--</option>
										</select>
										</div>
										</div>
										
									<div class="group">
									<label class="col-lg-2 control-label">Role</label>
									<div class="col-lg-4">
										<select class="form-control" name="role" data-bv-notempty
											data-bv-notempty-message="请选择角色" id="role" data-bv-group=".group">
											<option value="">--option--</option>
										</select>
									</div>
									</div>
									
								</div>
								<br/><br/>
								<div class="form-group">
										
										<div class="group">
										<div class="group">
											<label class="col-sm-2 control-label">Expected Salary</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="expected_salary"
													id="expected_salary" value="${ resume.expected_salary }"/>
											</div>
										</div>
									</div>
									<div class="group">
										<div class="group">
											<label class="col-sm-2 control-label">Real Salary</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="real_salary"
													id="real_salary" value="${ resume.real_salary }"/>
											</div>
										</div>
									</div>
								</div>
								<br/><br/>
								
								
								<div class="form-group">
										
										<div class="group">
										<div class="group">
											<label class="col-sm-2 control-label">Old_Company</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="old_company"
													id="old_company" value="${ resume.old_company }"/>
											</div>
										</div>
									</div>
									<div class="group">
										<div class="group">
											<label class="col-sm-2 control-label">Remark</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="remark"
													id="remark" value="${ resume.remark }"/>
											</div>
										</div>
									</div>
								</div>
								<br/><br/>
								<div class="form-group">
									
									<div class="group">
										<label class="col-sm-2 control-label">Upload Resume</label>
										<div class="col-sm-4">
											<input type="hidden" name="resume_path" id="resume_path">
											<span style="display: block;"><input  type="file" name="file" id="uploadId" style="display: inline;"/>
											<input style="display: inline;" type="button" id = "upload" value="UPLOAD"/></span>
										</div>
									</div>
									
								</div>
								
								<br/><br/><br/>
								
								<div class="form-group">
									    <div style="text-align:center;width:100%;">
									    <input type="submit" value="UPDATE"
										class="button btn btn-primary" data-dismiss="modal"
										style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin:auto ;">
									    </div>
								</div>
								<br/><br/>
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

	<script type="text/javascript" src="<%=path %>/js/pmo/updateEmployeeInfo.js"></script>
	<script type="text/javascript" src="<%=path %>/js/pmo/ajaxfileupload.js"></script>
	<script type="text/javascript" src="<%=path %>/js/pmo/resumeInput.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.zh-CN.js"></script>

</body>
</html>


