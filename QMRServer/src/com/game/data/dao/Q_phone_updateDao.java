package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_phone_update Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_phone_updateBean;

public class Q_phone_updateDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_phone_updateBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_phone_updateBean> list = (List<Q_phone_updateBean>)session.selectList("q_phone_update.select");
            return list;
    	}finally{
			session.close();
		}
    }
}