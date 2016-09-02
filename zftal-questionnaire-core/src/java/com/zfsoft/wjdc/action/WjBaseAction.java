package com.zfsoft.wjdc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ModelDriven;
import com.zfsoft.common.action.BaseAction;
import com.zfsoft.wjdc.dao.entites.WjpzModel;
import com.zfsoft.wjdc.interceptor.WjdcInterceptor;
import com.zfsoft.wjdc.service.svcinterface.IWjBaseService;

/**
 * 问卷基础ACTION
 * @author Administrator
 *
 */
public class WjBaseAction extends BaseAction implements ModelDriven<WjpzModel> {

	private WjpzModel model = new WjpzModel();
	private IWjBaseService iWjBaseService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 查询条件字段列表值加载
	 * @return
	 */
	public String getCxzdOption() {
		try {
			List<HashMap<String, String>> list = iWjBaseService.getZdoptionList(model); 
			getValueStack().set(DATA, list);
		} catch (Exception e) {
			logException(e);
		}
		return DATA;
	}
	
	/**
	 * 检查问卷对象数据是否存在
	 * @return
	 */
	public String jcWjdxsj() {
		Map<String, String> map = iWjBaseService.formatMap(getRequest().getParameterMap());
		map.put("bm", getRequest().getParameter("bm"));
		try {
			boolean result = iWjBaseService.getWjdxbsjByTj(map);
			getValueStack().set(DATA, result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return DATA;
	}
	
	/**
	 * 初始化问卷约束功能路径
	 * @return
	 */
	public String initWjysgnPath(){
		WjdcInterceptor.initWjysgnPath();
		return null;
	}
	
	public WjpzModel getModel() {
		return model;
	}

	public IWjBaseService getiWjBaseService() {
		return iWjBaseService;
	}

	public void setiWjBaseService(IWjBaseService iWjBaseService) {
		this.iWjBaseService = iWjBaseService;
	}


}
