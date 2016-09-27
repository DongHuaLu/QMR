package com.game.country.handler{

	import com.game.country.message.ResCountrySiegeHomingYuxiToClientMessage;
	import net.Handler;

	public class ResCountrySiegeHomingYuxiToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResCountrySiegeHomingYuxiToClientMessage = ResCountrySiegeHomingYuxiToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}