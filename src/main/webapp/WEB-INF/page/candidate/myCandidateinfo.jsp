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
									<i class="glyphicon glyphicon-user"></i> My Candidate
								</h2>
							</div>
							<div id="employeeInfo" class="box-content">
							    <form id="candidateForm" method="post" class="form-horizontal">
									<div class="group">
										<label class="col-sm-2 control-label">Name</label>
										<div class="col-sm-4">
											<input type="text" class="form-control" name="candidateName" id="candidateName" />
										</div>
									</div>
									<div class="group">
										<label class="col-lg-2 control-label">Source</label>
										<div class="col-lg-4">
											<select class="form-control" name="source" id="source" data-bv-group=".group">
												<option value="">--Option--</option>
										        <option>智联招聘下载</option>
										        <option>智联招聘投递</option>
										        <option>51job下载</option>
										        <option>51job投递</option>
										        <option>猎聘下载</option>
										      	<option>猎聘投递</option>
										        <option>人才热线下载</option>
										        <option>人才热线投递</option>
										        <option>拉钩下载</option>
										       	<option>拉钩投递</option>
										        <option>大街网下载</option>
										        <option>大街网投递</option>
										        <option>boss直聘</option>
										        <option>校园招聘</option>
										        <option>培训机构</option>
										        <option>部门招聘/自找</option>
										        <option>内部推荐-部门</option>
										        <option>内部推荐-个人</option>
										        <option>内部结转</option>
										        <option>其它供应商转入</option>
										        <option>二次入职</option>
										        <option>客户推荐</option>
										        <option>候选人推荐</option>
										        <option>猎头</option>
										        <option>挖角</option>
										        <option>职脉</option>
										        <option>微信</option>
										        <option>QQ</option>
										        <option>论坛</option>
										        <option>社区</option>
										        <option>天涯</option>
										        <option>其它招聘渠道</option>
											</select>
										</div>
									</div>
									<br/><br/><br/>
									<div class="group">
										<label class="col-sm-2 control-label">Tel</label>
										<div class="col-sm-4">
											<input type="text" class="form-control" name="candidateTel" id="candidateTel" />
										</div>
									</div>
									<div class="group">
										<label class="col-lg-2 control-label">Candidate Status</label>
										<div class="col-lg-4">
											<select class="form-control" name="candidateStatus" id="candidateStatus" data-bv-group=".group">
												<option value="">--Option--</option>
												<option value="0">招聘中</option>
												<option value="1">offer中</option>
												<option value="2">已入职</option>
											</select>
										</div>
									</div>
									<br/><br/><br/>
									<div class="group">
										<label class="col-sm-2 control-label">Email</label>
										<div class="col-sm-4">
											<input type="text" class="form-control" name="email" id="email" />
										</div>
									</div>
									<div class="group">
										<label class="col-lg-2 control-label">Education</label>
										<div class="col-lg-4">
											<select class="form-control" name="education" id="education" data-bv-group=".group">
												<option value="">--Option--</option>
												<option value="0">博士</option>
												<option value="1">硕士</option>
												<option value="2">学士</option>
												<option value="3">专科</option>
												<option value="4">高中</option>
											</select>
										</div>
									</div>
									<br/><br/><br/>
									<div class="group">
										<label class="col-sm-2 control-label">Work Years</label>
										<div class="col-lg-4">
											<select class="form-control" name="experienceYears" id="experienceYears" data-bv-group=".group">
												<option value="">--Option--</option>
												<option value="0">2年以下</option>
												<option value="1">3~5年</option>
												<option value="2">6~10年</option>
												<option value="3">11年以上</option>
											</select>
										</div>
									</div>
									<div class="group">
										<label class="col-lg-2 control-label">Major Type</label>
										<div class="col-lg-4">
											<select class="form-control" name="majorStatus" id="majorStatus" data-bv-group=".group">
												<option value="">--Option--</option>
												<option value="0">计算机相关专业</option>
												<option value="1">非计算机相关专业</option>
											</select>
										</div>
									</div>
									<br/><br/><br/>
									<div class="group">
										<label class="col-sm-2 control-label">Skill</label>
										<div class="col-lg-4">
											<select class="form-control" name="skill" id="skill" data-bv-group=".group">
												<option value="">--Option--</option>
											</select>
										</div>
									</div>
									<div class="group">
										<label class="col-lg-2 control-label">English Level</label>
										<div class="col-lg-4">
											<select class="form-control" name="englishLevel" id="englishLevel" data-bv-group=".group">
												<option value="">--Option--</option>
												<option value="0">非工作语言</option>
												<option value="1">工作语言</option>
											</select>
										</div>
									</div>
									<br/><br/><br/>
									<div class="group">
										<label class="col-lg-2 control-label">Interview Status</label>
										<div class="col-lg-4">
											<select class="form-control" name="interviewStatus" id="interviewStatus" data-bv-group=".group">
												<option value="">--Option--</option>
												<option value="0">未推送</option>
												<option value="1">已推送</option>
												<option value="2">面试中</option>
												<option value="3">面试完成</option>
												<option value="5">已退回</option>
												<option value="6">面试确认</option>
												<option value="7">重安排面试</option>
												
											</select>
										</div>
									</div>
									</br></br></br>
									<div class="form-group">
									    <div style="text-align:center;width:50%;;float:left;margin-right:40px;margin-top:10px;">
									    	<input type="button" value="Search"
												name="searchCandidateBtn" id="searchCandidateBtn" 
												class="button btn btn-primary" data-dismiss="modal"
												style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin:auto;cursor:pointer;">
									    </div>
									    <div style="text-align:center;width:20%;float:right;margin-right: 60px;margin-top:10px;">
									    	<input type="reset" value="Export" disabled="disabled"
												name="exportCandidateExcel" id="exportCandidateExcel"
												class="button btn btn-primary" data-dismiss="modal"
												style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin:auto;cursor:pointer;">
									    </div>
									</div>
								        
							        <div >
								    	<a href="" id="exceltHrefCandidate" style="display:none;">导出</a>
								    </div>
								    
									<div style="overflow: auto;">
									<table id="candidateList" class="table table-striped table-bordered">
										<thead>
											<tr>
												<th>Name</th>
												<th>Sex</th>
												<th>Age</th>
												<th>Tel</th>
												<th>Email</th>
												<th>Source</th>
												<th>Candidate Status</th>
												<th>Education</th>
												<th>Work Years</th>
												<th>Major Type</th>
												<th>English Level</th>
												<th>Skill</th>
												<th>Interview Status</th>
												<th>Subdept Name</th>
												<th>Operation</th>
											</tr>
										</thead>
										<tbody></tbody>
									</table>
									</div>
									<div style="height:70px">
										<div style="max-width:400px;float:left">
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
		<form id="editForm" action="" method="post" target="_blank">
			<input type="hidden" id="candidateId" name="candidateId" ></input>
		</form>
		<hr>
		<div class="modal fade" id="myCandidateListModal" tabindex="-1" role="dialog"
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
						<table id="excelBox"
							class="table table-striped table-bordered">
							<thead>
								<tr>
									<label><input type="checkbox" checked="checked" name="CANDIDATE_NAME" value="Name"/>Name&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="CANDIDATE_SEX" value="Sex"/>Sex&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="CANDIDATE_AGE" value="Age"/>Age&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="TEL" value="Tel"/>Tel&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="EMAIL" value="Email"/>Email&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="SOURCE" value="Source"/>Source&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="CANDIDATE_STATUS" value="Status"/>Status&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="EDUCATION" value="Education"/>Education&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="EXPERIENCE_YEARS" value="Work Years"/>Work Years&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="MAJOR_STATUS" value="Major Type"/>Major Type&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="ENGLISH_LEVEL" value="English Level"/>English Level&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="SKILL" value="Skill"/>Skill&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="COLLEGE" value="College"/>College&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="GRADUATE_DATE" value="Graduate Date"/>Graduate Date&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="b.NICKNAME as lockHR" value="Lock HR"/>Lock HR&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="CREATE_DATE" value="Create Date"/>Create Date&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="UPDATE_DATE" value="Update Date"/>Update Date&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="EXPECTED_SALARY" value="Expected Salary"/>Expected Salary&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="REAL_SALARY" value="Real Salary"/>Real Salary&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="ROLE" value="Role"/>Role&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="OLD_COMPANY" value="Old Company"/>Old Company&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="ENTY_DATE" value="Enty Date"/>Enty Date&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="ARRIVAL_DATE" value="Arrival Date"/>Arrival Date&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="c.NICKNAME as createUser" value="Create User"/>Create User&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="INTERVIEW_STATUS" value="Interview Status"/>Interview Status&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="REMARK" value="Remark"/>Remark&nbsp;&nbsp;</label>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
						<div class="center">
							<a class="btn btn-success" href="#" onClick="exportCondition()"> 
								<i class="glyphicon glyphicon-ok icon-white" ></i> 确定
							</a> 
							<a class="btn btn-info" href="#" data-dismiss="modal"> 
								<i class="glyphicon glyphicon-remove icon-white"></i> 取消
							</a>
						</div>
					</div>
				</div>
			</div>
			<!--/span-->
		</div>
		<div class="modal fade" id="myCandidatePushModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="box-header well" data-original-title="">
						<h2>
							<i class="glyphicon glyphicon-user"></i> Push Candiate
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
					 <form id="pushCandidateForm" method="post" class="form-horizontal">
					    <input type="hidden" name="csdeptid" id="csdeptid"></input>
						<div class="group">
							<label class="col-sm-2 control-label" style="width:30%;">Candidate Name</label>
							<div class="col-sm-4" style="width:60%;">
								<input type="text" class="form-control" name="pushCandidateName" id="pushCandidateName" disabled="disabled" />
								<input type="hidden"  name="pushCandidateId" id="pushCandidateId" />
							</div>
						</div>
						<br/><br/><br/>
						<div class="group">
							<label class="col-lg-2 control-label" style="width:30%;">Subdept Name</label>
							<div class="col-lg-4" style="width:60%;">
								<select class="form-control" name="csSubdeptName" id="csSubdeptName" data-bv-group=".group">
									<option value="">--Option--</option>
								</select>
							</div>
						</div>
						<br/><br/><br/>
						<div class="center">
							<a class="btn btn-success" href="#" id="pushSubmit" > 
								<i class="glyphicon glyphicon-ok icon-white" ></i> Confirm
							</a> &nbsp;&nbsp;&nbsp;
							<a class="btn btn-info" href="#" data-dismiss="modal"> 
								<i class="glyphicon glyphicon-remove icon-white"></i> Cancel
							</a>
						</div>
						</form>
					</div>
				</div>
			</div>
			<!--/span-->
		</div>
		<div class="modal fade" id="myCandidateStatusModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="box-header well" data-original-title="">
						<h2>
							<i class="glyphicon glyphicon-user"></i> Candidate Status
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
					 <form id="statusCandidateForm" method="post" class="form-horizontal">
						<div class="group">
							<label class="col-sm-2 control-label" style="width:30%;">Candidate Name</label>
							<div class="col-sm-4" style="width:60%;">
								<input type="text" class="form-control" name="updateCandidateStatusName" id="updateCandidateStatusName" disabled="disabled" />
								<input type="hidden"  name="updateCandidateStatusId" id="updateCandidateStatusId" />
							</div>
						</div>
						<br/><br/><br/>
						<div class="group">
							<label class="col-lg-2 control-label" style="width:30%;">Candidate Status</label>
							<div class="col-lg-4" style="width:60%;">
								<select class="form-control" id="myCandidateStatus" name="myCandidateStatus" data-bv-group=".group">
									<option value="">--Option--</option>
									<!-- <option value="0">招聘中</option>
									<option value="1">offer中</option>
									<option value="2">已入职</option> -->
									<option value="3">闲置中</option>
									<option value="4">暂不关注</option>
									<option value="5">黑名单</option>
									<option value="6">入职他司</option>
								</select>
							</div>
						</div>
						<br/><br/><br/>
						<div class="center">
							<a class="btn btn-success" href="#" id="statusSubmit" > 
								<i class="glyphicon glyphicon-ok icon-white" ></i> Confirm
							</a> &nbsp;&nbsp;&nbsp;
							<a class="btn btn-info" href="#" data-dismiss="modal"> 
								<i class="glyphicon glyphicon-remove icon-white"></i> Cancel
							</a>
						</div>
						
						</form>
					</div>
				</div>
			</div>
			<!--/span-->
		</div>
		<div class="modal fade" id="hrfeedBackbox" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="box-header well" data-original-title="">
						<h2>
							<i class="glyphicon glyphicon-user"></i> HR Feedback
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
						<input type="hidden" name="hrcandidateId" id="hrcandidateId"
							 />
						<div class="group">
							<label class="col-sm-2 control-label">Candidate Name:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" name="candidateNameId" id="candidateNameId" />
							</div>
						</div>
						<br/><br/><br/>
						<div class="group" id="recordTable">
							<label class="col-sm-2 control-label">Record:</label>
						</div>
						<br/>
						
						<div style="overflow: auto;" >
									<table id="feedbackList" class="table table-striped table-bordered" >
										<thead>
											
										</thead>
										<tbody></tbody>
									</table>
									</div>
						
						<div class="group">
						<from id="hrFeedBackForm">
							<label class="col-sm-2 control-label">Feedback:</label>
							<textarea name="hrFeedBack" id="hrFeedBack" class="form-control" style="margin:15px;width: 78%;" rows="6" data-bv-group=".group"></textarea>
							</from>
						</div>
						<div class="center">
							<a class="btn btn-success" href="#"
								onClick="updateHRFeedBack()"> 
								<i class="glyphicon glyphicon-ok icon-white"></i> Confirm
							</a> 
							<a class="btn btn-info" href="#" onClick="cancel()"> 
								<i class="glyphicon glyphicon-remove icon-white"></i> Cancel
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="hrInterviewConfirmbox" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="box-header well" data-original-title="">
						<h2>
							<i class="glyphicon glyphicon-user"></i> HR Interview Confirm
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
						<input type="hidden" name="hrConfirmCandidateId" id="hrConfirmCandidateId"
							 />
					   <input type="hidden" name="hrConfirmInterviewId" id="hrConfirmInterviewId"
							 />
						<div class="group">
							<label class="col-sm-2 control-label " style="width:30%;">Candidate Name:</label>
							<div class="col-sm-4" style="width:60%;">
								<input type="text" class="form-control"  name="hrConfirmCandidateNameId" id="hrConfirmCandidateNameId" />
							</div>
						</div>
						<br/><br/><br/>
						<div class="group">
							<label class="col-sm-2 control-label" style="width:30%;">Interview Date:</label>
							<div class="col-sm-4" style="width:60%;">
								<input type="text" class="form-control"  name="interviewDate" id="interviewDate" />
							</div>
						</div>
						<br/><br/><br/>
						
						<div class="group">
							<label class="col-sm-2 control-label" style="width:30%;">Interview Confirm:</label>
							<div class="col-sm-4" style="width:60%;">
								<select class="form-control"  id="confirmDate" name="confirmDate" data-bv-group=".group">
									<option value="confirm">确认</option>
									<option value="change">取消</option> 
								</select>
							</div>
						</div>
						<br/><br/><br/>
						
						<div class="group" style="display: none" id = "newInterviewDate">
							<form id="conformForm">
							<label class="col-sm-2 control-label" style="width:30%;">New Interview Date:</label>
							<textarea name="newDate" id="newDate" class="form-control" style="margin:15px;width:60%;" rows="6" data-bv-group=".group"></textarea>
						    </form>
						</div>
						<div class="center">
							<a class="btn btn-success" href="#"
								onClick="confirmInterviewDate()"> 
								<i class="glyphicon glyphicon-ok icon-white"></i> Confirm
							</a> 
							<a class="btn btn-info" href="#" onClick="cancelConform()"> 
								<i class="glyphicon glyphicon-remove icon-white"></i> Cancel
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<c:import url="/service/manage/footer" />
	</div>	
	<!--/.fluid-container-->
	
	<!-- 是否发送邮件框提示1 -->
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
				          <h5>Confirmed Success,Do you want to send an email to RM?</h5>
			            </div>
						<div class="modal-footer">
							 <button type="button" class="btn btn-default" data-dismiss="modal">No&nbsp;</button>
							 <button type="button" onclick="getRm()" class="btn btn-primary">Yes</button>
						</div>
					</div>
					
				</div>
				
	</div>
	
	<!-- 是否发送邮件框提示2 -->
	<div class="modal fade" id="issendemail2" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
					    <div class="modal-header">
							 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							 <h4 class="modal-title" id="myModalLabel">
								 Info!
							 </h4>
						</div>
						<div class="modal-body">
				          <h5>Push Successful,Do you want to send an email to RM?</h5>
			            </div>
						<div class="modal-footer">
							 <button type="button" class="btn btn-default" data-dismiss="modal">No&nbsp;</button>
							 <button type="button" onclick="getRm2()" class="btn btn-primary">Yes</button>
						</div>
					</div>
					
				</div>
				
	</div>
	
	<!-- RM列表1 -->
	<div class="modal fade" id="rmlist1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
					    <div class="modal-header">
							 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							 <h4 class="modal-title" id="myModalLabel">
								 RM List
							 </h4>
						</div>
						<div class="modal-body">
				          <input type="checkbox" name="issendtointerview" id="issendtointerview" value="1">
				                          是否发送邮件到面试官
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
	
	<!-- RM列表2 -->
	<div class="modal fade" id="rmlist2" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
					    <div class="modal-header">
							 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							 <h4 class="modal-title" id="myModalLabel">
								 RM List
							 </h4>
						</div>
						<div class="modal-body">
				          
				          <table id="hrdatatable2" class="table table-bordered table-hover table-condensed">
				            <thead>
					          <tr>
					            <th>
					             <input onclick="selAll2()" id="checkAll2" type="checkbox"/>
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
							 <button id="send" type="button" onclick="sendemail2()" class="btn btn-primary">Send&nbsp;&nbsp;</button>
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

	<script type="text/javascript" src="<%=path %>/js/pmo/myCandidateInfo.js"></script>
	<script type="text/javascript" src="<%=path %>/js/pmo/hrfeedback.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.zh-CN.js"></script>

</body>
</html>

