package com.zfsoft.wjdc.service.svcinterface;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.common.service.BaseService;
import com.zfsoft.wjdc.dao.entites.WjglModel;

public interface IWjglService  extends BaseService<WjglModel>{
	
	/**删除问卷信息*/
	public String scWjxx(String wjid) throws Exception;
	
	/**更新问卷状态*/
	public boolean updateWjzt(WjglModel model) throws Exception;
	
	/**获取问卷功能约束列表*/
	public List<HashMap<String,String>> getWjgnysList(WjglModel model) throws Exception;
	
	/**更新问卷功能约束*/
	public boolean updateWjgnys(WjglModel model) throws Exception;

}
