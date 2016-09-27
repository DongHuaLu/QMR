package com.game.globaldb;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * BaseDao
 */
import org.apache.ibatis.session.SqlSessionFactory;

public class BaseDao {

	protected SqlSessionFactory getSqlMapper(){
		return GlobalDBServer.getInstance().getSqlMapper();
	}
}