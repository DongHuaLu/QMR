package com.game.guild.handler{

	import com.game.guild.message.ResGuildInviteAddDoingToClientMessage;
	import net.Handler;

	public class ResGuildInviteAddDoingToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGuildInviteAddDoingToClientMessage = ResGuildInviteAddDoingToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}