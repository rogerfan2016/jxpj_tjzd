package com.zfsoft.evaluation.action;

import java.util.HashMap;
import java.util.Map;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;

import com.zfsoft.dao.page.PageList;
import com.zfsoft.dataprivilege.util.DeptFilterUtil;
import com.zfsoft.evaluation.entity.CheckInSurveyQuery;
import com.zfsoft.evaluation.entity.CurriculumScheduleEntity;
import com.zfsoft.evaluation.entity.ObtainSemesterDate;
import com.zfsoft.evaluation.entity.OpenLessonSettingEntity;
import com.zfsoft.evaluation.entity.OpinionEntity;
import com.zfsoft.evaluation.entity.OpinionQuery;
import com.zfsoft.evaluation.entity.TeacherOpenLessonEntity;
import com.zfsoft.evaluation.entity.TeacherOpenLessonQuery;
import com.zfsoft.evaluation.service.IEvaluationService;
import com.zfsoft.hrm.baseinfo.code.util.CodeUtil;
import com.zfsoft.hrm.common.HrmAction;
import com.zfsoft.hrm.common.Message;
import com.zfsoft.hrm.config.ICodeConstants;
import com.zfsoft.hrm.core.util.DownloadFilenameUtil;
import com.zfsoft.util.base.StringUtil;
import com.zfsoft.util.date.DateTimeUtil;

/**
 * 教师互评
 * @author Administrator
 *
 */
public class TeacherEvaluationAction extends HrmAction {

    /**
     * 
     */
    private static final long serialVersionUID = -1478933630415033562L;
    
    private IEvaluationService evaluationService;
    private OpenLessonSettingEntity model;
    private OpenLessonSettingEntity query;
    private CheckInSurveyQuery cisQuery;
    private TeacherOpenLessonQuery tolQuery;
    private OpinionQuery opinionQuery;
    private PageList<OpenLessonSettingEntity> openLessonSettingList;
    private PageList<TeacherOpenLessonEntity> lessonList;
    private PageList<CurriculumScheduleEntity> openLessonList;
    private PageList<OpinionEntity> opinionList;
    private String globalid;
    private String status;
    private String phase;
    private String sortFieldName = null;
	private String asc = "up";
	private String bsh = "0";

    /*****************************Action****************************************/
    /**
     * 听课设置
     * @return
     */
    public String setOpenLesson() {
        if (query == null) {
            query = new OpenLessonSettingEntity();
        }
        openLessonSettingList = evaluationService.getOpenLessonSettingAll(query);
        return "setOpenLesson";
    }
    
    /**
     * 设置
     * @return
     */
    public String setting() {
        return "setting";
    }
    
    /**
     * 刷新
     * @return
     */
    public String refresh() {
        evaluationService.refreshSetting();
        
        getValueStack().set(DATA, getMessage());
        return DATA;
    }
    
    /**
     * 保存申请设置
     * @return
     */
    public String saveSetting() {
        evaluationService.saveSetting(model);
        
        getValueStack().set(DATA, getMessage());
        return DATA;
    }
    
    /**
     * 预约听课
     * @return
     */
    public String declareOpenLesson() {
        if (tolQuery == null) {
            tolQuery = new TeacherOpenLessonQuery();
        }
        tolQuery.setTkjsid(getUser().getYhm());
        if (query != null) {
            tolQuery.setPerPageSize(query.getPerPageSize());
            tolQuery.setToPage(query.getToPage());
        }
        lessonList = evaluationService.getDeclareOpenLesson(tolQuery);
        
        return "declareOpenLesson";
    }
    
    /**
     * 选择预约课程
     * @return
     */
    public String chooseOpenLesson() {
        OpenLessonSettingEntity entity = evaluationService.getOpenLessonSetting();
        
        if (entity == null) {
            getValueStack().set("isSet", "N");
            return "chooseOpenLesson";
        } else {
            getValueStack().set("isSet", "Y");
        }
        
        if (cisQuery == null) {
            cisQuery = new CheckInSurveyQuery();
        }
        cisQuery.setUserid(getUser().getYhm());
        cisQuery.setUserlx(getUser().getYhlx());
        
        cisQuery.setFirstDay(entity.getKcsjks());
        cisQuery.setLastDay(entity.getKcsjjs());
        cisQuery.setSfckzj("no");
        cisQuery.setCondition(getSearchSQLByChooseLesson());
        
        if (query != null) {
            cisQuery.setPerPageSize(query.getPerPageSize());
            cisQuery.setToPage(query.getToPage());
        }
        openLessonList = evaluationService.getOpenLessonList(cisQuery);
        getValueStack().set("bsh", bsh);
        return "chooseOpenLesson";
    }
    
