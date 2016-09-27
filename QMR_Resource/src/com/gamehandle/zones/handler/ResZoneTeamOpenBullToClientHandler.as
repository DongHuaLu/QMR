package com.game.zones.handler{

	import com.game.zones.message.ResZoneTeamOpenBullToClientMessage;
	import net.Handler;

	public class ResZoneTeamOpenBullToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResZoneTeamOpenBullToClientMessage = ResZoneTeamOpenBullToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}