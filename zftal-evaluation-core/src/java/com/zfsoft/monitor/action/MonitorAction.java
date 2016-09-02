package com.zfsoft.monitor.action;

import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.zfsoft.common.factory.SessionFactory;
import com.zfsoft.dao.page.PageList;
import com.zfsoft.dataprivilege.dto.DeptFilter;
import com.zfsoft.dataprivilege.filter.impl.DealDeptFilter;
import com.zfsoft.dataprivilege.util.DataFilterUtil;
import com.zfsoft.dataprivilege.util.DeptFilterUtil;
import com.zfsoft.evaluation.utils.EvaluationUtils;
import com.zfsoft.hrm.baseinfo.code.entities.Item;
import com.zfsoft.hrm.baseinfo.code.util.CodeUtil;
import com.zfsoft.hrm.baseinfo.dyna.entities.DynaBean;
import com.zfsoft.hrm.baseinfo.dyna.util.DynaBeanUtil;
import com.zfsoft.hrm.common.HrmAction;
import com.zfsoft.hrm.config.ICodeConstants;
import com.zfsoft.hrm.core.util.DownloadFilenameUtil;
import com.zfsoft.hrm.report.entity.ReportView;
import com.zfsoft.monitor.entity.PatrolDetailEntity;
import com.zfsoft.monitor.entity.PatrolDetailStatusEnum;
import com.zfsoft.monitor.entity.PatrolEntity;
import com.zfsoft.monitor.entity.PatrolQuery;
import com.zfsoft.monitor.entity.PatrolType;
import com.zfsoft.monitor.service.IMonitorService;
import com.zfsoft.orcus.lang.TimeUtil;
import com.zfsoft.util.base.StringUtil;
import com.zfsoft.util.date.DateTimeUtil;

/**
 * 
 * @author Administrator
 *
 */
public class MonitorAction extends HrmAction {

    /**
     * 
     */
    private static final long serialVersionUID = -51966075774632388L;
    private IMonitorService monitorService;
    private PatrolEntity model;
    private PatrolDetailEntity detail;
    private PatrolQuery query;
    private PageList<PatrolEntity> pageList;
    private PageList<PatrolDetailEntity> patrolDetailList;
    private List<PatrolDetailEntity> list;
    private String globalid;
    private String[] globalids;
    private String week;
    private String firstDay;
    private String lastDay;
    private String fromTime;
    private String toTime;

    /*****************************Action****************************************/
    /**
     * 教学巡视任务查询
     */
    public String patrol() {
        if (query == null) {
            query = new PatrolQuery();
        }
        getValueStack().set("patrols", PatrolType.values());
        query.setCondition(getCondition("yes","rw"));
        pageList = monitorService.getPatrols(query);
        return "patrol";
    }
    
    /**
     * 学校巡视任务管理
     * @return
     */
    public String patrolBySchool() {
        if (query == null) {
            query = new PatrolQuery();
        }
        query.setXslx(PatrolType.school.getKeyStr());
        query.setCondition(getCondition("yes","rw"));
        pageList = monitorService.getPatrols(query);
        return "manage";
    }
    
    /**
     * 学院巡视任务管理
     * @return
     */
    public String patrolByDept() {
        if (query == null) {
            query = new PatrolQuery();
        }
        query.setXslx(PatrolType.dept.getKeyStr());
        query.setCondition(getCondition("yes","rw"));
        pageList = monitorService.getPatrols(query);
        return "manage";
    }
    
    /**
     * 添加巡视任务内容
     * @return
     */
    public String addPatrol() {
        if (model != null && !StringUtils.isEmpty(model.getGlobalid())) {
            model = monitorService.getPatrolById(model.getGlobalid());
        }

        getValueStack().set("deptList", getDeptList());
        getValueStack().set("patrols", PatrolType.values());
        return "addPatrol";
    }
    
