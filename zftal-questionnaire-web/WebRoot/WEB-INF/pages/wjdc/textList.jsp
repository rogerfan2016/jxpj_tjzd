<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <%@ include file="/WEB-INF/pages/globalweb/head/pagehead_v4.ini"%>
        <%@ include file="/WEB-INF/pages/globalweb/head/jqGrid.ini"%>
        <script type="text/javascript" src="<%=systemPath %>/js/globalweb/comm/dateformat.js"></script>
        <script type="text/javascript" src="<%=systemPath %>/js/wjdc/textClue.js"></script>
        <script type="text/javascript" src="<%=systemPath %>/js/wjdc/select.js"></script>
        <script type="text/javascript" src="<%=systemPath %>/js/wjdc/wjgl.js"></script>
        <link rel="stylesheet" href="<%=systemPath %>/css/plugins/textClue.css" type="text/css" media="all" />
        <script type="text/javascript">
            
            //用户答卷
            function yhdj(wjid){
                var url= _path+"/wjdc/stgl_yhdj.html?wjModel.wjid="+wjid;
                window.open(url);
            }
            
            function searchResult(){
                var url= _path+"/wjdc/yhdjgl_cxYhdjxx.html";
                location.href=url;
            }
            
            jQuery(function(){
                //var wjxxGrid = new WjxxGrid();
                //loadJqGrid("#tabGrid","#pager",wjxxGrid);
                //bdan();
                //initWjlx();
            });
            
        </script>
    </head>


    <body>
        <div class="toolbox">
            <!-- 加载当前菜单栏目下操作     -->
            
            <div class="buttonbox">
            <ul id="but_ancd">
                    <li>
                        <a href="wjtj_sttj.html?wjid=${model.wjid }" class="btn_fh" >
                            返回</a>
                    </li>
            </ul>
        </div>
        <div class="formbox">
            <form method="post" action="stgl_textList.html">
	        <div>
	            <input name="model.wjid" value="${model.wjid }" type="hidden">
	            <input name="model.stid" value="${model.stid }" type="hidden">
	            <button  style='display:none;' type="submit" id="search_go" >查 询</button>
	        </div>
            <table width="100%" class="dateline">
              <thead>
                <tr>
                    <td>
                        序号
                    </td>
                    <td>
                        答案文本
                    </td>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="pageList" id="xx">
                    <tr>
                        <td>
                           ${st.index + model.queryModel.currentResult +1}
                        </td>
                        <td>
                            <span>${xx.txnr}</span>
                        </td>
                    </tr>
                </s:iterator>
            </tbody>
            </table>
            <jsp:include page="/WEB-INF/pages/comm/pageFootMenu.jsp"></jsp:include>
            </form>
        </div>
    </body>
</html>
