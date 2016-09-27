package com.game.arrow.handler{

	import com.game.arrow.message.ResFightSpiritGetNumMessage;
	import net.Handler;

	public class ResFightSpiritGetNumHandler extends Handler {
	
		public override function action(): void{
			var msg: ResFightSpiritGetNumMessage = ResFightSpiritGetNumMessage(this.message);
			//TODO 添加消息处理
		}
	}
}