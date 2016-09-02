package com.zfsoft.evaluation.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.zfsoft.dao.page.PageList;
import com.zfsoft.dataprivilege.util.DeptFilterUtil;
import com.zfsoft.evaluation.entity.CheckInEntity;
import com.zfsoft.evaluation.entity.CheckInSurveyEntity;
import com.zfsoft.evaluation.entity.CheckInSurveyQuery;
import com.zfsoft.evaluation.entity.CurriculumScheduleEntity;
import com.zfsoft.evaluation.entity.ObtainSemesterDate;
import com.zfsoft.evaluation.entity.TeachingEntity;
import com.zfsoft.evaluation.entity.ViewQuestionnaireEntity;
import com.zfsoft.evaluation.service.IEvaluationService;
import com.zfsoft.hrm.common.HrmAction;
import com.zfsoft.hrm.core.util.DownloadFilenameUtil;
import com.zfsoft.hrm.report.entity.ReportView;
import com.zfsoft.orcus.lang.TimeUtil;
import com.zfsoft.util.base.StringUtil;
import com.zfsoft.util.date.DateTimeUtil;

/**
 * 实时评教
 * @author Administrator
 *
 */
public class StudentEvaluationAction extends HrmAction {

    /**
     * 
     */
    private static final long serialVersionUID = 4911828311265744060L;
    
    private IEvaluationService evaluationService;
    private TeachingEntity teachingEntity;
    private PageList<CheckInSurveyEntity> pageList;
    private PageList<CheckInEntity> list;
    private CheckInSurveyQuery query;
    private String week;
    private String firstDay;
    private String lastDay;
    private String globalid;
    private String mark;
    private String type;
    private String kcid;
    private int skc;
    private int kcs;

    /*****************************Action****************************************/
    /**
     * 查询课程表
     * @return
     */
    public String curriculum() {
        
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
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userlx", getUser().getYhlx());
        param.put("userid", getUser().getYhm());
        param.put("firstDay", firstDay);
        param.put("lastDay", lastDay);
        
        // 查询
        CurriculumScheduleEntity[][] curriculumSchedule = evaluationService.getCurriculumSchedule(param);
        
        // 页面显示行数
        int showCnt = curriculumSchedule.length;
        if (showCnt % 2 > 0) {
            getValueStack().set("trCnt", showCnt / 2 + 1);
        } else {
            getValueStack().set("trCnt", showCnt / 2);
        }
        getValueStack().set("totalCnt", showCnt);
        getValueStack().set("nowdayofweek", dayofweek - 1);
        getValueStack().set("curriculumSchedule", curriculumSchedule);
        getValueStack().set("mon", TimeUtil.format(TimeUtil.addDay(firstDay, 1), "yyyy-MM-dd"));
        getValueStack().set("tues", TimeUtil.format(TimeUtil.addDay(firstDay, 2), "yyyy-MM-dd"));
        getValueStack().set("wed", TimeUtil.format(TimeUtil.addDay(firstDay, 3), "yyyy-MM-dd"));
        getValueStack().set("thurs", TimeUtil.format(TimeUtil.addDay(firstDay, 4), "yyyy-MM-dd"));
        getValueStack().set("fri", TimeUtil.format(TimeUtil.addDay(firstDay, 5), "yyyy-MM-dd"));
        getValueStack().set("now", now);
        
        return "curriculum";
    }
    
    /**
     * 选择课程
     * @return
     */
    public String chooseCurriculum() {
        String kcsj = TimeUtil.format(TimeUtil.addDay(firstDay, Integer.parseInt(mark)), "yyyy-MM-dd");
        List<String> jcs = new ArrayList<String>();
        for (int i = 0; i < kcs; i++) {
            jcs.add(String.valueOf(skc * 2 + 1 + i));
        }
        
        Map<String, Object> param = new HashMap<String, Object>();
          param.put("userlx", getUser().getYhlx());
          param.put("userid", getUser().getYhm());
          param.put("kcid", kcid);
          param.put("jcs", jcs);
          param.put("firstDay", kcsj);
          param.put("lastDay", kcsj);
          List<CurriculumScheduleEntity> curriculumSchedule = evaluationService.getCurriculumScheduleByKcid(param);
          List<TeachingEntity> teachingEntities = new ArrayList<TeachingEntity>();
          TeachingEntity te = new TeachingEntity();
          for (CurriculumScheduleEntity e: curriculumSchedule) {
              te = evaluationService.getTeachingById(e.getGlobalid());
              te.setKcsj(kcsj);
              teachingEntities.add(te);
          }
      
          getValueStack().set("teachingEntities", teachingEntities);
          getValueStack().set("lx", getUser().getYhlx());
        return "chooseCurriculum";
    }
    
