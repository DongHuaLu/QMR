package com.game.guild.handler{

	import com.game.guild.message.ResGuildAutoGuildArgeeAddToClientMessage;
	import net.Handler;

	public class ResGuildAutoGuildArgeeAddToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGuildAutoGuildArgeeAddToClientMessage = ResGuildAutoGuildArgeeAddToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}