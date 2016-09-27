package com.game.country.handler{

	import com.game.country.message.ResCountryStructureInfoToClientMessage;
	import net.Handler;

	public class ResCountryStructureInfoToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResCountryStructureInfoToClientMessage = ResCountryStructureInfoToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}