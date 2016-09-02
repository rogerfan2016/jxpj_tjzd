package com.zfsoft.wjdc.service.svcinterface;

import com.zfsoft.common.service.BaseService;
import com.zfsoft.wjdc.dao.entites.YhdjglModel;
/**
 * 
* <p>Title: IMobileIndexService</p>
* <p>Description: 手机登陆首页</p>
* <p>Company: XXXX</p>
* @author    rogerfan
* @date      2015-12-20
 */
public interface IMobileIndexService extends BaseService<YhdjglModel>{

	/**
	 * 
	* <p>Title: getPatrolCount</p>
	* <p>Description: 查询巡视任务数量</p>
	* @param xsry 巡视人员
	* @param condition
	* @return
	 */
    public int getPatrolCount(String xsry,String condition);
    
    /**
     * 
    * <p>Title: getEvaluateCount</p>
    * <p>Description: 查询评教数量</p>
    * @param yhm
    * @param yhlx
    * @return
     */
    public int getEvaluateCount(String yhm,String yhlx);
}
