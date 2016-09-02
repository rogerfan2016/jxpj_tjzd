<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/pages/globalweb/head/pagehead_v4.ini"%>
		<%@ include file="/WEB-INF/pages/globalweb/head/jqGrid.ini"%>
		<script type="text/javascript"
			src="<%=systemPath%>/js/globalweb/comm/dateformat.js"></script>
		<script type="text/javascript"
			src="<%=systemPath%>/js/wjdc/textClue.js"></script>
		<script type="text/javascript"
			src="<%=systemPath%>/js/wjdc/select.js"></script>
		<script type="text/javascript"
			src="<%=systemPath%>/js/jquery/jquery.form.js"></script>
		<script type="text/javascript"
			src="<%=systemPath%>/js/wjdc/dateUtils.js"></script>
		<script type="text/javascript"
			src="<%=systemPath%>/js/My97DatePicker/WdatePicker.js"></script>
		<link rel="stylesheet"
			href="<%=systemPath%>/css/plugins/textClue.css" type="text/css"
			media="all" />
		<script type="text/javascript">
			var WjxxGrid = Class.create(BaseJqGrid,{  
				caption : "问卷分发列表",
				pager: "pager", //分页工具栏  
			    url: _path+'/wjdc/wjffgl_cxWjffxx.html?doType=query',  
			    colModel:[
			         {label:'ID',name:'wjid', index:'wjid', key:true, hidden:true},
				     {label:'问卷名称 ',name:'wjmc', index:'wjmc'},
				     {label:'问卷类型',name:'wjlxmc', index: 'wjlxmc',width:50},
			      	 {label:'问卷状态',name:'wjzt', index: 'wjzt',width:50},
			      	 {label:'发放人数',name:'ffrs', index: 'ffrs',width:50},
			      	 {label:'答卷人数',name:'djrs', index: 'djrs',width:50},
			      	 {label:'平均分',name:'pjdf', index: 'pjdf',width:40},
			      	 {label:'创建人',name:'cjrxm', index: 'cjrxm',width:50},
			      	 {label:'创建时间',name:'cjsj', index: 'cjsj',width:50},
				],
				sortname: 'cjsj', //首次加载要进行排序的字段 
			 	sortorder: "desc"
			});
			//按钮绑定
			function bdan() {
				var btn_ffwj=jQuery("#btn_ffwj");//问卷分发
				var btn_wjdjtj=jQuery("#btn_wjdjtj");//答卷统计
				var btn_wjdjxq=jQuery("#btn_wjdjxq");//答卷详情
				var btn_wjsttj=jQuery("#btn_wjsttj");//试题统计
				//var btn_wjjctj=jQuery("#btn_wjjctj");//交叉统计
				//var btn_wjjccspztj=jQuery("#btn_wjjccspztj");//交叉统计参数配置

				if(btn_ffwj != null){
					btn_ffwj.click(function () {
						wjff();
					});
				}
				if(btn_wjdjtj != null){btn_wjdjtj.click(function(){wjtj("djtj");});}
				if(btn_wjdjxq != null){btn_wjdjxq.click(function(){wjtj("djxq");});}
				if(btn_wjsttj != null){btn_wjsttj.click(function(){wjtj("sttj");});}
				//if(btn_wjjctj != null){btn_wjjctj.click(function(){wjtj("jctj");});}
				//if(btn_wjjccspztj != null){btn_wjjccspztj.click(function(){wjtj("jctjcspz");});}
			}
			//数据加载
			jQuery(function(){
				var wjxxGrid = new WjxxGrid();
				loadJqGrid("#tabGrid","#pager",wjxxGrid);
				bdan();
			});
			//问卷分发
			function wjff() {
				var id = getChecked();
				if(id.length != 1){
					alert('请先选定一条记录!');
					return;
				}
				var row = jQuery("#tabGrid").jqGrid('getRowData', id);
				var wjzt=row.wjzt;
				if(wjzt!="发布"&&wjzt!="运行"){
					alert('问卷状态是"发布"和"运行"的才可以分发！');
					return false;
				} 
				location.href='<%=systemPath%>/wjdc/wjffgl_cxFfwjxx.html?wjid='+id;
			}
			
			function wjtj(url){
				var id = getChecked();
				if(id.length != 1){
					alert('请先选定一条记录!');
					return;
				}
				location.href=_path+'/wjdc/wjtj_'+url+'.html?wjid='+id;
			}
			function searchResult() {
				var map = {};
				map["wjmc"] = jQuery('#wjmc').val();
				map["wjlx"] = jQuery('#wjlx').val();
				map["wjzt"] = jQuery('#wjzt').val();
				map["cjrxm"] = jQuery('#cjrxm').val();
				map["cjkssj"] = jQuery('#cjkssj').val();
				map["cjjssj"] = jQuery('#cjjssj').val();
				search('tabGrid',map);
			}
			//设置招聘信息链接
			function setLink(cellvalue, options, rowObject){
				var wjid = rowObject.wjid;
				var ffrs = rowObject.ffrs;
				return "<a target='_blank' href='<%=systemPath%>/wjdc/wjffgl_cxWjffxx.html?wjid="
				+ wjid + "'>" + cellvalue + "</a>";
			}
	</script>
	</head>

	<s:form action="/wjdc/wjffgl_cxWjffxx.html" method="post"
		theme="simple">
		<body>

			<div class="toolbox">
				<!-- 加载当前菜单栏目下操作     -->
				<div class="buttonbox">
					<ul id="but_ancd">
						<li>
							<a href="javascript:void(0);" id="btn_ffwj" class="btn_sz">
								分发问卷 </a>
						</li>
					
						<li>
							<a href="javascript:void(0);" id="btn_wjdjxq" class="btn_ck">
								答卷详情 </a>
						</li>
					
						<li>
							<a href="javascript:void(0);" id="btn_wjdjtj" class="btn_tj">
								答卷统计 </a>
						</li>
					
						<li>
							<a href="javascript:void(0);" id="btn_wjsttj" class="btn_tj">
								试题统计 </a>
						</li>
						<!-- 
						<li>
							<a href="javascript:void(0);" id="btn_wjjctj" class="btn_tj">
								交叉统计 </a>
						</li>
					
						<li>
							<a href="javascript:void(0);" id="btn_wjjccspztj" class="btn_tj">
								参数配置交叉统计 </a>
						</li>
						 -->
				</ul>
				</div>
				<!-- 加载当前菜单栏目下操作 -->

				<div class="searchtab">
					<table width="100%">
						<tfoot>
							<tr>
								<td colspan="6">
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									问卷名称
								</th>
								<td>
									<s:textfield name="wjmc" id="wjmc" onkeypress="enterEvent()"
										cssStyle="width:150px" maxlength="20"></s:textfield>
								</td>
								<th>
									问卷状态
								</th>
								<td>
									<s:select
										list="#{'':'','草稿':'草稿','发布':'发布','运行':'运行','停止':'停止' }"
										cssStyle="width:150px" name="wjzt" id="wjzt"></s:select>
								</td>
								<th>
									问卷类型
								</th>
								<td>
									<s:select list="#{'':'','CPL':'测评类','DCL':'调查类'}" name="wjlx"
										cssStyle="width:150px" id="wjlx"></s:select>
								</td>
							</tr>
							<tr>
								<th>
									设计者姓名
								</th>
								<td>
									<s:textfield name="cjrxm" id="cjrxm" onkeypress="enterEvent()"
										cssStyle="width:150px" maxlength="10"></s:textfield>
								</td>
								<th>
									创建时间
								</th>
								<td colspan="2">
									<input type="text" name="cjkssj" value=""
										id="cjkssj" style="width: 80px"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
									至
									<input type="text" name="cjjssj" value=""
										id="cjjssj" style="width: 80px"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
								</td>
								<td colspan="">
									<div class="btn">
										<button type="button" id="search_go" onclick="searchResult();">
											查 询
										</button>
										<button type="reset" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>

			</div>

			<div class="formbox">
				<table id="tabGrid"></table>
				<div id="pager"></div>
			</div>
		</body>
	</s:form>
</html>
