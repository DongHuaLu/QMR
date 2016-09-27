package com.game.guild.handler{

	import com.game.guild.message.ResGuildDeleteMemberToClientMessage;
	import net.Handler;

	public class ResGuildDeleteMemberToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGuildDeleteMemberToClientMessage = ResGuildDeleteMemberToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}