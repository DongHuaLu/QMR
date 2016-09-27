package com.game.pet.manager;

import java.util.ArrayList;
import java.util.List;

import com.game.cooldown.structs.CooldownTypes;
import com.game.count.manager.CountManager;
import com.game.count.structs.CountTypes;
import com.game.data.bean.Q_petinfoBean;
import com.game.data.manager.DataManager;
import com.game.manager.ManagerPool;
import com.game.pet.bean.PetAttribute;
import com.game.pet.bean.PetDetailInfo;
import com.game.pet.message.ResPetAttributeChangeMessage;
import com.game.pet.message.ResPetHeTiResultMessage;
import com.game.pet.message.ResPetListMessage;
import com.game.pet.struts.Pet;
import com.game.player.bean.PlayerAttributeItem;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.skill.bean.SkillInfo;
import com.game.skill.structs.Skill;
import com.game.structs.Attributes;
import com.game.utils.MessageUtil;

/**
 * 宠物信息管理器
 * @author 赵聪慧
 * @2012-8-30 下午1:27:48
 */
public class PetInfoManager {
	private static PetInfoManager  instance=new PetInfoManager ();
	public static PetInfoManager  getInstance(){
		return instance;
	}
	private PetInfoManager() {
		
	}
	public String getKey(Player player,int petModel){
		return petModel+"_"+player.getLevel();
	}
	public boolean isFullHp(Pet pet){
		return pet.getHp()>=pet.getMaxHp();
	}
	
	
	/**
	 * 获取当前出战宠物
	 * @param player
	 * @return
	 */
	public Pet getShowPet(Player player){
		for (Pet pet : player.getPetList()) {
			if(pet.isShow()){
				return pet;
			}
		}
		return null;
	}
	
	/**
	 * 按ID查找宠物
	 * @param player
	 * @param petId
	 * @return
	 */
	public Pet getPet(Player player, long petId){
		for (Pet pet:player.getPetList()) {
			if(pet.getId()==petId){
				return pet;
			}
		}
		return null;
	}
	public Pet getPetByModelId(Player player,int modelId){
		List<Pet> petList = player.getPetList();
		for (Pet pet : petList) {
			if(pet.getModelId()==modelId){
				return pet;
			}
		}
		return null;
	}
	
//	/** 多美人出战属于 后期需求  暂时不考虑
//	 * 获取玩家出战的宠物列表
//	 * @param player
//	 * @return
//	 */
//	public List<Pet> getShowPets(Player player){
//		ArrayList<Pet> list=new ArrayList<Pet>();
//		List<Pet> petList = player.getPetList();
//		if(petList!=null&&petList.size()>0){
//			for (Pet pet : petList) {
//				if(pet.isShow()){
//					list.add(pet);
//				}
//			}
//		}
//		return list;
//	}
	
	public PetDetailInfo getDetailInfo(Pet pet){
		PetDetailInfo info = new PetDetailInfo();
		if(pet.getDieTime()!=0&&pet.isDie()){
			Q_petinfoBean model = DataManager.getInstance().q_petinfoContainer.getMap().get(pet.getModelId());
			int revive_time = model.getQ_revive_time();
			
			
			info.setDieTime((int) ((revive_time-(System.currentTimeMillis()-pet.getDieTime()))/1000));	
//			System.out.println("复活时间"+info.getDieTime());
		}else{
			pet.setDieTime(0);
		}
		info.setHp(pet.getHp());
		info.setMp(pet.getMp());
		info.setSp(pet.getSp());
		info.setMaxHp(pet.getMaxHp());
		info.setMaxMp(pet.getMaxMp());
		info.setMaxSp(pet.getMaxSp());
		info.setSpeed(pet.getSpeed());
		info.setHtCount(pet.getHtcount());
		info.setDayCount((int) getPetHtDayCount(pet));
		if(pet.getHtcount()>0){
			info.setHtAddition(getHtAddition(pet));
		}
		info.setLevel(pet.getLevel());
		info.setPetId(pet.getId());
		info.setPetModelId(pet.getModelId());
		info.setShowState((byte) (pet.isShow()?1:0));
		for (Skill skill : pet.getSkills()) {
			if(skill!=null)
			info.getSkillInfos().add(getSkillInfo(skill));
		}
		return info;
	}

	private List<PlayerAttributeItem> getHtAddition(Pet pet){
		List<PlayerAttributeItem> list = new ArrayList<PlayerAttributeItem>();
		PlayerAttributeItem attribute = new PlayerAttributeItem();
		attribute.setType(Attributes.ATTACK.getValue());
		attribute.setValue(pet.getHtaddattack());
		list.add(attribute);
		attribute = new PlayerAttributeItem();
		attribute.setType(Attributes.DEFENSE.getValue());
		attribute.setValue(pet.getHtadddefence());
		list.add(attribute);
		attribute = new PlayerAttributeItem();
		attribute.setType(Attributes.CRIT.getValue());
		attribute.setValue(pet.getHtaddcrit());
		list.add(attribute);
		attribute = new PlayerAttributeItem();
		attribute.setType(Attributes.DODGE.getValue());
		attribute.setValue(pet.getHtadddodge());
		list.add(attribute);
		attribute = new PlayerAttributeItem();
		attribute.setType(Attributes.MAXHP.getValue());
		attribute.setValue(pet.getHtaddhp());
		list.add(attribute);
		attribute = new PlayerAttributeItem();
		attribute.setType(Attributes.MAXMP.getValue());
		attribute.setValue(pet.getHtaddmp());
		list.add(attribute);
		return list;
	}
	
