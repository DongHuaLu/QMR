package com.game.gift.handler{

	import com.game.gift.message.ResGetPlatformGiftMessage;
	import net.Handler;

	public class ResGetPlatformGiftHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGetPlatformGiftMessage = ResGetPlatformGiftMessage(this.message);
			//TODO 添加消息处理
		}
	}
}