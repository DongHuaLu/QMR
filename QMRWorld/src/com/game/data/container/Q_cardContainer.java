package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_cardBean;
import com.game.data.dao.Q_cardDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_card数据容器
 */
public class Q_cardContainer {

	private List<Q_cardBean> list;
	
	private HashMap<String, Q_cardBean> map = new HashMap<String, Q_cardBean>();

	private Q_cardDao dao = new Q_cardDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_cardBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_cardBean bean = (Q_cardBean) iter.next();
			map.put(""+bean.getQ_ag_id()+"_"+bean.getQ_card_type(), bean);
		}
	}

	public List<Q_cardBean> getList(){
		return list;
	}
	
	public HashMap<String, Q_cardBean> getMap(){
		return map;
	}
}