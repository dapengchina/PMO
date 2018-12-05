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
				<div class="row" >					
				<div class="box col-md-12">
						<div class="box-inner">
							<div class="box-header well" data-original-title="">
								<h2>
									<i class="glyphicon glyphicon-user"></i>  Employee-绩效结果-详情
								</h2>
							</div>
							<div id="employeeInfo" class="box-content">
							
							<div class="panel panel-default">	
                              <div class="panel-body">

											<div class="row">
							             	<div class="form-group col-md-2">
							                    <div class="input-group">
							                        <span class="input-group-addon">姓名</span>
							                        <input readonly="readonly" class="form-control" type="text"
							                        name="staffname" id="staffname">
							                    </div>
							                </div>

							                <div class="form-group col-md-2">
							                    <div class="input-group">
							                        <span class="input-group-addon">工号</span>
							                        <input style="width:110px;" readonly="readonly" class="form-control" type="text" 
							                        name="ehr" id="ehr">
							                    </div>
							                </div>
							                <div style="margin-left:13px;" class="form-group col-md-2">
							                    <div class="input-group">
							                        <span class="input-group-addon">所在部门</span>
							                        <input style="width:150px;" readonly="readonly" class="form-control" type="text" 
							                        name="du" id="du">
							                    </div>
							                </div>
							                <div style="margin-left:83px;" class="form-group col-md-2">
							                    <div class="input-group">
							                        <span class="input-group-addon">职位</span>
							                        <input style="width:100px;" readonly="readonly" class="form-control" type="text"
							                        name="position" id="position">
							                    </div>
							                </div>
											<div class="form-group col-md-2">
							                    <div class="input-group">
							                        <span class="input-group-addon">考核主管</span>
							                        <input readonly="readonly" class="form-control" type="text" 
							                        name="assessmentSupervisor" id="assessmentSupervisor">
							                    </div>
							                </div>
							            </div>            							

                               </div>
                             </div>
                             

                             <!-- 模板表格 -->
                             <table id="table1" border="1" width="100%" class="templateTable">
							   <thead>
                               <tr style="background-color:#d9edf7">
                                 <td>分类</td>
                                 <td>序号</td>
                                 <td>重点工作内容</td>
                                 <td>权重</td>
                                 <td>阶段目标</td>
                                 <td>关键措施</td>
                                 <td>测评部门考核</td>
                               </tr>
							   </thead>
                               <tr>
                                 <td colspan="7"><a href='javascript:void(0);' class='btn btn-info btn-sm' onclick="addTr1('table1', -1, 6);")><span class="glyphicon glyphicon-plus"></span> Plus</a>
								 </td>
                               </tr>
                             </table>

								<br/>

                             <table id="table2" border="1" width="100%" class="templateTable">
                               
                               <tr>
                                 <td colspan="7"><a href='javascript:void(0);' class='btn btn-info btn-sm' onclick="addTr2('table2', -1, 7);")><span class="glyphicon glyphicon-plus"></span> Plus</a>
								 </td>
                               </tr>
							 </table>

								<br/>

							 <div>个人能力提升计划IDP (IDP不作为考核项目，是个人成长所做出的承诺)</div>
                             <table id="table3" border="1" width="100%" class="templateTable">
							   <thead>
                               <tr  style="background-color:#d9edf7">
                                 <td >
								 待提高或发展能力/经验 <br> (识别关键项)
								 </td>
                                 <td>
								 实施活动及衡量标准 <br> (活动要具体、个性化，可衡量)								 
								 </td>
                                 <td>
								 所需支持人 <br> (导师、主管等相关人)
								 </td>
                                 <td> 
								 计划完成时间 <br> (计划要可执行)
								 </td>
                               </tr>
							   </thead>
							    <tr>
                                 <td colspan="4"><a href='javascript:void(0);' class='btn btn-info btn-sm' onclick="addTr2('table3', -1, 4);")><span class="glyphicon glyphicon-plus"></span> Plus</a>
								 </td>
                               </tr>
							 </table>

							<br>
							
							<div class="panel panel-default"  style="border:1px solid black">	
								 <div class="panel-body" >										
										<label class="col-lg-2" style="text-align:right; vertical-align:middle;display:inline-block;line-height:75px;">PerSettingComments</label>
										<div class="col-lg-10">
											<textarea readonly="readonly" rows="3" style="border:1px solid black" class="form-control" id="perSettingComments" ></textarea>
										</div>
								 </div>
							</div>
							
							<div class="panel panel-default"  style="border:1px solid black">	
								 <div class="panel-body" >										
										<label class="col-lg-2" style="text-align:right; vertical-align:middle;display:inline-block;line-height:75px;">SelfEvaluation</label>
										<div class="col-lg-10">
											<textarea readonly="readonly" rows="3" style="border:1px solid black" class="form-control" id="selfEvaluation" ></textarea>
										</div>
								 </div>
							</div>
							
							<div class="panel panel-default"  style="border:1px solid black">	
								 <div class="panel-body" >										
										<label class="col-lg-2" style="text-align:right; vertical-align:middle;display:inline-block;line-height:75px;">InitialEvaluation</label>
										<div class="col-lg-10">
											<textarea readonly="readonly" rows="3" style="border:1px solid black" class="form-control" id="initialEvaluation" ></textarea>
										</div>
								 </div>
							</div>
							
							<div class="panel panel-default"  style="border:1px solid black">	
								 <div class="panel-body" >										
										<label class="col-lg-2" style="text-align:right; vertical-align:middle;display:inline-block;line-height:75px;">FinalResult</label>
										<div class="col-lg-10">
											<textarea readonly="readonly" rows="3" style="border:1px solid black" class="form-control" id="finalResult" ></textarea>
										</div>
								 </div>
							</div>
			

								<span>&nbsp;</span>

								<div class="form-group">
									    
								</div>


                          
                            </div>        
						</div>
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
	
	<script type="text/javascript" src="<%=path %>/js/pmo/performance/employee/employeePerformanceDetail.js"></script>
    <script type="text/javascript" src="<%=path %>/js/pmo/performance.js"></script>
</body>
</html>


