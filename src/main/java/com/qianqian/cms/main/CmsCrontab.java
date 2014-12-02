package com.qianqian.cms.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 启动cms系统定时任务服务
 * @Project 	: cms.crontab
 * @Program Name: com.qianqian.cms.main.CmsCrontab.java
 * @ClassName	: CmsCrontab 
 * @Author 		: caozhifei 
 * @CreateDate  : 2014年5月26日 下午3:46:22
 */
public class CmsCrontab {
	/**
	 * 启动CMS系统定时任务服务
	 *  @Method_Name    : main
	 *  @param args 
	 *  @return         : void
	 *  @Creation Date  : 2014年5月26日 下午4:45:08
	 *  @version        : v1.00
	 *  @Author         : caozhifei 
	 *  @Update Date    : 
	 *  @Update Author  :
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("/spring/applicationContext.xml").start();
	}
}
