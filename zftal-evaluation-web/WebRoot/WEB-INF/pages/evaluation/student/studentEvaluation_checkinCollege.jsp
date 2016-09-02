<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@page import="com.zfsoft.hrm.config.ICodeConstants"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ct" uri="/custom-code"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
          //if ($("select[name='query.semester']").val() != "" && $.trim($("#query.schoolyear").val()).length == 0) {
          //  alert("选择学期时必须选择学年");
          //  return false;
          //}
          $("#week").val("");
          $("#search").submit();
        });
        
        //fillRows("20", "", "", false);//填充空行
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
    <!-- ECharts单文件引入 -->
    <script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/hrm/code.js"></script>
    <script type="text/javascript">
        // 路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        
        // 使用
        require(
            [
                'echarts',   
	            'echarts/chart/bar',
	            'echarts/chart/line'
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main'),'macarons'); 
                var option = ${echarts_obj};   
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
    </script>
    
  </head>
  <body>
    <form action="<%=request.getContextPath()%>/evaluation/student_checkinCollege.html" name="search" id="search" method="post">
      <input type="hidden" id="week" name="week" value="${week }"/>
      <input type="hidden" id="firstDay" name="firstDay" value="${firstDay }"/>
      <input type="hidden" id="lastDay" name="lastDay" value="${lastDay }"/>
      <div class="searchtab">
        <table width="100%" border="0">
          <tbody>
            <tr>
            <%--
              <th>学年</th>
              <td>
                <ct:codePicker name="query.schoolyear" catalog="<%=ICodeConstants.DM_DEF_XN %>" code="${query.schoolyear }" width="100"/>
              </td>
              <th>学期</th>
              <td>
                <select name="query.semester">
                  <option value=""></option>
                  <option value="0" <c:if test="${query.semester eq '0'}">selected</c:if>>第一学期</option>
                  <option value="1" <c:if test="${query.semester eq '1'}">selected</c:if>>第二学期</option>
                </select>
              </td>
               --%>
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
            <span>按学院学生考勤</span>
        </h3>
        <!--标题end-->
        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    	<div id="main" style="height:400px"></div>
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
                <td>学院</td>
                <td>应出勤总人次</td>
                <td>实际出勤总人次</td>
                <td>出勤比例</td>
                <td>缺勤总人次</td>
                <td>缺勤比例</td>
                <td>评教参与人次</td>
              </tr>
            </thead>
            <tbody id="list_body">
              <c:forEach items="${pageList}" var="info" varStatus="st">
              <tr name="tr">
                <td>${info.dept}</td>
                <td>
                  <!--<c:if test="${0 != info.ycqrs}">
                  <a style="color:#074695" href="#" onclick="showkq('${info.curriculum.globalid}', 'yq');">
                  ${info.ycqrs }
                  </a>
                  </c:if>
                  <c:if test="${0 eq info.ycqrs}">
                  ${info.ycqrs }
                  </c:if>-->
                  <fmt:formatNumber value="${info.ycqrs }"/>
                </td>
                <td>
                  <!--<c:if test="${0 != info.sjcqrs}">
                  <a style="color:#074695" href="#" onclick="showkq('${info.curriculum.globalid}', 'sj');">
                  ${info.sjcqrs }
                  </a>
                  </c:if>
                  <c:if test="${0 eq info.sjcqrs}">
                  ${info.sjcqrs }
                  </c:if>-->
                  <fmt:formatNumber value="${info.sjcqrs }"/>
                </td>
                <td>${info.cqbl }%</td>
                <td>
                  <!--<c:if test="${0 != info.qqrs}">
                  <a style="color:#074695" href="#" onclick="showkq('${info.curriculum.globalid}', 'qq');">
                  ${info.qqrs }
                  </a>
                  </c:if>
                  <c:if test="${0 eq info.qqrs}">
                  ${info.qqrs }
                  </c:if>-->
                  <fmt:formatNumber value="${info.qqrs }"/>
                </td>
                <td>${info.qqbl }%</td>
                <td>
                  <!--<c:if test="${0 != info.pjcyrs}">
                  <a style="color:#074695" href="#" onclick="showpj('${info.curriculum.globalid}');">
                  ${info.pjcyrs }
                  </a>
                  </c:if>
                  <c:if test="${0 eq info.pjcyrs}">
                  ${info.pjcyrs }
                  </c:if>-->
                  ${info.pjcyrs }
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