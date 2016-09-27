package com.game.dataserver.handler{

	import com.game.dataserver.message.ResGetBattlefieldTeamMemberInfoToGameMessage;
	import net.Handler;

	public class ResGetBattlefieldTeamMemberInfoToGameHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGetBattlefieldTeamMemberInfoToGameMessage = ResGetBattlefieldTeamMemberInfoToGameMessage(this.message);
			//TODO 添加消息处理
		}
	}
}