package com.zfsoft.evaluation.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibm.icu.util.Calendar;
import com.zfsoft.dao.page.PageList;
import com.zfsoft.dao.page.Paginator;
import com.zfsoft.evaluation.dao.daointerface.IEvaluationDao;
import com.zfsoft.evaluation.entity.CheckInEntity;
import com.zfsoft.evaluation.entity.CheckInSurveyEntity;
import com.zfsoft.evaluation.entity.CheckInSurveyQuery;
import com.zfsoft.evaluation.entity.CurriculumScheduleEntity;
import com.zfsoft.evaluation.entity.EvaluationConnEntity;
import com.zfsoft.evaluation.entity.EvaluationType;
import com.zfsoft.evaluation.entity.OpenLessonSettingEntity;
import com.zfsoft.evaluation.entity.OpinionEntity;
import com.zfsoft.evaluation.entity.OpinionQuery;
import com.zfsoft.evaluation.entity.SettingEntity;
import com.zfsoft.evaluation.entity.SettingQuery;
import com.zfsoft.evaluation.entity.TeacherOpenLessonEntity;
import com.zfsoft.evaluation.entity.TeacherOpenLessonQuery;
import com.zfsoft.evaluation.entity.TeachingEntity;
import com.zfsoft.evaluation.entity.ViewQuestionnaireEntity;
import com.zfsoft.evaluation.service.IEvaluationService;
import com.zfsoft.evaluation.utils.EvaluationUtils;
import com.zfsoft.hrm.core.exception.RuleException;
import com.zfsoft.orcus.lang.TimeUtil;
import com.zfsoft.util.base.StringUtil;
import com.zfsoft.wjdc.dao.entites.WjglModel;
import com.zfsoft.wjdc.service.svcinterface.IQuestionNaireService;

/**
 * 
 * @author Administrator
 *
 */
public class EvaluationServiceImpl implements IEvaluationService {

    private IEvaluationDao evaluationDao;
    private IQuestionNaireService questionNaireService;

    /**
     * @return the evaluationDao
     */
    public IEvaluationDao getEvaluationDao() {
        return evaluationDao;
    }

    /**
     * @param evaluationDao the evaluationDao to set
     */
    public void setEvaluationDao(IEvaluationDao evaluationDao) {
        this.evaluationDao = evaluationDao;
    }
    
    /**
     * @return the questionNaireService
     */
    public IQuestionNaireService getQuestionNaireService() {
        return questionNaireService;
    }

    /**
     * @param questionNaireService the questionNaireService to set
     */
    public void setQuestionNaireService(IQuestionNaireService questionNaireService) {
        this.questionNaireService = questionNaireService;
    }

    @Override
	public int getTeachingWeek(String dateStr) {
		return evaluationDao.getTeachingWeek(dateStr);
	}

	/**
     * 查询课表
     */
    @Override
    public CurriculumScheduleEntity[][] getCurriculumSchedule(Map<String, Object> param) {
        int maxJc = evaluationDao.getMaxJc(param);
        
        if (maxJc <= 8) {
            maxJc = 8;
        }
        
        // 课程表
        List<CurriculumScheduleEntity> retData = evaluationDao.getCurriculumSchedule(param);
        CurriculumScheduleEntity[][] ret = new CurriculumScheduleEntity[maxJc][7];
        int jc;
        int rq;
        for (CurriculumScheduleEntity cs : retData) {
            jc = Integer.parseInt(cs.getKcjc()) - 1;
            rq = getDateMark(cs.getKcsj());
            ret[jc][rq] = cs;
        }
        
        return ret;
    }
    
    /**
     * 查询课表
     */
    @Override
    public CurriculumScheduleEntity[] getCurriculumScheduleByDay(Map<String, Object> param) {
        
        int maxJc = evaluationDao.getMaxJc(param);
        
        if (maxJc <= 8) {
            maxJc = 8;
        }
        // 课程表
        List<CurriculumScheduleEntity> retData = evaluationDao.getCurriculumSchedule(param);
        
        CurriculumScheduleEntity[] ret = new CurriculumScheduleEntity[maxJc];
        int jc;
        for (CurriculumScheduleEntity cs : retData) {
            jc = Integer.parseInt(cs.getKcjc()) - 1;
            ret[jc] = cs;
        }
        
        return ret;
    }

    /**
     * 查询课表
     */
    @Override
    public TeachingEntity getTeachingById(String globalid) {
        TeachingEntity teachingEntity = evaluationDao.getTeachingById(globalid);
        
        if (teachingEntity != null) {
            teachingEntity.setXss(evaluationDao.getStudents(teachingEntity));
        }
        return teachingEntity;
    }
    
    /**
     * 查询课表
     */
    @Override
    public List<CurriculumScheduleEntity> getCurriculumScheduleByKcid(Map<String, Object> param) {
        return evaluationDao.getCurriculumSchedule(param);
    }
    
    @Override
	public CurriculumScheduleEntity getCurriculumScheduleById(String globalid) {
		return evaluationDao.getCurriculumScheduleById(globalid);
	}

	@Override
	public int countCurriculumByParam(Map<String, String> param) {
		return evaluationDao.countCurriculumByParam(param);
	}

