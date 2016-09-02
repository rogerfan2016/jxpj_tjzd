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
        $("#btn_dc").click(function(){//
            exportResultList();
        });
        // 导出汇总
        $("#btn_dchz").click(function() {
          window.open("<%=request.getContextPath()%>/inspection/task_exportSummary.html?" + $("#search").serialize());
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
        
       /* $("tbody > tr[name^='tr']").click(
            function(){ //监听单击行
                $("input:checkbox").removeAttr("checked");
                $("tbody > tr[name^='tr']").removeClass("current");
                $(this).attr("class", "current");
                $(this).find("input:checkbox").attr("checked","checked");
                current = $(this);
            }
        );*/

        $("button[name='search']").click(function(e){//搜索按钮
            reflashPage();
            e.preventDefault();//阻止默认的按钮事件，防止多次请求
        });
        
        fillRows("20", "", "", false);//填充空行
    });
    function viewData(){
    	if($("input[id='id']:checked").length==0){
            alert("请先选中操作行");
            return false;
        }
        var id = $("input[id='id']:checked").val();
        goUrl(_path+"/inspection/task_taskSummer.html?type=${type}&summerQuery.params['taskId']="+id);
        
    }
    function editData() {
        if($("input[id='id']:checked").length==0){
            alert("请先选中操作行");
            return false;
        }
        var id = $("input[id='id']:checked").val();
        showWindow("修改",_path+"/inspection/task_detail.html?inspectionTask.id="+id,700,400);
    }
    
    function searchMember(id){
        window.open("<%=request.getContextPath() %>/inspection/task_searchMember.html?query.id="+id);
    }
    
    function createData(){
        showWindow("增加",_path+"/inspection/task_detail.html?type=${type}", 700,400);
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
    function updateDate(id,zt){
    	if(zt == 0){
    		showConfirm("确定要停止此评价任务吗？");
    	}else{
    		showConfirm("确定要开始此评价任务吗？");
    	}
        
        $("#why_cancel").click(function(){
            alertDivClose();
        });
        $("#why_sure").click(function(){
            $.post("<%=request.getContextPath() %>/inspection/task_update.html?inspectionTask.id="+id+"&inspectionTask.zt="+zt, null, function(data){
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
    
    //导出被评价结果列表
    function exportResultList(){
        if($("input[id='id']:checked").length==0){
            alert("请先选中操作行");
            return false;
        }
        var id = $("input[id='id']:checked").val();
        window.open("<%=request.getContextPath()%>/inspection/task_export.html?taskId="+id);
    }
    
    function reload(){
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
                            <a id="btn_zj" class="btn_zj">增加</a>
                        </li>
                        <li>
                            <a id="btn_xg" class="btn_xg">修改</a>
                        </li>
                        <li>
                            <a id="btn_ck" class="btn_ck">评价结果</a>
                        </li>
                        <li>
				            <a id="btn_dc" class="btn_dc">导出评价结果</a>
				        </li>
				        <li>
				            <a id="btn_dchz" class="btn_dc">导出汇总结果</a>
				        </li>
                        <li>
                            <a id="btn_sc" class="btn_sc">取 消</a>
                        </li>
                    </ul>           
                </div>
          <p class="toolbox_fot">
                <em></em>
            </p>
        </div>
 <form action="<%=request.getContextPath()%>/inspection/task_list.html" name="search" id="search" method="post">
 <input type="hidden" id="sortFieldName" name="sortFieldName" value="${sortFieldName}"/>
 <input type="hidden" id="asc" name="asc" value="${asc}"/>
 <input type="hidden" name="type" value="${type}"/>
<div class="searchtab">
    <table width="100%" border="0">
      <tbody>
        <tr> 
        <th width="10%">时间区间</th>
        <td width="30%" colspan="3">
            <input onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" readonly="readonly" style="width: 100px" name="query.start" value="${query.startText }" />
            --
            <input onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" readonly="readonly" style="width: 100px" name="query.end" value="${query.endText }"/>
        </td>
        <th width="10%"></th>
          <td width="20%">
            <!-- <input type="text" style="width:128px" class="text_nor" name="query.wjText" value="${query.wjText }"/> -->
          </td>
        </tr>
      </tbody>
      <tfoot>
        <tr>
          <td colspan="6">
            <div class="btn">
              <button class="btn_cx" name="search" type="button">查 询</button>
            </div>
          </td>
        </tr>
      </tfoot>
    </table>
  </div>
        <div class="formbox">
<!--标题start-->
    <h3 class="datetitle_01">
        <span>评价任务管理<font color="#0457A7" style="font-weight:normal;"> </font></span>
    </h3>
<!--标题end-->
    <div class="con_overlfow">
        <table width="100%" class="dateline tablenowrap" id="tiptab">
                <thead id="list_head">
                    <tr>
                        <td width="5%"><input type="checkbox" disabled/></td>
                        <td>操作</td>
                        <td>状态</td>
                        <td>评价时间</td>
                        <td>调查问卷</td>
                        <td>评价对象/评价人员</td>
                    </tr>
                </thead>
                <tbody id="list_body" >
                    <s:iterator value="pageList" var="p" status="st">
	                    <tr name="tr">
	                        <td><input type="checkbox" id="id" value="${p.id }"/></td>
	                        <td>
	                        <c:if test="${p.zt eq '0' }"><a style="color:#074695" href="#" onclick="updateDate('${p.id }','1');">开始</a></c:if>
	                        <c:if test="${p.zt eq '1' }"><a style="color:#074695" href="#" onclick="updateDate('${p.id }','0');">停止</a></c:if>
	                        </td>
	                        <td>
							<c:if test="${p.zt eq '1' }"><font color="green">开始</font></c:if>
	                        <c:if test="${p.zt eq '0' }"><font color="red">停止</font></c:if>
							</td>
	                        <td><s:date name="taskDate" format="yyyy-MM-dd"/></td>
	                        <td>${p.wjText } </td>
	                        <td><span style="${p.dxNum>0?'color:red':''}">${p.dxNum}</span>/
	                        <a style="color:#074695" href="#" onclick="searchMember('${p.id }');">${p.memberNum}</a>
	                        </td>
	                    </tr>
                    </s:iterator>
                </tbody>
    </table>
    </div>
    <ct:page pageList="${pageList }" />
    </div>
      </form>
  </body>
</html>
