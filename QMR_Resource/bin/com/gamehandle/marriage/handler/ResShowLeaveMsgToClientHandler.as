package com.game.marriage.handler{

	import com.game.marriage.message.ResShowLeaveMsgToClientMessage;
	import net.Handler;

	public class ResShowLeaveMsgToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResShowLeaveMsgToClientMessage = ResShowLeaveMsgToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}