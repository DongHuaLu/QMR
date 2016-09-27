package com.game.shop.handler{

	import com.game.shop.message.ResBuyItemResultMessage;
	import net.Handler;

	public class ResBuyItemResultHandler extends Handler {
	
		public override function action(): void{
			var msg: ResBuyItemResultMessage = ResBuyItemResultMessage(this.message);
			//TODO 添加消息处理
		}
	}
}