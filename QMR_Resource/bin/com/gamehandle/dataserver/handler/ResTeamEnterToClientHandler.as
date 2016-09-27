package com.game.dataserver.handler{

	import com.game.dataserver.message.ResTeamEnterToClientMessage;
	import net.Handler;

	public class ResTeamEnterToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResTeamEnterToClientMessage = ResTeamEnterToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}