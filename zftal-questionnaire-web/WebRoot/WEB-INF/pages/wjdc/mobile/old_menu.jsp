<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/pages/wjdc/mobile/mobilePage.ini"%>
<title>问卷调查</title>
<base href=".">
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta charset="utf-8">
<link rel="stylesheet" href="<%=systemPath %>/css/wjdc/mobile/reset.css">
<link rel="stylesheet" href="<%=systemPath %>/css/wjdc/mobile/cate7_3.css">
<link rel="stylesheet" href="<%=systemPath %>/css/wjdc/mobile/font-awesome.css">
<link rel="stylesheet" href="<%=systemPath %>/css/wjdc/mobile/iscroll.css">
<script src="<%=systemPath %>/js/wjdc/mobile/iscroll.js" type="text/javascript"></script> 

<script type="text/javascript">
var myScroll;function loaded() {
myScroll = new iScroll('wrapper', {
snap: true,
momentum: false,
hScrollbar: false,
onScrollEnd: function () {
document.querySelector('#indicator > li.active').className = '';
document.querySelector('#indicator > li:nth-child(' + (this.currPageX+1) + ')').className = 'active';
}
 });
}document.addEventListener('DOMContentLoaded', loaded, false);
</script>
</head>
<body id="cate7" style="">
<div class="banner">
  <div id="wrapper" style="overflow: hidden;">
    <div id="scroller" style="width: 4067px; -webkit-transition: 0ms; transition: 0ms; -webkit-transform: translate3d(-1743px, 0px, 0px) scale(1);">
      <ul id="thelist">
        <li>
            <p></p>
            <img src="<%=systemPath %>/img/wjdc/mobile/1.jpg" style="width: 581px;"></li><li>
            <p></p>
            <img src="<%=systemPath %>/img/wjdc/mobile/2.jpg" style="width: 581px;"></li><li>
            <p></p>
            <img src="<%=systemPath %>/img/wjdc/mobile/3.jpg" style="width: 581px;"></li><li>
            <p></p>
            <img src="<%=systemPath %>/img/wjdc/mobile/4.jpg" style="width: 581px;"></li><li>
            <p></p>
            <img src="<%=systemPath %>/img/wjdc/mobile/5.jpg" style="width: 581px;"></li><li>
        </ul>
    </div>
  </div>
  <div id="nav">
    <ul id="indicator">
      <li class="active">1</li>
      <li class="">2</li>
      <li class="">3</li>
      <li class="">4</li>
      <li class="">5</li>
    </ul>
  </div>
</div>
<div>
<div>&nbsp;&nbsp;&nbsp;</div>
<div>&nbsp;&nbsp;&nbsp;</div>
<ul class="cateul">
    <li class="li1"> <a href="../evaluation/mobile_curriculum.html">
      <div class="menubtn">
        <div class="menuimg"><img src="<%=systemPath %>/img/wjdc/mobile/22.png"></div>
        <div class="menutitle" style="color:">我的课表</div>
      </div>
      </a> </li>
    <li class="li2"> <a href="../evaluation/mobile_response.html">
      <div class="menubtn">
        <div class="menuimg"><img src="<%=systemPath %>/img/wjdc/mobile/33.png"></div>
        <div class="menutitle" style="color:">参与的评价</div>
      </div>
      </a> </li>
    <li class="li0"> <a href="../evaluation/mobile_myEvaluation.html"> <%--<a href="./yhdjgl_cxYhdjxx.html">--%>
      <div class="menubtn">
        <div class="menuimg"><img src="<%=systemPath %>/img/wjdc/mobile/44.png"></div>
        <div class="menutitle" style="color:">我被评价</div>
      </div>
      </a> </li>
    <li class="li1"> <a href="#">
      <div class="menubtn">
        <div class="menuimg"><img src="<%=systemPath %>/img/wjdc/mobile/55.png"></div>
        <div class="menutitle" style="color:">任课说明书</div>
      </div>
      </a> </li>
    <li class="li2"> <a href="#">
      <div class="menubtn">
        <div class="menuimg"><img src="<%=systemPath %>/img/wjdc/mobile/66.png"></div>
        <div class="menutitle" style="color:">教学行政历</div>
      </div>
      </a></li>
    <li class="li0"> <a href="#">
      <div class="menubtn">
        <div class="menuimg"><img src="<%=systemPath %>/img/wjdc/mobile/11.png"></div>
        <div class="menutitle" style="color:">通知公告</div>
      </div>
      </a> </li>
  </ul>
</br>
</div>
    <div class="copyright">
© 2001-2015 XXXX版权所有</div>

