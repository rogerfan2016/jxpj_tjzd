package com.zfsoft.evaluation.action;

import java.util.List;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.zfsoft.dao.page.PageList;
import com.zfsoft.evaluation.entity.ClearDataEntity;
import com.zfsoft.evaluation.entity.ClearDataQuery;
import com.zfsoft.evaluation.service.IClearDataService;
import com.zfsoft.hrm.common.HrmAction;
import com.zfsoft.hrm.core.util.DownloadFilenameUtil;
import com.zfsoft.hrm.report.entity.ReportView;
import com.zfsoft.util.base.StringUtil;

/**
 * 
* <p>Title: ClearDataAction</p>
* <p>Description: 教学评价数据清洗评分</p>
* <p>Company: XXXX</p>
* @author    rogerfan
* @date      2016-1-8
 */
public class ClearDataAction extends HrmAction {

    /**
     * 
     */
    private static final long serialVersionUID = -51966075774632388L;
    private IClearDataService clearDataService;
    private ClearDataEntity model;
    private ClearDataQuery query;
    private PageList<ClearDataEntity> pageList;
    private List<ClearDataEntity> list;
    private String exportType;
    private String qxtj = "";

    /*****************************Action****************************************/
    /**
     * 清洗条件查询
     */
    public String searchConditionList() {
        if (query == null) {
            query = new ClearDataQuery();
        }
        list = clearDataService.getConditionList(query);
        return "searchConditionList";
    }

    /**
     * <p>Title: editCondition</p>
     * <p>Description: 增加或修改清洗条件</p>
     * @return
      */
     public String editCondition(){
    	 if(query != null && StringUtil.isNotEmpty(query.getTjid())){
    		 list = clearDataService.getConditionList(query);
             if(list != null && list.size() > 0){
            	 model = list.get(0);
             }
    	 }         
    	 return "editCondition";
     }
     
     /**
      * <p>Title: editCondition</p>
      * <p>Description: 修改条件状态</p>
      * @return
       */
      public String changeStatus(){
    	  if(StringUtil.isNotEmpty(model.getTjid())){
    		  clearDataService.modifyCondition(model);
     	  }
     	 return this.searchConditionList();
      }
      
      /**
      * <p>Title: clearData</p>
      * <p>Description: 清洗数据</p>
      * @return
       */
      public String doClearData(){
    	  clearDataService.doClearData(model);
    	  return this.searchConditionList();
      }
     
     /**
      * <p>Title: addCondition</p>
      * <p>Description: 保存清洗条件</p>
      * @return
       */
      public String saveCondition(){
    	  if(model != null){
    		  if(StringUtil.isNotEmpty(model.getTjid())){
        		  clearDataService.modifyCondition(model);
         	  }else{
         		  clearDataService.insertCondition(model);
         	  }
    	  }
    	  getValueStack().set(DATA, "操作成功！");
    	  return DATA;
      }
    
    /**
    * <p>Title: searchStudentsEvaluationList</p>
    * <p>Description: 按条件查询学生评分记录查询</p>
    * @return
     */
    public String searchStudentsEvaluationList() {
        if (query == null) {
            query = new ClearDataQuery();
        }else if(StringUtil.isNotEmpty(query.getQxtj())){
 		   String [] qxtjz= query.getQxtj().split(","); 
 		   query.setQxtjz(qxtjz);
 		   query.setQxtj("");
 	   	}
        pageList = clearDataService.getStudentsEvaluationList(query);
        if(query.getQxtjz() != null && query.getQxtjz().length > 0){
        	for(String tj:query.getQxtjz()){
            	qxtj += tj + ",";
            }
        }        
        getValueStack().set("qxtj", qxtj);
        // 获取清洗条件列表
        setCondition();
        return "searchStudentsEvaluationList";
    }
    
    /**
    * <p>Title: searchPersonalWeightList</p>
    * <p>Description: 查询个人加权评价结果 </p>
    * @return
     */
    public String searchPersonalWeightList() {
    	if (query == null) {
            query = new ClearDataQuery();
        }
        pageList = clearDataService.getPersonalWeightList(query);
        if(query.getQxtjz() != null && query.getQxtjz().length > 0){
        	for(String tj:query.getQxtjz()){
            	qxtj += tj + ",";
            }
        }        
        getValueStack().set("qxtj", qxtj);
        // 获取清洗条件列表
        setCondition();
        return "searchPersonalWeightList";
    }
    
    /**
     * <p>Title: searchCourseWeightList</p>
     * <p>Description: 查询课程加权评价结果 </p>
     * @return
      */
     public String searchCourseWeightList() {
     	if (query == null) {
             query = new ClearDataQuery();
         }
         pageList = clearDataService.getCourseWeightList(query);
         if(query.getQxtjz() != null && query.getQxtjz().length > 0){
         	for(String tj:query.getQxtjz()){
             	qxtj += tj + ",";
             }
         }
         getValueStack().set("qxtj", qxtj);
         // 获取清洗条件列表
         setCondition();
         return "searchCourseWeightList";
     }
     
