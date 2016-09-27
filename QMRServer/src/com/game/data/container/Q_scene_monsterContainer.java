package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_scene_monsterBean;
import com.game.data.dao.Q_scene_monsterDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_scene_monster数据容器
 */
public class Q_scene_monsterContainer {

	private List<Q_scene_monsterBean> list;
	
	private HashMap<Integer, Q_scene_monsterBean> map = new HashMap<Integer, Q_scene_monsterBean>();

	private Q_scene_monsterDao dao = new Q_scene_monsterDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_scene_monsterBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_scene_monsterBean bean = (Q_scene_monsterBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_scene_monsterBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_scene_monsterBean> getMap(){
		return map;
	}
}