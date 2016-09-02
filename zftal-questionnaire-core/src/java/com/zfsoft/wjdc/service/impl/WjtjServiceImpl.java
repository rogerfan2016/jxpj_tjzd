package com.zfsoft.wjdc.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zfsoft.common.service.BaseServiceImpl;
import com.zfsoft.wjdc.dao.daointerface.IWjtjDao;
import com.zfsoft.wjdc.dao.entites.StglModel;
import com.zfsoft.wjdc.dao.entites.WjglModel;
import com.zfsoft.wjdc.dao.entites.WjpzModel;
import com.zfsoft.wjdc.dao.entites.WjpzSjylxModel;
import com.zfsoft.wjdc.dao.entites.WjtjModel;
import com.zfsoft.wjdc.dao.entites.XxglModel;
import com.zfsoft.wjdc.service.svcinterface.IWjtjService;

public class WjtjServiceImpl extends BaseServiceImpl<WjtjModel, IWjtjDao> implements IWjtjService {
	
	private final String groupFieldNullAs="--未知数据--";
	
	/**获取问卷已分发的数据源*/
	public List<WjpzSjylxModel> getWjyffSjylxList(WjtjModel model) throws Exception{
		return dao.getWjyffSjylxList(model);
	}
	
	/**根据数据源指定的字段获取其列表*/
	public List<WjpzModel> getFieldPzListByFields(WjpzModel model) throws Exception{
		if(model.getFields()==null||model.getFields().length==0){
			return new ArrayList<WjpzModel>();
		}
		return dao.getFieldPzListByFields(model);
	}
	
	/**获取答卷统计列表*/
	public List<HashMap<String,Object>> getDjtjList(WjtjModel model,WjpzSjylxModel sjyModel,String whereSql) throws Exception{
//		String sql_="select bjdm_id,count(1) wjffrs,count(decode(djzt,'已答卷',1)) wjdjrs,round(count(decode(djzt,'已答卷',1))*100/count(1),2) wjdjrsbfb "+
//					"from  "+
//					"( "+
//					"select * from zftal_xtgl_xsxxb a left join wjdc_djrffb b on a.xh=b.zjz "+
//					"where b.wjid='123' and b.lxid='xs' and b.sfff='1' "+
//					") group by rollup(bjdm_id)";

		//拼接分组字符串
		StringBuffer groupFieldStr=new StringBuffer();
		String[] groupFields=model.getGroupFields();
		if(groupFields!=null&&groupFields.length>0){
			for(int i=0;i<groupFields.length;i++){
				groupFieldStr.append(groupFields[i]);
				if(i<groupFields.length-1){
					groupFieldStr.append(",");
				}
			}
		}
		
		//组装sql
		StringBuffer sql=new StringBuffer("select ");
		if(groupFieldStr.length()>0){
			sql.append(groupFieldStr);
			sql.append(",");
		}
		sql.append(" count(1) wjffrs,count(decode(djzt,'已答卷',1)) wjdjrs,decode(count(1),0,0,round(count(decode(djzt,'已答卷',1))*100/count(1),2)) wjdjrsbfb ");
		sql.append(" from ( ");
		sql.append(" select * from (select * from ");
		sql.append(sjyModel.getBm());
		sql.append(whereSql);
		sql.append(") a left join wjdc_djrffb b on a.");
		sql.append(sjyModel.getZj());
		sql.append(" =b.zjz ");
		sql.append(" where b.wjid='");
		sql.append(model.getWjid());
		sql.append("' and b.lxid='");
		sql.append(model.getLxid());
		sql.append("' and b.sfff='1' ");
		sql.append(" ) ");
		if(groupFieldStr.length()>0){
			sql.append(" group by rollup( ");
			sql.append(groupFieldStr);
			sql.append(" ) ");
		}
		model.setSql(sql.toString());
		return dao.getSqlMapList(model);
	}
	
	/**获取试题和试题大类排序后的列表*/
	public List<StglModel> getStxxAndStdlXxList(WjtjModel model) throws Exception{
		return dao.getStxxAndStdlXxList(model);
	}
	
