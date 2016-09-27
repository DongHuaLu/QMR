package com.game.player.handler{

	import com.game.player.message.ResJumpMaxSpeedMessage;
	import net.Handler;

	public class ResJumpMaxSpeedHandler extends Handler {
	
		public override function action(): void{
			var msg: ResJumpMaxSpeedMessage = ResJumpMaxSpeedMessage(this.message);
			//TODO 添加消息处理
		}
	}
}