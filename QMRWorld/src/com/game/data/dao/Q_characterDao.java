package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_character Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_characterBean;

public class Q_characterDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_characterBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_characterBean> list = (List<Q_characterBean>)session.selectList("q_character.select");
            return list;
    	}finally{
			session.close();
		}
    }
}