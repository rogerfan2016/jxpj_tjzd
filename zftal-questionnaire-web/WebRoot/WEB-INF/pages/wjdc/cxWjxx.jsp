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
			src="<%=systemPath%>/js/wjdc/wjgl.js"></script>
		<link rel="stylesheet" href="<%=systemPath%>/css/plugins/textClue.css"
			type="text/css" media="all" />
		<script type="text/javascript">
	var WjxxGrid = Class.create(BaseJqGrid, {
		caption : "问卷信息列表",
		pager : "pager", //分页工具栏  
		url : _path + '/wjdc/wjgl_cxWjxx.html?doType=query',
		colModel : [ {
			label : 'ID',
			name : 'wjid',
			index : 'wjid',
			key : true,
			hidden : true
		}, {
			label : '问卷名称 ',
			name : 'wjmc',
			index : 'wjmc'
		}, {
			label : '问卷类型',
			name : 'wjlxmc',
			index : 'wjlxmc',
			width : 50
		}, {
			label : '问卷状态',
			name : 'wjzt',
			index : 'wjzt',
			width : 50
		}, {
			label : '创建人',
			name : 'cjrmc',
			index : 'cjrmc',
			width : 50
		}, {
			label : '创建时间',
			name : 'cjsj',
			index : 'cjsj',
			width : 50
		}
		//,{label:'过期时间',name:'gqsj', index: 'gqsj'}
		],
		sortname : 'cjsj', //首次加载要进行排序的字段 
		sortorder : "desc"
	});
	jQuery(function() {
		var wjxxGrid = new WjxxGrid();
		loadJqGrid("#tabGrid", "#pager", wjxxGrid);
		bdan();
		//initWjlx();
	});
</script>
	</head>
	<body>
		<div class="toolbox">
			<!-- 加载当前菜单栏目下操作     -->
			<div class="buttonbox">
				<ul id="but_ancd">
					<li>
						<a href="javascript:void(0);" id="btn_zj" class="btn_zj">增加 </a>
					</li>
					<li>
						<a href="javascript:void(0);" id="btn_xg" class="btn_xg">修改 </a>
					</li>
					<li>
						<a href="javascript:void(0);" id="btn_sc" class="btn_sc">删除 </a>
					</li>
					<li>
						<a href="javascript:void(0);" id="btn_wjsj" class="btn_sz">问卷设计
						</a>
					</li>
					<li>
						<a href="javascript:void(0);" id="btn_wjyl" class="btn_ck">问卷预览
						</a>
					</li>
					<!-- 
					<li>
						<a href="javascript:void(0);" id="btn_wjgnys" class="btn_sz">功能约束
						</a>
					</li>
					 -->
					<li>
						<a href="javascript:void(0);" id="btn_wjztxg" class="btn_sh">状态修改
						</a>
					</li>
				</ul>
			</div>
			<!-- 加载当前菜单栏目下操作 -->
			<div class="searchtab">
				<table width="100%" border="0" id="searchTab">
					<tbody>
						<tr>
							<th>
								问卷名称
							</th>
							<td>
								<input type="text" name="wjmc" id="wjmc"
									onkeypress="enterEvent()" />
							</td>
							<th>
								问卷类型
							</th>
							<td>
								<s:select list="wjlxList" name="wjlx" id="wjlx" listKey="DM"
									listValue="MC" headerKey="" headerValue=""
									cssStyle="width:160px;" theme="simple"></s:select>
							</td>
							<th>
								问卷状态
							</th>
							<td>
								<s:select list="wjztList" name="wjzt" id="wjzt" listKey="DM"
									listValue="MC" headerKey="" headerValue=""
									cssStyle="width:160px;" theme="simple"></s:select>
							</td>
						</tr>
						<tr>
							<th>
								创建人
							</th>
							<td>
								<input type="text" name="cjrmc" id="cjrmc" onkeypress="enterEvent()" />
							</td>
							<th></th>
							<td>
							</td>
							<th></th>
							<td>
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
</html>
