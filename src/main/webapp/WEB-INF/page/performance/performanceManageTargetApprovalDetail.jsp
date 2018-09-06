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

				<div class="row" >	
				<div class="box col-md-12">
						<div class="box-inner" >
							<div class="box-header well" data-original-title="" >
								<h2>
									<i class="glyphicon glyphicon-user"></i>  Management->绩效目标->审批
								</h2>
							</div>

							<div id="employeeInfo" class="box-content">					
	
								
							<div class="panel panel-default">	
                              <div class="panel-body">

											<div class="row">
							             	<div class="form-group col-md-2">
							                    <div class="input-group">
							                        <span class="input-group-addon">姓名</span>
							                        <input class="form-control" type="text" placeholder="XXX">
							                    </div>
							                </div>

							                <div class="form-group col-md-2">
							                    <div class="input-group">
							                        <span class="input-group-addon">工号</span>
							                        <input class="form-control" type="text" placeholder="XXXXX">
							                    </div>
							                </div>
							                <div class="form-group col-md-2">
							                    <div class="input-group">
							                        <span class="input-group-addon">所在部门</span>
							                        <input class="form-control" type="text" placeholder="XX交付部">
							                    </div>
							                </div>
							                <div class="form-group col-md-2">
							                    <div class="input-group">
							                        <span class="input-group-addon">职位</span>
							                        <input class="form-control" type="text" placeholder="PM">
							                    </div>
							                </div>
											<div class="form-group col-md-2">
							                    <div class="input-group">
							                        <span class="input-group-addon">考核主管</span>
							                        <input class="form-control" type="text" placeholder="XXX交付经理">
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
								 <td rowspan="5" id="leftrowspan">重点工作</td>
                                 <td>1</td>
                                 <td>招聘管理</td>
                                 <td>25%</td>
                                 <td>Xpod team面试通过率达到30%</td>
                                 <td>
								 1.举办2次面试官培训，面试官认证通过3名人选，权重5%
								 <Br/>
								 2.增加内推量，下限50份简历资源，权重5%
								 </td>
                                 <td>XX交付经理</td>
                               </tr>

                               <tr>
                                 <td>2</td>
                                 <td>项目质量</td>
                                 <td>30%</td>
                                 <td>XXXXXXXXXXXX</td>
                                 <td>XXXXXXXX</td>
                                 <td>XX交付经理</td>
                               </tr>

                               <tr>
                                 <td>3</td>
                                 <td>技术分享</td>
                                 <td>20%</td>
                                 <td>XXXXXXXXXXXX</td>
                                 <td>XXXXXXXX</td>
                                 <td>XX交付经理</td>
                               </tr>

                               <tr>
                                 <td>4</td>
                                 <td>新人培养</td>
                                 <td>10%</td>
                                 <td>XXXXXXXXXXXX</td>
                                 <td>XXXXXXXX</td>
                                 <td>XX交付经理</td>
                               </tr>
                               <tr>
                                 <td colspan="6"><a href='javascript:void(0);' class='btn btn-info btn-small' onclick="addTr1('table1', -1, 6);")>+</a>
								 </td>
                               </tr>
                             </table>

								<br/>

                             <table id="table2" border="1" width="100%" class="templateTable">
                               <tr>
                                 <td>关键事件</td>
                                 <td>1</td>
                                 <td>Develop and Support</td>
                                 <td>15%</td>
                                 <td>Xpod team面试通过率达到30%</td>
                                 <td>
								   Organize technology research like POC
								 </td>
                                 <td>XX交付经理</td>
                               </tr>
                               <tr>
                                 <td colspan="7"><a href='javascript:void(0);' class='btn btn-info btn-small' onclick="addTr2('table2', -1, 7);")>+</a>
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
                                 <td> Spring Boot	 </td>
                                 <td> Pass exam </td>
                                 <td> Pim </td>
                                 <td> 2018-12-31 </td>
                               </tr>
							    <tr>
                                 <td colspan="4"><a href='javascript:void(0);' class='btn btn-info btn-small' onclick="addTr2('table3', -1, 4);")>+</a>
								 </td>
                               </tr>
							 </table>
								<br/>
							<div class="panel panel-default"  style="border:1px solid black">	
								 <div class="panel-body" >										
										<label class="col-lg-2" style="text-align:right; vertical-align:middle;display:inline-block;line-height:75px;">Comments</label>
										<div class="col-lg-10">
											<textarea rows="3" style="border:1px solid black" class="form-control" ></textarea>
										</div>
								 </div>
							</div>


									<span>&nbsp;</span>
									<div class="form-group">
												<div style="text-align:center;width:100%;">
												<input type="button" value="Reject" name="Save" id="Save" href="#"
												class="button btn btn-primary" data-dismiss="modal"
												onclick=""
												style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin:auto ;">
												<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
												<input type="button" value="Approval" name="Edit" id="Edit" href="#"
												class="button btn btn-primary" data-dismiss="modal"
												onclick=""
												style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin:auto ;">
												</div>
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
	
    <script type="text/javascript" src="<%=path %>/js/pmo/performance.js"></script>
	
	
</body>
</html>


