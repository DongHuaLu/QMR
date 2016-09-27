package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_newrole_defaultvalueBean;
import com.game.data.dao.Q_newrole_defaultvalueDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_newrole_defaultvalue数据容器
 */
public class Q_newrole_defaultvalueContainer {

	private List<Q_newrole_defaultvalueBean> list;
	
	private HashMap<Integer, Q_newrole_defaultvalueBean> map = new HashMap<Integer, Q_newrole_defaultvalueBean>();

	private Q_newrole_defaultvalueDao dao = new Q_newrole_defaultvalueDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_newrole_defaultvalueBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_newrole_defaultvalueBean bean = (Q_newrole_defaultvalueBean) iter
					.next();
			map.put(bean.getQ_sex(), bean);
		}
	}

	public List<Q_newrole_defaultvalueBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_newrole_defaultvalueBean> getMap(){
		return map;
	}
}