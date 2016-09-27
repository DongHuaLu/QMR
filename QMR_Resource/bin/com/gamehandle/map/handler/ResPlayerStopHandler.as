package com.game.map.handler{

	import com.game.map.message.ResPlayerStopMessage;
	import net.Handler;

	public class ResPlayerStopHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerStopMessage = ResPlayerStopMessage(this.message);
			//TODO 添加消息处理
		}
	}
}