    /**
     * 课程详细
     */
    public String curriculumDetail() {

        teachingEntity = evaluationService.getTeachingById(globalid);
        getValueStack().set("lx", getUser().getYhlx());
        getValueStack().set("type", type);
        
        if ("student".equals(getUser().getYhlx())) {
            return "curriculumDetail";
        }
        
        Map<String, String> param = new HashMap<String, String>();
        param.put("globalid", globalid);
        int cnt = evaluationService.getEvaluationCnt(param);
        if (cnt == 0) {
            getValueStack().set("mes", "还没有录入考勤，不能发起评教");
            return "curriculumDetail";
        }
        
        param.put("table", " , jxpj_sspj_xspj t2 ");
        param.put("condition", " and t1.pjid = t2.pjid ");
        cnt = evaluationService.getEvaluationCnt(param);
        if (cnt > 0) {
            getValueStack().set("mes", "不能重复发起评教");
            return "curriculumDetail";
        }
        getValueStack().set("mes", "已有考勤记录，可以发起评教");
        return "curriculumDetail";
    }
    
    /**
     * 保存教学日志
     * @return
     */
    public String saveTeachingLog() {
        evaluationService.saveTeachingLog(teachingEntity);
        
        getValueStack().set(DATA, getMessage());
        return DATA;
    }
    
    /**
     * 录入考勤
     * @return
     */
    public String saveCheckIn() {
    	// 录入考勤
        evaluationService.saveCheckIn(teachingEntity);
        // 生成学生评价记录
        evaluationService.sendStudentEvaluation(teachingEntity);
        getValueStack().set(DATA, getMessage());
        return DATA;
    }
    
    /**
     * 发起评教
     * @return
     */
    public String sendEvaluation() {
    	// 生成学生评价记录
        evaluationService.sendStudentEvaluation(teachingEntity);
        // 生成教师评价记录
        evaluationService.sendTeacherEvaluation(teachingEntity);
        getValueStack().set(DATA, getMessage());
        return DATA;
    }
    
    /**
     * 上课考勤管理
     * @return
     */
    public String checkin() {
        checkWorkList(false);
        return "checkin";
    }
    
    /**
     * 按学院统计考勤管理
     * @return
     */
	public String checkinCollege() {
		if (query == null) {
			query = new CheckInSurveyQuery();
		}
		buildSearchDate();
		query.setSfckzj("yes");
		pageList = evaluationService.getCheckInByCollege(query);
		this.fullBarDataXML(this.getReportView(pageList), "各院系平均出勤率","%",false);
		getValueStack().set("pageList", pageList);
		return "checkinCollege";
	}
    
    /**
     * 按学生考勤统计管理
     * @return
     */
    public String checkinAll(){
    	String dateStr = "";
    	if(query == null){
    		query = new CheckInSurveyQuery();
    	}
    	if(StringUtil.isNotEmpty(query.getMonth())){
    		dateStr = query.getMonth()+"-01";
    	}else{
    		dateStr = DateTimeUtil.getCurrDateStr();
    		query.setMonth(StringUtil.substring(dateStr, 0, 7));
    	}
    	query.setKcsjFrom(DateTimeUtil.getFirstDayOfMonth(DateTimeUtil.formatEndTime(dateStr)));
		query.setKcsjTo(DateTimeUtil.getLastDayOfMonth(DateTimeUtil.formatEndTime(dateStr)));
    	query.setCondition(this.getCondition());	
        pageList = evaluationService.getStudentAttendanceList(query);
        return "checkinAll";
    }
    
    /**
     * 按学生查询考勤明细
     * @return
     */
    public String checkinDetail(){
    	if(query != null){
    		// 只能查询教师本人所教课程的考勤情况
        	query.setGh(this.getUser().getYhm());	
        	list = evaluationService.getNoCheckinDetail(query);
    	}
        return "checkinDetail";
    }
    
