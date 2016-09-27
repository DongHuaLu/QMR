package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_task_daily_rewards Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_task_daily_rewardsBean;

public class Q_task_daily_rewardsDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_task_daily_rewardsBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_task_daily_rewardsBean> list = (List<Q_task_daily_rewardsBean>)session.selectList("q_task_daily_rewards.select");
            return list;
    	}finally{
			session.close();
		}
    }
}