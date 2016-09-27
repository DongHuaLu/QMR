package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_platformgift Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_platformgiftBean;

public class Q_platformgiftDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_platformgiftBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_platformgiftBean> list = (List<Q_platformgiftBean>)session.selectList("q_platformgift.select");
            return list;
    	}finally{
			session.close();
		}
    }
}