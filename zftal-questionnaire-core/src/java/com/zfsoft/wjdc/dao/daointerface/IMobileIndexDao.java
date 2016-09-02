package com.zfsoft.wjdc.dao.daointerface;

import java.util.Map;


public interface IMobileIndexDao {

	/**
	 * 
	* <p>Title: getPatrolCount</p>
	* <p>Description: 查询巡视任务数量</p>
	* @param param
	* @return
	 */
    public int getPatrolCount(Map<String, String> param);
    
    /**
     * 
    * <p>Title: getEvaluateCount</p>
    * <p>Description: 查询评教数量</p>
    * @param param
    * @return
     */
    public int getEvaluateCount(Map<String, String> param);
}
