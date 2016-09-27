package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_pets Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_petsBean;

public class Q_petsDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_petsBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_petsBean> list = (List<Q_petsBean>)session.selectList("q_pets.select");
            return list;
    	}finally{
			session.close();
		}
    }
}