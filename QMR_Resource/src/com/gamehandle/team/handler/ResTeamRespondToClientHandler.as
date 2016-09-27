package com.game.team.handler{

	import com.game.team.message.ResTeamRespondToClientMessage;
	import net.Handler;

	public class ResTeamRespondToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResTeamRespondToClientMessage = ResTeamRespondToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}