package com.game.scripts.handler{

	import com.game.scripts.message.ResPanelStatusInfoToClientMessage;
	import net.Handler;

	public class ResPanelStatusInfoToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPanelStatusInfoToClientMessage = ResPanelStatusInfoToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}