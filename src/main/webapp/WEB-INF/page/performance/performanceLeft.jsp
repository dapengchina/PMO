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
</head>

<body>
<small>
        <!-- left menu starts -->
        <div class="col-sm-2 col-lg-2">
            <div class="sidebar-nav">
                <div class="nav-canvas">
                    <div class="nav-sm nav nav-stacked">
                    </div>
                    <ul class="nav nav-pills nav-stacked main-menu">
                        <li class="nav-header">Performance Evaluation</li>
                        <c:forEach var="userAuthority" items="${list}" >	
                           	<c:if test="${userAuthority.menuParentId==null}">			   
						    <li class="accordion">
                            <a href="#"><i class="glyphicon glyphicon-tasks"></i><span>&nbsp;&nbsp;${userAuthority.menuName}</span></a>
                            <ul class="nav nav-pills nav-stacked">                              
                                 <c:forEach var="userAuthority0" items="${list}" >
                                  <c:if test="${userAuthority0.menuParentId==userAuthority.menuId}">
									    <li><a class="ajax-link" href="<%=path %>${userAuthority0.menuUrl}"><i class="glyphicon glyphicon-home"></i><span>&nbsp;&nbsp;${userAuthority0.menuName}</span></a></li>
								  </c:if>
                                 </c:forEach>
                            </ul>
                           </li> 
                           </c:if>
						</c:forEach>
						
						
                        <li class="accordion">
                            <a href="#"><i class="glyphicon glyphicon-tasks"></i><span>&nbsp;&nbsp;Employee</span></a>
                            <ul class="nav nav-pills nav-stacked">
                              <li>
                              <a class="ajax-link" href="#"><i class="glyphicon glyphicon-stats"></i><span>&nbsp;&nbsp;绩效目标</span></a>
                                     <ul class="   dropdown">
		                              	<li>
		                              		<a class="ajax-link" href="#"><i class="glyphicon glyphicon-folder-open"></i><span>&nbsp;&nbsp;绩效目标设定</span></a>
                   							<ul class=" dropdown">
				                              	<li class="active">
				                              		<a class="ajax-link" href="<%=path %>/service/performance/performanceEmpPBC.html"><i class="glyphicon glyphicon-home"></i><span>PBC绩效目标模板</span></a>
				                              	</li>
				                              	<li>
				                              		<a class="ajax-link" href="#"><i class=""></i><span>第四事业部绩效目标模板</span></a>
				                              	</li>
				                            </ul>		                              	
		                              	</li>
		                            </ul>
                              </li>
                              <li>
	                              <a class="ajax-link" href="#"><i class="glyphicon glyphicon-stats"></i><span>&nbsp;&nbsp;绩效考评</span></a>
	                              <ul class="dropdown">
	                              <li><a class="ajax-link" href="<%=path %>/service/performance/performanceEmpEvaSelf.html"><i class="glyphicon glyphicon-home"></i><span>&nbsp;&nbsp;员工自评</span></a></li>
	                              <li><a class="ajax-link" href="<%=path %>/service/performance/performanceEmpEvaProgress.html"><i class="glyphicon glyphicon-home"></i><span>&nbsp;&nbsp;考评进度</span></a></li>
	                              </ul>
                              </li>

                              <li>
                              	<a class="ajax-link" href="#"><i class="glyphicon glyphicon-stats"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;绩效结果</span></a>
	                              <ul class="dropdown">
	                              <li>
	                              	<a class="ajax-link" href="<%=path %>/service/performance/performanceEmpEvaCurrentPeriod.html"><i class="glyphicon glyphicon-home"></i><span>&nbsp;&nbsp;当期绩效</span></a>
                 					  <ul class=" dropdown">
		                              	<li class="active">
		                              		<a class="ajax-link" href="<%=path %>/service/performance/performanceEmpEvaCurrentPeriodDetail.html"><i class="glyphicon glyphicon-home"></i><span>Page-Detail</span></a>
		                              	</li>
		                            </ul>	
	                              </li>
	                              <li>
	                              	<a class="ajax-link" href="#"><i class="glyphicon glyphicon-folder-open"></i><span>&nbsp;&nbsp;历史绩效查询</span></a>
                 					  <ul class=" dropdown">
		                              	<li class="active">
		                              		<a class="ajax-link" href="<%=path %>/service/performance/performanceEmpEvaHistoryQuery.html"><i class="glyphicon glyphicon-home"></i><span>Page-Performance Doc</span></a>
		                              	</li>
		                              	<li>
		                              		<a class="ajax-link" href="<%=path %>/service/performance/performanceEmpEvaCurrentPeriodDetail.html"><i class="glyphicon glyphicon-home"></i><span>Page-Detail</span></a>
		                              	</li>
		                            </ul>
	                              </li>
	                              </ul>
                              </li>
                            </ul>
                        </li> 
                        
                        <li class="accordion">
                            <a href="#"><i class="glyphicon glyphicon-tasks"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;Management</span></a>
                            <ul class="nav nav-pills nav-stacked">
                              <li><a class="ajax-link" href="<%=path %>/service/resume/input.html"><i class="glyphicon glyphicon-home"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;候选人录入</span></a></li>
                              <li><a class="ajax-link" href="<%=path %>/service/candidate/getCandidate.html"><i class="glyphicon glyphicon-home"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;候选人列表</span></a></li>
  						      <li><a class="ajax-link" href="<%=path %>/service/candidate/getMyCandidate.html"><i class="glyphicon glyphicon-home"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;我的候选人</span></a></li>
  						      <li><a class="ajax-link" href="<%=path %>/service/candidate/getMyWaitEntryCandidate.html"><i class="glyphicon glyphicon-home"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;待入职候选人</span></a></li>
                            </ul>
                        </li>
                        
                        <li class="accordion">
                            <a href="#"><i class="glyphicon glyphicon-tasks"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;HR</span></a>
                            <ul class="nav nav-pills nav-stacked">
                               <li><a class="ajax-link" href="<%=path %>/service/demand/recruitdemand.html"><i class="glyphicon glyphicon-home"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;招聘需求</span></a></li>
                               <li><a class="ajax-link" href="<%=path %>/service/demand/demandInfo.html"><i class="glyphicon glyphicon-home"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;招聘需求查询</span></a></li>
                               <li><a class="ajax-link" href="<%=path %>/service/stayin/stayin.html"><i class="glyphicon glyphicon-home"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;RM待入职员工查看</span></a>
                               </li>
                            </ul>
                        </li>
                        
                        <li class="accordion">
                            <a href="#"><i class="glyphicon glyphicon-tasks"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;LOB</span></a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
</small>
</body>
</html>


