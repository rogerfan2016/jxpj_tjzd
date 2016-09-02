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
    <table border="0" width="100%">
        <tr>
            <td width="50%" class="kcbweek">第${week}教学周</td>
            <td>
                <div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'yyyy-mm-dd', viewMode: 'days'}">
                    <input type="text" class="am-form-field" placeholder="" readonly value="${nowDate}">
                    <span class="am-input-group-btn am-datepicker-add-on">
                        <button id="chooseDate" class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
                    </span>
                </div>
            </td>
        </tr>
    </table>
    <table border="1" width="100%" class="kcbtb">
        <thead>
        <tr align="center">
            <td width="20px">
                ${nowMonth}月
            </td>
            <c:if test="${nowdayofweek eq 0 and now eq sun }">
            <td class="active">
            </c:if>
            <c:if test="${nowdayofweek != 0 or now != sun }">
            <td>
            </c:if>
                ${sun}</br>周日
            </td>
            <c:if test="${nowdayofweek eq 1 and now eq mon }">
            <td class="active">
            </c:if>
            <c:if test="${nowdayofweek != 1 or now != mon }">
            <td>
            </c:if>
                ${mon}</br>周一
            </td>
            <c:if test="${nowdayofweek eq 2 and now eq tues }">
            <td class="active">
            </c:if>
            <c:if test="${nowdayofweek != 2 or now != tues }">
            <td>
            </c:if>
                ${tues}</br>周二
            </td>
            <c:if test="${nowdayofweek eq 3 and now eq wed }">
            <td class="active">
            </c:if>
            <c:if test="${nowdayofweek != 3 or now != wed }">
            <td>
            </c:if>
                ${wed}</br>周三
            </td>
            <c:if test="${nowdayofweek eq 4 and now eq thurs }">
            <td class="active">
            </c:if>
            <c:if test="${nowdayofweek != 4 or now != thurs }">
            <td>
            </c:if>
                ${thurs}</br>周四
            </td>
            <c:if test="${nowdayofweek eq 5 and now eq fri }">
            <td class="active">
            </c:if>
            <c:if test="${nowdayofweek != 5 or now != fri }">
            <td>
            </c:if>
                ${fri}</br>周五
            </td>
            <c:if test="${nowdayofweek eq 6 and now eq sat }">
            <td class="active">
            </c:if>
            <c:if test="${nowdayofweek != 6 or now != sat }">
            <td>
            </c:if>
                ${sat}</br>周六
            </td>
        </tr>
        </thead>
        <tbody>
<c:set value="0" var="kccnt" />
<c:forEach items="${curriculumSchedule}" var="cs" varStatus="st">
        <tr align="center">
            <td class="ktb">
                ${st.index + 1}
            </td>
            <c:forEach begin="0" end="6" varStatus="tdSt">
                <c:set value="${curriculumSchedule[st.index][tdSt.index]}" var="model" />
                <c:if test="${model == null}">
                    <td></td>
                </c:if>
                <c:if test="${model != null and model.globalid != 'GCDT'}">
                    <td rowspan="${model.sc}">
                        <div style="background-color:${colors[kccnt % 7] };height:${80 * model.sc - 2}px;">
                            <table border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td height="${80 * model.sc - 2}" align="center" valign="center">
                                        <input type="hidden" name="kcsj" value="${model.kcsj}"/>
                                        <input type="hidden" name="spancnt" value="${model.sc}"/>
                                        <input type="hidden" name="kcjc" value="${model.kcjc}"/>
                                        <input type="hidden" name="kcid" value="${model.kcid}"/>
                                        <a name="gotoKcxq">${model.kcmc }@${model.skdd }</a>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </td>
                    <c:set value="${kccnt + 1 }" var="kccnt" />
                </c:if>
            </c:forEach>
        </tr>
</c:forEach>
        </tbody>
    </table>
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
    
    data.header.content.title = "我的课表";
    data.header.content.left = leftc;

    var html = template(data);
    $tpl.before(html);
    
    $(function () {
        $("a[name='gotoKcxq']").click(function() {
            var param = "?kcid=" + $(this).closest("td").find("[name='kcid']").val()
                        + "&skc=" + $(this).closest("td").find("[name='kcjc']").val()
                        + "&kcs=" + $(this).closest("td").find("[name='spancnt']").val()
                        + "&middleDay=" + $(this).closest("td").find("[name='kcsj']").val();
            
            location.href = "<%=request.getContextPath()%>/evaluation/mobile_curriculumDetail.html" + param;
        });
        
        // 改变时间
        $('#chooseDate').datepicker().on('changeDate.datepicker.amui', function(event) {
            $(this).datepicker('close');
            location.href = "<%=request.getContextPath()%>/evaluation/mobile_curriculum.html?ncrdt=" + event.date.valueOf();
        });
    });
</script>
<%@ include file="/WEB-INF/pages/mobile/navbar.jsp" %>
</body>
</html>