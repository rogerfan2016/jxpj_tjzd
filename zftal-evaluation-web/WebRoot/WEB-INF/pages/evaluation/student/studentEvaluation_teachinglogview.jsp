<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@page import="com.zfsoft.hrm.config.ICodeConstants"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ct" uri="/custom-code"%>
<%@taglib prefix="code" uri="/WEB-INF/code.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <%@ include file="/commons/hrm/head.ini"%>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/hrm/code.js"></script>
    <script type="text/javascript">
      $(function() {
        // 查询
        $("#search_btn").click(function() {
          if ($("select[name='query.semester']").val() != "" && $.trim($("#schoolyear").val()).length == 0) {
            alert("选择学期时必须选择学年");
            return false;
          }
          $("#week").val("");
          $("#search").submit();
        });
        
        fillRows("20", "", "", false);//填充空行
      });
      
      // 修改
      function detail(globalid) {
        showWindow("查看教学日志", "<%=request.getContextPath()%>/evaluation/student_teachingLodDetail.html?globalid=" + globalid + "&type=view", "480","500");
      }

    </script>
  </head>
  <body>
    <form action="<%=request.getContextPath()%>/evaluation/student_teachinglogview.html" name="search" id="search" method="post">
      <input type="hidden" id="firstDay" name="firstDay" value="${firstDay }"/>
      <input type="hidden" id="lastDay" name="lastDay" value="${lastDay }"/>
      <div class="searchtab">
        <table width="100%" border="0">
          <tbody>
            <tr>
              <th>学年</th>
              <td>
                <input type="text" onfocus="WdatePicker({dateFmt:'yyyy'})" class="Wdate" name="query.schoolyear" id="schoolyear" value="${query.schoolyear}" style="width: 100px;" />
              </td>
              <th>学期</th>
              <td>
                <select name="query.semester" style="width:106px;">
                  <option value=""></option>
                  <option value="0" <c:if test="${query.semester eq '0'}">selected</c:if>>上学期</option>
                  <option value="1" <c:if test="${query.semester eq '1'}">selected</c:if>>下学期</option>
                </select>
              </td>
              <th>学院</th>
              <td>
                <ct:codePicker name="query.dept" catalog="<%=ICodeConstants.DM_DEF_ORG %>" code="${query.dept }" width="100"/>
              </td>
              <th>专业</th>
              <td>
                <input type="text" name="query.zy" class="text_nor" style="width: 100px" value="${query.zy }" />
              </td>
            </tr>
            <tr>
              <th>职工号</th>
              <td>
                <input type="text" name="query.gh" class="text_nor" style="width: 100px" value="${query.gh }" />
              </td>
              <th>姓名</th>
              <td>
                <input type="text" name="query.xm" class="text_nor" style="width: 100px" value="${query.xm }" />
              </td>
              <th>上课时间(From)</th>
              <td>
                <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" name="query.kcsjFrom" id="kcsjFrom" value="${query.kcsjFrom}" style="width: 100px;" />
              </td>
              <th>上课时间(To)</th>
              <td>
                <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" name="query.kcsjTo" id="kcsjTo" value="${query.kcsjTo}" style="width: 100px;" />
              </td>
            </tr>
          </tbody>
          <tfoot>
            <td colspan="8">
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
            <span>教学日志管理</span>
        </h3>
        <!--标题end-->
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
                <td>学年</td>
                <td>学期</td>
                <td>职工号</td>
                <td>任课老师</td>
                <td>上课时间</td>
                <td>星期</td>
                <td>学院</td>
                <td>专业</td>
                <td>课程名称</td>
                <td>课程节次</td>
                <td>操作</td>
              </tr>
            </thead>
            <tbody id="list_body">
              <c:forEach items="${pageList}" var="info" varStatus="st">
              <tr name="tr">
                <td>${info.schoolYear }</td>
                <td>
                  <c:if test="${info.semester eq '0' }">上学期</c:if>
                  <c:if test="${info.semester eq '1' }">下学期</c:if>
                </td>
                <td>${info.curriculum.rklsgh }</td>
                <td>${info.curriculum.rkls }</td>
                <td>${info.curriculum.kcsj }</td>
                <td>${info.dayofweek }</td>
                <td><ct:codeParse catalog="<%=ICodeConstants.DM_DEF_ORG %>" code="${info.curriculum.dept }" /></td>
                <td>${info.curriculum.sszy }</td>
                <td>${info.curriculum.kcmc }</td>
                <td>${info.curriculum.kcjc }</td>
                <td>
                  <a style="color:#074695" href="#" onclick="detail('${info.curriculum.globalid}');">查看详细</a>
                </td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        <ct:page pageList="${pageList }"  query="${query }" queryName="query"/>
      </div>
    </form>
  </body>
</html>