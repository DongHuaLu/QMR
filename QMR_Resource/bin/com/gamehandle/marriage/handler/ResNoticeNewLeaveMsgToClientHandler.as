package com.game.marriage.handler{

	import com.game.marriage.message.ResNoticeNewLeaveMsgToClientMessage;
	import net.Handler;

	public class ResNoticeNewLeaveMsgToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResNoticeNewLeaveMsgToClientMessage = ResNoticeNewLeaveMsgToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}