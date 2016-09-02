<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/mobile/meta.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <%@ include file="/WEB-INF/pages/wjdc/mobile/mobilePage.ini"%>
    <link rel="stylesheet" href="<%=systemPath %>/css/wjdc/mobile/jqmobo.css">
    <link rel="stylesheet" href="<%=systemPath %>/css/wjdc/mobile/mobile.css"/>
    <link rel="stylesheet" href="<%=systemPath %>/css/wjdc/mobile/list.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.ui.all.css" type="text/css" media="all" />
    <script src="<%=systemPath %>/js/wjdc/mobile/wjdc_mobile.js" type="text/javascript"></script>
        <style>
        .ui-autocomplete{
            z-index:12001;
            width: 100%;
            background: white;
        }
    </style>
<script type="text/javascript">
$(function(){
    var caches = {};
    $("#userInput" ).autocomplete({
                 source: function(request,response){
         var key=$.trim(request.term);
         if(key!=""){
                if(key in caches){
                    response(caches[key]);
                    }
                $.ajax({
                    type:'post',
                    url:'<%=request.getContextPath() %>/inspection/config_userListScopeThink.html',
                    dataType:'json',
                    data:'type=${type}&deptType=self&term='+key,
                    cache:true,
                    success:function(data){
                        caches[key]=data;
                        response(data);
                        }
                    });
             }
          },
                 minLength: 1, //触发条件，达到两个字符时，才进行联想
                 select: function( event, ui ) {
                     $("#xmLabel").html(ui.item.userName);
                     $("#xmInput").val(ui.item.userName);
                     $("#ghLabel").html(ui.item.userId);
                     $("#ghInput").val(ui.item.userId);
                     $("#dwmcLabel").html(ui.item.departmentName);
                     $("#dwmInput").val(ui.item.departmentId);
                     return false;
                 }
            }).data("ui-autocomplete")._renderItem = function( ul, item ) {
                  return $("<li>")
                    .append( "<a>" + "["+item.userId+"]"+item.userName+"--"+item.departmentName + "</a>" )
                    .appendTo( ul );
            };
            showErrorDiv($("#ValError"),true,"11111");
    $("#save_btn").click(function(){
    	$(".ValError").html("");
        if(!check()){
             return false;
        }
        
        var param=$("#form_edit").serialize();
        $.post("<%=request.getContextPath()%>/inspection_mobile/result_create.html?type=${type}",
            $("#form_edit").serialize(),function(data){
            if(data.success){
            	     location.href= _path+"/inspection_mobile/result_detail.html?type=${type}&inspectionTaskResult.id="+data.resultId;
                }else{
                	$(".ValError").html(data.text);
                }
        },"json");
        return false;
    });
    
    $("#cancel").click(function(){
        divClose();
    });

    $("#inspectionTaskResult_memberId").change(function(){
    	$("#memberText").html($("option[value='"+$(this).val()+"']").html());
    });
    $("#memberText").html($("option[value='"+$("#inspectionTaskResult_memberId").val()+"']").html());
});
function check()
{
    var name=$("input[name='inspectionTaskResult.dcdx']").val();
    if(name==null || name==''){
        showErrorDiv($("#dcdxDiv"),true,"必须选择评价对象");
        return false;
    }
    var type=$("select[name='inspectionTaskResult.memberId']").val();
    if(type==null || type==''){
        showErrorDiv($("#memberDiv"),true,"评价任务不能为空");
        return false;
    }
    return true;
}
    
</script>
  </head>
<body>
<form id="form_edit"  method="post">
  <!--    <div class="time"><span>状态：${wjModel.wjzt}</span><span>时间：2012-08-08</span></div>   -->

      <header data-am-widget="header" class="am-header am-header-default">
	      <div class="am-header-left am-header-nav">
	          <a href="../inspection_mobile/result_list.html?type=${type}" class="">
	              <img class="am-header-icon-custom" src="data:image/svg+xml;charset=utf-8,&lt;svg xmlns=&quot;http://www.w3.org/2000/svg&quot; viewBox=&quot;0 0 12 20&quot;&gt;&lt;path d=&quot;M10,0l2,2l-8,8l8,8l-2,2L0,10L10,0z&quot; fill=&quot;%23fff&quot;/&gt;&lt;/svg&gt;" alt=""/>
	          </a>
	      </div>
	
	      <h1 class="am-header-title">
	         增加评价结果
	      </h1>
	  </header>
      <div id="divContent">
      <div id="divQuestion">
      <form action="wjnr">
          <div id="dcdxDiv" class="field ui-field-contain" style="padding: 4px 4px 5px; border-style: solid; border-width: 2px 2px 1px; border-color: rgb(249, 249, 249) rgb(249, 249, 249) rgb(239, 239, 239); ">
          <div class="field-label">评价对象<span class="req">*</span></div>
          <div class="ui-input-text">
            <input id="userInput" name="textname_stid_6" type="text">
            <span style="color: red; font-size:12px;">提示：请输入职工号或姓名模糊查找</span>
          </div>
           <hr data-am-widget="divider" style="" class="am-divider am-divider-dashed" />
          <table class="datelist" width="100%">
          			<tr>
                        <th width="40%">
                            <span style="color: green; font-size:20px;">姓名/工号：</span>
                        </th>
                        <td width="60%">
                            <label id="xmLabel"></label>/<label id="ghLabel"></label>
                            <input id="ghInput" type="hidden" name="inspectionTaskResult.dcdx"/>
                        </td>
                    </tr>

                    <tr>
                        <th>
                            <span style="color: green; font-size:20px;">所在部门：</span>
                        </th>
                        <td>
                            <label id="dwmcLabel"></label>
                        </td>
                    </tr>
                    </table>
          <div class="errorMessage"></div></div>
          <div id="memberDiv" class="field ui-field-contain" style="padding: 4px 4px 5px; border-style: solid; border-width: 2px 2px 1px; border-color: rgb(249, 249, 249) rgb(249, 249, 249) rgb(239, 239, 239); ">
          <div class="field-label">评价任务<span class="req">*</span></div>
          <div class="ui-select">
          <span id="memberText"></span><span style="color: green;">[点击变更]</span>
          <select id="inspectionTaskResult_memberId" name="inspectionTaskResult.memberId">
                            <c:forEach items="${taskList}" var="member">
                                <option value="${member.id}">(${member.taskDateStr })${member.wjText }</option>
                            </c:forEach>
                            </select>
          </div>
          <div class="errorMessage"></div></div>
      </form>
      </div>
        
        <div class="footer">
            <div class="ValError">
            </div>
            <div id="divSubmit" style="padding: 10px;">
                <div id="tdCode" style="display: none;">
                </div>
                <a id="save_btn" href="javascript:;" class="button blue" >开始评价</a>
            </div>
        </div>
    </div>
</div>
</form>
 <%@ include file="/WEB-INF/pages/mobile/navbar.jsp" %>
</body>
</html>