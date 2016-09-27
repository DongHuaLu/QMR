package com.game.backpack.structs;

import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.message.ResTakeUpSuccessMessage;
import com.game.backpack.message.ResUseItemSuccessMessage;
import com.game.buff.structs.BuffType;
import com.game.config.Config;
import com.game.cooldown.structs.CooldownTypes;
import com.game.count.manager.CountManager;
import com.game.count.structs.CountTypes;
import com.game.data.bean.Q_buffBean;
import com.game.data.bean.Q_characterBean;
import com.game.data.bean.Q_itemBean;
import com.game.data.manager.DataManager;
import com.game.fight.structs.FighterState;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.CommonConfig;
import com.game.utils.MessageUtil;
import com.game.utils.Symbol;
import com.game.utils.TimeUtil;

public class Medicines extends Item implements IAutoUseItem {
	/**
	 * Logger for this class
	 */
	protected static final Logger logger = Logger.getLogger(Medicines.class);

	private static final long serialVersionUID = 9167941278406610539L;
	
	
//	public static int daymaxzqratio=4320;
//	/**
//	 * 经验丹开启每日限制的等级
//	 */
//	public static int explimitgrade=40;
	@Override
	public void use(Player player, String... parameters) {
		//数量判断
		if(this.getNum()==0) return;
		
		//获得物品模型
		Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(this.getItemModelId());
		if(model==null) return;
				
		//冷却判定
		if(ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.DRUG, String.valueOf(this.getItemModelId()))){
			return;
		}
		
		if(ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.DRUG_PUBLIC, String.valueOf(model.getQ_cooldown_level()))){
			return;
		}
		int num=1;
		if(parameters!=null&&parameters.length>=1){
			num = Integer.parseInt(parameters[0]);
		}
		int zhenqi=0;
		int exp=0;
		
		//禁止吃药判断
		if(FighterState.JINZHISHIYONGYAOPIN.compare(player.getFightState())) return;
		String buffString = model.getQ_buff();
		
		int spousenum = 0;
		int spousetype = 0;
		
		String[] buffs = buffString.split(Symbol.FENHAO);
		for (String buffidstr : buffs) {
			int buffid = Integer.parseInt(buffidstr);
			Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(buffid);
			
			if(buff==null){
				MessageUtil.notify_player(player, Notifys.NORMAL,ResManager.getInstance().getString("{1}BUFF效果不存在"),buffid+"");
				return;
			}
			//恢复mp类药品
			if(buff.getQ_action_type() == BuffType.MP){
				//禁止使用mp药物
				if(FighterState.JINZHINEILIHUIFU.compare(player.getFightState())){
					return;
				}
				spousenum = buff.getQ_effect_value();
				spousetype= BuffType.MP;
			}
			
			//恢复sp类药品
			if(buff.getQ_action_type() == BuffType.SP){
				//禁止使用sp药物
				if(FighterState.JINZHITILIHUIFU.compare(player.getFightState())){
					return;
				}
				
				Map map = ManagerPool.mapManager.getMap(player);
				if(map != null && map.getBanusesp() != 0){
					MessageUtil.notify_player(player, Notifys.MOUSEPOS, "本地图禁止使用加体力道具");
					return;
				}
				spousenum = buff.getQ_effect_value();
				spousetype= BuffType.SP;
			}
			
			// 恢复hp类药品
			if (buff.getQ_action_type() == BuffType.HP) {
				// 禁止使用hp药物
				if (FighterState.JINZHISHENGMINGHUOFU.compare(player.getFightState())) {
					return;
				}
				spousenum = buff.getQ_effect_value();
				spousetype= BuffType.HP;
			}
			if(buff.getQ_action_type()==BuffType.EXP){
				exp+=buff.getQ_effect_value();
			}
			if(buff.getQ_action_type()==BuffType.ZHENQI){
				zhenqi+=buff.getQ_effect_value();
			}
		}
		if(ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.DRUG, String.valueOf(this.getItemModelId()))){
			return;
		}
		
		if(ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.DRUG_PUBLIC, String.valueOf(model.getQ_cooldown_level()))){
			return;
		}
		
		if(getItemModelId()>=ItemConst.ZHENQIDAN_MIN&&getItemModelId()<=ItemConst.ZHENQIDAN_MAX){
			int max = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.ZHENGQI_MAX.getValue()).getQ_int_value();
			if(player.getZhenqi()+zhenqi*num>max){
				num=(int) Math.ceil(((double)max-player.getZhenqi())/zhenqi);
			}			
		}
		if (player.getLevel()<60&&getItemModelId() >= ItemConst.XIUWEIDAN_MIN && getItemModelId() <= ItemConst.XIUWEIDAN_MAX){
			//修为丹使用限制
			int firstlevel=0;
			if(!TimeUtil.isSameDay(player.getLastUseXiuWeiDanTime(), System.currentTimeMillis())){
				firstlevel=player.getLevel();
			}else{
				firstlevel=player.getDayFirstUseXiuWeiDanGrade();
			}
			int expDayLimitRatio = gotExpDayLimitRatio(firstlevel);
			if(expDayLimitRatio>0){
				Q_characterBean levelModel = DataManager.getInstance().q_characterContainer.getMap().get(firstlevel);
				if (isDayMax(player, CountTypes.XIUWEIDANTOP, levelModel.getQ_basis_exp() * expDayLimitRatio)) {
					MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("当日使用修为丹获得经验值以经达上限请明日再试"));
					return;
				}else{
					long count = CountManager.getInstance().getCount(player, CountTypes.XIUWEIDANTOP,player.getId()+"");
					long max=levelModel.getQ_basis_exp()*expDayLimitRatio;
					if(max>=count+exp*num){
						
					
					}else{
						if((max-count)%exp==0){
							num=(int)(max-count)/exp;
						}else{
							num=(int)Math.ceil(((double)max-count)/exp);
						}
					}
				}
			}
		}
