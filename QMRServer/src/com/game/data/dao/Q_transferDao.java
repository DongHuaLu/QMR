package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_transfer Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_transferBean;

public class Q_transferDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_transferBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_transferBean> list = (List<Q_transferBean>)session.selectList("q_transfer.select");
            return list;
    	}finally{
			session.close();
		}
    }
}