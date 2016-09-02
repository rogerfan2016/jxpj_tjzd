package com.zfsoft.wjdc.action;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.zfsoft.common.action.BaseAction;
import com.zfsoft.wjdc.dao.entites.WjpzSjylxModel;
import com.zfsoft.wjdc.dao.entites.YhdjglModel;
import com.zfsoft.wjdc.service.svcinterface.IWjBaseService;
import com.zfsoft.wjdc.service.svcinterface.IYhdjglService;

/**
 * 用户答卷管理
 */
public class YhdjglAction extends BaseAction implements ModelDriven<YhdjglModel>{
	
	private static final long serialVersionUID = 1L;
	private YhdjglModel model=new YhdjglModel();
	private IYhdjglService service;
	private IWjBaseService baseService;
	
	/**
	 * 查询用户答卷信息
	 */
	public String cxYhdjxx(){
			try {
				if(model.getSjyLxid()==null||"".equals(model.getSjyLxid())){
					WjpzSjylxModel sjyModel=baseService.getWjpzSjylxBySessionLxidModel(getUser().getYhlx());
					if(sjyModel!=null){
						model.setSjyLxid(sjyModel.getLxid());
					}
				}
				
				model.setDjrid(getUser().getYhm());
//				model.setSjyLxid("teacher");
//				model.setDjrid("admin");
				
				List<YhdjglModel> wjList=service.getPagedList(model);
				getValueStack().set("wjList", wjList);
			} catch (Exception e) {
				logException(e);
			}
		
		return SUCCESS;
	}

	public YhdjglModel getModel() {
		return model;
	}

	public IYhdjglService getService() {
		return service;
	}

	public void setService(IYhdjglService service) {
		this.service = service;
	}

	public IWjBaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(IWjBaseService baseService) {
		this.baseService = baseService;
	}
}
