package com.game.player.handler{

	import com.game.player.message.ResPlayerStateChangeMessage;
	import net.Handler;

	public class ResPlayerStateChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerStateChangeMessage = ResPlayerStateChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}