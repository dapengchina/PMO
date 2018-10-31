<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    String path = request.getContextPath();
			String url = request.getRequestURI();
			String currentPageName = "";
			if (url != null) {
				String[] strs = url.split("/");
				url = strs[strs.length - 1];
				currentPageName = url.substring(0, url.lastIndexOf('.'));
			}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>PMO</title>
<link rel="shortcut icon" href="<%=path%>/img/favicon.ico" />
<!-- The styles -->
<link href="<%=path%>/css/bootstrap-cerulean.min.css" rel="stylesheet" />
<link href="<%=path%>/css/charisma-app.css" rel="stylesheet" />
<link href='<%=path%>/bower_components/fullcalendar/dist/fullcalendar.css' rel='stylesheet' />
<link href='<%=path%>/bower_components/fullcalendar/dist/fullcalendar.print.css' rel='stylesheet' media='print' />
<link href='<%=path%>/bower_components/chosen/chosen.min.css' rel='stylesheet' />
<link href='<%=path%>/bower_components/colorbox/example3/colorbox.css' rel='stylesheet' />
<link href='<%=path%>/bower_components/responsive-tables/responsive-tables.css' rel='stylesheet' />
<link href='<%=path%>/bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css' rel='stylesheet' />
<link href='<%=path%>/bower_components/bootstrap-val/bootstrapValidator.css' rel='stylesheet' />
<link href='<%=path%>/css/jquery.noty.css' rel='stylesheet' />
<link href='<%=path%>/css/noty_theme_default.css' rel='stylesheet' />
<link href='<%=path%>/css/elfinder.min.css' rel='stylesheet' />
<link href='<%=path%>/css/elfinder.theme.css' rel='stylesheet' />
<link href='<%=path%>/css/jquery.iphone.toggle.css' rel='stylesheet' />
<link href='<%=path%>/css/uploadify.css' rel='stylesheet' />
<link href='<%=path%>/css/animate.min.css' rel='stylesheet' />
<link href='<%=path%>/css/bootstrap-datetimepicker.css' rel='stylesheet' />
<link href='<%=path%>/css/bootstrap-datetimepicker.min.css' rel='stylesheet' />
<style type="text/css">
.templateTable th, .templateTable td {
	text-align: center;
}
.table-thead-background  thead, th {
    background-color:#d9edf7;
    font-color:"white";
}
</style>
</head>
<script>
var path='<%=path%>';
</script>
<body>
	<c:import url="/service/manage/top" />

	<c:import url="/service/performance/performanceLeft">
		<c:param name="currentPageName" value="<%=currentPageName%>" />
	</c:import>
	<!-- content starts -->
	<div id="content" class="col-lg-10 col-sm-10">
		<div class="row">
			<div class="box col-md-12">
				<div class="box-inner">
					<input type="text" class="hidden" id="employeeId" value="${employeeId}"></input>
					<input type="text" class="hidden" id="type" value="${type}"></input>
					<input type="text" class="hidden" id="result" value="${result}"></input>
					<input type="text" class="hidden" id="resultId" value="${resultId}"></input>
					<div class="box-header well" data-original-title="">
						<h2 id="title_1" class="hidden">
							<i class="glyphicon glyphicon-user"></i> Management->绩效目标->审批
						</h2>
						<h2 id="title_2" class="hidden">
							<i class="glyphicon glyphicon-user"></i> Management->绩效考评->初评
						</h2>
						<h2 id="title_3" class="hidden">
							<i class="glyphicon glyphicon-user"></i> Management->绩效考评->审批
						</h2>
						<h2 id="title_4" class="hidden">
							<i class="glyphicon glyphicon-user"></i> Management->绩效考评->审批
						</h2>
						<h2 id="title_5" class="hidden">
							<i class="glyphicon glyphicon-user"></i> Management->绩效结果->绩效定稿
						</h2>
						<h2 id="title_6" class="hidden">
							<i class="glyphicon glyphicon-user"></i> Management->绩效结果->历史绩效
						</h2>
						<h2 id="title_7" class="hidden">
							<i class="glyphicon glyphicon-briefcase"></i> HRBP->绩效考评->集体评议
						</h2>
						<h2 id="title_8" class="hidden">
							<i class="glyphicon glyphicon-briefcase"></i> HRBP->绩效考评->审批
						</h2>
					</div>
					<div id="employeeInfo" class="box-content">
						<div class="panel panel-default">
							<div class="panel-body">
								<div class="row">
									<div class="form-group col-md-2">
										<div class="input-group">
											<span class="input-group-addon">姓名</span> <input class="form-control" type="text" name="userName" id="userName" readonly>
										</div>
									</div>
									<div class="form-group col-md-2">
										<div class="input-group">
											<span class="input-group-addon">工号</span> <input class="form-control" type="text" name="lob" id="lob" readonly>
										</div>
									</div>
									<div class="form-group col-md-2">
										<div class="input-group">
											<span class="input-group-addon">所在部门</span> <input class="form-control" type="text" name="bu" id="bu" readonly>
										</div>
									</div>
									<div class="form-group col-md-2">
										<div class="input-group">
											<span class="input-group-addon">职位</span> <input class="form-control" type="text" name="position" id="position" readonly>
										</div>
									</div>
									<div class="form-group col-md-2">
										<div class="input-group">
											<span class="input-group-addon">考核主管</span> <input class="form-control" type="text" name="supervisor" id="supervisor" readonly>
										</div>
									</div>
								</div>
							</div>
						</div>
						<table id="kpoTable" width="100%" class="templateTable"></table>
						<br />
						<table id="keyEventTable" width="100%" class="templateTable"></table>
						<br />
						<div>个人能力提升计划IDP (IDP不作为考核项目，是个人成长所做出的承诺)</div>
						<table id="planTable" width="100%" class="templateTable"></table>
						<br>
						<div class="panel panel-default" style="border:1px solid black">
							<div class="panel-body">
								<label class="col-lg-2" style="text-align:right; vertical-align:middle;display:inline-block;line-height:75px;">Self-Evaluation</label>
								<div class="col-lg-10">
									<textarea rows="3" style="border:1px solid black" class="form-control" id="selfAssessment" readonly></textarea>
								</div>
							</div>
						</div>
						<br>
						<div class="panel panel-default" style="border:1px solid black">
							<div class="panel-body">
								<label class="col-lg-2" style="text-align:right; vertical-align:middle;display:inline-block;line-height:75px;">Supervisor Feedback</label>
								<div class="col-lg-10">
									<textarea rows="3" style="border:1px solid black" class="form-control" id="feedback" readyonly></textarea>
								</div>
							</div>
						</div>
						<br>
						<div class="panel panel-default" style="border:1px solid black" id="ratingDiv">
							<div class="panel-body">
								<label class="col-lg-2" style="text-align:right; vertical-align:middle;display:inline-block;line-height:40px;">Rating</label>
								<div class="col-lg-10">
									<select class="form-control" id="rating" readonly>
										<option value="A">A</option>
										<option value="B+">B+</option>
										<option value="B">B</option>
										<option value="C">C</option>
										<option value="D">D</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div style="text-align:center;width:100%;"  id="button_1" class="hidden">
							<input type="button" value="Reject" name="Save" id="Save" href="#" class="button btn btn-primary" data-dismiss="modal" onclick="submit(-1);"
								style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin:auto ;">
							<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> 
							<input type="button" value="Approval" name="Edit" id="Edit" href="#" class="button btn btn-primary" data-dismiss="modal" onclick="submit(1);"
								style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin:auto ;">
						</div>
						<div style="text-align:center;width:100%;"  id="button_2" class="hidden">
							<input type="button" value="OK" name="Save" id="Save" href="#" class="button btn btn-primary" data-dismiss="modal" onclick="gradeSubmit();"
								style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin:auto ;">
							<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> <input type="button" value="Cancel" name="Edit" id="Edit" href="#" class="button btn btn-primary" data-dismiss="modal"
								onclick="gradeCancel();"
								style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin:auto ;">
						</div>
						<div style="text-align:center;width:100%;"  id="button_3" class="hidden">
							<input type="button" value="Back" name="Back" id="Back" href="#" class="button btn btn-primary" data-dismiss="modal" onclick="back();"
								style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin:auto ;">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- CSS引用 -->
	<link rel="stylesheet" href="<%=path%>/extensioncss/bootstarp-table/bootstrap-table.css" type="text/css" />
	<link rel="stylesheet" href="<%=path%>/extensioncss/bootstarp-table/bootstrap-table.min.css" type="text/css" />
	<link rel="stylesheet" href="<%=path%>/extensionjs/bootstrap3-editable/css/bootstrap-editable.css" type="text/css" />
	<link rel="stylesheet" href="<%=path%>/extensioncss/bootstarp-table/bootstrap-table-fixed-columns.css" type="text/css" />

	<!-- JS引用 -->
	<script src="<%=path%>/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="<%=path%>/extensionjs/bootstrap-table/dist/bootstrap-table.js" type="text/javascript"></script>
	<script src="<%=path%>/extensionjs/bootstrap-table/dist/bootstrap-table-locale-all.js" type="text/javascript"></script>
	<script src="<%=path%>/extensionjs/bootstrap-table/dist/bootstrap-table-fixed-columns.js" type="text/javascript"></script>
	<script src="<%=path%>/extensionjs/bootstrap-table/dist/locale/bootstrap-table-zh-CN.js"></script>
	<script src="<%=path%>/extensionjs/bootstrap3-editable/js/bootstrap-editable.js" type="text/javascript"></script>
	<script src="<%=path%>/extensionjs/bootstrap-table/dist/extensions/editable/bootstrap-table-editable.js" type="text/javascript"></script>


	<script src="<%=path%>/bower_components/bootstrap-val/bootstrapValidator.min.js"></script>
	<!-- library for cookie management -->
	<script src="<%=path%>/js/jquery.cookie.js"></script>
	<!-- calender plugin -->
	<script src='<%=path%>/bower_components/moment/min/moment.min.js'></script>
	<script src='<%=path%>/bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
	<!-- data table plugin -->
	<script src='<%=path%>/js/jquery.dataTables.min.js'></script>
	<!-- select or dropdown enhancer -->
	<script src="<%=path%>/bower_components/chosen/chosen.jquery.min.js"></script>
	<!-- plugin for gallery image view -->
	<script src="<%=path%>/bower_components/colorbox/jquery.colorbox-min.js"></script>
	<!-- notification plugin -->
	<script src="<%=path%>/js/jquery.noty.js"></script>
	<!-- library for making tables responsive -->
	<script src="<%=path%>/bower_components/responsive-tables/responsive-tables.js"></script>
	<!-- tour plugin -->
	<script src="<%=path%>/bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
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

	<script type="text/javascript" src="<%=path%>/js/pmo/performance.js"></script>
	<script type="text/javascript" src="<%=path%>/js/pmo/performanceDetail.js"></script>
</body>
</html>


