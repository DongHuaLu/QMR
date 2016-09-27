package com.game.friend.structs;

import com.alibaba.fastjson.JSON;
import com.game.db.bean.Friend;
import com.game.db.dao.FriendDao;
import com.game.friend.bean.RelationInfo;
import com.game.friend.manager.FriendManager;
import com.game.utils.VersionUpdateUtil;
import java.io.IOException;
import java.util.*;
import org.apache.log4j.Logger;

public class PlayerRelation {

	private Logger log = Logger.getLogger(PlayerRelation.class);
	private boolean boSave;		//是否已执行保存操作
	private int saveType;		//保存类型
	private long OwnPlayerId;	//所属玩家ID
	private HashMap<Long, RelationInfo> friendHashMap = new HashMap<Long, RelationInfo>();
	private HashMap<Long, RelationInfo> enemyHashMap = new HashMap<Long, RelationInfo>();
	private HashMap<Long, RelationInfo> recentcontactpersonHashMap = new HashMap<Long, RelationInfo>();
	private HashMap<Long, RelationInfo> blackHashMap = new HashMap<Long, RelationInfo>();
	private HashMap<Long, RelationInfo> attentionHashMap = new HashMap<Long, RelationInfo>();

	public boolean isBoSave() {
		return boSave;
	}

	public void setBoSave(boolean boSave) {
		this.boSave = boSave;
	}

	/**
	 * @return the saveType
	 */
	public int getSaveType() {
		return saveType;
	}

	/**
	 * @param saveType the saveType to set
	 */
	public void setSaveType(int saveType) {
		this.saveType = saveType;
	}

	/**
	 * @return the ownPlayerId
	 */
	public long getOwnPlayerId() {
		return OwnPlayerId;
	}

	/**
	 * @param ownPlayerId the ownPlayerId to set
	 */
	public void setOwnPlayerId(long ownPlayerId) {
		OwnPlayerId = ownPlayerId;
	}

	/**
	 * @return the friendHashMap
	 */
	public HashMap<Long, RelationInfo> getFriendHashMap() {
		return friendHashMap;
	}

	/**
	 * @return the enemyHashMap
	 */
	public HashMap<Long, RelationInfo> getEnemyHashMap() {
		return enemyHashMap;
	}

	/**
	 * @return the recentcontactpersonHashMap
	 */
	public HashMap<Long, RelationInfo> getRecentcontactpersonHashMap() {
		return recentcontactpersonHashMap;
	}

	/**
	 * @return the blackHashMap
	 */
	public HashMap<Long, RelationInfo> getBlackHashMap() {
		return blackHashMap;
	}

	/**
	 * @return the attentionHashMap
	 */
	public HashMap<Long, RelationInfo> getAttentionHashMap() {
		return attentionHashMap;
	}

	public RelationInfo sortMapByListToDelete(HashMap<Long, RelationInfo> SortHashMap, boolean deleteType) {
		//deletetype true 删掉末尾 false 删掉头
		List<RelationInfo> toList = new ArrayList<RelationInfo>();
		for (Map.Entry<Long, RelationInfo> entry : SortHashMap.entrySet()) {
			RelationInfo relationInfo = entry.getValue();
			toList.add(relationInfo);
		}

		Collections.sort(toList, new Comparator<RelationInfo>() {

			@Override
			public int compare(RelationInfo o1, RelationInfo o2) {
				return o2.getNAddTime() - o1.getNAddTime();
			}
		});
		if (deleteType) {
			return SortHashMap.remove(toList.get(toList.size() - 1).getLgUserId());
		} else {
			return SortHashMap.remove(toList.get(0).getLgUserId());
		}
	}

	public List<Long> getListWithMap(HashMap<Long, RelationInfo> withMap) {
		List<Long> toLongs = new ArrayList<Long>();
		for (Map.Entry<Long, RelationInfo> entry : withMap.entrySet()) {
			RelationInfo relationInfo = entry.getValue();
			if (relationInfo != null) {
				toLongs.add(relationInfo.getLgUserId());
			}
		}
		return toLongs;
	}

