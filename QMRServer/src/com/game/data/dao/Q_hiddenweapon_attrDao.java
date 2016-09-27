package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_hiddenweapon_attr Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_hiddenweapon_attrBean;

public class Q_hiddenweapon_attrDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_hiddenweapon_attrBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_hiddenweapon_attrBean> list = (List<Q_hiddenweapon_attrBean>)session.selectList("q_hiddenweapon_attr.select");
            return list;
    	}finally{
			session.close();
		}
    }
}