package com.zfsoft.wjdc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.zfsoft.common.service.BaseServiceImpl;
import com.zfsoft.util.base.StringUtil;
import com.zfsoft.wjdc.dao.daointerface.IWjBaseDao;
import com.zfsoft.wjdc.dao.entites.StglModel;
import com.zfsoft.wjdc.dao.entites.WjffglModel;
import com.zfsoft.wjdc.dao.entites.WjglModel;
import com.zfsoft.wjdc.dao.entites.WjpzModel;
import com.zfsoft.wjdc.dao.entites.WjpzSjylxModel;
import com.zfsoft.wjdc.dao.entites.XxglModel;
import com.zfsoft.wjdc.service.svcinterface.IWjBaseService;

public class WjBaseServiceImpl extends BaseServiceImpl<Object, IWjBaseDao> implements IWjBaseService{
	
	/**获取问卷对象*/
	public WjglModel getWjglModel(WjglModel model) throws Exception{
		return dao.getWjglModel(model);
	}
	
	/**获取问卷约束功能路径*/
	public String getWjYsgnPath() throws Exception{
		return dao.getWjYsgnPath();
	}
	
	/**获取问卷约束功能路径对应的问卷*/
	public List<WjglModel> getWjysgnPathWjList(HashMap<String, String> param) throws Exception{
		return dao.getWjysgnPathWjList(param);
	}
	
	/**获取问卷类型列表*/
	public List<HashMap<String, String>> getWjlxList() throws Exception{
		return dao.getWjlxList();
	}
	
	/**获取问卷状态列表*/
	public List<HashMap<String, String>> getWjztList() throws Exception{
		return dao.getWjztList();
	}
	
	/**获取问卷配置_数据源类型列表_通用方法*/
	public List<WjpzSjylxModel> getWjpzSjylxList() throws Exception {
		return dao.getWjpzSjylxList();
	}
	
	/**获取问卷配置_数据源类型*/
	public WjpzSjylxModel getWjpzSjylxModel(String lxid) throws Exception {
		return dao.getWjpzSjylxModel(lxid);
	}
	
	/**
	 * 获取问卷配置_数据源类型_根据sessionlxid
	 * @param sessionlxid
	 * @return
	 * @throws Exception
	 */
	public WjpzSjylxModel getWjpzSjylxBySessionLxidModel(String sessionlxid) throws Exception{
		return dao.getWjpzSjylxBySessionLxidModel(sessionlxid);
	}

	/**获取功能类别查询条件配置列表*/
	public List<WjpzModel> getGnlbCxjgPzList(WjpzModel model) throws Exception{
		return dao.getGnlbCxjgPzList(model);
	}

	/**获取功能类别查询结果配置列表*/
	public List<WjpzModel> getGnlbCxtjPzList(WjpzModel model) throws Exception{
		return dao.getGnlbCxtjPzList(model);
	}

	/**查询字段列表选项_通用方法*/
	public List<HashMap<String, String>> getZdoptionList(WjpzModel model)
			throws Exception {
		if ("sfff".equalsIgnoreCase(model.getZd())) {
			return getSfffList();
		}
		if ("djzt".equalsIgnoreCase(model.getZd())) {
			return getDjztList();
		}
		return dao.getCxtjzdzList(model);
	}
	
	/**获取是否分发列表*/
	public List<HashMap<String, String>> getSfffList() {
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("MC", "未分发");
		rs.add(map);
		map = new HashMap<String, String>();
		map.put("MC", "已分发");
		rs.add(map);
		return rs;
	}
	
	/**获取答卷状态列表*/
	public List<HashMap<String, String>> getDjztList() {
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("MC", "未答卷");
		rs.add(map);
		map = new HashMap<String, String>();
		map.put("MC", "已答卷");
		rs.add(map);
		return rs;
	}

	/**获取问卷对象查询结果列表*/
	public List<HashMap<String, String>> getWjdxCxjgList(WjffglModel model,
			Map<String, String> cxtjMap, List<WjpzModel> cxjgList,
			WjpzSjylxModel sjyModel) throws Exception {
		String sqls = getWjdxcxSql(cxtjMap, cxjgList, sjyModel, model);
		//WjpzModel model =  new WjpzModel();
		model.setSqls(sqls);
		return dao.getPagedWjdxList(model);
	}
	
