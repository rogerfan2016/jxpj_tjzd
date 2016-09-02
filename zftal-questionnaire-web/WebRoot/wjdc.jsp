<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <%@ include file="/WEB-INF/pages/globalweb/head/pagehead_v4.ini"%>  
    <title>问卷调查</title>
  </head>
   
<body>
	<div class="mainbody type_mainbody">
  		<div class="topframe_help">
    	<div class="head_help">
        <h2 class="floatleft"><a href="wjdc.jsp"><img src="img/logo_school.png"></a></h2>
        <p class="phone">咨询电话：0571-88888888</p>
    	</div>
  	</div>
  <div class="type_mainframe">      	
    <div class="typeleft floatleft">
      <div class="textlink" id="">
        <h3 onclick="showhidediv(this);" class="close"><span>问卷调查 </span></h3>
        <ul style="display:block;" class="hierarchy_03">
		  <li><a href="<%=path%>/wjdc/wjgl_cxWjxx.html" class="none_03" target="framecon"><span>问卷维护</span></a></li>
		  <li><a href="<%=path%>/wjdc/wjpz_wjpz.html" class="none_03" target="framecon"><span>问卷配置</span></a></li>
          <li><a href="<%=path%>/wjdc/wjffgl_cxWjffxx.html" class="none_03" target="framecon"><span>问卷分发</span></a></li>
        </ul>
      </div>
    </div>
    <div class="btn_hide_on"><button></button></div>
    <div class="btn_hide_off" style="display:none;"><button></button></div>
    <div class="typeright floatright">
      <!--选项卡-->
      <!---->
      <!--内容区主体-->
    <div class="typecon">
    <div id="haha">
          <iframe name="framecon" id="frame_content" allowtransparency="true" src="wjdc/wjgl_cxWjxx.html" width="100%" frameborder="0" marginwidth="0" marginheight="0" onload="this.height=100" scrolling="no" height="842"></iframe>
      <script type="text/javascript">
      
      function getNext(o)
      {
      	while(o)
      	{
      		if(o.nextSibling.nodeType==1)
      		{return o.nextSibling;}
      		o=o.nextSibling;
      	}
      	return o;
      }
      
      function showhidediv(o,id)
      {
          var sbtitle = getNext(o);
      	if(sbtitle)
      	{
         		if(sbtitle.style.display=='block')
      		{
            	 	sbtitle.style.display = 'none';
             		o.className = "open";
         		}
      		else
      		{
         			sbtitle.style.display = 'block';
        			o.className = "close";
         		}
      	
      		if(sbtitle.style.display=='none')
      		{
            	 	sbtitle.style.display = 'none';
             		o.className = "open";
         		}
      		else
      		{
         			sbtitle.style.display = 'block';
        			o.className = "close";
         		}
      		
      	}
      }
      
	  function reinitIframe(){
		var iframe = document.getElementById("frame_content");
		 	try{
				var bHeight = iframe.contentWindow.document.body.scrollHeight;
				var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
				var height = Math.max(bHeight, dHeight);
				iframe.height =  height;
			}catch (ex){}
	  }
window.setInterval("reinitIframe()", 200);
		</script>
    </div>
<div id="gotopbtn" style="width:22px; height:71px;position:fixed;_position:absolute; background:red;bottom:100px;right:10px;display:none;cursor:pointer;"><img src="../images/blue/ico_uptop.gif"></div>
<script type="text/javascript">
backTop=function (btnId){
	var btn=document.getElementById(btnId);
	var d=document.documentElement;
	window.onscroll=set;
	btn.onclick=function (){
		btn.style.display="none";
		window.onscroll=null;
		this.timer=setInterval(function(){
			d.scrollTop-=Math.ceil(d.scrollTop*0.1);
			if(d.scrollTop==0) clearInterval(btn.timer,window.onscroll=set);
		},10);
	};
	function set(){btn.style.display=d.scrollTop?'block':"none"}
};
backTop('gotopbtn');
	</script>
  	</div>
  	</div>
  </div>
    <!-- 版权信息 -->
	<%@include file="/WEB-INF/pages/globalweb/bottom.jsp" %>
</div>


</body>
</html>
