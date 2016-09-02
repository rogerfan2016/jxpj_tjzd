<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.zfsoft.hrm.config.ICodeConstants"%>
<%@taglib prefix="ct" uri="/custom-code"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <%@ include file="/commons/hrm/head.ini"%>
    <script type="text/javascript">
      $(function() {
        // 查询
        $("#search_btn").click(function() {
        	if($.trim($("input[name='query.xsid']").val()).length == 0 
        		&& $.trim($("input[name='query.xsxm']").val()).length == 0) {
     			alert("请输入学号或姓名查询条件！");
     			return false;
        	}
          	$("#search").submit();
        });
        
        fillRows("20", "", "", false);//填充空行
      });
    </script>
  </head>
  <body>
    <form action="<%=request.getContextPath()%>/evaluation/student_checkinDetail.html" name="search" id="search" method="post">
      <div class="searchtab">
        <table width="100%" border="0">
          <tbody>
            <tr>
              <th>学号</th>
              <td>
                <input name="query.xsid" value="${query.xsid }" type="text" style="width: 100px;" class="text_nor"/>
              </td>
              <th>姓名</th>
              <td>
                <input name="query.xsxm" value="${query.xsxm }" type="text" style="width: 100px;" class="text_nor"/>
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
            <span>缺勤学生查询</span>
        </h3>
        <!--标题end-->
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
                <td>学号</td>
                <td>姓名</td>
                <td>年级</td>
                <td>班级</td>
                <td>缺勤情况</td>
                <td>课程名称</td>
                <td>上课地点</td>
                <td>上课时间</td>
                <td>开课学院</td>
              </tr>
            </thead>
            <tbody id="list_body">
              <c:forEach items="${list}" var="info" varStatus="st">
              <tr name="tr">
                <td>${info.xsid }</td>
                <td>${info.xsxm }</td>
                <td>${info.nj }</td>
                <td>${info.xzb }</td>
                <td>${info.kqqk }</td>
                <td>${info.kcmc }</td>
                <td>${info.skdd }</td>
                <td>${info.sksj }</td>
                <td>${info.kkxy }</td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        <ct:page pageList="${list }"  query="${query }" queryName="query"/>
      </div>
    </form>
  </body>
</html>