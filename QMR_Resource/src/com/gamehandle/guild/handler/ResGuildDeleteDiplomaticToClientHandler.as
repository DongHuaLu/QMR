package com.game.guild.handler{

	import com.game.guild.message.ResGuildDeleteDiplomaticToClientMessage;
	import net.Handler;

	public class ResGuildDeleteDiplomaticToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGuildDeleteDiplomaticToClientMessage = ResGuildDeleteDiplomaticToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}