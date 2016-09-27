package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_scriptBean;
import com.game.data.dao.Q_scriptDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_script数据容器
 */
public class Q_scriptContainer {

	private List<Q_scriptBean> list;
	
	private HashMap<Integer, Q_scriptBean> map = new HashMap<Integer, Q_scriptBean>();

	private Q_scriptDao dao = new Q_scriptDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_scriptBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_scriptBean bean = (Q_scriptBean) iter
					.next();
			map.put(bean.getQ_script_id(), bean);
		}
	}

	public List<Q_scriptBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_scriptBean> getMap(){
		return map;
	}
}