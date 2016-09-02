package com.zfsoft.wjdc.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.zfsoft.common.service.BaseServiceImpl;
import com.zfsoft.wjdc.dao.daointerface.IMobileIndexDao;
import com.zfsoft.wjdc.dao.daointerface.IYhdjglDao;
import com.zfsoft.wjdc.dao.entites.YhdjglModel;
import com.zfsoft.wjdc.service.svcinterface.IMobileIndexService;

/**
 * 
* <p>Title: MobileIndexServiceImpl</p>
* <p>Description: </p>
* <p>Company: XXXX</p>
* @author    rogerfan
* @date      2015-12-20
 */
public class MobileIndexServiceImpl extends BaseServiceImpl<YhdjglModel, IYhdjglDao> implements IMobileIndexService {
	private IMobileIndexDao dao;

	/**
	 * @param dao the dao to set
	 */
	public void setDao(IMobileIndexDao dao) {
		this.dao = dao;
	}

	/* （非 Javadoc）
	* <p>Title: getPatrolCount</p>
	* <p>Description: </p>
	* @param yhm
	* @param condition
	* @return
	* @see com.zfsoft.wjdc.service.svcinterface.IMobileIndexService#getPatrolCount(java.lang.String, java.lang.String)
	*/
	@Override
	public int getPatrolCount(String xsry, String condition) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("xsry", xsry);
		return dao.getPatrolCount(param);
	}

	/* （非 Javadoc）
	* <p>Title: getEvaluateCount</p>
	* <p>Description: </p>
	* @param yhm
	* @param yhlx
	* @return
	* @see com.zfsoft.wjdc.service.svcinterface.IMobileIndexService#getEvaluateCount(java.lang.String, java.lang.String)
	*/
	@Override
	public int getEvaluateCount(String yhm, String yhlx) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("pjzt", "0");
        if ("student".equals(yhlx)) {
            param.put("pjryfiled", " t1.xsid as pjryid, t1.globalid ");
            param.put("tableSql", " jxpj_sspj_skkqglb t1, jxpj_sspj_xspj t2 ");
            param.put("whereSql", " and t1.xsid = '" + yhm + "' ");
        }else if ("teacher".equals(yhlx)) {
            param.put("pjryfiled", " t1.tkjsid as pjryid, t1.globalid ");
            param.put("tableSql", " jxpj_jshp_tkgl t1, jxpj_jshp_tkpj t2 ");
            param.put("whereSql", " and t1.tkjsid = '" + yhm + "' ");
        }
		return dao.getEvaluateCount(param);
	}

}
