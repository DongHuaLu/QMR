package com.game.db.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * System_grant Dao
 */
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.game.db.DBServer;
import com.game.db.bean.System_grantBean;
import com.game.utils.LoggerProvider;
import com.game.utils.TimeUtil;

public class System_grantDao {

	private SqlSessionFactory sqlMapper = DBServer.getInstance().getSqlMapper();
	private Logger log = Logger.getLogger(System_grantDao.class);
	public List<System_grantBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	@SuppressWarnings("unchecked")
			List<System_grantBean> list = (List<System_grantBean>)session.selectList("system_grant.select");
            return list;
    	}finally{
			session.close();
		}
    }
	
	
	
    public int insert(System_grantBean system_grantBean) throws SQLException {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.insert("system_grant.insert", system_grantBean);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("system_grant.insert "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
    		return rows;
    	}catch (Exception e) {
    		log.error(e);
		}finally{
			session.close();
		}
    	return 0;
    }

    public int update(System_grantBean system_grantBean) throws SQLException {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.update("system_grant.update", system_grantBean);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("system_grant.update "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return rows;
    	}catch (Exception e) {
			log.error(e);
		}finally{
			session.close();
		}
    	return 0;
    }
    
    
	public int delete(Integer id) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			int rows = session.delete("system_grant.delete", id);
			session.commit();
			LoggerProvider.getDbconsuminglog().info("system_grant.delete "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return rows;
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}
		return 0;
	}
	
}