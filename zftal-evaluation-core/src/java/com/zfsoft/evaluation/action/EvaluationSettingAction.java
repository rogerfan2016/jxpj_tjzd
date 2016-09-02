package com.zfsoft.evaluation.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.zfsoft.dao.page.PageList;
import com.zfsoft.evaluation.entity.EvaluationType;
import com.zfsoft.evaluation.entity.SettingEntity;
import com.zfsoft.evaluation.entity.SettingQuery;
import com.zfsoft.evaluation.entity.ViewQuestionnaireEntity;
import com.zfsoft.evaluation.service.IEvaluationService;
import com.zfsoft.hrm.common.HrmAction;
import com.zfsoft.hrm.core.exception.RuleException;
import com.zfsoft.wjdc.dao.entites.WjglModel;
import com.zfsoft.wjdc.service.svcinterface.IQuestionNaireService;

public class EvaluationSettingAction extends HrmAction {

    /**
     * 
     */
    private static final long serialVersionUID = -5927373395954682553L;
    private IEvaluationService evaluationService;
    private IQuestionNaireService questionNaireService;
    private SettingQuery query;
    private SettingEntity model;
    private PageList<SettingEntity> pageList;
    private String pjid;
    private List<ViewQuestionnaireEntity> questionnaires;
    
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
     * @return the query
     */
    public SettingQuery getQuery() {
        return query;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(SettingQuery query) {
        this.query = query;
    }

    /**
     * @return the pageList
     */
    public PageList<SettingEntity> getPageList() {
        return pageList;
    }

    /**
     * @param pageList the pageList to set
     */
    public void setPageList(PageList<SettingEntity> pageList) {
        this.pageList = pageList;
    }

    /**
     * @return the model
     */
    public SettingEntity getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(SettingEntity model) {
        this.model = model;
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

    /**
     * @return the pjid
     */
    public String getPjid() {
        return pjid;
    }

    /**
     * @param pjid the pjid to set
     */
    public void setPjid(String pjid) {
        this.pjid = pjid;
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

    /*****************************Action****************************************/
    /**
     * 设置
     * @return
     */
    public String setting() {
        if (query == null) {
            query = new SettingQuery();
        }
        getValueStack().set("pjs", EvaluationType.values());
        pageList = evaluationService.getQuestionnaires(query);
        return "setting";
    }
    
    /**
     * 保存
     * @return
     */
    public String addView() {
        
        if (model != null && !StringUtils.isEmpty(model.getGlobalid())) {
            model = evaluationService.getQuestionnaireById(model.getGlobalid());
        }
        getValueStack().set("pjs", EvaluationType.values());
        WjglModel wm = new WjglModel();
        wm.setWjzt("草稿");
        getValueStack().set("wjs", questionNaireService.getPagedList(wm));
        return "addView";
    }
    
    /**
     * 保存
     * @return
     */
    public String save() {
        
        boolean isExist = evaluationService.isExistSetting(model);
        if (isExist) {
            throw new RuleException("存在同类型的问卷（问卷、答卷人员类型、评教类型重复）！");
        }
        
        if (StringUtils.isEmpty(model.getGlobalid())) {
            model.setZcry(getUser().getYhm());
            model.setXgry(getUser().getYhm());
            evaluationService.saveQuestionnaire(model);
        } else {
            model.setXgry(getUser().getYhm());
            evaluationService.modifyQuestionnaire(model);
        }
        getValueStack().set(DATA, getMessage());
        return DATA;
    }
    
    /**
     * 修改
     * @return
     */
    public String modify() {
        model.setXgry(getUser().getYhm());
        evaluationService.modifyQuestionnaire(model);
        getValueStack().set(DATA, getMessage());
        return DATA;
    }
    
    /**
     * 评教列表
     * @return
     */
    public String evaluation() {
        questionnaires = evaluationService.getViewQuestionnaires(pjid);
        return "evaluation";
    }
}
