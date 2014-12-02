package com.qianqian.cms.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import org.quartz.utils.ConnectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;


public class C3p0ConnectionProvider implements ConnectionProvider{

	private static Logger log = LoggerFactory.getLogger(C3p0ConnectionProvider.class);
	private static ComboPooledDataSource cpds=new ComboPooledDataSource();  
	private static Properties JDBC =null;
		
    static {  
        try {  
        	JDBC = PropertiesLoaderUtils.loadAllProperties("conf/jdbc.properties");
            // 驱动器  
            cpds.setDriverClass(JDBC.getProperty("jdbc.driverClassName"));  
            // 数据库url  
            cpds.setJdbcUrl(JDBC.getProperty("jdbc.main.url"));  
            //用户名  
            cpds.setUser(JDBC.getProperty("jdbc.main.username"));  
            //密码  
            cpds.setPassword(JDBC.getProperty("jdbc.main.password"));  
            //初始化连接池的大小  
            cpds.setInitialPoolSize(Integer.valueOf(JDBC.getProperty("jdbc.main.minPoolSize")));  
            //最小连接数  
            cpds.setMinPoolSize(Integer.valueOf(JDBC.getProperty("jdbc.main.minPoolSize")));  
            //最大连接数  
            cpds.setMaxPoolSize(Integer.valueOf(JDBC.getProperty("jdbc.main.maxPoolSize"))); 
            //空闲时间
            cpds.setMaxIdleTime(Integer.valueOf(JDBC.getProperty("jdbc.main.maxIdleTime")));
            log.debug("load c3p0 config success ");
        } catch (Exception e) {  
            log.error(" load c3p0 error "+e.getMessage(), e);
        }  
          
          
    }  
	public Connection getConnection() throws SQLException {
		return cpds.getConnection();
		
	}

	public void shutdown() throws SQLException {
		// TODO Auto-generated method stub
	}

}
