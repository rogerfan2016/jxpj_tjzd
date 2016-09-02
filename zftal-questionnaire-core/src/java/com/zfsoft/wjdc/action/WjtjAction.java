package com.zfsoft.wjdc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.zfsoft.common.action.BaseAction;
import com.zfsoft.common.service.BaseLog;
import com.zfsoft.service.impl.LogEngineImpl;
import com.zfsoft.util.base.StringUtil;
import com.zfsoft.wjdc.dao.entites.StglModel;
import com.zfsoft.wjdc.dao.entites.WjglModel;
import com.zfsoft.wjdc.dao.entites.WjpzModel;
import com.zfsoft.wjdc.dao.entites.WjpzSjylxModel;
import com.zfsoft.wjdc.dao.entites.WjtjModel;
import com.zfsoft.wjdc.dao.entites.XxglModel;
import com.zfsoft.wjdc.service.svcinterface.IWjBaseService;
import com.zfsoft.wjdc.service.svcinterface.IWjtjService;

public class WjtjAction extends BaseAction implements ModelDriven<WjtjModel> {
	
	private WjtjModel model=new WjtjModel();
	private IWjtjService service;
	private IWjBaseService iWjBaseService;
	private BaseLog baseLog = LogEngineImpl.getInstance();
	
	/**答卷统计*/
	public String djtj(){
		ValueStack vs = getValueStack();
		try {
			//问卷已分发数据源列表
			List<WjpzSjylxModel> lxbtList = service.getWjyffSjylxList(model);
			String lxid = StringUtil.isEmpty(model.getLxid()) ? (lxbtList != null && lxbtList.size() > 0 ? 
					lxbtList.get(0).getLxid() : "") : model.getLxid();
			model.setLxid(lxid);
			
			//获取查询条件，结果
			WjpzModel wjpzModel = new WjpzModel();
			wjpzModel.setLxid(lxid);
			wjpzModel.setGnlb(IWjBaseService.GNLB_WJDTQKTJ);
			List<WjpzModel> tjList = iWjBaseService.getGnlbCxtjPzList(wjpzModel);	
			List<WjpzModel> jgList = iWjBaseService.getGnlbCxjgPzList(wjpzModel);
			WjpzSjylxModel sjyModel = iWjBaseService.getWjpzSjylxModel(lxid);
			
			Map<String, String> cxtjMap = iWjBaseService.formatMap(getRequest().getParameterMap());
			
			//查询结果展现
			wjpzModel.setFields(model.getGroupFields());
			List<WjpzModel> titList = service.getFieldPzListByFields(wjpzModel);
			
			String whereSql=iWjBaseService.getWhereSql(cxtjMap);
			List<HashMap<String, Object>> rsList = service.getDjtjList(model, sjyModel,whereSql);
			vs.set("titList", titList);
			getRequest().setAttribute("groupFields", model.getGroupFields());
			getRequest().setAttribute("rsList", rsList);
			vs.set("sjyModel", sjyModel);
			vs.set("tjList", tjList);	
			vs.set("jgList", jgList);	
			
			vs.set("lxbt",lxid);
			vs.set("wjid", model.getWjid());
			//将MAP转换为str查询条件值
			vs.set("valueStr", iWjBaseService.formatMaptoStr(cxtjMap));
			vs.set("lxbtList", lxbtList);//表头类型列表
		} catch (Exception e) {
			logException(e);
		}
		return "djtj";
	}
	
	/**试题统计*/
	public String sttj(){
		ValueStack vs = getValueStack();
		try {
			//问卷已分发数据源列表
			model.setDjzt("已答卷");
			List<WjpzSjylxModel> lxbtList = service.getWjyffSjylxList(model);
			String lxid = StringUtil.isEmpty(model.getLxid()) ? (lxbtList != null && lxbtList.size() > 0 ? 
					lxbtList.get(0).getLxid() : "") : model.getLxid();
			model.setLxid(lxid);
			
			//获取查询条件，结果
			WjpzModel wjpzModel = new WjpzModel();
			wjpzModel.setLxid(lxid);
			wjpzModel.setGnlb(IWjBaseService.GNLB_WJSTTJ);
			List<WjpzModel> tjList = iWjBaseService.getGnlbCxtjPzList(wjpzModel);	
			WjpzSjylxModel sjyModel = iWjBaseService.getWjpzSjylxModel(lxid);
			
			Map<String, String> cxtjMap = iWjBaseService.formatMap(getRequest().getParameterMap());
			
			//查询结果展现
			wjpzModel.setFields(model.getGroupFields());
			
			String whereSql=iWjBaseService.getWhereSql(cxtjMap);
			List<HashMap<String,Object>> rsList=service.getWjxzstTjxx(model,sjyModel,whereSql);
			
			//获取问卷相关信息
			WjglModel wjglModel=new WjglModel();
			wjglModel.setWjid(model.getWjid());
			wjglModel=iWjBaseService.getWjglModel(wjglModel);
			vs.set("wjglModel", wjglModel);
			
			vs.set("rsList", rsList);
			vs.set("sjyModel", sjyModel);
			vs.set("tjList", tjList);	
			
			vs.set("lxbt",lxid);
			vs.set("wjid", model.getWjid());
			//将MAP转换为str查询条件值
			vs.set("valueStr", iWjBaseService.formatMaptoStr(cxtjMap));
			vs.set("lxbtList", lxbtList);//表头类型列表
		} catch (Exception e) {
			logException(e);
		}
		return "sttj";
	}
	
