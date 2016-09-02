package com.zfsoft.evaluation.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.zfsoft.dao.page.PageList;
import com.zfsoft.dao.page.Paginator;
import com.zfsoft.evaluation.dao.daointerface.IClearDataDao;
import com.zfsoft.evaluation.entity.ClearDataEntity;
import com.zfsoft.evaluation.entity.ClearDataQuery;
import com.zfsoft.evaluation.service.IClearDataService;
import com.zfsoft.hrm.report.entity.ReportView;
import com.zfsoft.util.base.StringUtil;

/**
 * 
* <p>Title: ClearDataServiceImpl</p>
* <p>Description: </p>
* <p>Company: XXXX</p>
* @author    rogerfan
* @date      2016-1-8
 */
public class ClearDataServiceImpl implements IClearDataService {

    private IClearDataDao clearDataDao;

	/* （非 Javadoc）
	* <p>Title: getConditionList</p>
	* <p>Description: </p>
	* @param query
	* @return
	* @see com.zfsoft.evaluation.service.IClearDataService#getConditionList(com.zfsoft.evaluation.entity.ClearDataQuery)
	*/
	@Override
	public List<ClearDataEntity> getConditionList(ClearDataQuery query) {
		return clearDataDao.getConditionList(query);
	}

	/* （非 Javadoc）
	* <p>Title: insertCondition</p>
	* <p>Description: </p>
	* @param entity
	* @see com.zfsoft.evaluation.service.IClearDataService#insertCondition(com.zfsoft.evaluation.entity.ClearDataEntity)
	*/
	@Override
	public void insertCondition(ClearDataEntity entity) {
		clearDataDao.insertCondition(entity);
	}

	/* （非 Javadoc）
	* <p>Title: modifyCondition</p>
	* <p>Description: </p>
	* @param entity
	* @see com.zfsoft.evaluation.service.IClearDataService#modifyCondition(com.zfsoft.evaluation.entity.ClearDataEntity)
	*/
	@Override
	public void modifyCondition(ClearDataEntity entity) {
		clearDataDao.modifyCondition(entity);
		clearData(entity);
	}

	/* （非 Javadoc）
	* <p>Title: doClearData</p>
	* <p>Description: </p>
	* @param entity
	* @see com.zfsoft.evaluation.service.IClearDataService#doClearData(com.zfsoft.evaluation.entity.ClearDataEntity)
	*/
	@Override
	public void doClearData(ClearDataEntity entity) {
		clearData(entity);
	}

	private void clearData(ClearDataEntity entity) {
		// 如果是启用和停用则更新评分记录中的是否清洗状态
		if(StringUtil.isNotEmpty(entity.getZt())){
			ClearDataQuery query = new ClearDataQuery();
			query.setZt("1");
			query.setPerPageSize(Integer.MAX_VALUE);
			List<ClearDataEntity> list = clearDataDao.getConditionList(query);
			// 先清除标记
			entity.setSfqx("0");
			entity.setQxtj("");
			clearDataDao.modifyClearData(entity);
			// 再标记数据
			for(ClearDataEntity ent : list){
				entity.setSfqx("1");
				entity.setQxtj(ent.getTjmc() + "；");
				entity.setTjbds(ent.getTjbds());
				clearDataDao.modifyClearData(entity);
			}
		}
	}

	/* （非 Javadoc）
	* <p>Title: getPersonalWeightList</p>
	* <p>Description: </p>
	* @param query
	* @return
	* @see com.zfsoft.evaluation.service.IClearDataService#getPersonalWeightList(com.zfsoft.evaluation.entity.ClearDataQuery)
	*/
	@Override
	public PageList<ClearDataEntity> getPersonalWeightList(ClearDataQuery query) {
		PageList<ClearDataEntity> pageList = new PageList<ClearDataEntity>();
        Paginator paginator = new Paginator();
        if (query != null) {
        	query.setCondition(this.getCondition(query,false," and ( t.qxtj IS NULL or ( ", " and "));
            paginator.setItemsPerPage(query.getPerPageSize());
            paginator.setPage((Integer)query.getToPage());
            
            paginator.setItems(clearDataDao.getPersonalWeightCount(query));
            pageList.setPaginator(paginator);
            if(paginator.getBeginIndex() <= paginator.getItems()){
                query.setStartRow(paginator.getBeginIndex());
                query.setEndRow(paginator.getEndIndex());
                pageList.addAll(clearDataDao.getPersonalWeightList(query));
            }
        }
        return pageList;
	}

