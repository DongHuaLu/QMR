package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_npcBean;
import com.game.data.dao.Q_npcDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_npc数据容器
 */
public class Q_npcContainer {

	private List<Q_npcBean> list;
	
	private HashMap<Integer, Q_npcBean> map = new HashMap<Integer, Q_npcBean>();

	private Q_npcDao dao = new Q_npcDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_npcBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_npcBean bean = (Q_npcBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_npcBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_npcBean> getMap(){
		return map;
	}
}