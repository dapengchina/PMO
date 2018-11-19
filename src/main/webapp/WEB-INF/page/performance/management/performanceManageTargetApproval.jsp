<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String url = request.getRequestURI();
	String currentPageName = "";
	if (url != null) {
		String[] strs = url.split("/");
		url = strs[strs.length-1];
		currentPageName = url.substring(0, url.lastIndexOf('.'));
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8"/>
<title>PMO</title>
<link rel="shortcut icon" href="<%=path %>/img/favicon.ico"/>
<!-- The styles -->
<link href="<%=path %>/css/bootstrap-cerulean.min.css" rel="stylesheet"/>
<link href="<%=path %>/css/charisma-app.css" rel="stylesheet"/>
<link href='<%=path %>/bower_components/fullcalendar/dist/fullcalendar.css' rel='stylesheet'/>
<link href='<%=path %>/bower_components/fullcalendar/dist/fullcalendar.print.css' rel='stylesheet' media='print'/>
<link href='<%=path %>/bower_components/chosen/chosen.min.css' rel='stylesheet'/>
<link href='<%=path %>/bower_components/colorbox/example3/colorbox.css' rel='stylesheet'/>
<link href='<%=path %>/bower_components/responsive-tables/responsive-tables.css' rel='stylesheet'/>
<link href='<%=path %>/bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css' rel='stylesheet'/>
<link href='<%=path %>/bower_components/bootstrap-val/bootstrapValidator.css' rel='stylesheet'/>
<link href='<%=path %>/css/jquery.noty.css' rel='stylesheet'/>
<link href='<%=path %>/css/noty_theme_default.css' rel='stylesheet'/>
<link href='<%=path %>/css/elfinder.min.css' rel='stylesheet'/>
<link href='<%=path %>/css/elfinder.theme.css' rel='stylesheet'/>
<link href='<%=path %>/css/jquery.iphone.toggle.css' rel='stylesheet'/>
<link href='<%=path %>/css/uploadify.css' rel='stylesheet'/>
<link href='<%=path %>/css/animate.min.css' rel='stylesheet'/>
<link href='<%=path%>/css/bootstrap-datetimepicker.css' rel='stylesheet'/>
<link href='<%=path%>/css/bootstrap-datetimepicker.min.css' rel='stylesheet'/>
<style type="text/css">
.templateTable thead, .templateTable td { 
	text-align: center;
}
</style>
<style>
.table-thead-background  thead, th {
	background-color: #d9edf7;
}

a.link {
	position: relative;
}

div.submitTips {
	border: 1px solid #9a9898;
    border-radius: 5px;
    padding: 10px;
    background-color: white;
    position: absolute;
    width: 170px;
    line-height: 20px;
    word-wrap: break-word;
    margin-top: -30px;
    margin-left: 12px;
    z-index: 1000;
    display: none;
}
div.backboneTips{
	border: 1px solid #9a9898;
    border-radius: 5px;
    padding: 10px;
    background-color: white;
    position: absolute;
    width: 170px;
    line-height: 20px;
    word-wrap: break-word;
    margin-top: -30px;
    margin-left: 12px;
    z-index: 1000;
    display: none;
}
div .stateTips{
	border: 1px solid #9a9898;
    border-radius: 5px;
    padding: 10px;
    background-color: white;
    position: absolute;
    width: 170px;
    line-height: 20px;
    word-wrap: break-word;
    margin-top: -30px;
    margin-left: 12px;
    z-index: 1000;
    display: none;
}
</style>
</head>
<script>
	var path = '<%=path%>';
</script>
<body>
	<c:import url="/service/manage/top" />

	<c:import url="/service/performance/performanceLeft">
	    <c:param name="currentPageName" value="<%=currentPageName%>"/>
	</c:import> 


<!-- middle content start -->
			<div id="content" class="col-lg-10 col-sm-10">

				<div class="row" >	
				<div class="box col-md-12">
						<div class="box-inner" >
							<div class="box-header well" data-original-title="" >
								<h2>
									<i class="glyphicon glyphicon-user"></i>  Management-绩效目标-审批   
								</h2>
							</div>

							<div id="employeeInfo" class="box-content">					
	
							<!-- result box start -->
									<table id="table3" border="1" width="100%" >
										<tr style="background-color:#00688B">
										 <td ><font color="white"> 绩效目标审批</font>	 </td>
										</tr>
									   <tr>
										 <td> <table id="manageTargetApprovalList" class="table table-thead-background"></table> </td>									
									   </tr>
									</table>
									<span>&nbsp;</span>
									<div class="form-group">
												<div style="text-align:center;width:100%;">
												<input type="button" value="Select All" name="Save" id="Save" href="#"
												class="button btn btn-primary" data-dismiss="modal"
												onclick=""
												style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin:auto ;">
												<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
												<input type="button" value="Download" name="Edit" id="Edit" href="#"
												class="button btn btn-primary" data-dismiss="modal"
												onclick="download();"
												style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin:auto ;">
												</div>
										</div>

						    </div>
						 </div>
							<!-- result box end -->

	                           </div>  
							</div>
						</div>
	<!-- middle content end -->
	<div id="submitFilterDiv" class="hidden">
		<span style="color:#006633;font-size: 16px;">Submited</span>
		<div style="border-bottom:1px solid #0e5b87;margin: 10px 0px 20px 0px;"></div>
		<div class="checkbox-custom checkbox-default" style="margin-bottom: 10px;padding-left: 15px;">
			<input type="checkbox" id="yes" name="submitCheckbox" value="1" checked> <label for="yes">Yes</label>
		</div>
		<div class="checkbox-custom checkbox-default" style="margin-bottom: 10px;padding-left: 15px;">
			<input type="checkbox" id="no" name="submitCheckbox" value="0" checked> <label for="no">No</label>
		</div>
		<button type="button" class="btn btn-default" onClick="search(1)" style="margin-right:5px;">&nbsp;&nbsp;OK&nbsp;&nbsp;</button>
		<button type="button" class="btn btn-default" onClick="cancelFilter(1)">Cancel</button>
	</div>
	<div id="backboneFilterDiv" class="hidden">
		<span style="color:#006633;font-size: 16px;">Backbone</span>
		<div style="border-bottom:1px solid #0e5b87;margin: 10px 0px 20px 0px;"></div>
		<div class="checkbox-custom checkbox-default" style="margin-bottom: 10px;padding-left: 15px;">
			<input type="checkbox" id="yes" name="backboneCheckbox" value="1" checked> <label for="yes">Yes</label>
		</div>
		<div class="checkbox-custom checkbox-default" style="margin-bottom: 10px;padding-left: 15px;">
			<input type="checkbox" id="no" name="backboneCheckbox" value="0" checked> <label for="no">No</label>
		</div>
		<button type="button" class="btn btn-default" onClick="search(2)" style="margin-right:5px;">&nbsp;&nbsp;OK&nbsp;&nbsp;</button>
		<button type="button" class="btn btn-default" onClick="cancelFilter(2)">Cancel</button>
	</div>
	<div id="stateFilterDiv" class="hidden">
		<span style="color:#006633;font-size: 16px;">State</span>
		<div style="border-bottom:1px solid #0e5b87;margin: 10px 0px 20px 0px;"></div>
		<div class="checkbox-custom checkbox-default" style="margin-bottom: 10px;padding-left: 15px;">
			<input type="checkbox" id="yes" name="stateCheckbox" value="-0" checked> <label for="yes">未审批</label>
		</div>
		<div class="checkbox-custom checkbox-default" style="margin-bottom: 10px;padding-left: 15px;">
			<input type="checkbox" id="no" name="stateCheckbox" value="2" checked> <label for="no">审批通过</label>
		</div>
		<div class="checkbox-custom checkbox-default" style="margin-bottom: 10px;padding-left: 15px;">
			<input type="checkbox" id="no" name="stateCheckbox" value="3" checked> <label for="no">审批未通过</label>
		</div>
		<button type="button" class="btn btn-default" onClick="search(3)" style="margin-right:5px;">&nbsp;&nbsp;OK&nbsp;&nbsp;</button>
		<button type="button" class="btn btn-default" onClick="cancelFilter(3)">Cancel</button>
	</div>

	<div class="ch-container ">
		<c:import url="/service/manage/footer" />
	</div>

	<!-- CSS引用 -->
    <link rel="stylesheet" href="<%=path%>/extensioncss/bootstarp-table/bootstrap-table.css" type="text/css" />
    <link rel="stylesheet" href="<%=path %>/extensioncss/bootstarp-table/bootstrap-table.min.css" type="text/css" />
	<link rel="stylesheet" href="<%=path %>/extensionjs/bootstrap3-editable/css/bootstrap-editable.css" type="text/css" />
	<link rel="stylesheet" href="<%=path %>/extensioncss/bootstarp-table/bootstrap-table-fixed-columns.css" type="text/css" />      
  
    <!-- JS引用 -->
    <script src="<%=path %>/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="<%=path %>/extensionjs/bootstrap-table/dist/bootstrap-table.js" type="text/javascript"></script>
    <script src="<%=path %>/extensionjs/bootstrap-table/dist/bootstrap-table-locale-all.js" type="text/javascript"></script>
    <script src="<%=path %>/extensionjs/bootstrap-table/dist/bootstrap-table-fixed-columns.js" type="text/javascript"></script>
	<script src="<%=path %>/extensionjs/bootstrap-table/dist/locale/bootstrap-table-zh-CN.js"></script>
    <script src="<%=path %>/extensionjs/bootstrap3-editable/js/bootstrap-editable.js" type="text/javascript"></script>
    <script src="<%=path %>/extensionjs/bootstrap-table/dist/extensions/editable/bootstrap-table-editable.js" type="text/javascript"></script>
    
   
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
	
    <script type="text/javascript" src="<%=path %>/js/pmo/performance.js"></script>
    <script type="text/javascript" src="<%=path %>/js/pmo/performance/management/performanceManageTargetApproval.js"></script>
	
</body>
</html>


