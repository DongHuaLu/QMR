package com.game.guild.handler{

	import com.game.guild.message.ResGuildAloneGuildInfoToClientMessage;
	import net.Handler;

	public class ResGuildAloneGuildInfoToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGuildAloneGuildInfoToClientMessage = ResGuildAloneGuildInfoToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}