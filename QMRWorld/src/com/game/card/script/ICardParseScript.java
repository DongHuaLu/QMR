package com.game.card.script;

import com.game.player.structs.Player;
import com.game.script.IScript;
import java.util.HashMap;
import org.apache.mina.core.session.IoSession;

/**
 * CDKEY解析脚本
 *
 * @author 杨鸿岚
 */
public interface ICardParseScript extends IScript {

	/**
	 * CDKEY解析
	 *
	 * @param player	玩家
	 * @param card		CDKEY
	 * @param argUserName	平台账号名
	 * @param argZoneName	平台区服名
	 * @param argName	平台名
	 * @return int
	 */
	public int cardParse(Player player, String card, String argUserName, String argZoneName, String argName);
	
	/**
	 * HTTP发来的CDKEY进行处理
	 * @param ioSession http链接
	 * @param paramMap 参数Map
	 * @return 
	 */
	public void httpCardParse(IoSession ioSession, HashMap<String, String> paramMap);
}
