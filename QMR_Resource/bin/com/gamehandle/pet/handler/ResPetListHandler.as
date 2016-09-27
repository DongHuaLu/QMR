package com.game.pet.handler{

	import com.game.pet.message.ResPetListMessage;
	import net.Handler;

	public class ResPetListHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPetListMessage = ResPetListMessage(this.message);
			//TODO 添加消息处理
		}
	}
}