package com.game.country.handler{

	import com.game.country.message.ResCountrySiegeWarStateToClientMessage;
	import net.Handler;

	public class ResCountrySiegeWarStateToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResCountrySiegeWarStateToClientMessage = ResCountrySiegeWarStateToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}