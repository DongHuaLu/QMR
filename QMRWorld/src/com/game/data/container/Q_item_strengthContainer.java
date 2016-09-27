package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_item_strengthBean;
import com.game.data.dao.Q_item_strengthDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_item_strength数据容器
 */
public class Q_item_strengthContainer {

	private List<Q_item_strengthBean> list;
	
	private HashMap<String, Q_item_strengthBean> map = new HashMap<String, Q_item_strengthBean>();

	private Q_item_strengthDao dao = new Q_item_strengthDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_item_strengthBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_item_strengthBean bean = (Q_item_strengthBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_item_strengthBean> getList(){
		return list;
	}
	
	public HashMap<String, Q_item_strengthBean> getMap(){
		return map;
	}
}