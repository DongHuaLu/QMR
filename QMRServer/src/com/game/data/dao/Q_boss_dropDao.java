package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_boss_drop Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_boss_dropBean;

public class Q_boss_dropDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_boss_dropBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_boss_dropBean> list = (List<Q_boss_dropBean>)session.selectList("q_boss_drop.select");
            return list;
    	}finally{
			session.close();
		}
    }
}