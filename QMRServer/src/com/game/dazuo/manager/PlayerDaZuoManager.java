package com.game.dazuo.manager;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.buff.manager.BuffManager;
import com.game.buff.structs.BuffConst;
import com.game.buff.structs.BuffType;
import com.game.data.bean.Q_buffBean;
import com.game.data.bean.Q_characterBean;
import com.game.data.bean.Q_globalBean;
import com.game.data.manager.DataManager;
import com.game.dazuo.message.ResDazuoEruptMessage;
import com.game.dazuo.message.ResDazuoStateBroadCastMessage;
import com.game.dazuo.message.ResShuangXiuApplyMessage;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.manager.MapManager;
import com.game.map.message.ResSetPositionMessage;
import com.game.map.structs.Area;
import com.game.map.structs.Map;
import com.game.monster.manager.MonsterManager;
import com.game.pet.manager.PetInfoManager;
import com.game.pet.manager.PetOptManager;
import com.game.pet.manager.PetScriptManager;
import com.game.pet.struts.Pet;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerState;
import com.game.player.structs.AttributeChangeReason;
import com.game.prompt.structs.Notifys;
import com.game.utils.*;
import com.game.vip.manager.VipManager;

public class PlayerDaZuoManager {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PlayerDaZuoManager.class);
	public static final byte UNDAZUO = 0;//未打坐;
	public static final byte SINGLEDAZUO = 1;//单人打坐;
	public static final byte ROLESHUANGXIUE = 2;//与玩家双修;
	public static final byte PETSHUANGXIU = 3;//与宠物双修;
	public static final int SHUANGXIUTIMEOUT = 60 * 1000;//双修申请过时时间 
	public static PlayerDaZuoManager instance = new PlayerDaZuoManager();

	public static PlayerDaZuoManager getInstacne() {
		return instance;
	}

	private PlayerDaZuoManager() {
	}
	/**
	 * 系统开启双倍打坐经验和真气
	 *
	 */
	private byte DaZuoDoubleStatus;

	/**
	 * 是否开启打坐双倍 true 开启
	 *
	 * @return
	 */
	public String isDaZuoDouble() {
		if (MonsterManager.DaguaiDoubleTime != null && !MonsterManager.DaguaiDoubleTime.equals("")) {
			try {
				if (TimeUtil.checkRangeTime(MonsterManager.DaguaiDoubleTime)) {
					return MonsterManager.DaguaiDoubleTime;
				}
			} catch (Exception e) {
				logger.error(e);
			}
		}
		
		Q_globalBean global = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.DOUBLEXP_DAZUO.getValue());
		if (global != null) {
			String timeString = global.getQ_string_value();
			if (timeString!=null&&!timeString.equals("")) {
				String[] times = timeString.split(Symbol.FENHAO);
				for (String string : times) {
//					if (TimeUtil.isSatisfiedBy(string, System.currentTimeMillis())) {
//						return string;
//					}
					if (TimeUtil.checkRangeTime(string)) {
						return string;
					}
				}
			}
		}
		return null;
	}

	/**
	 * 发送双修申请 消息触发
	 *
	 * @param player
	 * @param target
	 */
	public void sendShuangXiuApply(Player player, long target) {
		if (!PlayerManager.getInstance().isOnline(target)) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉,对方已经离线"));
			return;
		}
		Player other = PlayerManager.getInstance().getOnLinePlayer(target);
		if (other == null) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉,对方已经离线"));
			return;
		}
		if (other.getSex() == player.getSex()) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉,不可以同性双修"));
			return;
		}

		if (!PlayerManager.getInstance().isSameMap(player, other)) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉,您与对方距离过远"));
			return;
		}
		if (MapUtils.countDistance(player.getPosition(), other.getPosition()) > MapUtils.GRID_BORDER * 2) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您与对方的距离过远"));
			return;
		}
		if (isRoleShuangXiu(other)) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉,对方正在与其他玩家双修中"));
			return;
		}
		Long time = other.getDazuoAcceptList().get(player.getId());
		if (time != null) {
			if (System.currentTimeMillis() - time < 10 * 1000) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您刚刚对该玩家发送过双修请求"));
				return;
			}
		}
		other.getDazuoAcceptList().put(player.getId(), System.currentTimeMillis());
		ResShuangXiuApplyMessage msg = new ResShuangXiuApplyMessage();
		msg.setRole(player.getId());
		MessageUtil.tell_player_message(other, msg);