	/**获取问卷选择试题统计信息*/
	public List<HashMap<String,Object>> getWjxzstTjxx(WjtjModel model,WjpzSjylxModel sjyModel,String whereSql) throws Exception{
		String wjid=model.getWjid();
		
		StringBuffer sql=new StringBuffer();
		sql.append("select a.stid,a.xxid,a.xxrs,a.xxzf, ");
	    sql.append("decode(a.xxid,null,'',to_number(decode(a.xxid,null,'',round(a.xxrs/decode(a.strs,0,1,a.strs)*100,2)))||'%') xxrsbfb, ");
	    sql.append("b.xxmc ");
	    sql.append("from ( ");
	    sql.append("select a.stid,a.xxid,count(distinct djrid) xxrs,");
	    sql.append("sum(case when djrid is not null then nvl(xxfz,0) when djrid is null then '0' end) as xxzf,");
	    sql.append("(select count(distinct djrid) from wjdc_wjhdb x where x.wjid='"+wjid+"' and x.stid=a.stid ");
	    sql.append("and exists (select 1 from ");
	    sql.append(sjyModel.getBm());
	    sql.append(" y ");
	    sql.append(whereSql);
	    sql.append(" and  y."+sjyModel.getZj()+"=x.djrid)");
	    
	    sql.append(") strs from ");
	    sql.append("(select * from wjdc_wjstxxb where wjid='"+wjid+"') a "); 
	    sql.append(" left join "); 
		sql.append(" (select * from wjdc_wjhdb where wjid='"+wjid+"') b ");
		sql.append(" on a.stid=b.stid and a.xxid=b.xxid ");
	    sql.append("and (exists (select 1 from ");
	    sql.append(sjyModel.getBm());
	    sql.append(" y ");
	    sql.append(whereSql);
	    sql.append(" and  y."+sjyModel.getZj()+"=b.djrid) or b.djrid is null)");

	    sql.append("group by rollup(a.stid,a.xxid) ");
	    
	    
	    
	    sql.append(") a ");
	    sql.append("left join ");
	    sql.append("(select * from wjdc_wjstxxb where wjid='"+wjid+"') ");
	    sql.append("b on a.stid=b.stid and a.xxid=b.xxid ");
	    sql.append("left join ");
	    sql.append("(select * from wjdc_wjstb where wjid='"+wjid+"') ");
	    sql.append("c on a.stid=c.stid ");
	    sql.append("order by to_number(c.xssx),to_number(b.xssx)");
	    
		model.setSql(sql.toString());
		
		List<HashMap<String,Object>> rsList=new ArrayList<HashMap<String,Object>>();
		List<StglModel> stList=dao.getStxxAndStdlXxList(model);
		List<HashMap<String,Object>> sttjList=dao.getSqlMapList(model);
		
		int sttjIndex=0;
		for (StglModel st : stList) {
			List<HashMap<String,Object>> sttjOneList=new ArrayList<HashMap<String,Object>>();
			HashMap<String,Object> sttjMap=new HashMap<String, Object>();
			sttjMap.put("stModel", st);
			sttjMap.put("sttjOneList", sttjOneList);
			
			HashMap<String,Object> sttj;
			for(int i=sttjIndex;i<sttjList.size();i++){
				sttj=sttjList.get(i);
				if(st.getStid().equals((String)sttj.get("STID"))){
					
					sttjOneList.add(sttj);
					sttjIndex++;
				}else{
					break;
				}
			}
			//sttjMap.put("reportHtml", fullDataXML(st, sttjOneList));
			rsList.add(sttjMap);
		}
		return rsList;//dao.getSqlMapList(model);
	}
	
	
//	private static String fullDataXML(StglModel st,List<HashMap<String,Object>> sttjOneList) {
//		String[] color = new String[] { "9D080D", "AFD8F8", "F6BD0F", "8BBA00", "FF8E46", "FF8E46", "008E8E", "D64646", "8E468E",
//				"588526", "B3AA00", "008ED6", "A186BE" };
//		StringBuffer xmlData = new StringBuffer();
//		xmlData.append("<?xml version='1.0' encoding='GBK'?>");
//		xmlData.append("<graph caption='' xAxisName='' yAxisName='' decimalPrecision='0' formatNumberScale='0'>");
//		int i=0;
//		for (Map<String,Object> map : sttjOneList) {
//			if(map.get("XXMC")==null||map.get("XXMC")=="") continue;
//			String str = map.get("XXMC").toString().substring(0,1);
//			xmlData.append("<category name='" + StringToXML(str.substring(0)) + "' />");
//			String usecolor = color[i % color.length];
//			xmlData.append("<set name='" + StringToXML(str.substring(0)) + "' value='"+map.get("XXRS")+"' color='"+usecolor+"' showValues='1'/>");
//			i++;
//		}
//		xmlData.append("</graph>");
//		return xmlData.toString();
//	}