    /**
     * 提交预约
     * @return
     */
    public String doSubmit() {
        Map<String, String> param = new HashMap<String, String>();
        param.put("globalid", globalid);
        param.put("tkjsid", getUser().getYhm());
        //判断预约的课程是否跟自己的课程冲突
        CurriculumScheduleEntity entity = evaluationService.getCurriculumScheduleById(globalid);
        if(entity != null){
        	param.put("kcsj", entity.getKcsj());
        	param.put("kcjc", entity.getKcjc());
        	int count = evaluationService.countCurriculumByParam(param);
        	if(count <= 0){
        		evaluationService.doSubmit(param);
        		if("1".equals(bsh)){
        			param.put("shzt", "3");
        	        evaluationService.changeStatus(param);
        		}
        		getValueStack().set(DATA, getMessage());
        	}else{
        		Message msg = new Message();
        		msg.setSuccess(false);
        		msg.setText("与自己的课程时间节次冲突，不能预约！");
        		getValueStack().set(DATA, msg);
        	}
        }
        return DATA;
    }
    
    /**
     * 取消预约
     * @return
     */
    public String cancelSubmit() {
        Map<String, String> param = new HashMap<String, String>();
        param.put("globalid", globalid);
        param.put("tkjsid", getUser().getYhm());
        evaluationService.cancelSubmit(param);

        getValueStack().set(DATA, getMessage());
        return DATA;
    }
    
    /**
     * 审核人删除预约记录
     * @return
     */
    public String delete() {
        Map<String, String> param = new HashMap<String, String>();
        param.put("globalid", globalid);
        evaluationService.cancelSubmit(param);

        getValueStack().set(DATA, getMessage());
        return DATA;
    }
    
    /**
     * 改变审核状态
     * @return
     */
    public String changeStatus() {
        Map<String, String> param = new HashMap<String, String>();
        param.put("globalid", globalid);
        param.put("shzt", status);
        param.put("tkjsid", getUser().getYhm());
        evaluationService.changeStatus(param);
        getValueStack().set(DATA, getMessage());
        return DATA;
    }
    
    /**
     * 听课管理
     * @return
     */
    public String auditOpenLesson() {
        if (tolQuery == null) {
            tolQuery = new TeacherOpenLessonQuery();
        }
        tolQuery.setPhase(phase);
        tolQuery.setCondition(getSearchSQLByAuditLesson());
        if (query != null) {
            tolQuery.setPerPageSize(query.getPerPageSize());
            tolQuery.setToPage(query.getToPage());
        }
        lessonList = evaluationService.getAuditOpenLesson(tolQuery);
        return "auditOpenLesson";
    }
    