	/**将查询结果转化为List<String[]>进行输出*/
	public List<String[]> getWjdxCxjgList(List<HashMap<String, String>> list,
			List<WjpzModel> cxjgList, String zj) throws Exception {
		List<String[]> rs = new ArrayList<String[]>();
		if (cxjgList != null && !cxjgList.isEmpty()) {
			WjpzModel model = new WjpzModel();
			model.setZd("pkValue");
			cxjgList.add(0, model);
			int strs_length=cxjgList.size();
			for (HashMap<String,String> map : list) {
				String[] strs=new String[strs_length];
				for(int i=0;i<cxjgList.size();i++){
					strs[i]=map.get(cxjgList.get(i).getZd().toUpperCase());
				}
				rs.add(strs);
			}
		}
		return rs;
	}
	
	/**
	 * 拼装问卷对象查询结果SQL
	 * @param cxtjMap
	 * @param cxjgList
	 * @param bm
	 * @return
	 * @throws Exception
	 */
	public String getWjdxcxSql(Map<String, String> cxtjMap,
			List<WjpzModel> cxjgList, WjpzSjylxModel sjyModel, WjffglModel wjmodel) throws Exception {
		StringBuffer sql = new StringBuffer("select ");
		sql.append(sjyModel.getZj());
		sql.append(" pkValue,");
		if (cxjgList != null && !cxjgList.isEmpty()) {
			for (WjpzModel model : cxjgList) {
				sql.append(model.getZd());
				sql.append(",");
			}
			sql.append("rownum r from ");
			sql.append(sjyModel.getBm());
			sql.append(" a ");
			sql.append(getWhereSql(cxtjMap));
			sql.append("and not exists (select 1 from wjdc_djrffb b where b.wjid='");
			sql.append(wjmodel.getWjid());
			sql.append("' and b.lxid='");
			sql.append(wjmodel.getLxid());
			sql.append("' and b.zjz=a.");
			sql.append(sjyModel.getZj());
			sql.append(")");
		}
		return sql.toString();
	}

