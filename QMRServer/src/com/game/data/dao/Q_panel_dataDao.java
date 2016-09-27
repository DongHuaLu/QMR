package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_panel_data Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_panel_dataBean;

public class Q_panel_dataDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_panel_dataBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_panel_dataBean> list = (List<Q_panel_dataBean>)session.selectList("q_panel_data.select");
            return list;
    	}finally{
			session.close();
		}
    }
}