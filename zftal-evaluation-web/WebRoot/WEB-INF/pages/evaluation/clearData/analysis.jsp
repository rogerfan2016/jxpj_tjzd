<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@page import="com.zfsoft.hrm.config.ICodeConstants"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="ct" uri="/custom-code"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <script type="text/javascript">
      $(function() {
        $("#cancel").click(function(){
          divClose();
          return false;
        });
      });
    </script>
  </head>
  <body>
      <div class="formbox">
        <!--标题start-->
        <h3 class="datetitle_01">
          <span>评价分析详情</span>
        </h3>
        <!--标题end-->
        <div class="con_overlfow">
          <table width="100%" class="dateline tablenowrap">
            <thead id="list_head">
              <tr>
                <td>职工号</td>
              	<td>教师姓名</td>
              	<td>评价总数</td>
            	<td>有效数量</td>
            	<td>清洗数量</td>
            	<td>方差</td>
            	<td>评分最大值</td>
            	<td>评分最小值</td>
              </tr>
            </thead>
            <tbody id="list_body">
              	<tr>
	            	<td>${model.jszgh }</td>
	              	<td><ct:PersonParse code="${model.jszgh }"/></td>
	            	<td>${model.pjzs }</td>
	            	<td>${model.yxsl }</td>
	            	<td><a style="color:#074695" href="<%=request.getContextPath()%>/evaluation/clearData_searchStudentsEvaluationList.html?query.sfqx=1&query.qxtj=${query.qxtj }&query.xn=${query.xn }&query.xq=${query.xq }&query.jszgh=${query.jszgh }"><b>${qxsl }</b></a></td>
	            	<td>${model.fc }</td>
	            	<td>${model.zdz }</td>
	            	<td>${model.zxz }</td>
            	</tr>
            </tbody>
          </table>
        </div>
      </div>
      <div class="tab">
        <table width="100%"  border="0" class="formlist" cellpadding="0" cellspacing="0">
          <tfoot>
            <tr>
              <td colspan="16">
                <div class="btn">
                  <button type="button" id="cancel">关 闭</button>
                </div>
              </td>
            </tr>
          </tfoot>
        </table>
      </div>
  </body>
</html>