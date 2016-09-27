package com.game.pet.handler{

	import com.game.pet.message.ResPetDieMessage;
	import net.Handler;

	public class ResPetDieHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPetDieMessage = ResPetDieMessage(this.message);
			//TODO 添加消息处理
		}
	}
}