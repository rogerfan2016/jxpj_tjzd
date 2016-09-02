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
      
      	/*$("tbody > tr[name^='tr']").dblclick(function(){//行数据双击事件
			var id = $(this).find("input[name='ids']").val();
			queryEntity(id);
		});*/
      
        // 查询
        $("#search_btn").click(function() {
          refreshList();
        });
        
        // 新增
        $("#btn_zj").click(function() {
          showWindow("增加任课说明书", "<%=request.getContextPath()%>/evaluation/class_editClassInstructions.html", "700","550");
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
          
          showWindow("修改任课说明书", "<%=request.getContextPath()%>/evaluation/class_editClassInstructions.html?query.id=" + ids.val(), "700","550");
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
          $.post('<%=request.getContextPath()%>/evaluation/class_deleteClassInstructions.html',ids.serialize(),function(data) {
            if(data.success) {
              refreshList();
            } else {
              alert(data);
            }
          }, "json");
          }
        });
        
         // 复制
        $("#btn_fz").click(function() {
          var ids = $("input[name='ids']:checked");
          if (ids.length == 0) {
            alert("请先选中操作行");
            return false;
          }
          
          if (ids.length > 1) {
            alert("请选中1行操作");
            return false;
          }
		  if(confirm("确定要复制该记录吗？")){
          $.post('<%=request.getContextPath()%>/evaluation/class_copyClassInstructions.html',ids.serialize(),function(data) {
            if(data.success) {
              refreshList();
            } else {
              alert(data);
            }
          }, "json");
          }
        });
        
        // 导出
        $("#btn_dc").click(function() {
          window.open("<%=request.getContextPath()%>/evaluation/class_export.html?" + $("#search").serialize());
        });
        
        fillRows("20", "", "", false);//填充空行
      });

	  // 查询列表
      function refreshList() {
        var form = $("#search");
        form.attr("action", "<%=request.getContextPath()%>/evaluation/class_searchClassInstructionsList.html");
        form.submit();
      }
      
    </script>
  </head>
  <body>
    <div class="toolbox">
      <div class="buttonbox">
        <ul>
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
            <a id="btn_fz" class="btn_fz">复制</a>
          </li>
        </ul>
      </div>
    </div>
    <form action="" name="search" id="search" method="post">
      <div class="searchtab">
        <table width="100%" border="0">
          <tbody>
            <tr>
              <th>学年</th>
              <td>
                <ct:codePicker name="query.xn" catalog="<%=ICodeConstants.DM_DEF_XN %>" code="${query.xn }" width="100"/>
              </td>
              <th>学期</th>
              <td>
                <select name="query.xq" style="width: 100px">
                  <option value="" selected>全部</option>
                  <option value="1" <c:if test="${query.xq eq '1' }">selected</c:if>>第1学期</option>
                  <option value="2" <c:if test="${query.xq eq '2' }">selected</c:if>>第2学期</option>
                </select>
              </td>
              <th>院系</th>
              <td>
                <ct:codePicker name="query.yx" catalog="<%=ICodeConstants.DM_DEF_ORG %>" code="${query.yx }" width="100"/>
              </td>
             </tr>
             <tr>
              <th>任课教师</th>
              <td>
                <input name="query.rkjs" value="${query.rkjs }" type="text" style="width: 100px;" class=""text_nor"/>
              </td>
              <th>课程名称</th>
              <td colspan="3">
                <input name="query.kcmc" value="${query.kcmc }" type="text" style="width: 100px;" class=""text_nor"/>
              </td>
            </tr>
          </tbody>
          <tfoot>
            <td colspan="6">
              <div class="btn">
                <button class="brn_cx" type="button" name="search_btn" id="search_btn" >查询</button>
              </div>
            </td>
          </tfoot>
        </table>
      </div>
      <div class="formbox">
        <!--标题start-->
        <h3 class="datetitle_01">
          <span>任课说明书列表</span>
        </h3>
        <!--标题end-->
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
                <td width="4%"></td>
                <td>操作</td>
                <td>状态</td>
                <td>课程名称</td>
                <td>学年</td>
                <td>学期</td>
                <td>院系</td>
                <td>专业</td>
                <td>年级</td>
                <td>班级</td>
                <td>讲课周次</td>
                <td>开始周</td>
                <td>结束周</td>
                <td>总学时</td>
                <td>讲课学时</td>
                <td>习题学时</td>
                <td>课堂讨论学时</td>
                <td>案例教学学时</td>
                <td>审核意见</td>
              </tr>
            </thead>
            <tbody id="list_body">
              <c:forEach items="${pageList}" var="info" varStatus="st">
              <tr name="tr">
                <td>
                  <input type="checkbox" name="ids" value="${info.id }" />
                </td>
                <td>
                	<c:if test="${info.zt eq '0' || info.zt eq '4' }">
                	<a style="color:#074695" href="<%=request.getContextPath()%>/evaluation/class_searchClassInstructionsDetailList.html?id=${info.id }">增加/修改明细</a>
					</c:if>
                	<c:if test="${info.zt eq '1' || info.zt eq '2' || info.zt eq '3'}">
                	<a style="color:#074695" href="<%=request.getContextPath()%>/evaluation/class_searchClassInstructionsDetailList.html?id=${info.id }">查看明细</a>
                	</c:if>
                </td>
                <td>
                	<c:if test="${info.zt eq '0' }">未提交</c:if>
                	<c:if test="${info.zt eq '1' }">待审核</c:if>
                	<c:if test="${info.zt eq '2' }"><font color="green">教研室审核通过</font></c:if>
                	<c:if test="${info.zt eq '3' }"><font color="green">院系审核通过</font></c:if>
                	<c:if test="${info.zt eq '4' }"><font color="red">审核不通过</font></c:if>
                </td>
                <td>${info.kcmc }</td>
                <td>${info.xn }</td>
                <td>第${info.xq }期</td>
                <td><ct:codeParse code="${info.yx }" catalog="<%=ICodeConstants.DM_DEF_ORG %>" /></td>
                <td>${info.zy }</td>
                <td>${info.nj }</td>
                <td>${info.bj }</td>
                <td>${info.jkzc }</td>
                <td>第${info.ksz }周</td>
                <td>第${info.jsz }周</td>
                <td>${info.zxs }</td>
                <td>${info.jkxs }</td>
                <td>${info.xtxs }</td>
                <td>${info.kttlxs }</td>
                <td>${info.aljxxs }</td>
                <td>${info.shyj }</td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        <ct:page pageList="${pageList }" query="${query }" queryName="query"/>
      </div>
    </form>
  </body>
</html>