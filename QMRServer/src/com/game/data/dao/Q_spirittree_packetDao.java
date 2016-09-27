package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_spirittree_packet Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_spirittree_packetBean;

public class Q_spirittree_packetDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_spirittree_packetBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_spirittree_packetBean> list = (List<Q_spirittree_packetBean>)session.selectList("q_spirittree_packet.select");
            return list;
    	}finally{
			session.close();
		}
    }
}