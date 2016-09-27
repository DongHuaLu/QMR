package com.game.guild.handler{

	import com.game.guild.message.ResGuildDeleteGuildToClientMessage;
	import net.Handler;

	public class ResGuildDeleteGuildToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGuildDeleteGuildToClientMessage = ResGuildDeleteGuildToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}