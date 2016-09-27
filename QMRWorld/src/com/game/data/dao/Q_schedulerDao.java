package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_scheduler Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_schedulerBean;

public class Q_schedulerDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_schedulerBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_schedulerBean> list = (List<Q_schedulerBean>)session.selectList("q_scheduler.select");
            return list;
    	}finally{
			session.close();
		}
    }
}