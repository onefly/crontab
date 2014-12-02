package com.qianqian.cms.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qianqian.cms.model.MapAdLocationEntity;
import com.qianqian.cms.service.IMapAdLocationService;
import com.qianqian.cms.util.Springfactory;
import com.qianqian.common.service.IStaticWebpageService;

/**
 * job 执行程序将新广告进行上线
 * 
 * @Project : cms.crontab
 * @Program Name: com.qianqian.cms.job.MapAdLocationOnlineJob.java
 * @ClassName : MapAdLocationJob
 * @Author : caozhifei
 * @CreateDate : 2014年5月22日 下午5:29:50
 */
public class MapAdLocationOnlineJob implements Job {
	private static Logger log = LoggerFactory
			.getLogger(MapAdLocationOnlineJob.class);
	/**
	 * 传入参数
	 */
	private MapAdLocationEntity mapAd;

	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			log.debug("广告上线 execute " + mapAd.toString());
			IMapAdLocationService mapAdLocationService = Springfactory
					.getBean("mapAdLocationService");
			mapAdLocationService.updateMapState(mapAd);
			// TODO 远程调用service生成静态页面
			IStaticWebpageService staticWebpageService = Springfactory
					.getBean("staticWebpageService");		
			if(mapAd.getLocationType() == 5 || mapAd.getLocationType() == 6){
				//右侧小轮播或6个标准广告位变动时所有裸价体验页面重新生成
				staticWebpageService.generateAllStaticWebpageByPageType(mapAd.getPageType());
				log.debug("包含右侧轮播和标准广告位的全部页面上线成功");
			}else{
				staticWebpageService.generateStaticWebpage(mapAd.getPageType(), mapAd.getCategory());
				log.debug("其他广告页面上线成功");
			}
		} catch (Exception e) {
			log.error("execete online error" + e.toString(), e);
//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e1) {
//				log.error("execete online error" + e1.toString(), e1);
//			}
			JobExecutionException e2 = new JobExecutionException(e);
			
			//e2.setRefireImmediately(true); // 异常之后立即重新执行
			throw e2;
		}

	}

	public MapAdLocationEntity getMapAd() {
		return mapAd;
	}

	public void setMapAd(MapAdLocationEntity mapAd) {
		this.mapAd = mapAd;
	}

}
