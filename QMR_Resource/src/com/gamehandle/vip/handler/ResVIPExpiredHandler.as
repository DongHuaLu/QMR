package com.game.vip.handler{

	import com.game.vip.message.ResVIPExpiredMessage;
	import net.Handler;

	public class ResVIPExpiredHandler extends Handler {
	
		public override function action(): void{
			var msg: ResVIPExpiredMessage = ResVIPExpiredMessage(this.message);
			//TODO 添加消息处理
		}
	}
}