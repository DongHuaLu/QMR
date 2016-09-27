package com.game.mail.handler{

	import com.game.mail.message.ResMailQueryListToClientMessage;
	import net.Handler;

	public class ResMailQueryListToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMailQueryListToClientMessage = ResMailQueryListToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}