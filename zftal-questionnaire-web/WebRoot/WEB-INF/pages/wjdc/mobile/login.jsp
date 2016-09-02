<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="com.zfsoft.util.date.TimeUtil"%>
<%@ page import="com.zfsoft.zfca.tp.cas.client.ZfssoConfig" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/WEB-INF/pages/mobile/meta.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0039)http://www.sojump.com/mobile/login.aspx -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/WEB-INF/pages/wjdc/mobile/mobilePage.ini"%>
        <script type="text/javascript"
            src="<%=systemPath %>/js/globalweb/comm/validate.js"></script>
            
        <% String usezfca = ZfssoConfig.usezfca;
            String goUrl = request.getContextPath() + "/wjdc_mobile/yhdjgl_cxYhdjxx.html";
        %>  
        <% if(null !=usezfca && "1".equals(usezfca)){ %>
        <script language="javascript">
            window.location.href = "<%=ZfssoConfig.casurl%>/login?service=<%=java.net.URLEncoder.encode("http://"+ZfssoConfig.ywxtservername+goUrl, "utf-8")%>";
        </script>
    <%}%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=systemPath %>/css/wjdc/mobile/mobile.css"/>
<style>
#getYzm, .getYzm
{
    background:none;
    padding:0;
}
</style>
<script type="text/javascript">
var theForm = document.forms['form1'];
if (!theForm) {
    theForm = document.form1;
}
function __doPostBack(eventTarget, eventArgument) {
    if (!theForm.onsubmit || (theForm.onsubmit() != false)) {
        theForm.__EVENTTARGET.value = eventTarget;
        theForm.__EVENTARGUMENT.value = eventArgument;
        theForm.submit();
    }
}
function isEmpty(dataid){
    if($('#' + dataid).val() != null && $('#' + dataid).val() != ''){
        return false;
    }else{
        return true;
    }
}
function showErrMsg(message){
	$(".errors").html("<span style='color:red'>"+message+"</span>");
}
//登录
function dl() {
    if(isEmpty('yhm')){
    	showErrMsg('用户名不能为空！');
        return false;
    }
    if(isEmpty('mm')){
    	showErrMsg('密码不能为空！');
        return false;
    }
    
    if(isEmpty('yzm') && $("#count").val()>=3){
        alert('验证码不能为空！');
        return false;
    }
                
    $("#btn_dl").attr("disabled","disabled");
    $(".errors").html("<span style='color:blue'>正在登录中，请稍候......</span>");
    $.post('<%=request.getContextPath() %>/wjdc_mobile/login_login.html', $('#form :input').serialize(), function(data){
        $("#btn_dl").removeAttr("disabled");
        if(data.success==false){
            showErrMsg(data.message);
            $("#count").val(data.logincount);
             if(data.logincount>=3){
                 $("#yzmdiv").css("display","block");
                 $("#mm").val("");
             }else{
                 $("#yzmdiv").css("display","none");
                 $("#mm").val("");
             }
        }else{
            $(".errors").html("");
            location.href="<%=request.getContextPath() %>/wjdc_mobile/index_initMenu.html";
        }
    });
    //document.forms[0].submit();
}
</script>

</head>
<body>
<!--
	<div class="content" style="margin-top: 15px;">
		<div class="am-slider am-slider-default" data-am-flexslider id="demo-slider-0">
		  <ul class="am-slides">
			<li><img src="../img/wjdc/mobile/1.jpg" /></li>
			<li><img src="../img/wjdc/mobile/2.jpg" /></li>
			<li><img src="../img/wjdc/mobile/3.jpg" /></li>
			<li><img src="../img/wjdc/mobile/4.jpg" /></li>
		  </ul>
		</div>
	</div>
-->
	<div class="content" style="margin-top: 40px;">
			<img src="<%=stylePath%>logo/logo_school.png" />
	</div>
    <div class="content" style="margin-top: 40px;">
        <form name="form" method="post" id="form" data-ajax="false">
			<div>
			    <input type="hidden" name="__VIEWSTATEGENERATOR" id="__VIEWSTATEGENERATOR" value="956819A2">
			    <input type="hidden" name="__EVENTVALIDATION" id="__EVENTVALIDATION" value="/wEWBAK+yKMNAqXVsrMJArWptJELAsKL2t4DcJPJ4YG8nlCWFWu0IIhp/bJrAtU=">
			</div>
	        <ul class="unstyled matrix_ul">
	            <li>
	                <div class="topic_input">
	                    <div class="topic_bel">
	                        <i class="ico_phone"></i>
	                    </div>
	                    <div class="topic_ipt">
	                        <input name="yhm" type="text" id="yhm" placeholder="职工号/学号" class="btx">
	                    </div>
	                </div>
	            </li>
	            <li>
	                <div class="topic_input">
	                    <div class="topic_bel">
	                        <i class="ico_p"></i>
	                    </div>
	                    <div class="topic_ipt">
	                        <input name="mm" type="password" id="mm" placeholder="密码" class="btx">
	                    </div>
	                </div>
	            </li>
	              
	            
	        </ul>
	        <div style="margin-top: 40px;">
	            <a id="btn_dl" class="button orange" onclick="dl()">登 录</a>
	        </div>
	        <!--
	        <p style="margin-top:15px;">
	            <a style="float: left;" href="#">注册账号</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">忘记密码？</a>
	        </p>
	         -->
	        
            <div class="errors" ></div>
        </form>
    </div>

	<footer data-am-widget="footer" class="am-footer am-footer-default" data-am-footer="{  }">
    	<div class="am-footer-miscs ">
        	<p>天津职业大学版权所有</p>
        	<p>Copyright©1978-2015</p>
        	<p>津ICP备08001121号 津教备0305号</p>
        	<p>技术支持：天津中大新程科技有限公司；电话：022-26352180</p>
    	</div>
  	</footer>

</body></html>