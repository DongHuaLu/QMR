package com.game.pet.handler{

	import com.game.pet.message.ResPetHidenMessage;
	import net.Handler;

	public class ResPetHidenHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPetHidenMessage = ResPetHidenMessage(this.message);
			//TODO 添加消息处理
		}
	}
}