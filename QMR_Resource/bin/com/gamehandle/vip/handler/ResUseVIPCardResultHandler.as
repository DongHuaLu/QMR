package com.game.vip.handler{

	import com.game.vip.message.ResUseVIPCardResultMessage;
	import net.Handler;

	public class ResUseVIPCardResultHandler extends Handler {
	
		public override function action(): void{
			var msg: ResUseVIPCardResultMessage = ResUseVIPCardResultMessage(this.message);
			//TODO 添加消息处理
		}
	}
}