package com.game.player.handler{

	import com.game.player.message.ResPlayerCheckOnlineMessage;
	import net.Handler;

	public class ResPlayerCheckOnlineHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerCheckOnlineMessage = ResPlayerCheckOnlineMessage(this.message);
			//TODO 添加消息处理
		}
	}
}