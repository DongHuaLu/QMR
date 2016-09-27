package com.game.team.handler{

	import com.game.team.message.ResTeammateMoveToClientMessage;
	import net.Handler;

	public class ResTeammateMoveToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResTeammateMoveToClientMessage = ResTeammateMoveToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}