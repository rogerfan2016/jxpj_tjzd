package com.zfsoft.wjdc.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ModelDriven;
import com.zfsoft.common.action.BaseAction;
import com.zfsoft.common.query.QueryModel;
import com.zfsoft.common.service.BaseLog;
import com.zfsoft.dao.page.PageList;
import com.zfsoft.service.impl.LogEngineImpl;
import com.zfsoft.util.base.BeanUtils;
import com.zfsoft.wjdc.dao.entites.StglModel;
import com.zfsoft.wjdc.dao.entites.WjglModel;
import com.zfsoft.wjdc.dao.entites.XxglModel;
import com.zfsoft.wjdc.service.svcinterface.IStglService;
import com.zfsoft.wjdc.service.svcinterface.IWjglService;

/**
 * 试题管理
 */
public class StglAction extends BaseAction implements ModelDriven<StglModel>{

	private static final long serialVersionUID = 1L;
	private IStglService service;
	private IWjglService wjglService;
	private StglModel model=new StglModel();
	private WjglModel wjModel=new WjglModel();
	private BaseLog baseLog = LogEngineImpl.getInstance();
	
	/**
	 * 编辑试题
	 * @return
	 */
	public String editStxx(){
		try {
			WjglModel wjm=wjglService.getModel(wjModel.getWjid());
			wjm.setDjrid(wjModel.getDjrid());//设置答卷人id
			BeanUtils.copyProperties(wjModel, wjm);
		} catch (Exception e) {
			logException(e);
		}
		return "editStxx";
	}
	
	/**
	 * 保存编辑的试题
	 * @return
	 */
	public String saveEditStxx(){
		HttpServletRequest request = getRequest();
		try {
			boolean result = service.saveEditStxx(request, wjModel);
			String key = result ? "I99001" : "I99002";
			getValueStack().set("result", getText(key));
			if (result) {
				baseLog.update(
						getUser(),
						getText("log.message.ywmc", new String[] { "问卷管理",
								"ZFXG_WJDC_WJSTB" }),
						"问卷信息管理",
						getText("log.message.czms", new String[] { "编辑试题",
								"问卷id", model.getWjid()}));
			}
		} catch (Exception e) {
			logException(e);
		}
		return editStxx();
	}
	
	/**
	 * 获取问卷试题相关信息
	 * @return
	 */
	public String getWjStxxList(){
		try {
			List<StglModel> stList=service.getStxxAndStdlXxList(wjModel);
			List<XxglModel> xxList=service.getStXxxxList(wjModel);
			HashMap<String,Object> rs=new HashMap<String, Object>();
			rs.put("tmxxs", stList);
			rs.put("xxxxs", xxList);
			getValueStack().set(DATA, rs);
			return DATA;
		} catch (Exception e) {
			logException(e);
		}
		return "";
	}
	
	/**
	 * 保存问卷答案
	 * @return
	 */
	public String saveWjDa(){	
		HttpServletRequest request = getRequest();
		try {
			wjModel.setDjrid(getUser().getYhm());
			//wjModel.setDjrid("admin");
			WjglModel wjglModel=service.getYhdjxx(wjModel);
			//如果是游客那么就自动生成一个djrid
			if(wjglModel!=null&&"已答卷".equals(wjglModel.getDjzt())){
				getValueStack().set("result", "该问卷已作答，不可重复提交！");
			}else{
				wjModel.setWjlx(wjglModel.getWjlx());
				String result = service.saveWjDa(request, wjModel);
				if("I99001".equals(result) || "I99002".equals(result)){
					if("I99001".equals(result)){
						baseLog.insert(
								getUser(),
								getText("log.message.ywmc", new String[] { "问卷管理",
										"ZFXG_WJDC_WJHDB" }),
								"我的问卷",
								getText("log.message.czms", new String[] { "回答问卷",
										"问卷id", wjModel.getWjid() }));
					}
					result = getText(result);
				}
				getValueStack().set("result",result );
			}
		} catch (Exception e) {
			logException(e);
		}
		return yhdj();
	}
	
	public String success(){
		wjModel=wjglService.getModel(wjModel.getWjid());
		return "success";
	}
	/**
	 * 获取问卷答案列表
	 * @return
	 */
	public String getWjDaList(){
		try {
			List<XxglModel> wjdaList=service.getWjDaList(wjModel);
			getValueStack().set(DATA, wjdaList);
			return DATA;
		} catch (Exception e) {
			logException(e);
		}
		return "";
	}
	
	/**
	 * 显示试题_预览
	 * @return
	 */
	public String showStxx(){
		try {
			WjglModel wjm=wjglService.getModel(wjModel.getWjid());
			if(wjModel.getDjrid()==null||"".equals(wjModel.getDjrid())){
				wjm.setDjrid(getUser().getYhm());//设置答卷人id
			}
			BeanUtils.copyProperties(wjModel, wjm);
		} catch (Exception e) {
			logException(e);
		}
		return "showStxx";
	}
	
	/**
	 * 用户答卷
	 * @return
	 */
	public String yhdj(){
		try {
			WjglModel wjm=wjglService.getModel(wjModel.getWjid());
			wjm.setDjrid(wjModel.getDjrid());
			if(wjm.getDjrid()==null||"".equals(wjm.getDjrid())){
				wjm.setDjrid(getUser().getYhm());//设置答卷人id
				//wjm.setDjrid("admin");
			}
			
			wjModel.setDjrid(wjm.getDjrid());
			WjglModel yhdjxx=service.getYhdjxx(wjModel);
			wjm.setDjzt(yhdjxx.getDjzt());
			
			BeanUtils.copyProperties(wjModel, wjm);
		} catch (Exception e) {
			logException(e);
		}		
		return "showStxx";
	}

	/**
	 * 文本列表
	 * @return
	 */
	public String textList(){
		try {
			wjModel=wjglService.getModel(model.getWjid());
			QueryModel qm = model.getQueryModel();
			model=service.getModel(model);
			model.setQueryModel(qm);
			PageList<XxglModel> list = service.getTextPageListByStid(model);
			getValueStack().set("pageList", list);
		} catch (Exception e) {
			logException(e);
		}		
		return "textList";
	}
	
	public IStglService getService() {
		return service;
	}

	public void setService(IStglService service) {
		this.service = service;
	}

	@Override
	public StglModel getModel() {
		return model;
	}

	public WjglModel getWjModel() {
		return wjModel;
	}

	public void setWjModel(WjglModel wjModel) {
		this.wjModel = wjModel;
	}

	public IWjglService getWjglService() {
		return wjglService;
	}

	public void setWjglService(IWjglService wjglService) {
		this.wjglService = wjglService;
	}

	
}
