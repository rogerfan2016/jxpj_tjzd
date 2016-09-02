<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@page import="com.zfsoft.hrm.config.ICodeConstants"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ct" uri="/custom-code"%>
<%@taglib prefix="code" uri="/WEB-INF/code.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <%@ include file="/commons/hrm/head.ini"%>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/hrm/code.js"></script>
    <script type="text/javascript">
      $(function() {
      	var id = $("#id").val();
        // 查询
        $("#search_btn").click(function() {
          refreshList();
        });
        
        // 新增
        $("#btn_zj").click(function() {
          showWindow("增加任课说明书明细", "<%=request.getContextPath()%>/evaluation/class_editClassInstructionsDetail.html?detailModel.rksmsid=${model.id }", "660","300");
        });
        
        // 修改
        $("#btn_xg").click(function() {
          var ids = $("input[name='ids']:checked");
          if (ids.length == 0) {
            alert("请先选中操作行");
            return false;
          }
          
          if (ids.length > 1) {
            alert("请选中1行操作");
            return false;
          }
          
          showWindow("修改任课说明书明细", "<%=request.getContextPath()%>/evaluation/class_editClassInstructionsDetail.html?query.id=" + ids.val(), "660","300");
        });
        
        // 删除
        $("#btn_sc").click(function() {
          var ids = $("input[name='ids']:checked");
          if (ids.length == 0) {
            alert("请先选中操作行");
            return false;
          }
          
          if (ids.length > 1) {
            alert("请选中1行操作");
            return false;
          }
		  if(confirm("确定要删除该记录吗？")){
          $.post('<%=request.getContextPath()%>/evaluation/class_deleteClassInstructionsDetail.html',ids.serialize(),function(data) {
            if(data.success) {
              refreshList();
            } else {
              alert(data);
            }
          }, "json");
          }
        });
        
        // 提交审核
        $("#btn_sh").click(function() {
		  if(confirm("提交审核后不能再编辑，确定要提交审核吗？")){
          $.post('<%=request.getContextPath()%>/evaluation/class_checkIn.html?id=${model.id }',function(data) {
            if(data.success) {
              refreshList();
            } else {
              alert(data);
            }
          }, "json");
          }
        });
        
        // 取消审核
        $("#btn_qxsh").click(function() {
		  if(confirm("确定要取消审核吗？")){
          $.post('<%=request.getContextPath()%>/evaluation/class_cancelCheckIn.html?id=${model.id }',function(data) {
            if(data.success) {
              refreshList();
            } else {
              alert(data);
            }
          }, "json");
          }
        });
        
        fillRows("20", "", "", false);//填充空行
      });

	  // 查询
      function refreshList() {
        var form = $("#search");
        form.attr("action", "<%=request.getContextPath()%>/evaluation/class_searchClassInstructionsDetailList.html");
        form.submit();
      }
      
    </script>
  </head>
  <body>
    <form action="" name="search" id="search" method="post">
    <input type="hidden" id="id" name="id" value="${model.id }"/>
    <c:if test="${model.zt eq '4' }">
    <div class="tab">
        <table width="100%"  border="0" class="formlist" cellpadding="0" cellspacing="0">
            <tr>
            	<th width="10%">
                	审核意见
              	</th>
				<td>
					<textarea id="shyj" name="model.shyj" rows="3" disabled="disabled" style="width:100%;font-size:12px">${model.shyj }</textarea>
				</td>
			</tr>
          </tbody>
        </table>
      </div>
    </c:if>
      <div class="searchtab">
	<h3 class="datetitle_01">
          <span>天津职业大学任课说明书</span>
    </h3>
 <span style="text-decoration:underline;">   ${model.xn } </span><b>学年 第</b>
 <span style="text-decoration:underline;">   ${model.xq } </span><b>学期</b></span></br>         
 <span style="text-decoration:underline;">   <ct:codeParse code="${model.yx }" catalog="<%=ICodeConstants.DM_DEF_ORG %>" />  </span><b>院、系、部；</b>  
 <span style="text-decoration:underline;">   ${model.zy }  </span><b>专业；</b>
 <span style="text-decoration:underline;">   ${model.nj }级  ${model.bj }  </span><b>班级；       课程名称：</b>  
 <span style="text-decoration:underline;">   ${model.kcmc }  </span><b>；任课教师：</b> 
 <span style="text-decoration:underline;">   <ct:PersonParse code="${model.rkjs }"/>  </span>；</br><b>讲课周次</b>
 <span style="text-decoration:underline;">   ${model.jkzc }  </span><b>第</b>  
 <span style="text-decoration:underline;">   ${model.ksz }  </span><b>周至第</b> 
 <span style="text-decoration:underline;">   ${model.jsz }  </span><b>周；课内总学时</b>  
 <span style="text-decoration:underline;">   ${model.zxs }   </span></br>
 <span style="font-weight:bold;">教学要求：</span>${model.jxyq }</br>
 <span style="font-weight:bold;">该课程在专业中的作用：</span>${model.kczy }</br>
 <span style="font-weight:bold;">教材及参考书： </span>${model.jccks }
      </div>
      <div class="toolbox">
      <div class="buttonbox">
        <ul>
        <c:if test="${model.zt eq '0' || model.zt eq '4' }">
          <li>
            <a id="btn_zj" class="btn_zj">增加</a>
          </li>
          <li>
            <a id="btn_xg" class="btn_xg">修改</a>
          </li>
          <li>
            <a id="btn_sc" class="btn_sc">删除</a>
          </li>
          <li>
            <a id="btn_sh" class="btn_sh">提交审核</a>
          </li>
          </c:if>
          <c:if test="${model.zt eq '1' }">
          <li>
            <a id="btn_qxsh" class="btn_qxsh">取消审核</a>
          </li>
          </c:if>
         </ul>
      </div>
    </div>
    
      <div class="formbox">
        <!--标题start-->
        <h3 class="datetitle_01">
          <span>任课说明书明细列表</span>
        </h3>
        <!--标题end-->
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
                <td width="4%"></td>
                <td>周次</td>
                <td>教学时数</td>
                <td>教学内容（含习题课、课堂讨论、案例教学等内容）</td>
                <td>训练方式（作业、实验、实训等）</td>
                <td>相关英语知识训练</td>
              </tr>
            </thead>
            <tbody id="list_body">
              <c:forEach items="${model.detailList}" var="info" varStatus="st">
              <tr name="tr">
                <td>
                  <input type="checkbox" name="ids" value="${info.id }"/>
                </td>
                <td>${info.zc }</td>
                <td>${info.jxss }</td>
                <td>${info.jxnr }</td>
                <td>${info.xlfs }</td>
                <td>${info.yyzsxl }</td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </form>
  </body>
</html>