package com.game.player.handler{

	import com.game.player.message.ResChangePlayerUserToClientMessage;
	import net.Handler;

	public class ResChangePlayerUserToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResChangePlayerUserToClientMessage = ResChangePlayerUserToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}