package com.zfsoft.wjdc_xc.action;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.zfsoft.common.spring.SpringHolder;
import com.zfsoft.dao.page.PageList;
import com.zfsoft.hrm.common.HrmAction;
import com.zfsoft.hrm.core.util.DownloadFilenameUtil;
import com.zfsoft.hrm.normal.info.entity.OverallInfo;
import com.zfsoft.hrm.normal.info.service.svcinterface.IOverallInfoService;
import com.zfsoft.util.base.StringUtil;
import com.zfsoft.wjdc_xc.entites.InspectionConfig;
import com.zfsoft.wjdc_xc.entites.InspectionTask;
import com.zfsoft.wjdc_xc.entites.InspectionTaskMember;
import com.zfsoft.wjdc_xc.query.InspectionSummerQuery;
import com.zfsoft.wjdc_xc.query.InspectionTaskQuery;
import com.zfsoft.wjdc_xc.service.IInspectionConfigService;
import com.zfsoft.wjdc_xc.service.IInspectionTaskService;

/**
 * 
 * @author ChenMinming
 * @date 2015-6-9
 * @version V1.0.0
 */
public class InspectionTaskAction extends HrmAction{

	private static final long serialVersionUID = 6219910320036556383L;
	private InspectionTask inspectionTask = new InspectionTask();
	private IInspectionTaskService inspectionTaskService;
	private IInspectionConfigService inspectionConfigService;
	private InspectionTaskQuery query= new InspectionTaskQuery();
	private PageList<InspectionTask> pageList;
	private PageList<Map<String, Object>> taskSummerList;
	private List<Map<String, Object>> taskResultSummaryList;
	private PageList<InspectionTaskMember> taskMemberList;
	private String taskId;
	private String type="XNXC";
	
	private InspectionSummerQuery summerQuery = new InspectionSummerQuery();
	public String list(){
		query.setConfigType(type);
		query.setOrderStr("taskDate desc");
		pageList = inspectionTaskService.getPagingList(query);
		return "page";
	}
	
	public String taskSummer(){
		getValueStack().set("taskSummerList",inspectionTaskService.getTaskSummerPage(summerQuery));
		return "taskSummer";
	}
	
	public String resultSummer(){
		getValueStack().set("resultSummerList",inspectionTaskService.getResultPagingList(summerQuery));
		return "resultSummer";
	}
	
	/**
     * 查询评价成员列表
     * @return
     * @throws Exception
     */
	public String searchMember(){
		getValueStack().set("memberList",inspectionTaskService.getTaskMemberList(query));
		return "searchMember";
	}
	
	/**
     * 导出被评价结果记录列表
     * @return
     * @throws Exception
     */
    public String export() throws Exception{
    	Map<String,String> map = new HashMap<String,String>();
    	if(StringUtil.isNotEmpty(taskId)){
        	inspectionTask = inspectionTaskService.findById(taskId);
        	map.put("taskId", taskId);
        }
        getResponse().reset();
        getResponse().setCharacterEncoding("utf-8");
        getResponse().setContentType("application/vnd.ms-excel");
        String useragent = getRequest().getHeader("user-agent");
        String disposition = DownloadFilenameUtil.fileDisposition(useragent, inspectionTask.getTaskDateStr()+ "-" + inspectionTask.getWjText()+".xls");
        getResponse().setHeader("Content-Disposition", disposition);
        
        WritableWorkbook wwb = Workbook.createWorkbook(getResponse().getOutputStream());
        
        WritableSheet sheet = wwb.createSheet(inspectionTask.getTaskDateStr()+ "-" + inspectionTask.getWjText(), 1);
        summerQuery.setPerPageSize(Integer.MAX_VALUE);
        summerQuery.setParams(map);
        taskSummerList = inspectionTaskService.getTaskSummerPage(summerQuery);
        
        //产生表头
        sheet.addCell(generateTheadLabel(0, 0, "职工号/部门编号"));
        sheet.addCell(generateTheadLabel(1, 0, "被评价对象"));
        sheet.addCell(generateTheadLabel(2, 0, "评价人数"));
        sheet.addCell(generateTheadLabel(3, 0, "评价得分"));	

        //产生内容
        int y = 0;
        for(Map<String, Object> m : taskSummerList){
            y++;
            sheet.addCell(generateValueLabel(0, y, m.get("DCDX")));
            sheet.addCell(generateValueLabel(1, y, m.get("DCDXMC")));
            sheet.addCell(generateValueLabel(2, y, m.get("NUM")));
            Object fz = m.get("FZ");
            Object num = m.get("NUM");
            DecimalFormat df = new DecimalFormat("#.00");   
            sheet.addCell(generateValueLabel(3, y, df.format(Double.valueOf(fz.toString())/Double.valueOf(num.toString()))));
        }
        wwb.write();
        wwb.close();
        return null;
    }
    
