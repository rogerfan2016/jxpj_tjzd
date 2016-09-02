<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/WEB-INF/pages/mobile/meta.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head id="Head1">
<title>
    ${wjglModel.wjmc }- 统计&amp;分析
</title>
    <%@ include file="/WEB-INF/pages/wjdc/mobile/mobilePage.ini"%>
    <meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1"/>
    <link rel="stylesheet" href="<%=systemPath %>/css/wjdc/mobile/mobile.css"/>
    <link rel="stylesheet" href="<%=systemPath %>/css/wjdc/mobile/list.css"/>

    <script type="text/javascript">
        $(function(){
            $("img.rsbfl").each(function(){
                var stid = $(this).attr("stid");
                var xxrs = $(this).attr("xxrs");
                var zrs = $("#sumnumber_person_"+stid).html();
                var width = Math.round(xxrs*150/zrs);
                $(this).css("width",width+"px");
            });
            $("span.pjf").each(function(){
                var zf = $(this).attr("zf");
                var zrs = $(this).attr("zrs");
                var pjf = Math.round(zf*10/zrs)/10;
                $(this).html(pjf);
            });
        });
    </script>
</head>
<body>
    <header data-am-widget="header" class="am-header am-header-default">
      <div class="am-header-left am-header-nav">
          <a href="javascript:history.go(-1);" class="">
              <img class="am-header-icon-custom" src="data:image/svg+xml;charset=utf-8,&lt;svg xmlns=&quot;http://www.w3.org/2000/svg&quot; viewBox=&quot;0 0 12 20&quot;&gt;&lt;path d=&quot;M10,0l2,2l-8,8l8,8l-2,2L0,10L10,0z&quot; fill=&quot;%23fff&quot;/&gt;&lt;/svg&gt;" alt=""/>
          </a>
      </div>

      <h1 class="am-header-title">
         统计结果
      </h1>
  </header>
     <div style="margin:10px 2%;">
            <!-- 表名 -->
        <input type="hidden" name="bm" id="bm" value="${sjyModel.bm }"/>
        
        <div class="tab_cur">
        </div>
                <s:iterator value="rsList" var="rs">
                    <s:if test="%{stModel.stlx=='STDL'}">
                    </s:if>
                    <s:else>
                        <div style="margin-bottom:20px;">
                        <div id="dtlQuestionCounter_${rs.stModel.stid}_spanTitle" style="line-height: 24px; margin-top:10px;">
                            <span id="dtlQuestionCounter_${rs.stModel.stid}_lbQuestionDesp" onclick="">
                                    <s:if test="wjglModel.autoaddstbh=='true'">
                                        ${rs.stModel.stbh}.
                                    </s:if>
                                    ${rs.stModel.stmc}(${rs.stModel.stlxmc})
                            </span>
                            &nbsp;
                        </div>
                        <s:if test="%{stModel.stlx=='DHWB'||stModel.stlx=='DHWBS'||stModel.stlx=='DXZH'||stModel.stlx=='DXSZH'}">
                        <div id="dtlQuestionCounter_${rs.stModel.stid}_divDetail" style="margin-top: 5px; line-height:20px;">
			                                 【<a href="stgl_textList.html?model.wjid=${rs.stModel.wjid }&model.stid=${rs.stModel.stid}" id="dtlQuestionCounter_${rs.stModel.stid}_hrefQuestion" class="link-U00a6e6" style="font-weight: bold;" data-rel="dialog" data-ajax="false">查看本题答案详细信息</a>】
			            </div>
			            </s:if>
			            <s:if test="%{stModel.stlx=='DX'||stModel.stlx=='DXS'||stModel.stlx=='DXZH'||stModel.stlx=='DXSZH'}">
                        <div id="dtlQuestionCounter_${rs.stModel.stid}_divData" style="clear: both;">
                            <s:if test="wjglModel.wjlx=='CPL'">
                            <div id="dtlQuestionCounter_${rs.stModel.stid}_divResult" class="itemdata" style="padding-top: 10px; padding-left: 5px;">
                                <span id="dtlQuestionCounter__${rs.stModel.stid}_lblValue">
                                    <b>本题平均分：
                                        <span style="color:red" id="sumnumber_${rs.stModel.stid}"
                                               zf='${sttjOneList[fn:length(sttjOneList)-1].XXZF}' 
                                               zrs='${sttjOneList[fn:length(sttjOneList)-1].XXRS}' 
                                               class='pjf'>0</span>
                                    </b>
                                </span>
                            </div>
                            </s:if>
                            <div id="dtlQuestionCounter_${rs.stModel.stid}_divResult" class="itemdata" style="padding-top: 10px; padding-left: 5px;">
                                <div style="overflow-y: auto;width:100%;">
                                    <table id="dtlQuestionCounter_${rs.stModel.stid}_tbItemCounter" class="tableResult" chartid="c10000" style="background-color:#D9E5ED;font-size:12px;width:100%;border-collapse:collapse;" border="0" cellpadding="3" cellspacing="0">
									    <thead>
									        <tr id="dtlQuestionCounter_${rs.stModel.stid}_titleRow" class="text2" style="font-weight:bold;" align="center">
									            <td style="cursor: pointer;">选项</td>
									            <td style="cursor: pointer;" align="center">小计</td>
									            <td align="center">比例</td>
									        </tr>
									    </thead>
									<tbody>
									   <c:if test="${fn:length(sttjOneList)>1}">
									    <c:forEach items="${sttjOneList}" var="sttj" end="${fn:length(sttjOneList)-2}">
									       <tr style="background:white">
                                                <td val="1">${sttj.XXMC}</td>
                                                <td align="center">${sttj.XXRS}</td>
                                                <td>
                                                <img src="<%=systemPath %>/img/wjdc/mobile/vote_cl_v2.png"
                                                stid='${rs.stModel.stid}' xxrs='${sttj.XXRS}' class='rsbfl'
                                                style="height:15px;border-width:0px;">
                                                ${sttj.XXRSBFB}
                                                </td>
                                            </tr>
									    </c:forEach>
									    </c:if>
								    </tbody><tfoot>
								        <tr>
								            <td><b>本题有效填写人次</b></td><td align="center"><b id="sumnumber_person_${rs.stModel.stid}">${sttjOneList[fn:length(sttjOneList)-1].XXRS }</b></td><td></td>
								        </tr>
								    </tfoot>
								</table>
							</div>
						</div>
						</s:if>
                    </s:else>
                </s:iterator>
      </div> 
</body></html>