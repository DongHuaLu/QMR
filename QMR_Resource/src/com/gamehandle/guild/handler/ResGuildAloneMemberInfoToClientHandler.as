package com.game.guild.handler{

	import com.game.guild.message.ResGuildAloneMemberInfoToClientMessage;
	import net.Handler;

	public class ResGuildAloneMemberInfoToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGuildAloneMemberInfoToClientMessage = ResGuildAloneMemberInfoToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}