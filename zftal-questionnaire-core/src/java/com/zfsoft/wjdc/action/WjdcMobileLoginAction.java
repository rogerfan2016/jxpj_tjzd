package com.zfsoft.wjdc.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.zfsoft.common.action.BaseAction;
import com.zfsoft.common.factory.SessionFactory;
import com.zfsoft.common.log.Role;
import com.zfsoft.common.log.User;
import com.zfsoft.common.service.BaseLog;
import com.zfsoft.dao.entities.LoginModel;
import com.zfsoft.dao.page.PageList;
import com.zfsoft.hrm.message.entity.Message;
import com.zfsoft.hrm.message.query.MessageQuery;
import com.zfsoft.hrm.message.service.svcinterface.IMessageService;
import com.zfsoft.hrm.pendingAffair.entities.PendingAffairInfo;
import com.zfsoft.hrm.pendingAffair.service.svcinterface.IPendingAffairService;
import com.zfsoft.service.impl.LogEngineImpl;
import com.zfsoft.service.svcinterface.ILoginService;
import com.zfsoft.util.base.StringUtil;
import com.zfsoft.wjdc.service.svcinterface.IMobileIndexService;

/**
 * 
 * @author ChenMinming
 * @date 2015-3-1
 * @version V1.0.0
 */
public class WjdcMobileLoginAction extends BaseAction implements ModelDriven<LoginModel> {

    private static final long serialVersionUID = 1L;
    private LoginModel model = new LoginModel();
    private ILoginService loginService;

    private IPendingAffairService pendingAffairService;
    private IMessageService messageService;
    private IMobileIndexService mobileIndexService;

    private BaseLog baseLog = LogEngineImpl.getInstance();

    public void initialize(){
        //TODO 做一些初始化操作（比如从缓存里读取一些信息），每个方法都调用
    }

    /**
	 * 会话失效
	 * @return
	 * @throws Exception
	 */
	public String timeout() throws Exception {
		getValueStack().set("tzurl", "wjdc_mobile/login_loginpage.html");
		return "timeout";
	}
	
	public String initMenu(){
		getValueStack().set("yhlx", getUser().getYhlx());
		User user = SessionFactory.getUser();
		// 我的消息数
		MessageQuery query = new MessageQuery();
		query.setStatus("0");
		query.setReceiver(user.getYhm());
		query.setPerPageSize(7);
		PageList<Message> pageList = messageService.getPagingList(query);
		int msgNum = pageList.getPaginator().getItems();
		if(msgNum > 0){
			getValueStack().set("msgNum", " <span class='am-badge am-badge-warning am-round'>"+ msgNum +"</span>");
		}else{
			getValueStack().set("msgNum", "");
		}
		// 评教数量
		int pjNum = mobileIndexService.getEvaluateCount(getUser().getYhm(), getUser().getYhlx());
		if(pjNum > 0){
			getValueStack().set("pjNum", " <span class='am-badge am-badge-warning am-round'>"+pjNum+"</span>");
		}else{
			getValueStack().set("pjNum", "");
		}
		// 如果登陆的是老师就取下面的数量
		List<PendingAffairInfo> list = null;
		if("teacher".equals(getUser().getYhlx())){
			// 待办事宜数
			list = pendingAffairService.getListByUser(user);
			list.addAll(pendingAffairService.getListByRoles(user));
			if(list.size() > 0){
				getValueStack().set("paNum", " <span class='am-badge am-badge-warning am-round'>"+list.size()+"</span>");
			}else{
				getValueStack().set("paNum", "");
			}		
			// 教学巡视数 xsNum
			int xsNum = mobileIndexService.getPatrolCount(user.getYhm(), "");
			if(xsNum > 0){
				getValueStack().set("xsNum", " <span class='am-badge am-badge-warning am-round'>"+xsNum+"</span>");
			}else{
				getValueStack().set("xsNum", "");
			}	
		}
		return "initMenu";
	}

