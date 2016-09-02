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
			
			//按钮绑定
			function bdan() {
				var btn_fh=jQuery("#btn_fh");//增加
				var btn_ck=jQuery("#btn_ck");//删除
				
				if (btn_fh != null) {
					btn_fh.click(function () {
						location.href='<%=systemPath %>/wjdc/wjffgl_cxWjffxx.html';
					});
				}

				
				if (btn_ck != null) {
					btn_ck.click(function () {
						ckwj();
					});
				}
			}
			//数据加载
			jQuery(document).ready(function(){
				bdan();
				loadOption();
				dispFiledValue();
				
				var lxbt = jQuery('#lxid').val();
				if (lxbt != null) {
					jQuery('#'+lxbt).addClass("ha");
				}
			});

			function refreshSearch(id,url){
				jQuery("#"+id).attr("action",url);
				jQuery("#"+id).attr("method","post");
				jQuery("#"+id).submit();
			}
			//查询结果
			function searchResult() {
			
				var id = "form_djxq";
				var url = "<%=systemPath %>/wjdc/wjtj_djxq.html";				
				refreshSearch(id,url);				
			}
			/*
			//查询结果
			function searchResult() {
				ajaxSubForm("form_djxq","<%=systemPath %>/wjdc/wjtj_djxq.html");
			}
			*/
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
				if (valueStr != "" && valueStr != null) {
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
			
			//查看问卷
			function ckwj(){
				var pkObj = jQuery("input[name='pkValue']").filter(":checked");
				if(pkObj.length!=1){
					alert("请选择一条记录！");
					return false;					
				}
				//alert(pkObj[0].value);
				var djrid=pkObj[0].value;
				var wjid=jQuery("#wjid").val();
				var url= _path+"/wjdc/stgl_yhdj.html?wjModel.wjid="+wjid+"&wjModel.djrid="+djrid;
				window.open(url);
			}
			
			//页签切换
			function yqqh(lxid){
				jQuery("#lxid").val(lxid);
				var inputs=document.getElementById("tbody_obj").getElementsByTagName("input");
				for(var i=0;i<inputs.length;i++){
					inputs[i].value="";
				}
				searchResult();
			}
		</script>
	</head>

	<body>
	<s:form action="/wjdc/wjtj_djxq.html" id="form_djxq" method="post" theme="simple">
		<!-- 表名 -->
		<input type="hidden" name="bm" id="bm" value="${sjyModel.bm }"/>
		
			<!-- 类型ID -->
			<input type="hidden" name="lxid" id="lxid" value="${lxbt }"/>
			<!-- 问卷ID -->
			<input type="hidden" name="wjid" id="wjid" value="${wjid }"/>
			<input type="hidden" name="valueStr" id="valueStr" value="${valueStr }"/>
			
			
			<!-- 循环出类型 -->
			<div class="comp_title">
		      <ul class="cla_lxbt">
				<s:iterator value="lxbtList" >
					 <li id="${lxid}"><a href="#" onclick="yqqh('${lxid}')"><span>${lxmc}</span></a></li>
				</s:iterator>
		      </ul>
		    </div>
		    
		
		<div class="toolbox">
			<!-- 加载当前菜单栏目下操作     -->
			
			<div class="buttonbox">
			<ul id="but_ancd">
					<li>
						<a href="javascript:void(0);" id="btn_ck" class="btn_ck" >
							查看</a>
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
						<s:iterator value="rsObj" id="zd" status="sta">
							<td>
							<s:if test="#sta.index==0">
								<input type="checkbox" id="pkValue" name="pkValue" value="${zd }" />
							</s:if>
							<s:else>
								${zd }
							</s:else>
							</td>
						</s:iterator>
					</tr>
				</s:iterator>
			</tbody>
			</table>
			<jsp:include page="/WEB-INF/pages/comm/pageFootMenu.jsp"></jsp:include>
		</div>
	</s:form>
	</body>
	  <s:if test="result != null && result != ''">
	  	<script>
	  	alert('${result}','',{'clkFun':function(){refershParent()}});
	  	</script>
	  </s:if>
	
</html>
