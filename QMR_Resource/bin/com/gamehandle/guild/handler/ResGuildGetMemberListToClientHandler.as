package com.game.guild.handler{

	import com.game.guild.message.ResGuildGetMemberListToClientMessage;
	import net.Handler;

	public class ResGuildGetMemberListToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGuildGetMemberListToClientMessage = ResGuildGetMemberListToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}