    /**
     * 导出被评价汇总结果记录列表
     * @return
     * @throws Exception
     */
    public String exportSummary() throws Exception{
    	Map<String,String> map = new HashMap<String,String>();
    	map.put("configType", type);
    	if(query == null){
        	map.put("start", "2010-01-01");
        	map.put("end", "2999-01-01");
        }else{
        	if(StringUtil.isNotEmpty(query.getStartText())){
        		map.put("start", query.getStartText());
        	}else{
        		map.put("start", "2010-01-01");
        	}
        	if(StringUtil.isNotEmpty(query.getEndText())){
        		map.put("end", query.getEndText());
        	}else{
        		map.put("end", "2999-01-01");
        	}
        }
        getResponse().reset();
        getResponse().setCharacterEncoding("utf-8");
        getResponse().setContentType("application/vnd.ms-excel");
        String useragent = getRequest().getHeader("user-agent");
        String disposition = DownloadFilenameUtil.fileDisposition(useragent, "评价结果汇总信息.xls");
        getResponse().setHeader("Content-Disposition", disposition);
        
        WritableWorkbook wwb = Workbook.createWorkbook(getResponse().getOutputStream());
        
        WritableSheet sheet = wwb.createSheet("评价结果汇总信息", 1);
        summerQuery.setParams(map);
        taskResultSummaryList = inspectionTaskService.getTaskResultSummary(summerQuery);
        
        //产生表头
        sheet.addCell(generateTheadLabel(0, 0, "职工号/部门编号"));
        sheet.addCell(generateTheadLabel(1, 0, "被评价对象"));
        sheet.addCell(generateTheadLabel(2, 0, "总分"));
        sheet.addCell(generateTheadLabel(3, 0, "评价人数"));
        sheet.addCell(generateTheadLabel(4, 0, "平均分"));	

        //产生内容
        int y = 0;
        for(Map<String, Object> m : taskResultSummaryList){
            y++;
            sheet.addCell(generateValueLabel(0, y, m.get("DCDX")));
            sheet.addCell(generateValueLabel(1, y, m.get("DCDXMC")));
            sheet.addCell(generateValueLabel(2, y, m.get("ZF")));
            sheet.addCell(generateValueLabel(3, y, m.get("PJRS"))); 
            sheet.addCell(generateValueLabel(4, y, m.get("PJF")));
        }
        wwb.write();
        wwb.close();
        return null;
    }
    
    /**
     * 导出评价人员记录列表
     * @return
     * @throws Exception
     */
    public String exportMember() throws Exception{
        getResponse().reset();
        getResponse().setCharacterEncoding("utf-8");
        getResponse().setContentType("application/vnd.ms-excel");
        String useragent = getRequest().getHeader("user-agent");
        String disposition = DownloadFilenameUtil.fileDisposition(useragent, "评价人员列表.xls");
        getResponse().setHeader("Content-Disposition", disposition);
        
        WritableWorkbook wwb = Workbook.createWorkbook(getResponse().getOutputStream());
        
        WritableSheet sheet = wwb.createSheet(inspectionTask.getTaskDateStr()+ "-" + inspectionTask.getWjText(), 1);
        query.setPerPageSize(Integer.MAX_VALUE);
        taskMemberList = inspectionTaskService.getTaskMemberList(query);
        
        //产生表头
        sheet.addCell(generateTheadLabel(0, 0, "职工号/学号"));
        sheet.addCell(generateTheadLabel(1, 0, "姓名"));
        sheet.addCell(generateTheadLabel(2, 0, "学院"));
        sheet.addCell(generateTheadLabel(3, 0, "专业"));	
        sheet.addCell(generateTheadLabel(4, 0, "行政班"));	

        //产生内容
        int y = 0;
        for(InspectionTaskMember m : taskMemberList){
            y++;
            sheet.addCell(generateValueLabel(0, y, m.getGh()));
            sheet.addCell(generateValueLabel(1, y, m.getXm()));
            sheet.addCell(generateValueLabel(2, y, m.getXy()));
            sheet.addCell(generateValueLabel(3, y, m.getZy()));
            sheet.addCell(generateValueLabel(4, y, m.getXzb()));
        }
        wwb.write();
        wwb.close();
        return null;
    }
	
