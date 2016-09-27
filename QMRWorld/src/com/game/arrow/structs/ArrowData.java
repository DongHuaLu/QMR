package com.game.arrow.structs;

import com.game.arrow.bean.ArrowInfo;
import com.game.object.GameObject;
import com.game.skill.structs.Skill;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * 弓箭数据
 *
 * @author 杨鸿岚
 */
public class ArrowData extends GameObject{
	
	private static final long serialVersionUID = 6697307896119123345L;

	private int arrowlv;							//弓箭等阶
	private ArrowStarData starData;						//星斗数据
	private ArrowBowData bowData;						//箭支数据
	private HashMap<String, FightSpiritData> fightSpiritDataMap;		//战魂数据
	private transient List<Skill> skilllist;				//技能数据
	

	public ArrowData() {
		starData = new ArrowStarData();
		bowData = new ArrowBowData();
		fightSpiritDataMap = new HashMap<String, FightSpiritData>();
		skilllist = new ArrayList<Skill>();
		starData.setStarmainlv(1);
		starData.setStarsublv(0);
		bowData.setBowmainlv(1);
		bowData.setBowsublv(0);
	}

	public int getArrowlv() {
		return arrowlv;
	}

	public void setArrowlv(int arrowlv) {
		this.arrowlv = arrowlv;
	}

	public ArrowBowData getBowData() {
		return bowData;
	}

	public void setBowData(ArrowBowData bowData) {
		this.bowData = bowData;
	}

	public ArrowStarData getStarData() {
		return starData;
	}

	public void setStarData(ArrowStarData starData) {
		this.starData = starData;
	}

	public HashMap<String, FightSpiritData> getFightSpiritDataMap() {
		return fightSpiritDataMap;
	}

	public void setFightSpiritDataMap(HashMap<String, FightSpiritData> fightSpiritDataMap) {
		this.fightSpiritDataMap = fightSpiritDataMap;
	}

	public List<Skill> getSkilllist() {
		return skilllist;
	}

	public void setSkilllist(List<Skill> skilllist) {
		this.skilllist = skilllist;
	}
	
	public ArrowInfo toInfo(){
		ArrowInfo arrowInfo = new ArrowInfo();
		arrowInfo.setArrowlv(getArrowlv());
		arrowInfo.setStarinfo(getStarData().toInfo());
		arrowInfo.setBowinfo(getBowData().toInfo());
		Iterator<FightSpiritData> iterator = getFightSpiritDataMap().values().iterator();
		while(iterator.hasNext()) {
			FightSpiritData fightSpiritData =  iterator.next();
			arrowInfo.getFightspiritList().add(fightSpiritData.toInfo());
		}
		return arrowInfo;
	}
}
