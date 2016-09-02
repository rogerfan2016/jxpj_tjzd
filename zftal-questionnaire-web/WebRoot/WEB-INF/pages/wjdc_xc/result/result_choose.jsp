<%@page import="com.zfsoft.hrm.config.ICodeConstants"%>
<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ct" uri="/custom-code"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <style>
        .ui-autocomplete{
            z-index:12001;
            width: 500px
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
                        .append( "<a>" + item.userName+"("+item.userId+") "+item.departmentName+"("
                                +item.departmentId+") " + "</a>" )
                        .appendTo( ul );
                };
                
        $("#save_btn").click(function(){
            
            if(!check()){
                 return false;
            }
            var param=$("#form_edit").serialize();
            $.post("<%=request.getContextPath()%>/inspection/result_create.html?type=${type}",
                $("#form_edit").serialize(),function(data){
            	var callback = function(){
                    reload();
                };
                if(data.success){
                    processDataCall(data, callback);
                    }else{
                        showWarning(data.text);
                    }
            },"json");
            return false;
        });
        
        $("#cancel").click(function(){
            divClose();
        });
    });

    function check()
    {
        var name=$("input[name='inspectionTaskResult.dcdx']").val();
        if(name==null || name==''){
            showWarning("必须选择评价对象");
            return false;
        }
        var type=$("select[name='inspectionTaskResult.memberId']").val();
        if(type==null || type==''){
            showWarning("评价任务不能为空");
            return false;
        }
        return true;
    }
    
    </script>
</head>
<body>
    <form id="form_edit">
        <div class="tab">
            <table width="100%"  border="0" class="formlist" cellpadding="0" cellspacing="0">
                <thead>
                    <tr>
                        <th colspan="4">
                            <span><font color="#0f5dc2" style="font-weight:normal;">增加信息</font></span>
                        </th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <td colspan="4">
                            <div class="bz">"<span class="red">*</span>"为必填项</div>
                            <div class="btn">
                                <button id="save_btn">保 存</button>
                                <button id="cancel" type="button" onclick="divClose();">取 消</button>
                            </div>
                        </td>
                    </tr>
                </tfoot>
                <tbody>
                    <tr>
                        <th>
                            <span class="red"></span>人员查询
                        </th>
                        <td colspan="3">
                            <input type="text" id="userInput"><br/>
                            <span class="red">输入职工号、姓名、部门代码、部门名称和状态查找</span>
                        </td>
                    </tr>
                    <tr>
                        <th width="20%">
                            <span class="red"></span>职工号
                        </th>
                        <td width="30%">
                            <label id="ghLabel"></label>
                            <input id="ghInput" type="hidden" name="inspectionTaskResult.dcdx"/>
                        </td>
                        <th width="30%">
                            <span class="red"></span>姓名
                        </th>
                        <td width="20%">
                            <label id="xmLabel"></label>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <span class="red"></span>所在部门
                        </th>
                        <td colspan="3">
                            <label id="dwmcLabel"></label>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <span class="red">*</span>评价任务
                        </th>
                        <td colspan="3">
                            <select name="inspectionTaskResult.memberId">
                            <c:forEach items="${taskList}" var="member">
                                <option value="${member.id}">${member.wjText }(${member.taskDateStr })</option>
                            </c:forEach>
                            </select>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </form>
</body>
</html>