package com.game.team.handler{

	import com.game.team.message.ResMapSearchTeamInfoClientMessage;
	import net.Handler;

	public class ResMapSearchTeamInfoClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMapSearchTeamInfoClientMessage = ResMapSearchTeamInfoClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}