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
    <link rel="stylesheet" href="<%=systemPath %>/css/wjdc/mobile/jqmobo.css">
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
       //�û����
            function yhdj(id){
            	location.href="<%=request.getContextPath() %>/inspection_mobile/result_detail.html?type=${type}&inspectionTaskResult.id="+id;
            }

            $(function(){
                $(".header_nav").find("a").each(function(){
                    if($(this).attr("status")=="${query.status}"){
                        $(this).parent("li").addClass("nav_active");
                    }
                    $(this).click(function(){
                        $("#djzt").val($(this).attr("status"));
                        $("#wjListForm").submit();
                    });
                });

                $("#but_zj").click(function(){
                	location.href=_path+"/inspection_mobile/result_choose.html?type=${type}";
                });
                
                if($("#ulQs").find("li").length==0){
                    $("#liNotFind").show();
                }
                
            });

            function wjdc_xc_result_toPager(page){
          	  if ($("#nowPage").val() == page) {
        		    return false;
           	  }
                $("#nowPage").val(page);
                $("form:first").submit();
            }
           
    </script>
</head>
<body>
<script type="text/x-handlebars-template" id="amz-tpl">
    {{>header header}}
</script>
    <div class="header">
        <ul class="header_nav" style="width:280px;">
            <li><a href="javascript:;" status="0">δ�ύ</a></li>
            <li><a href="javascript:;" status="1">���ύ</a></li>
        </ul>
    </div>
     <form id="wjListForm" action="result_list.html?type=${type}" method="post">
     <input type="hidden" name="query.status" id="djzt" value="${query.status}"/>
     <c:if test="${empty pageList}">
     <div id="liNotFind" class="survey_list" style="padding-top:30px;text-align:center;height:80px;display:none;">
       <span style="color:green">���޼�¼</span>
     </div>
     </c:if>
     <div data-am-widget="list_news" class="am-list-news am-list-news-default" >
        <div class="am-list-news-bd">
            <ul class="am-list">
				<s:iterator value="pageList" var="p" status="st">
                <li class="am-g am-list-item-dated">
                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                        <tr>
                            <td valign="center" width="70%">
                                	���۶���${p.dcdxText }<br>
                               	 	����ģ�壺${p.wjText }<br>
                               		����ʱ�䣺<s:date name="taskDate" format="yyyy-MM-dd"/><br>
                               		���۽����<font color="red"><c:if test="${p.zf eq null}">0</c:if>${p.zf }</font>��<br>
                            </td>
		                	<td align="center" valign="center">
		                		<c:if test="${p.rwzt=='1'}">
		                            <c:if test="${p.status=='0'}">
		                                <button type="button" onclick="yhdj('${p.id}')" style="border-radius:10px;" class="am-btn am-btn-success">����</button>
		                            </c:if>
		                            <c:if test="${p.status=='1'}">
		                                <button type="button" onclick="yhdj('${p.id}')" style="border-radius:10px;" class="am-btn am-btn-success">�༭</button>
		                            </c:if>
		                        </c:if>
		                        <c:if test="${p.rwzt!='1'}">
		                        	<button type="button" style="border-radius:10px;" class="am-btn am-btn-default">ֹͣ</button>
		                        </c:if>
		                        <c:if test="${p.status!='0'}">
		                         	<button type="button" onclick="yhdj('${p.id}')" style="border-radius:10px;" class="am-btn am-btn-secondary">�鿴</button>
		                    	</c:if>
		                	</td>
                        </tr>
                    </table>
                </li>
				</s:iterator>
            </ul>
        </div>
    </div>
    <input type="hidden" id="totalSize" name="totalSize" value="${pageInfo.items }"/>
	<input type="hidden" id="nowPage" name="query.toPage" value="${pageInfo.page }"/>
	<input type="hidden" id="perSize" name="query.perPageSize" value="${pageInfo.itemsPerPage }"/>
	</br>
	<table cellSpacing="0" cellPadding="0" width="100%" class="pageTage">
	  <tr>
	    <td align="left" style="padding-left: 20px"><div id="previous"><a onclick="wjdc_xc_result_toPager(${pageInfo.page<2?pageInfo.page:pageInfo.page-1});" href="#">��һҳ</a></div></td>
	    <td align="center">��<span style="color:red;">${pageInfo.page}</span>ҳ/��<span id="totalPage" style="color:red;">${pageInfo.lastPage }</span>ҳ</td>
	    <td align="right" style="padding-right: 20px"><div id="next"><a onclick="wjdc_xc_result_toPager(${pageInfo.page<pageInfo.lastPage?pageInfo.page+1:pageInfo.page});" href="#">��һҳ</a></div></td>
	  </tr>
	</table>
    </form>
    </div>
        
   <div class="footer">
            <div class="ValError">
            </div>
            <div id="divSubmit" style="padding: 10px;">
                <div id="tdCode" style="display: none;">
                </div>
                <a id="but_zj" href="javascript:;" class="button blue" onclick="wjSubmitCheckMe()">��Ҫ����</a>
            </div>
        </div>
 <%@ include file="/WEB-INF/pages/mobile/navbar.jsp" %>
<script type="text/javascript"> 

    var $tpl = $('#amz-tpl');
    var source = $tpl.text();
    var template = Handlebars.compile(source);
    var leftc = [];
    var obj = {};
    obj.link = "javascript:history.go(-1);";
    obj.icon = "chevron-left";
    leftc.push(obj);
    
    <c:choose> 
		<c:when test="${type =='PJSKLS'}">
	    	data.header.content.title = "�����ڿ���ʦ";
	    </c:when>
	    <c:when test="${type =='PJBZR'}">
	    	data.header.content.title = "���۰�����";
		</c:when>
		<c:when test="${type =='JSPJBZR'}">
	    	data.header.content.title = "��ʦ���۰�����";
		</c:when>
		<c:when test="${type =='JSPJBJ'}">
	    	data.header.content.title = "��ʦ���۰༶";
		</c:when>
	    <c:when test="${type =='XNXC'}">
	    	data.header.content.title = "�����������";
		</c:when>
		<c:otherwise>
	    	data.header.content.title = "��ѧ����";
		</c:otherwise>
	</c:choose> 
    data.header.content.left = leftc;

    var html = template(data);
    $tpl.before(html);
</script>
</body>
</html>