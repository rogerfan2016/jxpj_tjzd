package com.zfsoft.baseData.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.baseData.dao.daointerface.IBaseDataDao;
import com.zfsoft.baseData.entity.ProcedureEntity;
import com.zfsoft.baseData.entity.ViewPropertyEntity;
import com.zfsoft.baseData.service.IBaseDataService;
import com.zfsoft.dao.page.PageList;
import com.zfsoft.dao.page.Paginator;
import com.zfsoft.hrm.baseinfo.dyna.entities.DynaBean;
import com.zfsoft.hrm.baseinfo.dyna.entities.DynaBeanQuery;
import com.zfsoft.hrm.baseinfo.infoclass.entities.Catalog;
import com.zfsoft.hrm.baseinfo.infoclass.entities.InfoClass;
import com.zfsoft.hrm.baseinfo.infoclass.entities.InfoProperty;
import com.zfsoft.hrm.baseinfo.infoclass.query.CatalogQuery;
import com.zfsoft.hrm.baseinfo.infoclass.service.svcinterface.ICatalogService;
import com.zfsoft.hrm.baseinfo.infoclass.service.svcinterface.IInfoClassService;

/**
 * 
 * @author Administrator
 *
 */
public class BaseDataServiceImpl implements IBaseDataService {

    private IBaseDataDao baseDataDao;
    private ICatalogService catalogService;
    private IInfoClassService infoClassService;
    private String baseTypeDir;
    
    /**
     * @return the baseDataDao
     */
    public IBaseDataDao getBaseDataDao() {
        return baseDataDao;
    }

    /**
     * @param baseDataDao the baseDataDao to set
     */
    public void setBaseDataDao(IBaseDataDao baseDataDao) {
        this.baseDataDao = baseDataDao;
    }

    /**
     * @return the catalogService
     */
    public ICatalogService getCatalogService() {
        return catalogService;
    }

    /**
     * @param catalogService the catalogService to set
     */
    public void setCatalogService(ICatalogService catalogService) {
        this.catalogService = catalogService;
    }

    /**
     * @return the infoClassService
     */
    public IInfoClassService getInfoClassService() {
        return infoClassService;
    }

    /**
     * @param infoClassService the infoClassService to set
     */
    public void setInfoClassService(IInfoClassService infoClassService) {
        this.infoClassService = infoClassService;
    }

    /**
     * @return the baseTypeDir
     */
    public String getBaseTypeDir() {
        return baseTypeDir;
    }

    /**
     * @param baseTypeDir the baseTypeDir to set
     */
    public void setBaseTypeDir(String baseTypeDir) {
        this.baseTypeDir = baseTypeDir;
    }

    /**
     * 获取同步数据
     */
    @Override
    public PageList<DynaBean> getSynchronizedBaseData(DynaBeanQuery query) {
        PageList<DynaBean> pageList = new PageList<DynaBean>();
        Paginator paginator = new Paginator();
        if (query != null) {
            paginator.setItemsPerPage(query.getPerPageSize());
            paginator.setPage((Integer) query.getToPage());

            paginator.setItems(baseDataDao.getSynchronizedBaseDataCount(query));
            pageList.setPaginator(paginator);

            if (paginator.getBeginIndex() <= paginator.getItems()) {
                query.setStartRow(paginator.getBeginIndex());
                query.setEndRow(paginator.getEndIndex());
                List<Map<String, Object>> list = baseDataDao.getSynchronizedBaseDataList(query);
                DynaBean dynaBean;
                
                for (Map<String, Object> m : list) {
                    dynaBean = new DynaBean(query.getClazz());
                    dynaBean.setValues(m);
                    pageList.add(dynaBean);
                }
            }
        }
        return pageList;
    }

    /**
     * 同步数据
     */
    @Override
    public Map<String, String> executeSynchronized(String procedureId) {
        ProcedureEntity procedure = getProceduresById(procedureId, "");
        Map<String, String> param = new HashMap<String, String>();
        String sqlProcedure = "{call Procedure_Name (Parameters)}";
        
        // 拼接SQL，设置参数
        String outParam = "#{out_code, mode=OUT, jdbcType=VARCHAR}, #{out_message, mode=OUT, jdbcType=VARCHAR}";
        sqlProcedure = sqlProcedure.replace("Procedure_Name", procedure.getProcedureName());
        sqlProcedure = sqlProcedure.replace("Parameters", outParam);
        
        param.put("procedure", sqlProcedure);
        baseDataDao.executeProcedure(param);
        
        return param;
    }

