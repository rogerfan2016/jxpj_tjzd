<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.zfsoft.wjdc.dao.entites.XxglModel"%>
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
		<script type="text/javascript" src="<%=systemPath %>/js/wjdc/wjtj.js"></script>
		<link rel="stylesheet" href="<%=systemPath %>/css/plugins/textClue.css" type="text/css" media="all" />
		<script type="text/javascript">
		
			//按钮绑定
			function bdan() {
				var btn_fh=jQuery("#btn_fh");//返回
				
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

			//查询结果
			function searchResult() {
				var groupFields=jQuery("#groupFields").val();
				var groupFields2=jQuery("#groupFields2").val();
				var stidx=jQuery("#stidx").val();
				var stidy=jQuery("#stidy").val();
				
				if(groupFields==""&&groupFields2==""&&stidx==""){
					alert("请选择分值项或分组试题！");
					return false;
				}
				if((groupFields!=""||groupFields2!="")&&stidx!=""){
					alert("分组项和分组试题只可选择其中一个！");
					return false;
				}
				if(groupFields!=""&&groupFields==groupFields2){
					alert("分组项一和分组项二不可以相同！");
					return false;
				}
				if(stidy==""){
					alert("请选择统计试题！");
					return false;
				}
				
				ajaxSubForm("form_djtj", "<%=systemPath %>/wjdc/wjtj_jctj.html");
			}
		</script>
	</head>

	<s:form action="/wjdc/wjtj_jctj.html" method="post" id="form_djtj" theme="simple">
	<body style="height: 950px">
		<!-- 表名 -->
		<input type="hidden" name="bm" id="bm" value="${sjyModel.bm }"/>
		
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>问卷调查管理 - 问卷分发 - 交叉统计</a>
			</p>	
		</div>
			<!-- 类型ID -->
			<input type="hidden" name="lxid" id="lxid" value="${lxbt }"/>
			<input type="hidden" name="wjid" id="wjid" value="${wjid }"/>
			<input type="hidden" name="valueStr" id="valueStr" value="${valueStr }"/>
			
			<!-- 循环出类型 -->
			<div class="comp_title">
		      <ul class="cla_lxbt">
				<s:iterator value="lxbtList" >
					 <li id="${lxid}"><a href="#" onclick="location.href='<%=systemPath %>/wjdc/wjtj_jctj.html?lxid=${lxid }'+'&wjid=${wjid }';"><span>${lxmc}</span></a></li>
				</s:iterator>
		      </ul>
		    </div>
		
		<div class="toolbox">
			<!-- 加载当前菜单栏目下操作     -->
			
			<div class="buttonbox">
			<ul id="but_ancd">
<!--					<li>-->
<!--						<a href="javascript:void(0);" id="btn_zj" class="btn_zj" >-->
<!--							增加</a>-->
<!--					</li>-->
<!--					<li>-->
<!--						<a href="javascript:void(0);" id="btn_sc" class="btn_sc" >-->
<!--							删除</a>-->
<!--					</li>-->
<!--					<li>-->
<!--						<a href="javascript:void(0);" id="btn_fb" class="btn_shtg" >-->
<!--							分发</a>-->
<!--					</li>-->
					<li>
						<a href="javascript:void(0);" id="btn_fh" class="btn_fh" >
							返回</a>
					</li>
			</ul>
		</div>
			
			<!-- 加载当前菜单栏目下操作 -->

			<div class="searchtab">
		          <table width="100%">
		            <!-- tfoot>
					<tr>
                		<td colspan="6">
                				<div class="btn">
                   					<button type="button" id="search_go"
										onclick="searchResult();" >
										统 计
									</button>
									<button type="reset" onclick="searchReset();return false;">
										重 置
									</button>
			                  </div>
			                 </td>
			              </tr>
		            </tfoot-->
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
		              <!-- tr>
		              	<th>分组项</th>
		              	<td>
		              		<s:select list="jgList" listKey="zd" listValue="zdmc" name="groupFields" headerKey="" headerValue=""></s:select>
		              	</td>
		              	<th>分组试题</th>
		              	<td>
		              		<s:select name="stidx" id="stidx" list="wjstList" listKey="stid" listValue="stmc" headerKey="" headerValue=""></s:select>
		              	</td>
		              	<th>统计试题</th>
		              	<td>
		              		<s:select name="stidy" id="stidy" list="wjstList" listKey="stid" listValue="stmc" headerKey="" headerValue=""></s:select>
		              	</td>
		              </tr-->
		            </tbody>
		          </table>
        	</div>
		</div>
		
		<div class="searchbox">
				<table class=searchbox_cx width="100%">
					<tbody>
						<tr>
			              	<td>
			              		分组项一&nbsp;<s:select name="groupFields" value="#{groupFields[0]}" cssStyle="width:200px;" id="groupFields" list="jgList" listKey="zd" listValue="zdmc" headerKey="" headerValue=""></s:select>
			              	</td>
			              	<td>
			              		分组项二&nbsp;<s:select name="groupFields" value="#{groupFields[1]}" cssStyle="width:200px;" id="groupFields2" list="jgList" listKey="zd" listValue="zdmc" headerKey="" headerValue=""></s:select>
			              	</td>
							<td rowspan="2">
								<div class="btn">
                   					<button type="button" id="search_go"
										onclick="searchResult();" >
										统 计
									</button>
									<button type="reset" onclick="searchReset();return false;">
										重 置
									</button>
			                  </div>
							</td>
						</tr>
						<tr>
							<td>
			              		分组试题&nbsp;<s:select name="stidx" cssStyle="width:200px;" id="stidx" list="wjstList" listKey="stid" listValue="stmc" headerKey="" headerValue=""></s:select>
			              	</td>
			              	<td>
			              		统计试题&nbsp;<s:select name="stidy" cssStyle="width:200px;" id="stidy" list="wjstList" listKey="stid" listValue="stmc" headerKey="" headerValue=""></s:select>
			              	</td>
						</tr>
					</tbody>
				</table>
			</div>

		<div class="formbox">
			<table width="100%" class="dateline">
              <thead>
				<tr>
					<td colspan="${xy_colspan}">x/y</td>
					<s:iterator value="xxList" id="xx">
						<td colspan="2">${xx.xxmc}</td>
					</s:iterator>
					<td>小计</td>
				</tr>
			</thead>
			<tbody>
				<%
				List<HashMap<String,Object>> rsList=(List<HashMap<String,Object>>)request.getAttribute("rsList");
				List<XxglModel> xxList=(List<XxglModel>)request.getAttribute("xxList");
				String[] groupFields=(String[])request.getAttribute("groupFields");
				String xy_colspan=(String)request.getAttribute("xy_colspan");
				for(HashMap<String,Object> rs : rsList){
					%><tr><%
					for(int i=0;i<groupFields.length;i++){
						if(groupFields[i]!=null&&!"".equals(groupFields[i])){
							if("1".equals(xy_colspan)){
								%><td><%=rs.get("XXMC"+i)==null?"":rs.get("XXMC"+i)%></td><%
							}else{
								if(rs.get("XXMC"+i+"COLSPANNO")==null)
								if(rs.get("XXMC"+i+"ROWSPAN")!=null){//该处是用于处理多个分组字段需要合并单元格的处理
									%><td rowspan="<%=rs.get("XXMC"+i+"ROWSPAN") %>"
									<%if(rs.get("XXMC"+i+"COLSPAN")!=null){ %>
										colspan="<%=rs.get("XXMC"+i+"COLSPAN") %>"
									<%} %> 
										  >
										<%=rs.get("XXMC"+i)==null?"":rs.get("XXMC"+i)%></td><%
								}
							}
						}
					}
					for(XxglModel xx : xxList){
						%><td width="1%">
						<%=rs.get(xx.getXxid().toUpperCase()+"_RS")==null?"":rs.get(xx.getXxid().toUpperCase()+"_RS")%>
						</td>
						<td width="1%">
						<%=rs.get(xx.getXxid().toUpperCase()+"_RSBFB")==null?"":rs.get(xx.getXxid().toUpperCase()+"_RSBFB")%>
						</td><%
					}
					%><td><%=rs.get("ZRS")%></td><%
					%></tr><%
				}				
				 %>
				<s:iterator value="rsList" id="rs">
					<tr>
						<td>${rs.xxmc}</td>
						<td>${rs.stModel.stlxmc}</td>
						<td>${rs.stModel.stlxmc}</td>
					</tr>
				</s:iterator>
			</tbody>
			</table>
		</div>
	</body>
		<input type="hidden" name="result" id="result" value="${result}"/>
	  <s:if test="result != null && result != ''">
	  	<script>
	  	alert1('${result}','',{'clkFun':function(){refershParent()});
	  	</script>
	  </s:if>
	</s:form>
</html>
