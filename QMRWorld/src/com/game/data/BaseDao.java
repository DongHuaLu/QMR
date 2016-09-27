package com.game.data;

import org.apache.ibatis.session.SqlSessionFactory;

public class BaseDao {

	protected SqlSessionFactory getSqlMapper(){
		return DataServer.getInstance().getSqlMapper();
	}
}
