package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_role_random_nameBean;
import com.game.data.dao.Q_role_random_nameDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_role_random_name数据容器
 */
public class Q_role_random_nameContainer {

	private List<Q_role_random_nameBean> list;
	
	private HashMap<Integer, Q_role_random_nameBean> map = new HashMap<Integer, Q_role_random_nameBean>();

	private Q_role_random_nameDao dao = new Q_role_random_nameDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_role_random_nameBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_role_random_nameBean bean = (Q_role_random_nameBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_role_random_nameBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_role_random_nameBean> getMap(){
		return map;
	}
}