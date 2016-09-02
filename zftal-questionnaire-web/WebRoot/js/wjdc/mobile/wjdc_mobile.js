$(function() {
    var wjid=$("#wjid").val();
    var djrid=$("#djrid").val();
	$.post("wjdc_mobile/stgl_getWjStxxList.html","wjModel.wjid="+wjid,function(data){
		showWJNR(data);
        bindEven();
        getWjdaxx(wjid,djrid);
    }, "json");
});
function showWJNR(data){
	var html="";
    var no=1;
    var xxMap={};
    for(var i=0;i<data.xxxxs.length;i++){
        var l=0;
        if(xxMap[data.xxxxs[i].stid]!=null){
            l=xxMap[data.xxxxs[i].stid].length;
        }else{
            xxMap[data.xxxxs[i].stid]=new Array();
        }
        xxMap[data.xxxxs[i].stid][l]=data.xxxxs[i];
    }
    for(var i=0;i<data.tmxxs.length;i++){
        var stHTL="<div class='field ui-field-contain' id='"+data.tmxxs[i].stid+"' style='padding: 4px 4px 5px; border-style: solid; border-width: 2px 2px 1px; border-color: rgb(249, 249, 249) rgb(249, 249, 249) rgb(239, 239, 239);' ";
        if(data.tmxxs[i].sfbd='true'){
            stHTL+=" req='1' ";
        }
        var checkSize="";
        if(data.tmxxs[i].xxkzdxzs!=null){
            checkSize=" checksize='"+data.tmxxs[i].xxkzdxzs+"' ";
        } 
        
        stHTL+=" topic='"+data.tmxxs[i].xssx+"' data-role='fieldcontain' type='"+data.tmxxs[i].stlx+"' "+checkSize+" >";
        if(data.tmxxs[i].stlx=="STDL"){
            stHTL+="<div class='field-label' style='font-size:18px !important'>"+data.tmxxs[i].stmc+"</div></div>";
        }else{
            stHTL+="<div class='field-label'>";
            if("${wjModel.autoaddstbh}"=="true"){
                stHTL+=no+".";no++;
            }
            stHTL+=data.tmxxs[i].stmc;
            if(data.tmxxs[i].sfbd='true'){
                stHTL+="<span class='req'>*</span>"
            }
            stHTL+="</div>";
            //单选或者单选组合
            if(data.tmxxs[i].stlx=="DX"||data.tmxxs[i].stlx=="DXZH"){
                stHTL+="<div class='ui-controlgroup'>";
                var xxs=xxMap[data.tmxxs[i].stid];
                for(var j=0;j<xxs.length;j++){
                    stHTL+="<div class='ui-radio'><span class='jqradiowrapper'><input value="+xxs[j].xxid+" id="+xxs[j].xxid+" name='xxname_"+xxs[j].stid+"' style='display:none;' type='radio'><a class='jqradio' href='javascript:;'></a></span>"
                    +"<label for='"+xxs[j].xxid+"'>"+xxs[j].xxmc+"</label>";
                    if(data.tmxxs[i].stlx=="DXZH" && xxs.length==j+1){
                        stHTL+="<div class='ui-text'><input id='textid_"+data.tmxxs[i].stid+"' name='textname_"+data.tmxxs[i].stid+"' type='text'></div>";
                    }
                    stHTL+="</div>";
                }
                stHTL+="</div>";
            }
            //多选或者多选组合
            else if(data.tmxxs[i].stlx=="DXS"||data.tmxxs[i].stlx=="DXSZH"){
                stHTL+="<div class='ui-controlgroup'>";
                var xxs=xxMap[data.tmxxs[i].stid];
                for(var j=0;j<xxs.length;j++){
                    stHTL+="<div class='ui-checkbox'><span class='jqcheckwrapper'><input value="+xxs[j].xxid+" id="+xxs[j].xxid+" name='xxname_"+xxs[j].stid+"' style='display:none;' type='checkbox'><a class='jqcheck' href='javascript:;'></a></span>"
                    +"<label for='"+xxs[j].xxid+"'>"+xxs[j].xxmc+"</label>";
                    if(data.tmxxs[i].stlx=="DXSZH" && xxs.length==j+1){
                        stHTL+="<div class='ui-text'><input id='textid_"+data.tmxxs[i].stid+"' name='textname_"+data.tmxxs[i].stid+"' type='text'></div>";
                    }
                    stHTL+="</div>";
                }
                stHTL+="</div>";
            }
            //单行文本
            else if(data.tmxxs[i].stlx=="DHWB"){
                stHTL+="<div class='ui-input-text'>";
                stHTL+="<input id='textid_"+data.tmxxs[i].stid+"' name='textname_"+data.tmxxs[i].stid+"' type='text'>";
                stHTL+="</div>";
            }//多行文本
            else if(data.tmxxs[i].stlx=="DHWBS"){
                stHTL+="<div class='ui-input-text'>";
                stHTL+="<textarea id='textid_"+data.tmxxs[i].stid+"' rows='5' name='textname_"+data.tmxxs[i].stid+"' type='text'/>";
                stHTL+="</div>";
            }
            else{
                continue;
            }
            stHTL+="<div class='errorMessage'></div></div>";
        }
        html+=stHTL;
        }
    $("#wjview").html(html);

}
function bindEven(){
    $("div.ui-radio").click(function(){
        var check = $(this).find("input[type='radio']")[0].checked;
    	$(this).parent("div").find("a.jqradio").removeClass("jqchecked");
    	$(this).find("input[type='radio']")[0].checked=!check;
        $(this).parent("div").find(".focuschoice").removeClass("focuschoice");
        if(!check)
        {
            $(this).addClass("focuschoice");	
            $(this).find("a.jqradio").addClass("jqchecked");
        }
        //return false;
    });
    $("div.ui-checkbox").click(function(){
        var check = $(this).find("input[type='checkbox']")[0].checked;
        $(this).find("input[type='checkbox']")[0].checked=!check;
        if(check)
        {
            $(this).removeClass("focuschoice");
            $(this).find("a.jqcheck").removeClass("jqchecked");
        }else{
        	$(this).addClass("focuschoice");
            $(this).find("a.jqcheck").addClass("jqchecked");
        }
        //return false;
    });
    $("input[type='text']").focus(function(){
        $(this).parent("div").addClass("ui-focus");
        //return false;
    });
    $("input[type='text']").blur(function(){
    	$(this).parent("div").removeClass("ui-focus");
        return false;
    });

    $(".field:visible").click(function(){
    	showErrorDiv($(this),false,null);
        return false;
    });
}

