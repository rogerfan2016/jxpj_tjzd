package com.zfsoft.wjdc.service.svcinterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zfsoft.common.service.BaseService;
import com.zfsoft.wjdc.dao.entites.StglModel;
import com.zfsoft.wjdc.dao.entites.WjglModel;
import com.zfsoft.wjdc.dao.entites.WjpzModel;
import com.zfsoft.wjdc.dao.entites.WjpzSjylxModel;
import com.zfsoft.wjdc.dao.entites.WjtjModel;
import com.zfsoft.wjdc.dao.entites.XxglModel;

public interface IWjtjService extends BaseService<WjtjModel>{

	/**获取问卷已分发的数据源*/
	public List<WjpzSjylxModel> getWjyffSjylxList(WjtjModel model) throws Exception;
	
	/**根据数据源指定的字段获取其列表*/
	public List<WjpzModel> getFieldPzListByFields(WjpzModel model) throws Exception;
	
	/**获取答卷统计列表*/
	public List<HashMap<String,Object>> getDjtjList(WjtjModel model,WjpzSjylxModel sjyModel,String whereSql) throws Exception;
	
	/**获取试题和试题大类排序后的列表*/
	public List<StglModel> getStxxAndStdlXxList(WjtjModel model) throws Exception;
	
	/**获取问卷选择试题统计信息*/
	public List<HashMap<String,Object>> getWjxzstTjxx(WjtjModel model,WjpzSjylxModel sjyModel,String whereSql) throws Exception;
	
	/**获取问卷选择试题交叉统计信息*/
	public List<HashMap<String,Object>> getWjxzstJctjxx(WjtjModel model,WjpzSjylxModel sjyModel,String whereSql,List<XxglModel> xxList,
			HttpServletRequest request) throws Exception;
	
	/**获取问卷选项试题列表*/
	public List<StglModel> getWjxxstxx(WjglModel model) throws Exception;
	
	/**获取答卷详情列表*/
	public List<String[]> getDjxqList(String whereSql,
			List<WjpzModel> cxjgList, WjpzSjylxModel sjyModel,WjtjModel model) throws Exception;
	
	/**获取交叉统计参数配置列表*/
	public List<HashMap<String,Object>> getJctjcspzList(WjtjModel model) throws Exception;
	
	/**获取交叉统计参数配置分组字段*/
	public String[] getJctjcspzOne(WjtjModel model) throws Exception;
	
}
