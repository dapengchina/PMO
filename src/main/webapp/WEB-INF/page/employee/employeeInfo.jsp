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
									<i class="glyphicon glyphicon-user"></i> Information Maintenance
								</h2>
							</div>
							<div id="employeeInfo" class="box-content">
							    
							    <form id="employeeForm" method="post" class="form-horizontal">
									
									<input id="userId" name="userId" type="hidden" value="${loginUser.userId}"/>
										<div class="group">
										<label class="col-lg-2 control-label">BU</label>
										<div class="col-lg-4">
											<select class="form-control" name="csBu" data-bv-notempty
												data-bv-notempty-message="请选择角色" id="csBu" data-bv-group=".group">
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
											<select class="form-control" name="csSubDept" data-bv-notempty
												data-bv-notempty-message="请选择角色" id="csSubDept" data-bv-group=".group">
												<option value="">-- Option--</option>
											</select>
										</div>
										</div>
										</br></br></br>
										<div class="group">
											<label class="col-sm-2 control-label">LOB</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="lob"
													id="lob" />
											</div>
										</div>
										<div class="group">
											<label class="col-sm-2 control-label">Staff ID</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="hsbcStaffId"
													id="hsbcStaffId" />
											</div>
										</div>
										</br></br></br>
										
								        <div id="successAlert" class="alert alert-success" style="display: none;"></div>
										
										<div class="group">
											<label class="col-sm-2 control-label">E-HR</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="eHr"
													id="eHr" />
											</div>
										</div>
										<div class="group">
											<label class="col-sm-2 control-label">Name</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="staffName"
													id="staffName" />
											</div>
										</div>
										</br></br></br>
										
										<div class="group">
										<label class="col-lg-2 control-label">Status</label>
										<div class="col-lg-4">
											<select class="form-control" name="resourceStatus" data-bv-notempty
												data-bv-notempty-message="please select  resourceStatus" id="resourceStatus" data-bv-group=".group">
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
										</br></br></br>
										
										<div class="group">
										<label class="col-lg-2 control-label">RM</label>
										<div class="col-lg-4">
											<select class="form-control" name="nickName" data-bv-notempty
												id="RM" data-bv-group=".group">
												<option value="">--Option--</option>
											</select>
										</div>
										</div>
										</br></br></br>
										 <div class="panel panel-default">
							 <div class="panel-heading">Employee Upgrade Record Query Conditions</div>
                             <div class="panel-body">
                                    <div class="group">
										<label class="col-lg-2 control-label">StartTime</label>  
                                        <!--指定 date标记--> 
                                        <div class="col-lg-4">
                                        <div class='input-group date' id='datetimepicker1'>  
                                            <input id="startTime" type='text' class="form-control" />  
                                            <span class="input-group-addon">  
                                             <span class="glyphicon glyphicon-calendar"></span>  
                                            </span>  
                                        </div> 
                                        </div> 
									</div>
									<div class="group">
										<label class="col-lg-2 control-label">EndTime</label>  
                                        <!--指定 date标记-->  
                                        <div class="col-lg-4">
                                        <div class='input-group date' id='datetimepicker2'>  
                                            <input id="endTime" type='text' class="form-control" />  
                                            <span class="input-group-addon">  
                                              <span class="glyphicon glyphicon-calendar"></span>  
                                            </span>  
                                        </div>
                                        </div>  
									</div>
									
                               </div>
                             </div>
										
										<div class="form-group">
									    <div style="text-align:center;width:50%;float:left">
									    <input type="button" value="Search"
										name="searchBtn" id="searchBtn" href="#"
										class="button btn btn-primary" data-dismiss="modal"
										style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin:auto ;">
									    </div>
									    <div style="text-align:center;width:50%;float:right">
									    <input type="button" value="Export" disabled="disabled"
										name="exportExcel" id="exportExcel" href="#"
										class="button btn btn-primary" data-dismiss="modal"
										style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin:auto ;">
									    </div>
								        </div>
								        
								        <div >
									    <a href="" id="exceltHref" style="display:none;">Export</a>
									    </div>
								    
									<div style="overflow: auto;">
									<table id="employeeList"
										class="table table-striped table-bordered">
										<thead>
											<tr>
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
											<li><a href="#" id="fristPage" onclick="loadEmployeeList('frist')">First</a></li>
											<li><a href="#" id="previousPage" onclick="loadEmployeeList('previous')">Last</a></li>
											<li><a href="#" id="nextPage" onclick="loadEmployeeList('next')">Next</a></li>
											<li><a href="#" id="lastPage" onclick="loadEmployeeList('last')">End</a></li>
										</ul>
										
										<div style="max-width: 400px; float: right; margin-top: 30px;">
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
										<!-- <br>
										共<span id="pageCount"></span>页   第<span id="currentPage"></span>页 -->
									</div>
									
								</form>
								<form action="" id="editForm" method="post" target="_blank">
									<input id="employeeId" name="employeeId" type="hidden" />
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
						<table id="excelBox"
							class="table table-striped table-bordered">
							<thead>
								<tr>
									<label><input type="checkbox" name="HSBC Staff ID"/>HSBC Staff ID&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Staff Name"/>Staff Name&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="LN"/>LN&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="E-Mail"/>E-Mail&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Staff Region"/>Staff Region&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Staff Location (country)"/>Staff Location (country)&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Location Type (HSBCOffice/ODC)"/>Location Type (HSBCOffice/ODC)&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Onshore or Offshore"/>Onshore or Offshore&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="GB/GF"/>GB/GF&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="HSBC Dept">HSBC Dept&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="HSBC Sub Dept"/>HSBC Sub Dept&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="HSBC Manager"/>HSBC Manager&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Project Name"/>Project Name&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="SOW#"/>SOW#&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="SOW# Expired Date"/>SOW# Expired Date&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Staff Category(CATG/CATB)"/>Staff Category(CATG/CATB)&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Engagement Type (T&M/FixedCost)"/>Engagement Type (T&M/FixedCost)&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="HSBC DOJ"/>HSBC DOJ&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Experience on HSBC account in Months"/>Experience on HSBC account in Months&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Graduation Date"/>Graduation Date&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Total Experience in Months"/>Total Experience in Months&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="MSA Role"/>MSA Role&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Skills/Technology"/>Skills/Technology&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Entry Date"/>Entry Date&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Billing Currency"/>Billing Currency&nbsp;&nbsp;</label>
									<c:if test="${(sessionScope.loginUser.userType eq '0') || (sessionScope.loginUser.userType eq '1')|| (sessionScope.loginUser.userType eq '2')|| (sessionScope.loginUser.userType eq '3')|| (sessionScope.loginUser.userType eq '4')|| (sessionScope.loginUser.userType eq '5')|| (sessionScope.loginUser.userType eq '9')|| (sessionScope.loginUser.userType eq '13')|| (sessionScope.loginUser.userType eq '14')|| (sessionScope.loginUser.userType eq '15')}">
									<label><input type="checkbox" name="Bill Rate"/>Bill Rate&nbsp;&nbsp;</label>
									</c:if>
									<label><input type="checkbox" name="Resource Status (Active/Terminated)"/>Resource Status (Active/Terminated)&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="If terminated mention LWD"/>LWD&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Reason for Termination"/>Reason for Termination&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="E-HR"/>E-HR&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="LOB"/>LOB&nbsp;&nbsp;</label>	
									<label><input type="checkbox" name="RM"/>RM&nbsp;&nbsp;</label>								
									<!-- <label><input type="checkbox" name="AM"/>AM&nbsp;&nbsp;</label> -->
									<label><input type="checkbox" name="CS Dept"/>CS Dept&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Itworkyear"/>Itworkyear&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Ch Pro Number"/>Ch Pro Number&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Ch Pro StartDate"/>Ch Pro StartDate&nbsp;&nbsp;</label>
									<label><input type="checkbox" name="Ch Pro Name"/>Ch Pro Name&nbsp;&nbsp;</label>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
						<div class="center">
							<a class="btn btn-success" href="#" onClick="selectAll()"> Select All
							</a> 
							<a class="btn btn-success" href="#" onClick="exportCondition()"> <i
								class="glyphicon glyphicon-ok icon-white" ></i> Confirm
							</a> <a class="btn btn-info" href="#" onClick="exportCancel()"> <i 
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
	
	<!-- 员工调级 -->
	<div class="modal fade" id="employeeUpgrade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div style="width:65%" class="modal-dialog">
					<div class="modal-content">
					    <div class="modal-header">
							 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							 <h4 class="modal-title" id="myModalLabel">
								 Promote Detail Information Of <b id="upgradeemployeename"></b>
							 </h4>
						</div>
						<div class="modal-body">
						   <!-- 员工调级记录模块 -->
						   <div class="panel panel-default">
							 <div class="panel-heading">Employee Upgrade History Record</div>
                             <div class="panel-body">
                                 <table id="employeeUpgradeRecordList"></table>
                             </div>
                           </div>
                           <!-- 员工基本信息模块 -->
						   <div class="panel panel-default">
							 <div class="panel-heading">Employee Base Information</div>
                             <div class="panel-body">
                                    <div class="group">
										<label class="col-sm-2 control-label">Promote Role</label>
										<div class="col-sm-4">
											<select class="form-control" name="promoteroleupgrade"
													id="promoteroleupgrade">
												<option value="">--Option--</option>	
											</select>
										</div>
									</div>
									<div class="group">
										<label class="col-sm-2 control-label">Now Rate</label>
										<div class="col-sm-4">
											<input type="text" class="form-control" name="nowrateupgrade"
													id="nowrateupgrade" />
										</div>
									</div>
									<br/><br/><br/>
									<div class="group">
										<label class="col-lg-2 control-label">Effective Date</label>  
                                        <!--指定 date标记-->  
                                        <div class="col-lg-4">
                                        <div class='input-group date' id='datetimepicker3'>  
                                            <input id="effectivedate" type='text' class="form-control" />  
                                            <span class="input-group-addon">  
                                              <span class="glyphicon glyphicon-calendar"></span>  
                                            </span>  
                                        </div>
                                        </div>  
									</div>
                                    <div class="group">
										<label class="col-sm-2 control-label">E-HR</label>
										<div class="col-sm-4">
											<input readonly="readonly" type="text" class="form-control" name="eHrupgrade"
													id="eHrupgrade" />
										</div>
									</div>
									<br/><br/><br/>
									<div class="group">
										<label class="col-sm-2 control-label">StaffId</label>
										<div class="col-sm-4">
											<input readonly="readonly" type="text" class="form-control" name="staffidupgrade"
													id="staffidupgrade" />
										</div>
									</div>
									<div class="group">
										<label class="col-sm-2 control-label">Staff Name</label>
										<div class="col-sm-4">
											<input readonly="readonly" type="text" class="form-control" name="staffnameupgrade"
													id="staffnameupgrade" />
										</div>
									</div>
									<br/><br/><br/>
									<div class="group">
										<label class="col-sm-2 control-label">LOB</label>
										<div class="col-sm-4">
											<input readonly="readonly" type="text" class="form-control" name="lobupgrade"
													id="lobupgrade" />
										</div>
									</div>
									<div class="group">
										<label class="col-sm-2 control-label">MSA Role</label>
										<div class="col-sm-4">
											<input readonly="readonly" type="text" class="form-control" name="msaroleupgrade"
													id="msaroleupgrade" />
										</div>
									</div>
									<br/><br/><br/>
									<div class="group">
										<label class="col-sm-2 control-label">Previous Rate</label>
										<div class="col-sm-4">
											<input readonly="readonly" type="text" class="form-control" name="previousrateupgrade"
													id="previousrateupgrade" />
										</div>
									</div>
									<br/><br/><br/>
									
									
                               </div>
                           </div>
						
						 
						 
			            </div>
						<div class="modal-footer">
							 <button id="cancel" type="button" class="btn btn-default" data-dismiss="modal">Cancel&nbsp;</button>
							 <button id="send" type="button" onclick="sendemail()" class="btn btn-primary">Update&nbsp;&nbsp;</button>
						</div>
					</div>
					
				</div>
				
	</div>
	<!--/.fluid-container-->

	<!-- external javascript -->
	<!-- CSS引用 -->
    <link rel="stylesheet" href="<%=path %>/extensioncss/bootstarp-table/bootstrap-table.css" type="text/css" />
    <link rel="stylesheet" href="<%=path %>/extensioncss/bootstarp-table/bootstrap-table.min.css" type="text/css" />     
  
    <!-- JS引用 -->
    <script src="<%=path %>/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="<%=path %>/extensionjs/bootstrap-table/dist/bootstrap-table.js" type="text/javascript"></script>
    <script src="<%=path %>/extensionjs/bootstrap-table/dist/bootstrap-table-locale-all.js" type="text/javascript"></script>
	<script src="<%=path %>/extensionjs/bootstrap-table/dist/locale/bootstrap-table-zh-CN.js"></script>

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

	<script type="text/javascript" src="<%=path %>/js/pmo/employeeInfo.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.zh-CN.js"></script>

</body>
</html>


