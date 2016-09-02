package com.zfsoft.evaluation.action;

import com.zfsoft.dao.page.PageList;
import com.zfsoft.dataprivilege.util.DeptFilterUtil;
import com.zfsoft.evaluation.entity.ClassInstructionsDetailEntity;
import com.zfsoft.evaluation.entity.ClassInstructionsEntity;
import com.zfsoft.evaluation.service.IClassInstructionsService;
import com.zfsoft.hrm.baseinfo.org.query.OrgQuery;
import com.zfsoft.hrm.baseinfo.org.service.svcinterface.IOrgService;
import com.zfsoft.hrm.common.HrmAction;
import com.zfsoft.util.base.StringUtil;
import com.zfsoft.util.date.DateTimeUtil;

/**
 * 
 * <p>
 * Title: ClassInstructionsAction
 * </p>
 * <p>
 * Description: 任课说明书管理
 * </p>
 * <p>
 * Company: XXXX
 * </p>
 * 
 * @author rogerfan
 * @date 2016-1-22
 */
public class ClassInstructionsAction extends HrmAction {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private IClassInstructionsService classInstructionsService;
	private IOrgService orgService;
	private OrgQuery orgQuery;
	private ClassInstructionsEntity query;
	private ClassInstructionsEntity model;
	private ClassInstructionsDetailEntity detailModel;
	private PageList<ClassInstructionsEntity> pageList;
	private String id;
	private String[] ids;
	private String zt;

	/**
	 * <p>
	 * Title: searchClassInstructionsList
	 * </p>
	 * <p>
	 * Description: 任课说明书查询
	 * </p>
	 * 
	 * @return
	 */
	public String searchClassInstructionsList() {
		if (query == null) {
			query = new ClassInstructionsEntity();
		}
		query.setCjr(getUser().getYhm());
		pageList = classInstructionsService.getClassInstructionsList(query);
		return "searchClassInstructionsList";
	}
	
	/**
	 * <p>
	 * Title: searchClassInstructionsDetailList
	 * </p>
	 * <p>
	 * Description: 任课说明书详情查询
	 * </p>
	 * 
	 * @return
	 */
	public String searchClassInstructionsDetailList() {
		model = classInstructionsService.getClassInstructionsById(id);
		return "searchClassInstructionsDetailList";
	}
	
	/**
	 * 
	* <p>Title: checkClassInstructionsList</p>
	* <p>Description: 查询教研室审核任课说明书列表</p>
	* @return
	 */
	public String checkClassInstructionsList() {
		if (query == null) {
			query = new ClassInstructionsEntity();
		}
		if(StringUtil.isEmpty(query.getZt())){
			query.setCondition(this.getCondition(" and t.zt in ('1','2','4')"));
		}else if("3".equals(query.getZt())){
			query.setCondition(" and t.zt in ('2','4')");
		}else{
			query.setCondition(" and t.zt = '2'");
		}
		pageList = classInstructionsService.getClassInstructionsList(query);
		return "checkClassInstructionsList";
	}
	
	/**
	 * 
	* <p>Title: checkClassInstructionsDetailList</p>
	* <p>Description: 查询教研室审核任课说明书详情列表</p>
	* @return
	 */
	public String checkClassInstructionsDetailList() {
		model = classInstructionsService.getClassInstructionsById(id);
		return "checkClassInstructionsDetailList";
	}
	
	/**
	 * 
	* <p>Title: checkIn</p>
	* <p>Description: 提交审核</p>
	* @return
	 */
	public String checkIn() {
		if (StringUtil.isNotEmpty(id)) {
			model = new ClassInstructionsEntity();
			model.setZt("1");
			model.setId(id);
			classInstructionsService.modifyClassInstructions(model);
		}
		getValueStack().set(DATA, getMessage());
		return DATA;
	}
	
	/**
	 * 
	* <p>Title: cancelCheckIn</p>
	* <p>Description: 取消审核</p>
	* @return
	 */
	public String cancelCheckIn() {
		if (StringUtil.isNotEmpty(id)) {
			model = new ClassInstructionsEntity();
			model.setZt("0");
			model.setId(id);
			classInstructionsService.modifyClassInstructions(model);
		}
		getValueStack().set(DATA, getMessage());
		return DATA;
	}
	
