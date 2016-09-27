package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_bulletinBean;
import com.game.data.dao.Q_bulletinDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_bulletin数据容器
 */
public class Q_bulletinContainer {

	private List<Q_bulletinBean> list;
	
	private HashMap<Integer, Q_bulletinBean> map = new HashMap<Integer, Q_bulletinBean>();

	private Q_bulletinDao dao = new Q_bulletinDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_bulletinBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_bulletinBean bean = (Q_bulletinBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_bulletinBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_bulletinBean> getMap(){
		return map;
	}
}