    /**
     * 保存巡视任务内容
     * @return
     */
    public String savePatrol() {
        if (StringUtils.isEmpty(model.getGlobalid())) {
            model.setZcry(getUser().getYhm());
            model.setXgry(getUser().getYhm());
            model.setWeekOfDay(EvaluationUtils.getDayOfWeek(model.getXsrq()));
            if ("dept".equals(model.getXslx().getKeyStr())) {
                model.setXsdw(getSelfDept());
            }else{
            	if(StringUtil.isEmpty(model.getXsdw()) 
            			&& StringUtil.isEmpty(model.getXscdmc())){
            		getValueStack().set(DATA, "开课学院和巡视地点至少选择一个！");
                    return DATA;
            	}
            }
            monitorService.savePatrol(model);
        } else {
            model.setWeekOfDay(EvaluationUtils.getDayOfWeek(model.getXsrq()));
            model.setXgry(getUser().getYhm());
            monitorService.modifyPatrol(model);
        }
        getValueStack().set(DATA, getMessage());
        return DATA;
    }
    
    /**
     * 修改巡视任务内容
     * @return
     */
    public String modify() {
        model.setXgry(getUser().getYhm());
        monitorService.modifyPatrol(model);
        getValueStack().set(DATA, getMessage());
        return DATA;
    }
    
    /**
     * 删除巡视任务内容
     * @return
     */
    public String remove() {
        for (String globalid : globalids) {
            monitorService.removePatrol(globalid.trim());
        }
        getValueStack().set(DATA, getMessage());
        return DATA;
    }
    
//=========================================================================================================

    /**
     * 巡视记录学院处理管理
     * @return
     */
    public String solveByDept() {
        if (query == null) {
            query = new PatrolQuery();
            query.setZt(PatrolDetailStatusEnum.STATUS_YSB.getKey());
        }
        query.setCondition(getCondition("yes","jl"));
        query.setCllx("1");
        patrolDetailList = monitorService.findPatrolDetailList(query);
        getValueStack().set("patrols", PatrolType.values());
        return "solveByDept";
    }
    
    /**
     * 学院反馈处理
     * @return
     */
    public String feedBackByDept() {
        detail = monitorService.getPatrolDetailById(detail.getId());
        return "feedBackByDept";
    }
    
    /**
     * 巡视记录学校处理管理
     * @return
     */
    public String solveBySchool() {
        if (query == null) {
            query = new PatrolQuery();
            query.setZt(PatrolDetailStatusEnum.STATUS_YXYCL.getKey());
        }
        query.setCondition(getCondition("yes","jl"));
        query.setCllx("2");
        query.setXslx(PatrolType.school.getKeyStr());
        patrolDetailList = monitorService.findPatrolDetailList(query);
        getValueStack().set("patrols", PatrolType.values());
        return "solveBySchool";
    }
    
    /**
     * 学校审核处理
     * @return
     */
    public String auditingBySchool() {
        detail = monitorService.getPatrolDetailById(detail.getId());
        return "auditingBySchool";
    }
    
    /**
     * 按学院汇总统计巡视
     * @return
     */
    public String summary() {
    	int dayofweek = TimeUtil.dayOfWeek();
        String now = TimeUtil.current("yyyy-MM-dd");
        // 上一周
        if ("topweek".equals(week)) {
            firstDay = TimeUtil.format(TimeUtil.addDay(firstDay, -7), "yyyy-MM-dd");
            lastDay = TimeUtil.format(TimeUtil.addDay(lastDay, -7), "yyyy-MM-dd");
        // 下一周
        } else if ("nextweek".equals(week)) {
            firstDay = TimeUtil.format(TimeUtil.addDay(firstDay, 7), "yyyy-MM-dd");
            lastDay = TimeUtil.format(TimeUtil.addDay(lastDay, 7), "yyyy-MM-dd");
        } else if ("hold".equals(week)) {
        // 本周
        } else {
            firstDay = TimeUtil.format(TimeUtil.addDay(now, (1 - dayofweek)), "yyyy-MM-dd");
            lastDay = TimeUtil.format(TimeUtil.addDay(now, (7 - dayofweek)), "yyyy-MM-dd");
        }
        // 时间段查询条件
        if(StringUtil.isNotEmpty(fromTime) || StringUtil.isNotEmpty(toTime)){
        	firstDay = fromTime;
        	lastDay = toTime;
        }
        if(query == null){
        	query = new PatrolQuery();
        }
        query.setFirstDay(firstDay);
        query.setLastDay(lastDay);
        // 查询
        list = monitorService.getPatrolDetailSummary(query);
        String[] legend = new String[2];
        legend[0] = "巡视课程数";
        legend[1] = "存在问题数";        
        this.fullBar2DataXML(this.getReportView(list), legend,"",false);
        getValueStack().set("nowdayofweek", dayofweek - 1);
        getValueStack().set("patrols", PatrolType.values());
        getValueStack().set("now", now);
        getValueStack().set("firstDay", firstDay);
        getValueStack().set("lastDay", lastDay);
        getValueStack().set("fromTime", fromTime);
        getValueStack().set("toTime", toTime);
        return "summary";
    }
    
