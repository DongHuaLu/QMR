package com.game.spirittree.structs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.game.manager.ManagerPool;
import com.game.object.GameObject;
import com.game.player.structs.PlayerWorldInfo;
import com.game.spirittree.bean.FruitInfo;
import com.game.spirittree.bean.FruitRewardinfo;

public class Fruit extends GameObject{

	private static final long serialVersionUID = -8950780624616794443L;
	
	//果实类型,0普通果实,1银色,2金色奇异果
	private byte type;	
	//果实成熟时间
	private int ripeningtime ;	
	//是否干旱，，0否，1是
	private byte isarid;	
	//上次干旱时间（决定是否进入干旱随机）
	private int aridtime;
	//组包ID列表
	private List<Integer> groupidlist = new ArrayList<Integer>();		
	//果实奖励列表
	private List<FruitReward> fruitrewardlist = new ArrayList<FruitReward>();
	// 偷窃者/偷窃数量百分比
	private HashMap<String ,Integer> stealer = new HashMap<String ,Integer>();		
	//剩余产量
	private int yield;
	//果实主人ID
	private long fruithostid;
	
	
	
	/**得到果实信息
	 * 
	 * @return
	 */
	public FruitInfo makeinfo(){
		return makeinfo(0);
	}

	/**得到果实信息
	 * 
	 * @return
	 */
	public FruitInfo makeinfo(int type){
		FruitInfo info = new FruitInfo();
		if (type == 0) {
			ManagerPool.spiritTreeManager.aridevent(this);//随机干旱
		}
		info.setFruitid(getId());
		info.setGroupidlist(getGroupidlist());
		long s =System.currentTimeMillis()/1000;
		info.setTimeleft( getRipeningtime() - (int)s);
		info.setType(getType());
		info.setYield(getYield());
		if (info.getTimeleft() < 0) {	//小于0表示成熟
			for (FruitReward reward : this.getFruitrewardlist()) {
				FruitRewardinfo rewardinfo = reward.makeinfo();
				info.getFruitrewardinfo().add(rewardinfo);
				this.setIsarid((byte) 0);//成熟后清除干旱事件
			}	
		}else {
			if (getType() == 1 || getType() == 2) {	//奇异果未成熟也显示奖励
				for (FruitReward reward : this.getFruitrewardlist()) {
					FruitRewardinfo rewardinfo = reward.makeinfo();
					info.getFruitrewardinfo().add(rewardinfo);
				}	
			}
			info.setIsarid(getIsarid());
		}
		info.setHostid(getFruithostid());
		//得到玩家名字
		info.setHostname(ManagerPool.playerManager.getPlayerName(getFruithostid()));
		return info;
	}
	
	
	/**获取果实主人名字
	 * 
	 * @return
	 */
	public String obtainPlayeNname(){
		PlayerWorldInfo worldInfo = ManagerPool.playerManager.getPlayerWorldInfo(getFruithostid());
		if (worldInfo != null) {
			return worldInfo.getName();
		}
		return "未知";
	}
	
	
	
	/**果实类型0普通果实,1银色,2金色奇异果
	 * 
	 * @return
	 */
	public byte getType() {
		return type;
	}
	/**果实类型0普通果实,1银色,2金色奇异果
	 * 
	 * @return
	 */
	public void setType(byte type) {
		this.type = type;
	}
	
	/**果实成熟时间
	 * 
	 * @return
	 */
	public int getRipeningtime() {
		return ripeningtime;
	}
	/**果实成熟时间
	 * 
	 * @return
	 */
	public void setRipeningtime(int ripeningtime) {
		this.ripeningtime = ripeningtime;
	}
	
	/**是否干旱，，0否，1是
	 * 
	 * @return
	 */
	public byte getIsarid() {
		return isarid;
	}
	/**是否干旱，，0否，1是
	 * 
	 * @return
	 */
	public void setIsarid(byte isarid) {
		this.isarid = isarid;
	}

	

	/**
	 * 上次干旱时间（决定是否进入干旱随机）
	 * @return
	 */
	public int getAridtime() {
		return aridtime;
	}
	/**
	 * 上次干旱时间（决定是否进入干旱随机）
	 * @return
	 */
	public void setAridtime(int aridtime) {
		this.aridtime = aridtime;
	}
	
	/**组包ID列表
	 * 
	 * @return
	 */
	public List<Integer> getGroupidlist() {
		return groupidlist;
	}
	/**组包ID列表
	 * 
	 * @return
	 */
	public void setGroupidlist(List<Integer> groupidlist) {
		this.groupidlist = groupidlist;
	}
	
	/**偷窃者/偷窃数量百分比
	 * 
	 * @return
	 */
	public HashMap<String ,Integer> getStealer() {
		return stealer;
	}
	/**偷窃者/偷窃数量百分比
	 * 
	 * @return
	 */
	public void setStealer(HashMap<String ,Integer> stealer) {
		this.stealer = stealer;
	}
	
	/**剩余产量
	 * 
	 * @return
	 */
	public int getYield() {
		return yield;
	}
	/**剩余产量
	 * 
	 * @return
	 */
	public void setYield(int yield) {
		this.yield = yield;
	}
	
	
	/** 果实主人ID
	 * 
	 * @return
	 */
	public long getFruithostid() {
		return fruithostid;
	}
	/** 果实主人ID
	 * 
	 * @return
	 */
	public void setFruithostid(long fruithostid) {
		this.fruithostid = fruithostid;
	}
	
	
	/**果实奖励列表
	 * 
	 * @return
	 */
	public List<FruitReward> getFruitrewardlist() {
		return fruitrewardlist;
	}
	
	/**果实奖励列表
	 * 
	 * @param fruitrewardlist
	 */
	public void setFruitrewardlist(List<FruitReward> fruitrewardlist) {
		this.fruitrewardlist = fruitrewardlist;
	}	
	
	
	
	
}
