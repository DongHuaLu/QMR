package com.game.zones.handler{

	import com.game.zones.message.ResZoneTeamNoticeToClientMessage;
	import net.Handler;

	public class ResZoneTeamNoticeToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResZoneTeamNoticeToClientMessage = ResZoneTeamNoticeToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}