package com.game.player.handler{

	import com.game.player.message.ResOtherPlayerInfoMessage;
	import net.Handler;

	public class ResOtherPlayerInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResOtherPlayerInfoMessage = ResOtherPlayerInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}