	/**交叉统计*/
	public String jctj(){
		ValueStack vs = getValueStack();
		try {
			//问卷已分发数据源列表
			model.setDjzt("已答卷");
			List<WjpzSjylxModel> lxbtList = service.getWjyffSjylxList(model);
			String lxid = StringUtil.isEmpty(model.getLxid()) ? (lxbtList != null && lxbtList.size() > 0 ? 
					lxbtList.get(0).getLxid() : "") : model.getLxid();
			model.setLxid(lxid);
			
			//获取查询条件，结果
			WjpzModel wjpzModel = new WjpzModel();
			wjpzModel.setLxid(lxid);
			wjpzModel.setGnlb(IWjBaseService.GNLB_WJSTJCTJ);
			List<WjpzModel> tjList = iWjBaseService.getGnlbCxtjPzList(wjpzModel);	
			List<WjpzModel> jgList = iWjBaseService.getGnlbCxjgPzList(wjpzModel);
			WjpzSjylxModel sjyModel = iWjBaseService.getWjpzSjylxModel(lxid);
			
			Map<String, String> cxtjMap = iWjBaseService.formatMap(getRequest().getParameterMap());
			
			//查询结果展现
			wjpzModel.setFields(model.getGroupFields());
			
			//获取统计试题选项列表
			StglModel stglModel=new StglModel();
			stglModel.setWjid(model.getWjid());
			stglModel.setStid(model.getStidy());
			List<XxglModel> xxList=iWjBaseService.getWjOneStXxList(stglModel);
			//获取数据源过滤条件
			String whereSql=iWjBaseService.getWhereSql(cxtjMap);
			//获取最后的结果集
			List<HashMap<String,Object>> rsList=service.getWjxzstJctjxx(model,sjyModel,whereSql,xxList,getRequest());
			
			//vs.set("rsList", rsList);
			vs.set("sjyModel", sjyModel);
			vs.set("tjList", tjList);
			vs.set("jgList", jgList);
			
			vs.set("lxbt",lxid);
			vs.set("wjid", model.getWjid());
			//将MAP转换为str查询条件值
			vs.set("valueStr", iWjBaseService.formatMaptoStr(cxtjMap));
			vs.set("lxbtList", lxbtList);//表头类型列表

			WjglModel wjglModel=new WjglModel();
			wjglModel.setWjid(model.getWjid());
			vs.set("wjstList", service.getWjxxstxx(wjglModel));
			
			vs.set("xxList", xxList);
			getRequest().setAttribute("xxList", xxList);
			getRequest().setAttribute("rsList", rsList);
		} catch (Exception e) {
			logException(e);
		}
		return "jctj";
	}
	
	/**交叉统计参数配置*/
	public String jctjcspz(){
		ValueStack vs = getValueStack();
		try {
			if(model.getTjtype()!=null&&!"".equals(model.getTjtype())){
				model.setGroupFields(service.getJctjcspzOne(model));
			}
			//问卷已分发数据源列表
			model.setDjzt("已答卷");
			List<WjpzSjylxModel> lxbtList = service.getWjyffSjylxList(model);
			String lxid = StringUtil.isEmpty(model.getLxid()) ? (lxbtList != null && lxbtList.size() > 0 ? 
					lxbtList.get(0).getLxid() : "") : model.getLxid();
			model.setLxid(lxid);
			
			//获取查询条件，结果
			WjpzModel wjpzModel = new WjpzModel();
			wjpzModel.setLxid(lxid);
			wjpzModel.setGnlb(IWjBaseService.GNLB_WJSTJCTJ);
			List<WjpzModel> tjList = iWjBaseService.getGnlbCxtjPzList(wjpzModel);	
//			List<WjpzModel> jgList = iWjBaseService.getGnlbCxjgPzList(wjpzModel);
			WjpzSjylxModel sjyModel = iWjBaseService.getWjpzSjylxModel(lxid);
			
			Map<String, String> cxtjMap = iWjBaseService.formatMap(getRequest().getParameterMap());
			
			//查询结果展现
			wjpzModel.setFields(model.getGroupFields());
			
			//获取统计试题选项列表
			StglModel stglModel=new StglModel();
			stglModel.setWjid(model.getWjid());
			stglModel.setStid(model.getStidy());
			List<XxglModel> xxList=iWjBaseService.getWjOneStXxList(stglModel);
			//获取数据源过滤条件
			String whereSql=iWjBaseService.getWhereSql(cxtjMap);
			//获取最后的结果集
			List<HashMap<String,Object>> rsList=service.getWjxzstJctjxx(model,sjyModel,whereSql,xxList,getRequest());
			
			//vs.set("rsList", rsList);
			vs.set("sjyModel", sjyModel);
			vs.set("tjList", tjList);
//			vs.set("jgList", jgList);
			vs.set("jgList", service.getJctjcspzList(model));
			
			vs.set("lxbt",lxid);
			vs.set("wjid", model.getWjid());
			//将MAP转换为str查询条件值
			vs.set("valueStr", iWjBaseService.formatMaptoStr(cxtjMap));
			vs.set("lxbtList", lxbtList);//表头类型列表

			WjglModel wjglModel=new WjglModel();
			wjglModel.setWjid(model.getWjid());
			vs.set("wjstList", service.getWjxxstxx(wjglModel));
			
			vs.set("xxList", xxList);
			getRequest().setAttribute("xxList", xxList);
			getRequest().setAttribute("rsList", rsList);
		} catch (Exception e) {
			logException(e);
		}
		return "jctjcspz";
	}
	
