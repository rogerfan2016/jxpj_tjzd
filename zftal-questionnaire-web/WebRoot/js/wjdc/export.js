/**
 *公用自定义导出功能相关JS 
 */
//保存配置
function saveConfig(){
	var selectZd = jQuery("#selectUl").find(":input");
	var unselectZd = jQuery("#unselectUl").find(":input");
	var selectCol = new Array();
	var unselectCol = new Array();
	
	for (var i = 0 ; i < selectZd.length ; i++){
		selectCol[i]=selectZd.eq(i).val();
	}
	
	for (var i = 0 ; i < unselectZd.length ; i++){
		unselectCol[i]=unselectZd.eq(i).val();
	}
	
	if (selectCol.length == 0){
		alert("请选择您要导出的列！");
		return false;
	}
	
	//未修改过设置直接导出
	if (!isModify){
		doExport();
		return;
	}
	
	jQuery.post(
			_path+"/zfxg!plugins/export_saveCustomConfig.html",
			{
				"dcclbh":jQuery("#dcclbh").val(),
			 	"selectZd":selectCol.toString(),
			 	"unselectZd":unselectCol.toString()
			 },
			function(data){
				if (Boolean(data)){
					jQuery("#exportButton").click();
				} else {
					alert("导出设置保存失败！");
				}
			}
	);
	
}

//直接导出
function doExport(){
	jQuery("#selectUl").find(":input").attr("name","exportModel.selectCol");
	if (jQuery("#selectUl").find(":input").length == 0){
		alert("请选择您要导出的列！");
		return false;
	}
	var pCol = jQuery("input[name$=selectCol]",window.parent.document);
	if (pCol.length > 0){
		pCol.remove();
	}
	var pForm = jQuery(parent.window.document).find("form").eq(0);
	pForm.append(jQuery("#selectUl").find(":input").clone());
	parent.window.ymPrompt.doHandler("export",false);
}

//点击加号
function select(obj){
	var li = jQuery(obj).parent();
	jQuery(obj).parent().appendTo(jQuery("#selectUl"));
	jQuery(obj).remove();
	li.append("<span class='choose_yx' onclick='unselect(this)'></span>");
	saveOrder();
}

//点击减号
function unselect(obj){
	var li = jQuery(obj).parent();
	jQuery(obj).parent().appendTo(jQuery("#unselectUl"));
	jQuery(obj).remove();
	li.append("<span class='choose_wx' onclick='select(this)'></span>");
	saveOrder();
}