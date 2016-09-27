package com.game.guild.handler{

	import com.game.guild.message.ResGuildQuitToClientMessage;
	import net.Handler;

	public class ResGuildQuitToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGuildQuitToClientMessage = ResGuildQuitToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}