package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_task_main Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_task_mainBean;

public class Q_task_mainDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_task_mainBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_task_mainBean> list = (List<Q_task_mainBean>)session.selectList("q_task_main.select");
            return list;
    	}finally{
			session.close();
		}
    }
}