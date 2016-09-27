package com.game.pet.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.config.Config;
import com.game.cooldown.structs.CooldownTypes;
import com.game.count.manager.CountManager;
import com.game.count.structs.CountTypes;
import com.game.data.bean.Q_itemBean;
import com.game.data.bean.Q_mapBean;
import com.game.data.bean.Q_petinfoBean;
import com.game.data.bean.Q_rankBean;
import com.game.data.bean.Q_skill_modelBean;
import com.game.data.bean.Q_task_mainBean;
import com.game.data.manager.DataManager;
import com.game.dazuo.manager.PlayerDaZuoManager;
import com.game.dblog.LogService;
import com.game.fight.structs.Fighter;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.manager.MapManager;
import com.game.map.message.ResPetJumpPositionsMessage;
import com.game.map.message.ResRoundPetDisappearMessage;
import com.game.map.message.ResRoundPetMessage;
import com.game.map.structs.Area;
import com.game.map.structs.Jump;
import com.game.map.structs.Map;
import com.game.monster.structs.Monster;
import com.game.pet.log.PetAddLog;
import com.game.pet.log.PetClearHeTiCDLog;
import com.game.pet.log.PetGotLog;
import com.game.pet.log.PetHeTiLog;
import com.game.pet.message.ResPetAddMessage;
import com.game.pet.message.ResPetDieBroadcastMessage;
import com.game.pet.message.ResPetDieMessage;
import com.game.pet.message.ResPetHTCDClearResultMessage;
import com.game.pet.message.ResPetHidenMessage;
import com.game.pet.message.ResPetShowMessage;
import com.game.pet.message.ResPetSkillChangeMessage;
import com.game.pet.struts.Pet;
import com.game.pet.struts.PetJumpState;
import com.game.pet.struts.PetRunState;
import com.game.pet.struts.PetState;
import com.game.player.manager.PlayerAttributeManager;
import com.game.player.manager.PlayerManager;
import com.game.player.message.ReqSyncPlayerPetInfoMessage;
import com.game.player.message.ReqSyncPlayerPetMessage;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttributeType;
import com.game.prompt.structs.Notifys;
import com.game.skill.bean.SkillInfo;
import com.game.skill.structs.Skill;
import com.game.structs.Grid;
import com.game.structs.Position;
import com.game.structs.Reasons;
import com.game.task.struts.MainTask;
import com.game.utils.Global;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.StringUtil;
import com.game.utils.Symbol;
import com.game.utils.TimeUtil;
/**
 * 宠物操作管理 
 * @author 赵聪慧
 *
 */
public class PetOptManager {
	/**
	 * 玩家主动攻击
	 */
	public static int FIGHTTYPE_PLAYER_ATTACK=1;
	/**
	 * 玩家攻击波及
	 */
	public static int FIGHTTYPE_PLAYER_DAMAGE=2;
	/**
	 * 玩家被攻击
	 */
	public static int FIGHTTYPE_PLAYER_DEFENCE=3;
	/**
	 * 美人被攻击
	 */
	public static int FIGHTTYPE_PET_DEFENCE=4;
	
	/**
	 * 无战斗状态
	 */
	public static int FIGHTTYPE_PET_IDEL=0;
	
	/**
	 * 合体增加最多的属性条数
	 */
	public static int HTADDATTRIBUTECOUNT=3;
	
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PetOptManager.class);

	private static final PetOptManager instance=new PetOptManager();
	
