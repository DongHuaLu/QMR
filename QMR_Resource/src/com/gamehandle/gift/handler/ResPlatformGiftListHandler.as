package com.game.gift.handler{

	import com.game.gift.message.ResPlatformGiftListMessage;
	import net.Handler;

	public class ResPlatformGiftListHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlatformGiftListMessage = ResPlatformGiftListMessage(this.message);
			//TODO 添加消息处理
		}
	}
}