package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_monster_dropgroupBean;
import com.game.data.dao.Q_monster_dropgroupDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_monster_dropgroup数据容器
 */
public class Q_monster_dropgroupContainer {

	private List<Q_monster_dropgroupBean> list;
	
	private HashMap<Integer, Q_monster_dropgroupBean> map = new HashMap<Integer, Q_monster_dropgroupBean>();

	private Q_monster_dropgroupDao dao = new Q_monster_dropgroupDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_monster_dropgroupBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_monster_dropgroupBean bean = (Q_monster_dropgroupBean) iter
					.next();
			map.put(bean.getQ_group_id(), bean);
		}
	}

	public List<Q_monster_dropgroupBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_monster_dropgroupBean> getMap(){
		return map;
	}
}