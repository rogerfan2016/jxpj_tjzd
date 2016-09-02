<%@page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="code" uri="/WEB-INF/code.tld" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

  <head>
    <%@include file="/commons/hrm/head.ini" %>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/hrm/code.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/hrm/infoclass/property.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/hrm/select.js"></script>
    <script type="text/javascript"> 
      var fixedWin = true;

      function fixedWindow(res) {
        // 固定
        if (res) {
          $("#mesbox_sel").hide();
          $("#message_list2").show();
          $("#mesbox_title").attr("style","background-image:none!important");
          $(".prompt").hide();
        // 取消固定
        } else {
          $("#message_list2").hide();
          $(".datetitle_01 li").attr("class","ico_xl");
          $(".prompt").show();
        }
        
        $("input[name='fixedWin']").val(res);
        fixedWin = res;
        
        return false;
      }

      $(function() {

        $(".datetitle_01 li").hover(function() {
          if(fixedWin) return false;
          
          $(this).parent().next().show();
          $(this).attr("class","sel_dropdown");
        }, function() {
          if (fixedWin) {
            return false;
          }
          
          $(this).parent().next().hide();
          $(".datetitle_01 li").attr("class","ico_xl");
        });
        
        $("#mesbox_sel").hover(function() {
          $("#mesbox_sel").show();
          $(".datetitle_01 li").attr("class","sel_dropdown");
        }, function() {
          $("#mesbox_sel").hide();
          $(".datetitle_01 li").attr("class","ico_xl");
          if(fixedWin) $(".datetitle_01 li").removeClass("ico_xl");;
        });
        
        //固定窗口
        $("#Fixed_win").click(function() {
          fixedWindow(true);
        });
          
        //取消固定窗口
        $(".ico_close03").click(function() {
          fixedWindow(false);
        });

        //关闭提示信息
        $(".close").click(function() {
          $(".prompt").hide();
        })
        
        var res = false;
        
        //信息类选择
        $(".mes_list_con ul li").click(function() {
          if( $(this).attr("name") == null ) return;
          if( res ) return;
          
          $("#classId").val( $(this).attr("name") );
          $("form:first").submit();
        });

      });
      
      var current = null;
      
      function currentF(name){
        if ( name == null || name == "" || name == current ) {
          return false;
        }

        
        $("li[name='" + current + "']").removeClass("current");
        current = name;
        $("input[name='classId']").val(name);
        
        $("li[name='" + name + "']").attr("class", "current");
      }
    </script>
  </head>

  <body>
    <form action="<%=request.getContextPath() %>/basedata/basedata_setView.html" method="post">
      <input type="hidden" id="classId" name="classId" value="${classId }" />
      <input type="hidden" name="catalogId" value="${catalogId }" />
      <input type="hidden" name="fixedWin" value="${fixedWin }" />
    </form>
        
    <div class="selectbox" style="position:relative; z-index: 1;" >
      <ul class="datetitle_01">
        <li class="">信息类列表</li>
      </ul>
      <div class="mesbox_sel" id="mesbox_sel" style="display:none;">
        <c:forEach items="${catalogs }" var="catalog">
        <div class="mes_list_con">
          <h2 name="${catalog.guid }"><a href="#">${catalog.name }</a></h2>
          <ul>
            <c:forEach items="${catalog.classes}" var="clasz">
            <li name="${clasz.guid }" editable="${clasz.typeInfo.editable }" xxly="${clasz.xxly}"><a href="#">${clasz.name }</a></li>
            </c:forEach>
          </ul>
          <div class="clear"></div>
        </div>
        </c:forEach>
        
        <h1>
            <a href="#" id="Fixed_win" class="name" title="固定目录窗口">固定窗口</a>
        </h1>
      </div>
      
      <div class="mes_list" id="message_list2" style="display: block;">
        <a class="ico_close03" onmouseover="this.className='ico_close02'" onmouseout="this.className='ico_close03'" href="#" title="取消固定"></a>
        <c:forEach items="${catalogs }" var="catalog">
        <div class="mes_list_con">
          <h2 name="${catalog.guid}"><a href="#">${catalog.name }</a></h2>
          <ul>
            <c:forEach items="${catalog.classes}" var="clasz">
            <li name="${clasz.guid }" editable="${clasz.typeInfo.editable }" xxly="${clasz.xxly}"><a href="#">${clasz.name }</a></li>
            </c:forEach>
          </ul>
          <div class="clear"></div>
        </div>
        </c:forEach>
      </div>
    </div>

    <div class="prompt" style="display: none; z-index: 0;">
      <h3><span>操作提示：</span></h3>
      <p>鼠标移动至"信息类列表"可以选择/切换信息类</p>
      <a class="close" title="隐藏"></a>
    </div>
    
    <jsp:include page="synchronize_property.jsp" flush="true"></jsp:include>
    
    <script type="text/javascript">
      currentF("${classId}");
      
      <c:if test="${fixedWin eq false}">
        fixedWindow( false );
      </c:if>
    </script>
  </body>
</html>