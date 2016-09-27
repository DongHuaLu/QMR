package com.game.mail.handler{

	import com.game.mail.message.ResMailQueryUserToClientMessage;
	import net.Handler;

	public class ResMailQueryUserToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMailQueryUserToClientMessage = ResMailQueryUserToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}