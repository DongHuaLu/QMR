package com.game.protect.handler{

	import com.game.protect.message.ResVerificationCoolingToGameMessage;
	import net.Handler;

	public class ResVerificationCoolingToGameHandler extends Handler {
	
		public override function action(): void{
			var msg: ResVerificationCoolingToGameMessage = ResVerificationCoolingToGameMessage(this.message);
			//TODO 添加消息处理
		}
	}
}