    /**
     * 按班级考勤统计管理
     * @return
     */
    public String checkinAllByClass(){
    	if(query == null){
    		query = new CheckInSurveyQuery();
    	}
    	if(StringUtil.isEmpty(query.getMonth())){
    		query.setMonth(DateTimeUtil.getFormatDate(DateTimeUtil.getCurrDate(), "yyyy-MM"));
    	}
    	if(StringUtil.isNotEmpty(query.getMonth())){
    		String dateStr = query.getMonth()+"-01";
    		query.setKcsjFrom(DateTimeUtil.getFirstDayOfMonth(DateTimeUtil.formatEndTime(dateStr)));
    		query.setKcsjTo(DateTimeUtil.getLastDayOfMonth(DateTimeUtil.formatEndTime(dateStr)));
    	}
    	query.setCondition(this.getCondition());
    	pageList = evaluationService.getCheckSummaryListByClass(query);
        return "checkinAllByClass";
    }
    
    /**
     * 导出按班级考勤统计列表
     * @return
     * @throws Exception
     */
    public String exportCheckinAllByClass() throws Exception{
        getResponse().reset();
        getResponse().setCharacterEncoding("utf-8");
        getResponse().setContentType("application/vnd.ms-excel");
        String useragent = getRequest().getHeader("user-agent");
        String disposition = DownloadFilenameUtil.fileDisposition(useragent, "按班级考勤统计信息.xls");
        getResponse().setHeader("Content-Disposition", disposition);
        
        WritableWorkbook wwb = Workbook.createWorkbook(getResponse().getOutputStream());
        
        WritableSheet sheet = wwb.createSheet("按班级考勤统计信息", 1);
        query.setPerPageSize(Integer.MAX_VALUE);
        if(StringUtil.isEmpty(query.getMonth())){
    		query.setMonth(DateTimeUtil.getFormatDate(DateTimeUtil.getCurrDate(), "yyyy-MM"));
    	}
    	if(StringUtil.isNotEmpty(query.getMonth())){
    		String dateStr = query.getMonth()+"-01";
    		query.setKcsjFrom(DateTimeUtil.getFirstDayOfMonth(DateTimeUtil.formatEndTime(dateStr)));
    		query.setKcsjTo(DateTimeUtil.getLastDayOfMonth(DateTimeUtil.formatEndTime(dateStr)));
    	}
    	query.setCondition(this.getCondition());
    	pageList = evaluationService.getCheckSummaryListByClass(query);
        
        //产生表头
        sheet.addCell(generateTheadLabel(0, 0, "班级"));
        sheet.addCell(generateTheadLabel(1, 0, "学院"));
        sheet.addCell(generateTheadLabel(2, 0, "专业"));
        sheet.addCell(generateTheadLabel(3, 0, "考勤次数"));
        sheet.addCell(generateTheadLabel(4, 0, "出勤次数"));
        sheet.addCell(generateTheadLabel(5, 0, "旷课次数"));
        sheet.addCell(generateTheadLabel(6, 0, "事假次数"));
        sheet.addCell(generateTheadLabel(7, 0, "病假次数"));
        sheet.addCell(generateTheadLabel(8, 0, "迟到次数"));
        sheet.addCell(generateTheadLabel(9, 0, "出勤比例"));
        sheet.addCell(generateTheadLabel(10, 0, "缺勤比例"));

        //产生内容
        int y = 0;
        for(CheckInSurveyEntity h : pageList){
            y++;
            sheet.addCell(generateValueLabel(0, y, h.getXzb()));
            sheet.addCell(generateValueLabel(1, y, h.getXy()));
            sheet.addCell(generateValueLabel(2, y, h.getZy()));
            sheet.addCell(generateValueLabel(3, y, h.getKqcs()));
            sheet.addCell(generateValueLabel(4, y, h.getCqcs()));
            sheet.addCell(generateValueLabel(5, y, h.getKkcs()));
            sheet.addCell(generateValueLabel(6, y, h.getSjcs()));
            sheet.addCell(generateValueLabel(7, y, h.getBjcs()));
            sheet.addCell(generateValueLabel(8, y, h.getCdcs()));
            sheet.addCell(generateValueLabel(9, y, h.getCqbl()));
            sheet.addCell(generateValueLabel(10, y, h.getQqbl()));
        }
        wwb.write();
        wwb.close();
        return null;
    }
    
