package com.qianqian.cms.service;

import com.qianqian.cms.model.MapAdLocationEntity;

/**
 * 广告位与广告映射关系状态修改
 * @Project 	: cms.crontab
 * @Program Name: com.qianqian.cms.service.IMapAdLocation.java
 * @ClassName	: IMapAdLocation 
 * @Author 		: caozhifei 
 * @CreateDate  : 2014年5月22日 下午4:44:54
 */
public interface IMapAdLocationService{
	/**
	 * 修改广告位与广告映射的展示状态
	 *  @Method_Name    : updateMapState
	 *  @param mapAd 
	 *  @return         : void
	 *  @Creation Date  : 2014年5月22日 下午5:09:57
	 *  @version        : v1.00
	 *  @Author         : caozhifei 
	 *  @Update Date    : 
	 *  @Update Author  :
	 */
	void updateMapState(MapAdLocationEntity mapAd);
	/**
	 * 将到期的广告进行下线处理
	 *  @Method_Name    : updateStateOffline
	 *  @param mapAd 
	 *  @return         : void
	 *  @Creation Date  : 2014年5月22日 下午5:48:43
	 *  @version        : v1.00
	 *  @Author         : caozhifei 
	 *  @Update Date    : 
	 *  @Update Author  :
	 */
	void updateStateOffline(MapAdLocationEntity mapAd);
	
}