    /**
     * 保存巡视记录
     * @return
     */
    public String saveDetail() {
    	detail.setYxclr(getUser().getYhm());
    	detail.setXxclr(getUser().getYhm());
    	if("4".equals(detail.getZt()) && StringUtil.isEmpty(detail.getXxclyj())){
    		detail.setXxclyj("同意！");
    	}
        monitorService.modifyPatrolDetail(detail);
        getValueStack().set(DATA, getMessage());
        return DATA;
    }
    /**
     * 导出巡视记录列表
     * @return
     * @throws Exception
     */
    public String export() throws Exception{
        getResponse().reset();
        getResponse().setCharacterEncoding("utf-8");
        getResponse().setContentType("application/vnd.ms-excel");
        String useragent = getRequest().getHeader("user-agent");
        String disposition = DownloadFilenameUtil.fileDisposition(useragent, "教学巡视信息.xls");
        getResponse().setHeader("Content-Disposition", disposition);
        
        WritableWorkbook wwb = Workbook.createWorkbook(getResponse().getOutputStream());
        
        WritableSheet sheet = wwb.createSheet("教学巡视信息", 1);
        query.setPerPageSize(Integer.MAX_VALUE);
        query.setCondition(getCondition("yes","jl"));
        patrolDetailList = monitorService.findPatrolDetailList(query);
        
        //产生表头
        sheet.addCell(generateTheadLabel(0, 0, "巡视类型"));
        sheet.addCell(generateTheadLabel(1, 0, "巡视日期"));
        sheet.addCell(generateTheadLabel(2, 0, "课程节次"));
        sheet.addCell(generateTheadLabel(3, 0, "巡视人员"));
        sheet.addCell(generateTheadLabel(4, 0, "巡视单位"));
        sheet.addCell(generateTheadLabel(5, 0, "教学楼"));
        sheet.addCell(generateTheadLabel(6, 0, "巡视教室"));
        sheet.addCell(generateTheadLabel(7, 0, "课程名称"));
        sheet.addCell(generateTheadLabel(8, 0, "授课教师"));
        sheet.addCell(generateTheadLabel(9, 0, "上课学生数"));
        sheet.addCell(generateTheadLabel(10, 0, "状态"));
        sheet.addCell(generateTheadLabel(11, 0, "存在问题"));
        sheet.addCell(generateTheadLabel(12, 0, "院系处理意见"));
        sheet.addCell(generateTheadLabel(13, 0, "院系处理人"));
        sheet.addCell(generateTheadLabel(14, 0, "院系处理时间"));
        sheet.addCell(generateTheadLabel(15, 0, "学校审核意见"));
        sheet.addCell(generateTheadLabel(16, 0, "学校审核人"));
        sheet.addCell(generateTheadLabel(17, 0, "学校审核时间"));

        //产生内容
        int y = 0;
        for(PatrolDetailEntity h : patrolDetailList){
            y++;
            sheet.addCell(generateValueLabel(0, y, PatrolType.dept.getKeyStr().equals(h.getXslx())?"院系":"学校"));
            sheet.addCell(generateValueLabel(1, y, h.getXsrq()));
            sheet.addCell(generateValueLabel(2, y, h.getKcjc()));
            sheet.addCell(generateValueLabel(3, y, h.getXsryxm()));
            sheet.addCell(generateValueLabel(4, y, StringUtils.isEmpty(h.getXsdw()) ? "" : CodeUtil.getItemValue(ICodeConstants.DM_DEF_ORG, h.getXsdw())));
            sheet.addCell(generateValueLabel(5, y, h.getJxl()));
            sheet.addCell(generateValueLabel(6, y, h.getXsdd()));
            sheet.addCell(generateValueLabel(7, y, h.getKcmc()));
            sheet.addCell(generateValueLabel(8, y, h.getJsxm()));
            sheet.addCell(generateValueLabel(9, y, h.getSkxss()));
            sheet.addCell(generateValueLabel(10, y, h.getZt()));
            sheet.addCell(generateValueLabel(11, y, h.getCzwt()+"（"+h.getBz()+"）"));
            sheet.addCell(generateValueLabel(12, y, h.getYxclyj()));
            sheet.addCell(generateValueLabel(13, y, h.getYxclr()));
            sheet.addCell(generateValueLabel(14, y, DateTimeUtil.getFormatDateTime(h.getYxclsj())));
            sheet.addCell(generateValueLabel(15, y, h.getXxclyj()));
            sheet.addCell(generateValueLabel(16, y, h.getXxclr()));
            sheet.addCell(generateValueLabel(17, y, DateTimeUtil.getFormatDateTime(h.getXxclsj())));
        }
        wwb.write();
        wwb.close();
        return null;
    }

