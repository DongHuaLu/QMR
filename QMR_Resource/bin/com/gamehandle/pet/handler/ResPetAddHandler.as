package com.game.pet.handler{

	import com.game.pet.message.ResPetAddMessage;
	import net.Handler;

	public class ResPetAddHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPetAddMessage = ResPetAddMessage(this.message);
			//TODO 添加消息处理
		}
	}
}