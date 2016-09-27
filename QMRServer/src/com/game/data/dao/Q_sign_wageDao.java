package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_sign_wage Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_sign_wageBean;

public class Q_sign_wageDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_sign_wageBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_sign_wageBean> list = (List<Q_sign_wageBean>)session.selectList("q_sign_wage.select");
            return list;
    	}finally{
			session.close();
		}
    }
}