     /**
      * <p>Title: searchPersonalWeightSumList</p>
      * <p>Description: 查询个人加权评价结果 </p>
      * @return
       */
      public String searchPersonalWeightSumList() {
      	if (query == null) {
              query = new ClearDataQuery();
          }
          pageList = clearDataService.getPersonalWeightSumList(query);
          return "searchPersonalWeightSumList";
      }
      
      /**
       * <p>Title: searchCourseWeightSumList</p>
       * <p>Description: 查询课程加权评价结果 </p>
       * @return
        */
       public String searchCourseWeightSumList() {
       	if (query == null) {
               query = new ClearDataQuery();
           }
           pageList = clearDataService.getCourseWeightSumList(query);
           return "searchCourseWeightSumList";
       }
       
       /**
        * 
       * <p>Title: viewReportByPieChart</p>
       * <p>Description: 展示汇总统计饼图图表</p>
       * @return
        */
       public String viewReportByPieChart(){
    	   ReportView view = clearDataService.getReportViewList(query);
    	   this.fullPieDataXML(view);
    	   return "viewReportByPieChart";
       }
       
       /**
        * 
       * <p>Title: viewReportByBarChart</p>
       * <p>Description: 展示汇总统计饼图图表</p>
       * @return
        */
       public String viewReportByBarChart(){
    	   ReportView view = clearDataService.getReportViewList(query);
    	   this.fullBarDataXML(view,"评价分数","",true);
    	   return "viewReportByBarChart";
       }
       /**
        * 
       * <p>Title: analysis</p>
       * <p>Description: 评价分析</p>
       * @return
        */
       public String analysis(){
    	   if(query != null && StringUtil.isNotEmpty(query.getQxtj())){
    		   String [] qxtjz= query.getQxtj().split(","); 
    		   query.setQxtjz(qxtjz);
    		   getValueStack().set("qxtj", query.getQxtj());
    	   }
    	   model = clearDataService.getVarianceResult(query);
    	   getValueStack().set("qxsl", Integer.valueOf(model.getPjzs())-Integer.valueOf(model.getYxsl()));
    	   return "analysis";
       }
    /**
     * 
    * <p>Title: exportScoreRecord</p>
    * <p>Description: 导出评价得分记录</p>
    * @return
    * @throws Exception
     */
    public String exportScoreRecord() throws Exception{
        getResponse().reset();
        getResponse().setCharacterEncoding("utf-8");
        getResponse().setContentType("application/vnd.ms-excel");
        String useragent = getRequest().getHeader("user-agent");
        String disposition = DownloadFilenameUtil.fileDisposition(useragent, "评价结果信息.xls");
        getResponse().setHeader("Content-Disposition", disposition);
        
        WritableWorkbook wwb = Workbook.createWorkbook(getResponse().getOutputStream());
        WritableSheet sheet = null;
        query.setPerPageSize(Integer.MAX_VALUE);
        if("grjq".equals(exportType)){
        	sheet = wwb.createSheet("个人加权评价结果信息", 1);
        	pageList = clearDataService.getPersonalWeightList(query);
        }else if("kcjq".equals(exportType)){
        	sheet = wwb.createSheet("课程加权评价结果信息", 1);
        	pageList = clearDataService.getCourseWeightList(query);
        }
        
        //产生表头
        sheet.addCell(generateTheadLabel(0, 0, "学年"));
        sheet.addCell(generateTheadLabel(1, 0, "学期"));
        if("grjq".equals(exportType)){
        	sheet.addCell(generateTheadLabel(2, 0, "职工号"));
            sheet.addCell(generateTheadLabel(3, 0, "教师姓名"));
            sheet.addCell(generateTheadLabel(4, 0, "院系"));
            sheet.addCell(generateTheadLabel(5, 0, "分数"));
        }else if("kcjq".equals(exportType)){
        	sheet.addCell(generateTheadLabel(2, 0, "课程名称"));
            sheet.addCell(generateTheadLabel(3, 0, "分数"));
        }

        //产生内容
        int y = 0;
        for(ClearDataEntity h : pageList){
            y++;
            sheet.addCell(generateValueLabel(0, y, h.getXn()));
            sheet.addCell(generateValueLabel(1, y, h.getXq()));
            if("grjq".equals(exportType)){
            	sheet.addCell(generateValueLabel(2, y, h.getJszgh()));
                sheet.addCell(generateValueLabel(3, y, h.getJsxm()));
                sheet.addCell(generateValueLabel(4, y, h.getYx()));
                sheet.addCell(generateValueNumber(5, y, (StringUtil.isEmpty(h.getZf()) ? 0 : Double.valueOf(h.getZf()).doubleValue() )));
            }else if("kcjq".equals(exportType)){
            	sheet.addCell(generateValueLabel(2, y, h.getKcmc()));
                sheet.addCell(generateValueNumber(3, y, (StringUtil.isEmpty(h.getZf()) ? 0 : Double.valueOf(h.getZf()).doubleValue() )));
            }
        }
        wwb.write();
        wwb.close();
        return null;
    }
    
