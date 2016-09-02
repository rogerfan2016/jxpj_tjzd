package com.zfsoft.evaluation.service.impl;

import com.zfsoft.dao.page.PageList;
import com.zfsoft.dao.page.Paginator;
import com.zfsoft.evaluation.dao.daointerface.IClassInstructionsDao;
import com.zfsoft.evaluation.entity.ClassInstructionsDetailEntity;
import com.zfsoft.evaluation.entity.ClassInstructionsEntity;
import com.zfsoft.evaluation.service.IClassInstructionsService;
import com.zfsoft.util.base.StringUtil;

/**
 * 
* <p>Title: ClassInstructionsServiceImpl</p>
* <p>Description: 任课说明书管理</p>
* <p>Company: XXXX</p>
* @author    rogerfan
* @date      2016-1-22
 */
public class ClassInstructionsServiceImpl implements IClassInstructionsService {

    private IClassInstructionsDao classInstructionsDao;

	/* （非 Javadoc）
	* <p>Title: getClassInstructionsList</p>
	* <p>Description: </p>
	* @param entity
	* @return
	* @see com.zfsoft.evaluation.service.IClassInstructionsService#getClassInstructionsList(com.zfsoft.evaluation.entity.ClassInstructionsEntity)
	*/
	@Override
	public PageList<ClassInstructionsEntity> getClassInstructionsList(
			ClassInstructionsEntity entity) {
		PageList<ClassInstructionsEntity> pageList = new PageList<ClassInstructionsEntity>();
        Paginator paginator = new Paginator();
        if (entity != null) {
            paginator.setItemsPerPage(entity.getPerPageSize());
            paginator.setPage((Integer)entity.getToPage());
            
            paginator.setItems(classInstructionsDao.getClassInstructionsCount(entity));
            pageList.setPaginator(paginator);
            if(paginator.getBeginIndex() <= paginator.getItems()){
                entity.setStartRow(paginator.getBeginIndex());
                entity.setEndRow(paginator.getEndIndex());
                pageList.addAll(classInstructionsDao.getClassInstructionsList(entity));
            }
        }
        return pageList;
	}

	/* （非 Javadoc）
	* <p>Title: getClassInstructionsById</p>
	* <p>Description: </p>
	* @param id
	* @return
	* @see com.zfsoft.evaluation.service.IClassInstructionsService#getClassInstructionsById(java.lang.String)
	*/
	@Override
	public ClassInstructionsEntity getClassInstructionsById(String id) {
		return classInstructionsDao.getClassInstructionsById(id);
	}

	/* （非 Javadoc）
	* <p>Title: saveClassInstructions</p>
	* <p>Description: </p>
	* @param entity
	* @see com.zfsoft.evaluation.service.IClassInstructionsService#saveClassInstructions(com.zfsoft.evaluation.entity.ClassInstructionsEntity)
	*/
	@Override
	public void saveClassInstructions(ClassInstructionsEntity entity) {
		classInstructionsDao.saveClassInstructions(entity);
	}

	/* （非 Javadoc）
	* <p>Title: modifyClassInstructions</p>
	* <p>Description: </p>
	* @param entity
	* @see com.zfsoft.evaluation.service.IClassInstructionsService#modifyClassInstructions(com.zfsoft.evaluation.entity.ClassInstructionsEntity)
	*/
	@Override
	public void modifyClassInstructions(ClassInstructionsEntity entity) {
		classInstructionsDao.modifyClassInstructions(entity);
	}
	
	@Override
	public void copyClassInstructions(String id) {
		if(StringUtil.isNotEmpty(id)){
			ClassInstructionsEntity entity = classInstructionsDao.getClassInstructionsById(id);
			if(entity != null){
				entity.setId("");
				// 插入任课说明书记录
				classInstructionsDao.saveClassInstructions(entity);
				// 插入详情记录
				for(ClassInstructionsDetailEntity object : entity.getDetailList()){
					object.setRksmsid(entity.getId());
					classInstructionsDao.saveClassInstructionsDetail(object);
				}
			}
		}
	}

	/* （非 Javadoc）
	* <p>Title: deleteClassInstructions</p>
	* <p>Description: </p>
	* @param id
	* @see com.zfsoft.evaluation.service.IClassInstructionsService#deleteClassInstructions(java.lang.String)
	*/
	@Override
	public void deleteClassInstructions(String id) {
		// 删除明细
		classInstructionsDao.deleteDetailByRksmsId(id);
		// 删除记录
		classInstructionsDao.deleteClassInstructions(id);
	}

	/* （非 Javadoc）
	* <p>Title: getClassInstructionsDetailById</p>
	* <p>Description: </p>
	* @param id
	* @return
	* @see com.zfsoft.evaluation.service.IClassInstructionsService#getClassInstructionsDetailById(java.lang.String)
	*/
	@Override
	public ClassInstructionsDetailEntity getClassInstructionsDetailById(
			String id) {
		return classInstructionsDao.getClassInstructionsDetailById(id);
	}

	/* （非 Javadoc）
	* <p>Title: saveClassInstructionsDetail</p>
	* <p>Description: </p>
	* @param entity
	* @see com.zfsoft.evaluation.service.IClassInstructionsService#saveClassInstructionsDetail(com.zfsoft.evaluation.entity.ClassInstructionsDetailEntity)
	*/
	@Override
	public void saveClassInstructionsDetail(ClassInstructionsDetailEntity entity) {
		classInstructionsDao.saveClassInstructionsDetail(entity);
	}

	/* （非 Javadoc）
	* <p>Title: modifyClassInstructionsDetail</p>
	* <p>Description: </p>
	* @param entity
	* @see com.zfsoft.evaluation.service.IClassInstructionsService#modifyClassInstructionsDetail(com.zfsoft.evaluation.entity.ClassInstructionsDetailEntity)
	*/
	@Override
	public void modifyClassInstructionsDetail(
			ClassInstructionsDetailEntity entity) {
		classInstructionsDao.modifyClassInstructionsDetail(entity);
	}

	/* （非 Javadoc）
	* <p>Title: deleteClassInstructionsDetail</p>
	* <p>Description: </p>
	* @param id
	* @see com.zfsoft.evaluation.service.IClassInstructionsService#deleteClassInstructionsDetail(java.lang.String)
	*/
	@Override
	public void deleteClassInstructionsDetail(String id) {
		classInstructionsDao.deleteClassInstructionsDetail(id);
	}

//=================================================================================================================
	/**
	 * @param classInstructionsDao the classInstructionsDao to set
	 */
	public void setClassInstructionsDao(IClassInstructionsDao classInstructionsDao) {
		this.classInstructionsDao = classInstructionsDao;
	}

}
