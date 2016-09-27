package com.game.player.handler{

	import com.game.player.message.ResPlayerNonageStateMessage;
	import net.Handler;

	public class ResPlayerNonageStateHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerNonageStateMessage = ResPlayerNonageStateMessage(this.message);
			//TODO 添加消息处理
		}
	}
}