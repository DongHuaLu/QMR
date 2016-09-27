package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_vip Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_vipBean;

public class Q_vipDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_vipBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_vipBean> list = (List<Q_vipBean>)session.selectList("q_vip.select");
            return list;
    	}finally{
			session.close();
		}
    }
}