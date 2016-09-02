<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <%@ include file="/WEB-INF/pages/globalweb/head/pagehead_v4.ini"%>
<!--    <link rel="stylesheet" href="http://10.71.19.19:82/zfstyle_v4/css/public.css" type="text/css" media="all" />-->
<!--    <link rel="stylesheet" href="http://10.71.19.19:82/zfstyle_v4/css/website.css" type="text/css" media="all" />-->
<!--    <link rel="stylesheet" href="http://10.71.19.19:82/zfstyle_v4/css/global.css" type="text/css" media="all" />-->
<!--[if IE 6]> 
<script src="http://10.71.19.19:82/zfstyle_v4/js/ie6comm.js"></script> 
<script> 
DD_belatedPNG.fix('img,.mainbody,.topframe,.mainframe,.botframe,.menu,.type_mainframe'); 
</script> 
<![endif]-->

<script type="text/javascript" src="<%=systemPath %>/js/wjdc/wj_model.js"></script>
<script type="text/javascript" src="<%=systemPath %>/js/wjdc/wj_jieXi.js"></script>
<script type="text/javascript" src="<%=systemPath %>/js/wjdc/wj_cs.js"></script>
<script type="text/javascript" src="<%=systemPath %>/js/wjdc/wj_oneSt.js"></script>
<script type="text/javascript" src="<%=systemPath %>/js/wjdc/wj_main.js"></script>


</head>
<body class="body_dcd">
<form action="wjdc/stgl_saveEditStxx.html" method="post">
<input type="hidden" id="wjid" name="wjModel.wjid" value="${wjModel.wjid}"/>
<input type="hidden" id="wjlx" value="${wjModel.wjlx}"/>
<input type="hidden" id="wjzf" value=""/>
<input type="hidden" id="djrid" value="${wjModel.djrid}"/>
<input type="hidden" id="wjzt" value="${wjModel.wjzt}"/>
<input type="hidden" id="djzt" value="${wjModel.djzt}"/>
<input type="hidden" id="result" value="${result}"/>
<input type="hidden" id="hidden_autoaddstbh" value="${wjModel.autoaddstbh}"/>
<input type="hidden" name="inspectionTaskResult.id" value="${inspectionTaskResult.id}"/>
<div class="main_dcd">
  <div class="single_wjdc">
  <!--    <div class="time"><span>状态：${wjModel.wjzt}</span><span>时间：2012-08-08</span></div>   -->
      <h3>${wjModel.wjmc}</h3>
      <div class="text">
          ${wjModel.jsy}
      </div>
      
      
      
<!--      <div style="padding: 10px; margin: 5px;" class="ui-tabs ui-widget ui-widget-content ui-corner-all">-->
<!--                <div>-->
<!--                </div>-->
<!--                <textarea style="width: 100%;"  rows="10" id="textInput"></textarea>-->
<!--            </div>-->
      
      
      <fieldset id="wjview" class="question_con">
        <legend>问卷内容</legend>
      </fieldset>
      <div class="text">
          ${wjModel.jwy}
      </div>
      <div style="width: 100%" align="center">
          <input id="but_tj" type="button" value="提交" onclick="wjSubmitCheckMe()"/>  
        <!--  <input type="button" value="提交" onclick="wjSubmitCheck()"/>   -->
          <input type="button" value="关闭" onclick="window.close()"/>
      </div>
      
  </div>
</div>


</form>
</body>
</html>
<script type="text/javascript">
    if("${inspectionTaskResult.rwzt != '1'}"=="true"){
        jQuery("#but_tj").remove();
    }
    /**问卷提交验证*/
    function wjSubmitCheckMe(){
        var res=static_wjModel.getWjSubmitCheckZt(true);
        if(!res){
            return false;
        }
        var url="inspection/result_saveWjDa.html?type=${type}";
        document.forms[0].action=url;
        document.forms[0].submit();
        
    }
    if(jQuery("#result").val()!=""){
        window.onunload=function(){
            if(window.opener&&window.opener.document.getElementById("search_go")){
                window.opener.document.getElementById("search_go").click();
            }
        }
        alert('${result}','',{"clkFun":function(){window.close()}});
    }
</script>