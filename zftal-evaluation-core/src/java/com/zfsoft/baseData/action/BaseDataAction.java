package com.zfsoft.baseData.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;

import com.zfsoft.baseData.entity.ProcedureEntity;
import com.zfsoft.baseData.entity.ViewPropertyEntity;
import com.zfsoft.baseData.service.IBaseDataService;
import com.zfsoft.common.spring.SpringHolder;
import com.zfsoft.dao.page.PageList;
import com.zfsoft.hrm.baseinfo.dyna.entities.DynaBean;
import com.zfsoft.hrm.baseinfo.dyna.entities.DynaBeanQuery;
import com.zfsoft.hrm.baseinfo.dyna.html.Type;
import com.zfsoft.hrm.baseinfo.finfo.util.FormInfoUtil;
import com.zfsoft.hrm.baseinfo.infoclass.entities.Catalog;
import com.zfsoft.hrm.baseinfo.infoclass.entities.InfoClass;
import com.zfsoft.hrm.baseinfo.infoclass.entities.InfoProperty;
import com.zfsoft.hrm.common.HrmAction;
import com.zfsoft.hrm.schedule.ScheduleControlService;
import com.zfsoft.hrm.schedule.util.QuartzTriggerUtil;
import com.zfsoft.orcus.lang.TimeUtil;
import com.zfsoft.util.base.StringUtil;

/**
 * 
 * @author Administrator
 *
 */
public class BaseDataAction extends HrmAction implements Job {

    /**
     * 
     */
    private static final long serialVersionUID = 8278043594823104988L;
    private IBaseDataService baseDataService;
    private ScheduleControlService scheduleControlService;
    private PageList<DynaBean> pageList = new PageList<DynaBean>();
    private DynaBeanQuery query;
    private String classId;
    private String procedureId;
    private String catalogId;
    private ProcedureEntity model;
    private int showMore = -1;
    private String asc;
    private String sortFieldName;
    private final static String PROCEDURE_REGULAR_NAME = "PR_";
    private boolean fixedWin = true;
    private ViewPropertyEntity vpModel;
    
    /**
     * @return the baseDataService
     */
    public IBaseDataService getBaseDataService() {
        return baseDataService;
    }
    
    /**
     * @param baseDataService the baseDataService to set
     */
    public void setBaseDataService(IBaseDataService baseDataService) {
        this.baseDataService = baseDataService;
    }

    /**
     * @return the classId
     */
    public String getClassId() {
        return classId;
    }

    /**
     * @param classId the classId to set
     */
    public void setClassId(String classId) {
        this.classId = classId;
    }

    /**
     * @return the procedureId
     */
    public String getProcedureId() {
        return procedureId;
    }

    /**
     * @param procedureId the procedureId to set
     */
    public void setProcedureId(String procedureId) {
        this.procedureId = procedureId;
    }

    /**
     * @return the pageList
     */
    public PageList<DynaBean> getPageList() {
        return pageList;
    }

    /**
     * @param pageList the pageList to set
     */
    public void setPageList(PageList<DynaBean> pageList) {
        this.pageList = pageList;
    }

