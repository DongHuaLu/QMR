package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_backpack_grid Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_backpack_gridBean;

public class Q_backpack_gridDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_backpack_gridBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_backpack_gridBean> list = (List<Q_backpack_gridBean>)session.selectList("q_backpack_grid.select");
            return list;
    	}finally{
			session.close();
		}
    }
}