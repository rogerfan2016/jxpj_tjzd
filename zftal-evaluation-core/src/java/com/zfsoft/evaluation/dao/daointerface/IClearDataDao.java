package com.zfsoft.evaluation.dao.daointerface;

import java.util.List;

import com.zfsoft.evaluation.entity.ClearDataEntity;
import com.zfsoft.evaluation.entity.ClearDataQuery;

/**
* <p>Title: IClearDataDao</p>
* <p>Description: </p>
* <p>Company: XXXX</p>
* @author    rogerfan
* @date      2016-1-8
 */
public interface IClearDataDao {

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
     * 查询个人加权评分结果记录数量
     * @param query
     * @return
     */
    public int getPersonalWeightCount(ClearDataQuery query);

    /**
     * 查询个人加权评分结果记录内容
     * @param query
     * @return
     */
    public List<ClearDataEntity> getPersonalWeightList(ClearDataQuery query);
    
    /**
     * 查询课程加权评分结果记录数量
     * @param query
     * @return
     */
    public int getCourseWeightCount(ClearDataQuery query);

    /**
     * 查询课程加权评分结果记录内容
     * @param query
     * @return
     */
    public List<ClearDataEntity> getCourseWeightList(ClearDataQuery query);
    
    /**
     * 查询学生评分记录数据数量
     * @param query
     * @return
     */
    public int getStudentsEvaluationCount(ClearDataQuery query);

    /**
     * 查询学生评分记录列表 
     * @param query
     * @return
     */
    public List<ClearDataEntity> getStudentsEvaluationList(ClearDataQuery query);
    
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
     * 查询学生评分存档记录数量
     * @param query
     * @return
     */
    public int getStuEvaFileCount(ClearDataQuery query);

    /**
     * 查询学生评分存档记录列表 
     * @param query
     * @return
     */
    public List<ClearDataEntity> getStuEvaFileList(ClearDataQuery query);
    
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
     * 修改学生评分记录的清洗状态
     * @param entity
     */
    public void modifyClearData(ClearDataEntity entity);
    
    /**
     * 查询个人加权评分结果汇总数量
     * @param query
     * @return
     */
    public int getPersonalWeightSumCount(ClearDataQuery query);

    /**
     * 查询个人加权评分结果汇总内容
     * @param query
     * @return
     */
    public List<ClearDataEntity> getPersonalWeightSumList(ClearDataQuery query);
    
    /**
     * 查询课程加权评分结果汇总数量
     * @param query
     * @return
     */
    public int getCourseWeightSumCount(ClearDataQuery query);

    /**
     * 查询课程加权评分结果汇总内容
     * @param query
     * @return
     */
    public List<ClearDataEntity> getCourseWeightSumList(ClearDataQuery query);
    
    /**
     * 统计汇总图形展示数据
     * @param query
     * @return
     */
    public List<ClearDataEntity> getReportViewList(ClearDataQuery query);

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
