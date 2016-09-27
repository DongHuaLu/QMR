package com.game.mail.handler{

	import com.game.mail.message.ResMailSendNewMailToClientMessage;
	import net.Handler;

	public class ResMailSendNewMailToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMailSendNewMailToClientMessage = ResMailSendNewMailToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}