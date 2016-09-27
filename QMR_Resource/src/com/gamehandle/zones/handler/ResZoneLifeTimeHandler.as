package com.game.zones.handler{

	import com.game.zones.message.ResZoneLifeTimeMessage;
	import net.Handler;

	public class ResZoneLifeTimeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResZoneLifeTimeMessage = ResZoneLifeTimeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}