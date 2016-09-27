package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_monsterBean;
import com.game.data.dao.Q_monsterDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_monster数据容器
 */
public class Q_monsterContainer {

	private List<Q_monsterBean> list;
	
	private HashMap<Integer, Q_monsterBean> map = new HashMap<Integer, Q_monsterBean>();

	private Q_monsterDao dao = new Q_monsterDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_monsterBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_monsterBean bean = (Q_monsterBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_monsterBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_monsterBean> getMap(){
		return map;
	}
}