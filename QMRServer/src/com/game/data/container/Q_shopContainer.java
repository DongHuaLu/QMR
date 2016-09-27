package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_shopBean;
import com.game.data.dao.Q_shopDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_shop数据容器
 */
public class Q_shopContainer {

	private List<Q_shopBean> list;
	
	private HashMap<Integer, Q_shopBean> map = new HashMap<Integer, Q_shopBean>();

	private Q_shopDao dao = new Q_shopDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_shopBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_shopBean bean = (Q_shopBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_shopBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_shopBean> getMap(){
		return map;
	}
}