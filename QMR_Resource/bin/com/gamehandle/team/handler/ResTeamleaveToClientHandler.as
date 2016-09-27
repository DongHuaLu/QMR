package com.game.team.handler{

	import com.game.team.message.ResTeamleaveToClientMessage;
	import net.Handler;

	public class ResTeamleaveToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResTeamleaveToClientMessage = ResTeamleaveToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}