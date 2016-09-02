<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ct" uri="/custom-code"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <%@ include file="/commons/hrm/head.ini"%>
    <script type="text/javascript">
      function viewQuestionnaire(wjid, djrid) {
        var url = _path + "/wjdc/stgl_yhdj.html?wjModel.wjid=" + wjid + "&wjModel.djrid=" + djrid;
        window.open(url);
      }
    </script>
  </head>
  <body>
    <form action="<%=request.getContextPath()%>/evaluation/setting_evaluation.html" method="post">
      <div class="formbox">
        <!--标题start-->
        <h3 class="datetitle_01">
          <span>问卷列表</span>
        </h3>
        <!--标题end-->
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
                <td>序号</td>
                <td>问卷名称</td>
                <td>评教状态</td>
                <td>操作</td>
              </tr>
            </thead>
            <tbody id="list_body">
              <c:forEach items="${questionnaires}" var="info" varStatus="st">
              <tr name="tr">
                <td>${st.index+ 1 }</td>
                <td>${info.wjmc }</td>
                <td>
                  <c:if test="${info.pjzt eq '0' }">未评价</c:if>
                  <c:if test="${info.pjzt eq '1' }">已评价</c:if>
                </td>
                <td>
                  <c:if test="${info.pjzt eq '0' }"><a style="color:#074695" href="#" onclick="viewQuestionnaire('${info.xwjid}', '${info.pjryid}');">我要评价</a></c:if>
                  <c:if test="${info.pjzt eq '1' }"><a style="color:#074695" href="#" onclick="viewQuestionnaire('${info.xwjid}', '${info.pjryid}');"">查看评价</a></c:if>
                </td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </form>
  </body>
</html>