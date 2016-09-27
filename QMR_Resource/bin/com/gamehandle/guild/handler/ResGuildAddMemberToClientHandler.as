package com.game.guild.handler{

	import com.game.guild.message.ResGuildAddMemberToClientMessage;
	import net.Handler;

	public class ResGuildAddMemberToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGuildAddMemberToClientMessage = ResGuildAddMemberToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}