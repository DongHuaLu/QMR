package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_meihuaxuanwu Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_meihuaxuanwuBean;

public class Q_meihuaxuanwuDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_meihuaxuanwuBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_meihuaxuanwuBean> list = (List<Q_meihuaxuanwuBean>)session.selectList("q_meihuaxuanwu.select");
            return list;
    	}finally{
			session.close();
		}
    }
}