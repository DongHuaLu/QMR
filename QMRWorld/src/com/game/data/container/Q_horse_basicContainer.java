package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_horse_basicBean;
import com.game.data.dao.Q_horse_basicDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_horse_basic数据容器
 */
public class Q_horse_basicContainer {

	private List<Q_horse_basicBean> list;
	
	private HashMap<Integer, Q_horse_basicBean> map = new HashMap<Integer, Q_horse_basicBean>();

	private Q_horse_basicDao dao = new Q_horse_basicDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_horse_basicBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_horse_basicBean bean = (Q_horse_basicBean) iter
					.next();
			map.put(bean.getQ_layer(), bean);
		}
	}

	public List<Q_horse_basicBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_horse_basicBean> getMap(){
		return map;
	}
}