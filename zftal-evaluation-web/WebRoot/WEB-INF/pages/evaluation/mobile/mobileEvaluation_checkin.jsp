<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta chaset="UTF-8">
<%@ include file="/WEB-INF/pages/mobile/meta.jsp" %>
<title></title>
</head>
<body>
<script type="text/x-handlebars-template" id="amz-tpl">
    {{>header header}}
<div data-am-sticky="{animation: 'slide-bottom'}">
<c:choose>
	<c:when test="${mesf=='1'}">
		<button class="am-btn am-btn-failed am-btn-block">${mes}</button>
	</c:when>
	<c:when test="${mesf=='2'}">
		<button class="am-btn am-btn-success am-btn-block">${mes}</button>
	</c:when>
	<c:otherwise>
		<button class="am-btn am-btn-success am-btn-block">${mes}</button>
	</c:otherwise>
</c:choose>
</div>
<form id="editform">
    <input type="hidden" id="globalid" name="teachingEntity.globalid" value="${teachingEntity.globalid }"/>
	<input type="hidden" id="rklsgh" name="teachingEntity.rklsgh" value="${teachingEntity.rklsgh }"/>
    <input type="hidden" id="kcid" name="teachingEntity.kcid" value="${teachingEntity.kcid }"/>
    <input type="hidden" id="kcsj" name="teachingEntity.kcsj" value="${teachingEntity.kcsj }"/>
    <div data-am-widget="list_news" class="am-list-news am-list-news-default" >
        <div class="am-list-news-bd">
            <ul class="am-list">
<c:forEach items="${teachingEntity.xss}" var="model" varStatus="st">
                <li class="am-g am-list-item-dated">
                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                        <tr>
                            <td height="60px">
                                <input type="hidden" name="teachingEntity.xss[${st.index }].xsid" value="${model.xsid }"/>
                                ${model.xsxm }(${model.xsid })
                            </br>
                                <input type="radio" name="teachingEntity.xss[${st.index }].kqqk" <c:if test="${mesf == '1'}">disabled="disabled"</c:if> value="0" <c:if test="${model.kqqk eq 0 or model.kqqk eq null or model.kqqk eq ''}">checked="checked"</c:if>/>出勤
                                <input type="radio" name="teachingEntity.xss[${st.index }].kqqk" <c:if test="${mesf == '1'}">disabled="disabled"</c:if> value="1" <c:if test="${model.kqqk eq 1 }">checked="checked"</c:if>/> 旷课
                                <input type="radio" name="teachingEntity.xss[${st.index }].kqqk" <c:if test="${mesf == '1'}">disabled="disabled"</c:if> value="2" <c:if test="${model.kqqk eq 2 }">checked="checked"</c:if>/> 事假
                                <input type="radio" name="teachingEntity.xss[${st.index }].kqqk" <c:if test="${mesf == '1'}">disabled="disabled"</c:if> value="3" <c:if test="${model.kqqk eq 3 }">checked="checked"</c:if>/> 病假
								<input type="radio" name="teachingEntity.xss[${st.index }].kqqk" <c:if test="${mesf == '1'}">disabled="disabled"</c:if> value="4" <c:if test="${model.kqqk eq 4 }">checked="checked"</c:if>/> 迟到
                            </td>
                        </tr>
                    </table>
                </li>
</c:forEach>
            </ul>
        </div>
    </div>
</form>
<div data-am-sticky="{animation: 'slide-bottom'}">
	<c:if test="${mesf=='0'}">
		<button id="save_btn" class="am-btn am-btn-primary am-btn-block" data-am-modal="{target: '#my-modal-loading'}">提交保存</button>
	</c:if>
	<c:if test="${mesf=='2'}">
		<button id="save_btn" class="am-btn am-btn-primary am-btn-block" data-am-modal="{target: '#my-modal-loading'}">编辑考勤</button>
		<button id="send_btn" class="am-btn am-btn-primary am-btn-block" data-am-modal="{target: '#my-modal-loading'}">发起评教</button>
	</c:if>
</div>
<!-- 模态窗口 -->
<div class="am-modal am-modal-loading am-modal-no-btn" tabindex="-1" id="my-modal-loading">
  <div class="am-modal-dialog">
    <div class="am-modal-hd">正在载入...</div>
    <div class="am-modal-bd">
      <span class="am-icon-spinner am-icon-spin"></span>
    </div>
  </div>
</div>
</script>

<script type="text/javascript"> 

    var $tpl = $('#amz-tpl');
    var source = $tpl.text();
    var template = Handlebars.compile(source);
    var leftc = [];
    var obj = {};
    obj.link = "javascript:history.go(-1);";
    obj.icon = "chevron-left";
    leftc.push(obj);
    
    data.header.content.title = "考勤与评价";
    data.header.content.left = leftc;
    var html = template(data);
    $tpl.before(html);
    
    $(function () {
    	// 录入考勤
        $("#save_btn").click(function() {
        
          $.post("<%=request.getContextPath()%>/evaluation/mobile_saveCheckIn.html",
            $("#editform").serialize(), function(data) {
              if(data.success){
                location.href = "<%=request.getContextPath()%>/evaluation/mobile_myEvaluation.html";
              }else{
                showWarning(data.text);
              }
            },"json");
          return false;
        });
        // 发起评教
        $("#send_btn").click(function() {
        
          $.post("<%=request.getContextPath()%>/evaluation/mobile_evaluation.html",
            $("#editform").serialize(), function(data) {
              if(data.success){
                location.href = "<%=request.getContextPath()%>/evaluation/mobile_myEvaluation.html";
              }else{
                showWarning(data.text);
              }
            },"json");
          return false;
        });
    });
</script>
 <%@ include file="/WEB-INF/pages/mobile/navbar.jsp" %>
</body>
</html>