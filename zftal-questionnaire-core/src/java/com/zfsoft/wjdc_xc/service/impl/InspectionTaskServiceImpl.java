package com.zfsoft.wjdc_xc.service.impl;

import java.util.List;
import java.util.Map;

import com.zfsoft.dao.page.PageList;
import com.zfsoft.dao.page.Paginator;
import com.zfsoft.hrm.normal.info.entity.OverallInfo;
import com.zfsoft.util.base.StringUtil;
import com.zfsoft.wjdc_xc.dao.IInspectionTaskDao;
import com.zfsoft.wjdc_xc.entites.InspectionTask;
import com.zfsoft.wjdc_xc.entites.InspectionTaskMember;
import com.zfsoft.wjdc_xc.query.InspectionSummerQuery;
import com.zfsoft.wjdc_xc.query.InspectionTaskQuery;
import com.zfsoft.wjdc_xc.service.IInspectionTaskService;

/**
 * 
 * @author ChenMinming
 * @date 2015-6-10
 * @version V1.0.0
 */
public class InspectionTaskServiceImpl implements IInspectionTaskService{
	
	private IInspectionTaskDao inspectionTaskDao;

	@Override
	public InspectionTask findById(String id) {
		InspectionTask t = inspectionTaskDao.findById(id);
		InspectionTaskMember mQuery = new InspectionTaskMember();
		mQuery.setTaskId(id);
		mQuery.setConfigType(t.getConfigType());
		t.setMemberList(inspectionTaskDao.getMemberList(mQuery));
		return t;
	}

	@Override
	public PageList<InspectionTask> getPagingList(InspectionTaskQuery query) {
		PageList<InspectionTask> pageList = new PageList<InspectionTask>();
		Paginator paginator = new Paginator();
		if(query!=null){
			paginator.setItemsPerPage(query.getPerPageSize());
			paginator.setPage((Integer)query.getToPage());
			
			paginator.setItems(inspectionTaskDao.getPagingInfoCount(query));
			pageList.setPaginator(paginator);
			
			if(paginator.getBeginIndex() <= paginator.getItems()){
				query.setStartRow(paginator.getBeginIndex());
				query.setEndRow(paginator.getEndIndex());
				List<InspectionTask> list = inspectionTaskDao.getPagingInfoList(query);
				pageList.addAll(list);
			}
		}
		return pageList;
	}
	
	@Override
	public PageList<Map<String, Object>> getTaskSummerPage(InspectionSummerQuery query) {
		PageList<Map<String, Object>> pageList = new PageList<Map<String, Object>>();
		Paginator paginator = new Paginator();
		if(query!=null){
			paginator.setItemsPerPage(query.getPerPageSize());
			paginator.setPage((Integer)query.getToPage());
			
			paginator.setItems(inspectionTaskDao.getTaskSummerPageCount(query));
			pageList.setPaginator(paginator);
			
			if(paginator.getBeginIndex() <= paginator.getItems()){
				query.setStartRow(paginator.getBeginIndex());
				query.setEndRow(paginator.getEndIndex());
				List<Map<String, Object>> list = inspectionTaskDao.getTaskSummerPage(query);
				pageList.addAll(list);
			}
		}
		return pageList;
	}
	
	@Override
	public List<Map<String, Object>> getTaskResultSummary(
			InspectionSummerQuery query) {
		return inspectionTaskDao.getTaskResultSummary(query);
	}

	@Override
	public PageList<Map<String, Object>> getResultPagingList(InspectionSummerQuery query) {
		PageList<Map<String, Object>> pageList = new PageList<Map<String, Object>>();
		Paginator paginator = new Paginator();
		if(query!=null){
			paginator.setItemsPerPage(query.getPerPageSize());
			paginator.setPage((Integer)query.getToPage());
			
			paginator.setItems(inspectionTaskDao.getXcdxSummerPageCount(query));
			pageList.setPaginator(paginator);
			
			if(paginator.getBeginIndex() <= paginator.getItems()){
				query.setStartRow(paginator.getBeginIndex());
				query.setEndRow(paginator.getEndIndex());
				List<Map<String, Object>> list = inspectionTaskDao.getXcdxSummerPage(query);
				pageList.addAll(list);
			}
		}
		return pageList;
	}

