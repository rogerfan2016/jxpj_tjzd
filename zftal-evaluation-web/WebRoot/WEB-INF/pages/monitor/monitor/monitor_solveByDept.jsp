    <%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@page import="com.zfsoft.hrm.config.ICodeConstants"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
          window.open("<%=request.getContextPath()%>/monitor/monitor_export.html" + $("#search").serialize());
        });
        
        fillRows("20", "", "", false);//填充空行
      });
      
      function modifyDetail(id){
      	showWindow("反馈处理巡视记录", "<%=request.getContextPath()%>/monitor/monitor_feedBackByDept.html?detail.id=" + id, "400","330");
      }
      
      function refreshList() {
        var form = $("#search");
        form.attr("action", "<%=request.getContextPath()%>/monitor/monitor_solveByDept.html");
        form.submit();
      }
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
    <form action="<%=request.getContextPath()%>/monitor/monitor_solveByDept.html" name="search" id="search" method="post">
      <div class="searchtab">
        <table width="100%" border="0">
          <tbody>
            <tr>
              <th>巡视日期</th>
              <td>
                <input name="query.xsrq" value="${query.xsrq }" type="text" style="width: 100px;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate"/>
              </td>
              <th>巡视人员</th>
              <td>
                <input name="query.xsry" value="${query.xsry }" type="text" style="width: 100px;" class="text_nor"/>
              </td>
              <th>巡视场地</th>
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
            <tr>
              <th>巡视类型</th>
              <td>
                <select name="query.xslx" style="width:106px">
                  <option value="">全部</option>
                  <c:forEach items="${patrols}" var="p">
                  <option value="${p }" <c:if test="${p eq query.xslx }">selected</c:if>>${p.text }</option>
                  </c:forEach>
                </select>
              </td>
              <th>反馈状态</th>
              <td  colspan="3">
                <select name="query.zt" style="width: 100px">
                  <option value="" selected>全部</option>
                  <option value="1" <c:if test="${query.zt eq '1' }">selected</c:if>>未处理</option>
                  <option value="2" <c:if test="${query.zt eq '2' }">selected</c:if>>已处理</option>
                  <option value="3" <c:if test="${query.zt eq '3' }">selected</c:if>>审核通过</option>
                  <option value="4" <c:if test="${query.zt eq '4' }">selected</c:if>>审核不通过</option>
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
          <span>教学巡视记录列表</span>
        </h3>
        <!--标题end-->
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
                <td>操作</td>
                <td>院系处理意见</td>
                <td>存在问题</td>
                <td>状态</td>
                <td>巡视日期</td>
                <td>巡视类型</td>
                <td>巡视人员</td>
                <td>上报时间</td>
                <td>巡视教室</td>
                <td>教学楼</td>
                <td>开课学院</td>
                <td>课程名称</td>
                <td>授课教师</td>
                <td>上课学生数</td>
                <td>上课班级</td>
              </tr>
            </thead>
            <tbody id="list_body">
              <c:forEach items="${patrolDetailList}" var="info" varStatus="st">
              <tr name="tr">
              	<td>
              	<c:if test="${info.xslx eq 'dept' }">
                	<c:if test="${info.zt eq '1' or info.zt eq '3'}"><a style="color:#074695" href="#" onclick="modifyDetail(${info.id });">处理</a></c:if>
                	<c:if test="${info.zt eq '2' or info.zt eq '4'}">已处理</c:if>
                </c:if>
                <c:if test="${info.xslx eq 'school' }">
                	<c:if test="${info.zt eq '1' or info.zt eq '3'}"><a style="color:#074695" href="#" onclick="modifyDetail(${info.id });">反馈</a></c:if>
                	<c:if test="${info.zt eq '2' or info.zt eq '4'}">已反馈</c:if>
                </c:if>
                </td>
                <td>${info.yxclyj }</td>
                <td>${info.czwt }<c:if test="${info.bz != null}">（${info.bz }）</c:if></td>
                <td>
                	<c:if test="${info.zt eq '0' }"><font color="red">未上报</font></c:if>
                	<c:if test="${info.zt eq '1' }"><font color="green">已上报</font></c:if>
                	<c:if test="${info.zt eq '2' }"><font color="green">院系已处理</font></c:if>
                	<c:if test="${info.zt eq '3' }"><font color="red">审核通过</font></c:if>
                	<c:if test="${info.zt eq '4' }"><font color="green">审核不通过</font></c:if>
                </td> 
                <td>${info.xsrq }</td>
                <td>
                	<c:if test="${info.xslx eq 'dept' }">院系</c:if>
                	<c:if test="${info.xslx eq 'school' }">学校</c:if>
                </td>
                <td>${info.xsryxm }</td>
                <td><fmt:formatDate value="${info.sbsj }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>${info.xsdd }</td>
                <td>${info.jxl }</td>
                <td><ct:codeParse catalog="<%=ICodeConstants.DM_DEF_ORG %>" code="${info.kkxy }" /></td>
                <td>${info.kcmc }</td>
                <td>${info.jsxm }</td>
                <td>${info.skxss }人</td>
                <td>${info.skbj }</td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        <ct:page pageList="${patrolDetailList }"  query="${query }" queryName="query" />
      </div>
    </form>
  </body>
</html>