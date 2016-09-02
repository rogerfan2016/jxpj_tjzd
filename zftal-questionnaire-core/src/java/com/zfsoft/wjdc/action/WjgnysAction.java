package com.zfsoft.wjdc.action;

import com.opensymphony.xwork2.ModelDriven;
import com.zfsoft.common.action.BaseAction;
import com.zfsoft.wjdc.dao.entites.WjgnysModel;
import com.zfsoft.wjdc.service.svcinterface.IWjBaseService;
import com.zfsoft.wjdc.service.svcinterface.IWjgnysService;

/**
 *问卷功能约束 
 */
public class WjgnysAction extends BaseAction implements ModelDriven<WjgnysModel>{
	
	private static final long serialVersionUID = 1L;
	private WjgnysModel model=new WjgnysModel();
	private IWjgnysService service;
	private IWjBaseService iWjBaseService;
	
	
	

	public WjgnysModel getModel() {
		return model;
	}


	public final IWjgnysService getService() {
		return service;
	}


	public final void setService(IWjgnysService service) {
		this.service = service;
	}


	public final IWjBaseService getiWjBaseService() {
		return iWjBaseService;
	}


	public final void setiWjBaseService(IWjBaseService iWjBaseService) {
		this.iWjBaseService = iWjBaseService;
	}
	
	

}
