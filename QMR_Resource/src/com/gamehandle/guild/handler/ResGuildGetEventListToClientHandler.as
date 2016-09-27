package com.game.guild.handler{

	import com.game.guild.message.ResGuildGetEventListToClientMessage;
	import net.Handler;

	public class ResGuildGetEventListToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGuildGetEventListToClientMessage = ResGuildGetEventListToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}