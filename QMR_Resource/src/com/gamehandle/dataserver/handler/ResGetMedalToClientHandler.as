package com.game.dataserver.handler{

	import com.game.dataserver.message.ResGetMedalToClientMessage;
	import net.Handler;

	public class ResGetMedalToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGetMedalToClientMessage = ResGetMedalToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}