	@Override
	public void remove(InspectionTask inspectionTask) {
		InspectionTaskMember member = new InspectionTaskMember();
		member.setTaskId(inspectionTask.getId());
		inspectionTaskDao.removeMember(member);
		inspectionTaskDao.remove(inspectionTask.getId());
	}

	@Override
	public void save(InspectionTask inspectionTask,List<OverallInfo> overallInfoList) {
		if(StringUtil.isEmpty(inspectionTask.getId())){
			inspectionTaskDao.insert(inspectionTask);
			// 如果配置的选择人员不为空，即：CONFIG_KEY=personList
			if(inspectionTask.getMemberList()!=null){
				for (InspectionTaskMember m : inspectionTask.getMemberList()) {
					m.setTaskId(inspectionTask.getId());
					inspectionTaskDao.insertMember(m);
				}
			}
			// 如果选择人员为空，则通过关系SQL来查找评价人。即：CONFIG_KEY=sql_append
			else if(overallInfoList != null){
				InspectionTaskMember m = new InspectionTaskMember();
				for(OverallInfo info : overallInfoList){
					m.setTaskId(inspectionTask.getId());
					m.setGh(info.getUserId());
					inspectionTaskDao.insertMember(m);
				}
			}
		}else{
			inspectionTaskDao.update(inspectionTask);
			// 如果评价人是通过页面选择人员配置，即：CONFIG_KEY=personList
			if("personList".equals(inspectionTask.getConfigType())){
				InspectionTaskMember member = new InspectionTaskMember();
				member.setTaskId(inspectionTask.getId());
				List<InspectionTaskMember> list = inspectionTaskDao.getMemberList(member);
				List<InspectionTaskMember> mList = inspectionTask.getMemberList();
				for (int i = list.size()-1; i >=0; i--) {
					boolean found=false;
					for (int j = inspectionTask.getMemberList().size()-1; j >= 0; j--) {
						if(list.get(i).getGh().equals(mList.get(j).getGh())){
							list.remove(i);
							mList.remove(j);
							found=true;break;
						}
					}
					if(!found)
						inspectionTaskDao.removeMember(list.get(i));
				}
				for (InspectionTaskMember m : mList) {
					m.setTaskId(member.getTaskId());
					inspectionTaskDao.insertMember(m);
				}
			}			
		}
	}
	
	@Override
	public void update(InspectionTask inspectionTask) {
		inspectionTaskDao.update(inspectionTask);
	}

	@Override
	public List<InspectionTaskMember> getMemberList(InspectionTaskMember query) {
		return inspectionTaskDao.getMemberList(query);
	}

	/**
	 * 设置
	 * @param inspectionTaskDao 
	 */
	public void setInspectionTaskDao(IInspectionTaskDao inspectionTaskDao) {
		this.inspectionTaskDao = inspectionTaskDao;
	}

	@Override
	public PageList<InspectionTaskMember> getTaskMemberList(
			InspectionTaskQuery query) {
		PageList<InspectionTaskMember> pageList = new PageList<InspectionTaskMember>();
		Paginator paginator = new Paginator();
		if(query!=null){
			paginator.setItemsPerPage(query.getPerPageSize());
			paginator.setPage((Integer)query.getToPage());
			
			paginator.setItems(inspectionTaskDao.getTaskMemberCount(query));
			pageList.setPaginator(paginator);
			
			if(paginator.getBeginIndex() <= paginator.getItems()){
				query.setStartRow(paginator.getBeginIndex());
				query.setEndRow(paginator.getEndIndex());
				List<InspectionTaskMember> list = inspectionTaskDao.getTaskMemberList(query);
				pageList.addAll(list);
			}
		}
		return pageList;
	}
	
}
