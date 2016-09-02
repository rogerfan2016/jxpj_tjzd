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
          refreshList();
        });
        
        // 新增
        $("#btn_zj").click(function() {
          showWindow("增加巡视任务", "<%=request.getContextPath()%>/monitor/monitor_addPatrol.html?model.xslx=" + $("input[name='query.xslx']").val(), "500","330");
        });
        
        // 修改
        $("#btn_xg").click(function() {
          var globalids = $("input[name='globalids']:checked");
          if (globalids.length == 0) {
            alert("请先选中操作行");
            return false;
          }
          
          if (globalids.length > 1) {
            alert("请选中1行操作");
            return false;
          }
          
          showWindow("修改巡视任务", "<%=request.getContextPath()%>/monitor/monitor_addPatrol.html?model.xslx=" + $("input[name='query.xslx']").val() + "&model.globalid=" + globalids.val(), "500","330");
        });
        
        // 提交
        $("#btn_sh").click(function() {
          var globalids = $("input[name='globalids']:checked");
          if (globalids.length == 0) {
            alert("请先选中操作行");
            return false;
          }
          
          if (globalids.length > 1) {
            alert("请选中1行操作");
            return false;
          }
          showWindow("录入巡视记录", "<%=request.getContextPath()%>/monitor/monitor_addPatrol.html?modify=1&model.xslx=" + $("input[name='query.xslx']").val() + "&model.globalid=" + globalids.val(), "500","630");
        });
        
        // 删除
        $("#btn_sc").click(function() {
          var globalids = $("input[name='globalids']:checked");
          if (globalids.length == 0) {
            alert("请先选中操作行");
            return false;
          }

          $.post('<%=request.getContextPath()%>/monitor/monitor_remove.html',globalids.serialize(),function(data) {
            if(data.success) {
              refreshList();
            } else {
              alert(data.text);
            }
          }, "json");
        });
        
        // 导出
        $("#btn_dc").click(function() {
          window.open("<%=request.getContextPath()%>/monitor/monitor_export.html?" + $("#search").serialize());
        });
        
        fillRows("20", "", "", false);//填充空行
      });
      
      function refreshList() {
        var form = $("#search");
        var xslx = $("input[name='query.xslx']").val();
        if (xslx == "dept") {
          form.attr("action", "<%=request.getContextPath()%>/monitor/monitor_patrolByDept.html");
        } else {
          form.attr("action", "<%=request.getContextPath()%>/monitor/monitor_patrolBySchool.html");
        }

        form.submit();
      }

    </script>
  </head>
  <body>
    <div class="toolbox">
      <div class="buttonbox">
        <ul>
          <li>
            <a id="btn_zj" class="btn_zj">增加巡视任务</a>
          </li>
          <li>
            <a id="btn_xg" class="btn_xg">修改巡视任务</a>
          </li>
          <!-- 
          <li>
            <a id="btn_sh" class="btn_sh">录入巡视记录</a>
          </li>
           -->
          <li>
            <a id="btn_sc" class="btn_sc">删除巡视任务</a>
          </li>
          <li>
            <a id="btn_dc" class="btn_dc">导出巡视记录</a>
          </li>
        </ul>
      </div>
    </div>
    <form action="" name="search" id="search" method="post">
      <div class="searchtab">
        <table width="100%" border="0">
          <tbody>
            <tr>
              <th>巡视日期</th>
              <td>
                <input name="query.xsrq" value="${query.xsrq }" type="text" style="width: 100px;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate"/>
              </td>
              <th>教学周</th>
              <td>
                <input name="query.jxz" value="${query.jxz }" type="text" style="width: 100px;" class="text_nor"/>
              </td>
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
                <input name="query.xslx" value="${query.xslx }" type="hidden" />
                <c:if test="${query.xslx eq 'dept' }">
                <input name="query.xsdw" value="${query.xsdw }" type="hidden" />
                </c:if>
              </td>
            </tr>
            <!-- 
            <c:if test="${query.xslx eq 'school' }">
            <tr>
              <th>开课学院</th>
              <td colspan="7">
                <ct:codePicker name="query.xsdw" catalog="<%=ICodeConstants.DM_DEF_ORG %>" code="${query.xsdw }" width="100"/>
              </td>
            </tr>
            </c:if>
             -->
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
          <span>
          <c:if test="${query.xslx eq 'school' }">学校
          </c:if>
          <c:if test="${query.xslx eq 'dept' }">学院
          </c:if>巡视管理</span>
        </h3>
        <!--标题end-->
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
                <td width="4%"></td>
                <td>巡视日期</td>
                <td>教学周</td>
                <td>星期</td>
                <td>课程节次</td>
                <td>巡视人员</td>
                <td>巡视地点</td>
                <!-- <td>开课学院</td> -->
                <td>操作</td>
              </tr>
            </thead>
            <tbody id="list_body">
              <c:forEach items="${pageList}" var="info" varStatus="st">
              <tr name="tr">
                <td>
                  <input type="checkbox" name="globalids" value="${info.globalid }"/>
                </td>
                <td>${info.xsrq }</td>
                <td>第${info.jxz }周</td>
                <td>${info.weekOfDay }</td>
                <td>${info.kcjc }</td>
                <td><ct:PersonParse code="${info.xsry }"/></td>
                <td>${info.xscdmc }</td>
                <!-- 
                <td>
                  <ct:codeParse catalog="<%=ICodeConstants.DM_DEF_ORG %>" code="${info.xsdw }" />
                </td>
                 -->
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