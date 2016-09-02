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
<script id="changyan_mobile_js" charset="utf-8" type="text/javascript" 
	src="http://changyan.sohu.com/upload/mobile/wap-js/changyan_mobile.js?client_id=cys5AyTbR&conf=prod_edd6e284ce8dd171ae0818d0da3c3250">
</script>
<script type="text/x-handlebars-template" id="amz-tpl">
    {{>header header}}
<!-- 多说组件
	<div data-am-widget="duoshuo" class="am-duoshuo am-duoshuo-default" data-ds-short-name="tjzydx" data-title="意见反馈" data-thread-key="1234567890">
		<div class="ds-thread" >
		</div>
	</div>
-->
<!-- 畅言组件  -->
<div id="SOHUCS"></div>

<form id="editform">
    <div style="padding:15px;">
        <textarea id="txtOpinion" name="txtOpinion" rows="10" style="border-radius:10px;" class="am-form-field" placeholder="告诉我们您的想法，我们会做的更好"></textarea>
    </div>
</form>
    <div style="padding:0 25px;">
        <div id="saveOpinion" style="border-radius:10px; display:block;" class="am-btn am-btn-success">提交</div>
    </div>
    <div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
        <div class="am-modal-dialog">
            <div class="am-modal-bd">
                请输入意见！
            </div>
            <div class="am-modal-footer">
                <span class="am-modal-btn">确定</span>
            </div>
        </div>
    </div>
	<div class="am-modal am-modal-alert" tabindex="-1" id="success">
        <div class="am-modal-dialog">
            <div class="am-modal-bd">
                提交成功，感谢你的支持！
            </div>
            <div class="am-modal-footer">
                <span class="am-modal-btn">确定</span>
            </div>
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
    
    data.header.content.title = "意见反馈";
    data.header.content.left = leftc;

    var html = template(data);
    $tpl.before(html);
    
    $(function () {
        // 提交
        $("#saveOpinion").click(function() {
          if ($.trim($("#txtOpinion").val()).length == 0) {
            $("#my-alert").modal();
            return false;
          }
        
          $.post("<%=request.getContextPath()%>/evaluation/mobile_saveOpinion.html",
            $("#editform").serialize(), function(data) {
              if(data.success){
                $("#success").modal();
              }else{
                showWarning(data.text);
              }
            },"json");
          return false;
        });
    });
</script>
<%@ include file="/WEB-INF/pages/mobile/navbar.jsp" %>
</body>
</html>