package com.game.skill.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.cooldown.structs.CooldownTypes;
import com.game.data.bean.Q_buffBean;
import com.game.data.bean.Q_characterBean;
import com.game.data.bean.Q_itemBean;
import com.game.data.bean.Q_skill_modelBean;
import com.game.data.bean.Q_skill_realmBean;
import com.game.data.manager.DataManager;
import com.game.dblog.LogService;
import com.game.fight.manager.FightManager;
import com.game.fight.structs.Fighter;
import com.game.fight.structs.FighterState;
import com.game.fightpower.manager.FightPowerManager;
import com.game.hiddenweapon.manager.HiddenWeaponManager;
import com.game.hiddenweapon.message.ResHiddenWeaponSkillRestrictionMessage;
import com.game.hiddenweapon.structs.HiddenWeapon;
import com.game.horse.struts.HorseSkill;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.marriage.message.ResGetSpouseSkillToClientMessage;
import com.game.monster.structs.Monster;
import com.game.pet.struts.Pet;
import com.game.player.message.ReqSyncPlayerSkillMessage;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttribute;
import com.game.player.structs.AttributeChangeReason;
import com.game.prompt.structs.Notifys;
import com.game.script.structs.ScriptEnum;
import com.game.skill.bean.SkillInfo;
import com.game.skill.bean.SkillLevelInfo;
import com.game.skill.log.SkillLevelAction;
import com.game.skill.log.SkillLevelUpLog;
import com.game.skill.message.SkillAddMessage;
import com.game.skill.message.SkillChangeMessage;
import com.game.skill.message.SkillInfosMessage;
import com.game.skill.message.SkillLingWuResultMessage;
import com.game.skill.message.SkillRemoveMessage;
import com.game.skill.message.SkillStartLevelUpMessage;
import com.game.skill.script.ITriggerSkillScript;
import com.game.skill.structs.Skill;
import com.game.structs.Reasons;
import com.game.team.bean.TeamInfo;
import com.game.team.bean.TeamMemberInfo;
import com.game.utils.Global;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.Symbol;

public class SkillManager {

	private Logger log = Logger.getLogger(SkillManager.class);
	
	private static Object obj = new Object();

	//管理类实例
	private static SkillManager manager;
	
	/**
	 * 快速结束  每分钟需要的元宝
	 */
	private static final int ENDSECONDSNEEDYUANBAO=1;
	
	private SkillManager(){}
	
	public static SkillManager getInstance(){
		synchronized (obj) {
			if(manager == null){
				manager = new SkillManager();
			}
		}
		return manager;
	}
	
