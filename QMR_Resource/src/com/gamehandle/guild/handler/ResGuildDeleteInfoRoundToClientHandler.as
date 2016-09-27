package com.game.guild.handler{

	import com.game.guild.message.ResGuildDeleteInfoRoundToClientMessage;
	import net.Handler;

	public class ResGuildDeleteInfoRoundToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGuildDeleteInfoRoundToClientMessage = ResGuildDeleteInfoRoundToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}