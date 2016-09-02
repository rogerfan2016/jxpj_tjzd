<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8"> 
<title>提交成功</title>
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1">
    <%@ include file="/WEB-INF/pages/wjdc/mobile/mobilePage.ini"%>
    <link rel="stylesheet" href="<%=systemPath %>/css/wjdc/mobile/jqmobo.css">
</head>
<body>
    <div id="toptitle">
        <h1 class="htitle">
            ${wjModel.wjmc }</h1>
    </div>
    
    <div style="text-align:center; margin:20px 0 10px;">
        <div class="formfield" style="padding-bottom:30px;">
            <div class="description">您的答卷已经提交，感谢您的参与！<font color="red"><span  id="time">3</span></font>秒后系统将自动返回到首页</div>
            <a onclick="backList()" style="cursor: pointer;text-decoration:underline;" target="_top" class="bold underline">如果您的浏览器没有自动跳转,请点击这里</a>
        </div>
    </div>
    </div>
       <script type="text/javascript">
    
     function backList(){
        var url = _path+"/wjdc_mobile/index_initMenu.html";
        document.location.href = url;
    }
    var time=document.getElementById("time").innerHTML;
    var timeNum= parseInt(time);
    minusTime(timeNum);
    function minusTime( time){
        
        var interval = setInterval(function(){
                document.getElementById("time").innerHTML=time+"";
                if(time == 0){
                    clearInterval(interval);
                    backList();
                }
                time--;
                
            },1000);
        return ;
    }
  </script>
</body>
</html>