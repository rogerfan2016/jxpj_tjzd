<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/pages/globalweb/head/pagehead_v4.ini"%>
		<%@ include file="/WEB-INF/pages/globalweb/head/jqGrid.ini"%>
		<script type="text/javascript" src="<%=systemPath %>/js/globalweb/comm/dateformat.js"></script>
		<script type="text/javascript" src="<%=systemPath %>/js/wjdc/textClue.js"></script>
        <script type="text/javascript" src="<%=systemPath %>/js/wjdc/select.js"></script>
		<script type="text/javascript" src="<%=systemPath %>/js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="<%=systemPath %>/js/wjdc/dateUtils.js"></script>
		<link rel="stylesheet" href="<%=systemPath %>/css/plugins/textClue.css" type="text/css" media="all" />
		<script type="text/javascript">
		
			function scwjdxxx() {
				var pkObj = jQuery("input[name='pkValue']");
				var flag = false;
				jQuery(pkObj).each(function(i,n) {
					if (jQuery(n) && jQuery(n).attr("checked")) {
						flag = true;
						return;
					}
				});
				if (flag) {
					var _do = function(){
						ajaxSubForm("cxwjff", "<%=systemPath %>/wjdc/wjffgl_cxFfwjxx.html?doType=del");
						searchResult();
					}
					showConfirmDivLayer('您确定要删除选择的记录吗？',{'okFun':_do});
				} else {
					alert("请勾选要删除的记录！");
				}
			}
			//问卷对象发布
			function wjdxfb() {
				var pkObj = jQuery("input[name='pkValue']");
				var flag = false;
				jQuery(pkObj).each(function(i,n) {
					if (jQuery(n) && jQuery(n).attr("checked")) {
						flag = true;
						return;
					}
				});
				if (flag) {
					var _do = function(){
						ajaxSubForm("cxwjff", "<%=systemPath %>/wjdc/wjffgl_cxFfwjxx.html?doType=ff");
						searchResult();
					}
					showConfirmDivLayer('您确定要分发所选记录吗？',{'okFun':_do});
				} else {
					alert("请勾选要分发的记录！");
				}		
			}

			//问卷对象取消分发
			function wjdxqxfs(){
				var pkObj = jQuery("input[name='pkValue']");
				var flag = false;
				jQuery(pkObj).each(function(i,n) {
					if (jQuery(n) && jQuery(n).attr("checked")) {
						flag = true;
						return;
					}
				});
				if (flag) {
					var _do = function(){
						ajaxSubForm("cxwjff", "<%=systemPath %>/wjdc/wjffgl_cxFfwjxx.html?doType=qxff");
						searchResult();
					}
					showConfirmDivLayer('您确定要取消分发所选记录吗？',{'okFun':_do});
				} else {
					alert("请勾选要取消分发的记录！");
				}
			}
			
			//按钮绑定
			function bdan() {
				var btn_zj=jQuery("#btn_zj");//增加
				var btn_sc=jQuery("#btn_sc");//删除
				var btn_fb=jQuery("#btn_fb");//发布
				var btn_fh=jQuery("#btn_fh");//返回
				var btn_qxfb=jQuery("#btn_qxfb");//取消分发
				
				if (btn_zj != null) {
					btn_zj.click(function () {
						showWindow('选择分发对象',700,545,"<%=systemPath %>/wjdc/wjffgl_zjWjffxx.html?lxid="+jQuery('#lxid').val()+"&wjid="+jQuery('#wjid').val());
					});
				}
				
				if (btn_sc != null) {
					btn_sc.click(function () {
						scwjdxxx();
					});
				}
				
				if (btn_fb != null) {
					btn_fb.click(function () {
						wjdxfb();
					});
				}

				if (btn_qxfb != null){
					btn_qxfb.click(function () {
						wjdxqxfs();
					}
					);											
				} 
				
				if (btn_fh != null) {
					btn_fh.click(function () {
						location.href='<%=systemPath %>/wjdc/wjffgl_cxWjffxx.html';
					});
				}
			}
			//数据加载
			jQuery(document).ready(function(){
				bdan();

				var lxbt = jQuery('#lxid').val();
				if (lxbt != null) {
					jQuery('#'+lxbt).addClass("ha");
				}				
				loadOption();				
				dispFiledValue();
			});
			
			function refreshSearch(id,url){
				jQuery("#"+id).attr("action",url);
				jQuery("#"+id).attr("method","post");
				jQuery("#"+id).submit();
			}
			//查询结果
			function searchResult() {
			
				var id = "cxwjff";
				var url = "<%=systemPath %>/wjdc/wjffgl_cxFfwjxx.html";				
				refreshSearch(id,url);				
			}
			
			//加载SELECT标签列表数据
			function loadOption() {
				var selectObj = jQuery(".tj_select");
				if (selectObj != null && selectObj.length > 0) {
					jQuery(selectObj).each(function (i,n) {
						if (jQuery(n)) {
							jQuery(n).textClue({
								id:jQuery(n).attr("id"),
								divId:jQuery(n).attr("id")+"Div",
								url:_path+'/wjdc/wjbase_getCxzdOption.html',
								listKey:'MC',
								listText:'MC',
								params:{bm:jQuery('#bm').val(),zd:jQuery(n).attr("id")}
							});
						}
					});
				}
			}
			//选中行首复选框
			function selectPk(obj,zdmc) {
				var pkObj = jQuery("input[name='pkValue']");
				jQuery(pkObj).each(function(i,n) {
					if (jQuery(n)) {
						jQuery(n).attr("checked",obj.checked);
					}
				});
			}
			//回显查询条件值
			function dispFiledValue() {
				var valueStr = jQuery('#valueStr').val();
				if (valueStr != "") {
					var array = valueStr.split("!!@@split!!@@");
					for (var i=0;i<array.length;i++) {
						var zdmc = array[i].split("!!=@@")[0];
						var zdz = array[i].split("!!=@@")[1];
						if (jQuery("#"+zdmc)) {
							jQuery("#"+zdmc).val(zdz);
						}	
					}
				}
			}
		</script>
	</head>

	<s:form action="/wjdc/wjffgl_cxFfwjxx.html" method="post" id="cxwjff" theme="simple">
	<body style="height: 950px">
		<!-- 表名 -->
		<input type="hidden" name="bm" id="bm" value="${sjyModel.bm }"/>
		
			<!-- 类型ID -->
			<input type="hidden" name="lxid" id="lxid" value="${lxbt }"/>
			<input type="hidden" name="wjid" id="wjid" value="${wjid }"/>
			<input type="hidden" name="valueStr" id="valueStr" value="${valueStr }"/>
			
			<!-- 循环出类型 -->
			<div class="comp_title">
		      <ul class="cla_lxbt">
				<s:iterator value="lxbtList" >
					 <li id="${lxid}"><a href="#" onclick="location.href='<%=systemPath %>/wjdc/wjffgl_cxFfwjxx.html?lxid=${lxid }'+'&wjid=${wjid }';"><span>${lxmc}</span></a></li>
				</s:iterator>
		      </ul>
		    </div>
		
		<div class="toolbox">
			<!-- 加载当前菜单栏目下操作     -->
			
			<div class="buttonbox">
			<ul id="but_ancd">
					<li>
						<a href="javascript:void(0);" id="btn_zj" class="btn_zj" >
							增加</a>
					</li>
					<li>
						<a href="javascript:void(0);" id="btn_sc" class="btn_sc" >
							删除</a>
					</li>
					<li>
						<a href="javascript:void(0);" id="btn_fb" class="btn_shtg" >
							分发</a>
					</li>
					<li>
						<a href="javascript:void(0);" id="btn_qxfb" class="btn_shtg" >
							取消分发</a>
					</li>
					<li>
						<a href="javascript:void(0);" id="btn_fh" class="btn_fh" >
							返回</a>
					</li>
			</ul>
		</div>
			
			<!-- 加载当前菜单栏目下操作 -->

			<div class="searchtab">
		          <table width="100%">
		            <tfoot>
					<tr>
                <td colspan="6">
                	<div class="btn">
                   	<button type="button" id="search_go"
										onclick="searchResult();" >
										查 询
									</button>
									<button type="reset" onclick="searchReset();return false;">
										重 置
									</button>
			                  </div>
			                 </td>
			              </tr>
		            </tfoot>
		            <tbody id="tbody_obj">
		              <tr>
		              	<!-- 循环出查询条件列表 -->
		              	<s:iterator value="tjList" id="tjobj" status="tjsta">
		              		<s:if test="(#tjsta.index%3==0) && (#tjsta.index != tjList.size() && (#tjsta.index!=0))">
		              			</tr><tr>
		              		</s:if>
		              		<th>
		              			${zdmc }
		              		</th>
		              		<td>
		              			<input type="text" name="cx_${zd}" style="width:150px" maxlength="15" id="${zd }" class="tj_${bqlx }"/>
		              		</td>
		              	</s:iterator>
		              </tr>
		            </tbody>
		          </table>
        	</div>
		</div>

		<div class="formbox">
			<table width="100%" class="dateline">
              <thead>
				<tr>
					<td>
						<input type="checkbox" name="ck" id="ck" onclick="selectPk(this,'pkValue')"/>
					</td>
				<s:iterator value="titList" id="titObj">
					<td>
						${mc }
					</td>
				</s:iterator>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="rsList" id="rsObj">
					<tr>
						<s:iterator value="rsObj" id="zd" status="sta" >
							
							<s:if test="#sta.index==0">
								<td>
									<input type="checkbox" id="pkValue" name="pkValue" value="${zd }" <s:iterator value="rsObj" id="innerzd" begin="1" end="1">${innerzd}</s:iterator>/>
								</td>
							</s:if>
							<s:elseif test="#sta.index==1">
								
							</s:elseif>
							<s:else>
								<td >
								${zd }
								</td>
							</s:else>
							
						</s:iterator>
					</tr>
				</s:iterator>
			</tbody>
			</table>
			<jsp:include page="/WEB-INF/pages/comm/pageFootMenu.jsp"></jsp:include>
		</div>
	</body>
		<input type="hidden" name="result" id="result" value="${result}"/>
	  <s:if test="result != null && result != ''">
	  	<script>
	  	//alert('${result}','',{'clkFun':function(){refershParent()}});
	  	</script>
	  </s:if>
	</s:form>
</html>