//		MessageUtil.notify_player(player,Notifys.NORMAL,"您对玩家{1}发送了双修请求",other.getName());
	}

	/**
	 * 拒绝双修申请
	 *
	 * @param player
	 * @param sender
	 */
	public void refuseShuangXiu(Player player, Player sender) {
		if (sender != null) {
			player.getDazuoAcceptList().remove(sender.getId());
			MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("玩家{1}拒绝了您的双修请求"), player.getName());
		}
	}

	/**
	 * 开始打坐 单修或者与美人双修
	 *
	 * @param player
	 */
	public void startOrEndDaZuo(Player player) {
		if (isSit(player)) {
			breakDaZuo(player);
		} else {
			if (player.isDie()) {
				return;
			}
			if (!ManagerPool.countryManager.isSiegeWarandMap(player)) {
				if (isSit(player)) {
					breakDaZuo(player);
				}
				MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("本地图禁止打坐"));
				return;
			}
			if (PlayerState.SWIM.compare(player.getState())) {
				MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("游泳时不可以打坐"));
				return;
			}
			if (PlayerState.JUMP.compare(player.getState())) {
				return;
			}
			if (PlayerState.DOUBLEJUMP.compare(player.getState())) {
				return;
			}
			MapManager.getInstance().playerStopRun(player);
			//判断是否有美人
			Pet showPet = PetInfoManager.getInstance().getShowPet(player);
			if (showPet != null&&!showPet.isDie()) {
				PetOptManager.getInstance().petTransToOwner(showPet);
				int addtimer = 0;
				if (player.checkKingCityBuffOfKing() || player.checkKingCityBuffOfNormal()) {
					Q_buffBean buffModel = ManagerPool.dataManager.q_buffContainer.getMap().get(BuffConst.PETEMEDITATION);
					if (buffModel != null) {
						int kingcitydazuoAddCof = DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.KINGCITY_DAZUOSPEEDADDCOF.getValue()).getQ_int_value();
						addtimer = buffModel.getQ_effect_cooldown() - kingcitydazuoAddCof;
						if (addtimer < 0) {
							addtimer = 0;
						}
					}
				}
				BuffManager.getInstance().addBuff(player, player, BuffConst.PETEMEDITATION, 0, addtimer, 0, 0);
				player.setDazuoState(PETSHUANGXIU);
				player.setState(PlayerState.SIT);
				player.setDazuoBeginTime(System.currentTimeMillis());
				ResDazuoStateBroadCastMessage msg = new ResDazuoStateBroadCastMessage();
				msg.setState(player.getDazuoState());
				msg.setRoleAId(player.getId());
				msg.setRoleAX(player.getPosition().getX());
				msg.setRoleAY(player.getPosition().getY());
				msg.getRoleAPets().add(showPet.getId());
				MessageUtil.tell_round_message(player, msg);
				PetScriptManager.getInstance().petShuangXiu(showPet);

			} else {
				//单人打坐
				int addtimer = 0;
				if (player.checkKingCityBuffOfKing() || player.checkKingCityBuffOfNormal()) {
					Q_buffBean buffModel = ManagerPool.dataManager.q_buffContainer.getMap().get(BuffConst.SINGLEMEDITATION);
					if (buffModel != null) {
						int kingcitydazuoAddCof = DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.KINGCITY_DAZUOSPEEDADDCOF.getValue()).getQ_int_value();
						addtimer = buffModel.getQ_effect_cooldown() - kingcitydazuoAddCof;
						if (addtimer < 0) {
							addtimer = 0;
						}
					}
				}
				BuffManager.getInstance().addBuff(player, player, BuffConst.SINGLEMEDITATION, 0, addtimer, 0, 0);
				player.setDazuoState(SINGLEDAZUO);
				player.setState(PlayerState.SIT);
				player.setDazuoBeginTime(System.currentTimeMillis());
				ResDazuoStateBroadCastMessage msg = new ResDazuoStateBroadCastMessage();
				msg.setState(player.getDazuoState());
				msg.setRoleAId(player.getId());
				msg.setRoleAX(player.getPosition().getX());
				msg.setRoleAY(player.getPosition().getY());
				MessageUtil.tell_round_message(player, msg);
			}

		}
	}

	/**
	 * 接收双修申请开始双修
	 *
	 * @param player
	 * @param player2
	 */
	public void startShuangXiu(Player player, long senderid) {
		if (!ManagerPool.countryManager.isSiegeWarandMap(player)) {
			if (isSit(player)) {
				breakDaZuo(player);
			}
//			MessageUtil.notify_player(player, Notifys.NORMAL, "本地图禁止打坐");
			return;
		}
		if (!PlayerManager.getInstance().isOnline(senderid)) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉,对方已经离线"));
			return;
		}
		Player sender = PlayerManager.getInstance().getOnLinePlayer(senderid);
		if (sender == null) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉,对方已经离线"));
			return;
		}
		if (!PlayerManager.getInstance().isSameMap(player, sender)) {
			ResSetPositionMessage msg = new ResSetPositionMessage();
			msg.setPersonId(player.getId());
			msg.setPosition(player.getPosition());
			MessageUtil.tell_round_message(player, msg);
			msg = new ResSetPositionMessage();
			msg.setPersonId(sender.getId());
			msg.setPosition(sender.getPosition());
			MessageUtil.tell_round_message(sender, msg);
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉,您与对方距离过远"));
			return;
		}
		if (MapUtils.countDistance(player.getPosition(), sender.getPosition()) > MapUtils.GRID_BORDER * 2) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您与对方的距离过远"));
			return;
		}
		if (isRoleShuangXiu(sender)) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉,对方正在与其他玩家双修中"));
			return;
		}
		if (isRoleShuangXiu(player)) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您已经在跟其他玩家双修中了"));
			//己经处于双修状态
			return;
		}
		Long acceptTime = player.getDazuoAcceptList().get(senderid);
		if (acceptTime == null || System.currentTimeMillis() - acceptTime > SHUANGXIUTIMEOUT) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("申请超时"));
			return;
		}
		if (player.isDie()) {
			return;
		}
		if (sender.isDie()) {
			return;
		}
		if (PlayerState.SWIM.compare(player.getState())) {
			MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("很抱歉,游泳时不可以打坐"));
			return;
		}
		if (PlayerState.SWIM.compare(sender.getState())) {
			MessageUtil.notify_player(sender, Notifys.NORMAL, ResManager.getInstance().getString("很抱歉,游泳时不可以打坐"));
			return;
		}
		if (PlayerState.JUMP.compare(sender.getState())) {
			return;
		}
		if (PlayerState.DOUBLEJUMP.compare(sender.getState())) {
			return;
		}
		if (PlayerState.JUMP.compare(player.getState())) {
			return;
		}
		if (PlayerState.DOUBLEJUMP.compare(player.getState())) {
			return;
		}
		MapManager.getInstance().playerStopRun(player);
		MapManager.getInstance().playerStopRun(sender);
