
function xgWjxx(){
	var id = getChecked();
	if(id.length != 1){
		alert('请先选定一条记录!');
		return;
	}
	var row = jQuery("#tabGrid").jqGrid('getRowData', id);
	if(row.wjzt!="草稿"){
		alert("问卷只有在草稿状态才可以修改！");
		return false;
	}
	var row = jQuery("#tabGrid").jqGrid('getRowData', id);
	var url= _path+"/wjdc/wjgl_xgWjxx.html?wjid="+row.wjid; 
	showWindow('修改问卷信息',800,540,url);
}

//编辑问卷试题信息
function editWjStxx(){
	var id = getChecked();
	if(id.length != 1){
		alert('请先选定一条记录!');
		return;
	}
	var row = jQuery("#tabGrid").jqGrid('getRowData', id);
	if(row.wjzt!="草稿"){
		alert("问卷只有在草稿状态才可以进行设计！");
		return false;
	}
	var row = jQuery("#tabGrid").jqGrid('getRowData', id);
	var url= _path+"/wjdc/stgl_editStxx.html?wjModel.wjid="+row.wjid; 
	//document.location.href=url;
	window.open(url)
	//showWindow('编辑问卷试题信息',650,470,url);
}

//查看问卷试题信息
function showWjStxx(){
	var id = getChecked();
	if(id.length != 1){
		alert('请先选定一条记录!');
		return;
	}
	
	var row = jQuery("#tabGrid").jqGrid('getRowData', id);
	var url= _path+"/wjdc/stgl_showStxx.html?wjModel.wjid="+row.wjid; 
	window.open(url)
}

//删除问卷信息
function scWjxx(){
	var id = getChecked();
	if(id.length != 1){
		alert('请先选定一条记录!');
		return;
	}
	var row = jQuery("#tabGrid").jqGrid('getRowData', id);
	if(row.wjzt!="草稿"){
		alert("问卷状态不为草稿状态，不可删除！");
		return;
	}
	plcz(_path+'/wjdc/wjgl_scWjxx.html','删除');
}

//修改问卷状态
function updateWjzt(){
	var id = getChecked();
	if(id.length != 1){
		alert('请先选定一条记录!');
		return;
	}
	
	var row = jQuery("#tabGrid").jqGrid('getRowData', id);
	var url= _path+"/wjdc/wjgl_updateWjzt.html?wjid="+row.wjid; 
	showWindow('修改问卷状态',450,210,url);
}

//问卷功能约束
function wjgnys(){
	var id = getChecked();
	if(id.length != 1){
		alert('请先选定一条记录!');
		return;
	}
	
	var row = jQuery("#tabGrid").jqGrid('getRowData', id);
	var url= _path+"/wjdc/wjgl_wjgnys.html?wjid="+row.wjid; 
	showWindow('设置问卷功能约束',350,400,url);
}

function bdan(){
	var btn_zj=jQuery("#btn_zj");//增加
	var btn_xg=jQuery("#btn_xg");//修改
	var btn_sc=jQuery("#btn_sc");//删除
	var btn_sz=jQuery("#btn_wjsj");//编辑试题
	var btn_ck=jQuery("#btn_wjyl");//查看试题
	var btn_sh=jQuery("#btn_wjztxg");//修改问卷状态
	var btn_gnys=jQuery("#btn_wjgnys");//功能约束

	if(btn_zj != null){
		btn_zj.click(function () {
			var url=_path+'/wjdc/wjgl_zjWjxx.html';
			showWindow('增加问卷信息',700,500,url);
			return false;
		});
	}
	
	if(btn_xg != null){
		btn_xg.click(function () {
			xgWjxx();
		});
	}

	if(btn_sc != null){
		btn_sc.click(function () {
			//plcz(_path+'/wjdc/wjgl_scWjxx.html','删除');
			scWjxx();
		});
	}
	
	if(btn_sz != null){
		btn_sz.click(function () {
			editWjStxx();
		});
	}
	
	if(btn_ck != null){
		btn_ck.click(function () {
			showWjStxx();
		});
	}
	
	if(btn_sh != null){
		btn_sh.click(function () {
			updateWjzt();
		});
	}
	
	if(btn_gnys != null){
		btn_gnys.click(function () {
			wjgnys();
		});
	}
}

function searchResult() {
	var map = {};
	map["wjmc"] = jQuery('#wjmc').val();
	map["wjlx"] = jQuery('#wjlx').val();
	map["wjzt"] = jQuery('#wjzt').val();
	map["cjrmc"] = jQuery('#cjrmc').val();

	search('tabGrid',map);
}

function initWjlx(){
	jQuery('#wjlxmc').textClue({
		id:'wjlxmc',
		divId:'wjlxmcDiv',
		url:_path+'/wjdc/wjgl_getWjlxList.html',
		listKey:'DM',
		listText:'MC',
		valueId:'wjlx'
	});
}