<style type="text/css">
body { margin-bottom:60px !important; }
a, button, input { -webkit-tap-highlight-color:rgba(255, 0, 0, 0); }
ul, li { list-style:none; margin:0; padding:0 }
.top_bar { position: fixed; z-index: 900; bottom: 0; left: 0; right: 0; margin: auto; font-family: Helvetica, Tahoma, Arial, Microsoft YaHei, sans-serif; }
.top_menu { display:-webkit-box; border-top: 1px solid #3D3D46; display: block; width: 100%; background: rgba(255, 255, 255, 0.7); height: 48px;display: -webkit-box; display: box; margin:0; padding:0; -webkit-box-orient: horizontal; background: -webkit-gradient(linear, 0 0, 0 100%, from(#524945), to(#48403c), color-stop(60%, #524945)); box-shadow: 0 1px 0 0 rgba(255, 255, 255, 0.1) inset; }
.top_bar .top_menu>li { -webkit-box-flex:1; position:relative; text-align:center; }
.top_menu li:first-child { background:none; }
.top_bar .top_menu>li>a { height:48px; margin-right: 1px; display:block; text-align:center; color:#FFF; text-decoration:none; text-shadow: 0 1px rgba(0, 0, 0, 0.3); -webkit-box-flex:1; }
.top_bar .top_menu>li.home { max-width:70px }
.top_bar .top_menu>li.home a { height: 60px; width: 60px; margin: auto; border-radius: 60px; position: relative; top: -14px; background: url('tpl/Wap/default/common/images/home.png') no-repeat center center; background-size: 100% 100%; }
.top_bar .top_menu>li>a label { overflow:hidden; margin: 0 0 0 0; font-size: 12px; display: block !important; line-height: 18px; text-align: center; }
.top_bar .top_menu>li>a img { padding: 3px 0 0 0; height: 24px; width: 24px; color: #fff; line-height: 48px; vertical-align:middle; }
.top_bar li:first-child a { display: block; }
.menu_font { text-align:left; position:absolute; right:10px; z-index:500; background: -webkit-gradient(linear, 0 0, 0 100%, from(#524945), to(#48403c), color-stop(60%, #524945)); border-radius: 5px; width: 120px; margin-top: 10px; padding: 0; box-shadow: 0 1px 5px rgba(0, 0, 0, 0.3); }
.menu_font.hidden { display:none; }
.menu_font { top:inherit !important; bottom:60px; }
.menu_font li a { height:40px; margin-right: 1px; display:block; text-align:center; color:#FFF; text-decoration:none; text-shadow: 0 1px rgba(0, 0, 0, 0.3); -webkit-box-flex:1; }
.menu_font li a { text-align: left !important; }
.top_menu li:last-of-type a { background: none; }
.menu_font:after { top: inherit!important; bottom: -6px; border-color: #48403c rgba(0, 0, 0, 0) rgba(0, 0, 0, 0); border-width: 6px 6px 0; position: absolute; content: ""; display: inline-block; width: 0; height: 0; border-style: solid; left: 80%; }
.menu_font li { border-top: 1px solid rgba(255, 255, 255, 0.1); border-bottom: 1px solid rgba(0, 0, 0, 0.2); }
.menu_font li:first-of-type { border-top: 0; }
.menu_font li:last-of-type { border-bottom: 0; }
.menu_font li a { height: 40px; line-height: 40px !important; position: relative; color: #fff; display: block; width: 100%; text-indent: 10px; white-space: nowrap; text-overflow: ellipsis; overflow: hidden; }
.menu_font li a img { width: 20px; height:20px; display: inline-block; margin-top:-2px; color: #fff; line-height: 40px; vertical-align:middle; }
.menu_font>li>a label { padding:3px 0 0 3px; font-size:14px; overflow:hidden; margin: 0; }
#sharemcover { position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0, 0, 0, 0.7); display: none; z-index: 20000; }
#sharemcover img { position: fixed; right: 18px; top: 5px; width: 260px; height: 180px; z-index: 20001; border:0; }
.top_bar .top_menu>li>a:hover, .top_bar .top_menu>li>a:active { background-color:#333; }
.menu_font li a:hover, .menu_font li a:active{ background-color:#333; }
.menu_font li:first-of-type a { border-radius:5px 5px 0 0; }
.menu_font li:last-of-type a { border-radius:0 0 5px 5px; }
.litype{width:58px;}
#plug-wrap { position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0, 0, 0, 0); z-index:800; }
#cate18 .device {bottom: 49px;}
#cate18 #indicator {bottom: 240px;}
#cate19 .device {bottom: 49px;}
#cate19 #indicator {bottom: 330px;}
#cate19 .pagination {bottom: 60px;}
</style>

<script>
var count = document.getElementById("thelist").getElementsByTagName("img").length;  
for(i=0;i<count;i++){
 document.getElementById("thelist").getElementsByTagName("img").item(i).style.cssText = " width:"+document.body.clientWidth+"px";}document.getElementById("scroller").style.cssText = " width:"+document.body.clientWidth*count+"px";
 setInterval(function(){
myScroll.scrollToPage('next', 0,400,count);
},3500 );window.onresize = function(){ 
for(i=0;i<count;i++){
document.getElementById("thelist").getElementsByTagName("img").item(i).style.cssText = " width:"+document.body.clientWidth+"px";} document.getElementById("scroller").style.cssText = " width:"+document.body.clientWidth*count+"px";
} </script>

<script src="<%=systemPath %>/js/wjdc/mobile/zepto.min.js" type="text/javascript"></script> 
<script src="<%=systemPath %>/js/wjdc/mobile/plugmenu.js" type="text/javascript"></script> 
<style type="text/css">
body { margin-bottom:60px !important; }
a, button, input { -webkit-tap-highlight-color:rgba(255, 0, 0, 0); }
ul, li { list-style:none; margin:0; padding:0 }
.top_bar { position: fixed; z-index: 900; bottom: 0; left: 0; right: 0; margin: auto; font-family: Helvetica, Tahoma, Arial, Microsoft YaHei, sans-serif; }
.top_menu { display:-webkit-box; border-top: 1px solid #3D3D46; display: block; width: 100%; background: rgba(255, 255, 255, 0.7); height: 48px;display: -webkit-box; display: box; margin:0; padding:0; -webkit-box-orient: horizontal; background: -webkit-gradient(linear, 0 0, 0 100%, from(#524945), to(#48403c), color-stop(60%, #524945)); box-shadow: 0 1px 0 0 rgba(255, 255, 255, 0.1) inset; }
.top_bar .top_menu>li { -webkit-box-flex:1; position:relative; text-align:center; }
.top_menu li:first-child { background:none; }
.top_bar .top_menu>li>a { height:48px; margin-right: 1px; display:block; text-align:center; color:#FFF; text-decoration:none; text-shadow: 0 1px rgba(0, 0, 0, 0.3); -webkit-box-flex:1; }
.top_bar .top_menu>li.home { max-width:70px }
.top_bar .top_menu>li.home a { height: 60px; width: 60px; margin: auto; border-radius: 60px; position: relative; top: -14px; background: url('./湖州微生活_files/home.png') no-repeat center center; background-size: 100% 100%; }
.top_bar .top_menu>li>a label { overflow:hidden; margin: 0 0 0 0; font-size: 12px; display: block !important; line-height: 18px; text-align: center; }
.top_bar .top_menu>li>a img { padding: 3px 0 0 0; height: 24px; width: 24px; color: #fff; line-height: 48px; vertical-align:middle; }
.top_bar li:first-child a { display: block; }
.menu_font { text-align:left; position:absolute; right:10px; z-index:500; background: -webkit-gradient(linear, 0 0, 0 100%, from(#524945), to(#48403c), color-stop(60%, #524945)); border-radius: 5px; width: 120px; margin-top: 10px; padding: 0; box-shadow: 0 1px 5px rgba(0, 0, 0, 0.3); }
.menu_font.hidden { display:none; }
.menu_font { top:inherit !important; bottom:60px; }
.menu_font li a { height:40px; margin-right: 1px; display:block; text-align:center; color:#FFF; text-decoration:none; text-shadow: 0 1px rgba(0, 0, 0, 0.3); -webkit-box-flex:1; }
.menu_font li a { text-align: left !important; }
.top_menu li:last-of-type a { background: none; }
.menu_font:after { top: inherit!important; bottom: -6px; border-color: #48403c rgba(0, 0, 0, 0) rgba(0, 0, 0, 0); border-width: 6px 6px 0; position: absolute; content: ""; display: inline-block; width: 0; height: 0; border-style: solid; left: 80%; }
.menu_font li { border-top: 1px solid rgba(255, 255, 255, 0.1); border-bottom: 1px solid rgba(0, 0, 0, 0.2); }
.menu_font li:first-of-type { border-top: 0; }
.menu_font li:last-of-type { border-bottom: 0; }
.menu_font li a { height: 40px; line-height: 40px !important; position: relative; color: #fff; display: block; width: 100%; text-indent: 10px; white-space: nowrap; text-overflow: ellipsis; overflow: hidden; }
.menu_font li a img { width: 20px; height:20px; display: inline-block; margin-top:-2px; color: #fff; line-height: 40px; vertical-align:middle; }
.menu_font>li>a label { padding:3px 0 0 3px; font-size:14px; overflow:hidden; margin: 0; }
#sharemcover { position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0, 0, 0, 0.7); display: none; z-index: 20000; }
#sharemcover img { position: fixed; right: 18px; top: 5px; width: 260px; height: 180px; z-index: 20001; border:0; }
.top_bar .top_menu>li>a:hover, .top_bar .top_menu>li>a:active { background-color:#333; }
.menu_font li a:hover, .menu_font li a:active{ background-color:#333; }
.menu_font li:first-of-type a { border-radius:5px 5px 0 0; }
.menu_font li:last-of-type a { border-radius:0 0 5px 5px; }
.litype{width:58px;}
#plug-wrap { position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0, 0, 0, 0); z-index:800; }
#cate18 .device {bottom: 49px;}
#cate18 #indicator {bottom: 240px;}
#cate19 .device {bottom: 49px;}
#cate19 #indicator {bottom: 330px;}
#cate19 .pagination {bottom: 60px;}
</style>
</body></html>