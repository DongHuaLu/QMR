package com.game.guild.handler{

	import com.game.guild.message.ResGuildAutoArgeeAddGuildToClientMessage;
	import net.Handler;

	public class ResGuildAutoArgeeAddGuildToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGuildAutoArgeeAddGuildToClientMessage = ResGuildAutoArgeeAddGuildToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}