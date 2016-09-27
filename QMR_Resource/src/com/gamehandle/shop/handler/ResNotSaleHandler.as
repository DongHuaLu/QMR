package com.game.shop.handler{

	import com.game.shop.message.ResNotSaleMessage;
	import net.Handler;

	public class ResNotSaleHandler extends Handler {
	
		public override function action(): void{
			var msg: ResNotSaleMessage = ResNotSaleMessage(this.message);
			//TODO 添加消息处理
		}
	}
}