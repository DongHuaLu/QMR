package com.game.zones.handler{

	import com.game.zones.message.ResZoneTeamNoticeSelectToClientMessage;
	import net.Handler;

	public class ResZoneTeamNoticeSelectToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResZoneTeamNoticeSelectToClientMessage = ResZoneTeamNoticeSelectToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}