	public ResPetHeTiResultMessage getPetHeTiMsg(Pet pet,boolean isSuccess){
		Player player = PlayerManager.getInstance().getOnLinePlayer(pet.getOwnerId());
		long dayhtcount = CountManager.getInstance().getCount(player, CountTypes.PET_HT,pet.getModelId()+"");
		ResPetHeTiResultMessage msg=new ResPetHeTiResultMessage();
		msg.setDayCount((int) getPetHtDayCount(pet));
		msg.setHtCoolDownTime(getPetHtCoolDownTime(pet));
		msg.setHtCount(pet.getHtcount());
		msg.setDayCount((int) dayhtcount);
		msg.setIsSuccess((byte)(isSuccess?1:0));
		msg.setPetId(pet.getId());
		msg.setHtAddition(getHtAddition(pet));
		return msg;
	}
	
	/**
	 * 获取美人合体冷确秒数
	 * @param pet
	 * @return
	 */
	public int getPetHtCoolDownTime(Pet pet){
		long cooldownTime = ManagerPool.cooldownManager.getCooldownTime(pet, CooldownTypes.PET_HETI,String.valueOf(pet.getModelId()));
		return (int)(cooldownTime/1000);
	}
	/**
	 * 获取当日合体次数
	 * @param pet
	 * @return
	 */
	public long getPetHtDayCount(Pet pet){
		Player onLinePlayer = PlayerManager.getInstance().getOnLinePlayer(pet.getOwnerId());
		if(onLinePlayer==null){
			return 0;
		}
		long dayhtcount = CountManager.getInstance().getCount(onLinePlayer, CountTypes.PET_HT,pet.getModelId()+"");
		return dayhtcount;
	}
	
	
	/**
	 * 获取技能信息
	 * @param skill 技能
	 * @return
	 */
	public SkillInfo getSkillInfo(Skill skill){
		SkillInfo info = new SkillInfo();
		info.setSkillId(skill.getId());
		info.setSkillModelId(skill.getSkillModelId());
		info.setSkillLevel(skill.getSkillLevel());
//		info.setLongyuanLevel(LongYuanManager.getInstance().getLongYuanSkillLevel(player, info.getSkillModelId()));
		return info;
	}
	
	public void sendPetInfo(Player player){
		List<Pet> petList = player.getPetList();
		List<PetDetailInfo> petInfos=new ArrayList<PetDetailInfo>();
		for (Pet pet : petList) {
//			if(pet.isShow()&&pet.isDie()){
//				Q_petinfoBean model = DataManager.getInstance().q_petinfoContainer.getMap().get(pet.getModelId());
//				int revive_time = model.getQ_revive_time();
//				if(System.currentTimeMillis()-pet.getDieTime()>=revive_time){
//					pet.resetPet();	
//				}	
//			}
			petInfos.add(getDetailInfo(pet));
		}
		ResPetListMessage resPetListMessage=new ResPetListMessage();
		resPetListMessage.setPets(petInfos);
		MessageUtil.tell_player_message(player, resPetListMessage);
	}
	
	public void onHpChange(Pet pet){
		ResPetAttributeChangeMessage msg=new ResPetAttributeChangeMessage();
		msg.setPetId(pet.getId());
		PetAttribute attribute=new PetAttribute();
		attribute.setType(Attributes.HP.getValue());
		attribute.setValue(pet.getHp());
		msg.setAttributeChange(attribute);
		MessageUtil.tell_round_message(pet, msg);
		PetScriptManager.getInstance().hpChange(pet);
	}
	
	public void onMpChange(Pet pet){
		ResPetAttributeChangeMessage msg=new ResPetAttributeChangeMessage();
		msg.setPetId(pet.getId());
		PetAttribute attribute=new PetAttribute();
		attribute.setType(Attributes.MP.getValue());
		attribute.setValue(pet.getMp());
		msg.setAttributeChange(attribute);
		MessageUtil.tell_round_message(pet, msg);
	}
	
	public void onSpChange(Pet pet){
		ResPetAttributeChangeMessage msg=new ResPetAttributeChangeMessage();
		msg.setPetId(pet.getId());
		PetAttribute attribute=new PetAttribute();
		attribute.setType(Attributes.SP.getValue());
		attribute.setValue(pet.getSp());
		msg.setAttributeChange(attribute);
		MessageUtil.tell_round_message(pet, msg);
	}
	
	public void onSpeedChange(Pet pet){
		ResPetAttributeChangeMessage msg=new ResPetAttributeChangeMessage();
		msg.setPetId(pet.getId());
		PetAttribute attribute=new PetAttribute();
		attribute.setType(Attributes.SPEED.getValue());
		attribute.setValue(pet.getSpeed());
		msg.setAttributeChange(attribute);
		MessageUtil.tell_round_message(pet, msg);
	}
	/**
	 * 美人血量变化
	 * @param pet
	 * @param recover
	 */
	public void addHp(Pet pet,int recover) {
		if(pet.getHp() == pet.getMaxHp()) return;
		int hp=pet.getHp()+recover;
		if(hp>=pet.getMaxHp()){
			pet.setHp(pet.getMaxHp());
		}else{
			pet.setHp(hp);
		}
		onHpChange(pet);
	}
	
	
	
	/**获取宠物主人
	 * 
	 * @param fighter
	 * @return
	 */
	public Player getPetHost(Pet pet){
		long attackplayerid = pet.getOwnerId();
		return  ManagerPool.playerManager.getPlayer(attackplayerid);
	}

}
