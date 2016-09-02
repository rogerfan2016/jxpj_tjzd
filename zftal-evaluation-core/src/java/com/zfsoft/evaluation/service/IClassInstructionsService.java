package com.zfsoft.evaluation.service;

import com.zfsoft.dao.page.PageList;
import com.zfsoft.evaluation.entity.ClassInstructionsDetailEntity;
import com.zfsoft.evaluation.entity.ClassInstructionsEntity;

/**
 * 
* <p>Title: IClassInstructionsService</p>
* <p>Description: 任课说明书内容</p>
* <p>Company: XXXX</p>
* @author    rogerfan
* @date      2016-1-22
 */
public interface IClassInstructionsService {

    /**
     * 查询任课说明书内容
     * @param entity
     * @return
     */
    public PageList<ClassInstructionsEntity> getClassInstructionsList(ClassInstructionsEntity entity);

    /**
     * 根据任课说明书ID查询任课说明书内容
     * @param id
     * @return
     */
    public ClassInstructionsEntity getClassInstructionsById(String id);

    /**
     * 保存任课说明书内容
     * @param entity
     */
    public void saveClassInstructions(ClassInstructionsEntity entity);

    /**
     * 修改任课说明书内容
     * @param entity
     */
    public void modifyClassInstructions(ClassInstructionsEntity entity);
    
    /**
     * 复制任课说明书
     * @param id
     */
    public void copyClassInstructions(String id);
    
    /**
     * 删除任课说明书内容
     * @param entity
     */
    public void deleteClassInstructions(String id);
    
    /**
     * 根据任课说明书明细ID查询任课说明书明细内容
     * @param id
     * @return
     */
    public ClassInstructionsDetailEntity getClassInstructionsDetailById(String id);
    
    /**
     * 保存任课说明书明细内容
     * @param entity
     */
    public void saveClassInstructionsDetail(ClassInstructionsDetailEntity entity);

    /**
     * 修改任课说明书明细内容
     * @param entity
     */
    public void modifyClassInstructionsDetail(ClassInstructionsDetailEntity entity);
    
    /**
     * 删除任课说明书明细内容
     * @param entity
     */
    public void deleteClassInstructionsDetail(String id);
}
