package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_newfunc Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_newfuncBean;

public class Q_newfuncDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_newfuncBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_newfuncBean> list = (List<Q_newfuncBean>)session.selectList("q_newfunc.select");
            return list;
    	}finally{
			session.close();
		}
    }
}