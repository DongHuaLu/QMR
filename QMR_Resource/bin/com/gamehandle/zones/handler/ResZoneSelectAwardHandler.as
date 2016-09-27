package com.game.zones.handler{

	import com.game.zones.message.ResZoneSelectAwardMessage;
	import net.Handler;

	public class ResZoneSelectAwardHandler extends Handler {
	
		public override function action(): void{
			var msg: ResZoneSelectAwardMessage = ResZoneSelectAwardMessage(this.message);
			//TODO 添加消息处理
		}
	}
}