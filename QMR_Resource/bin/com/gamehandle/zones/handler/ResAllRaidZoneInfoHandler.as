package com.game.zones.handler{

	import com.game.zones.message.ResAllRaidZoneInfoMessage;
	import net.Handler;

	public class ResAllRaidZoneInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResAllRaidZoneInfoMessage = ResAllRaidZoneInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}