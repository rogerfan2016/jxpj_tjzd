package com.zfsoft.wjdc.action;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.zfsoft.common.action.BaseAction;
import com.zfsoft.wjdc.dao.entites.WjpzModel;
import com.zfsoft.wjdc.dao.entites.WjpzSjylxModel;
import com.zfsoft.wjdc.service.svcinterface.IWjBaseService;
import com.zfsoft.wjdc.service.svcinterface.IWjpzService;

public class WjpzAction extends BaseAction implements ModelDriven<WjpzModel>{
	
	private IWjpzService service;
	private IWjBaseService baseService;
	private WjpzModel model=new WjpzModel();
	
	/**
	 * 问卷配置
	 * @return
	 */
	public String wjpz(){
		try {
			List<WjpzSjylxModel> wjpzsjylxList=baseService.getWjpzSjylxList();
			getValueStack().set("wjlxList",wjpzsjylxList);
			if((model.getLxid()==null||"".equals(model.getLxid()))
					&&wjpzsjylxList.size()>0){
				model.setLxid(wjpzsjylxList.get(0).getLxid());
			}
			getValueStack().set("gnlbpzList",service.getGnlbPzxx(model.getLxid()));
			
		} catch (Exception e) {
			logException(e);
		}
		return "wjpz";
	}
	
	/**
	 * 保存问卷配置信息
	 * @return
	 */
	public String saveWjpz(){
		
		try {
			service.saveWjpz(getRequest(), model);
		} catch (Exception e) {
			logException(e);
		}
		return wjpz();
	}

	public WjpzModel getModel() {
		return model;
	}

	public IWjpzService getService() {
		return service;
	}

	public void setService(IWjpzService service) {
		this.service = service;
	}

	public void setModel(WjpzModel model) {
		this.model = model;
	}

	public IWjBaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(IWjBaseService baseService) {
		this.baseService = baseService;
	}

}
