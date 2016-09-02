<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="ct" uri="/custom-code"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/WEB-INF/pages/mobile/meta.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
    <title>${wjModel.wjmc }- ${model.stmc }</title>
    <%@ include file="/WEB-INF/pages/wjdc/mobile/mobilePage.ini"%>
    <meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1"/>
    <link rel="stylesheet" href="<%=systemPath %>/css/wjdc/mobile/mobile.css"/>
    <link rel="stylesheet" href="<%=systemPath %>/css/wjdc/mobile/list.css"/>
    <script type="text/javascript" src="<%=systemPath %>/js/hrm/page.js"></script>
    <style type="text/css">
    .pageleft{
    padding-left:0.5em;
    float:left;
    color:#000;
    }
    .pageright{
    padding-right:1.2em;
    float:right;
    }
    
    </style>
    
    </head>
<body>

    <header data-am-widget="header" class="am-header am-header-default">
      <div class="am-header-left am-header-nav">
          <a href="javascript:history.go(-1);" class="">
              <img class="am-header-icon-custom" src="data:image/svg+xml;charset=utf-8,&lt;svg xmlns=&quot;http://www.w3.org/2000/svg&quot; viewBox=&quot;0 0 12 20&quot;&gt;&lt;path d=&quot;M10,0l2,2l-8,8l8,8l-2,2L0,10L10,0z&quot; fill=&quot;%23fff&quot;/&gt;&lt;/svg&gt;" alt=""/>
          </a>
      </div>

      <h1 class="am-header-title">
         ${wjModel.wjmc }- ${model.stmc }
      </h1>
  </header>
    <form method="post" action="stgl_textList.html">
		<div>
		    <input name="model.wjid" value="${model.wjid }" type="hidden">
		    <input name="model.stid" value="${model.stid }" type="hidden">
		</div>
        
        <div style="margin:15px 10px; text-align: left;">
	        <div id="divData">
	            <div>
				    <table class="tableResult" id="grvwJoinActivityList" style="color:#333333;border-color:#999999;border-width:1px;border-style:Solid;width:100%;border-collapse:collapse;" border="0" cellspacing="0">
				        <tbody>
				        <tr style="color:White;background-color:Gray;" align="center" valign="middle">
				            <th scope="col">序号</th><th scope="col" style="color:White;" align="left">答案文本</th>
				        </tr>
				        <s:iterator value="pageList" var="xx" status="st">
				            <tr style="color:#333333;background-color:#DFF2FC;border-color:#999999;font-size:12px;height:20px;">
					            <td style="color:#000666;height:18px;width:50px;" align="center">
					               ${st.index + model.queryModel.currentResult +1}
					            </td>
					            <td style="color:Black;height:18px;" align="left">
					                    <span>${xx.txnr}</span>
					            </td>
				            </tr>
				        </s:iterator>
				        <c:if test="${fn:length(pageList)==0}">
				        <tr>
				            <td colspan="2" align="center" style="color: red">暂无数据</td>    
				        </tr>
				        </c:if>
				        </tbody>
				    </table>
	            </div>
	            <div style="margin-top:5px;"></div>
	            <ct:page pageList="${pageList}" queryName="model.queryModel" query="${model.queryModel}"/>
	        </div>
       </div>
    </form>
</body></html>