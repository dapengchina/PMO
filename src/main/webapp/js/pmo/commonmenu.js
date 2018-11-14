$(function () {
	loadInitMenu();
});

//加载初始菜单
function loadInitMenu(){
  $.ajax({
	  url: path+'/service/commonmenu/getMenu',
	  dataType:"json",
	  async:true,
	  cache:false,
	  type:"get",
	  success: function(result){
		serverdata=JSON.stringify(result);
		//console.log("返回数据"+serverdata);
		var options = {
			      bootstrap2: false, 
			      showTags: true,
				  enableLinks: true,
			      levels: 5,
			      data:serverdata,
			      state: {expanded: false}
		};
	    $('#treeview').treeview(options);
	  },
	  error: function (XMLHttpRequest, textStatus, errorThrown) {
	    console.log(XMLHttpRequest.status);
	    console.log(XMLHttpRequest.readyState);
	    console.log(textStatus);
	  }
  });
}




