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
      <!-- left menu starts -->
      <div class="col-sm-2 col-lg-2">
          <div class="sidebar-nav">
           <!--菜单栏  -->   
           
            <ul class="nav nav-pills">
                <li class="nav-header">HRBP</li>
			    <li class="dropdown">
			        <a href="" class="dropdown-toggle" data-toggle="dropdown">Performance Assessment<span class="caret"></span></a>
			        <ul class="dropdown-menu">
				        <li>
				            <div id="getHR"><a href="#">Group Assessment</a></div>				        
				        </li>
				        <li>
				            <div id="getAP"><a href="#">Approval</a></div>
				        </li>
			        </ul>
			    </li>
			    <li class="dropdown">
			        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Data Upload Temlate<span class="caret"></span></a>
			        <ul class="dropdown-menu">
			            <li>
			                <div id="getTem"><a href="#">Temlate</a></div>
			            </li>
			            <li>
			                <div id="getDU"><a href="#">Data Upload</a></div>
			            </li>
			        </ul>
			    </li>
	            <li class="dropdown">
			        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Assessment Result<span class="caret"></span></a>
			        <ul class="dropdown-menu">
			            <li>
			               <div id="getLP"><a href="#">Latest Performance</a></div>
			            </li>
			            <li>
			               <div id="getPR"><a href="#">Performance History Search</a></div>
			            </li>
			        </ul>
			    </li>
            </ul> 
           
           <!--菜单栏  -->
          </div>
      </div>
</body>
</html>


