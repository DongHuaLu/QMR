package com.game.db.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.game.db.DBServer;
import com.game.db.bean.MarriageBean;
import com.game.utils.LoggerProvider;
import com.game.utils.TimeUtil;

public class MarriageDao {

	private Logger log = Logger.getLogger(MarriageDao.class);
	private SqlSessionFactory sqlMapper = DBServer.getInstance().getSqlMapper();

    public int insert(MarriageBean spbean) throws SQLException {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.insert("marriage.insert", spbean);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("marriage.insert "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
    		return rows;
    	}catch (Exception e) {
    		log.error(e);
		}finally{
			session.close();
		}
    	return 0;
    }

    @SuppressWarnings("unchecked")
	public List<MarriageBean> select() throws SQLException {
        SqlSession session = sqlMapper.openSession();
        try{
        	long currentTimeMillis = System.currentTimeMillis();
        	List<MarriageBean> list = (List<MarriageBean>)session.selectList("marriage.select");
        	LoggerProvider.getDbconsuminglog().info("marriage.select "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return list;
    	}catch (Exception e) {
    		log.error(e);
		}finally{
			session.close();
		}
    	return new ArrayList<MarriageBean>();
    }

    
    /**读取单个玩家的摆摊信息
     * 
     * @return
     * @throws SQLException
     */
	public MarriageBean selectsingle(Long id)  {
        SqlSession session = sqlMapper.openSession();
        try{
        	long currentTimeMillis = System.currentTimeMillis();
        	MarriageBean spirittreeBean = (MarriageBean)session.selectOne("marriage.selectsingle",id);
        	LoggerProvider.getDbconsuminglog().info("marriage.selectsingle "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return spirittreeBean;
		}finally{
			session.close();
		}
    }
    
    
    public int update(MarriageBean selectsingle) throws SQLException {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.update("marriage.update", selectsingle);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("marriage.update "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
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
			int rows = session.delete("marriage.delete", id);
			session.commit();
			LoggerProvider.getDbconsuminglog().info("marriage.delete "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return rows;
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}
		return 0;
	}
	
	

	
}







