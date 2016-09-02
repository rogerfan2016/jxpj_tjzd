<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ct" uri="/custom-code"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <%@ include file="/commons/hrm/head.ini"%>
    <script type="text/javascript">
      $(function() {
        // 增加
        $("#btn_zj").click(function() {
          showWindow("听课申请设置", "<%=request.getContextPath()%>/evaluation/teacher_setting.html", "480","230");
        });
        
        // 刷新状态
        $("#btn_sxzt").click(function() {
          $.post("<%=request.getContextPath()%>/evaluation/teacher_refresh.html",null,function(data) {
            if(data.success){
              $("#search").submit();
            } else {
              alert(data.text);
            }
          },"json");
        });
        
        fillRows("20", "", "", false);//填充空行
      });
    </script>
  </head>
  <body>
    <form action="<%=request.getContextPath()%>/evaluation/teacher_setOpenLesson.html" name="search" id="search" method="post">
      <div class="toolbox">
        <div class="buttonbox">
          <ul>
            <li>
              <a id="btn_zj" class="btn_zj">增 加</a>
            </li>
            <li>
              <a id="btn_sxzt" class="btn_sx">刷新状态</a>
            </li>
          </ul>
        </div>
      </div>
      <div class="formbox">
        <!--标题start-->
        <h3 class="datetitle_01">
          <span>预约听课设置</span>
        </h3>
        <!--标题end-->
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
                <td>批次</td>
                <td>听课申请时间</td>
                <td>预约课程时间</td>
                <td>状态</td>
              </tr>
            </thead>
            <tbody id="list_body">
              <c:forEach items="${openLessonSettingList}" var="info" varStatus="st">
              <tr name="tr">
                <td>${info.tksqpcmc }</td>
                <td>${info.sqsjks }&nbsp;&nbsp;至&nbsp;&nbsp;${info.sqsjjs }</td>
                <td>${info.kcsjks }&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;${info.kcsjjs }</td>
                <td>
                  <c:if test="${info.syzt eq '0' }">启用</c:if>
                  <c:if test="${info.syzt eq '1' }">失效</c:if>
                  <c:if test="${info.syzt eq '2' }">停用</c:if>
                </td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        <ct:page pageList="${openLessonSettingList }" />
      </div>
    </form>
  </body>
</html>