//		HorseManager.getInstance().unride(player);
//		HorseManager.getInstance().unride(sender);
		if (!isSit(player)) {
			player.setDazuoBeginTime(System.currentTimeMillis());
			player.setDazuoEruptCount(0);
			player.setDazuoEruptExp(0);
			player.setDazuoEruptZq(0);
		}
		player.setDazuotarget(sender.getId());
		player.getDazuoAcceptList().clear();
		player.setDazuoState(ROLESHUANGXIUE);
		player.setState(PlayerState.SIT);

		if (!isSit(sender)) {
			sender.setDazuoBeginTime(System.currentTimeMillis());
			sender.setDazuoEruptCount(0);
			sender.setDazuoEruptExp(0);
			sender.setDazuoEruptZq(0);
		}
		sender.setDazuotarget(player.getId());
		sender.getDazuoAcceptList().clear();
		sender.setDazuoState(ROLESHUANGXIUE);
		sender.setState(PlayerState.SIT);
		//如果是夫妻
		boolean isspouse = false;
		if (sender.getMarriageid() == player.getMarriageid() && sender.getMarriageid() > 0) {
			isspouse = true;
		}
		
		Q_buffBean buffModel = ManagerPool.dataManager.q_buffContainer.getMap().get(BuffConst.ROLESMEDITATION);
		int addtimer = buffModel.getQ_effect_cooldown();
		int cut = 0;
		if (player.checkKingCityBuffOfKing() || player.checkKingCityBuffOfNormal()) {
			cut = DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.KINGCITY_DAZUOSPEEDADDCOF.getValue()).getQ_int_value();
		}
		
		if (isspouse && player.getLevel() >= 40) {
			cut = cut + 1000;	//大于40级，而且是配偶
			ManagerPool.buffManager.addBuff(player, player, 9167, 0, 0, 0);//结婚技能 合和双修
		}
		if (addtimer-cut < 0) {
			addtimer = 0;
		}else {
			addtimer = addtimer-cut;
		}
		BuffManager.getInstance().addBuff(player, player, BuffConst.ROLESMEDITATION, 0, addtimer, 0, 0);
		
		addtimer = buffModel.getQ_effect_cooldown();
		cut = 0;
		if (sender.checkKingCityBuffOfKing() || sender.checkKingCityBuffOfNormal()) {
			cut = DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.KINGCITY_DAZUOSPEEDADDCOF.getValue()).getQ_int_value();
		}
		if (isspouse && sender.getLevel() >= 40) {
			cut = cut + 1000;//大于40级，而且是配偶
			ManagerPool.buffManager.addBuff(sender, sender, 9167, 0, 0, 0);//结婚技能 合和双修
		}
		if (addtimer-cut < 0) {
			addtimer = 0;
		}else {
			addtimer = addtimer-cut;
		}
		BuffManager.getInstance().addBuff(sender, sender, BuffConst.ROLESMEDITATION, 0, addtimer, 0, 0);
		
		ResDazuoStateBroadCastMessage msg = new ResDazuoStateBroadCastMessage();
		msg.setRoleAId(player.getId());
		Pet playerPet = PetInfoManager.getInstance().getShowPet(player);
		if (playerPet != null&&!playerPet.isDie()) {
			PetOptManager.getInstance().petTransToOwner(playerPet);
			msg.getRoleAPets().add(playerPet.getId());
			PetScriptManager.getInstance().petShuangXiu(playerPet);
		}
		msg.setRoleAX(player.getPosition().getX());
		msg.setRoleAY(player.getPosition().getY());
		msg.setRoleBId(sender.getId());
		Pet senderPet = PetInfoManager.getInstance().getShowPet(sender);
		msg.setRoleBX(sender.getPosition().getX());
		msg.setRoleBY(sender.getPosition().getY());
		if (senderPet != null&&!senderPet.isDie()) {
			PetOptManager.getInstance().petTransToOwner(senderPet);
			msg.getRoleBPets().add(senderPet.getId());
			PetScriptManager.getInstance().petShuangXiu(senderPet);
		}
		msg.setState(ROLESHUANGXIUE);
		//对起点区域和落点区域附近广播
		Map map = MapManager.getInstance().getMap(player);
		List<Area> startRounds = MapManager.getInstance().getRound(map, player.getPosition());
		HashSet<Area> round = new HashSet<Area>();
		round.addAll(startRounds);
		if(playerPet!=null&&!playerPet.isDie()){
			List<Area> petARound = MapManager.getInstance().getRound(map, playerPet.getPosition());
			round.addAll(petARound);
		}
		
		List<Area> endRounds = MapManager.getInstance().getRound(map, sender.getPosition());
		round.addAll(endRounds);
		if(senderPet!=null&&!senderPet.isDie()){
			List<Area> petBRound = MapManager.getInstance().getRound(map,senderPet.getPosition());
			round.addAll(petBRound);	
		}
		Iterator<Area> iter = round.iterator();
		while (iter.hasNext()) {
			Area area = (Area) iter.next();
			Iterator<Player> iterPlayer = area.getPlayers().iterator();
			while (iterPlayer.hasNext()) {
				Player other = (Player) iterPlayer.next();
				msg.getRoleId().add(other.getId());
			}
		}
		MessageUtil.send_to_gate(map.getSendId(),msg);
	}

	/**
	 * 美人出战
	 *
	 * @param player
	 */
	public void petShow(Player player) {
		if(player.isDie()){
			return;
		}
		if (!isSit(player)) {
			return;
		}
		Pet showPet = PetInfoManager.getInstance().getShowPet(player);
		if (showPet == null) {
			return;
		}
		if (isPetShuangXiu(player)) {
			//不可能的情况 出战时会先将之前的待宠收回  暂时不考虑多美人的情况
//			List<Pet> petList = player.getPetList();
		}
		if (isSingleSit(player)) {

			PetOptManager.getInstance().petTransToOwner(showPet);
			//BUFF变化
			int addtimer = 0;
			if (player.checkKingCityBuffOfKing() || player.checkKingCityBuffOfNormal()) {
				Q_buffBean buffModel = ManagerPool.dataManager.q_buffContainer.getMap().get(BuffConst.PETEMEDITATION);
				if (buffModel != null) {
					int kingcitydazuoAddCof = DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.KINGCITY_DAZUOSPEEDADDCOF.getValue()).getQ_int_value();
					addtimer = buffModel.getQ_effect_cooldown() - kingcitydazuoAddCof;
					if (addtimer < 0) {
						addtimer = 0;
					}
				}
			}
			BuffManager.getInstance().addBuff(player, player, BuffConst.PETEMEDITATION, 0, addtimer, 0, 0);
			player.setDazuoState(PETSHUANGXIU);
			player.setState(PlayerState.SIT);
			ResDazuoStateBroadCastMessage msg = new ResDazuoStateBroadCastMessage();
			msg.setState(player.getDazuoState());
			msg.setRoleAId(player.getId());
			msg.setRoleAX(player.getPosition().getX());
			msg.setRoleAY(player.getPosition().getY());
			if(showPet!=null)
			msg.getRoleAPets().add(showPet.getId());
			MessageUtil.tell_round_message(player, msg);
			ResDazuoEruptMessage msg2 = new ResDazuoEruptMessage();
			msg2.setDazuoexp(player.getDazuoExp());
			msg2.setDazuozq(player.getDazuoZq());
			msg2.setEruptCount(player.getDazuoEruptCount());
			msg2.setEruptExp(player.getDazuoEruptExp());
			msg2.setEruptZQ(player.getDazuoEruptZq());
			msg2.setDuration((int) ((System.currentTimeMillis() - player.getDazuoBeginTime()) / 1000));
			MessageUtil.tell_player_message(player, msg2);
		}
		if (isRoleShuangXiu(player)) {
			long dazuotarget = player.getDazuotarget();
			Player target = PlayerManager.getInstance().getOnLinePlayer(dazuotarget);
			if (target == null) {
				return;
			}
			PetOptManager.getInstance().petTransToOwner(showPet);
			Pet targetPet = PetInfoManager.getInstance().getShowPet(target);
			Pet ownerPet = PetInfoManager.getInstance().getShowPet(player);
			ResDazuoStateBroadCastMessage msg = new ResDazuoStateBroadCastMessage();
			msg.setRoleAId(player.getId());
			msg.setRoleAX(player.getPosition().getX());
			msg.setRoleAY(player.getPosition().getY());
			if(ownerPet!=null)
			msg.getRoleAPets().add(ownerPet.getId());
			msg.setRoleBId(target.getId());
			msg.setRoleBX(target.getPosition().getX());
			msg.setRoleBY(target.getPosition().getY());
			if(targetPet!=null)
			msg.getRoleBPets().add(targetPet.getId());
			msg.setState(player.getDazuoState());
			Map map = MapManager.getInstance().getMap(player);
			List<Area> startRounds = MapManager.getInstance().getRound(map, player.getPosition());
			HashSet<Area> round = new HashSet<Area>();
			round.addAll(startRounds);
			if(ownerPet!=null&&!ownerPet.isDie()){
				List<Area> petARound = MapManager.getInstance().getRound(map, ownerPet.getPosition());
				round.addAll(petARound);	
			}
			List<Area> endRounds = MapManager.getInstance().getRound(map, target.getPosition());
			round.addAll(endRounds);
			if(targetPet!=null&&!targetPet.isDie()){
				List<Area> petBRound = MapManager.getInstance().getRound(map, targetPet.getPosition());
				round.addAll(petBRound);				
			}
			Iterator<Area> iter = round.iterator();
			while (iter.hasNext()) {
				Area area = (Area) iter.next();
				Iterator<Player> iterPlayer = area.getPlayers().iterator();
				while (iterPlayer.hasNext()) {
					Player other = (Player) iterPlayer.next();
					msg.getRoleId().add(other.getId());
				}
			}

			MessageUtil.send_to_gate(map.getSendId(),msg);
			ResDazuoEruptMessage msg2 = new ResDazuoEruptMessage();
			msg2.setDazuoexp(player.getDazuoExp());
			msg2.setDazuozq(player.getDazuoZq());
			msg2.setEruptCount(player.getDazuoEruptCount());
			msg2.setEruptExp(player.getDazuoEruptExp());
			msg2.setEruptZQ(player.getDazuoEruptZq());
			msg2.setDuration((int) ((System.currentTimeMillis() - player.getDazuoBeginTime()) / 1000));
			ResDazuoEruptMessage msg3 = new ResDazuoEruptMessage();
			msg3.setDazuoexp(target.getDazuoExp());
			msg3.setDazuozq(target.getDazuoZq());
			msg3.setEruptCount(target.getDazuoEruptCount());
			msg3.setEruptExp(target.getDazuoEruptExp());
			msg3.setEruptZQ(target.getDazuoEruptZq());
			msg3.setDuration((int) ((System.currentTimeMillis() - target.getDazuoBeginTime()) / 1000));
			MessageUtil.tell_player_message(target, msg3);
		}
	}

	/**
	 * 美人收回 死亡等影响到双修的操作
	 *
	 * @param player
	 */
	public void petHide(Player player) {
		if (!isSit(player)) {
			return;
		}
		if (isPetShuangXiu(player)) {
			//BUFF变化
			int addtimer = 0;
			if (player.checkKingCityBuffOfKing() || player.checkKingCityBuffOfNormal()) {
				Q_buffBean buffModel = ManagerPool.dataManager.q_buffContainer.getMap().get(BuffConst.SINGLEMEDITATION);
				if (buffModel != null) {
					int kingcitydazuoAddCof = DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.KINGCITY_DAZUOSPEEDADDCOF.getValue()).getQ_int_value();
					addtimer = buffModel.getQ_effect_cooldown() - kingcitydazuoAddCof;
					if (addtimer < 0) {
						addtimer = 0;
					}
				}
			}
			BuffManager.getInstance().addBuff(player, player, BuffConst.SINGLEMEDITATION, 0, addtimer, 0, 0);
			player.setDazuoState(SINGLEDAZUO);
			player.setState(PlayerState.SIT);
			ResDazuoStateBroadCastMessage msg = new ResDazuoStateBroadCastMessage();
			msg.setState(player.getDazuoState());
			msg.setRoleAId(player.getId());
			msg.setRoleAX(player.getPosition().getX());
			msg.setRoleAY(player.getPosition().getY());
//			msg.getRoleAPets().add(showPet.getId());
			msg.setState(player.getDazuoState());
			MessageUtil.tell_round_message(player, msg);
			
			ResDazuoEruptMessage msg2 = new ResDazuoEruptMessage();
			msg2.setDazuoexp(player.getDazuoExp());
			msg2.setDazuozq(player.getDazuoZq());
			msg2.setEruptCount(player.getDazuoEruptCount());
			msg2.setEruptExp(player.getDazuoEruptExp());
			msg2.setEruptZQ(player.getDazuoEruptZq());
			msg2.setDuration((int) ((System.currentTimeMillis() - player.getDazuoBeginTime()) / 1000));
			MessageUtil.tell_player_message(player, msg2);
		}
		if (isRoleShuangXiu(player)) {
			long dazuotarget = player.getDazuotarget();
			Player target = PlayerManager.getInstance().getOnLinePlayer(dazuotarget);
			if (target == null) {
				return;
			}
			Pet targetPet = PetInfoManager.getInstance().getShowPet(target);
			Pet ownerPet=PetInfoManager.getInstance().getShowPet(player);
			ResDazuoStateBroadCastMessage msg = new ResDazuoStateBroadCastMessage();
			msg.setRoleAId(player.getId());
			msg.setRoleAX(player.getPosition().getX());
			msg.setRoleAY(player.getPosition().getY());
			msg.setRoleBId(target.getId());
			if(ownerPet!=null)
			msg.getRoleAPets().add(ownerPet.getId());
			msg.setRoleBX(target.getPosition().getX());
			msg.setRoleBY(target.getPosition().getY());
			if(targetPet!=null)
			msg.getRoleBPets().add(targetPet.getId());
			msg.setState(player.getDazuoState());
			Map map = MapManager.getInstance().getMap(player);
			List<Area> startRounds = MapManager.getInstance().getRound(map, player.getPosition());
			HashSet<Area> round = new HashSet<Area>();
			round.addAll(startRounds);
			if(ownerPet!=null){
				List<Area> petARound = MapManager.getInstance().getRound(map, ownerPet.getPosition());
				round.addAll(petARound);	
			}
			List<Area> endRounds = MapManager.getInstance().getRound(map, target.getPosition());
			round.addAll(endRounds);
			if(targetPet!=null){
				List<Area> petBRound = MapManager.getInstance().getRound(map, targetPet.getPosition());
				round.addAll(petBRound);	
			}
			Iterator<Area> iter = round.iterator();
			while (iter.hasNext()) {
				Area area = (Area) iter.next();
				Iterator<Player> iterPlayer = area.getPlayers().iterator();
				while (iterPlayer.hasNext()) {
					Player other = (Player) iterPlayer.next();
					msg.getRoleId().add(other.getId());
				}
			}
			MessageUtil.send_to_gate(map.getSendId(),msg);
			ResDazuoEruptMessage msg2 = new ResDazuoEruptMessage();
			msg2.setDazuoexp(player.getDazuoExp());
			msg2.setDazuozq(player.getDazuoZq());
			msg2.setEruptCount(player.getDazuoEruptCount());
			msg2.setEruptExp(player.getDazuoEruptExp());
			msg2.setEruptZQ(player.getDazuoEruptZq());
			msg2.setDuration((int) ((System.currentTimeMillis() - player.getDazuoBeginTime()) / 1000));
			MessageUtil.tell_player_message(player, msg2);
			ResDazuoEruptMessage msg3 = new ResDazuoEruptMessage();
			msg3.setDazuoexp(target.getDazuoExp());
			msg3.setDazuozq(target.getDazuoZq());
			msg3.setEruptCount(target.getDazuoEruptCount());
			msg3.setEruptExp(target.getDazuoEruptExp());
			msg3.setEruptZQ(target.getDazuoEruptZq());
			msg3.setDuration((int) ((System.currentTimeMillis() - player.getDazuoBeginTime()) / 1000));
			MessageUtil.tell_player_message(target, msg3);
		}

	}

	/**
	 * 打断打坐
	 *
	 * @param player
	 */
	public void breakDaZuo(Player player) {
		if (PlayerState.SWIM.compare(player.getState())) {
			//没在打坐或在游泳中
			return;
		}
//		if (PlayerState.SWIM.compare(player.getState())) {
//			int dazuoEruptExp = player.getDazuoEruptExp();
//			int dazuoEruptZq = player.getDazuoEruptZq();
//			if (dazuoEruptExp > 0 || dazuoEruptZq > 0) {
//				PlayerManager.getInstance().addExp(player, dazuoEruptExp);
//				PlayerManager.getInstance().addZhenqi(player, dazuoEruptZq);
//				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "获得游泳暴击经验{1}真气{2}", dazuoEruptExp + "", dazuoEruptZq + "");
//			}
//			player.setDazuotarget(0);
//			player.setDazuoEruptCount(0);
//			player.setDazuoBeginTime(0);
//			player.setDazuoEruptExp(0);
//			player.setDazuoEruptZq(0);
//			player.setDazuoExp(0);
//			player.setDazuoZq(0);
//			player.setState(PlayerState.STAND);
//			player.setDazuoState(UNDAZUO);
//			BuffManager.getInstance().removeByType(player, BuffType.SITINMEDITATION);
////			MessageUtil.notify_player(player, Notifys.NORMAL, "您退出了打坐状态");
//			ResDazuoStateBroadCastMessage msg = new ResDazuoStateBroadCastMessage();
//			msg.setRoleAId(player.getId());
//			msg.setState(player.getDazuoState());
//			MessageUtil.tell_round_message(player, msg);
//		}
		
		Map map = MapManager.getInstance().getMap(player);
		if(map==null){
			return;
		}
		if (isSingleSit(player)) {
			//退出单人打坐
			int dazuoEruptExp = player.getDazuoEruptExp();
			int dazuoEruptZq = player.getDazuoEruptZq();
			if (dazuoEruptExp > 0 || dazuoEruptZq > 0) {
				PlayerManager.getInstance().addExp(player, dazuoEruptExp,AttributeChangeReason.DAZUO);
				PlayerManager.getInstance().addZhenqi(player, dazuoEruptZq,AttributeChangeReason.DAZUO);
				//MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "获得打坐暴击经验{1}真气{2}", dazuoEruptExp + "", dazuoEruptZq + "");
				if (dazuoEruptZq != 0 && VipManager.getInstance().getPlayerVipId(player) == 0) {
					ParseUtil parseUtil = new ParseUtil();
					parseUtil.setValue(String.format(ResManager.getInstance().getString("您获得打坐暴击经验%d真气%d（{@}+真气0）"), dazuoEruptExp, dazuoEruptZq), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player), 2));
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, parseUtil.toString());
				} else {
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("您获得打坐暴击经验{1}真气{2}"), dazuoEruptExp + "", dazuoEruptZq + "");
				}
			}
			player.setDazuotarget(0);
			player.setDazuoEruptCount(0);
			player.setDazuoBeginTime(0);
			player.setDazuoEruptExp(0);
			player.setDazuoEruptZq(0);
			player.setDazuoExp(0);
			player.setDazuoZq(0);
			player.setState(PlayerState.STAND);
			player.setDazuoState(UNDAZUO);
			BuffManager.getInstance().removeByType(player, BuffType.SITINMEDITATION);
			ResDazuoStateBroadCastMessage msg = new ResDazuoStateBroadCastMessage();
			msg.setRoleAId(player.getId());
			msg.setState(player.getDazuoState());
			MessageUtil.tell_round_message(player, msg);
		}
		if (isPetShuangXiu(player)) {
			//退出美人双修
			int dazuoEruptExp = player.getDazuoEruptExp();
			int dazuoEruptZq = player.getDazuoEruptZq();
			if (dazuoEruptExp > 0 || dazuoEruptZq > 0) {
				PlayerManager.getInstance().addExp(player, dazuoEruptExp, AttributeChangeReason.DAZUO);
				PlayerManager.getInstance().addZhenqi(player, dazuoEruptZq,AttributeChangeReason.DAZUO);
				//MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "获得打坐暴击经验{1}真气{2}", dazuoEruptExp + "", dazuoEruptZq + "");
				if (dazuoEruptZq != 0 && VipManager.getInstance().getPlayerVipId(player) == 0) {
					ParseUtil parseUtil = new ParseUtil();
					parseUtil.setValue(String.format(ResManager.getInstance().getString("您获得打坐暴击经验%d真气%d（{@}+真气0）"), dazuoEruptExp, dazuoEruptZq), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player), 2));
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, parseUtil.toString());
				} else {
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("您获得打坐暴击经验{1}真气{2}"), dazuoEruptExp + "", dazuoEruptZq + "");
				}
			}
			player.setDazuotarget(0);
			player.setDazuoEruptCount(0);
			player.setDazuoBeginTime(0);
			player.setDazuoEruptExp(0);
			player.setDazuoEruptZq(0);
			player.setDazuoExp(0);
			player.setDazuoZq(0);
			player.setState(PlayerState.STAND);
			player.setDazuoState(UNDAZUO);
			BuffManager.getInstance().removeByType(player, BuffType.SITINMEDITATION);
