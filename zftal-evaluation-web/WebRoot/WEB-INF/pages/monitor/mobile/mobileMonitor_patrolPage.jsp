<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.zfsoft.hrm.config.ICodeConstants"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ct" uri="/custom-code"%>
<%@taglib prefix="code" uri="/WEB-INF/code.tld" %>
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
    <div data-am-widget="tabs" class="am-tabs am-tabs-default">
        <ul class="am-tabs-nav am-cf">
<c:forEach items="${patrols}" var="p">
            <li class="<c:if test='${xslx eq p.keyStr}'>am-active</c:if>"><a onclick="changeTabs('${p.keyStr}')">${p.text}</a></li>
</c:forEach>
        </ul>

        <div id="panel0">
            <div data-am-widget="list_news" class="am-list-news am-list-news-default" >
                <div class="am-list-news-bd">
                    <ul class="am-list">
<c:forEach items="${list}" var="entity">
                        <li class="am-g am-list-item-desced">
                            <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                <tr>
                                    <td valign="center" width="90%">
                                        <div><b>开课学院：</b><c:if test="${entity.xsdw == null}">所有学院</c:if><ct:codeParse catalog="<%=ICodeConstants.DM_DEF_ORG %>" code="${entity.xsdw }" /></div>
                                        <div><b>巡视地点：</b><c:if test="${entity.xscdmc == null}">所有教学楼</c:if>${entity.xscdmc}</div>
                                        <div class="am-list-item-text">第${entity.jxz}周 ${entity.xsrq} 第${entity.kcjc}节 ${entity.weekOfDay}</div>
                                    </td>
                                    <td align="center" valign="center">
                                        <input type="hidden" name="globalid" value="${entity.globalid}" />
									<c:if test="${entity.sfkxs eq '1'}">	
                                        <c:if test="${entity.zt eq '1'}">
                                        <button type="button" style="border-radius:10px;" class="am-btn am-btn-success">录入巡视结果</button>
                                        </c:if>
                                        <c:if test="${entity.zt eq '2'}">
                                        <button type="button" style="border-radius:10px;" class="am-btn am-btn-success">修改巡视结果</button>
                                        </c:if>
									</c:if>
									<c:if test="${entity.sfkxs eq '0'}">
										<button type="button" style="border-radius:10px;" class="am-btn am-btn-default">巡视未开始</button>
									</c:if>
									<c:if test="${entity.sfkxs eq '2'}">
										<button type="button" style="border-radius:10px;" class="am-btn am-btn-danger">巡视已过期</button>
									</c:if>
                                    </td>
                                </tr>
                            </table>
                        </li>
</c:forEach>
                    </ul>
                </div>
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
    obj.link = "../wjdc_mobile/index_initMenu.html?type=1";
    obj.icon = "chevron-left";
    leftc.push(obj);
    
    data.header.content.title = "教学巡视";
    data.header.content.left = leftc;

    var html = template(data);
    $tpl.before(html);
    
    $(function () {
        $(".am-btn-success").click(function() {
            var param = "?model.xslx=${xslx}&model.globalid=" + $(this).closest("td").find("[name='globalid']").val();
            
            location.href = "<%=request.getContextPath()%>/monitor/mobile_patrolDetail.html" + param;
        });
        
    });
    
    function changeTabs(val) {
        location.href = "<%=request.getContextPath()%>/monitor/mobile_patrol.html?xslx=" + val;
    }
</script>
<%@ include file="/WEB-INF/pages/mobile/navbar.jsp" %>
</body>
</html>