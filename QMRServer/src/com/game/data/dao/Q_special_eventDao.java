package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_special_event Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_special_eventBean;

public class Q_special_eventDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_special_eventBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_special_eventBean> list = (List<Q_special_eventBean>)session.selectList("q_special_event.select");
            return list;
    	}finally{
			session.close();
		}
    }
}