package com.game.data;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * @version 创建时间：2010-11-8
 * 数据库服务器
 */
public class DataServer {

	
	private static Object obj = new Object();
	//服务器
	private static DataServer server;
	//sql工厂
	private SqlSessionFactory sqlMapper;
	
	private DataServer(){
		try{
			String resource = "world-config/data-config.xml";
			InputStream in = new FileInputStream(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(in);
			in.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static DataServer getInstance(){
		synchronized (obj) {
			if(server == null) server = new DataServer();
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
