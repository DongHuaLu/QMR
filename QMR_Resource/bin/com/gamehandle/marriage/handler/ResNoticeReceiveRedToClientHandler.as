package com.game.marriage.handler{

	import com.game.marriage.message.ResNoticeReceiveRedToClientMessage;
	import net.Handler;

	public class ResNoticeReceiveRedToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResNoticeReceiveRedToClientMessage = ResNoticeReceiveRedToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}