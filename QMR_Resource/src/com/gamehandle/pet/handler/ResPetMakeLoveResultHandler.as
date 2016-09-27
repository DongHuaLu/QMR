package com.game.pet.handler{

	import com.game.pet.message.ResPetMakeLoveResultMessage;
	import net.Handler;

	public class ResPetMakeLoveResultHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPetMakeLoveResultMessage = ResPetMakeLoveResultMessage(this.message);
			//TODO 添加消息处理
		}
	}
}