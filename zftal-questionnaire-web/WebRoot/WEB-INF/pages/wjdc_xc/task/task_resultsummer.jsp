<%@ page language="java" import="java.util.*,com.zfsoft.hrm.config.ICodeConstants" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
        $("#btn_zj").click(function(){//功能条增加按钮
            createData();
        });
        $("#btn_ck").click(function(){//
            viewData();
        });
        $("#btn_sc").click(function(){//
            removeData();
        });
        $("#btn_xg").click(function(){//
            editData();
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
        
        $("tbody > tr[name^='tr']").click(
            function(){ //监听单击行
                $("input:checkbox").removeAttr("checked");
                $("tbody > tr[name^='tr']").removeClass("current");
                $(this).attr("class", "current");
                $(this).find("input:checkbox").attr("checked","checked");
                current = $(this);
            }
        );

        $("button[name='search']").click(function(e){//搜索按钮
            reflashPage();
            e.preventDefault();//阻止默认的按钮事件，防止多次请求
        });
        
        fillRows("20", "", "", false);//填充空行
    });
    
    function editData() {
        if($("input[id='id']:checked").length==0){
            alert("请先选中操作行");
            return false;
        }
        var id = $("input[id='id']:checked").val();
        showWindow("修改",_path+"/inspection/task_detail.html?inspectionTask.id="+id,700,400);
    }
    
    function createData(){
        showWindow("增加",_path+"/inspection/task_detail.html", 700,400);
    }
    function removeData(){
        if($("input[id='id']:checked").length==0){
            alert("请先选中操作行");
            return false;
        }
        var id = $("input[id='id']:checked").val();
      //删除
        showConfirm("确定要删除吗？");

        $("#why_cancel").click(function(){
            alertDivClose();
        });
        $("#why_sure").click(function(){
            $.post("<%=request.getContextPath() %>/inspection/task_remove.html?inspectionTask.id="+id, null, function(data){
                var callback = function(){
                    reload();
                };
                
                processDataCall(data, callback);
            }, "json");
        });
        
    }
    function reflashPage(){
        $("#search").attr("action","<%=request.getContextPath()%>/inspection/task_list.html");
        $("#search").attr("method","post");
        $("#search").submit();
    }
    function back(){
    	goUrl(_path+"/inspection/task_taskSummer.html?type=${type}&summerQuery.params['taskId']=${summerQuery.params['taskId']}");
    }
    function toWjView(id){
    	window.open(_path+"/inspection/result_detail.html?inspectionTaskResult.id="+id);
    }
    </script>
  </head>
  <body>

  <div class="toolbox">
        <!-- 按钮 -->
                <div class="buttonbox">
                    <ul>
                    </ul>           
                    <a id="back" class="btn_fh_rs" style="cursor: pointer" onclick="back()">返 回</a>
                </div>
          <p class="toolbox_fot">
                <em></em>
            </p>
        </div>
 <form action="<%=request.getContextPath()%>/inspection/task_resultSummer.html" name="search" id="search" method="post">
 <input type="hidden" name="type" value="${type }" />
 <input type="hidden" id="sortFieldName" name="sortFieldName" value="${sortFieldName}"/>
 <input type="hidden" id="asc" name="asc" value="${asc}"/>
 <input type="hidden" id="asc" name="summerQuery.params['dcdx']" value="${summerQuery.params['dcdx'] }"/>
 <input type="hidden" id="asc" name="summerQuery.params['taskId']" value="${summerQuery.params['taskId']}"/>
 
        <div class="formbox">
<!--标题start-->
    <h3 class="datetitle_01">
        <span>上报详情<font color="#0457A7" style="font-weight:normal;"> </font></span>
    </h3>
<!--标题end-->
    <div class="con_overlfow">
        <table width="100%" class="dateline tablenowrap" id="tiptab">
                <thead id="list_head">
                    <tr>
                        <td width="5%"><input type="checkbox" disabled/></td>
                        <td>上报人</td>
                        <td>评分</td>
                        <td>操作</td>
                    </tr>
                </thead>
                <tbody id="list_body" >
                    <s:iterator value="resultSummerList" var="p" status="st">
                        <tr name="tr">
                            <td><input type="checkbox" id="id" value="${p.id }"/></td>
                            <td><ct:PersonParse code="${p['GH'] }" /></td>
                            <td>${p['FZ'] } </td>
                            <td><a style="color: blue;" onclick="toWjView('${p['ID']}')">查看评价详情</a></td>
                        </tr>
                    </s:iterator>
                </tbody>
    </table>
    </div>
    <ct:page pageList="${resultSummerList }" queryName="summerQuery" />
    </div>
      </form>
  </body>
</html>
