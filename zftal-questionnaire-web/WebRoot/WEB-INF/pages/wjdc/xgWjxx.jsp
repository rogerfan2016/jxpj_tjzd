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
			 
			 if (inputResult() && checkInputNotNull('wjmc!!wjlx')){ 
			 	if(jQuery("#wjlx").val()=="CPL"&&!checkInputNotNull("wjzf")){
			 		return false;
			 	} 
				 subForm(_path+'/wjdc/wjgl_xgBcWjxx.html');
			 }
		}
		
		
		function wjlxChange(){
			var wjlx=jQuery("#wjlx").val();
			if(wjlx=="CPL"){
				jQuery("#th_span_wjzf").css("display","");
				jQuery("#td_span_wjzf").css("display","");
			}else{
				jQuery("#wjzf").val("");
				jQuery("#th_span_wjzf").css("display","none");
				jQuery("#td_span_wjzf").css("display","none");
			}
		}
		
		jQuery(function(){
			wjlxChange();
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
	        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
	          <div class="btn">
	            <button name="btn_tj" onclick="save();" type="button">保 存</button>
	            <button name="btn_fh" onclick="iFClose()" type="button">关闭</button>
	          </div>
	        </td>
	      </tr>
	    </tfoot>
	    <tbody>
	      <tr>
	      	 <th width="15%"><span class="red">*</span>问卷名称</th>
	         <td colspan="3">
	         	<s:hidden name="wjid"></s:hidden>
		        <s:textfield maxlength="20" name="wjmc" id="wjmc" cssStyle="width:76%"></s:textfield> 
	         </td>
	      </tr>
	      <tr>
	        <th width="15%"><span class="red">*</span>问卷类型</th>
	        <td width="35%">
	        <!-- 
		        <s:select list="wjlxList" listKey="DM" listValue="MC" headerKey="" headerValue="请选择" name="wjlx" id="wjlx" cssStyle="width:40%" onchange="wjlxChange();"></s:select>
	         -->
	         <s:select list="wjlxList" listKey="DM" listValue="MC" name="wjlx" id="wjlx" cssStyle="width:40%" onchange="wjlxChange();"></s:select>
	        </td>
	      	<th>
	      		<span id="th_span_wjzf"><span class="red">*</span>问卷总分</span>
	      	</th>
	      	<td width="35%">
	      		<span id="td_span_wjzf">
	      		<s:textfield maxlength="3" name="wjzf" id="wjzf" cssStyle="width:40%" ></s:textfield>
	      		</span>
	      	</td>
	      </tr>
	      <tr style="display: none;">
	      	<th width="15%"><span class="red">*</span>单行选项个数</th>
	      	<td>
	      		<s:textfield maxlength="2" name="dags" id="dags" cssStyle="width:40%" ></s:textfield>
	      	</td>
	      	<th></th>
	      	<td>
	      	</td>
	      </tr>
	    	<tr>
	        	<th colspan="1"><span>卷首语</span></th>
	    		<td colspan="3">
	    			<s:textarea name="jsy" id="jsy" style="width:100%;height:130px" maxlength="250"></s:textarea>
	    		</td>
	        </tr>
	    	<tr>
	        	<th colspan="1"><span>卷尾语</span></th>
	    		<td colspan="3">
	    			<s:textarea name="jwy" id="jwy" style="width:100%;height:130px" maxlength="250"></s:textarea>
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