package com.game.team.handler{

	import com.game.team.message.ResTeamClientRefreshMessage;
	import net.Handler;

	public class ResTeamClientRefreshHandler extends Handler {
	
		public override function action(): void{
			var msg: ResTeamClientRefreshMessage = ResTeamClientRefreshMessage(this.message);
			//TODO 添加消息处理
		}
	}
}