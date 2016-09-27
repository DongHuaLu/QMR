package com.game.stalls.handler{

	import com.game.stalls.message.ResStallsOpenUpMessage;
	import net.Handler;

	public class ResStallsOpenUpHandler extends Handler {
	
		public override function action(): void{
			var msg: ResStallsOpenUpMessage = ResStallsOpenUpMessage(this.message);
			//TODO 添加消息处理
		}
	}
}