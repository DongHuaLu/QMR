package com.game.zones.handler{

	import com.game.zones.message.ResZoneContinuousRaidsEndMessage;
	import net.Handler;

	public class ResZoneContinuousRaidsEndHandler extends Handler {
	
		public override function action(): void{
			var msg: ResZoneContinuousRaidsEndMessage = ResZoneContinuousRaidsEndMessage(this.message);
			//TODO 添加消息处理
		}
	}
}