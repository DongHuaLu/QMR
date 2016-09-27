package com.game.player.handler{

	import com.game.player.message.ResPlayerNonageTimeMessage;
	import net.Handler;

	public class ResPlayerNonageTimeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerNonageTimeMessage = ResPlayerNonageTimeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}