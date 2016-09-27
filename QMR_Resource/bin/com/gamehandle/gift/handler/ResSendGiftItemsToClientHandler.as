package com.game.gift.handler{

	import com.game.gift.message.ResSendGiftItemsToClientMessage;
	import net.Handler;

	public class ResSendGiftItemsToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResSendGiftItemsToClientMessage = ResSendGiftItemsToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}