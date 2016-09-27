package com.game.npc.handler{

	import com.game.npc.message.ResStartGatherMessage;
	import net.Handler;

	public class ResStartGatherHandler extends Handler {
	
		public override function action(): void{
			var msg: ResStartGatherMessage = ResStartGatherMessage(this.message);
			//TODO 添加消息处理
		}
	}
}