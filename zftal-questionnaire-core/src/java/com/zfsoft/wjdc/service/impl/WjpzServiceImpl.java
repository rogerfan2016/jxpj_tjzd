package com.zfsoft.wjdc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zfsoft.common.service.BaseServiceImpl;
import com.zfsoft.wjdc.dao.daointerface.IWjpzDao;
import com.zfsoft.wjdc.dao.entites.WjpzModel;
import com.zfsoft.wjdc.dao.entites.WjpzSjylxModel;
import com.zfsoft.wjdc.service.svcinterface.IWjBaseService;
import com.zfsoft.wjdc.service.svcinterface.IWjpzService;

public class WjpzServiceImpl extends BaseServiceImpl<WjpzModel, IWjpzDao> implements IWjpzService {

	/**
	 * 获取功能类别配置的相关信息
	 * @param lxid
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,Object>> getGnlbPzxx(String lxid) throws Exception{
		WjpzModel para=new WjpzModel();
		para.setLxid(lxid);
		String[][] gnlbs=IWjBaseService.GNLBS;
		List<HashMap<String,Object>> gnlbList=new ArrayList<HashMap<String,Object>>();
		
		for(int i=0;i<gnlbs.length;i++){
			String gnlbdm=gnlbs[i][0];
			String gnlbmc=gnlbs[i][1];
			para.setGnlb(gnlbdm);
			HashMap<String, Object> gnlbMap=new HashMap<String, Object>();
			
			gnlbMap.put("gnlbdm", gnlbdm);
			gnlbMap.put("gnlbmc", gnlbmc);
			gnlbMap.put("cxjgpzList", dao.getGnlbCxjgPzAllList(para));
			gnlbMap.put("cxtjpzList", dao.getGnlbCxtjPzAllList(para));
			
			gnlbList.add(gnlbMap);
		}
		return gnlbList;
	}
	
	/**
	 * 保存问卷配置信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveWjpz(HttpServletRequest request,WjpzModel model) throws Exception{
		String[][] gnlbs=IWjBaseService.GNLBS;
		List<WjpzModel> cxtjlist=new ArrayList<WjpzModel>();//查询条件
		List<WjpzModel> cxjglist=new ArrayList<WjpzModel>();//查询结果
		String gnlb=null;
		for(int i=0;i<gnlbs.length;i++){
			gnlb=gnlbs[i][0];
			//查询条件
			String[] cxtj=request.getParameterValues("cxtj_"+gnlb);
			if(cxtj!=null&&cxtj.length>0){
				for(int j=0;j<cxtj.length;j++){
					WjpzModel cxtjModel=new WjpzModel();
					cxtjModel.setLxid(model.getLxid());
					cxtjModel.setGnlb(gnlb);
					cxtjModel.setZd(cxtj[j]);
					cxtjModel.setXssx(""+j);
					cxtjModel.setSfqy("1");
					
					cxtjlist.add(cxtjModel);
				}
			}
			//查询结果
			String[] cxjg=request.getParameterValues("cxjg_"+gnlb);
			if(cxjg!=null&&cxjg.length>0){
				for(int j=0;j<cxjg.length;j++){
					WjpzModel cxjgModel=new WjpzModel();
					cxjgModel.setLxid(model.getLxid());
					cxjgModel.setGnlb(gnlb);
					cxjgModel.setZd(cxjg[j]);
					cxjgModel.setXssx(""+j);
					cxjgModel.setSfqy("1");
					
					cxjglist.add(cxjgModel);
				}
			}
		}
		if(cxtjlist.size()>0){
			dao.deleteCxtjPzxx(model);
			dao.insertCxtjPzxx(cxtjlist);
		}
		if(cxjglist.size()>0){
			dao.deleteCxjgPzxx(model);
			dao.insertCxjgPzxx(cxjglist);
		}
		return true;
	}

}
