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
          $("#search").submit();
        });
        
        // 导出
        $("#btn_dc").click(function() {
          window.open("<%=request.getContextPath()%>/monitor/monitor_export.html?" + $("#search").serialize());
        });
        
        fillRows("20", "", "", false);//填充空行
      });
    </script>
  </head>
  <body>
    <div class="toolbox">
      <div class="buttonbox">
        <ul>
          <li>
            <a id="btn_dc" class="btn_dc">导出巡视记录</a>
          </li>
        </ul>
      </div>
    </div>
    <form action="<%=request.getContextPath()%>/monitor/monitor_patrol.html" name="search" id="search" method="post">
      <div class="searchtab">
        <table width="100%" border="0">
          <tbody>
            <tr>
              <th>巡视日期</th>
              <td>
                <input name="query.xsrq" value="${query.xsrq }" type="text" style="width: 100px;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate"/>
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
              <!-- 
              <th>教学周</th>
              <td>
                <input name="query.jxz" value="${query.jxz }" type="text" style="width: 100px;" class="text_nor"/>
              </td>
               -->
              <th>巡视人员</th>
              <td>
                <input name="query.xsry" value="${query.xsry }" type="text" style="width: 100px;" class="text_nor"/>
              </td>
              <th>巡视地点</th>
              <td>
                <select name="query.xscdmc" style="width: 100px">
                  <option value="" selected>全部</option>
                  <option value="1" <c:if test="${query.xscdmc eq '1' }">selected</c:if>>1</option>
                  <option value="2" <c:if test="${query.xscdmc eq '2' }">selected</c:if>>2</option>
                  <option value="3" <c:if test="${query.xscdmc eq '3' }">selected</c:if>>3</option>
                  <option value="4" <c:if test="${query.xscdmc eq '4' }">selected</c:if>>4</option>
                  <option value="5" <c:if test="${query.xscdmc eq '5' }">selected</c:if>>5</option>
                  <option value="6" <c:if test="${query.xscdmc eq '6' }">selected</c:if>>6</option>
                  <option value="c2" <c:if test="${query.xscdmc eq 'c2' }">selected</c:if>>c2</option>
                  <option value="第一实验楼" <c:if test="${query.xscdmc eq '第一实验楼' }">selected</c:if>>第一实验楼</option>
                  <option value="第二实验楼" <c:if test="${query.xscdmc eq '第二实验楼' }">selected</c:if>>第二实验楼</option>
                  <option value="第六实验楼" <c:if test="${query.xscdmc eq '第六实验楼' }">selected</c:if>>第六实验楼</option>
                  <option value="化工实训楼" <c:if test="${query.xscdmc eq '化工实训楼' }">selected</c:if>>化工实训楼</option>
                  <option value="综合实验楼" <c:if test="${query.xscdmc eq '综合实验楼' }">selected</c:if>>综合实验楼</option>
                </select>
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
          <span>教学巡视</span>
        </h3>
        <!--标题end-->
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
                <td>巡视类型</td>
                <td>巡视日期</td>
                <td>教学周</td>
                <td>星期</td>
                <td>课程节次</td>
                <td>巡视人员</td>
                <td>巡视地点</td>
                <td>操作</td>
              </tr>
            </thead>
            <tbody id="list_body">
              <c:forEach items="${pageList}" var="info" varStatus="st">
              <tr name="tr">
                <td>${info.xslx.text }</td>
                <td>${info.xsrq }</td>
                <td>第${info.jxz }周</td>
                <td>${info.weekOfDay }</td>
                <td>${info.kcjc }</td>
                <td><ct:PersonParse code="${info.xsry }"/></td>
                <td>${info.xscdmc }</td>
                <td><a style="color:#074695" href="<%=request.getContextPath() %>/monitor/monitor_patrolDetail.html?globalid=${info.globalid }">查看巡视记录</a></td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        <ct:page pageList="${pageList }"  query="${query }" queryName="query" />
      </div>
    </form>
  </body>
</html>