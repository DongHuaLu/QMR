package com.game.mail.handler{

	import com.game.mail.message.ResMailGetNewMailToClientMessage;
	import net.Handler;

	public class ResMailGetNewMailToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMailGetNewMailToClientMessage = ResMailGetNewMailToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}