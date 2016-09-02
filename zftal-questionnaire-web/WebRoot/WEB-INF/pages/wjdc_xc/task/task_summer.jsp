<%@ page language="java" import="java.util.*,com.zfsoft.hrm.config.ICodeConstants" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="ct" uri="/custom-code"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
     <%@ include file="/commons/hrm/head.ini" %>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.ui.core.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.ui.all.css" type="text/css" media="all" />
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/hrm/code.js"></script>
    <script type="text/javascript">
    $(function(){
        $("#btn_ck").click(function(){//
        	if($("input[id='id']:checked").length==0){
                alert("请先选中操作行");
                return false;
            }
            var id = $("input[id='id']:checked").val();
            goUrl(_path+"/inspection/task_resultSummer.html?type=${type}&summerQuery.params['taskId']=${summerQuery.params['taskId']}&summerQuery.params['dcdx']="+id);
        });
        var current = null;

        $("input:checkbox").click(function(e){
            e.stopPropagation();
            if($(this).is(":checked")){
                $(this).closest("tr").click();
            }else{
                $(this).closest("tr").removeClass("current");
            }
        });
        
        $("#btn_dc").click(function(){//导出被评价列表
            exportResultList();
        });
        
        $("tbody > tr[name^='tr']").click(
            function(){ //监听单击行
                $("input:checkbox").removeAttr("checked");
                $("tbody > tr[name^='tr']").removeClass("current");
                $(this).attr("class", "current");
                $(this).find("input:checkbox").attr("checked","checked");
                current = $(this);
            }
        );
        
        fillRows("20", "", "", false);//填充空行
    });
    
    //导出被评价结果列表
    function exportResultList(){
        window.open("<%=request.getContextPath()%>/inspection/task_export.html?taskId=${summerQuery.params['taskId'] }");
    }
    
    function back(){
        goUrl(_path+"/inspection/task_list.html?type=${type}");
    }
    </script>
  </head>
  <body>

  <div class="toolbox">
        <!-- 按钮 -->
                <div class="buttonbox">
                    <ul>
                        <li>
                            <a id="btn_ck" class="btn_ck">查看评价人列表</a>
                        </li><!--
                        <li>
				            <a id="btn_dc" class="btn_dc">导出被评价列表</a>
				        </li>
                    --></ul>
                    <a id="back" class="btn_fh_rs" style="cursor: pointer" onclick="back()">返 回</a>
                </div>
          <p class="toolbox_fot">
                <em></em>
            </p>
        </div>
 <form action="<%=request.getContextPath()%>/inspection/task_taskSummer.html" name="search" id="search" method="post">
        <div class="formbox">
<!--标题start-->
    <h3 class="datetitle_01">
        <span>评价任务管理<font color="#0457A7" style="font-weight:normal;"></font></span>
        <input type="hidden" name="summerQuery.params['taskId']" value="${summerQuery.params['taskId'] }"/>
        <input type="hidden" name="type" value="${type }" />
    </h3>
<!--标题end-->
    <div class="con_overlfow">
        <table width="100%" class="dateline tablenowrap" id="tiptab">
                <thead id="list_head">
                    <tr>
                        <td width="5%"><input type="checkbox" disabled/></td>
                        <td>被评价对象编号</td>
                        <td>被评价对象</td>
                        <td>评价人数</td>
                        <td>评价得分</td>
                    </tr>
                </thead>
                <tbody id="list_body" >
                    <s:iterator value="taskSummerList" var="p" status="st">
                        <tr name="tr">
                            <td><input type="checkbox" id="id" value="${p['DCDX'] }"/></td>
                            <td>${p['DCDX'] }</td>
                            <td>${p['DCDXMC'] }</td>
                            <td>${p['NUM'] } </td>
                            <td>
                            <fmt:parseNumber value="${p['FZ'] }" var="a" /> 
                            <fmt:parseNumber value="${p['NUM']}" var="b"/>
                            <fmt:formatNumber value="${a/b}" type="number" pattern="0.00"/></td>
                        </tr>
                    </s:iterator>
                </tbody>
    </table>
    </div>
    <ct:page pageList="${taskSummerList }" queryName="summerQuery" />
    </div>
      </form>
  </body>
</html>
