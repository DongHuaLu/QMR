package com.game.dataserver.handler{

	import com.game.dataserver.message.ResEnterErrorToClientMessage;
	import net.Handler;

	public class ResEnterErrorToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResEnterErrorToClientMessage = ResEnterErrorToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}