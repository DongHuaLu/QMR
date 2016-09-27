package com.game.player.handler{

	import com.game.player.message.ResPlayerNameInfoToClientMessage;
	import net.Handler;

	public class ResPlayerNameInfoToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerNameInfoToClientMessage = ResPlayerNameInfoToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}