package com.game.zones.handler{

	import com.game.zones.message.ResZoneTimeRecordNoticeMessage;
	import net.Handler;

	public class ResZoneTimeRecordNoticeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResZoneTimeRecordNoticeMessage = ResZoneTimeRecordNoticeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}