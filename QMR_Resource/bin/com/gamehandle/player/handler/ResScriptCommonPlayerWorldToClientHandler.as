package com.game.player.handler{

	import com.game.player.message.ResScriptCommonPlayerWorldToClientMessage;
	import net.Handler;

	public class ResScriptCommonPlayerWorldToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResScriptCommonPlayerWorldToClientMessage = ResScriptCommonPlayerWorldToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}