function wjSubmitCheck(){
	if(validate()){
		var url="stgl_saveWjDa.html";
        document.forms[0].action=url;
        document.forms[0].submit();
    }else{
	}
}
function validate(){
    var check=true;
    var errDiv=null;
    $(".field:visible").each(function(){
        var d=$(this),a=validateQ(d);
        if(!a){
            check=false;
            if(errDiv==null)
            	errDiv=d;
        }
    });
    if(!check){
        if(errDiv){
            $("html, body").animate({scrollTop:$(errDiv).offset().top},600);}
        }else{}
    return check;
}

function validateQ(obj){
	showErrorDiv(obj,false,null);
    if($(obj)==null)return true;
    var msg="";
    var type=$(obj).attr("type");
    var stid = $(obj).attr("id");
    var error=false;
    if(type=="STDL")return true;
    //必填判断
    if($(obj).attr("req")=='1'&&!error){
        msg="此题是必答题";
        if(type=='DX'||type=='DXS'||type=='DXZH'||type=='DXSZH'){
            if($("input[name='xxname_"+stid+"']").filter(":checked").length==0){
                error=true;
            }
        }else{
        	var text = $("#textid_"+stid).val();
            if(text.replace(/\s*/g,"")==""){
            	error=true;
            }
        }
    }
    //多选最大选项判断
    if($(obj).attr("checkSize")!=null&&!error){
    	msg="此题最多只允许选择"+$(obj).attr("checkSize")+"项";
    	if($("input[name='xxname_"+stid+"']").filter(":checked").length>$(obj).attr("checkSize")){
            error=true;
        }
    }
    //组合题型最后一个选项选中时候需要判断文本框填写情况
    if((type=='DXZH'||type=='DXSZH')&&!error){
        msg="选择最后一项时文本内容不能为空";
        var xx= $("input[name='xxname_"+stid+"']");
        var select= xx.filter(":checked");
        if(xx[xx.length-1].id==select[select.length-1].id){
        	var text = $("#textid_"+stid).val();
            if(text.replace(/\s*/g,"")==""){
                error=true;
            }
        }
    }
    showErrorDiv(obj,error,msg);
    return !error;
}

function showErrorDiv(obj,error,errorMsg){
	$(obj).attr("style","padding: 4px 4px 5px; border-style: solid; border-width: 2px 2px 1px; border-color: rgb(249, 249, 249) rgb(249, 249, 249) rgb(239, 239, 239); ");
	$(obj).find(".errorMessage").html("");
    if(error){
    	$(obj).attr("style","padding: 4px; border: 2px solid rgb(255, 153, 0);");
    	$(obj).find(".errorMessage").html(errorMsg);
    }
}

/**获取问卷答案信息，自动加载使用，并显示加载出来*/
function getWjdaxx(wjid,djrid){
    var date=new Date();
    if(djrid==null){
        return false;
    }
    $.post("wjdc_mobile/stgl_getWjDaList.html","wjModel.wjid="+wjid+"&wjModel.djrid="+djrid,function(data){
        for(var i=0;i<data.length;i++){
            if(data[i].txnr!=null&&data[i].txnr!=''&&$("#textid_"+data[i].stid)!=null)
                $("#textid_"+data[i].stid).val(data[i].txnr);
            if(data[i].xxid!=null&&data[i].xxid!=0){
                var obj=$("#"+data[i].xxid).parent("span").parent("div");
                $(obj).addClass("focuschoice");    
                $(obj).find("input[id='"+data[i].xxid+"']")[0].checked=true;
                $("#"+data[i].xxid).parent("span").find("a").addClass("jqchecked");
            }
        }
    }, "json");
}