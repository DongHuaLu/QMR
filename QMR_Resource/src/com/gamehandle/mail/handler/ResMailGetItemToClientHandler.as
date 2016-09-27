package com.game.mail.handler{

	import com.game.mail.message.ResMailGetItemToClientMessage;
	import net.Handler;

	public class ResMailGetItemToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMailGetItemToClientMessage = ResMailGetItemToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}