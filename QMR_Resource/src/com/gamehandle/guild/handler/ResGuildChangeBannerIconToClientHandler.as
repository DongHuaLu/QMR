package com.game.guild.handler{

	import com.game.guild.message.ResGuildChangeBannerIconToClientMessage;
	import net.Handler;

	public class ResGuildChangeBannerIconToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGuildChangeBannerIconToClientMessage = ResGuildChangeBannerIconToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}