//			MessageUtil.notify_player(player, Notifys.NORMAL, "您退出了双修状态");
			ResDazuoStateBroadCastMessage msg = new ResDazuoStateBroadCastMessage();
			msg.setRoleAId(player.getId());
			msg.setState(player.getDazuoState());
			Pet showPet = PetInfoManager.getInstance().getShowPet(player);
			
			List<Area> aRounds = MapManager.getInstance().getRound(map, player.getPosition());
			HashSet<Area> round = new HashSet<Area>();
			round.addAll(aRounds);
			if (showPet != null&&!showPet.isDie()) {
				List<Area> petRound = MapManager.getInstance().getRound(map, showPet.getPosition());
				round.addAll(petRound);
				msg.getRoleAPets().add(showPet.getId());
			}
			Iterator<Area> iter = round.iterator();
			while (iter.hasNext()) {
				Area area = (Area) iter.next();
				Iterator<Player> iterPlayer = area.getPlayers().iterator();
				while (iterPlayer.hasNext()) {
					Player scenePlayer = (Player) iterPlayer.next();
					msg.getRoleId().add(scenePlayer.getId());
				}
			}
			MessageUtil.send_to_gate(map.getSendId(),msg);
		}
		if (isRoleShuangXiu(player)) {
			//打断与玩家双修
			int dazuoEruptExp = player.getDazuoEruptExp();
			int dazuoEruptZq = player.getDazuoEruptZq();
			if (dazuoEruptExp > 0 || dazuoEruptZq > 0) {
				PlayerManager.getInstance().addExp(player, dazuoEruptExp, AttributeChangeReason.DAZUO);
				PlayerManager.getInstance().addZhenqi(player, dazuoEruptZq,AttributeChangeReason.DAZUO);
				//MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "获得打坐暴击经验{1}真气{2}", dazuoEruptExp + "", dazuoEruptZq + "");
				if (dazuoEruptZq != 0 && VipManager.getInstance().getPlayerVipId(player) == 0) {
					ParseUtil parseUtil = new ParseUtil();
					parseUtil.setValue(String.format(ResManager.getInstance().getString("您获得打坐暴击经验%d真气%d（{@}+真气0）"), dazuoEruptExp, dazuoEruptZq), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player), 2));
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, parseUtil.toString());
				} else {
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("您获得打坐暴击经验{1}真气{2}"), dazuoEruptExp + "", dazuoEruptZq + "");
				}
			}
			//打断双修
			Player other = PlayerManager.getInstance().getPlayer(player.getDazuotarget());

			int Exp = other.getDazuoEruptExp();
			int Zq = other.getDazuoEruptZq();
			if (Exp > 0 || Zq > 0) {
				PlayerManager.getInstance().addExp(other, Exp, AttributeChangeReason.DAZUO);
				PlayerManager.getInstance().addZhenqi(other, Zq,AttributeChangeReason.DAZUO);
				//MessageUtil.notify_player(other, Notifys.CHAT_SYSTEM, "获得打坐暴击经验{1}真气{2}", Exp + "", Zq + "");
				if (Zq != 0 && VipManager.getInstance().getPlayerVipId(other) == 0) {
					ParseUtil parseUtil = new ParseUtil();
					parseUtil.setValue(String.format(ResManager.getInstance().getString("您获得打坐暴击经验%d真气%d（{@}+真气0）"), Exp, Zq), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(other), 2));
					MessageUtil.notify_player(other, Notifys.CHAT_SYSTEM, parseUtil.toString());
				} else {
					MessageUtil.notify_player(other, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("您获得打坐暴击经验{1}真气{2}"), Exp + "", Zq + "");
				}
			}
			//删除和合双休BUFF
			ManagerPool.buffManager.removeByBuffId(player, 9167);
			ManagerPool.buffManager.removeByBuffId(other, 9167);
			
			player.setDazuotarget(0);
			player.setDazuoEruptCount(0);
			player.setDazuoBeginTime(0);
			player.setDazuoEruptExp(0);
			player.setDazuoEruptZq(0);
			player.setDazuoExp(0);
			player.setDazuoZq(0);
			player.setState(PlayerState.STAND);
			player.setDazuoState(UNDAZUO);
			other.setDazuotarget(0);
			other.setDazuoEruptCount(0);
			other.setDazuoBeginTime(0);
			other.setDazuoEruptExp(0);
			other.setDazuoEruptZq(0);
			other.setDazuoExp(0);
			other.setDazuoZq(0);
			other.setState(PlayerState.STAND);
			other.setDazuoState(UNDAZUO);
			BuffManager.getInstance().removeByType(player, BuffType.SITINMEDITATION);
			BuffManager.getInstance().removeByType(other, BuffType.SITINMEDITATION);
			MessageUtil.notify_player(other, Notifys.NORMAL, ResManager.getInstance().getString("由于对方中断了打坐，您退出了双修状态"));
			ResDazuoStateBroadCastMessage msg = new ResDazuoStateBroadCastMessage();
			msg.setRoleAId(player.getId());
			msg.setRoleAX(player.getPosition().getX());
			msg.setRoleAY(player.getPosition().getY());
			Pet showPetA = PetInfoManager.getInstance().getShowPet(player);
			if (showPetA != null&&!showPetA.isDie()) {
				msg.getRoleAPets().add(showPetA.getId());
			}
			msg.setRoleBId(other.getId());
			msg.setRoleBX(other.getPosition().getX());
			msg.setRoleBY(other.getPosition().getY());
			Pet showPetB = PetInfoManager.getInstance().getShowPet(other);
			if (showPetB != null&&!showPetB.isDie()) {
				msg.getRoleBPets().add(showPetB.getId());
			}
			msg.setState(UNDAZUO);
			//对起点区域和落点区域附近广播
			List<Area> aRounds = MapManager.getInstance().getRound(map, player.getPosition());
			HashSet<Area> round = new HashSet<Area>();
			round.addAll(aRounds);
			List<Area> bRounds = MapManager.getInstance().getRound(map, other.getPosition());
			round.addAll(bRounds);

			if (showPetA != null&&!showPetA.isDie()) {
				List<Area> petARounds = MapManager.getInstance().getRound(map, showPetA.getPosition());
				round.addAll(petARounds);
			}
			if (showPetB != null&&!showPetB.isDie()) {
				List<Area> petBRounds = MapManager.getInstance().getRound(map, showPetB.getPosition());
				round.addAll(petBRounds);
			}
			Iterator<Area> iter = round.iterator();
			while (iter.hasNext()) {
				Area area = (Area) iter.next();
				Iterator<Player> iterPlayer = area.getPlayers().iterator();
				while (iterPlayer.hasNext()) {
					Player scenePlayer = (Player) iterPlayer.next();
					msg.getRoleId().add(scenePlayer.getId());
				}
			}
			MessageUtil.send_to_gate(map.getSendId(),msg);
			//被打断的一方继续打座
			startOrEndDaZuo(other);
		}
	}

	/**
	 * 打坐buff
	 *
	 * @param player
	 * @return 0-未作用 1-作用成功 2-移除此buff
	 */
	public int buffAction(Player player) {
		Q_characterBean model = DataManager.getInstance().q_characterContainer.getMap().get(player.getLevel());
		if (model == null) {
			logger.error(player.getLevel() + "级没有配玩家升级所需经验数据", new Exception());
			return 0;
		}
		if (PlayerState.SWIM.compare(player.getState())) {
			dealswing(player);
		}
		if (isSingleSit(player)) {
			dealSingleSitProfit(player);
		}
		if (isPetShuangXiu(player)) {
			dealPetSitProfit(player);
		}
		if (isRoleShuangXiu(player)) {
			dealRoleSitProfit(player);
		}
		if (!isSingleSit(player) && !isPetShuangXiu(player) && !isRoleShuangXiu(player) && !PlayerState.SWIM.compare(player.getState())) {
			return 2;
//			BuffManager.getInstance().removeByType(player, BuffType.SITINMEDITATION);
		}

		return 1;
	}

	private void dealswing(Player player) {
		Q_characterBean model = DataManager.getInstance().q_characterContainer.getMap().get(player.getLevel());
		if (model == null) {
			logger.error(player.getLevel() + "级没有配玩家升级所需经验数据", new Exception());
			return;
		}
		int expbeishu=0;
		int zhenqibeishu = 0; 	//七夕活动，真气倍数
		if (getDaZuoDoubleStatus() == 1) {
			expbeishu=1;
			zhenqibeishu = 1;
		}
		int zhenQi = (int)Math.ceil((model.getQ_dazuozq() * ((double)player.getZhenQiMultiple() / 10000 + zhenqibeishu)));
		int exp=(int)Math.ceil(model.getQ_dazuoexp()*((double)player.getExpMultiple()/10000+expbeishu));
		
		PlayerManager.getInstance().addExp(player, exp, AttributeChangeReason.DAZUO);
		int addZhenqi = PlayerManager.getInstance().addZhenqi(player, zhenQi,AttributeChangeReason.DAZUO);
		if (addZhenqi != 0 && VipManager.getInstance().getPlayerVipId(player) == 0) {
			ParseUtil parseUtil = new ParseUtil();
			parseUtil.setValue(String.format(ResManager.getInstance().getString("您游泳获得真气%d（{@}+真气0）"), addZhenqi), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player), 2));
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, parseUtil.toString());
		} else {
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("您游泳获得真气{1}"), addZhenqi + "");
		}
		
		
		player.setDazuoZq(player.getDazuoZq()+addZhenqi);
		player.setDazuoExp(player.getDazuoExp()+exp);
		if (!PlayerManager.getInstance().isFullHp(player)) {
			int addhp = model.getQ_dazuohp();
			if (player.getHp() + addhp > player.getMaxHp()) {
				player.setHp(player.getMaxHp());
			} else {
				player.setHp(player.getHp() + addhp);
			}
			int min = player.getMaxHp() * 2000/ Global.MAX_PROBABILITY;
			if(addhp >= min){
				logger.error("玩家：" + player.getName() + "(" + player.getId() + ")回复生命" + addhp + ",因为游泳");
			}
			ManagerPool.playerManager.onHpChange(player);
		}
		if (!PlayerManager.getInstance().isFullMp(player)) {
			int addmp = model.getQ_dazuomp();
			if (player.getMp() + addmp > player.getMaxMp()) {
				player.setMp(player.getMaxMp());
			} else {
				player.setMp(player.getMp() + addmp);
			}
			ManagerPool.playerManager.onMpChange(player);
		}
		if (!PlayerManager.getInstance().isFullSp(player)) {
			int addsp = model.getQ_dazuosp();
			if (player.getSp() + addsp > player.getMaxSp()) {
				player.setSp(player.getMaxSp());
			} else {
				player.setSp(player.getSp() + addsp);
			}
			ManagerPool.playerManager.onSpChange(player);
		}
		if (player.getDazuoEruptCount() < Global.ONCEDAZUO_MAXERUPTCOUNT) {
			if (TimeUtil.isNowSatisfiedBy(Global.DAZUO_NIGHT_TIME)) {
				//夜间
				if (RandomUtils.defaultIsGenerate(model.getQ_erupt_night_prob())) {
					//暴击
					player.setDazuoEruptCount(player.getDazuoEruptCount() + 1);
					player.setDazuoEruptExp(player.getDazuoEruptExp() + model.getQ_erupt_night_exp());
					player.setDazuoEruptZq(player.getDazuoEruptZq() + model.getQ_erupt_night_zq());
				}
			} else {
				//白天
				if (RandomUtils.defaultIsGenerate(model.getQ_eurpt_day_prob())) {
					player.setDazuoEruptCount(player.getDazuoEruptCount() + 1);
					player.setDazuoEruptExp(player.getDazuoEruptExp() + model.getQ_erupt_day_exp());
					player.setDazuoEruptZq(player.getDazuoEruptZq() + model.getQ_erupt_day_zq());
					//暴击					
				}
			}
		}
		ResDazuoEruptMessage msg = new ResDazuoEruptMessage();
		msg.setDazuoexp(player.getDazuoExp());
		msg.setDazuozq(player.getDazuoZq());
		msg.setEruptCount(player.getDazuoEruptCount());
		msg.setEruptExp(player.getDazuoEruptExp());
		msg.setEruptZQ(player.getDazuoEruptZq());
		msg.setDuration((int) ((System.currentTimeMillis() - player.getDazuoBeginTime()) / 1000));
		MessageUtil.tell_player_message(player, msg);
	}

	/**
	 * 单人打坐收益
	 *
	 * @param player
	 */
	private void dealSingleSitProfit(Player player) {
		Q_characterBean model = DataManager.getInstance().q_characterContainer.getMap().get(player.getLevel());
		if (model == null) {
			logger.error(player.getLevel() + "级没有配玩家升级所需经验数据", new Exception());
			return;
		}

		int zhenqibeishu = 0; 	//七夕活动，真气倍数
		int expbeishu=0;
		if (getDaZuoDoubleStatus() == 1) {
			expbeishu=1;
			zhenqibeishu = 1;
		}
		
		int zhenQi = (int)Math.ceil((model.getQ_dazuozq() * ((double)player.getZhenQiMultiple() / 10000 + zhenqibeishu)));
		int exp=(int)Math.ceil(model.getQ_dazuoexp()*((double)player.getExpMultiple()/10000+expbeishu));
		
		PlayerManager.getInstance().addExp(player, exp, AttributeChangeReason.DAZUO);
		int addZhenqi = PlayerManager.getInstance().addZhenqi(player, zhenQi,AttributeChangeReason.DAZUO);
		if (addZhenqi != 0 && VipManager.getInstance().getPlayerVipId(player) == 0) {
			ParseUtil parseUtil = new ParseUtil();
			parseUtil.setValue(String.format(ResManager.getInstance().getString("您打坐获得真气%d（{@}+真气0）"), addZhenqi), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player), 2));
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, parseUtil.toString());
		} else {
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("您打坐获得真气{1}"), addZhenqi + "");
		}
		
		player.setDazuoExp(player.getDazuoExp()+exp);
		player.setDazuoZq(player.getDazuoZq()+addZhenqi);
		if (!PlayerManager.getInstance().isFullHp(player)) {
			int addhp = model.getQ_dazuohp();
			if (player.getHp() + addhp > player.getMaxHp()) {
				player.setHp(player.getMaxHp());
			} else {
				player.setHp(player.getHp() + addhp);
			}
			int min = player.getMaxHp() * 2000/ Global.MAX_PROBABILITY;
			if(addhp >= min){
				logger.error("玩家：" + player.getName() + "(" + player.getId() + ")回复生命" + addhp + ",因为打坐");
			}
			ManagerPool.playerManager.onHpChange(player);
		}
		if (!PlayerManager.getInstance().isFullMp(player)) {
			int addmp = model.getQ_dazuomp();
			if (player.getMp() + addmp > player.getMaxMp()) {
				player.setMp(player.getMaxMp());
			} else {
				player.setMp(player.getMp() + addmp);
			}
			ManagerPool.playerManager.onMpChange(player);
		}
		if (!PlayerManager.getInstance().isFullSp(player)) {
			int addsp = model.getQ_dazuosp();
			if (player.getSp() + addsp > player.getMaxSp()) {
				player.setSp(player.getMaxSp());
			} else {
				player.setSp(player.getSp() + addsp);
			}
			ManagerPool.playerManager.onSpChange(player);
		}
		if (player.getDazuoEruptCount() < Global.ONCEDAZUO_MAXERUPTCOUNT) {
			if (TimeUtil.isNowSatisfiedBy(Global.DAZUO_NIGHT_TIME)) {
				//夜间
				if (RandomUtils.defaultIsGenerate(model.getQ_erupt_night_prob())) {
					//暴击
					player.setDazuoEruptCount(player.getDazuoEruptCount() + 1);
					player.setDazuoEruptExp(player.getDazuoEruptExp() + model.getQ_erupt_night_exp());
					player.setDazuoEruptZq(player.getDazuoEruptZq() + model.getQ_erupt_night_zq());
				}
			} else {
				//白天
				if (RandomUtils.defaultIsGenerate(model.getQ_eurpt_day_prob())) {
					player.setDazuoEruptCount(player.getDazuoEruptCount() + 1);
					player.setDazuoEruptExp(player.getDazuoEruptExp() + model.getQ_erupt_day_exp());
					player.setDazuoEruptZq(player.getDazuoEruptZq() + model.getQ_erupt_day_zq());
					//暴击					
				}
			}
		}
		ResDazuoEruptMessage msg = new ResDazuoEruptMessage();
		msg.setDazuoexp(player.getDazuoExp());
		msg.setDazuozq(player.getDazuoZq());
		msg.setEruptCount(player.getDazuoEruptCount());
		msg.setEruptExp(player.getDazuoEruptExp());
		msg.setEruptZQ(player.getDazuoEruptZq());
		msg.setDuration((int) ((System.currentTimeMillis() - player.getDazuoBeginTime()) / 1000));
		MessageUtil.tell_player_message(player, msg);
	}

	/**
	 * 宠物双修收益
	 *
	 * @param player
	 */
	private void dealPetSitProfit(Player player) {
		Q_characterBean model = DataManager.getInstance().q_characterContainer.getMap().get(player.getLevel());
		if (model == null) {
			logger.error(player.getLevel() + "级没有配玩家升级所需经验数据", new Exception());
			return;
		}
		int zhenqibeishu = 0; 	//七夕活动，真气倍数
		int expbeishu=0;
		if (getDaZuoDoubleStatus() == 1) {
			expbeishu=1;
			zhenqibeishu = 1;
		}
		
		int zhenQi = (int)Math.ceil((model.getQ_pet_sxzq() * ((double)player.getZhenQiMultiple() / 10000 + zhenqibeishu)));
		int exp=(int)Math.ceil( model.getQ_pet_sxexp()*((double)player.getExpMultiple()/10000+expbeishu));
		
		PlayerManager.getInstance().addExp(player, exp, AttributeChangeReason.DAZUO);
		int addZhenqi = PlayerManager.getInstance().addZhenqi(player, zhenQi,AttributeChangeReason.DAZUO);
		if (addZhenqi != 0 && VipManager.getInstance().getPlayerVipId(player) == 0) {
			ParseUtil parseUtil = new ParseUtil();
			parseUtil.setValue(String.format(ResManager.getInstance().getString("您打坐获得真气%d（{@}+真气0）"), addZhenqi), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player), 2));
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, parseUtil.toString());
		} else {
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("您打坐获得真气{1}"), addZhenqi + "");
		}
		
		player.setDazuoZq(player.getDazuoZq()+addZhenqi);
		player.setDazuoExp(player.getDazuoExp()+exp);
		if (!PlayerManager.getInstance().isFullHp(player)) {
			int addhp = model.getQ_pet_sxhp();
			if (player.getHp() + addhp > player.getMaxHp()) {
				player.setHp(player.getMaxHp());
			} else {
				player.setHp(player.getHp() + addhp);
			}
			int min = player.getMaxHp() * 2000/ Global.MAX_PROBABILITY;
			if(addhp >= min){
				logger.error("玩家：" + player.getName() + "(" + player.getId() + ")回复生命" + addhp + ",因为和宠物打坐");
			}
			ManagerPool.playerManager.onHpChange(player);
		}
		if (!PlayerManager.getInstance().isFullMp(player)) {
			int addmp = model.getQ_pet_sxmp();
			if (player.getMp() + addmp > player.getMaxMp()) {
				player.setMp(player.getMaxMp());
			} else {
				player.setMp(player.getMp() + addmp);
			}
			ManagerPool.playerManager.onMpChange(player);
		}
		if (!PlayerManager.getInstance().isFullSp(player)) {
			int addsp = model.getQ_pet_sxsp();
			if (player.getSp() + addsp > player.getMaxSp()) {
				player.setSp(player.getMaxSp());
			} else {
				player.setSp(player.getSp() + addsp);
			}
			ManagerPool.playerManager.onSpChange(player);
		}



		if (player.getDazuoEruptCount() < Global.ONCEDAZUO_MAXERUPTCOUNT) {
			if (TimeUtil.isNowSatisfiedBy(Global.DAZUO_NIGHT_TIME)) {
				//夜间
				if (RandomUtils.defaultIsGenerate(model.getQ_petnightsx_prob())) {
					//暴击
					player.setDazuoEruptCount(player.getDazuoEruptCount() + 1);
					player.setDazuoEruptExp(player.getDazuoEruptExp() + model.getQ_petnightsx_exp());
					player.setDazuoEruptZq(player.getDazuoEruptZq() + model.getQ_petnightsx_zq());
				}
			} else {
				//白天
				if (RandomUtils.defaultIsGenerate(model.getQ_eurpt_day_prob())) {
					player.setDazuoEruptCount(player.getDazuoEruptCount() + 1);
					player.setDazuoEruptExp(player.getDazuoEruptExp() + model.getQ_petdaysx_exp());
					player.setDazuoEruptZq(player.getDazuoEruptZq() + model.getQ_petdaysx_zq());
					//暴击					
				}
			}
		}
		ResDazuoEruptMessage msg = new ResDazuoEruptMessage();
		msg.setDazuoexp(player.getDazuoExp());
		msg.setDazuozq(player.getDazuoZq());
		msg.setEruptCount(player.getDazuoEruptCount());
		msg.setEruptExp(player.getDazuoEruptExp());
		msg.setEruptZQ(player.getDazuoEruptZq());
		msg.setDuration((int) ((System.currentTimeMillis() - player.getDazuoBeginTime()) / 1000));
		MessageUtil.tell_player_message(player, msg);
	}

	/**
	 * 玩家双修收益
	 *
	 * @param player
	 */
	private void dealRoleSitProfit(Player player) {
		Q_characterBean model = DataManager.getInstance().q_characterContainer.getMap().get(player.getLevel());
		if (model == null) {
			logger.error(player.getLevel() + "级没有配玩家升级所需经验数据", new Exception());
			return;
		}
		int zhenqibeishu = 0; 	//七夕活动，真气倍数
		int expbeishu=0;
		if (getDaZuoDoubleStatus() == 1) {
			expbeishu=1;
			zhenqibeishu = 1;
		}
		
		int zhenQi = (int)Math.ceil((model.getQ_role_sxzq() * ((double)player.getZhenQiMultiple() / 10000 + zhenqibeishu)));
		int exp=(int)Math.ceil( model.getQ_role_sxexp()*((double)player.getExpMultiple()/10000+expbeishu));
		
		PlayerManager.getInstance().addExp(player, exp, AttributeChangeReason.DAZUO);
		int addZhenqi = PlayerManager.getInstance().addZhenqi(player, zhenQi,AttributeChangeReason.DAZUO);
		if (addZhenqi != 0 && VipManager.getInstance().getPlayerVipId(player) == 0) {
			ParseUtil parseUtil = new ParseUtil();
			parseUtil.setValue(String.format(ResManager.getInstance().getString("您打坐获得真气%d（{@}+真气0）"), addZhenqi), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player), 2));
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, parseUtil.toString());
		} else {
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("您打坐获得真气{1}"), addZhenqi + "");
		}

		player.setDazuoExp(player.getDazuoExp()+exp);
		player.setDazuoZq(player.getDazuoZq()+addZhenqi);
		if (!PlayerManager.getInstance().isFullHp(player)) {
			int addhp = model.getQ_role_sxhp();
			if (player.getHp() + addhp > player.getMaxHp()) {
				player.setHp(player.getMaxHp());
			} else {
				player.setHp(player.getHp() + addhp);
			}
			int min = player.getMaxHp() * 2000/ Global.MAX_PROBABILITY;
			if(addhp >= min){
				logger.error("玩家：" + player.getName() + "(" + player.getId() + ")回复生命" + addhp + ",因为和玩家打坐");
			}
			ManagerPool.playerManager.onHpChange(player);
		}
		if (!PlayerManager.getInstance().isFullMp(player)) {
			int addmp = model.getQ_role_sxmp();
			if (player.getMp() + addmp > player.getMaxMp()) {
				player.setMp(player.getMaxMp());
			} else {
				player.setMp(player.getMp() + addmp);
			}
			ManagerPool.playerManager.onMpChange(player);
		}
		if (!PlayerManager.getInstance().isFullSp(player)) {
			int addsp = model.getQ_role_sxsp();
			if (player.getSp() + addsp > player.getMaxSp()) {
				player.setSp(player.getMaxSp());
			} else {
				player.setSp(player.getSp() + addsp);
			}
			ManagerPool.playerManager.onSpChange(player);
		}
		if (player.getDazuoEruptCount() < Global.ONCEDAZUO_MAXERUPTCOUNT) {
			if (TimeUtil.isNowSatisfiedBy(Global.DAZUO_NIGHT_TIME)) {
				//夜间
				if (RandomUtils.defaultIsGenerate(model.getQ_rolenightsx_prob())) {
					//暴击
					player.setDazuoEruptCount(player.getDazuoEruptCount() + 1);
					player.setDazuoEruptExp(player.getDazuoEruptExp() + model.getQ_rolenightsx_exp());
					player.setDazuoEruptZq(player.getDazuoEruptZq() + model.getQ_rolenightsx_zq());
				}
			} else {
				//白天
				if (RandomUtils.defaultIsGenerate(model.getQ_eurpt_day_prob())) {
					player.setDazuoEruptCount(player.getDazuoEruptCount() + 1);
					player.setDazuoEruptExp(player.getDazuoEruptExp() + model.getQ_roledaysx_exp());
					player.setDazuoEruptZq(player.getDazuoEruptZq() + model.getQ_roledaysx_zq());
					//暴击					
				}
			}
		}
		ResDazuoEruptMessage msg = new ResDazuoEruptMessage();
		msg.setDazuoexp(player.getDazuoExp());
		msg.setDazuozq(player.getDazuoZq());
		msg.setEruptCount(player.getDazuoEruptCount());
		msg.setEruptExp(player.getDazuoEruptExp());
		msg.setEruptZQ(player.getDazuoEruptZq());
		msg.setDuration((int) ((System.currentTimeMillis() - player.getDazuoBeginTime()) / 1000));
		MessageUtil.tell_player_message(player, msg);
	}

	/**
	 * 是否在打坐
	 *
	 * @return
	 */
	public boolean isSit(Player role) {
		if (PlayerState.SIT.compare(role.getState())) {
			//没在打坐中
			return true;
		}
		return false;
	}

	/**
	 * 是否单人打坐
	 *
	 * @param role
	 * @return
	 */
	public boolean isSingleSit(Player role) {
		if (role.getDazuoState() == SINGLEDAZUO) {
			return true;
		}
//		
//		
//		List<Buff> buffByType = BuffManager.getInstance().getBuffByType(role, BuffType.SITINMEDITATION);
//		if(buffByType!=null&&buffByType.size()>=0){
//			for (Buff buff : buffByType) {
//				if(buff.getModelId()==BuffConst.SINGLEMEDITATION){
//					return true;
//				}
//			}
//		}		
		return false;
	}

	/**
	 * 是否与宠物双修中
	 *
	 * @param role
	 * @return
	 */
	public boolean isPetShuangXiu(Player role) {
		if (role.getDazuoState() == PETSHUANGXIU) {
			return true;
		}
//		List<Buff> buffByType = BuffManager.getInstance().getBuffByType(role, BuffType.SITINMEDITATION);
//		if(buffByType!=null&&buffByType.size()>=0){
//			for (Buff buff : buffByType) {
//				if(buff.getModelId()==BuffConst.PETEMEDITATION){
//					return true;
//				}
//			}
//		}	
		return false;
	}

	/**
	 * 是否与玩家双修中
	 *
	 * @param role
	 * @return
	 */
	public boolean isRoleShuangXiu(Player role) {
		if (role.getDazuoState() == ROLESHUANGXIUE) {
			return true;
		}
//		List<Buff> buffByType = BuffManager.getInstance().getBuffByType(role, BuffType.SITINMEDITATION);
//		if(buffByType!=null&&buffByType.size()>=0){
//			for (Buff buff : buffByType) {
//				if(buff.getModelId()==BuffConst.ROLESMEDITATION){
//					return true;
//				}
//			}
//		}
		return false;
	}

	public byte getDaZuoDoubleStatus() {
		return DaZuoDoubleStatus;
	}

	public void setDaZuoDoubleStatus(byte daZuoDoubleStatus) {
		DaZuoDoubleStatus = daZuoDoubleStatus;
	}
}
