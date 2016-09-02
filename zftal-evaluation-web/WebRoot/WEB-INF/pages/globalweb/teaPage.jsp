<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
	<%@ include file="/WEB-INF/pages/globalweb/head/pagehead_v4.ini"%>
	<script type="text/javascript">
	$(document).ready(function(e) {
        $(".piclink_02x ul li").hover(function(){
			if($(this).attr("class")	==	"piclink_02x_last"){
				$(this).css("left","583px");
			}else if($(this).attr("class")	==	"piclink_02x_last1"){
				$(this).css("left","425px");
			}
			$(this).children(".piclink_02x_1").css("display","block");
			$(this).css("z-index","3")
		},function(){
			if($(this).attr("class")	==	"piclink_02x_last"){
				$(this).css("left","809px");
			}else if($(this).attr("class")	==	"piclink_02x_last1"){
				$(this).css("left","651px");
			}
			
			$(this).children(".piclink_02x_1").css("display","none");
			$(this).css("z-index","2")
		})
		
        $(".piclink_02xx ul li").hover(function(){
			if($(this).attr("class")	==	"piclink_02xx_last"){
				$(this).css("left","584px");
			}else if($(this).attr("class")	==	"piclink_02xx_last1"){
				$(this).css("left","421px");
			}
			$(this).children(".piclink_02xx_1").css("display","block");
			$(this).css("z-index","3")
		},function(){
			if($(this).attr("class")	==	"piclink_02xx_last"){
				$(this).css("left","810px");
			}else if($(this).attr("class")	==	"piclink_02xx_last1"){
				$(this).css("left","647px");
			}
			$(this).children(".piclink_02xx_1").css("display","none");
			$(this).css("z-index","2")
		})
		
		$(".piclink_02x_2 .but button").click(function(){
			$(this).parent().parent().parent().css("display","none");
			$(this).parent().parent().parent().parent().css("z-index","2")
		})
		$(".piclink_02xx_2 .but button").click(function(){
			$(this).parent().parent().parent().css("display","none");
			$(this).parent().parent().parent().parent().css("z-index","2")
		})
    });
    
	$(function(){
		loadPendingAffair();
		//loadNotice();
		loadFileDown();
		//setInterval("loadMessage()",60*1000);
		
		$("a[id^='usual_']").click(function(){
			quickMenu(this);
		});
	});

	// 通知公告
	function loadNotice(){
		var divContent = $("div#notice");
		$("#windown-content").unbind("ajaxStart");
		$.post(_path+'/message/noticeView_listData.html','',function(data){
			if(data.success){
				createNoticeList(data.result,divContent);
			}
		},"json");
	};
	
	// 待办事宜
	function loadPendingAffair(){
		var divContent = $("div#todo");
		$("#windown-content").unbind("ajaxStart");
		$.post(_path+'/pendingAffair/pendingAffair_indexList.html','',function(data){
			if(data.success){
				createPendingAffairList(data.result,divContent);
			}
		},"json");
	};
	
	function createPendingAffairList(data,div){
		var html = "<ul></ul>";
		var content = $(html);
		$.each(data,function(){
			var d = this;
			$(content).append("<li><a href='#' id='usual_"+d.menu+"'>"+d.affairName+"</a></li>");
			$(content).find("a:last").data("data",d);
		});
		if($(content).find("li").length==0){
			$(content).append("<li><span>暂无信息</span></li>");
		}

		$(content).find("a").click(function(){
			var data0 = $(this).data("data");
			var menu0 = data0.menu;
			if(data0.menu==null){
				alert("ID缺失");
				return false;
			}
			var topCode = menu0.substring(0,3);
			//$("[name='quickId']:hidden").val(menu0);
			//$("a[id='li_"+topCode+"']").click();
			quickMenu(this);
		});
		$(div).find("ul").remove();
		$(div).find("div.newscon").append($(content));
	}
	
	function createNoticeList(data,div){
		var html = "<ul></ul>";
		var content = $(html);
		$.each(data,function(){
			var d = this;
			if(d.top==1){
				$(content).append("<li><a href='#'><font color='red'>【置顶】</font>"+d.title+"</a><span class='time'>"+d.createTime.substr(0,10)+"</span></li>");
			}else{
				$(content).append("<li><a href='#'>"+d.title+"</a><span class='time'>"+d.createTime.substr(0,10)+"</span></li>");
			}
			$(content).find("a:last").data("data",d);
		});
		if($(content).find("li").length==0){
			$(content).append("<li><span>暂无信息</span></li>");
		}
		$(content).find("a").click(function(){
			var data = $(this).data("data");
			if(data.guid==null){
				alert("ID缺失");
				return false;
			}
			var callback = function(){
				//loadMessage();
			};
			showWindow("查看",720,400,"<%=request.getContextPath()%>/message/noticeView_info.html?notice.guid="+data.guid,callback);
		});
		$(div).find("ul").remove();
		$(div).find("div.newscon").append($(content));
	}
	
	function loadFileDown(){
		var divContent = $("div#download");
		$("#windown-content").unbind("ajaxStart");
		$.post(_path+'/message/file_viewList.html','',function(data){
			if(data.success){
				createFileList(data.result,divContent);
			}
		},"json");
	};
	
	function createFileList(data,div){
		var html = "<ul></ul>";
		var content = $(html);
		$.each(data,function(){
			var d = this;
			if(d.top==1){
				$(content).append("<li><a href='#'><font color='red'>【置顶】</font>"+d.name+"</a></li>");
			}else{
				$(content).append("<li><a href='#'>"+d.name+"</a></li>");
			}
			$(content).find("a:last").data("data",d);
		});
		if($(content).find("li").length==0){
			$(content).append("<li><span>暂无信息</span></li>");
		}
		$(content).find("a").click(function(){
			var data = $(this).data("data");
			if(data.guid==null){
				alert("ID缺失");
				return false;
			}
			var url = "<%=request.getContextPath() %>/file/attachement_download.html?guId=" + data.fileId;
			window.open( url, "400", "300", true);
		});
		$(div).find("ul").remove();
		$(div).append($(content));
	}
	
	function quickMenu(obj){
		var ids = $(obj).attr("id").split("_");
		var targetCode = ids[1];
		var topCode = targetCode.substring(0,3);
		$("[name='quickId']:hidden").val(targetCode);
		$("a[id='li_"+topCode+"']").click();
	}
	</script>
	<!--[if IE 6]> 
	<script src="../js/ie6comm.js"></script> 
	<script> 
	DD_belatedPNG.fix('img,.mainbody,.topframe,.mainframe,.botframe,.menu,.type_mainframe'); 
	</script> 
	<![endif]-->
