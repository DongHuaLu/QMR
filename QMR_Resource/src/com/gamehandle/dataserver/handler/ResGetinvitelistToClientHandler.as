package com.game.dataserver.handler{

	import com.game.dataserver.message.ResGetinvitelistToClientMessage;
	import net.Handler;

	public class ResGetinvitelistToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGetinvitelistToClientMessage = ResGetinvitelistToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}