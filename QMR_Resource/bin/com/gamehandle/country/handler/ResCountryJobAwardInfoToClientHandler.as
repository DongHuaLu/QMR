package com.game.country.handler{

	import com.game.country.message.ResCountryJobAwardInfoToClientMessage;
	import net.Handler;

	public class ResCountryJobAwardInfoToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResCountryJobAwardInfoToClientMessage = ResCountryJobAwardInfoToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}