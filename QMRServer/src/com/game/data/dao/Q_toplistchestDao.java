package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_toplistchest Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_toplistchestBean;

public class Q_toplistchestDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_toplistchestBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_toplistchestBean> list = (List<Q_toplistchestBean>)session.selectList("q_toplistchest.select");
            return list;
    	}finally{
			session.close();
		}
    }
}