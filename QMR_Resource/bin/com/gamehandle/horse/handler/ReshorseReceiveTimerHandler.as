package com.game.horse.handler{

	import com.game.horse.message.ReshorseReceiveTimerMessage;
	import net.Handler;

	public class ReshorseReceiveTimerHandler extends Handler {
	
		public override function action(): void{
			var msg: ReshorseReceiveTimerMessage = ReshorseReceiveTimerMessage(this.message);
			//TODO 添加消息处理
		}
	}
}