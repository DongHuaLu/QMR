package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_guildbannerBean;
import com.game.data.dao.Q_guildbannerDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_guildbanner数据容器
 */
public class Q_guildbannerContainer {

	private List<Q_guildbannerBean> list;
	
	private HashMap<Integer, Q_guildbannerBean> map = new HashMap<Integer, Q_guildbannerBean>();

	private Q_guildbannerDao dao = new Q_guildbannerDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_guildbannerBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_guildbannerBean bean = (Q_guildbannerBean) iter
					.next();
			map.put(bean.getGuildbannerlv(), bean);
		}
	}

	public List<Q_guildbannerBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_guildbannerBean> getMap(){
		return map;
	}
}