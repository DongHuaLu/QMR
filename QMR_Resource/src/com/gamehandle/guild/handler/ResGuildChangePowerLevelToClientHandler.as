package com.game.guild.handler{

	import com.game.guild.message.ResGuildChangePowerLevelToClientMessage;
	import net.Handler;

	public class ResGuildChangePowerLevelToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGuildChangePowerLevelToClientMessage = ResGuildChangePowerLevelToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}