package com.game.marriage.handler{

	import com.game.marriage.message.ReqRedEnvelopeToClientMessage;
	import net.Handler;

	public class ReqRedEnvelopeToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ReqRedEnvelopeToClientMessage = ReqRedEnvelopeToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}