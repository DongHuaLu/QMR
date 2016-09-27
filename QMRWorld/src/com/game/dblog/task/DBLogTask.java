package com.game.dblog.task;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.game.dblog.bean.BaseLogBean;

/**
 * 
 * @author 赵聪慧
 * @2012-8-20 下午10:25:43
 */
public class DBLogTask implements Runnable {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DBLogTask.class);
	public final static AtomicInteger count=new AtomicInteger();
	private DataSource ds;
	private BaseLogBean bean;
	public DBLogTask(BaseLogBean bean,DataSource ds){
		this.ds=ds;
		this.bean=bean;
	}
	@Override
	public void run() {
		String buildSql="";
		String buildCreateTableSql="";
		Connection connection=null;
		try {
			buildCreateTableSql = bean.buildCreateTableSql(bean.getTime());
			buildSql = bean.buildSql();
			connection = ds.getConnection();
			Statement createStatement = connection.createStatement();
			createStatement.execute(buildCreateTableSql);
			createStatement.executeUpdate(buildSql);
			
			if(logger.isDebugEnabled()){
				logger.debug(buildCreateTableSql);
				logger.debug(buildSql);	
			}
			count.getAndIncrement();
		} catch (SQLException e) {
			logger.error(e+":"+buildCreateTableSql+"\n"+buildSql);
		}finally{
			if(connection!=null){
				try {
					connection.close();
				} catch (SQLException e) {
					logger.error(e,e);
				}
			}
		}
	}
	public BaseLogBean getBean() {
		return bean;
	}
	public void setBean(BaseLogBean bean) {
		this.bean = bean;
	}
	
}
