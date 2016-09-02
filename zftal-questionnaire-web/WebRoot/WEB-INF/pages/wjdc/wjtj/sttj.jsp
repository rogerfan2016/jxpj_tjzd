<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				
				jQuery("img.rsbfl").each(function(){
	                var stid = jQuery(this).attr("stid");
	                var xxrs = jQuery(this).attr("xxrs");
	                var zrs = jQuery("#sumnumber_person_"+stid).html();
	                var width = Math.round(xxrs*150/zrs);
	                jQuery(this).css("width",width+"px");
	            });
				jQuery("span.pjf").each(function(){
                    var zf = jQuery(this).attr("zf");
                    var zrs = jQuery(this).attr("zrs");
                    var pjf = Math.round(zf*10/zrs)/10;
                    jQuery(this).html(pjf);
                });
			});

			//查询结果
			function searchResult() {
				ajaxSubForm("form_djtj", "<%=systemPath %>/wjdc/wjtj_sttj.html");
			}
		</script>
	</head>

	<s:form action="/wjdc/wjtj_sttj.html" method="post" id="form_djtj" theme="simple">
	<body style="height: 950px">
		<!-- 表名 -->
		<input type="hidden" name="bm" id="bm" value="${sjyModel.bm }"/>
		<div class="tab_cur">
		</div>
			<!-- 类型ID -->
			<input type="hidden" name="lxid" id="lxid" value="${lxbt }"/>
			<input type="hidden" name="wjid" id="wjid" value="${wjid }"/>
			<input type="hidden" name="valueStr" id="valueStr" value="${valueStr }"/>
			
			<!-- 循环出类型 -->
			<div class="comp_title">
		      <ul class="cla_lxbt">
				<s:iterator value="lxbtList" >
					 <li id="${lxid}"><a href="#" onclick="location.href='<%=systemPath %>/wjdc/wjtj_sttj.html?lxid=${lxid }'+'&wjid=${wjid }';"><span>${lxmc}</span></a></li>
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
		          <!-- 
		            <tfoot>
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
		            </tfoot>
		            -->
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
		<div class="tab">
				<table class=formlist border=0 width="100%">
					<s:iterator value="rsList" id="rs">
					<s:if test="%{stModel.stlx=='STDL'}">
<!--						<thead>-->
<!--							<tr>-->
<!--								<th colspan=4>-->
<!--									<span>${rs.stModel.stmc}</span>-->
<!--								</th>-->
<!--							</tr>-->
<!--						</thead>-->
					</s:if>
					<s:else>
						<thead>
							<tr>
								<th colspan=3>
									<span>
									<s:if test="wjglModel.autoaddstbh=='true'">
										${rs.stModel.stbh}.
									</s:if>
									${rs.stModel.stmc}(${rs.stModel.stlxmc})
									</span>
								</th>
							</tr>
						</thead>
						<tbody>
<!--							<tr>-->
<!--								<th>${rs.stModel.stbh}</th>-->
<!--								<th>${rs.stModel.stmc}</th>-->
<!--								<th>${rs.stModel.stlxmc}</th>-->
<!--							</tr>-->
                            <s:if test="%{stModel.stlx=='DHWB'||stModel.stlx=='DHWBS'||stModel.stlx=='DXZH'||stModel.stlx=='DXSZH'}">
                                <tr><td colspan="3">
                                    <div style="margin-top: 5px; line-height:20px;">
	                                                                                          【<a href="stgl_textList.html?model.wjid=${rs.stModel.wjid }&model.stid=${rs.stModel.stid}" 
	                                       style="color:#45afe3;font-weight: bold;" data-rel="dialog" data-ajax="false">
	                                                                                            查看本题答案详细信息</a>】
                                    </div>
                                </td></tr>
                            </s:if>
							<s:if test="%{sttjOneList.size()>0}">
							<tr>
								<td colspan="3">
									<table class="dateline" width="100%">
										<s:if test="wjglModel.wjlx=='CPL'">
					                    <tr><td colspan="3">
		                                    <div style="margin-top: 5px; line-height:20px;font-weight: bold">
		                                      <h> 本题平均分：
		                                      <span style="color:red" id="sumnumber_${rs.stModel.stid}"
		                                       zf='${sttjOneList[fn:length(sttjOneList)-1].XXZF}' 
		                                       zrs='${sttjOneList[fn:length(sttjOneList)-1].XXRS}' 
		                                       class='pjf'>1</span>
		                                      </h>
		                                    </div>
		                                </td></tr>
                                        </tr>
			                            </s:if>
										<c:forEach items="${sttjOneList}" var="sttj" end="${fn:length(sttjOneList)-2}">
											<tr>
												<td width="30%">${sttj.XXMC}</td>
												<td align="center" width="10%">${sttj.XXRS}</td>
												<td>
                                                <img src="<%=systemPath %>/img/wjdc/mobile/vote_cl_v2.png"
                                                stid='${rs.stModel.stid}' xxrs='${sttj.XXRS}' class='rsbfl'
                                                style="height:15px;border-width:0px;">
                                                ${sttj.XXRSBFB}
                                                </td>
											</tr>
										</c:forEach>
                                        <tr>
                                            <td>本题有效填写人次</td>
                                            <td align="center" id="sumnumber_person_${rs.stModel.stid}">${sttjOneList[fn:length(sttjOneList)-1].XXRS }</td>
                                            <td></td>
                                        </tr>
									</table>
								</td>
							</tr>
							</s:if>
						</tbody>
					</s:else>
				</s:iterator>
					
					
				</table>
			</div>
		<!-- div class="formbox" style="display: none;">
			<table width="100%" class="dateline">
              <thead>
				<tr>
					<td>编号</td>
					<td>试题名称</td>
					<td>试题类型</td>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="rsList" id="rs">
					<s:if test="%{stModel.stlx=='STDL'}">
					<tr style="background-color: #F2F2F2">
						<td colspan="3">${rs.stModel.stmc}</td>
					</tr>
					</s:if>
					<s:else>
					<tr style="background-color: #e8f0fb">
						<td>${rs.stModel.stbh}</td>
						<td>${rs.stModel.stmc}</td>
						<td>${rs.stModel.stlxmc}</td>
					</tr>
					</s:else>
					<s:if test="%{sttjOneList.size()>0}">
					<tr>
						<td colspan="3">
							<table width="100%">
								<s:iterator value="sttjOneList" id="sttj">
									<tr>
										<td width="60%" style="background:#f3f5f8">${sttj.XXMC}</td>
										<td width="20%">${sttj.XXRS}</td>
										<td>${sttj.XXRSBFB}</td>
									</tr>
								</s:iterator>
							</table>
						</td>
					</tr>
					</s:if>
				</s:iterator>
			</tbody>
			</table>
			<div style="display: none;">

			</div>
		</div-->
	</body>
		<input type="hidden" name="result" id="result" value="${result}"/>
	  <s:if test="result != null && result != ''">
	  	<script>
	  	alert1('${result}','',{'clkFun':function(){refershParent()});
	  	</script>
	  </s:if>
	</s:form>
</html>
