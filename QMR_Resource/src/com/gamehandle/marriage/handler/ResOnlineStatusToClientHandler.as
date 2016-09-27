package com.game.marriage.handler{

	import com.game.marriage.message.ResOnlineStatusToClientMessage;
	import net.Handler;

	public class ResOnlineStatusToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResOnlineStatusToClientMessage = ResOnlineStatusToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}