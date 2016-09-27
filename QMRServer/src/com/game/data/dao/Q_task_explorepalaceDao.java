package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_task_explorepalace Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_task_explorepalaceBean;

public class Q_task_explorepalaceDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_task_explorepalaceBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_task_explorepalaceBean> list = (List<Q_task_explorepalaceBean>)session.selectList("q_task_explorepalace.select");
            return list;
    	}finally{
			session.close();
		}
    }
}