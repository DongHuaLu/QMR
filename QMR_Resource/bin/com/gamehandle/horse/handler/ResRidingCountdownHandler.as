package com.game.horse.handler{

	import com.game.horse.message.ResRidingCountdownMessage;
	import net.Handler;

	public class ResRidingCountdownHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRidingCountdownMessage = ResRidingCountdownMessage(this.message);
			//TODO 添加消息处理
		}
	}
}