package com.game.zones.handler{

	import com.game.zones.message.ResZoneContinuousRaidsMessage;
	import net.Handler;

	public class ResZoneContinuousRaidsHandler extends Handler {
	
		public override function action(): void{
			var msg: ResZoneContinuousRaidsMessage = ResZoneContinuousRaidsMessage(this.message);
			//TODO 添加消息处理
		}
	}
}