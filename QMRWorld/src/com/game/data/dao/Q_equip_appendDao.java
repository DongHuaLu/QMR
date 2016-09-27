package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_equip_append Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_equip_appendBean;

public class Q_equip_appendDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_equip_appendBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_equip_appendBean> list = (List<Q_equip_appendBean>)session.selectList("q_equip_append.select");
            return list;
    	}finally{
			session.close();
		}
    }
}