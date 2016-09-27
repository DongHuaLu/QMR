package com.game.fight.handler{

	import com.game.fight.message.ResFightBroadcastMessage;
	import net.Handler;

	public class ResFightBroadcastHandler extends Handler {
	
		public override function action(): void{
			var msg: ResFightBroadcastMessage = ResFightBroadcastMessage(this.message);
			//TODO 添加消息处理
		}
	}
}