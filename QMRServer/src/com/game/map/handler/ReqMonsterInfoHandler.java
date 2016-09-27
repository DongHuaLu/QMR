package com.game.map.handler;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.map.manager.MapManager;
import com.game.map.message.ReqMonsterInfoMessage;
import com.game.map.message.ResRoundMonsterMessage;
import com.game.map.structs.Map;
import com.game.monster.structs.Monster;
import com.game.player.structs.Player;
import com.game.structs.Grid;
import com.game.utils.MessageUtil;
/**
 * 单个请求怪物信息
 * @author 赵聪慧
 *
 */
public class ReqMonsterInfoHandler extends Handler{

	Logger log = Logger.getLogger(ReqMonsterInfoHandler.class);

	public void action(){
		try{
			ReqMonsterInfoMessage msg = (ReqMonsterInfoMessage)this.getMessage();
			long monsterId = msg.getMonsterId();
			Player player= (Player)getParameter();
			Map map = MapManager.getInstance().getMap(player);
			if(map==null){
				log.error("map is null",new Exception(" rid:"+player.getId()+" modelid="+player.getMapModelId()+" mid:"+player.getMap()));
				return;
			}
			Monster monster = map.getMonsters().get(monsterId);
			if(player==null){ //角色不能为空
//				log.error("ReqMonsterInfoMessage player is null");
				return;
			}
			if(monster == null){ //怪物不能为空
//				log.error("ReqMonsterInfoMessage player("+player.getName()+":"+player.getId()+") monster("+monsterId+") is null");
				return;
			}
			if(monster.isDie()){ //怪物不能死亡
//				log.error("ReqMonsterInfoMessage player("+player.getName()+":"+player.getId()+") monster("+monsterId+") is die");
				return;
			}
			if(!monster.canSee(player)){ //怪物不能是不可见的
//				log.error("ReqMonsterInfoMessage player("+player.getName()+":"+player.getId()+") monster("+monsterId+") can not see player("+player.getId()+":"+player.getName()+")");
				return;
			}
			//
			if(zones.contains(map.getMapModelid())){ //记录副本请求怪物信息日志
				log.error("ReqMonsterInfoMessage player("+player.getName()+":"+player.getId()+") map("+map.getMapModelid()+":"+map.getId()+":"+map.getLineId()+" "+player.getPosition().toString()+") monster("+monster.getModelId()+":"+monster.getId()+")");
			}
			//
			if(player!=null && monster!=null && !monster.isDie() && monster.canSee(player)){
				ResRoundMonsterMessage resp=new ResRoundMonsterMessage();
				Grid[][] grids = MapManager.getInstance().getMapBlocks(map.getMapModelid());
				resp.setMonster(MapManager.getInstance().getMonsterInfo(monster, grids));
				MessageUtil.tell_player_message(player, resp);
			}
		}catch(Exception e){
			log.error(e);
		}
	}
	private static List<Integer> zones = Arrays.asList(30005,30007,30008,30009,30010,30011,30012,30013,41001,41002,41003,41004,41005,41006,41007,41100,41101,41102,41103,41104,41105,41106,41107,41108,41109,41110,41111,41112,41113,41114,41115,41116,41117,41118,41119,41120,42001,42002,42003,42004,42005,42006,42007,42008,42101,42111,42121,42122,42123);
}