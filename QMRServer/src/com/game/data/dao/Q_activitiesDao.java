package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_activities Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_activitiesBean;

public class Q_activitiesDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_activitiesBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_activitiesBean> list = (List<Q_activitiesBean>)session.selectList("q_activities.select");
            return list;
    	}finally{
			session.close();
		}
    }
}