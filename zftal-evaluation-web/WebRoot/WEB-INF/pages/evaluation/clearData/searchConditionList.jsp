<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <%@ include file="/commons/hrm/head.ini"%>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/hrm/code.js"></script>
    <script type="text/javascript">
      $(function() {
        // 查询
        $("#search_btn").click(function() {
          refreshList();
        });
        
        // 新增
        $("#btn_zj").click(function() {
          showWindow("增加清洗数据条件", "<%=request.getContextPath()%>/evaluation/clearData_editCondition.html", "400","230");
        });
        
        fillRows("20", "", "", false);//填充空行
      }); 
      function refreshList() {
        var form = $("#search");
        form.attr("action", "<%=request.getContextPath()%>/evaluation/clearData_patrolBySchool.html");
        form.submit()
      }
      
      function editCondition(tjid) {
        showWindow("修改清洗条件", "<%=request.getContextPath()%>/evaluation/clearData_editCondition.html?query.tjid=" + tjid, "400","230");
      }

    </script>
  </head>
  <body>
    <div class="toolbox">
      <div class="buttonbox">
        <ul>
          <li>
            <a id="btn_zj" class="btn_zj">增加清洗条件</a>
          </li>
          <li>
            <a id="btn_xg" class="btn_xg">清洗数据</a>
          </li>
        </ul>
      </div>
    </div>
    <form action="" name="search" id="search" method="post">
      <div class="formbox">
        <!--标题start-->
        <h3 class="datetitle_01">
          <span>清洗条件列表</span>
        </h3>
        <!--标题end-->
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
                <td>序号</td>
                <td>条件名称</td>
                <td>条件表达式</td>
                <td>状态</td>
                <td>操作</td>
              </tr>
            </thead>
            <tbody id="list_body">
              <c:forEach items="${list }" var="info" varStatus="st">
              <tr name="tr">
              	<td>${st.index + 1 }</td>
                <td>${info.tjmc }</td>
                <td>${info.tjbds }</td>
                <td>
          		<c:if test="${info.zt eq '1' }"><font color="green">启用</></c:if>
                <c:if test="${info.zt eq '2' }"><font color="red">未启用</></c:if>
                </td>
                <td>
                <a style="color:#074695" href="#" onclick="editCondition('${info.tjid }')">修改</a>
                <c:if test="${info.zt eq '2' }">
                	<a style="color:#074695" href="<%=request.getContextPath() %>/evaluation/clearData_changeStatus.html?model.zt=1&model.tjid=${info.tjid }">启用</a>
                </c:if>
                <c:if test="${info.zt eq '1' }">
                	<a style="color:#074695" href="<%=request.getContextPath() %>/evaluation/clearData_changeStatus.html?model.zt=2&model.tjid=${info.tjid }">停用</a>
                </c:if>
                </td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </form>
  </body>
</html>