	/**
	 * 
	* <p>Title: checkPassByJys</p>
	* <p>Description: 教研室审核通过</p>
	* @return
	 */
	public String checkPassByJys() {
		if (model != null) {
			model.setZt("2");
			model.setShyj("【教研室审核通过】：（"+ this.getUser().getXm() + " | " +DateTimeUtil.getCurrDateTimeStr() +"）；");
			classInstructionsService.modifyClassInstructions(model);
		}
		getValueStack().set(DATA, getMessage());
		return DATA;
	}
	
	/**
	 * 
	* <p>Title: checkPassByYx</p>
	* <p>Description: 院系审核通过</p>
	* @return
	 */
	public String checkPassByYx() {
		if (model != null) {
			model.setZt("3");
			model.setShyj("【院系审核通过】：（"+ this.getUser().getXm() + " | " + DateTimeUtil.getCurrDateTimeStr() +"）；");
			classInstructionsService.modifyClassInstructions(model);
		}
		getValueStack().set(DATA, getMessage());
		return DATA;
	}
	
	/**
	 * 
	* <p>Title: checkNoPass</p>
	* <p>Description: 审核不通过</p>
	* @return
	 */
	public String checkNoPass() {
		if (model != null) {
			if("1".equals(model.getZt())){
				model.setShyj("【教研室审核不通过】：审核意见：" + model.getShyj() + "（"+ this.getUser().getXm() + " | " +DateTimeUtil.getCurrDateTimeStr() +"）");
			}else{
				model.setShyj("【院系审核不通过】：审核意见：" + model.getShyj() + "（"+ this.getUser().getXm() + " | " +DateTimeUtil.getCurrDateTimeStr() +"）");
			}
			model.setZt("4");
			classInstructionsService.modifyClassInstructions(model);
		}
		getValueStack().set(DATA, getMessage());
		return DATA;
	}
	
	/**
	 * 
	* <p>Title: checkClassInstructionsList</p>
	* <p>Description: 查询院系审核任课说明书列表2</p>
	* @return
	 */
	public String checkClassInstructionsList2() {
		if (query == null) {
			query = new ClassInstructionsEntity();
		}
		if(StringUtil.isEmpty(query.getZt())){
			query.setCondition(this.getCondition(" and t.zt in ('2','3','4')"));
		}else if("3".equals(query.getZt())){
			query.setCondition(" and t.zt in ('3','4')");
		}else{
			query.setCondition(" and t.zt = '2'");
		}		
		pageList = classInstructionsService.getClassInstructionsList(query);
		return "checkClassInstructionsList2";
	}
	
	/**
	 * 
	* <p>Title: checkClassInstructionsDetailList</p>
	* <p>Description: 查询院系审核任课说明书详情列表2</p>
	* @return
	 */
	public String checkClassInstructionsDetailList2() {
		model = classInstructionsService.getClassInstructionsById(id);
		return "checkClassInstructionsDetailList2";
	}

	/**
	 * <p>
	 * Title: editClassInstructions
	 * </p>
	 * <p>
	 * Description: 增加或修改任课说明书
	 * </p>
	 * 
	 * @return
	 */
	public String editClassInstructions() {
		if (query != null && StringUtil.isNotEmpty(query.getId())) {
			model = classInstructionsService.getClassInstructionsById(query.getId());
		}
		orgQuery = new OrgQuery();
		orgQuery.setType("1");
		getValueStack().set("orgList", orgService.findOrgList(orgQuery));
		return "editClassInstructions";
	}
	
	/**
	 * <p>
	 * Title: copyClassInstructions
	 * </p>
	 * <p>
	 * Description: 复制任课说明书
	 * </p>
	 * 
	 * @return
	 */
	public String copyClassInstructions() {
		if (StringUtil.isNotEmpty(ids)) {
			classInstructionsService.copyClassInstructions(ids[0]);
		}
		getValueStack().set(DATA, getMessage());
		return DATA;
	}

	/**
	 * <p>
	 * Title: saveClassInstructions
	 * </p>
	 * <p>
	 * Description: 保存
	 * </p>
	 * 
	 * @return
	 */
	public String saveClassInstructions() {
		if (model != null) {
			if (StringUtil.isNotEmpty(model.getId())) {
				classInstructionsService.modifyClassInstructions(model);
			} else {
				model.setRkjs(getUser().getYhm());
				model.setCjr(getUser().getYhm());
				classInstructionsService.saveClassInstructions(model);
			}
		}
		getValueStack().set(DATA, getMessage());
		return DATA;
	}

