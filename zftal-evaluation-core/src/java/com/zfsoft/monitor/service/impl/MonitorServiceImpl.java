package com.zfsoft.monitor.service.impl;

import java.util.List;

import com.zfsoft.dao.page.PageList;
import com.zfsoft.dao.page.Paginator;
import com.zfsoft.monitor.dao.daointerface.IMonitorDao;
import com.zfsoft.monitor.entity.PatrolDetailEntity;
import com.zfsoft.monitor.entity.PatrolEntity;
import com.zfsoft.monitor.entity.PatrolQuery;
import com.zfsoft.monitor.service.IMonitorService;

/**
 * 
 * @author Administrator
 *
 */
public class MonitorServiceImpl implements IMonitorService {

    private IMonitorDao monitorDao;

    /**
     * @return the monitorDao
     */
    public IMonitorDao getMonitorDao() {
        return monitorDao;
    }

    /**
     * @param monitorDao the monitorDao to set
     */
    public void setMonitorDao(IMonitorDao monitorDao) {
        this.monitorDao = monitorDao;
    }

    /**
     * 查询巡视内容
     */
    @Override
    public PageList<PatrolEntity> getPatrols(PatrolQuery query) {
        PageList<PatrolEntity> pageList = new PageList<PatrolEntity>();
        Paginator paginator = new Paginator();
        if (query != null) {
            paginator.setItemsPerPage(query.getPerPageSize());
            paginator.setPage((Integer)query.getToPage());
            
            paginator.setItems(monitorDao.getPatrolsCount(query));
            pageList.setPaginator(paginator);
            if(paginator.getBeginIndex() <= paginator.getItems()){
                query.setStartRow(paginator.getBeginIndex());
                query.setEndRow(paginator.getEndIndex());
                pageList.addAll(monitorDao.getPatrols(query));
            }
        }

        return pageList;
    }

	/* （非 Javadoc）
	* <p>Title: getPatrolsByMobile</p>
	* <p>Description: </p>
	* @param query
	* @return
	* @see com.zfsoft.monitor.service.IMonitorService#getPatrolsByMobile(com.zfsoft.monitor.entity.PatrolQuery)
	*/
	@Override
	public List<PatrolEntity> getPatrolsByMobile(PatrolQuery query) {
		return monitorDao.getPatrolsByMobile(query);
	}

	/**
     * 查询巡视内容
     */
    @Override
    public PatrolEntity getPatrolById(String globalid) {
        return monitorDao.getPatrolById(globalid);
    }

    /**
     * 保存巡视内容
     */
    @Override
    public void savePatrol(PatrolEntity model) {
        int row = monitorDao.savePatrol(model);
        if(row > 0){
        	PatrolDetailEntity detail = new PatrolDetailEntity();
        	detail.setXsdw(model.getXsdw());
        	detail.setXscdmc(model.getXscdmc());
        	detail.setKcsj(model.getXsrq());
        	detail.setKcjc(model.getKcjc());
        	detail.setGlobalid(model.getGlobalid());
        	monitorDao.savePatrolDetail(detail);
        }
    }

    /**
     * 修改巡视内容
     */
    @Override
    public void modifyPatrol(PatrolEntity model) {
        monitorDao.modifyPatrol(model);
    }

    /**
     * 删除巡视内容
     */
	@Override
	public void removePatrol(String id) {
		monitorDao.removePatrol(id);
	}
//====================================================================================

	/* （非 Javadoc）
	* <p>Title: findPatrolDetailListById</p>
	* <p>Description: </p>
	* @param query
	* @return
	* @see com.zfsoft.monitor.service.IMonitorService#findPatrolDetailListById(com.zfsoft.monitor.entity.PatrolQuery)
	*/
	@Override
	public List<PatrolDetailEntity> findPatrolDetailListById(PatrolQuery query) {
		PageList<PatrolDetailEntity> pageList = new PageList<PatrolDetailEntity>();
        Paginator paginator = new Paginator();
        if (query != null) {
            paginator.setItemsPerPage(query.getPerPageSize());
            paginator.setPage((Integer)query.getToPage());

            paginator.setItems(monitorDao.findPatrolDetailCount(query));
            pageList.setPaginator(paginator);
            if(paginator.getBeginIndex() <= paginator.getItems()){
                query.setStartRow(paginator.getBeginIndex());
                query.setEndRow(paginator.getEndIndex());
                pageList.addAll(monitorDao.findPatrolDetailListById(query));
            }
        }

        return pageList;
	}

