package com.game.country.handler{

	import com.game.country.message.ResCountryArtilleryLocusToClientMessage;
	import net.Handler;

	public class ResCountryArtilleryLocusToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResCountryArtilleryLocusToClientMessage = ResCountryArtilleryLocusToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}