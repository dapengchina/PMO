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
</head>
<script>
var path='<%=path%>';
</script>
<body>
	<c:import url="/service/manage/top" />

	<c:import url="/service/performance/performanceLeft" />


<!-- middle content start -->
			<div id="content" class="col-lg-10 col-sm-10">

				<div class="row" >	
				<div class="box col-md-12">
						<div class="box-inner" >
							<div class="box-header well" data-original-title="" >
								<h2>
									<i class="glyphicon glyphicon-user"></i>  Management->绩效目标->审批
								</h2>
							</div>

							<div id="employeeInfo" class="box-content">					
	
							<!-- result box start -->
									<table id="table3" border="1" width="100%" >
										<tr style="background-color:#00688B">
										 <td colspan="10"><font color="white"> 绩效目标审批</font>	 </td>
										</tr>
										<tr style="background-color:#d9edf7">
												<th><input type="checkbox"/></th>
												<th>SL</th>
												<th>E-HR</th>
												<th>Employee Name</th>
												<th>MSA Role</th>
												<th>Skill/Technology</th>
												<th>是否提交</th>
												<th>业务先锋</th>
												<th>审批状态</th>
												<th>Detail</th>
									   </tr>
									   <tr>
										 <td><input type="checkbox"/></td>
										 <td> 1	 </td>
										 <td> 0090127655	 </td>
										 <td> XXX  </td>
										 <td> Senior </td>
										 <td> HR </td>
										 <td> 是  </td>
										 <td> 是 </td>
										 <td> 已审批 </td>
										 <td> <a href='performanceManageTargetApprovalDetail.html' class='btn btn-info btn-small'><i class="glyphicon glyphicon-edit"></i></a> </td>
									   </tr>
									   <tr>
										 <td><input type="checkbox"/></td>
										 <td> 2	 </td>
										 <td> 0090127656	 </td>
										 <td> XXX  </td>
										 <td> Junior </td>
										 <td> HTML </td>
										 <td> 是  </td>
										 <td> 是 </td>
										 <td> 未审批 </td>
										 <td> <a href='performanceManageTargetApprovalDetail.html' class='btn btn-info btn-small'><i class="glyphicon glyphicon-edit"></i></a> </td>
									   </tr>
									   <tr>
										 <td><input type="checkbox"/></td>
										 <td> 3	 </td>
										 <td> 0090127657	 </td>
										 <td> XXX  </td>
										 <td> Junior Tester </td>
										 <td> BA </td>
										 <td> 是  </td>
										 <td> 是 </td>
										 <td> 已审批 </td>
										 <td> <a href='performanceManageTargetApprovalDetail.html' class='btn btn-info btn-small'><i class="glyphicon glyphicon-edit"></i></a> </td>
									   </tr>
									   <tr>
										 <td><input type="checkbox"/></td>
										 <td> 4	 </td>
										 <td> 0090127658	 </td>
										 <td> XXX  </td>
										 <td> AWS </td>
										 <td> HR </td>
										 <td> 否  </td>
										 <td> 是 </td>
										 <td> 已审批 </td>
										 <td> <a href='performanceManageTargetApprovalDetail.html' class='btn btn-info btn-small'><i class="glyphicon glyphicon-edit"></i></a> </td>
									   </tr>
									   <tr>
										 <td><input type="checkbox"/></td>
										 <td> 5	 </td>
										 <td> 0090127659	 </td>
										 <td> XXX  </td>
										 <td> Junior Tester </td>
										 <td> C </td>
										 <td> 否  </td>
										 <td> 是 </td>
										 <td> 已审批 </td>
										 <td> <a href='performanceManageTargetApprovalDetail.html' class='btn btn-info btn-small'><i class="glyphicon glyphicon-edit"></i></a> </td>
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
												onclick=""
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
	
	
<div class="ch-container ">
	<c:import url="/service/manage/footer" />
</div>

	<!-- CSS引用 -->
    <link rel="stylesheet" href="<%=path %>/extensioncss/bootstarp-table/bootstrap-table.css" type="text/css" />
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
	
    <script type="text/javascript" src="<%=path %>/js/pmo/offlineOpe.js"></script>
    <script type="text/javascript" src="<%=path %>/js/pmo/performance.js"></script>
	
	
</body>
</html>


