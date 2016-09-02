<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <%@ include file="/commons/hrm/head.ini" %>
    <script type="text/javascript">
    </script>
  </head>
  <body>
    <div class="content" style="overflow-y:auto;overflow-x:hidden;">
      <div class="con_overlfow">
        <table width="100%" class="dateline tablenowrap" id="tiptab">
          <thead id="list_head">
            <tr>
              <td>学生</td>
              <td>评教情况</td>
              <td>生成时间</td>
              <td>评教时间</td>
            </tr>
          </thead>
          <tbody id="list_body" >
            <c:forEach items="${beans}" var="model" varStatus="st">
              <tr>
                <td>${model.xsxm }[${model.xsid }]</td>
                <td>
                  <c:if test="${model.pjqk eq 0 or model.pjqk eq null or model.pjqk eq ''}">未评价</c:if>
                  <c:if test="${model.pjqk eq 1 }">已评价</c:if>
                </td>
                <td>${model.scsj }</td>
                <td>${model.pjsj }</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
    <table width="100%" border="0" class="formlist" cellpadding="0" cellspacing="0">
      <tfoot>
        <tr>
          <td>
            <div class="btn">
              <button type="button" id="cancel" onclick="divClose();">取 消</button>
            </div>
          </td>
        </tr>
      </tfoot>
    </table>
  </body>
</html>