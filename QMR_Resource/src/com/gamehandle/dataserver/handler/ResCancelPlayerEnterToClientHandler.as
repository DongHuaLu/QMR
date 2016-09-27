package com.game.dataserver.handler{

	import com.game.dataserver.message.ResCancelPlayerEnterToClientMessage;
	import net.Handler;

	public class ResCancelPlayerEnterToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResCancelPlayerEnterToClientMessage = ResCancelPlayerEnterToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}