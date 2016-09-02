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
        
        // 导出
        $("#btn_dc").click(function() {
          window.open("<%=request.getContextPath()%>/evaluation/student_exportSummaryAbsentByClass.html?" + $("#search").serialize());
        });
        
        fillRows("20", "", "", false);//填充空行
      });
    </script>
  </head>
  <body>
  <div class="toolbox">
      <div class="buttonbox">
        <ul>
          <li>
            <a id="btn_dc" class="btn_dc">导出考勤记录</a>
          </li>
        </ul>
      </div>
    </div>
    <form action="<%=request.getContextPath()%>/evaluation/student_summaryAbsentByClass.html" name="search" id="search" method="post">
      <div class="searchtab">
        <table width="100%" border="0">
          <tbody>
            <tr>
              <th>开课学院</th>
              <td>
                <ct:codePicker name="query.xy" catalog="<%=ICodeConstants.DM_DEF_ORG %>" code="${query.xy }" width="100"/>
              </td>
              <th>班级</th>
              <td>
                <input name="query.xzb" value="${query.xzb }" type="text" style="width: 100px;" class="text_nor"/>
              </td> 
              <th>教师姓名</th>
              <td>
                <input name="query.jsxm" value="${query.jsxm }" type="text" style="width: 100px;" class="text_nor"/>
              </td>            
              <th>月份</th>
              <td>
              	<input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" class="Wdate" name="query.month" id="schoolyear" value="${query.month}" style="width: 70px;" />
              </td>
            </tr>
          </tbody>
          <tfoot>
            <td colspan="8">
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
            <span>按班级课程统计缺勤学生情况</span>
        </h3>
        <!--标题end-->
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
              	<td>学院</td>
                <td>班级</td>  
                <td>课程名称</td>
                <td>教师姓名</td>
                <td>课程总学时</td>
                <td>学生缺勤情况</td>
              </tr>
            </thead>
            <tbody id="list_body">
              <c:forEach items="${pageList}" var="info" varStatus="st">
              <tr name="tr">
              	<td>${info.xy }</td>
              	<td>${info.xzb }</td>                
                <td>${info.kcmc }</td>
                <td>${info.jsxm }</td>
                <td>${info.kczxs }学时</td>
                <td>${info.qqxs }</td>
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