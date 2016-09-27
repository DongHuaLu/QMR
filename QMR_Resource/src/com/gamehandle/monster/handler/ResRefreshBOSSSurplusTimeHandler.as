package com.game.monster.handler{

	import com.game.monster.message.ResRefreshBOSSSurplusTimeMessage;
	import net.Handler;

	public class ResRefreshBOSSSurplusTimeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRefreshBOSSSurplusTimeMessage = ResRefreshBOSSSurplusTimeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}