    /**
     * 
    * <p>Title: exportScoreRecord</p>
    * <p>Description: 导出学生评分记录</p>
    * @return
    * @throws Exception
     */
    public String exportClearData() throws Exception{
        getResponse().reset();
        getResponse().setCharacterEncoding("utf-8");
        getResponse().setContentType("application/vnd.ms-excel");
        String useragent = getRequest().getHeader("user-agent");
        String disposition = DownloadFilenameUtil.fileDisposition(useragent, "学生评分记录信息.xls");
        getResponse().setHeader("Content-Disposition", disposition);
        
        WritableWorkbook wwb = Workbook.createWorkbook(getResponse().getOutputStream());
        
        WritableSheet sheet = wwb.createSheet("学生评分记录信息", 1);
        query.setPerPageSize(Integer.MAX_VALUE);
        pageList = clearDataService.getStudentsEvaluationList(query);
        
        //产生表头
        sheet.addCell(generateTheadLabel(0, 0, "学年"));
        sheet.addCell(generateTheadLabel(1, 0, "学期"));
        sheet.addCell(generateTheadLabel(2, 0, "学号"));
        sheet.addCell(generateTheadLabel(3, 0, "学生姓名"));
        sheet.addCell(generateTheadLabel(4, 0, "课程名称"));
        sheet.addCell(generateTheadLabel(5, 0, "职工号"));
        sheet.addCell(generateTheadLabel(6, 0, "教师姓名"));
        sheet.addCell(generateTheadLabel(7, 0, "院系"));
        sheet.addCell(generateTheadLabel(8, 0, "分数"));
        sheet.addCell(generateTheadLabel(9, 0, "学生成绩"));
        sheet.addCell(generateTheadLabel(10, 0, "成绩备注"));
        sheet.addCell(generateTheadLabel(11, 0, "绩点"));
        sheet.addCell(generateTheadLabel(12, 0, "是否清洗"));
        if("1".equals(query.getSfqx())){
        	 sheet.addCell(generateTheadLabel(13, 0, "清洗条件"));
        }       

        //产生内容
        int y = 0;
        for(ClearDataEntity h : pageList){
            y++;
            sheet.addCell(generateValueLabel(0, y, h.getXn()));
            sheet.addCell(generateValueLabel(1, y, "第" + h.getXq() + "学期"));
            sheet.addCell(generateValueLabel(2, y, h.getXh()));
            sheet.addCell(generateValueLabel(3, y, h.getXsxm()));
            sheet.addCell(generateValueLabel(4, y, h.getKcmc()));
            sheet.addCell(generateValueLabel(5, y, h.getJszgh()));
            sheet.addCell(generateValueLabel(6, y, h.getJsxm()));
            sheet.addCell(generateValueLabel(7, y, h.getYx()));
            sheet.addCell(generateValueNumber(8, y, (StringUtil.isEmpty(h.getZf()) ? 0 : Double.valueOf(h.getZf()).doubleValue() )));
            sheet.addCell(generateValueLabel(9, y, h.getCj()));
            sheet.addCell(generateValueLabel(10, y, h.getCjbz()));
            sheet.addCell(generateValueLabel(11, y, h.getJd()));
            sheet.addCell(generateValueLabel(12, y, (h.getSfqx() == "0" ? "否" : "是")));
            if("1".equals(query.getSfqx())){
            	 sheet.addCell(generateValueLabel(13, y, h.getQxtj()));
            }           
        }
        wwb.write();
        wwb.close();
        return null;
    }
    
//============================================================================================================================

    /**
     * 清洗条件
     */
	private void setCondition() {
		// 获取清洗条件
        query.setZt("1");
        list = clearDataService.getConditionList(query);
	}

	/**=================================================================================================**/
	/**
	 * @return the model
	 */
	public ClearDataEntity getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(ClearDataEntity model) {
		this.model = model;
	}

	/**
	 * @return the query
	 */
	public ClearDataQuery getQuery() {
		return query;
	}

	/**
	 * @param query the query to set
	 */
	public void setQuery(ClearDataQuery query) {
		this.query = query;
	}

	/**
	 * @return the pageList
	 */
	public PageList<ClearDataEntity> getPageList() {
		return pageList;
	}

	/**
	 * @param pageList the pageList to set
	 */
	public void setPageList(PageList<ClearDataEntity> pageList) {
		this.pageList = pageList;
	}

	/**
	 * @param clearDataService the clearDataService to set
	 */
	public void setClearDataService(IClearDataService clearDataService) {
		this.clearDataService = clearDataService;
	}

	/**
	 * @return the exportType
	 */
	public String getExportType() {
		return exportType;
	}

	/**
	 * @param exportType the exportType to set
	 */
	public void setExportType(String exportType) {
		this.exportType = exportType;
	}

	/**
	 * @return the list
	 */
	public List<ClearDataEntity> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<ClearDataEntity> list) {
		this.list = list;
	}
	
}