	/* （非 Javadoc）
	* <p>Title: getCourseWeightList</p>
	* <p>Description: </p>
	* @param query
	* @return
	* @see com.zfsoft.evaluation.service.IClearDataService#getCourseWeightList(com.zfsoft.evaluation.entity.ClearDataQuery)
	*/
	@Override
	public PageList<ClearDataEntity> getCourseWeightList(ClearDataQuery query) {
		PageList<ClearDataEntity> pageList = new PageList<ClearDataEntity>();
        Paginator paginator = new Paginator();
        if (query != null) {
        	query.setCondition(this.getCondition(query,false," and ( t.qxtj IS NULL or ( ", " and "));
            paginator.setItemsPerPage(query.getPerPageSize());
            paginator.setPage((Integer)query.getToPage());
            
            paginator.setItems(clearDataDao.getCourseWeightCount(query));
            pageList.setPaginator(paginator);
            if(paginator.getBeginIndex() <= paginator.getItems()){
                query.setStartRow(paginator.getBeginIndex());
                query.setEndRow(paginator.getEndIndex());
                pageList.addAll(clearDataDao.getCourseWeightList(query));
            }
        }
        return pageList;
	}

	/* （非 Javadoc）
	* <p>Title: getStudentsEvaluationList</p>
	* <p>Description: </p>
	* @param query
	* @return
	* @see com.zfsoft.evaluation.service.IClearDataService#getStudentsEvaluationList(com.zfsoft.evaluation.entity.ClearDataQuery)
	*/
	@Override
	public PageList<ClearDataEntity> getStudentsEvaluationList(
			ClearDataQuery query) {
		PageList<ClearDataEntity> pageList = new PageList<ClearDataEntity>();
        Paginator paginator = new Paginator();
        if (query != null) {
        	query.setCondition(this.getCondition(query,true," and ( ( ", " or "));
            paginator.setItemsPerPage(query.getPerPageSize());
            paginator.setPage((Integer)query.getToPage());
            
            paginator.setItems(clearDataDao.getStudentsEvaluationCount(query));
            pageList.setPaginator(paginator);
            if(paginator.getBeginIndex() <= paginator.getItems()){
                query.setStartRow(paginator.getBeginIndex());
                query.setEndRow(paginator.getEndIndex());
                pageList.addAll(clearDataDao.getStudentsEvaluationList(query));
            }
        }
        return pageList;
	}

	/* （非 Javadoc）
	* <p>Title: filingStudentsEvaluation</p>
	* <p>Description: </p>
	* @param entity
	* @see com.zfsoft.evaluation.service.IClearDataService#filingStudentsEvaluation(com.zfsoft.evaluation.entity.ClearDataEntity)
	*/
	@Override
	public void filingStudentsEvaluation(ClearDataEntity entity) {
		clearDataDao.filingStudentsEvaluation(entity);
	}

	/* （非 Javadoc）
	* <p>Title: deleteStudentsEvaluationFile</p>
	* <p>Description: </p>
	* @param entity
	* @see com.zfsoft.evaluation.service.IClearDataService#deleteStudentsEvaluationFile(com.zfsoft.evaluation.entity.ClearDataEntity)
	*/
	@Override
	public void deleteStudentsEvaluationFile(ClearDataEntity entity) {
		clearDataDao.deleteStudentsEvaluationFile(entity);
	}

	/* （非 Javadoc）
	* <p>Title: filingScoreRecord</p>
	* <p>Description: </p>
	* @param entity
	* @see com.zfsoft.evaluation.service.IClearDataService#filingScoreRecord(com.zfsoft.evaluation.entity.ClearDataEntity)
	*/
	@Override
	public void filingScoreRecord(ClearDataEntity entity) {
		clearDataDao.filingScoreRecord(entity);
	}

	/* （非 Javadoc）
	* <p>Title: deleteScoreRecordFile</p>
	* <p>Description: </p>
	* @param entity
	* @see com.zfsoft.evaluation.service.IClearDataService#deleteScoreRecordFile(com.zfsoft.evaluation.entity.ClearDataEntity)
	*/
	@Override
	public void deleteScoreRecordFile(ClearDataEntity entity) {
		clearDataDao.deleteScoreRecordFile(entity);
	}
		
	/* （非 Javadoc）
	* <p>Title: getPersonalWeightSumList</p>
	* <p>Description: </p>
	* @param query
	* @return
	* @see com.zfsoft.evaluation.service.IClearDataService#getPersonalWeightSumList(com.zfsoft.evaluation.entity.ClearDataQuery)
	*/
	@Override
	public PageList<ClearDataEntity> getPersonalWeightSumList(ClearDataQuery query) {
		PageList<ClearDataEntity> pageList = new PageList<ClearDataEntity>();
        Paginator paginator = new Paginator();
        if (query != null) {
        	query.setCondition(this.getCondition(query,false," and ( t.qxtj IS NULL or ( ", " and "));
            paginator.setItemsPerPage(query.getPerPageSize());
            paginator.setPage((Integer)query.getToPage());
            
            paginator.setItems(clearDataDao.getPersonalWeightSumCount(query));
            pageList.setPaginator(paginator);
            if(paginator.getBeginIndex() <= paginator.getItems()){
                query.setStartRow(paginator.getBeginIndex());
                query.setEndRow(paginator.getEndIndex());
                pageList.addAll(clearDataDao.getPersonalWeightSumList(query));
            }
        }
        return pageList;
	}

