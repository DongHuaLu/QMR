package com.game.guild.handler{

	import com.game.guild.message.ResGuildGetGuildShortInfoListToClientMessage;
	import net.Handler;

	public class ResGuildGetGuildShortInfoListToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGuildGetGuildShortInfoListToClientMessage = ResGuildGetGuildShortInfoListToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}