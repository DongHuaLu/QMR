package com.game.dianjiangchun.handler{

	import com.game.dianjiangchun.message.SendDianjiangchunInfoToClientMessage;
	import net.Handler;

	public class SendDianjiangchunInfoToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: SendDianjiangchunInfoToClientMessage = SendDianjiangchunInfoToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}