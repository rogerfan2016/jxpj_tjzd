package com.zfsoft.monitor.service;

import java.util.List;

import com.zfsoft.dao.page.PageList;
import com.zfsoft.monitor.entity.PatrolDetailEntity;
import com.zfsoft.monitor.entity.PatrolEntity;
import com.zfsoft.monitor.entity.PatrolQuery;

/**
 * 
 * @author Administrator
 *
 */
public interface IMonitorService {
    
    /**
     * 查询巡视内容
     * @param query
     * @return
     */
    public PageList<PatrolEntity> getPatrols(PatrolQuery query);
    
    /**
     * 手机端查询巡视任务内容
     * @param query
     * @return
     */
    public List<PatrolEntity> getPatrolsByMobile(PatrolQuery query);

    /**
     * 查询巡视内容
     * @param globalid
     * @return
     */
    public PatrolEntity getPatrolById(String globalid);

    /**
     * 保存巡视内容
     * @param model
     */
    public void savePatrol(PatrolEntity model);

    /**
     * 修改巡视内容
     * @param model
     */
    public void modifyPatrol(PatrolEntity model);

    /**
     * 删除巡视内容
     * @param model
     */
    public void removePatrol(String id);
    
//==============================================================================================
    /**
     * 查询巡视记录列表记录数
     * @param query
     * @return
     */
    public int findPatrolDetailCount(PatrolQuery query);
    
    /**
    * <p>Title: findPatrolDetailList</p>
    * <p>Description: 查询巡视记录列表内容 </p>
    * @param query
    * @return
     */
    public PageList<PatrolDetailEntity> findPatrolDetailList(PatrolQuery query);
    
    /**
     * 根据巡视任务ID查询巡视记录列表内容
     * @param query
     * @return
     */
    public List<PatrolDetailEntity> findPatrolDetailListById(PatrolQuery query);
    
    /**
     * 根据巡视记录ID查询巡视记录内容
     * @param globalid
     * @return
     */
    public PatrolDetailEntity getPatrolDetailById(String id);
    
    /**
     * 保存巡视记录内容
     * @param model
     */
    public void savePatrolDetail(PatrolDetailEntity model);
    
    /**
     * 修改巡视记录内容
     * @param model
     */
    public void updatePatrolDetail(PatrolEntity model);
    
    /**
     * 反馈或审核巡视记录
     * @param model
     */
    public void modifyPatrolDetail(PatrolDetailEntity model);
    
    /**
     * 根据任务ID删除所有巡视记录内容
     * @param id
     */
    public void removePatrolDetailByGlobalid(String globalid);

    /**
     * 按学院汇总统计巡视情况
     * @param query
     * @return
     */
    public List<PatrolDetailEntity> getPatrolDetailSummary(PatrolQuery query);
}
