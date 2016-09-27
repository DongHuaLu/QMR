package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_petattribute Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_petattributeBean;

public class Q_petattributeDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_petattributeBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_petattributeBean> list = (List<Q_petattributeBean>)session.selectList("q_petattribute.select");
            return list;
    	}finally{
			session.close();
		}
    }
}