package com.game.backpack.structs;

import org.apache.log4j.Logger;

import com.game.backpack.script.IItemScript;
import com.game.data.bean.Q_itemBean;
import com.game.data.manager.DataManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

public class ScriptItem extends Item implements IAutoUseItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7470884872809626154L;

	private static Logger log=Logger.getLogger(ScriptItem.class);
	@Override
	public void use(Player player, String... parameters) {
		Q_itemBean q_itemBean = DataManager.getInstance().q_itemContainer.getMap().get(getItemModelId());
		//获取脚本编号
		int q_script = q_itemBean.getQ_script();
		//脚本类物品
		IItemScript script = (IItemScript)ManagerPool.scriptManager.getScript(q_script);
		
//		if(q_script==1009103){
////		脚本调试
//			JewelCaseORBox box=new JewelCaseORBox();
//			box.use(this, player, parameters);
//			return;
//		}
//		if(q_script==1009104){
//			XongXinBaoZiDan xxbzd=new XongXinBaoZiDan();
//			xxbzd.use(this, player, parameters);
//		}

		
		//触发脚本
		if(script!=null){
			script.use(this, player, parameters);
		}
		
		
	}

	@Override
	public void unuse(Player player, String... parameters) {
	}

	@Override
	public boolean autoTakeUpUse(Player player, String... parameters) {
		Q_itemBean q_itemBean = DataManager.getInstance().q_itemContainer.getMap().get(getItemModelId());
		//获取脚本编号
		int q_script = q_itemBean.getQ_script();
		//脚本类物品
		IItemScript script = (IItemScript)ManagerPool.scriptManager.getScript(q_script);
		if(script!=null){
			try{
				script.use(this, player, parameters);
				return true;
			}catch (Exception e) {
				log.error(e, e);
			}
		}
		return false;
	}
}