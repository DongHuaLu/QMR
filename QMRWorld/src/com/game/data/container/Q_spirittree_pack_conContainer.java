package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_spirittree_pack_conBean;
import com.game.data.dao.Q_spirittree_pack_conDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_spirittree_pack_con数据容器
 */
public class Q_spirittree_pack_conContainer {

	private List<Q_spirittree_pack_conBean> list;
	
	private HashMap<Integer, Q_spirittree_pack_conBean> map = new HashMap<Integer, Q_spirittree_pack_conBean>();

	private Q_spirittree_pack_conDao dao = new Q_spirittree_pack_conDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_spirittree_pack_conBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_spirittree_pack_conBean bean = (Q_spirittree_pack_conBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_spirittree_pack_conBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_spirittree_pack_conBean> getMap(){
		return map;
	}
}