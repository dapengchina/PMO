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

	<c:import url="/service/performance/performanceLeft" />

 	
<!-- middle content start -->
			<div id="content" class="col-lg-10 col-sm-10">

				<div class="row" >	
				<div class="box col-md-12">
						<div class="box-inner" >
							<div class="box-header well" data-original-title="" >
								<h2>
									<i class="glyphicon glyphicon-user"></i>  Management->绩效考评->初评->查看客户反馈详情
								</h2>
							</div>

							<div id="employeeInfo" class="box-content">					
								
							<div class="panel panel-default">	
                              <div class="panel-body">

									<table id="table1" border="1" width="100%" borderColor="green" >
										<tr style="">
										 <td rowspan="4" width="55%">
											说明：<br/>
											1.<font color="red">比例控制：A 10-15%; B+ 30-40%; B 30-40%; C 5-10%; D 0-5%; A未使用比例可用于B+, 但A&B+ &lt; 55%; </font><br/>
											2.考评周期内在岗时间不足一个月的长假员工，不参与考评，请各部门在备注中注明原因，HRBP部会从OA系统取数据
											进行统一复核;<br/>
											3.标蓝色行的异动和借阅人员，考评部门需参考有监督权交付部的建议，HRBP做复核，以确保这部分人员的绩效考核被合理对待。<br/>
										 </td>
										 <td colspan="7" style="text-align:center"><font color="green"> 主管比例统计(参考比例要求控制)</font>	 </td>
										</tr>
									   <tr>
										 <td> A <br/>(10-15%)</td>
										 <td> B+ <br/>(30-40%) </td>
										 <td> B <br/> &nbsp;</td>
										 <td> C <br/>(5-10%)</td>
										 <td> D <br/>(0-5%) </td>
										 <td> 参评比例合计 </td>
									   </tr>
									   <tr>
										 <td> 10 </td>
										 <td> 60  </td>
										 <td> 30 </td>
										 <td> 22 </td>
										 <td> 4  </td>
										 <td> 76 </td>
									   </tr>
									   <tr>
										 <td> 7.9%	 </td>
										 <td> 47.6%  </td>
										 <td> 23.8% </td>
										 <td> 17.5% </td>
										 <td> 3.2% </td>
										 <td> 100% </td>
									   </tr>
									</table>

									<br/>
									<small><small>
									<table id="table2" border="1" width="100%" >
										<tr style="background-color:#00688B">
											<th><font color="white"> 绩效目标</font></th>
											<th><font color="white"> 序号</font></th>
											<th><font color="white"> EHR编号</font></th>
											<th><font color="white"> LOB工号</font></th>
											<th><font color="white"> 姓名</font></th>
											<th><font color="white"> 入职时间</font></th>
											<th><font color="white"> 职务</font></th>
											<th><font color="white"> 业务线</font></th>
											<th><font color="white"> BU </font></th>
											<th><font color="white"> 交付部</font></th>
											<th><font color="white"> 归属地 </font></th>
											<th><font color="white"> 是否骨干</font></th>
											<th><font color="white"> 是否参评 </font></th>
											<th><font color="white"> 直接主管 </font></th>
											<th><font color="white"> 客户反馈 </font></th>
											<th><font color="white"> 初评(依据客户反馈)</font></th>
											<th><font color="white"> 直接主管初评结果 </font></th>
											<th><font color="white"> 部门集体评议结果 </font></th>
											<th><font color="white"> 集体评议主管  </font></th>
											<th><font color="white"> A/C/D人员绩效事实</font></th>
											<th><font color="white"> 是否绩效跳变</font></th>
											<th><font color="white"> 备注</font></th>
											<th><font color="white"> 上季度绩效 </font></th>
											<th><font color="white"> 上上季度绩效 </font></th>
											<th><font color="white"> 上上上季度绩效 </font></th>
										</tr>
									   <tr>
										 <td>  <i class="glyphicon glyphicon-edit"></i> 	 </td>
										 <td> 1  </td>
										 <td> xxxxx </td>
										 <td> xxx </td>
										 <td> Tom  </td>
										 <td> 2017-10-11 </td>
										 <td> Developer </td>
										 <td> HSBC </td>
										 <td> xxx 事业部 </td>
										 <td> xxx 交付部 </td>
										 <td> Xian </td>
										 <td> 是  </td>
										 <td> 是 </td>
										 <td> XXX Rm </td>
										 <td> 工作认真 </td>
										 <td> B+ </td>
										 <td>   </td>
										 <td>   </td>
										 <td> XXX交付部经理 </td>
										 <td>   </td>
										 <td>   </td>
										 <td>   </td>
										 <td> B+  </td>
										 <td> b+  </td>
										 <td> A </td>
									   </tr>
									   <tr>
										 <td>  <i class="glyphicon glyphicon-edit"></i> 	 </td>
										 <td> 2  </td>
										 <td> xxxxx </td>
										 <td> xxx </td>
										 <td> Beuben  </td>
										 <td> 2015-1-16 </td>
										 <td> Developer </td>
										 <td> HSBC </td>
										 <td> xxx 事业部 </td>
										 <td> xxx 交付部 </td>
										 <td> Xian </td>
										 <td> 是  </td>
										 <td> 是 </td>
										 <td> XXX  </td>
										 <td> 工作认真 </td>
										 <td> B+ </td>
										 <td>   </td>
										 <td>   </td>
										 <td> XXX </td>
										 <td>   </td>
										 <td>   </td>
										 <td>   </td>
										 <td> B+  </td>
										 <td> A  </td>
										 <td> A </td>
									   </tr>
									   <tr>
										 <td>   	 </td>
										 <td> 3  </td>
										 <td> xxxxx </td>
										 <td> xxx </td>
										 <td> William  </td>
										 <td> 2016-10-11 </td>
										 <td> Senior Developer </td>
										 <td> HSBC </td>
										 <td> xxx 事业部 </td>
										 <td> xxx 交付部 </td>
										 <td> Xian </td>
										 <td> 是  </td>
										 <td> 是 </td>
										 <td> XXX Rm </td>
										 <td> 
											<a href="#" class="link">该员工平时工
												<div class="tips">该员工平时工作仔细认真，负责。不但执行力强，而且工作配合度也好，有积极向上的工作态度，能主动协调其他同事工作，并且能及时完成上级领导安排的其他工作</div>
											</a>
										 </td>
										 <td> B+ </td>
										 <td>   </td>
										 <td>   </td>
										 <td> XXX交付部经理 </td>
										 <td>   </td>
										 <td>   </td>
										 <td>   </td>
										 <td> B  </td>
										 <td> B+  </td>
										 <td> B </td>
									   </tr>
									   <tr>
										 <td>  	 </td>
										 <td> 4  </td>
										 <td> xxxxx </td>
										 <td> xxx </td>
										 <td> Clinton  </td>
										 <td> 2015-8-14 </td>
										 <td> Developer </td>
										 <td> HSBC </td>
										 <td> xxx 事业部 </td>
										 <td> xxx 交付部 </td>
										 <td> Xian </td>
										 <td> 是  </td>
										 <td> 是 </td>
										 <td> XXX Rm </td>
										 <td> 业务知识扎实 </td>
										 <td> A </td>
										 <td>   </td>
										 <td>   </td>
										 <td> XXX交付部经理 </td>
										 <td>   </td>
										 <td>   </td>
										 <td>   </td>
										 <td> A  </td>
										 <td> B+  </td>
										 <td> B+ </td>
									   </tr>
									   <tr>
										 <td>  <i class="glyphicon glyphicon-edit"></i> 	 </td>
										 <td> 5  </td>
										 <td> xxxxx </td>
										 <td> xxx </td>
										 <td> Smith  </td>
										 <td> 2014-3-7 </td>
										 <td> Level2 Coach Role </td>
										 <td> HSBC </td>
										 <td> xxx 事业部 </td>
										 <td> xxx 交付部 </td>
										 <td> Xian </td>
										 <td> 是  </td>
										 <td> 是 </td>
										 <td> XXX Rm </td>
										 <td> 业绩发展迅速 </td>
										 <td> A </td>
										 <td>   </td>
										 <td>   </td>
										 <td> XXX交付部经理 </td>
										 <td>   </td>
										 <td>   </td>
										 <td>   </td>
										 <td> B  </td>
										 <td> B  </td>
										 <td> C </td>
									   </tr>
									</table>
									</small></small>
						     </div>
                             </div>                          

									<span>&nbsp;</span>
									<div class="form-group">
												<div style="text-align:center;width:100%;">
												<input type="button" value="Save" name="Save" id="Save" href="#"
												class="button btn btn-primary" data-dismiss="modal"
												onclick=""
												style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin:auto ;">
												<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
												<input type="button" value="Submit" name="Edit" id="Edit" href="#"
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
	
    <script type="text/javascript" src="<%=path %>/js/pmo/offlineOpe.js"></script>
    <script type="text/javascript" src="<%=path %>/js/pmo/performance.js"></script>
	
	
</body>
</html>

