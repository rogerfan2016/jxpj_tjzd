<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="ct" uri="/custom-code"%>
<%@ include file="/WEB-INF/pages/mobile/meta.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <%@ include file="/WEB-INF/pages/wjdc/mobile/mobilePage.ini"%>
	<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1"/>
	<link rel="stylesheet" href="<%=systemPath %>/css/wjdc/mobile/mobile.css"/>
	<link rel="stylesheet" href="<%=systemPath %>/css/wjdc/mobile/list.css"/>
	<script type="text/javascript" src="<%=systemPath %>/js/hrm/page.js"></script>
	<style type="text/css">
	.pageleft{
	padding-left:0.5em;
	float:left;
	color:#000;
	}
	.pageright{
	padding-right:1.2em;
	float:right;
	}
	
	</style>
	<script type="text/javascript">
	   //用户答卷
            function yhdj(wjid){
            	location.href= _path+"/wjdc_mobile/stgl_yhdj.html?wjModel.wjid="+wjid;
            }

            $(function(){
                $(".header_nav").find("a").each(function(){
                    if($(this).html()=="${model.djzt}"){
                        $(this).parent("li").addClass("nav_active");
                    }
                    $(this).click(function(){
                        $("#djzt").val($(this).html());
                        $("#wjListForm").submit();
                    });
                });
                if($("#ulQs").find("li").length==0){
                    $("#liNotFind").show();
                }
            });
           
	</script>

</head>
<body>
<header data-am-widget="header" class="am-header am-header-default">
      <div class="am-header-left am-header-nav">
          <a href="../wjdc_mobile/index_initMenu.html" class="">
              <img class="am-header-icon-custom" src="data:image/svg+xml;charset=utf-8,&lt;svg xmlns=&quot;http://www.w3.org/2000/svg&quot; viewBox=&quot;0 0 12 20&quot;&gt;&lt;path d=&quot;M10,0l2,2l-8,8l8,8l-2,2L0,10L10,0z&quot; fill=&quot;%23fff&quot;/&gt;&lt;/svg&gt;" alt=""/>
          </a>
      </div>

      <h1 class="am-header-title">
         学生评教
      </h1>
  </header>
    <div class="header">
        <ul class="header_nav" style="width:180px;">
            <li><a href="javascript:;">未评价</a></li>
            <li><a href="javascript:;">已评价</a></li>
        </ul>
    </div>
     <form id="wjListForm" action="yhdjgl_cxYhdjxx.html" method="post">
     <input type="hidden" name="model.djzt" id="djzt" value="${model.djzt}"/>
     <div id="liNotFind" class="survey_list" style="padding-top:30px;text-align:center;height:80px;display:none;">
       <span style="color:green">暂无记录</span>
     </div>
    <ul id="ulQs" class="survey_list">
    <s:iterator value="wjList" id="wj">
        <li class="list_li">
            <div class="list_li_conent">
                <div class="list_text">${wj.wjmc}</div>
                <div class="header_right">
                <s:if test="djzt=='未答卷' && wjzt=='运行'">
                    <a onclick="yhdj('${wj.wjid}')" href="javascript:;" >
                        <div class="miniButton green rm10 div_add_but">评价</div>
                    </a>
                </s:if>
                <s:else>
                    <a onclick="yhdj('${wj.wjid}')" href="javascript:;" >
                        <div class="miniButton green rm10 div_add_but">查看</div>
                    </a>
                </s:else>
                </div>
            </div>
        </li>
    </s:iterator>
	</ul>
	<ct:page pageList="${wjList}" queryName="model.queryModel" query="${model.queryModel}"/>
	</form>
	<%@ include file="/WEB-INF/pages/mobile/navbar.jsp" %>
</body></html>