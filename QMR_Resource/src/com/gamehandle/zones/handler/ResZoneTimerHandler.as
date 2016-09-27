package com.game.zones.handler{

	import com.game.zones.message.ResZoneTimerMessage;
	import net.Handler;

	public class ResZoneTimerHandler extends Handler {
	
		public override function action(): void{
			var msg: ResZoneTimerMessage = ResZoneTimerMessage(this.message);
			//TODO 添加消息处理
		}
	}
}