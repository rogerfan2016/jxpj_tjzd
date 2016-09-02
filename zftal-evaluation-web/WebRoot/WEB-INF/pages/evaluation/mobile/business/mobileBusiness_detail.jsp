<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="ct" uri="/custom-code"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<meta chaset="UTF-8">
<%@ include file="/WEB-INF/pages/mobile/meta.jsp" %>
<title></title>
</head>
 <script type="text/javascript" src="<%=request.getContextPath() %>/js/mobile/billaudit.js"></script>
    <script type="text/javascript">
    $(function(){
			var params={idInput:"${infoChange.id}",
						container1:$("#content1"),
						container2:$("#content2"),
						billConfigId:'${spBusiness.billId }',
						billInstanceId:'${infoChange.billInstanceId }',
						approveBillClassesPrivilege:'${privilegeExpression}',
						passAuditUrl:"/infochange/audit_pass.html?query.classId=${classId}",
						rejectAuditUrl:"/infochange/audit_reject.html?query.classId=${classId}",
						saveAuditUrl:"/infochange/audit_save.html?query.classId=${classId}",
						backAuditUrl:"/infochange/audit_back.html?query.classId=${classId}",
						returnUrl:"/evaluation/mobileBusiness_center.html"};
			$("#back").click(function(){//功能条增加按钮
                location.href = _path+"/evaluation/mobileBusiness_center.html";
            });
           
			load(params);
		});
    
    </script>
<body>
<script type="text/x-handlebars-template" id="amz-tpl">
    {{>header header}}
<div id="content1">
	</div>
	<div id="content2" style="display:none;">
		<form id="form1">
		<c:forEach items="${excutedList}" var="node" varStatus="st">
		<div class="title-nav">
			<h3><span id="yuef">环节：${node.nodeName }</span></h3>
		</div>
		<div class="yuezj-list yuezj-list-cur" style="height:60px;font-size:12px;">
			<div style="float:left;width:45%;" class="yuezj-list-title">
				审核人：<ct:PersonParse code="${node.auditorId }"/>
			</div>
			<div style="float:left;width:55%" class="yuezj-list-title">
				审核时间：<fmt:formatDate value="${node.auditTime}" pattern="yyyy-MM-dd HH:mm"/>
			</div>						
			<div style="float:left;width:45%" class="yuezj-list-title">
				审核状态：${node.statusName }
			</div>
			<div style="float:left;width:55%" class="yuezj-list-title">
				审核意见：${node.suggestion }
			</div>
		</div>
		
		</c:forEach>
		<div class="por-rz">
			<div class="por-rz-tool"><a id="logButton" href="#">查看审核日志</a></div>
			<div id="logContent" style="display:none;" class="por-rz-con">
				<table class="por-rz-tab">
					<tbody>
				<s:if test="logList.size()>0">
				<s:iterator value="logList" var="log">
					<tr><th style="width: 120px;"><s:date name="logTime" format="yyyy-MM-dd HH:mm" /></th><td>${log.ocontent }</td></tr>
				</s:iterator>
				</s:if>
				<s:else>
					<tr><th>-</th><td>暂无</td></tr>
				</s:else>
					</tbody>
				</table>
			</div>
		</div>
		<br>
		<c:if test="${not empty currentNode}">
		<div class="formbox">
			<div class="con_overlfow">
			<table width="100%" id="tiptab" class="formlist">
			<thead id="list_head2">
				<tr>
					<th colspan="4">${currentNode.nodeName }</th>
				</tr>
			</thead>
			<tbody id="list_body2">
				<tr>
					<th><span class="red">*</span>审核意见
						<input type="hidden" value="${currentNode.nodeId }" name="node.nodeId">
					</th>
					<td colspan="3">
				<div style="text-align:center;">
      				<textarea class="" id="doc-ta-1" name="node.suggestion" rows="5">${currentNode.suggestion }</textarea>
   				 </div>

</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4">
					<div class="btn">
					<input type="hidden" name="infoChange.id" value="${infoChange.id }"/>
						<button type="button" name="pass" id="input">通过</button>
						<button type="button" name="reject" id="input">不通过</button>
					</div>
					</td>
				</tr>
			</tfoot>
			</table>
			</div>
		</div>
		<div style="position: absolute;display:none; z-index: 1; width: 110px;" class="btn" id="menu">
			<s:iterator value="excutedList" var="node">
			<button style="margin-top:4px;margin-right:3px" type="button" name="backButton" id="${node.nodeId }">${node.nodeName }</button>
			</s:iterator>
		</div>
		</c:if>
		</form>
	</div>

</script>

<script type="text/javascript"> 

    var $tpl = $('#amz-tpl');
    var source = $tpl.text();
    var template = Handlebars.compile(source);
    var leftc = [];
    var obj = {};
    obj.link = "../evaluation/mobileBusiness_center.html?btype=${btype}";
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
    
    
</script>
 <%@ include file="/WEB-INF/pages/mobile/navbar.jsp" %>
</body>
</html>