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
        
        // 导出
        $("#btn_dc").click(function() {
          window.open("<%=request.getContextPath()%>/evaluation/teacher_export.html?" + $("#search").serialize());
        });
        
        initSelect("tolQuery.shzt", "${tolQuery.shzt}");
        fillRows("20", "", "", false);//填充空行
      });
      
      // 提交审核
      function doAudit(globalid, status) {
        $.post('<%=request.getContextPath() %>/evaluation/teacher_changeStatus.html', 'globalid=' + globalid + '&status=' + status, function(data){
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
		
	  // 删除预约记录
      function deleteObject(globalid) {
      	if(confirm("确定要删除该记录吗？")){
        $.post('<%=request.getContextPath() %>/evaluation/teacher_delete.html', 'globalid=' + globalid, function(data){
          var callback = function() {
            $("#search").submit();
          };
          if (data.success) {
            processDataCall(data, callback);
          } else {
            showWarning(data.text);
          }
        }, "json");
		}
        return false;
      }
    </script>
  </head>
  <body>
  	<div class="toolbox">
      <div class="buttonbox">
        <ul>
          <li>
            <a id="btn_dc" class="btn_dc">导出</a>
          </li>
        </ul>
      </div>
    </div>
    <form action="<%=request.getContextPath()%>/evaluation/teacher_auditOpenLesson.html" name="search" id="search" method="post">
      <input name="phase" type="hidden" value="${phase }"/>
      <div class="searchtab">
        <table width="100%" border="0">
          <tbody>
            <tr>
              <th>听课老师工号</th>
              <td>
                <input type="text" class="text_nor" name="tolQuery.tkjsid" id="tkjsid" value="${tolQuery.tkjsid}" style="width: 90px;" />
              </td>
              <th>听课老师姓名</th>
              <td>
                <input type="text" class="text_nor" name="tolQuery.tkjsxm" id="tkjsxm" value="${tolQuery.tkjsxm}" style="width: 90px;" />
              </td>
              <th>专业</th>
              <td>
                <input type="text" class="text_nor" name="tolQuery.zy" id="zy" value="${tolQuery.zy}" style="width: 90px;" />
              </td>
              <th>课程名称</th>
              <td>
                <input type="text" class="text_nor" name="tolQuery.kcmc" id="kcmc" value="${tolQuery.kcmc}" style="width: 90px;" />
              </td>
            </tr>
            <tr>
              <th>听课地点</th>
              <td>
                <input type="text" class="text_nor" name="tolQuery.tkdd" id="tkdd" value="${tolQuery.tkdd}" style="width: 90px;" />
              </td>
              <th>听课时间(From)</th>
              <td>
                <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" name="tolQuery.kcsjFrom" id="kcsjFrom" value="${tolQuery.kcsjFrom}" style="width: 90px;" />
              </td>
              <th>听课时间(To)</th>
              <td>
                <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" name="tolQuery.kcsjTo" id="kcsjTo" value="${tolQuery.kcsjTo}" style="width: 90px;" />
              </td>
              <th>审核状态</th>
              <td>
                <select name="tolQuery.shzt" style="width:90px;">
                  <option value="">全部</option>
                  <option value="1">待审核</option>
                  <option value="2">审核通过</option>
                  <option value="3">审核不通过</option>
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
          <span>听课审核列表</span>
        </h3>
        <!--标题end-->
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
                <td>序号</td>
                <td>操作</td>
                <td>状态</td>
                <td>听课老师工号</td>
                <td>听课老师</td>
                <td>上课时间</td>
                <td>上课地点</td>
                <td>星期</td>
                <td>专业</td>
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
                  <c:if test="${info.shzt eq '1' or info.shzt eq '2'}">
                    <a style="color:#074695" href="#" onclick="doAudit('${info.globalid}', '3');">通过</a>
                    <a style="color:#074695" href="#" onclick="doAudit('${info.globalid}', '4');">不通过</a>
                  </c:if>
                  <c:if test="${info.shzt != '2' && info.shzt != '1' }"><a style="color:#074695" href="#" onclick="deleteObject('${info.globalid}');">删除</a></c:if>
                </td>
                <td>
                  <c:if test="${info.shzt eq '0' }">未提交</c:if>
                  <c:if test="${info.shzt eq '1' }">待审核</c:if>
                  <c:if test="${info.shzt eq '2' }">待审核</c:if>
                  <c:if test="${info.shzt eq '3' }">审核通过</c:if>
                  <c:if test="${info.shzt eq '4' }">审核不通过</c:if>
                </td>
                <td>${info.tkjsid }</td>
                <td>${info.tkjsxm }</td>
                <td>${info.curriculum.kcsj }</td>
                <td>${info.curriculum.skdd }</td>
                <td>${info.dayofweek }</td>
                <td>${info.curriculum.sszy }</td>
                <td>${info.curriculum.kcmc }</td>
                <td>${info.curriculum.rkls }</td>
                <td>${info.curriculum.kcjc }</td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        <ct:page pageList="${lessonList }"  query="${tolQuery }" queryName="tolQuery"/>
      </div>
    </form>
  </body>
</html>