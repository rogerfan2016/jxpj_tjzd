package com.zfsoft.monitor.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.zfsoft.hrm.common.HrmAction;
import com.zfsoft.monitor.entity.PatrolEntity;
import com.zfsoft.monitor.entity.PatrolQuery;
import com.zfsoft.monitor.entity.PatrolType;
import com.zfsoft.monitor.service.IMonitorService;

/**
 * 
 * @author Administrator
 *
 */
public class MobileMonitorAction extends HrmAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4095621991204424054L;
	private IMonitorService monitorService;
	private String xslx;
	private List<PatrolEntity> list;
	private PatrolEntity model;
	
	/*****************************Action****************************************/
	/**
	 * 巡视
	 * @return
	 */
	public String patrol() {
		PatrolQuery query = new PatrolQuery();
		
		if (StringUtils.isEmpty(xslx)) {
			xslx = PatrolType.dept.getKeyStr();
		}
		query.setXslx(xslx);
		
        getValueStack().set("patrols", PatrolType.values());
        query.setCondition(" and t.xsry like '%" + getUser().getYhm() + "%'");
//        query.setCondition(" and t.xsry like '%100537%'");
        
        list = monitorService.getPatrolsByMobile(query);
		return "patrol";
	}
	
	/**
	 * 巡视详情
	 * @return
	 */
	public String patrolDetail() {
		if (model != null && !StringUtils.isEmpty(model.getGlobalid())) {
            model = monitorService.getPatrolById(model.getGlobalid());
        }
		return "patrolDetail";
	}
	
	/**
     * 保存巡视结果内容
     * @return
     */
    public String savePatrolDetail() {
        model.setXgry(getUser().getYhm());
        monitorService.updatePatrolDetail(model);
        getValueStack().set(DATA, getMessage());
        return DATA;
    }
    
//============================================================================================
	/**
	 * @return the monitorService
	 */
	public IMonitorService getMonitorService() {
		return monitorService;
	}

	/**
	 * @param monitorService the monitorService to set
	 */
	public void setMonitorService(IMonitorService monitorService) {
		this.monitorService = monitorService;
	}

    /**
	 * @return the xslx
	 */
	public String getXslx() {
		return xslx;
	}

	/**
	 * @param xslx the xslx to set
	 */
	public void setXslx(String xslx) {
		this.xslx = xslx;
	}

	/**
	 * @return the model
	 */
	public PatrolEntity getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(PatrolEntity model) {
		this.model = model;
	}

	/**
	 * @return the list
	 */
	public List<PatrolEntity> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<PatrolEntity> list) {
		this.list = list;
	}

}
