package com.zfsoft.wjdc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zfsoft.common.query.QueryModel;
import com.zfsoft.common.service.BaseServiceImpl;
import com.zfsoft.dao.page.PageList;
import com.zfsoft.util.base.StringUtil;
import com.zfsoft.wjdc.dao.daointerface.IStglDao;
import com.zfsoft.wjdc.dao.entites.StdlModel;
import com.zfsoft.wjdc.dao.entites.StglModel;
import com.zfsoft.wjdc.dao.entites.WjglModel;
import com.zfsoft.wjdc.dao.entites.XxglModel;
import com.zfsoft.wjdc.dao.entites.YhdjglModel;
import com.zfsoft.wjdc.service.svcinterface.IStglService;

public class StglServiceImpl extends BaseServiceImpl<StglModel, IStglDao> implements IStglService{
	
	private final String STLX_DX="DX";//单选
	private final String STLX_DXS="DXS";//多选
	private final String STLX_DXZH="DXZH";//单选组合
	private final String STLX_DXSZH="DXSZH";//多选组合
	private final String STLX_DHWB="DHWB";//单行文本
	private final String STLX_DHWBS="DHWBS";//多行文本
	
	private final String STLX_STDL="STDL";//特殊类型，试题大类，为了与前台进行对应
	
	
	/**
	 * 保存编辑的试题信息
	 * @throws Exception 
	 */
	@Override
	public boolean saveEditStxx(HttpServletRequest request,WjglModel model) throws Exception {
		String wjid=model.getWjid();
		
		String[] stids=request.getParameterValues("hidden_stid");//试题id
		String[] stmcs=request.getParameterValues("hidden_stmc");//试题名称
		String[] stlxs=request.getParameterValues("hidden_stlx");//试题类型
		String[] dhxxgss=request.getParameterValues("hidden_dhxxgs");//单行选项个数
		String[] sfbds=request.getParameterValues("hidden_sfbd");//是否必答
		String[] stzfs=request.getParameterValues("hidden_stzf");//试题总分
		String[] xxkzdxzs=request.getParameterValues("hidden_xxkzdxzs");//选项可最多选择数
		//String[] stdlids=request.getParameterValues("hidden_stdlid");//试题大类id
		
		if(stids==null||
           stmcs==null||
           stlxs==null||
           dhxxgss==null||
           sfbds==null||
           stzfs==null||
           xxkzdxzs==null||
           stids.length==0||
           stids.length!=stmcs.length||
           stids.length!=stlxs.length||
           stids.length!=dhxxgss.length||
           stids.length!=sfbds.length||
           stids.length!=stzfs.length||
           stids.length!=xxkzdxzs.length
		   ){//首先保证数据的是符合规范的
			return false;
		}
		
		ArrayList<StdlModel> stdlModels=new ArrayList<StdlModel>();//试题大类model
		ArrayList<StglModel> stglModels=new ArrayList<StglModel>();//试题model
		ArrayList<XxglModel> xxglModels=new ArrayList<XxglModel>();//选项model
		
		String curr_stdlid="";//当前的试题大类的id
		for(int i=0;i<stids.length;i++){
			if(STLX_STDL.equalsIgnoreCase(stlxs[i])){//如果是试题大类
				StdlModel stdl=new StdlModel();
				stdl.setWjid(wjid);
				stdl.setStdlid(stids[i]);
				stdl.setStdlmc(stmcs[i]);
				stdl.setXssx(i+"");
				
				stdlModels.add(stdl);
				curr_stdlid=stids[i];
			}else{//常规试题
				StglModel st=new StglModel();
				st.setWjid(wjid);
				st.setStid(stids[i]);
				st.setStmc(stmcs[i]);
				st.setStlx(stlxs[i]);
				st.setDhxxgs(dhxxgss[i]);
				st.setSfbd(sfbds[i]);
				st.setStzf(stzfs[i]);
				st.setXxkzdxzs(xxkzdxzs[i]);
				st.setXssx(i+"");
				st.setStdlid(curr_stdlid);
				
				stglModels.add(st);
				
				//根据试题的类型获取相应的选项信息
				if(STLX_DX.equalsIgnoreCase(stlxs[i])||
				   STLX_DXS.equalsIgnoreCase(stlxs[i])||
				   STLX_DXZH.equalsIgnoreCase(stlxs[i])||
				   STLX_DXSZH.equalsIgnoreCase(stlxs[i])){
					String stid=st.getStid();
					String[] xxids = request.getParameterValues("hidden_xxxx_xxid_"+stid);//选项id
				    //String[] xxbh = "";//选项编号
				    String[] xxmcs = request.getParameterValues("hidden_xxxx_xxmc_"+stid);//选项名称
				    String[] xxfzs = request.getParameterValues("hidden_xxxx_xxfz_"+stid);//选项分值
				    //String[] sfklr = "";//是否可录入
				    //String[] xssx = ""//显示顺序
				    if(xxids==null||xxmcs==null||xxfzs==null||
				       xxids.length==0||xxids.length!=xxmcs.length||xxids.length!=xxfzs.length){
				    	//添加错误信息
				    	System.out.println(st.getStmc());
				    	continue;
				    }else{
				    	for(int j=0;j<xxids.length;j++){
				    		XxglModel xx=new XxglModel();
				    		xx.setWjid(wjid);
				    		xx.setStid(stid);
				    		xx.setXxid(xxids[j]);
				    		xx.setXxmc(xxmcs[j]);
				    		xx.setXxfz(xxfzs[j]);
				    		xx.setXssx(j+"");
				    		
				    		xxglModels.add(xx);
				    	}
				    }
				}
			}
		}
		
		WjglModel wjModel=new WjglModel();
		wjModel.setWjid(wjid);
		
		int res=-1;
		//处理试题大类信息
		res=dao.deleteStdlxx(wjModel);
		if(res>-1&&stdlModels.size()>0){
			res=dao.insertStdlxx(stdlModels);
		}
		//处理试题信息
		res=dao.deleteStxx(wjModel);
		if(res>-1&&stglModels.size()>0){
			res=dao.insertStxx(stglModels);
		}
		//处理试题选项信息
		res=dao.deleteXxxx(wjModel);
		if(res>-1&&xxglModels.size()>0){
			res=dao.insertXxxx(xxglModels);
		}
		//更新问卷的自动增加试题编号字段
		if(res>-1){
			res=dao.updateWjxxAutoAddStbh(model);
		}
		return res>0?true:false;
	}
	
