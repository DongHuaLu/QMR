package com.game.player.handler{

	import com.game.player.message.ResChangePlayerNameToClientMessage;
	import net.Handler;

	public class ResChangePlayerNameToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResChangePlayerNameToClientMessage = ResChangePlayerNameToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}