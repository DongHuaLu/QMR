package com.game.zones.handler{

	import com.game.zones.message.ResZonePassShowMessage;
	import net.Handler;

	public class ResZonePassShowHandler extends Handler {
	
		public override function action(): void{
			var msg: ResZonePassShowMessage = ResZonePassShowMessage(this.message);
			//TODO 添加消息处理
		}
	}
}