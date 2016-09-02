<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <script type="text/javascript">
      $(function() {
        $("#save").click(function() {
          if($.trim($("#pcmc").val()).length == 0) {
            alert("批次不得为空，请重新输入！");
            return false;
          }
          
          if($.trim($("#tksqFrom").val()).length == 0) {
            alert("听课申请时间（开始）不得为空，请重新输入！");
            return false;
          }
          if($.trim($("#tksqTo").val()).length == 0) {
            alert("听课申请时间（结束）不得为空，请重新输入！");
            return false;
          }
          if($.trim($("#yykcFrom").val()).length == 0) {
            alert("预约课程时间（开始）不得为空，请重新输入！");
            return false;
          }
          if($.trim($("#yykcTo").val()).length == 0) {
            alert("预约课程时间（结束）不得为空，请重新输入！");
            return false;
          }
          
          $.post('<%=request.getContextPath() %>/evaluation/teacher_saveSetting.html', $('#editform').serialize(), function(data){
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
                <span>听课申请设置</span>
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
                <span class="red">*</span>批次
              </th>
              <td>
                <input type="text" class="text_nor" id="pcmc" name="model.tksqpcmc" style="width:240px" value=""/>
              </td>
            </tr>
            <tr>
              <th>
                <span class="red">*</span>听课申请时间
              </th>
              <td>
                <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" name="model.sqsjks" id="tksqFrom" value="" style="width: 100px;" />
                &nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;
                <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" name="model.sqsjjs" id="tksqTo" value="" style="width: 100px;" />
              </td>
            </tr>
            <tr>
              <th>
                <span class="red">*</span>预约课程时间
              </th>
              <td>
                <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" name="model.kcsjks" id="yykcFrom" value="" style="width: 100px;" />
                &nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;
                <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" name="model.kcsjjs" id="yykcTo" value="" style="width: 100px;" />
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </form>
  </body>
</html>