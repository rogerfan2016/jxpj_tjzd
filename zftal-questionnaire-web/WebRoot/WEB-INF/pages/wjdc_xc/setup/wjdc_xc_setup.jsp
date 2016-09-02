<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="ct" uri="/custom-code"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <%@ include file="/commons/hrm/head.ini" %>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.ui.core.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.ui.all.css" type="text/css" media="all" />
    <script type="text/javascript">
    function groupView(val){
        var result = showTopWin( _path + "/inspection/config_" + val+".html?query.params['type']=${type}", 440, 520, null );
        if(!result){
            result=window.returnValue;
        }
        if( result == null ) return;
        var dl = $("#dl_"+val);
        var param="inspectionConfig.configType=${type}&inspectionConfig.key="+val;
        for(var i=0;i<result.length;i++)
            param+="&valueList="+result[i][0];
        
        //alert(param);
        $.post("<%=request.getContextPath()%>/inspection/config_save.html",
            param,function(data){
                var callback = function(){
                	location.href="<%=request.getContextPath()%>/inspection/config_setup.html?type=${type}";
                };
                if(data.success){
                    processDataCall(data, callback);
                }else{
                    showWarning(data.text);
                }
                        
        },"json");
        return false;
    }
    function removeItem(val, param){
    	var p ="inspectionConfig.configType=${type}&inspectionConfig.key="+param+"&inspectionConfig.value="+val;
    	 $.post("<%=request.getContextPath()%>/inspection/config_remove.html",
    	            p,function(data){
    	                if(data.success){
    	                	$("#"+param+"_"+val).closest("dd").remove();
    	                }else{
    	                    showWarning(data.text);
    	                }
    	                        
    	        },"json");
    }
    
    </script>
  </head>
  <body>
<div id="testID" >    
  <div class="tab">
    <table align="center" class="formlist">
        <thead>
            <tr>
                <th colspan="4">
                    <span>评价基础设置<font color="#0f5dc2" style="font-weight:normal;"></font></span>
                </th>
            </tr>
        </thead>
        <tbody id="form1">
            <tr>
                <tr id='ALIAS_tr' class='tsdx_tr'>
                <th>评价组成员<span class="red">*</span></th>
                <td colspan=3">
                     <div class="search_advanced" id="myTbody3">  
                       <div class="selected-attr after" style="float:none;min-height:30px;_height:30px">
                        <dl id ="dl_personList">
                        <c:forEach items="${personList}" var="p">
                        <dd> <a id="personList_${p['GH'] }" href="#"><h5>${p['XM'] }</h5>
                                <span class="close-icon" title="取消" onclick="removeItem('${p['GH'] }','personList')"></span></a>
                                <input type="hidden" value="${p['GH'] }"></dd>
                        </c:forEach>
                        </dl>
                       </div>
                        <button onclick="groupView('personList')" type="button">添加人员</button>
                    </div>
                </td>
            </tr>
            <tr>
                <tr id='ALIAS_tr' class='tsdx_tr'>
                <th>评价表单模板<span class="red">*</span></th>
                <td colspan="3">
                     <div class="search_advanced" id="myTbody3">  
                       <div class="selected-attr after" style="float:none;min-height:30px;_height:30px">
                        <dl id ="dl_dcwjList">
                            <c:forEach items="${dcwjList}" var="p">
                            <dd> <a id="dcwjList_${p['WJID'] }" href="#"><h5>${p['WJMC'] }</h5>
                                <span class="close-icon" title="取消" onclick="removeItem('${p['WJID'] }','dcwjList')"></span></a>
                                <input type="hidden" value="${p['WJID'] }"></dd>
                            </c:forEach>
                        </dl>
                       </div>
                        <button onclick="groupView('dcwjList')" type="button">添加模板</button>
                    </div>
                </td>
            </tr>
        </tbody>
    <tfoot>
      <!-- tr>
        <td colspan="4">
            <div class="bz">"<span class="red">*</span>"为必填项</div>
            <div class="btn">
                <button id="action" name="action" >保 存</button>
                <c:if test="${config.type==null}">
                    <button id="action" name="action" onclick="changeType(null);">取 消</button>
                </c:if>
            </div>
        </td>
      </tr -->
    </tfoot>
    </table>
    </div>
</div>
  </body>
</html>