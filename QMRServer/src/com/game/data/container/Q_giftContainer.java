package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_giftBean;
import com.game.data.dao.Q_giftDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_gift数据容器
 */
public class Q_giftContainer {

	private List<Q_giftBean> list;
	
	private HashMap<Integer, Q_giftBean> map = new HashMap<Integer, Q_giftBean>();

	private Q_giftDao dao = new Q_giftDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_giftBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_giftBean bean = (Q_giftBean) iter
					.next();
			map.put(bean.getQ_gift_id(), bean);
		}
	}

	public List<Q_giftBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_giftBean> getMap(){
		return map;
	}
}