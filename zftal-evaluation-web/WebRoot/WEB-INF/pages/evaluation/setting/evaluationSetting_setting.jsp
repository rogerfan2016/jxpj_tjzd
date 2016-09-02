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
        
        // 新增
        $("#btn_zj").click(function() {
          showWindow("增加问卷", "<%=request.getContextPath()%>/evaluation/setting_addView.html", "480","230");
        });
        
        initSelect("query.syzt", "${query.syzt}");
        initSelect("query.djrylx", "${query.djrylx}");
        initSelect("query.pjlx", "${query.pjlx}");
        
        fillRows("20", "", "", false);//填充空行
      });
      
      // 改变使用状态
      function doUpdate(globalid, status) {
        $.post('<%=request.getContextPath() %>/evaluation/setting_modify.html', 'model.globalid=' + globalid + '&model.syzt=' + status, function(data){
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
      
      // 改变使用状态
      function toEdit(globalid) {
        showWindow("修改问卷", "<%=request.getContextPath()%>/evaluation/setting_addView.html?model.globalid=" + globalid, "480","230");
      }

    </script>
  </head>
  <body>
    <div class="toolbox">
      <div class="buttonbox">
        <ul>
          <li>
            <a id="btn_zj" class="btn_zj">增加问卷</a>
          </li>
        </ul>
      </div>
    </div>
    <form action="<%=request.getContextPath()%>/evaluation/setting_setting.html" name="search" id="search" method="post">
      <div class="searchtab">
        <table width="100%" border="0">
          <tbody>
            <tr>
              <th width="50">问卷名称</th>
              <td width="150">
                <input type="text" class="text_nor" name="query.wjmc" id="wjmc" value="${query.wjmc}" style="width: 120px;" />
              </td>
              <th>答卷人员类型</th>
              <td>
                <select name="query.djrylx" style="width:100px;">
                  <option value="">全部</option>
                  <option value="0">学生</option>
                  <option value="1">教师</option>
                </select>
              </td>
              <th>使用状态</th>
              <td>
                <select name="query.syzt" style="width:100px;">
                  <option value="">全部</option>
                  <option value="0">启用</option>
                  <option value="1">停用</option>
                </select>
              </td>
              <th>评教类型</th>
              <td>
                <select name="query.pjlx" style="width:120px">
                  <option value="">全部</option>
                  <c:forEach items="${pjs}" var="pj">
                  <option value="${pj }">${pj.text }</option>
                  </c:forEach>
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
          <span>问卷列表</span>
        </h3>
        <!--标题end-->
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
                <td>序号</td>
                <td>问卷名称</td>
                <td>答卷人员类型</td>
                <td>评教类型</td>
                <td>使用状态</td>
                <td>创建时间</td>
                <td>创建者</td>
                <td>修改时间</td>
                <td>修改者</td>
                <td>操作</td>
              </tr>
            </thead>
            <tbody id="list_body">
              <c:forEach items="${pageList}" var="info" varStatus="st">
              <tr name="tr">
                <td>${st.index+ 1 }</td>
                <td><a style="color:#074695" href="#" onclick="toEdit('${info.globalid}');">${info.wjmc }</a></td>
                <td>
                  <c:if test="${info.djrylx eq '0' }">学生</c:if>
                  <c:if test="${info.djrylx eq '1' }">教师</c:if>
                </td>
                <td>${info.pjlx.text }</td>
                <td>
                  <c:if test="${info.syzt eq '0' }">启用</c:if>
                  <c:if test="${info.syzt eq '1' }">停用</c:if>
                </td>
                <td>${info.zcsj }</td>
                <td>${info.zcryxm }</td>
                <td>${info.xgsj }</td>
                <td>${info.xgryxm }</td>
                <td>
                  <c:if test="${info.syzt eq '0' }"><a style="color:#074695" href="#" onclick="doUpdate('${info.globalid}', '1');">停用</a></c:if>
                  <c:if test="${info.syzt eq '1' }"><a style="color:#074695" href="#" onclick="doUpdate('${info.globalid}', '0');">启用</a></c:if>
                </td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        <ct:page pageList="${pageList }"  query="${query }" queryName="query"/>
      </div>
    </form>
  </body>
</html>