package com.game.gift.handler{

	import com.game.gift.message.ResSendGiftInfoToClientMessage;
	import net.Handler;

	public class ResSendGiftInfoToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResSendGiftInfoToClientMessage = ResSendGiftInfoToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}