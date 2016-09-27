package com.game.country.handler{

	import com.game.country.message.ResCountrySiegeUpYuxiToClientMessage;
	import net.Handler;

	public class ResCountrySiegeUpYuxiToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResCountrySiegeUpYuxiToClientMessage = ResCountrySiegeUpYuxiToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}