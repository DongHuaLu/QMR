package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_task_extra_rewards Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_task_extra_rewardsBean;

public class Q_task_extra_rewardsDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_task_extra_rewardsBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_task_extra_rewardsBean> list = (List<Q_task_extra_rewardsBean>)session.selectList("q_task_extra_rewards.select");
            return list;
    	}finally{
			session.close();
		}
    }
}