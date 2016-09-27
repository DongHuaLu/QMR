package com.game.fight.handler{

	import com.game.fight.message.ResFightFailedBroadcastMessage;
	import net.Handler;

	public class ResFightFailedBroadcastHandler extends Handler {
	
		public override function action(): void{
			var msg: ResFightFailedBroadcastMessage = ResFightFailedBroadcastMessage(this.message);
			//TODO 添加消息处理
		}
	}
}