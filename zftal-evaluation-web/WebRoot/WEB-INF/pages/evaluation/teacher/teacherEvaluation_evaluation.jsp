<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ct" uri="/custom-code"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <%@ include file="/commons/hrm/head.ini"%>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/hrm/code.js"></script>
    <script type="text/javascript">
      $(function() {
        
        // 查询
        $("#search_btn").click(function() {
          if ($("select[name='tolQuery.semester']").val() != "" && $.trim($("#schoolyear").val()).length == 0) {
            alert("选择学期时必须选择学年");
            return false;
          }
          $("#search").submit();
        });
        initSelect("tolQuery.shzt", "${tolQuery.shzt}");
        fillRows("20", "", "", false);//填充空行
      });
      
      // 评教
      function doEvaluation(pjid) {
        showWindow("查看问卷", "<%=request.getContextPath()%>/evaluation/setting_evaluation.html?pjid=" + pjid, "480","230");
      }

    </script>
  </head>
  <body>
    <form action="<%=request.getContextPath()%>/evaluation/teacher_evaluation.html" name="search" id="search" method="post">
      <input name="phase" type="hidden" value="${phase }"/>
      <div class="searchtab">
        <table width="100%" border="0">
          <tbody>
            <tr>
              <th>学年</th>
              <td>
                <input type="text" onfocus="WdatePicker({dateFmt:'yyyy'})" class="Wdate" name="tolQuery.schoolyear" id="schoolyear" value="${tolQuery.schoolyear}" style="width: 80px;" />
              </td>
              <th>学期</th>
              <td>
                <select name="tolQuery.semester" style="width:80px;">
                  <option value=""></option>
                  <option value="0" <c:if test="${tolQuery.semester eq '0'}">selected</c:if>>上学期</option>
                  <option value="1" <c:if test="${tolQuery.semester eq '1'}">selected</c:if>>下学期</option>
                </select>
              </td>
              <th>职工号</th>
              <td>
                <input type="text" class="text_nor" name="tolQuery.tkjsid" id="tkjsid" value="${tolQuery.tkjsid}" style="width: 80px;" />
              </td>
              <th>姓名</th>
              <td>
                <input type="text" class="text_nor" name="tolQuery.tkjsxm" id="tkjsxm" value="${tolQuery.tkjsxm}" style="width: 80px;" />
              </td>
              <th>是否评价</th>
              <td>
                <select name="tolQuery.sfpj" style="width:80px;">
                  <option value="0" <c:if test="${tolQuery.sfpj eq '0'}">selected</c:if>>未评价</option>
                  <option value="1" <c:if test="${tolQuery.sfpj eq '1'}">selected</c:if>>已评价</option>
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
          <span>被听课列表</span>
        </h3>
        <!--标题end-->
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
              	<td>操作</td>
                <td>年度</td>
                <td>学期</td>
                <td>职工号</td>
                <td>任课老师</td>
                <td>上课时间</td>
                <td>上课地点</td>
                <td>星期</td>
                <td>课程名称</td>
                <td>课程节次</td>
              </tr>
            </thead>
            <tbody id="list_body">
              <c:forEach items="${lessonList}" var="info" varStatus="st">
              <tr name="tr">
              	<td>
                  <a style="color:#074695" href="#" onclick="doEvaluation('${info.pjid}');">评教</a>
                </td>
                <td>${info.schoolyear }</td>
                <td>
                  <c:if test="${info.semester eq '0' }">上学期</c:if>
                  <c:if test="${info.semester eq '1' }">下学期</c:if>
                </td>
                <td>${info.curriculum.rklsgh }</td>
                <td>${info.curriculum.rkls }</td>
                <td>${info.curriculum.kcsj }</td>
                <td>${info.curriculum.skdd }</td>
                <td>${info.dayofweek }</td>
                <td>${info.curriculum.kcmc }</td>
                <td>${info.curriculum.kcjc }</td>
                
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        <ct:page pageList="${lessonList }" query="${tolQuery }" queryName="tolQuery" />
      </div>
    </form>
  </body>
</html>