package com.game.team.handler{

	import com.game.team.message.ResInviteClientMessage;
	import net.Handler;

	public class ResInviteClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResInviteClientMessage = ResInviteClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}