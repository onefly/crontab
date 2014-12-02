package com.qianqian.cms.service.impl;


import org.springframework.beans.factory.annotation.Autowired;

import com.qianqian.cms.mapper.MapAdLocationMapper;
import com.qianqian.cms.model.MapAdLocationEntity;
import com.qianqian.cms.service.IMapAdLocationService;

/**
 * 广告位与广告映射关系状态修改
 * @Project 	: cms.crontab
 * @Program Name: com.qianqian.cms.service.impl.MapAdLocationImpl.java
 * @ClassName	: MapAdLocationImpl 
 * @Author 		: caozhifei 
 * @CreateDate  : 2014年5月22日 下午4:50:26
 */
public class MapAdLocationServiceImpl implements IMapAdLocationService{
	@Autowired
	private MapAdLocationMapper mapAdLocationMapper;
	
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
	@Override
	public void updateMapState(MapAdLocationEntity mapAd) {
		mapAdLocationMapper.updateByLocationId(mapAd);//首先将广告位上的广告标为下线状态
		mapAdLocationMapper.updateByPrimaryKey(mapAd);//将新广告状态标为上线状态
	}
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
	@Override
	public void updateStateOffline(MapAdLocationEntity mapAd) {
		mapAdLocationMapper.updateByLocationId(mapAd);//将广告位上的广告标为下线状态		
	}

}
