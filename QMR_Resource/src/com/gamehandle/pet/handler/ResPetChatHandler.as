package com.game.pet.handler{

	import com.game.pet.message.ResPetChatMessage;
	import net.Handler;

	public class ResPetChatHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPetChatMessage = ResPetChatMessage(this.message);
			//TODO 添加消息处理
		}
	}
}