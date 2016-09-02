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
<c:forEach items="${teachingEntities}" var="model" varStatus="st">
                <li class="am-g am-list-item-dated">
                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                        <tr>
                            <td valign="center" width="70%">
                                <input type="hidden" name="globalid" value="${model.globalid }"/>
           						<b>课程名称：</b>${model.kcmc }<br>                   
								<b>任课老师：</b>${model.rkls }<br>
                                <b>上课地点：</b>${model.skdd }<br>
                                <b>上课时间：</b>${model.kcsj }<br>
								<b>上课节次：</b>${model.kcjc }节<br>
<!--                               
教学内容：${model.jxnr }<br>
课外作业：${model.kwzy }<br>
 备注说明：${model.bzsm }<br>
-->
							</td>
					<c:if test="${lx eq 'teacher' }">
						<c:if test="${isCheckIn eq '0' }">
							<c:if test="${model.zt eq '0' }">
                            	<td align="center" valign="center"><button type="button" name="gotoKp" style="border-radius:10px;" class="am-btn am-btn-success">录入考勤</button></td>
							</c:if>
							<c:if test="${model.zt eq '1' }">
                            	<td align="center" valign="center">
									<button type="button" name="gotoKp" style="border-radius:10px;" class="am-btn am-btn-primary">编辑考勤</button></br>
									<button type="button" name="gotoKp" style="border-radius:10px;" class="am-btn am-btn-warning">发起评教</button>
								</td>
							</c:if>
							<c:if test="${model.zt eq '2' }">
                            	<td align="center" valign="center"><button type="button" name="gotoKp" style="border-radius:10px;" class="am-btn am-btn-primary">查看考勤</button></td>
							</c:if>
						</c:if>
						<c:if test="${isCheckIn eq '1' }">
							<c:if test="${model.zt eq '2' }">
                            	<td align="center" valign="center"><button type="button" name="gotoKp" style="border-radius:10px;" class="am-btn am-btn-primary">查看考勤</button></td>
							</c:if>
							<c:if test="${model.zt != '2' }">
                            	<td align="center" valign="center"><button type="button" name="wks" style="border-radius:10px;" class="am-btn am-btn-default">未开始</button></td>
							</c:if>
						</c:if>
						<c:if test="${isCheckIn eq '-1' }">
							<c:if test="${model.zt eq '2' }">
                            	<td align="center" valign="center"><button type="button" name="gotoKp" style="border-radius:10px;" class="am-btn am-btn-primary">查看考勤</button></td>
							</c:if>
							<c:if test="${model.zt != '2' }">
                            	<td align="center" valign="center"><button type="button" name="ygq" style="border-radius:10px;" class="am-btn am-btn-default">已过期</button></td>
							</c:if>
						</c:if>
					</c:if>
                        </tr>
                    </table>
                </li>
				<li class="am-g am-list-item-dated">
					<font color="red" size="3px"><b>提醒说明：</b></br>1、考勤和评教只能当天提交和发起！</br>2、发起了评教就不允许再编辑考勤！</font>
				</li>
</c:forEach>
            </ul>
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
    
    data.header.content.title = "课程表详情";
    data.header.content.left = leftc;

    var html = template(data);
    $tpl.before(html);
    
    $(function () {
        // 评教
        $("[name='gotoKp']").click(function() {
          location.href = "<%=request.getContextPath()%>/evaluation/mobile_checkin.html?globalid=" + $(this).closest("tr").find("[name='globalid']").val();
          return false;
        });
    });
</script>
<%@ include file="/WEB-INF/pages/mobile/navbar.jsp" %>
</body>
</html>