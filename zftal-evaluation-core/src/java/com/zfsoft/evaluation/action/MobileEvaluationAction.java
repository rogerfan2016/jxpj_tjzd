package com.zfsoft.evaluation.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.zfsoft.common.factory.SessionFactory;
import com.zfsoft.common.log.User;
import com.zfsoft.dao.page.PageList;
import com.zfsoft.evaluation.entity.CurriculumScheduleEntity;
import com.zfsoft.evaluation.entity.OpenLessonSettingEntity;
import com.zfsoft.evaluation.entity.TeacherOpenLessonEntity;
import com.zfsoft.evaluation.entity.TeacherOpenLessonQuery;
import com.zfsoft.evaluation.entity.TeachingEntity;
import com.zfsoft.evaluation.entity.ViewQuestionnaireEntity;
import com.zfsoft.evaluation.service.IEvaluationService;
import com.zfsoft.hrm.common.HrmAction;
import com.zfsoft.hrm.dybill.enums.PrivilegeType;
import com.zfsoft.hrm.infochange.entity.InfoChange;
import com.zfsoft.hrm.infochange.entity.InfoChangeAudit;
import com.zfsoft.hrm.infochange.query.InfoChangeQuery;
import com.zfsoft.hrm.infochange.service.IInfoChangeService;
import com.zfsoft.hrm.pendingAffair.entities.PendingAffairInfo;
import com.zfsoft.hrm.pendingAffair.service.svcinterface.IPendingAffairService;
import com.zfsoft.orcus.lang.TimeUtil;
import com.zfsoft.util.base.StringUtil;
import com.zfsoft.util.date.DateTimeUtil;
import com.zfsoft.workflow.model.SpBusiness;
import com.zfsoft.workflow.service.ISpBusinessService;

/**
 * 
 * @author Administrator
 *
 */
public class MobileEvaluationAction extends HrmAction {

    /**
     * 
     */
    private static final long serialVersionUID = 4911828311265744060L;
    
