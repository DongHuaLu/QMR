package com.game.pet.timer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.data.bean.Q_petattributeBean;
import com.game.data.bean.Q_petinfoBean;
import com.game.data.manager.DataManager;
import com.game.dazuo.manager.PlayerDaZuoManager;
import com.game.manager.ManagerPool;
import com.game.map.message.ResRoundPetMessage;
import com.game.map.structs.Map;
import com.game.pet.manager.PetInfoManager;
import com.game.pet.manager.PetScriptManager;
import com.game.pet.message.ResPetReviveMessage;
import com.game.pet.struts.Pet;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.structs.Grid;
import com.game.timer.TimerEvent;
import com.game.utils.Global;
import com.game.utils.MessageUtil;
/**
 * 宠物回血
 * @author 赵聪慧
 *
 */
public class PetHeartTimer extends TimerEvent {
	
	private static final Logger logger = Logger.getLogger(PetHeartTimer.class);
	
	private int serverId;
	
	private int lineId;
	
	private int mapId;
	
	public PetHeartTimer(int serverId, int lineId, int mapId) {
		super(-1, 1000);
		this.serverId = serverId;
		this.lineId = lineId;
		this.mapId = mapId;
	}

	@Override
	public void action() {
		// 获取地图
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		if (map.isEmpty())
			return;
		
		//地图阻挡信息
		Grid[][] blocks = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
				
		Iterator<Pet> iterator = map.getPets().values().iterator();
		List<Pet> hides = new ArrayList<Pet>();
		try{
			while (iterator.hasNext()) {
				Pet pet = (Pet) iterator.next();
				
				if(pet.getServerId()!=this.serverId || pet.getLine()!=this.lineId || pet.getMap()!=this.mapId){
					continue;
				}
				if(!pet.isDie()&&pet.isShow()){
					PetScriptManager.getInstance().petTimmerAction(pet);
				}
				if(!PetInfoManager.getInstance().isFullHp(pet) && !pet.isDie()){
					Player player = PlayerManager.getInstance().getPlayer(pet.getOwnerId());
					Q_petattributeBean model= DataManager.getInstance().q_petattributeContainer.getMap().get(pet.getModelId()+"_"+pet.getLevel());
					
					if(PlayerDaZuoManager.getInstacne().isSit(player)){
						int recover = model.getQ_dz_recover_hp();
						//打坐状态下的血量恢复
						if(pet.getLastRecoveryTime()==0){
							pet.setLastRecoveryTime(System.currentTimeMillis());
						}else{
							long step = System.currentTimeMillis() - pet.getLastRecoveryTime();
							long dazuostep=System.currentTimeMillis()-player.getDazuoBeginTime();
							if(step>=Global.PET_DAZUORECOVERY_STEP&&dazuostep>Global.PET_DAZUORECOVERY_STEP){
								pet.setLastRecoveryTime(System.currentTimeMillis());
								PetInfoManager.getInstance().addHp(pet, recover);
							}
						}
					}else{
						int recover = model.getQ_recover_hp();
						//非打坐状态下的血量恢复 
						long ideltime = System.currentTimeMillis() - pet.getLastFightTime();
						if(ideltime>=Global.PET_RECOVERY_NEEDIDELTIME){
							long beforeRecovery = System.currentTimeMillis() - pet.getLastRecoveryTime();
							
							if(recover>0 && beforeRecovery>=Global.PET_RECOVERY_INTERVALTIME){
								pet.setLastRecoveryTime(System.currentTimeMillis());
								PetInfoManager.getInstance().addHp(pet, recover);
							}
						}	
					}
				}
				
				if(pet.isDie()){
					//死亡状态
					Q_petinfoBean model = DataManager.getInstance().q_petinfoContainer.getMap().get(pet.getModelId());
					int revive_time = model.getQ_revive_time();
					if(System.currentTimeMillis()-pet.getDieTime()>=revive_time){
						//传送到主人身边
						Player player = ManagerPool.playerManager.getPlayer(pet.getOwnerId());
						ManagerPool.mapManager.petTrans(pet, player.getPosition());
						
						pet.resetPet();
						try{
							logger.error("pet " + pet.getId() + "(" + pet.getModelId() + ") owner " + player.getId() + " revive!");
						}catch (Exception e) {
							logger.error(e, e);
						}
						
						ResPetReviveMessage rmsg=new ResPetReviveMessage();
						rmsg.setPet(PetInfoManager.getInstance().getDetailInfo(pet));
						MessageUtil.tell_player_message(player, rmsg);
						//广播周围宠物出现消息
						ResRoundPetMessage msg = new ResRoundPetMessage();
						msg.setPet(ManagerPool.mapManager.getPetInfo(pet, blocks));
						MessageUtil.tell_round_message(pet, msg);

						PlayerDaZuoManager.getInstacne().petShow(player);
						
						logger.debug("宠物" + model.getQ_name() + "复活");
						
						
						if(player.isDie()){
							hides.add(pet);
						}
					}
				}	
				
			}
		}catch (Exception e) {
			logger.error(e,e);
		}
		
		
		for (int i = 0; i < hides.size(); i++) {
			Player player = PlayerManager.getInstance().getPlayer(hides.get(i).getOwnerId());
			if (player != null) logger.error("角色[" + player.getId() + "]美人[" + hides.get(i).getId() + "]操作[timer force hide]");
			ManagerPool.petOptManager.forceHidePet(hides.get(i));
		}
		
	}
}
