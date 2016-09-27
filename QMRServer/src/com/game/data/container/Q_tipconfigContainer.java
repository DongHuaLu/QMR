package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_tipconfigBean;
import com.game.data.dao.Q_tipconfigDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_tipconfig数据容器
 */
public class Q_tipconfigContainer {

	private List<Q_tipconfigBean> list;
	
	private HashMap<String, Q_tipconfigBean> map = new HashMap<String, Q_tipconfigBean>();

	private Q_tipconfigDao dao = new Q_tipconfigDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_tipconfigBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_tipconfigBean bean = (Q_tipconfigBean) iter
					.next();
			map.put(bean.getTipsID(), bean);
		}
	}

	public List<Q_tipconfigBean> getList(){
		return list;
	}
	
	public HashMap<String, Q_tipconfigBean> getMap(){
		return map;
	}
}