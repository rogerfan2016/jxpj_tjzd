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
          if ($.trim($("textarea[name='detail.yxclyj']").val()).length == 0) {
          	alert("院系反馈意见不能为空！");
            return false;
          }
          
          $.post('<%=request.getContextPath() %>/monitor/monitor_saveDetail.html', $('#editform').serialize(), function(data){
            var callback = function(){
              refreshList();
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
        <input type="hidden" name="detail.id" value="${detail.id }"/>
        <input type="hidden" name="detail.zt" value="2"/>
        <table width="100%"  border="0" class="formlist" cellpadding="0" cellspacing="0">
          <thead>
            <tr>
              <th colspan="2">
                <span>学院反馈处理</span>
              </th>
            </tr>
          </thead>
          <tfoot>
            <tr>
              <td colspan="2">
                <div class="bz">"<span class="red">*</span>"为必填项</div>
                <div class="btn">
                  <button type="button" id="save">提交反馈</button>
                  <button type="button" id="cancel">取 消</button>
                </div>
              </td>
            </tr>
          </tfoot>
          <tbody>
            <tr>
              <th>
                <span class="red"></span>存在问题
              </th>
              <td>
                <textarea name="czwt" rows="5" disabled="disabled" cols="40">${detail.czwt }<c:if test="${detail.bz != null}">（${detail.bz }）</c:if></textarea>
              </td>
            </tr>
            <tr>
              <th>
                <span class="red">*</span>处理/反馈结果
              </th>
              <td>
                <textarea name="detail.yxclyj" rows="5" cols="40">${detail.yxclyj }</textarea>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </form>
  </body>
</html>