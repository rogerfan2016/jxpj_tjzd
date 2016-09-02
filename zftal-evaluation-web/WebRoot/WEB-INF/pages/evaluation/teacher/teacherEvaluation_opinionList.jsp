<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ct" uri="/custom-code"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <%@ include file="/commons/hrm/head.ini"%>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/hrm/code.js"></script>
  </head>
  <script type="text/javascript">
      $(function() {
        
        // 查询
        $("#search_btn").click(function() {
          $("#search").submit();
        });
        fillRows("20", "", "", false);//填充空行
      });
      
      // 详情
      function doOpinion(id) {
        showWindow("意见反馈详情", "<%=request.getContextPath()%>/evaluation/teacher_opinionDetail.html?id=" + id, "480","230");
      }

    </script>
  <body>
    <form action="<%=request.getContextPath()%>/evaluation/teacher_searchOpinionList.html" name="search" id="search" method="post">
      <input name="phase" type="hidden" value="${phase }"/>
      <div class="searchtab">
        <table width="100%" border="0">
          <tbody>
            <tr>
              <th>姓名</th>
              <td>
                <input type="text" class="text_nor" name="opinionQuery.yhm" id="yhm" value="${opinionQuery.yhm}" style="width: 80px;" />
              </td>
              <th>来源</th>
              <td>
                <select name="opinionQuery.rylx" style="width:80px;">
                  <option value="teacher" <c:if test="${opinionQuery.rylx eq 'teacher'}">selected</c:if>>教师</option>
                  <option value="student" <c:if test="${opinionQuery.rylx eq 'student'}">selected</c:if>>学生</option>
                </select>
              </td>
            </tr>
          </tbody>
          <tfoot>
            <td colspan="10">
              <div class="btn">
                <button class="brn_cx" type="button" id="search_btn" >查询</button>
              </div>
            </td>
          </tfoot>
        </table>
      </div>
      <div class="formbox">
        <!--标题start-->
        <h3 class="datetitle_01">
          <span>意见反馈列表</span>
        </h3>
        <!--标题end-->
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
              	<!-- <td>操作</td> -->
                <td>反馈时间</td>
                <td>反馈人</td>
                <td>反馈来源</td>
                <td>反馈内容</td>
              </tr>
            </thead>
            <tbody id="list_body">
              <c:forEach items="${opinionList}" var="info" varStatus="st">
              <tr name="tr">
              <!-- 
              	<td>
                  <a style="color:#074695" href="#" onclick="doOpinion('${info.id}');">详情</a>
                </td>
               -->              	
                <td>${info.fksj }</td>
                <td>${info.yhm }</td>
                <td><c:if test="${info.rylx eq 'teacher'}">教师</c:if><c:if test="${info.rylx eq 'student'}">学生</c:if></td>
                <td>${info.yjfk }</td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        <ct:page pageList="${opinionList }" query="${opinionQuery }" queryName="opinionQuery" />
      </div>
    </form>
  </body>
</html>