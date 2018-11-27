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
<style type="text/css">
	a.link{position:relative;}
	a.link div.tips{
						border:1px solid black;
						padding:10px;
						background-color:#D7E7FC;
						color:red;
						position:absolute;
						width:300px;
  					    line-height:20px;
						word-wrap:break-word ;
						display:none;
	}
	a.link:hover{}
	a.link:hover div.tips{
						display:inline;
					}
</style>
</head>
<script>
var path='<%=path%>';
</script>
<body>
	<c:import url="/service/manage/top" />
	<c:import url="/service/performance/performanceLeft">
	    <c:param name="currentPageName" value="<%=currentPageName%>"/>
	</c:import>
    <!-- middle content start -->
	<div id="content" class="col-lg-10 col-sm-10">
		<!-- content starts -->
		<div class="box col-md-12">
			<div class="box-inner">
				<div class="box-header well" data-original-title="">
					<h2>
						<i class="glyphicon glyphicon-user"></i>  LOB-绩效结果-历史绩效
					</h2>
				</div>
				<div id="employeeInfo" class="box-content">
				<!-- search box start -->
				<div class="panel panel-default" id="searchPanel">
					<div class="panel-heading" style="background-color:#00688B">
						<font color="white"> Search for Records</font>
					</div>
                    <div class="panel-body">
						<div class="group">
							<label class="col-sm-2 control-label">E-HR</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="eHr" name="eHr" value="" />
							</div>
						</div>
						<div class="group">
							<label class="col-sm-2 control-label">Employee Name</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="staffName" name="staffName" value="" />
							</div>
						</div>
						</br></br></br>
						<div class="group">
							<label class="col-sm-2 control-label">BU</label>
							<div class="col-sm-4">
								<input readonly="readonly" type="text" class="form-control" id="bu" name="bu" value="${BU }" />
							</div>
						</div>
						<div class="group">
							<label class="col-sm-2 control-label">DU</label>
							<div class="col-sm-4">
								<input readonly="readonly" type="text" class="form-control" id="du" name="du" value="${DU }" />
							</div>
						</div>
						</br></br></br>
                        <div class="group">
							<label class="col-lg-2 control-label">Period Between</label>  
                            <div class="col-lg-2">
                               <div class='input-group date' id='datetimepicker1'>  
                                  <input id="startYear" name="startYear" type='text' class="form-control" value="2010" />  
                                  <span class="input-group-addon">  
                                     <span class="glyphicon glyphicon-calendar"></span>  
                                  </span>  
                               </div> 
                             </div> 
							 <div class="col-lg-2">
                                <select class="form-control" id="startQuarter" name="startQuarter">
									<option value="1">Q1</option>
									<option value="2">Q2</option>
									<option value="3">Q3</option>
									<option value="4">Q4</option>
								</select>
                             </div> 
						</div>
                        <div class="group">
							 <label class="col-lg-2 control-label">To</label>  
                             <div class="col-lg-2">
                                 <div class='input-group date' id='datetimepicker2'>  
                                     <input type='text' name="endYear" class="form-control" id="endYear"  value="2018" />  
                                     <span class="input-group-addon">  
                                        <span class="glyphicon glyphicon-calendar"></span>  
                                     </span>  
                                 </div> 
                             </div> 
							 <div class="col-lg-2">
                                  <select class="form-control"  id="endQuarter" name="endQuarter">
										<option value="1">Q1</option>
										<option value="2">Q2</option>
										<option value="3">Q3</option>
										<option value="4" selected>Q4</option>
								  </select>
                              </div> 
						</div>
						</br></br></br></br>
						<div class="form-group" style="border-top: solid 1px #ddd;padding-top: 20px;margin-bottom:0px;">
							<div style="text-align:left;width:50%;">
								<input type="button" value="Search"
										name="searchBtn" id="searchBtn" onClick="search();"
										class="button btn btn-primary" data-dismiss="modal"
										style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 50px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px;">
								<input type="button" value="Clear" href="#" onClick="clearParams();"
										class="button btn btn-primary" data-dismiss="modal"
										style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px;">
							</div>
						</div>
					</div>

		
                </div>
                <table id="manageResultHistoryQueryList" class="table table-thead-background"></table>
				<span>&nbsp;</span>
				<br/>
				
				<div class="form-group" >
					<div style="text-align:center;width:100%;">
						<input type="button" value="Select All"
											name="searchBtn" id="searchBtn" href="#"
											class="button btn btn-primary" data-dismiss="modal"
											style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 50px 0px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px;">
						<input type="button" value="Export" href="#" onClick="historyResultExport();"
											class="button btn btn-primary" data-dismiss="modal"
											style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 0px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px;">
					</div>
				</div>
				</div>
            </div>
			<!-- result box end -->
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
	
		<script src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/pmo/performance.js"></script>
	<script type="text/javascript" src="<%=path %>/js/pmo/performance/lob/performanceLobHistory.js"></script>
	    
</body>
</html>