</head>
<body>
<div class="mainbody">
	<!-- TOP菜单的加载 -->
	<div class="topframe" id="topframe" style="z-index:1;">
	     <jsp:include page="top.jsp" flush="true"></jsp:include>
	</div>
	
    <div class="mainframe" id="mainframe">
        <div class="newsnotice" id="todo">
   			<h3><span>待办事宜</span><a href="#" id="usual_N703010"></a></h3>
   			<div class="newscon">
   				<div class="newspic">
		          	<img src="<%=stylePath %>/images/newspic.jpg" width="235" height="176"/>
				</div>
				<ul></ul>
   			</div>
   		</div>

        <div class="remindtoday" id="download">
            <h3><span>文件下载</span><a href="#" id="usual_N704020"></a></h3>
            <ul></ul>
        </div>
        <div class="piclink_02xx">
        	<h3>教学质量评价流程</h3>
            <ul>
            	<li style="left:0px;">
                	<div class="piclink_02xx_0"><p class="title">评价设置</p></div>
                	<div class="piclink_02xx_1">
                    	<div class="piclink_02xx_2">
                            <p class="title1">* 评价设置</p>
                            <div class="con">评价批次设置、评价项目设置（评价指标、指标权重、计算类型）、评价环节设置</div>
                            <div class="but"><button>我知道了</button></div>
                        </div>
                    </div>
                </li>
            	<li style="left:162px;">
                	<div class="piclink_02xx_0"><p class="title">评价数据获取</p></div>
                	<div class="piclink_02xx_1">
                    	<div class="piclink_02xx_2">
                            <p class="title1">* 评价数据获取</p>
                            <div class="con">手工执行从教务系统获取相关的组织机构、教师信息、学生信息、教学任务等数据</div>
                            <div class="but"><button>我知道了</button></div>
                        </div>
                    </div>
                </li>
            	<li style="left:324px;">
                	<div class="piclink_02xx_0"><p class="title">新建评价项目</p></div>
                	<div class="piclink_02xx_1">
                    	<div class="piclink_02xx_2">
                            <p class="title1">* 新建评价项目</p>
                            <div class="con">针对授课教师，创建对应的评价项目及评价批次</div>
                            <div class="but"><button>我知道了</button></div>
                        </div>
                    </div>
                </li>
            	<li style="left:485px;">
                	<div class="piclink_02xx_0"><p class="title">发布评价</p></div>
                	<div class="piclink_02xx_1">
                    	<div class="piclink_02xx_2">
                            <p class="title1">* 发布评价</p>
                            <div class="con">针对授课教师对应的评价项目进行发布，发布后，学生可对授课教师进行评价</div>
                            <div class="but"><button>我知道了</button></div>
                        </div>
                    </div>
                </li>
            	<li style="left:647px;" class="piclink_02xx_last1">
                	<div class="piclink_02xx_1">
                    	<div class="piclink_02xx_2">
                            <p class="title1">* 学生评价</p>
                            <div class="con">学生对授课教师进行评价</div>
                            <div class="but"><button>我知道了</button></div>
                        </div>
                    </div> 
                    <div class="piclink_02xx_0"><p class="title">学生评价</p></div>               	
                </li>
            	<li style="left:810px;" class="piclink_02xx_last">
                	<div class="piclink_02xx_1">
                    	<div class="piclink_02xx_2">
                            <p class="title1">* 评价结果汇总</p>
                            <div class="con">评价管理员查询全校授课教师评价结果、查询每个授课教师考核明细情况</div>
                            <div class="but"><button>我知道了</button></div>
                        </div>
                    </div>
                    <div class="piclink_02xx_0"><p class="title">评价结果汇总</p></div>                	
                </li>                
            </ul>
        </div>
        
	<!-- 底部页面加载 -->
	<div class="botframe" id="botframe">
	  	<!-- 版权信息 -->
		<%@include file="/WEB-INF/pages/globalweb/bottom.jsp" %>
	</div>	
</div>

</body>
</html>
