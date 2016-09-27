package com.game.pet.handler{

	import com.game.pet.message.ResPetHeTiResultMessage;
	import net.Handler;

	public class ResPetHeTiResultHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPetHeTiResultMessage = ResPetHeTiResultMessage(this.message);
			//TODO 添加消息处理
		}
	}
}