    /**
     * 导出教师听课列表
     * @return
     * @throws Exception
     */
    public String export() throws Exception{
        getResponse().reset();
        getResponse().setCharacterEncoding("utf-8");
        getResponse().setContentType("application/vnd.ms-excel");
        String useragent = getRequest().getHeader("user-agent");
        String disposition = DownloadFilenameUtil.fileDisposition(useragent, DateTimeUtil.getCurrDateStr() + "-教师听课信息.xls");
        getResponse().setHeader("Content-Disposition", disposition);
        
        WritableWorkbook wwb = Workbook.createWorkbook(getResponse().getOutputStream());
        
        WritableSheet sheet = wwb.createSheet("教师听课信息", 1);
        tolQuery.setPerPageSize(Integer.MAX_VALUE);
        lessonList = evaluationService.getAuditOpenLesson(tolQuery);
        
        //产生表头
        sheet.addCell(generateTheadLabel(0, 0, "听课教师职工号"));
        sheet.addCell(generateTheadLabel(1, 0, "听课教师"));
        sheet.addCell(generateTheadLabel(2, 0, "上课时间"));
        sheet.addCell(generateTheadLabel(3, 0, "上课地点"));
        sheet.addCell(generateTheadLabel(4, 0, "星期"));
        sheet.addCell(generateTheadLabel(5, 0, "专业"));
        sheet.addCell(generateTheadLabel(6, 0, "课程名称"));
        sheet.addCell(generateTheadLabel(7, 0, "任课教师"));
        sheet.addCell(generateTheadLabel(8, 0, "课程节次"));
        
        //产生内容
        int y = 0;
        for(TeacherOpenLessonEntity h : lessonList){
            y++;
            sheet.addCell(generateValueLabel(0, y, h.getTkjsid()));
            sheet.addCell(generateValueLabel(1, y, h.getTkjsxm()));
            sheet.addCell(generateValueLabel(2, y, h.getCurriculum().getKcsj()));
            sheet.addCell(generateValueLabel(3, y, h.getCurriculum().getSkdd()));
            sheet.addCell(generateValueLabel(4, y, h.getDayofweek()));
            sheet.addCell(generateValueLabel(5, y, h.getCurriculum().getSszy()));
            sheet.addCell(generateValueLabel(6, y, h.getCurriculum().getKcmc()));
            sheet.addCell(generateValueLabel(7, y, h.getCurriculum().getRkls()));
            sheet.addCell(generateValueLabel(8, y, h.getCurriculum().getKcjc()));
        }
        wwb.write();
        wwb.close();
        return null;
    }
    
    /**
     * 听课查询
     * @return
     */
    public String active() {
        if (tolQuery == null) {
            tolQuery = new TeacherOpenLessonQuery();
        }
        String[] st = ObtainSemesterDate.getSchoolTime(tolQuery.getSchoolyear(), tolQuery.getSemester());
        tolQuery.setFirstDay(st[0]);
        tolQuery.setLastDay(st[1]);
        tolQuery.setCondition(getSearchSQLByOpenLesson("active"));
        if (query != null) {
            tolQuery.setPerPageSize(query.getPerPageSize());
            tolQuery.setToPage(query.getToPage());
        }
        lessonList = evaluationService.getSearchOpenLesson(tolQuery);
        return "active";
    }
    
    /**
     * 导出听课评价列表
     * @return
     * @throws Exception
     */
    public String exportActive() throws Exception{
        getResponse().reset();
        getResponse().setCharacterEncoding("utf-8");
        getResponse().setContentType("application/vnd.ms-excel");
        String useragent = getRequest().getHeader("user-agent");
        String disposition = DownloadFilenameUtil.fileDisposition(useragent, DateTimeUtil.getCurrDateStr() + "-教师听课评价信息.xls");
        getResponse().setHeader("Content-Disposition", disposition);
        
        WritableWorkbook wwb = Workbook.createWorkbook(getResponse().getOutputStream());
        
        WritableSheet sheet = wwb.createSheet("教师听课评价信息", 1);
        tolQuery.setPerPageSize(Integer.MAX_VALUE);
        tolQuery.setCondition(getSearchSQLByOpenLesson("active"));
        String[] st = ObtainSemesterDate.getSchoolTime(tolQuery.getSchoolyear(), tolQuery.getSemester());
        tolQuery.setFirstDay(st[0]);
        tolQuery.setLastDay(st[1]);
        lessonList = evaluationService.getSearchOpenLesson(tolQuery);
        
        //产生表头
        sheet.addCell(generateTheadLabel(0, 0, "年度"));
        sheet.addCell(generateTheadLabel(1, 0, "学期"));
        sheet.addCell(generateTheadLabel(2, 0, "专业"));
        sheet.addCell(generateTheadLabel(3, 0, "部门"));
        sheet.addCell(generateTheadLabel(4, 0, "听课教师工号"));
        sheet.addCell(generateTheadLabel(5, 0, "听课教师姓名"));
        sheet.addCell(generateTheadLabel(6, 0, "上课时间"));
        sheet.addCell(generateTheadLabel(7, 0, "星期"));
        sheet.addCell(generateTheadLabel(8, 0, "课程名称"));
        sheet.addCell(generateTheadLabel(9, 0, "任课教师"));
        sheet.addCell(generateTheadLabel(10, 0, "课程节次"));
        sheet.addCell(generateTheadLabel(11, 0, "授课内容"));
        sheet.addCell(generateTheadLabel(12, 0, "意见建议"));
        
        //产生内容
        int y = 0;
        for(TeacherOpenLessonEntity h : lessonList){
            y++;
            sheet.addCell(generateValueLabel(0, y, h.getSchoolyear()));
            sheet.addCell(generateValueLabel(1, y, ("0".equals(h.getSemester())? "上学期" : "下学期")));
            sheet.addCell(generateValueLabel(2, y, h.getCurriculum().getSszy()));
            sheet.addCell(generateValueLabel(3, y, StringUtils.isEmpty(h.getDept()) ? "" : CodeUtil.getItemValue(ICodeConstants.DM_DEF_ORG, h.getDept())));
            sheet.addCell(generateValueLabel(4, y, h.getTkjsid()));
            sheet.addCell(generateValueLabel(5, y, h.getTkjsxm()));
            sheet.addCell(generateValueLabel(6, y, h.getSksj()));
            sheet.addCell(generateValueLabel(7, y, h.getDayofweek()));
            sheet.addCell(generateValueLabel(8, y, h.getCurriculum().getKcmc()));
            sheet.addCell(generateValueLabel(9, y, h.getCurriculum().getRkls()));
            sheet.addCell(generateValueLabel(10, y, h.getCurriculum().getKcjc()));
            sheet.addCell(generateValueLabel(11, y, h.getSknr()));
            sheet.addCell(generateValueLabel(12, y, h.getYjjy()));
        }
        wwb.write();
        wwb.close();
        return null;
    }
   