    private IEvaluationService evaluationService;
    private IPendingAffairService pendingAffairService;
    private List<PendingAffairInfo> list;	
    private List<ViewQuestionnaireEntity> questionnaires;
    private String dayMark;
    private String middleDay;
    private String kcid;
    private int skc;
    private int kcs;
    private String pjzt;
    private int totalSize;
    private int nowPage;
    private int perSize;
    private String handle;
    private String globalid;
    private TeachingEntity teachingEntity;
    private String firstDay;
    private String lastDay;
    private String ncrdt;
    private String txtOpinion;
    private TeacherOpenLessonQuery tolQuery;
    private OpenLessonSettingEntity query;
    private PageList<TeacherOpenLessonEntity> lessonList;
    
    
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
    
    
    /*****************************Action****************************************/
    /**
     * 移动端课程表
     * @return
     */
    public String curriculum() {
        
        String[] colors = new String[]{"#E095B8", "#62BDE6", "#AFA3D8", "#EFC574", "#B5D66E", "#79C2AB", "#84A5EB"};
        String now = "";
        Calendar calendar = Calendar.getInstance();
        int dayofweek = 0;
        if (!StringUtils.isEmpty(ncrdt)) {
            calendar.setTimeInMillis(Long.parseLong(ncrdt));
        }
        dayofweek = calendar.get(Calendar.DAY_OF_WEEK);
        now = TimeUtil.format(calendar.getTime(), "yyyy-MM-dd");

        firstDay = TimeUtil.format(TimeUtil.addDay(now, (1 - dayofweek)), "yyyy-MM-dd");
        lastDay = TimeUtil.format(TimeUtil.addDay(now, (7 - dayofweek)), "yyyy-MM-dd");
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userlx", getUser().getYhlx());
        param.put("userid", getUser().getYhm());
        param.put("firstDay", firstDay);
        param.put("lastDay", lastDay);
        
        // 查询
        CurriculumScheduleEntity[][] curriculumSchedule = evaluationService.getCurriculumSchedule(param);
        
        int size = curriculumSchedule.length;
        CurriculumScheduleEntity[][] csem = new CurriculumScheduleEntity[size][7];
        CurriculumScheduleEntity tp = null;
        CurriculumScheduleEntity tpq = null;
        CurriculumScheduleEntity gc = new CurriculumScheduleEntity();
        gc.setGlobalid("GCDT");
        int rowspan = 1;
        int nr = 0;
        
        for (int col = 0; col < 7 ; col++) {
            tpq = null;
            for (int row = 0; row < size; row++) {
                tp = curriculumSchedule[row][col];
                if (tp == null) {
                    rowspan = 1;
                    csem[row][col] = null;
                } else {
                    if (tpq == null) {
                        tp.setSc(rowspan);
                        nr = row;
                        csem[row][col] = tp;
                    } else {
                        if (tp.getKcid().equals(tpq.getKcid())) {
                            rowspan++;
                            csem[nr][col].setSc(rowspan);
                            csem[row][col] = gc;
                        } else {
                            rowspan = 1;
                            tp.setSc(rowspan);
                            nr = row;
                            csem[row][col] = tp;
                        }
                    }
                }
                tpq = tp;
            }
        }
        
        // 页面显示行数
        getValueStack().set("nowdayofweek", dayofweek - 1);
        getValueStack().set("curriculumSchedule", csem);
        getValueStack().set("sun", Integer.parseInt(firstDay.substring(8, 10)));
        getValueStack().set("mon", Integer.parseInt(TimeUtil.format(TimeUtil.addDay(firstDay, 1), "yyyy-MM-dd").substring(8, 10)));
        getValueStack().set("tues", Integer.parseInt(TimeUtil.format(TimeUtil.addDay(firstDay, 2), "yyyy-MM-dd").substring(8, 10)));
        getValueStack().set("wed", Integer.parseInt(TimeUtil.format(TimeUtil.addDay(firstDay, 3), "yyyy-MM-dd").substring(8, 10)));
        getValueStack().set("thurs", Integer.parseInt(TimeUtil.format(TimeUtil.addDay(firstDay, 4), "yyyy-MM-dd").substring(8, 10)));
        getValueStack().set("fri", Integer.parseInt(TimeUtil.format(TimeUtil.addDay(firstDay, 5), "yyyy-MM-dd").substring(8, 10)));
        getValueStack().set("sat", Integer.parseInt(lastDay.substring(8, 10)));
        getValueStack().set("now", Integer.parseInt(now.substring(8, 10)));
        getValueStack().set("nowMonth", Integer.parseInt(now.substring(5, 7)));
        getValueStack().set("nowDate", now);
        getValueStack().set("week", evaluationService.getTeachingWeek(DateTimeUtil.getCurrDateStr()));
        getValueStack().set("colors", colors);
        
        return "curriculum";
    }
    
    /**
     * 移动端课程表考勤
     * @return
     */
    public String checkin() {
        
        teachingEntity = evaluationService.getTeachingById(globalid);
        getValueStack().set("kcid", kcid);
        getValueStack().set("kcs", kcs);
        getValueStack().set("skc", skc);
        getValueStack().set("middleDay", middleDay);
        Map<String, String> param = new HashMap<String, String>();
        // 判断是否保存考勤
        param.put("globalid", globalid);
        int cnt = evaluationService.getEvaluationCnt(param);
        if (cnt == 0) {
            getValueStack().set("mes", "未录入考勤和发起评教！");
            getValueStack().set("mesf", "0");
            return "checkin";
        }else{
        	// 判断是否生成学生考评记录
            param.put("table", " , jxpj_sspj_xspj t2 ");
            param.put("condition", " and t1.pjid = t2.pjid ");
            cnt = evaluationService.getEvaluationCnt(param);
            if (cnt > 0) {
                getValueStack().set("mes", "已录入考勤并发起评教！");
                getValueStack().set("mesf", "1");
                return "checkin";
            }else{
            	getValueStack().set("mes", "已录入考勤但未发起评教！");
                getValueStack().set("mesf", "2");
                return "checkin";
            }
        }
    }
    
