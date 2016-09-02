<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

  <head>
    <script type="text/javascript">
      $(function(){
        fillRows(15, '', '', false);
        
        $("input[name='chooseView']").click(function() {
          var check = "";
          if ($(this).is(':checked')) {
            check = "y";
          } else {
            check = "n";
          }
          var param = "vpModel.classId=" + $("#classId").val()
           + "&vpModel.propertyId=" + $(this).closest("tr").attr("id")
           + "&vpModel.viewStatus=" + check;

          modifyItem(param);
        });
        
        $("input[name='chooseCondition']").click(function() {
          var check = "";
          if ($(this).is(':checked')) {
            check = "y";
          } else {
            check = "n";
          }
          var param = "vpModel.classId=" + $("#classId").val()
           + "&vpModel.propertyId=" + $(this).closest("tr").attr("id")
           + "&vpModel.conditionStatus=" + check;
          
          modifyItem(param);
        });
      })
      
      // 修改属性
      function modifyItem(param) {
        $.post('<%=request.getContextPath() %>/basedata/basedata_modifyViewPro.html', param, function(data){
          var callback = function(){
            $("form:first").submit();
          };
          
          processDataCall(data, callback);
        }, "json");
      }
    </script>
  </head>

  <body>
    <div class="formbox">
      <h3 class="datetitle_01">
        <span>${infoClass.name }属性信息</span>
      </h3>
      <table summary="" class="dateline" align="" width="100%">
        <thead id="list_head">
          <tr>
            <td>序号</td>
            <td>属性名称</td>
            <td>字段名称</td>
            <td>可显示</td>
            <td>可编辑</td>
            <td>必填</td>
            <td>同步</td>
            <td>虚拟</td>
            <td width="60px">WEB显示</td>
            <td width="60px">WEB条件</td>
          </tr>
        </thead>
        <tbody id="list_body">
          <c:forEach items="${infoClass.properties }" var="bean" varStatus="i">
          <tr id="${bean.guid }">
            <td>${i.index+1 }</td>
            <td>${bean.name }</td>
            <td>${bean.fieldName }</td>
            <td><c:if test="${bean.viewable eq true}">是</c:if><c:if test="${!bean.viewable eq true}"><font class="red">否</font></c:if></td>
            <td><c:if test="${bean.editable eq true}">是</c:if><c:if test="${!bean.editable eq true}"><font class="red">否</font></c:if></td>
            <td><c:if test="${bean.need eq true}">是</c:if><c:if test="${!bean.need eq true}"><font class="red">否</font></c:if></td>
            <td><c:if test="${!empty bean.syncToField}">是</c:if><c:if test="${empty bean.syncToField}"><font class="red">否</font></c:if></td>
            <td><c:if test="${bean.virtual eq true}">是</c:if><c:if test="${!bean.virtual eq true}"><font class="red">否</font></c:if></td>
            <td>
              <c:if test="${bean.viewable eq true}">
              <input type="checkbox" name="chooseView" <c:if test="${vpro[bean.guid].viewStatus eq 'y'}">checked="checked"</c:if> />
              </c:if>
            </td>
            <td>
              <c:if test="${bean.viewable eq true}">
              <input type="checkbox" name="chooseCondition" <c:if test="${vpro[bean.guid].conditionStatus eq 'y'}">checked="checked"</c:if> />
              </c:if>
            </td>
          </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </body>

</html>