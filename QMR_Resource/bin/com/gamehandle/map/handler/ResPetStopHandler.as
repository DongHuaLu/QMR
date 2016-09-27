package com.game.map.handler{

	import com.game.map.message.ResPetStopMessage;
	import net.Handler;

	public class ResPetStopHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPetStopMessage = ResPetStopMessage(this.message);
			//TODO 添加消息处理
		}
	}
}