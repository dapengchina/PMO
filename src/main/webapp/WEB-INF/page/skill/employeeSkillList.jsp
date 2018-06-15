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
<meta charset="utf-8" />
<title>PMO</title>
<link rel="shortcut icon" href="<%=path %>/img/favicon.ico" />
<!-- The styles -->
<link href="<%=path %>/css/bootstrap-cerulean.min.css" rel="stylesheet" />
<link href="<%=path %>/css/charisma-app.css" rel="stylesheet" />
<link
	href='<%=path %>/bower_components/fullcalendar/dist/fullcalendar.css'
	rel='stylesheet' />
<link
	href='<%=path %>/bower_components/fullcalendar/dist/fullcalendar.print.css'
	rel='stylesheet' media='print' />
<link href='<%=path %>/bower_components/chosen/chosen.min.css'
	rel='stylesheet' />
<link href='<%=path %>/bower_components/colorbox/example3/colorbox.css'
	rel='stylesheet' />
<link
	href='<%=path %>/bower_components/responsive-tables/responsive-tables.css'
	rel='stylesheet' />
<link
	href='<%=path %>/bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css'
	rel='stylesheet' />
<link
	href='<%=path %>/bower_components/bootstrap-val/bootstrapValidator.css'
	rel='stylesheet' />
<link href='<%=path %>/css/jquery.noty.css' rel='stylesheet' />
<link href='<%=path %>/css/noty_theme_default.css' rel='stylesheet' />
<link href='<%=path %>/css/elfinder.min.css' rel='stylesheet' />
<link href='<%=path %>/css/elfinder.theme.css' rel='stylesheet' />
<link href='<%=path %>/css/jquery.iphone.toggle.css' rel='stylesheet' />
<link href='<%=path %>/css/uploadify.css' rel='stylesheet' />
<link href='<%=path %>/css/animate.min.css' rel='stylesheet' />
<link href='<%=path%>/css/bootstrap-datetimepicker.css' rel='stylesheet' />
<link href='<%=path%>/css/bootstrap-datetimepicker.min.css' rel='stylesheet' />
<style type="text/css">
.ssf {
	font-size: 15px;
	font-weight: bold;
	color: red;
}
</style>
</head>
<script>
var path='<%=path%>';
</script>
<body>
	<c:import url="/service/manage/top" />
	<div class="ch-container">
		<div class="row">
			<c:import url="/service/manage/left" />
			<div id="content" class="col-lg-10 col-sm-10">
				<!-- content starts -->
				<div class="row">
					<div class="box col-md-12">
						<div class="box-inner">
							<div class="box-header well" data-original-title="">
								<h2>
									<i class="glyphicon glyphicon-user"></i> Information
									Maintenance
								</h2>
							</div>
							<div id="employeeInfo" class="box-content">
								<div class="panel panel-default">
<!-- 									<div class="panel-heading">Query Conditions</div> -->
									<div class="panel-body">
										<div class="group">
											<label class="col-sm-2 control-label">E-HR</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="eHr"
													id="eHr" />
											</div>
										</div>
										<div class="group">
											<label class="col-sm-2 control-label">Staff ID</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="staffId"
													id="staffId" />
											</div>
										</div>
										</br></br></br>
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
										</br></br></br>
										<div class="group">
										<label class="col-lg-2 control-label">Role</label>
										<div class="col-lg-4">
											<select class="form-control" name="role" data-bv-notempty
												data-bv-notempty-message="请选择角色" id="role" data-bv-group=".group">
												<option value="">-- Option--</option>
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
										<label class="col-lg-2 control-label">Skill</label>
										<div class="col-lg-4">
											<select class="form-control" name="paramName" data-bv-notempty
												data-bv-notempty-message="请选择角色" id="paramName" data-bv-group=".group">
												<option value="">-- Option--</option>
											</select>
										</div>
										</div>
										<div class="group">
										<label class="col-lg-2 control-label">Main Skill</label>
										<div class="col-lg-4">
											<input type="checkbox" CHECKED  value="1" name="mainAbility" id="mainAbility" onclick="return  false;" disabled>
										</div>
										</div>
										</br></br></br>
										<div class="group">
											<label class="col-sm-2 control-label">Official Certification</label>
											<div class="col-sm-4">
												<input type="checkbox" value="" name="officialAccreditation" id="officialAccreditation">
											</div>
										</div>
										<div class="group">
											<label class="col-sm-2 control-label">work Experience(Years)</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="workExperience"	id="workExperience" />
											</div>
										</div>
										</br></br></br>
										<div class="group">
											<label class="col-lg-2 control-label">Capability Level</label>
											<div class="col-lg-4">
												<select class="form-control" name="capabilityLevel" data-bv-notempty
													data-bv-notempty-message="请选择Level" id="capabilityLevel" data-bv-group=".group">
													<option value="">-- Option--</option>
												</select>
											</div>
										</div>
										</br></br></br>
										<div class="group">
											<div class="col-sm-4">
												<button onClick="search()" type="button"
													style="margin-left: 50px" id="btn_query"
													class="btn btn-primary">Search</button>
											</div>
										</div>
									</div>
									
