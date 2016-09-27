package com.game.login.handler{

	import com.game.login.message.ResCreateFailedMessage;
	import net.Handler;

	public class ResCreateFailedHandler extends Handler {
	
		public override function action(): void{
			var msg: ResCreateFailedMessage = ResCreateFailedMessage(this.message);
			//TODO 添加消息处理
		}
	}
}