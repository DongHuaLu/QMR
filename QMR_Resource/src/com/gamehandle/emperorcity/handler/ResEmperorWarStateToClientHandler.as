package com.game.emperorcity.handler{

	import com.game.emperorcity.message.ResEmperorWarStateToClientMessage;
	import net.Handler;

	public class ResEmperorWarStateToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResEmperorWarStateToClientMessage = ResEmperorWarStateToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}