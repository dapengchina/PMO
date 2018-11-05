$().ready(function() {
	// 判断头部显示(management/HRBP)
	var type = $("#displayType").val();
	if (type == 1) {
		$("#managementH2").attr("style", "display:true");
		$("#HRBPH2").attr("style", "display:none");
	} else if (type == 2) {
		$("#managementH2").attr("style", "display:none");
		$("#HRBPH2").attr("style", "display:true");
	}
});
function selectAll() {
	$("input[name='template']").each(function(index, element) {
		$(this).attr("checked", true);
	});
}
/* 单模板下载 */
function download(templateId) {
	// 发送请求
	var url = path + '/service/performanceTemplate/download?templateId=' + templateId;
	window.location.href = url;
}
/* 多模板下载 */
function downloads() {
	var templateIds = new Array();
	// 获取已选模板id
	$("input[name='template']:checked").each(function(index, element) {
		templateIds.push($(this).val());
	});
	if (templateIds.length == 0) {
		alert("请勾选下载模板");
		return;
	}
	// 发送请求
	var url = path + '/service/performanceTemplate/downloads?templateIds=' + templateIds;
	window.location.href = url;
}