	/**
	 * 获取试题和试题大类排序后的列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<StglModel> getStxxAndStdlXxList(WjglModel model) throws Exception{
		return dao.getStxxAndStdlXxList(model);
	}
	
	/**
	 * 获取试题选项信息列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<XxglModel> getStXxxxList(WjglModel model) throws Exception{
		return dao.getStXxxxList(model);
	}

	/**
	 * 保存问卷答案
	 */
	public String saveWjDa(HttpServletRequest request,WjglModel model) throws Exception {
		StringBuffer errMsg=new StringBuffer();//错误信息
		List<StglModel> stxxList=dao.getStxxList(model);
		List<XxglModel> xxglModels=new ArrayList<XxglModel>();
		String stid;//试题id
		String stmc;//试题名称
		String stlx;//试题类型
		String sfbd;//是否必答
		int xxkzdxzs;//选项可最多选择数，用于多选题
		for (StglModel st : stxxList) {
			stid=st.getStid();
			stmc=st.getStmc();
			stlx=st.getStlx();
			sfbd=st.getSfbd();
			xxkzdxzs=(st.getXxkzdxzs()==null||"".equals(st.getXxkzdxzs()))?1000:Integer.parseInt(st.getXxkzdxzs());
			
			//根据试题的类型获取相应的选项信息
			if(STLX_DX.equalsIgnoreCase(stlx)||
			   STLX_DXS.equalsIgnoreCase(stlx)||
			   STLX_DXZH.equalsIgnoreCase(stlx)||
			   STLX_DXSZH.equalsIgnoreCase(stlx)){
				String[] xxids=request.getParameterValues("xxname_"+stid);
				xxids=(xxids==null?new String[0]:xxids);
				if(xxids.length==0&&"是".equals(sfbd)){
					errMsg.append("\""+stmc+"\"题目为必答题，必须选择！\n");
					continue;
				}
				if((STLX_DXS.equalsIgnoreCase(stlx)||
					STLX_DXSZH.equalsIgnoreCase(stlx))&&
					xxids.length>xxkzdxzs){
					errMsg.append("\""+stmc+"\"题目选项选择了"+xxids.length+"个，超过了设置的最多可选择"+xxkzdxzs+"个！\n");
				}
				for(int i=0;i<xxids.length;i++){
					XxglModel xx=new XxglModel();
					xx.setDjrid(model.getDjrid());
					xx.setWjid(model.getWjid());
					xx.setStid(stid);
					xx.setXxid(xxids[i]);
					xx.setTxnr("");
					xx.setPlsx("");
					xxglModels.add(xx);
				}
				
				//处理组合题的选项
				if(STLX_DXZH.equalsIgnoreCase(stlx)||
				   STLX_DXSZH.equalsIgnoreCase(stlx)){
					String text=request.getParameter("textname_"+stid);
					text=(text==null?"":text);
					if("".equals(text.trim())){
						//if("是".equals(sfbd)){
						//	errMsg.append("\""+stmc+"\"题目为必答题，必须填写！\n");
						//}
						continue;
					}
					XxglModel xx=new XxglModel();
					xx.setDjrid(model.getDjrid());
					xx.setWjid(model.getWjid());
					xx.setStid(stid);
					xx.setXxid("0");//文本题目，没有选项，默认置0
					xx.setTxnr(text);
					xx.setPlsx("");
					
					xxglModels.add(xx);
				}
			}else if(STLX_DHWB.equalsIgnoreCase(stlx)||//根据试题的类型获取相应的文本信息
			         STLX_DHWBS.equalsIgnoreCase(stlx)){
				String text=request.getParameter("textname_"+stid);
				text=(text==null?"":text);
				if("".equals(text.trim())){
					if("是".equals(sfbd)){
						errMsg.append("\""+stmc+"\"题目为必答题，必须填写！\n");
					}
					continue;
				}
				XxglModel xx=new XxglModel();
				xx.setDjrid(model.getDjrid());
				xx.setWjid(model.getWjid());
				xx.setStid(stid);
				xx.setXxid("0");//文本题目，没有选项，默认置0
				xx.setTxnr(text);
				xx.setPlsx("");
				xxglModels.add(xx);
			}
		}
		
		if(errMsg.length()>0){
			System.out.println("#######问卷答案保存验证信息"+model.getDjrid()+"："+errMsg.toString());
			return errMsg.toString();
		}
		
		int res=-1;
		res=dao.deleteWjDaxx(model);
		if(res>-1){
			res=dao.insertWjDaxx(xxglModels);
		}
		YhdjglModel yhdjglModel=new YhdjglModel();
		yhdjglModel.setWjid(model.getWjid());
		yhdjglModel.setDjrid(model.getDjrid());
		yhdjglModel.setDjzt("已答卷");
		if(res>0){
			res=dao.updateYhdjzt(yhdjglModel);
		}
		return res>0?"I99001" : "I99002";
	}
	
	/**
	 * 获取问卷答案信息列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<XxglModel> getWjDaList(WjglModel model) throws Exception{
		return dao.getWjDaList(model);
	}
	
	/**
	 * 获取试题类型列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getStlxList() throws Exception{
		return dao.getStlxList();
	}
	
	/**
	 * 获取用户答卷信息
	 * @param wjglModel
	 * @return
	 * @throws Exception
	 */
	public WjglModel getYhdjxx(WjglModel wjglModel) throws Exception{
		return dao.getYhdjxx(wjglModel);
	}
	
	/**
	 * 根据问卷id及试题id获取对应试题的答案结果（分页）
	 * @param stglModel
	 * @return
	 * @throws Exception
	 */
	public PageList<XxglModel> getTextPageListByStid(StglModel stglModel) throws Exception{
		PageList<XxglModel> pageList = new PageList<XxglModel>();
		if(stglModel!=null){
			QueryModel queryModel = stglModel.getQueryModel();
			queryModel.setTotalResult(dao.getTextPageCountByStid(stglModel));
			pageList.addAll(dao.getTextPageListByStid(stglModel));
			pageList.setPaginator(queryModel);
		}
		return pageList;
	}

}
