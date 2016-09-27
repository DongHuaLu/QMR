package com.game.guild.handler{

	import com.game.guild.message.ResGuildSubmitItemToClientMessage;
	import net.Handler;

	public class ResGuildSubmitItemToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGuildSubmitItemToClientMessage = ResGuildSubmitItemToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}