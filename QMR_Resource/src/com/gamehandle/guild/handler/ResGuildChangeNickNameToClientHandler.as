package com.game.guild.handler{

	import com.game.guild.message.ResGuildChangeNickNameToClientMessage;
	import net.Handler;

	public class ResGuildChangeNickNameToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGuildChangeNickNameToClientMessage = ResGuildChangeNickNameToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}