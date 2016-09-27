package com.game.player.handler{

	import com.game.player.message.ResPlayerDieMessage;
	import net.Handler;

	public class ResPlayerDieHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerDieMessage = ResPlayerDieMessage(this.message);
			//TODO 添加消息处理
		}
	}
}