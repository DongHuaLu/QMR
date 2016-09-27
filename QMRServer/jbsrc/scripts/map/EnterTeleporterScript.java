package scripts.map;

import java.util.List;

import org.apache.log4j.Logger;

import com.game.data.bean.Q_transferBean;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.script.IEnterTeleporterScript;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.script.structs.ScriptEnum;
import com.game.structs.Grid;
import com.game.structs.Position;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.Symbol;

public class EnterTeleporterScript implements IEnterTeleporterScript {

	private Logger log = Logger.getLogger(EnterMapScript.class);
	public static int mizong_scriptId = 4006;		//迷踪幻境副本scriptid
	public static int maze_scriptId = 4007;		//迷宫scriptid
	public static int baguazheng_scriptId = 4008;				//八卦阵
	
	
	@Override
	public int getId() {
		return ScriptEnum.ON_TELEPORTERS;
	}

	@Override
	public void onTeleporter(Player player, int line, int tranId ,int scriptid) {
		Q_transferBean tran = ManagerPool.dataManager.q_transferContainer.getMap().get(tranId);
		if (scriptid == 0) {
			// 传送地点 {X,Y}#半径@判断等级
			String targetpoint = tran.getQ_tran_to_range();
			targetpoint = targetpoint.replaceAll("\\{|\\}", "");
			String[] strs = targetpoint.split(Symbol.AT_REG);
			int needgrade = Integer.parseInt(strs[1]);
			// 等级不足
			if (player.getLevel() < needgrade) {
				MessageUtil.notify_player(player, Notifys.ERROR, java.text.MessageFormat.format(ResManager.getInstance().getString("等级限制，需要达到{0}级才可进入"), new Object[] {needgrade}));
				ManagerPool.mapManager.changeMapFailed(player);
				return;
			}

			String targetexp[] = strs[0].split(Symbol.JINGHAO_REG);
			String point[] = targetexp[0].split(Symbol.DOUHAO_REG);
			short radius = (short) (Short.parseShort(targetexp[1]) * MapUtils.GRID_BORDER);
			short targetx = (short) (Short.parseShort(point[0]) * MapUtils.GRID_BORDER);
			short targety = (short) (Short.parseShort(point[1]) * MapUtils.GRID_BORDER);
			Position center = new Position(targetx, targety);
			Grid[][] mapInfos = ManagerPool.mapManager.getMapBlocks(tran.getQ_tran_to_map());
			List<Grid> grids = MapUtils.getRoundGrid(center, radius, mapInfos);
			// 随即传送位置
			Position position = null;
			while (grids.size() > 0) {
				Grid grid = grids.get(RandomUtils.random(grids.size()));
				grids.remove(grid);
				if (grid.getBlock() != 0) {
					position = grid.getCenter();
					break;
				}
			}
			// 开始传送
			if (position != null) {
				ManagerPool.mapManager.changeMap(player, tran.getQ_tran_to_map(), tran.getQ_tran_to_map(), line, position, (byte)1, this.getClass().getName());
			}
		} else {

			
			IEnterTeleporterScript script = (IEnterTeleporterScript) ManagerPool.scriptManager.getScript(maze_scriptId);
			if (script != null) {
				try {
					script.onTeleporter(player, line, tranId,scriptid);
				} catch (Exception e) {
					log.error(e, e);
				}
			}
			
			
			script = (IEnterTeleporterScript) ManagerPool.scriptManager.getScript(mizong_scriptId);
			if (script != null) {
				try {
					script.onTeleporter(player, line, tranId,scriptid);
				} catch (Exception e) {
					log.error(e, e);
				}
			}
			
			script = (IEnterTeleporterScript) ManagerPool.scriptManager.getScript(baguazheng_scriptId);
			if (script != null) {
				try {
					script.onTeleporter(player, line, tranId, scriptid);
				} catch (Exception e) {
					log.error(e, e);
				}
			}
			
			
		}
	}
}
