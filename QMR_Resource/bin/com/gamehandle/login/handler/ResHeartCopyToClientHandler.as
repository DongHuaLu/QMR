package com.game.login.handler{

	import com.game.login.message.ResHeartCopyToClientMessage;
	import net.Handler;

	public class ResHeartCopyToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResHeartCopyToClientMessage = ResHeartCopyToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}