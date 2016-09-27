package com.game.dataserver.handler{

	import com.game.dataserver.message.ResTeamResetToClientMessage;
	import net.Handler;

	public class ResTeamResetToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResTeamResetToClientMessage = ResTeamResetToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}