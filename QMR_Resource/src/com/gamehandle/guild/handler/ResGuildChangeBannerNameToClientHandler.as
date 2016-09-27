package com.game.guild.handler{

	import com.game.guild.message.ResGuildChangeBannerNameToClientMessage;
	import net.Handler;

	public class ResGuildChangeBannerNameToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGuildChangeBannerNameToClientMessage = ResGuildChangeBannerNameToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}