    /**
     * 保存考勤记录并生成教师听课评价记录
     * @return
     */
    public String saveCheckIn() {
        evaluationService.saveCheckIn(teachingEntity);
        evaluationService.sendTeacherEvaluation(teachingEntity);
        getValueStack().set(DATA, getMessage());
        return DATA;
    }
    
    /**
     * 发起学生评价评教
     * @return
     */
    public String evaluation() {
    	// 判断是否保存考勤
    	Map<String, String> param = new HashMap<String, String>();
        param.put("globalid", teachingEntity.getGlobalid());
        int cnt = evaluationService.getEvaluationCnt(param);
        if (cnt == 0) {
            getValueStack().set(DATA, "未录入考勤不能发起评教！");
            return "DATA";
        }else{
        	// 生成学评价记录
        	evaluationService.sendStudentEvaluation(teachingEntity);
        	// 生成教师评价记录
            evaluationService.sendTeacherEvaluation(teachingEntity);
            getValueStack().set(DATA, getMessage());
            return DATA;
        }        
    }
    
    /**
     * 移动端课程信息
     * @return
     */
    public String curriculumDetail() {
        List<String> jcs = new ArrayList<String>();
        String kcjc = "";
        for (int i = 0; i < kcs; i++) {
            jcs.add(String.valueOf(skc + i));
            kcjc += skc+i;
            if(i != kcs-1){
            	kcjc += ",";
            }
        }
        // 如果为空就取当前时间
        if(StringUtil.isEmpty(middleDay)){
        	middleDay = DateTimeUtil.getCurrDateStr();
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userlx", getUser().getYhlx());
        param.put("userid", getUser().getYhm());
        param.put("kcid", kcid);
        param.put("jcs", jcs);
        param.put("kcjc", skc);
        param.put("firstDay", middleDay);
        param.put("lastDay", middleDay);
        List<CurriculumScheduleEntity> curriculumSchedule = evaluationService.getCurriculumScheduleByKcid(param);
        List<TeachingEntity> teachingEntities = new ArrayList<TeachingEntity>();
        TeachingEntity te = new TeachingEntity();
        for (CurriculumScheduleEntity e: curriculumSchedule) {
            te = evaluationService.getTeachingById(e.getGlobalid());
            te.setKcsj(middleDay);
            // 判断是否保存考勤
        	Map<String, String> map = new HashMap<String, String>();
        	map.put("globalid", e.getGlobalid());
            int cnt = evaluationService.getEvaluationCnt(map);
            if (cnt == 0) {
            	te.setZt("0");
            }else{
            	// 判断是否生成学生考评记录
            	map.put("table", " , jxpj_sspj_xspj t2 ");
            	map.put("condition", " and t1.pjid = t2.pjid ");
                int cnt1 = evaluationService.getEvaluationCnt(map);
                if(cnt1 == 0){
                	te.setZt("1");
                }else{
                	te.setZt("2");
                }            	
            }
            te.setKcjc(kcjc);
            teachingEntities.add(te);
        }
        getValueStack().set("isCheckIn", DateTimeUtil.compare_date(middleDay, DateTimeUtil.getCurrDateStr()));
        getValueStack().set("teachingEntities", teachingEntities);
        getValueStack().set("lx", getUser().getYhlx());
//        getValueStack().set("kcjc", kcjc);
//        getValueStack().set("kcid", kcid);
//        getValueStack().set("kcs", kcs);
//        getValueStack().set("skc", skc);
        getValueStack().set("middleDay", middleDay);
        
        return "curriculumDetail";
    }
    
    /**
     * 我的评价
     * @return
     */
    public String myEvaluation() {
        Map<String, String> param = new HashMap<String, String>();
        param.put("rklsgh", getUser().getYhm());
        List<ViewQuestionnaireEntity> temp = evaluationService.getMyEvaluation(param);
        questionnaires = getPageList(temp);
        return "myEvaluation";
    }
    
    /**
     * 实时评教
     * @return
     */
    public String response() {
        Map<String, String> param = new HashMap<String, String>();
        if (StringUtils.isEmpty(pjzt)) {
            pjzt = "0";
        }
        param.put("pjzt", pjzt);
        if ("student".equals(getUser().getYhlx())) {
            param.put("pjryfiled", " t1.xsid as pjryid, t1.globalid ");
            param.put("tableSql", " jxpj_sspj_skkqglb t1, jxpj_sspj_xspj t2 ");
            param.put("whereSql", " and t1.xsid = '" + getUser().getYhm() + "' ");
        } else {
            return "response";
        }
        List<ViewQuestionnaireEntity> temp = evaluationService.getMyResponse(param);
        questionnaires = getPageList(temp);
        return "response";
    }
    
    /**
     * 听课评价
     * @return
     */
    public String response2() {
        Map<String, String> param = new HashMap<String, String>();
        if (StringUtils.isEmpty(pjzt)) {
            pjzt = "0";
        }
        param.put("pjzt", pjzt);
        if ("teacher".equals(getUser().getYhlx())) {
            param.put("pjryfiled", " t1.tkjsid as pjryid, t1.globalid ");
            param.put("tableSql", " jxpj_jshp_tkgl t1, jxpj_jshp_tkpj t2 ");
            param.put("whereSql", " and t1.tkjsid = '" + getUser().getYhm() + "' ");
        } else {
            return "response2";
        }
        List<ViewQuestionnaireEntity> temp = evaluationService.getMyResponse(param);
        questionnaires = getPageList(temp);
        return "response2";
    }
    
    /**
     * 意见反馈
     */
    public String opinion() {
        return "opinion";
    }
    
    /**
     * 保存意见
     * @return
     */
    public String saveOpinion() {
        Map<String, String> param = new HashMap<String, String>();
        param.put("userId", getUser().getYhm());
        param.put("userNm", getUser().getXm());
        param.put("rylx", getUser().getYhlx());
        param.put("opinion", txtOpinion);
        evaluationService.saveOpinion(param);
        getValueStack().set(DATA, getMessage());
        return DATA;
    }
    
    /*****************************待办事宜****************************************/
    /**
     * 待办事宜列表
     * @return
     */
    public String pendingAffairList(){
		User user = SessionFactory.getUser();
		list = pendingAffairService.getListByUser(user);
		list.addAll(pendingAffairService.getListByRoles(user));
		getValueStack().set("list", list);
		return "pendingAffairList";
	}
    
     /**
     * 分页
     * @param temp
     * @return
     */
    private List<ViewQuestionnaireEntity> getPageList(List<ViewQuestionnaireEntity> temp) {
        List<ViewQuestionnaireEntity> ret = new ArrayList<ViewQuestionnaireEntity>();
        if (perSize == 0) {
            perSize = 10;
        }
        // 获取总页数
        if (temp.size() % perSize == 0) {
            totalSize = temp.size() / perSize;
        } else {
            totalSize = temp.size() / perSize + 1;
        }
        
        // 上一页
        if ("previous".equals(handle)) {
            nowPage = nowPage - 1;
        // 下一页
        } else if ("next".equals(handle)) {
            nowPage = nowPage + 1;
        } else {
            nowPage = 1;
        }
        int start = (nowPage - 1) * perSize + 1;
        int end = nowPage * perSize;
        
        for (int i = 0; i < temp.size(); i++) {
            if (i >= (start - 1) && i <= (end - 1)) {
                ret.add(temp.get(i));
            }
        }
        return ret;
    }
    
    
    /**
     * 进入某个分类的待办事宜列表
     * byzhangqy
     */
    public String pendingAffairByType() throws Exception{

		 String readSession = getRequest().getParameter("readSession");
		if("true".equals(readSession)){
			infoChangeQuery = (InfoChangeQuery )getSession().getAttribute("InfoChangeAuditAction_InfoChangeQuery");
			if(infoChangeQuery==null) infoChangeQuery = new InfoChangeQuery();
		}
		infoChangeQuery.setOnwer(false);
		infoChangeQuery.setOrderStr( " commitDate DESC" );
    	
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
		return "pendingAffairByType";
	}
    
    /**
     * 进入某个待办准备审核
     * byzhangqy
     */
    
    public String pendingAffairDetail(){
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
		
		return "pendingAffairDetail";
    }
    
    /*****************************注入****************************************/
    /**
     * @param evaluationService the evaluationService to set
     */
    public void setEvaluationService(IEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }
    /**
     * @param pendingAffairService
     */
    public void setPendingAffairService(IPendingAffairService pendingAffairService) {
		this.pendingAffairService = pendingAffairService;
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

    /**
     * @return the middleDay
     */
    public String getMiddleDay() {
        return middleDay;
    }

    /**
     * @param middleDay the middleDay to set
     */
    public void setMiddleDay(String middleDay) {
        this.middleDay = middleDay;
    }

    /**
     * @return the dayMark
     */
    public String getDayMark() {
        return dayMark;
    }

    /**
     * @param dayMark the dayMark to set
     */
    public void setDayMark(String dayMark) {
        this.dayMark = dayMark;
    }

    /**
     * @return the questionnaires
     */
    public List<ViewQuestionnaireEntity> getQuestionnaires() {
        return questionnaires;
    }

    /**
     * @param questionnaires the questionnaires to set
     */
    public void setQuestionnaires(List<ViewQuestionnaireEntity> questionnaires) {
        this.questionnaires = questionnaires;
    }

    /**
     * @return the pjzt
     */
    public String getPjzt() {
        return pjzt;
    }

    /**
     * @param pjzt the pjzt to set
     */
    public void setPjzt(String pjzt) {
        this.pjzt = pjzt;
    }

    /**
     * @return the totalSize
     */
    public int getTotalSize() {
        return totalSize;
    }

    /**
     * @param totalSize the totalSize to set
     */
    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    /**
     * @return the nowPage
     */
    public int getNowPage() {
        return nowPage;
    }

    /**
     * @param nowPage the nowPage to set
     */
    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    /**
     * @return the perSize
     */
    public int getPerSize() {
        return perSize;
    }

    /**
     * @param perSize the perSize to set
     */
    public void setPerSize(int perSize) {
        this.perSize = perSize;
    }

    /**
     * @return the handle
     */
    public String getHandle() {
        return handle;
    }

    /**
     * @param handle the handle to set
     */
    public void setHandle(String handle) {
        this.handle = handle;
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
     * @return the ncrdt
     */
    public String getNcrdt() {
        return ncrdt;
    }

    /**
     * @param ncrdt the ncrdt to set
     */
    public void setNcrdt(String ncrdt) {
        this.ncrdt = ncrdt;
    }

    /**
     * @return the txtOpinion
     */
    public String getTxtOpinion() {
        return txtOpinion;
    }

    /**
     * @param txtOpinion the txtOpinion to set
     */
    public void setTxtOpinion(String txtOpinion) {
        this.txtOpinion = txtOpinion;
    }

	public IInfoChangeService getInfoChangeService() {
		return infoChangeService;
	}

	public void setInfoChangeService(IInfoChangeService infoChangeService) {
		this.infoChangeService = infoChangeService;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}
    
	public PageList<InfoChange> getPageList() {
		return pageList;
	}

	public void setPageList(PageList<InfoChange> pageList) {
		this.pageList = pageList;
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

	public InfoChange getInfoChange() {
		return infoChange;
	}

	public void setInfoChange(InfoChange infoChange) {
		this.infoChange = infoChange;
	}

	public SpBusiness getSpBusiness() {
		return spBusiness;
	}

	public void setSpBusiness(SpBusiness spBusiness) {
		this.spBusiness = spBusiness;
	}

	public String getWorkId() {
		return workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}
	
	public TeacherOpenLessonQuery getTolQuery() {
		return tolQuery;
	}

	public void setTolQuery(TeacherOpenLessonQuery tolQuery) {
		this.tolQuery = tolQuery;
	}

	public OpenLessonSettingEntity getQuery() {
		return query;
	}

	public void setQuery(OpenLessonSettingEntity query) {
		this.query = query;
	}

	public PageList<TeacherOpenLessonEntity> getLessonList() {
		return lessonList;
	}

	public void setLessonList(PageList<TeacherOpenLessonEntity> lessonList) {
		this.lessonList = lessonList;
	}	
	
    
}
