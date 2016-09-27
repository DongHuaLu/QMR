package com.game.horse.struts;

import java.util.ArrayList;
import java.util.List;

import com.game.backpack.structs.HorseEquip;
import com.game.object.GameObject;

public class Horse  extends GameObject{
	private static final long serialVersionUID = 6051175944342842925L;
	
	//坐骑最高阶层  ,0默认没有坐骑，
	private short layer;
	
	//坐骑技能等级总和
	private int skilllevelsum ;
	
	//坐骑最高阶的当前等级
	private int horselevel;
	
	//坐骑升阶时间
	private long horseUpTime;
	
	//坐骑技能列表
	private List<HorseSkill> skills = new ArrayList<HorseSkill>();
	//坐骑装备
	private HorseEquip[] horseequips = new HorseEquip[4];

	public short getLayer() {
		return layer;
	}

	public void setLayer(short layer) {
		this.layer = layer;
	}

	public int getSkilllevelsum() {
		return skilllevelsum;
	}

	public void setSkilllevelsum(int skilllevelsum) {
		this.skilllevelsum = skilllevelsum;
	}

	public int getHorselevel() {
		return horselevel;
	}

	public void setHorselevel(int horselevel) {
		this.horselevel = horselevel;
	}

	public long getHorseUpTime() {
		return horseUpTime;
	}

	public void setHorseUpTime(long horseUpTime) {
		this.horseUpTime = horseUpTime;
	}

	public List<HorseSkill> getSkills() {
		return skills;
	}

	public void setSkills(List<HorseSkill> skills) {
		this.skills = skills;
	}

	public HorseEquip[] getHorseequips() {
		return horseequips;
	}

	public void setHorseequips(HorseEquip[] horseequips) {
		this.horseequips = horseequips;
	}
	
}
