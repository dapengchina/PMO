$(function(){
	
	loadRole();
	loadSkill();
	loadSource();
	dateType();
});

function loadSkill(){
	var url = path+'/json/skill.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#skill").append("<option>"+item.name+"</option>");
	       })
	});
}

function loadSource(){
	var url = path+'/json/source.json'
	$.getJSON(url,  function(data) {
		$.each(data, function(i, item) {
			$("#source").append("<option>"+item.name+"</option>");
		})
	});
}


function loadRole(){
	var url = path+'/json/msaRole.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#role").append("<option>"+item.name+"</option>");
	       })
	});
}
function dateType(){
	$('.form_datetime').datetimepicker({
		weekStart: 1,
		minView:'month',
		todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
		language:'zh-CN',
		format: 'yyyy-mm-dd',
		pickerPosition: 'bottom-left',
		showMeridian: 1
	}).on('changeDate', function(ev){
		 $('#recruitdemandFormEdit').bootstrapValidator('revalidateField', 'GRADUATE_DATE1'); 
	});
}