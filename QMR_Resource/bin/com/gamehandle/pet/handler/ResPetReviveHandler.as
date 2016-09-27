package com.game.pet.handler{

	import com.game.pet.message.ResPetReviveMessage;
	import net.Handler;

	public class ResPetReviveHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPetReviveMessage = ResPetReviveMessage(this.message);
			//TODO 添加消息处理
		}
	}
}