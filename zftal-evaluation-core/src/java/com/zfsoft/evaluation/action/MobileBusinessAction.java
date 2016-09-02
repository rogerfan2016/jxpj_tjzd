package com.zfsoft.evaluation.action;

import com.zfsoft.common.system.SubSystemHolder;
import com.zfsoft.dao.page.PageList;
import com.zfsoft.hrm.common.HrmAction;
import com.zfsoft.hrm.dybill.enums.PrivilegeType;
import com.zfsoft.hrm.infochange.entity.InfoChange;
import com.zfsoft.hrm.infochange.entity.InfoChangeAudit;
import com.zfsoft.hrm.infochange.query.InfoChangeQuery;
import com.zfsoft.hrm.infochange.service.IInfoChangeService;
import com.zfsoft.util.base.StringUtil;
import com.zfsoft.workflow.model.SpBusiness;
import com.zfsoft.workflow.service.ISpBusinessService;

/**
 * 
 * 针对具体业务的功能类
 * @author zhangqy
 *
 */
public class MobileBusinessAction extends HrmAction {


	private static final long serialVersionUID = -9217147867646770843L;

	 //待办事宜信息类
	private InfoChangeQuery infoChangeQuery = new InfoChangeQuery();
	private IInfoChangeService infoChangeService;
	private PageList<InfoChange> pageList;
	
	//业务管理表主键
	private String classId;
	private String status=null;
	
	//待办审核
	private ISpBusinessService spBusinessService;
	private SpBusiness spBusiness;
	private InfoChange infoChange = new InfoChange();
	private String workId;
	
	//业务类型
	private String btype;
	
	
	 /**
     * 教学监控-调停课、学籍异动
     * byzhangqy
     */
    public String center() throws Exception{

		 String readSession = getRequest().getParameter("readSession");
		if("true".equals(readSession)){
			infoChangeQuery = (InfoChangeQuery )getSession().getAttribute("InfoChangeAuditAction_InfoChangeQuery");
			if(infoChangeQuery==null) infoChangeQuery = new InfoChangeQuery();
		}
		infoChangeQuery.setOnwer(false);
		infoChangeQuery.setOrderStr( " commitDate DESC" );
		btype=getRequest().getParameter("btype");
		infoChangeQuery.setClassId(SubSystemHolder.getPropertiesValue(btype));
    	
    	if(StringUtil.isNotBlank(status)){
    		infoChangeQuery.setStatusStr(status);
    	}else{
    		status="WAIT_AUDITING";
    		infoChangeQuery.setStatusStr(status);
		}
		
		pageList = infoChangeService.getPagedListForMobile(infoChangeQuery);
		int beginIndex = pageList.getPaginator().getBeginIndex();
		pageList.setPaginator(infoChangeQuery);
		getSession().setAttribute("InfoChangeAuditAction_InfoChangeQuery",infoChangeQuery);
		//getValueStack().set("statusArray", WorkNodeStatusEnum.values());
		getValueStack().set("status",status);
		getValueStack().set("beginIndex", beginIndex);
		getValueStack().set("btype",btype);
		return "center";
	}
    
    /**
     * 进入某个业务准备处理
     * byzhangqy
     */
    
    public String detail(){
    	spBusiness=spBusinessService.findSpBusinessByRelDetail(classId,workId);
		infoChange = infoChangeService.getInfoChangeById(classId,workId);
		InfoChangeAudit audit = infoChangeService.getAudit(classId,infoChange.getId());
		if(audit==null){
			return "pendingAffairDetail";
		}
		String privilegeExpression =  null;
		if(audit.getCurrentNode() !=null){
			 privilegeExpression = audit.getCurrentNode().getCommitWorkBillClassesPrivilege();
		
		if(!"add".equals(infoChange.getOpType())){
			privilegeExpression = privilegeExpression
			.replaceAll(PrivilegeType.SEARCH_ADD_DELETE_EDIT.toString(), PrivilegeType.SEARCH_EDIT.toString())
			.replaceAll(PrivilegeType.SEARCH_ADD_DELETE.toString(), PrivilegeType.SEARCH.toString());
			}	
			getValueStack().set("privilegeExpression", privilegeExpression);
			getValueStack().set("currentNode", audit.getCurrentNode());
		}
		getValueStack().set("excutedList", audit.getExcutedList());
		getValueStack().set("logList", audit.getLogList());		
		getValueStack().set("word", getRequest().getParameter("word"));
		getValueStack().set("btype",getRequest().getParameter("btype"));
		
		return "detail";
    }
	
	
	
	public InfoChangeQuery getInfoChangeQuery() {
		return infoChangeQuery;
	}
	public void setInfoChangeQuery(InfoChangeQuery infoChangeQuery) {
		this.infoChangeQuery = infoChangeQuery;
	}
	public IInfoChangeService getInfoChangeService() {
		return infoChangeService;
	}
	public void setInfoChangeService(IInfoChangeService infoChangeService) {
		this.infoChangeService = infoChangeService;
	}
	public PageList<InfoChange> getPageList() {
		return pageList;
	}
	public void setPageList(PageList<InfoChange> pageList) {
		this.pageList = pageList;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ISpBusinessService getSpBusinessService() {
		return spBusinessService;
	}
	public void setSpBusinessService(ISpBusinessService spBusinessService) {
		this.spBusinessService = spBusinessService;
	}
	public SpBusiness getSpBusiness() {
		return spBusiness;
	}
	public void setSpBusiness(SpBusiness spBusiness) {
		this.spBusiness = spBusiness;
	}
	public InfoChange getInfoChange() {
		return infoChange;
	}
	public void setInfoChange(InfoChange infoChange) {
		this.infoChange = infoChange;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public String getbtype() {
		return btype;
	}

	public void setbtype(String btype) {
		this.btype = btype;
	} 
	
}
