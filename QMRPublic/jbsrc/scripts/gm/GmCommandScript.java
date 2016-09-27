package scripts.gm;

import org.apache.log4j.Logger;

import com.game.gm.script.IGmCommandScript;


public class GmCommandScript implements IGmCommandScript {

	protected static Logger log = Logger.getLogger(GmCommandScript.class);
	
	//GM命令
	public static int GM_COMMAND = 13;
		
	@Override
	public int getId() {
		return GM_COMMAND;
	}

	@Override
	public void doCommand(String command) {
		//分割指令
		String[] strs = command.split(" ");
		
		if("&reloadmap".equalsIgnoreCase(strs[0])){
		}
	}
	
}