    /**
     * 取得存储过程
     */
    @Override
    public List<ProcedureEntity> getProcedures(String catalogId) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("catalogType", baseTypeDir);
        param.put("catalogId", catalogId);
        return baseDataDao.getProcedures(param);
    }

    /**
     * 取得目录
     */
    @Override
    public List<Catalog> getBaseCatalog() {
        CatalogQuery query=new CatalogQuery();
        query.setType(baseTypeDir);
        
        return catalogService.getFullList( query );
    }

    /**
     * 取得DB中存储过程
     */
    @Override
    public List<String> getDBProcedures() {
        return baseDataDao.getDBProcedures();
    }

    /**
     * 获取存储过程
     */
    @Override
    public ProcedureEntity getProceduresById(String procedureId, String classId) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("procedureId", procedureId);
        param.put("classId", classId);
        return baseDataDao.getProceduresById(param);
    }

    /**
     * 插入存储过程
     */
    @Override
    public void insertProcedure(ProcedureEntity model) {
        baseDataDao.insertProcedure(model);
    }

    /**
     * 修改存储过程
     */
    @Override
    public void modifyProcedure(ProcedureEntity model) {
        baseDataDao.modifyProcedure(model);
    }

    /**
     * 取得信息类信息
     */
    @Override
    public InfoClass getFullInfoClass(String classId) {
        return infoClassService.getFullInfoClass(classId);
    }

    /**
     * 修改显示属性
     */
    @Override
    public void modifyViewPro(ViewPropertyEntity vpModel) {
        baseDataDao.modifyViewPro(vpModel);
    }

    /**
     * 刷新显示属性
     */
    @Override
    public List<ViewPropertyEntity> refreshViewPro(String classId) {
        List<ViewPropertyEntity> vepros = new ArrayList<ViewPropertyEntity>();
        InfoClass infoClass = this.getFullInfoClass(classId);
        List<InfoProperty> vpros = infoClass.getViewables();
        List<ViewPropertyEntity> epros = baseDataDao.findViewPro(classId);
        
        if (vpros != null && vpros.size() > 0) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("classId", classId);
            param.put("vpros", vpros);
            
            vepros = baseDataDao.findViewProByPIds(param);
        }
        ViewPropertyEntity vpModel;
        
        // 刷新数据，将信息类可显示的属性全部置成画面可显示和查询条件
        if (epros != null && epros.size() > 0) {
            // 现有信息类字段和画面显示属性全部不匹配
            if (vepros == null || vepros.size() == 0) {
                vpModel = new ViewPropertyEntity();
                vpModel.setClassId(classId);
                baseDataDao.removeViewPro(vpModel);
                for (InfoProperty vp : vpros) {
                    vpModel = new ViewPropertyEntity();
                    vpModel.setClassId(classId);
                    vpModel.setPropertyId(vp.getGuid());
                    vpModel.setViewStatus("y");
                    vpModel.setConditionStatus("y");
                    baseDataDao.addViewPro(vpModel);
                }
            } else {
                // 部分匹配
                if (epros.size() != vepros.size()) {
                    for (ViewPropertyEntity ep : epros) {
                        boolean isExist = false;
                        for (ViewPropertyEntity vep : vepros) {
                            if (ep.getPropertyId().equals(vep.getPropertyId())) {
                                isExist = true;
                                break;
                            }
                        }
                        // 不存在
                        if (!isExist) {
                            baseDataDao.removeViewPro(ep);
                        }
                    }
                }
                
                if (vpros.size() != vepros.size()) {
                    for (InfoProperty vp : vpros) {
                        boolean isExist = false;
                        for (ViewPropertyEntity vep : vepros) {
                            if (vp.getGuid().equals(vep.getPropertyId())) {
                                isExist = true;
                                break;
                            }
                        }
                        // 不存在
                        if (!isExist) {
                            vpModel = new ViewPropertyEntity();
                            vpModel.setClassId(classId);
                            vpModel.setPropertyId(vp.getGuid());
                            vpModel.setViewStatus("y");
                            vpModel.setConditionStatus("y");
                            baseDataDao.addViewPro(vpModel);
                        }
                    }
                }
                
                
            }
        // 初始数据，将信息类可显示的属性全部置成画面可显示和查询条件
        } else {
            for (InfoProperty vp : vpros) {
                vpModel = new ViewPropertyEntity();
                vpModel.setClassId(classId);
                vpModel.setPropertyId(vp.getGuid());
                vpModel.setViewStatus("y");
                vpModel.setConditionStatus("y");
                baseDataDao.addViewPro(vpModel);
            }
        }
        
        return baseDataDao.findViewPro(classId);
    }

    /**
     * 取得显示属性
     */
    @Override
    public List<ViewPropertyEntity> findViewPro(String classId) {
        return baseDataDao.findViewPro(classId);
    }

}
