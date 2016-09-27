package com.game.country.handler{

	import com.game.country.message.ResKingCityYuXiCoordinateToClientMessage;
	import net.Handler;

	public class ResKingCityYuXiCoordinateToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResKingCityYuXiCoordinateToClientMessage = ResKingCityYuXiCoordinateToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}