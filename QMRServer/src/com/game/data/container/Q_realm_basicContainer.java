package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_realm_basicBean;
import com.game.data.dao.Q_realm_basicDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_realm_basic数据容器
 */
public class Q_realm_basicContainer {

	private List<Q_realm_basicBean> list;
	
	private HashMap<Integer, Q_realm_basicBean> map = new HashMap<Integer, Q_realm_basicBean>();

	private Q_realm_basicDao dao = new Q_realm_basicDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_realm_basicBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_realm_basicBean bean = (Q_realm_basicBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_realm_basicBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_realm_basicBean> getMap(){
		return map;
	}
}