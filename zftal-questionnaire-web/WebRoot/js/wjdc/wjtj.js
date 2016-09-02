//加载SELECT标签列表数据
function loadOption() {
	var selectObj = jQuery(".tj_select");
	if (selectObj != null && selectObj.length > 0) {
		jQuery(selectObj).each(function(i, n) {
			if (jQuery(n)) {
				jQuery(n).textClue( {
					id : jQuery(n).attr("id"),
					divId : jQuery(n).attr("id") + "Div",
					url : _path + '/wjdc/wjbase_getCxzdOption.html',
					listKey : 'MC',
					listText : 'MC',
					params : {
						bm : jQuery('#bm').val(),
						zd : jQuery(n).attr("id")
					}
				});
			}
		});
	}
}

//回显查询条件值
function dispFiledValue() {
	var valueStr = jQuery('#valueStr').val();
	if (valueStr != "" && valueStr != null) {
		var array = valueStr.split("!!@@split!!@@");
		for ( var i = 0; i < array.length; i++) {
			var zdmc = array[i].split("!!=@@")[0];
			var zdz = array[i].split("!!=@@")[1];
			if (jQuery("#" + zdmc)) {
				jQuery("#" + zdmc).val(zdz);
			}
		}
	}
}