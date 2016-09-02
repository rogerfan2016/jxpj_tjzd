<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ct" uri="/custom-code"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <%@ include file="/commons/hrm/head.ini"%>
    <script type="text/javascript">
      $(function() {
      
        // 查询
        $("#search_btn").click(function() {
          $("#search").submit();
        });
        
        if ("${isSet}" == "Y") {
          fillRows("10", "", "", false);//填充空行
        }
      });
      
      // 提交预约
      function doSubmit(globalid) {
        $.post('<%=request.getContextPath() %>/evaluation/teacher_doSubmit.html?bsh=${bsh }&globalid=' + globalid, function(data){
          var callback = function() {
            $("#search").submit();
          };
          if (data.success) {
            processDataCall(data, callback);
          } else {
            showWarning(data.text);
          }
        }, "json");

        return false;
      }
      
      // 取消预约
      function cancelSubmit(globalid) {
        $.post('<%=request.getContextPath() %>/evaluation/teacher_cancelSubmit.html', 'globalid=' + globalid, function(data){
          var callback = function() {
            $("#search").submit();
          };
          if (data.success) {
            processDataCall(data, callback);
          } else {
            showWarning(data.text);
          }
        }, "json");

        return false;
      }
      
    </script>
  </head>
  <body>
    <c:if test="${isSet eq 'N'}">
      <div class="page_prompt">
        <div class="page_promptcon">
          <h3>温馨提示：</h3>
          <p><font color="#0f5dc2">没有开放预约听课申请</font></p>
        </div>
        <p>&nbsp;</p>
      </div>
    </c:if>
    <c:if test="${isSet != 'N' }">
      <form action="<%=request.getContextPath()%>/evaluation/teacher_chooseOpenLesson.html" name="search" id="search" method="post">
      	<input type="hidden" id="bsh" name="bsh" value="${bsh }"/>
        <div class="searchtab">
          <table width="100%" border="0">
            <tbody>
              <tr>
                <th>授课老师工号</th>
                <td>
                  <input type="text" class="text_nor" name="cisQuery.gh" id="rklsgh" value="${cisQuery.gh}" style="width: 100px;" />
                </td>
                <th>授课老师姓名</th>
                <td>
                  <input type="text" class="text_nor" name="cisQuery.xm" id="rklsxm" value="${cisQuery.xm}" style="width: 100px;" />
                </td>
                <th>课程名称</th>
                <td>
                  <input type="text" class="text_nor" name="cisQuery.kcmc" id="kcmc" value="${cisQuery.kcmc}" style="width: 100px;" />
                </td>
                <th>年份</th>
                <td>
                  <input type="text" onfocus="WdatePicker({dateFmt:'yyyy'})" class="Wdate" name="cisQuery.year" id="year" value="${cisQuery.year}" style="width: 100px;" />
                </td>
              </tr>
              <tr>
                <th>上课时间(From)</th>
                <td>
                  <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" name="cisQuery.kcsjFrom" id="kcsjFrom" value="${cisQuery.kcsjFrom}" style="width: 100px;" />
                </td>
                <th>上课时间(To)</th>
                <td colspan="5">
                  <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" name="cisQuery.kcsjTo" id="kcsjTo" value="${cisQuery.kcsjTo}" style="width: 100px;" />
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
            <span>选课列表</span>
          </h3>
          <!--标题end-->
          <div class="con_overlfow">
            <table width="100%" class="dateline tablenowrap">
              <thead id="list_head">
                <tr>
                  <td>序号</td>
                  <td>操作</td>
	              <td>状态</td>                  
                  <td>学年</td>
                  <td>学期</td>
                  <td>专业</td>
                  <td>上课时间</td>
                  <td>上课地点</td>
                  <td>星期</td>
                  <td>课程名称</td>
                  <td>任课老师</td>
                  <td>课程节次</td>
                </tr>
              </thead>
              <tbody id="list_body">
                <c:forEach items="${openLessonList}" var="info" varStatus="st">
                <tr name="tr">
                  <td>${st.index + 1 }</td>
                  <td>
                    <c:if test="${info.shzt eq null or info.shzt eq '' }"><a style="color:#074695" href="#" onclick="doSubmit('${info.globalid}');">预约</a></c:if>
                    <c:if test="${info.shzt eq '0' }"><a style="color:#074695" href="#" onclick="cancelSubmit('${info.globalid}');">取消预约</a></c:if>
                  </td>
                  <td>
                    <c:if test="${info.shzt eq '0' }">未提交</c:if>
                    <c:if test="${info.shzt eq '1' }">待审核</c:if>
                    <c:if test="${info.shzt eq '2' }">待审核</c:if>
                    <c:if test="${info.shzt eq '3' }">审核通过</c:if>
                    <c:if test="${info.shzt eq '4' }">审核不通过</c:if>
                  </td>
                  <td>${info.schoolyear }</td>
                  <td>
                    <c:if test="${info.semester eq '0' }">上学期</c:if>
                    <c:if test="${info.semester eq '1' }">下学期</c:if>
                  </td>
                  <td>${info.sszy }</td>
                  <td>${info.kcsj }</td>
                  <td>${info.skdd }</td>
                  <td>${info.dayofweek }</td>
                  <td>${info.kcmc }</td>
                  <td>${info.rkls }</td>
                  <td>${info.kcjc }</td>
                </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
          <ct:page pageList="${openLessonList }"  query="${cisQuery }" queryName="cisQuery"/>
        </div>
      </form>
    </c:if>
  </body>
</html>