package com.game.pet.struts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.buff.structs.AttributeBuff;
import com.game.buff.structs.Buff;
import com.game.config.Config;
import com.game.data.bean.Q_petattributeBean;
import com.game.data.bean.Q_petinfoBean;
import com.game.data.manager.DataManager;
import com.game.fight.structs.Fighter;
import com.game.map.structs.IMapObject;
import com.game.map.structs.Jump;
import com.game.player.structs.Person;
import com.game.player.structs.Player;
import com.game.skill.structs.Skill;
import com.game.structs.Position;

public class Pet extends Person implements IMapObject, Fighter{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Pet.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//攻击对象列表
	private transient List<Fighter> attackTargets = new ArrayList<Fighter>();
	//攻击对象
	private transient Fighter attTarget;
	
//	攻击对象的类型  
	private transient int targetType;
	
	//宠物状态
	private transient PetState state=PetState.UNSHOW;
	
	private transient PetJumpState jumpState=PetJumpState.NOMAL;
	
	private transient PetRunState runState=PetRunState.RUN;
	
	private transient Jump jump=new Jump();
	
	private transient Position dest;
	//默认单攻技能
	private Skill defaultSingleSkill=new Skill();
	//默认群攻技能
	private Skill defaultMutileSkill=new Skill();
	
	//移动耗时时间
	protected transient int cost = 0;
	//移动上一步时间
	protected transient long prevStep = 0;
	//上一次寻路的时间 
	private transient long lastFindRoadsTime=0;
	//最后一次攻击或被攻击时间 用于计算战斗状态
	private transient long lastFightTime=0;
	//上一次回血时间
	private transient long lastRecoveryTime=0;
	//是否强制收回的
	private transient boolean isForceHide=false;
	//标志列表
	private HashSet<String> tagSet=new HashSet<String>();
	
	//是否显示 
	private boolean show=false;

	//死亡时间 
	private long dieTime=0l;
	
	private long ownerId;
	//合体次数
	private int htcount=0;
	//出战次数
	private int showCount=0;
	//出战时间
	private long showTime=0l;
	
	private int serverId;
	
	//装备列表
//	private Equip[] equips = new Equip[12];
	
	//天赋技能排在第一个 不能动
	private Skill[] skills=new Skill[8];
	
	private int htaddhp;

	// 合体增加内力
	private int htaddmp;

	// 合体增加攻击
	private int htaddattack;

	// 合体增加防御
	private int htadddefence;

	// 合体增加暴击
	private int htaddcrit;

	// 合体增加闪避
	private int htadddodge;
	
	public Pet(Player player,int modelid){
		setModelId(modelid);
		setId(Config.getId());
		
		ownerId=player.getId();
		show=false;
		state=PetState.IDEL;
		Q_petinfoBean model = DataManager.getInstance().q_petinfoContainer.getMap().get(modelid);
		setLevel(player.getLevel()>model.getQ_pet_maxlevel()?model.getQ_pet_maxlevel():player.getLevel());
//		setLevel(player.getLevel()>model.getQ_level()?model.getQ_level():player.getLevel());
		if(model!=null){
			int q_default_skillId = model.getQ_skill();//TODO 美人技能处理
			Skill skill = new Skill();
			skill.setId(Config.getId());
			skill.setSkillLevel(1);
			skill.setSkillModelId(q_default_skillId);
			skills[0]=skill;
		}
		if(model.getQ_skill_single()!=0){
			defaultSingleSkill.setSkillModelId(model.getQ_skill_single());
			defaultSingleSkill.setSkillLevel(1);
		}
		if(model.getQ_skill_multi()!=0){
			defaultMutileSkill.setSkillModelId(model.getQ_skill_multi());
			defaultMutileSkill.setSkillLevel(1);
		}
	}
	
	public Pet(){
	}
	
	public boolean isShow() {
		return show;
	}

	public PetJumpState getJumpState() {
		return jumpState;
	}
	public void setJumpState(PetJumpState jumpState) {
		this.jumpState = jumpState;
	}
	public void setShow(boolean show) {
		this.show = show;
	}

	public PetState getState() {
		return state;
	}

	public void changeStateTo(PetState state) {
		PetState before=this.state;
		this.state = state;
		logger.debug("宠物"+getId()+"状态从"+before+"切换到"+state);
	}
	
