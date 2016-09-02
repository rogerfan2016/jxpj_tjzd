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
<div data-am-widget="list_news" class="am-list-news am-list-news-default" >
  <div class="am-list-news-bd">
  <ul class="am-list">
	<c:forEach items="${list}" var="info" varStatus="st">
      <li class="am-g am-list-item-dated">
          <a href="mobile_pendingAffairByType.html" class="am-list-item-hd ">${info.affairName }(${info.sumNumber })条消息</a>
      </li>
	</c:forEach>
  </ul>
	<c:if test="${empty list}">
	<span>暂无记录</span>
	</c:if>
  </div>
</script>

<script type="text/javascript"> 

    var $tpl = $('#amz-tpl');
    var source = $tpl.text();
    var template = Handlebars.compile(source);
    var leftc = [];
    var obj = {};
    obj.link = "../wjdc_mobile/index_initMenu.html";
    obj.icon = "chevron-left";
    leftc.push(obj);
    
    data.header.content.title = "待办事宜";
    data.header.content.left = leftc;
    var html = template(data);
    $tpl.before(html);
</script>
 <%@ include file="/WEB-INF/pages/mobile/navbar.jsp" %>
</body>
</html>