    /**
     * 部门列表
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<Item> getDeptList() {
        // 部门类别
        List<Item> orgs = CodeUtil.getChildren(ICodeConstants.DM_DEF_ORG, null);
        List<Item> deptList = new ArrayList<Item>();
        List<DeptFilter> deptFilters = (List<DeptFilter>) DataFilterUtil.getCondition("bmgl");
        DeptFilter df = null;
        
        // 部门过滤
        if (SessionFactory.getUser().getJsdms().indexOf("admin") != -1) {
            for (Item org : orgs) {
                deptList.addAll(org.getChildren());
            }
        } else {
            for (DeptFilter deptFilter : deptFilters) {
                if (SessionFactory.getUser().getJsdms().indexOf(deptFilter.getRoleId()) != -1) {
                    df = deptFilter;
                }
            }
            if (df != null) {
                if (DealDeptFilter.TYPE_SELF.equals(df.getDataType())) {
                    DynaBean selfInfo = DynaBeanUtil.getPerson(getUser().getYhm());
                    Assert.notNull(selfInfo, "无法获取所在的部门");
                    String depId=(String)selfInfo.getValue("dwm");
                    List<Item>  items = CodeUtil.getReverseItemList(ICodeConstants.DM_DEF_ORG, depId);
                    Assert.isTrue(items!=null&&items.size() >= 2, "无法获取所在的部门");
                    deptList.add(items.get(1));
                } else if (!DealDeptFilter.TYPE_ALL.equals(df.getDataType())) {
                    for (Item org:orgs) {
                        for (Item dept : org.getChildren()) {
                            if (df.getOrgList().contains(dept.getGuid())) {
                                deptList.add(dept);
                            }
                        }
                    }
                } else {
                    for (Item org:orgs) {
                        deptList.addAll(org.getChildren());
                    }
                }
            }
        }
        
        return deptList;
    }
//-----------------------------------巡视记录---------------------------------------
    /**
     * 查询巡视记录
     */
    public String patrolDetail(){
        model = monitorService.getPatrolById(globalid);
        return "patrolDetail";
    }
//============================================================================================================================
    /**
     * 巡视的查询条件
     * @return
     */
    private String getCondition(String purview, String type) {
        String express = "";
        if (!StringUtil.isEmpty(query.getXsry())) {
            express += " and t.xsryxm like '%" + query.getXsry() + "%'";
        }
        
        if (!StringUtil.isEmpty(query.getXscdmc())) {
            express += " and t.xscdmc like '%" + query.getXscdmc() + "%'";
        }
        
        if (!StringUtil.isEmpty(query.getXsdw())) {
            express += " and t.xsdw in (select HRM_BZGL_ZZJGB.bmdm as dwm from HRM_BZGL_ZZJGB where HRM_BZGL_ZZJGB.bmdm = ";
            express += query.getXsdw();
            express += " or HRM_BZGL_ZZJGB.sjbmdm=" + query.getXsdw() + ")";
        }
        
        if ("yes".equals(purview)) {
        	// 如果查询的是巡视任务
        	if("rw".equals(type)){
        		String deptFilter = DeptFilterUtil.getCondition("t", "xsdw");
                if(!StringUtil.isEmpty(deptFilter)){
                    express += " and (" + deptFilter + ")";
                }
        	}else{
        		String deptFilter = DeptFilterUtil.getCondition("j", "kkxy");
                if(!StringUtil.isEmpty(deptFilter)){
                    express += " and (" + deptFilter + ")";
                }
        	}            
        }        
        return express;
    }
//=======================================================================================    
    /**
     * 
     * @param query
     * @return
     */
    private ReportView getReportView(List<PatrolDetailEntity> list) {
		ReportView view = new ReportView();
		view.setReportTitle("按学院汇总统计巡视情况");
		view.setSubTitle("时间范围：" + this.firstDay + " - " + this.lastDay);
		List<String[]> valueList = new ArrayList<String[]>();
		for(PatrolDetailEntity entity : list){
			String[] array = new String[3];
			array[0] = entity.getXsdw();
			array[1] = entity.getXsjls();
			array[2] = entity.getCzwts();
			valueList.add(array);
		}
		view.setValueList(valueList);
		return view;
	}
    
