package com.game.stalls.handler{

	import com.game.stalls.message.ResStallsPlayerIdLookMessage;
	import net.Handler;

	public class ResStallsPlayerIdLookHandler extends Handler {
	
		public override function action(): void{
			var msg: ResStallsPlayerIdLookMessage = ResStallsPlayerIdLookMessage(this.message);
			//TODO 添加消息处理
		}
	}
}