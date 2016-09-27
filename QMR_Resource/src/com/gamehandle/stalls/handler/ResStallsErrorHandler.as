package com.game.stalls.handler{

	import com.game.stalls.message.ResStallsErrorMessage;
	import net.Handler;

	public class ResStallsErrorHandler extends Handler {
	
		public override function action(): void{
			var msg: ResStallsErrorMessage = ResStallsErrorMessage(this.message);
			//TODO 添加消息处理
		}
	}
}