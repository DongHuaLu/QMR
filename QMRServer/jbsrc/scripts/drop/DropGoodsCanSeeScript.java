package scripts.drop;
import com.game.drop.script.IDropGoodsCanSeeScript;
import com.game.drop.structs.MapDropInfo;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;

/**
 * 
 * @author 赵聪慧
 * @2012-9-12 上午2:54:06
 */
public class DropGoodsCanSeeScript implements IDropGoodsCanSeeScript {

	@Override
	public int getId() {
		return ScriptEnum.DROPGOODS_SEE;
	}

	@Override
	public boolean cansee(Player player, MapDropInfo mapDropInfo) {
		if(mapDropInfo.getShowSet().contains(player.getId())){
			return true;
		}else if(mapDropInfo.getHideSet().contains(player.getId())){
			return false;
		}
		return mapDropInfo.isShow();
	}

}
