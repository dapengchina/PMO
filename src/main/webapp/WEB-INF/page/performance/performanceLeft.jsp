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
</head>

<body>
 <small>
 <div class="col-sm-2 col-lg-2"  >
    <script src="https://cdn.bootcss.com/bootstrap-treeview/1.2.0/bootstrap-treeview.min.js"></script>
    
       <div id="treeview">
							  	</div>
  	<script type="text/javascript">
  	
  	var currentPageName='<%=currentPageName%>';

  	var urlstr = path+'/service/performance/performanceLeftMenu/' + currentPageName;
  	var serverdata = [];
  	$(document).ready(function() {  
  		$.ajax({
  			url: urlstr,
  			dataType:"json",
  			async:true,
  			cache:false,
  			type:"get",
  			success: function(result){
  			    //console.log("data==" + JSON.stringify(result));
  				serverdata=JSON.stringify(result);
	  			showmenu();
  			},
  			 error: function (XMLHttpRequest, textStatus, errorThrown) {
  	             // 状态码
  	             console.log(XMLHttpRequest.status);
  	             // 状态
  	             console.log(XMLHttpRequest.readyState);
  	             // 错误信息   
  	             console.log(textStatus);
  	         }
  		});
  	});	
  	
  	
      function buildDomTree() {         
        var data = [];
		var tree = [ {
			text: "Employee",
			icon:"glyphicon glyphicon-stop",
			selectedIcon:"glyphicon glyphicon-stop",
			color:"#2FA4E7",
			backColor:"#FFFFFF",
			state: {expanded: false},
			nodes: [{
				  text: "绩效目标",
				  icon:"glyphicon glyphicon-stop",
				  selectedIcon:"glyphicon glyphicon-stop",
				  color:"#2FA4E7",
				  backColor:"#FFFFFF",
				  state: {expanded: false},
				  nodes: [{
					text: "绩效目标设定",
					icon:"glyphicon glyphicon-stop",
				    selectedIcon:"glyphicon glyphicon-stop",
				    color:"#2FA4E7",
				    backColor:"#FFFFFF",
					href:"performanceEmpPBC.html"
				  }]
				 },{
				text: "绩效考评",
				icon:"glyphicon glyphicon-stop",
				selectedIcon:"glyphicon glyphicon-stop",
				color:"#2FA4E7",
				backColor:"#FFFFFF",
				state: {expanded: false},
				nodes: [{
					text: "员工自评",
					icon:"glyphicon glyphicon-stop",
				    selectedIcon:"glyphicon glyphicon-stop",
				    color:"#2FA4E7",
				    backColor:"#FFFFFF",
					href: "performanceEmpEvaSelf.html"
					}, {
					text: "考评进度",
					icon:"glyphicon glyphicon-stop",
				    selectedIcon:"glyphicon glyphicon-stop",
				    color:"#2FA4E7",
				    backColor:"#FFFFFF",
					href: "performanceEmpEvaProgress.html"
				  }]
			  },{
				text: "绩效结果",
				icon:"glyphicon glyphicon-stop",
				selectedIcon:"glyphicon glyphicon-stop",
				color:"#2FA4E7",
				backColor:"#FFFFFF",
				state: {expanded: false},
				nodes:[{
				  text: "当期绩效",
				  icon:"glyphicon glyphicon-stop",
				  selectedIcon:"glyphicon glyphicon-stop",
				  color:"#2FA4E7",
				  backColor:"#FFFFFF",
    			  href: "performanceEmpEvaCurrentPeriod.html"
				},{
				  text: "历史绩效查询",
				  icon:"glyphicon glyphicon-stop",
				  selectedIcon:"glyphicon glyphicon-stop",
				  color:"#2FA4E7",
				  backColor:"#FFFFFF"
				}]
		  }]},{
				text: "Management",
				icon:"glyphicon glyphicon-stop",
				selectedIcon:"glyphicon glyphicon-stop",
				color:"#2FA4E7",
				backColor:"#FFFFFF",
				state: {expanded: false},
				nodes: [{
					  text: "绩效目标",
					  icon:"glyphicon glyphicon-stop",
					  selectedIcon:"glyphicon glyphicon-stop",
					  color:"#2FA4E7",
					  backColor:"#FFFFFF",
					  state: {expanded: false},
					  nodes: [{
						text: "审批",
						icon:"glyphicon glyphicon-stop",
						selectedIcon:"glyphicon glyphicon-stop",
						color:"#2FA4E7",
						backColor:"#FFFFFF"
					  }]
					 },{
					text: "绩效考评",
					icon:"glyphicon glyphicon-stop",
					selectedIcon:"glyphicon glyphicon-stop",
					color:"#2FA4E7",
					backColor:"#FFFFFF",
					state: {expanded: false},
					nodes: [{
						text: "初评",
						icon:"glyphicon glyphicon-stop",
						selectedIcon:"glyphicon glyphicon-stop",
						color:"#2FA4E7",
						backColor:"#FFFFFF"
						}, {
						text: "审批",
						icon:"glyphicon glyphicon-stop",
						selectedIcon:"glyphicon glyphicon-stop",
						color:"#2FA4E7",
						backColor:"#FFFFFF"
					  }]
				  },{
					text: "绩效结果",
					icon:"glyphicon glyphicon-stop",
					selectedIcon:"glyphicon glyphicon-stop",
					color:"#2FA4E7",
					backColor:"#FFFFFF",
					state: {expanded: false},
					nodes:[{
							text: "绩效定稿",
							icon:"glyphicon glyphicon-stop",
							selectedIcon:"glyphicon glyphicon-stop",
							color:"#2FA4E7",
							backColor:"#FFFFFF",
							href:"performanceManageEvaFinal.html"
					},{
					  text: "历史绩效",
					  icon:"glyphicon glyphicon-stop",
					  selectedIcon:"glyphicon glyphicon-stop",
					  color:"#2FA4E7",
					  backColor:"#FFFFFF",
					  href:"performanceManageResultHistoryQuery.html"
					}]
				},{
				  text: "Template Download",
				  icon:"glyphicon glyphicon-stop",
				  selectedIcon:"glyphicon glyphicon-stop",
				  color:"#2FA4E7",
				  backColor:"#FFFFFF",
				  href: "performanceManageTemplateDownload.html"
				}]
		  },{
		  		text: "HR",
		  		icon:"glyphicon glyphicon-stop",
			    selectedIcon:"glyphicon glyphicon-stop",
			    color:"#2FA4E7",
			    backColor:"#FFFFFF"
		  },{
		  		text: "LOB",
		  		icon:"glyphicon glyphicon-stop",
				selectedIcon:"glyphicon glyphicon-stop",
				color:"#2FA4E7",
				backColor:"#FFFFFF"
		  }];
        return tree;
      }

      function showmenu() {
        var options = {
          bootstrap2: false, 
          showTags: true,
		  enableLinks: true,
          levels: 5,
          data: buildDomTree()
        };
   		//console.log("serverdata======" + serverdata);
        $('#treeview').treeview(options);
        
      };
      
  
  	</script>
 </div>
 </small>


</body>
</html>


