package com.game.data.dao;

/**
 * @author ExcelUtil Auto Maker
 *
 * @version 1.0.0
 *
 * Guildbanner Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.game.data.BaseDao;
import com.game.data.bean.Q_guildbannerBean;

public class Q_guildbannerDao extends BaseDao {

	private Logger log = Logger.getLogger(Q_guildbannerDao.class);
	private SqlSessionFactory sqlMapper = getSqlMapper();

	@SuppressWarnings("unchecked")
	public List<Q_guildbannerBean> select() {
		SqlSession session = sqlMapper.openSession();
		try {
			List<Q_guildbannerBean> list = (List<Q_guildbannerBean>) session.selectList("q_guildbanner.select");
			return list;
		} finally {
			session.close();
		}
	}

	public int insert(Q_guildbannerBean guildbannerBean) {
		SqlSession session = sqlMapper.openSession();
		try {
			int rows = session.insert("q_guildbanner.insert", guildbannerBean);
			session.commit();
			return rows;
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}
		return 0;
	}

	public int update(Q_guildbannerBean guildbannerBean) {
		SqlSession session = sqlMapper.openSession();
		try {
			int rows = session.update("q_guildbanner.update", guildbannerBean);
			session.commit();
			return rows;
		} catch (Exception e) {
			log.error(e, e);
		} finally {
			session.close();
		}
		return 0;
	}
}