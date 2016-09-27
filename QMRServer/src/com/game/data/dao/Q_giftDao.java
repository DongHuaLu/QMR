package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_gift Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_giftBean;

public class Q_giftDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_giftBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_giftBean> list = (List<Q_giftBean>)session.selectList("q_gift.select");
            return list;
    	}finally{
			session.close();
		}
    }
}