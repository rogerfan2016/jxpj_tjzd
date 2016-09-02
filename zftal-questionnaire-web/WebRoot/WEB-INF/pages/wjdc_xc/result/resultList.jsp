<%@ page language="java" import="java.util.*,com.zfsoft.hrm.config.ICodeConstants" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="ct" uri="/custom-code"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
     <%@ include file="/commons/hrm/head.ini" %>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.ui.core.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.ui.all.css" type="text/css" media="all" />
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/hrm/code.js"></script>
    <script type="text/javascript">
    $(function(){
        $("select[name='query.status']").val("${query.status}");
        $("#btn_zj").click(function(){//功能条增加按钮
            createData();
        });
        $("#btn_sc").click(function(){//
            removeData();
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
        showWindow("修改",_path+"/inspection/result_detail.html?type=${type}&inspectionTask.id="+id, 450,280);
    }
    
    function createData(){
        showWindow("增加",_path+"/inspection/result_choose.html?type=${type}", 450,280);
    }
    function removeData(){
        if($("input[id='id']:checked").length==0){
            alert("请先选中操作行");
            return false;
        }
        var id = $("input[id='id']:checked").val();
        var status=$("input[id='id']:checked").closest("tr").attr("status");
        if("0"!=status){
        	alert("只能删除未提交的记录");
            return false;
        }
      //删除
        showConfirm("确定要删除吗？");

        $("#why_cancel").click(function(){
            alertDivClose();
        });
        $("#why_sure").click(function(){
            $.post("<%=request.getContextPath() %>/inspection/result_remove.html?inspectionTaskResult.id="+id, null, function(data){
                var callback = function(){
                    reload();
                };
                
                reflashPage(data, callback);
            }, "json");
        });
        
    }
    function reflashPage(){
        $("#search").attr("action","<%=request.getContextPath()%>/inspection/result_list.html?type=${type}");
        $("#search").attr("method","post");
        $("#search").submit();
    }
    function reload(){
        goUrl("<%=request.getContextPath() %>/inspection/result_list.html?type=${type}");
    }

    function toWjView(id,wjid){
    	window.open("<%=request.getContextPath() %>/inspection/result_detail.html?type=${type}&inspectionTaskResult.id="+id);
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
                            <a id="btn_sc" class="btn_sc">取 消</a>
                        </li>
                    </ul>
                </div>
          <p class="toolbox_fot">
                <em></em>
            </p>
        </div>
 <form action="<%=request.getContextPath()%>/inspection/result_list.html?type=${type}" name="search" id="search" method="post">
 <input type="hidden" id="sortFieldName" name="sortFieldName" value="${sortFieldName}"/>
 <input type="hidden" id="asc" name="asc" value="${asc}"/>
<div class="searchtab">
    <table width="100%" border="0">
      <tbody>
        <tr> 
        <th width="10%">被评价对象</th>
          <td>
            <input type="text" style="width:128px" class="text_nor" name="query.dcdxText" value="${query.dcdxText }"/>
          </td>
        <th width="10%">状态</th>
        <td>
            <select name="query.status">
                <option value="">全部</option>
                <option value="0">未评价</option>
                <option value="1">已评价</option>
            </select>
        </td>
        <th width="10%">时间区间</th>
        <td>
            <input onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" readonly="readonly" style="width: 100px" name="query.start" value="<s:date name="query.start" format="yyyy-MM-dd"/>" />
            --
            <input onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" readonly="readonly" style="width: 100px" name="query.end" value="<s:date name="query.end" format="yyyy-MM-dd"/>"/>
        </td>
        </tr>
      </tbody>
      <tfoot>
        <tr>
          <td colspan="6">
            <div class="btn">
              <button class="btn_cx" id="search_go" name="search" type="button">查 询</button>
            </div>
          </td>
        </tr>
      </tfoot>
    </table>
  </div>
        <div class="formbox">
<!--标题start-->
    <h3 class="datetitle_01">
        <span>评价提交管理<font color="#0457A7" style="font-weight:normal;"> </font></span>
    </h3>
<!--标题end-->
    <div class="con_overlfow">
        <table width="100%" class="dateline tablenowrap" id="tiptab">
                <thead id="list_head">
                    <tr>
                        <td width="5%"><input type="checkbox" disabled/></td>
                        <td>评价任务</td>
                        <td>被评价对象</td>
                        <td>评价结果</td>
                        <td>评价状态</td>
                        <td>任务状态</td>
                        <td>操作</td>
                    </tr>
                </thead>
                <tbody id="list_body" >
                    <s:iterator value="pageList" var="p" status="st">
                    <tr name="tr" status="${p.status }">
                        <td><input type="checkbox" id="id" value="${p.id }"/></td>
                        <td>${p.wjText }(<s:date name="taskDate" format="yyyy-MM-dd"/>)</td>
                        <td>${p.dcdxText } </td>
                        <td><c:if test="${p.zf eq null}">0</c:if>${p.zf }分 </td>
                        <td>
                        	<c:if test="${p.status=='0'}">未评价</c:if>
                            <c:if test="${p.status!='0'}">已评价</c:if>
                        </td>
                        <td>
                        	<c:if test="${p.rwzt eq '1' }"><font color="green">开始</font></c:if>
	                        <c:if test="${p.rwzt eq '0' }"><font color="red">停止</font></c:if>
                        </td>
                        <td>
                        <c:if test="${p.rwzt=='1'}">
                            <c:if test="${p.status=='0'}">
                                <a style="color: blue;" onclick="toWjView('${p.id}','${p.wjid}')">录入评价结果</a>
                            </c:if>
                            <c:if test="${p.status=='1'}">
                                <a style="color: blue;" onclick="toWjView('${p.id}','${p.wjid}')">编辑评价结果</a>
                            </c:if>
                        </c:if>
                            <c:if test="${p.status!='0'}">
                                <a style="color: blue;" onclick="toWjView('${p.id}','${p.wjid}')">查看评价详情</a>
                            </c:if>
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
