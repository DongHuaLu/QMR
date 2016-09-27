package com.game.country.handler{

	import com.game.country.message.ReqCountrySiegeSelectToClientMessage;
	import net.Handler;

	public class ReqCountrySiegeSelectToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ReqCountrySiegeSelectToClientMessage = ReqCountrySiegeSelectToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}