	/**
	 * @deprecated 所有技能自动学习  前端手动学习技能消息禁用 FIXME 增加技能请用addSkill方法
	 * 
	 * 学习技能
	 * @param roldId 玩家Id
	 * @param skillModelId 技能模板Id
	 * @param bookId 技能书Id
	 */
	public void study(Player player, int skillModelId, long bookId) {
		// 初学 默认取第一级
		Q_skill_modelBean skillModel = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skillModelId + "_" + 1);
		if (skillModel == null) {
			log.error("技能为空：" + skillModelId + "_" + 1);
			return;
		}
		if(skillModel.getQ_panel_type()==2){
			log.error("非法请求龙元心法技能不允许以此方式学习roleId"+player.getId());
			return;
		}
		if (player.getLevel() < skillModel.getQ_study_needgrade()) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，人物等级不足，无法学习"));
			return;
		}
		// 已拥有该技能
		if (isHaveSkill(player, skillModelId)) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您已经学习过{1}技能，无需重复学习"), skillModel.getQ_skillName());
			return;
		}
		long action=Config.getId();
		// 物品检查
		if (bookId != 0) {
			// 点击技能书学习
			Item book = ManagerPool.backpackManager.getItemById(player, bookId);
			if (book == null) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("找不到技能书"));
				return;
			} else {
				if(book.isTrade()){
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉 ,物品正在被交易中无法使用"));
					return;
				}
				Q_itemBean bookmodel = ManagerPool.dataManager.q_itemContainer.getMap().get(book.getItemModelId());
				if (skillModelId != bookmodel.getQ_skill()) {
					log.error("传入的参数不一至 技能书学习传入学习的技能为" + skillModelId + "物品上关联的技能为" + bookmodel.getQ_skill());
					return;
				}
				ManagerPool.backpackManager.removeItem(player, bookId, Reasons.SKILLSTUDY,action);
			}
		} else {
			if(skillModel.getQ_panel_type()==0){
				//没有技能书 又不在技能面板上的 不允许学习
				return;
			}
			// 点击面板学习
			if (skillModel.getQ_study_needbook() != 0) {
				Q_itemBean bookmodel = ManagerPool.dataManager.q_itemContainer.getMap().get(skillModel.getQ_study_needbook());
				if (bookmodel == null) {
					log.error("找不到的技能书ID" + skillModel.getQ_study_needbook());
					return;
				}
				Item book = ManagerPool.backpackManager.getFirstItemByModelId(player, skillModel.getQ_study_needbook());
				if (book != null) {
					BackpackManager.getInstance().removeItemByCellId(player, book.getGridId(),Reasons.SKILLSTUDY,action);
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您需要先拥有技能书《{1}》才能学习"),  BackpackManager.getInstance().getName(bookmodel.getQ_id()));
					return;
				}
			}
		}
		addSkill(player, skillModelId);
		
	}

	private void startUpLevel(Player player, Skill skill){
		player.setNowLearnSkillId(skill.getSkillModelId());
		player.setSkillLearnTime(0);
		MessageUtil.tell_player_message(player, getSkillStartLevelUpMessage(skill));
	}
	
	public void endUpLevel(Player player, Skill skill, int grade, boolean result) {
		int upLevel = grade - skill.getSkillLevel();
		player.setNowLearnSkillId(-1);
		player.setSkillLearnTime(-1);
		setLevel(player, skill, grade, result);
		player.setTotalSkillLevel(getSkillLevelSum(player));
		player.setSkillUpTime(System.currentTimeMillis());
		
		if (upLevel > 0) {
			ReqSyncPlayerSkillMessage msg = new ReqSyncPlayerSkillMessage();
			msg.setPlayerId(player.getId());
			msg.setSkill(skill.getSkillModelId());
			msg.setUpLevel(upLevel);
			msg.setSkillTime(player.getSkillUpTime());
			MessageUtil.send_to_world(msg);
			
			FightPowerManager.getInstance().Update(player);
		}
	}
	
	/**设定技能等级
	 * 
	 * @param player
	 * @param skill
	 * @param grade
	 * @param result
	 */
	public void setLevel(Player player,Skill skill,int grade,boolean result){
		skill.setSkillLevel(grade);
		MessageUtil.tell_player_message(player, getLingWuResultMessage(skill,result));	
	}
	
	
	/**技能增加等级（累加）
	 * 
	 */
	public void skilllevelup(Player player,Skill skill,int grade,boolean result){
		grade = skill.getSkillLevel() + grade;
		setLevel(player,skill,grade,result);
	}
	
	
	
	
	
	public boolean addSkill(Player player, int skillModelId) {
		// 初学 默认取第一级
		Q_skill_modelBean skillModel = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skillModelId + "_" + 1);
		if (skillModel == null) {
			log.error("技能为空：" + skillModelId + "_" + 1);
			return false;
		}
				
		if (player.getLevel() < skillModel.getQ_study_needgrade()) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，人物等级不足，无法学习"));
			return false;
		}
		if (isHaveSkill(player, skillModelId)) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您已经学习过{1}技能，无需重复学习"), skillModel.getQ_skillName());
			return false;
		}
		Skill skill = new Skill();
		skill.setId(Config.getId());
		skill.setSkillLevel(1);
		skill.setSkillModelId(skillModel.getQ_skillID());
		player.getSkills().add(skill);
		
		MessageUtil.tell_player_message(player,getSkillAddMessage(player,skill));
		
		//被动技能增加buff
		if(skillModel.getQ_trigger_type()==2 && skillModel.getQ_passive_action()==0){
			triggerSkill(player, player, skill, true);
		}
		try {
			SkillLevelUpLog log=new SkillLevelUpLog();
			log.setAction("addSkill");
			log.setActionId(Config.getId());
			log.setBeforelevel(0);
			log.setBeforeTime(0);
			log.setLevel(1);
			log.setResult(1);
			log.setResumegold(0);
			log.setResumegoods("");
			log.setResumemoney(0);
			log.setResumezhenqi(0);
			log.setRoleId(player.getId());
			log.setSid(player.getCreateServerId());
			log.setSkillId(skill.getSkillModelId());
		} catch (Exception e) {
			log.error(e, e);
		}
		return true;
	}
	
	public void goldEndUpLevel(Player player){
		if(player.getNowLearnSkillId()<=0||player.getSkillLearnTime()<0){
			MessageUtil.notify_player(player, Notifys.NORMAL,ResManager.getInstance().getString("没有正在学习的技能"));
			return;
		}
		int nowLearnSkillModelId = player.getNowLearnSkillId();
		int skillLearnTime = player.getSkillLearnTime();
		Skill skillById = ManagerPool.skillManager.getSkillByModelId(player, nowLearnSkillModelId);
		if(skillById==null){
			return;
		}
		Q_skill_modelBean q_skill_modelBean = DataManager.getInstance().q_skill_modelContainer.getMap().get(skillById.getSkillModelId() + "_" + skillById.getSkillLevel());
		if(q_skill_modelBean==null){
			return;
		}
		long action=Config.getId();
		if (DataManager.getInstance().q_skill_modelContainer.getMap().get(skillById.getSkillModelId() + "_" + (skillById.getSkillLevel() + 1)) != null) {
			int q_up_need_time = DataManager.getInstance().q_skill_modelContainer.getMap().get(skillById.getSkillModelId() + "_" + (skillById.getSkillLevel()+1))
					.getQ_up_need_time();
			int q_up_prob = DataManager.getInstance().q_skill_modelContainer.getMap().get(skillById.getSkillModelId() + "_" + (skillById.getSkillLevel()+1))
					.getQ_up_prob();
			int remin = q_up_need_time - skillLearnTime;
			int residue = 0;
			if (remin > 60000) {
				residue = remin / 60000;
			} else {
				residue = 1;
			}
			int needgold = residue * ENDSECONDSNEEDYUANBAO;
			boolean result = false;
			int beforelevel = skillById.getSkillLevel();
			if(skillById.getSkillLevel()<q_skill_modelBean.getQ_max_level()){
				if (!BackpackManager.getInstance().checkGold(player, needgold)) {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉操作失败,元宝不够"));
					return;
				}
				BackpackManager.getInstance().changeGold(player, -needgold, Reasons.YBSKILLSTUDY,action);
				if (RandomUtils.defaultIsGenerate(q_up_prob)) {
					result = true;
					endUpLevel(player, skillById, skillById.getSkillLevel() + 1, result);
				} else {
					endUpLevel(player, skillById, skillById.getSkillLevel(), result);
				}
			}else{
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该技能已经达到最高等级"));
				endUpLevel(player, skillById, skillById.getSkillLevel(), false);
			}
			try {
				SkillLevelUpLog log = new SkillLevelUpLog();
				log.setActionId(action);
				log.setAction(SkillLevelAction.ENDLEVELUP.toString());
				log.setBeforeTime(skillLearnTime);
				log.setResumegold(needgold);
				log.setLevel(skillById.getSkillLevel());
				log.setSkillId(skillById.getSkillModelId());
				log.setBeforelevel(beforelevel);
				log.setResult(result ? 1 : 0);
				log.setRoleId(player.getId());
				log.setSid(player.getCreateServerId());
				LogService.getInstance().execute(log);
			} catch (Exception e) {
				log.error(e, e);
			}
		}
	}
	
	public SkillLingWuResultMessage getLingWuResultMessage(Skill skill, boolean result){
		SkillLingWuResultMessage message=new SkillLingWuResultMessage();
		message.setIssuccess((byte)(result==true?1:0));
		message.setGrade(skill.getSkillLevel());
		message.setSkillModelId(skill.getSkillModelId());
		return message;
	}
	
	public SkillStartLevelUpMessage getSkillStartLevelUpMessage(Skill skill){
		SkillStartLevelUpMessage message=new SkillStartLevelUpMessage();
		message.setSkillModelId(skill.getSkillModelId());
		return message;
	}
	
	/**
	 * 升级技能
	 * @param roldId 玩家Id
	 * @param skillId 技能Id
	 * @param i 
	 */
	public void levelUp(Player player, int skillModelId) {
		Skill skill =getSkillByModelId(player, skillModelId); 
		if(skill==null){
			return;
		}
		String nowskillModelId=skill.getSkillModelId()+"_"+(skill.getSkillLevel());
		String targetSkillModelId=skill.getSkillModelId()+"_"+(skill.getSkillLevel()+1);
		Q_skill_modelBean nowSkillModel = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(nowskillModelId);
		Q_skill_modelBean targetSkillModel = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(targetSkillModelId);
		int q_max_level = nowSkillModel.getQ_max_level();
		if(skill.getSkillLevel()>=q_max_level){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该技能达到了最高等级"));
			return;
		}
		if(targetSkillModel==null){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该技能达到了最高等级"));
			return;
		}
		if (player.getLevel() < targetSkillModel.getQ_needgrade()) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，人物等级不足，无法学习"));
			return;
		}

		if (player.getNowLearnSkillId() > 0) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，同一时间只能研究一种技能"));
			return;
		}
		if (targetSkillModel.getQ_up_need_zhengqi() > player.getZhenqi()) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，技能升级所需真气不足"));
			return;
		}

		if (targetSkillModel.getQ_up_need_copper() > player.getMoney()) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，技能升级所需铜币不足"));
			return;
		}
		long action=Config.getId();
		String needGoods = targetSkillModel.getQ_up_need_goods();
		if(needGoods!=null&&needGoods.length()>0){
			String[] split = needGoods.split(";");
			for (String string : split) {
				if(StringUtils.isBlank(string)){
					continue;
				}
				String[] split2 = string.split("_");
				int goodmodel=Integer.parseInt(split2[0]);
				int neednum=Integer.parseInt(split2[1]);
				Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(goodmodel);
				int num=ManagerPool.backpackManager.getItemNum(player, goodmodel);
				if(num<neednum){
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，技能升级所需：{1} * {2}数量不足"), BackpackManager.getInstance().getName(model.getQ_id()),""+neednum);
					return;
				}
			}
		}
		
		
		//移除物品
		if(needGoods!=null&&needGoods.length()>0){
			String[] split = needGoods.split(";");
			for (String string : split) {
				if(StringUtils.isBlank(string)){
					continue;
				}
				String[] split2 = string.split("_");
				int goodmodel=Integer.parseInt(split2[0]);
				int neednum=Integer.parseInt(split2[1]);
				ManagerPool.backpackManager.removeItem(player,goodmodel,neednum,Reasons.SKILLLEVELUP,action);
			}
		}
		//扣除金币
		ManagerPool.backpackManager.changeMoney(player, -targetSkillModel.getQ_up_need_copper(),Reasons.SKILLLEVELUP,action);
		//扣除真气
		ManagerPool.playerManager.addZhenqi(player,- targetSkillModel.getQ_up_need_zhengqi(),AttributeChangeReason.SKILLLEVELUP);
		int beforelevel=skill.getSkillLevel();
		
		if(targetSkillModel.getQ_up_need_time()<=0){
			if (RandomUtils.defaultIsGenerate(targetSkillModel.getQ_up_prob())) {
				endUpLevel(player, skill, skill.getSkillLevel() + 1, true);
			} else {
				endUpLevel(player, skill, skill.getSkillLevel(), false);
			}	
		}else{
			startUpLevel(player, skill);
		}
		try {
			SkillLevelUpLog log = new SkillLevelUpLog();
			log.setAction(SkillLevelAction.STARTLEVELUP.toString());
			log.setActionId(action);
			log.setBeforeTime(0);
			log.setBeforelevel(beforelevel);
			log.setLevel(skill.getSkillLevel());
			log.setSkillId(skill.getSkillModelId());			
			log.setResult(1);
			log.setResumegold(0);
			log.setResumegoods(needGoods);
			log.setResumemoney(targetSkillModel.getQ_up_need_copper());
			log.setResumezhenqi(targetSkillModel.getQ_up_need_zhengqi());
			log.setRoleId(player.getId());
			log.setSid(player.getCreateServerId());
			LogService.getInstance().execute(log);
		} catch (Exception e) {
			log.error(e, e);
		}
	}
	
	/**
	 * 是否拥有技能
	 * @param roleId 玩家Id
	 * @param skillModelId 技能模板Id
	 * @return
	 */
	public boolean isHaveSkill(Player player, int skillModelId){
		Iterator<Skill> iter = player.getSkills().iterator();
		//遍历技能
		while (iter.hasNext()) {
			Skill skill = (Skill) iter.next();
			if(skill.getSkillModelId() == skillModelId){
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * 按模板Id获得技能
	 * @param roleId 玩家id
	 * @param nowLearnSkillId 技能模板id
	 * @return
	 */
	public Skill getSkillByModelId(Player player, int nowLearnSkillId){
		Iterator<Skill> iter = player.getSkills().iterator();
		//遍历技能
		while (iter.hasNext()) {
			Skill skill = (Skill) iter.next();
			if(skill.getSkillModelId() == nowLearnSkillId){
				return skill;
			}
		}
		
		// 暗器也需要处理下
		HiddenWeapon weapon = HiddenWeaponManager.getInstance().getHiddenWeapon(player);
		Skill retSkill = null;
		if (weapon != null) {
			Iterator<Skill> it = weapon.getSkills().values().iterator();
			while (it.hasNext()) {
				Skill tmp = it.next();
				if (tmp.getSkillModelId() == nowLearnSkillId) {
					if (retSkill == null) {
						retSkill = tmp;
					} else {
						if (retSkill.getSkillLevel() < tmp.getSkillLevel()) {
							retSkill = tmp;
						}
					}
				}
			}
		}
		return retSkill;
	}
	
	
	/**
	 * 按模板Id获得技能
	 * @param roleId 玩家id
	 * @param nowLearnSkillId 技能模板id
	 * @return
	 */
	public Skill getSkillByModelId(Monster monster, int nowLearnSkillId){
		Iterator<Skill> iter = monster.getSkills().iterator();
		//遍历技能
		while (iter.hasNext()) {
			Skill skill = (Skill) iter.next();
			if(skill.getSkillModelId() == nowLearnSkillId){
				return skill;
			}
		}
		
		return null;
	}
	
	
	
	/**
	 * 返回玩家身上所有可以被攻击触发的技能
	 * @param player
	 * @return
	 */
	public List<Skill> getSkillTriggerByAttack(Player player){
		List<Skill> skills = new ArrayList<Skill>();
		Iterator<Skill> iter = player.getSkills().iterator();
		//遍历技能
		while (iter.hasNext()) {
			Skill skill = (Skill) iter.next();
			//获得技能模板
			Q_skill_modelBean skillModel= ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getRealLevel(player));
			if(skillModel == null) continue;
			if(skillModel.getQ_trigger_type()!=2) continue;
			//技能在攻击时触发
			if(skillModel.getQ_passive_action() == 1 || skillModel.getQ_passive_action() == 3){
				skills.add(skill);
			}
		}
		
		skills.addAll(getHorseSkillTriggerByAttack(player));
		skills.addAll(getHiddenWeaponSkillTriggerByAttack(player));
		skills.addAll(getPetSkillTriggerByAttack(player));
		return skills;
	}
	
	/**
	 * 返回玩家身上所有可以在攻击之前触发的技能
	 * @param player
	 * @return
	 */
	public List<Skill> getSkillTriggerBeforeAttack(Player player){
		List<Skill> skills = new ArrayList<Skill>();
		Iterator<Skill> iter = player.getSkills().iterator();
		//遍历技能
		while (iter.hasNext()) {
			Skill skill = (Skill) iter.next();
			//获得技能模板
			Q_skill_modelBean skillModel= ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getRealLevel(player));
			if(skillModel == null) continue;
			if(skillModel.getQ_trigger_type()!=2) continue;
			//技能在攻击前触发
			if(skillModel.getQ_passive_action() == 4){
				skills.add(skill);
			}
		}
		
		skills.addAll(getHorseSkillTriggerBeforeAttack(player));
		skills.addAll(getHiddenWeaponSkillTriggerBeforeAttack(player));
		return skills;
	}
	
	/**
	 * 返回玩家身上所有可以在被攻击之前触发的技能
	 * @param player
	 * @return
	 */
	public List<Skill> getSkillTriggerBeforeDefense(Player player){
		List<Skill> skills = new ArrayList<Skill>();
		Iterator<Skill> iter = player.getSkills().iterator();
		//遍历技能
		while (iter.hasNext()) {
			Skill skill = (Skill) iter.next();
			//获得技能模板
			Q_skill_modelBean skillModel= ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getRealLevel(player));
			if(skillModel == null) continue;
			if(skillModel.getQ_trigger_type()!=2) continue;
			//技能在被攻击前触发
			if(skillModel.getQ_passive_action() == 5){
				skills.add(skill);
			}
		}
		
		skills.addAll(getHorseSkillTriggerBeforeDefense(player));
		skills.addAll(getHiddenWeaponSkillTriggerBeforeDefense(player));
		return skills;
	}
	
	/**
	 * 返回玩家身上所有可以被防御触发的技能
	 * @param player
	 * @return
	 */
	public List<Skill> getSkillTriggerByDefense(Player player){
		List<Skill> skills = new ArrayList<Skill>();
		Iterator<Skill> iter = player.getSkills().iterator();
		//遍历技能
		while (iter.hasNext()) {
			Skill skill = (Skill) iter.next();
			//获得技能模板
			Q_skill_modelBean skillModel= ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getRealLevel(player));
			if(skillModel == null) continue;
			if(skillModel.getQ_trigger_type()!=2) continue;
			//技能在防御时触发
			if(skillModel.getQ_passive_action() == 2 || skillModel.getQ_passive_action() == 3){
				skills.add(skill);
			}
		}
		
		skills.addAll(getHorseSkillTriggerByDefense(player));
		skills.addAll(getHiddenWeaponSkillTriggerByDefense(player));
		skills.addAll(getPetSkillTriggerByDefense(player));
		return skills;
	}
	
	/**
	 * 返回玩家身上所有可以被死亡触发的技能
	 * @param player
	 * @return
	 */
	public List<Skill> getSkillTriggerByDie(Player player){
		List<Skill> skills = new ArrayList<Skill>();
		Iterator<Skill> iter = player.getSkills().iterator();
		//遍历技能
		while (iter.hasNext()) {
			Skill skill = (Skill) iter.next();
			//获得技能模板
			Q_skill_modelBean skillModel= ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getRealLevel(player));
			if(skillModel == null) continue;
			if(skillModel.getQ_trigger_type()!=2) continue;
			//技能在攻击时触发
			if(skillModel.getQ_passive_action() == 9){
				skills.add(skill);
			}
		}
		
		skills.addAll(getPetSkillTriggerByDie(player));
		return skills;
	}
	
	//------------------------------返回坐骑技能--------------------------------

	/**
	 * 返回玩家身上所有可以被攻击触发的技能
	 * @param player
	 * @return
	 */
	public List<Skill> getHorseSkillTriggerByAttack(Player player){
		List<Skill> skills = new ArrayList<Skill>();
		List<HorseSkill> hskills = ManagerPool.horseManager.selectActSkill(player);
		if (hskills != null && hskills.size() > 0) {
			Iterator<HorseSkill> iter = hskills.iterator();
			//遍历技能
			while (iter.hasNext()) {
				Skill skill = (Skill) iter.next();
				//获得技能模板
				Q_skill_modelBean skillModel= ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getRealLevel(player));
				if(skillModel == null) continue;
				
				if(skillModel.getQ_trigger_type()!=2) continue;
				//技能在攻击时触发
				if(skillModel.getQ_passive_action() == 1 || skillModel.getQ_passive_action() == 3){
					skills.add(skill);
				}
			}
		}
		return skills;
	}
	
	/**
	 * 返回玩家身上所有可以在攻击之前触发的技能
	 * @param player
	 * @return
	 */
	public List<Skill> getHorseSkillTriggerBeforeAttack(Player player){
		List<Skill> skills = new ArrayList<Skill>();
		List<HorseSkill> hskills = ManagerPool.horseManager.selectActSkill(player);
		if (hskills != null && hskills.size() > 0) {
			Iterator<HorseSkill> iter = hskills.iterator();
			//遍历技能
			while (iter.hasNext()) {
				Skill skill = (Skill) iter.next();
				//获得技能模板
				Q_skill_modelBean skillModel= ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getRealLevel(player));
				if(skillModel == null) continue;
				if(skillModel.getQ_trigger_type()!=2) continue;
				//技能在攻击前触发
				if(skillModel.getQ_passive_action() == 4){
					skills.add(skill);
				}
			}
		}
		return skills;
	}
	
	/**
	 * 返回玩家身上所有可以在攻击之前触发的技能
	 * @param player
	 * @return
	 */
	public List<Skill> getHorseSkillTriggerBeforeDefense(Player player){
		List<Skill> skills = new ArrayList<Skill>();
		List<HorseSkill> hskills = ManagerPool.horseManager.selectActSkill(player);
		if (hskills != null && hskills.size() > 0) {
			Iterator<HorseSkill> iter = hskills.iterator();
			//遍历技能
			while (iter.hasNext()) {
				Skill skill = (Skill) iter.next();
				//获得技能模板
				Q_skill_modelBean skillModel= ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getRealLevel(player));
				if(skillModel == null) continue;
				if(skillModel.getQ_trigger_type()!=2) continue;
				//技能在被攻击前触发
				if(skillModel.getQ_passive_action() == 5){
					skills.add(skill);
				}
			}
		}
		return skills;
	}
	
	/**
	 * 返回玩家身上所有可以被防御触发的技能
	 * @param player
	 * @return
	 */
	public List<Skill> getHorseSkillTriggerByDefense(Player player){
		List<Skill> skills = new ArrayList<Skill>();
		List<HorseSkill> hskills = ManagerPool.horseManager.selectActSkill(player);
		if (hskills != null && hskills.size() > 0) {
			Iterator<HorseSkill> iter = hskills.iterator();
			//遍历技能
			while (iter.hasNext()) {
				Skill skill = (Skill) iter.next();
				//获得技能模板
				Q_skill_modelBean skillModel= ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getRealLevel(player));
				if(skillModel == null) continue;
				if(skillModel.getQ_trigger_type()!=2) continue;
				//技能在防御时触发
				if(skillModel.getQ_passive_action() == 2 || skillModel.getQ_passive_action() == 3){
					skills.add(skill);
				}
			}
		}
		
		return skills;
	}
	
	//------------------------------end--------------------------------
	
	//------------------------------返回暗器技能--------------------------------

	/**
	 * 返回玩家身上所有可以被攻击触发的技能
	 * @param player
	 * @return
	 */
	public List<Skill> getHiddenWeaponSkillTriggerByAttack(Player player){
		List<Skill> ret = new ArrayList<Skill>();
		if (!HiddenWeaponManager.getInstance().isWearHiddenWeapon(player)) {
			return ret;
		}
		HiddenWeapon weapon = HiddenWeaponManager.getInstance().getHiddenWeapon(player);
		if (weapon == null) {
			return ret;
		}
		
		Iterator<Skill> it = weapon.getSkills().values().iterator();
		while (it.hasNext()) {
			Skill skill = it.next();
			Q_skill_modelBean skillModel= ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getSkillLevel());
			if(skillModel == null) continue;
			if(skillModel.getQ_trigger_type() !=2 ) continue;
			if(skillModel.getQ_passive_action() == 1 || skillModel.getQ_passive_action() == 3){
				ret.add(skill);
			}
		}
		return ret;
	}
		
	/**
	 * 返回玩家身上所有可以在攻击之前触发的技能
	 * @param player
	 * @return
	 */
	public List<Skill> getHiddenWeaponSkillTriggerBeforeAttack(Player player){
		List<Skill> ret = new ArrayList<Skill>();
		if (!HiddenWeaponManager.getInstance().isWearHiddenWeapon(player)) {
			return ret;
		}
		HiddenWeapon weapon = HiddenWeaponManager.getInstance().getHiddenWeapon(player);
		if (weapon == null) {
			return ret;
		}
		
		Iterator<Skill> it = weapon.getSkills().values().iterator();
		while (it.hasNext()) {
			Skill skill = it.next();
			Q_skill_modelBean skillModel= ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getSkillLevel());
			if(skillModel == null) continue;
			if(skillModel.getQ_trigger_type() !=2 ) continue;
			if(skillModel.getQ_passive_action() == 4){
				ret.add(skill);
			}
		}
		return ret;
	}
		
	/**
	 * 返回玩家身上所有可以在攻击之前触发的技能
	 * @param player
	 * @return
	 */
	public List<Skill> getHiddenWeaponSkillTriggerBeforeDefense(Player player){
		List<Skill> ret = new ArrayList<Skill>();
		if (!HiddenWeaponManager.getInstance().isWearHiddenWeapon(player)) {
			return ret;
		}
		HiddenWeapon weapon = HiddenWeaponManager.getInstance().getHiddenWeapon(player);
		if (weapon == null) {
			return ret;
		}
		
		Iterator<Skill> it = weapon.getSkills().values().iterator();
		while (it.hasNext()) {
			Skill skill = it.next();
			Q_skill_modelBean skillModel= ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getSkillLevel());
			if(skillModel == null) continue;
			if(skillModel.getQ_trigger_type() !=2 ) continue;
			if(skillModel.getQ_passive_action() == 5){
				ret.add(skill);
			}
		}
		return ret;
	}
		
	/**
	 * 返回玩家身上所有可以被防御触发的技能
	 * @param player
	 * @return
	 */
	public List<Skill> getHiddenWeaponSkillTriggerByDefense(Player player){
		List<Skill> ret = new ArrayList<Skill>();
		if (!HiddenWeaponManager.getInstance().isWearHiddenWeapon(player)) {
			return ret;
		}
		HiddenWeapon weapon = HiddenWeaponManager.getInstance().getHiddenWeapon(player);
		if (weapon == null) {
			return ret;
		}
		
		Iterator<Skill> it = weapon.getSkills().values().iterator();
		while (it.hasNext()) {
			Skill skill = it.next();
			Q_skill_modelBean skillModel= ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getSkillLevel());
			if(skillModel == null) continue;
			if(skillModel.getQ_trigger_type() !=2 ) continue;
			if(skillModel.getQ_passive_action() == 2 || skillModel.getQ_passive_action() == 3){
				ret.add(skill);
			}
		}
		return ret;
	}
	
	//------------------------------end--------------------------------
	
	//------------------------------返回宠物技能--------------------------------

	/**
	 * 返回宠物身上所有可以被玩家攻击触发的技能
	 * @param player
	 * @return
	 */
	public List<Skill> getPetSkillTriggerByAttack(Player player){
		List<Skill> skills = new ArrayList<Skill>();
		List<Pet> pets = player.getPetList();
		if (pets != null && pets.size() > 0) {
			Iterator<Pet> iter = pets.iterator();
			while (iter.hasNext()) {
				Pet pet = (Pet) iter.next();
				if(!pet.isShow() || pet.isDie()) continue;
				
				//遍历技能
				for (int i = 0; i < pet.getSkills().length; i++) {
					Skill skill = pet.getSkills()[i];
					if(skill==null) continue;
					//获得技能模板
					Q_skill_modelBean skillModel= ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getSkillLevel());
					if(skillModel == null) continue;
					if(skillModel.getQ_trigger_type()!=2) continue;
					//技能在攻击时触发
					if(skillModel.getQ_passive_action() == 1 || skillModel.getQ_passive_action() == 3){
						skills.add(skill);
					}
				}
			}
			
		}
		return skills;
	}
	
	/**
	 * 返回宠物身上所有可以被玩家防御触发的技能
	 * @param player
	 * @return
	 */
	public List<Skill> getPetSkillTriggerByDefense(Player player){
		List<Skill> skills = new ArrayList<Skill>();
		List<Pet> pets = player.getPetList();
		if (pets != null && pets.size() > 0) {
			Iterator<Pet> iter = pets.iterator();
			while (iter.hasNext()) {
				Pet pet = (Pet) iter.next();
				if(!pet.isShow() || pet.isDie()) continue;
				
				//遍历技能
				for (int i = 0; i < pet.getSkills().length; i++) {
					Skill skill = pet.getSkills()[i];
					if(skill==null) continue;
					//获得技能模板
					Q_skill_modelBean skillModel= ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getSkillLevel());
					if(skillModel == null) continue;
					if(skillModel.getQ_trigger_type()!=2) continue;
					//技能在攻击时触发
					if(skillModel.getQ_passive_action() == 2 || skillModel.getQ_passive_action() == 3){
						skills.add(skill);
					}
				}
			}
			
		}
		return skills;
	}
	
	/**
	 * 返回宠物身上所有可以被玩家死亡触发的技能
	 * @param player
	 * @return
	 */
	public List<Skill> getPetSkillTriggerByDie(Player player){
		List<Skill> skills = new ArrayList<Skill>();
		List<Pet> pets = player.getPetList();
		if (pets != null && pets.size() > 0) {
			Iterator<Pet> iter = pets.iterator();
			while (iter.hasNext()) {
				Pet pet = (Pet) iter.next();
				if(!pet.isShow() || pet.isDie()) continue;
				
				//遍历技能
				for (int i = 0; i < pet.getSkills().length; i++) {
					Skill skill = pet.getSkills()[i];
					if(skill==null) continue;
					//获得技能模板
					Q_skill_modelBean skillModel= ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getSkillLevel());
					if(skillModel == null) continue;
					if(skillModel.getQ_trigger_type()!=2) continue;
					//技能在攻击时触发
					if(skillModel.getQ_passive_action() == 9){
						skills.add(skill);
					}
				}
			}
			
		}
		return skills;
	}
	
	/**
	 * 返回宠物身上所有可以在宠物攻击触发的技能
	 * @param player
	 * @return
	 */
	public List<Skill> getPetSkillTriggerByAttack(Pet pet){
		List<Skill> skills = new ArrayList<Skill>();
		if(!pet.isShow() || pet.isDie()){
			return skills;
		}
		
		//遍历技能
		for (int i = 0; i < pet.getSkills().length; i++) {
			Skill skill = pet.getSkills()[i];
			if(skill==null) continue;
			//获得技能模板
			Q_skill_modelBean skillModel= ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getSkillLevel());
			if(skillModel == null) continue;
			if(skillModel.getQ_trigger_type()!=2) continue;
			//技能在攻击时触发
			if(skillModel.getQ_passive_action() == 6 || skillModel.getQ_passive_action() == 8){
				skills.add(skill);
			}
		}
		return skills;
	}
	
	/**
	 * 返回宠物身上所有可以在宠物防御触发的技能
	 * @param player
	 * @return
	 */
	public List<Skill> getPetSkillTriggerByDefense(Pet pet){
		List<Skill> skills = new ArrayList<Skill>();
		if(!pet.isShow() || pet.isDie()){
			return skills;
		}
		
		//遍历技能
		for (int i = 0; i < pet.getSkills().length; i++) {
			Skill skill = pet.getSkills()[i];
			if(skill==null) continue;
			//获得技能模板
			Q_skill_modelBean skillModel= ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getSkillLevel());
			if(skillModel == null) continue;
			if(skillModel.getQ_trigger_type()!=2) continue;
			//技能在攻击时触发
			if(skillModel.getQ_passive_action() == 7 || skillModel.getQ_passive_action() == 8){
				skills.add(skill);
			}
		}
		return skills;
	}
	
	//------------------------------end--------------------------------
	
	
	
	/**
	 * 触发技能
	 * @param source 来源者
	 * @param target 目标
	 * @param skill	技能
	 * @param success false-计算成功率  true-必定成功
	 * @return
	 */
	public int triggerSkill(Fighter source, Fighter target, Skill skill, boolean success){
		
		if (!canTrigger(source, target, skill)) {
			return 0;
		}
		
		//获得技能模板
		Q_skill_modelBean skillModel= null;
		if(source instanceof Player){
			skillModel = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getRealLevel((Player)source));
		}else{
			skillModel = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getSkillLevel());
		}
		if(skillModel == null) return 0;
		
		// 火墙技能 走特殊流程
		if (skillModel != null && skillModel.getQ_skill_type() == 1 && source instanceof Player && target != null) {
			FightManager.getInstance().useGroundMagic((Player) source, skill, target.getMap(), target.getLine(), target.getPosition());
			return 0;
		}
		
		//没有可触发buff
		if(skillModel.getQ_passive_buff()==null || ("").equals(skillModel.getQ_passive_buff().trim())) return 0;
		
		//未成功触发技能
		int prop = RandomUtils.random(Global.MAX_PROBABILITY);
		if(prop >= skillModel.getQ_passive_prob() && !success){
			return 0;
		}
		
		//被动触发技能冷却
		if(source instanceof Player){
			//技能冷却
			if(ManagerPool.cooldownManager.isCooldowning((Player)source, CooldownTypes.SKILL_TRIGGER, String.valueOf(skillModel.getQ_skillID()))){
				return 0;
			}
			//技能公共冷却
			if(ManagerPool.cooldownManager.isCooldowning((Player)source, CooldownTypes.SKILL_TRIGGER_PUBLIC, String.valueOf(skillModel.getQ_public_cd_level()))){
				return 0;
			}
		}else if(source instanceof Pet){
			Player player = ManagerPool.playerManager.getPlayer(((Pet) source).getOwnerId());
			//技能冷却
			if(ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.SKILL_TRIGGER, String.valueOf(skillModel.getQ_skillID()))){
				return 0;
			}
			//技能公共冷却
			if(ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.SKILL_TRIGGER_PUBLIC, String.valueOf(skillModel.getQ_public_cd_level()))){
				return 0;
			}
		}
		
		//作用对象为主人
		if(source instanceof Pet && skillModel.getQ_target()==6){
			Player player = ManagerPool.playerManager.getPlayer(((Pet) source).getOwnerId());
			target = player;
		}
		
		//特殊，骑战兵器保护buff
		if(skill.getSkillModelId()==Global.UNWEAR_HORSE_WEAPON_SKILL){
			if(!(target instanceof Player)){
				return 0;
			}
			else if(FighterState.HORSEWEAPONPROTECT.compare(target.getFightState())){
				return 0;
			}
		}
		
		//获得抵抗技能模板
		Q_skill_modelBean resistModel = null;
		if(skillModel.getQ_restriction()!=0){
			//获得对手身上的抵抗技能
			Skill resist = null;
			if(target instanceof Player){
				resist = getSkillByModelId((Player)target, skillModel.getQ_restriction());
				if(resist!=null){
					resistModel = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(resist.getSkillModelId() + "_" + resist.getRealLevel((Player)target));
					int prob = RandomUtils.random(Global.MAX_PROBABILITY);
					if(prob < resistModel.getQ_passive_prob()){
					}else{
						resistModel = null;
					}
					
				}
			}else if(target instanceof Monster){
				resist = getSkillByModelId((Monster)target, skillModel.getQ_restriction());
				if(resist!=null){
					resistModel = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(resist.getSkillModelId() + "_" + resist.getSkillLevel());
					int prob = RandomUtils.random(Global.MAX_PROBABILITY);
					if(prob < resistModel.getQ_passive_prob()){
					}else{
						resistModel = null;
					}
					
				}
			}
			
		}
		//计算成功几率
		int probability = skillModel.getQ_bufq_trigger_factor() * 100;
		if(resistModel!=null){
			probability -= (resistModel.getQ_bufq_defence_factor() * 100);
		}
		//有抵抗技能的时候几率最小0，最大50%  暗器技能无限制
		if (skillModel.getQ_skill_user() != 5) {
			if(skillModel.getQ_restriction()!=0){
				if(probability < 0) probability = 0;
				else if(probability > 5000) probability = 5000;
			}
		}
		
		if(RandomUtils.random(Global.MAX_PROBABILITY) >= probability){
			//BUFF添加未成功
			
			// 暗器技能被抵抗
			if (resistModel != null && skillModel.getQ_skill_user() == 5 && target instanceof Player) {
				ResHiddenWeaponSkillRestrictionMessage msg = new ResHiddenWeaponSkillRestrictionMessage();
				msg.setPlayerid(target.getId());
				msg.setSkill(skillModel.getQ_skillID());
				MessageUtil.tell_round_message(target, msg);
			}
			return 0;
		}
		
		//计算Buff时间
		int time = skillModel.getQ_bufq_timeup_factor();
		if(resistModel!=null){
			time -= resistModel.getQ_bufq_timedown_factor();
		}
		
		int value = skillModel.getQ_bufq_num_factor();
		if(resistModel!=null){
			value -= resistModel.getQ_bufq_num_factor();
		}
		
		int percent = skillModel.getQ_bufq_action_factor();
		if(resistModel!=null){
			percent -= resistModel.getQ_bufq_action_factor();
		}
		
		//被动触发技能冷却
