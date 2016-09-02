package com.zfsoft.wjdc_xc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.common.spring.SpringHolder;
import com.zfsoft.dao.page.PageList;
import com.zfsoft.dao.page.Paginator;
import com.zfsoft.wjdc.dao.daointerface.IStglDao;
import com.zfsoft.wjdc.dao.daointerface.IWjffglDao;
import com.zfsoft.wjdc.dao.entites.WjffglModel;
import com.zfsoft.wjdc.dao.entites.WjglModel;
import com.zfsoft.wjdc_xc.dao.IInspectionTaskResultDao;
import com.zfsoft.wjdc_xc.entites.InspectionTaskResult;
import com.zfsoft.wjdc_xc.query.InspectionTaskResultQuery;
import com.zfsoft.wjdc_xc.service.IInspectionTaskResultService;

/**
 * 
 * @author ChenMinming
 * @date 2015-6-12
 * @version V1.0.0
 */
public class InspectionTaskResultServiceImpl implements IInspectionTaskResultService{
	
	private IInspectionTaskResultDao inspectionTaskResultDao;
	private IWjffglDao wjffglDao;

	@Override
	public InspectionTaskResult findById(String id) {
		// TODO Auto-generated method stub
		return inspectionTaskResultDao.findById(id);
	}

	@Override
	public PageList<InspectionTaskResult> getPagingList(
			InspectionTaskResultQuery query) {
		PageList<InspectionTaskResult> pageList = new PageList<InspectionTaskResult>();
		Paginator paginator = new Paginator();
		if(query!=null){
			paginator.setItemsPerPage(query.getPerPageSize());
			paginator.setPage((Integer)query.getToPage());
			
			paginator.setItems(inspectionTaskResultDao.getPagingInfoCount(query));
			pageList.setPaginator(paginator);
			
			if(paginator.getBeginIndex() <= paginator.getItems()){
				query.setStartRow(paginator.getBeginIndex());
				query.setEndRow(paginator.getEndIndex());
				List<InspectionTaskResult> list = inspectionTaskResultDao.getPagingInfoList(query);
				pageList.addAll(list);
			}
		}
		return pageList;
	}

	@Override
	public void insert(InspectionTaskResult inspectionTaskResult) {
		inspectionTaskResultDao.insert(inspectionTaskResult);
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<WjffglModel> list = new ArrayList<WjffglModel>();
		WjffglModel record = new WjffglModel();	
		record.setZjz(inspectionTaskResult.getId());
		record.setLxid("WJDC_XC");
		record.setWjid(inspectionTaskResult.getWjid());
		list.add(record);
		map.put("list", list);
		wjffglDao.batchInsertWjffdx(map);
	}

	@Override
	public int findCount(InspectionTaskResultQuery query) {
		return inspectionTaskResultDao.getPagingInfoCount(query);
	}
	
	@Override
	public void remove(InspectionTaskResult inspectionTaskResult) throws Exception {
		inspectionTaskResult = inspectionTaskResultDao.findById(inspectionTaskResult.getId());
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<WjffglModel> list = new ArrayList<WjffglModel>();
		map.put("lxid", "WJDC_XC");
		map.put("wjid", inspectionTaskResult.getWjid());
		WjffglModel record = new WjffglModel();	
		record.setZjz(inspectionTaskResult.getId());
		list.add(record);
		map.put("list", list);
		wjffglDao.batchDeleteWjffdx(map);
		
		IStglDao stglDao = SpringHolder.getBean("stglDao",IStglDao.class);
		WjglModel wjModel = new WjglModel();
		wjModel.setWjid(inspectionTaskResult.getWjid());
		wjModel.setDjrid(inspectionTaskResult.getId());
		stglDao.deleteWjDaxx(wjModel);
		
		inspectionTaskResultDao.remove(inspectionTaskResult);
	}

	@Override
	public void updateStatus(InspectionTaskResult inspectionTaskResult) {
		inspectionTaskResultDao.update(inspectionTaskResult);
	}

	@Override
	public int getFzSum(String id) {
		return inspectionTaskResultDao.getFzSum(id);
	}

	/**
	 * 设置
	 * @param inspectionTaskResultDao 
	 */
	public void setInspectionTaskResultDao(
			IInspectionTaskResultDao inspectionTaskResultDao) {
		this.inspectionTaskResultDao = inspectionTaskResultDao;
	}

	/**
	 * 设置
	 * @param wjffglDao 
	 */
	public void setWjffglDao(IWjffglDao wjffglDao) {
		this.wjffglDao = wjffglDao;
	}

	

}
