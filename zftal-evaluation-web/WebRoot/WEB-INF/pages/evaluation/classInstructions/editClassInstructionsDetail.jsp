<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@page import="com.zfsoft.hrm.config.ICodeConstants"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="ct" uri="/custom-code"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  <%@ include file="/commons/hrm/head.ini"%>
  <script type="text/javascript" src="<%=request.getContextPath() %>/js/hrm/code.js"></script>
    <script type="text/javascript">
      $(function() {
        $("#save").click(function() {
          var rep = /^[0-9]*[1-9][0-9]*$/; //正整数
          if($.trim($("#zc").val()).length == 0) {
            alert("周次不能为空！");
            return false;
          }
          if($.trim($("#jxss").val()).length == 0) {
            alert("教学时数不能为空！");
            return false;
          }
          if(!rep.test($.trim($("#jxss").val()))){
            alert("教学时数必须是整数！");
            return false;
       	  }
       	  if($.trim($("#xlfs").val()).length == 0) {
            alert("训练方式不能为空！");
            return false;
          }
          if($.trim($("#jxnr").val()).length == 0) {
            alert("教学内容中的作用内容不得为空！");
            return false;
          }    	
            
          $.post('<%=request.getContextPath() %>/evaluation/class_saveClassInstructionsDetail.html', $('#editform').serialize(), function(data){
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
      	<input type="hidden" name="detailModel.rksmsid" value="${detailModel.rksmsid }"/>
      	<input type="hidden" name="detailModel.id" value="${detailModel.id }"/>
        <table width="100%"  border="0" class="formlist" cellpadding="0" cellspacing="0">
          <thead>
            <tr>
              <th colspan="4">
                <span>维护任课说明书详情</span>
              </th>
            </tr>
          </thead>
          <tfoot>
            <tr>
              <td colspan="4">
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
                <span class="red">*</span>周次
              </th>
              <td>
              	<input id="zc" name="detailModel.zc" value="${detailModel.zc }" type="text" maxlength="20" style="width: 100px;" class="text_nor"/>
              </td>
              <th>
                <span class="red">*</span>教学时数
              </th>
              <td>
                <input id="jxss" name="detailModel.jxss" value="${detailModel.jxss }" type="text" maxlength="10" style="width: 100px;" class="text_nor"/>
              </td>
            </tr>
           <tr>
              <th>
                <span class="red">*</span>训练方式
              </th>
              <td>
              	<select id="xlfs" name="detailModel.xlfs" style="width: 100px">
                  <option value="" selected>请选择</option>
                  <option value="作业" <c:if test="${detailModel.xlfs eq '作业' }">selected</c:if>>作业</option>
                  <option value="实验" <c:if test="${detailModel.xlfs eq '实验' }">selected</c:if>>实验</option>
                  <option value="实训" <c:if test="${detailModel.xlfs eq '实训' }">selected</c:if>>实训</option>
                  <option value="教学做" <c:if test="${detailModel.xlfs eq '教学做' }">selected</c:if>>教学做</option>
                  <option value="其他" <c:if test="${detailModel.xlfs eq '其他' }">selected</c:if>>其他</option>
                </select>
              </td>
              <th>
                <span class="red"></span>相关英语知识训练
              </th>
              <td>
                <input id="yyzsxl" name="detailModel.yyzsxl" value="${detailModel.yyzsxl }" type="text" maxlength="200" style="width: 200px;" class="text_nor"/>
              </td>
            </tr>
            <tr>
            	<th>
                	<span class="red">*</span>教学内容
              	</th>
				<td colspan="3">
					<textarea id="jxnr" name="detailModel.jxnr" rows="5" style="width:100%;font-size:12px">${detailModel.jxnr }</textarea>
				</td>
			</tr>
          </tbody>
        </table>
      </div>
    </form>
  </body>
</html>