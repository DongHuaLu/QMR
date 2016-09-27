package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_map_block Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_map_blockBean;

public class Q_map_blockDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_map_blockBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_map_blockBean> list = (List<Q_map_blockBean>)session.selectList("q_map_block.select");
            return list;
    	}finally{
			session.close();
		}
    }
}