	/**
	 * <p>
	 * Title: editClassInstructionsDetail
	 * </p>
	 * <p>
	 * Description: 增加或修改任课说明书详情
	 * </p>
	 * 
	 * @return
	 */
	public String editClassInstructionsDetail() {
		if (query != null && StringUtil.isNotEmpty(query.getId())) {
			detailModel = classInstructionsService
					.getClassInstructionsDetailById(query.getId());
		}
		return "editClassInstructionsDetail";
	}

	/**
	 * <p>
	 * Title: saveClassInstructionsDetail
	 * </p>
	 * <p>
	 * Description: 保存详情
	 * </p>
	 * 
	 * @return
	 */
	public String saveClassInstructionsDetail() {
		if (detailModel != null) {
			if (StringUtil.isNotEmpty(detailModel.getId())) {
				classInstructionsService
						.modifyClassInstructionsDetail(detailModel);
			} else {
				classInstructionsService
						.saveClassInstructionsDetail(detailModel);
			}
		}
		getValueStack().set(DATA, getMessage());
		return DATA;
	}
	
	/**
	 * 
	* <p>Title: deleteClassInstructions</p>
	* <p>Description: 删除任课说明书</p>
	* @return
	 */
	public String deleteClassInstructions() {
		if (StringUtil.isNotEmpty(ids)) {
			model = classInstructionsService.getClassInstructionsById(ids[0]);
			if(!("0".equals(model.getZt()) || "4".equals(model.getZt()))){
				getValueStack().set(DATA, "审核通过或审核中记录不能删除！");
			}else{
				classInstructionsService.deleteClassInstructions(ids[0]);
				getValueStack().set(DATA, getMessage());
			}
		}
		return DATA;
	}
	
	/**
	 * 
	* <p>Title: deleteClassInstructionsDetail</p>
	* <p>Description: 删除任课说明书详情</p>
	* @return
	 */
	public String deleteClassInstructionsDetail() {
		for (String globalid : ids) {
			classInstructionsService.deleteClassInstructionsDetail(globalid.trim());
        }
		getValueStack().set(DATA, getMessage());
		return DATA;
	}

	// ============================================================================================================================

	/**
     * 查询条件
     * @return
     */
    private String getCondition(String status) {
        String express = "";
        String deptFilter = DeptFilterUtil.getCondition("t", "yx");
        if(!StringUtil.isEmpty(deptFilter)){
            express += " and (" + deptFilter + ")";
        }
        // 状态条件
        if(StringUtil.isNotEmpty(status)){
        	express += status;
        }
        return express;
    }
    
	/**
	 * @return the pageList
	 */
	public PageList<ClassInstructionsEntity> getPageList() {
		return pageList;
	}

	/**
	 * @return the query
	 */
	public ClassInstructionsEntity getQuery() {
		return query;
	}

	/**
	 * @param query
	 *            the query to set
	 */
	public void setQuery(ClassInstructionsEntity query) {
		this.query = query;
	}

	/**
	 * @return the model
	 */
	public ClassInstructionsEntity getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(ClassInstructionsEntity model) {
		this.model = model;
	}

	/**
	 * @return the detailModel
	 */
	public ClassInstructionsDetailEntity getDetailModel() {
		return detailModel;
	}

	/**
	 * @param detailModel
	 *            the detailModel to set
	 */
	public void setDetailModel(ClassInstructionsDetailEntity detailModel) {
		this.detailModel = detailModel;
	}

	/**
	 * @param pageList
	 *            the pageList to set
	 */
	public void setPageList(PageList<ClassInstructionsEntity> pageList) {
		this.pageList = pageList;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the zt
	 */
	public String getZt() {
		return zt;
	}

	/**
	 * @param zt the zt to set
	 */
	public void setZt(String zt) {
		this.zt = zt;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public OrgQuery getOrgQuery() {
		return orgQuery;
	}

	public void setOrgQuery(OrgQuery orgQuery) {
		this.orgQuery = orgQuery;
	}

	/**
	 * @param classInstructionsService
	 *            the classInstructionsService to set
	 */
	public void setClassInstructionsService(
			IClassInstructionsService classInstructionsService) {
		this.classInstructionsService = classInstructionsService;
	}

	public void setOrgService(IOrgService orgService) {
		this.orgService = orgService;
	}
	
}
