<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%><!DOCTYPE html><html><head><meta chaset="UTF-8"><%@ include file="/WEB-INF/pages/mobile/meta.jsp" %></head><body>    <script type="text/x-handlebars-template" id="amz-tpl">    {{>header header}}	<div class="am-slider am-slider-default" data-am-flexslider id="demo-slider-0">		  <ul class="am-slides">			<li><img src="http://www.tjtc.edu.cn/huandeng/huandeng31.jpg" /></li>			<li><img src="http://www.tjtc.edu.cn/huandeng/huandeng8.jpg" /></li>			<li><img src="http://www.tjtc.edu.cn/huandeng/huandeng37.jpg" /></li>			<li><img src="http://www.tjtc.edu.cn/huandeng/huandeng33.jpg" /></li>			<li><img src="http://www.tjtc.edu.cn/huandeng/huandeng32.jpg" /></li>			<li><img src="http://www.tjtc.edu.cn/huandeng/huandeng35.jpg" /></li>		  </ul>	</div>	<div data-am-widget="tabs" class="am-tabs am-tabs-default">		<ul class="am-tabs-nav am-cf">   		<c:choose>			<c:when test="${yhlx eq 'teacher' }">			<li class="am-active"><a onclick="showPanel('panel0');">教学监控</a></li>            <li class=""><a onclick="showPanel('panel1');">教学评价</a></li>			</c:when>			<c:when test="${yhlx eq 'student' }">			<li class="">我的应用</a></li>			</c:when>        </c:choose>            </ul>        <div id="panel0">            {{>gallery gallery0}}        </div>        <div id="panel1">            {{>gallery gallery1}}        </div>    </div></script><script type="text/javascript">     var $tpl = $('#amz-tpl');    var source = $tpl.text();    var template = Handlebars.compile(source);    data.header.content.title = "应用中心";    data.header.content.left = {};    var panel0 = [];    var panel1 = [];    var gop = {};    gop.cols = 2;    gop.gallery = false;    data.gallery0.options = gop;    data.gallery0.theme = "bordered";        if(${yhlx eq 'teacher'}){    cobj = {};    cobj.img = "../assets/i/app/xxfj.png";    cobj.link = "../evaluation/mobile_pendingAffairList.html";    cobj.title = "待办事宜 ${paNum }";    panel0.push(cobj);    cobj = {};    cobj.img = "../assets/i/app/xntz.png";    cobj.link = "../message/message_m_page.html";    cobj.title = "我的消息 ${msgNum }";    panel0.push(cobj);    var cobj = {};    cobj.img = "../assets/i/app/msjs.png";    cobj.link = "../evaluation/mobile_curriculum.html";    cobj.title = "我的课表";    panel0.push(cobj);    cobj = {};    cobj.img = "../assets/i/app/xxls.png";    cobj.link = "";    cobj.title = "任课说明书";    panel0.push(cobj);    cobj = {};    cobj.img = "../assets/i/app/xxdt.png";    //cobj.link = "../evaluation/mobile_curriculumDetail.html";    cobj.link = "../evaluation/mobile_curriculum.html";    cobj.title = "考勤管理";    panel0.push(cobj);    cobj = {};    cobj.img = "../assets/i/app/xxfj.png";    cobj.link = "../monitor/mobile_patrol.html";    cobj.title = "教学巡视 ${xsNum }";    panel0.push(cobj);    cobj = {};    cobj.img = "../assets/i/app/msjs.png";    cobj.link = "../evaluation/mobileBusiness_center.html?btype=adjust_lesson_bid";    cobj.title = "调停课审核(>4学时)";    panel0.push(cobj);    cobj = {};    cobj.img = "../assets/i/app/msjs.png";    cobj.link = "../evaluation/mobileBusiness_center.html?btype=adjust_lesson_bid2";    cobj.title = "调停课审核(<=4学时)";    panel0.push(cobj);    cobj = {};    cobj.img = "../assets/i/app/ttxw.png";    cobj.link = "../evaluation/mobileBusiness_center.html?btype=school_change_bid";    cobj.title = "学籍异动审核";    panel0.push(cobj);    data.gallery0.content = panel0;           data.gallery1.options = gop;    data.gallery1.theme = "bordered";    cobj = {};    cobj.img = "../assets/i/app/ttxw.png";    cobj.link = "../evaluation/mobile_myEvaluation.html";    cobj.title = "评教结果";    panel1.push(cobj);    cobj = {};    cobj.img = "../assets/i/app/xxdt.png";    cobj.link = "../evaluation/mobile_response2.html";    cobj.title = "教师评价 ${pjNum }";    panel1.push(cobj);    cobj = {};    cobj.img = "../assets/i/app/xntz.png";    cobj.link = "../inspection_mobile/result_list.html";    cobj.title = "督导评教";    panel1.push(cobj);       data.gallery1.content = panel1;    }        if(${yhlx eq 'student'}){    cobj = {};    cobj.img = "../assets/i/app/xntz.png";    cobj.link = "../message/message_m_page.html";    cobj.title = "我的消息 ${msgNum }";    panel0.push(cobj);    var cobj = {};    cobj.img = "../assets/i/app/msjs.png";    cobj.link = "../evaluation/mobile_curriculum.html";    cobj.title = "我的课表";    panel0.push(cobj);    cobj = {};    cobj.img = "../assets/i/app/xxls.png";    cobj.link = "";    cobj.title = "任务说明书";    panel0.push(cobj);    cobj = {};    cobj.img = "../assets/i/app/xxdt.png";    cobj.link = "../evaluation/mobile_response.html";    cobj.title = "学生评教 ${pjNum }";    panel0.push(cobj);    data.gallery0.content = panel0;	}	    var html = template(data);    $tpl.before(html);        function showPanel(id) {        $("#panel0").hide();        $("#panel1").hide();        $("#" + id).show();    };        $(function () {    	if(${yhlx eq 'teacher'}){    		$("#panel0").show();        	$("#panel1").hide();    	}else if(${yhlx eq 'student'}){    		$("#panel0").show();    	}else if(${yhlx }==2){    		$("#panel0").show();    	}else if(${yhlx }==3){    		$("#panel0").show();    	}       });</script><%@ include file="/WEB-INF/pages/mobile/navbar.jsp" %></body></html>