    /**
     * 方法名: login
     * 方法描述: 登录
     * 修改时间：2011-12-20 上午10:49:38
     * 参数 @return
     * 返回类型 String
     *
     * @throws
     */
    public String login() {
    	 ValueStack vs = getValueStack();
         HttpSession session = getSession();
         try {
             User user = loginService.cxYhxx(model);
             if (StringUtil.isEmpty(user.getYhm())) {
                 vs.set("message", getText("login.info.failed"));
                 if ("0".equals(user.getSfqy())) {
                	 HashMap<Object, Object> map=new HashMap<Object, Object>();
                	 map.put("success", false);
                  	 map.put("message", "用户被锁定！请联系管理员"); 
                  	 this.getValueStack().set(DATA, map);
                     return DATA;
				 }
             } else {
                 //登录成功
                 session.setAttribute(USER_INFO_KEY, user);
                 //session.setAttribute(user.getYhm(), indexService.cxJsxxLb(user));
                 //初始化角色信息
                 this.initRoles(user);
                 
                 baseLog.login(user, getText("log.message.ywmc",
                         new String[]{"登陆系统", "xg_xtgl_yhb"}),
                         "系统管理", getText("log.message.czms",
                         new String[]{"登陆系统", "用户名", user.getYhm()}));
                 HashMap<Object, Object> map=new HashMap<Object, Object>();
             	map.put("success", true);
             	this.getValueStack().set(DATA, map);
                 return DATA;
             }
         } catch (Exception e) {
            //logException(e);
        	 HashMap<Object, Object> map=new HashMap<Object, Object>();
         	map.put("success", false);
         	map.put("message", "登陆用户名或密码错误！");
         	this.getValueStack().set(DATA, map);
             return DATA;
         }
         //来过后让session的验证码失效！防止Ajax重复提交
         HashMap<Object, Object> map=new HashMap<Object, Object>();
     	map.put("success", false);
     	map.put("message", "验证码不正确！"); 
     	this.getValueStack().set(DATA, map);
         return DATA;
    }

    /**
     * 方法名: logout
     * 方法描述: 注销
     * 修改时间：2011-12-20 上午10:49:50
     * 参数 @return
     * 返回类型 String
     *
     * @throws
     */
    public String logout() {
        HttpSession session = getSession();
        User user = getUser();
        if(null == user)
        {
        	return LOGIN;
        }
        baseLog.logout(user, getText("log.message.ywmc",
                new String[]{"登出系统", "xg_xtgl_yhb"}),
                "系统管理", getText("log.message.czms",
                new String[]{"登出系统", "用户名", user.getYhm()}));

        session.invalidate();
        return LOGIN;
    }

    /**
     * @throws IOException 
     * 方法名: login
     * 方法描述: 注销
     * 修改时间：2011-12-20 上午10:49:50
     * 参数 @return
     * 返回类型 String
     *
     * @throws
     */
    public String loginpage() throws IOException {
//        HttpSession session = getSession();
        User user = getUser();
        if(null == user)
        {
        	return LOGIN;
        }
//        baseLog.logout(user, getText("log.message.ywmc",
//                new String[]{"登出系统", "xg_xtgl_yhb"}),
//                "系统管理", getText("log.message.czms",
//                new String[]{"登出系统", "用户名", user.getYhm()}));
//
//        session.invalidate();
//        return LOGIN;
        //getResponse().sendRedirect(getRequest().getContextPath()+"/xtgl/index_initMenu.html");
		return "success";
    }

    /**
     * 获取验证码
     *
     * @return
     */
    public String yzm() {
        return "yzm";
    }

	/**
	 * 初始化角色信息
	 * @param user
	 */
    private void initRoles(User user){
    	//用户拥有的角色
    	List<Role> allRoles=user.getAllRoles();
    	
		if(allRoles==null||allRoles.size()==0){
			List<String> emptyList=Collections.emptyList();
			user.setJsdms(emptyList);
			getRequest().getSession().setAttribute(user.getYhm(), null);
			return;
		}else{
			List<String> jsdms=new ArrayList<String>();
			for(Role role:user.getAllRoles()){
				jsdms.add(role.getJsdm());
			}
			user.setJsdms(jsdms);
		}
    	getRequest().getSession().setAttribute(user.getYhm(), user.getJsdms());
    }
    
    public LoginModel getModel() {
        return model;
    }

    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

	/**
	 * @param model the model to set
	 */
	public void setModel(LoginModel model) {
		this.model = model;
	}

	/**
	 * @param pendingAffairService the pendingAffairService to set
	 */
	public void setPendingAffairService(IPendingAffairService pendingAffairService) {
		this.pendingAffairService = pendingAffairService;
	}

	/**
	 * @param messageService the messageService to set
	 */
	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
	}

	/**
	 * @param mobileIndexService the mobileIndexService to set
	 */
	public void setMobileIndexService(IMobileIndexService mobileIndexService) {
		this.mobileIndexService = mobileIndexService;
	}
	
}