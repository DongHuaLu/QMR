package com.game.zones.handler{

	import com.game.zones.message.ResZoneKillProgressRateMessage;
	import net.Handler;

	public class ResZoneKillProgressRateHandler extends Handler {
	
		public override function action(): void{
			var msg: ResZoneKillProgressRateMessage = ResZoneKillProgressRateMessage(this.message);
			//TODO 添加消息处理
		}
	}
}