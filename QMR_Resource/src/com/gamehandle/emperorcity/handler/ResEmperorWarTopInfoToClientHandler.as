package com.game.emperorcity.handler{

	import com.game.emperorcity.message.ResEmperorWarTopInfoToClientMessage;
	import net.Handler;

	public class ResEmperorWarTopInfoToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResEmperorWarTopInfoToClientMessage = ResEmperorWarTopInfoToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}