	/**
     * 保存教学日志
     */
    @Override
    public void saveTeachingLog(TeachingEntity teachingEntity) {
        int cnt = evaluationDao.getTeachingLogById(teachingEntity.getGlobalid());
        
        if (cnt > 0) {
            evaluationDao.modifyTeachingLog(teachingEntity);
        } else {
            evaluationDao.insertTeachingLog(teachingEntity);
        }
    }

	/**
     * 考勤录入
     */
    @Override
    public void saveCheckIn(TeachingEntity teachingEntity) {
        int cnt = 0;
        Map<String, String> param = new HashMap<String, String>();
        param.put("globalid", teachingEntity.getGlobalid());
        param.put("table", " , jxpj_sspj_xspj t2 ");
        param.put("condition", " and t1.pjid = t2.pjid ");
        int ecnt = evaluationDao.getXsEvaluationCnt(param);
        
        if (ecnt > 0) {
            throw new RuleException("已经发起评教，不能再录入考勤");
        }
        
        for (CheckInEntity xs : teachingEntity.getXss()) {
            xs.setGlobalid(teachingEntity.getGlobalid());
            xs.setKcid(teachingEntity.getKcid());
            xs.setSksj(teachingEntity.getKcsj());
            xs.setPjid(teachingEntity.getGlobalid()+xs.getXsid());
            
            cnt = evaluationDao.getCheckIn(xs);
            if (cnt > 0) {
                evaluationDao.modifyCheckIn(xs);
            } else {
                evaluationDao.insertCheckIn(xs);
            }
        }
    }

