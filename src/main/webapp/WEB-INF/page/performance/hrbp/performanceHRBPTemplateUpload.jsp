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
.templateTable thead, .templateTable td {
	text-align: center;
}
.fileInput{
        height:256px;
        font-size: 300px;
        position:absolute;
        right:0;
        top:0;
        opacity: 0;
        filter:alpha(opacity=0);
        cursor:pointer;
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


	<!-- middle content start -->
	<div id="content" class="col-lg-10 col-sm-10">

		<div class="row">
			<div class="box col-md-12">
				<div class="box-inner">
					<div class="box-header well" data-original-title="">
						<h2>
							<i class="glyphicon glyphicon-briefcase"></i> HRBP-Template-Data Upload
						</h2>
					</div>
					<div class="box-content">
						<table id="table1" border="1" width="100%">
							<tr style="background-color:#00688B">
								<td><font color="white"> Data Upload</font></td>
							</tr>
							<tr>
								<td class="text-center">
									<table id="table2" border="1" width="60%" class="templateTable" style="margin:0 auto;">
										<thead>
											<tr style="background-color:#d9edf7">
												<td>Name</td>
												<td>Operate</td>
											</tr>
										</thead>
										<c:forEach items="${list}" var="item" varStatus="status">
											<tr>
												<td >${item.name}</td>
												<td>
													<input type="button" value="Upload" class="button btn btn-primary" data-dismiss="modal"
													onclick="showModal(${item.id})"
													style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin:auto ;">
												</td>
											</tr>
										</c:forEach>
									</table>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
		<!-- 上传文件模态框（Modal） -->
		<div class="modal fade" id="uploadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header" style="background-color:#00688B">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <font color="white"> Data Upload</font>
		            </div>
		            <div class="modal-body" style="height: 200px;text-align: center;padding: 75px 20px;">
		            <form id="uploadForm">
			            <span>Choose a file</span>
			            <input type="text" id="templateUploadInput" class="form-control" style="display: inline;width: 300px;"></input>
			            <div class="file-container" style="display:inline-block;position:relative;overflow: hidden;vertical-align:middle">
					        <button class="btn btn-default fileinput-button" type="button">Browse...</button>
					        <input type="file" id="upload" name="upload" onchange="loadFile(this.files[0])" style="position:absolute;top:0;left:0;font-size:34px; opacity:0">
					    </div>
		            </form>
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-primary" onclick="upload()">Upload</button>
		                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
		            </div>
		        </div><!-- /.modal-content -->
		    </div><!-- /.modal -->
		</div>
	</div>



	<!-- middle content end -->





	<div class="ch-container ">
		<c:import url="/service/manage/footer" />
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
	<script type="text/javascript" src="<%=path %>/js/pmo/ajaxfileupload.js"></script>

	<script type="text/javascript" src="<%=path%>/js/pmo/performance.js"></script>
	<script type="text/javascript" src="<%=path%>/js/pmo/performance/hrbp/performanceHRBPDataUpload.js"></script>

</body>
</html>


