package com.game.horse.handler{

	import com.game.horse.message.ReshorseLuckyPenteMessage;
	import net.Handler;

	public class ReshorseLuckyPenteHandler extends Handler {
	
		public override function action(): void{
			var msg: ReshorseLuckyPenteMessage = ReshorseLuckyPenteMessage(this.message);
			//TODO 添加消息处理
		}
	}
}