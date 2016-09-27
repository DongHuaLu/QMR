package com.game.db.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.game.db.DBServer;
import com.game.db.bean.GoldExpend;
import com.game.utils.LoggerProvider;
import com.game.utils.TimeUtil;

public class GoldExpendDao {

	protected Logger log = Logger.getLogger(GoldExpendDao.class);
	
	private SqlSessionFactory sqlMapper = DBServer.getInstance().getSqlMapper();


    public int insert(GoldExpend expend) {
        SqlSession session = sqlMapper.openSession();
    	try {
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.insert("gold_expend.insert", expend);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("gold_expend.insert "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
    		return rows;
    	} catch (Exception e) {
    		log.error(e, e);
    		return 0;
    	} finally {
			session.close();
		}
    }

	@SuppressWarnings("unchecked")
	public List<GoldExpend> select(long roleid, long startTime) {
		List<GoldExpend> ret = new ArrayList<GoldExpend>();
		SqlSession session = sqlMapper.openSession();
    	try {
    		long currentTimeMillis = System.currentTimeMillis();
    		HashMap<String, Object> map = new HashMap<String, Object>();
    		map.put("roleId", roleid);
    		map.put("startTime", startTime);
    		ret = session.selectList("gold_expend.select", map);
    		LoggerProvider.getDbconsuminglog().info("gold_expend.select "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
    	} finally {
			session.close();
		}
    	return ret;
	}
}