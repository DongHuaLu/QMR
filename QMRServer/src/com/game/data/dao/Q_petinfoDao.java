package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_petinfo Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_petinfoBean;

public class Q_petinfoDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_petinfoBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_petinfoBean> list = (List<Q_petinfoBean>)session.selectList("q_petinfo.select");
            return list;
    	}finally{
			session.close();
		}
    }
}