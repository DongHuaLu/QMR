package com.game.player.handler{

	import com.game.player.message.ResPlayerSpeedChangeMessage;
	import net.Handler;

	public class ResPlayerSpeedChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerSpeedChangeMessage = ResPlayerSpeedChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}