<!-- 									<div class="input-group spinner" data-trigger="spinner"> -->
<!-- 									    <input type="text" class="form-control text-center" value="1" data-min="-10" data-max="10" data-step="2" data-rule="quantity"> -->
<!-- 									    <span class="input-group-addon"> -->
<!-- 									        <a href="javascript:;" class="spin-up" data-spin="up"><i class="fa fa-caret-up"></i></a> -->
<!-- 									        <a href="javascript:;" class="spin-down" data-spin="down"><i class="fa fa-caret-down"></i></a> -->
<!-- 									    </span> -->
<!-- 									</div> -->
									
<!-- 								</div> -->
								<!-- 数据列表  -->
								<table id="skillList"></table>
<!-- 								</br> -->
								<div class="group">
										<div class="col-sm-4">
											<button onClick="toBatch()" type="button"
													style="margin-left: 50px" id="btn_batchEdit"
													class="btn btn-primary">Batch Edit</button>
										</div>
								</div>
								</br></br></br>
							</div>
							
							<form action="" id="editForm" method="post" target="_self">
									<input id="id" name="id" type="hidden" />
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id="skillDetailDiv">
		<button class="btn btn-primary btn-md" data-toggle="modal" data-target="#myModal" id="detailBtn" style="display: none;"></button>
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog" style="width: 70%">
		        <div class="modal-content" >
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		                <h4 class="modal-title" id="myModalLabel"></h4>
		            </div>
		            <div class="modal-body">
		            	<div class="panel-group" id="accordion">
						    <div class="panel panel-default" style="display: none;">
						        <div class="panel-heading">
						            <h4 class="panel-title">
						                <a data-toggle="collapse" data-parent="#accordion" href="#collapse" id="accordionLink">
						                </a>
						            </h4>
						        </div>
						        <div id="collapse" class="panel-collapse collapse in">
									<table 
										class="table table-bordered">
										<thead>
											<tr>
												<th>Skill</th>
												<th>Level</th>
												<th>Main Skill</th>
												<th>Official Certification</th>
												<th>Certification Name</th>
												<th>work Experience(Years)</th>
											</tr>
										</thead>
										<tbody></tbody>
									</table>
						        </div>
						    </div>
		            	</div>
			            <div class="modal-footer">
							</br></br></br>
							<div class="group">
								<div class="col-sm-4">
									<button onClick="update()" type="button"
										id="updateSkills" 
										style="margin-left: 200px"
										class="btn btn-primary">Update</button>
								</div>
								<div class="col-sm-4">
									<button onClick="batchUpdate()" type="button"
										id="batchUpdate" 
										style="margin-left: 50px"
										class="btn btn-primary">batch Update</button>
								</div>
								<button type="button" class="btn btn-primary" data-dismiss="modal">关闭
								</button>
							</div>
			            </div>
		        	</div><!-- /.modal-content -->
		    	</div><!-- /.modal-dialog -->
			</div>
		</div>
	</div>
	<!-- /.modal -->
	
	<c:import url="/service/manage/footer" />

	<!-- CSS引用 -->
	<link rel="stylesheet"
		href="<%=path %>/extensioncss/bootstarp-table/bootstrap-table.css"
		type="text/css" />
	<link rel="stylesheet"
		href="<%=path %>/extensioncss/bootstarp-table/bootstrap-table.min.css"
		type="text/css" />
	<link rel="stylesheet"
		href="<%=path %>/extensionjs/bootstrap3-editable/css/bootstrap-editable.css"
		type="text/css" />
	<link rel="stylesheet"
		href="<%=path %>/extensioncss/bootstarp-table/bootstrap-table-fixed-columns.css"
		type="text/css" />

	<!-- JS引用 -->
	<script
		src="<%=path %>/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script
		src="<%=path %>/extensionjs/bootstrap-table/dist/bootstrap-table.js"
		type="text/javascript"></script>
	<script
		src="<%=path %>/extensionjs/bootstrap-table/dist/bootstrap-table-locale-all.js"
		type="text/javascript"></script>
	<script
		src="<%=path %>/extensionjs/bootstrap-table/dist/bootstrap-table-fixed-columns.js"
		type="text/javascript"></script>
	<script
		src="<%=path %>/extensionjs/bootstrap-table/dist/locale/bootstrap-table-zh-CN.js"></script>
	<script
		src="<%=path %>/extensionjs/bootstrap3-editable/js/bootstrap-editable.js"
		type="text/javascript"></script>
	<script
		src="<%=path %>/extensionjs/bootstrap-table/dist/extensions/editable/bootstrap-table-editable.js"
		type="text/javascript"></script>


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

	<script type="text/javascript" src="<%=path %>/js/pmo/employeeSkillList.js"></script>

</body>
</html>


