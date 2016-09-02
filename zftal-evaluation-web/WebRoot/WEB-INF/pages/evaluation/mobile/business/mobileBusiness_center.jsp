<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
		<c:choose>
			<c:when test="${status == 'WAIT_AUDITING' }">
			<li class="am-active"><a onclick="showPanel('panel0','WAIT_AUDITING','${btype}');">待审核</a></li>
            <li class=""><a onclick="showPanel('panel1','HAS_AUDITED','${btype}');">已审核</a></li>
			</c:when>
			 <c:otherwise>
			<li class=""><a onclick="showPanel('panel0','WAIT_AUDITING','${btype}');">待审核</a></li>
            <li class="am-active"><a onclick="showPanel('panel1','HAS_AUDITED','${btype}');">已审核</a></li>
			</c:otherwise>

        </c:choose>    
        </ul>

        <div id="panel0">
            {{>gallery gallery0}}
        </div>

        <div id="panel1">
            {{>gallery gallery1}}
        </div>
    </div>

<div data-am-widget="list_news" class="am-list-news am-list-news-default" >
  <div class="am-list-news-bd">
  <ul class="am-list">
	<c:forEach items="${pageList}" var="page" varStatus="st">
     
	<li class="am-g am-list-item-dated">	
		<c:choose>
		<c:when test="${status == 'WAIT_AUDITING' }">
          <a href="mobileBusiness_detail.html?classId=${page.classId}&workId=${page.id}&btype=${btype}" class="am-list-item-hd ">
			${page.className}[${page.userName}]
		</a>
		<span class="am-list-date"><fmt:formatDate value="${page.commitDate}" pattern="yyyy-MM-dd"/></span>
		</c:when>
		<c:otherwise>
 		<a href="mobileBusiness_detail.html?classId=${page.classId}&workId=${page.id}&btype=${btype}" class="am-list-item-hd ">
			${page.className}[${page.userName}]  &nbsp;&nbsp;&nbsp;&nbsp; ${page.status.text}
		</a>
		<span class="am-list-date"><fmt:formatDate value="${page.auditDate}" pattern="yyyy-MM-dd"/></span>
		</c:otherwise>
 		</c:choose>
      </li>
	</c:forEach>
  </ul>
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
    if("${btype}"=="adjust_lesson_bid")
    {
    	data.header.content.title = "调停课";
    }
    else if("${btype}"=="school_change_bid")
    {
    	data.header.content.title = "学籍异动";
    }
    data.header.content.left = leftc;
    var html = template(data);
    $tpl.before(html);
    
    function showPanel(id,staus,btype) {
        $("#panel0").hide();
        $("#panel1").hide();
        //$("#panel2").hide();
        $("#" + id).show();
        location.href = "../evaluation/mobileBusiness_center.html?btype="+btype+"&status="+staus;
    };

    $(function () {
    	if(${status}=='WAIT_AUDITING'){
    		$("#panel0").show();
        	$("#panel1").hide();
        	//$("#panel2").hide();
    	}else {
    		$("#panel0").hide();
        	$("#panel1").show();
        	//$("#panel2").hide();
    	}
    });
</script>
 <%@ include file="/WEB-INF/pages/mobile/navbar.jsp" %>
</body>
</html>