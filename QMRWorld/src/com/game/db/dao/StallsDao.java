package com.game.db.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.game.db.BaseDao;
import com.game.db.bean.StallsBean;
import com.game.utils.LoggerProvider;
import com.game.utils.TimeUtil;

public class StallsDao extends BaseDao {

	private Logger log = Logger.getLogger(StallsDao.class);
	private SqlSessionFactory sqlMapper = getSqlMapper();

    public int insert(StallsBean stallsbean) throws SQLException {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.insert("stalls.insert", stallsbean);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("stalls.insert "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
    		return rows;
    	}catch (Exception e) {
    		log.error(e);
		}finally{
			session.close();
		}
    	return 0;
    }

    @SuppressWarnings("unchecked")
	public List<StallsBean> select() throws SQLException {
        SqlSession session = sqlMapper.openSession();
        try{
        	long currentTimeMillis = System.currentTimeMillis();
        	List<StallsBean> list = (List<StallsBean>)session.selectList("stalls.select");
        	LoggerProvider.getDbconsuminglog().info("stalls.select "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return list;
    	}catch (Exception e) {
    		log.error(e);
		}finally{
			session.close();
		}
    	return new ArrayList<StallsBean>();
    }

    
    /**读取单个玩家的摆摊信息
     * 
     * @return
     * @throws SQLException
     */
	public StallsBean selectsingle(Long roleid) throws SQLException {
        SqlSession session = sqlMapper.openSession();
        try{
        	long currentTimeMillis = System.currentTimeMillis();
        	StallsBean stallsBean = (StallsBean)session.selectOne("stalls.selectsingle",roleid);
        	LoggerProvider.getDbconsuminglog().info("stalls.selectsingle "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return stallsBean;
    	}catch (Exception e) {
			log.error(e);
		}finally{
			session.close();
		}
    	return null;
    }
    
    
    public int update(StallsBean stallsbean) throws SQLException {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.update("stalls.update", stallsbean);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("stalls.update "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return rows;
    	}catch (Exception e) {
			log.error(e);
		}finally{
			session.close();
		}
    	return 0;
    }
    
	public int delete(Long roleid) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			int rows = session.delete("stalls.delete", roleid);
			session.commit();
			LoggerProvider.getDbconsuminglog().info("stalls.delete "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return rows;
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}
		return 0;
	}
	
	
//	public static void main(String[] args) {
//		StallsDao stallsDao = new StallsDao();
//		
//		try {
//			System.err.println(stallsDao.selectsingle((long) 1).getStallsdata());
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}
//		
//	}
//	
	
	
	

}







