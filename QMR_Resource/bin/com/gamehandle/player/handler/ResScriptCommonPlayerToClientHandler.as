package com.game.player.handler{

	import com.game.player.message.ResScriptCommonPlayerToClientMessage;
	import net.Handler;

	public class ResScriptCommonPlayerToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResScriptCommonPlayerToClientMessage = ResScriptCommonPlayerToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}