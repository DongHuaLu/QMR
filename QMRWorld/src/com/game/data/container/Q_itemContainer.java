package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_itemBean;
import com.game.data.dao.Q_itemDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_item数据容器
 */
public class Q_itemContainer {

	private List<Q_itemBean> list;
	
	private HashMap<Integer, Q_itemBean> map = new HashMap<Integer, Q_itemBean>();

	private Q_itemDao dao = new Q_itemDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_itemBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_itemBean bean = (Q_itemBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_itemBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_itemBean> getMap(){
		return map;
	}
}