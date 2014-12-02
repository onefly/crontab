package com.qianqian.cms.service.impl;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qianqian.cms.job.MapAdLocationOfflineJob;
import com.qianqian.cms.job.MapAdLocationOnlineJob;
import com.qianqian.cms.model.MapAdLocationEntity;
import com.qianqian.cms.service.IMapAdTaskService;

/**
 * 对外提供的创建任务接口实现
 * 
 * @Project : cms.crontab
 * @Program Name: com.qianqian.cms.open.impl.MapAdTaskServiceImpl.java
 * @ClassName : MapAdTaskServiceImpl
 * @Author : caozhifei
 * @CreateDate : 2014年5月22日 下午6:01:29
 */
@Service("mapAdTaskService")
public class MapAdTaskServiceImpl implements IMapAdTaskService {
	private static Logger log = LoggerFactory
			.getLogger(MapAdTaskServiceImpl.class);
	@Autowired
	private Scheduler mapAdScheduler;

	/**
	 * 创建定时任务触发器和job
	 * 
	 * @Method_Name : createMapAdTask
	 * @param mapAd
	 * @return
	 * 
	 * @Creation Date : 2014年5月22日 下午6:01:45
	 * @version : v1.00
	 * @throws SchedulerException
	 * @Author : caozhifei
	 * @Update Date :
	 * @Update Author :
	 */
	@Override
	public void createMapAdTask(MapAdLocationEntity mapAd)
			throws SchedulerException {
		createOnlineJob(mapAd);
		//广告下线时间为空则不创建广告下线的定时任务
		if (mapAd.getEndTime() != null) {
			createOfflineJob(mapAd);
		}
	}

	/**
	 * 删除指定的定时任务
	 * 
	 * @Method_Name : deleteMapAdTask
	 * @param mapAd
	 * @throws SchedulerException
	 * @return : void
	 * @Creation Date : 2014年5月27日 下午12:00:39
	 * @version : v1.00
	 * @Author : caozhifei
	 * @Update Date :
	 * @Update Author :
	 */
	@Override
	public void deleteMapAdTask(MapAdLocationEntity mapAd)
			throws SchedulerException {
		deleteOnlineJob(mapAd);
		deleteOfflineJob(mapAd);
	}

	/**
	 * 创建上线操作的触发器job
	 * 
	 * @Method_Name : createOnlineJob
	 * @param mapAd
	 * @throws SchedulerException
	 * @return : void
	 * @Creation Date : 2014年5月22日 下午5:56:01
	 * @version : v1.00
	 * @Author : caozhifei
	 * @Update Date :
	 * @Update Author :
	 */
	private void createOnlineJob(MapAdLocationEntity mapAd)
			throws SchedulerException {
		String name = mapAd.getId() + "";
		boolean jobDetailIsExist = mapAdScheduler.checkExists(JobKey.jobKey(
				"jobDetail-on-" + name, "jobDetailGroup-on-" + name));
		if (jobDetailIsExist) {
			mapAdScheduler.deleteJob(JobKey.jobKey("jobDetail-on-" + name,
					"jobDetailGroup-on-" + name));
			log.debug("jobDetail online is already exist, so delete success !"
					+ name);
		}
		// 创建新的job
		JobDetail jobDetail = newJob(MapAdLocationOnlineJob.class)
				.withIdentity("jobDetail-on-" + name,
						"jobDetailGroup-on-" + name).build();
		jobDetail.getJobDataMap().put("mapAd", mapAd);
		boolean cronTriggerIsExist = mapAdScheduler.checkExists(TriggerKey
				.triggerKey("simpleTrigger-on-" + name, "triggerGroup-on-"
						+ name));
		if (cronTriggerIsExist) {
			mapAdScheduler.unscheduleJob(TriggerKey.triggerKey(
					"simpleTrigger-on-" + name, "triggerGroup-on-" + name));
			log.debug("trigger online is already exist, so delete trigger success !"
					+ name);
		}
		// new一个新的触发器
		Trigger cronTrigger = newTrigger()
				.withIdentity("simpleTrigger-on-" + name,
						"triggerGroup-on-" + name)
				.startAt(mapAd.getStartTime()).build();

		// 作业和触发器设置到调度器中
		mapAdScheduler.scheduleJob(jobDetail, cronTrigger);

	}

