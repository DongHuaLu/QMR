package com.game.drop.manager;

import org.apache.log4j.Logger;

import com.game.drop.script.IDropGoodsScript;
import com.game.drop.structs.DropItem;
import com.game.drop.structs.MapDropInfo;
import com.game.monster.structs.Monster;
import com.game.script.manager.ScriptManager;

/**
 * 脚本掉落
 * @author 赵聪慧
 * 2012-2-29下午1:53:49
 */
public class ScripDrop extends DropItem {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ScripDrop.class);

	private int scriptId;

	public int getScriptId() {
		return scriptId;
	}

	public void setScriptId(int scriptId) {
		this.scriptId = scriptId;
	}

	@Override
	protected MapDropInfo buildGoodsInfo(Monster monster) {
		try{
//			DropGoodsScriptImpl impl=new DropGoodsScriptImpl();
//			return impl.buildGoodsInfo(this, monster);
			IDropGoodsScript dropDrop =(IDropGoodsScript) ScriptManager.getInstance().getScript(getScriptId());
			return dropDrop.buildGoodsInfo(this, monster);
		}catch(Exception ex){
			logger.error(ex,ex);
		}
		return null;
	}
}
