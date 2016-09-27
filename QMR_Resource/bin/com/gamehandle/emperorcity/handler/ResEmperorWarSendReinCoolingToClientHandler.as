package com.game.emperorcity.handler{

	import com.game.emperorcity.message.ResEmperorWarSendReinCoolingToClientMessage;
	import net.Handler;

	public class ResEmperorWarSendReinCoolingToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResEmperorWarSendReinCoolingToClientMessage = ResEmperorWarSendReinCoolingToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}