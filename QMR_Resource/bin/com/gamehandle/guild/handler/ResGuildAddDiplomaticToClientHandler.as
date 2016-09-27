package com.game.guild.handler{

	import com.game.guild.message.ResGuildAddDiplomaticToClientMessage;
	import net.Handler;

	public class ResGuildAddDiplomaticToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGuildAddDiplomaticToClientMessage = ResGuildAddDiplomaticToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}