    /**
     * 按班级课程统计缺勤学生
     * @return
     */
    public String summaryAbsentByClass(){
    	if(query == null){
    		query = new CheckInSurveyQuery();
    	}
    	if(StringUtil.isEmpty(query.getMonth())){
    		query.setMonth(DateTimeUtil.getFormatDate(DateTimeUtil.getCurrDate(), "yyyy-MM"));
    	}
    	if(StringUtil.isNotEmpty(query.getMonth())){
    		String dateStr = query.getMonth()+"-01";
    		query.setKcsjFrom(DateTimeUtil.getFirstDayOfMonth(DateTimeUtil.formatEndTime(dateStr)));
    		query.setKcsjTo(DateTimeUtil.getLastDayOfMonth(DateTimeUtil.formatEndTime(dateStr)));
    	}
    	query.setCondition(this.getCondition());
    	pageList = evaluationService.getSummaryAbsentListByClass(query);
        return "summaryAbsentByClass";
    }
    
    /**
     * 导出按班级课程统计缺勤学生列表
     * @return
     * @throws Exception
     */
    public String exportSummaryAbsentByClass() throws Exception{
        getResponse().reset();
        getResponse().setCharacterEncoding("utf-8");
        getResponse().setContentType("application/vnd.ms-excel");
        String useragent = getRequest().getHeader("user-agent");
        String disposition = DownloadFilenameUtil.fileDisposition(useragent, "按班级课程统计缺勤学生信息.xls");
        getResponse().setHeader("Content-Disposition", disposition);
        
        WritableWorkbook wwb = Workbook.createWorkbook(getResponse().getOutputStream());
        
        WritableSheet sheet = wwb.createSheet("按班级课程统计缺勤学生信息", 1);
        query.setPerPageSize(Integer.MAX_VALUE);
        if(StringUtil.isEmpty(query.getMonth())){
    		query.setMonth(DateTimeUtil.getFormatDate(DateTimeUtil.getCurrDate(), "yyyy-MM"));
    	}
    	if(StringUtil.isNotEmpty(query.getMonth())){
    		String dateStr = query.getMonth()+"-01";
    		query.setKcsjFrom(DateTimeUtil.getFirstDayOfMonth(DateTimeUtil.formatEndTime(dateStr)));
    		query.setKcsjTo(DateTimeUtil.getLastDayOfMonth(DateTimeUtil.formatEndTime(dateStr)));
    	}
    	query.setCondition(this.getCondition());
    	pageList = evaluationService.getSummaryAbsentListByClass(query);
        
        //产生表头
        sheet.addCell(generateTheadLabel(0, 0, "学院"));
        sheet.addCell(generateTheadLabel(1, 0, "班级"));
        sheet.addCell(generateTheadLabel(2, 0, "课程名称"));
        sheet.addCell(generateTheadLabel(3, 0, "教师姓名"));
        sheet.addCell(generateTheadLabel(4, 0, "课程总学时"));
        sheet.addCell(generateTheadLabel(5, 0, "学生缺勤情况"));

        //产生内容
        int y = 0;
        for(CheckInSurveyEntity h : pageList){
            y++;
            sheet.addCell(generateValueLabel(0, y, h.getXy()));
            sheet.addCell(generateValueLabel(1, y, h.getXzb()));
            sheet.addCell(generateValueLabel(2, y, h.getKcmc()));
            sheet.addCell(generateValueLabel(3, y, h.getJsxm()));
            sheet.addCell(generateValueLabel(4, y, h.getKczxs()+"学时"));
            sheet.addCell(generateValueLabel(5, y, h.getQqxs()));
        }
        wwb.write();
        wwb.close();
        return null;
    }
    