	private static String StringToXML(String value) {
		value = value.toString().replaceAll("&", "&amp;");
		value = value.toString().replaceAll("<", "&lt;");
		value = value.toString().replaceAll(">", "&gt;");
		return value;
	}

	
	/**获取问卷选择试题交叉统计信息*/
	public List<HashMap<String,Object>> getWjxzstJctjxx(WjtjModel model,WjpzSjylxModel sjyModel,String whereSql,List<XxglModel> xxList
			,HttpServletRequest request) throws Exception{
		List<HashMap<String,Object>> tjxxList;
		String[] groupFields=new String[]{};
		String xy_colspan="1";
		if(model.getStidx()!=null&&!"".equals(model.getStidx())&&model.getStidy()!=null&&!"".equals(model.getStidy())){
			tjxxList=getWjxzstJcStTjxx(model, sjyModel, whereSql, xxList);
			groupFields=new String[]{model.getStidx()};
		}else if(model.getGroupFields()!=null&&model.getGroupFields().length>0&&model.getStidy()!=null&&!"".equals(model.getStidy())){
			//首先处理掉空的分组，以便后面程序的处理
			String[] groupFields_temp=model.getGroupFields();
			int colspan=0;
			for(int i=0;i<groupFields_temp.length;i++){
				if(groupFields_temp[i]!=null&&!"".equals(groupFields_temp[i])){
					colspan++;
				}
			}
			groupFields=new String[colspan];
			colspan=0;
			for(int i=0;i<groupFields_temp.length;i++){
				if(groupFields_temp[i]!=null&&!"".equals(groupFields_temp[i])){
					groupFields[colspan]=groupFields_temp[i];
					colspan++;
				}
			}
			model.setGroupFields(groupFields);
			
			tjxxList=getWjxzstJcSjyTjxx(model, sjyModel, whereSql, xxList);
			xy_colspan=colspan+"";
			//下面是用于处理合并单元格预处理的数据标记 start
			if(colspan>1){//只有出现大于一个的分组项才需要进行合并单元格
				int[][] group_count=getFzxGroupCount(model, sjyModel, whereSql,colspan);
				int tjxxList_index=0;
				for(int i=0;i<groupFields.length;i++){
						tjxxList_index=0;
						for(int j=0;j<group_count[i].length;j++){
							tjxxList.get(tjxxList_index).put("XXMC"+i+"ROWSPAN", group_count[i][j]);
							tjxxList_index+=group_count[i][j];
							tjxxList.get(tjxxList_index-1).put("XXMC"+(i+1)+"COLSPAN", groupFields.length-i-1);
							for(int i_col=i+2;i_col<groupFields.length;i_col++){
									tjxxList.get(tjxxList_index-1).put("XXMC"+i_col+"COLSPANNO", "1");
							}
						}
				}
				//处理小计和总计
				if(tjxxList!=null&&tjxxList.size()>0){
					HashMap<String,Object> last = tjxxList.get(tjxxList.size()-1);
					for(int i=1;i<groupFields.length;i++){
						last.put("XXMC"+i, "");
						last.put("XXMC"+i+"COLSPANNO", "1");
//						last.put("XXMC0COLSPAN", groupFields.length);
					}
				}
			}
			if(tjxxList!=null&&tjxxList.size()>0){
				HashMap<String,Object> last = tjxxList.get(tjxxList.size()-1);
				last.put("XXMC0", "总计");
				last.put("XXMC0COLSPAN", groupFields.length);
			}
			//下面是用于处理合并单元格预处理的数据标记 end
		}else{
			tjxxList=new ArrayList<HashMap<String,Object>>();
		}
		request.setAttribute("xy_colspan", xy_colspan);
		request.setAttribute("groupFields", groupFields);
		return tjxxList;
	}
	
