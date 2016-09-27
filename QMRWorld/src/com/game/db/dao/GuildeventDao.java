package com.game.db.dao;

/**
 * @author ExcelUtil Auto Maker
 *
 * @version 1.0.0
 *
 * Guildevent Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.game.db.BaseDao;
import com.game.db.bean.GuildeventBean;
import com.game.utils.LoggerProvider;
import com.game.utils.TimeUtil;

public class GuildeventDao extends BaseDao {

	private Logger log = Logger.getLogger(GuildeventDao.class);
	private SqlSessionFactory sqlMapper = getSqlMapper();

	@SuppressWarnings("unchecked")
	public List<GuildeventBean> select() {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			List<GuildeventBean> list = (List<GuildeventBean>) session.selectList("guildevent.select");
			LoggerProvider.getDbconsuminglog().info("guildevent.select "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} finally {
			session.close();
		}
	}

	public List<GuildeventBean> selectbyid(long guildid) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			@SuppressWarnings("unchecked")
			List<GuildeventBean> list = (List<GuildeventBean>) session.selectList("guildevent.selectbyid",guildid);
			LoggerProvider.getDbconsuminglog().info("guildevent.selectbyid "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} finally {
			session.close();
		}
	}

	public int insert(GuildeventBean guildeventBean) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			int rows = session.insert("guildevent.insert", guildeventBean);
			session.commit();
			LoggerProvider.getDbconsuminglog().info("guildevent.insert "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return rows;
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}
		return 0;
	}

	public int update(GuildeventBean guildeventBean) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			int rows = session.update("guildevent.update", guildeventBean);
			session.commit();
			LoggerProvider.getDbconsuminglog().info("guildevent.update "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return rows;
		} catch (Exception e) {
			log.error(e, e);
		} finally {
			session.close();
		}
		return 0;
	}

	public int delete(Long eventid) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			int rows = session.delete("guildevent.delete", eventid);
			session.commit();
			LoggerProvider.getDbconsuminglog().info("guildevent.delete "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return rows;
		} catch (Exception e) {
			log.error(e, e);
		} finally {
			session.close();
		}
		return 0;
	}
}