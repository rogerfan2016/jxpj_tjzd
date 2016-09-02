package com.zfsoft.baseData.service;

import java.util.List;
import java.util.Map;

import com.zfsoft.baseData.entity.ProcedureEntity;
import com.zfsoft.baseData.entity.ViewPropertyEntity;
import com.zfsoft.dao.page.PageList;
import com.zfsoft.hrm.baseinfo.dyna.entities.DynaBean;
import com.zfsoft.hrm.baseinfo.dyna.entities.DynaBeanQuery;
import com.zfsoft.hrm.baseinfo.infoclass.entities.Catalog;
import com.zfsoft.hrm.baseinfo.infoclass.entities.InfoClass;

/**
 * 
 * @author Administrator
 *
 */
public interface IBaseDataService {

    /**
     * 获取同步数据
     * @param query
     * @return
     */
    public PageList<DynaBean> getSynchronizedBaseData(DynaBeanQuery query);

    /**
     * 同步数据
     * @param procedureId 
     * @return
     */
    public Map<String, String> executeSynchronized(String procedureId);

    /**
     * 取得存储过程
     * @param catalogId 
     */
    public List<ProcedureEntity> getProcedures(String catalogId);

    /**
     * 取得目录
     * @return
     */
    public List<Catalog> getBaseCatalog();

    /**
     * 取得DB中存储过程
     * @return
     */
    public List<String> getDBProcedures();

    /**
     * 获取存储过程
     * @param procedureId
     * @param classId
     * @return
     */
    public ProcedureEntity getProceduresById(String procedureId, String classId);

    /**
     * 插入存储过程
     * @param model
     */
    public void insertProcedure(ProcedureEntity model);

    /**
     * 修改存储过程
     * @param model
     */
    public void modifyProcedure(ProcedureEntity model);

    /**
     * 取得信息类信息
     * @param classId
     * @return
     */
    public InfoClass getFullInfoClass(String classId);

    /**
     * 修改显示属性
     * @param vpModel
     */
    public void modifyViewPro(ViewPropertyEntity vpModel);

    /**
     * 刷新显示属性
     * @param classId
     */
    public List<ViewPropertyEntity> refreshViewPro(String classId);

    /**
     * 取得显示属性
     * @param classId
     * @return
     */
    public List<ViewPropertyEntity> findViewPro(String classId);

}