	/**获取问卷选择试题交叉试题统计信息*/
	public List<HashMap<String,Object>> getWjxzstJcStTjxx(WjtjModel model,WjpzSjylxModel sjyModel,String whereSql,List<XxglModel> xxList) throws Exception{
		if(model.getStidx()==null||model.getStidy()==null){
			return new ArrayList<HashMap<String,Object>>();
		}
		String wjid=model.getWjid();
		String stid_x=model.getStidx();
		String stid_y=model.getStidy();
		
		//处理数据源查询条件范围
		String sjySqlExists=" and exists ( select 1 from "+sjyModel.getBm()+" y "+whereSql+" and y."+sjyModel.getZj()+"=x.djrid) ";
		
		StringBuffer sql=new StringBuffer();//主sql
		StringBuffer sqlOuter=new StringBuffer();//外层sql
		sqlOuter.append("select xxid,xxmc xxmc0 ");
		StringBuffer sqlInner=new StringBuffer();//内层sql
		sqlInner.append("select a.xxid,a.xxmc,a.xssx ");
		for(XxglModel xx : xxList){
			String xxid=xx.getXxid();
			sqlInner.append(",(select count(1) from wjdc_wjhdb x where x.wjid='"+wjid+"' and x.stid='"+stid_y+"' and x.xxid='"+xxid+"'");
			sqlInner.append(" and exists (select 1 from wjdc_wjhdb y where y.wjid='"+wjid+"' and y.stid='"+stid_x+"' and y.xxid=a.xxid and y.djrid=x.djrid) ");
			sqlInner.append(sjySqlExists+") "+xxid+"_rs ");
			sqlOuter.append(","+xxid+"_rs ");
			sqlOuter.append(",round(decode(zrs,0,0,"+xxid+"_rs/zrs*100),2)||'%' "+xxid+"_rsbfb ");
		}
		sqlInner.append(",(select count(distinct djrid) from wjdc_wjhdb x where x.wjid='"+wjid+"' and x.stid='"+stid_x+"' and x.xxid=a.xxid");
		sqlInner.append(sjySqlExists+") zrs " );
		sqlOuter.append(",zrs ");
		sqlInner.append(" from wjdc_wjstxxb a where wjid='"+wjid+"' and stid='"+stid_x+"' ");
		
		sql.append(sqlOuter);
		sql.append(" from ( ");
		sql.append(sqlInner);
		sql.append(" ) ");
		sql.append(" order by to_number(xssx) ");
		
		model.setSql(sql.toString());
		
		return dao.getSqlMapList(model);
	}
	
