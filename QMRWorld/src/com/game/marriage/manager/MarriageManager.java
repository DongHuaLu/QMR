package com.game.marriage.manager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.db.bean.MarriageBean;
import com.game.db.dao.MarriageDao;
import com.game.json.JSONserializable;
import com.game.marriage.message.ReqDeleteMarriageToWorldMessage;
import com.game.marriage.message.ReqUpdatedMarriageToWorldMessage;
import com.game.marriage.structs.Marriage;
import com.game.marriage.structs.Spouse;
import com.game.utils.VersionUpdateUtil;

/**结婚系统 （世界服  只读）
 * 
 * @author zhangrong
 *
 */
public class MarriageManager {
	private Logger log = Logger.getLogger(MarriageManager.class);
	
	/**婚姻信息列表
	 * 
	 */
	private static HashMap<Long, Marriage> marriagemap = new  HashMap<Long, Marriage>();
	
	private static HashMap<Long, Marriage> playermarriagemap = new  HashMap<Long, Marriage>();
	
	private static Object obj = new Object();
	
	private MarriageManager(){
	
	}
	
	private static MarriageManager manager;
	
	public static MarriageManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new MarriageManager();
			}
		}
		return manager;
	}
	private MarriageDao marriageDao = new MarriageDao();
	
	public MarriageDao getMarriageDao() {
		return marriageDao;
	}
	
	
	
	
	
	/**
	 * 服务器启动 ，从数据库 读取所有结婚信息
	 * 
	 */
	public void loadAllMarriage() {
		try {
			List<MarriageBean> list = getMarriageDao().select();
			Iterator<MarriageBean> iterator = list.iterator();
			while (iterator.hasNext()) {
				MarriageBean marriageBean = (MarriageBean) iterator.next();
				if (marriageBean != null) {
					Marriage marriage = (Marriage) JSONserializable.toObject(VersionUpdateUtil.dateLoad(marriageBean.getData()), Marriage.class);
					marriagemap.put(marriageBean.getId(), marriage);
					playermarriagemap.put(marriage.getSpouseslist().get(0).getPlayerid(), marriage);
					playermarriagemap.put(marriage.getSpouseslist().get(1).getPlayerid(), marriage);
				}
			}
			log.error("婚姻数据载入完毕");
		}catch (Exception e) {
			log.error(e,e);
		}
	}
	
	/**根据玩家ID获得婚姻
	 * 
	 * @param id
	 * @return
	 */
	public Marriage getPlayerMarriage(long id ){
		if(playermarriagemap.containsKey(id)){
			return playermarriagemap.get(id);
		}
		return null;
	}

	
	
	
	/**获取结婚信息
	 * 
	 * @param id
	 * @return
	 */
	public Marriage getMarriage(long id){
		if(marriagemap.containsKey(id)){
			return marriagemap.get(id);
		}
		return null;
	}
	


	public static HashMap<Long, Marriage> getMarriagemap() {
		return marriagemap;
	}




	public static void setMarriagemap(HashMap<Long, Marriage> marriagemap) {
		MarriageManager.marriagemap = marriagemap;
	}




	
	/**
	 * 删除
	 * @param msg
	 */
	public void stReqDeleteMarriageToWorldMessage(ReqDeleteMarriageToWorldMessage msg) {
		if (marriagemap.containsKey(msg.getMarriageid())) {
			Marriage marriage = marriagemap.remove(msg.getMarriageid());
			playermarriagemap.remove(marriage.getSpouseslist().get(0).getPlayerid());
			playermarriagemap.remove(marriage.getSpouseslist().get(1).getPlayerid());
		}
	}

	


	/**
	 * 更新
	 * @param msg
	 */
	public void stReqUpdatedMarriageToWorldMessage(ReqUpdatedMarriageToWorldMessage msg) {
		if (marriagemap.containsKey(msg.getMarriageid())) {
			Marriage marriage = marriagemap.get(msg.getMarriageid());
			marriage.getSpouseslist().get(0).setPlayerid(msg.getBridegroomid());
			marriage.getSpouseslist().get(1).setPlayerid(msg.getBrideid());
			marriage.getSpouseslist().get(0).setName(msg.getBridegroom());
			marriage.getSpouseslist().get(1).setName(msg.getBride());
			playermarriagemap.put(marriage.getSpouseslist().get(0).getPlayerid(), marriage);
			playermarriagemap.put(marriage.getSpouseslist().get(1).getPlayerid(), marriage);
		}else {
			Marriage marriage = new Marriage();
			Spouse aSpouse = new Spouse();
			Spouse bSpouse = new Spouse();
			aSpouse.setPlayerid(msg.getBridegroomid());
			aSpouse.setName(msg.getBridegroom());
			bSpouse.setPlayerid(msg.getBrideid());
			bSpouse.setName(msg.getBride());
			marriage.getSpouseslist().add(aSpouse);
			marriage.getSpouseslist().add(bSpouse);
			marriage.setId(msg.getMarriageid());
			marriagemap.put(msg.getMarriageid(), marriage);
			playermarriagemap.put(msg.getBridegroomid(), marriage);
			playermarriagemap.put(msg.getBrideid(), marriage);
		}
	}

	public static HashMap<Long, Marriage> getPlayermarriagemap() {
		return playermarriagemap;
	}

	public static void setPlayermarriagemap(
			HashMap<Long, Marriage> playermarriagemap) {
		MarriageManager.playermarriagemap = playermarriagemap;
	}
	
	
	
	
	
	
	
	
	
}
