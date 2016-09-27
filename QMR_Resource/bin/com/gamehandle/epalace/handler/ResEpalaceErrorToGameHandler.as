package com.game.epalace.handler{

	import com.game.epalace.message.ResEpalaceErrorToGameMessage;
	import net.Handler;

	public class ResEpalaceErrorToGameHandler extends Handler {
	
		public override function action(): void{
			var msg: ResEpalaceErrorToGameMessage = ResEpalaceErrorToGameMessage(this.message);
			//TODO 添加消息处理
		}
	}
}