    @Override
    public void sendStudentEvaluation(TeachingEntity teachingEntity) {
        String globalid = teachingEntity.getGlobalid();
        String rklsgh = teachingEntity.getRklsgh();
        Map<String, String> param = new HashMap<String, String>();
        SettingQuery query = new SettingQuery();
        List<SettingEntity> questionnaires = new ArrayList<SettingEntity>();
        String[] memberId = null;
        EvaluationConnEntity ec = new EvaluationConnEntity();
        param.put("globalid", globalid);
        param.put("table", " , jxpj_sspj_xspj t2 ");
        param.put("condition", " and t1.pjid = t2.pjid ");
        int cnt1 = evaluationDao.getXsEvaluationCnt(param);
        
        // 判断是否生成学生评价记录
        if (cnt1 <= 0) {
            //throw new RuleException("不能重复发起评教");
	        try {
	            // 查询参与评教的人员信息
	            List<String> sspjinfo = evaluationDao.getSspjInfo(globalid);
	            // 查询评教问卷
	            questionnaires.clear();
	            query.setPerPageSize(Integer.MAX_VALUE);
	            query.setPjlx(EvaluationType.SSPJ.getKeyStr());
	            query.setDjrylx("0");
	            questionnaires = this.getQuestionnaires(query);
	            int i = 0;
	            // 遍历
	            memberId = new String[sspjinfo.size()];
	            for (String sspj : sspjinfo) {
	                memberId[i] = sspj;
	                i++;
	            }
	            
	            for (SettingEntity e : questionnaires) {
	                ec.setCondition(" and t1.kqqk = '0' ");
	                ec.setGlobalid(globalid);
	                ec.setTableSql(" jxpj_sspj_skkqglb ");
	                ec.setWjid(e.getWjid());
	                ec.setXwjid(globalid+"0"+rklsgh);
	                ec.setPjrylx("0");
	                
	                evaluationDao.addPjgx(ec);
	                questionNaireService.doDistribute(e.getWjid(), ec.getXwjid(), e.getWjmc(), memberId, "student", "", "");
	            }
	            
	            evaluationDao.insertXsEvaluation(globalid);
	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
        }
    }
    
    @Override
	public void sendTeacherEvaluation(TeachingEntity teachingEntity) {
    	String globalid = teachingEntity.getGlobalid();
    	String rklsgh = teachingEntity.getRklsgh();
        Map<String, String> param = new HashMap<String, String>();
        SettingQuery query = new SettingQuery();
        List<SettingEntity> questionnaires = new ArrayList<SettingEntity>();
        String[] memberId = null;
        param.clear();
        param.put("globalid", globalid);
        int cnt1 = evaluationDao.getJsEvaluationCnt(param);
        param.put("table", " , jxpj_jshp_tkpj t2 ");
        param.put("condition", " and t1.pjid = t2.pjid ");
        int cnt2 = evaluationDao.getJsEvaluationCnt(param);
        
        // 判断是否有听课教师记录和评价记录
        if (cnt1 > 0 && cnt2 == 0) {
            try {
            	// 查询参与评教的人员信息
                List<String> jshpinfo = evaluationDao.getJshpInfo(globalid);
                EvaluationConnEntity ec = new EvaluationConnEntity();
                // 查询评教问卷
                questionnaires.clear();
                // 获取课程分类
                String kcfl = evaluationDao.getKcfl(teachingEntity.getKcid());
                if(StringUtil.isEmpty(kcfl)){
                	kcfl = EvaluationType.JSHP.getKeyStr();
                }
                query.setPjlx(kcfl);
                query.setDjrylx("1");
                questionnaires = this.getQuestionnaires(query);
                
                int i = 0;
                // 遍历
                memberId = new String[jshpinfo.size()];
                for (String jshp : jshpinfo) {
                    memberId[i] = jshp;
                    i++;
                }
                
                for (SettingEntity e : questionnaires) {
                    ec.setCondition(" and t1.shzt = '3' ");
                    ec.setGlobalid(globalid);
                    ec.setTableSql(" jxpj_jshp_tkgl ");
                    ec.setWjid(e.getWjid());
                    ec.setXwjid(globalid+"1"+rklsgh);
                    ec.setPjrylx("1");
                    
                    evaluationDao.addPjgx(ec);
                    questionNaireService.doDistribute(e.getWjid(), ec.getXwjid(), e.getWjmc(), memberId, "teacher", "", "");
                }
                evaluationDao.insertJsEvaluation(globalid);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
	}

	public List<String> getCollegeList(){
    	return evaluationDao.getCollegeList();
    }

    /**
     * 按学院统计考勤
     */
    @Override
    public PageList<CheckInSurveyEntity> getCheckInByCollege(CheckInSurveyQuery query) {
    	
    	PageList<CheckInSurveyEntity> pageList = new PageList<CheckInSurveyEntity>();
    	Paginator paginator = new Paginator();
    	//按学院考勤人数
    	List<CheckInSurveyEntity> list = evaluationDao.getEvaluationCountByCollege(query);
    	
    	 for (CheckInSurveyEntity checkInSurveyEntity : list) {
             // 评教状态
             if (checkInSurveyEntity.getPjcyrs() > 0) {
                 checkInSurveyEntity.setSffqpj("已发起");
             } else {
                 checkInSurveyEntity.setSffqpj("未发起");
             }
             pageList.add(checkInSurveyEntity);
    	 }
    	 
    	 paginator.setItemsPerPage(query.getPerPageSize());
         paginator.setPage((Integer) query.getToPage());

         paginator.setItems(list.size());
         pageList.setPaginator(paginator);
         
         if (list.size() == 0) {
             return pageList;
         }
         
         return pageList;
    	
    }

	/* （非 Javadoc）
	* <p>Title: getStudentAttendanceList</p>
	* <p>Description: </p>
	* @param query
	* @return
	* @see com.zfsoft.evaluation.service.IEvaluationService#getStudentAttendanceList(com.zfsoft.evaluation.entity.CheckInSurveyQuery)
	*/
	@Override
	public PageList<CheckInSurveyEntity> getStudentAttendanceList(
			CheckInSurveyQuery query) {
		PageList<CheckInSurveyEntity> pageList = new PageList<CheckInSurveyEntity>();
        Paginator paginator = new Paginator();
        if(query != null){
            paginator.setItemsPerPage(query.getPerPageSize());
            paginator.setPage((Integer)query.getToPage());
            
            paginator.setItems(evaluationDao.getStudentAttendanceCount(query));
            pageList.setPaginator(paginator);
            if(paginator.getBeginIndex() <= paginator.getItems()){
                query.setStartRow(paginator.getBeginIndex());
                query.setEndRow(paginator.getEndIndex());
                pageList.addAll(evaluationDao.getStudentAttendanceList(query));
            }
        }
        return pageList;
	}

	@Override
	public PageList<CheckInEntity> getNoCheckinDetail(
			CheckInSurveyQuery query) {
		PageList<CheckInEntity> pageList = new PageList<CheckInEntity>();
        Paginator paginator = new Paginator();
        if(query != null){
            paginator.setItemsPerPage(query.getPerPageSize());
            paginator.setPage((Integer)query.getToPage());
            
            paginator.setItems(evaluationDao.getNoCheckinDetailCount(query));
            pageList.setPaginator(paginator);
            if(paginator.getBeginIndex() <= paginator.getItems()){
                query.setStartRow(paginator.getBeginIndex());
                query.setEndRow(paginator.getEndIndex());
                pageList.addAll(evaluationDao.getNoCheckinDetail(query));
            }
        }
        return pageList;
	}

	@Override
	public PageList<CheckInSurveyEntity> getSummaryAbsentListByClass(
			CheckInSurveyQuery query) {
		PageList<CheckInSurveyEntity> pageList = new PageList<CheckInSurveyEntity>();
        Paginator paginator = new Paginator();
        if(query != null){
            paginator.setItemsPerPage(query.getPerPageSize());
            paginator.setPage((Integer)query.getToPage());
            
            paginator.setItems(evaluationDao.getSummaryAbsentCountByClass(query));
            pageList.setPaginator(paginator);
            if(paginator.getBeginIndex() <= paginator.getItems()){
                query.setStartRow(paginator.getBeginIndex());
                query.setEndRow(paginator.getEndIndex());
                pageList.addAll(evaluationDao.getSummaryAbsentListByClass(query));
            }
        }
        return pageList;
	}

	@Override
	public PageList<CheckInSurveyEntity> getCheckSummaryListByClass(
			CheckInSurveyQuery query) {
		PageList<CheckInSurveyEntity> pageList = new PageList<CheckInSurveyEntity>();
        Paginator paginator = new Paginator();
        if(query != null){
            paginator.setItemsPerPage(query.getPerPageSize());
            paginator.setPage((Integer)query.getToPage());
            
            paginator.setItems(evaluationDao.getCheckSummaryCountByClass(query));
            pageList.setPaginator(paginator);
            if(paginator.getBeginIndex() <= paginator.getItems()){
                query.setStartRow(paginator.getBeginIndex());
                query.setEndRow(paginator.getEndIndex());
                pageList.addAll(evaluationDao.getCheckSummaryListByClass(query));
            }
        }
        return pageList;
	}

	@Override
	public PageList<CheckInSurveyEntity> getCheckSummaryListByGrade(
			CheckInSurveyQuery query) {
		PageList<CheckInSurveyEntity> pageList = new PageList<CheckInSurveyEntity>();
        Paginator paginator = new Paginator();
        if(query != null){
            paginator.setItemsPerPage(query.getPerPageSize());
            paginator.setPage((Integer)query.getToPage());
            
            paginator.setItems(evaluationDao.getCheckSummaryCountByGrade(query));
            pageList.setPaginator(paginator);
            if(paginator.getBeginIndex() <= paginator.getItems()){
                query.setStartRow(paginator.getBeginIndex());
                query.setEndRow(paginator.getEndIndex());
                pageList.addAll(evaluationDao.getCheckSummaryListByGrade(query));
            }
        }
        return pageList;
	}

	/**
     * 上课考勤管理
     */
    @Override
    public PageList<CheckInSurveyEntity> getCheckInSurveyEntities(CheckInSurveyQuery query) {

        PageList<CheckInSurveyEntity> pageList = new PageList<CheckInSurveyEntity>();
        List <CheckInSurveyEntity> temp = new ArrayList<CheckInSurveyEntity>();
        Paginator paginator = new Paginator();
        
        List<CurriculumScheduleEntity> retData = evaluationDao.getCheckInSurveyEntities(query);

        CheckInSurveyEntity checkInSurveyEntity;
        
        // 遍历课程
        for (CurriculumScheduleEntity e : retData) {
            checkInSurveyEntity = new CheckInSurveyEntity();
            checkInSurveyEntity.setCurriculum(e);
            // 星期
            checkInSurveyEntity.setDayofweek(EvaluationUtils.getDayOfWeek(e.getKcsj()));
            Map<String, BigDecimal> counts = null;
            if(StringUtil.isNotBlank(query.getDept())){
            	List<CheckInSurveyEntity> list = evaluationDao.getEvaluationCountByCollege(query);
            	if(list != null && list.size()>0){
            		checkInSurveyEntity = list.get(0);
            	}
            	// 评教状态
                if (checkInSurveyEntity.getPjcyrs() > 0) {
                    checkInSurveyEntity.setSffqpj("已发起");
                } else {
                    checkInSurveyEntity.setSffqpj("未发起");
                }
            }
            else{
            	// 考勤人数
            	counts = this.getEvaluationCount(e.getGlobalid());
            	checkInSurveyEntity.setDept(counts.get("KKXY")==null?"":counts.get("KKXY").toString());
                checkInSurveyEntity.setYcqrs(counts.get("YCQRS").intValue());
                checkInSurveyEntity.setSjcqrs(counts.get("SJCQRS").intValue());
                checkInSurveyEntity.setQqrs(counts.get("QQRS").intValue());
                // 评教状态
                if (counts.get("PJCYRS").intValue() > 0) {
                    checkInSurveyEntity.setSffqpj("已发起");
                } else {
                    checkInSurveyEntity.setSffqpj("未发起");
                }
                checkInSurveyEntity.setPjcyrs(counts.get("PJCYRS").intValue());
            }
            temp.add(checkInSurveyEntity);
        }
        paginator.setItemsPerPage(query.getPerPageSize());
        paginator.setPage((Integer) query.getToPage());

        paginator.setItems(temp.size());
        pageList.setPaginator(paginator);
        
        if (temp.size() == 0) {
            return pageList;
        }

        if (paginator.getBeginIndex() <= paginator.getItems()) {
            pageList.addAll(temp.subList(paginator.getBeginIndex() - 1, paginator.getEndIndex()));
        }

        return pageList;
    }
    
    /**
     * 查询评教数目
     */
    @Override
    public Map<String, BigDecimal> getEvaluationCount(String globalid) {
        return evaluationDao.getEvaluationCount(globalid);
    }
    
    /**
     * 查询考勤和评教
     */
    @Override
    public List<CheckInEntity> getEvaluationDetail(Map<String, String> param) {
        return evaluationDao.getEvaluationDetail(param);
    }
    
    /**
     * 教学日志查询
     */
    @Override
    public PageList<CheckInSurveyEntity> getTeachingLog(CheckInSurveyQuery query) {
        PageList<CheckInSurveyEntity> pageList = new PageList<CheckInSurveyEntity>();
        List <CheckInSurveyEntity> temp = new ArrayList<CheckInSurveyEntity>();
        Paginator paginator = new Paginator();
        
        List<CurriculumScheduleEntity> retData = evaluationDao.getTeachingLog(query);
        CheckInSurveyEntity checkInSurveyEntity;
        // 遍历课程
        for (CurriculumScheduleEntity e : retData) {
            checkInSurveyEntity = new CheckInSurveyEntity();
            checkInSurveyEntity.setCurriculum(e);
            // 星期
            checkInSurveyEntity.setDayofweek(EvaluationUtils.getDayOfWeek(e.getKcsj()));
            String[] semester = getYearAndSemester(query, e.getKcsj());
            // 年度
            checkInSurveyEntity.setSchoolYear(semester[0]);
            // 学期
            checkInSurveyEntity.setSemester(semester[1]);

            temp.add(checkInSurveyEntity);
        }
        paginator.setItemsPerPage(query.getPerPageSize());
        paginator.setPage((Integer) query.getToPage());

        paginator.setItems(temp.size());
        pageList.setPaginator(paginator);
        
        if (temp.size() == 0) {
            return pageList;
        }

        if (paginator.getBeginIndex() <= paginator.getItems()) {
            pageList.addAll(temp.subList(paginator.getBeginIndex() - 1, paginator.getEndIndex()));
        }

        return pageList;
    }
    
    /**
     * 查询评教
     */
    @Override
    public PageList<CheckInSurveyEntity> getEvaluations(CheckInSurveyQuery query) {
        PageList<CheckInSurveyEntity> pageList = new PageList<CheckInSurveyEntity>();
        List <CheckInSurveyEntity> temp = new ArrayList<CheckInSurveyEntity>();
        Paginator paginator = new Paginator();
        
        List<CurriculumScheduleEntity> retData = evaluationDao.getTeachingLog(query);
        // 评教
        List<CheckInEntity> kqs = new ArrayList<CheckInEntity>();
        Map<String, String> param = new HashMap<String, String>();
        param.put("userid", query.getUserid());
        param.put("pjqk", query.getPjqk());

        kqs = evaluationDao.getStudentEvaluations(param);

        CheckInSurveyEntity checkInSurveyEntity;
        
        for (CheckInEntity kq : kqs) {
            for (CurriculumScheduleEntity ce : retData) {
                if (kq.getGlobalid().equals(ce.getGlobalid())) {
                    checkInSurveyEntity = new CheckInSurveyEntity();
                    checkInSurveyEntity.setCurriculum(ce);
                    checkInSurveyEntity.setCheckInEntity(kq);
                    checkInSurveyEntity.setDayofweek(EvaluationUtils.getDayOfWeek(ce.getKcsj()));
                    temp.add(checkInSurveyEntity);
                    break;
                }
            }
        }
        
        paginator.setItemsPerPage(query.getPerPageSize());
        paginator.setPage((Integer) query.getToPage());

        paginator.setItems(temp.size());
        pageList.setPaginator(paginator);
        
        if (temp.size() == 0) {
            return pageList;
        }

        if (paginator.getBeginIndex() <= paginator.getItems()) {
            pageList.addAll(temp.subList(paginator.getBeginIndex() - 1, paginator.getEndIndex()));
        }
        return pageList;
    }

    /**
     * 评教记录数
     */
    @Override
    public int getEvaluationCnt(Map<String, String> param) {
        return evaluationDao.getXsEvaluationCnt(param);
    }
    

    /**
     * 保存申请听课设置
     */
    @Override
    public void saveSetting(OpenLessonSettingEntity model) {
        
        Date f = TimeUtil.toDate(model.getSqsjks());
        Date t = TimeUtil.toDate(model.getSqsjjs());
        Date now = TimeUtil.toDate(TimeUtil.current("yyyy-MM-dd"));
        
        if (t.compareTo(f) < 0) {
            throw new RuleException("听课申请结束时间不能早于开始时间");
        }
        
        if (t.compareTo(now) < 0) {
            throw new RuleException("听课申请结束时间不能早于今日");
        }
        
        f = TimeUtil.toDate(model.getKcsjks());
        t = TimeUtil.toDate(model.getKcsjjs());
        
        if (t.compareTo(f) < 0) {
            throw new RuleException("预约课程结束时间不能早于开始时间");
        }
        
        if (t.compareTo(now) < 0) {
            throw new RuleException("预约课程结束时间不能早于今日");
        }
        
        evaluationDao.modifyStatusBySave();
        evaluationDao.saveSetting(model);
    }

    /**
     * 查询申请听课设置
     */
    @Override
    public PageList<OpenLessonSettingEntity> getOpenLessonSettingAll(OpenLessonSettingEntity query) {
        PageList<OpenLessonSettingEntity> pageList = new PageList<OpenLessonSettingEntity>();
        Paginator paginator = new Paginator();
        if(query != null){
            paginator.setItemsPerPage(query.getPerPageSize());
            paginator.setPage((Integer)query.getToPage());
            
            paginator.setItems(evaluationDao.getOpenLessonSettingCount(query));
            pageList.setPaginator(paginator);
            if(paginator.getBeginIndex() <= paginator.getItems()){
                query.setStartRow(paginator.getBeginIndex());
                query.setEndRow(paginator.getEndIndex());
                pageList.addAll(evaluationDao.getOpenLessonSettingAll(query));
            }
        }
        return pageList;
    }

    /**
     * 刷新
     */
    @Override
    public void refreshSetting() {
        OpenLessonSettingEntity entity = this.getOpenLessonSetting();
        
        Date t = TimeUtil.toDate(entity.getSqsjjs());
        Date now = TimeUtil.toDate(TimeUtil.current("yyyy-MM-dd"));
        
        if (t.compareTo(now) < 0) {
            evaluationDao.modifyStatusByRefresh();
        }
    }

    /**
     * 查询申请听课设置
     */
    @Override
    public OpenLessonSettingEntity getOpenLessonSetting() {
        return evaluationDao.getOpenLessonSetting();
    }
    
    /**
     * 预约听课列表
     */
    @Override
    public PageList<TeacherOpenLessonEntity> getDeclareOpenLesson(TeacherOpenLessonQuery query) {
        PageList<TeacherOpenLessonEntity> pageList = new PageList<TeacherOpenLessonEntity>();
        Paginator paginator = new Paginator();
        if(query != null){
            paginator.setItemsPerPage(query.getPerPageSize());
            paginator.setPage((Integer)query.getToPage());
            
            paginator.setItems(evaluationDao.getDeclareOpenLessonCount(query));
            pageList.setPaginator(paginator);
            if(paginator.getBeginIndex() <= paginator.getItems()){
                query.setStartRow(paginator.getBeginIndex());
                query.setEndRow(paginator.getEndIndex());
                pageList.addAll(evaluationDao.getDeclareOpenLesson(query));
            }
        }
        
        for (TeacherOpenLessonEntity ope : pageList) {
            ope.setDayofweek(EvaluationUtils.getDayOfWeek(ope.getCurriculum().getKcsj()));
            String[] semester = getYearAndSemester(new CheckInSurveyQuery(), ope.getCurriculum().getKcsj());
            ope.setSchoolyear(semester[0]);
            ope.setSemester(semester[1]);
        }
        return pageList;
    }
    
    /**
     * 开放预约听课列表
     */
    @Override
    public PageList<CurriculumScheduleEntity> getOpenLessonList(CheckInSurveyQuery query) {
        PageList<CurriculumScheduleEntity> pageList = new PageList<CurriculumScheduleEntity>();
        Paginator paginator = new Paginator();
        if(query != null){
            paginator.setItemsPerPage(query.getPerPageSize());
            paginator.setPage((Integer)query.getToPage());
            
            paginator.setItems(evaluationDao.getOpenLessonCount(query));
            pageList.setPaginator(paginator);
            if(paginator.getBeginIndex() <= paginator.getItems()){
                query.setStartRow(paginator.getBeginIndex());
                query.setEndRow(paginator.getEndIndex());
                pageList.addAll(evaluationDao.getOpenLesson(query));
            }
        }
        
        for (CurriculumScheduleEntity ope : pageList) {
            ope.setDayofweek(EvaluationUtils.getDayOfWeek(ope.getKcsj()));
            String[] semester = getYearAndSemester(new CheckInSurveyQuery(), ope.getKcsj());
            ope.setSchoolyear(semester[0]);
            ope.setSemester(semester[1]);
        }
        return pageList;
    }
    
    /**
     * 提交预约
     */
    @Override
    public void doSubmit(Map<String, String> param) {
        evaluationDao.doSubmit(param);
    }

    /**
     * 改变审核状态
     */
    @Override
    public void changeStatus(Map<String, String> param) {
        evaluationDao.changeStatus(param);
    }

    /**
     * 取消预约
     */
    @Override
    public void cancelSubmit(Map<String, String> param) {
        evaluationDao.cancelSubmit(param);
    }

    /**
     * 听课管理
     */
    @Override
    public PageList<TeacherOpenLessonEntity> getAuditOpenLesson(TeacherOpenLessonQuery query) {
        PageList<TeacherOpenLessonEntity> pageList = new PageList<TeacherOpenLessonEntity>();
        Paginator paginator = new Paginator();
        if(query != null){
            paginator.setItemsPerPage(query.getPerPageSize());
            paginator.setPage((Integer)query.getToPage());
            
            paginator.setItems(evaluationDao.getAuditOpenLessonCount(query));
            pageList.setPaginator(paginator);
            if(paginator.getBeginIndex() <= paginator.getItems()){
                query.setStartRow(paginator.getBeginIndex());
                query.setEndRow(paginator.getEndIndex());
                pageList.addAll(evaluationDao.getAuditOpenLesson(query));
            }
        }
        
        for (TeacherOpenLessonEntity ope : pageList) {
            ope.setDayofweek(EvaluationUtils.getDayOfWeek(ope.getCurriculum().getKcsj()));
        }
        return pageList;
    }
    
    /**
     * 听课查询
     */
    @Override
    public PageList<TeacherOpenLessonEntity> getSearchOpenLesson(TeacherOpenLessonQuery query) {
        PageList<TeacherOpenLessonEntity> pageList = new PageList<TeacherOpenLessonEntity>();
        Paginator paginator = new Paginator();
        if(query != null){
            paginator.setItemsPerPage(query.getPerPageSize());
            paginator.setPage((Integer)query.getToPage());
            
            paginator.setItems(evaluationDao.getSearchOpenLessonCount(query));
            pageList.setPaginator(paginator);
            if(paginator.getBeginIndex() <= paginator.getItems()){
                query.setStartRow(paginator.getBeginIndex());
                query.setEndRow(paginator.getEndIndex());
                pageList.addAll(evaluationDao.getSearchOpenLesson(query));
            }
        }
        
        for (TeacherOpenLessonEntity ope : pageList) {
            ope.setDayofweek(EvaluationUtils.getDayOfWeek(ope.getCurriculum().getKcsj()));
            String[] semester = getYearAndSemester(new CheckInSurveyQuery(), ope.getCurriculum().getKcsj());
            ope.setSchoolyear(semester[0]);
            ope.setSemester(semester[1]);
        }
        return pageList;
    }
    
    /**
     * 听课评价
     */
    @Override
    public PageList<TeacherOpenLessonEntity> getEvaluationOpenLesson(TeacherOpenLessonQuery query) {
        PageList<TeacherOpenLessonEntity> pageList = new PageList<TeacherOpenLessonEntity>();
        Paginator paginator = new Paginator();
        if(query != null){
            paginator.setItemsPerPage(query.getPerPageSize());
            paginator.setPage((Integer)query.getToPage());
            
            paginator.setItems(evaluationDao.getEvaluationOpenLessonCount(query));
            pageList.setPaginator(paginator);
            if(paginator.getBeginIndex() <= paginator.getItems()){
                query.setStartRow(paginator.getBeginIndex());
                query.setEndRow(paginator.getEndIndex());
                pageList.addAll(evaluationDao.getEvaluationOpenLesson(query));
            }
        }
        
        for (TeacherOpenLessonEntity ope : pageList) {
            ope.setDayofweek(EvaluationUtils.getDayOfWeek(ope.getCurriculum().getKcsj()));
            String[] semester = getYearAndSemester(new CheckInSurveyQuery(), ope.getCurriculum().getKcsj());
            ope.setSchoolyear(semester[0]);
            ope.setSemester(semester[1]);
        }
        return pageList;
    }

    /**
     * 取得问卷
     */
    @Override
    public PageList<SettingEntity> getQuestionnaires(
            SettingQuery query) {
        PageList<SettingEntity> pageList = new PageList<SettingEntity>();
        Paginator paginator = new Paginator();
        if(query != null){
            paginator.setItemsPerPage(query.getPerPageSize());
            paginator.setPage((Integer)query.getToPage());
            
            paginator.setItems(evaluationDao.getQuestionnairesCount(query));
            pageList.setPaginator(paginator);
            if(paginator.getBeginIndex() <= paginator.getItems()){
                query.setStartRow(paginator.getBeginIndex());
                query.setEndRow(paginator.getEndIndex());
                pageList.addAll(evaluationDao.getQuestionnaires(query));
            }
        }

        return pageList;
    }

    /**
     * 取得问卷
     */
    @Override
    public SettingEntity getQuestionnaireById(String globalid) {
        return evaluationDao.getQuestionnaireById(globalid);
    }

    /**
     * 增加问卷
     */
    @Override
    public void saveQuestionnaire(SettingEntity settingEntity) {
        evaluationDao.saveQuestionnaire(settingEntity);
    }

    /**
     * 修改问卷
     */
    @Override
    public void modifyQuestionnaire(SettingEntity settingEntity) {
        evaluationDao.modifyQuestionnaire(settingEntity);
    }
    
    /**
     * 存在判断
     */
    @Override
    public boolean isExistSetting(SettingEntity m) {
        int cnt = evaluationDao.isExistSetting(m);
        
        if (cnt > 0) {
            return true;
        }
        return false;
    }
    
    /**
     * 取得问卷
     */
    @Override
    public List<ViewQuestionnaireEntity> getViewQuestionnaires(String pjid) {
        String pjrylx = evaluationDao.getPjrylx(pjid);
        ViewQuestionnaireEntity entity = new ViewQuestionnaireEntity();
        entity.setPjid(pjid);
        if ("0".equals(pjrylx)) {
            entity.setPjryfiled(" ,t1.xsid as pjryid, t2.globalid ");
            entity.setTableSql(" ,jxpj_sspj_xspj t1, jxpj_sspj_skkqglb t2 ");
        } else if ("1".equals(pjrylx)) {
            entity.setPjryfiled(" ,t1.tkjsid as pjryid, t2.globalid ");
            entity.setTableSql(" ,jxpj_jshp_tkpj t1, jxpj_jshp_tkgl t2 ");
        }
        
        List<ViewQuestionnaireEntity> temp = evaluationDao.getQuestionnaireByPjid(entity);
        
        try {
            WjglModel info;
            for (ViewQuestionnaireEntity m : temp) {
                info = questionNaireService.getYhdjxx(m.getXwjid(), m.getPjryid());
    
                if ("已答卷".equals(info.getDjzt())) {
                    m.setPjzt("1");
                } else {
                    m.setPjzt("0");
                }
                evaluationDao.updateWjzt(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return temp;
    }

    /**
     * 我的评价
     */
    @Override
    public List<ViewQuestionnaireEntity> getMyEvaluation(Map<String, String> param) {
        List<ViewQuestionnaireEntity> mes = evaluationDao.getMyEvaluation(param);
        
        for (ViewQuestionnaireEntity m : mes) {
            m.setPjcnt(questionNaireService.getModelSummer(m.getXwjid()));
        }
        
        return mes;
    }

    /**
     * 问卷调查
     */
    @Override
    public List<ViewQuestionnaireEntity> getMyResponse(Map<String, String> param) {
        return evaluationDao.getMyResponse(param);
    }
    
    /**
     * 保存意见
     */
    @Override
    public void saveOpinion(Map<String, String> param) {
        evaluationDao.saveOpinion(param);
    }
    
    /**
     * 取得下标
     * @param date
     * @return
     */
    private int getDateMark(String date) {
        if (EvaluationUtils.isDate(date)) {
            return TimeUtil.toCalendar(date).get(Calendar.DAY_OF_WEEK) - 1;
        } else {
            return Integer.parseInt(date);
        }
    }

    /**
     * 取得学期和年度
     * @param query
     * @param kcsj
     * @return
     */
    private String[] getYearAndSemester(CheckInSurveyQuery query, String kcsj) {
        String[] ret = new String[2];
        String preYear = TimeUtil.format(TimeUtil.addYear(kcsj, -1), "yyyy-MM-dd").substring(0, 4);
        String thisYear = kcsj.substring(0, 4);
        String datef = "";
        String datet = "";
        
        if (!StringUtil.isEmpty(query.getSchoolyear())) {
            ret[0] = query.getSchoolyear();
            if (!StringUtil.isEmpty(query.getSemester())) {
                ret[1] = query.getSemester();
            } else {
                datef = TimeUtil.format(query.getSchoolyear() + "-09-01", "yyyy-MM-dd");
                datet = TimeUtil.format(TimeUtil.addDay(TimeUtil.addYear(query.getSchoolyear() + "-03-01", 1), -1), "yyyy-MM-dd");
                
                if (TimeUtil.toDate(datef).before(TimeUtil.toDate(kcsj)) && TimeUtil.toDate(datet).after(TimeUtil.toDate(kcsj))) {
                    ret[1] = "0";
                    return ret;
                }
                
                datef = TimeUtil.format(TimeUtil.addYear(query.getSchoolyear() + "-03-01", 1), "yyyy-MM-dd");
                datet = TimeUtil.format(TimeUtil.addYear(query.getSchoolyear() + "-08-31", 1), "yyyy-MM-dd");
                if (TimeUtil.toDate(datef).before(TimeUtil.toDate(kcsj)) && TimeUtil.toDate(datet).after(TimeUtil.toDate(kcsj))) {
                    ret[1] = "1";
                    return ret;
                }
            }
        } else {
            datef = TimeUtil.format(preYear + "-09-01", "yyyy-MM-dd");
            datet = TimeUtil.format(TimeUtil.addDay(TimeUtil.addYear(preYear + "-03-01", 1), -1), "yyyy-MM-dd");
            if (TimeUtil.toDate(datef).before(TimeUtil.toDate(kcsj)) && TimeUtil.toDate(datet).after(TimeUtil.toDate(kcsj))) {
                ret[0] = preYear;
                ret[1] = "0";
                return ret;
            }
            
            datef = TimeUtil.format(TimeUtil.addYear(preYear + "-03-01", 1), "yyyy-MM-dd");
            datet = TimeUtil.format(TimeUtil.addYear(preYear + "-08-31", 1), "yyyy-MM-dd");
            if (TimeUtil.toDate(datef).before(TimeUtil.toDate(kcsj)) && TimeUtil.toDate(datet).after(TimeUtil.toDate(kcsj))) {
                ret[0] = preYear;
                ret[1] = "1";
                return ret;
            }
            
            datef = TimeUtil.format(thisYear + "-09-01", "yyyy-MM-dd");
            datet = TimeUtil.format(TimeUtil.addDay(TimeUtil.addYear(thisYear + "-03-01", 1), -1), "yyyy-MM-dd");
            if (TimeUtil.toDate(datef).before(TimeUtil.toDate(kcsj)) && TimeUtil.toDate(datet).after(TimeUtil.toDate(kcsj))) {
                ret[0] = thisYear;
                ret[1] = "0";
                return ret;
            }
            
            datef = TimeUtil.format(TimeUtil.addYear(thisYear + "-03-01", 1), "yyyy-MM-dd");
            datet = TimeUtil.format(TimeUtil.addYear(thisYear + "-08-31", 1), "yyyy-MM-dd");
            if (TimeUtil.toDate(datef).before(TimeUtil.toDate(kcsj)) && TimeUtil.toDate(datet).after(TimeUtil.toDate(kcsj))) {
                ret[0] = thisYear;
                ret[1] = "1";
                return ret;
            }
        }
        
        return ret;
    }

	@Override
	public PageList<OpinionEntity> getOpinionList(OpinionQuery query) {
		PageList<OpinionEntity> pageList = new PageList<OpinionEntity>();
        Paginator paginator = new Paginator();
        if(query != null){
            paginator.setItemsPerPage(query.getPerPageSize());
            paginator.setPage((Integer)query.getToPage());
            paginator.setItems(evaluationDao.getOpinionListCount(query));
            pageList.setPaginator(paginator);
            if(paginator.getBeginIndex() <= paginator.getItems()){
                query.setStartRow(paginator.getBeginIndex());
                query.setEndRow(paginator.getEndIndex());
                pageList.addAll(evaluationDao.getOpinionList(query));
            }
        }

        return pageList;
	}

	@Override
	public OpinionEntity getOpinionById(String globalid) {
		// TODO Auto-generated method stub
		return null;
	}

}