    /**
     * 取得部门
     * @return
     */
    private String getSelfDept() {
        DynaBean selfInfo = DynaBeanUtil.getPerson(getUser().getYhm());
        if (selfInfo == null) {
            return "";
        }
        String depId = (String)selfInfo.getValue("dwm");
        if (StringUtils.isEmpty(depId)) {
            return "";
        }
        List<Item> items = CodeUtil.getReverseItemList(ICodeConstants.DM_DEF_ORG, depId);
        return items.get(1).getGuid();
    }

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
     * @return the pageList
     */
    public PageList<PatrolEntity> getPageList() {
        return pageList;
    }

    /**
     * @param pageList the pageList to set
     */
    public void setPageList(PageList<PatrolEntity> pageList) {
        this.pageList = pageList;
    }

    /**
     * @return the query
     */
    public PatrolQuery getQuery() {
        return query;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(PatrolQuery query) {
        this.query = query;
    }

    /**
     * @return the globalids
     */
    public String[] getGlobalids() {
        return globalids;
    }

    /**
     * @param globalids the globalids to set
     */
    public void setGlobalids(String[] globalids) {
        this.globalids = globalids;
    }

	/**
	 * @return the globalid
	 */
	public String getGlobalid() {
		return globalid;
	}

	/**
	 * @param globalid the globalid to set
	 */
	public void setGlobalid(String globalid) {
		this.globalid = globalid;
	}

	/**
	 * @return the patrolDetailList
	 */
	public PageList<PatrolDetailEntity> getPatrolDetailList() {
		return patrolDetailList;
	}

	/**
	 * @param patrolDetailList the patrolDetailList to set
	 */
	public void setPatrolDetailList(PageList<PatrolDetailEntity> patrolDetailList) {
		this.patrolDetailList = patrolDetailList;
	}

	/**
	 * @return the detail
	 */
	public PatrolDetailEntity getDetail() {
		return detail;
	}

	/**
	 * @param detail the detail to set
	 */
	public void setDetail(PatrolDetailEntity detail) {
		this.detail = detail;
	}

	public List<PatrolDetailEntity> getList() {
		return list;
	}

	public void setList(List<PatrolDetailEntity> list) {
		this.list = list;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getFirstDay() {
		return firstDay;
	}

	public void setFirstDay(String firstDay) {
		this.firstDay = firstDay;
	}

	public String getLastDay() {
		return lastDay;
	}

	public void setLastDay(String lastDay) {
		this.lastDay = lastDay;
	}

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
    
}
