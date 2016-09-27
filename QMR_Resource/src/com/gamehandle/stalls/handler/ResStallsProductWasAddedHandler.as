package com.game.stalls.handler{

	import com.game.stalls.message.ResStallsProductWasAddedMessage;
	import net.Handler;

	public class ResStallsProductWasAddedHandler extends Handler {
	
		public override function action(): void{
			var msg: ResStallsProductWasAddedMessage = ResStallsProductWasAddedMessage(this.message);
			//TODO 添加消息处理
		}
	}
}