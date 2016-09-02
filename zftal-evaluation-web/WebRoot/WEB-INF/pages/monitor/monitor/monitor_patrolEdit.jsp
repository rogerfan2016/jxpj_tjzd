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
          if ($.trim($("input[name='model.xsrq']").val()).length == 0) {
              alert("巡视日期不能为空！");
              return false;
            }
            
            var rep = /^\+?[1-9][0-9]*$/; //正整数
            if ($.trim($("input[name='model.jxz']").val()).length == 0) {
              alert("教学周不能为空！");
              return false;
            }
            
            if(!rep.test($.trim($("input[name='model.jxz']").val()))){
              alert("教学周只能输入正整数");
              return false;
            }
            
            if ($.trim($("select[name='model.kcjc']").val()).length == 0) {
              alert("课程节次不能为空！");
              return false;
            }
                      
            if ($.trim($("input[name='model.xsry']").val()).length == 0) {
              alert("巡视人员不能为空！");
              return false;
            }
            
          $.post('<%=request.getContextPath() %>/monitor/monitor_savePatrol.html', $('#editform').serialize(), function(data){
            if(data.success) {
              refreshList();
            } else {
              alert(data);
            }
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
        <input type="hidden" name="model.xslx" value="${model.xslx }"/>
        <table width="100%"  border="0" class="formlist" cellpadding="0" cellspacing="0">
          <thead>
            <tr>
              <th colspan="2">
                <span>维护巡视任务</span>
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
                <span class="red">*</span>巡视日期
              </th>
              <td>
                <input type="hidden" name="model.globalid" value="${model.globalid }"/>
                <input name="model.xsrq" value="${model.xsrq }" type="text" style="width: 295px;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate"/>
                <c:if test="${model.xslx eq 'dept' }">
                <input type="hidden" name="model.xsdw" value="${model.xsdw }"/>
                </c:if>
              </td>
            </tr>
            <tr>
              <th>
                <span class="red">*</span>教学周
              </th>
              <td>
                <input name="model.jxz" value="${model.jxz }" type="text" style="width: 293px;" class="text_nor"/>
              </td>
            </tr>
            <tr>
              <th>
                <span class="red">*</span>课程节次
              </th>
              <td>
                <select name="model.kcjc" style="width: 300px">
                  <option value="" <c:if test="${model.kcjc eq '' }">selected</c:if>>请选择</option>
                  <option value="1" <c:if test="${model.kcjc eq '1' }">selected</c:if>>第1节</option>
                  <option value="2" <c:if test="${model.kcjc eq '2' }">selected</c:if>>第2节</option>
                  <option value="3" <c:if test="${model.kcjc eq '3' }">selected</c:if>>第3节</option>
                  <option value="4" <c:if test="${model.kcjc eq '4' }">selected</c:if>>第4节</option>
                  <option value="5" <c:if test="${model.kcjc eq '5' }">selected</c:if>>第5节</option>
                  <option value="6" <c:if test="${model.kcjc eq '6' }">selected</c:if>>第6节</option>
                  <option value="7" <c:if test="${model.kcjc eq '7' }">selected</c:if>>第7节</option>
                  <option value="8" <c:if test="${model.kcjc eq '8' }">selected</c:if>>第8节</option>
                </select>
              </td>
            </tr>
            <c:if test="${model.xslx eq 'school' }">
            <tr>
              <th>
                <span class="red"></span>开课学院
              </th>
              <td>
                <select name="model.xsdw" style="width: 300px">
                	<option value="">请选择</option>
                  <c:forEach items="${deptList}" var="d">
                  <option value="${d.guid }">${d.description }</option>
                  </c:forEach>
                </select>
              </td>
            </tr>
            </c:if>
            <tr>
              <th>
                <span class="red">*</span>巡视人员
              </th>
              <td>
                <ct:selectPerson id="model.xsry" name="model.xsry" single="false" width="295px" type="teacher" value="${model.xsry }"/>
              </td>
            </tr>
            <tr>
              <th>
                	巡视地点
              </th>
              <td>
                <select name="model.xscdmc" style="width: 300px">
                  <option value="" <c:if test="${model.xscdmc eq '' }">selected</c:if>>请选择</option>
                  <option value="1" <c:if test="${model.xscdmc eq '1' }">selected</c:if>>1</option>
                  <option value="2" <c:if test="${model.xscdmc eq '2' }">selected</c:if>>2</option>
                  <option value="3" <c:if test="${model.xscdmc eq '3' }">selected</c:if>>3</option>
                  <option value="4" <c:if test="${model.xscdmc eq '4' }">selected</c:if>>4</option>
                  <option value="5" <c:if test="${model.xscdmc eq '5' }">selected</c:if>>5</option>
                  <option value="6" <c:if test="${model.xscdmc eq '6' }">selected</c:if>>6</option>
                  <option value="c2" <c:if test="${model.xscdmc eq 'c2' }">selected</c:if>>c2</option>
                  <option value="第一实验楼" <c:if test="${model.xscdmc eq '第一实验楼' }">selected</c:if>>第一实验楼</option>
                  <option value="第二实验楼" <c:if test="${model.xscdmc eq '第二实验楼' }">selected</c:if>>第二实验楼</option>
                  <option value="第六实验楼" <c:if test="${model.xscdmc eq '第六实验楼' }">selected</c:if>>第六实验楼</option>
                  <option value="化工实训楼" <c:if test="${model.xscdmc eq '化工实训楼' }">selected</c:if>>化工实训楼</option>
                  <option value="综合实验楼" <c:if test="${model.xscdmc eq '综合实验楼' }">selected</c:if>>综合实验楼</option>
                </select>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </form>
  </body>
</html>