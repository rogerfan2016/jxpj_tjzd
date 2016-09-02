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
      
        $("#back").click(function(){//功能条返回按钮
			location.href=javascript:history.go(-1);
		});
        
        fillRows("20", "", "", false);//填充空行
      });
    </script>
  </head>
  <body>
    <div class="toolbox">
      <div class="buttonbox">
        <a id="back" class="btn_fh_rs" style="cursor: pointer" onclick="history.go(-1)">返 回</a>
      </div>
    </div>
      <div class="searchtab">
      	<!--标题start-->
        <h3 class="datetitle_01">
          <span>教学巡视任务</span>
        </h3>
        <!--标题end-->
        <table width="100%" border="0">
          <tbody>
            <tr>
              <td><b>巡视日期：</b>${model.xsrq }</td>
              <td><b>巡视类型：</b>${model.xslx.text }</td>
              <td><b>教学周：</b>${model.jxz }</td>
              <td><b>星期：</b>${model.weekOfDay }</td>
           	</tr>
           	<tr>
              <td><b>巡视人员：</b>${model.xsryxm }</td>
              <td><b>巡视场地：</b>${model.xscdmc }</td>
              <td><b>课程节次：</b>第${model.kcjc }节</td>
              <td></td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="formbox">
        <!--标题start-->
        <h3 class="datetitle_01">
          <span>教学巡视记录</span>
        </h3>
        <!--标题end-->
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
              	<td>存在问题</td>
                <td>补充说明</td>
                <td>院系处理意见</td>
                <td>学校审核意见</td>
                <td>巡视人员</td>
                <td>上报时间</td>
                <td>状态</td>
                <td>开课学院</td>
                <td>巡视地点</td>
                <td>教学楼</td>
                <td>课程名称</td>
                <td>教师姓名</td>
                <td>上课学生数</td>
               	<td>上课班级</td> 
              </tr>
            </thead>
            <tbody id="list_body">
              <c:forEach items="${model.patrolDetailEntityList}" var="info" varStatus="st">
              <tr name="tr">
              	<td>${info.czwt }<c:if test="${info.bz != null}">（${info.bz }）</c:if></td>
                <td>${info.bz }</td>
                <td>${info.yxclyj }</td>
                <td>${info.xxclyj }</td>
                <td>${info.xsryxm }</td>
                <td><fmt:formatDate value="${info.sbsj }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>
                	<c:if test="${info.zt eq '0' }"><font color="red">未上报</font></c:if>
                	<c:if test="${info.zt eq '1' }"><font color="green">已上报</font></c:if>
                	<c:if test="${info.zt eq '2' }"><font color="green">院系已处理</font></c:if>
                	<c:if test="${info.zt eq '3' }"><font color="green">学校已审核</font></c:if>
                </td>
                <td><ct:codeParse catalog="<%=ICodeConstants.DM_DEF_ORG %>" code="${info.kkxy }" /></td>
                <td>${info.xsdd }</td>
                <td>${info.jxl }</td>
                <td>${info.kcmc }</td>
                <td>${info.jsxm }</td>
                <td>${info.skxss }人</td>
				<td>${info.skbj }</td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
  </body>
</html>