package com.zfsoft.wjdc.dao.daointerface;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.common.dao.BaseDao;
import com.zfsoft.wjdc.dao.entites.StglModel;
import com.zfsoft.wjdc.dao.entites.WjglModel;
import com.zfsoft.wjdc.dao.entites.WjpzModel;
import com.zfsoft.wjdc.dao.entites.WjpzSjylxModel;
import com.zfsoft.wjdc.dao.entites.WjtjModel;
import com.zfsoft.wjdc.dao.entites.XxglModel;

public interface IWjtjDao extends BaseDao<WjtjModel>{
	
	/**获取问卷已分发的数据源*/
	public List<WjpzSjylxModel> getWjyffSjylxList(WjtjModel model) throws Exception;
	
	/**根据数据源指定的字段获取其列表*/
	public List<WjpzModel> getFieldPzListByFields(WjpzModel model) throws Exception;
	
	/**获取传入sql的结果集列表*/
	public List<HashMap<String,Object>> getSqlMapList(WjtjModel model) throws Exception;
	
	/**获取传入sql的结果集列表(分页)*/
	public List<HashMap<String,Object>> getPagedSqlMapList(WjtjModel model) throws Exception;
	
	/**获取试题和试题大类排序后的列表*/
	public List<StglModel> getStxxAndStdlXxList(WjtjModel model) throws Exception;
	
	/**获取问卷选项试题列表*/
	public List<StglModel> getWjxxstxx(WjglModel model) throws Exception;
	
	/**获取特定问卷试题的选项列表*/
	public List<XxglModel> getStxxList(WjtjModel model) throws Exception;

	/**获取交叉统计参数配置列表*/
	public List<HashMap<String,Object>> getJctjcspzList(WjtjModel model) throws Exception;
	
	/**获取交叉统计参数配置分组字段*/
	public HashMap<String,Object> getJctjcspzOne(WjtjModel model) throws Exception;
	
}
