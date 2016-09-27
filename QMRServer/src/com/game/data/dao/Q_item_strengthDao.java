package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_item_strength Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_item_strengthBean;

public class Q_item_strengthDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_item_strengthBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_item_strengthBean> list = (List<Q_item_strengthBean>)session.selectList("q_item_strength.select");
            return list;
    	}finally{
			session.close();
		}
    }
}