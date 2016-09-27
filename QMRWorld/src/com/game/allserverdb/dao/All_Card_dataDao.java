package com.game.allserverdb.dao;

/**
 * @author ExcelUtil Auto Maker
 *
 * @version 1.0.0
 *
 * all_card_data Dao
 */
import com.game.allserverdb.BaseDao;
import com.game.allserverdb.bean.All_Card_dataBean;
import com.game.utils.LoggerProvider;
import com.game.utils.TimeUtil;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

public class All_Card_dataDao extends BaseDao {

	private Logger log = Logger.getLogger(All_Card_dataDao.class);
	private SqlSessionFactory sqlMapper = getSqlMapper();

	@SuppressWarnings("unchecked")
	public List<All_Card_dataBean> select() {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			List<All_Card_dataBean> list = (List<All_Card_dataBean>) session.selectList("all_card_data.select");
			LoggerProvider.getDbconsuminglog().info("all_card_data.select "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<All_Card_dataBean> selectbyid(String cardid) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			List<All_Card_dataBean> list = (List<All_Card_dataBean>) session.selectList("all_card_data.selectbyid", cardid);
			LoggerProvider.getDbconsuminglog().info("all_card_data.selectbyid "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<All_Card_dataBean> selectbyaccountandagidonly(All_Card_dataBean card_dataBean) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			List<All_Card_dataBean> list = (List<All_Card_dataBean>) session.selectList("all_card_data.selectbyaccountandagidonly", card_dataBean);
			LoggerProvider.getDbconsuminglog().info("all_card_data.selectbyaccountandagidonly "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<All_Card_dataBean> selectbyaccountandtypeonly(All_Card_dataBean card_dataBean) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			List<All_Card_dataBean> list = (List<All_Card_dataBean>) session.selectList("all_card_data.selectbyaccountandtypeonly", card_dataBean);
			LoggerProvider.getDbconsuminglog().info("all_card_data.selectbyaccountandtypeonly "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<All_Card_dataBean> selectbyroleidandagidonly(All_Card_dataBean card_dataBean) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			List<All_Card_dataBean> list = (List<All_Card_dataBean>) session.selectList("all_card_data.selectbyroleidandagidonly", card_dataBean);
			LoggerProvider.getDbconsuminglog().info("all_card_data.selectbyroleidandagidonly "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<All_Card_dataBean> selectbyroleidandtypeonly(All_Card_dataBean card_dataBean) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			List<All_Card_dataBean> list = (List<All_Card_dataBean>) session.selectList("all_card_data.selectbyroleidandtypeonly", card_dataBean);
			LoggerProvider.getDbconsuminglog().info("all_card_data.selectbyroleidandtypeonly "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} finally {
			session.close();
		}
	}

	public int insert(All_Card_dataBean card_dataBean) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			int rows = session.insert("all_card_data.insert", card_dataBean);
			LoggerProvider.getDbconsuminglog().info("all_card_data.insert "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
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