	/**
	 * 主人ID
	 * @return
	 */
	public long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}
	public long getLastFindRoadsTime() {
		return lastFindRoadsTime;
	}
	
	public void setLastFindRoadsTime(long time){
		this.lastFindRoadsTime=time;
	}
	
	public Skill[] getSkills() {
		return skills;
	}
	public void setSkills(Skill[] skills) {
		this.skills = skills;
	}
	/**
	 * 合体次数
	 * @return
	 */
	public int getHtcount() {
		return htcount;
	}
	public void setHtcount(int htcount) {
		this.htcount = htcount;
	}
	public int getSpeed() {

		int baseSpeed = 160;
		//Buff加成
		int bufValue = 0;
		int bufPercent = 0;
		int totalPercent = 0;
		
		for (int i = 0; i < this.getBuffs().size(); i++) {
			Buff buff = this.getBuffs().get(i);
			if(buff instanceof AttributeBuff){
				AttributeBuff aBuff = (AttributeBuff)buff;
				bufValue += aBuff.getSpeed();
				bufPercent += aBuff.getSpeedPercent();
				totalPercent += aBuff.getTotalSpeedPercent();
			}
		}
		
		baseSpeed = (baseSpeed + bufValue) * (10000 + bufPercent) / 10000;
		
//		//计算装备加值
//		int equipValue = 0;
//		for (int i = 0; i < this.getEquips().length; i++) {
//			Equip equip = this.getEquips()[i];
//			if(equip==null) continue;
//			equipValue += equip.getSpeed();
//		}
//		baseSpeed += equipValue;
		
		//总体buff加成
		baseSpeed = baseSpeed * (10000 + totalPercent) / 10000;
				
		return baseSpeed;
	}
	
	
