<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ct" uri="/custom-code"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <%@ include file="/commons/hrm/head.ini"%>
    <script type="text/javascript">
      $(function() {
        // 查询
        $("#search_btn").click(function() {
          $("#search").submit();
        });
        
        fillRows("20", "", "", false);//填充空行
      });

      // 评教
      function doEvaluation(pjid) {
        showWindow("查看问卷", "<%=request.getContextPath()%>/evaluation/setting_evaluation.html?pjid=" + pjid, "480","230");
      }
      
    </script>
  </head>
  <body>
    <form action="<%=request.getContextPath()%>/evaluation/student_evaluation.html" name="search" id="search" method="post">
      <div class="searchtab">
        <table width="100%" border="0">
          <tbody>
            <tr>
              <th width="60">状态</th>
              <td>
                <select name="query.pjqk" style="width:100px;">
                  <option value="">全部</option>
                  <option value="0" <c:if test="${query.pjqk eq '0'}">selected</c:if>>未评价</option>
                  <option value="1" <c:if test="${query.pjqk eq '1'}">selected</c:if>>已评价</option>
                </select>
              </td>
            </tr>
          </tbody>
          <tfoot>
            <td colspan="2">
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
            <span>评教管理</span>
        </h3>
        <!--标题end-->
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
                <td>上课时间</td>
                <td>星期</td>
                <td>课程名称</td>
                <td>任课老师</td>
                <td>课程节次</td>
                <td>操作</td>
              </tr>
            </thead>
            <tbody id="list_body">
              <c:forEach items="${pageList}" var="info" varStatus="st">
              <tr name="tr">
                <td>${info.curriculum.kcsj }</td>
                <td>${info.dayofweek }</td>
                <td>${info.curriculum.kcmc }</td>
                <td>${info.curriculum.rkls }</td>
                <td>${info.curriculum.kcjc }</td>
                <td>
                  <a style="color:#074695" href="#" onclick="doEvaluation('${info.checkInEntity.pjid}');">评教</a>
                </td>
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