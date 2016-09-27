package com.game.shop.handler{

	import com.game.shop.message.ResShopItemListMessage;
	import net.Handler;

	public class ResShopItemListHandler extends Handler {
	
		public override function action(): void{
			var msg: ResShopItemListMessage = ResShopItemListMessage(this.message);
			//TODO 添加消息处理
		}
	}
}