package com.game.db.dao;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.game.db.BaseDao;
import com.game.db.bean.RegistrarBean;
import com.game.utils.LoggerProvider;
import com.game.utils.TimeUtil;

public class RegistrarDao extends BaseDao {

	private Logger log = Logger.getLogger(RegistrarDao.class);
	private SqlSessionFactory sqlMapper = getSqlMapper();
	
	//
    public int insert(RegistrarBean bean) throws SQLException {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.insert("registrar.insert", bean);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("registrar.insert "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
    		return rows;
    	}catch (Exception e) {
    		log.error(e);
		}finally{
			session.close();
		}
    	return 0;
    }
    //
	public RegistrarBean selectbyuserid(long userid) throws SQLException { 
        SqlSession session = sqlMapper.openSession();
        try{
        	long currentTimeMillis = System.currentTimeMillis();
        	RegistrarBean bean = (RegistrarBean)session.selectOne("registrar.selectbyuserid",userid);
        	LoggerProvider.getDbconsuminglog().info("registrar.selectbyuserid "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return bean;
    	}catch (Exception e) {
			log.error(e);
		}finally{
			session.close();
		}
    	return null;
    }
	//
	public RegistrarBean selectparambyuserid(long userid) throws SQLException {
		SqlSession session = sqlMapper.openSession();
	    try{
	    	long currentTimeMillis = System.currentTimeMillis();
        	RegistrarBean bean = (RegistrarBean)session.selectOne("registrar.selectparamsbyuserid",userid);
        	LoggerProvider.getDbconsuminglog().info("registrar.selectparamsbyuserid "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return bean;
    	}catch (Exception e) {
			log.error(e);
		}finally{
			session.close();
		}
    	return null;
	}
	//
	public int updateRegistrarByUserid(RegistrarBean bean) throws SQLException {  //修改登录器奖励领取状态
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.update("registrar.updateregistrarbyuserid", bean);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("registrar.updateregistrarbyuserid "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return rows;
    	}catch (Exception e) {
			log.error(e,e);
		}finally{
			session.close();
		}
    	return 0;
    }
	//
	public int updateRechargeByUserid(RegistrarBean bean) throws SQLException {  //修改账号首冲领奖情况
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.update("registrar.updaterechargebyuserid", bean);
    		if(rows<=0) rows = insert(new RegistrarBean(bean.getUserid(), 0L, 0, 0));
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("registrar.updaterechargebyuserid "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return rows;
    	}catch (Exception e) {
			log.error(e,e);
		}finally{
			session.close();
		}
    	return 0;
    }
	//
	public int updateRegistrarParamByUserid(RegistrarBean bean) throws SQLException {
		SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.update("registrar.updateregistrarparams", bean);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("registrar.updateregistrarparams "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return rows;
    	}catch (Exception e) {
			log.error(e,e);
		}finally{
			session.close();
		}
    	return 0;
	}
	
	
//	public static void main(String[] args){
//    	RegistrarBean bean = new RegistrarBean(1L, 234L, 0, 0);
//    	RegistrarDao dao = new RegistrarDao();
//    	try {
//    		//dao.insert(bean);
//    		//RegistrarBean be = dao.selectbyuserid(1L);
//    		//System.out.println(be.getUserid());
//    		//dao.updateRegistrarParamByUserid(new RegistrarBean(8444337654804250626L, "demoparams"));
//    		System.out.println(dao.selectparambyuserid(8444337654804250626L).getParams());
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//    }
}







