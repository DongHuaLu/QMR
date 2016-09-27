package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_task_daily_cond Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_task_daily_condBean;

public class Q_task_daily_condDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_task_daily_condBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_task_daily_condBean> list = (List<Q_task_daily_condBean>)session.selectList("q_task_daily_cond.select");
            return list;
    	}finally{
			session.close();
		}
    }
}