<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
</script>
<form action="<%=request.getContextPath()%>/evaluation/mobile_myEvaluation.html" name="wjListForm" id="wjListForm" method="post">
<c:if test="${fn:length(questionnaires) == 0}">
      <div style="padding-top:30px;text-align:center;height:80px;">
        <span style="color:green;font-size:14px;">暂无记录</span>
      </div>
</c:if>
<c:if test="${fn:length(questionnaires) > 0}">
    <div data-am-widget="list_news" class="am-list-news am-list-news-default" >
        <div class="am-list-news-bd">
            <ul class="am-list">
<c:forEach items="${questionnaires}" var="info" varStatus="st">
<c:set value="${info.pjcnt['ANSWER_NUM'] }/${info.pjcnt['ALL_NUM'] }" var="vbl" />
                <li class="am-g am-list-item-dated" data-am-popover="{theme: 'warning', content: '已评数/总数：${vbl}', trigger: 'hover focus'}">
                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                        <tr>
                            <td valign="center" width="70%">
                                <b>问卷名称：</b>${info.wjmc } [<span style="color: red;"><b>${vbl}</b></span>]<br>
                                <b>课程名称：</b>${info.curriculum.kcmc}<br>
                                <b>上课地点：</b>${info.curriculum.skdd}<br>
                                <b>上课时间：</b>${info.curriculum.kcsj} 第${info.curriculum.kcjc}节<br></td>
                            <td align="center" valign="center" width="20%">
                                <input type="hidden" name="xwjid" value="${info.xwjid}"/>
                                <button type="button" name="gotoKcxq" style="border-radius:10px;" class="am-btn am-btn-success">查看</button>
                            </td>
                        </tr>
                    </table>
                </li>
</c:forEach>
            </ul>
        </div>
    </div>
    <%@ include file="/WEB-INF/pages/mobile/bottomTag.jsp"%>
</c:if>
</form>

<script type="text/javascript"> 

    var $tpl = $('#amz-tpl');
    var source = $tpl.text();
    var template = Handlebars.compile(source);
    var leftc = [];
    var obj = {};
    obj.link = "javascript:history.go(-1);";
    obj.icon = "chevron-left";
    leftc.push(obj);
    
    data.header.content.title = "评教结果";
    data.header.content.left = leftc;

    var html = template(data);
    $tpl.before(html);
    
    $(function () {
        showButton();
        $("button[name='gotoKcxq']").click(function() {
            location.href = "<%=request.getContextPath()%>/wjdc_mobile/wjtj_sttj.html?model.wjid=" + $(this).closest("td").find("[name='xwjid']").val();
        });

    });
</script>
<%@ include file="/WEB-INF/pages/mobile/navbar.jsp" %>
</body>
</html>