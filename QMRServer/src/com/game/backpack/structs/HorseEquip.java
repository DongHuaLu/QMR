package com.game.backpack.structs;

import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.config.Config;
import com.game.data.bean.Q_itemBean;
import com.game.horse.struts.Horse;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttributeType;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.CommonConfig;
import com.game.utils.MessageUtil;

public class HorseEquip extends Equip {
	private Logger log = Logger.getLogger(HorseEquip.class);

	private static final long serialVersionUID = 3440260000258031580L;

	
	/**
	 * 穿着装备
	 * @param roleId 玩家id
	 */
	public void use(Player player, String... parameters){
		Horse horse = ManagerPool.horseManager.getHorse(player);
		if (horse != null && horse.getLayer() > 0) {
			long action = Config.getId();
			//获得物品模型
			Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(this.getItemModelId());
			if(model==null) return;
			int pos = kind(model.getQ_kind());
			if (pos == -1) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("没有此装备的位置."));
				return;
			}
			if(model.getQ_bind() == 2){
				//装备时绑定
				this.setBind(true);
			}
			
			if(!this.isBind()){
				//顶级强化装备佩戴时将装备设为绑定（0不生效，1生效）
				if(this.isFullStrength() && ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.EQUIP_TOPLEVEL_INTENSIFY_USEBIND.getValue()).getQ_int_value() == 1){
					this.setBind(true);
				}
			}
			
			if(!this.isBind()){
				//顶级镶嵌装备佩戴将装备设为绑定（0不生效，1生效）
				if(this.isFullEnchase() && ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.EQUIP_TOPLEVEL_INLAY_USEBIND.getValue()).getQ_int_value() == 1){
					this.setBind(true);
				}
			}
	
			if(!this.isBind()){
				//紫色装备佩戴时将装备设为绑定（0不生效，1生效）
				if(this.getQuality()==4 && ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.EQUIP_PURPLE_USEBIND.getValue()).getQ_int_value() == 1){
					this.setBind(true);
				}
			}
			
			//获得对应装备栏装备
			HorseEquip weared = horse.getHorseequips()[pos];
			
			//未装备装备
			if(weared==null){
				ManagerPool.backpackManager.removeItemByCellId(player, getGridId(),Reasons.WEAREDORUNWEARED,action);
				this.setGridId(-1);
				horse.getHorseequips()[pos] = this;
				//发送装备信息
				MessageUtil.tell_player_message(player, ManagerPool.equipManager.getWearEquipInfo(this));
				//重新计算属性
				ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.HORSE_EQUIP, this.getItemModelId());
			}else{
				weared.setGridId(this.getGridId());

				MessageUtil.tell_player_message(player, ManagerPool.equipManager.getUnwearEquipInfo(weared));
				ManagerPool.backpackManager.removeItem(player, getId(),Reasons.WEAREDORUNWEARED,action);
				ManagerPool.backpackManager.addItem(player, weared,Reasons.WEAREDORUNWEARED,action);
				this.setGridId(-1);
				horse.getHorseequips()[pos] = this;
				//发送装备信息
				MessageUtil.tell_player_message(player, ManagerPool.equipManager.getWearEquipInfo(this));
				//重新计算属性
				ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.HORSE_EQUIP, this.getItemModelId());
				
			}
			ManagerPool.playerManager.savePlayer(player);
		}else {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您尚未获得坐骑."));
		}
	}
	
	/**
	 * 卸下装备
	 * @param roleId 玩家id
	 */
	public void unuse(Player player, String... parameters){
		Horse horse = ManagerPool.horseManager.getHorse(player);
		//获得物品模型
		Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(this.getItemModelId());
		if(model==null) return;
		int pos = kind(model.getQ_kind());
		if (pos == -1) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("没有此装备的位置."));
			return;
		}
		//获得对应装备栏装备
		HorseEquip weared = horse.getHorseequips()[pos];
		
		//装备该装备
		if(weared==this){
			if(!BackpackManager.getInstance().hasAddSpace(player, weared)){
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("对不起，您的背包空格不足，请清理后再进行本操作"));
				return;
			}
			if (!ManagerPool.backpackManager.addItem(player, weared,Reasons.WEAREDORUNWEARED,Config.getId())){
				//加入包裹失败
				return;
			}
			horse.getHorseequips()[pos] = null;
			//发送卸下装备信息
			MessageUtil.tell_player_message(player, ManagerPool.equipManager.getUnwearEquipInfo(this));
			
			if(log.isDebugEnabled()){
				log.debug("UNWEAR " + this.getId() + " TO " + this.getGridId());	
			}
			//重新计算属性
			ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.HORSE_EQUIP, this.getItemModelId());
			
			ManagerPool.playerManager.savePlayer(player);
		}
	}
	
	
	
	/**返回正确位置
	 * 
	 * @param pos
	 * @return
	 */
	public int kind(int pos){
		int xpos = -1;
		if (pos == 20) {
			xpos = 0;
		}else if(pos == 21){
			xpos = 1;
		}else if(pos == 22){
			xpos = 2;
		}else if(pos == 23){
			xpos = 3;
		}
		return xpos;
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
}
