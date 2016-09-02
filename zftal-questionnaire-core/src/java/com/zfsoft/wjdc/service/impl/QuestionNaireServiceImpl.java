package com.zfsoft.wjdc.service.impl;

import java.util.List;
import java.util.Map;

import com.zfsoft.util.base.StringUtil;
import com.zfsoft.wjdc.dao.daointerface.IQuestionNaireDao;
import com.zfsoft.wjdc.dao.daointerface.IStglDao;
import com.zfsoft.wjdc.dao.daointerface.IWjglDao;
import com.zfsoft.wjdc.dao.entites.WjffglModel;
import com.zfsoft.wjdc.dao.entites.WjglModel;
import com.zfsoft.wjdc.service.svcinterface.IQuestionNaireService;
import com.zfsoft.wjdc.service.svcinterface.IWjffglService;

/**
 * 
 * @author ChenMinming
 * @date 2015-2-3
 * @version V1.0.0
 */
public class QuestionNaireServiceImpl implements IQuestionNaireService {
	
	private IWjglDao wjglDao;
	private IStglDao stglDao;
	private IWjffglService wjffglService;
	private IQuestionNaireDao questionNaireDao;

	@Override
	public boolean doDistribute(String modelId, String targetId,
			String targetDesc, String[] memberId, String lxid,String jsy,String jwy) throws Exception{
		if(targetId==null||StringUtil.isEmpty(targetId.trim())){
			return false;//目标问卷ID不能为空
		}
		WjglModel targetModel = wjglDao.getModel(targetId);
		//如果指定Id的目标问卷不存在 则根据模板复制创建
		if(targetModel==null){
			if(modelId==null||StringUtil.isEmpty(modelId.trim())){
				return false;//复制失败 未传入原模板ID
			}
			WjglModel sModel = wjglDao.getModel(modelId);
			if(sModel==null){
				return false;//复制失败 无法找到指定ID的问卷
			}
			sModel.setWjid(targetId);
			sModel.setModelId(modelId);
			sModel.setWjmc(targetDesc);
			sModel.setJsy(jsy);
			sModel.setJwy(jwy);
			doCopyWjModel(sModel);
		}
		WjffglModel wjffglModel = new WjffglModel();
		wjffglModel.setWjid(targetId);
		wjffglModel.setLxid(lxid);
		wjffglModel.setPkValue(memberId);
		wjffglService.bcWjffdx(wjffglModel);
		wjffglService.xgWjffdxbz(wjffglModel);
		return true;
	}
	
	
	private void doCopyWjModel(WjglModel wjglModel) throws Exception{
		wjglDao.delete(wjglModel.getWjid());
		wjglModel.setWjzt("运行");
		questionNaireDao.copyWjxx(wjglModel);
		stglDao.deleteStdlxx(wjglModel);
		questionNaireDao.copyStdlxx(wjglModel);
		stglDao.deleteStxx(wjglModel);
		questionNaireDao.copyStxx(wjglModel);
		stglDao.deleteXxxx(wjglModel);
		questionNaireDao.copyXxxx(wjglModel);
	}

	@Override
	public List<WjglModel> getPagedList(WjglModel query) {
		return wjglDao.getPagedList(query);
	}
	
	@Override
	public Map<String, Object> getModelSummer(String modelId) {
		WjglModel wjglModel = new WjglModel();
		wjglModel.setModelId(modelId);
		return questionNaireDao.findModelSummer(wjglModel);
	}

	/**
	 * 取得问卷
	 * @throws Exception 
	 */
	@Override
	public WjglModel getYhdjxx(String wjid, String memberId) throws Exception {
		WjglModel query = new WjglModel();
		query.setWjid(wjid);
		query.setDjrid(memberId);
		return stglDao.getYhdjxx(query);
	}

	/**
	 * 设置
	 * @param wjglDao 
	 */
	public void setWjglDao(IWjglDao wjglDao) {
		this.wjglDao = wjglDao;
	}


	/**
	 * 设置
	 * @param stglDao 
	 */
	public void setStglDao(IStglDao stglDao) {
		this.stglDao = stglDao;
	}


	/**
	 * 设置
	 * @param wjffglService 
	 */
	public void setWjffglService(IWjffglService wjffglService) {
		this.wjffglService = wjffglService;
	}


	/**
	 * 设置
	 * @param questionNaireDao 
	 */
	public void setQuestionNaireDao(IQuestionNaireDao questionNaireDao) {
		this.questionNaireDao = questionNaireDao;
	}


}
