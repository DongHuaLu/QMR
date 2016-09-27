package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_arrow_bow Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_arrow_bowBean;

public class Q_arrow_bowDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_arrow_bowBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_arrow_bowBean> list = (List<Q_arrow_bowBean>)session.selectList("q_arrow_bow.select");
            return list;
    	}finally{
			session.close();
		}
    }
}