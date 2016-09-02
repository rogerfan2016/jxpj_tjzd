<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.zfsoft.hrm.config.ICodeConstants"%>
<%@taglib prefix="ct" uri="/custom-code"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <%@ include file="/commons/hrm/head.ini"%>
    <script type="text/javascript">
      $(function() {
        // 查询
        $("#search_btn").click(function() {
        	if($.trim($("input[name='query.xzb']").val()).length == 0) {
     			alert("请输入班级查询条件！");
     			return false;
        	}
        	if($.trim($("input[name='query.month']").val()).length == 0) {
     			alert("请输入月份查询条件！");
     			return false;
        	}
          	$("#search").submit();
        });
        
        fillRows("20", "", "", false);//填充空行
      });
    </script>
  </head>
  <body>
    <form action="<%=request.getContextPath()%>/evaluation/student_checkinAll.html" name="search" id="search" method="post">
      <div class="searchtab">
        <table width="100%" border="0">
          <tbody>
            <tr>
              <th>学号</th>
              <td>
                <input name="query.xsid" value="${query.xsid }" type="text" style="width: 100px;" class="text_nor"/>
              </td>
              <th>姓名</th>
              <td>
                <input name="query.xsxm" value="${query.xsxm }" type="text" style="width: 100px;" class="text_nor"/>
              </td>
              <th>班级</th>
              <td>
                <input name="query.xzb" value="${query.xzb }" type="text" style="width: 100px;" class="text_nor"/>
              </td>            
              <th>月份</th>
              <td>
              	<input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" class="Wdate" name="query.month" id="schoolyear" value="${query.month}" style="width: 70px;" />
              </td>
            </tr>
          </tbody>
          <tfoot>
            <td colspan="10">
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
            <span>上课考勤查询</span>
        </h3>
        <!--标题end-->
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
                <td>学号</td>
                <td>姓名</td>
                <td>学院</td>
                <td>班级</td>
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
                <td>${info.xsid }</td>
                <td>${info.xsxm }</td>
                <td><ct:codeParse code="${info.xy }" catalog="<%=ICodeConstants.DM_DEF_ORG %>" /></td>
                <td>${info.xzb }</td>
                <td>${info.kqcs }</td>
                <td>${info.cqcs }</td>
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