    /**
     * @return the query
     */
    public DynaBeanQuery getQuery() {
        return query;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(DynaBeanQuery query) {
        this.query = query;
    }

    /**
     * @return the catalogId
     */
    public String getCatalogId() {
        return catalogId;
    }

    /**
     * @param catalogId the catalogId to set
     */
    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    /**
     * @return the model
     */
    public ProcedureEntity getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(ProcedureEntity model) {
        this.model = model;
    }

    /**
     * @return the showMore
     */
    public int getShowMore() {
        return showMore;
    }

    /**
     * @param showMore the showMore to set
     */
    public void setShowMore(int showMore) {
        this.showMore = showMore;
    }

    /**
     * @return the asc
     */
    public String getAsc() {
        return asc;
    }

    /**
     * @param asc the asc to set
     */
    public void setAsc(String asc) {
        this.asc = asc;
    }

    /**
     * @return the sortFieldName
     */
    public String getSortFieldName() {
        return sortFieldName;
    }

    /**
     * @param sortFieldName the sortFieldName to set
     */
    public void setSortFieldName(String sortFieldName) {
        this.sortFieldName = sortFieldName;
    }

    /**
     * @return the scheduleControlService
     */
    public ScheduleControlService getScheduleControlService() {
        return scheduleControlService;
    }

    /**
     * @param scheduleControlService the scheduleControlService to set
     */
    public void setScheduleControlService(
            ScheduleControlService scheduleControlService) {
        this.scheduleControlService = scheduleControlService;
    }

    /**
     * @return the fixedWin
     */
    public boolean isFixedWin() {
        return fixedWin;
    }

    /**
     * @param fixedWin the fixedWin to set
     */
    public void setFixedWin(boolean fixedWin) {
        this.fixedWin = fixedWin;
    }

    /**
     * @return the vpModel
     */
    public ViewPropertyEntity getVpModel() {
        return vpModel;
    }

    /**
     * @param vpModel the vpModel to set
     */
    public void setVpModel(ViewPropertyEntity vpModel) {
        this.vpModel = vpModel;
    }

    /**
     * 同步数据
     */
    public String synBaseData() {
        InfoClass clazz = FormInfoUtil.reFillPropertyByRole(getUser().getJsdms(), classId);
        if (query == null) {
            query = new DynaBeanQuery();
        }
        
        ProcedureEntity p = baseDataService.getProceduresById("", classId);
        
        if (p != null) {
            procedureId = p.getProcedureId();
        } else {
            procedureId = "";
        }
        
        List<ViewPropertyEntity> lvpro = baseDataService.findViewPro(classId);
        Map<String, ViewPropertyEntity> pros = new HashMap<String, ViewPropertyEntity>();
        for (ViewPropertyEntity pro : lvpro) {
            pros.put(pro.getPropertyId(), pro);
        }
        
        List<InfoProperty> viewables = new ArrayList<InfoProperty>();
        List<InfoProperty> conditions = new ArrayList<InfoProperty>();
        List<InfoProperty> tempview = clazz.getViewables();
        StringBuilder sbMoreConds = new StringBuilder();
        Map<String,Object> valuesMap = new HashMap<String, Object>();
        
        for (InfoProperty infoProperty : tempview) {
            if (!Type.PHOTO.equals(infoProperty.getFieldType()) && !Type.IMAGE.equals(infoProperty.getFieldType()) && !Type.FILE.equals(infoProperty.getFieldType())) {
                if (pros.containsKey(infoProperty.getGuid())) {
                    if ("y".equals(pros.get(infoProperty.getGuid()).getViewStatus())) {
                        viewables.add(infoProperty);
                    }
                    if ("y".equals(pros.get(infoProperty.getGuid()).getConditionStatus())) {
                        conditions.add(infoProperty);
                    }
                }
                
                String fieldName = "t." + infoProperty.getFieldName();
                if (infoProperty.getVirtual()) {
                    fieldName = infoProperty.getDisplayFormula("t");
                }
                
                if (Type.DATE.equalsIgnoreCase(infoProperty.getTypeInfo().getName()) || Type.MONTH.equalsIgnoreCase(infoProperty.getTypeInfo().getName()) || Type.YEAR.equalsIgnoreCase(infoProperty.getTypeInfo().getName())) {
                    String[] values = getRequest().getParameterValues(infoProperty.getFieldName());
                    if (values == null) {
                        continue;
                    }
                    
                    valuesMap.put(infoProperty.getFieldName(), values);
                    if (StringUtils.isNotEmpty(values[0])) {
                        sbMoreConds.append(" and ");
                        sbMoreConds.append(fieldName);
                        sbMoreConds.append(">= to_date('");
                        sbMoreConds.append(values[0]);
                        sbMoreConds.append("','");
                        sbMoreConds.append(infoProperty.getTypeInfo().getFormat());
                        sbMoreConds.append("')");
                    }
                    if (StringUtils.isNotEmpty(values[1])) {
                        sbMoreConds.append(" and ");
                        sbMoreConds.append(fieldName);
                        sbMoreConds.append("<= to_date('");
                        sbMoreConds.append(values[1]);
                        sbMoreConds.append("','");
                        sbMoreConds.append(infoProperty.getTypeInfo().getFormat());
                        sbMoreConds.append("')");
                    }
                } else {
                    String value = getRequest().getParameter(infoProperty.getFieldName());
                    if (StringUtils.isEmpty(value)) {
                        continue;
                    }
                    valuesMap.put(infoProperty.getFieldName(), value);
                    if (Type.CODE.equalsIgnoreCase(infoProperty.getTypeInfo().getName()) || Type.SIGLE_SEL.equalsIgnoreCase(infoProperty.getTypeInfo().getName())) {
                        sbMoreConds.append(" and ");
                        sbMoreConds.append(fieldName);
                        sbMoreConds.append("='");
                        sbMoreConds.append(value);
                        sbMoreConds.append("'");
                    } else if (Type.NUMBER.equalsIgnoreCase(infoProperty.getTypeInfo().getName())) {
                        sbMoreConds.append(" and ");
                        sbMoreConds.append(fieldName);
                        sbMoreConds.append("=");
                        sbMoreConds.append(value);
                        sbMoreConds.append("");
                    } else {
                        sbMoreConds.append(" and ");
                        sbMoreConds.append(fieldName);
                        sbMoreConds.append(" like '%");
                        sbMoreConds.append(value.replaceAll("'", "''"));
                        sbMoreConds.append("%'");
                    }
                }
            }
        }
        
        String orderStr = "";
        if (!StringUtil.isEmpty(sortFieldName)) {
            String fieldName = "t." + sortFieldName;
            if("up".equals(asc)){
                orderStr = fieldName;
            }else{
                orderStr = fieldName + " desc";
            }
        }else{
            orderStr = "t.bh";
        }
        
        String express = " (1 = 1) ";
        if (sbMoreConds.length() > 0) {
            express += sbMoreConds.toString();
        }
        
        query.setClazz(clazz);
        query.setExpress(express);
        query.setOrderStr(orderStr);
        pageList = baseDataService.getSynchronizedBaseData(query);
        
        getValueStack().set("viewables", viewables);
        getValueStack().set("conditions", conditions);
        getValueStack().set("valuesMap", valuesMap);
        
        return "synBaseData";
    }
    
    /**
     * 获取存储过程信息
     * @return
     */
    public String setProcedure() {
        List<Catalog> baseCatalog = baseDataService.getBaseCatalog();
        if (baseCatalog == null || baseCatalog.size() == 0) {
            return "setProcedure";
        }
        
        if (StringUtils.isEmpty(catalogId)) {
            catalogId = baseCatalog.get(0).getGuid();
        }
        List<ProcedureEntity> procedures = baseDataService.getProcedures(catalogId);
        getValueStack().set("catalogs", baseCatalog);
        getValueStack().set("procedures", procedures);
        return "setProcedure";
    }
    
    /**
     * 同步
     * @return
     */
    public String doSynchronized() {
        
        Map<String, String> retMessage = baseDataService.executeSynchronized(procedureId);
        getValueStack().set(DATA, retMessage);
        return DATA;
    }
    
    /**
     * 定时
     * @return
     */
    public String regular() {
        List<String> dbprocedures = baseDataService.getDBProcedures();
        if(!StringUtils.isEmpty(model.getProcedureId())) {
            model = baseDataService.getProceduresById(model.getProcedureId(), "");
        }
        
        getValueStack().set("dbprocedures", dbprocedures);
        return "regular";
    }

    /**
     * 定时
     * @return
     * @throws Exception 
     */
    public String doRegular() throws Exception {
        Trigger trigger = null;
        boolean hasRegular = false;
        String beginDayOfYear = TimeUtil.current("yyyy-01-01");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = df.parse(beginDayOfYear);
        String TriggerName = PROCEDURE_REGULAR_NAME + model.getProcedureId();
        
        // 执行周期（0每月1每季度2每半年3每年）
        // 每月
        if ("0".equals(model.getExecuteCyc())) {
            hasRegular = true;
            trigger = QuartzTriggerUtil.getMyMonthTrigger(TriggerName, 1, startDate, null);
            // 每季度
        } else if ("1".equals(model.getExecuteCyc())) {
            hasRegular = true;
            trigger = QuartzTriggerUtil.getMyMonthTrigger(TriggerName, 3, startDate, null);
            // 每半年
        } else if ("2".equals(model.getExecuteCyc())) {
            hasRegular = true;
            trigger = QuartzTriggerUtil.getMyMonthTrigger(TriggerName, 6, startDate, null);
            // 每年
        } else if ("3".equals(model.getExecuteCyc())) {
            hasRegular = true;
            trigger = QuartzTriggerUtil.getMyYearTrigger(TriggerName, 1, startDate, null);
        } else {
            hasRegular = false;
        }
        // 测试用代码 start
//        hasRegular = true;
//        trigger = QuartzTriggerUtil.getMyMinuteTrigger(TriggerName, 1, new Date(), null);
        // 测试用代码 end
        JobDataMap map = trigger.getJobDataMap();
        map.put("ProcedureId", model.getProcedureId());
        trigger.setJobDataMap(map);
        scheduleControlService.removeTrigger(TriggerName);
        scheduleControlService.addTriggerToJob(TriggerName, trigger, BaseDataAction.class);
        if(!hasRegular || "off".equals(model.getRegularSwitch())){
            scheduleControlService.pauseTrigger(TriggerName);
        }
        
        if (StringUtils.isEmpty(model.getProcedureId())) {
            baseDataService.insertProcedure(model);
        } else {
            baseDataService.modifyProcedure(model);
        }
        getValueStack().set(DATA, getMessage());
        return DATA;
    }
    
    /**
     * 显示设置
     * @return
     */
    public String setView() {
        List<Catalog> baseCatalog = baseDataService.getBaseCatalog();
        if (baseCatalog == null || baseCatalog.size() == 0) {
            return "setView";
        }
        
        if (StringUtils.isEmpty(catalogId)) {
            catalogId = baseCatalog.get(0).getGuid();
        }
        
        if (StringUtils.isEmpty(classId)) {
            List<InfoClass> icList = baseCatalog.get(0).getClasses();
            
            if (icList != null && icList.size() > 0) {
                classId = icList.get(0).getGuid();
            }
        }
        
        InfoClass infoClass = null;
        if (!StringUtils.isEmpty(classId)) {
            infoClass = baseDataService.getFullInfoClass(classId);
        }
        
        if (infoClass == null) {
            getValueStack().set("infoClass", null);
        } else {
            getValueStack().set("infoClass", infoClass);
            List<ViewPropertyEntity> lvpro = baseDataService.refreshViewPro(classId);
            Map<String, ViewPropertyEntity> mvpro = new HashMap<String, ViewPropertyEntity>();
            for (ViewPropertyEntity vp : lvpro) {
                mvpro.put(vp.getPropertyId(), vp);
            }
            
            getValueStack().set("vpro", mvpro);
        }
        
        getValueStack().set("catalogs", baseCatalog);
        
        return "setView";
    }
    
    /**
     * 增加显示属性
     * @return
     */
    public String modifyViewPro() {
        baseDataService.modifyViewPro(vpModel);
        getValueStack().set(DATA, getMessage());
        return DATA;
    }

    /**
     * 定时
     * @return
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        baseDataService = (IBaseDataService)SpringHolder.getBean("baseDataService");
        JobDataMap map = context.getTrigger().getJobDataMap();
        baseDataService.executeSynchronized(map.getString("ProcedureId"));
    }
}
