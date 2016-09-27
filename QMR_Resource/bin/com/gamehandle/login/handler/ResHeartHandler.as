package com.game.login.handler{

	import com.game.login.message.ResHeartMessage;
	import net.Handler;

	public class ResHeartHandler extends Handler {
	
		public override function action(): void{
			var msg: ResHeartMessage = ResHeartMessage(this.message);
			//TODO 添加消息处理
		}
	}
}