package com.zfsoft.evaluation.service;

import java.util.List;

import com.zfsoft.dao.page.PageList;
import com.zfsoft.evaluation.entity.ClearDataEntity;
import com.zfsoft.evaluation.entity.ClearDataQuery;
import com.zfsoft.hrm.report.entity.ReportView;

/**
 * 
* <p>Title: IClearDataService</p>
* <p>Description: </p>
* <p>Company: XXXX</p>
* @author    rogerfan
* @date      2016-1-8
 */
public interface IClearDataService {

    /**
     * 查询清洗条件内容
     * @param query
     * @return
     */
    public List<ClearDataEntity> getConditionList(ClearDataQuery query);
    
    /**
     * 添加清洗条件
     * @param entity
     */
    public void insertCondition(ClearDataEntity entity);
    
    /**
     * 修改清洗条件
     * @param entity
     */
    public void modifyCondition(ClearDataEntity entity);
    
    /**
    * <p>Title: doClearData</p>
    * <p>Description: 清洗数据</p>
    * @param entity
     */
    public void doClearData(ClearDataEntity entity);
    
    /**
     * 查询个人加权评分结果记录内容
     * @param query
     * @return
     */
    public PageList<ClearDataEntity> getPersonalWeightList(ClearDataQuery query);
    
    /**
     * 查询课程加权评分结果记录内容
     * @param query
     * @return
     */
    public PageList<ClearDataEntity> getCourseWeightList(ClearDataQuery query);
    
    /**
     * 查询学生评分记录内容
     * @param query
     * @return
     */
    public PageList<ClearDataEntity> getStudentsEvaluationList(ClearDataQuery query);
    
    /**
     * 存档学生评价记录
     * @param entity
     */
    public void filingStudentsEvaluation(ClearDataEntity entity);

    /**
     * 删除学生评价存档记录
     * @param entity
     */
    public void deleteStudentsEvaluationFile(ClearDataEntity entity);
    
    /**
     * 存档教学评价数据
     * @param entity
     */
    public void filingScoreRecord(ClearDataEntity entity);

    /**
     * 删除教学评价存档数据
     * @param entity
     */
    public void deleteScoreRecordFile(ClearDataEntity entity);

    /**
     * 查询个人加权评分结果汇总内容
     * @param query
     * @return
     */
    public PageList<ClearDataEntity> getPersonalWeightSumList(ClearDataQuery query);

    /**
     * 查询课程加权评分结果汇总内容
     * @param query
     * @return
     */
    public PageList<ClearDataEntity> getCourseWeightSumList(ClearDataQuery query);
    
    /**
     * 统计汇总图形展示数据
     * @param query
     * @return
     */
    public ReportView getReportViewList(ClearDataQuery query);
    
    /**
     * 统计分析方差最大最小值等数据
     * @param query
     * @return
     */
    public ClearDataEntity getVarianceResult(ClearDataQuery query);
    
    /**
     * 清洗数量
     * @param query
     * @return
     */
    public int countQxsl(ClearDataQuery query);
}
