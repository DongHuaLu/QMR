package com.game.country.handler{

	import com.game.country.message.ResKingCityChestPanelShowToClientMessage;
	import net.Handler;

	public class ResKingCityChestPanelShowToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResKingCityChestPanelShowToClientMessage = ResKingCityChestPanelShowToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}