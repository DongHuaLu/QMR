package com.game.backpack.structs;

import java.util.List;

import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.config.Config;
import com.game.cooldown.structs.CooldownTypes;
import com.game.data.bean.Q_itemBean;
import com.game.data.manager.DataManager;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.manager.MapManager;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerState;
import com.game.prompt.structs.Notifys;
import com.game.structs.Grid;
import com.game.structs.Reasons;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.Symbol;
/**
 * 传送卷轴
 * @author 赵聪慧
 *
 */
public class ScrollTrans extends Item{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ScrollTrans.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void use(Player player, String... parameters) {
		Q_itemBean q_itemBean = DataManager.getInstance().q_itemContainer.getMap().get(getItemModelId());
		if(PlayerState.FIGHT.compare(player.getState())){
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您尚未脱离战斗状态，无法使用传送卷轴进行传送"));
			return;
		}
		if(q_itemBean==null){
			return;
		}
//		int q_cooldown_type = q_itemBean.getQ_cooldown_type();
		//冷却判定
		if (ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.DRUG, String.valueOf(this.getItemModelId()))) {
			return;
		}

		if (ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.DRUG_PUBLIC, String.valueOf(q_itemBean.getQ_cooldown_level()))) {
			return;
		}
		
		
		if(q_itemBean.getQ_transfer()==2){
			if(BackpackManager.getInstance().removeItem(player, this, 1,Reasons.GOODUSE,Config.getId())){
				List<Grid> allNoBlockGrid = MapUtils.getAllNoBlockGrid(player.getMapModelId());
				if(allNoBlockGrid.size()>0){
					Grid grid = allNoBlockGrid.get(RandomUtils.random(allNoBlockGrid.size()));
					MapManager.getInstance().changePosition(player, grid.getCenter());
//					MapManager.getInstance().changeMap(player, player.getMap(), player.getMapModelId(), player.getLine(), grid.getCenter());
				}else{
					logger.error("该地图没有一个非阻挡点");
				}
				// 添加卷轴冷却
				ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.DRUG, String.valueOf(this.getItemModelId()), q_itemBean.getQ_cooldown());
				// 添加卷轴公共冷却
				ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.DRUG_PUBLIC, String.valueOf(q_itemBean.getQ_cooldown_level()), q_itemBean.getQ_cooldown_type());
			}
			return;
		}
		if(q_itemBean.getQ_transfer()==3||q_itemBean.getQ_transfer()==1||q_itemBean.getQ_transfer()==4){
			if(!ManagerPool.mapManager.ischangMap(player)){
				return;
			}
			//定点传送
			if(BackpackManager.getInstance().removeItem(player, this, 1,Reasons.GOODUSE,Config.getId())){
				String q_transfer_position = q_itemBean.getQ_transfer_position();
				String[] split = q_transfer_position.split(Symbol.DOUHAO_REG);
				Grid grid = MapUtils.getGrid(Integer.parseInt(split[0]), Integer.parseInt(split[1]), ManagerPool.mapManager.getMapBlocks(q_itemBean.getQ_transfer_map()));
//				short x=Short.parseShort(Short.parseShort(split[0])*MapUtils.GRID_BORDER+"");
//				short y=Short.parseShort(Short.parseShort(split[1])*MapUtils.GRID_BORDER+"");
				ManagerPool.mapManager.changeMap(player, q_itemBean.getQ_transfer_map(), q_itemBean.getQ_transfer_map(), 0, grid.getCenter(), this.getClass().getName() + ".use");
				// 添加卷轴冷却
				ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.DRUG, String.valueOf(this.getItemModelId()), q_itemBean.getQ_cooldown());
				// 添加卷轴公共冷却
				ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.DRUG_PUBLIC, String.valueOf(q_itemBean.getQ_cooldown_level()), q_itemBean.getQ_cooldown_type());
			}	
			return;
		}
		logger.error("未识别的卷轴类型"+q_itemBean.getQ_transfer());
	}

	@Override
	public void unuse(Player player, String... parameters) {
		
	}

}
