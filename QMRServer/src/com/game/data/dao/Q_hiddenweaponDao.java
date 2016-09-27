package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_hiddenweapon Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_hiddenweaponBean;

public class Q_hiddenweaponDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_hiddenweaponBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_hiddenweaponBean> list = (List<Q_hiddenweaponBean>)session.selectList("q_hiddenweapon.select");
            return list;
    	}finally{
			session.close();
		}
    }
}