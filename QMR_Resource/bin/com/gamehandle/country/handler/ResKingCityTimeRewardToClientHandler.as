package com.game.country.handler{

	import com.game.country.message.ResKingCityTimeRewardToClientMessage;
	import net.Handler;

	public class ResKingCityTimeRewardToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResKingCityTimeRewardToClientMessage = ResKingCityTimeRewardToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}