	/**问卷选择试题交叉数据源统计信息*/
	public List<HashMap<String,Object>> getWjxzstJcSjyTjxx(WjtjModel model,WjpzSjylxModel sjyModel,String whereSql,List<XxglModel> xxList) throws Exception{
		if(model.getGroupFields()==null||model.getGroupFields().length==0||model.getStidy()==null){
			return new ArrayList<HashMap<String,Object>>();
		}
		String wjid=model.getWjid();
		String[] groupFields=model.getGroupFields();
		StringBuffer sql_gf1=new StringBuffer();
		StringBuffer sql_gf2=new StringBuffer();
		StringBuffer sql_groupFieldNullAs=new StringBuffer();//用于处理null的数据，不然合并单元格会出问题的
		for(int i=0;i<groupFields.length;i++){
			if(groupFields[i]!=null&&!"".equals(groupFields[i])){
				sql_gf1.append("nvl(b.");
				sql_gf1.append(groupFields[i]);
				sql_gf1.append(",'小计') xxmc");
				sql_gf1.append(i);
				sql_gf1.append(" ,");
				
				sql_gf2.append(groupFields[i]);
				sql_gf2.append(",");
				
				sql_groupFieldNullAs.append(", nvl(");
				sql_groupFieldNullAs.append(groupFields[i]);
				sql_groupFieldNullAs.append(",'"+groupFieldNullAs+"')");
				sql_groupFieldNullAs.append(groupFields[i]);
			}
		}
		
		//处理数据源查询条件范围
		String sjySqlExists=" and exists ( select 1 from "+sjyModel.getBm()+" y "+whereSql+" and y."+sjyModel.getZj()+"=x.djrid) ";
		
		StringBuffer sql=new StringBuffer();//主sql
		sql.append("select "+sql_gf1.toString()+" count(distinct a.djrid) zrs ");
		for(XxglModel xx : xxList){
			String xxid=xx.getXxid();
			sql.append(",count(decode(a.xxid,'"+xxid+"',1)) "+xxid+"_rs ");
			sql.append(",decode(count(distinct a.djrid),0,0,round(count(decode(a.xxid,'"+xxid+"',1))/count(distinct a.djrid)*100,2))||'%' "+xxid+"_rsbfb ");
		}
		sql.append(" from (select * from wjdc_wjhdb x where wjid='"+wjid+"' and xxid!='0' "+sjySqlExists+") a ");
		sql.append(" left join (select "+sjyModel.getZj()+sql_groupFieldNullAs.toString()+" from "+sjyModel.getBm()+" "+whereSql+") b on a.djrid=b."+sjyModel.getZj());
		sql.append(" group by rollup("+sql_gf2.substring(0, sql_gf2.length()-1)+")");
		sql.append(" order by "+sql_gf2.substring(0, sql_gf2.length()-1));
		
		model.setSql(sql.toString());
		return dao.getSqlMapList(model);
		
		
//		if(model.getGroupFields()==null||model.getGroupFields().length==0||model.getStidy()==null){
//			return new ArrayList<HashMap<String,Object>>();
//		}
//		String wjid=model.getWjid();
//		String groupField=model.getGroupFields()[0];
//		
//		//处理数据源查询条件范围
//		String sjySqlExists=" and exists ( select 1 from "+sjyModel.getBm()+" y "+whereSql+" and y."+sjyModel.getZj()+"=x.djrid) ";
//		
//		StringBuffer sql=new StringBuffer();//主sql
//		sql.append("select b."+groupField+" xxmc,count(distinct a.djrid) zrs ");
//		for(XxglModel xx : xxList){
//			String xxid=xx.getXxid();
//			sql.append(",count(decode(a.xxid,'"+xxid+"',1)) "+xxid+"_rs ");
//			sql.append(",decode(count(1),0,0,round(count(decode(a.xxid,'"+xxid+"',1))/count(1)*100,2))||'%' "+xxid+"_rsbfb ");
//		}
//		sql.append(" from (select * from wjdc_wjhdb x where wjid='"+wjid+"' and xxid!='0' "+sjySqlExists+") a ");
//		sql.append(" left join (select * from "+sjyModel.getBm()+" "+whereSql+") b on a.djrid=b."+sjyModel.getZj());
//		sql.append(" group by rollup("+groupField+")");
//		
//		model.setSql(sql.toString());
//		return dao.getSqlMapList(model);
	}
	
	/**获取问卷选项试题列表*/
	public List<StglModel> getWjxxstxx(WjglModel model) throws Exception{
		return dao.getWjxxstxx(model);
	}
	
