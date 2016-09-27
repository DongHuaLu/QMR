package com.game.marriage.handler{

	import com.game.marriage.message.ResRedEnvelopeToClientMessage;
	import net.Handler;

	public class ResRedEnvelopeToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRedEnvelopeToClientMessage = ResRedEnvelopeToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}