    /**
     * 按年级考勤统计管理
     * @return
     */
    public String checkinAllByGrade(){
    	if(query == null){
    		query = new CheckInSurveyQuery();
    	}
    	if(StringUtil.isEmpty(query.getMonth())){
    		query.setMonth(DateTimeUtil.getFormatDate(DateTimeUtil.getCurrDate(), "yyyy-MM"));
    	}
    	if(StringUtil.isNotEmpty(query.getMonth())){
    		String dateStr = query.getMonth()+"-01";
    		query.setKcsjFrom(DateTimeUtil.getFirstDayOfMonth(DateTimeUtil.formatEndTime(dateStr)));
    		query.setKcsjTo(DateTimeUtil.getLastDayOfMonth(DateTimeUtil.formatEndTime(dateStr)));
    	}
//    	query.setCondition(this.getCondition());
    	pageList = evaluationService.getCheckSummaryListByGrade(query);
        return "checkinAllByGrade";
    }
    
    private void buildSearchDate(){
    	 int dayofweek = TimeUtil.dayOfWeek();
         String now = TimeUtil.current("yyyy-MM-dd");
    	   // 上一周
        if ("topweek".equals(week)) {
            firstDay = TimeUtil.format(TimeUtil.addDay(firstDay, -7), "yyyy-MM-dd");
            lastDay = TimeUtil.format(TimeUtil.addDay(lastDay, -7), "yyyy-MM-dd");
            query.setFirstDay(firstDay);
            query.setLastDay(lastDay);
            week = "hold";
        // 下一周
        } else if ("nextweek".equals(week)) {
            firstDay = TimeUtil.format(TimeUtil.addDay(firstDay, 7), "yyyy-MM-dd");
            lastDay = TimeUtil.format(TimeUtil.addDay(lastDay, 7), "yyyy-MM-dd");
            query.setFirstDay(firstDay);
            query.setLastDay(lastDay);
            week = "hold";
        // 本周
        } else if ("nowweek".equals(week)) {
            firstDay = TimeUtil.format(TimeUtil.addDay(now, (1 - dayofweek)), "yyyy-MM-dd");
            lastDay = TimeUtil.format(TimeUtil.addDay(now, (7 - dayofweek)), "yyyy-MM-dd");
            query.setFirstDay(firstDay);
            query.setLastDay(lastDay);
            week = "hold";
        } else if ("hold".equals(week)) {
        	query.setFirstDay(firstDay);
        	query.setLastDay(lastDay);
        } else {
            firstDay = TimeUtil.format(TimeUtil.addDay(now, (1 - dayofweek)), "yyyy-MM-dd");
            lastDay = TimeUtil.format(TimeUtil.addDay(now, (7 - dayofweek)), "yyyy-MM-dd");
           // pquery = query;
            String[] st = ObtainSemesterDate.getSchoolTime(query.getSchoolyear(), query.getSemester());
            //pquery.setFirstDay(st[0]);
            //pquery.setLastDay(st[1]);
            query.setFirstDay(st[0]);
            query.setLastDay(st[1]);
           
//            if(st[0] == null && st[0] == null  ){
//           	 query.setFirstDay(firstDay);
//           	 query.setLastDay(lastDay);
//            }else{
//           	 query.setFirstDay(st[0]);
//                query.setLastDay(st[1]);
//            }
        }
    }
    
    /**
     * 上课考勤统计
     * @return
     */
    public String checkinStatistics() {
        checkWorkList(true);
        return "checkinStatistics";
    }

