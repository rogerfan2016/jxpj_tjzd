<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@page import="com.zfsoft.hrm.config.ICodeConstants"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ct" uri="/custom-code"%>
<%@taglib prefix="code" uri="/WEB-INF/code.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <%@ include file="/commons/hrm/head.ini"%>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/hrm/code.js"></script>
    <script type="text/javascript">
      $(function() {
      
      	$("tbody > tr[name^='tr']").dblclick(function(){//行数据双击事件
			var id = $(this).find("input[name='ids']").val();
			queryEntity(id);
		});
      
        // 查询
        $("#search_btn").click(function() {
          refreshList();
        });
        
        // 导出
        $("#btn_dc").click(function() {
          window.open("<%=request.getContextPath()%>/evaluation/class_export.html?" + $("#search").serialize());
        });
        
        fillRows("20", "", "", false);//填充空行
      });

	  // 查询
      function refreshList() {
        var form = $("#search");
        form.attr("action", "<%=request.getContextPath()%>/evaluation/class_checkClassInstructionsList2.html");
        form.submit();
      }
      
    </script>
  </head>
  <body>
    <div class="toolbox">
      <div class="buttonbox">
        <ul>
          <!--<li>
            <a id="btn_dc" class="btn_dc">导出</a>
          </li>
        --></ul>
      </div>
    </div>
    <form action="" name="search" id="search" method="post">
      <div class="searchtab">
        <table width="100%" border="0">
          <tbody>
            <tr>
              <th>学年</th>
              <td>
                <ct:codePicker name="query.xn" catalog="<%=ICodeConstants.DM_DEF_XN %>" code="${query.xn }" width="100"/>
              </td>
              <th>学期</th>
              <td>
                <select name="query.xq" style="width: 100px">
                  <option value="" selected>全部</option>
                  <option value="1" <c:if test="${query.xq eq '1' }">selected</c:if>>第1学期</option>
                  <option value="2" <c:if test="${query.xq eq '2' }">selected</c:if>>第2学期</option>
                </select>
              </td>
              <th>院系</th>
              <td>
                <ct:codePicker name="query.yx" catalog="<%=ICodeConstants.DM_DEF_ORG %>" code="${query.yx }" width="100"/>
              </td>
             </tr>
             <tr>
              <th>任课教师</th>
              <td>
                <input name="query.rkjs" value="${query.rkjs }" type="text" style="width: 100px;" class=""text_nor"/>
              </td>
              <th>课程名称</th>
              <td>
                <input name="query.kcmc" value="${query.kcmc }" type="text" style="width: 100px;" class=""text_nor"/>
              </td>
              <th>状态</th>
              <td>
                <select name="query.zt" style="width: 100px">
                  <option value="" selected>全部</option>
                  <option value="2" <c:if test="${query.zt eq '2' }">selected</c:if>>待审核</option>
                  <option value="3" <c:if test="${query.zt eq '3' }">selected</c:if>>已审核</option>
                </select>
              </td>
            </tr>
          </tbody>
          <tfoot>
            <td colspan="6">
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
          <span>任课说明书列表</span>
        </h3>
        <!--标题end-->
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
                <td>操作</td>
                <td>状态</td>
                <td>任课老师</td>
                <td>课程名称</td>
                <td>学年</td>
                <td>学期</td>
                <td>院系</td>
                <td>专业</td>
                <td>年级</td>
                <td>班级</td>
                <td>讲课周次</td>
                <td>开始周</td>
                <td>结束周</td>
                <td>总学时</td>
                <td>讲课学时</td>
                <td>习题学时</td>
                <td>课堂讨论学时</td>
                <td>案例教学学时</td>
                <td>审核意见</td>
              </tr>
            </thead>
            <tbody id="list_body">
              <c:forEach items="${pageList}" var="info" varStatus="st">
              <tr name="tr">
                <td>
                <c:if test="${info.zt eq '2' }">
                	<a style="color:#074695" onclick="showWindow('任课说明书详情', '<%=request.getContextPath()%>/evaluation/class_checkClassInstructionsDetailList2.html?id=${info.id }', '700','550');" href="#">审核</a>
                </c:if>
                <c:if test="${info.zt eq '3' or info.zt eq '4' }">
                	<a style="color:#074695" onclick="showWindow('任课说明书详情', '<%=request.getContextPath()%>/evaluation/class_checkClassInstructionsDetailList2.html?id=${info.id }', '700','550');" href="#">查看</a>
                </c:if>
                </td>
                <td>
                	<c:if test="${info.zt eq '2' }">待审核</c:if>
                	<c:if test="${info.zt eq '3' }"><font color="green">审核通过</font></c:if>
                	<c:if test="${info.zt eq '4' }"><font color="red">审核不通过</font></c:if>
                </td>
                <td><ct:PersonParse code="${info.rkjs }"/></td>
                <td>${info.kcmc }</td>
                <td>${info.xn }</td>
                <td>第${info.xq }期</td>
                <td><ct:codeParse code="${info.yx }" catalog="<%=ICodeConstants.DM_DEF_ORG %>" /></td>
                <td>${info.zy }</td>
                <td>${info.nj }</td>
                <td>${info.bj }</td>
                <td>${info.jkzc }</td>
                <td>第${info.ksz }周</td>
                <td>第${info.jsz }周</td>
                <td>${info.zxs }</td>
                <td>${info.jkxs }</td>
                <td>${info.xtxs }</td>
                <td>${info.kttlxs }</td>
                <td>${info.aljxxs }</td>
                <td>${info.shyj }</td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        <ct:page pageList="${pageList }" query="${query }" queryName="query"/>
      </div>
    </form>
  </body>
</html>