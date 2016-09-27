package com.game.login.handler{

	import com.game.login.message.ResDelPlayerStatusToClientMessage;
	import net.Handler;

	public class ResDelPlayerStatusToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResDelPlayerStatusToClientMessage = ResDelPlayerStatusToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}