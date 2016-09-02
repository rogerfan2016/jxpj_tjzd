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
        
        // 新增预约
        $("#btn_zj").click(function() {
          showTopWin("<%=request.getContextPath()%>/evaluation/teacher_chooseOpenLesson.html?bsh=${bsh}&cisQuery.perPageSize=10", "750","550","yes");
          $("#search").submit();
        });
        
        initSelect("tolQuery.shzt", "${tolQuery.shzt}");
        
        fillRows("20", "", "", false);//填充空行
      });
      
      // 提交审核
      function doAudit(globalid) {
        $.post('<%=request.getContextPath() %>/evaluation/teacher_changeStatus.html', 'globalid=' + globalid + '&status=1', function(data){
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
      
      // 取消审核
      function cancelAudit(globalid) {
        $.post('<%=request.getContextPath() %>/evaluation/teacher_changeStatus.html', 'globalid=' + globalid + '&status=0', function(data){
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
    <div class="toolbox">
      <div class="buttonbox">
        <ul>
          <li>
            <a id="btn_zj" class="btn_zj">新增预约</a>
          </li>
          <li>
            <a id="btn_sc" class="btn_sc">取消预约</a>
          </li>
        </ul>
      </div>
    </div>
    <form action="<%=request.getContextPath()%>/evaluation/teacher_declareOpenLesson.html" name="search" id="search" method="post">
      <div class="searchtab">
        <table width="100%" border="0">
          <tbody>
            <tr>
              <th width="50">月　份</th>
              <td width="120">
                <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" class="Wdate" name="tolQuery.tkyf" id="tkyf" value="${tolQuery.tkyf}" style="width: 100px;" />
              </td>
              <th width="70">审核状态</th>
              <td>
                <select name="tolQuery.shzt">
                  <option value="">全部</option>
                  <option value="0">未提交</option>
                  <option value="1">待审核</option>
                  <option value="2">审核通过</option>
                  <option value="3">审核不通过</option>
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
          <span>预约听课列表</span>
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
              <c:forEach items="${lessonList}" var="info" varStatus="st">
              <tr name="tr">
              	<td>${st.index + 1 }</td>
              	<td>
                  <c:if test="${info.shzt eq '0' or info.shzt eq '4' }"><a style="color:#074695" href="#" onclick="doAudit('${info.globalid}');">提交审核</a></c:if>
                  <c:if test="${info.shzt eq '1' }"><a style="color:#074695" href="#" onclick="cancelAudit('${info.globalid}');">取消审核</a></c:if>
                  <c:if test="${info.shzt != '1' && info.shzt != '0'}">无</c:if>
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
                <td>${info.curriculum.sszy }</td>
                <td>${info.curriculum.kcsj }</td>
                <td>${info.curriculum.skdd }</td>
                <td>${info.dayofweek }</td>
                <td>${info.curriculum.kcmc }</td>
                <td>${info.curriculum.rkls }</td>
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