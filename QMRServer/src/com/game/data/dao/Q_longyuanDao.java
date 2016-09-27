package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_longyuan Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_longyuanBean;

public class Q_longyuanDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_longyuanBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_longyuanBean> list = (List<Q_longyuanBean>)session.selectList("q_longyuan.select");
            return list;
    	}finally{
			session.close();
		}
    }
}