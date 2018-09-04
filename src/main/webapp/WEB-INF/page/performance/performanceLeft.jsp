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
 <div class="col-sm-2 col-lg-2"  >
    <link href="https://cdn.bootcss.com/bootstrap-treeview/1.2.0/bootstrap-treeview.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-treeview/1.2.0/bootstrap-treeview.min.js"></script>
    
       <div  >
            <div class="sidebar-nav">
                <div class="nav-canvas">
							<div >
							  <h6>Performance Evalutation</h6>
							  <div class="row">
								<div id="treeview">
							  	</div>
							  </div>
							</div> 
                </div>
            </div>
        </div>
        
<script type="text/javascript">
function setCookie(c_name,value,expiredays)
{
	var exdate=new Date()
	exdate.setDate(exdate.getDate()+expiredays)
	document.cookie=c_name+ "=" +escape(value)+
	((expiredays==null) ? "" : ";expires="+exdate.toGMTString())
}
function getCookie(c_name)
{
	if (document.cookie.length>0)
	  {
	  c_start=document.cookie.indexOf(c_name + "=")
	  if (c_start!=-1)
	    { 
	    c_start=c_start + c_name.length+1 
	    c_end=document.cookie.indexOf(";",c_start)
	    if (c_end==-1) c_end=document.cookie.length
	    return unescape(document.cookie.substring(c_start,c_end))
	    } 
	  }
	return ""
}
</script>

  	<script type="text/javascript">
  	  var nextNodeId=getCookie('nextNodeId');
  	  console.log("nextNodeId=========" + nextNodeId);
      function buildDomTree() {         
        var data = [];
		var tree = [ {
			text: "Employee",
			state: {expanded: false},
			nodes: [{
				  text: "绩效目标",
				  state: {expanded: true},
				  nodes: [{
					text: "绩效目标设定",
					nodes: [{
						text: "PBC绩效目标模板",
						"nodeId":4,
						href:"performanceEmpPBC.html"
					},{
						text: "第四事业部绩效目标模板"
					}]
				  }]
				 },{
				text: "绩效考评",

				nodes: [{
					text: "员工自评",
					href: "performanceEmpEvaSelf.html"
					}, {
					text: "考评进度",
					href: "performanceEmpEvaProgress.html"
				  }]
			  },{
				text: "绩效结果",
				nodes:[{
				  text: "当期绩效",
    			  href: "performanceEmpEvaCurrentPeriod.html",
				  nodes: [{
						text: "Page-Detail",
						href: "performanceEmpEvaCurrentPeriodDetail.html"
				  }]
				},{
				  text: "历史绩效查询",
				  nodes: [{
						text: "Page-Performance Doc",
						href: "performanceEmpEvaHistoryQuery.html"
				  }, {
						text: "Page-Detail",
						href: "performanceEmpEvaCurrentPeriodDetail.html"
				  }]
				}]
		  }]},{
				text: "Management",
				state: {expanded: true},
				nodes: [{
					  text: "绩效目标",
					  state: {expanded: true},
					  nodes: [{
						text: "审批",
						nodes: [{
							text: "Page-下拉列表筛选",
							href:"performanceManageTargetApproval.html"
						},{
							text: "Page-下拉列表选结",
							href:"performanceManageTargetApprovalFilter.html"
						},{
							text: "Page-绩效目标详情审批",
							href:".html",
							href:"performanceManageTargetApprovalDetail.html"
						}]
					  }]
					 },{
					text: "绩效考评",
					state: {expanded: true},
					nodes: [{
						text: "初评",
						nodes: [{
							text: "Page-查看客户反馈详情",
							href: "performanceManageEvaFirstDetail.html"
						},{
							text: "Page-绩效评价",
							href:"performanceManageEvaFirst.html"
						},{
							text: "Page-初评结果+绩效事实",
							href: "performanceManageEvaFirstDetailComments.html"
						}]
						}, {
						text: "审批",
						nodes: [{
							text: "Page-交付部审批",
							href:"performanceManageEvaSecondDU.html"
						},{
							text: "Page-点击操作查员工绩效",
							href:"performanceManageEvaSecondQuery.html"
						},{
							text: "Page-事业部审批+事业部",
							href:"performanceManageEvaSecondBU.html"
						},{
							text: "Page-点击操作查看交付部",
							href: "performanceManageEvaSecondQueryDU.html"
						}]
					  }]
				  },{
					text: "绩效结果",
					state: {expanded: true},
					nodes:[{
							text: "绩效定稿",
							href:"performanceManageEvaFinal.html"
					},{
					  text: "历史绩效",
					  href:"performanceManageResultHistoryQuery.html",
					  nodes: [{
							text: "Page-历史绩效Detail",
							href:"performanceManageResultHistory.html"
					  }, {
							text: "Page-筛查XXX的历史绩效",
							href:"performanceManageResultHistoryQuery.html"
					  }]
					}]
				},{
				  text: "Template Download",
				  href: "performanceManageTemplateDownload.html"
				}]
		  },{
		  		text: "HR"
		  },{
		  		text: "LOB"
		  }];
        return tree;
      }
      


      $(function() {
        var options = {
          bootstrap2: false, 
          showTags: true,
		  enableLinks: true,
          levels: 5,
          selectNode: [ 4, { silent: true } ],
          onNodeSelected: function(event, data) {
  		    	console.log("data==" + JSON.stringify(data));
  		  		console.log("data.nodeid==" + data.nodeId);
  		  		nextNodeId=data.nodeId;
  		  		setCookie('nextNodeId',nextNodeId,1);
  	  		},
          data: buildDomTree()
        };

        $('#treeview').treeview(options);
        
      });
      
  
  	</script>
 </div>
 </small>


</body>
</html>


