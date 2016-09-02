<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <%@ include file="/commons/hrm/head.ini" %>
    <script type="text/javascript">
      $(function(){
        // 保存
        $("#save_btn").click(function() {
          $.post("<%=request.getContextPath()%>/evaluation/student_saveTeachingLog.html",
            $("#editform").serialize(), function(data) {
              if(data.success){
                divClose();
              }else{
                showWarning(data.text);
              }
            },"json");
          return false;
        });
      });
    </script>
  </head>
  <body>
    <form id="editform">
      <input type="hidden" id="globalid" name="teachingEntity.globalid" value="${teachingEntity.globalid }"/>
      <input type="hidden" id="kcid" name="teachingEntity.kcid" value="${teachingEntity.kcid }"/>
      <div class="content" style="overflow-y:auto;overflow-x:hidden;" >
        <table width="100%"  border="0" class="formlist" cellpadding="0" cellspacing="0">
          <thead>
            <tr>
              <th colspan="4">
                <span><font color="#0f5dc2" style="font-weight:normal;">教学日志</font></span>
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th>课程名称</th>
              <td>
                ${teachingEntity.kcmc }
              </td>
            </tr>
            <tr>
              <th>任课老师</th>
              <td>
                ${teachingEntity.rkls }
              </td>
            </tr>
            <tr>
              <th>教学内容</th>
              <td>
                <textarea name="teachingEntity.jxnr" rows="5" cols="40" <c:if test="${type eq 'view' }">readonly="readonly"</c:if>>${teachingEntity.jxnr }</textarea>
              </td>
            </tr>
            <tr>
              <th>课外作业</th>
              <td>
                <textarea name="teachingEntity.kwzy" rows="5" cols="40" <c:if test="${type eq 'view' }">readonly="readonly"</c:if>>${teachingEntity.kwzy }</textarea>
              </td>
            </tr>
            <tr>
              <th>备注说明</th>
              <td>
                <textarea name="teachingEntity.bzsm" rows="5" cols="40" <c:if test="${type eq 'view' }">readonly="readonly"</c:if>>${teachingEntity.bzsm }</textarea>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </form>
    <table width="100%"  border="0" class="formlist" cellpadding="0" cellspacing="0">
      <tfoot>
        <tr>
          <td colspan="2">
            <div class="bz">"<span class="red">*</span>"为必填项</div>
            <div class="btn">
              <c:if test="${type != 'view' }">
              <button type="button" id="save_btn">保 存</button>
              </c:if>
              <button type="button" id="cancel" onclick="divClose();">取 消</button>
            </div>
          </td>
        </tr>
      </tfoot>
    </table>
  </body>
</html>