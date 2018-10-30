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
									<i class="glyphicon glyphicon-user"></i> Batch Maintenance
								</h2>
							</div>
							<div id="employeeInfo" class="box-content">
								<form id="employeeForm" method="post" class="form-horizontal">
									<div class="group">
										<label class="col-lg-2 control-label">BU</label>
										<div class="col-lg-4">
											<select class="form-control" name="csBu" data-bv-notempty
												data-bv-notempty-message="请选择角色" id="csBu"
												data-bv-group=".group">
												<option value="">-- Option--</option>
												<!-- <option value="1">第一事业部</option>
												<option value="2">第二事业部</option>
												<option value="3">第三事业部</option>
												<option value="4">第四事业部</option>
												<option value="5">业务部门</option> -->
											</select>
										</div>
									</div>
									<div class="group">
										<label class="col-lg-2 control-label">DU</label>
										<div class="col-lg-4">
											<select class="form-control" name="csSubDept"
												data-bv-notempty data-bv-notempty-message="请选择角色"
												id="csSubDept" data-bv-group=".group">
												<option value="">-- Option--</option>
											</select>
										</div>
									</div>
									<br/> <br/> <br/>
									<div class="group">
										<label class="col-sm-2 control-label">LOB</label>
										<div class="col-sm-4">
											<input type="text" class="form-control" name="lob" id="lob" />
										</div>
									</div>
									<div class="group">
										<label class="col-sm-2 control-label">HSBC Staff ID</label>
										<div class="col-sm-4">
											<input type="text" class="form-control" name="hsbcStaffId"
												id="hsbcStaffId" />
										</div>
									</div>
									<br/> <br/> <br/>

									<div id="successAlert" class="alert alert-success"
										style="display: none;"></div>

									<div class="group">
										<label class="col-sm-2 control-label">E-HR</label>
										<div class="col-sm-4">
											<input type="text" class="form-control" name="eHr" id="eHr" />
										</div>
									</div>
									<div class="group">
										<label class="col-sm-2 control-label">Staff Name</label>
										<div class="col-sm-4">
											<input type="text" class="form-control" name="staffName"
												id="staffName" />
										</div>
									</div>
									<br/> <br/> <br/>
									<div class="group">
										<label class="col-lg-2 control-label">Status</label>
										<div class="col-lg-4">
											<select class="form-control" name="resourceStatus"
												data-bv-notempty
												data-bv-notempty-message="please select  resourceStatus"
												id="resourceStatus" data-bv-group=".group">
												<option value="">--Option--</option>
											</select>
										</div>
									</div>
									<div class="group">
										<label class="col-lg-2 control-label">EngagementType</label>
										<div class="col-lg-4">
											<select class="form-control" name="engagementType" data-bv-notempty
												id="engagementType" data-bv-group=".group">
												<option value="">--Option--</option>
											</select>
										</div>
										</div>
										<br/><br/><br/>
										
									<div class="group">
										<label class="col-lg-2 control-label">RM</label>
										<div class="col-lg-4">
											<select class="form-control" name="resourceStatus" data-bv-notempty
												data-bv-notempty-message="please select  resourceStatus" id="RM" data-bv-group=".group">
												<option value="">--Option--</option>
											</select>
										</div>
										</div>									
									<br/><br/><br/>
									
									<div class="form-group">
										<div style="text-align: center; width: 50%; float: right">
											<input type="button" value="Modify" name="transfer"
												id="transfer" href="#" class="button btn btn-primary"
												data-dismiss="modal"
												style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin: auto;">
										</div>
										<div style="text-align: center; width: 50%; float: left">
											<input type="button" value="Search" name="searchBtn"
												id="searchBtn"  class="button btn btn-primary"
												data-dismiss="modal"
												style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin: auto;">
										</div>
										<br/> <br/>
										<div class="group" style="text-align: center; display: none;"
											id="transferBox">
											<label id="num" class="col-sm-2 control-label">Employee</label>
											<div class="col-sm-4">
												<textarea name="transBox" cols="80" rows="4" id="transBox" disabled="disabled"></textarea>
											</div>
										</div>
									</div>
									
									<div style="overflow: auto;">
										<table id="employeeList"
											class="table table-striped table-bordered">
											<thead>
												<tr>
													<th width="30px" align="center"><input id='employeeIds' type='checkbox' onclick='checkedEmployeeAll()' ></th>
													<th>SL</th>
													<th>StaffName</th>												
													<th>ER</th>
													<th>LOB</th>
													<th>StaffId</th>
													<th>DU</th>
													<th>EngagementType</th>
													<th>Status</th>
													<th>RM</th>
													<th>Operate</th>
												</tr>
											</thead>
											<tbody>
											</tbody>
										</table>
									</div>
									<div>
										<ul class="pagination pagination-centered">
											<li><a href="#" id="fristPage"
												onclick="loadEmployeeList('frist')">First</a></li>
											<li><a href="#" id="previousPage"
												onclick="loadEmployeeList('previous')">Last</a></li>
											<li><a href="#" id="nextPage"
												onclick="loadEmployeeList('next')">Next</a></li>
											<li><a href="#" id="lastPage"
												onclick="loadEmployeeList('last')">End</a></li>
										</ul>
										<div style="max-width:400px;float:right;margin-top:30px;">
										<!-- 每页<span id="pageDataCount"></span>条&nbsp;&nbsp;第<span id="currentPage"></span>页&nbsp;/&nbsp;共<span id="pageCount"></span>页&nbsp;&nbsp;共<span id="dataCount"></span>条 -->
											<b><span id="currentPage"></span> / <span id="pageCount"></span>
												<select class="pagination pagination-centered"	name="pageRecordsNum" data-bv-notempty
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
								<form action="" id="editForm" method="post" target="_blank">
									<input id="employeeId" name="employeeId" type="hidden" />
									<input type="hidden" name="batchinfo" id="batchinfo" value="${batch}"></input>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="modifyMadal"  role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="box-header well" data-original-title="">
						<h2>
							<i class="glyphicon glyphicon-user"></i> Modify
						</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-round btn-default  btn-minimize "><i
								class="glyphicon glyphicon-chevron-up"></i></a> <a
								class="btn btn-round btn-default" href="#" data-dismiss="modal">
								<i class="glyphicon glyphicon-remove"></i>
							</a>
						</div>
					</div>
					<div id="batchModify" class="box-content">
						<form id="modifyForm" method="post" class="form-horizontal">
						
						<div class="form-group">
						    <div class="group">
								<label class="col-sm-2 control-label" >Staff Name</label>
								<div class="col-sm-8" >
									<input type="text" class="form-control" name="staffNames" id="staffNames" disabled="disabled" />
									<input type="hidden"  name="staffIds" id="staffIds" />
								</div>
							</div>
						</div>	
						    
						    <div class="form-group">
							<div class="group">
								<label class="col-lg-2 control-label" >Modify
									Type</label>
								<div class="col-lg-8" >
									<select class="form-control" name="modifyName" id="modifyName"
										data-bv-group=".group">
										<option value="" >-Option-</option>
										<option value="1">Project properties</option>
										<option value="2">Role</option>
										<option value="3">Department</option>
										<option value="4">RM</option>
									</select>
								</div>
							</div>
							</div>
							<div style="display: none" id="projectProperties">
								<div class="form-group">
								<div class="group">
									<label class="col-sm-2 control-label" >Project Name</label>
									<div class="col-sm-8" >
										<input type="text" class="form-control" name="projectName" data-bv-group=".group" id="projectName"  /> 
									</div>
								</div>
								</div>
								
								<div class="form-group">
								<div class="group">
									<label class="col-sm-2 control-label" >SOW#</label>
									<div class="col-sm-8" >
										<input type="text" class="form-control" name="sowName" data-bv-group=".group" id="sowName"  />
									</div>
								</div>
								</div>
								
								<div class="form-group">
								<div class="group">
									<label class="col-sm-2 control-label " >SOW# Expired Date</label>
										<div class="col-md-8" >
											<div class="input-group date form_datetime1 col-sm-12"
												data-link-field="dt_set_order_time_input">
												<input class="form-control" type="text" readonly data-bv-group=".group"
													id="sowExpiredDate1" name="sowExpiredDate1"> <span
													class="input-group-addon"><span
													class="glyphicon glyphicon-calendar"></span></span> <input type="hidden"
													id="sowExpiredDate2" name="sowExpiredDate2" />
											</div>
										</div>
								</div>
								</div>
								
								<div class="form-group">
								<div class="group">
									<label class="col-lg-2 control-label" >Engagement Type</label>
										<div class="col-lg-8" >
											<select class="form-control" name="engagementType" data-bv-notempty
												id="engagementType2" data-bv-group=".group">
												<option value="">--Option--</option>
											</select>
										</div>
								</div>
								</div>
							</div>
							<div style="display: none" id="humanRole">
								<div class="form-group">
								<div class="group">
									<label class="col-lg-2 control-label" >MSA Role</label>
										<div class="col-lg-8" >
											<select class="form-control" name="role" data-bv-notempty
												id="role" data-bv-group=".group" onchange="changeData()">
												<option value="">--Option--</option>
											</select>
										</div>
								</div>
								</div>
								<div class="form-group">
								<div class="group">
										<label class="col-lg-2 control-label" >Skills/Technology</label>
										<div class="col-lg-8" >
											<select class="form-control" name="skill" data-bv-notempty
												 id="skill" data-bv-group=".group" onchange="changeData()">
												<option value="">--Option--</option>
											</select>
										</div>
										</div>
							</div>
							<div class="form-group">
								<div class="group">
									<label class="col-lg-2 control-label" >Staff Region</label>
										<div class="col-lg-8" >
											<select class="form-control" name="staffRegion" data-bv-notempty
												 id="staffRegion" data-bv-group=".group" onchange="changeData()">
												<option value="">--Option--</option>
											</select>
										</div>
								</div>
								</div>
								<div class="form-group">
								<div class="group">
									<label class="col-sm-2 control-label" >Bill
										Rate</label>
									<div class="col-sm-8" >
										<input type="text" class="form-control" name="billRate" id="billRate"  /> 
									</div>
								</div>
								</div>
							</div>
							<div style="display: none" id="departmentModify">
							    <div class="form-group">
							    <div class="group">
									<label class="col-lg-2 control-label">GBGF</label>
									<div class="col-lg-8">
										<select onchange="changeGBGF()" class="form-control" name="gbGf" data-bv-notempty
												data-bv-notempty-message="please select  GB_GF" id="gbGf" data-bv-group=".group">
										  <option value="">--Option--</option>
										</select>
									</div>
								</div>
							    </div>
							
								<div class="form-group">
								<div class="group">
									<label class="col-lg-2 control-label" >HSBC Dept</label>
										<div class="col-lg-8" >
											<select onchange="changeHSBCDept()" class="form-control" name="hsbcDept" data-bv-notempty
												 id="hsbcDept" data-bv-group=".group">
											<option value="">--Option--</option>
											</select>
										</div>
								</div>
								</div>
								<div class="form-group">
								<div class="group">
									<label class="col-lg-2 control-label" >HSBC Sub Dept</label>
										<div class="col-lg-8" >
											<select class="form-control" name="hsbcSubDept"  id="hsbcSubDept" data-bv-group=".group">
												<option value="">--Option--</option>
											</select>
										</div>
								</div>
								</div>
								<div class="form-group">
								<div class="group">
									<label class="col-sm-2 control-label" >HSBC
										Manager</label>
									<div class="col-sm-8" >
										<input type="text" class="form-control" name="hsbcManager" id="hsbcManager"  /> 
									</div>
								</div>
								</div>
							</div>
							<div style="display: none" id="nickName">
								<div class="form-group">
								<div class="group">
											<label class="col-lg-2 control-label">RM</label>
											<div class="col-lg-8">
												<select class="form-control" name="nickName" data-bv-notempty
													 id="rmName" data-bv-group=".group">
													<option value="">--Option--</option>
												</select>
											</div>
								</div>
								</div>
							</div>
							<div class="center">
								<a class="btn btn-success" href="#" id="transSubmit"> <i
									class="glyphicon glyphicon-ok icon-white"></i> Confirm
								</a> &nbsp;&nbsp;&nbsp; <a class="btn btn-info" href="#"
									data-dismiss="modal"> <i
									class="glyphicon glyphicon-remove icon-white"></i> Cancel
								</a>
							</div>
						</form>
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

	<script type="text/javascript"
		src="<%=path%>/js/pmo/employeeInfoBatch.js"></script>
	<script type="text/javascript"
		src="<%=path%>/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript"
		src="<%=path%>/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>/js/bootstrap-datetimepicker.zh-CN.js"></script>

</body>
</html>


