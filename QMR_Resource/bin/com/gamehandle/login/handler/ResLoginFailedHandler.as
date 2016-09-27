package com.game.login.handler{

	import com.game.login.message.ResLoginFailedMessage;
	import net.Handler;

	public class ResLoginFailedHandler extends Handler {
	
		public override function action(): void{
			var msg: ResLoginFailedMessage = ResLoginFailedMessage(this.message);
			//TODO 添加消息处理
		}
	}
}