package com.game.country.handler{

	import com.game.country.message.ResCountryTopInfoToClientMessage;
	import net.Handler;

	public class ResCountryTopInfoToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResCountryTopInfoToClientMessage = ResCountryTopInfoToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}