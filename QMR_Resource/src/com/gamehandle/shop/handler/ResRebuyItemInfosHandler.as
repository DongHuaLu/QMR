package com.game.shop.handler{

	import com.game.shop.message.ResRebuyItemInfosMessage;
	import net.Handler;

	public class ResRebuyItemInfosHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRebuyItemInfosMessage = ResRebuyItemInfosMessage(this.message);
			//TODO 添加消息处理
		}
	}
}