package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_monster_dropprobBean;
import com.game.data.dao.Q_monster_dropprobDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_monster_dropprob数据容器
 */
public class Q_monster_dropprobContainer {

	private List<Q_monster_dropprobBean> list;
	
	private HashMap<Integer, Q_monster_dropprobBean> map = new HashMap<Integer, Q_monster_dropprobBean>();

	private Q_monster_dropprobDao dao = new Q_monster_dropprobDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_monster_dropprobBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_monster_dropprobBean bean = (Q_monster_dropprobBean) iter
					.next();
			map.put(bean.getQ_monsterid(), bean);
		}
	}

	public List<Q_monster_dropprobBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_monster_dropprobBean> getMap(){
		return map;
	}
}