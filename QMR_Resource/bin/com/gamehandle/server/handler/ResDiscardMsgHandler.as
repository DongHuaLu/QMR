package com.game.server.handler{

	import com.game.server.message.ResDiscardMsgMessage;
	import net.Handler;

	public class ResDiscardMsgHandler extends Handler {
	
		public override function action(): void{
			var msg: ResDiscardMsgMessage = ResDiscardMsgMessage(this.message);
			//TODO 添加消息处理
		}
	}
}