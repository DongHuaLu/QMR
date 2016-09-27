package com.game.buff.manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.buff.bean.BuffInfo;
import com.game.buff.bean.BuffPara;
import com.game.buff.message.ResAddBuffMessage;
import com.game.buff.message.ResBuffInfoMessage;
import com.game.buff.message.ResBuffsMessage;
import com.game.buff.message.ResChangeBuffMessage;
import com.game.buff.message.ResRemoveBuffMessage;
import com.game.buff.script.IBuffScript;
import com.game.buff.structs.AttributeBuff;
import com.game.buff.structs.Buff;
import com.game.buff.structs.BuffConst;
import com.game.buff.structs.BuffParaType;
import com.game.buff.structs.BuffType;
import com.game.buff.structs.HitBuff;
import com.game.buff.structs.KillBuff;
import com.game.buff.structs.StateBuff;
import com.game.buff.structs.TimeBuff;
import com.game.buff.structs.TriggerBuff;
import com.game.buff.structs.TriggerType;
import com.game.bugtrace.manager.BugTraceManager;
import com.game.config.Config;
import com.game.data.bean.Q_buffBean;
import com.game.data.bean.Q_vipBean;
import com.game.data.manager.DataManager;
import com.game.fight.structs.Fighter;
import com.game.fight.structs.FighterState;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.structs.IMapObject;
import com.game.monster.structs.Monster;
import com.game.pet.struts.Pet;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttributeType;
import com.game.player.structs.PlayerState;
import com.game.prompt.structs.Notifys;
import com.game.utils.Global;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.vip.manager.VipManager;

public class BuffManager {

	protected Logger log = Logger.getLogger(BuffManager.class);
	private static Object obj = new Object();
	//玩家管理类实例
	private static BuffManager manager;
	
	//龙元武学buff
	private HashSet<Integer> longyuanBuffs = new HashSet<Integer>();

	private BuffManager() {
		longyuanBuffs.add(10010);
		longyuanBuffs.add(10011);
		longyuanBuffs.add(10012);
		longyuanBuffs.add(10013);
		longyuanBuffs.add(10014);
		longyuanBuffs.add(10015);
		longyuanBuffs.add(10016);
		longyuanBuffs.add(40032);
		longyuanBuffs.add(40034);
	}

