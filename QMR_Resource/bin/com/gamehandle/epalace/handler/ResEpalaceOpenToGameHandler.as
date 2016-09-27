package com.game.epalace.handler{

	import com.game.epalace.message.ResEpalaceOpenToGameMessage;
	import net.Handler;

	public class ResEpalaceOpenToGameHandler extends Handler {
	
		public override function action(): void{
			var msg: ResEpalaceOpenToGameMessage = ResEpalaceOpenToGameMessage(this.message);
			//TODO 添加消息处理
		}
	}
}