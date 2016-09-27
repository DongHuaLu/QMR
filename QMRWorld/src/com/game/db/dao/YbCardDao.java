package com.game.db.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.game.db.BaseDao;
import com.game.db.bean.YbCardBean;
import com.game.utils.LoggerProvider;
import com.game.utils.TimeUtil;

public class YbCardDao extends BaseDao {

	private Logger log = Logger.getLogger(YbCardDao.class);
	private SqlSessionFactory sqlMapper = getSqlMapper();

    public int insert(YbCardBean ybcardbean) throws SQLException {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.insert("ybcard.insert", ybcardbean);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("ybcard.insert "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
    		return rows;
    	}catch (Exception e) {
    		log.error(e);
		}finally{
			session.close();
		}
    	return 0;
    }

    @SuppressWarnings("unchecked")
	public List<YbCardBean> select() throws SQLException {
        SqlSession session = sqlMapper.openSession();
        try{
        	long currentTimeMillis = System.currentTimeMillis();
        	List<YbCardBean> list = (List<YbCardBean>)session.selectList("ybcard.select");
        	LoggerProvider.getDbconsuminglog().info("ybcard.select "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return list;
    	}catch (Exception e) {
    		log.error(e);
		}finally{
			session.close();
		}
    	return null;
    }

    
    /**读取单个玩家的摆摊信息
     * 
     * @return
     * @throws SQLException
     */
	public YbCardBean selectsingle(String username) throws SQLException {
        SqlSession session = sqlMapper.openSession();
        try{
        	long currentTimeMillis = System.currentTimeMillis();
        	YbCardBean ybcard = (YbCardBean)session.selectOne("ybcard.selectsingle",username);
        	LoggerProvider.getDbconsuminglog().info("ybcard.selectsingle "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return ybcard;
    	}catch (Exception e) {
			log.error(e);
		}finally{
			session.close();
		}
    	return null;
    }
    
    
    public int update(YbCardBean ybcardbean) throws SQLException {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.update("ybcard.update", ybcardbean);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("ybcard.update "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return rows;
    	}catch (Exception e) {
			log.error(e);
		}finally{
			session.close();
		}
    	return 0;
    }
    
	public int delete(String username) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			int rows = session.delete("ybcard.delete", username);
			session.commit();
			LoggerProvider.getDbconsuminglog().info("ybcard.delete "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return rows;
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}
		return 0;
	}
	

	
	

}