	public void saveAllInfo(Friend friend) {
		friend.setRoleid(getOwnPlayerId());
		friend.setFriendlist("");
		for (long friendid : friendHashMap.keySet()) {
			RelationInfo relationInfo = friendHashMap.get(friendid);
			if (relationInfo != null) {
				friend.setFriendlist(friend.getFriendlist() + relationInfo.toString() + "|");
			}
		}
		friend.setEnemylist("");
		for (long enemyid : enemyHashMap.keySet()) {
			RelationInfo relationInfo = enemyHashMap.get(enemyid);
			if (relationInfo != null) {
				friend.setEnemylist(friend.getEnemylist() + relationInfo.toString() + "|");
			}
		}
		friend.setRecentcontactpersonlist("");
//		for (long recentcontactpersonid : recentcontactpersonHashMap.keySet()) {
//			RelationInfo relationInfo = recentcontactpersonHashMap.get(recentcontactpersonid);
//			if (relationInfo != null) {
//				friend.setRecentcontactpersonlist(friend.getRecentcontactpersonlist() + relationInfo.toString() + "|");
//			}
//		}
		friend.setBlacklist("");
		for (long blackid : blackHashMap.keySet()) {
			RelationInfo relationInfo = blackHashMap.get(blackid);
			if (relationInfo != null) {
				friend.setBlacklist(friend.getBlacklist() + relationInfo.toString() + "|");
			}
		}
		friend.setAttentionlist("");
//		for (long attentionid : attentionHashMap.keySet()) {
//			RelationInfo relationInfo = attentionHashMap.get(attentionid);
//			if (relationInfo != null) {
//				friend.setAttentionlist(friend.getAttentionlist() + relationInfo.toString() + "|");
//			}
//		}
		friend.setFriendlist(VersionUpdateUtil.dateSave(friend.getFriendlist(), 512));
		friend.setEnemylist(VersionUpdateUtil.dateSave(friend.getEnemylist(), 512));
		//friend.setRecentcontactpersonlist(VersionUpdateUtil.dateSave(friend.getRecentcontactpersonlist(), 512));
		friend.setBlacklist(VersionUpdateUtil.dateSave(friend.getBlacklist(), 512));
		//friend.setAttentionlist(VersionUpdateUtil.dateSave(friend.getAttentionlist(), 512));
	}

	private FriendDao dao = new FriendDao();
	
	public void loadAllInfo(Friend friend) {
		try {
			friend.setFriendlist(VersionUpdateUtil.dateLoad(friend.getFriendlist()));
			friend.setEnemylist(VersionUpdateUtil.dateLoad(friend.getEnemylist()));
			//friend.setRecentcontactpersonlist(VersionUpdateUtil.dateLoad(friend.getRecentcontactpersonlist()));
			friend.setBlacklist(VersionUpdateUtil.dateLoad(friend.getBlacklist()));
			//friend.setAttentionlist(VersionUpdateUtil.dateLoad(friend.getAttentionlist()));
		} catch (Exception ex) {
			log.error(ex, ex);
			log.error("好友解析出错："+JSON.toJSONString(friend));
			return;
		}
		boolean save = false;
		setOwnPlayerId(friend.getRoleid());
		if (!"".equals(friend.getFriendlist())) {
			if(friend.getFriendlist().indexOf("ζޓއއއ°恋爱|咱|没份♂")!=-1){
				friend.setFriendlist(friend.getFriendlist().replaceAll("ζޓއއއ°恋爱\\|咱\\|没份♂", ""));
				save = true;
			}
			String[] friendStrings = friend.getFriendlist().split("\\|");
			for (int i = 0; i < friendStrings.length; i++) {
				String infoString = friendStrings[i];
				RelationInfo relationInfo = new RelationInfo();
//				relationInfo.toStructs(infoString);
				toStructs(relationInfo, infoString);
				if (relationInfo.getLgUserId() != 0) {
					friendHashMap.put(relationInfo.getLgUserId(), relationInfo);
				}
			}
		}
		if (!"".equals(friend.getEnemylist())) {
			if(friend.getEnemylist().indexOf("ζޓއއއ°恋爱|咱|没份♂")!=-1){
				friend.setEnemylist(friend.getEnemylist().replaceAll("ζޓއއއ°恋爱\\|咱\\|没份♂", ""));
				save = true;
			}
			String[] enemyStrings = friend.getEnemylist().split("\\|");
			for (int i = 0; i < enemyStrings.length; i++) {
				String infoString = enemyStrings[i];
				RelationInfo relationInfo = new RelationInfo();
//				relationInfo.toStructs(infoString);
				toStructs(relationInfo, infoString);
				if (relationInfo.getLgUserId() != 0) {
					enemyHashMap.put(relationInfo.getLgUserId(), relationInfo);
				}
			}
		}
		if (!"".equals(friend.getRecentcontactpersonlist())) {
//			String[] recentcontactpersonStrings = friend.getRecentcontactpersonlist().split("\\|");
//			for (int i = 0; i < recentcontactpersonStrings.length; i++) {
//				String infoString = recentcontactpersonStrings[i];
//				RelationInfo relationInfo = new RelationInfo();
////				relationInfo.toStructs(infoString);
//				toStructs(relationInfo, infoString);
//				if (relationInfo.getLgUserId() != 0) {
//					recentcontactpersonHashMap.put(relationInfo.getLgUserId(), relationInfo);
//				}
//			}
		}
		if (!"".equals(friend.getBlacklist())) {
			if(friend.getBlacklist().indexOf("ζޓއއއ°恋爱|咱|没份♂")!=-1){
				friend.setBlacklist(friend.getBlacklist().replaceAll("ζޓއއއ°恋爱\\|咱\\|没份♂", ""));
				save = true;
			}
			String[] blackStrings = friend.getBlacklist().split("\\|");
			for (int i = 0; i < blackStrings.length; i++) {
				String infoString = blackStrings[i];
				RelationInfo relationInfo = new RelationInfo();
//				relationInfo.toStructs(infoString);
				toStructs(relationInfo, infoString);
				if (relationInfo.getLgUserId() != 0) {
					blackHashMap.put(relationInfo.getLgUserId(), relationInfo);
				}
			}
		}
		if (!"".equals(friend.getAttentionlist())) {
//			String[] attentionStrings = friend.getAttentionlist().split("\\|");
//			for (int i = 0; i < attentionStrings.length; i++) {
//				String infoString = attentionStrings[i];
//				RelationInfo relationInfo = new RelationInfo();
////				relationInfo.toStructs(infoString);
//				toStructs(relationInfo, infoString);
//				if (relationInfo.getLgUserId() != 0) {
//					attentionHashMap.put(relationInfo.getLgUserId(), relationInfo);
//				}
//			}
		}
		if(save){
			Friend friendsave = new Friend();
			this.saveAllInfo(friendsave);
			try{
				dao.update(friendsave);
			}catch (Exception e) {
				log.error(e, e);
			}
		}
	}

