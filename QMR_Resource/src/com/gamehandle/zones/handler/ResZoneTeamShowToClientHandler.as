package com.game.zones.handler{

	import com.game.zones.message.ResZoneTeamShowToClientMessage;
	import net.Handler;

	public class ResZoneTeamShowToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResZoneTeamShowToClientMessage = ResZoneTeamShowToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}