//	private static int maxpets=24;
	//最大可拥有的数量
	public static PetOptManager getInstance(){
		return instance;
	}
	private PetOptManager(){
	}
	
	/**
	 * 包含宠物
	 * @param player
	 * @param modelid
	 * @return
	 */
	public boolean hasPet(Player player, int modelid) {
		if (player != null && player.getPetList() != null && player.getPetList().size() > 0) {
			List<Pet> petList = player.getPetList();
			for (Pet pet : petList) {
				if (pet.getModelId() == modelid) {
					// 以经有了
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 增加宠物
	 * @param player
	 * @param modelId 模型 
	 * @param reason 原因 
	 * @param exclude 是否排除( 白夷女2  鹿丹儿7  赵倩10  赵致12 )
	 * 	               exclude=true 并且 modelid 是以上4个之一 则 不允许增加
	 */
	public void addPet(Player player, int modelId,String reason,long actionId, boolean exclude) {
		if(exclude){
			if(modelId==2 || modelId==7 || modelId==10 || modelId==12){
				logger.error("排除特定侍宠\t"+player.getName()+"\t"+modelId+"\t"+reason);
				return;
			}
		}
		addPet(player, modelId, reason, actionId);
	}
	
	public void addPet(Player player, int modelId,String reason,long actionId) {
		Q_petinfoBean model = DataManager.getInstance().q_petinfoContainer.getMap().get(modelId);
		if(model==null){
			logger.error("未知的美人模型"+modelId);
			return;
		}
		if(hasPet(player, modelId)){
			logger.error("已经有该模型的美人"+modelId);
			return;
		}
		Pet pet = new Pet(player, modelId);
		player.addPet(pet);
		pet.setHp(pet.getMaxHp());
		pet.setSp(pet.getMaxSp());
		pet.setMp(pet.getMaxMp());
		ResPetAddMessage msg = new ResPetAddMessage();
		// Grid[][] grids =
		// ManagerPool.mapManager.getMapBlocks(player.getMapModelId());
		msg.setPet(PetInfoManager.getInstance().getDetailInfo(pet));
		MessageUtil.tell_player_message(player, msg);
		
		try{
			if(player.getLevel() >= Global.SYNC_PLAYER_LEVEL && pet.getModelId() >= Global.SYNC_EVENT_PET){
				ReqSyncPlayerPetMessage petmsg = new ReqSyncPlayerPetMessage();
				petmsg.setPlayerId(player.getId());
				petmsg.setPet(JSONserializable.toString(pet));
				MessageUtil.send_to_world(petmsg);
			}
		}catch (Exception e) {
			logger.error(e,e);
		}
		try{
			PetAddLog log=new PetAddLog();
			log.setPetid(pet.getId());
			log.setPetmodelid(modelId);
			log.setReason(reason);
			log.setRoleId(player.getId());
			log.setActionId(actionId);
			log.setUserId(player.getUserId());
			log.setSid(player.getCreateServerId());
			LogService.getInstance().execute(log);
		}catch (Exception e) {
			logger.error(e,e);
		}
		logger.debug("玩家" + player.getId() + "得到宠物" + modelId);
		if(model.getQ_got_notice()==1){
			MessageUtil.notify_All_player(Notifys.SROLL, ResManager.getInstance().getString("恭喜玩家『{1}』激活美人{2}成功！"),player.getName(),model.getQ_name());
		}
	}
	
	
	/**
	 * 钦点宠物
	 * @param player
	 * @param modelId
	 */
	public void gotPet(Player player,int modelId){
		Q_petinfoBean model = DataManager.getInstance().q_petinfoContainer.getMap().get(modelId);
		if(model==null){
			logger.error("不存在的宠物模型"+modelId+"");
			return;
		}		
		if(hasPet(player, modelId)){
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("宠物不可重复获取"));
			return;
		}
		int q_got_need_task = model.getQ_got_need_task();
		
		MainTask mainTask = player.getCurrentMainTasks().get(0);
		if(player.getCurrentMainTasks().size()!=0&&q_got_need_task>=mainTask.getModelid()){
			Q_task_mainBean q_task_mainBean = DataManager.getInstance().q_task_mainContainer.getMap().get(q_got_need_task);
			if(q_task_mainBean==null){
				logger.error("找不到的任务模型"+q_got_need_task);
				return;
			}
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("激活{1}美人需要完成任务{2}"),model.getQ_name(),q_task_mainBean.getQ_name());
			return;
		}
		
		int q_got_needlevel = model.getQ_got_needlevel();
		if(q_got_needlevel>player.getLevel()){
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("激活{1}美人需要达到{2}级"),model.getQ_name(),""+q_got_needlevel);
			return;
		}
		



//		int q_got_resume_sw = model.getQ_got_resume_sw();
		
//	 	TODO 钦点美人检查声望(声望系统目前取消)
//		不满足要求
//	 		个人重要信息提示：
//	 	“激活XX美人需要消耗XX点声望，您目前声望不足”
//	 	 	满足要求
//	 		进入下个逻辑

		
		
//			判断角色当前剩余铜币是否满足要求
//	 	不满足要求
//		个人重要信息提示：
//	“激活XX美人需要消耗XX点声望，您目前声望不足”
//	 	满足要求
//		进入下个逻辑
		 int resume_gold = model.getQ_got_resume_gold();//铜币
		 if(player.getMoney()<resume_gold){
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("激活{1}美人需要消耗{2}铜币，您目前铜币不足"),model.getQ_name(),resume_gold+"");
			 return;
		 }
		
		String q_got_resumeitem = model.getQ_got_resumeitem();
		if(q_got_resumeitem!=null && !("").equals(q_got_resumeitem)){
			String[] itemstrs = q_got_resumeitem.split(Symbol.FENHAO_REG);
			for (int i = 0; i < itemstrs.length; i++) {
				String[] itemstr = itemstrs[i].split(Symbol.XIAHUAXIAN_REG);
				int got_resumeitem = Integer.parseInt(itemstr[0]);
				int got_resumenum = Integer.parseInt(itemstr[1]);
//				int q_got_resumenum = model.getQ_got_resumenum();
				if(got_resumeitem!=0&&got_resumenum!=0){
					Q_itemBean q_itemBean = DataManager.getInstance().q_itemContainer.getMap().get(got_resumeitem);
					if(q_itemBean==null){
						logger.error("找不到的物品ID"+q_got_resumeitem);
						return;
					}
					if(BackpackManager.getInstance().getItemNum(player, got_resumeitem)<got_resumenum){
						String name = BackpackManager.getInstance().getName(q_itemBean.getQ_id());
						MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("激活{1}美人需要消耗{2}{3}个，您目前{4}数量不足"),model.getQ_name(),name,got_resumenum+"",q_itemBean.getQ_name());	
						return;
					}
				}
			}
		}
		
		
		
		int q_got_needpet = model.getQ_got_needpet();
		if(q_got_needpet!=0&&!hasPet(player, q_got_needpet)){
			Q_petinfoBean needPetModel = DataManager.getInstance().q_petinfoContainer.getMap().get(q_got_needpet);
			if(needPetModel==null){
				logger.error("找不到的宠物模型q_got_needpet"+q_got_needpet);
				return ;
			}
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("激活{1}美人需要先激活{2}美人"),model.getQ_name(),needPetModel.getQ_name());
			return;
		}
		int q_rank_cond = model.getQ_rank_cond();
		Q_rankBean bean = DataManager.getInstance().q_rankContainer.getMap().get(q_rank_cond);
		if(q_rank_cond!=0&&bean!=null){
			if (player.getRanklevel() < q_rank_cond) {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("激活{1}美人需要达到{2}军衔"),model.getQ_name(),bean.getQ_rankname());
				return;
			}
		}
		
		long action=Config.getId();
		BackpackManager.getInstance().changeMoney(player,-resume_gold,Reasons.PET_QINGDIAN,action);
		
		if(q_got_resumeitem!=null && !("").equals(q_got_resumeitem)){
			String[] itemstrs = q_got_resumeitem.split(Symbol.FENHAO_REG);
			for (int i = 0; i < itemstrs.length; i++) {
				String[] itemstr = itemstrs[i].split(Symbol.XIAHUAXIAN_REG);
				int got_resumeitem = Integer.parseInt(itemstr[0]);
				int got_resumenum = Integer.parseInt(itemstr[1]);
				BackpackManager.getInstance().removeItem(player, got_resumeitem,got_resumenum, Reasons.PET_QINGDIAN, action);
			}
		}
			
		addPet(player, modelId, "qindian", action, true);
		try{
			PetGotLog log=new PetGotLog();
			log.setActionId(action);
			log.setModelId(modelId);
//			log.setPetId();
			log.setRoleId(player.getId());
			log.setUserId(player.getUserId());
			log.setSid(player.getCreateServerId());
			LogService.getInstance().execute(log);
		}catch (Exception e) {
			logger.error(e,e);
		}
	}
	
	public void changeLevel(Player player){
		List<Pet> petList = player.getPetList();
		Grid[][] grids = ManagerPool.mapManager.getMapBlocks(player.getMapModelId());
		for (Pet pet : petList) {
			Q_petinfoBean model = DataManager.getInstance().q_petinfoContainer.getMap().get(pet.getModelId());
			int oldlevel = pet.getLevel();
			pet.setLevel(player.getLevel()>model.getQ_pet_maxlevel()?model.getQ_pet_maxlevel():player.getLevel());
			pet.setHp(pet.getMaxHp());
			pet.setMp(pet.getMaxMp());
			pet.setSp(pet.getMaxSp());
			if(pet.isShow()){
				ResRoundPetMessage msg = new ResRoundPetMessage();
				msg.setPet(MapManager.getInstance().getPetInfo(pet, grids));
				MessageUtil.tell_round_message(pet, msg);
			}
			if(oldlevel > pet.getLevel() && player.getLevel() >= Global.SYNC_PLAYER_LEVEL && pet.getModelId() >= Global.SYNC_EVENT_PET){
				try{
					ReqSyncPlayerPetInfoMessage petmsg = new ReqSyncPlayerPetInfoMessage();
					petmsg.setPlayerId(player.getId());
					petmsg.setPetId(pet.getModelId());
					petmsg.setPetHeti(pet.getHtcount());
					petmsg.setPetLevel(pet.getLevel());
					MessageUtil.send_to_world(petmsg);
				}catch (Exception e) {
					logger.error(e,e);
				}
			}
		}
		PetInfoManager.getInstance().sendPetInfo(player);
	}
	
	
	
	/**
	 * 宠物出战
	 * @param player
	 * @param petId
	 */
	public void showPet(Player player, long petId) {
		if(player.isDie()){
			return;
		}
		Pet pet = PetInfoManager.getInstance().getPet(player, petId);
		if (pet == null) {
			return;
		}
		Pet showPet = PetInfoManager.getInstance().getShowPet(player);
		if(showPet!=null){
			if(showPet.getId()==petId){
				return;
			}
			if (!PetInfoManager.getInstance().isFullHp(showPet)) {
				MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("切换美人需要当前美人血量处于满值状态，退出战斗美人会自动回血"));
				return;
			}
			logger.error("角色[" + player.getId() + "]美人[" + pet.getId() + "]操作[hide because new pet show]");
			hidePet(player, showPet.getId());			
		}
		pet.setShowTime(System.currentTimeMillis());
		// 重新出战的美人处于满血状态
		pet.resetPet();
		pet.setHp(pet.getMaxHp());
		logger.error("角色[" + player.getId() + "]美人[" + pet.getId() + "]操作[player show pet]");
		MapManager.getInstance().enterMap(pet);
		ResPetShowMessage msg=new ResPetShowMessage();
		msg.setPet(PetInfoManager.getInstance().getDetailInfo(pet));
		MessageUtil.tell_player_message(player, msg);
		PlayerDaZuoManager.getInstacne().petShow(player);
		PetScriptManager.getInstance().showPet(pet);
	}
	
	/**
	 * 宠物休息
	 * @param player
	 * @param petId
	 */
	public void hidePet(Player player,long petId){
//	 	判断当前出战美人血量是否是满值，如果非满值则弹出个人重要信息提示：
//	 	“切换美人需要当前美人血量处于满值状态，退出战斗美人会自动回血”
//	 	 	若当前出战美人血量为满值则收起场景中，出战美人
		Pet pet = PetInfoManager.getInstance().getPet(player, petId);
		if(pet==null){
			return;
		}
		if(!pet.isShow()){
			return;
		}
		if(!PetInfoManager.getInstance().isFullHp(pet)){
			MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("收回美人需要当前美人血量处于满值状态，退出战斗美人会自动回血"));
			return;	
		}
		changeAttackTarget(pet, null, PetOptManager.FIGHTTYPE_PET_IDEL);
		PetScriptManager.getInstance().hidePet(pet);
		MapManager.getInstance().quitMap(pet);
		logger.error("角色[" + player.getId() + "]美人[" + pet.getId() + "]操作[hide]");
		pet.setShow(false);
		ResPetHidenMessage msg=new ResPetHidenMessage();
		msg.setPet(PetInfoManager.getInstance().getDetailInfo(pet));
		MessageUtil.tell_player_message(player, msg);
		PlayerDaZuoManager.getInstacne().petHide(player);
		
	}
	
	/**
	 * 宠物强制休息
	 * @param player
	 * @param petId
	 */
	public void forceHidePet(Pet pet){
		
		if(pet==null){
			return;
		}
		if(!pet.isShow()){
			return;
		}
		
		Player player = ManagerPool.playerManager.getPlayer(pet.getOwnerId());
		if(player==null){
			return;
		}
		changeAttackTarget(pet, null, PetOptManager.FIGHTTYPE_PET_IDEL);
		MapManager.getInstance().quitMap(pet);
		
		logger.error("角色[" + player.getId() + "]美人[" + pet.getId() + "]操作[force hide]");
		pet.setShow(false);
		pet.setForceHide(true);
		
		ResPetHidenMessage msg=new ResPetHidenMessage();
		msg.setPet(PetInfoManager.getInstance().getDetailInfo(pet));
		MessageUtil.tell_player_message(player, msg);
		PlayerDaZuoManager.getInstacne().petHide(player);
		PetScriptManager.getInstance().hidePet(pet);
	}
	
	/**
	 * 合体
	 * @param player
	 * @param petId
	 */
	public void heti(Player player,long petId){
		Pet pet =  PetInfoManager.getInstance().getPet(player, petId);
		if(pet==null){
			logger.error("不存在的美人ID ROLEID:"+player.getId()+"美人ID"+petId);
			return;
		}
		Q_petinfoBean model = DataManager.getInstance().q_petinfoContainer.getMap().get(pet.getModelId());
		if(model==null){
			
			logger.error("不存在的美人模型 ROLEID:"+player.getId()+"美人ID"+petId+"MODELID"+pet.getModelId());
			return;
		}
		long cooldownTime = ManagerPool.cooldownManager.getCooldownTime(pet, CooldownTypes.PET_HETI,String.valueOf(model.getQ_model_id()));
		if(cooldownTime>0){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("目前合体未冷却，请等待{1}后再尝试"), TimeUtil.millisecondToStr(cooldownTime));
			return;
		}
		if(pet.getHtcount()>=model.getQ_ht_count()){
			int nextmodelId=0;
			List<Q_petinfoBean> list = DataManager.getInstance().q_petinfoContainer.getList();
			ArrayList<Q_petinfoBean> sortList=new ArrayList<Q_petinfoBean>();
			sortList.addAll(list);
			Collections.sort(sortList, new Comparator<Q_petinfoBean>() {
				@Override
				public int compare(Q_petinfoBean o1, Q_petinfoBean o2) {
					return o1.getQ_model_id()-o2.getQ_model_id();
				}
			});
			for (Q_petinfoBean q_petinfoBean : sortList) {
				if(q_petinfoBean.getQ_model_id()>model.getQ_model_id()){
					nextmodelId=model.getQ_model_id();
					break;
				}
			}
			Q_petinfoBean nextModel = DataManager.getInstance().q_petinfoContainer.getMap().get(nextmodelId);
			if(nextmodelId!=0&&nextModel!=null){
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("{1}美人合体次数已经达到上限，下一阶美人{2}拥有更多的合体次数"),model.getQ_name(),nextModel.getQ_name());
			}else{
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("{1}美人合体次数已经达到上限"),model.getQ_name());	
			}			
			return;
		}
		long action = Config.getId();
		long dayhtcount = CountManager.getInstance().getCount(player, CountTypes.PET_HT,model.getQ_model_id()+"");
		
		if (dayhtcount>=model.getQ_ht_daycount() ) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("{1}美人今日合体次数已经达到上限，请与其他美人合体吧"), model.getQ_name());
			return;
		}
		if(model.getQ_ht_resume_copper()>player.getMoney()){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("{1}美人合体需要消耗{2}铜币，您的铜币数量不足"), model.getQ_name(),model.getQ_ht_resume_copper()+"");
			return;
		}
		if(model.getQ_ht_resume_item()!=0&&model.getQ_ht_resume_num()!=0){
			Q_itemBean resumeItem= DataManager.getInstance().q_itemContainer.getMap().get(model.getQ_ht_resume_item());
			if(resumeItem==null){
				logger.error("找不到的合体消耗材料ID"+model.getQ_ht_resume_item());
				return;
			}
			if(BackpackManager.getInstance().getItemNum(player, model.getQ_ht_resume_item())<model.getQ_ht_resume_num()){
				String name = BackpackManager.getInstance().getName(resumeItem.getQ_id());
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("{1}美人合体需要消耗{2}{3}个，您的{4}数量不足"), model.getQ_name(),name,model.getQ_ht_resume_num()+"",name);
				return;
			}
			if(!BackpackManager.getInstance().removeItem(player, model.getQ_ht_resume_item(),model.getQ_ht_resume_num(), Reasons.PET_HETI,action)){
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("扣除物品失败"));
				return;
			}
		}

		BackpackManager.getInstance().changeMoney(player, -model.getQ_ht_resume_copper(),Reasons.PET_HETI, action);
		
		if(RandomUtils.defaultIsGenerate(10000)){
			if(pet.getHtcount()==0){
				//首次合体
				int attributecount=0;
				
				if (attributecount <= HTADDATTRIBUTECOUNT&&model.getQ_ht_firstattack()!=0) {
					pet.setHtaddattack(pet.getHtaddattack()+model.getQ_ht_firstattack());
					attributecount++;
				}
				if (attributecount <= HTADDATTRIBUTECOUNT&&model.getQ_ht_firstcrit()!=0) {
					pet.setHtaddcrit(pet.getHtaddcrit()+model.getQ_ht_firstcrit());
					attributecount++;
				}
				if (attributecount <= HTADDATTRIBUTECOUNT&&model.getQ_ht_firstdefence()!=0) {
					pet.setHtadddefence(pet.getHtadddefence()+model.getQ_ht_firstdefence());
					attributecount++;
				}
				if (attributecount <= HTADDATTRIBUTECOUNT&&model.getQ_ht_firstdodge()!=0) {
					pet.setHtadddodge(pet.getHtadddodge()+model.getQ_ht_firstdodge());
					attributecount++;
				}
				if (attributecount <= HTADDATTRIBUTECOUNT&&model.getQ_ht_firsthp()!=0) {
					pet.setHtaddhp(pet.getHtaddhp()+model.getQ_ht_firsthp());
					attributecount++;
				}
				if (attributecount <= HTADDATTRIBUTECOUNT&&model.getQ_ht_firstmp()!=0) {
					pet.setHtaddmp(pet.getHtaddmp()+model.getQ_ht_firstmp());
					attributecount++;
				}
				//TODO 奖励经验脚本
				//model.getQ_ht_first_exp_script();
			}else{
				int attributecount=0;
				if (attributecount <= HTADDATTRIBUTECOUNT&&model.getQ_ht_addattack()!=0) {
					pet.setHtaddattack(pet.getHtaddattack()+model.getQ_ht_addattack());					
					attributecount++;
				}
				if (attributecount <= HTADDATTRIBUTECOUNT&&model.getQ_ht_addcrit()!=0) {
					pet.setHtaddcrit(pet.getHtaddcrit()+model.getQ_ht_addcrit());
					attributecount++;
				}
				if (attributecount <= HTADDATTRIBUTECOUNT&&model.getQ_ht_adddefence()!=0) {
					pet.setHtadddefence(pet.getHtadddefence()+model.getQ_ht_adddefence());
					attributecount++;
				}
				if (attributecount <= HTADDATTRIBUTECOUNT&&model.getQ_ht_adddodge()!=0) {
					pet.setHtadddodge(pet.getHtadddodge()+model.getQ_ht_adddodge());
					attributecount++;
				}
				if (attributecount <= HTADDATTRIBUTECOUNT&&model.getQ_ht_addhp()!=0) {
					pet.setHtaddhp(pet.getHtaddhp()+model.getQ_ht_addhp());
					attributecount++;
				}
				if (attributecount <= HTADDATTRIBUTECOUNT&&model.getQ_ht_addmp()!=0) {
					pet.setHtaddmp(pet.getHtaddmp()+model.getQ_ht_addmp());
					attributecount++;
				}
				//TODO 奖励经验脚本
				//model.getQ_ht_exp_script();
			}
			pet.setHtcount(pet.getHtcount()+1);
			PlayerAttributeManager.getInstance().countPlayerAttribute(player, PlayerAttributeType.PET);
			ManagerPool.cooldownManager.addCooldown(pet,CooldownTypes.PET_HETI,String.valueOf(model.getQ_model_id()), model.getQ_ht_cooldown());
			ManagerPool.countManager.addCount(player, CountTypes.PET_HT, pet.getModelId()+"", CountManager.COUNT_DAY, 1, 0);
			try{
				if(player.getLevel() >= Global.SYNC_PLAYER_LEVEL && pet.getModelId() >= Global.SYNC_EVENT_PET){
					ReqSyncPlayerPetInfoMessage petmsg = new ReqSyncPlayerPetInfoMessage();
					petmsg.setPlayerId(player.getId());
					petmsg.setPetId(pet.getModelId());
					petmsg.setPetHeti(pet.getHtcount());
					petmsg.setPetLevel(pet.getLevel());
					MessageUtil.send_to_world(petmsg);
				}
			}catch (Exception e) {
				logger.error(e,e);
			}
			try{
				PetHeTiLog log=new PetHeTiLog();
				log.setActionid(action);
				log.setCount(pet.getHtcount());
				log.setModelid(pet.getModelId());
				log.setPetid(pet.getModelId());
				log.setRoleid(pet.getOwnerId());	
				log.setUserId(player.getUserId());
				LogService.getInstance().execute(log);
			}catch (Exception e) {
				logger.error(e,e);
			}
			MessageUtil.tell_player_message(player, PetInfoManager.getInstance().getPetHeTiMsg(pet, true));
		}else{
			MessageUtil.tell_player_message(player, PetInfoManager.getInstance().getPetHeTiMsg(pet, false));
		}
	}
	
	/**
	 * 
	 * @param player
	 * @param petId
	 */
	public void clearHtCD(Player player,int modelId){		
		Pet pet =  PetInfoManager.getInstance().getPetByModelId(player, modelId);
		if(pet==null){
			logger.error("不存在的美人ID ROLEID:"+player.getId()+"美人ID"+modelId);
			return;
		}
		Q_petinfoBean model = DataManager.getInstance().q_petinfoContainer.getMap().get(modelId);
		if(model==null){
			logger.error("不存在的美人模型 ROLEID:"+player.getId()+"美人ID"+pet.getId()+"MODELID"+pet.getModelId());
			return;
		}
		
		long cooldownTime = ManagerPool.cooldownManager.getCooldownTime(pet, CooldownTypes.PET_HETI,String.valueOf(model.getQ_model_id()));
//		//有CD时间才扣元宝
		if(cooldownTime<=0){
			return;
		}
		
		int needgold = 0;
		int clearCDResumeGold= model.getQ_ht_clearcd_gold();
//		按分钟计 不足一分钟按一分钟计
		if (cooldownTime <=  60*1000) {
			needgold=clearCDResumeGold;
		} else {
			needgold = (int) (cooldownTime / (60 * 1000)*clearCDResumeGold);
			if (cooldownTime % (60 * 1000) > 0) {
				needgold++;
			}
		}
		// Gold gold = BackpackManager.getInstance().getGold(player);
		if (!BackpackManager.getInstance().checkGold(player, needgold)) {
			MessageUtil.notify_player(player, Notifys.ERROR, "元宝数量不足需要{1}", "" + needgold);
			return;
		}
		long action = Config.getId();
		BackpackManager.getInstance().changeGold(player, -needgold, Reasons.PET_HETI,action);
		ManagerPool.cooldownManager.removeCooldown(pet, CooldownTypes.PET_HETI, String.valueOf(model.getQ_model_id()));
		ResPetHTCDClearResultMessage resp=new ResPetHTCDClearResultMessage();
		resp.setPet(PetInfoManager.getInstance().getDetailInfo(pet));
		MessageUtil.tell_player_message(player, resp);
		
		try{
			PetClearHeTiCDLog log=new PetClearHeTiCDLog();
			log.setActionid(action);
			log.setCdtime((int)(cooldownTime/1000));
			log.setPetId(pet.getId());
			log.setResumegold(needgold);
			log.setRoleId(player.getId());
			log.setUserid(player.getUserId());
			log.setSid(player.getCreateServerId());
			LogService.getInstance().execute(log);
			
			
		}catch (Exception e) {
			logger.error(e,e);
		}
		
	}
	

	
	public void changeSkill(Player player, long petId, int index, int skillModel){
		if(index==0){
			logger.error("默认技能不能切换");
			return;
		}
		Pet pet =  PetInfoManager.getInstance().getPet(player, petId);
		if(pet==null){
			logger.error("不存在的美人ID ROLEID:"+player.getId()+"美人ID"+petId);
			return ;
		}
		Q_petinfoBean model = DataManager.getInstance().q_petinfoContainer.getMap().get(pet.getModelId());
		if(model==null){
			logger.error("不存在的美人模型 ROLEID:"+player.getId()+"美人ID"+petId+"MODELID"+pet.getModelId());
			return;
		}
		if (!PetInfoManager.getInstance().isFullHp(pet)) {
			MessageUtil.notify_player(player, Notifys.NORMAL, "切换技能需要当前美人血量处于满值状态，退出战斗美人会自动回血");
			return;
		}
		Q_skill_modelBean q_skill_modelBean = DataManager.getInstance().q_skill_modelContainer.getMap().get(skillModel+"_"+1);
		if(q_skill_modelBean==null){
			logger.error("不存在的美人技能模型模型 ROLEID:"+player.getId()+"美人ID"+petId+"MODELID"+skillModel+"");
			return;
		}
		if(index<0&&index>=model.getQ_skill_num()){
			logger.error("索引从0开始美人共有"+model.getQ_skill_num()+"个技能格请求索引位置："+index);
			return;
		}
		
		int q_iscgs = model.getQ_iscgs();
		if(q_iscgs!=1){
			MessageUtil.notify_player(player, Notifys.ERROR, "该美人不可切换技能");
			return;
		}
		
//		if(model.getQ_cgs_need_viplevel()!=0){
//			
//			MessageUtil.notify_player(player, Notifys.ERROR, "为该美人切换技能需要VIP{1}级",model.getQ_cgs_need_viplevel()+"");
//			return;
//		}
		String q_ablecg_skill = model.getQ_ablecg_skill();
		if(StringUtil.isBlank(q_ablecg_skill)){
			MessageUtil.notify_player(player, Notifys.ERROR, "{1}美人不拥有{2}技能",model.getQ_name(),q_skill_modelBean.getQ_skillName());
			return;
		}
		String[] split = q_ablecg_skill.split(Symbol.FENHAO_REG);
		if(!StringUtil.hasObject(split,String.valueOf(skillModel))){
			MessageUtil.notify_player(player, Notifys.ERROR, "{1}美人不拥有{2}技能",model.getQ_name(),q_skill_modelBean.getQ_skillName());
			return;
		}
		for (int i = 0; i < pet.getSkills().length; i++) {
			Skill skill = pet.getSkills()[i];
			if (skill != null && skill.getSkillModelId() == skillModel) {
				if(i==0){
					logger.error("默认技能不能切换");
					return;
				}
				// 存在相同技能 先移除
				pet.getSkills()[i] = null;
			}
		}
		Skill skill = new Skill();
		skill.setId(Config.getId());
		skill.setSkillLevel(1);
		skill.setSkillModelId(skillModel);		
		pet.getSkills()[index]=skill;
		model.getQ_skill_single();
		ResPetSkillChangeMessage msg=new ResPetSkillChangeMessage();
		msg.setPetId(petId);
		for (Skill vskill : pet.getSkills()) {
			if(vskill!=null)
				msg.getSkillInfos().add(PetInfoManager.getInstance().getSkillInfo(vskill));
			else
				msg.getSkillInfos().add(new SkillInfo());
		}
		MessageUtil.tell_player_message(player, msg);
		MessageUtil.notify_player(player, Notifys.SUCCESS,"成功将{1}侍宠技能切换成{2}", model.getQ_name(),q_skill_modelBean.getQ_skillName());
	}
	

	public void die(Pet pet, Fighter killer){
		
		// 停止移动
		MapManager.getInstance().petStopRun(pet);
//		// 获得所在地图
//		Map map = MapManager.getInstance().getMap(pet.getServerId(), pet.getLine(), pet.getMap());
//		if (map == null) {
//			logger.error("map " + pet.getMap() + " not found!");
//			return;
//		}
//		// 获得所在区域
//		int areaId = MapManager.getInstance().getAreaId(pet.getPosition());
//		Area area = map.getAreas().get(areaId);
//		//移出宠物
//		if(area.getPets().contains(pet)){
//			area.getPets().remove(pet);
//		}
		ResPetDieBroadcastMessage dmsg = new ResPetDieBroadcastMessage();
		dmsg.setPetId(pet.getId());
		MessageUtil.tell_round_message(pet, dmsg);
		
		changeAttackTarget(pet, null, PetOptManager.FIGHTTYPE_PET_IDEL);
		
		pet.changeStateTo(PetState.DIE);
		
		pet.setDieTime(System.currentTimeMillis());
		
		//通知主人
		ResPetDieMessage msg=new ResPetDieMessage();
		msg.setPet(PetInfoManager.getInstance().getDetailInfo(pet));
		Player player = PlayerManager.getInstance().getPlayer(pet.getOwnerId());
		if(player!=null) MessageUtil.tell_player_message(player, msg);
		if(player!=null) PlayerDaZuoManager.getInstacne().petHide(player);
		try{
			logger.error("pet " + pet.getId() + "(" + pet.getModelId() + ") owner " + player.getId() + " die!");
		}catch (Exception e) {
			logger.error(e, e);
		}
		if(killer instanceof Player){
			PetScriptManager.getInstance().playerKillTarget((Player)killer,pet);
		}
		

//		// 通知周围玩家
//		ResPetDieBroadcastMessage broadcastmsg=new ResPetDieBroadcastMessage();
//		MessageUtil.tell_round_message(pet, broadcastmsg);
	}

	

	
	/**
	 * 玩家攻击
	 * @param player
	 * @param fighter
	 * @param damage
	 */
	public void onwerAttack(Player player, Fighter fighter, int damage){
		if(fighter==null||fighter.isDie()){
			return;
		}
		
		if(player.getPetList().size()>0){
			List<Pet> petList = player.getPetList();
			for (Pet pet : petList) {
				if(pet.isShow() && !pet.isDie()){
					//切换宠物的攻击目标
//					pet.setAttackTarget(fighter);
					PetOptManager.getInstance().changeAttackTarget(pet, fighter, PetOptManager.FIGHTTYPE_PLAYER_ATTACK);
				}
			}
		}
	}
	
	/**
	 * 玩家攻击波及
	 * @param player
	 * @param fighter
	 * @param damage
	 */
	public void onwerDamage(Player player, Fighter fighter, int damage){
		if(fighter==null||fighter.isDie()){
			return;
		}
		
		if(player.getPetList().size()>0&&damage>0){
			List<Pet> petList = player.getPetList();
			for (Pet pet : petList) {
				if(pet.isShow() && !pet.isDie()){
					//切换宠物的攻击目标
//					pet.setAttackTarget(fighter);
					if(pet.getTargetType()==FIGHTTYPE_PLAYER_ATTACK){
						continue;
					}
					PetOptManager.getInstance().changeAttackTarget(pet,fighter, PetOptManager.FIGHTTYPE_PLAYER_DAMAGE);
				}
			}
		}
	}
	
	/**
	 * 玩家被攻击
	 * @param fighter
	 * @param player
	 * @param damage
	 */
	public void ownerDefence(Fighter fighter, Player player, int damage){
		if(fighter==null||fighter.isDie()){
			return;
		}
		if(damage>0)
		PlayerDaZuoManager.getInstacne().breakDaZuo(player);
		if(fighter instanceof Pet){
			return;
		}	
		if(player.getPetList().size()>0 && damage>0){
			List<Pet> petList = player.getPetList();
			for (Pet pet : petList) {
				if(!pet.isShow()){
					continue;
				}
				if(pet.isDie()){
					continue;
				}
				if(pet.getTargetType()==FIGHTTYPE_PLAYER_ATTACK){
					continue;
				}
				if(pet.getTargetType()==FIGHTTYPE_PLAYER_DAMAGE){
					continue;
				}
				changeAttackTarget(pet, fighter, FIGHTTYPE_PLAYER_DEFENCE);
			}
		}
	}
	
	/**
	 * 美人切换攻击目标
	 * @param pet
	 * @param fighter
	 * @param type	切换原因  用于判断优先级
	 */
	public void changeAttackTarget(Pet pet, Fighter fighter, int type){
		if(fighter instanceof Pet || !pet.isShow() || pet.isDie()){
			return;
		}
		if(pet.getTargetType()==type && type!=FIGHTTYPE_PLAYER_ATTACK){
			if(fighter!=null && !pet.getAttackTargets().contains(fighter)){
				if(fighter instanceof Player){
					Iterator<Fighter> iter = pet.getAttackTargets().iterator();
					while (iter.hasNext()) {
						Fighter fighter2 = (Fighter) iter.next();
						if(fighter2 instanceof Monster) iter.remove();
					}
				}
				if(pet.getAttackTargets().size()<10){
					pet.getAttackTargets().add(fighter);
					logger.debug("增加目标"+fighter+" "+type +" 目标个数:"+pet.getAttackTargets().size());
				}
			}
		}else{
			pet.setTargetType(type);
			pet.getAttackTargets().clear();
			if(fighter!=null) pet.getAttackTargets().add(fighter);
			logger.debug("切换目标"+fighter+" "+type +" 目标个数:"+pet.getAttackTargets().size());
		}
		
	}
	
	
	
	/**
	 * 宠物被攻击
	 * @param fighter
	 * @param pet
	 * @param damage
	 */
	public void petDefence(Fighter fighter, Pet pet, int damage){
		if(fighter==null || fighter.isDie()){
			return;
		}
		if(damage<=0){
			return;
		}
		if(fighter instanceof Pet){
			logger.error("宠物不可以攻击宠物");
			return;
		}
		
		if(!pet.isShow()){
			return;
		}
		
		if(pet.isDie()){
			return;
		}
		Player player = PlayerManager.getInstance().getPlayer(pet.getOwnerId());
		PlayerDaZuoManager.getInstacne().breakDaZuo(player);
		if(pet.getTargetType()==FIGHTTYPE_PLAYER_ATTACK){
			return;
		}
		if(pet.getTargetType()==FIGHTTYPE_PLAYER_DAMAGE){
			return;
		}
		if(pet.getTargetType()==FIGHTTYPE_PLAYER_DEFENCE){
			return;
		}
		changeAttackTarget(pet, fighter, FIGHTTYPE_PET_DEFENCE);
	}
	
	/**
	 * 传送到主人附近
	 * @param pet
	 * @param mapid
	 * @param pos
	 */
	public void petTransToOwner(Pet pet){
		Map map = ManagerPool.mapManager.getMap(pet.getServerId(), pet.getLine(), pet.getMap());
		if(map==null) return;
		Player player = PlayerManager.getInstance().getPlayer(pet.getOwnerId());
		Position pos = MapUtils.getBackPoint(player.getPosition(), player.getDirection(), map.getMapModelid());
		ManagerPool.mapManager.petTrans(pet, pos);
	}
	
	public boolean petJump(Pet pet, int type, Position target){
		if(pet.getJumpState()!=PetJumpState.NOMAL){
			return false;
		}
		Map map = MapManager.getInstance().getMap(pet);
		if(map==null) return false;
		
		boolean result = false;
		if(type == 1){
			MapManager.getInstance().petStopRun(pet);
//			target = getJumpTarget(map, pet.getPosition(), target).getCenter();
			//一跳
			Jump jump = pet.getJump();
			jump.setJumpStart(pet.getPosition());
			jump.setJumpEnd(target);
			
			jump.setJumpStartTime(System.currentTimeMillis());
			jump.setSpeed(pet.getSpeed() * Global.JUMP_SPEED/ Global.MAX_PROBABILITY);
			// 设置跳跃总时间
			int time = (int) (MapUtils.countDistance(jump.getJumpStart(),jump.getJumpEnd()) * 1000 / jump.getSpeed());
			jump.setTotalTime(time);
			
			pet.setJumpState(PetJumpState.JUMP);
			
			result =  true;
		}else if(type == 2){
			//一跳过程中
			//触发二跳
			Jump jump = pet.getJump();
			jump.setJumpStart(pet.getPosition());
			jump.setJumpEnd(target);
 			jump.setSpeed(pet.getSpeed() * Global.JUMP_SPEED/ Global.MAX_PROBABILITY);
			jump.setJumpStartTime(System.currentTimeMillis());
			// 设置跳跃总时间
			int time = (int) (MapUtils.countDistance(jump.getJumpStart(),jump.getJumpEnd()) * 1000 / jump.getSpeed());
			jump.setTotalTime(time);
			
			pet.setJumpState(PetJumpState.DOUBJUMP);

			result =  true;
		}
		
		if(result){
			Position start = pet.getPosition();
			ManagerPool.petOptManager.setPetPosition(pet, target);
			
			// 开始移动
			HashMap<Long, Pet> runnings = map.getRunningPets();
			if (!runnings.containsKey(pet.getId())) {
				runnings.put(pet.getId(), pet);
			}
			/**
			 * 计算区域 *
			 */
			// 起跳所在区域
			int startAreaId = ManagerPool.mapManager.getAreaId(start);
			// 结束所在区域
			int endAreaId = ManagerPool.mapManager.getAreaId(pet.getPosition());
			
			logger.debug("pet " + pet.getId() + "(" + pet.getModelId() + ") changarea from " +startAreaId + " to " + endAreaId);
			// 跳跃区域改变
			if (startAreaId != endAreaId) {
				Area startArea = map.getAreas().get(startAreaId);
				if (startArea == null) {
					logger.debug("start area " + startAreaId + " is null, pet "
						+ pet.getId() + " position" + start);
				}
				if (!startArea.getPets().contains(pet)) {
					logger.error("changearea area " + startArea.getId() + " not contains pet "
						+ pet.getId());
					Iterator<Area> iter = map.getAreas().values().iterator();
					while (iter.hasNext()) {
						Area area = (Area) iter.next();
						if (area.getPets().contains(pet)) {
							logger.error("changearea area " + area.getId() + " contains pet "
								+ pet.getMapModelId());
							area.getPets().remove(pet);
						}
					}
				}else{
					startArea.getPets().remove(pet);
				}
				Area newArea = map.getAreas().get(endAreaId);
				if (newArea == null) {
					logger.error("new area " + endAreaId + " is null, pet "
						+ pet.getId() + " position" + pet.getPosition());
				}
				newArea.getPets().add(pet);
			}

	
			// 跳跃信息
			ResPetJumpPositionsMessage msg=new ResPetJumpPositionsMessage();
			msg.getPositions().add(start);
			msg.getPositions().add(target);
			msg.setState((byte)(pet.getJumpState()==PetJumpState.DOUBJUMP?2:1));
			msg.setTime(pet.getJump().getTotalTime());
			msg.setPetId(pet.getId());
	
			//对起点区域和落点区域附近广播
			HashSet<Area> round = new HashSet<Area>();
			List<Area> oldRound = ManagerPool.mapManager.getRoundAreas(map, startAreaId);
			for (int i = 0; i < oldRound.size(); i++) {
				round.add(oldRound.get(i));
			}
			
			List<Area> newRound = ManagerPool.mapManager.getRoundAreas(map, endAreaId);
			for (int i = 0; i < newRound.size(); i++) {
				round.add(newRound.get(i));
			}
	
			Iterator<Area> iterArea = round.iterator();
			while (iterArea.hasNext()) {
				Area area = (Area) iterArea.next();
				Iterator<Player> iterPlayer = area.getPlayers().iterator();
				while (iterPlayer.hasNext()) {
					Player other = (Player) iterPlayer.next();
					msg.getRoleId().add(other.getId());
				}
			}
	
			MessageUtil.send_to_gate(map.getSendId(), msg);
			
			// 站立区域改变
			if (endAreaId != startAreaId) {
				
				HashSet<Area> oldSet = new HashSet<Area>();
				for (int i = 0; i < oldRound.size(); i++) {
					oldSet.add(oldRound.get(i));
				}

				HashSet<Area> newSet = new HashSet<Area>();
				for (int i = 0; i < newRound.size(); i++) {
					newSet.add(newRound.get(i));
				}
				
				ResRoundPetDisappearMessage hidemsg = new ResRoundPetDisappearMessage();
				hidemsg.getPetIds().add(pet.getId());
				for (int i = 0; i < oldRound.size(); i++) {
					Area area = oldRound.get(i);
					if (!newSet.contains(area)) {
//						System.out.println("area " + endAreaId + " hideid " + area.getId());
						Iterator<Player> iter = area.getPlayers().iterator();
						while (iter.hasNext()) {
							Player other = (Player) iter.next();
							if (pet.canSee(other)) {
								hidemsg.getRoleId().add(other.getId());
//								System.out.println("hide pet area " + area.getId() + " player " + other.getId());
							}
						}
					}
				}
				//宠物在原视野消失
				if (hidemsg.getRoleId().size() > 0) {
					MessageUtil.send_to_gate(map.getSendId(), hidemsg);
				}
				
				Grid[][] grids = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
				ResRoundPetMessage showmsg = new ResRoundPetMessage();
				showmsg.setPet(ManagerPool.mapManager.getPetInfo(pet, grids));
		
				for (int i = 0; i < newRound.size(); i++) {
					Area area = newRound.get(i);
					if (!oldSet.contains(area)) {
//						System.out.println("area " + endAreaId + " showid " + area.getId());
						Iterator<Player> iter = area.getPlayers().iterator();
						while (iter.hasNext()) {
							Player other = (Player) iter.next();
		
							if (pet.canSee(other)) {
								showmsg.getRoleId().add(other.getId());
//								System.out.println("show pet area " + area.getId() + " player " + other.getId());
							}
						}
					}
				}
				//宠物在新视野中出现
				if (showmsg.getRoleId().size() > 0) {
					MessageUtil.send_to_gate(map.getSendId(), showmsg);
				}
			}
		}
		return false;
	}
	
	/**
	 * 切换地点
	 *
	 * @param player
	 * @param pos 坐标
	 *
	 */
	public void setPetPosition(Pet pet, Position pos) {

		Q_mapBean mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(pet.getMapModelId());

		Grid[][] grids = ManagerPool.mapManager.getMapBlocks(mapBean.getQ_map_id());

		if (pos.getX() > mapBean.getQ_map_width() || pos.getY() > mapBean.getQ_map_height()) {
			return;
		}

		pet.setPosition(pos);

		Grid grid = MapUtils.getGrid(pos, grids);
		if (ManagerPool.mapManager.isSwimGrid(grid)) {
			if (PetRunState.RUN==pet.getRunState()) {
				//游泳状态
				pet.setRunState(PetRunState.SWIM);
				
				logger.debug("切换游泳状态");
			}
		} else {
			if (PetRunState.SWIM==pet.getRunState()) {
				//走路状态
				pet.setRunState(PetRunState.RUN);
				
				logger.debug("切换走路状态");
			}
		}
	}
	
	
