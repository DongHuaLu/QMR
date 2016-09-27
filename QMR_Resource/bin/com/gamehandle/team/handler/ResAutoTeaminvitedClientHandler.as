package com.game.team.handler{

	import com.game.team.message.ResAutoTeaminvitedClientMessage;
	import net.Handler;

	public class ResAutoTeaminvitedClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResAutoTeaminvitedClientMessage = ResAutoTeaminvitedClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}