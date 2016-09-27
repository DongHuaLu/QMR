package com.game.protect.handler{

	import com.game.protect.message.ResAskedPanelToClientMessage;
	import net.Handler;

	public class ResAskedPanelToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResAskedPanelToClientMessage = ResAskedPanelToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}