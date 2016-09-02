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
          var rep = /^(0|\+?[1-9][0-9]*)$/;  //正整数+0
   		  if($.trim($("#xn").val()).length == 0) {
            alert("学年不能为空！");
            return false;
          }
          if($.trim($("#xq").val()).length == 0) {
            alert("学期不能为空！");
            return false;
          }
          if($.trim($("#yx").val()).length == 0) {
            alert("院系不能为空！");
            return false;
          }
          if($.trim($("#zy").val()).length == 0) {
            alert("专业不能为空！");
            return false;
          }
          if($.trim($("#nj").val()).length == 0) {
            alert("年级不能为空！");
            return false;
          }
          if($.trim($("#bj").val()).length == 0) {
            alert("班级不能为空！");
            return false;
          }
          if($.trim($("#kcmc").val()).length == 0) {
            alert("课程名称不能为空！");
            return false;
          }
          if($.trim($("#ksz").val()).length == 0) {
            alert("开始周不能为空！");
            return false;
          }
          if(!rep.test($.trim($("#ksz").val()))){
	         alert("开始周必须是整数！");
	         return false;
	      }
          if($.trim($("#jsz").val()).length == 0) {
            alert("结束周不能为空！");
            return false;
          }
          if(!rep.test($.trim($("#jsz").val()))){
	         alert("结束周必须是整数！");
	         return false;
	      }
          if($.trim($("#zxs").val()).length == 0) {
            alert("总学时不能为空！");
            return false;
          }
          if(!rep.test($.trim($("#zxs").val()))){
            alert("总学时必须是整数！");
            return false;
       	  }
       	  if($.trim($("#jkxs").val()).length > 0) {
	       	  if(!rep.test($.trim($("#jkxs").val()))){
	            alert("讲课学时必须是整数！");
	            return false;
	       	  }
	      }
	      if($.trim($("#xtxs").val()).length > 0) {
	       	  if(!rep.test($.trim($("#xtxs").val()))){
	            alert("习题学时必须是整数！");
	            return false;
	       	  }
	      }
	      if($.trim($("#kttlxs").val()).length > 0) {
	       	  if(!rep.test($.trim($("#kttlxs").val()))){
	            alert("课堂讨论学时必须是整数！");
	            return false;
	       	  }
       	  }
       	  if($.trim($("#aljxxs").val()).length > 0) {
	       	  if(!rep.test($.trim($("#aljxxs").val()))){
	            alert("案例教学学时必须是整数！");
	            return false;
	       	  }
       	  }
       	  if($.trim($("#jxyq").val()).length == 0) {
            alert("教学要求不能为空！");
            return false;
          }
          if($.trim($("#kczy").val()).length == 0) {
            alert("课程在专业中的作用内容不得为空！");
            return false;
          }   
           
          if($.trim($("#jccks").val()).length == 0) {
            alert("教材及参考书内容不得为空！");
            return false;
          }	
            
          $.post('<%=request.getContextPath() %>/evaluation/class_saveClassInstructions.html', $('#editform').serialize(), function(data){
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
      	<input type="hidden" name="model.id" value="${model.id }"/>
      	<input type="hidden" name="model.rkjs" value="${model.rkjs }"/>
        <table width="100%"  border="0" class="formlist" cellpadding="0" cellspacing="0">
          <thead>
            <tr>
              <th colspan="4">
                <span>维护任课说明书</span>
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
                <span class="red">*</span>学年
              </th>
              <td>
              	<%=SubSystemHolder.getPropertiesValue("defult_xn") %>
              	<input id="xn" type="hidden" name="model.xn" value='<%=SubSystemHolder.getPropertiesValue("defult_xn") %>'/>
              </td>
              <th>
                <span class="red">*</span>学期
              </th>
              <td>
                <%=SubSystemHolder.getPropertiesValue("defult_xq") %>
              	<input id="xq" type="hidden" name="model.xq" value='<%=SubSystemHolder.getPropertiesValue("defult_xq") %>'/>
              </td>
            </tr>
            
            <tr>
              <th>
                <span class="red">*</span>院系
              </th>
              <td>
              	<select id="yx" name="model.yx" style="width: 180px">
                  <option value="" selected>请选择</option>
                  <c:forEach items="${orgList}" var="org" varStatus="st">
                  <option value="${org.oid }" <c:if test="${model.yx eq org.oid }">selected</c:if>>${org.name }</option>
                  </c:forEach>
                </select>
              </td>
              <th>
                <span class="red">*</span>专业
              </th>
              <td>
                <input id="zy" name="model.zy" value="${model.zy }" type="text" maxlength="50" style="width: 130px;" class="text_nor"/>
              </td>
            </tr>
            <tr>
	            <th>
	                <span class="red">*</span>年级
	              </th>
	              <td>
	                <input id="nj" name="model.nj" value="${model.nj }" type="text" maxlength="50" style="width: 100px;" class="text_nor"/>
	              </td>
	            
              <th>
                <span class="red">*</span>班级
              </th>
              <td>
                <input id="bj" name="model.bj" value="${model.bj }" type="text" maxlength="50" style="width: 100px;" class="text_nor"/>
              </td>
           </tr>
           <tr>
	            <th>
	                <span class="red">*</span>课程名称
	              </th>
	              <td>
	               	<input id="kcmc" name="model.kcmc" value="${model.kcmc }" type="text" maxlength="60" style="width: 180px;" class="text_nor"/>
	              </td>
	            <th>
	                <span class="red">*</span>开始/结束周
	              </th>
	              <td>
	              	讲课周次<input id="jkzc" name="model.jkzc" value="${model.jkzc }" type="text" maxlength="2" style="width: 20px;" class="text_nor"/>周；
	              	第<input id="ksz" name="model.ksz" value="${model.ksz }" type="text" maxlength="2" style="width: 20px;" class="text_nor"/>周 -
	               	第<input id="jsz" name="model.jsz" value="${model.jsz }" type="text" maxlength="2" style="width: 20px;" class="text_nor"/>周
	              </td>
           </tr>
           <tr>
	            <th>
	            	学时
	            </th>
	            <td colspan="3">
	                <span class="red">*</span>总学时：<input id="zxs" name="model.zxs" value="${model.zxs }" type="text" maxlength="3" style="width: 50px;" class="text_nor"/></br>
	                	讲课学时：<input id="jkxs" name="model.jkxs" value="${model.jkxs }" type="text" maxlength="3" style="width: 30px;" class="text_nor"/>
	                	习题学时：<input id="xtxs" name="model.xtxs" value="${model.xtxs }" type="text" maxlength="3" style="width: 30px;" class="text_nor"/>
	                	课堂讨论学时：<input id="kttlxs" name="model.kttlxs" value="${model.kttlxs }" type="text" maxlength="3" style="width: 30px;" class="text_nor"/>
	                	案例教学学时：<input id="aljxxs" name="model.aljxxs" value="${model.aljxxs }" type="text" maxlength="3" style="width: 30px;" class="text_nor"/>
	            </td>  
           </tr>
            <tr>
            	<th>
                	<span class="red">*</span>教学要求
              	</th>
				<td colspan="3">
					<textarea id="jxyq" name="model.jxyq" rows="5" style="width:100%;font-size:12px">${model.jxyq }</textarea>
				</td>
			</tr>
			<tr>
              <th>
                <span class="red">*</span>课程在专业中的作用
              </th>
              <td colspan="3">
                <textarea id="kczy" name="model.kczy" rows="5" style="width:100%;font-size:12px">${model.kczy }</textarea>
              </td>
            </tr>
            <tr>
              <th>
                <span class="red">*</span>教材及参考书
              </th>
              <td colspan="3">
                <textarea id="jccks" name="model.jccks" rows="2" style="width:100%;font-size:12px">${model.jccks }</textarea>
              </td>
            </tr>            
          </tbody>
        </table>
      </div>
    </form>
  </body>
</html>