	/**
	 * 获取分组项，各各层次的个数，用于分组项在展现时合并单元格
	 * @return
	 */
	public int[][] getFzxGroupCount(WjtjModel model,WjpzSjylxModel sjyModel,String whereSql,int groupFieldCount) throws Exception{
		
		if(model.getGroupFields()==null||model.getGroupFields().length==0||model.getStidy()==null){
			return new int[0][];
		}
		String wjid=model.getWjid();
		String[] groupFields=model.getGroupFields();
		StringBuffer sql_gf1=new StringBuffer();
		StringBuffer sql_gf2=new StringBuffer();
		StringBuffer sql_groupFieldNullAs=new StringBuffer();//用于处理null的数据，不然合并单元格会出问题的
		for(int i=0;i<groupFields.length;i++){
			if(groupFields[i]!=null&&!"".equals(groupFields[i])){
				sql_gf1.append("b.");
				sql_gf1.append(groupFields[i]);
				sql_gf1.append(" xxmc");
				sql_gf1.append(i);
				sql_gf1.append(" ,");
				
				sql_gf2.append(groupFields[i]);
				sql_gf2.append(",");
				
				sql_groupFieldNullAs.append(", nvl(");
				sql_groupFieldNullAs.append(groupFields[i]);
				sql_groupFieldNullAs.append(",'"+groupFieldNullAs+"') ");
				sql_groupFieldNullAs.append(groupFields[i]);
			}
		}
		
		//处理数据源查询条件范围
		String sjySqlExists=" and exists ( select 1 from "+sjyModel.getBm()+" y "+whereSql+" and y."+sjyModel.getZj()+"=x.djrid) ";
		
		StringBuffer tableName_temp=new StringBuffer();//主sql
		tableName_temp.append("(");
		tableName_temp.append("select "+sql_gf1.toString()+" '1' zrs ");
		tableName_temp.append(" from (select * from wjdc_wjhdb x where wjid='"+wjid+"' and xxid!='0' "+sjySqlExists+") a ");
		tableName_temp.append(" left join (select "+sjyModel.getZj()+sql_groupFieldNullAs.toString()+" from "+sjyModel.getBm()+" "+whereSql+") b on a.djrid=b."+sjyModel.getZj());
		tableName_temp.append(" group by rollup("+sql_gf2.substring(0, sql_gf2.length()-1)+")");
		//sql.append(" order by "+sql_gf2.substring(0, sql_gf2.length()-1));
		tableName_temp.append(")");
		
		StringBuffer sql=new StringBuffer();
		StringBuffer sql_groupField=new StringBuffer();
		List<HashMap<String,Object>> rs_temp=null;
		int[][] group_count=new int[groupFieldCount][];
		int index_mark=0;
		for(int i=0;i<groupFields.length;i++){
			if(groupFields[i]!=null&&!"".equals(groupFields[i])){
				sql=new StringBuffer();
				sql_groupField.append("xxmc"+i);
				sql.append("select "+sql_groupField.toString()+",count(1) num from "+tableName_temp.toString()+" group by "+
						sql_groupField.toString()+" order by "+sql_groupField.toString());
				model.setSql(sql.toString());
				rs_temp=dao.getSqlMapList(model);
				int[] group_count_one=new int[rs_temp.size()];
				for(int j=0;j<rs_temp.size();j++){
					group_count_one[j]=((BigDecimal)rs_temp.get(j).get("NUM")).intValue();
				}
				group_count[index_mark]=group_count_one;
				sql_groupField.append(",");
				index_mark++;
			}
		}
		return group_count;
	}

	/**获取答卷详情列表*/
	public List<String[]> getDjxqList(String whereSql,
			List<WjpzModel> cxjgList, WjpzSjylxModel sjyModel,WjtjModel model) throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append("select rownum r,");
		sql.append(sjyModel.getZj()+" pkValue ");
		for (WjpzModel wjpzModel : cxjgList) {
			sql.append(",");
			sql.append(wjpzModel.getZd());
		}
		sql.append(" from ");
		sql.append(sjyModel.getBm());
		sql.append(" a inner join wjdc_djrffb b on b.zjz=a.");
		sql.append(sjyModel.getZj());
		sql.append(whereSql);
		sql.append(" and b.lxid='"+sjyModel.getLxid()+"' and b.wjid='"+model.getWjid()+"' ");
		
		model.setSql(sql.toString());
		List<HashMap<String,Object>> list = dao.getPagedSqlMapList(model);
		
		List<String[]> rs = new ArrayList<String[]>();
		if (cxjgList != null && !cxjgList.isEmpty()) {
			WjpzModel wjpzModel = new WjpzModel();
			wjpzModel.setZd("pkValue");
			cxjgList.add(0, wjpzModel);
			int strs_length=cxjgList.size();
			for (HashMap<String,Object> map : list) {
				String[] strs=new String[strs_length];
				for(int i=0;i<cxjgList.size();i++){
					strs[i]=(String)map.get(cxjgList.get(i).getZd().toUpperCase());
				}
				rs.add(strs);
			}
		}
		return rs;
	}
	
	/**获取交叉统计参数配置列表*/
	public List<HashMap<String,Object>> getJctjcspzList(WjtjModel model) throws Exception{
		return dao.getJctjcspzList(model);
	}
	
	/**获取交叉统计参数配置分组字段*/
	public String[] getJctjcspzOne(WjtjModel model) throws Exception{
		HashMap<String,Object> rs = dao.getJctjcspzOne(model);
		return rs.get("GROUPFIELD").toString().split(",");
	}

}