	private void checkWorkList(boolean isAllData) {
		//CheckInSurveyQuery pquery = new CheckInSurveyQuery();
        int dayofweek = TimeUtil.dayOfWeek();
        String now = TimeUtil.current("yyyy-MM-dd");
        
        if (query == null) {
            query = new CheckInSurveyQuery();
        }
        // 判断是否查询全部数据
        if(!isAllData){
        	query.setUserid(getUser().getYhm());
            query.setUserlx(getUser().getYhlx());
            
           // pquery.setUserid(getUser().getYhm());
           // pquery.setUserlx(getUser().getYhlx());
        }
        //pquery.setToPage(query.getToPage());
        //pquery.setPerPageSize(query.getPerPageSize());
        // 上一周
        if ("topweek".equals(week)) {
            firstDay = TimeUtil.format(TimeUtil.addDay(firstDay, -7), "yyyy-MM-dd");
            lastDay = TimeUtil.format(TimeUtil.addDay(lastDay, -7), "yyyy-MM-dd");
            query.setFirstDay(firstDay);
            query.setLastDay(lastDay);
            week = "hold";
        // 下一周
        } else if ("nextweek".equals(week)) {
            firstDay = TimeUtil.format(TimeUtil.addDay(firstDay, 7), "yyyy-MM-dd");
            lastDay = TimeUtil.format(TimeUtil.addDay(lastDay, 7), "yyyy-MM-dd");
            query.setFirstDay(firstDay);
            query.setLastDay(lastDay);
            week = "hold";
        // 本周
        } else if ("nowweek".equals(week)) {
            firstDay = TimeUtil.format(TimeUtil.addDay(now, (1 - dayofweek)), "yyyy-MM-dd");
            lastDay = TimeUtil.format(TimeUtil.addDay(now, (7 - dayofweek)), "yyyy-MM-dd");
            query.setFirstDay(firstDay);
            query.setLastDay(lastDay);
            week = "hold";
        } else if ("hold".equals(week)) {
        	query.setFirstDay(firstDay);
        	query.setLastDay(lastDay);
        } else {
            firstDay = TimeUtil.format(TimeUtil.addDay(now, (1 - dayofweek)), "yyyy-MM-dd");
            lastDay = TimeUtil.format(TimeUtil.addDay(now, (7 - dayofweek)), "yyyy-MM-dd");
           // pquery = query;
            String[] st = ObtainSemesterDate.getSchoolTime(query.getSchoolyear(), query.getSemester());
            //pquery.setFirstDay(st[0]);
            //pquery.setLastDay(st[1]);
            query.setFirstDay(st[0]);
            query.setLastDay(st[1]);
        }
        query.setSfckzj("yes");
        pageList = evaluationService.getCheckInSurveyEntities(query);
	}
    
    /**
     * 展示考勤
     * @return
     */
    public String showkq() {
        List<CheckInEntity> kqs = new ArrayList<CheckInEntity>();
        Map<String, String> param = new HashMap<String, String>();
        param.put("globalid", globalid);
        param.put("type", type);
        kqs = evaluationService.getEvaluationDetail(param);
        getValueStack().set("beans", kqs);
        
        return "showkq";
    }
    
    /**
     * 展示评教
     * @return
     */
    public String showpj() {
        List<CheckInEntity> kqs = new ArrayList<CheckInEntity>();
        Map<String, String> param = new HashMap<String, String>();
        param.put("globalid", globalid);
        param.put("type", "sj");
        kqs = evaluationService.getEvaluationDetail(param);
        getValueStack().set("beans", kqs);
        
        return "showpj";
    }
    
    /**
     * 教学日志管理
     */
    public String teachinglogset() {

        checkWorkList(false);
        return "teachinglogset";
    }
    
    /**
     * 修改教学日志
     * @return
     */
    public String teachingLodDetail() {
        teachingEntity = evaluationService.getTeachingById(globalid);
        return "teachingLodDetail";
    }
    /**
     * 教学日志查询
     */
    public String teachinglogview() {
        if (query == null) {
            query = new CheckInSurveyQuery();
        }

        String[] st = ObtainSemesterDate.getSchoolTime(query.getSchoolyear(), query.getSemester());
        query.setFirstDay(st[0]);
        query.setLastDay(st[1]);
        query.setCondition(getSearchSQL());
        
        pageList = evaluationService.getTeachingLog(query);
        return "teachinglogview";
    }
    
    /**
     * 学生实时评教
     */
    public String evaluation() {

        if (query == null) {
            query = new CheckInSurveyQuery();
        }
        query.setUserid(getUser().getYhm());
        query.setUserlx(getUser().getYhlx());
        
        pageList = evaluationService.getEvaluations(query);
        return "evaluation";
    }
    
    /**
     * 评教查询统计
     */
    public String statistics() {
        Map<String, String> param = new HashMap<String, String>();
//        param.put("rklsgh", getUser().getYhm());
        List<ViewQuestionnaireEntity> questionnaires = evaluationService.getMyEvaluation(param);
        getValueStack().set("beans", questionnaires);
        return "statistics";
    }
    
