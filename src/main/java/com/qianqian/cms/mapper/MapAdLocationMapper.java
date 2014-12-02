package com.qianqian.cms.mapper;

import com.qianqian.cms.model.MapAdLocationEntity;

public interface MapAdLocationMapper {
	/**
	 * 根据广告位编号将该位置的在线广告修改为下线状态
	 *  @Method_Name    : updateByLocationIdAndState
	 *  @param mapAd
	 *  @return 
	 *  @return         : int
	 *  @Creation Date  : 2014年5月22日 下午4:55:08
	 *  @version        : v1.00
	 *  @Author         : caozhifei 
	 *  @Update Date    : 
	 *  @Update Author  :
	 */
	int updateByLocationId(MapAdLocationEntity mapAd);
	/**
	 * 根据主键将广告展示状态修改为上线状态
	 *  @Method_Name    : updateByPrimaryKey
	 *  @param id
	 *  @return 
	 *  @return         : int
	 *  @Creation Date  : 2014年5月22日 下午4:56:32
	 *  @version        : v1.00
	 *  @Author         : caozhifei 
	 *  @Update Date    : 
	 *  @Update Author  :
	 */
	int updateByPrimaryKey(MapAdLocationEntity mapAd);
	
}