	public void toStructs(RelationInfo relationInfo, String infoString) {
		if ("".equals(infoString)) {
			return;
		}
		infoString = infoString.trim();
		try{
			infoString = infoString.substring(1, infoString.length() - 1);
		}catch (Exception e) {
			log.error(e, e);
		}
		String[] infoStrings = infoString.split(",");
		for (int i = 0; i < infoStrings.length; i++) {
			String paramString = infoStrings[i];
			int idx = paramString.indexOf(":");
			if (idx != -1) {
				if ("lgUserId".equals(paramString.substring(0, idx))) {
					relationInfo.setLgUserId(Long.valueOf(paramString.substring(idx + 1)));
				} else if ("szName".equals(paramString.substring(0, idx))) {
					relationInfo.setSzName(paramString.substring(idx + 1));
				} else if ("nPopularity".equals(paramString.substring(0, idx))) {
					relationInfo.setNPopularity(Integer.valueOf(paramString.substring(idx + 1)));
				} else if ("nLevel".equals(paramString.substring(0, idx))) {
					relationInfo.setNLevel(Integer.valueOf(paramString.substring(idx + 1)));
				} else if ("nIcon".equals(paramString.substring(0, idx))) {
					relationInfo.setNIcon(Integer.valueOf(paramString.substring(idx + 1)));
				} else if ("btSex".equals(paramString.substring(0, idx))) {
					relationInfo.setBtSex(Byte.valueOf(paramString.substring(idx + 1)));
				} else if ("btJob".equals(paramString.substring(0, idx))) {
					relationInfo.setBtJob(Byte.valueOf(paramString.substring(idx + 1)));
				} else if ("nMapId".equals(paramString.substring(0, idx))) {
					relationInfo.setNMapId(Integer.valueOf(paramString.substring(idx + 1)));
				} else if ("szMood".equals(paramString.substring(0, idx))) {
					relationInfo.setSzMood(paramString.substring(idx + 1));
				} else if ("nRelationDegree".equals(paramString.substring(0, idx))) {
					relationInfo.setNRelationDegree(Integer.valueOf(paramString.substring(idx + 1)));
				} else if ("nAddTime".equals(paramString.substring(0, idx))) {
					relationInfo.setNAddTime(Integer.valueOf(paramString.substring(idx + 1)));
				} else if ("btState".equals(paramString.substring(0, idx))) {
					relationInfo.setBtState(Byte.valueOf(paramString.substring(idx + 1)));
				} else if ("btListType".equals(paramString.substring(0, idx))) {
					relationInfo.setBtListType(Byte.valueOf(paramString.substring(idx + 1)));
				} else if ("btSortIdx".equals(paramString.substring(0, idx))) {
					relationInfo.setBtSortIdx(Byte.valueOf(paramString.substring(idx + 1)));
				}
			}
		}
	}
}
