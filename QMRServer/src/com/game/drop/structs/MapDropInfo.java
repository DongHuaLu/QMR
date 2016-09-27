package com.game.drop.structs;

import java.util.HashSet;

import org.apache.log4j.Logger;

import com.game.backpack.structs.Item;
import com.game.drop.script.IDropGoodsCanSeeScript;
import com.game.manager.ManagerPool;
import com.game.map.bean.DropGoodsInfo;
import com.game.map.structs.IMapObject;
import com.game.map.structs.Map;
import com.game.monster.script.IMonsterCanSeeScript;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;
import com.game.structs.Position;
import com.game.utils.MapUtils;

/**
 * 物品掉落 封装
 *
 * @author 赵聪慧
 *
 */
public class MapDropInfo implements IMapObject {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MapDropInfo.class);

	private DropGoodsInfo dropinfo;	//消息  记录物品掉落座标等信息
	private Item item;				//物品实体
	private Map map;
	private long cleartimepoint;	//消失时间点
	
	//是否显示
	private boolean show = true;
	//显示set
	private HashSet<Long> showSet = new HashSet<Long>();
	//隐藏set
	private HashSet<Long> hideSet = new HashSet<Long>();
	
	
	public MapDropInfo(DropGoodsInfo info,Item item,Map map, long cleartimepoint){
		this.dropinfo=info;
		this.item=item;
		this.map=map;
		this.cleartimepoint = cleartimepoint;
	}

	public DropGoodsInfo getDropinfo() {
		return dropinfo;
	}

	public Item getItem() {
		return item;
	}

	public long getCleartimepoint() {
		return cleartimepoint;
	}

	public void setCleartimepoint(long cleartimepoint) {
		this.cleartimepoint = cleartimepoint;
	}

	@Override
	public int getServerId() {
		return map.getServerId();
	}

	@Override
	public int getLine() {
		return map.getLineId();
	}

	@Override
	public int getMap() {
		return (int) map.getId();
	}

	@Override
	public Position getPosition() {
		return MapUtils.buildPosition(dropinfo.getX(), dropinfo.getY());
	}

	@Override
	public boolean canSee(Player player) {
		IDropGoodsCanSeeScript script = (IDropGoodsCanSeeScript)ManagerPool.scriptManager.getScript(ScriptEnum.DROPGOODS_SEE);
		if(script!=null){
			try{
				return script.cansee(player, this);
			}catch (Exception e) {
				logger.error(e, e);
			}
		}else{
			logger.error("dropGoods是否可见脚本不存在！");
		}
		return true;
	}

	@Override
	public long getId() {
		return item.getId();
	}

	public int getMapModelid() {
		return map.getMapModelid();
	}

	public Map getMapIns() {
		return map;
	}
	public boolean isShow() {
		return show;
	}
	public void setShow(boolean show) {
		this.show = show;
	}
	public HashSet<Long> getShowSet() {
		return showSet;
	}
	public void setShowSet(HashSet<Long> showSet) {
		this.showSet = showSet;
	}
	public HashSet<Long> getHideSet() {
		return hideSet;
	}
	public void setHideSet(HashSet<Long> hideSet) {
		this.hideSet = hideSet;
	}
}
