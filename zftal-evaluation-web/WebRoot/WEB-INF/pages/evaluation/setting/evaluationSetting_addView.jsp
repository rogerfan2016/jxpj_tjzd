<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <script type="text/javascript">
      $(function() {
        $("#save").click(function() {
          if($.trim($("input[name='model.wjmc']").val()).length == 0) {
            alert("问卷名称不得为空，请重新输入！");
            return false;
          }
          
          $.post('<%=request.getContextPath() %>/evaluation/setting_save.html', $('#editform').serialize(), function(data){
            var callback = function(){
              $("#search").submit();
            };
            
            processDataCall(data, callback);
          }, "json");

          return false;
        });
        
        $("#cancel").click(function(){
          divClose();
          return false;
        });
      });
    </script>
  </head>

  <body>
    <form id="editform">
      <div class="tab">
        <table width="100%"  border="0" class="formlist" cellpadding="0" cellspacing="0">
          <thead>
            <tr>
              <th colspan="2">
                <span>问卷设置</span>
              </th>
            </tr>
          </thead>
          <tfoot>
            <tr>
              <td colspan="2">
                <div class="bz">"<span class="red">*</span>"为必填项</div>
                <div class="btn">
                  <button type="button" id="save">保 存</button>
                  <button type="button" id="cancel">取 消</button>
                </div>
              </td>
            </tr>
          </tfoot>
          <tbody>
            <tr>
              <th>
                <span class="red">*</span>问卷名称
              </th>
              <td>
                <input type="hidden" name="model.globalid" value="${model.globalid }"/>
                <input type="text" class="text_nor" name="model.wjmc" style="width:200px" value="${model.wjmc }"/>
              </td>
            </tr>
            <tr>
              <th>
                <span class="red">*</span>问卷
              </th>
              <td>
                <select name="model.wjid" style="width:206px">
                  <c:forEach items="${wjs}" var="wj">
                  <option value="${wj.wjid }" <c:if test="${model.wjid eq wj.wjid }">selected</c:if>>${wj.wjmc }</option>
                  </c:forEach>
                </select>
              </td>
            </tr>
            <tr>
              <th>
                <span class="red">*</span>评教类型
              </th>
              <td>
                <select name="model.pjlx" style="width:206px">
                  <c:forEach items="${pjs}" var="pj">
                  <option value="${pj }" <c:if test="${model.pjlx eq pj }">selected</c:if>>${pj.text }</option>
                  </c:forEach>
                </select>
              </td>
            </tr>
            <tr>
              <th>
                <span class="red">*</span>答卷人员类型
              </th>
              <td>
                <select name="model.djrylx" style="width:206px">
                  <option value="0" <c:if test="${model.djrylx eq '0' }">selected</c:if>>学生</option>
                  <option value="1" <c:if test="${model.djrylx eq '1' }">selected</c:if>>教师</option>
                </select>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </form>
  </body>
</html>