package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_scene_monster_areaBean;
import com.game.data.dao.Q_scene_monster_areaDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_scene_monster_area数据容器
 */
public class Q_scene_monster_areaContainer {

	private List<Q_scene_monster_areaBean> list;
	
	private HashMap<Integer, Q_scene_monster_areaBean> map = new HashMap<Integer, Q_scene_monster_areaBean>();

	private Q_scene_monster_areaDao dao = new Q_scene_monster_areaDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_scene_monster_areaBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_scene_monster_areaBean bean = (Q_scene_monster_areaBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_scene_monster_areaBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_scene_monster_areaBean> getMap(){
		return map;
	}
}