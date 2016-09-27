package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_spirittree_packetBean;
import com.game.data.dao.Q_spirittree_packetDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_spirittree_packet数据容器
 */
public class Q_spirittree_packetContainer {

	private List<Q_spirittree_packetBean> list;
	
	private HashMap<Integer, Q_spirittree_packetBean> map = new HashMap<Integer, Q_spirittree_packetBean>();

	private Q_spirittree_packetDao dao = new Q_spirittree_packetDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_spirittree_packetBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_spirittree_packetBean bean = (Q_spirittree_packetBean) iter
					.next();
			map.put(bean.getQ_packet_id(), bean);
		}
	}

	public List<Q_spirittree_packetBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_spirittree_packetBean> getMap(){
		return map;
	}
}