	public String djxq(){
		ValueStack vs = getValueStack();
		try {
			List<WjpzSjylxModel> lxbtList = iWjBaseService.getWjpzSjylxList();
			String lxid = StringUtil.isEmpty(model.getLxid()) ? (lxbtList != null && lxbtList.size() > 0 ? 
					lxbtList.get(0).getLxid() : "") : model.getLxid();
			model.setLxid(lxid);
			
			//删除问卷对象
//			plscYffwjdxxx(vs, user);
			
			//发布问卷对象标志
//			xgWjffdxbz(vs, user);
			
			//获取查询条件，结果
			WjpzModel wjpzModel = new WjpzModel();
			wjpzModel.setLxid(lxid);
			wjpzModel.setGnlb(IWjBaseService.GNLB_WJFF);
			List<WjpzModel> tjList = iWjBaseService.getGnlbCxtjPzList(wjpzModel);	
			List<WjpzModel> jgList = iWjBaseService.getGnlbCxjgPzList(wjpzModel);
			WjpzSjylxModel sjyModel = iWjBaseService.getWjpzSjylxModel(lxid);
			
			Map<String, String> cxtjMap = iWjBaseService.formatMap(getRequest().getParameterMap());
			//获取数据源过滤条件
			String whereSql=iWjBaseService.getWhereSql(cxtjMap);
			//查询结果展现
			List<HashMap<String, String>> titList = iWjBaseService.getWjdxcxbtList(jgList);
			setTjjg(jgList, titList);//特殊处理查询条件及结果
			List<String[]> rsList = service.getDjxqList(whereSql, jgList, sjyModel,model);
//			List<String[]> rs = service.getWjdxCxjgList(rsList, jgList, sjyModel.getZj());//格式化输出结果
			vs.set("titList", titList);
			vs.set("rsList", rsList);
			vs.set("sjyModel", sjyModel);
			WjpzModel pzModel = new WjpzModel();
			pzModel.setZd("djzt");
			pzModel.setZdmc("答卷状态");
			pzModel.setBqlx("select");
			tjList.add(pzModel);
			vs.set("tjList", tjList);		
			vs.set("lxbt",lxid);
			vs.set("wjid", model.getWjid());
			//将MAP转换为str查询条件值
			vs.set("valueStr", iWjBaseService.formatMaptoStr(cxtjMap));
			vs.set("lxbtList", lxbtList);//表头类型列表
		} catch (Exception e) {
			logException(e);
		}
		return "djxq";
	}
	
	private void setTjjg(List<WjpzModel> jgList,
			List<HashMap<String, String>> titList) {
		//单独增加对是否发放，显示控制的字段
		WjpzModel record = new WjpzModel();
//		record.setZd("djzt");
//		jgList.add(jgList.size()-1, record);
		record = new WjpzModel();
		record.setZd("djzt");
		jgList.add(record);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("dm", "djzt");
		map.put("mc", "答卷状态");
		titList.add(map);
		//end
	}
	
	

	@Override
	public WjtjModel getModel() {
		return model;
	}


	public IWjtjService getService() {
		return service;
	}


	public void setService(IWjtjService service) {
		this.service = service;
	}

	public IWjBaseService getiWjBaseService() {
		return iWjBaseService;
	}


	public void setiWjBaseService(IWjBaseService iWjBaseService) {
		this.iWjBaseService = iWjBaseService;
	}


	public void setModel(WjtjModel model) {
		this.model = model;
	}

}
