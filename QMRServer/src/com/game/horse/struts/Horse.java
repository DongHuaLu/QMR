package com.game.horse.struts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.game.backpack.structs.HorseEquip;
import com.game.cooldown.structs.CooldownTypes;
import com.game.data.bean.Q_horse_basicBean;
import com.game.data.bean.Q_horse_skillsBean;
import com.game.equip.bean.EquipInfo;
import com.game.equip.manager.EquipManager;
import com.game.horse.bean.HorseInfo;
import com.game.manager.ManagerPool;
import com.game.object.GameObject;
import com.game.player.structs.Player;
import com.game.utils.TimeUtil;

public class Horse  extends GameObject{
	private static final long serialVersionUID = 6051175944342842925L;
	
	//每天0点清除祝福值和升级次数
	private static final int CLEAR_HOUR= 0 ;	
	
	//时间保存 每天X点进1，发生变化说明已经到下个时间点
	private int timeday ;
	//是否可得到新坐骑 ： 0 未得到， 1 可领取，2已经得到
	private byte newhorse ;
	//坐骑最高阶层  ,0默认没有坐骑，
	private short layer;
	//当前骑乘阶数
	private short curlayer;
	//当前骑乘状态
	private byte status;
	
	//当前祝福值
	private int dayblessvalue ;
	//当前进阶次数
	private int dayupnum ;
	//历史祝福值
	private int hisblessvalue ;
	//历史进阶次数
	private int hisupnum ;
	
	//清除冷却CD次数
	private int clearcdnum ;
	//摇动宝盒次数（拉杆次数）
	private int boxnum;

	//今日所获的坐骑进阶经验
	private long dayexp;
	
	//坐骑技能列表
	private List<HorseSkill> skills = new ArrayList<HorseSkill>();
	//坐骑装备
	private HorseEquip[] horseequips = new HorseEquip[4];
	
	//坐骑技能等级总和
	private int skilllevelsum ;
	
	//坐骑最高阶的当前等级
	private int horselevel;
	
	//坐骑潜能（潜能丹使用数量）
	private int potential;
	
	
	//坐骑炼骨丹（使用数量）
	private int mixingbone;
	
	
	//坐骑升阶时间
	private long horseUpTime;
	/**产生当前坐骑的装备和技能等展示信息
	 * 
	 * @return
	 */
	public HorseInfo CreateHorseInfo(Player player){
		HorseInfo info = new HorseInfo();
		info.setLayer(getLayer());
		info.setStatus(getStatus());
		info.setDayblessvalue(getDayblessvalue());
		info.setPotential(getPotential());
		info.setMixingbone(getMixingbone());
		long cooldownTime = ManagerPool.cooldownManager.getCooldownTime(player, CooldownTypes.HORSE_SKILLUPCD, null);
		if (cooldownTime < 0) {
			cooldownTime = 0;
		}
		info.setBoxcdtime((int) (cooldownTime/1000));
		if (info.getBoxcdtime() > 0) {
			info.setBoxnum((byte) ManagerPool.horseManager.getHorseSkillUpNumMax());
			info.setCdtimeyuanbao(ManagerPool.horseManager.getClearCDYuanBao(player));
		}else {
			info.setBoxnum((byte) getBoxnum());
		}

		if(this.getCurlayer() == 0){
			info.setCurlayer(this.getLayer());
		}else {
			info.setCurlayer(this.getCurlayer());
		}

		for (int i = 0; i < this.horseequips.length; i++) {
			if(this.horseequips[i] != null ){
				EquipInfo equipInfo = EquipManager.getInstance().getEquipInfo(this.horseequips[i]);
				info.getHorseequipinfo().add(equipInfo);
			}
		}
		
		for (HorseSkill skill : skills) {
			info.getSkillinfolist().add(skill.createSkillInfo());
		}
		return info;
	}
	
	

	
	
	/**每天清理清除祝福值和冷却次数
	 * 
	 */
	public void ClearDay(){
		int xday = TimeUtil.GetCurDay(CLEAR_HOUR);
		if(getTimeday() != xday ){
			this.setTimeday(xday);
			this.setDayexp(0);
			if( getDayupnum() > getHisupnum()){
				this.setHisupnum(getDayupnum());
			}
			this.setDayupnum(0);
			if( getDayblessvalue() > getHisblessvalue()){
				this.setHisblessvalue(getDayblessvalue());
			}
			this.setDayblessvalue(0);
			this.setClearcdnum(0);
		}
	}
	
	
	/**
	 * 本阶最高可升级技能等级
	 * 
	 */
	public int skillMaxLevel(){
		Q_horse_basicBean horsedata = ManagerPool.horseManager.getDBHorseBasic(getLayer());
		return horsedata.getQ_skill_level_max();
	}
	
	
	/**加入技能（数据库改动的时候用）
	 * 
	 */
	public void putskill(){
		HashMap<Integer, Q_horse_skillsBean> skillModel = ManagerPool.dataManager.q_horse_skillsContainer.getMap();
		Iterator<Entry<Integer, Q_horse_skillsBean>> model = skillModel.entrySet().iterator();
		ArrayList<HorseSkill> dellist = new ArrayList<HorseSkill>();
		//先删除数据库内不存在的技能
		for (HorseSkill horseSkill : this.skills) {
			boolean is = false;
			while (model.hasNext()) {
				Entry<Integer, Q_horse_skillsBean> entry = model.next();
				if (horseSkill.getSkillModelId() == entry.getKey()) {
					is = true;
					break;
				}
			}
			if (is == false) {
				dellist.add(horseSkill);
			}
		}
		
		for (HorseSkill delSkill : dellist) {
			this.skills.remove(delSkill);
		}
		dellist=null;
		
		//再添加数据库的新技能
		while (model.hasNext()) {
			Entry<Integer, Q_horse_skillsBean> entry = model.next();
			if (CheckSkillIsExist(entry.getKey()) == false) {
				HorseSkill horseSkill = new HorseSkill();
				horseSkill.setSkillLevel(1);
				horseSkill.setSkillModelId(entry.getKey());
				this.skills.add(horseSkill);
			}
		}
	}
	
	
	
