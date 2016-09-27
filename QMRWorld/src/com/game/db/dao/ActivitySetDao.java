package com.game.db.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.game.db.BaseDao;
import com.game.db.bean.ActivitySetBean;
import com.game.utils.LoggerProvider;
import com.game.utils.TimeUtil;

public class ActivitySetDao extends BaseDao {

	private Logger log = Logger.getLogger(ActivitySetDao.class);
	private SqlSessionFactory sqlMapper = getSqlMapper();

	
	
    public int insert(ActivitySetBean activitySetBean) throws SQLException {
        SqlSession session = sqlMapper.openSession();
        
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.insert("activity_set.insert", activitySetBean);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("activity_set.insert "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
    		return rows;
    	}catch (Exception e) {
    		log.error(e);
		}finally{
			session.close();
		}
    	return 0;
    }

    @SuppressWarnings("unchecked")
	public List<ActivitySetBean> select() throws SQLException {
        SqlSession session = sqlMapper.openSession();
        try{
        	long currentTimeMillis = System.currentTimeMillis();
    		
        	List<ActivitySetBean> list = (List<ActivitySetBean>)session.selectList("activity_set.select");
        	LoggerProvider.getDbconsuminglog().info("activity_set.select "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
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
	public ActivitySetBean selectsingle(int index) throws SQLException {
        SqlSession session = sqlMapper.openSession();
        try{
        	long currentTimeMillis = System.currentTimeMillis();
        	ActivitySetBean activitySetBean = (ActivitySetBean)session.selectOne("activity_set.selectsingle",index);
        	LoggerProvider.getDbconsuminglog().info("activity_set.selectsingle "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return activitySetBean;
    	}catch (Exception e) {
			log.error(e);
		}finally{
			session.close();
		}
    	return null;
    }
    
    
    public int update(ActivitySetBean activitySetBean) throws SQLException {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.update("activity_set.update", activitySetBean);
    		LoggerProvider.getDbconsuminglog().info("activity_set.update "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
    		session.commit();
            return rows;
    	}catch (Exception e) {
			log.error(e);
		}finally{
			session.close();
		}
    	return 0;
    }
    

	

	
	

}







