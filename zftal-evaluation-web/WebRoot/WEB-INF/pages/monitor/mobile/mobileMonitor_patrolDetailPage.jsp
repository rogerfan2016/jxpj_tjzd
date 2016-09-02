<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.zfsoft.hrm.config.ICodeConstants"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ct" uri="/custom-code"%>
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
<form id="editform">
	<input type="hidden" name="model.globalid" value="${model.globalid }"/>
    <div data-am-widget="list_news" class="am-list-news am-list-news-default" >
        <div class="am-list-news-bd">
            <ul class="am-list">
<c:forEach items="${model.patrolDetailEntityList}" var="obj" varStatus="st">
                <li class="am-g am-list-item-dated">
                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                        <tr>
                            <td valign="left" width="50%" height="40px">
                                <input type="hidden" name="model.patrolDetailEntityList[${st.index }].id" value="${obj.id }"/>
                                	<b>开课学院：</b><ct:codeParse catalog="<%=ICodeConstants.DM_DEF_ORG %>" code="${obj.kkxy }" /></br>
									<b>巡视教室：</b>${obj.xsdd }</br>
									<b>课程名称：</b>${obj.kcmc }</br>
									<b>教师姓名：</b>${obj.jsxm }</br>
                                    <b>上课学生数：</b>${obj.skxss }人</br>
									<b>上课班级：</b>${obj.skbj }
                            </td>
                            <td align="left" valign="left">
								<label class="am-checkbox am-warning">
                                	<input type="checkbox" data-am-ucheck name="model.patrolDetailEntityList[${st.index }].czwt" value="教学秩序正常" <c:if test="${fn:contains(obj.czwt,'教学秩序正常') or obj.czwt eq null or obj.czwt eq ''}">checked="checked"</c:if>/>教学秩序正常
                                </label>
								<label class="am-checkbox am-warning">
									<input type="checkbox" data-am-ucheck name="model.patrolDetailEntityList[${st.index }].czwt" value="学生出勤率低" <c:if test="${fn:contains(obj.czwt,'学生出勤率低') }">checked="checked"</c:if>/>学生出勤率低
                                </label>
								<label class="am-checkbox am-warning">
									<input type="checkbox" data-am-ucheck name="model.patrolDetailEntityList[${st.index }].czwt" value="教师迟到" <c:if test="${fn:contains(obj.czwt,'教师迟到') }">checked="checked"</c:if>/>教师迟到
                                </label>
								<label class="am-checkbox am-warning">
									<input type="checkbox" data-am-ucheck name="model.patrolDetailEntityList[${st.index }].czwt" value="教具未准备" <c:if test="${fn:contains(obj.czwt,'教具未准备') }">checked="checked"</c:if>/>教具未准备
								</label>
								<label class="am-checkbox am-warning">
									<input type="checkbox" data-am-ucheck name="model.patrolDetailEntityList[${st.index }].czwt" value="其他" <c:if test="${fn:contains(obj.czwt,'其他') }">checked="checked"</c:if>/>其他
								</label>
								<label class="am-checkbox am-warning">
									补充说明：<input type="text" name="model.patrolDetailEntityList[${st.index }].bz" value="${obj.bz }"/>
								</label>
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
	<button id="send_btn" class="am-btn am-btn-primary am-btn-block" data-am-modal="{target: '#my-modal-loading'}">提交巡视结果</button>
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
    
    data.header.content.title = "巡视记录";
    data.header.content.left = leftc;
    var html = template(data);
    $tpl.before(html);
    
    $(function () {
        // 评教
        $("#send_btn").click(function() {
        
          $.post("<%=request.getContextPath()%>/monitor/mobile_savePatrolDetail.html",
            $("#editform").serialize(), function(data) {
              if(data.success){
                location.href = "<%=request.getContextPath()%>/monitor/mobile_patrol.html?xslx=${model.xslx }";
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