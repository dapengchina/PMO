<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String currentPageName=request.getParameter("currentPageName");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <link rel="stylesheet" href="<%=path %>/extensionjs/bootstarp-treeview/bootstrap-treeview.css"></link>
  <script src="<%=path %>/bower_components/jquery/jquery.min.js"></script>
  <script type="text/javascript" src="<%=path %>/extensionjs/bootstarp-treeview/bootstrap-treeview.js"></script>
  <script type="text/javascript" src="<%=path %>/js/pmo/commonmenu.js"></script>
</head>

<body>
 <small>
  <div class="col-sm-2 col-lg-2"  >
    <div id="treeview"></div>
  </div>
 </small>
</body>
</html>


