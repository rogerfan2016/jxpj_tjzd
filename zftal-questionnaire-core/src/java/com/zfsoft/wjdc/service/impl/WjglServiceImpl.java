package com.zfsoft.wjdc.service.impl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.common.service.BaseServiceImpl;
import com.zfsoft.wjdc.dao.daointerface.IWjglDao;
import com.zfsoft.wjdc.dao.entites.WjglModel;
import com.zfsoft.wjdc.service.svcinterface.IWjglService;

public class WjglServiceImpl extends BaseServiceImpl<WjglModel, IWjglDao> implements IWjglService {
	
	/**删除问卷信息*/
	public String scWjxx(String wjid) throws Exception {
		String msg=null;
		WjglModel model=dao.getModel(wjid);
		if("草稿".equals(model.getWjzt())){
			HashMap wjxgrs=dao.getWjxgrs(model);
			String ydjrs=wjxgrs.get("YDJRS").toString();
			if("0".equals(ydjrs)){
				int count=dao.delete(wjid);
				if(count==1){
					dao.deleteWjSt(wjid);
					dao.deleteWjStXx(wjid);
					msg="删除成功！";
				}else{
					msg="删除失败！";
				}
			}else{
				msg="问卷已有"+ydjrs+"人答卷，不可以进行删除！";
			}
		}else{
			msg="问卷处于\""+model.getWjzt()+"\"状态，只有草稿状态才可以删除！";
		}
		return msg;
	}
	
	/**更新问卷状态*/
	public boolean updateWjzt(WjglModel model) throws Exception{
		int res = dao.updateWjzt(model);
		return res==1?true:false;
	}
	
	/**获取问卷功能约束列表*/
	public List<HashMap<String,String>> getWjgnysList(WjglModel model) throws Exception{
		return dao.getWjgnysList(model);
	}
	
	/**更新问卷功能约束*/
	public boolean updateWjgnys(WjglModel model) throws Exception{
		int res1=dao.deleteWjgnys(model);
		int res2=1;
		String[] ysgndms=model.getYsgndms();
		if(ysgndms!=null&&ysgndms.length>0){
			res2=dao.insertWjgnys(model);
		}
		return res1>-1&&res2>0;
	}

}