    /**
     * 被听课查询
     * @return
     */
    public String passive() {
        if (tolQuery == null) {
            tolQuery = new TeacherOpenLessonQuery();
        }
        String[] st = ObtainSemesterDate.getSchoolTime(tolQuery.getSchoolyear(), tolQuery.getSemester());
        tolQuery.setFirstDay(st[0]);
        tolQuery.setLastDay(st[1]);
        tolQuery.setTableSql(" left join jxpj_jcsj_jsxx jsr on t1.rklsgh = jsr.zgh ");
        tolQuery.setCondition(getSearchSQLByOpenLesson("passive"));
        if (query != null) {
            tolQuery.setPerPageSize(query.getPerPageSize());
            tolQuery.setToPage(query.getToPage());
        }
        lessonList = evaluationService.getSearchOpenLesson(tolQuery);
        return "passive";
    }
    
    /**
     * 听课评价
     * @return
     */
    public String evaluation() {
        if (tolQuery == null) {
            tolQuery = new TeacherOpenLessonQuery();
        }
        
        if (StringUtils.isEmpty(tolQuery.getSfpj())) {
            tolQuery.setSfpj("0");
        }
        String[] st = ObtainSemesterDate.getSchoolTime(tolQuery.getSchoolyear(), tolQuery.getSemester());
        tolQuery.setFirstDay(st[0]);
        tolQuery.setLastDay(st[1]);
        String condition = " and t.tkjsid = '" + getUser().getYhm() + "' ";
        if (!StringUtil.isEmpty(tolQuery.getTkjsid())) {
            condition += " and t1.rklsgh like '%" + tolQuery.getTkjsid() + "%'";
        }
        
        if(!StringUtil.isEmpty(tolQuery.getTkjsxm())){
            condition += " and js.xm like '%" + tolQuery.getTkjsxm() + "%' ";
        }

        tolQuery.setCondition(condition);
        if (query != null) {
            tolQuery.setPerPageSize(query.getPerPageSize());
            tolQuery.setToPage(query.getToPage());
        }
        lessonList = evaluationService.getEvaluationOpenLesson(tolQuery);
        return "evaluation";
    }
    
//=================================================================================================================    
    /**
     * 查询条件
     * @return
     */
    private String getSearchSQLByChooseLesson() {
        String express = "";

        String deptFilter = DeptFilterUtil.getCondition("t", "kkxy");
        if (!StringUtil.isEmpty(deptFilter)) {
            express += " and (" + deptFilter + ")";
        }
    
        if (!StringUtil.isEmpty(cisQuery.getGh())) {
            express += " and t.rklsgh like '%" + cisQuery.getGh() + "%'";
        }
        
        if(!StringUtil.isEmpty(cisQuery.getXm())){
            express += " and js.xm like '%" + cisQuery.getXm() + "%' ";
        }
        
        if(!StringUtil.isEmpty(cisQuery.getKcmc())){
            express += " and t.kcmc like '%" + cisQuery.getKcmc() + "%' ";
        }
        
        if(!StringUtil.isEmpty(cisQuery.getDept())){
            express += " and t.kkxy in" +
                    " (select HRM_BZGL_ZZJGB.bmdm as dwm from HRM_BZGL_ZZJGB where HRM_BZGL_ZZJGB.bmdm = " + cisQuery.getDept()
                    +" or HRM_BZGL_ZZJGB.sjbmdm = " + cisQuery.getDept() + ")";
        }
        return express;
    }
    
