package com.game.team.handler{

	import com.game.team.message.ResAutoIntoTeamApplyClientMessage;
	import net.Handler;

	public class ResAutoIntoTeamApplyClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResAutoIntoTeamApplyClientMessage = ResAutoIntoTeamApplyClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}