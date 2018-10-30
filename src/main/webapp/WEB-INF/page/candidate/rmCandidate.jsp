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
<link href='<%=path %>/bower_components/fullcalendar/dist/fullcalendar.css' rel='stylesheet'>
<link href='<%=path %>/bower_components/fullcalendar/dist/fullcalendar.print.css' rel='stylesheet' media='print'>
<link href='<%=path %>/bower_components/chosen/chosen.min.css' rel='stylesheet'>
<link href='<%=path %>/bower_components/colorbox/example3/colorbox.css' rel='stylesheet'>
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

<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- The fav icon -->
<link rel="shortcut icon" href="<%=path %>/img/favicon.ico">

<script>
var path='<%=path%>';
</script>
<style>
.table th, .table td { 
text-align: center;
vertical-align: middle!important;
}
.bootstrap-dialog-message{
	overflow: auto;
}
</style>
</head>
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
									<i class="glyphicon glyphicon-user"></i> Interview Arrangement
								</h2>
							</div>
							<div id="demandInfo" class="box-content">
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
							    
							    <form id="demandForm" method="post" class="form-horizontal">
									<div style="overflow: auto;">
									<table id="rmCandidateList"
										class="table table-striped table-bordered">
										<thead>
											<tr   style="text-align:center;">
												<th>Name</th>
												<th>Interview Status</th>
												<th>Gender</th>
												<th>Age</th>
												<th>Skill</th>
												<!-- <th>Education</th> -->
												<th>Works Years</th>
												<th>Tel</th>
												<th>Email</th>
												<th>CS Dept</th>
												<th>HR</th>
												<th>Operation</th>
											</tr>
										</thead>
										
									</table>
									</div>
									<div>
										<ul class="pagination pagination-centered">
											<li><a href="#" id="fristPage">First</a></li>
											<li><a href="#" id="previousPage" >Last</a></li>
											<li><a href="#" id="nextPage" >Next</a></li>
											<li><a href="#" id="lastPage" >End</a></li>
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
					<form action="" id="interviewForm">
					    <input id="puid" name="puid" type="hidden"></input>
						<table id="excelBox"
							class="table table-striped table-bordered">
							<thead>
								<tr>
									<td><label class="col-sx-2 control-label">Interview Date</label></td>
									<td>
									<div class="input-group date form_datetime col-sm-12"
										data-link-field="dt_set_order_time_input">
										<input class="form-control" type="text" disabled="disabled"
											id="graduationDate1" name="graduationDate1"/> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-th"></span></span> <input type="hidden"
											id="interviewDate" name="interviewDate" />
									</div>
									</td>
								</tr>
								<tr>
									<td><label class="col-sz-2 control-label">Interview Type</label></td>
									<td>
											<select class="form-control" name="interviewType" data-bv-notempty
												data-bv-notempty-message="请选择角色" id="interviewType" data-bv-group=".group">
												<option value="">--Option--</option>
												<option value="0">电话面试</option>
												<option value="1">现场面试</option>
											</select>
									</td>
								</tr>
								<tr>
									<td><label class="col-sz-2 control-label">Project</label></td>
									<td>
									<input type="text" class="form-control" name="projectName" id="projectName" />
									</td>
								</tr>
								<tr>
									<td><label class="col-sz-2 control-label">Interviewer</label></td>
									<td>
											<select class="form-control" name="interviewer" data-bv-notempty
												data-bv-notempty-message="请选择角色" id="interviewer" data-bv-group=".group">
												<option value="">--Option--</option>
											</select>
									</td>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
						<div class="center">
							<!-- <a class="btn btn-success" href="#" id="addInterviewer"><i  class="glyphicon glyphicon-ok icon-white" ></i> 确定
							</a>  -->
							<button type="button" id="addInterviewer" class="btn btn-primary btn-primary-new">Confirm</button>
							<a class="btn btn-info" href="#" data-dismiss="modal"> <i
								class="glyphicon glyphicon-remove icon-white"></i> Cancel
							</a>
						</div>
						</form>
					</div>
				</div>
			</div>
			<!--/span-->
		</div>
		
		
		
		
		
		
		
	
		
		
	<div class="modal fade" id="confirmBox" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="box-header well" data-original-title="">
						<h2>
							<i class="glyphicon glyphicon-user"></i>Confirm Information
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
						<form id="confirmForm">
							<!-- <input type="hidden" name="interviewId" id="interviewId" /> -->
							<div class="group">
								<label class="col-sm-2 control-label">Candidate Name:</label>
								<div class="col-sm-6">
									<input type="text" readonly="true" class="form-control" name="candidateName" id="candidateName" />
								</div>
							</div>
							<br/><br/><br/>
							<div class="group">
								<label class="col-sm-2 control-label">Confirm Information:</label>
								<div class="col-sm-10">
								<form id="confirmForm01">
									<textarea name="confirmInfo" id="confirmInfo" class="form-control" style="width: 78%;" rows="4"></textarea>
								</form>
								</div>
							</div>
							</br></br></br></br></br></br></br>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>		
		
		<form id="resumeForm" action="" method="post" target="_blank">
			<input type="hidden" id="candidateId" name="candidateId" ></input>
		</form>
		<div style="display: none" id="table_area" ></div>
		<c:import url="/service/manage/footer" />


	<!--/.fluid-container-->
	
	<!-- 是否发送邮件框提示 -->
	<div class="modal fade" id="issendemail" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
					    <div class="modal-header">
							 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							 <h4 class="modal-title" id="myModalLabel">
								 Info!
							 </h4>
						</div>
						<div class="modal-body">
				          <h5>Do you want to send an email to HR?</h5>
			            </div>
						<div class="modal-footer">
							 <button type="button" class="btn btn-default" data-dismiss="modal">No&nbsp;</button>
							 <button type="button" onclick="getHr()" class="btn btn-primary">Yes</button>
						</div>
					</div>
					
				</div>
				
	</div>
	
	<!-- HR列表 -->
	<div class="modal fade" id="hrlist" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
					    <div class="modal-header">
							 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							 <h4 class="modal-title" id="myModalLabel">
								 HR List
							 </h4>
						</div>
						<div class="modal-body">
				          
				          <table id="hrdatatable" class="table table-bordered table-hover table-condensed">
				            <thead>
					          <tr>
					            <th>
					             <input onclick="selAll()" id="checkAll" type="checkbox"/>
					            </th>
						        <th>
							      USERNAME
						        </th>
						        <th>
							      NICKNAME
						        </th>
						        <th>
							      USERTYPE
						        </th>
						        <th>
							      EMAIL
						        </th>
					         </tr>
				           </thead>
				           <tbody id="hrdata">
				           </tbody>
			             </table>
				          
			            </div>
						<div class="modal-footer">
							 <button id="cancel" type="button" class="btn btn-default" data-dismiss="modal">Cancel&nbsp;</button>
							 <button id="send" type="button" onclick="sendemail()" class="btn btn-primary">Send&nbsp;&nbsp;</button>
						</div>
					</div>
					
				</div>
				
	</div>
	
	<!-- 进度显示 -->
	<div class="modal fade" id="jindu" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="progress progress-striped active" style="margin-top:300px;">
	          <div class="progress-bar progress-bar-success" role="progressbar"
		            aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
		            style="width: 100%;">邮件发送中......
		            <span class="sr-only">100% 完成</span>
	          </div>
             </div>
		</div>		
	</div>

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

	<script type="text/javascript" src="<%=path %>/js/pmo/rmCandidate.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-dialog.js"></script>
</body>
</html>