//		long max=levelModel.getQ_basis_exp()*expDayLimitRatio;
		if(getItemModelId()==ItemConst.WANSHOU_XIUWEIDAN&&isDayMax(player, CountTypes.WANSHOUXIUWEIDANDAYCOUNT,1)){
			MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("万寿修为丹每日只可使用一次"));
			return;
		}
		// 使用成功
		if (BackpackManager.getInstance().removeItem(player, this, num,Reasons.GOODUSE,Config.getId())){
			if(exp!=0&&player.getLevel()<60&&getItemModelId()>=ItemConst.XIUWEIDAN_MIN&&getItemModelId()<=ItemConst.XIUWEIDAN_MAX){
				if(!TimeUtil.isSameDay(player.getLastUseXiuWeiDanTime(), System.currentTimeMillis())){
					player.setDayFirstUseXiuWeiDanGrade(player.getLevel());
				}
				player.setLastUseXiuWeiDanTime(System.currentTimeMillis());
				CountManager.getInstance().addCount(player, CountTypes.XIUWEIDANTOP,player.getId()+"",  CountManager.COUNT_DAY,exp*num, 0);
			}
			ManagerPool.backpackManager.setItemBuff(player,buffString,num);
			//并蒂莲花 技能，本人使用药物，配偶额外加血
			ManagerPool.marriageManager.addSolution(player, spousetype, spousenum);
			
			// 添加药品冷却
			ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.DRUG, String.valueOf(this.getItemModelId()), model.getQ_cooldown());
			// 添加药品公共冷却
			ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.DRUG_PUBLIC, String.valueOf(model.getQ_cooldown_level()), model.getQ_cooldown_type());				
			
			if(getItemModelId()==ItemConst.WANSHOU_XIUWEIDAN){
				CountManager.getInstance().addCount(player, CountTypes.WANSHOUXIUWEIDANDAYCOUNT, player.getId()+"",CountManager.COUNT_DAY, 1,0);
			}
			ResUseItemSuccessMessage msg = new ResUseItemSuccessMessage();
			msg.setItemId(model.getQ_id());
			MessageUtil.tell_player_message(player, msg);
		}
	}
	
	private boolean isDayMax(Player player,CountTypes type,long max){
		long count = CountManager.getInstance().getCount(player, type,player.getId()+"");
		if(count>=max){
			return true;
		}
		return false;
	}

	public boolean autoTakeUpUse(Player player, String... parameters) {
		// 数量判断
		if (this.getNum() == 0)
			return false;

		// 获得物品模型
		Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(this.getItemModelId());
		if (model == null)
			return false;
//		// 冷却判定
//		if (ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.DRUG, String.valueOf(this.getItemModelId()))) {
//			return;
//		}
//
//		if (ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.DRUG_PUBLIC, String.valueOf(model.getQ_cooldown_level()))) {
//			return;
//		}
		// 禁止吃药判断
		if (FighterState.JINZHISHIYONGYAOPIN.compare(player.getFightState()))
			return false;
		
		String buffString = model.getQ_buff();
		String[] buffs = buffString.split(Symbol.FENHAO);
		int buffid = Integer.parseInt(buffs[0]);
		Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(buffid);
		if (buff == null)
			return false;
		
//		if(buff.getQ_action_type()!=BuffType.SP&&buff.getQ_action_type()!=BuffType.MP&&buff.getQ_action_type()!=BuffType.SP){
//			//不是红蓝体力药品   不能自动使用
//			return false;
//		}		
		int before=0;
		// 恢复mp类药品
		if (buff.getQ_action_type() == BuffType.MP) {
			// 禁止使用mp药物
			if (FighterState.JINZHINEILIHUIFU.compare(player.getFightState())) {
				return false;
			}
			before=player.getMp();
		}

		// 恢复sp类药品
		if (buff.getQ_action_type() == BuffType.SP) {
			// 禁止使用sp药物
			if (FighterState.JINZHITILIHUIFU.compare(player.getFightState())) {
				return false;
			}
			before=player.getSp();
		}
		// 恢复hp类药品
		if (buff.getQ_action_type() == BuffType.HP) {
			// 禁止使用hp药物
			if (FighterState.JINZHISHENGMINGHUOFU.compare(player.getFightState())) {
				return false;
			}
			before=player.getHp();
		}
		
		ManagerPool.backpackManager.setItemBuff(player,buffString,1);
		int after=0;
		switch (buff.getQ_action_type()) {
		case BuffType.MP:
			after=player.getMp();
			break;
		case BuffType.SP:
			after=player.getSp();
			break;
		case BuffType.HP:
			after=player.getHp();
			break;
		default:
			break;
		}	
		ResTakeUpSuccessMessage msg = new ResTakeUpSuccessMessage();
		msg.setGoodsId(getId());
		msg.setGoodModelId(getItemModelId());
		msg.setEffectType(buff.getQ_action_type());
		int value=after-before;
		msg.setEffectValue(value<0?0:value);
		MessageUtil.tell_player_message(player, msg);;
		return true;
//		// 使用成功
//		if (result > 0 && BackpackManager.getInstance().removeItem(player, this, 1, Reasons.GOODUSE, Config.getId())) {
//			ResUseItemSuccessMessage msg = new ResUseItemSuccessMessage();
//			msg.setItemId(model.getQ_id());
//			MessageUtil.tell_player_message(player, msg);
//		}
	}
	
	public int gotExpDayLimitRatio(int level){
		if(level>=40&&level<45){
			return 2280;
		}
		if(level>=45&&level<50){
			return 3360;
		}
		if(level>=50&&level<55){
			return 3960;
		}
		if(level>=55&&level<60){
			return 4320;
		}
		return -1;
	}
	
	@Override
	public void unuse(Player player, String... parameters) {}
}
