package com.zfsoft.wjdc.service.svcinterface;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zfsoft.common.service.BaseService;
import com.zfsoft.wjdc.dao.entites.WjpzModel;

public interface IWjpzService extends BaseService<WjpzModel> {
	
	/**
	 * 获取功能类别配置的相关信息
	 * @param lxid
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,Object>> getGnlbPzxx(String lxid) throws Exception;
	
	/**
	 * 保存问卷配置信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveWjpz(HttpServletRequest request,WjpzModel model) throws Exception;
	
	

}
