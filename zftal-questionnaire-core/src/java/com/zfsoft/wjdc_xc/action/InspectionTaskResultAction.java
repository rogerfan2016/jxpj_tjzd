package com.zfsoft.wjdc_xc.action;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zfsoft.dao.page.PageList;
import com.zfsoft.hrm.common.HrmAction;
import com.zfsoft.wjdc.dao.entites.WjglModel;
import com.zfsoft.wjdc.service.svcinterface.IStglService;
import com.zfsoft.wjdc.service.svcinterface.IWjglService;
import com.zfsoft.wjdc_xc.entites.InspectionTaskMember;
import com.zfsoft.wjdc_xc.entites.InspectionTaskResult;
import com.zfsoft.wjdc_xc.query.InspectionTaskResultQuery;
import com.zfsoft.wjdc_xc.service.IInspectionTaskResultService;
import com.zfsoft.wjdc_xc.service.IInspectionTaskService;

/**
 * 
 * @author ChenMinming
 * @date 2015-6-12
 * @version V1.0.0
 */
public class InspectionTaskResultAction extends HrmAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5691816845344518185L;
	private InspectionTaskResultQuery query = new InspectionTaskResultQuery();
	private InspectionTaskResult inspectionTaskResult;
	private IInspectionTaskService inspectionTaskService;
	private IInspectionTaskResultService inspectionTaskResultService;
	private PageList<InspectionTaskResult> pageList = new PageList<InspectionTaskResult>();
	
	
	private IStglService stglService;
	private IWjglService wjglService;
	private String type="XNXC";
	
	/**
	 * 手机端录入的评价列表展示
	 * @return
	 */
	public String list(){
		query.setConfigType(type);
		query.setGh(getUser().getYhm());
		if(query.getStatus()==null){
			query.setStatus("0");
		}
		query.setPerPageSize(6);
		pageList=inspectionTaskResultService.getPagingList(query);
		getValueStack().set("pageInfo",pageList.getPaginator());
		getValueStack().set("type",type);
		return "list";
	}
	
	/**
	 * 添加评价记录
	 * @return
	 */
	public String choose(){
		InspectionTaskMember memberQuery = new InspectionTaskMember();
		memberQuery.setGh(getUser().getYhm());
//		memberQuery.setQueryDayNum(3); // 取多少天的任务
		memberQuery.setConfigType(type);
		getValueStack().set("taskList", inspectionTaskService.getMemberList(memberQuery));
		return "choose";
	}
	
	public String create(){
		InspectionTaskMember memberQuery = new InspectionTaskMember();
		memberQuery.setId(inspectionTaskResult.getMemberId());
		memberQuery.setConfigType(type);
		List<InspectionTaskMember> mList = inspectionTaskService.getMemberList(memberQuery);
		if(mList==null||mList.size()<1||!getUser().getYhm().equals(mList.get(0).getGh())){
			setErrorMessage("指定巡查任务不存在或您没有被分配到该任务！");
			getValueStack().set(DATA, getMessage());
			return DATA;
		}
		query.setDcdx(inspectionTaskResult.getDcdx());
		query.setConfigType(type);
		query.setMemberId(inspectionTaskResult.getMemberId());
		if(inspectionTaskResultService.findCount(query)>0){
			setErrorMessage("该对象已存在相应巡查任务的记录！");
			getValueStack().set(DATA, getMessage());
			return DATA;
		}
		inspectionTaskResult.setWjid(mList.get(0).getWjid());
		inspectionTaskResult.setDcr(this.getUser().getYhm());
		inspectionTaskResultService.insert(inspectionTaskResult); 
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("text", getMessage().getText());
		map.put("html", getMessage().getHtml());
		map.put("resultId", inspectionTaskResult.getId());
		getValueStack().set(DATA, map);
		return DATA;
	}
	
	/**
	 * 用户答卷
	 * @return
	 */
	public String detail(){
		try {
			inspectionTaskResult = inspectionTaskResultService.findById(inspectionTaskResult.getId());
			WjglModel wjm=wjglService.getModel(inspectionTaskResult.getWjid());
			if(wjm.getDjrid()==null||"".equals(wjm.getDjrid())){
				wjm.setDjrid(getUser().getYhm());//设置答卷人id
			}
			wjm.setDjrid(inspectionTaskResult.getId());
			WjglModel yhdjxx=stglService.getYhdjxx(wjm);
			wjm.setDjzt(yhdjxx.getDjzt());
			getValueStack().set("wjModel", wjm);
			
		} catch (Exception e) {
			logException(e);
		}		
		return "detail";
	}
	/**
	 * 保存问卷答案
	 * @return
	 */
	public String saveWjDa(){	
		HttpServletRequest request = getRequest();
		try {
			inspectionTaskResult = inspectionTaskResultService.findById(inspectionTaskResult.getId());
			WjglModel wjModel=wjglService.getModel(inspectionTaskResult.getWjid());
			wjModel.setDjrid(inspectionTaskResult.getId());
			WjglModel wjglModel=stglService.getYhdjxx(wjModel);
			
			if(wjglModel!=null&&!"1".equals(inspectionTaskResult.getRwzt())){
				getValueStack().set("result", "该问卷任务已停止，不能修改或提交答卷！");
			}else{
				query.setGh(getUser().getYhm());
				query.setConfigType(type);
				query.setMemberId(inspectionTaskResult.getMemberId());
				if(inspectionTaskResultService.findCount(query)<1){
					getValueStack().set("result", "您无权提交他人的问卷！");
				}else{
					// 保存问卷答案
					String result = stglService.saveWjDa(request, wjModel);
					// 计算总分并更新到答卷记录中
					int zf = inspectionTaskResultService.getFzSum(inspectionTaskResult.getId());
					// 修改评价记录信息
					inspectionTaskResult.setStatus("1");
					inspectionTaskResult.setZf(String.valueOf(zf));
					inspectionTaskResultService.updateStatus(inspectionTaskResult);
					if("I99001".equals(result) || "I99002".equals(result)){
						result = getText(result);
					}
					getValueStack().set("result",result );
				}
			}
		} catch (Exception e) {
			logException(e);
		}
		return detail();
	}
	
	public String remove() throws Exception{
		inspectionTaskResultService.remove(inspectionTaskResult);
		getValueStack().set(DATA, getMessage()); 
		return DATA;
	}

	/**
	 * 返回
	 */
	public InspectionTaskResultQuery getQuery() {
		return query;
	}

	/**
	 * 设置
	 * @param query 
	 */
	public void setQuery(InspectionTaskResultQuery query) {
		this.query = query;
	}

	/**
	 * 返回
	 */
	public InspectionTaskResult getInspectionTaskResult() {
		return inspectionTaskResult;
	}

	/**
	 * 设置
	 * @param inspectionTaskResult 
	 */
	public void setInspectionTaskResult(InspectionTaskResult inspectionTaskResult) {
		this.inspectionTaskResult = inspectionTaskResult;
	}

	/**
	 * 返回
	 */
	public PageList<InspectionTaskResult> getPageList() {
		return pageList;
	}

	/**
	 * 设置
	 * @param pageList 
	 */
	public void setPageList(PageList<InspectionTaskResult> pageList) {
		this.pageList = pageList;
	}

	/**
	 * 设置
	 * @param inspectionTaskService 
	 */
	public void setInspectionTaskService(
			IInspectionTaskService inspectionTaskService) {
		this.inspectionTaskService = inspectionTaskService;
	}

	/**
	 * 设置
	 * @param inspectionTaskResultService 
	 */
	public void setInspectionTaskResultService(
			IInspectionTaskResultService inspectionTaskResultService) {
		this.inspectionTaskResultService = inspectionTaskResultService;
	}

	/**
	 * 设置
	 * @param service 
	 */
	public void setStglService(IStglService stglService) {
		this.stglService = stglService;
	}

	/**
	 * 设置
	 * @param wjglService 
	 */
	public void setWjglService(IWjglService wjglService) {
		this.wjglService = wjglService;
	}

	/**
	 * 返回
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置
	 * @param type 
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
}
