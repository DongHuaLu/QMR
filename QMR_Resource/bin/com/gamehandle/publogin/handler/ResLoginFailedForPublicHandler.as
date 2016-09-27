package com.game.publogin.handler{

	import com.game.publogin.message.ResLoginFailedForPublicMessage;
	import net.Handler;

	public class ResLoginFailedForPublicHandler extends Handler {
	
		public override function action(): void{
			var msg: ResLoginFailedForPublicMessage = ResLoginFailedForPublicMessage(this.message);
			//TODO 添加消息处理
		}
	}
}