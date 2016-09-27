package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_item Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_itemBean;

public class Q_itemDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_itemBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_itemBean> list = (List<Q_itemBean>)session.selectList("q_item.select");
            return list;
    	}finally{
			session.close();
		}
    }
}