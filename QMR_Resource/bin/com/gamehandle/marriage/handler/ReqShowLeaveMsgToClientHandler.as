package com.game.marriage.handler{

	import com.game.marriage.message.ReqShowLeaveMsgToClientMessage;
	import net.Handler;

	public class ReqShowLeaveMsgToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ReqShowLeaveMsgToClientMessage = ReqShowLeaveMsgToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}