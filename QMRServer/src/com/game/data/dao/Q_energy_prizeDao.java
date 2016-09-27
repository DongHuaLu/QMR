package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_energy_prize Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_energy_prizeBean;

public class Q_energy_prizeDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_energy_prizeBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_energy_prizeBean> list = (List<Q_energy_prizeBean>)session.selectList("q_energy_prize.select");
            return list;
    	}finally{
			session.close();
		}
    }
}