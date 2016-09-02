<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.zfsoft.hrm.config.ICodeConstants"%>
<%@taglib prefix="ct" uri="/custom-code"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <%@ include file="/commons/hrm/head.ini"%>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/hrm/code.js"></script>
    <script type="text/javascript">
      $(function() {
        // 查询
        $("#search_btn").click(function() {
          $("#search").submit();
        });
        fillRows("20", "", "", false);//填充空行
      });
    </script>
  </head>
  <body>
    <form action="<%=request.getContextPath()%>/evaluation/student_checkinAllByGrade.html" name="search" id="search" method="post">
      <div class="searchtab">
        <table width="100%" border="0">
          <tbody>
            <tr>
              <th>学院</th>
              <td>
                <ct:codePicker name="query.xy" catalog="<%=ICodeConstants.DM_DEF_ORG %>" code="${query.xy }" width="100"/>
              </td>
              <th>年级</th>
              <td>
                <input type="text" onfocus="WdatePicker({dateFmt:'yyyy'})" class="Wdate" name="query.nj" id="schoolyear" value="${query.nj}" style="width: 70px;" />
              </td>            
              <th>月份</th>
              <td>
              	<input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" class="Wdate" name="query.month" id="schoolyear" value="${query.month}" style="width: 70px;" />
              </td>
            </tr>
          </tbody>
          <tfoot>
            <td colspan="6">
              <div class="btn">
                <button class="brn_cx" type="button" id="search_btn" >查询</button>
              </div>
            </td>
          </tfoot>
        </table>
      </div>
      <div class="formbox">
        <!--标题start-->
        <h3 class="datetitle_01">
            <span>按年级统计上课考勤情况</span>
        </h3>
        <!--标题end-->
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
              	<td>学院</td>
                <td>年级</td>
                <td>考勤次数</td>
                <td>出勤次数</td>
                <td>旷课次数</td>
                <td>事假次数</td>
                <td>病假次数</td>
                <td>迟到次数</td>
                <td>出勤比例</td>
                <td>缺勤比例</td>
              </tr>
            </thead>
            <tbody id="list_body">
              <c:forEach items="${pageList}" var="info" varStatus="st">
              <tr name="tr">
              	<td>${info.xy }</td>
              	<td>${info.nj }</td>
                <td><fmt:formatNumber value="${info.kqcs }"/></td>
                <td><fmt:formatNumber value="${info.cqcs }"/></td>
                <td>${info.kkcs }</td>
                <td>${info.sjcs }</td>
                <td>${info.bjcs }</td>
                <td>${info.cdcs }</td>
                <td>${info.cqbl }</td>
                <td>${info.qqbl }</td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        <ct:page pageList="${pageList }"  query="${query }" queryName="query"/>
      </div>
    </form>
  </body>
</html>