<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ct" uri="/custom-code"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <%@ include file="/commons/hrm/head.ini"%>
    <script type="text/javascript">
      $(function() {
        // 上一周课程表
        $("#topweek").click(function() {
          $("#week").val("topweek");
          $("#search").submit();
        });
        
        // 本周课程表
        $("#nowweek").click(function() {
          $("#week").val("nowweek");
          $("#search").submit();
        });
        
        // 下一周课程表
        $("#nextweek").click(function() {
          $("#week").val("nextweek");
          $("#search").submit();
        });
      
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

      // 评教
      function showpj(globalid) {
        showWindow("评教信息", "<%=request.getContextPath()%>/evaluation/student_showpj.html?globalid=" + globalid, "480","500");
      }
      
      // 考勤
      function showkq(globalid, type) {
        var title = "";
        if (type == "yq") {
          title = "应出勤人员";
        } else if (type == "sj") {
          title = "实际出勤人员";
        } else if (type == "qq") {
          title = "缺勤勤人员";
        }
        
        showWindow(title, "<%=request.getContextPath()%>/evaluation/student_showkq.html?globalid=" + globalid + "&type=" + type, "480","500");
      }
    </script>
  </head>
  <body>
    <form action="<%=request.getContextPath()%>/evaluation/student_checkin.html" name="search" id="search" method="post">
      <input type="hidden" id="week" name="week" value="${week }"/>
      <input type="hidden" id="firstDay" name="firstDay" value="${firstDay }"/>
      <input type="hidden" id="lastDay" name="lastDay" value="${lastDay }"/>
      <div class="searchtab">
        <table width="100%" border="0">
          <tbody>
            <tr>
              <th>年份</th>
              <td>
                <input type="text" onfocus="WdatePicker({dateFmt:'yyyy'})" class="Wdate" name="query.year" id="year" value="${query.year}" style="width: 70px;" />
              </td>
              <th>学年</th>
              <td>
                <input type="text" onfocus="WdatePicker({dateFmt:'yyyy'})" class="Wdate" name="query.schoolyear" id="schoolyear" value="${query.schoolyear}" style="width: 70px;" />
              </td>
              <th>学期</th>
              <td>
                <select name="query.semester">
                  <option value=""></option>
                  <option value="0" <c:if test="${query.semester eq '0'}">selected</c:if>>上学期</option>
                  <option value="1" <c:if test="${query.semester eq '1'}">selected</c:if>>下学期</option>
                </select>
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
            <td colspan="10">
              <div class="btn">
                <button class="btn_cx" type="button" id="topweek">上一周</button>
                <button class="btn_cx" type="button" id="nowweek">本周</button>
                <button class="btn_cx" type="button" id="nextweek">下一周</button>
                <button class="brn_cx" type="button" id="search_btn" >查询</button>
              </div>
            </td>
          </tfoot>
        </table>
      </div>
      <div class="formbox">
        <!--标题start-->
        <h3 class="datetitle_01">
            <span>上课考勤管理</span>
        </h3>
        <!--标题end-->
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
                <td>上课时间</td>
                <td>星期</td>
                <td>课程名称</td>
                <td>任课老师</td>
                <td>课程节次</td>
                <td>应出勤人数</td>
                <td>实际出勤人数</td>
                <td>缺勤人数</td>
                <td>是否发起评教</td>
                <td>评教参与人数</td>
              </tr>
            </thead>
            <tbody id="list_body">
              <c:forEach items="${pageList}" var="info" varStatus="st">
              <tr name="tr">
                <td>${info.curriculum.kcsj }</td>
                <td>${info.dayofweek }</td>
                <td>${info.curriculum.kcmc }</td>
                <td>${info.curriculum.rkls }</td>
                <td>${info.curriculum.kcjc }</td>
                <td>
                  <c:if test="${0 != info.ycqrs}">
                  <a style="color:#074695" href="#" onclick="showkq('${info.curriculum.globalid}', 'yq');">
                  ${info.ycqrs }
                  </a>
                  </c:if>
                  <c:if test="${0 eq info.ycqrs}">
                  ${info.ycqrs }
                  </c:if>
                </td>
                <td>
                  <c:if test="${0 != info.sjcqrs}">
                  <a style="color:#074695" href="#" onclick="showkq('${info.curriculum.globalid}', 'sj');">
                  ${info.sjcqrs }
                  </a>
                  </c:if>
                  <c:if test="${0 eq info.sjcqrs}">
                  ${info.sjcqrs }
                  </c:if>
                </td>
                <td>
                  <c:if test="${0 != info.qqrs}">
                  <a style="color:#074695" href="#" onclick="showkq('${info.curriculum.globalid}', 'qq');">
                  ${info.qqrs }
                  </a>
                  </c:if>
                  <c:if test="${0 eq info.qqrs}">
                  ${info.qqrs }
                  </c:if>
                </td>
                <td>${info.sffqpj }</td>
                <td>
                  <c:if test="${0 != info.pjcyrs}">
                  <a style="color:#074695" href="#" onclick="showpj('${info.curriculum.globalid}');">
                  ${info.pjcyrs }
                  </a>
                  </c:if>
                  <c:if test="${0 eq info.pjcyrs}">
                  ${info.pjcyrs }
                  </c:if>
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