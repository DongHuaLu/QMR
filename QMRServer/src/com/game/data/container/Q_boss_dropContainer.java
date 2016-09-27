package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_boss_dropBean;
import com.game.data.dao.Q_boss_dropDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_boss_drop数据容器
 */
public class Q_boss_dropContainer {

	private List<Q_boss_dropBean> list;
	
	private HashMap<Integer, Q_boss_dropBean> map = new HashMap<Integer, Q_boss_dropBean>();

	private Q_boss_dropDao dao = new Q_boss_dropDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_boss_dropBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_boss_dropBean bean = (Q_boss_dropBean) iter
					.next();
			map.put(bean.getQ_boss_id(), bean);
		}
	}

	public List<Q_boss_dropBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_boss_dropBean> getMap(){
		return map;
	}
}