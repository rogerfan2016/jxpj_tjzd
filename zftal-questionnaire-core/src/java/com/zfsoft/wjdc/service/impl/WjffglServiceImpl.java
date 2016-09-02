package com.zfsoft.wjdc.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.zfsoft.common.service.BaseServiceImpl;
import com.zfsoft.util.base.BeanUtils;
import com.zfsoft.util.base.StringUtil;
import com.zfsoft.wjdc.dao.daointerface.IWjffglDao;
import com.zfsoft.wjdc.dao.entites.WjffglModel;
import com.zfsoft.wjdc.dao.entites.WjpzSjylxModel;
import com.zfsoft.wjdc.service.svcinterface.IWjffglService;

/**
 * 问卷发放管理SERVICEIMPL
 * @author Administrator
 *
 */
public class WjffglServiceImpl extends BaseServiceImpl<WjffglModel, IWjffglDao> 
	implements IWjffglService {

	/**
	 * 查询问卷发放列表
	 * @param model
	 * @return
	 */
	public List<WjffglModel> getPagedList(WjffglModel model) {
		return dao.getPagedList(model);
	}
	
	/**保存问卷发放对象*/
	public boolean bcWjffdx(WjffglModel model) throws Exception {
		boolean result = false;
		result = plscWjffdx(model);
		if (result) {
			result = plcrWjffdx(model);
		}
		return result;
	}
	
	/**批量删除问卷发放对象*/
	public boolean plscWjffdx(WjffglModel model) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("wjid", model.getWjid());
		map.put("lxid", model.getLxid());
		map.put("list", getWjffList(model));
		int count = dao.batchDeleteWjffdx(map);
		return count >= 0 ? true : false;
	}

	/**
	 * 将MODEL转为LIST
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private List<WjffglModel> getWjffList(WjffglModel model)
			throws IllegalAccessException, InvocationTargetException {
		String[] zjzArray = model.getPkValue();
		List<WjffglModel> list = new ArrayList<WjffglModel>();
		if (zjzArray != null && zjzArray.length > 0) {
			for (String s : zjzArray) {
				WjffglModel record = new WjffglModel();
				BeanUtils.copyProperties(record, model);
				record.setZjz(s);
				list.add(record);
			}
		}
		return list;
	}
	
	/**
	 * 批量插入问卷发放对象
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean plcrWjffdx(WjffglModel model) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("list", getWjffList(model));
		int count = dao.batchInsertWjffdx(map);
		return count > 0 ? true : false;
	}
	
	/**批量删除问卷对象信息*/
	public boolean plscYffwjffdx(WjffglModel model) throws Exception{
		boolean result = false;
		String[] pkValue = model.getPkValue();
		if (pkValue != null && pkValue.length > 0) {
			HashMap<String, Object> rs = new HashMap<String, Object>();
			rs.put("wjid", model.getWjid());
			rs.put("lxid", model.getLxid());
			List<WjffglModel> list = new ArrayList<WjffglModel>();
			for (String s : pkValue) {
				WjffglModel record = new WjffglModel();
				record.setZjz(s);
				list.add(record);
			}
			rs.put("list", list);
			int count = dao.batchDeleteYffwjffdx(rs);
			result = count > 0 ? true : false;
		}
		return result;
	}
	
	/**修改问卷发放标志
	public boolean xgWjffdxbz(WjffglModel model) throws Exception{
		int count = dao.updateWjffdxbz(model);
		return count > 0 ? true : false;
	}
	*/
	/**修改问卷发放标志*/
	public boolean xgWjffdxbz(WjffglModel model) throws Exception{
		if(model == null){
			return false;
		}
		String[] pkValue = model.getPkValue();
		int count = 0;
		if (pkValue != null && pkValue.length > 0) {
			HashMap<String, Object> rs = new HashMap<String, Object>();
			rs.put("wjid", model.getWjid());
			List<WjffglModel> list = new ArrayList<WjffglModel>();
			for (String s : pkValue) {
				WjffglModel record = new WjffglModel();
				record.setZjz(s);
				list.add(record);
			}
			rs.put("list", list);
			count = dao.updateWjffdxbz(rs);
		}
		return count > 0;
	}
	
	/**
	 * 拼装WHERE条件
	 * @param map
	 * @return
	 */
	public String getWhereSql(Map<String, String> map) {
		StringBuffer whereSql = new StringBuffer("");
		if (map != null && !map.isEmpty()) {
			Set<Entry<String, String>> set = map.entrySet();
			for (Entry<String, String> entry : set) {
				//循环出每个条件进行拼装
				if (entry != null && StringUtil.isNotEmpty(entry.getValue())) {
					whereSql.append(" and b.");
					whereSql.append(entry.getKey());
					whereSql.append(" like '%");
					whereSql.append(entry.getValue().replaceAll(".*([';]+|(--)+).*", " "));
					whereSql.append("%'");
				}
			}
		}
		return whereSql.toString();
	}
	
	/**
	 * 获取按条件下删除的SQL
	 * @param wjpzModel
	 * @param map
	 * @return
	 */
	public String getPlscWjdxBytjSql(WjpzSjylxModel sjyModel, Map<String, String> map) {
		StringBuffer sql = new StringBuffer("select 1 from ");
		sql.append(sjyModel.getBm());
		sql.append(" b where a.zjz=b.");
		sql.append(sjyModel.getZj());
		sql.append(getWhereSql(map));
		return sql.toString();
	}
	
	/**
	 * 根据条件批量删除问卷对象信息
	 * @param sjyModel
	 * @param map
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean plscWjdxBytj(WjpzSjylxModel sjyModel, Map<String, String> map, WjffglModel model) throws Exception {
		model.setSqls(getPlscWjdxBytjSql(sjyModel, map));
		int count = dao.batchDeleteWjffdxBytj(model);
		return count >= 0 ? true : false;
	}
	
	/**根据条件批量插入问卷发放对象*/
	public boolean plcxWjdxBytj(WjpzSjylxModel sjyModel, Map<String, String> map, WjffglModel model) throws Exception {
		StringBuffer sql = new StringBuffer("select '");
		sql.append(model.getWjid());
		sql.append("' wjid,'");
		sql.append(model.getLxid());
		sql.append("' lxid,");
		sql.append(sjyModel.getZj());
		sql.append(" from ");
		sql.append(sjyModel.getBm());
		sql.append(" b where 1=1 ");
		sql.append(getWhereSql(map));
		sql.append("and not exists (select 1 from wjdc_djrffb c where c.wjid='");
		sql.append(model.getWjid());
		sql.append("' and c.lxid='");
		sql.append(model.getLxid());
		sql.append("' and c.zjz=b.");
		sql.append(sjyModel.getZj());
		sql.append(")");
		model.setSqls(sql.toString());
		int count = dao.batchInsertWjffdxBytj(model);
		return count >= 0 ? true : false;
	}
	
	/**
	 * 根据条件保存问卷
	 * @param sjyModel
	 * @param map
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean bcWjdxBytj(WjpzSjylxModel sjyModel, Map<String, String> map, WjffglModel model) throws Exception {
		boolean result = plscWjdxBytj(sjyModel, map, model);
		result = result ? plcxWjdxBytj(sjyModel, map, model) : false;
		return result;
	}

	/**
	 * 修改问卷发放对象标志 未分发
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean xgWjffdxbzWff(WjffglModel model) throws Exception {
		if(model == null){
			return false;
		}
		String[] pkValue = model.getPkValue();
		int count = 0;
		if (pkValue != null && pkValue.length > 0) {
			HashMap<String, Object> rs = new HashMap<String, Object>();
			rs.put("wjid", model.getWjid());
			List<WjffglModel> list = new ArrayList<WjffglModel>();
			for (String s : pkValue) {
				WjffglModel record = new WjffglModel();
				record.setZjz(s);
				list.add(record);
			}
			rs.put("list", list);
			count = dao.updateWjffdxbzWff(rs);
		}
		return count > 0;
	}
}