	/**检测技能是否在数据库内存在
	 * 
	 * @param id
	 * @return
	 */
	public boolean CheckSkillIsExist(int id){
		for (HorseSkill horseSkill : this.skills) {
			if (horseSkill.getSkillModelId() == id) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	/**技能表
	 * 
	 * @return
	 */
	public List<HorseSkill> getSkills() {
		return skills;
	}

	public void setSkills(List<HorseSkill> skills) {
		this.skills = skills;
	}
	
	/**坐骑身上装备
	 * 
	 * @return
	 */
	public HorseEquip[] getHorseequips() {
		return horseequips;
	}

	public void setHorseequips(HorseEquip[] horseequips) {
		this.horseequips = horseequips;
	}
	/**坐骑最高阶层  ,0默认没有坐骑
	 * 
	 * @return
	 */
	public short getLayer() {
		return layer;
	}
	/**坐骑最高阶层  ,0默认没有坐骑
	 * 
	 * @return
	 */
	public void setLayer(short layer) {
		this.layer = layer;
	}


	public int getTimeday() {
		return timeday;
	}

	public void setTimeday(int timeday) {
		this.timeday = timeday;
	}


	/**当前骑乘的阶数，
	 * 
	 * @return
	 */
	public short getCurlayer() {
		return curlayer;
	}
/**当前骑乘阶数
 * 
 * @param curlayer
 */
	public void setCurlayer(short curlayer) {
		this.curlayer = curlayer;
	}


/**当前骑乘状态
 * 
 * @return
 */
	public byte getStatus() {
		return status;
	}


	/**当前骑乘状态
	 * 
	 * @return
	 */
	public void setStatus(byte status) {
		this.status = status;
	}


	/**新手是否领取坐骑
	 * 
	 * @return
	 */
	public byte getNewhorse() {
		return newhorse;
	}


	/**新手是否领取坐骑
	 * 
	 * @return
	 */

	public void setNewhorse(byte newhorse) {
		this.newhorse = newhorse;
	}


/**当前祝福值
 * 
 * @return
 */
	public int getDayblessvalue() {
		return dayblessvalue;
	}


	/**当前祝福值
	 * 
	 * @return
	 */
	public void setDayblessvalue(int dayblessvalue) {
		this.dayblessvalue = dayblessvalue;
	}


	/**当前进阶次数
	 * 
	 * @return
	 */
	public int getDayupnum() {
		return dayupnum;
	}


	/**当前进阶次数
	 * 
	 * @return
	 */
	public void setDayupnum(int dayupnum) {
		this.dayupnum = dayupnum;
	}


	/**历史祝福值
	 * 
	 * @return
	 */
	public int getHisblessvalue() {
		return hisblessvalue;
	}


	/**历史祝福值
	 * 
	 * @return
	 */
	public void setHisblessvalue(int hisblessvalue) {
		this.hisblessvalue = hisblessvalue;
	}

	/**历史进阶次数
	 * 
	 * @return
	 */

	public int getHisupnum() {
		return hisupnum;
	}

	/**历史进阶次数
	 * 
	 * @return
	 *
	 */

	public void setHisupnum(int hisupnum) {
		this.hisupnum = hisupnum;
	}


/**清除冷却CD次数
 * 
 * @return
 */
	public int getClearcdnum() {
		return clearcdnum;
	}


/**清除冷却CD次数
 * 
 * @return
 */

	public void setClearcdnum(int clearcdnum) {
		this.clearcdnum = clearcdnum;
	}


/**摇动宝盒次数（拉杆次数）
 * 
 * @return
 */
	public int getBoxnum() {
		return boxnum;
	}


/**摇动宝盒次数（拉杆次数）
 * 
 * @param boxnum
 */
	public void setBoxnum(int boxnum) {
		this.boxnum = boxnum;
	}


	/**今日所获的坐骑进阶经验
	 * 
	 * @return
	 */
	public long getDayexp() {
		return dayexp;
	}
	
	
	/**今日所获的坐骑进阶经验
	 * 
	 * @param dayexp
	 */
	public void setDayexp(long dayexp) {
		this.dayexp = dayexp;
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





	public int getPotential() {
		return potential;
	}





	public void setPotential(int potential) {
		this.potential = potential;
	}





	public int getMixingbone() {
		return mixingbone;
	}





	public void setMixingbone(int mixingbone) {
		this.mixingbone = mixingbone;
	}

	
}
