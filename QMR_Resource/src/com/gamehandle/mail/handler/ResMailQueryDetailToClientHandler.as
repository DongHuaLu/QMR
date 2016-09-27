package com.game.mail.handler{

	import com.game.mail.message.ResMailQueryDetailToClientMessage;
	import net.Handler;

	public class ResMailQueryDetailToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMailQueryDetailToClientMessage = ResMailQueryDetailToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}