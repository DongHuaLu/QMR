package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_qingfengguyun Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_qingfengguyunBean;

public class Q_qingfengguyunDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_qingfengguyunBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_qingfengguyunBean> list = (List<Q_qingfengguyunBean>)session.selectList("q_qingfengguyun.select");
            return list;
    	}finally{
			session.close();
		}
    }
}