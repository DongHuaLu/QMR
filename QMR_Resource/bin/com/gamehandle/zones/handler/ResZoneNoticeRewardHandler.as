package com.game.zones.handler{

	import com.game.zones.message.ResZoneNoticeRewardMessage;
	import net.Handler;

	public class ResZoneNoticeRewardHandler extends Handler {
	
		public override function action(): void{
			var msg: ResZoneNoticeRewardMessage = ResZoneNoticeRewardMessage(this.message);
			//TODO 添加消息处理
		}
	}
}