	public String save() throws UnsupportedEncodingException{
		if(inspectionTask != null && StringUtil.isNotEmpty(inspectionTask.getConfigType())){
			List<OverallInfo> overallInfoList = null;
			InspectionConfig bean = new InspectionConfig();
			bean.setConfigType(inspectionTask.getConfigType());
			bean.setKey("sql_append");
			List<String> list = inspectionConfigService.findAppendValue(bean);
			// 如果是靠关系来找评价人和被评价对象
			if(list != null && list.size() > 0){
				String sql = "1=1";
				if(list!=null&&list.size()>0){
					sql=list.get(0);
					sql=sql.replaceAll("\\$\\{[^\\}]*\\}", "'"+getUser().getYhm()+"'");
					
				}
				IOverallInfoService overallInfoService = SpringHolder.getBean("overallInfoService", IOverallInfoService.class);
				// 限制评价人数配置
//				int num =10;
//				try {
//					num=Integer.valueOf(getRequest().getParameter("maxItemSize"));
//				} catch (Exception e) {
//				}
//				overallInfoList=overallInfoService.userListThink(null," rownum<="+num+" and "+sql);
				overallInfoList=overallInfoService.userListThink(null, sql);
			}
			inspectionTaskService.save(inspectionTask,overallInfoList);
		}
		getValueStack().set(DATA,getMessage());
		return DATA;
	}
	
	public String update(){
		inspectionTaskService.update(inspectionTask);
		getValueStack().set(DATA,getMessage());
		return DATA;
	}
	
	public String remove(){
		inspectionTask=inspectionTaskService.findById(inspectionTask.getId());
		if(inspectionTask.getDxNum()>0){
			setErrorMessage("不允许删除已经存在提交记录的信息！");
			getValueStack().set(DATA,getMessage());
			return DATA;
		}
		inspectionTaskService.remove(inspectionTask);
		getValueStack().set(DATA,getMessage());
		return DATA;
	}
	public String detail(){
		if(StringUtil.isNotEmpty(inspectionTask.getId())){
			inspectionTask=inspectionTaskService.findById(inspectionTask.getId());
		}else{
			inspectionTask.setTaskDate(new Date());
			inspectionTask.setConfigType(type);
		}
		getValueStack().set("personList", inspectionConfigService.getCheckedPersonList(inspectionTask.getConfigType()));
		getValueStack().set("dcwjList", inspectionConfigService.getCheckedDcwjList(inspectionTask.getConfigType()));
		return "detail";
	}
	/**
	 * 返回
	 */
	public InspectionTask getInspectionTask() {
		return inspectionTask;
	}
	/**
	 * 设置
	 * @param inspectionTask 
	 */
	public void setInspectionTask(InspectionTask inspectionTask) {
		this.inspectionTask = inspectionTask;
	}
	/**
	 * 返回
	 */
	public PageList<InspectionTask> getPageList() {
		return pageList;
	}
	/**
	 * 设置
	 * @param pageList 
	 */
	public void setPageList(PageList<InspectionTask> pageList) {
		this.pageList = pageList;
	}
	/**
	 * 返回
	 */
	public InspectionTaskQuery getQuery() {
		return query;
	}
	/**
	 * 设置
	 * @param query 
	 */
	public void setQuery(InspectionTaskQuery query) {
		this.query = query;
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
	 * @param inspectionConfigService 
	 */
	public void setInspectionConfigService(
			IInspectionConfigService inspectionConfigService) {
		this.inspectionConfigService = inspectionConfigService;
	}

	/**
	 * 返回
	 */
	public InspectionSummerQuery getSummerQuery() {
		return summerQuery;
	}

	/**
	 * 设置
	 * @param summerQuery 
	 */
	public void setSummerQuery(InspectionSummerQuery summerQuery) {
		this.summerQuery = summerQuery;
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

	public PageList<Map<String, Object>> getTaskSummerList() {
		return taskSummerList;
	}

	public void setTaskSummerList(PageList<Map<String, Object>> taskSummerList) {
		this.taskSummerList = taskSummerList;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public PageList<InspectionTaskMember> getTaskMemberList() {
		return taskMemberList;
	}

	public void setTaskMemberList(PageList<InspectionTaskMember> taskMemberList) {
		this.taskMemberList = taskMemberList;
	}

	public List<Map<String, Object>> getTaskResultSummaryList() {
		return taskResultSummaryList;
	}

	public void setTaskResultSummaryList(
			List<Map<String, Object>> taskResultSummaryList) {
		this.taskResultSummaryList = taskResultSummaryList;
	}
	
}
