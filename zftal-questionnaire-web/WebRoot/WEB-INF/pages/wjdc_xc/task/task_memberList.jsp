<%@ page language="java" import="java.util.*,com.zfsoft.hrm.config.ICodeConstants" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="ct" uri="/custom-code"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
     <%@ include file="/commons/hrm/head.ini" %>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.ui.core.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.ui.all.css" type="text/css" media="all" />
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/hrm/code.js"></script>
    <script type="text/javascript">
   		$(function() {
      
        // 查询
        $("#search_btn").click(function() {
          refreshList();
        });
        // 导出
        $("#btn_dc").click(function() {
          window.open("<%=request.getContextPath()%>/inspection/task_exportMember.html?" + $("#search").serialize());
        });
        
        fillRows("20", "", "", false);//填充空行
      });
      
      // 查询列表
      function refreshList() {
        var form = $("#search");
        form.attr("action", "<%=request.getContextPath()%>/inspection/task_searchMember.html");
        form.submit();
      }
    </script>
  </head>
  <body>
  <div class="toolbox">
        <!-- 按钮 -->
                <div class="buttonbox">
                    <ul>
                        <li>
				            <a id="btn_dc" class="btn_dc">导出评价人员</a>
				        </li>
                    </ul>
                </div>
          <p class="toolbox_fot">
                <em></em>
            </p>
        </div>
 <form action="<%=request.getContextPath()%>/inspection/task_searchMember.html" name="search" id="search" method="post">
 	<input type="hidden" name="query.id" value="${query.id }" />
 	<div class="searchtab">
        <table width="100%" border="0">
          <tbody>
            <tr>
              <th>学院</th>
              <td>
                <ct:codePicker name="query.xy" catalog="<%=ICodeConstants.DM_DEF_ORG %>" code="${query.xy }" width="100"/>
              </td>
              <th>姓名</th>
              <td>
                <input name="query.xm" value="${query.xm }" type="text" style="width: 100px;" class=""text_nor"/>
              </td>
              <th>专业</th>
              <td>
                <input name="query.zy" value="${query.zy }" type="text" style="width: 100px;" class=""text_nor"/>
              </td>
              <th>行政班</th>
              <td>
                <input name="query.xzb" value="${query.xzb }" type="text" style="width: 100px;" class=""text_nor"/>
              </td>
            </tr>
          </tbody>
          <tfoot>
            <td colspan="8">
              <div class="btn">
                <button class="brn_cx" type="button" name="search_btn" id="search_btn" >查询</button>
              </div>
            </td>
          </tfoot>
        </table>
      </div>
         
	<div class="formbox">
<!--标题start-->
    <h3 class="datetitle_01">
        <span>评价人员列表<font color="#0457A7" style="font-weight:normal;"> </font></span>
    </h3>
<!--标题end-->
    <div class="con_overlfow">
        <table width="100%" class="dateline tablenowrap" id="tiptab">
                <thead id="list_head">
                    <tr>
                        <td>职工号/学号</td>
                        <td>姓名</td>
                        <td>学院</td>
                        <td>专业</td>
                        <td>行政班</td>
                    </tr>
                </thead>
                <tbody id="list_body" >
                    <s:iterator value="memberList" var="p" status="st">
                        <tr name="tr">
                            <td>${p.gh }</td>
                            <td>${p.xm } </td>
                            <td>${p.xy }</td>
                            <td>${p.zy }</td>
                            <td>${p.xzb }</td>
                        </tr>
                    </s:iterator>
                </tbody>
    	</table>
    </div>
    <ct:page pageList="${memberList }" queryName="query" />
    </div>
      </form>
  </body>
</html>
