function ajaxSubForm(id,url){
	jQuery("#"+id).ajaxSubmit({
		target:"#rightContent",
		url:url,
		type:'POST'
	});
}