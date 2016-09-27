package com.game.pet.handler{

	import com.game.pet.message.ResPetDieBroadcastMessage;
	import net.Handler;

	public class ResPetDieBroadcastHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPetDieBroadcastMessage = ResPetDieBroadcastMessage(this.message);
			//TODO 添加消息处理
		}
	}
}