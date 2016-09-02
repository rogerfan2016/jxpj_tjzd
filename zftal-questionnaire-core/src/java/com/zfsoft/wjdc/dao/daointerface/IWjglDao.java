package com.zfsoft.wjdc.dao.daointerface;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.common.dao.BaseDao;
import com.zfsoft.wjdc.dao.entites.WjglModel;

public interface IWjglDao  extends BaseDao<WjglModel>{
	
	/**删除问卷信息*/
	public int delete(String wjid) throws Exception;
	
	/**删除问卷试题信息*/
	public int deleteWjSt(String wjid) throws Exception;
	
	/**删除问卷试题选项信息*/
	public int deleteWjStXx(String wjid) throws Exception;
	
	/**更新问卷状态*/
	public int updateWjzt(WjglModel model) throws Exception;
	
	/**获取问卷相关人数*/
	public HashMap getWjxgrs(WjglModel model) throws Exception;
	
	/**获取问卷功能约束列表*/
	public List<HashMap<String,String>> getWjgnysList(WjglModel model) throws Exception;
	
	/**删除问卷功能约束*/
	public int deleteWjgnys(WjglModel model) throws Exception;
	
	/**插入问卷功能约束*/
	public int insertWjgnys(WjglModel model) throws Exception;
}
