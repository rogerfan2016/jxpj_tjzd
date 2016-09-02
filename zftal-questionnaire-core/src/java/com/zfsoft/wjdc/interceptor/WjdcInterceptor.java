package com.zfsoft.wjdc.interceptor;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.ValueStack;
import com.zfsoft.common.factory.ServiceFactory;
import com.zfsoft.common.factory.SessionFactory;
import com.zfsoft.common.log.User;
import com.zfsoft.wjdc.dao.entites.WjglModel;
import com.zfsoft.wjdc.dao.entites.WjpzSjylxModel;
import com.zfsoft.wjdc.service.svcinterface.IWjBaseService;

/**
 * 问卷调查拦截器,主要功能是处理问卷约束的功能路径
 */
public class WjdcInterceptor extends AbstractInterceptor {
	
	private static String wjysgnPaths="";
	static{
		initWjysgnPath();	
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		ValueStack vs = ServletActionContext.getValueStack(request);
		String path=ServletActionContext.getRequest().getServletPath();
		
		User user = SessionFactory.getUser();
		
		if(wjysgnPaths.contains(path)){
			if(user!=null){
				WjpzSjylxModel sjy=((IWjBaseService)ServiceFactory.getService("wjBaseService")).getWjpzSjylxBySessionLxidModel(user.getYhlx());
				if(sjy!=null){
					HashMap<String, String> param=new HashMap<String, String>();
					param.put("ysgnpath", path);
					param.put("zjz", user.getYhm());
					param.put("lxid", sjy.getLxid());
					
					List<WjglModel> wjList=((IWjBaseService)ServiceFactory.getService("wjBaseService")).getWjysgnPathWjList(param);
					if(wjList.size()>0){
						vs.set("path", path);
						vs.set("tzurl", "/zfxg/wjdc/stgl_yhdj.html?wjModel.wjid="+wjList.get(0).getWjid());
				        return "wjdctz";
					}
				}
			}
		}
		
		return invocation.invoke();
	}
	
	public static void initWjysgnPath(){
		try {
			String paths=((IWjBaseService)ServiceFactory.getService("wjBaseService")).getWjYsgnPath();
			if(paths!=null){
				wjysgnPaths=paths;
			}else{
				wjysgnPaths="";
			}
			System.out.println("#######################:"+wjysgnPaths);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