	/**获取问卷对象查询表头列表*/
	public List<HashMap<String, String>> getWjdxcxbtList(
			List<WjpzModel> list) throws Exception {
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String,String>>(){};
		if (list != null) {
			for (WjpzModel model : list) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("dm", model.getZd());
				map.put("mc", model.getZdmc());
				rs.add(map);
			}
		}
		return rs;
	}
	
	/**
	 * 拼装WHERE条件
	 * @param map
	 * @return
	 */
	public String getWhereSql(Map<String, String> map) {
		StringBuffer whereSql = new StringBuffer(" where 1=1 ");
		if (map != null && !map.isEmpty()) {
			Set<Entry<String, String>> set = map.entrySet();
			for (Entry<String, String> entry : set) {
				//循环出每个条件进行拼装
				if (entry != null && StringUtil.isNotEmpty(entry.getValue())) {
					whereSql.append(" and ");
					whereSql.append(entry.getKey());
					whereSql.append(" like '%");
					whereSql.append(entry.getValue().replaceAll(".*([';]+|(--)+).*", " "));
					whereSql.append("%'");
				}
			}
		}
		return whereSql.toString();
	}

	/**格式化REQUESTMAP内容*/
	public Map<String, String> formatMap(Map<String, String[]> map) {
		Map<String, String> rs = new HashMap<String, String>();
		if (map != null && !map.isEmpty()) {
			Set<Entry<String, String[]>> set = map.entrySet();
			for(Entry<String, String[]> entry : set) {
				if (entry.getValue() != null && entry.getValue().length > 0
						&& entry.getKey().contains("cx_")) {
					rs.put(entry.getKey().substring(3, entry.getKey().length()),
							entry.getValue()[0]);
				}
			}
		}
		return rs;
	}
	
	/**获取已发放问卷SQL*/
	public String getYffwjdxSql(Map<String, String> cxtjMap,
			List<WjpzModel> cxjgList, WjpzSjylxModel sjyModel, WjffglModel wjmodel) {
		StringBuffer sql = new StringBuffer("select * from (select * from (select ");
		sql.append(sjyModel.getZj());
		sql.append(" pkValue,rownum r,");
		
		sql.append("(select (case when sfff='1' and djzt = '已答卷' then 'disabled=true' else '' end) from wjdc_djrffb c where c.zjz=a.");
		sql.append(sjyModel.getZj());
		sql.append(" and c.wjid='"+wjmodel.getWjid()+"'");
		sql.append(" and c.lxid ='"+wjmodel.getLxid()+"'");
		sql.append(") disabled,");
		if (cxjgList != null && !cxjgList.isEmpty()) {
			for (int i=0;i<cxjgList.size();i++) {
				WjpzModel model = cxjgList.get(i);
				sql.append(model.getZd());
				//if (i!=cxjgList.size()-1) {					
					sql.append(",");
				//}
			}
			//增加是否发放标志 begin
			//end
			sql.append("sfff from ");
			
			sql.append("(select a.*,");
			sql.append("(select (case when sfff='1' then '已分发' else '未分发' end) from wjdc_djrffb c where c.zjz=a.");
			sql.append(sjyModel.getZj());
			sql.append(" and c.wjid='"+wjmodel.getWjid()+"'");
			sql.append(" and c.lxid ='"+wjmodel.getLxid()+"'");
			sql.append(") sfff from ");
			
			sql.append(sjyModel.getBm());
			sql.append(" a) a");
			sql.append(getWhereSql(cxtjMap));
			sql.append(")a ");
			sql.append(" where exists (select 1 from wjdc_djrffb b where b.wjid='");
			sql.append(wjmodel.getWjid());
			sql.append("' and b.lxid='");
			sql.append(wjmodel.getLxid());
			sql.append("' and b.zjz=a.pkValue");
			//sql.append(sjyModel.getZj());
			sql.append(")");
		}
		sql.append(") order by disabled desc");
		return sql.toString();
	}
	
	/**获取已发放问卷对象查询结果列表*/
	public List<HashMap<String, String>> getYffWjdxCxjgList(WjffglModel model,
			Map<String, String> cxtjMap, List<WjpzModel> cxjgList,
			WjpzSjylxModel sjyModel) throws Exception {
		String sqls = getYffwjdxSql(cxtjMap, cxjgList, sjyModel, model);
		//WjpzModel model =  new WjpzModel();
		model.setSqls(sqls);
		return dao.getPagedWjdxList(model);
	}
	
	/**根据条件查询问卷对象表数据*/
	public boolean getWjdxbsjByTj(Map<String, String> map) throws Exception{
		WjpzModel model = new WjpzModel();
		StringBuffer sql = new StringBuffer("select count(*) from ");
		sql.append(map.get("bm"));
		map.remove("bm");
		sql.append(getWhereSql(map));
		model.setSqls(sql.toString());
		String count = dao.getWjdxbsjByTj(model);
		return "0".equalsIgnoreCase(count) ? false : true;
	}
	
	/**格式化MAP转为String*/
	public String formatMaptoStr(Map<String, String> map) {
		StringBuffer rs = new StringBuffer("");
		if (map != null && !map.isEmpty()) {
			Set<Entry<String, String>> set = map.entrySet();
			for (Entry<String, String> entry : set) {
				if (StringUtil.isNotEmpty(entry.getValue())) {
					rs.append(entry.getKey());
					rs.append("!!=@@");
					rs.append(entry.getValue());
					rs.append("!!@@split!!@@");
				}
			}
		}
		return rs.toString();
	}
	
	/**
	 * 获取问卷单个试题选项列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<XxglModel> getWjOneStXxList(StglModel model) throws Exception{
		if(model.getWjid()==null){
			model.setWjid("");
		}
		if(model.getStid()==null){
			model.setStid("");
		}
		List<XxglModel> xxList=dao.getWjOneStXxList(model);
		if(xxList==null){
			xxList=new ArrayList<XxglModel>();
		}
		return xxList;
	}
	
}
