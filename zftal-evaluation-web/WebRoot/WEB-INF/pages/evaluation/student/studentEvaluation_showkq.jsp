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
              <td>考勤状态</td>
              <td width="125">备注</td>
            </tr>
          </thead>
          <tbody id="list_body" >
            <c:forEach items="${beans}" var="model" varStatus="st">
              <tr>
                <td>${model.xsxm }[${model.xsid }]</td>
                <td>
                  <c:if test="${model.kqqk eq 0 or model.kqqk eq null or model.kqqk eq ''}">出勤</c:if>
                  <c:if test="${model.kqqk eq 1 }">旷课</c:if>
                  <c:if test="${model.kqqk eq 2 }">事假</c:if>
                  <c:if test="${model.kqqk eq 3 }">病假</c:if>
                  <c:if test="${model.kqqk eq 4 }">迟到</c:if>
                </td>
                <td>${model.bzsm }</td>
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