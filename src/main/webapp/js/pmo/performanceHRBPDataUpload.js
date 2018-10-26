$().ready(function() {
});
var templateId;// 全局变量，保存当前上传的文件名
// 关闭模态框时重置表单数据
$("#uploadModal").on('hide.bs.modal', function() {
	$("#uploadForm").get(0).reset();
	templateId = "";
});
// 显示模态框
function showModal(curId) {
	templateId = curId;
	$("#uploadModal").modal("show");
}
// 上传文件名在输入框显示
function loadFile(file) {
	$("#templateUploadInput").val(file.name);
}
// 上传文件
function upload() {
	var file = $("#upload").val();
	// 判空
	if (file == "") {
		alert("Please upload file!");
		return;
	}
	var ary = file.split(".");
	var fileType = ary[ary.length - 1];
	// 判断文件类型
	if (fileType != "doc" && fileType != "xlsx" && fileType != "docx") {
		alert("Please upload a template in doc or xlsx format!");
		return;
	}
	// 文件上传
	$.ajaxFileUpload({
		url : path + '/service/performanceTemplate/upload.html',// 用于文件上传的服务器端请求地址
		type : "post",
		fileElementId : 'upload',// 文件上传控件的id属性 input必须加name属性
		data : {
			"templateId" : templateId
		},
		success : function(data) {
			$("#uploadModal").modal("hide");
			alert("Successfully Upload");
		},
		error : function(data, status, e)// 服务器响应失败处理函数
		{
			$("#uploadModal").modal("hide");
			alert(e);
		}
	});
}