package com.game.globaldb;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 数据库服务
 */
public class GlobalDBServer {

	private Logger log = Logger.getLogger(GlobalDBServer.class);
	
	private static Object obj = new Object();
	//sql工厂
	private SqlSessionFactory sqlMapper;

	private static GlobalDBServer server;
	
	private GlobalDBServer(){
		try{
			String resource = "world-config/global-config.xml";
			InputStream in = new FileInputStream(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(in);
			in.close();
		}catch (IOException e) {
			log.error(e,e);
		}
	}
	
	public static GlobalDBServer getInstance(){
		synchronized (obj) {
			if(server == null) server = new GlobalDBServer();
		}
		return server;
	}

	public SqlSessionFactory getSqlMapper() {
		return sqlMapper;
	}

	public void setSqlMapper(SqlSessionFactory sqlMapper) {
		this.sqlMapper = sqlMapper;
	}

	

}
