package com.game.zones.handler{

	import com.game.zones.message.ResZoneApplyDataInfoToClientMessage;
	import net.Handler;

	public class ResZoneApplyDataInfoToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResZoneApplyDataInfoToClientMessage = ResZoneApplyDataInfoToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}