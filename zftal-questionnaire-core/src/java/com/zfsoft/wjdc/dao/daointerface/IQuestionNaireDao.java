package com.zfsoft.wjdc.dao.daointerface;

import java.util.Map;

import com.zfsoft.wjdc.dao.entites.WjglModel;

/**
 * 
 * @author ChenMinming
 * @date 2015-2-3
 * @version V1.0.0
 */
public interface IQuestionNaireDao {
	/**
	 * 复制试题大类信息
	 * @param model
	 */
	public void copyStdlxx(WjglModel model);
	/**
	 * 复制试题信息
	 * @param model
	 */
	public void copyStxx(WjglModel model);
	/**
	 * 复制试题选项信息
	 * @param model
	 */
	public void copyXxxx(WjglModel model);
	/**
	 * 复制问卷信息
	 * @param model
	 */
	public void copyWjxx(WjglModel model);
	
	public Map<String, Object> findModelSummer(WjglModel model);
	
}
