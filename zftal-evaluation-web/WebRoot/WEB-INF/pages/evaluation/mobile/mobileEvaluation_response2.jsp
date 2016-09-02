<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ct" uri="/custom-code"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/pages/mobile/meta.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <%@ include file="/WEB-INF/pages/wjdc/mobile/mobilePage.ini"%>
    <link rel="stylesheet" href="<%=stylePath %>css/public.css" type="text/css" media="all" />
    <link rel="stylesheet" href="<%=systemPath %>/css/mobile/mobile.css"/>
    <script type="text/javascript" src="<%=systemPath %>/js/mobile/pageTag.js"></script>
    <script type="text/javascript">
      $(function() {
        $(".header_nav").find("a").each(function() {
          if ($(this).attr("pjzt") == "${pjzt}") {
            $(this).parent("li").addClass("nav_active");
          }
          $(this).click(function() {
            $("#pjzt").val($(this).attr("pjzt"));
            $("#wjListForm").submit();
          });
        });
        
        showButton();
        
        $("tr[name^=hdnTr_]").hide();
        $("tr[name^=hdnTr_]").each(function() {
          $(this).find("input[name='isshow']").val("no");
        });
        
        $("tr[name='tr']").click(function() {
          var id = $(this).find("input[name='globalid']").val();
          var isshow = $("tr[name^=hdnTr_" + id + "]").find("input[name='isshow']");
          if ($(isshow).val() == "no") {
            $("tr[name^=hdnTr_]").hide();
            $("tr[name^=hdnTr_]").each(function() {
              $(this).find("input[name='isshow']").val("no");
            });
            $("tr[name^=hdnTr_" + id + "]").show();
            $(isshow).val("yes");
          } else {
            $("tr[name^=hdnTr_" + id + "]").hide();
            $(isshow).val("no");
          }
        });
      });

      // 评教
      function doEvaluation(wjid, djrid, pjid) {
        location.href = _path + "/wjdc_mobile/stgl_yhdj.html?wjModel.wjid=" + wjid + "&wjModel.djrid=" + djrid + "&wjModel.pjid=" + pjid;
      }
    </script>
  </head>
  <body>
  <header data-am-widget="header" class="am-header am-header-default">
      <div class="am-header-left am-header-nav">
          <a href="../wjdc_mobile/index_initMenu.html" class="">
              <img class="am-header-icon-custom" src="data:image/svg+xml;charset=utf-8,&lt;svg xmlns=&quot;http://www.w3.org/2000/svg&quot; viewBox=&quot;0 0 12 20&quot;&gt;&lt;path d=&quot;M10,0l2,2l-8,8l8,8l-2,2L0,10L10,0z&quot; fill=&quot;%23fff&quot;/&gt;&lt;/svg&gt;" alt=""/>
          </a>
      </div>

      <h1 class="am-header-title">教师评价</h1>
  </header>
    <div class="header">
      <ul class="header_nav" style="width:180px;">
        <li><a href="javascript:;" pjzt="0">未评价</a></li>
        <li><a href="javascript:;" pjzt="1">已评价</a></li>
      </ul>
    </div>
    
    <form action="<%=request.getContextPath()%>/evaluation/mobile_response2.html" name="wjListForm" id="wjListForm" method="post">
    <input type="hidden" name="pjzt" id="pjzt" value="${pjzt}"/>
    <c:if test="${fn:length(questionnaires) == 0}">
      <div style="padding-top:30px;text-align:center;height:80px;">
        <span style="color:green;font-size:14px;">暂无记录</span>
      </div>
    </c:if>
    <c:if test="${fn:length(questionnaires) > 0}">
      <table width="100%" class="wjList">
        <tbody>
          <c:forEach items="${questionnaires}" var="info" varStatus="st">
          <tr name="tr">
            <td width="30" align="center">${st.index + 1 }</td>
            <td class="sapceTd">
              <input type="hidden" name="globalid" value="${info.xwjid }"/>
            </td>
            <td class="autocut" style="padding-left: 10px; white-space:normal;">
            [${info.wjmc }]${info.curriculum.kcmc}
            <br/>上课时间：${info.curriculum.kcsj} 第${info.curriculum.kcjc}节
            </td>
            <td width="70" align="center">
              <c:if test="${0 eq info.pjzt}">
                <a href="#" onclick="doEvaluation('${info.xwjid}', '${info.pjryid}', '${info.pjid}');">
                  <div class="miniButton">评价</div>
                </a>
              </c:if>
              <c:if test="${0 != info.pjzt}">
                <a href="#" onclick="doEvaluation('${info.xwjid}', '${info.pjryid}');">
                  <div class="miniButton">查看</div>
                </a>
              </c:if>
            </td>
          </tr>
          <tr name="hdnTr_${info.xwjid }">
            <td colSpan="5" class="hdnInfoTd">
              <input type="hidden" name="isshow" value="no"/>
              <div>
              </br>上课地点：${info.curriculum.skdd}
              </br>上课时间：${info.curriculum.kcsj}
              </br>上课节次：${info.curriculum.kcjc}
              </br>任课老师：${info.curriculum.rkls}
              </br>&nbsp;
              </div>
            </td>
          </tr>
          </c:forEach>
        </tbody>
      </table>
      <%@ include file="/WEB-INF/pages/mobile/bottomTag.jsp"%>
    </c:if>
    </form>
    <%@ include file="/WEB-INF/pages/mobile/navbar.jsp" %>
  </body>
</html>