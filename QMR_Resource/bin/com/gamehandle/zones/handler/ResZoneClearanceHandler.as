package com.game.zones.handler{

	import com.game.zones.message.ResZoneClearanceMessage;
	import net.Handler;

	public class ResZoneClearanceHandler extends Handler {
	
		public override function action(): void{
			var msg: ResZoneClearanceMessage = ResZoneClearanceMessage(this.message);
			//TODO 添加消息处理
		}
	}
}