package com.game.country.handler{

	import com.game.country.message.ResCountrySiegeYuXiImmediateToClientMessage;
	import net.Handler;

	public class ResCountrySiegeYuXiImmediateToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResCountrySiegeYuXiImmediateToClientMessage = ResCountrySiegeYuXiImmediateToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}