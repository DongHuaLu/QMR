package com.game.guild.handler{

	import com.game.guild.message.ResGuildGetGuildListToClientMessage;
	import net.Handler;

	public class ResGuildGetGuildListToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGuildGetGuildListToClientMessage = ResGuildGetGuildListToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}