    /**
     * 查询条件
     * @return
     */
    private String getSearchSQL() {
        String express = "";

        String deptFilter = DeptFilterUtil.getCondition("js", "bm");
        if (!StringUtil.isEmpty(deptFilter)) {
            express += " and (" + deptFilter + ")";
        }
    
        if (!StringUtil.isEmpty(query.getGh())) {
            express += " and t.rklsgh like '%" + query.getGh() + "%'";
        }
        
        if(!StringUtil.isEmpty(query.getXm())){
            express += " and js.xm like '%" + query.getXm() + "%' ";
        }
        
        if(!StringUtil.isEmpty(query.getDept())){
            express += " and js.bm in" +
                    " (select HRM_BZGL_ZZJGB.bmdm as dwm from HRM_BZGL_ZZJGB where HRM_BZGL_ZZJGB.bmdm = " + query.getDept()
                    +" or HRM_BZGL_ZZJGB.sjbmdm = " + query.getDept() + ")";
        }
        return express;
    }
    
    /**
     * 查询条件
     * @return
     */
    private String getCondition() {
        String express = "";
        String deptFilter = DeptFilterUtil.getCondition("t", "kkxybm");
        if(!StringUtil.isEmpty(deptFilter)){
            express += " and (" + deptFilter + ")";
        }
        return express;
    }
    
    /**
     * 
     * @param query
     * @return
     */
    private ReportView getReportView(PageList<CheckInSurveyEntity> pageList) {
		ReportView view = new ReportView();
		view.setReportTitle("各院系平均出勤率");
		List<String[]> valueList = new ArrayList<String[]>();
		for(CheckInSurveyEntity entity : pageList){
			String[] array = new String[2];
			array[0] = entity.getCqbl();
			array[1] = entity.getDept();
			valueList.add(array);
		}
		view.setValueList(valueList);
		return view;
	}

/********************************************************************************************************************/
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
     * @return the week
     */
    public String getWeek() {
        return week;
    }

    /**
     * @param week the week to set
     */
    public void setWeek(String week) {
        this.week = week;
    }

    /**
     * @return the firstDay
     */
    public String getFirstDay() {
        return firstDay;
    }

    /**
     * @param firstDay the firstDay to set
     */
    public void setFirstDay(String firstDay) {
        this.firstDay = firstDay;
    }

    /**
     * @return the lastDay
     */
    public String getLastDay() {
        return lastDay;
    }

    /**
     * @param lastDay the lastDay to set
     */
    public void setLastDay(String lastDay) {
        this.lastDay = lastDay;
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
     * @return the teachingEntity
     */
    public TeachingEntity getTeachingEntity() {
        return teachingEntity;
    }

    /**
     * @param teachingEntity the teachingEntity to set
     */
    public void setTeachingEntity(TeachingEntity teachingEntity) {
        this.teachingEntity = teachingEntity;
    }

    /**
     * @return the mark
     */
    public String getMark() {
        return mark;
    }

    /**
     * @param mark the mark to set
     */
    public void setMark(String mark) {
        this.mark = mark;
    }

    /**
     * @return the pageList
     */
    public PageList<CheckInSurveyEntity> getPageList() {
        return pageList;
    }

    /**
     * @param pageList the pageList to set
     */
    public void setPageList(PageList<CheckInSurveyEntity> pageList) {
        this.pageList = pageList;
    }

    /**
     * @return the query
     */
    public CheckInSurveyQuery getQuery() {
        return query;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(CheckInSurveyQuery query) {
        this.query = query;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the kcid
     */
    public String getKcid() {
        return kcid;
    }

    /**
     * @param kcid the kcid to set
     */
    public void setKcid(String kcid) {
        this.kcid = kcid;
    }

    /**
     * @return the skc
     */
    public int getSkc() {
        return skc;
    }

    /**
     * @param skc the skc to set
     */
    public void setSkc(int skc) {
        this.skc = skc;
    }

    /**
     * @return the kcs
     */
    public int getKcs() {
        return kcs;
    }

    /**
     * @param kcs the kcs to set
     */
    public void setKcs(int kcs) {
        this.kcs = kcs;
    }

	public PageList<CheckInEntity> getList() {
		return list;
	}

	public void setList(PageList<CheckInEntity> list) {
		this.list = list;
	}
    
}
