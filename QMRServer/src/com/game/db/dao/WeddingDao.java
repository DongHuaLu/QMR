package com.game.db.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.game.db.DBServer;
import com.game.db.bean.WeddingBean;
import com.game.utils.LoggerProvider;
import com.game.utils.TimeUtil;

public class WeddingDao {

	private Logger log = Logger.getLogger(WeddingDao.class);
	private SqlSessionFactory sqlMapper = DBServer.getInstance().getSqlMapper();

    public int insert(WeddingBean spbean) throws SQLException {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.insert("wedding.insert", spbean);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("wedding.insert "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
    		return rows;
    	}catch (Exception e) {
    		log.error(e);
		}finally{
			session.close();
		}
    	return 0;
    }

    @SuppressWarnings("unchecked")
	public List<WeddingBean> select() throws SQLException {
        SqlSession session = sqlMapper.openSession();
        try{
        	long currentTimeMillis = System.currentTimeMillis();
        	List<WeddingBean> list = (List<WeddingBean>)session.selectList("wedding.select");
        	LoggerProvider.getDbconsuminglog().info("wedding.select "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return list;
    	}catch (Exception e) {
    		log.error(e);
		}finally{
			session.close();
		}
    	return new ArrayList<WeddingBean>();
    }

    
    /**读取单个玩家的摆摊信息
     * 
     * @return
     * @throws SQLException
     */
	public WeddingBean selectsingle(Long id)  {
        SqlSession session = sqlMapper.openSession();
        try{
        	long currentTimeMillis = System.currentTimeMillis();
        	WeddingBean spirittreeBean = (WeddingBean)session.selectOne("wedding.selectsingle",id);
        	LoggerProvider.getDbconsuminglog().info("wedding.selectsingle "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return spirittreeBean;
		}finally{
			session.close();
		}
    }
    
    
    public int update(WeddingBean selectsingle) throws SQLException {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.update("wedding.update", selectsingle);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("wedding.update "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return rows;
    	}catch (Exception e) {
			log.error(e);
		}finally{
			session.close();
		}
    	return 0;
    }
    
	public int delete(Long id) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			int rows = session.delete("wedding.delete", id);
			session.commit();
			LoggerProvider.getDbconsuminglog().info("wedding.delete "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return rows;
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}
		return 0;
	}
	
	

	
}







