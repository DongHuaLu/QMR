package com.game.horse.handler{

	import com.game.horse.message.ReshorseLuckyRodMessage;
	import net.Handler;

	public class ReshorseLuckyRodHandler extends Handler {
	
		public override function action(): void{
			var msg: ReshorseLuckyRodMessage = ReshorseLuckyRodMessage(this.message);
			//TODO 添加消息处理
		}
	}
}