package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_tipconfig Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_tipconfigBean;

public class Q_tipconfigDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_tipconfigBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_tipconfigBean> list = (List<Q_tipconfigBean>)session.selectList("q_tipconfig.select");
            return list;
    	}finally{
			session.close();
		}
    }
}