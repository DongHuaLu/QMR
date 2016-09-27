package com.game.mail.handler{

	import com.game.mail.message.ResMailReturnToClientMessage;
	import net.Handler;

	public class ResMailReturnToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMailReturnToClientMessage = ResMailReturnToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}