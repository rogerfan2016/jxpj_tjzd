<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <%@ include file="/WEB-INF/pages/wjdc/mobile/mobilePage.ini"%>
    <%@ include file="/WEB-INF/pages/mobile/meta.jsp" %>
    <link rel="stylesheet" href="<%=systemPath %>/css/wjdc/mobile/jqmobo.css">
    <link rel="stylesheet" href="<%=systemPath %>/css/wjdc/mobile/mobile.css"/>
    <link rel="stylesheet" href="<%=systemPath %>/css/wjdc/mobile/list.css"/>
    <script src="<%=systemPath %>/js/wjdc/mobile/wjdc_mobile.js" type="text/javascript"></script>
  </head>
<body>
<form action="wjdc/stgl_saveEditStxx.html" method="post">
<input type="hidden" id="wjid" name="wjModel.wjid" value="${wjModel.wjid}"/>
<input type="hidden" id="wjlx" value="${wjModel.wjlx}"/>
<input type="hidden" id="wjzf" value=""/>
<input type="hidden" id="djrid" value="${wjModel.djrid}"/>
<input type="hidden" id="wjzt" value="${wjModel.wjzt}"/>
<input type="hidden" id="djzt" value="${wjModel.djzt}"/>
<input type="hidden" id="pjid" name="wjModel.pjid" value="${wjModel.pjid}"/>
<input type="hidden" id="result" value="${result}"/>
<input type="hidden" id="hidden_autoaddstbh" value="${wjModel.autoaddstbh}"/>
<input type="hidden" name="inspectionTaskResult.id" value="${inspectionTaskResult.id}"/>
  <!--    <div class="time"><span>状态：${wjModel.wjzt}</span><span>时间：2012-08-08</span></div>   
      <div id="toptitle">
        <h1 class="htitle">
            ${wjModel.wjmc}</h1>
            <div class="header_right">
                    <a href="../inspection_mobile/result_list.html?type=${type }" >
                        <div class="miniButton green rm10 div_add_but">返回列表</div>
                    </a>
            </div>
      </div>
 -->     
 	<script type="text/x-handlebars-template" id="amz-tpl">
    {{>header header}}
	</script>
      <div id="divContent">
        <div style="margin: 10px; background: none repeat scroll 0% 0% rgb(239, 247, 255); padding: 10px; text-align: left; font-size: 13px; visibility: visible;">
         ${wjModel.jsy}
        </div>
        
      
      
<!--      <div style="padding: 10px; margin: 5px;" class="ui-tabs ui-widget ui-widget-content ui-corner-all">-->
<!--                <div>-->
<!--                </div>-->
<!--                <textarea style="width: 100%;"  rows="10" id="textInput"></textarea>-->
<!--            </div>-->
      
      <div id="divQuestion">
      <form action="wjnr">
          <fieldset id="wjview" class="fieldset">
            <legend>问卷内容</legend>
          </fieldset>
      </form>
      </div>
      <div style="margin: 10px; background: none repeat scroll 0% 0% rgb(239, 247, 255); padding: 10px; text-align: left; font-size: 13px; visibility: visible;">
         ${wjModel.jwy}
        </div>
        
        <div class="footer">
            <div class="ValError">
            </div>
            <div id="divSubmit" style="padding: 10px;">
                <div id="tdCode" style="display: none;">
                </div>
                <a id="but_tj" href="javascript:;" class="button blue" onclick="wjSubmitCheckMe()">提交</a>
            </div>
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
        if(validate()){
        	var url="inspection_mobile/result_saveWjDa.html?type=${type}";
            document.forms[0].action=url;
            document.forms[0].submit();
        }else{
        }
    }
    
    if(jQuery("#result").val()!=""){
    	 location.href= _path+"/inspection_mobile/stgl_success.html?type=${type}&wjModel.wjid=${wjModel.wjid}";
    }
</script>
 <%@ include file="/WEB-INF/pages/mobile/navbar.jsp" %>
<script type="text/javascript"> 

    var $tpl = $('#amz-tpl');
    var source = $tpl.text();
    var template = Handlebars.compile(source);
    var leftc = [];
    var obj = {};
    obj.link = "javascript:history.go(-1);";
    obj.icon = "chevron-left";
    leftc.push(obj);
    data.header.content.title = "${wjModel.wjmc}";
    data.header.content.left = leftc;

    var html = template(data);
    $tpl.before(html);
</script>