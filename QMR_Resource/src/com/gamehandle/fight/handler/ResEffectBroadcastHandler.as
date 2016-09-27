package com.game.fight.handler{

	import com.game.fight.message.ResEffectBroadcastMessage;
	import net.Handler;

	public class ResEffectBroadcastHandler extends Handler {
	
		public override function action(): void{
			var msg: ResEffectBroadcastMessage = ResEffectBroadcastMessage(this.message);
			//TODO 添加消息处理
		}
	}
}