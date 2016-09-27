package com.game.pet.handler{

	import com.game.pet.message.ResPetChangeMessage;
	import net.Handler;

	public class ResPetChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPetChangeMessage = ResPetChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}