    /**
     * 查询条件
     * @return
     */
    private String getSearchSQLByAuditLesson() {
        String express = "";
        // 数据范围条件
        String deptFilter = DeptFilterUtil.getCondition("t1", "kkxy");
        if (!StringUtil.isEmpty(deptFilter)) {
            express += " and (" + deptFilter + ")";
        }
        if(!StringUtil.isEmpty(tolQuery.getDept())){
            express += " and t1.kkxy in" +
                    " (select HRM_BZGL_ZZJGB.bmdm as dwm from HRM_BZGL_ZZJGB where HRM_BZGL_ZZJGB.bmdm = " + tolQuery.getDept()
                    +" or HRM_BZGL_ZZJGB.sjbmdm = " + tolQuery.getDept() + ")";
        }
        if (!StringUtil.isEmpty(tolQuery.getTkjsid())) {
            express += " and t.tkjsid like '%" + tolQuery.getTkjsid() + "%'";
        }
        
        if(!StringUtil.isEmpty(tolQuery.getTkjsxm())){
            express += " and js.xm like '%" + tolQuery.getTkjsxm() + "%' ";
        }
        
        if(!StringUtil.isEmpty(tolQuery.getZy())){
            express += " and zy.zymc like '%" + tolQuery.getZy() + "%' ";
        }
        
        if(!StringUtil.isEmpty(tolQuery.getKcmc())){
            express += " and t1.kcmc like '%" + tolQuery.getKcmc() + "%' ";
        }
        
        if(!StringUtil.isEmpty(tolQuery.getTkdd())){
            express += " and t1.skdd like '%" + tolQuery.getTkdd() + "%' ";
        }
        
        return express;
    }
    
    /**
     * 查询条件
     * @return
     */
    private String getSearchSQLByOpenLesson(String type) {
        String express = "";
        String deptFilter = "";
        if ("active".equals(type)) {
            if (!StringUtil.isEmpty(tolQuery.getTkjsid())) {
                express += " and t.tkjsid like '%" + tolQuery.getTkjsid() + "%'";
            }
            
            if(!StringUtil.isEmpty(tolQuery.getTkjsxm())){
                express += " and js.xm like '%" + tolQuery.getTkjsxm() + "%' ";
            }
            
            deptFilter = DeptFilterUtil.getCondition("t1", "kkxy");
            
            if(!StringUtil.isEmpty(tolQuery.getDept())){
                express += " and t1.kkxy in" +
                        " (select HRM_BZGL_ZZJGB.bmdm as dwm from HRM_BZGL_ZZJGB where HRM_BZGL_ZZJGB.bmdm = " + tolQuery.getDept()
                        +" or HRM_BZGL_ZZJGB.sjbmdm = " + tolQuery.getDept() + ")";
            }
        } else {
            if (!StringUtil.isEmpty(tolQuery.getTkjsid())) {
                express += " and t1.rklsgh like '%" + tolQuery.getTkjsid() + "%'";
            }
            
            if(!StringUtil.isEmpty(tolQuery.getTkjsxm())){
                express += " and jsr.xm like '%" + tolQuery.getTkjsxm() + "%' ";
            }
            
            deptFilter = DeptFilterUtil.getCondition("t1", "kkxy");
            
            if(!StringUtil.isEmpty(tolQuery.getDept())){
                express += " and t1.kkxy in" +
                        " (select HRM_BZGL_ZZJGB.bmdm as dwm from HRM_BZGL_ZZJGB where HRM_BZGL_ZZJGB.bmdm = " + tolQuery.getDept()
                        +" or HRM_BZGL_ZZJGB.sjbmdm = " + tolQuery.getDept() + ")";
            }
        }
        
        if(!StringUtil.isEmpty(tolQuery.getZy())){
            express += " and zy.zymc like '%" + tolQuery.getZy() + "%' ";
        }
        
        
        if (!StringUtil.isEmpty(deptFilter)) {
            express += " and (" + deptFilter + ")";
        }

        return express;
    }
    /**==================================意见反馈====================================*/
    public String searchOpinionList() {
    	//TODO
    	if (opinionQuery == null) {
    		opinionQuery = new OpinionQuery();
        }
    	if(!StringUtil.isEmpty(sortFieldName)){
			if(asc.equals("up")){
				opinionQuery.setOrderStr( sortFieldName );
			}else{
				opinionQuery.setOrderStr( sortFieldName +" desc");
			}
		}else{
			opinionQuery.setOrderStr( " BELONG_TO_SYS_NAME" );
		}
    	opinionList = evaluationService.getOpinionList(opinionQuery);
		int beginIndex = opinionList.getPaginator().getBeginIndex();
		getValueStack().set("beginIndex",beginIndex);
    	return "searchOpinionList";
    }
    
