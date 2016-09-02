package com.zfsoft.wjdc.service.svcinterface;

import java.util.Map;

import com.zfsoft.common.service.BaseService;
import com.zfsoft.wjdc.dao.entites.WjffglModel;
import com.zfsoft.wjdc.dao.entites.WjpzSjylxModel;

/**
 * 问卷发放管理SERVICE
 * @author Administrator
 *
 */
public interface IWjffglService extends BaseService<WjffglModel> {
	public static final String DJZT_YDJ="已答卷";//答卷状态：已答卷
	public static final String DJZT_WDJ="未答卷";//答卷状态：未答卷
	
	
	/**
	 * 保存问卷发放对象
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean bcWjffdx(WjffglModel model) throws Exception;

	/**
	 * 批量删除已发放问卷对象信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean plscYffwjffdx(WjffglModel model) throws Exception;
	
	/**
	 * 修改问卷发放对象标志
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean xgWjffdxbz(WjffglModel model) throws Exception;
	
	/**
	 * 根据条件保存问卷对象
	 * @param sjyModel
	 * @param map
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean bcWjdxBytj(WjpzSjylxModel sjyModel, Map<String, String> map, WjffglModel model) throws Exception;
	
	/**
	 * 修改问卷发放对象标志 未分发
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean xgWjffdxbzWff(WjffglModel model) throws Exception;
}
