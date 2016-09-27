package scripts.player;

import java.util.List;

import org.apache.log4j.Logger;

import com.game.cooldown.structs.CooldownTypes;
import com.game.manager.ManagerPool;
import com.game.map.structs.Jump;
import com.game.player.script.IPlayerJumpScript;
import com.game.player.script.PlayerCheckType;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerState;
import com.game.script.structs.ScriptEnum;
import com.game.structs.Position;
import com.game.utils.Global;
import com.game.utils.MapUtils;

public class PlayerJumpScript implements IPlayerJumpScript {

	protected static Logger log = Logger.getLogger(PlayerJumpScript.class);
	
	@Override
	public int getId() {
		return ScriptEnum.PLAYER_JUMP;
	}

	@Override
	public boolean onJump(Player player, List<Position> positions, int type) {
		//二次跳跃中
		if (PlayerState.DOUBLEJUMP.compare(player.getState())) {
			log.error("玩家(" + player.getId() + ")已经二次跳跃中了");
			if (ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.JUMP, null, 0)) {
				long time = ManagerPool.cooldownManager.getCooldownTime(player, CooldownTypes.JUMP, null);
				log.error("玩家(" + player.getId() + ")已经二次跳跃中了,冷却剩余:" + time);
				ManagerPool.playerManager.playercheck(player, PlayerCheckType.JUMP_COOLDOWN, time);
			}
//			ManagerPool.mapManager.resetPlayerJumpPosition(player);
//			return false;
		}else if (type==1 && PlayerState.JUMP.compare(player.getState())){
			log.error("玩家(" + player.getId() + ")已经跳跃中了");
			if (ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.JUMP, null, 0)) {
				long time = ManagerPool.cooldownManager.getCooldownTime(player, CooldownTypes.JUMP, null);
				log.error("玩家(" + player.getId() + ")已经跳跃中了,冷却剩余:" + time);
				ManagerPool.playerManager.playercheck(player, PlayerCheckType.JUMP_COOLDOWN, time);
			}
//			ManagerPool.mapManager.resetPlayerJumpPosition(player);
//			return false;
		}
		// 取起点坐标
		Position start = positions.get(0);
				
		if (!PlayerState.JUMP.compare(player.getState())) {
			// 判断起点坐标差值
			double distance = MapUtils.countDistance(start, player.getPosition());
			double allow_distance = (double) Global.DISTANCE * player.getSpeed() / Global.BASE_SPEED * Global.JUMP_SPEED / Global.MAX_PROBABILITY;
			if (allow_distance < Global.DISTANCE) {
				allow_distance = Global.DISTANCE;
			}
			allow_distance = 1000;
			if (distance > allow_distance) {
				log.error("player " + player.getId() + " 一跳起始点距离过远, distance "
					+ distance + " allow_distance "
					+ allow_distance + ", now " + player.getPosition() + ", jump "
					+ start);
				ManagerPool.playerManager.playercheck(player, PlayerCheckType.JUMP_DISTANCE, distance);
				// 清空当前移动路径
				ManagerPool.mapManager.resetPlayerJumpPosition(player);
				return false;
			}
		} else {
			Jump jump = player.getJump();
			// 检查方向
//			int dir1 = MapUtils.countDirection(jump.getJumpEnd(), jump.getJumpStart());
//			int dir2 = MapUtils.countDirection(start, jump.getJumpStart());
//			if (dir1 != dir2) {
//				log.error("player " + player.getId() + " 二跳方向不对, dir1 "
//					+ dir1 + " dir2 "
//					+ dir2 + ", jumpStart " + jump.getJumpStart() + ", jumpEnd "
//					+ jump.getJumpEnd() + ", start " + start);
//				player.check();
//			}

			long time = System.currentTimeMillis() - jump.getJumpStartTime();
			// 判断起点坐标差值
			double allow_distance = (double) time * jump.getSpeed() / 1000 + (double) Global.DISTANCE * player.getSpeed() / Global.BASE_SPEED * Global.JUMP_SPEED / Global.MAX_PROBABILITY;
			double distance = MapUtils.countDistance(start, jump.getJumpStart());
			// 检查距离
			allow_distance = 1000;
			if (distance > allow_distance) {
				log.error("player " + player.getId() + " 二跳起始点距离过远, distance "
					+ distance + " allow_distance "
					+ allow_distance + ", time " + time + ", speed "
					+ jump.getSpeed());
				ManagerPool.playerManager.playercheck(player, PlayerCheckType.JUMP_DISTANCE, distance);
				
				ManagerPool.mapManager.resetPlayerJumpPosition(player);
				return false;
			}
		}
		return true;
	}
	
}
