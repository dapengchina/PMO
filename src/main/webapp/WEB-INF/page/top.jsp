<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
%>
<%-- <%
	@page import="controller.UserController"
%>
<% 
 UserController uc = new UserController();
   String a = uc.loginOut(); 
%> --%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
    <meta name="author" content="Muhammad Usman">

    <!-- The styles -->
    <link
		href='<%=path %>/bower_components/bootstrap-val/bootstrapValidator.css'
		rel='stylesheet'>

    <link href="<%=path %>/css/bootstrap-cerulean.min.css" rel="stylesheet">

    <link href="<%=path %>/css/charisma-app.css" rel="stylesheet">
    <link href='<%=path %>/bower_components/fullcalendar/dist/fullcalendar.css' rel='stylesheet'>
    <link href='<%=path %>/bower_components/fullcalendar/dist/fullcalendar.print.css' rel='stylesheet' media='print'>
    <link href='<%=path %>/bower_components/chosen/chosen.min.css' rel='stylesheet'>
    <link href='<%=path %>/bower_components/colorbox/example3/colorbox.css' rel='stylesheet'>
    <link href='<%=path %>/bower_components/responsive-tables/responsive-tables.css' rel='stylesheet'>
    <link href='<%=path %>/bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css' rel='stylesheet'>
    <link href='<%=path %>/css/jquery.noty.css' rel='stylesheet'>
    <link href='<%=path %>/css/noty_theme_default.css' rel='stylesheet'>
    <link href='<%=path %>/css/elfinder.min.css' rel='stylesheet'>
    <link href='<%=path %>/css/elfinder.theme.css' rel='stylesheet'>
    <link href='<%=path %>/css/jquery.iphone.toggle.css' rel='stylesheet'>
    <link href='<%=path %>/css/uploadify.css' rel='stylesheet'>
    <link href='<%=path %>/css/animate.min.css' rel='stylesheet'>
    <link href="<%=path %>/css/login.css" rel="stylesheet">

    <!-- jQuery -->
    <script src="<%=path %>/bower_components/jquery/jquery.min.js">
   
  
    
    </script>
	
    <!-- The fav icon -->
    <link rel="shortcut icon" href="<%=path %>/img/favicon.ico">
</head>

<body>
	
	<input type="hidden" id="is_malaysia" value="${sessionScope.loginUser.userLocation}"/>
    <!-- topbar starts -->
    <div class="navbar navbar-default" role="navigation">

        <div class="navbar-inner">
        	<button type="button" class="navbar-toggle pull-left animated flip">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<%=path %>/service/user/welcome.html"> <img alt="Charisma Logo" src="<%=path %>/img/title_logo.png" class="hidden-xs"/>
                <span>PMO</span></a>
            <!-- user dropdown starts -->
            <div class="btn-group pull-right">
            	<span style="float: left;margin-right: 20px;font-size: 1.2em;" >Hi , ${ sessionScope.loginUser.nickname}</span>
                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    <i class="glyphicon glyphicon-user"></i><span class="hidden-sm hidden-xs"> ${sessionScope.employee.getName()}</span>
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
<!--                 <li><a href="#">个人信息</a></li>-->
					 <li><a id="editPwd" href="#">Change Password</a></li>
                  	<li class="divider"></li>
                 	 <%-- <li><a href="<%=path %>/UserController?method=loginOut" rel="stylesheet">注销</a></li> --%>
                  <%--  <li><a href="${pageContext.request.contextPath}/LoginOutController">注销</a></li> --%>
                   <li><a href="<%=path %>/service/logOut/logOut.html">Sign Out</a></li>
                    
                </ul>
            </div>
            <!-- user dropdown ends -->
        </div>
    </div>
    <!-- topbar ends -->
    
   <!--  <div class="modal fade" id="loginOut2" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
    
    </div> -->

    <div class="modal fade" id="editPassword" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
		 <form id="editPasswordForm" class="form-horizontal" method="post">

			<div class="modal-dialog">
				<div class="modal-content">
					<div class="box-header well" data-original-title="">
						<h2>
							<i class="glyphicon glyphicon-user"></i> Change Password
						</h2>

						<div class="box-icon">
							<a href="#" class="btn btn-round btn-default  btn-minimize "><i
								class="glyphicon glyphicon-chevron-up"></i></a> <a
								class="btn btn-round btn-default" href="#" data-dismiss="modal">
								<i class="glyphicon glyphicon-remove"></i>
							</a>
						</div>
					</div>
					 <div class="alert alert-info new-login-box-header" id="updateAlert" style="display: none;">
                <!-- Welcome to SpiderNet -->
            		</div>
					<div id="excelCheckBox" class="box-content">
						<!-- New Password:<input type="password" id="newPassword"/><br/><br/><br/>
						Confirm Password:<input type="password" id="confPassword"/> -->
						
							<div class="group">
								<label class="col-sm-2 control-label">New Password</label>
									<div class="col-sm-8">
										<input type="password" class="form-control" name="hsbcProjectName"
											id="newPassword"/>
									</div>
							</div><br><br><br>
						    <div class="group">
								<label class="col-sm-2 control-label">Confirm Password</label>
									<div class="col-sm-8">
										<input type="password" class="form-control" name="hsbcProjectManager"
											id="confPassword"/>
									</div>
							</div><br><br><br>
						
						<div class="center">
							<a class="btn btn-success" href="#" data-dismiss="modal" onclick="updatePassword()"> <i
								class="glyphicon glyphicon-ok icon-white" ></i> Confirm
							</a> <a class="btn btn-info" href="#" data-dismiss="modal"> <i
								class="glyphicon glyphicon-remove icon-white"></i> Cancel
							</a>
						</div>
					</div>
				</div>
			</div>
            </form>
	</div>
    <!-- external javascript -->
	<script type="text/javascript">
		var path = "<%=path%>";
		var showBankbone = true;
		if("MALAYSIA" == $("#is_malaysia").val().toUpperCase()){
			showBankbone = false;
		}
	</script>

<script src="<%=path %>/js/pmo/updatePassword.js"></script>
</body>

</html>


