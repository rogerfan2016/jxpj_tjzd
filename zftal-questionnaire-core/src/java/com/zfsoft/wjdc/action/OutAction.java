package com.zfsoft.wjdc.action;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.zfsoft.common.action.BaseAction;
import com.zfsoft.common.factory.SessionFactory;
import com.zfsoft.common.log.User;
import com.zfsoft.common.service.BaseLog;
import com.zfsoft.dao.entities.LoginModel;
import com.zfsoft.service.impl.LogEngineImpl;
import com.zfsoft.service.svcinterface.IIndexService;
import com.zfsoft.service.svcinterface.ILoginService;
import com.zfsoft.util.encrypt.DBEncrypt;

public class OutAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private LoginModel model = new LoginModel();
	private ILoginService loginService;
	private IIndexService indexService;

	private BaseLog baseLog = LogEngineImpl.getInstance();

	private String yhm;

	public void initialize() {
		// TODO 做一些初始化操作（比如从缓存里读取一些信息），每个方法都调用
	}

	/**
	 * 方法名: login 方法描述: 登录 修改时间：2011-12-20 上午10:49:38 参数 @return 返回类型 String
	 * 
	 * @throws
	 */
	public String login() {
		HttpSession session = getSession();
		DBEncrypt dbEncrypt = new DBEncrypt();
		try {
			String yhm2 = dbEncrypt.dCode(yhm.getBytes());
			User user = SessionFactory.getUser();
			if (user != null) {
				if (user.getYhm().equals(yhm2)) {
					HashMap map = new HashMap();
					map.put("success", true);
					this.getValueStack().set(DATA, map);
					return "success";
				} else {
					session.invalidate();
					session = getSession();
				}
			}
			model.setYhm(yhm2);
			user = loginService.cxYhxx(model);
			// 登录成功
			session.setAttribute(USER_INFO_KEY, user);
			session.setAttribute(user.getYhm(), indexService.cxJsxxLb(user));
			baseLog.login(
					user,
					getText("log.message.ywmc", new String[] { "登陆系统",
							"xg_xtgl_yhb" }),
					"系统管理",
					getText("log.message.czms", new String[] { "登陆系统", "用户名",
							user.getYhm() }));
			HashMap map = new HashMap();
			map.put("success", true);
			this.getValueStack().set(DATA, map);
			return "success";
		} catch (Exception e) {
			this.getValueStack().set(DATA, "用户非法！");
			return DATA;
		}

	}

	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	public IIndexService getIndexService() {
		return indexService;
	}

	public void setIndexService(IIndexService indexService) {
		this.indexService = indexService;
	}

	public BaseLog getBaseLog() {
		return baseLog;
	}

	public void setBaseLog(BaseLog baseLog) {
		this.baseLog = baseLog;
	}

	public String getYhm() {
		return yhm;
	}

	public void setYhm(String yhm) {
		this.yhm = yhm;
	}

	public LoginModel getModel() {
		return model;
	}

}