//	public Equip[] getEquips() {
//		return equips;
//	}
//	public void setEquips(Equip[] equips) {
//		this.equips = equips;
//	}
	
	@Override
	public int getMaxHp() {
		// 宠物HP
		Q_petattributeBean model = DataManager.getInstance().q_petattributeContainer.getMap().get(getModelId()+"_"+getLevel());
		if(model==null){
			//logger.error("找不到的出战属性模型"+getModelId()+"_"+getLevel());
			return 10;
		}
		return model.getQ_maxhp();
	}

	@Override
	public int getMaxMp() {
		// 宠物HP
		return 0; 
	}
	
	@Override
	public int getMaxSp() {
		//座骑SP
		return 0;
	}
	
	@Override
	public int getAttack() {
		Q_petattributeBean model = DataManager.getInstance().q_petattributeContainer.getMap().get(getModelId()+"_"+getLevel());
		if(model==null){
			//logger.error("找不到的出战属性模型"+getModelId()+"_"+getLevel());
			return 10;
		}
		return model.getQ_attack();
	}
	
	@Override
	public int getAttack(Skill skill) {
		Q_petattributeBean model = DataManager.getInstance().q_petattributeContainer.getMap().get(getModelId()+"_"+getLevel());
		if(model!=null&&model.getQ_attack()>0){
			return model.getQ_attack();
		}
		return 0;
	}
	
	@Override
	public int getDefense() {
		return 0;
	}
	
	@Override
	public int getAttackSpeed() {
		Q_petattributeBean model = DataManager.getInstance().q_petattributeContainer.getMap().get(getModelId()+"_"+getLevel());
		if(model==null){
			//logger.error("找不到的出战属性模型"+getModelId()+"_"+getLevel());
			return 10;
		}
		return model.getQ_attack_speed();
	}
	
	@Override
	public int getLuck() {
		return 0;
	}
	
	@Override
	public int getCrit() {
		Q_petattributeBean model = DataManager.getInstance().q_petattributeContainer.getMap().get(getModelId()+"_"+getLevel());
		if(model==null){
			//logger.error("找不到的出战属性模型"+getModelId()+"_"+getLevel());
			return 10;
		}
		return model.getQ_crit();
	}
	
	@Override
	public int getDodge() {
		Q_petattributeBean model = DataManager.getInstance().q_petattributeContainer.getMap().get(getModelId()+"_"+getLevel());
		if(model==null){
			//logger.error("找不到的出战属性模型"+getModelId()+"_"+getLevel());
			return 10;
		}
		return model.getQ_dodge();
	}

	public int getModelId() {
		return modelId;
	}
	public void setModelId(int modelId) {
		this.modelId = modelId;
	}
	
	public int getHtaddhp() {
		return htaddhp;
	}

	public void setHtaddhp(int htaddhp) {
		this.htaddhp = htaddhp;
	}

	public int getHtaddmp() {
		return htaddmp;
	}

	public void setHtaddmp(int htaddmp) {
		this.htaddmp = htaddmp;
	}

	public int getHtaddattack() {
		return htaddattack;
	}

	public void setHtaddattack(int htaddattack) {
		this.htaddattack = htaddattack;
	}

	public int getHtadddefence() {
		return htadddefence;
	}

	public void setHtadddefence(int htadddefence) {
		this.htadddefence = htadddefence;
	}

	public int getHtaddcrit() {
		return htaddcrit;
	}

	public void setHtaddcrit(int htaddcrit) {
		this.htaddcrit = htaddcrit;
	}

	public int getHtadddodge() {
		return htadddodge;
	}

	public void setHtadddodge(int htadddodge) {
		this.htadddodge = htadddodge;
	}
	@Override
	public boolean isDie() {
		return getState().getValue()==PetState.DIE.getValue();
	}
	
	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	@Override
	public int getServerId() {
		return this.serverId;
	}
	
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public long getPrevStep() {
		return prevStep;
	}
	public void setPrevStep(long prevStep) {
		this.prevStep = prevStep;
	}
	public List<Fighter> getAttackTargets() {
		return attackTargets;
	}
	public void setAttackTargets(List<Fighter> attackTargets) {
		this.attackTargets = attackTargets;
	}
	
	public Skill getDefaultSingleSkill() {
		Q_petinfoBean model = DataManager.getInstance().q_petinfoContainer.getMap().get(this.getModelId());
		if(model.getQ_skill_single()!=0){
			defaultSingleSkill.setSkillModelId(model.getQ_skill_single());
			defaultSingleSkill.setSkillLevel(1);
		}
		return defaultSingleSkill;
	}

	public void setDefaultSingleSkill(Skill defaultSingleSkill) {
		this.defaultSingleSkill = defaultSingleSkill;
	}

	public Skill getDefaultMutileSkill() {
		Q_petinfoBean model = DataManager.getInstance().q_petinfoContainer.getMap().get(this.getModelId());
		if(model.getQ_skill_multi()!=0){
			defaultMutileSkill.setSkillModelId(model.getQ_skill_multi());
			defaultMutileSkill.setSkillLevel(1);
		}
		return defaultMutileSkill;
	}

	public void setDefaultMutileSkill(Skill defaultMutileSkill) {
		this.defaultMutileSkill = defaultMutileSkill;
	}

	public long getDieTime() {
		return dieTime;
	}
	public void setDieTime(long dieTime) {
		this.dieTime = dieTime;
	}

	
	public long getLastFightTime() {
		return lastFightTime;
	}
	public void setLastFightTime(long lastFightTime) {
		this.lastFightTime = lastFightTime;
	}
	
	public long getLastRecoveryTime() {
		return lastRecoveryTime;
	}
	public void setLastRecoveryTime(long lastRecoveryTime) {
		this.lastRecoveryTime = lastRecoveryTime;
	}
	public int getTargetType() {
		return targetType;
	}
	public void setTargetType(int targetType) {
		this.targetType = targetType;
	}
	
	
	public Jump getJump() {
		return jump;
	}
	public void setJump(Jump jump) {
		this.jump = jump;
	}
	
	
	public int getShowCount() {
		return showCount;
	}
	public void setShowCount(int showCount) {
		this.showCount = showCount;
	}
	public long getShowTime() {
		return showTime;
	}
	public void setShowTime(long showTime) {
		this.showTime = showTime;
	}
	
	public HashSet<String> getTagSet() {
		return tagSet;
	}
	public void setTagSet(HashSet<String> tagSet) {
		this.tagSet = tagSet;
	}
	public void resetPet() {
		// BUFF清除
		this.getBuffs().clear();
		// 清除目标
		this.attackTargets.clear();
		// 设置血量
		this.setHp((int) (this.getMaxHp()*.1));
		// 设置内力
		this.setMp(this.getMaxMp());
		// 设置体力
		this.setSp(this.getMaxSp());
		// 设置正常
		this.changeStateTo(PetState.IDEL);
		//死亡时间清0
		this.setDieTime(0);
		// 设置战斗状态正常
		this.setFightState(0);
		//攻击目标清除
		this.setTargetType(0);
	}
	
	@Override
	public boolean canSee(Player player) {
		return !this.isDie();
	}

	public Position getDest() {
		return dest;
	}

	public void setDest(Position dest) {
		this.dest = dest;
	}

	public Fighter getAttTarget() {
		return attTarget;
	}

	public void setAttTarget(Fighter attTarget) {
		this.attTarget = attTarget;
	}

	public PetRunState getRunState() {
		return runState;
	}

	public void setRunState(PetRunState runState) {
		this.runState = runState;
	}

	public boolean isForceHide() {
		return isForceHide;
	}

	public void setForceHide(boolean isForceHide) {
		this.isForceHide = isForceHide;
	}
}
