<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <%@ include file="/commons/hrm/head.ini" %>
    <script type="text/javascript">
      $(function(){
        // 保存
        $("#save_btn").click(function() {
          $.post("<%=request.getContextPath()%>/evaluation/student_saveTeachingLog.html",
            $("#editform").serialize(), function(data) {
              if(data.success){
                window.close();
              }else{
                showWarning(data.text);
              }
            },"json");
          return false;
        });
        
        // 录入
        $("#checkin_btn").click(function() {
          $.post("<%=request.getContextPath()%>/evaluation/student_saveCheckIn.html",
            $("#editform").serialize(), function(data) {
              if(data.success){
                $("#mes").html("已有考勤记录，可以发起评教");
              }else{
                showWarning(data.text);
              }
            },"json");
          return false;
        });
        
        // 评教
        $("#send_btn").click(function() {
          $.post("<%=request.getContextPath()%>/evaluation/student_sendEvaluation.html",
            $("#editform").serialize(), function(data) {
              if(data.success){
                window.close();
              }else{
                showWarning(data.text);
              }
            },"json");
          return false;
        });
        
      });
    </script>
  </head>
  <body>
    <form id="editform">
      <input type="hidden" id="globalid" name="teachingEntity.globalid" value="${teachingEntity.globalid }"/>
      <input type="hidden" id="rklsgh" name="teachingEntity.rklsgh" value="${teachingEntity.rklsgh }"/>
      <input type="hidden" id="kcid" name="teachingEntity.kcid" value="${teachingEntity.kcid }"/>
      <input type="hidden" id="kcsj" name="teachingEntity.kcsj" value="${teachingEntity.kcsj }"/>
      <c:if test="${type eq '1' }">
      <div id="div_logview" class="content" style="overflow-y:auto;overflow-x:hidden;" >
        <table width="100%"  border="0" class="formlist" cellpadding="0" cellspacing="0">
          <thead>
            <tr>
              <th colspan="4">
                <span><font color="#0f5dc2" style="font-weight:normal;">教学日志</font></span>
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th>课程名称</th>
              <td>
                ${teachingEntity.kcmc }
              </td>
            </tr>
            <c:if test="${teacher != 'teacher' }">
            <tr>
              <th>任课老师</th>
              <td>
                <input type="hidden" name="teachingEntity.rklsgh" value="${teachingEntity.rklsgh }"/>
                ${teachingEntity.rkls }
              </td>
            </tr>
            </c:if>
            <tr>
              <th>教学内容</th>
              <td>
                <textarea name="teachingEntity.jxnr" rows="5" cols="40" <c:if test="${lx != 'teacher' }">readonly="readonly"</c:if>>${teachingEntity.jxnr }</textarea>
              </td>
            </tr>
            <tr>
              <th>课外作业</th>
              <td>
                <textarea name="teachingEntity.kwzy" rows="5" cols="40" <c:if test="${lx != 'teacher' }">readonly="readonly"</c:if>>${teachingEntity.kwzy }</textarea>
              </td>
            </tr>
            <tr>
              <th>备注说明</th>
              <td>
                <textarea name="teachingEntity.bzsm" rows="5" cols="40" <c:if test="${lx != 'teacher' }">readonly="readonly"</c:if>>${teachingEntity.bzsm }</textarea>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      </c:if>
      <c:if test="${type eq '2' }">
      <c:if test="${lx eq 'teacher' }">
      <div id="div_checkin" class="content" style="overflow-y:auto;overflow-x:hidden;">
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap" id="tiptab">
            <thead id="list_head">
              <tr>
                <td>学生</td>
                <td>考勤状态</td>
                <td width="125">备注</td>
              </tr>
            </thead>
            <tbody id="list_body" >
              <c:forEach items="${teachingEntity.xss}" var="model" varStatus="st">
                <tr>
                  <td>
                    <input type="hidden" name="teachingEntity.xss[${st.index }].xsid" value="${model.xsid }"/>
                    ${model.xsxm }
                  </td>
                  <td>
                    <input type="radio" name="teachingEntity.xss[${st.index }].kqqk" value="0" <c:if test="${model.kqqk eq 0 or model.kqqk eq null or model.kqqk eq ''}">checked="checked"</c:if>/>出勤
                    <input type="radio" name="teachingEntity.xss[${st.index }].kqqk" value="1" <c:if test="${model.kqqk eq 1 }">checked="checked"</c:if>/>旷课
                    <input type="radio" name="teachingEntity.xss[${st.index }].kqqk" value="2" <c:if test="${model.kqqk eq 2 }">checked="checked"</c:if>/>事假
                    <input type="radio" name="teachingEntity.xss[${st.index }].kqqk" value="3" <c:if test="${model.kqqk eq 3 }">checked="checked"</c:if>/>病假
                    <input type="radio" name="teachingEntity.xss[${st.index }].kqqk" value="4" <c:if test="${model.kqqk eq 4 }">checked="checked"</c:if>/>迟到
                  </td>
                  <td><input type="text" name="teachingEntity.xss[${st.index }].bzsm" value="${model.bzsm }" class="text_nor"/></td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
      </c:if>
      </c:if>
    </form>
    <table width="100%"  border="0" class="formlist" cellpadding="0" cellspacing="0">
      <tfoot>
        <tr>
          <td colspan="2">
            <c:if test="${type eq '2' }">
            <div id="mes" class="bz">${mes }</div>
            </c:if>
            <div class="btn">
              <c:if test="${lx eq 'teacher' }">
              <c:if test="${type eq '1' }">
              <button type="button" id="save_btn">保存</button>
              </c:if>
              <c:if test="${type eq '2' }">
              <button type="button" id="checkin_btn">保存考勤</button>
              <button type="button" id="send_btn">发起评教</button>
              </c:if>
              </c:if>
              <button type="button" id="cancel" onclick="window.close();">取消</button>
            </div>
          </td>
        </tr>
      </tfoot>
    </table>
  </body>
</html>