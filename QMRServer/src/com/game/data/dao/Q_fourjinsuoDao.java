package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_fourjinsuo Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_fourjinsuoBean;

public class Q_fourjinsuoDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_fourjinsuoBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_fourjinsuoBean> list = (List<Q_fourjinsuoBean>)session.selectList("q_fourjinsuo.select");
            return list;
    	}finally{
			session.close();
		}
    }
}