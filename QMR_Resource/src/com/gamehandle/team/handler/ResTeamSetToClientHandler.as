package com.game.team.handler{

	import com.game.team.message.ResTeamSetToClientMessage;
	import net.Handler;

	public class ResTeamSetToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResTeamSetToClientMessage = ResTeamSetToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}