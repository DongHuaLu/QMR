package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_realm_breakBean;
import com.game.data.dao.Q_realm_breakDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_realm_break数据容器
 */
public class Q_realm_breakContainer {

	private List<Q_realm_breakBean> list;
	
	private HashMap<Integer, Q_realm_breakBean> map = new HashMap<Integer, Q_realm_breakBean>();

	private Q_realm_breakDao dao = new Q_realm_breakDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_realm_breakBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_realm_breakBean bean = (Q_realm_breakBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_realm_breakBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_realm_breakBean> getMap(){
		return map;
	}
}