package com.game.db.dao;

/**
 * @author ExcelUtil Auto Maker
 *
 * @version 1.0.0
 *
 * Card_data Dao
 */
import com.game.db.BaseDao;
import com.game.db.bean.Card_dataBean;
import com.game.utils.LoggerProvider;
import com.game.utils.TimeUtil;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

public class Card_dataDao extends BaseDao {

	private Logger log = Logger.getLogger(Card_dataDao.class);
	private SqlSessionFactory sqlMapper = getSqlMapper();

	@SuppressWarnings("unchecked")
	public List<Card_dataBean> select() {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			List<Card_dataBean> list = (List<Card_dataBean>) session.selectList("card_data.select");
			LoggerProvider.getDbconsuminglog().info("card_data.select "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Card_dataBean> selectbyid(String cardid) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			List<Card_dataBean> list = (List<Card_dataBean>) session.selectList("card_data.selectbyid", cardid);
			LoggerProvider.getDbconsuminglog().info("card_data.selectbyid "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Card_dataBean> selectbyaccountandagidonly(Card_dataBean card_dataBean) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			List<Card_dataBean> list = (List<Card_dataBean>) session.selectList("card_data.selectbyaccountandagidonly", card_dataBean);
			LoggerProvider.getDbconsuminglog().info("card_data.selectbyaccountandagidonly "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Card_dataBean> selectbyaccountandtypeonly(Card_dataBean card_dataBean) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			List<Card_dataBean> list = (List<Card_dataBean>) session.selectList("card_data.selectbyaccountandtypeonly", card_dataBean);
			LoggerProvider.getDbconsuminglog().info("card_data.selectbyaccountandtypeonly "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Card_dataBean> selectbyroleidandagidonly(Card_dataBean card_dataBean) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			List<Card_dataBean> list = (List<Card_dataBean>) session.selectList("card_data.selectbyroleidandagidonly", card_dataBean);
			LoggerProvider.getDbconsuminglog().info("card_data.selectbyroleidandagidonly "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Card_dataBean> selectbyroleidandtypeonly(Card_dataBean card_dataBean) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			List<Card_dataBean> list = (List<Card_dataBean>) session.selectList("card_data.selectbyroleidandtypeonly", card_dataBean);
			LoggerProvider.getDbconsuminglog().info("card_data.selectbyroleidandtypeonly "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} finally {
			session.close();
		}
	}

	public int insert(Card_dataBean card_dataBean) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			int rows = session.insert("card_data.insert", card_dataBean);
			LoggerProvider.getDbconsuminglog().info("card_data.insert "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			session.commit();
			return rows;
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}
		return 0;
	}
}