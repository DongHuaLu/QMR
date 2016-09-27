package com.game.country.handler{

	import com.game.country.message.ResKingCityChestSelectToClientMessage;
	import net.Handler;

	public class ResKingCityChestSelectToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResKingCityChestSelectToClientMessage = ResKingCityChestSelectToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}