    /**==============================================================================*/
    /**
     * @return the evaluationService
     */
    public IEvaluationService getEvaluationService() {
        return evaluationService;
    }

    /**
     * @param evaluationService the evaluationService to set
     */
    public void setEvaluationService(IEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }
    
    /**
     * @return the model
     */
    public OpenLessonSettingEntity getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(OpenLessonSettingEntity model) {
        this.model = model;
    }

    /**
     * @return the openLessonSettingList
     */
    public PageList<OpenLessonSettingEntity> getOpenLessonSettingList() {
        return openLessonSettingList;
    }

    /**
     * @param openLessonSettingList the openLessonSettingList to set
     */
    public void setOpenLessonSettingList(
            PageList<OpenLessonSettingEntity> openLessonSettingList) {
        this.openLessonSettingList = openLessonSettingList;
    }

    /**
     * @return the query
     */
    public OpenLessonSettingEntity getQuery() {
        return query;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(OpenLessonSettingEntity query) {
        this.query = query;
    }

    /**
     * @return the cisQuery
     */
    public CheckInSurveyQuery getCisQuery() {
        return cisQuery;
    }

    /**
     * @param cisQuery the cisQuery to set
     */
    public void setCisQuery(CheckInSurveyQuery cisQuery) {
        this.cisQuery = cisQuery;
    }

    /**
     * @return the tolQuery
     */
    public TeacherOpenLessonQuery getTolQuery() {
        return tolQuery;
    }

    /**
     * @param tolQuery the tolQuery to set
     */
    public void setTolQuery(TeacherOpenLessonQuery tolQuery) {
        this.tolQuery = tolQuery;
    }

    public OpinionQuery getOpinionQuery() {
		return opinionQuery;
	}

	public void setOpinionQuery(OpinionQuery opinionQuery) {
		this.opinionQuery = opinionQuery;
	}

	/**
     * @return the lessonList
     */
    public PageList<TeacherOpenLessonEntity> getLessonList() {
        return lessonList;
    }

    /**
     * @param lessonList the lessonList to set
     */
    public void setLessonList(PageList<TeacherOpenLessonEntity> lessonList) {
        this.lessonList = lessonList;
    }

    /**
     * @return the openLessonList
     */
    public PageList<CurriculumScheduleEntity> getOpenLessonList() {
        return openLessonList;
    }

    /**
     * @param openLessonList the openLessonList to set
     */
    public void setOpenLessonList(PageList<CurriculumScheduleEntity> openLessonList) {
        this.openLessonList = openLessonList;
    }

    public PageList<OpinionEntity> getOpinionList() {
		return opinionList;
	}

	public void setOpinionList(PageList<OpinionEntity> opinionList) {
		this.opinionList = opinionList;
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the phase
     */
    public String getPhase() {
        return phase;
    }

    /**
     * @param phase the phase to set
     */
    public void setPhase(String phase) {
        this.phase = phase;
    }

	public String getSortFieldName() {
		return sortFieldName;
	}

	public void setSortFieldName(String sortFieldName) {
		this.sortFieldName = sortFieldName;
	}

	public String getAsc() {
		return asc;
	}

	public void setAsc(String asc) {
		this.asc = asc;
	}

	public String getBsh() {
		return bsh;
	}

	public void setBsh(String bsh) {
		this.bsh = bsh;
	}

}
