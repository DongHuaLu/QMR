package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_shop Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_shopBean;

public class Q_shopDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_shopBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_shopBean> list = (List<Q_shopBean>)session.selectList("q_shop.select");
            return list;
    	}finally{
			session.close();
		}
    }
}