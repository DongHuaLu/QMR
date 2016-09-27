package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_scene_objBean;
import com.game.data.dao.Q_scene_objDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_scene_obj数据容器
 */
public class Q_scene_objContainer {

	private List<Q_scene_objBean> list;
	
	private HashMap<Integer, Q_scene_objBean> map = new HashMap<Integer, Q_scene_objBean>();

	private Q_scene_objDao dao = new Q_scene_objDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_scene_objBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_scene_objBean bean = (Q_scene_objBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_scene_objBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_scene_objBean> getMap(){
		return map;
	}
}