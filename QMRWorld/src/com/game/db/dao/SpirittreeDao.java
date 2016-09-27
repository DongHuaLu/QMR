package com.game.db.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.game.db.BaseDao;
import com.game.db.bean.SpirittreeBean;
import com.game.utils.LoggerProvider;
import com.game.utils.TimeUtil;

public class SpirittreeDao extends BaseDao {

	private Logger log = Logger.getLogger(SpirittreeDao.class);
	private SqlSessionFactory sqlMapper = getSqlMapper();

    public int insert(SpirittreeBean spbean) throws SQLException {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.insert("spirittree.insert", spbean);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("spirittree.insert "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
    		return rows;
    	}catch (Exception e) {
    		log.error(e);
		}finally{
			session.close();
		}
    	return 0;
    }

    @SuppressWarnings("unchecked")
	public List<SpirittreeBean> select() throws SQLException {
        SqlSession session = sqlMapper.openSession();
        try{
        	long currentTimeMillis = System.currentTimeMillis();
        	List<SpirittreeBean> list = (List<SpirittreeBean>)session.selectList("spirittree.select");
        	LoggerProvider.getDbconsuminglog().info("spirittree.select "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return list;
    	}catch (Exception e) {
    		log.error(e);
		}finally{
			session.close();
		}
    	return new ArrayList<SpirittreeBean>();
    }

    
    /**读取单个玩家的摆摊信息
     * 
     * @return
     * @throws SQLException
     */
	public SpirittreeBean selectsingle(Long roleid)  {
        SqlSession session = sqlMapper.openSession();
        try{
        	long currentTimeMillis = System.currentTimeMillis();
        	SpirittreeBean spirittreeBean = (SpirittreeBean)session.selectOne("spirittree.selectsingle",roleid);
        	LoggerProvider.getDbconsuminglog().info("spirittree.selectsingle "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return spirittreeBean;
		}finally{
			session.close();
		}
    }
    
    
    public int update(SpirittreeBean selectsingle) throws SQLException {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.update("spirittree.update", selectsingle);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("spirittree.update "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
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
			int rows = session.delete("spirittree.delete", roleid);
			session.commit();
			LoggerProvider.getDbconsuminglog().info("spirittree.delete "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return rows;
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}
		return 0;
	}
	
	

	public int getcount() throws SQLException {
		SqlSession session = sqlMapper.openSession();
		try {
			int count = (Integer) session.selectOne("spirittree.getcount");
			return count;
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpirittreeBean> selectbeginandend(int begin, int end,int time) throws SQLException {
		SqlSession session = sqlMapper.openSession();
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("beginidx", begin);
			map.put("endidx", end);
			map.put("time", time);
			List<SpirittreeBean> list = (List<SpirittreeBean>) session.selectList("spirittree.selectbeginandend", map);
			
			return list;
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}
		return new ArrayList<SpirittreeBean>();
	}
	
}







