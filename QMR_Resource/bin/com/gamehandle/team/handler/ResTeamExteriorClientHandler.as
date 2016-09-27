package com.game.team.handler{

	import com.game.team.message.ResTeamExteriorClientMessage;
	import net.Handler;

	public class ResTeamExteriorClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResTeamExteriorClientMessage = ResTeamExteriorClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}