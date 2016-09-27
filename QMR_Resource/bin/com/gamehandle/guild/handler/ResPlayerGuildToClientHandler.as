package com.game.guild.handler{

	import com.game.guild.message.ResPlayerGuildToClientMessage;
	import net.Handler;

	public class ResPlayerGuildToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerGuildToClientMessage = ResPlayerGuildToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}