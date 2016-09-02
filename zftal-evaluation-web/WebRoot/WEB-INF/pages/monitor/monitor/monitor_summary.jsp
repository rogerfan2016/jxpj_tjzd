<%@ page language="java" import="java.util.*,com.zfsoft.hrm.config.ICodeConstants" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <%@ include file="/commons/hrm/head.ini" %>
    <script type="text/javascript">
    $(function() {
      // 查询
        $("#search_btn").click(function() {
          $("#search").submit();
        });
      // 上一周
      $("#topweek").click(function() {
        $("#week").val("topweek");
        $("#search").submit();
      });
      
      // 本周
      $("#nowweek").click(function() {
        $("#week").val("nowweek");
        $("#search").submit();
      });
      
      // 下一周
      $("#nextweek").click(function() {
        $("#week").val("nextweek");
        $("#search").submit();
      });
    
    });
    
    // 查看存在问题解决情况
    function detail(kcid, skc, mark) {
      var param = "<%=request.getContextPath()%>/evaluation/student_chooseCurriculum.html";
      param += "?kcid=" + kcid + "&skc=" + skc + "&kcs=2";
      param += "&firstDay=" + $("#firstDay").val();
      param += "&mark=" + mark;
      showWindow("课程信息", param, "480", "400");
    }
    </script>
    <!-- ECharts单文件引入 -->
    <script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
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
    <form action="<%=request.getContextPath()%>/monitor/monitor_summary.html" name="search" id="search" method="post">
      <input type="hidden" id="week" name="week" value="${week }"/>
      <input type="hidden" id="firstDay" name="firstDay" value="${firstDay }"/>
      <input type="hidden" id="lastDay" name="lastDay" value="${lastDay }"/>
      <div class="searchtab">
        <table width="100%" border="0">
          <tbody>
            <tr>
              <th>巡视日期</th>
              <td>
                <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" name="fromTime" id="fromTime" value="${fromTime}" style="width: 100px;" />--
                <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" name="toTime" id="toTime" value="${toTime}" style="width: 100px;" />
              </td>
              <th>巡视类型</th>
              <td>
                <select name="query.xslx" style="width:106px">
                  <option value="">全部</option>
                  <c:forEach items="${patrols}" var="p">
                  <option value="${p }" <c:if test="${p eq query.xslx }">selected</c:if>>${p.text }</option>
                  </c:forEach>
                </select>
              </td>
            </tr>
          </tbody>
          <tfoot>
            <td colspan="4">
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
          <span>按学院汇总统计巡视情况</span>
        </h3>
        <!--标题end-->
        <div class="searchtab">
          <table width="100%" border="0">
            <tfoot>
              <tr>
                <td>
                 	【 时间范围】：<c:if test="${firstDay == '' and lastDay != '' }">最早 -- ${lastDay }</c:if>
                 	<c:if test="${firstDay != '' and lastDay != '' }">${firstDay } -- ${lastDay }</c:if> 
                 	<c:if test="${lastDay == '' and firstDay != ''}">${firstDay } -- 现在</c:if>  
                 	 【巡视类型】：<c:if test="${query.xslx eq null or query.xslx == ''}">全部</c:if>
                 	 <c:if test="${query.xslx eq 'dept'}">学院</c:if>
                 	 <c:if test="${query.xslx eq 'school'}">学校</c:if>
                  <div class="btn">
                    <button class="btn_cx" type="button" id="topweek">上一周</button>
                    <button class="btn_cx" type="button" id="nowweek">本周</button>
                    <button class="btn_cx" type="button" id="nextweek">下一周</button>
                  </div>
                </td>
              </tr>
            </tfoot>
          </table>
        </div>
        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    	<div id="main" style="height:400px"></div>
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
                <td>巡视单位(院系)</td>
                <td>巡视任务数</td>
                <td>巡视课程数</td>
                <td>正常数量</td>
                <td>存在问题数量</td>
                <td>正常比例</td>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${list}" var="info" varStatus="st">
              <tr name="tr">
                  <td>${info.xsdw }</td>
                  <td>${info.xsrws }</td>
                  <td>${info.xsjls }</td>
                  <td>${info.zcs }</td>
                  <td>${info.czwts }</td>
                  <td>${info.zcbl }%</td>              
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </form>
  </body>
</html>
