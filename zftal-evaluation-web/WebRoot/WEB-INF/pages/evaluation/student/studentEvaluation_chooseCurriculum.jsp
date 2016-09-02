<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ct" uri="/custom-code"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <%@ include file="/commons/hrm/head.ini"%>
    <script type="text/javascript">
      
      function doSubmit(globalid, type) {
        var param = "<%=request.getContextPath() %>/evaluation/student_curriculumDetail.html";
        param += "?globalid=" + globalid + "&type=" + type;
        var message = "";
        if (type == "1") {
          message = "教学日志";
        } else {
          message = "考勤及评教";
        }
        //showWindow(message, param, "480", "550");
        showTopWin(param, "480","550","yes");
      }
      
    </script>
  </head>
  <body>
    <div class="formbox">
      <!--标题start-->
      <h3 class="datetitle_01">
        <span>选课列表</span>
      </h3>
      <!--标题end-->
      <div class="con_overlfow">
        <table width="100%" class="dateline tablenowrap">
          <thead id="list_head">
            <tr>
              <td>上课时间</td>
              <td>课程名称</td>
              <td>课程节次</td>
              <td>操作</td>
            </tr>
          </thead>
          <tbody id="list_body">
            <c:forEach items="${teachingEntities}" var="info" varStatus="st">
            <tr name="tr">
              <td>${info.kcsj }</td>
              <td>${info.kcmc }</td>
              <td>${info.kcjc }</td>
              <td>
                <a style="color:#074695" href="#" onclick="doSubmit('${info.globalid}', 1);">教学日志</a>
                <c:if test="${lx eq 'teacher' }"><a style="color:#074695" href="#" onclick="doSubmit('${info.globalid}', 2);">考勤及评教</a></c:if>
              </td>
            </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </body>
</html>