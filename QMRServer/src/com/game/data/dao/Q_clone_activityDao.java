package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_clone_activity Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_clone_activityBean;

public class Q_clone_activityDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_clone_activityBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_clone_activityBean> list = (List<Q_clone_activityBean>)session.selectList("q_clone_activity.select");
            return list;
    	}finally{
			session.close();
		}
    }
}