	/* （非 Javadoc）
	* <p>Title: getCourseWeightSumList</p>
	* <p>Description: </p>
	* @param query
	* @return
	* @see com.zfsoft.evaluation.service.IClearDataService#getCourseWeightSumList(com.zfsoft.evaluation.entity.ClearDataQuery)
	*/
	@Override
	public PageList<ClearDataEntity> getCourseWeightSumList(ClearDataQuery query) {
		PageList<ClearDataEntity> pageList = new PageList<ClearDataEntity>();
        Paginator paginator = new Paginator();
        if (query != null) {
        	query.setCondition(this.getCondition(query,false," and ( t.qxtj IS NULL or ( ", " and "));
            paginator.setItemsPerPage(query.getPerPageSize());
            paginator.setPage((Integer)query.getToPage());
            
            paginator.setItems(clearDataDao.getCourseWeightSumCount(query));
            pageList.setPaginator(paginator);
            if(paginator.getBeginIndex() <= paginator.getItems()){
                query.setStartRow(paginator.getBeginIndex());
                query.setEndRow(paginator.getEndIndex());
                pageList.addAll(clearDataDao.getCourseWeightSumList(query));
            }
        }
        return pageList;
	}

	/* （非 Javadoc）
	* <p>Title: getReportViewList</p>
	* <p>Description: </p>
	* @param query
	* @return
	* @see com.zfsoft.evaluation.service.IClearDataService#getReportViewList(com.zfsoft.evaluation.entity.ClearDataQuery)
	*/
	@Override
	public ReportView getReportViewList(ClearDataQuery query) {
		ReportView view = new ReportView();
		List<ClearDataEntity> list = clearDataDao.getReportViewList(query);
		// 如果是以课程为统计维度
		if(StringUtil.isNotEmpty(query.getKcdm())){
			if(StringUtil.isNotEmpty(query.getJxb())){
				view.setReportTitle("同一门课程不同教学班评价对比");
			}else{
				view.setReportTitle("同一门课程不同教师评价对比");
			}
		}
		// 如果是以教师为统计维度
		else if(StringUtil.isNotEmpty(query.getJszgh())){
			if(StringUtil.isNotEmpty(query.getJxb())){
				view.setReportTitle("同一位教师不同教学班评价对比");
			}else{
				view.setReportTitle("同一位教师不同课程评价对比");
			}
		}
		List<String[]> valueList = new ArrayList<String[]>();
		for(ClearDataEntity entity : list){
			String[] array = new String[2];
			array[0] = entity.getZf();
			// 如果是以课程为统计维度
			if(StringUtil.isNotEmpty(query.getKcdm())){
				view.setSubTitle(entity.getKcmc());
				if(StringUtil.isNotEmpty(query.getJxb())){
					array[1] = entity.getJxbjs();
				}else{
					array[1] = entity.getJsxm();
				}
			}
			// 如果是以教师为统计维度
			else if(StringUtil.isNotEmpty(query.getJszgh())){
				view.setSubTitle(entity.getJsxm());
				if(StringUtil.isNotEmpty(query.getJxb())){
					array[1] = entity.getJxbkc();
				}else{
					array[1] = entity.getKcmc();
				}
			}
			valueList.add(array);
		}
		view.setValueList(valueList);
		return view;
	}

	/* （非 Javadoc）
	* <p>Title: getVarianceResult</p>
	* <p>Description: </p>
	* @param query
	* @return
	* @see com.zfsoft.evaluation.service.IClearDataService#getVarianceResult(com.zfsoft.evaluation.entity.ClearDataQuery)
	*/
	@Override
	public ClearDataEntity getVarianceResult(ClearDataQuery query) {
		query.setCondition(this.getCondition(query,false," and ( t.qxtj IS NULL or ( ", " and "));
		return clearDataDao.getVarianceResult(query);
	}

	/* （非 Javadoc）
	* <p>Title: countQxsl</p>
	* <p>Description: </p>
	* @param query
	* @return
	* @see com.zfsoft.evaluation.service.IClearDataService#countQxsl(com.zfsoft.evaluation.entity.ClearDataQuery)
	*/
	@Override
	public int countQxsl(ClearDataQuery query) {
		query.setCondition(this.getCondition(query,false," and ( t.qxtj IS NOT NULL and ( ", " and "));
		return clearDataDao.countQxsl(query);
	}

	/**==================================================IOC========================================================**/
	
	private String getCondition(ClearDataQuery query, boolean isContain, String conStr, String linkStr) {
		// 组装查询条件
		if(query.getQxtjz() != null && query.getQxtjz().length > 0){
			for (int i = 0; i < query.getQxtjz().length; i++) {
				if(isContain){
					conStr += " t.qxtj like '%" + query.getQxtjz()[i] + "%' ";
				}else{
					conStr += " t.qxtj not like '%" + query.getQxtjz()[i] + "%' ";
				}
				if(i < query.getQxtjz().length - 1){
					conStr += linkStr;
				}
			}
			conStr += ") )";
		}else{
			conStr = "";
		}
		return conStr;
	}
	
	/**
	 * @param clearDataDao the clearDataDao to set
	 */
	public void setClearDataDao(IClearDataDao clearDataDao) {
		this.clearDataDao = clearDataDao;
	}

}
