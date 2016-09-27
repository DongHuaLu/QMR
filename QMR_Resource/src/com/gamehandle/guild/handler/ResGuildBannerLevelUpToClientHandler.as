package com.game.guild.handler{

	import com.game.guild.message.ResGuildBannerLevelUpToClientMessage;
	import net.Handler;

	public class ResGuildBannerLevelUpToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGuildBannerLevelUpToClientMessage = ResGuildBannerLevelUpToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}