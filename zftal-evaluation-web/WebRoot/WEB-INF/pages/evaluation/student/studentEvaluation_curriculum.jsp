<%@ page language="java" import="java.util.*,com.zfsoft.hrm.config.ICodeConstants" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ct" uri="/custom-code"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <%@ include file="/commons/hrm/head.ini" %>
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
    
    });
    
    // 查看课程信息
    function detail(kcid, skc, mark) {
      var param = "<%=request.getContextPath()%>/evaluation/student_chooseCurriculum.html";
      param += "?kcid=" + kcid + "&skc=" + skc + "&kcs=2";
      param += "&firstDay=" + $("#firstDay").val();
      param += "&mark=" + mark;
      //showTopWin(param, "480","400","yes");
      showWindow("课程信息", param, "480", "400");
    }
    </script>
  </head>
  <body>
    <form action="<%=request.getContextPath()%>/evaluation/student_curriculum.html" name="search" id="search" method="post">
      <input type="hidden" id="week" name="week" value="${week }"/>
      <input type="hidden" id="firstDay" name="firstDay" value="${firstDay }"/>
      <input type="hidden" id="lastDay" name="lastDay" value="${lastDay }"/>
      <div class="formbox">
        <!--标题start-->
        <h3 class="datetitle_01">
          <span>课程表</span>
        </h3>
        <!--标题end-->
        <div class="searchtab">
          <table width="100%" border="0">
            <tfoot>
              <tr>
                <td>
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
        <div class="con_overlfow">
          <table width="100%" border="1" style="border-width:1px;border-style:solid;border-color:#b9b9b9;" id="tiptab">
            <thead>
              <tr align="center" style="background:#F2F2F2;font-weight:bold;" height="50px">
                <td width="9%">课表</td>
                <td width="13%">
                  <c:if test="${nowdayofweek eq 0 and now eq firstDay }">
                    <font class="red">星期日</br>${firstDay }</font>
                  </c:if>
                  <c:if test="${nowdayofweek != 0 or now != firstDay }">星期日</br>${firstDay }</c:if></td>
                <td width="13%">
                  <c:if test="${nowdayofweek eq 1 and now eq mon }">
                    <font class="red">星期一</br>${mon }</font>
                  </c:if>
                  <c:if test="${nowdayofweek != 1 or now != mon }">星期一</br>${mon }</c:if></td>
                <td width="13%">
                  <c:if test="${nowdayofweek eq 2 and now eq tues }">
                    <font class="red">星期二</br>${tues }</font>
                  </c:if>
                  <c:if test="${nowdayofweek != 2 or now != tues }">星期二</br>${tues }</c:if></td>
                <td width="13%">
                  <c:if test="${nowdayofweek eq 3 and now eq wed }">
                    <font class="red">星期三</br>${wed }</font>
                  </c:if>
                  <c:if test="${nowdayofweek != 3 or now != wed }">星期三</br>${wed }</c:if></td>
                <td width="13%">
                  <c:if test="${nowdayofweek eq 4 and now eq thurs }">
                    <font class="red">星期四</br>${thurs }</font>
                  </c:if>
                  <c:if test="${nowdayofweek != 4 or now != thurs }">星期四</br>${thurs }</c:if></td>
                <td width="13%">
                  <c:if test="${nowdayofweek eq 5 and now eq fri }">
                    <font class="red">星期五</br>${fri }</font>
                  </c:if>
                  <c:if test="${nowdayofweek != 5 or now != fri }">星期五</br>${fri }</c:if></td>
                <td width="13%">
                  <c:if test="${nowdayofweek eq 6 and now eq lastDay }">
                    <font class="red">星期六</br>${lastDay }</font>
                  </c:if>
                  <c:if test="${nowdayofweek != 6 or now != lastDay }">星期六</br>${lastDay }</c:if></td>
              </tr>
            </thead>
            <tbody>
              <c:forEach begin="0" end="${trCnt - 1 }" varStatus="topSt">
              <tr align="center" height="80px">
                <c:if test="${(topSt.index * 2 + 1) >= totalCnt }">
                  <td>${topSt.index * 2 + 1 }</td>
                </c:if>	
                <c:if test="${(topSt.index * 2 + 1) < totalCnt }">
                  <td>${topSt.index * 2 + 1 } - ${topSt.index * 2 + 2 }</td>
                </c:if>
                <c:forEach begin="0" end="6" varStatus="tdSt">
                  <c:set value="${curriculumSchedule[topSt.index * 2][tdSt.index]}" var="model1" />
                  <c:set value="${curriculumSchedule[topSt.index * 2 + 1][tdSt.index]}" var="model2" />
                  <td>
                    <c:if test="${model1 != null }">
                      <a style="color:#074695;" href="#" onclick="detail('${model1.kcid }', '${topSt.index }', '${tdSt.index }')">
                      <div>${model1.kcmc }</div>
                      <div>${model1.rkls }</div>
                      <div>${model1.skdd }</div>
                      <div>${model1.sszy }</div>
                      </a>
                    </c:if>
                    <c:if test="${model2 != null }">
                    <c:if test="${model1 != null and model2.kcid != model1.kcid }">
                      ----------------------
                      <a style="color:#074695;" href="#" onclick="detail('${model2.kcid }', '${topSt.index }', '${tdSt.index }')">
                      <div>${model2.kcmc }</div>
                      <div>${model2.rkls }</div>
                      <div>${model2.skdd }</div>
                      <div>${model2.sszy }</div>
                      </a>
                    </c:if>
                    <c:if test="${model1 == null }">
                      ----------------------
                      <a style="color:#074695;" href="#" onclick="detail('${model2.kcid }', '${topSt.index }', '${tdSt.index }')">
                      <div>${model2.kcmc }</div>
                      <div>${model2.rkls }</div>
                      <div>${model2.skdd }</div>
                      <div>${model2.sszy }</div>
                      </a>
                    </c:if>
                    </c:if>
                  </td>
                </c:forEach>
              </tr>
              </c:forEach>
            </tbody>
            <tfoot>
            
            </tfoot>
          </table>
        </div>
      </div>
    </form>
  </body>
</html>
