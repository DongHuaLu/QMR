package com.game.country.handler{

	import com.game.country.message.ResCountryWarCarDamageToClientMessage;
	import net.Handler;

	public class ResCountryWarCarDamageToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResCountryWarCarDamageToClientMessage = ResCountryWarCarDamageToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}