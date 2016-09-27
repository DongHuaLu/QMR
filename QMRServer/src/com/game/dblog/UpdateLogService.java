package com.game.dblog;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.game.utils.StringUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import db.util.ColumnInfo;
import db.util.DBUtils;
import db.util.TableCompar;

/**
 * 
 * @author 赵聪慧
 * @2012-10-29 下午11:57:59
 */
public class UpdateLogService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UpdateLogService.class);
	private static UpdateLogService instacne=new UpdateLogService();
	public static UpdateLogService getInstance(){
		return instacne;
	}
	private static ThreadGroup threadGroup=new ThreadGroup("ROLESTATESAVETHREAD");
	private static int threadnum=8;
	private List<UpdateThread> threadarray=new ArrayList<UpdateThread>();
	private ComboPooledDataSource ds;
	private boolean start=false;
	
	
	private UpdateLogService(){
		logger.info("初始化update日志数据库服务");
		try {
			String propesFile="server-config/logDBConfig.properties";
			File file = new File(propesFile);
			Properties prop=new Properties();
			prop.load(new FileInputStream(file));
			ds=new ComboPooledDataSource();
			ds.setDriverClass(prop.getProperty("logdbpool.drivers"));
			ds.setJdbcUrl(prop.getProperty("logdbpool.url"));
			ds.setPassword(prop.getProperty("logdbpool.password"));
			ds.setUser(prop.getProperty("logdbpool.user"));
			ds.setInitialPoolSize(10);
			ds.setAcquireIncrement(10);
			ds.setMinPoolSize(10);
			ds.setMaxPoolSize(30);
			ds.setMaxIdleTime(60000);
			ds.setCheckoutTimeout(2000);
			ds.setIdleConnectionTestPeriod(60*10);
			ds.setPreferredTestQuery(prop.getProperty("logdbpool.validationquery"));
			logger.info("初始化update日志数据库服务");
			logger.info("启动update日志线程池完毕");
		} catch (Exception ex){
			logger.error(ex,ex);
		}
		logger.info("初始update化日志数据库服务结束");
		checkTable();
		for (int i = 0; i < 8; i++) {
			UpdateThread thread=new UpdateThread(threadGroup,""+i,ds);
			threadarray.add(thread);
			thread.start();
		}
		start=true;
	}
	
	
	private void checkTable(){
		Connection connection = null;
		try {
			connection = ds.getConnection();
			List<String> tableName = DBUtils.getTableName(connection);
			if(!tableName.contains("rolestate")){
				logger.info("rolestate表不存在创建");
				try{
					String buildDDL = UpdateThread.buildDDL();
					Statement createStatement = connection.createStatement();
					createStatement.execute(buildDDL);
				}catch (Exception e) {
					logger.info("rolestate表创建失败");
				}
				logger.info("rolestate表创建完毕");
			}else{
				logger.info("rolestate表存在检查差异");
				List<ColumnInfo> columnDefine = DBUtils.getColumnDefine(connection,"rolestate");
				List<ColumnInfo> buildFields = UpdateThread.buildFields();
				try {
					List<String> compartor = TableCompar.getInstance().compartor("rolestate",buildFields, columnDefine);
					if(compartor.size()>0){
						Statement createStatement = connection.createStatement();
						for (String string : compartor) {
							createStatement.execute(string);
						}	
					}
					
					
					
//					String compartor = 
//					if(!StringUtil.isBlank(compartor)){
//							
//					}
				} catch (Exception e) {
					logger.error("表结构自动适配失败",e);
				}
			}
		} catch (SQLException e) {
			logger.error(e,e);
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
	
	public void updateRoleDate(long roleId){
		try{
			if(start){
				int tag=(int) (Math.abs(roleId)%threadnum);
				UpdateThread updateThread ;
				if(threadarray.size()<tag+1){
					updateThread=threadarray.get(0);
				}else{
					updateThread=threadarray.get(tag);
				}
				if(updateThread!=null&&updateThread.getQueue().size()<1000){
					updateThread.updateState(roleId);				
				}
			}	
		}catch (Exception e) {
			logger.error(e,e);
		}
		
	}
	
	
	public void updateRoleDateByStart(long roleId){
		if(start){
			int tag=(int) (roleId%threadnum);
			UpdateThread updateThread ;
			if(threadarray.size()<tag+1){
				updateThread=threadarray.get(0);
			}else{
				updateThread=threadarray.get(tag);
			}
			if(updateThread!=null){
				updateThread.updateState(roleId);				
			}
		}
	}
	
	public static void main(String agrs[]){
//		for(int i=0;i<100;i++){
//			System.out.println(i%8);
//		}
	}
	
}
