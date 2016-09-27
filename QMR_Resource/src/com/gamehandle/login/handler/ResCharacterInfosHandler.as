package com.game.login.handler{

	import com.game.login.message.ResCharacterInfosMessage;
	import net.Handler;

	public class ResCharacterInfosHandler extends Handler {
	
		public override function action(): void{
			var msg: ResCharacterInfosMessage = ResCharacterInfosMessage(this.message);
			//TODO 添加消息处理
		}
	}
}