//	/**
//	 * 获取可跳点
//	 * @param pet
//	 * @param pos
//	 * @return
//	 */
//	public Grid getJumpTarget(Map map, Position source, Position target){
////		Map map = MapManager.getInstance().getMap(pet);
//		Grid[][] mapBlocks = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
//		//起跳格
//		Grid sourceGrid= MapUtils.getGrid(source, mapBlocks);
//		//终点格
//		Grid targetGrid= MapUtils.getGrid(target, mapBlocks);
//		while(MapUtils.countDistance(sourceGrid.getCenter(), targetGrid.getCenter())>Global.PET_MAX_JUMPDISTANCE||MapUtils.isBlock(targetGrid)){
////			 8:⊙,7：↖， 6：←， 5：↙， 4：↓， 3：↘， 2：→，1：↗，0：↑
//			int countDirection = countDirection(targetGrid, sourceGrid);
//			switch (countDirection) {
//			case 0:
//				//上方 X减一 Y轴不变
//				targetGrid=mapBlocks[targetGrid.getX()][targetGrid.getY()-1];
//				break;
//			case 1:
//				targetGrid=mapBlocks[targetGrid.getX()+1][targetGrid.getY()-1];
//				break;
//			case 2:
//				targetGrid=mapBlocks[targetGrid.getX()][targetGrid.getY()+1];
//				break;
//			case 3:
//				targetGrid=mapBlocks[targetGrid.getX()+1][targetGrid.getY()+1];
//				break;
//			case 4:
//				targetGrid=mapBlocks[targetGrid.getX()+1][targetGrid.getY()];
//				break;
//			case 5:
//				targetGrid=mapBlocks[targetGrid.getX()-1][targetGrid.getY()+1];
//				break;
//			case 6:
//				targetGrid=mapBlocks[targetGrid.getX()-1][targetGrid.getY()];
//				break;
//			case 7:
//				targetGrid=mapBlocks[targetGrid.getX()-1][targetGrid.getY()-1];
//				break;
//			case 8:
//				targetGrid=sourceGrid;
//			default:
//				break;
//			}	
//		}
//		if(MapUtils.countDistance(sourceGrid.getCenter(), targetGrid.getCenter())<=Global.PET_MAX_JUMPDISTANCE&&!MapUtils.isBlock(targetGrid)){
//			return targetGrid;
//		}		
//		return sourceGrid;
//	}
//	
//	/**
//	 * 获取两点间的中间点
//	 * @param map
//	 * @param source
//	 * @param target
//	 * @return
//	 */
//	public Grid getMiddleGrid(Map map,Position source,Position target){
//		int x=(source.getX()+target.getX())/2;
//		int y=(source.getY()-target.getY())/2;
//		Grid[][] mapBlocks = MapManager.getInstance().getMapBlocks(map.getMapModelid());
//		return mapBlocks[x][y];
//	}
//	
//	/**
//	 * 计算以1为原点 2所在的方向  8:⊙,7：↖， 6：←， 5：↙， 4：↓， 3：↘， 2：→，1：↗，0：↑
//	 * @param grid1
//	 * @param grid2
//	 * @return
//	 */
//	private static int countDirection(Grid grid1, Grid grid2){
//		if(grid1.getY() == grid2.getY() && grid1.getX() == grid2.getX()){
//			return 8;
//		}else if(grid1.getY() == grid2.getY()) {
//			if(grid1.getX() < grid2.getX()){
//				return 2;
//			}else{
//				return 6;
//			}
//		}else if(grid1.getX() == grid2.getX()) {
//			if(grid1.getY() < grid2.getY()){
//				return 4;
//			}else{
//				return 0;
//			}
//		}else if(grid1.getX() < grid2.getX()){
//			if(grid1.getY() < grid2.getY()){
//				return 3;
//			}else{
//				return 1;
//			}
//		}else{
//			if(grid1.getY() < grid2.getY()){
//				return 5;
//			}else{
//				return 7;
//			}
//		}
//	}
}