	public static BuffManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new BuffManager();
			}
		}
		return manager;
	}

	/**
	 * 添加buff
	 *
	 * @param source 来源
	 * @param target 目标
	 * @param buffId buff编号
	 * @param total	增加时间
	 * @param addtion 增加效果
	 * @param addtionPercent 增加效果百分比
	 * @return
	 */
	public int addBuff(Fighter source, Fighter target, int buffId, long total, int addtion, int addtionPercent) {
		return addBuff(source, target, buffId, 1, total, 0, addtion, addtionPercent);
	}

	/**
	 * 添加buff
	 *
	 * @param source 来源
	 * @param target 目标
	 * @param buffId buff编号
	 * @param total	增加时间
	 * @param addtimer 间隔时间
	 * @param addtion 增加效果
	 * @param addtionPercent 增加效果百分比
	 * @return
	 */
	public int addBuff(Fighter source, Fighter target, int buffId, long total, int addtimer, int addtion, int addtionPercent) {
		return addBuff(source, target, buffId, 1, total, addtimer, addtion, addtionPercent);
	}
	
	/**
	 * 添加buff
	 *
	 * @param source 来源
	 * @param target 目标
	 * @param buffId buff编号
	 * @param num 使用数量
	 * @param total	增加时间
	 * @param addtimer 间隔时间
	 * @param addtion 增加效果
	 * @param addtionPercent 增加效果百分比
	 * @return
	 */
	public int addBuff(Fighter source, Fighter target, int buffId, int num, long total, int addtimer, int addtion, int addtionPercent) {
		//获取BUFF模型
		Q_buffBean buffModel = ManagerPool.dataManager.q_buffContainer.getMap().get(buffId);
		if (buffModel == null) {
			if (source != null && buffId == BuffConst.KINGCITY_KING) {
				BugTraceManager.getInstance().trace(source.getId(), source.getName(), "秦王BUFF未加载", "buff model不存在:" + buffId);
			}
			return 0;
		}
		
		//判断BUFF作用者是否死亡
		if (buffModel.getQ_target() == 1 && source.isDie()) {
			if (buffId == BuffConst.KINGCITY_KING) {
				BugTraceManager.getInstance().trace(source.getId(), source.getName(), "秦王BUFF未加载", "玩家已经死亡");
			}
			return 0;
		} else if (buffModel.getQ_target() == 2 && target.isDie()) {
			if (buffId == BuffConst.KINGCITY_KING) {
				BugTraceManager.getInstance().trace(target.getId(), source.getName(), "秦王BUFF未加载", "玩家已经死亡");
			}
			return 0;
		}

		Fighter adder = null;
		if (buffModel.getQ_target() == 1 || buffModel.getQ_target() == 4) {
			adder = source;
		} else if(buffModel.getQ_target() == 2) {
			adder = target;
		} else if(buffModel.getQ_target() == 5 && source instanceof Pet) {
			Player player = ManagerPool.playerManager.getPlayer(((Pet) source).getOwnerId());
			adder = player;
		}
		
		if(adder==null){
			log.error("Buff(" + buffId + ")->Source(" + source + ")->Target(" + target + "):not have adder");
			return 0;
		}
		//只对玩家有效
		if(buffModel.getQ_target_type()==1 && !(adder instanceof Player)){
			log.error("加buff失败:只对玩家有效(玩家:" + target.getId() + ";模型:" + buffId + ")");
			return 0;
		}
		//按类型获取Buff
		Buff buff = getBuff(buffModel.getQ_action_type(), buffId);
		buff.setId(Config.getId());
		//buff类型
		buff.setActionType(buffModel.getQ_action_type());
		//buff模型id
		buff.setModelId(buffId);
		//替换等级
		buff.setReplaceLevel(buffModel.getQ_replace_level());
		//开始时间
		buff.setStart(System.currentTimeMillis());
		//持续时间 -1永远存在
		if (buffModel.getQ_effect_time() != -1) {
			buff.setTotalTime((long)buffModel.getQ_effect_time() * 1000 + total);
			//VIP对增益BUFF的加成
			if(source instanceof Player){
				int vipid = VipManager.getInstance().getPlayerVipId((Player)source);  //玩家VIP等级
				if( vipid>0){ //是VIP并且要加的BUFF不是VIPBUFF
					if(buffModel.getQ_vip_bonus()==1){  //对增益BUFF计算vip加成 
						Q_vipBean bean = DataManager.getInstance().q_vipContainer.getMap().get(vipid);
						buff.setTotalTime((long)(buff.getTotalTime() * ((double)(10000+bean.getQ_pos_buff())/(double)10000)));
					}
				}
			}
			
			if(FighterState.LINGLONG.compare(adder.getFightState())){
				if(buffModel.getQ_effect_type()==2 && check(buffModel, BuffConst.LINGLONG_BUFF)){
					Q_buffBean effectModel = ManagerPool.dataManager.q_buffContainer.getMap().get(BuffConst.LINGLONG_BUFF);
					if(effectModel!=null){
						buff.setTotalTime((long)(buff.getTotalTime() * ((double)(10000+effectModel.getQ_effect_ratio())/(double)10000)));
						buff.getBackups().put(String.valueOf(BuffConst.LINGLONG_BUFF), "1");
					}
				}
			}
			
			if(FighterState.ZHEYING.compare(adder.getFightState())){
				if(buffModel.getQ_effect_type()==1 && check(buffModel, BuffConst.ZHEYING_BUFF)){
					log.error("加buff失败:遮影(玩家:" + adder.getId() + ";模型:" + buffId + ")");
					if (buffId == BuffConst.KINGCITY_KING) {
						BugTraceManager.getInstance().trace(target.getId(), source.getName(), "秦王BUFF未加载", "遮影");
					}
					return 0;
				}
			}
			
			if (buff.getTotalTime() < 1000) {
				log.error("加buff失败:时间小于1000(玩家:" + target.getId() + ";模型:" + buffId + ")");
				if (buffId == BuffConst.KINGCITY_KING) {
					BugTraceManager.getInstance().trace(target.getId(), source.getName(), "秦王BUFF未加载", "时间小于1000");
				}
				return 0;
			}
		} else {
			buff.setTotalTime(-1);
		}

		//是否下线计时
		buff.setCount(buffModel.getQ_Line_time());
		//总持续时间
		buff.setTotalRemainTime(buff.getTotalTime());
		//间隔时间
		buff.setTimer((addtimer==0)?buffModel.getQ_effect_cooldown():addtimer);
		//剩余时间
		buff.setRemain(buff.getTimer());
		//来源
		buff.setSource(source.getId());
		//来源类型 0-玩家 1-宠物 2-怪物
		if (source instanceof Monster) {
			buff.setSourceType(2);
		}
		//值加成
		buff.setValue(addtion);
		//比例加成
		buff.setPercent(addtionPercent);
		//参数
		buff.setParameter(buffModel.getQ_effect_maxvalue());
		//生命池等加倍
		if(buff.getModelId()==1027){
			List<Buff> buffs = getBuffByModelId(adder, 1407);
			if(buffs!=null && buffs.size()>0){
				buff.setParameter(buff.getParameter() * 2);
			}
		}
		//内力池加倍
		else if(buff.getModelId()==1028){
			List<Buff> buffs = getBuffByModelId(adder, 1408);
			if(buffs!=null && buffs.size()>0){
				buff.setParameter(buff.getParameter() * 2);
			}
		}
		//叠加层数
		buff.setOverlay(num);
		//参数数组
//		buff.getParameters().add(buffModel.getQ_Bonus_skill());
		//计算成功几率
		int percent = buffModel.getQ_trigger_prob();
		if (RandomUtils.random(Global.MAX_PROBABILITY) >= percent) {
			//添加失败
			log.error("加buff失败:未命中成功几率(玩家:" + target.getId() + ";模型:" + buffId + ")");
			if (buffId == BuffConst.KINGCITY_KING) {
				BugTraceManager.getInstance().trace(adder.getId(), source.getName(), "秦王BUFF未加载", "buff未命中");
			}
			return 0;
		}
		
		if (buff instanceof TimeBuff && buff.getTimer() == 0) {
			//即时作用类buff
			int ret = buff.action(source, adder);
			if (ret == 0) {
				log.error("加buff失败:action失败(玩家:" + target.getId() + ";模型:" + buffId + ")");
			}
			if (buffId == BuffConst.KINGCITY_KING) {
				BugTraceManager.getInstance().trace(adder.getId(), source.getName(), "秦王BUFF未加载", "buff时间为0");
			}
			return ret;
		} else {
			//获取对象身上同类Buff
//			List<Buff> buffs = getBuffByType(adder, buff.getActionType());
			List<Buff> buffs = getBuffByReplaceLevel(adder, buff.getModelId(), buff.getReplaceLevel());
			if (buffs.size() > 0) {
				//可重复Buff
				if (buffModel.getQ_overlay() == 1) {
					//未到最大叠加数量
					if (buffs.size() < buffModel.getQ_overlay_maxcount() || buffModel.getQ_overlay_maxcount() == -1) {
						buff.add(source, adder);
						adder.getBuffs().add(buff);
						//添加buff消息
						sendAddBuffMessage(source, adder, buff);
						//DEBUFF自动移除判断
						if (buffModel.getQ_effect_type() == 2) {
							autoRemoveDebuff(adder, buff);
						}
						//重新计算属性
						if (adder instanceof Player) {
							ManagerPool.playerAttributeManager.countPlayerAttribute((Player) adder, PlayerAttributeType.BUFF);
						}

						return 1;
					}
					if (buffId == BuffConst.KINGCITY_KING) {
						BugTraceManager.getInstance().trace(target.getId(), source.getName(), "秦王BUFF未加载", "可叠加buff,叠加次数超过上限");
					}
				} //效果替换
				else if (buffModel.getQ_overlay() == 2) {
					//移除原有buff
					for (int i = 0; i < buffs.size(); i++) {
						Buff tb = buffs.get(i);
						if (buffModel.getQ_replace_level() == 0) {
							if (tb.getModelId() == buff.getModelId()) {
								remove(adder, tb);
							}
						} else {
							if (tb.getReplaceLevel() == buff.getReplaceLevel()) {
								remove(adder, tb);
							}
						}
					}

					buff.add(source, adder);
					adder.getBuffs().add(buff);
					//添加buff消息
					sendAddBuffMessage(source, adder, buff);
					//DEBUFF自动移除判断
					if (buffModel.getQ_effect_type() == 2) {
						autoRemoveDebuff(adder, buff);
					}

					//重新计算属性
					if (adder instanceof Player) {
						ManagerPool.playerAttributeManager.countPlayerAttribute((Player) adder, PlayerAttributeType.BUFF);
					}

					return 1;
				} //最大值叠加
				else if (buffModel.getQ_overlay() == 3) {
					//原有buff加上属性值
					Buff old = null;
					for (int i = 0; i < buffs.size(); i++) {
						Buff tb = buffs.get(i);
						if (buffModel.getQ_replace_level() == 0) {
							if (tb.getModelId() == buff.getModelId()) {
								old = tb;
								break;
							}
						} else {
							if (tb.getReplaceLevel() == buff.getReplaceLevel()) {
								old = tb;
								break;
							}
						}
					}
					
					if (buffModel.getQ_overlay_maxcount() != -1){
						int overlay = 0;
						if(old != null) overlay = old.getOverlay();
						overlay += buff.getOverlay();
						if(overlay > buffModel.getQ_overlay_maxcount()){
							log.error("加buff失败:层数太多(玩家:" + target.getId() + ";模型:" + buffId + ")");
							if (buffId == BuffConst.KINGCITY_KING) {
								BugTraceManager.getInstance().trace(target.getId(), source.getName(), "秦王BUFF未加载", "值叠加,层数太多");
							}
							return 0;
						}
					}
					
					if (old != null) {
						old.setParameter(old.getParameter() + buff.getParameter() * buff.getOverlay());
						old.setOverlay(old.getOverlay() + buff.getOverlay());

						//添加buff消息
						sendChangeBuffMessage(source, adder, old);
					} else {
						buff.setParameter(buff.getParameter() * buff.getOverlay());
						
						buff.add(source, adder);
						adder.getBuffs().add(buff);
						//添加buff消息
						sendAddBuffMessage(source, adder, buff);
						//DEBUFF自动移除判断
						if (buffModel.getQ_effect_type() == 2) {
							autoRemoveDebuff(adder, buff);
						}
					}

					//重新计算属性
					if (adder instanceof Player) {
						ManagerPool.playerAttributeManager.countPlayerAttribute((Player) adder, PlayerAttributeType.BUFF);
					}

					return 1;
				} //最大时间叠加
				else if (buffModel.getQ_overlay() == 4) {
					//原有buff加上时间
					Buff old = null;
					for (int i = 0; i < buffs.size(); i++) {
						Buff tb = buffs.get(i);
						if (buffModel.getQ_replace_level() == 0) {
							if (tb.getModelId() == buff.getModelId()) {
								old = tb;
								break;
							}
						} else {
							if (tb.getReplaceLevel() == buff.getReplaceLevel()) {
								old = tb;
								break;
							}
						}
					}
					
					if (buffModel.getQ_overlay_maxcount() != -1){
						int overlay = 0;
						if(old != null) overlay = old.getOverlay();
						overlay += buff.getOverlay();
						if(overlay > buffModel.getQ_overlay_maxcount()){
							log.error("加buff失败:层数太多(玩家:" + target.getId() + ";模型:" + buffId + ")");
							if (buffId == BuffConst.KINGCITY_KING) {
								BugTraceManager.getInstance().trace(target.getId(), source.getName(), "秦王BUFF未加载", "时间叠加,层数太多");
							}
							return 0;
						}
					}

					if (old != null) {
						old.setTotalTime(old.getTotalTime() + buff.getTotalTime() * buff.getOverlay());
						old.setTotalRemainTime(old.getTotalRemainTime() + buff.getTotalRemainTime() * buff.getOverlay());

						//添加buff消息
						sendChangeBuffMessage(source, adder, old);
					} else {
						buff.setTotalTime(buff.getTotalTime() * buff.getOverlay());
						buff.setTotalRemainTime(buff.getTotalRemainTime() * buff.getOverlay());
						buff.setOverlay(1);
						
						buff.add(source, adder);
						adder.getBuffs().add(buff);
						//添加buff消息
						sendAddBuffMessage(source, adder, buff);
						//DEBUFF自动移除判断
						if (buffModel.getQ_effect_type() == 2) {
							autoRemoveDebuff(adder, buff);
						}
					}

					//重新计算属性
					if (adder instanceof Player) {
						ManagerPool.playerAttributeManager.countPlayerAttribute((Player) adder, PlayerAttributeType.BUFF);
					}

					return 1;
				}
			} else {
				
				if (buffModel.getQ_overlay() == 3) {
					//原有buff加上属性值
					buff.setParameter(buff.getParameter() * buff.getOverlay());
				} //最大时间叠加
				else if (buffModel.getQ_overlay() == 4) {
					buff.setTotalTime(buff.getTotalTime() * buff.getOverlay());
					buff.setTotalRemainTime(buff.getTotalRemainTime() * buff.getOverlay());
					buff.setOverlay(1);
				}
				buff.add(source, adder);
				adder.getBuffs().add(buff);
				//添加buff消息
				sendAddBuffMessage(source, adder, buff);
				//DEBUFF自动移除判断
				if (buffModel.getQ_effect_type() == 2) {
					autoRemoveDebuff(adder, buff);
				}

				//重新计算属性
				if (adder instanceof Player) {
					ManagerPool.playerAttributeManager.countPlayerAttribute((Player) adder, PlayerAttributeType.BUFF);
				}

				return 1;
			}
		}

		return 0;
	}

	private void autoRemoveDebuff(Fighter fighter, Buff debuff) {
		//拥有自动解除负面状态的Buff
		if (FighterState.FUMIANZIDONGJIECHU.compare(fighter.getFightState())) {
			Buff buff = getBuffByType(fighter, BuffType.RELIEFDEBUFF).get(0);
			//获取BUFF模型
			Q_buffBean buffModel = ManagerPool.dataManager.q_buffContainer.getMap().get(buff.getModelId());
			int percent = buff.getPercent() + buffModel.getQ_effect_ratio();
			//计算是否成功移除
			if (RandomUtils.random(Global.MAX_PROBABILITY) < percent) {
				remove(fighter, debuff);
			}
		}
	}

	/**
	 * 计算身上的Buff
	 *
	 * @param fighter
	 */
	public void countBuff(Fighter fighter) {
		Buff preBuff = null;
		try{
			//遍历buff
			Buff[] buffs = fighter.getBuffs().toArray(new Buff[0]);
			boolean recount = false;
			for (int i = 0; i < buffs.length; i++) {
				Buff buff = buffs[i];
				preBuff = buff;
				if (!fighter.isDie() && buff instanceof TimeBuff) {
					buff.setRemain(buff.getRemain() - 1000);
					buff.setTotalRemainTime(buff.getTotalRemainTime() - 1000);
					//剩余时间为0
					if (buff.getRemain() == 0) {
						int result = buff.action(fighter, fighter);
						//效果结束
						if (result == 2) {
							buff.remove(fighter);
							fighter.getBuffs().remove(buff);
							//通知移除buff
							sendRemoveBuffMessage(fighter, buff);
							recount = true;
							continue;
						} else if(result == 3) {
							break;
						} else {
							//重置剩余时间
							buff.setRemain(buff.getTimer());
						}
					}
				}
	
				//非永久有效
				if (buff.getTotalTime() != -1) {
					if (buff.getCount() == 1) {
						//buff过期
						if (buff.getStart() + buff.getTotalTime() < System.currentTimeMillis()) {
							buff.remove(fighter);
							fighter.getBuffs().remove(buff);
							//pk保护buff移除提示
							if (buff.getModelId() == Global.PROTECT_FOR_KILLED) {
								MessageUtil.notify_player((Player) fighter, Notifys.NORMAL, ResManager.getInstance().getString("PK保护时间已过，行走PK区域时请小心"));
								log.error("玩家(" + ((Player) fighter).getId() + ")PK状态为(" + ((Player) fighter).getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(((Player) fighter).getState()) + ")移除和平保护buff因为时间结束");	
							}else if(buff.getModelId() == Global.PROTECT_IN_NIGHT){
								log.error("玩家(" + ((Player) fighter).getId() + ")PK状态为(" + ((Player) fighter).getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(((Player) fighter).getState()) + ")移除夜晚和平保护buff因为时间结束");	
							}else if( (buff.getModelId()>=1401 && buff.getModelId()<=1403) || buff.getModelId()==1411 || buff.getModelId()==1412 ){
							
								if(fighter instanceof Player){
									VipManager.getInstance().removeVipEvent((Player)fighter);
								}
							}
							//通知移除buff
							sendRemoveBuffMessage(fighter, buff);
							
							recount = true;
						}
					} else {
						if(!(buff instanceof TimeBuff)) buff.setTotalRemainTime(buff.getTotalRemainTime() - 1000);
						if (buff.countTotalRemainTime(fighter) <= 0) {
							buff.remove(fighter);
							fighter.getBuffs().remove(buff);
							//pk保护buff移除提示
							if (buff.getModelId() == Global.PROTECT_FOR_KILLED) {
								MessageUtil.notify_player((Player) fighter, Notifys.NORMAL, ResManager.getInstance().getString("PK保护时间已过，行走PK区域时请小心"));
							}
							//通知移除buff
							sendRemoveBuffMessage(fighter, buff);
							recount = true;
						}
					}
				}
			}
			//重新计算属性
			if (fighter instanceof Player && recount) {
				ManagerPool.playerAttributeManager.countPlayerAttribute((Player) fighter, PlayerAttributeType.BUFF);
			}
		}catch (Exception e) {
			if(preBuff!=null) log.error("计算BUFF(" + preBuff.getModelId() + ")后出错!");
			log.error(e, e);
		}
	}

	/**
	 * 移除Buff
	 *
	 * @param fighter
	 * @param buff
	 */
	private void remove(Fighter fighter, Buff buff) {
		buff.remove(fighter);
		fighter.getBuffs().remove(buff);
		//通知移除buff
		sendRemoveBuffMessage(fighter, buff);
	}

	/**
	 * 移除Buff
	 *
	 * @param fighter
	 * @param buff
	 */
	public void removeBuff(Fighter fighter, Buff buff) {
		remove(fighter, buff);
		//重新计算属性
		if (fighter instanceof Player) {
			ManagerPool.playerAttributeManager.countPlayerAttribute((Player) fighter, PlayerAttributeType.BUFF);
		}
	}

	/**
	 * 按id移除Buff
	 *
	 * @param roleId
	 * @param id
	 */
	public void removeById(Player player, long id) {
		Iterator<Buff> iter = player.getBuffs().iterator();
		while (iter.hasNext()) {
			Buff buff = (Buff) iter.next();
			if (buff.getId() == id) {
				buff.remove(player);
				iter.remove();
				//通知移除buff
				sendRemoveBuffMessage(player, buff);
			}
		}

		//重新计算属性
		ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.BUFF);
	}

	/**
	 * 按buffbean id移除Buff
	 *
	 * @param roleId
	 * @param id
	 */
	public void removeByBuffId(Player player, int id) {
		Iterator<Buff> iter = player.getBuffs().iterator();
		while (iter.hasNext()) {
			Buff buff = (Buff) iter.next();
			if (buff.getModelId() == id) {
				buff.remove(player);
				iter.remove();
				//通知移除buff
				sendRemoveBuffMessage(player, buff);
			}
		}

		//重新计算属性
		ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.BUFF);
	}

	/**
	 * 按类型移除Buff
	 *
	 * @param fighter
	 * @param type
	 */
	public void removeByType(Fighter fighter, int type) {
		List<Buff> buffs = getBuffByType(fighter, type);
		for (int i = 0; i < buffs.size(); i++) {
			remove(fighter, buffs.get(i));
		}
		//重新计算属性
		if (fighter instanceof Player) {
			ManagerPool.playerAttributeManager.countPlayerAttribute((Player) fighter, PlayerAttributeType.BUFF);
		}
	}

	/**
	 * 死亡或下线时移除Buff
	 *
	 * @param fighter
	 */
	public void removeOnDieOrQuit(Fighter fighter, boolean die) {
		Iterator<Buff> iter = fighter.getBuffs().iterator();
		while (iter.hasNext()) {
			Buff buff = (Buff) iter.next();
			Q_buffBean buffModel = ManagerPool.dataManager.q_buffContainer.getMap().get(buff.getModelId());
			if (buffModel.getQ_effect_dieordown_clear() == 1) {
				buff.remove(fighter);
				iter.remove();
				//通知移除buff
				sendRemoveBuffMessage(fighter, buff);
			}
		}
		//重新计算属性
		if (fighter instanceof Player && die) {
			ManagerPool.playerAttributeManager.countPlayerAttribute((Player) fighter, PlayerAttributeType.BUFF);
		}
	}
	
	/**
	 * 按类型移除Buff
	 *
	 * @param fighter
	 */
	public void removeAll(Fighter fighter) {
		Iterator<Buff> iter = fighter.getBuffs().iterator();
		while (iter.hasNext()) {
			Buff buff = (Buff) iter.next();
			buff.remove(fighter);
			iter.remove();
			
			//通知移除buff
			sendRemoveBuffMessage(fighter, buff);
		}
		//重新计算属性
		if (fighter instanceof Player) {
			ManagerPool.playerAttributeManager.countPlayerAttribute((Player) fighter, PlayerAttributeType.BUFF);
		}
	}

	/**
	 * 按类型获取buff
	 *
	 * @param fighter
	 * @param type
	 * @return
	 */
	public List<Buff> getBuffByType(Fighter fighter, int type) {
		List<Buff> buffs = new ArrayList<Buff>();
		Iterator<Buff> iter = fighter.getBuffs().iterator();
		while (iter.hasNext()) {
			Buff buff = (Buff) iter.next();
			if (buff.getActionType() == type) {
				buffs.add(buff);
			}
		}
		return buffs;
	}
	
	/**
	 * 按替换等级获取buff
	 *
	 * @param fighter
	 * @param type
	 * @return
	 */
	public List<Buff> getBuffByReplaceLevel(Fighter fighter, int modelId, int level) {
		List<Buff> buffs = new ArrayList<Buff>();
		Iterator<Buff> iter = fighter.getBuffs().iterator();
		while (iter.hasNext()) {
			Buff buff = (Buff) iter.next();
			if(level==0){
				if(buff.getModelId()==modelId){
					buffs.add(buff);
				}
			}else if (buff.getReplaceLevel() == level) {
				buffs.add(buff);
			}
		}
		return buffs;
	}
	
	/**
	 * 是否拥有龙元武学buff
	 * @param player
	 * @return
	 */
	public boolean isHaveLongyuanBuff(Fighter fighter){
		Iterator<Buff> iter = fighter.getBuffs().iterator();
		while (iter.hasNext()) {
			Buff buff = (Buff) iter.next();
			if(longyuanBuffs.contains(buff.getModelId())){
				return true;
			}
		}
		return false;
	}

	/**
	 * 按类型获取buff
	 *
	 * @param fighter
	 * @param type
	 * @return
	 */
	public List<Buff> getBuffByModelId(Fighter fighter, int modelId) {
		List<Buff> buffs = new ArrayList<Buff>();
		Iterator<Buff> iter = fighter.getBuffs().iterator();
		while (iter.hasNext()) {
			Buff buff = (Buff) iter.next();
			if (buff.getModelId() == modelId) {
				buffs.add(buff);
			}
		}
		return buffs;
	}

	/**
	 * 获得攻击触发的Buff
	 *
	 * @param fighter
	 * @return
	 */
	public List<Buff> getBuffTriggerByAttack(Fighter fighter) {
		return getBuffTriggerByType(fighter, TriggerType.ATTACK_SUCCESS);
	}

	/**
	 * 获得防御触发的Buff
	 *
	 * @param fighter
	 * @return
	 */
	public List<Buff> getBuffTriggerByDefense(Fighter fighter) {
		return getBuffTriggerByType(fighter, TriggerType.DEFENSE);
	}

	/**
	 * 按触发类型获得Buff
	 *
	 * @param fighter
	 * @param type
	 * @return
	 */
	public List<Buff> getBuffTriggerByType(Fighter fighter, int type) {
		List<Buff> buffs = new ArrayList<Buff>();
		Iterator<Buff> iter = fighter.getBuffs().iterator();
		while (iter.hasNext()) {
			Buff buff = (Buff) iter.next();
			if (buff instanceof TriggerBuff && ((TriggerBuff) buff).getTriggerType() == type) {
				buffs.add(buff);
			}
		}
		return buffs;
	}

	/**
	 * 按id获得Buff
	 *
	 * @param fighter
	 * @param id
	 * @return
	 */
	public Buff getBuffById(Fighter fighter, long id) {
		Iterator<Buff> iter = fighter.getBuffs().iterator();
		while (iter.hasNext()) {
			Buff buff = (Buff) iter.next();
			if (buff.getId() == id) {
				return buff;
			}
		}
		return null;
	}

	/**
	 * 按获取最后添加的debuff
	 *
	 * @param fighter
	 * @return
	 */
	public Buff getLastDebuff(Fighter fighter) {
		for (int i = fighter.getBuffs().size() - 1; i >= 0; i--) {
			Buff buff = fighter.getBuffs().get(i);
			//获取BUFF模型
			Q_buffBean buffModel = ManagerPool.dataManager.q_buffContainer.getMap().get(buff.getModelId());
			if (buffModel.getQ_effect_type() == 2) {
				return buff;
			}
		}

		return null;
	}

	/**
	 * 触发buff
	 *
	 * @param source
	 * @param target
	 * @param buf
	 * @return
	 */
	public int triggerBuff(Fighter source, Fighter target, Buff buf) {
		return buf.action(source, target);
	}

	/**
	 * 发送buff信息
	 */
	public void sendBuffInfos(Player player) {
		ResBuffsMessage msg = new ResBuffsMessage();
		msg.setFightState(player.getFightState());
		//血池血量
		for (Buff buff : player.getBuffs()) {
			//获取BUFF模型
			Q_buffBean buffModel = ManagerPool.dataManager.q_buffContainer.getMap().get(buff.getModelId());
			if (buffModel.getQ_cast_type() != 0) {
				msg.getBuff().add(getBuffInfo(player, buff));
			}
		}

		MessageUtil.tell_player_message(player, msg);
	}

	/**
	 * 发送buff数值变化信息
	 */
	public void sendBuffInfoMessage(Player player, long buffid) {
		Buff buff = getBuffById(player, buffid);
		if (buff == null) {
			return;
		}

		ResBuffInfoMessage msg = new ResBuffInfoMessage();
		msg.setBuffId(buff.getId());
		//血池血量
		if (buff.getParameter() != 0) {
			msg.setValue(buff.getParameter());
		}
		
		//Buff 总时间 //改成发送剩余时间 单位 秒
		int remain = 0;
		if (buff.getTotalTime() == -1) {//永久BUFF
			remain = -1;
		} else if (buff.getCount() == 1) {
			remain = (int) ((buff.getStart() + buff.getTotalTime() - System.currentTimeMillis()) / 1000); //非永久buff 计算剩余时间
			if (remain < 0) {
				remain = 0;
			}
		} else if (buff.getCount() == 0) {
			remain = (int)(buff.countTotalRemainTime(player) / 1000);//非永久buff 计算剩余时间
			if (remain < 0) {
				remain = 0;
			}
		}
		msg.setRemain(remain);  // 剩余时间=应该结束的时间-当前时间 单位 秒

		MessageUtil.tell_player_message(player, msg);
	}

	/**
	 * 发送增加buff信息
	 *
	 * @param source
	 * @param adder
	 * @param buff
	 */
	private void sendAddBuffMessage(Fighter source, Fighter adder, Buff buff) {
		//获取BUFF模型
		Q_buffBean buffModel = ManagerPool.dataManager.q_buffContainer.getMap().get(buff.getModelId());

		ResAddBuffMessage msg = new ResAddBuffMessage();
		//角色Id
		msg.setPersonId(adder.getId());
		//来源Id
		msg.setSourceId(source.getId());
		//战斗状态
		msg.setFightState(adder.getFightState());
		//buff信息
		msg.setBuff(getBuffInfo(adder, buff));

		if (buffModel.getQ_cast_type() == 2) {
			//周围广播buff
			MessageUtil.tell_round_message((IMapObject) adder, msg);
		} else if (buffModel.getQ_cast_type() == 1) {
			if (source.getId() != adder.getId()) {
				if (source instanceof Player) {
					MessageUtil.tell_player_message((Player) source, msg);
				}
				if (adder instanceof Player) {
					MessageUtil.tell_player_message((Player) adder, msg);
				}
			} else {
				if (adder instanceof Player) {
					MessageUtil.tell_player_message((Player) adder, msg);
				}
			}
		}
	}

	/**
	 * 发送更改buff信息
	 *
	 * @param source
	 * @param buffId
	 */
	public void sendChangeBuffMessage(Player player, long buffId) {
		Buff buff = getBuffById(player, buffId);
		if (buff == null) {
			return;
		}

		ResChangeBuffMessage msg = new ResChangeBuffMessage();
		//角色Id
		msg.setPersonId(player.getId());
		//来源Id
		msg.setSourceId(player.getId());
		//战斗状态
		msg.setFightState(player.getFightState());
		//buff信息
		msg.setBuff(getBuffInfo(player, buff));

		MessageUtil.tell_player_message(player, msg);
	}
	
	/**
	 * 发送更改buff信息
	 *
	 * @param source
	 * @param adder
	 * @param buff
	 */
	public void sendChangeBuffMessage(Fighter source, Fighter adder, Buff buff) {
		//获取BUFF模型
		Q_buffBean buffModel = ManagerPool.dataManager.q_buffContainer.getMap().get(buff.getModelId());

		ResChangeBuffMessage msg = new ResChangeBuffMessage();
		//角色Id
		msg.setPersonId(adder.getId());
		//来源Id
		msg.setSourceId(source.getId());
		//战斗状态
		msg.setFightState(adder.getFightState());
		//buff信息
		msg.setBuff(getBuffInfo(adder, buff));

		if (buffModel.getQ_cast_type() == 2) {
			//周围广播buff
			MessageUtil.tell_round_message((IMapObject) adder, msg);
		} else if (buffModel.getQ_cast_type() == 1) {
			if (source.getId() != adder.getId()) {
				if (source instanceof Player) {
					MessageUtil.tell_player_message((Player) source, msg);
				}
				if (adder instanceof Player) {
					MessageUtil.tell_player_message((Player) adder, msg);
				}
			} else {
				if (adder instanceof Player) {
					MessageUtil.tell_player_message((Player) adder, msg);
				}
			}
		}
	}

	/**
	 * 发送移除Buff信息
	 *
	 * @param fighter
	 * @param buff
	 */
	public void sendRemoveBuffMessage(Fighter fighter, Buff buff) {
		//获取BUFF模型
		Q_buffBean buffModel = ManagerPool.dataManager.q_buffContainer.getMap().get(buff.getModelId());

		ResRemoveBuffMessage msg = new ResRemoveBuffMessage();
		//角色Id
		msg.setPersonId(fighter.getId());
		//战斗状态
		msg.setFightState(fighter.getFightState());
		//buff Id
		msg.setBuffId(buff.getId());

		if (buffModel.getQ_cast_type() == 2) {
			//周围广播buff
			MessageUtil.tell_round_message((IMapObject) fighter, msg);
		} else if (buffModel.getQ_cast_type() == 1) {
			if (fighter instanceof Player) {
				MessageUtil.tell_player_message((Player) fighter, msg);
			}
		}
	}

	/**
	 * 获取buff简要信息
	 *
	 * @param buff
	 * @return
	 */
	public BuffInfo getBuffInfo(Fighter adder, Buff buff) {
		//获取BUFF模型
		Q_buffBean buffModel = ManagerPool.dataManager.q_buffContainer.getMap().get(buff.getModelId());
		//buff信息
		BuffInfo info = new BuffInfo();
		//Buff Id
		info.setBuffId(buff.getId());
		//Buff 模板Id
		info.setModelId(buff.getModelId());
		//Buff 总时间 //改成发送剩余时间 单位 秒
		int remain = 0;
		if (buff.getTotalTime() == -1) {//永久BUFF
			remain = -1;
		} else if (buff.getCount() == 1) {
			remain = (int) ((buff.getStart() + buff.getTotalTime() - System.currentTimeMillis()) / 1000); //非永久buff 计算剩余时间
			if (remain < 0) {
				remain = 0;
			}
		} else if (buff.getCount() == 0) {
			remain = (int)(buff.countTotalRemainTime(adder) / 1000);//非永久buff 计算剩余时间
			if (remain < 0) {
				remain = 0;
			}
		}
		info.setRemain(remain);  // 剩余时间=应该结束的时间-当前时间 单位 秒
		info.setTotal((int)(buff.getTotalTime()/1000));
		
		info.setOverlay(buff.getOverlay());
		//Buff 数值(血池时是血量)
		if (buff.getParameter() == 0) {
			info.setValue(buffModel.getQ_effect_value() + buff.getValue());
		} else {
			info.setValue(buff.getParameter());
		}

		if (adder instanceof Player) {
			if (buff instanceof KillBuff) {
				info.setValue(((Player) adder).getEvencutatk());
			} else if (buff instanceof HitBuff) {
				info.setValue(ManagerPool.batterManager.getbossBatter((Player) adder));
			}
		}
		//Buff 比例
		info.setPercent(buffModel.getQ_effect_ratio() + buff.getPercent());
		
		if(buffModel.getQ_colour() > 0){
			BuffPara para = new BuffPara();
			para.setType(BuffParaType.CHANGECOLOR);
			para.setValue(buffModel.getQ_colour());
			info.getBuffparas().add(para);
		}

		return info;
	}

	/**
	 * 是否影响buff效果
	 * @param model 被影响buff模板
	 * @param modelId 影响buff模板ID
	 * @return
	 */
	public boolean check(Q_buffBean model, int modelId){
		if(model.getQ_skill_influnce()==null || ("").equals(model.getQ_skill_influnce())){
			return false;
		}
		if(model.getQ_skill_influnce().indexOf("[" + modelId + "]") > -1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 按类型获得Buff
	 *
	 * @param type 类型
	 * @return
	 */
	private Buff getBuff(int type, int buffModelId) {
		switch (type) {
			//1	增加等级
			case BuffType.LEVEL:
				return new TimeBuff();
			//2	增加人物经验
			case BuffType.EXP:
				return new TimeBuff();
			//3	增加人物真气
			case BuffType.ZHENQI:
				return new TimeBuff();
			//4	增加战场声望
			case BuffType.BATTLEEXP:
				return new TimeBuff();
			//5	增加或减少当前生命值
			case BuffType.HP:
				return new TimeBuff();
			//6	增加或减少当前内力值
			case BuffType.MP:
				return new TimeBuff();
			//7	增加或减少当前体力值
			case BuffType.SP:
				return new TimeBuff();
			//8	增加或减少生命值上限
			case BuffType.MAXHP:
				return new AttributeBuff();
			//9	增加或减少内力值上限
			case BuffType.MAXMP:
				return new AttributeBuff();
			//10	增加或减少体力值上限
			case BuffType.MAXSP:
				return new AttributeBuff();
			//11	增加或减少攻击力
			case BuffType.ATTACK:
				return new AttributeBuff();
			//12	增加或减少防御力
			case BuffType.DEFENSE:
				return new AttributeBuff();
			//13	增加或减少闪避值
			case BuffType.DODGE:
				return new AttributeBuff();
			//14	增加或减少爆击值
			case BuffType.CRIT:
				return new AttributeBuff();
			//15	增加或减少幸运值
			case BuffType.LUCK:
				return new AttributeBuff();
			//16	增加或减少攻击速度
			case BuffType.ATTACKSPEED:
				return new AttributeBuff();
			//17	增加或减少移动速度
			case BuffType.SPEED:
				return new AttributeBuff();
			//18	每隔一段时间加满人物的生命，直至剩余容量用完
			case BuffType.FILLHP:
				return new TimeBuff();
			//19	每隔一段时间加满人物的内力，直至剩余容量用完
			case BuffType.FILLMP:
				return new TimeBuff();
			//20	每隔一段时间加满人物的体力，直至剩余容量用完
			case BuffType.FILLSP:
				return new TimeBuff();
			//21	打怪获得多倍经验，持续一段时间后消失
			case BuffType.MULEXP:
				return new AttributeBuff();
			//22	打怪获得多倍打坐真气，持续一段时间后消失
			case BuffType.MULZHENQI:
				return new AttributeBuff();
			//23	在一段时间内享受禁止被PK的保护，持续一段时间后或当玩家主动PK其他玩家时消失
			case BuffType.PKPROTECT:
				return new StateBuff();
			//24	有一定成功几率将目标定身（不能移动，不能跳跃，不能格挡，不能使用技能，允许吃药），持续一段时间后恢复
			case BuffType.STAKME:
				return new StateBuff();
			//25	有一定成功几率将目标所有装备附加的攻击力降低一部分比例甚至降为负值，持续一段时间后恢复
			case BuffType.EQUIPATTACK:
				return new AttributeBuff();
			//26	有一定成功几率将目标所有装备附加的防御力降低一部分比例甚至降为负值，持续一段时间后恢复
			case BuffType.EQUIPDEFENSE:
				return new AttributeBuff();
			//27	有一定成功几率将目标的内力值清零，且禁止目标吃内力恢复类药品（使用药品时返回消息将禁止使用的消息提示），持续一段时间后恢复
			case BuffType.MPEMPTY:
				return new StateBuff();
			//28	有一定成功几率将目标的体力值清零，且禁止目标吃体力恢复类药品（使用药品时返回消息将禁止使用的消息提示），持续一段时间后恢复
			case BuffType.SPEMPTY:
				return new StateBuff();
			//29	有一定成功几率将目标的攻击速度降至其原有攻击速度的一定比例（允许比负100%还低），持续一段时间后恢复
			case BuffType.ATTACKSPEEDRECUDE:
				return new AttributeBuff();
			//30	有一定成功几率将目标的移动速度降至其原有移动速度的一定比例（允许比负100%还低），持续一段时间后恢复
			case BuffType.SPEEDRECUDE:
				return new AttributeBuff();
			//31	有一定成功几率将目标的闪避值清零，持续一段时间后恢复
			case BuffType.DODGEEMPTY:
				return new StateBuff();
			//32	有一定成功几率将目标的暴击值清零，持续一段时间后恢复
			case BuffType.CRITEMPTY:
				return new StateBuff();
			//33	有一定成功几率令目标的方向控制键倒置（上变下，下变上，左变右，右变左），持续一段时间后恢复
			case BuffType.BABELISM:
				return new StateBuff();
			//34	有一定成功几率将目标从坐骑上击落，并且在一段时间内禁止骑乘，持续一段时间后恢复
			case BuffType.FORBIDRIDE:
				return new StateBuff();
			//35	有一定成功几率将目标沉睡（不能移动，不能跳跃，不能格挡，不能使用技能，允许吃药）持续一段时间或者被攻击一次后效果消失
			case BuffType.SLEEP:
				return new StateBuff();
			//36	有一定成功几率对目标施毒，被施毒后在一定时间内每隔一段时间均按比例减血
			case BuffType.POISON:
				return new TimeBuff();
			//37	在一定时间内按比例提高主角的攻击力，效果与丹药相加
			case BuffType.ATTACKPERCENT:
				return new AttributeBuff();
			//38	在一定时间内按比例提高主角的防御力，效果与丹药相加
			case BuffType.DEFENSEPERCENT:
				return new AttributeBuff();
			//39	在一定时间内按比例提高主角的攻击速度，效果与丹药相加
			case BuffType.ATTACKSPEEDPERCENT:
				return new AttributeBuff();
			//40	在一定时间内按比例提高主角的移动速度，效果与丹药相加
			case BuffType.SPEEDPERCENT:
				return new AttributeBuff();
			//41	在一定时间内主角跳跃、二次跳跃不减少体力值
			case BuffType.NOCOSTFORJUMP:
				return new StateBuff();
			//42	在一定时间内主角格挡不减少内力值
			case BuffType.NOCOSTFORBLOCK:
				return new StateBuff();
			//43	在一定时间内使用格挡，每当主角格挡消耗真气时，均按主角生命上限的一定比例恢复主角当前生命值
			case BuffType.RECOVERFORBLOCK:
				return new TriggerBuff();
			//44	在一定时间内使用跳跃，每当主角跳跃时，均按主角生命上限的一定比例恢复主角当前生命值
			case BuffType.RECOVERFORJUMP:
				return new TriggerBuff();
			//45	在一定时间内无敌，主角免疫所有伤害，持续一段时间后消失
			case BuffType.INVINCIBLE:
				return new StateBuff();
			//46	在一定时间内若主角死亡则免费原地健康复活一次（不弹出复活提示面板，直接播放复活倒计时），持续一段时间或者死亡复活一次后效果消失
			case BuffType.FREERELIVE:
				return new StateBuff();
			//47	在一定时间内若主角攻击则必定产生多倍暴击，持续一段时间或者使用三次后效果消失
			case BuffType.MULCRIT:
				return new TriggerBuff();
			//48	在一定时间内每次主角发出攻击，若命中，均按其自身内力上限的一定比例恢复自身当前内力值，按同样比例减少目标内力值（吸蓝）
			case BuffType.RECOVERMPONATTACK:
				return new TriggerBuff();
			//49	在一定时间内每次主角发出攻击，若命中，均按其自身体力上限的一定比例恢复自身当前体力值，按同样比例减少目标体力值（吸体）
			case BuffType.RECOVERSPONATTACK:
				return new TriggerBuff();
			//50	在一定时间内每次主角发出攻击，若命中，均按其自身生命上限的一定比例恢复自身当前生命值（吸血）
			case BuffType.RECOVERHPONATTACK:
				return new TriggerBuff();
			//51	在一定时间内令目标每次在被攻击时，若被命中，均至少按照其生命上限的一定比例减少其当前生命值（魅惑）
			case BuffType.REDUCEHPONBEATTACK:
				return new TriggerBuff();
			//52	有一定成功几率将目标的攻击力清零，持续一段时间后恢复
			case BuffType.ATTACKEMPTY:
				return new StateBuff();
			//53	有一定成功几率当主角受到负面状态时，予以立刻解除
			case BuffType.RELIEFDEBUFF:
				return new StateBuff();
			//54	有一定成功几率将目标的防御力清零，持续一段时间后恢复
			case BuffType.DEFENSEEMPTY:
				return new StateBuff();
			//55	有一定成功几率令目标的主界面中央出现黑影（无法看清自己以及自己周边环境），持续一段时间后恢复
			case BuffType.BLIND:
				return new StateBuff();
			//56	有一定成功几率令目标每次被攻击命中后（攻击可来自任何人），所受到的伤害均按比例额外加深，持续一段时间后恢复
			case BuffType.DEEPENDAMAGE:
				return new TriggerBuff();
			//57	有一定成功几率令目标生命上限下降持续一段时间
			case BuffType.REDUCEMAXHP:
				return new AttributeBuff();
			//58	有一定成功几率令目标内力上限下降持续一段时间
			case BuffType.REDUCEMAXMP:
				return new AttributeBuff();
			//59	有一定成功几率令目标体力上限下降持续一段时间
			case BuffType.REDUCEMAXSP:
				return new AttributeBuff();
			//60	有一定成功几率令目标无法使用任何药品，持续一段时间
			case BuffType.FROBIDDRUG:
				return new StateBuff();
			//61	令内力盾新增功能，反弹无视防御伤害
			case BuffType.REBOUND:
				return new StateBuff();
			//62	令内力盾新增功能，每隔2秒清除一个自己所受的负面BUFF（按所受时间倒序清除）
			case BuffType.CLEARDEBUFF:
				return new TriggerBuff();
			//63  	触发对所选目标造成额外N点伤害
			case BuffType.DAMAGE:
				return new TimeBuff();
			//64  	触发减少额外N点伤害
			case BuffType.REDUCE:
				return new TimeBuff();
			//66	夜晚挂机保护PK, 当玩家主动PK其他玩家时消失
			case BuffType.PROTECTFORNIGHT:
				return new StateBuff();
			//67	在一定时间内按比例提高主角的暴击值，效果可与丹药相加
			case BuffType.CRITPERCENT:
				return new AttributeBuff();
			//68	在一定时间内按比例提高主角的闪避值，效果可与丹药相加
			case BuffType.DODGEPERCENT:
				return new AttributeBuff();
			//69	在一定时间内对所有主角技能等级增加
			case BuffType.SKILL:
				return new AttributeBuff();
			//70	令内力盾新增功能，每次消耗内力时，恢复对应的血量
			case BuffType.RECOVERSAMEFORBLOCK:
				return new TriggerBuff();
			//71	属性加成BUFF
			case BuffType.POWERUP:
				return new AttributeBuff();
			//72       打座BUFF
			case BuffType.SITINMEDITATION:
				return new TimeBuff();
			//75       连斩BUFF
			case BuffType.KILLBUFF:
				return new KillBuff();
			//76       连击BUFF
			case BuffType.HITBUFF:
				return new HitBuff();
			//77       显示BUFF
			case BuffType.SHOWBUFF:
				return new StateBuff();
			//78       霸权BUFF
			case BuffType.BAQUANBUFF:
				return new TriggerBuff();
			//79       仁德BUFF
			case BuffType.RENDEBUFF:
				return new TriggerBuff();
			//80 状态BUFF 仅用于标识状态 
			case BuffType.STATEBUFF:
				return new StateBuff();
			//82 勾魂BUFF
			case BuffType.GOUHUNBUFF:
				return new StateBuff();
			//83 梅花BUFF
			case BuffType.MEIHUABUFF:
				return new TriggerBuff();
			//84 玲珑BUFF
			case BuffType.LINGLONGBUFF:
				return new StateBuff();
			//85 遮影BUFF
			case BuffType.ZHEYINGBUFF:
				return new StateBuff();
			//86 强行PK BUFF
			case BuffType.FORCEPKBUFF:
				return new StateBuff();
			//87 紫芒BUFF
			case BuffType.ZIMANGBUFF:
				return new StateBuff();
			//88 跳跃减血BUFF
			case BuffType.DAMAGEFORJUMP:
				return new TriggerBuff();
			//89 boss buff（玩家减血,死亡）
			case BuffType.DAMAGEANDDIE:
				return new TimeBuff();
			//90 boss虚弱
			case BuffType.BOSSWEAK:
				return new StateBuff();
			//91 骑战兵器缴械
			case BuffType.HORSEWEAPONUNWEAR:
				return new StateBuff();
			//92 防骑战兵器缴械
			case BuffType.HORSEWEAPONPROTECT:
				return new StateBuff();
			//93商城购买指定道具时,降低价格(指定数量的元宝),可叠加
			case BuffType.MALLBUYREDUCE:
				return new AttributeBuff();
			//81 脚本BUFF
			case BuffType.SCRIPTBUFF:
				//获取BUFF模型
				Q_buffBean buffModel = ManagerPool.dataManager.q_buffContainer.getMap().get(buffModelId);
				if(buffModel==null) return null;
				IBuffScript script = (IBuffScript) ManagerPool.scriptManager.getScript(buffModel.getQ_script_id());
				if (script != null) {
					try {
						return script.getBuff();
					} catch (Exception e) {
						log.error(e, e);
					}
				} else {
					log.error("buff(" + buffModelId + ")脚本不存在！");
				}
				return null;
			
				
		}
		return null;
	}
}
