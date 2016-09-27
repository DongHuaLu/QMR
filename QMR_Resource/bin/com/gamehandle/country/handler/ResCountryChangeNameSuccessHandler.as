package com.game.country.handler{

	import com.game.country.message.ResCountryChangeNameSuccessMessage;
	import net.Handler;

	public class ResCountryChangeNameSuccessHandler extends Handler {
	
		public override function action(): void{
			var msg: ResCountryChangeNameSuccessMessage = ResCountryChangeNameSuccessMessage(this.message);
			//TODO 添加消息处理
		}
	}
}