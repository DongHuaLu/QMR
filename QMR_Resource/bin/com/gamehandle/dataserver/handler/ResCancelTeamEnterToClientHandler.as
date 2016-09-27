package com.game.dataserver.handler{

	import com.game.dataserver.message.ResCancelTeamEnterToClientMessage;
	import net.Handler;

	public class ResCancelTeamEnterToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResCancelTeamEnterToClientMessage = ResCancelTeamEnterToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}