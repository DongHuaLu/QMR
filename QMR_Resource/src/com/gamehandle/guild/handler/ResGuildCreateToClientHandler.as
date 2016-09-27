package com.game.guild.handler{

	import com.game.guild.message.ResGuildCreateToClientMessage;
	import net.Handler;

	public class ResGuildCreateToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGuildCreateToClientMessage = ResGuildCreateToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}