	/* （非 Javadoc）
	* <p>Title: findPatrolDetailCount</p>
	* <p>Description: </p>
	* @param query
	* @return
	* @see com.zfsoft.monitor.service.IMonitorService#findPatrolDetailCount(com.zfsoft.monitor.entity.PatrolQuery)
	*/
	@Override
	public int findPatrolDetailCount(PatrolQuery query) {
		return monitorDao.findPatrolDetailCount(query);
	}

	/* （非 Javadoc）
	* <p>Title: findPatrolDetailList</p>
	* <p>Description: </p>
	* @param query
	* @return
	* @see com.zfsoft.monitor.service.IMonitorService#findPatrolDetailList(com.zfsoft.monitor.entity.PatrolQuery)
	*/
	@Override
	public PageList<PatrolDetailEntity> findPatrolDetailList(PatrolQuery query) {
		PageList<PatrolDetailEntity> pageList = new PageList<PatrolDetailEntity>();
        Paginator paginator = new Paginator();
        if (query != null) {
            paginator.setItemsPerPage(query.getPerPageSize());
            paginator.setPage((Integer)query.getToPage());

            paginator.setItems(monitorDao.findPatrolDetailCount(query));
            pageList.setPaginator(paginator);
            if(paginator.getBeginIndex() <= paginator.getItems()){
                query.setStartRow(paginator.getBeginIndex());
                query.setEndRow(paginator.getEndIndex());
                pageList.addAll(monitorDao.findPatrolDetailList(query));
            }
        }

        return pageList;
	}

	/* （非 Javadoc）
	* <p>Title: getPatrolDetailById</p>
	* <p>Description: </p>
	* @param id
	* @return
	* @see com.zfsoft.monitor.service.IMonitorService#getPatrolDetailById(java.lang.String)
	*/
	@Override
	public PatrolDetailEntity getPatrolDetailById(String id) {
		return monitorDao.getPatrolDetailById(id);
	}

	/* （非 Javadoc）
	* <p>Title: savePatrolDetail</p>
	* <p>Description: </p>
	* @param model
	* @see com.zfsoft.monitor.service.IMonitorService#savePatrolDetail(com.zfsoft.monitor.entity.PatrolDetailEntity)
	*/
	@Override
	public void savePatrolDetail(PatrolDetailEntity model) {
		monitorDao.savePatrolDetail(model);
	}

	/* （非 Javadoc）
	* <p>Title: updatePatrolDetail</p>
	* <p>Description: </p>
	* @param model
	* @see com.zfsoft.monitor.service.IMonitorService#updatePatrolDetail(com.zfsoft.monitor.entity.PatrolEntity)
	*/
	@Override
	public void updatePatrolDetail(PatrolEntity model) {
		if(model != null && model.getPatrolDetailEntityList() != null 
				&& model.getPatrolDetailEntityList().size() > 0){
			for(PatrolDetailEntity obj : model.getPatrolDetailEntityList()){
				obj.setXsry(model.getXgry());
				obj.setZt("1");
				monitorDao.updatePatrolDetail(obj);
			}
			PatrolEntity obj2 = new PatrolEntity();
			obj2.setZt("2");
			obj2.setXgry(model.getXgry());
			obj2.setGlobalid(model.getGlobalid());
			monitorDao.modifyPatrol(obj2);
		}
	}

	/* （非 Javadoc）
	* <p>Title: modifyPatrolDetail</p>
	* <p>Description: </p>
	* @param model
	* @see com.zfsoft.monitor.service.IMonitorService#modifyPatrolDetail(com.zfsoft.monitor.entity.PatrolDetailEntity)
	*/
	@Override
	public void modifyPatrolDetail(PatrolDetailEntity model) {
		monitorDao.modifyPatrolDetail(model);
	}

	/* （非 Javadoc）
	* <p>Title: removePatrolDetailByGlobalid</p>
	* <p>Description: </p>
	* @param globalid
	* @see com.zfsoft.monitor.service.IMonitorService#removePatrolDetailByGlobalid(java.lang.String)
	*/
	@Override
	public void removePatrolDetailByGlobalid(String globalid) {
		monitorDao.removePatrolDetailByGlobalid(globalid);
	}

	@Override
	public List<PatrolDetailEntity> getPatrolDetailSummary(PatrolQuery query) {
		return monitorDao.getPatrolDetailSummary(query);
	}

}
