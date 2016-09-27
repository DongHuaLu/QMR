package com.game.npc.handler{

	import com.game.npc.message.ResStopGatherMessage;
	import net.Handler;

	public class ResStopGatherHandler extends Handler {
	
		public override function action(): void{
			var msg: ResStopGatherMessage = ResStopGatherMessage(this.message);
			//TODO 添加消息处理
		}
	}
}