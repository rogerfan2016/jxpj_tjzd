package com.zfsoft.wjdc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.zfsoft.common.factory.SessionFactory;
import com.zfsoft.common.log.User;

/**
 * 
 * @author ChenMinming
 * @date 2015-3-1
 * @version V1.0.0
 */
public class WjdcMobileInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -3722179973134636629L;
	Log log = LogFactory.getLog(WjdcMobileInterceptor.class);
	/**
	 *@see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		User user = SessionFactory.getUser();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response =  ServletActionContext.getResponse();
		if(user == null){
			response.sendRedirect("/"+request.getContextPath()+"/wjdc_mobile/login_timeout.html");
			return null;
		}
	    return invocation.invoke(); 
	}  
}
