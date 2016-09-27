package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_map_blockBean;
import com.game.data.dao.Q_map_blockDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_map_block数据容器
 */
public class Q_map_blockContainer {

	private List<Q_map_blockBean> list;
	
	private HashMap<Integer, Q_map_blockBean> map = new HashMap<Integer, Q_map_blockBean>();

	private Q_map_blockDao dao = new Q_map_blockDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_map_blockBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_map_blockBean bean = (Q_map_blockBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_map_blockBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_map_blockBean> getMap(){
		return map;
	}
}