//		if(source instanceof Player){
//			//开始冷却
//			double speed = 1 + ((double)source.getAttackSpeed()) / 1000;
//			//添加技能冷却
//			ManagerPool.cooldownManager.addCooldown((Player)source, CooldownTypes.SKILL_TRIGGER, String.valueOf(skillModel.getQ_skillID()), (long)(skillModel.getQ_cd() / speed));
//			//添加技能公共冷却
//			ManagerPool.cooldownManager.addCooldown((Player)source, CooldownTypes.SKILL_TRIGGER_PUBLIC, String.valueOf(skillModel.getQ_public_cd_level()), (long)(skillModel.getQ_public_cd() / speed));
//		}else if(source instanceof Pet){
//			Player player = ManagerPool.playerManager.getPlayer(((Pet) source).getOwnerId());
//			//开始冷却
//			double speed = 1 + ((double)source.getAttackSpeed()) / 1000;
//			//添加技能冷却
//			ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.SKILL_TRIGGER, String.valueOf(skillModel.getQ_skillID()), (long)(skillModel.getQ_cd() / speed));
//			//添加技能公共冷却
//			ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.SKILL_TRIGGER_PUBLIC, String.valueOf(skillModel.getQ_public_cd_level()), (long)(skillModel.getQ_public_cd() / speed));
//		}
		
		String[] buffs = skillModel.getQ_passive_buff().split(Symbol.FENHAO_REG);
		for (int i = 0; i < buffs.length; i++) {
			//获取BUFF模型
			Q_buffBean buffModel = ManagerPool.dataManager.q_buffContainer.getMap().get(Integer.parseInt(buffs[i]));
			if(buffModel==null) continue;
			
			List<Fighter> adders = new ArrayList<Fighter>();
			
			//同队队友添加buff
			if(buffModel.getQ_target()==4){
				Player player = ManagerPool.playerManager.getPlayer(source.getId());
				if (player!= null) {
					//角色有组队 给自己和队员加buff
					if(player.getTeamid() > 0){
						TeamInfo team = ManagerPool.teamManager.getTeam(player.getTeamid());
						if (team != null) {
							TeamMemberInfo[] teamMember = team.getMemberinfo().toArray(new TeamMemberInfo[0]);
							for (TeamMemberInfo teamMemberInfo : teamMember) {
								Player xplayer = ManagerPool.playerManager.getOnLinePlayer(teamMemberInfo.getMemberid());
								if (xplayer.getLine() == player.getLine() && xplayer.getMap() == player.getMap()) {
									adders.add(xplayer);
								}
							}
						}
					}else{
						//角色没有组队 只给自己加buff
						adders.add(player);
					}
				}
			}else{
				adders.add(source);
			}
			for (int j = 0; j < adders.size(); j++) {
				ManagerPool.buffManager.addBuff(adders.get(j), target, Integer.parseInt(buffs[i]), time, value, percent);
			}
		}
		
		return 1;
	}
	
	public boolean canTrigger(Fighter source, Fighter target, Skill skill) {

		// 暗器、骑兵的群攻效果对以下副本无效：秦风古韵 梅花桩 七耀战将 白起
		ITriggerSkillScript script = (ITriggerSkillScript)ManagerPool.scriptManager.getScript(ScriptEnum.SKILL_TRIGGER);
		if(script != null) {
			try {
				if (!script.canTrigger(source, target, skill)) {
					return false;
				}
			} catch (Exception e) {
				log.error(e, e);
				return false;
			}
		} else {
			log.error("暗器触发技能失败,找不到技能触发脚本");
			return false;
		}
		return true;
	}

	/**
	 * 获取武功境界
	 * @param player
	 * @return
	 */
	public Q_skill_realmBean getSkillRealm(Player player){
		//计算武功等级总和
		int level = 0;
		for (int i = 0; i < player.getSkills().size(); i++) {
			int modelid = player.getSkills().get(i).getSkillModelId();
			if (modelid >= 25005 && modelid <= 25007) {	//排除结婚技能
				continue;
			}
			level += player.getSkills().get(i).getSkillLevel();
		}
		
		List<Q_skill_realmBean> realms = ManagerPool.dataManager.q_skill_realmContainer.getList();
		for (int i = realms.size() - 1; i > -1; i--) {
			Q_skill_realmBean realm = realms.get(i);
			if(level >= realm.getQ_arrive_levelsum() && player.getLevel() >= realm.getQ_show_needgrade()) return realm;
		}
		
		return null;
	}
	
	public int getSkillLevelSum(Player player){
		int level = 0;
		for (int i = 0; i < player.getSkills().size(); i++) {
			int modelid = player.getSkills().get(i).getSkillModelId();
			if (modelid >= 25005 && modelid <= 25007) {	//排除结婚技能
				continue;
			}
			level += player.getSkills().get(i).getSkillLevel();
		}
		return level;
	}
	
	/**
	 * 设置默认技能
	 * @param roleId 玩家id
	 * @param skillModelId 技能模板id
	 */
	public void setDefaultSkill(Player player, int skillModelId){
		player.setDefaultSkill(skillModelId);
	}
	
	/**
	 * 发送玩家全部技能信息
	 * @param roleId 玩家id
	 */
	public void sendSkillInfos(Player player){
		
		SkillInfosMessage msg = new SkillInfosMessage();
		msg.setDefaultSkill(player.getDefaultSkill());
		
		Iterator<Skill> iter = player.getSkills().iterator();
		//遍历技能
		while (iter.hasNext()) {
			Skill skill = (Skill) iter.next();
			msg.getSkills().add(getSkillInfo(player,skill));
		}
		
		MessageUtil.tell_player_message(player, msg);
	}
	
	
	/**
	 * 发送配偶全部技能信息
	 * @param Player spouseplayer
	 */
	public void sendSpouseSkillInfos(Player player,Player spouseplayer){
		ResGetSpouseSkillToClientMessage msg = new ResGetSpouseSkillToClientMessage();
		msg.setDefaultSkill(spouseplayer.getDefaultSkill());
		Iterator<Skill> iter = spouseplayer.getSkills().iterator();
		//遍历技能
		while (iter.hasNext()) {
			Skill skill = (Skill) iter.next();
			msg.getSkills().add(getSkillInfo(spouseplayer,skill));
		}
		MessageUtil.tell_player_message(player, msg);
	}
	

	
	public void sendSkillInfo(Player player,Skill skill){
		SkillChangeMessage msg=new SkillChangeMessage();
		msg.setSkills(getSkillInfo(player, skill));
		MessageUtil.tell_player_message(player, msg);
	}
	
	public void sendSkillInfo(Player player, int skillModel){
		Skill skill = getSkillByModelId(player, skillModel);
		if(skill!=null){
			SkillChangeMessage msg=new SkillChangeMessage();
			msg.setSkills(getSkillInfo(player, skill));
			MessageUtil.tell_player_message(player, msg);
		}
	}
	
	/**
	 * 获取技能增加消息
	 * @param skill 技能
	 * @return
	 */
	public SkillAddMessage getSkillAddMessage(Player player,Skill skill){
		SkillAddMessage msg = new SkillAddMessage();
		msg.setSkill(getSkillInfo(player,skill));
		return msg;
	}
	
	/**
	 * 获取技能移除消息
	 * @param skill 技能
	 * @return
	 */
	public SkillRemoveMessage getSkillRemoveMessage(Skill skill){
		SkillRemoveMessage msg = new SkillRemoveMessage();
		msg.setSkillModelId(skill.getSkillModelId());
		return msg;
	}
	
	/**
	 * 获取技能信息
	 * @param skill 技能
	 * @return
	 */
	public SkillInfo getSkillInfo(Player player, Skill skill){
		SkillInfo info = new SkillInfo();
		info.setSkillId(skill.getId());
		info.setSkillModelId(skill.getSkillModelId());
		info.setSkillLevel(skill.getSkillLevel());
		setSkillLevelInfo(player, info.getSkillAddLevels(), skill);
		return info;
	}

	/**
	 * 获取技能加成等级
	 * @param player
	 * @param list
	 * @param skill
	 */
	public void setSkillLevelInfo(Player player, List<SkillLevelInfo> list, Skill skill){
		Iterator<Entry<Integer, PlayerAttribute>> iter = player.getAttributes().entrySet().iterator();
		while (iter.hasNext()) {
			Entry<java.lang.Integer, com.game.player.structs.PlayerAttribute> entry = (Entry<java.lang.Integer, com.game.player.structs.PlayerAttribute>) iter
					.next();

			PlayerAttribute playerAttribute = entry.getValue();
			if(playerAttribute.getSkillLevelUp().size() > 0){
				int level = 0;
				if(playerAttribute.getSkillLevelUp().containsKey(-1)) level = playerAttribute.getSkillLevelUp().get(-1);
				if(playerAttribute.getSkillLevelUp().containsKey(skill.getSkillModelId()))
					level += playerAttribute.getSkillLevelUp().get(skill.getSkillModelId());
				if(level!=0){
					SkillLevelInfo info = new SkillLevelInfo();
					info.setKey(entry.getKey());
					info.setLevel(level);
					list.add(info);
				}
			}
		}
		
	}
	
	/**
	 * 升级触发
	 * @param role
	 */
	public void autoStudySkill(Player role){
		int level = role.getLevel();
		Q_characterBean model = ManagerPool.dataManager.q_characterContainer.getMap().get(level);
		if(model==null) return;
		int q_skill = model.getQ_skill();
		if(q_skill!=0){
			Q_skill_modelBean skillModel= ManagerPool.dataManager.q_skill_modelContainer.getMap().get(q_skill+"_"+1);
			if(skillModel!=null&&!SkillManager.getInstance().isHaveSkill(role, q_skill)){
				SkillManager.getInstance().addSkill(role, q_skill);
			}
		}		
	}
	
	/**删除技能
	 * 
	 * @param player
	 * @param skillModelId
	 * @return 
	 */
	public boolean removeSkill(Player player , int skillModelId){
		Iterator<Skill> iter = player.getSkills().iterator();
		//遍历技能
		while (iter.hasNext()) {
			Skill skill = (Skill) iter.next();
			if(skill.getSkillModelId() == skillModelId){
				iter.remove();
				MessageUtil.tell_player_message(player, getSkillRemoveMessage(skill));
				return true;
			}
		}
		return false;
	}

	public boolean isIgnoreJumpMiss(Q_skill_modelBean model) {
		if (model.getQ_is_ignore() % 10 == 1) {
			return true;
		}
		return false;
	}

	public boolean isIgnoreMiss(Q_skill_modelBean model) {
		int flag = model.getQ_is_ignore() / 10;
		if (flag % 10 == 1) {
			return true;
		}
		return false;
	}

	public boolean isIgnoreDefense(Q_skill_modelBean model) {
		int flag = model.getQ_is_ignore() / 100;
		if (flag % 10 == 1) {
			return true;
		}
		return false;
	}
}
