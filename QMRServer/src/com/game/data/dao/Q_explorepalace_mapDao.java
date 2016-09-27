package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_explorepalace_map Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_explorepalace_mapBean;

public class Q_explorepalace_mapDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_explorepalace_mapBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_explorepalace_mapBean> list = (List<Q_explorepalace_mapBean>)session.selectList("q_explorepalace_map.select");
            return list;
    	}finally{
			session.close();
		}
    }
}