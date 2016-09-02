package com.zfsoft.wjdc.service.svcinterface;

import java.util.List;
import java.util.Map;

import com.zfsoft.wjdc.dao.entites.WjglModel;

/**
 * 
 * @author ChenMinming
 * @date 2015-2-3
 * @version V1.0.0
 */
public interface IQuestionNaireService {
	/**
	 * 获取调查问卷对象
	 * @param queryModel
	 * @return
	 */
	List<WjglModel> getPagedList(WjglModel query);
	/**
	 * 问卷分发
	 * 以指定的问卷为模板进行复制生成一份新问卷
	 * 并进行分发操作
	 * @param modelId 问卷Id
	 * @param targetId 生成的新问卷ID（必填、若新问卷的id已经存在则不重复生成）
	 * @param targetDesc 新问卷名称
	 * @param memberId 答题成员（必填）
	 */
	public boolean doDistribute(String modelId, String targetId,
			String targetDesc, String[] memberId, String lxid,String jsy,String jwy) throws Exception;
	
	/**
	 * 获取关于问卷的相关统计数据
	 * 返回对象为Map
	 * ALL_NUM  分发总人数
	 * ANSWER_NUM 答卷人数
	 */
	Map<String,Object> getModelSummer(String modelId);

	/**
	 * 取得问卷
	 * @param wjid
	 * @param memberId
	 * @return
	 * @throws Exception 
	 */
	public WjglModel getYhdjxx(String wjid, String memberId) throws Exception;
}