	/**
	 * 删除指定job
	 * 
	 * @Method_Name : deleteOnlineJob
	 * @param mapAd
	 * @throws SchedulerException
	 * @return : void
	 * @Creation Date : 2014年5月27日 上午11:58:47
	 * @version : v1.00
	 * @Author : caozhifei
	 * @Update Date :
	 * @Update Author :
	 */
	private void deleteOnlineJob(MapAdLocationEntity mapAd)
			throws SchedulerException {
		String name = mapAd.getId() + "";
		boolean jobDetailIsExist = mapAdScheduler.checkExists(JobKey.jobKey(
				"jobDetail-on-" + name, "jobDetailGroup-on-" + name));
		if (jobDetailIsExist) {
			mapAdScheduler.deleteJob(JobKey.jobKey("jobDetail-on-" + name,
					"jobDetailGroup-on-" + name));
			log.debug(" delete jobDetail success !" + name);
		}
		boolean cronTriggerIsExist = mapAdScheduler.checkExists(TriggerKey
				.triggerKey("simpleTrigger-on-" + name, "triggerGroup-on-"
						+ name));
		if (cronTriggerIsExist) {
			mapAdScheduler.unscheduleJob(TriggerKey.triggerKey(
					"simpleTrigger-on-" + name, "triggerGroup-on-" + name));
			log.debug(" delete trigger success !" + name);
		}
	}

	/**
	 * 创建上线操作的触发器job
	 * 
	 * @Method_Name : createOnlineJob
	 * @param mapAd
	 * @throws SchedulerException
	 * @return : void
	 * @Creation Date : 2014年5月22日 下午5:56:01
	 * @version : v1.00
	 * @Author : caozhifei
	 * @Update Date :
	 * @Update Author :
	 */
	private void createOfflineJob(MapAdLocationEntity mapAd)
			throws SchedulerException {
		String name = mapAd.getId() + "";
		boolean jobDetailIsExist = mapAdScheduler.checkExists(JobKey.jobKey(
				"jobDetail-off-" + name, "jobDetailGroup-off-" + name));
		if (jobDetailIsExist) {
			mapAdScheduler.deleteJob(JobKey.jobKey("jobDetail-off-" + name,
					"jobDetailGroup-off-" + name));
			log.debug("jobDetail offline is already exist, so delete success !"
					+ name);
		}
		// 创建新的job
		JobDetail jobDetail = newJob(MapAdLocationOfflineJob.class)
				.withIdentity("jobDetail-off-" + name,
						"jobDetailGroup-off-" + name).build();
		jobDetail.getJobDataMap().put("mapAd", mapAd);
		boolean cronTriggerIsExist = mapAdScheduler.checkExists(TriggerKey
				.triggerKey("simpleTrigger-off-" + name, "triggerGroup-off-"
						+ name));
		if (cronTriggerIsExist) {
			mapAdScheduler.unscheduleJob(TriggerKey.triggerKey(
					"simpleTrigger-off-" + name, "triggerGroup-off-" + name));
			log.debug("trigger offline is already exist, so delete trigger success !"
					+ name);
		}
		// new一个新的触发器
		Trigger cronTrigger = newTrigger()
				.withIdentity("simpleTrigger-off-" + name,
						"triggerGroup-off-" + name).startAt(mapAd.getEndTime())
				.build();

		// 作业和触发器设置到调度器中
		mapAdScheduler.scheduleJob(jobDetail, cronTrigger);

	}

	/**
	 * 删除指定job
	 * 
	 * @Method_Name : deleteOnlineJob
	 * @param mapAd
	 * @throws SchedulerException
	 * @return : void
	 * @Creation Date : 2014年5月27日 上午11:58:47
	 * @version : v1.00
	 * @Author : caozhifei
	 * @Update Date :
	 * @Update Author :
	 */
	private void deleteOfflineJob(MapAdLocationEntity mapAd)
			throws SchedulerException {
		String name = mapAd.getId() + "";
		boolean jobDetailIsExist = mapAdScheduler.checkExists(JobKey.jobKey(
				"jobDetail-off-" + name, "jobDetailGroup-off-" + name));
		if (jobDetailIsExist) {
			mapAdScheduler.deleteJob(JobKey.jobKey("jobDetail-off-" + name,
					"jobDetailGroup-off-" + name));
			log.debug(" delete jobDetail success !" + name);
		}
		boolean cronTriggerIsExist = mapAdScheduler.checkExists(TriggerKey
				.triggerKey("simpleTrigger-off-" + name, "triggerGroup-off-"
						+ name));
		if (cronTriggerIsExist) {
			mapAdScheduler.unscheduleJob(TriggerKey.triggerKey(
					"simpleTrigger-off-" + name, "triggerGroup-off-" + name));
			log.debug(" delete trigger success !" + name);
		}
	}
}
