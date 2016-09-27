package scripts.web_tw;

import java.util.ArrayList;
import java.util.List;

import com.game.player.structs.Player;
import com.game.script.IScript;
import com.game.scripts.bean.PanelInfo;
import com.game.utils.NpcParamUtil;
/**台湾特殊激活码超连接面板
 * 
 * @author zhangrong
 *
 */
public class TwHttpCode implements IScript{

	@Override
	public int getId() {
		return 104;
	}
	
	//类型1 弹出面板
	public void gettwhttp(List<Object> para){
		Player player = (Player) para.get(0);
		if (player == null ) {
			return;
		}
		int type = Integer.parseInt((String)para.get(1)); 
		if (type == 1) {
			int panelid = Integer.parseInt((String)para.get(2)); 
			PanelInfo panel = NpcParamUtil.getPanelInfo(player, panelid);
			List<String> list = new ArrayList<String>();
			panel.setButtoninfolist(NpcParamUtil.getbuttonInfo(player , list));
			NpcParamUtil.showPanel(player, panel);
		}
	}

}
