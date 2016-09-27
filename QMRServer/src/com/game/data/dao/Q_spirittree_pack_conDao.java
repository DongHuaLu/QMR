package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_spirittree_pack_con Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_spirittree_pack_conBean;

public class Q_spirittree_pack_conDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_spirittree_pack_conBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_spirittree_pack_conBean> list = (List<Q_spirittree_pack_conBean>)session.selectList("q_spirittree_pack_con.select");
            return list;
    	}finally{
			session.close();
		}
    }
}