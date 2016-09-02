<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@page import="com.zfsoft.hrm.config.ICodeConstants"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="ct" uri="/custom-code"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <script type="text/javascript">
      $(function() {
        $("#save").click(function() {
          if ($.trim($("input[name='model.tjmc']").val()).length == 0) {
              alert("条件名称不能为空！");
              return false;
            }
            
            if ($.trim($("textarea[name='model.tjbds']").val()).length == 0) {
              alert("条件表达式不能为空！");
              return false;
            }
            
          $.post('<%=request.getContextPath() %>/evaluation/clearData_saveCondition.html', $('#editform').serialize(), function(data){
          	  alert(data);
              divClose();
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
                <span>维护清洗条件</span>
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
                <span class="red">*</span>条件名称
              </th>
              <td>
              	<input name="model.tjid" value="${model.tjid }" type="hidden"/>
                <input name="model.tjmc" value="${model.tjmc }" type="text" style="width: 250px;" class="text_nor"/>
              </td>
            </tr>
            <tr>
              <th>
                <span class="red">*</span>条件表达式
              </th>
              <td>
                <textarea name="model.tjbds" rows="4" style="width: 250px;">${model.tjbds }</textarea>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </form>
  </body>
</html>