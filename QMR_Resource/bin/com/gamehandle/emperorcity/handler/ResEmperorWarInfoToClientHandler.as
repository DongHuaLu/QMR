package com.game.emperorcity.handler{

	import com.game.emperorcity.message.ResEmperorWarInfoToClientMessage;
	import net.Handler;

	public class ResEmperorWarInfoToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResEmperorWarInfoToClientMessage = ResEmperorWarInfoToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}