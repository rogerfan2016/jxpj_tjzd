<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<%@ include file="/WEB-INF/pages/globalweb/head/pagehead_v4.ini"%>
		<script type="text/javascript" src="<%=systemPath %>/js/globalweb/comm/validate.js"></script>
		<script type="text/javascript" src="<%=systemPath %>/js/globalweb/comm/inputPrompt.js"></script>
		<script type="text/javascript" src="<%=systemPath %>/js/wjdc/textClue.js"></script>
        <script type="text/javascript" src="<%=systemPath %>/js/wjdc/select.js"></script>
		<script type="text/javascript" src="<%=systemPath %>/js/zfjy/jyweb/dwgl.js"></script>
		<script type="text/javascript" src="<%=systemPath %>/js/editor/kindeditor-min.js"></script>
		<script type="text/javascript" src="<%=systemPath %>/js/editor/editorParams.js"></script>
		<script type="text/javascript" src="<%=systemPath %>/js/editor/zh_CN.js"></script>
		<link rel="stylesheet" href="<%=systemPath %>/css/plugins/textClue.css" type="text/css" media="all" />
		<script type="text/javascript">
		//var editor;
		
		function save(){
			 //jQuery("#dwjj").val(editor.html());
			 
			 //if (inputResult() && checkInputNotNull('wjmc!!wjlx!!jsy!!jwy')){ 
				 subForm(_path+'/wjdc/wjgl_updateWjzt.html');
			 //}
		}
		
		
		jQuery(function(){
			//initShengShi();
			//initLsbm();
			//editor = KindEditor.create('textarea[name="jsy"]', defaultOption);
		}); 
		</script>
	</head>
	<body>
	<s:form method="post" theme="simple">
	 <div class="tab">
	  <table width="100%"  border="0" class="formlist" cellpadding="0" cellspacing="0">
	    <thead>
	    	<tr>
	        	<th colspan="4"><span>问卷信息</span></th>
	        </tr>
	    </thead>
	    <tfoot>
	      <tr>
	        <td colspan="4"><div class="bz"></div>
	          <div class="btn">
	            <button name="btn_tj" onclick="save();" type="button">保 存</button>
	            <button name="btn_fh" onclick="iFClose()" type="button">关闭</button>
	          </div>
	        </td>
	      </tr>
	    </tfoot>
	    <tbody>
	      <tr>
	      	 <th width="15%"><span class="red">*</span>问卷状态</th>
	         <td colspan="3">
	         	<s:hidden name="wjid"></s:hidden>
	         	<s:select list="wjztList" name="wjzt" id="wjzt" listKey="DM" listValue="MC" 
								cssStyle="width:160px;" theme="simple"></s:select> 
	         </td>
	      </tr>
	    </tbody>
	  </table>
  </div>
  <s:if test="result != null && result != ''">
  	<script defer="defer">
  		alert('${result}','',{'clkFun':function(){
  			refershParent();
  		}});
  	</script>
  </s:if>
</s:form>
</body>
</html>