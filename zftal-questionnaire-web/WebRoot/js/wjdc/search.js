/**
 * 
 * 高级查询页面引入该脚本<br>
 * 作用：① 初始化高级查询事件<br>
 * ② 高级查询关联的某些函数与效果<br>
 * ③ 高级查询最终查询条件<br>
 * @author Penghui.Qu 2012-12-27
 * 
 */

/**
 * 高级查询页面初始化事件
 */
jQuery(function(){
	//模糊查询的选中状态切换
	jQuery("#mhcx_options").hover(function(){
		jQuery("#mhcx_options dd,#mhcx_options b").show();
	}, function() {
		jQuery("#mhcx_options dd").hide();
	});
	
	jQuery("#mhcx_options ,#mhcx_options div").hover(function(){
		jQuery(this).addClass("hover");
	},function(){
		jQuery(this).removeClass("hover");
	});
	
	//模糊查询选中类型切换
	jQuery("#mhcx_options div").click(function(){
		jQuery("#text").val(jQuery(this).html());
		jQuery("#blurType").val(jQuery(this).attr("value"));
		jQuery("#mhcx_options dd").hide();
	});
	
	//模糊查询输入框光标移入清除提示
	jQuery("#blurValue").bind("focus",function(){
		if (jQuery("#blurValue").val() == "请输入关键词"){
			jQuery("#blurValue").val("");
		}
	});
	
	//模糊查询输入框光标离开，若文本框内容为空加上提示
	jQuery("#blurValue").bind("blur",function(){
		if (jQuery("#blurValue").val() == ""){
			jQuery("#blurValue").val("请输入关键词");
		}
	});
	
	//模糊查询文本框回车查询
	jQuery("#blurValue").bind("keyup",function(e){
		if (e.keyCode == 13){
			jQuery("#do_query").click();
		}
	});
	
	//更多搜索条件的收起与展开
	jQuery("#a_id_more").bind("click",function(){
		
		var _self = jQuery("#a_id_more");
		
		if (_self.attr("class") == "up"){
			_self.attr("class","down");
			_self.text("展开");
			jQuery("#div_searchContent").css("display","none");
		} else{
			_self.attr("class","up");
			_self.text("收起");
			jQuery("#div_searchContent").css("display","block");
		}
	});
	
});


/**
 * 条件查询项点击事件
 * @param obj
 * @param only 是否单选
 */
function bindSelectOption(obj,only){
	var _self = jQuery(obj);
	var _type = jQuery(obj).attr("typekey");
	var _value = jQuery(obj).attr("value");
	var selectId = _type +"_"+jQuery(obj).attr("value");
	var selctClassName = jQuery(obj).attr("class");
	
	//如果该项已经选中再次点击后起效（通过样式判断是否已经选中）
	if (selctClassName == "selectedValue"){
		//从已经选中条件中将此项删除
		jQuery("#"+selectId).remove();
		//移除已经选中的标识样式
		jQuery("a[typekey="+_type+"][value="+_value+"]").removeClass("selectedValue");
		//如果没有已经选中的条件则隐藏已经选中样式
		if (jQuery("#dl_choice dd").length == 0){
			jQuery("#div_choice").css("display","none");
		}
		
	}else {
		
		if (only){
			jQuery("#dl_choice dd[name=select_"+_type+"]").click();
		}
		
		//点击某选择项后，创建已选条件 内容
		var selectContent = "<dd id='"+selectId+"' name='select_"+_type+"' value='"+_value+"'>";
		selectContent+= "<a href='javascript:void(0);'>";
		selectContent+= "<h5>";
		selectContent+= jQuery(obj).attr("typelabel");
		selectContent+= "</h5>";
		selectContent+= jQuery(obj).attr("label");
		selectContent+= "<span class='close-icon' title='取消'></span>";
		selectContent+= "</a>";
		selectContent+= "</dd>";
		var selectDd = jQuery(selectContent);	
		
		//为已选条件绑定点击事件
		selectDd.bind("click",function(){
			_self.click();
		});
		jQuery("#dl_choice").append(selectDd);
		//显示已选条件 栏
		jQuery("#div_choice").css("display","inline");
		//为该选择项增加已经选中样式
		jQuery(obj).addClass("selectedValue");
	}
}

/**
 * 重置已选条件（联动后某些选择项已经不存在，但是已选条件中有该选择项
 * 此函数的目的是，将已选条件中不存在的选择项删除。
 * ）
 */
function resetSelectOption(){
	var selectDd = jQuery("#dl_choice dd");
	for (var i = 0 ; i < selectDd.length ; i++){
		var arr = jQuery(selectDd.eq(i)).attr("id").split("_");
		var aLink = jQuery("a[typekey="+arr[0]+"][value="+arr[1]+"]");
		if (aLink.length == 0){
			selectDd.eq(i).click();
		} else {
			aLink.attr("class","selectedValue");
		}
	}
}

/**
 * 单类选择项的收起与更多
 * @param obj
 * @param liName
 * @param displayType
 */
function flexMore(obj,liName,displayType){
	 var className = jQuery(obj).attr("class");
	 
	 if (className == "more_down"){
		 jQuery(obj).text("收起");
		 jQuery(obj).removeClass("more_down");
		 jQuery(obj).addClass("more_up");
		 jQuery(obj).parent().find("li[name="+liName+"]").css("display",displayType||"inline");
		 jQuery(obj).parent().find("a[name=a_hidden]").css("display","block");
		 
	 } else {
		 jQuery(obj).text("更多");
		 jQuery(obj).removeClass("more_up");
		 jQuery(obj).addClass("more_down");
		 jQuery(obj).parent().find("li[name="+liName+"]").css("display","none");
		 jQuery(obj).parent().find("a[name=a_hidden]").css("display","none");
	 }
}


/**
 * 高级查询选择或输入的条件对象
 * @param b （是否将选择型条件收起）
 * @returns {___anonymous4078_4079}
 */
function getSearchMap(b){
	var searchMap = {};
	var searchArray = [];
	var selectDd = jQuery("#dl_choice dd");
	
	for (var i = 0 ; i < selectDd.length ; i++){
		var value = selectDd.eq(i).attr("id");
		searchArray.push(value.replace("_","!search!"));
	}
	//已选条件
	searchMap["searchModel.selectOptions"] = searchArray.join("!selectOption!");

	if (jQuery("#blurType").val() == ""){
		var typeArray = jQuery("div[name=blurType]");
		var types = [];
		
		for (var i = 0 ; i < typeArray.length ; i++){
			types.push(typeArray.eq(i).attr("value"));
		}
		
		searchMap["searchModel.blurType"]= types.join("!blurType!");
	} else {
		searchMap["searchModel.blurType"]= jQuery("#blurType").val();
	}
	//模糊查询条件
	searchMap["searchModel.blurValue"]= jQuery("#blurValue").val() == "请输入关键词" ? "" : jQuery("#blurValue").val();
	
	//收起选择项
	if (jQuery("#a_id_more").attr("class") == "up" && b){
		jQuery("#a_id_more").click();
	}
	
	return searchMap;
}


