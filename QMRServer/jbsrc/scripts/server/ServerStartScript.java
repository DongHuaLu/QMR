package scripts.server;

import com.game.manager.ManagerPool;
import com.game.script.structs.ScriptEnum;
import com.game.server.impl.WServer;
import com.game.server.script.IServerStartScript;
/**服务器启动调用
 * 
 * @author zhangrong
 *
 */
public class ServerStartScript implements IServerStartScript {

	@Override
	public int getId() {
		return ScriptEnum.SERVER_START;
	}

	@Override
	public void onStart(String web, int serverId) {
		setswitch();
	}
	
	//设定功能开关
	public void setswitch(){
		//韩国版-关闭境界系统
		if(WServer.getInstance().getServerWeb().equals("hgpupugame")) {	
			ManagerPool.realmManager.setIsopen(false);
		}
		
		
	}
	
	
	

}
